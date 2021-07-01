package com.example.loraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TransferSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_success);
    }

    public void gotoQRActivity(View v) {
        Intent intent = new Intent(TransferSuccessActivity.this, CheckQRCodeActivity.class);
        startActivity(intent);
    }

    public void gotoSensors(View v) {
        String role = checkRole();
        Intent intent = new Intent();

        if (role.equals("mechanic"))  intent = new Intent(TransferSuccessActivity.this, SensorMechanicActivity.class);
        else  intent = new Intent(TransferSuccessActivity.this, SensorEngineerActivity.class);

        startActivity(intent);
    }

    protected String checkRole() {
        Role r = ((Role)getApplicationContext());
        return r.getRole();
    }

}