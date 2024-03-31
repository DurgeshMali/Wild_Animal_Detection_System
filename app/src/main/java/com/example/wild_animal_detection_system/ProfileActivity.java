package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.textView6);
        password = findViewById(R.id.textView10);
        phone = findViewById(R.id.textView12);

        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("userName");
        String passwordUser = intent.getStringExtra("userPassword");
        String phoneUser = intent.getStringExtra("userPhone");

        name.setText(nameUser);
        password.setText(passwordUser);
        phone.setText(phoneUser);
    }
}