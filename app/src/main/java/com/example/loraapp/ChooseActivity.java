package com.example.loraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ChooseActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    Intent intentScan, intentTrans;
    String url = "http://192.168.50.170:5000";
    int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_choose, mapFragment)
                .commit();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void gotoMain(View v) {
        Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void gotoQRCheckRegister(View v) {
        intentScan = new Intent(ChooseActivity.this, QrCodeActivity.class);
        intentScan.putExtra("mode", 1);
        startActivityForResult(intentScan, REQUEST_CODE_QR_SCAN);
        mode = 1;
    }

    public void gotoQRCheckTransfer(View v) {
        intentTrans = new Intent(ChooseActivity.this, QrCodeActivity.class);
        intentTrans.putExtra("mode", 2);
        startActivityForResult(intentTrans, REQUEST_CODE_QR_SCAN);
        mode = 2;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Log.d("SCAN", "COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(ChooseActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");

            double latitude=0, longitude=0;
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (lm != null) {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if (location != null) {
                    latitude = location.getLongitude();
                    longitude = location.getLatitude();
                    Log.d("DATA", "Have scan result in your app activity :" + result + " " + latitude + " " + longitude);
                }
            }

            ServerConnection conn = new ServerConnection(url, this);
            if(mode == 1){
                conn.sendPos(result, new DeviceCoords(latitude, longitude));
            }
            else {
                if (conn.checkPos(result, latitude, longitude)){
                    Intent intent = new Intent(ChooseActivity.this, CheckSuccessActivity.class);
                    intent.putExtra("device_code", result);
                    startActivity(intent);
                }
            }
        }
    }
}