package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText newPassword, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newPassword = findViewById(R.id.editTextText);
        confirmPassword = findViewById(R.id.editTextPassword);
        String mobileno = getIntent().getStringExtra("mobile").trim();

        FirebaseFirestore.getInstance().collection("Users").document(mobileno).update("password",newPassword);
    }
}