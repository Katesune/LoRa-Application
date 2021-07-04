package com.example.loraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;

public class CheckQRCodeActivity extends AppCompatActivity {

    int mode;
    String status;
    Button message_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_q_r_code);

        message_butt = findViewById(R.id.message);
        mode = getIntent().getIntExtra("mode", 1);
        status = "transfer successful";

        if (mode == 1) {
            checkQRRegister();
        }  else checkQRTransfer();

    }

    protected void checkQRRegister() {

        switch(status) {
            case ("register filed"):
                Toast.makeText(CheckQRCodeActivity.this, "FILED", Toast.LENGTH_SHORT).show();
                message_butt.setBackgroundResource(R.drawable.registfiled);
                break;
            case ("register successful"):
                Toast.makeText(CheckQRCodeActivity.this, "Register is successful", Toast.LENGTH_SHORT).show();
                Intent reg_intent = new Intent(CheckQRCodeActivity.this, RegisterSuccessActivity.class);
                startActivity(reg_intent);
                break;
            default:
                break;
        }
    }

    protected void checkQRTransfer() {
        switch(status) {
            case ("auth filed"):
                Toast.makeText(CheckQRCodeActivity.this, "FILED", Toast.LENGTH_SHORT).show();
                message_butt.setBackgroundResource(R.drawable.unauthmessage);
                Intent un_auth_intent = new Intent(CheckQRCodeActivity.this, TransferSuccessActivity.class);
                startActivity(un_auth_intent);
                break;
            case ("transfer successful"):
                Toast.makeText(CheckQRCodeActivity.this, "Transfer is successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CheckQRCodeActivity.this, TransferSuccessActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void gotoChooseActivity(View v) {
        Intent intent = new Intent(CheckQRCodeActivity.this, ChooseActivity.class);
        startActivity(intent);
    }
}