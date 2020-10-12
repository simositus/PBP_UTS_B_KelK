package com.tubes.sandangin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Tshirt> tshirts;
    private TshirtViewAdapter adapter;
    Tshirt tees;
    private GridView gridView;
    private String CHANNEL_ID = "Channel 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Tshirt> ListTshirt;

        //get data tshirt
        tshirts = new TshirtList().tshirts;

        //recycler view
        gridView = findViewById(R.id.gridview);
        adapter = new TshirtViewAdapter(ListActivity.this, tshirts);
        gridView.setAdapter(adapter);

        Button buttonBuy = findViewById(R.id.buttonBuy);
//        buttonBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createNotificationChannel();
//                addNotification();
//            }
//        });
    }
    public void toastBuy(String buy) {
        Toast toast = Toast.makeText(this, buy, Toast.LENGTH_LONG);
        toast.show();
    }

    public void displayBuy(View v) {
        toastBuy("Terimakasih Sudah Membeli Item Ini :D");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_store_24)
                .setContentTitle("ITEM BERHASIL DIBELI")
                .setContentText("Terima Kasih Telah Membeli Produk Sandangin :D")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}