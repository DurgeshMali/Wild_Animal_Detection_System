package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingupActivity extends AppCompatActivity {
    EditText name, password, repassword, phonenno;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        name = findViewById(R.id.editTextText3);
        phonenno = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextText);
        repassword = findViewById(R.id.editTextPassword);

        signUpButton = findViewById(R.id.button2);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Please enter the Name");
                    return;
                }
                if(TextUtils.isEmpty(phonenno.getText().toString())) {
                    phonenno.setError("Please Enter Phone no.");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Please enter the Name");
                    return;
                }
                if(TextUtils.isEmpty(repassword.getText().toString())) {
                    repassword.setError("Please Enter Phone no.");
                    return;
                }
                Toast.makeText(SingupActivity.this, "Singup Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SingupActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}