package com.tubes.sandangin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String mag = "Successful";
                        if(!task.isSuccessful()) {
                            mag = "Failed";
                        }
                        Toast.makeText(MainActivity.this, mag, Toast.LENGTH_SHORT).show();
                    }
                });

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

    public void openCamera(View view){
        Intent intent = new Intent(this, CameraOperation.class);
        startActivity(intent);
    }

    public void openShop(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}