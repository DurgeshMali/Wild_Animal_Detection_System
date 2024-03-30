package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText phonenoETV, passwordETV;
    Button loginButton,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phonenoETV = findViewById(R.id.editTextText);
        passwordETV = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.button2);
        signUp = findViewById(R.id.button_sign_up);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phonenoETV.getText().toString();
                String password = passwordETV.getText().toString();

                if(validateInfo(phone, password)) {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SingupActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInfo(String phone, String password) {
        if(phone.length() == 0) {
            phonenoETV.requestFocus();
            phonenoETV.setError("Field cannot be empty");
            return false;
        }
        else if(phone.length() < 10 || phone.length() > 10 || phone.charAt(0) < 54 ) {
            phonenoETV.requestFocus();
            phonenoETV.setError("Invalid phone number");
            return false;
        }
        else if(password.length() == 0) {
            passwordETV.requestFocus();
            passwordETV.setError("Field cannot be empty");
            return false;
        }
        else if(password.length() < 6) {
            passwordETV.requestFocus();
            passwordETV.setError("Invalid Password");
            return false;
        }
        return true;
    }
}