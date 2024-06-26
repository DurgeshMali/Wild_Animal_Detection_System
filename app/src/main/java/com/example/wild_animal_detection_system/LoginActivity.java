package com.example.wild_animal_detection_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText phonenoETV, passwordETV;
    Button loginButton,forgotPassword;
    TextView signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phonenoETV = findViewById(R.id.editTextText);
        passwordETV = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.button_3);
        signupButton = findViewById(R.id.button_sign_up);
        forgotPassword = findViewById(R.id.button_forgot_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phonenoETV.getText().toString();
                String password = passwordETV.getText().toString();

                if(validateInfo(phone, password)) {
                    checkUser();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SingupActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validateInfo(String phone, String password) {
        if(phone.length() == 0) {
            phonenoETV.requestFocus();
            phonenoETV.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        else if(password.length() == 0) {
            passwordETV.requestFocus();
            passwordETV.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        return true;
    }

    public void checkUser() {
        String phone = phonenoETV.getText().toString().trim();
        String password = passwordETV.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUserDatabase = reference.orderByChild("phoneNo").equalTo(phone);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    phonenoETV.setError(null);
                    String passwordFromDB = snapshot.child(phone).child("password").getValue(String.class);

                    if(passwordFromDB.equals(password)) {
                        phonenoETV.setError(null);

                        // pass the data using Intent
                        String nameFromDB = snapshot.child(phone).child("name").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(phone).child("phoneNo").getValue(String.class);
                        String passwordNoFromDB = snapshot.child(phone).child("password").getValue(String.class);

                        Toast.makeText(LoginActivity.this, getString(R.string.login_successfully), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                        intent.putExtra("userName", nameFromDB);
                        intent.putExtra("userPhone", phoneNoFromDB);
                        intent.putExtra("userPassword", passwordNoFromDB);
                        startActivity(intent);
                    }
                    else {
                        passwordETV.requestFocus();
                        passwordETV.setError(getString(R.string.invalid_password));
                    }
                }
                else {
                    phonenoETV.requestFocus();
                    phonenoETV.setError(getString(R.string.user_does_not_exist));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}