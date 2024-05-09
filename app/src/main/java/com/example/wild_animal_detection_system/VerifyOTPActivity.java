package com.example.wild_animal_detection_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {
    EditText otpno1, otpno2, otpno3, otpno4, otpno5, otpno6;
    TextView mobilenoTV, resendOTP;
    Button verifyOTPButton;
    String getOTPBackend;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        otpno1 = findViewById(R.id.edit_text1);
        otpno2 = findViewById(R.id.edit_text2);
        otpno3 = findViewById(R.id.edit_text3);
        otpno4 = findViewById(R.id.edit_text4);
        otpno5 = findViewById(R.id.edit_text5);
        otpno6 = findViewById(R.id.edit_text6);
        mobilenoTV = findViewById(R.id.textView16);
        verifyOTPButton = findViewById(R.id.button2);
        resendOTP = findViewById(R.id.text_view);

        progressBar = findViewById(R.id.progress_circular);

        String mobileno = getIntent().getStringExtra("mobile");
        mobilenoTV.setText("+91"+mobileno);

        getOTPBackend = getIntent().getStringExtra("backendOTP");

        verifyOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no1 = otpno1.getText().toString().trim();
                String no2 = otpno2.getText().toString().trim();
                String no3 = otpno3.getText().toString().trim();
                String no4 = otpno4.getText().toString().trim();
                String no5 = otpno5.getText().toString().trim();
                String no6 = otpno6.getText().toString().trim();

                if(validOTP(no1,no2,no3,no4,no5,no6)) {
                    String enteredOTP = no1 + no2 + no3 + no4 + no5 + no6;

                    if(getOTPBackend != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyOTPButton.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                          getOTPBackend,enteredOTP
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        verifyOTPButton.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful()) {
                                            Intent intent = new Intent(VerifyOTPActivity.this, ResetPasswordActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.putExtra("mobile", mobileno);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(VerifyOTPActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(VerifyOTPActivity.this, "Please check Internet Connection.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        numberOTPMove();

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + mobileno, 60, TimeUnit.SECONDS, VerifyOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyOTPActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newbackendOTP, forceResendingToken);
                                getOTPBackend = newbackendOTP;
                            }
                        }
                );
            }
        });
    }

    private void numberOTPMove() {
        otpno1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    otpno2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otpno2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    otpno3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otpno3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    otpno4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otpno4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    otpno5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otpno5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    otpno6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public boolean validOTP(String no1, String no2, String no3, String no4, String no5, String no6) {
        if(!no1.isEmpty() && !no2.isEmpty() && !no3.isEmpty() && !no4.isEmpty() && !no5.isEmpty() && !no6.isEmpty()) {
            return true;
        }
        Toast.makeText(VerifyOTPActivity.this, "Please enter all numbers.", Toast.LENGTH_SHORT).show();
        return false;
    }
}