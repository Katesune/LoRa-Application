package com.example.loraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
    }

    public void gotoQRActivity(View v) {
        Intent intent = new Intent(RegisterSuccessActivity.this, CheckQRCodeActivity.class);
        startActivity(intent);
    }

    public void gotoAddSensorInfo(View v) {
        Intent intent = new Intent(RegisterSuccessActivity.this, AddSensorActivity.class);
        startActivity(intent);
    }
}