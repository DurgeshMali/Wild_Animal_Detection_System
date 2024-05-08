package com.example.wild_animal_detection_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText phone_No;
    Button getOtpButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        phone_No = findViewById(R.id.editTextText);
        getOtpButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progress_circular);

        getOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneno = phone_No.getText().toString();

                if(validateInfo(phoneno)) {
                    checkUser();
                }
            }
        });
    }

    public boolean validateInfo(String phoneno) {
        if(phoneno.length() == 0) {
            phone_No.requestFocus();
            phone_No.setError("Field cannot be empty");
            return false;
        }
        else if(phoneno.length() < 10 || phoneno.length() > 10 || phoneno.charAt(0) < 54 ) {
            phone_No.requestFocus();
            phone_No.setError("Invalid phone number");
            return false;
        }
        return true;
    }

    public void checkUser() {
        String phone = phone_No.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUserDatabase = reference.orderByChild("phoneNo").equalTo(phone);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    progressBar.setVisibility(View.VISIBLE);
                    getOtpButton.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + phone, 60, TimeUnit.SECONDS, ForgotPasswordActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    getOtpButton.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    getOtpButton.setVisibility(View.VISIBLE);
                                    Toast.makeText(ForgotPasswordActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    System.out.println(e.getMessage());
                                }

                                @Override
                                public void onCodeSent(@NonNull String backendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    getOtpButton.setVisibility(View.VISIBLE);
                                    Toast.makeText(ForgotPasswordActivity.this, "please verify the OTP", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgotPasswordActivity.this, VerifyOTPActivity.class);
                                    intent.putExtra("mobile", phone);
                                    intent.putExtra("backendOTP",backendOTP);
                                    startActivity(intent);
                                }
                            }
                    );
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "User does not exist, Please create an Account.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}