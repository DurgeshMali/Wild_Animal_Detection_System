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

public class EditProfileActivity extends AppCompatActivity {
    EditText editName, editPassword;
    String userName, userPassword, userPhone;
    Button editBTN;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        editName = findViewById(R.id.textView11);
        editPassword = findViewById(R.id.textView12);
        editBTN = findViewById(R.id.button3);

        showData();

        editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((isNameChanged() && isPasswordChanged()) || isNameChanged() || isPasswordChanged()){
                    Toast.makeText(EditProfileActivity.this, getString(R.string.data_updated), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userPassword", userPassword);
                    intent.putExtra("userPhone", userPhone);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditProfileActivity.this, getString(R.string.no_changes_found), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isNameChanged() {
        if (!userName.equals(editName.getText().toString())){
            reference.child(userPhone).child("name").setValue(editName.getText().toString());
            userName = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPasswordChanged() {
        if (!userPassword.equals(editPassword.getText().toString())){
            reference.child(userPhone).child("password").setValue(editPassword.getText().toString());
            userPassword = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData() {
        Intent intent = getIntent();

        userName = intent.getStringExtra("name");
        userPassword = intent.getStringExtra("password");
        userPhone = intent.getStringExtra("phone");

        editName.setText(userName);
        editPassword.setText(userPassword);
    }
}