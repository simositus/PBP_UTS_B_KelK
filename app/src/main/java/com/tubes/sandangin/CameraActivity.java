package com.tubes.sandangin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class CameraActivity extends AppCompatActivity implements SensorEventListener, EasyPermissions.PermissionCallbacks{

    //Accelerometer
    private long lastUpdate=0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD=600;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static final String TAG = "Camera Activity";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        CameraPermission();
    }

    @Override
    public void onSensorChanged(@NonNull SensorEvent sensorEvent){

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[0];
            float z = sensorEvent.values[0];
            long curTime = System.currentTimeMillis();

            if ((curTime-lastUpdate)>100){
                long diffTime = (curTime-lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x+y+z-last_x-last_y-last_z)/diffTime*10000;
                if (speed>SHAKE_THRESHOLD){
                    Toast.makeText(getApplicationContext(), "SHAKE", Toast.LENGTH_SHORT).show();
                    Log.d("key", "asd");
                    Intent i = new Intent(CameraActivity.this, CameraOperation.class);
                    startActivity(i);
                }
                last_x=x;
                last_y=y;
                last_z=z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    //    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_CODE)
    private void CameraPermission(){
//        Log.d(TAG, "cameraPermission: asking user for permission");
        String[] permission = {Manifest.permission.CAMERA};

        if(EasyPermissions.hasPermissions(this, permission)){
            Toast.makeText(this, "Kamera Siap Pakai", Toast.LENGTH_SHORT).show();
        }else {
            EasyPermissions.requestPermissions(this, "Kamera Gagal Diakses",
                    REQUEST_CODE, permission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(REQUEST_CODE, permissions,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}
