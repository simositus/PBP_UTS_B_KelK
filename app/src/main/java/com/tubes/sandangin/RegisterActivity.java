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

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout til_name, til_email, til_user, til_pass, til_confpass;
    private TextInputEditText ti_name, ti_email, ti_user, ti_pass, ti_confpass;
    private TextView signin;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* Menginisialisasi variable dengan Form Name, Email, User, Form Password, dan Form Repassword
        dari Layout RegisterActivity */
        ti_name = findViewById(R.id.ti_name_signup);
        ti_email = findViewById(R.id.ti_email_signup);
        ti_user = findViewById(R.id.ti_user_signup);
        ti_pass = findViewById(R.id.ti_pass_signup);
        ti_confpass = findViewById(R.id.ti_confpass_signup);
        til_name = findViewById(R.id.til_name_signup);
        til_email = findViewById(R.id.til_email_signup);
        til_user = findViewById(R.id.til_user_signup);
        til_pass = findViewById(R.id.til_pass_signup);
        til_confpass = findViewById(R.id.til_confpass_signup);
        signin = findViewById(R.id.button_signinSignup);
        signup = findViewById(R.id.button_signupSignup);

        /* Menjalankan Method cekin() jika merasakan tombol SignUp di keyboard disentuh */
        ti_confpass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    cekin();
                    return true;
                }
                return false;
            }
        });

        /* Menjalankan Method cekin() jika merasakan tombol SignUp disentuh */
        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekin();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity */
    private void cekin(){
        /* Mereset semua Error dan fokus menjadi default */
        til_name.setErrorEnabled(false);
        til_name.setError(null);
        til_email.setErrorEnabled(false);
        til_email.setError(null);
        til_user.setErrorEnabled(false);
        til_user.setError(null);
        til_pass.setErrorEnabled(false);
        til_pass.setError(null);
        til_confpass.setErrorEnabled(false);
        til_confpass.setError(null);

        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String name= ti_name.getText().toString();
        String email = ti_email.getText().toString();
        String username = ti_user.getText().toString();
        String password = ti_pass.getText().toString();
        String confirpass = ti_confpass.getText().toString();

        /* Jika form name kosong di Method cekUserName() maka, Set error di Form-
         * UserName dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
            if (TextUtils.isEmpty(username)){
                ti_user.setError("This field is required");
                fokus = ti_user;
                cancel = true;
            }else if(cekUser(username)){
                ti_user.setError("This Username is already exist");
                fokus = ti_user;
                cancel = true;
            }

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekName() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(name)){
            ti_name.setError("This field is required");
            fokus = ti_name;
            cancel = true;
        }

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekName() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(email)){
            ti_email.setError("This field is required");
            fokus = ti_email;
            cancel = true;
        }else if(!isValidEmail(email)){
            til_email.setErrorEnabled(true);
            til_email.setError("Email tidak valid");
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)){
            ti_pass.setError("This field is required");
            fokus = ti_pass;
            cancel = true;
        }else if (!cekPassword(password,confirpass)){
            ti_confpass.setError("This password is incorrect");
            fokus = ti_confpass;
            cancel = true;
        }

        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(),username);
            Preferences.setRegisteredPass(getBaseContext(),password);
            Preferences.setRegisteredName(getBaseContext(),name);
            Preferences.setRegisteredEmail(getBaseContext(),email);
            finish();
        }
    }

    /** validasi email */
    public final static boolean isValidEmail(CharSequence target)
    {
        if (TextUtils.isEmpty(target))
        {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    /** True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password, String confirpass){
        return password.equals(confirpass);
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
