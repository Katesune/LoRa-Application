package com.example.loraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddSensorActivity extends AppCompatActivity {

    String role;

    EditText tv_type;
    EditText tv_period;
    EditText tv_sensor_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        tv_type = findViewById(R.id.type);
        tv_period = findViewById(R.id.period);
        tv_sensor_group = findViewById(R.id.sensor_group);
    }

    public void gotoRegisterSuccess(View v) {
        Intent intent = new Intent(AddSensorActivity.this, RegisterSuccessActivity.class);
        startActivity(intent);
    }

    public void gotoSensors(View v) {

//        String type = tv_type.getText().toString();
//        String period = tv_period.getText().toString();
//        String sensor_group = tv_sensor_group.getText().toString();

        String role = checkRole();
        Intent intent;

        if (role.equals("mechanic"))  intent = new Intent(AddSensorActivity.this, SensorMechanicActivity.class);
        else  intent = new Intent(AddSensorActivity.this, SensorEngineerActivity.class);

        startActivity(intent);
    }

    protected String checkRole() {
        Role r = ((Role)getApplicationContext());
        return r.getRole();
    }
}