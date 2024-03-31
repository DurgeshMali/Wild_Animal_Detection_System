package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView userName;
    CardView myProfile, language, liveFeed, customizeCamera, contactUs, help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName = findViewById(R.id.title_view);
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("userName");
        userName.setText("Welcome "+nameUser);

        myProfile = findViewById(R.id.CardView1);
        language = findViewById(R.id.CardView2);
        liveFeed = findViewById(R.id.CardView3);
        customizeCamera = findViewById(R.id.CardView4);
        contactUs = findViewById(R.id.CardView5);
        help = findViewById(R.id.CardView6);

    }


}