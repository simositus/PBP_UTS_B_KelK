package com.tubes.sandangin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Deklarasi dan Menginisialisasi variable nama dengan Label user dari Layout MainActivity */
        TextView user = findViewById(R.id.tv_userMain);
//        TextView nama = findViewById(R.id.tv_namaMain);
//        TextView email = findViewById(R.id.tv_emailMain);

        /* Men-set Label data User, nama, dan email sedang login dari Preferences */
        /**Untuk fungsi nama dan email bisa langsung masuk ke data profil*/
        user.setText(Preferences.getLoggedInUser(getBaseContext()));
//        nama.setText(Preferences.getLoggedInName(getBaseContext()));
//        email.setText(Preferences.getLoggedInEmail(getBaseContext()));

        /* Men-set Status dan User yang sedang login menjadi default atau kosong di
         * Data Preferences. Kemudian menuju ke LoginActivity*/
        findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menghapus Status login dan kembali ke Login Activity
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                finish();
            }
        });
    }
    public void openShop(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}