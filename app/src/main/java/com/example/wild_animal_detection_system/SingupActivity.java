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

public class SingupActivity extends AppCompatActivity {
    EditText nameETV, passwordETV, phonenoETV;
    Button signUpButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        nameETV = findViewById(R.id.editTextText3);
        phonenoETV = findViewById(R.id.editTextText2);
        passwordETV = findViewById(R.id.editTextText);
        signUpButton = findViewById(R.id.button_3);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Users");

                String name = nameETV.getText().toString();
                String phone = phonenoETV.getText().toString();
                String password = passwordETV.getText().toString();

                if(validateInfo(name, phone, password)) {
                    Users users = new Users(name, phone, password);
                    reference.child(phone).setValue(users);

                    Toast.makeText(SingupActivity.this, getString(R.string.account_created_successfully), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SingupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInfo(String name, String phone, String password) {
        if(name.length() == 0) {
            nameETV.requestFocus();
            nameETV.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        else if(!name.matches("[a-zA-Z]+")) {
            nameETV.requestFocus();
            nameETV.setError(getString(R.string.enter_only_alphabates));
            return false;
        }
        else if(phone.length() == 0) {
            phonenoETV.requestFocus();
            phonenoETV.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        else if(phone.length() < 10 || phone.length() > 10 || phone.charAt(0) < 54 ) {
            phonenoETV.requestFocus();
            phonenoETV.setError(getString(R.string.invalid_phone_number));
            return false;
        }
        else if(password.length() == 0) {
            passwordETV.requestFocus();
            passwordETV.setError(getString(R.string.field_cannot_be_empty));
            return false;
        }
        else if(password.length() < 6) {
            passwordETV.requestFocus();
            passwordETV.setError(getString(R.string.minimum_6_character_required));
            return false;
        }
        return true;
    }
}