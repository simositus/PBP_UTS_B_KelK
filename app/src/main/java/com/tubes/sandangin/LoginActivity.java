package com.tubes.sandangin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout til_user, til_pass;
    private TextInputEditText ti_user, ti_pass;
    private TextView signup;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        ti_user = findViewById(R.id.ti_user_signin);
        ti_pass = findViewById(R.id.ti_pass_signin);
        til_user = findViewById(R.id.til_user_signin);
        til_pass = findViewById(R.id.til_pass_signin);
        signin = findViewById(R.id.button_signinSignin);
        signup = findViewById(R.id.button_signupSignin);

        /* Menjalankan Method cekin() Jika tombol SignIn di keyboard di sentuh */
        ti_pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    cekin();
                    return true;
                }
                return false;
            }
        });

        /* Menjalankan Method cekin() jika merasakan tombol SignIn disentuh */
        findViewById(R.id.button_signinSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekin();
            }
        });

        /* Ke RegisterActivity jika merasakan tombol SignUp disentuh */
        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
            }
        });
    }

    /** ke MainActivity jika data Status Login dari Data Preferences bernilai true */
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity */
    private void cekin() {
        /* Mereset semua Error dan fokus menjadi default */

        til_user.setErrorEnabled(false);
        til_user.setError(null);
        til_pass.setErrorEnabled(false);
        til_pass.setError(null);

        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari form User dan form Password dengan variable baru bertipe String*/
        String username = ti_user.getText().toString();
        String password = ti_pass.getText().toString();

        /* Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka, Set error
         *  di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(username)) {
            ti_user.setError("This field is required");
            fokus = ti_user;
            cancel = true;
        } else if(!cekUser(username)) {
            ti_user.setError("This Username is not found");
            fokus = ti_user;
            cancel = true;
        }

            /* Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password*/
        if (TextUtils.isEmpty(password)) {
            ti_pass.setError("This field is required");
            fokus = ti_pass;
            cancel = true;
        } else if (!cekPassword(password)) {
            ti_pass.setError("This password is incorrect");
            fokus = ti_pass;
            cancel = true;
        }

        /* Jika cancel true, variable fokus mendapatkan fokus */
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences */
    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInName(getBaseContext(),Preferences.getRegisteredName(getBaseContext()));
        Preferences.setLoggedInEmail(getBaseContext(),Preferences.getRegisteredEmail(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));finish();
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String username){
        return username.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //hide keyboard when touch other view
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}