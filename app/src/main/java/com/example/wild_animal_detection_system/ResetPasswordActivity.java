package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText newPassword, confirmPassword;
    Button resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newPassword = findViewById(R.id.editTextText);
        confirmPassword = findViewById(R.id.editTextPassword);
        resetPassword = findViewById(R.id.button_3);
        String mobileno = getIntent().getStringExtra("mobile");

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPass = newPassword.getText().toString().trim();
                String confPass = confirmPassword.getText().toString().trim();

                if(validPassword(newPass,confPass)) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(mobileno).child("password").setValue(newPass);
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.password_reset_successfully),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validPassword(String newPass, String confPass) {
        if(newPass.length() == 0) {
            newPassword.requestFocus();
            newPassword.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        else if(newPass.length() < 6) {
            newPassword.requestFocus();
            newPassword.setError(getString(R.string.minimum_6_character_required));
            return false;
        }
        else if(confirmPassword.length() == 0) {
            confirmPassword.requestFocus();
            confirmPassword.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        else if(!newPass.equals(confPass)) {
            confirmPassword.requestFocus();
            confirmPassword.setError(getString(R.string.confirm_password_and_new_password_should_be_same));
            return false;
        }
        return true;
    }
}