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
    EditText nameETV, passwordETV, phonenoETV;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        nameETV = findViewById(R.id.editTextText3);
        phonenoETV = findViewById(R.id.editTextText2);
        passwordETV = findViewById(R.id.editTextText);
        signUpButton = findViewById(R.id.button2);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameETV.getText().toString();
                String phone = phonenoETV.getText().toString();
                String password = passwordETV.getText().toString();

                if(validateInfo(name, phone, password)) {
                    Toast.makeText(SingupActivity.this, "Singup Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SingupActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInfo(String name, String phone, String password) {
        if(name.length() == 0) {
            nameETV.requestFocus();
            nameETV.setError("Field cannot be empty");
            return false;
        }
        else if(!name.matches("[a-zA-Z]+")) {
            nameETV.requestFocus();
            nameETV.setError("Enter only Alphabates");
            return false;
        }
        else if(phone.length() == 0) {
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
            passwordETV.setError("Minimum 6 character required");
            return false;
        }
        return true;
    }
}