package com.example.loraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText tv_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_role = findViewById(R.id.role);
    }

    public void gotoChooseActivity(View v) {
        String role = tv_role.getText().toString().replace(" ", "");

        Role r = ((Role)getApplicationContext());
        r.setRole("engineer");

        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        startActivity(intent);


//        if (!role.isEmpty()) {
//
//            Role r = ((Role) getApplicationContext());
//            r.setRole(role);
//
//            if (role.equals("mechanic")) {
//                Intent intent = new Intent(MainActivity.this, DataActivity.class);
//                startActivity(intent);
//            } else if (role.equals("engineer")) {
//                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
//                startActivity(intent);
//            } else Toast.makeText(MainActivity.this, "This role doesn't exist", Toast.LENGTH_SHORT).show();
//        }
    }
}