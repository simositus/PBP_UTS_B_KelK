package com.tubes.sandangin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //otomatis pindah activity setelah waktu tertentu
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 2000L);
    }
}

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    sleep(4000);
//                    Intent i = new Intent(Splash.this, MainActivity.class);
//                    startActivity(i);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
