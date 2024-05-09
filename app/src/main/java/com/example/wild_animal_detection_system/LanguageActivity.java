package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private Button btnEnglish, btnHindi, btnGujarati, btnMarathi, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        btnEnglish = findViewById(R.id.button1);
        btnHindi = findViewById(R.id.button_3);
        btnGujarati = findViewById(R.id.button3);
        btnMarathi = findViewById(R.id.button4);
        btnCancel = findViewById(R.id.button5);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage("en");
                startActivity(new Intent(LanguageActivity.this,MainActivity.class));
            }
        });

        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage("hi");
                startActivity(new Intent(LanguageActivity.this,MainActivity.class));
            }
        });

        btnGujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage("gu");
                startActivity(new Intent(LanguageActivity.this,MainActivity.class));
            }
        });

        btnMarathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage("mr");
                startActivity(new Intent(LanguageActivity.this,MainActivity.class));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LanguageActivity.this,MainActivity.class));
            }
        });
    }

    public void setLanguage(String languageCode) {
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }
}