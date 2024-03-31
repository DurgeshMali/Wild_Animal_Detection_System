package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName = findViewById(R.id.title_view);
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("userName");
        userName.setText("Welcome "+nameUser);
    }


}