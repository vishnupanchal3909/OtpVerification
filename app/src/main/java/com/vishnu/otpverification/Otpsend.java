package com.vishnu.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.DataOutputStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Otpsend extends AppCompatActivity {

    EditText number;
    Button send;
    FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsend);
        number=findViewById(R.id.input);
        send=findViewById(R.id.send);
        mAuth=FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(number.getText().toString().trim().isEmpty()){
                   Toast.makeText(Otpsend.this, "Invalid Phone number", Toast.LENGTH_SHORT).show();
               }else if(number.getText().toString().trim().length() != 10){
                   Toast.makeText(Otpsend.this, "Enter Coorect Number", Toast.LENGTH_SHORT).show();
               }
               else{
                   ///OTP Send Code
                   ///Ty sathi firebase document madhe jaun phone number search karun first link ope karun "Safenet & reCpatch cha first madhe Google cloud console madhe jaun "Android Device Managment" Enable karaych ani  nanatar firbase madhe jaun app add karaychya thikani add Fingarfints madhe jaun SHA1 And SHA256 Add karaych mag  hotay run
                   otpsend();
               }
            }

        });

    }

    private void otpsend() {

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(Otpsend.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Intent intent=new Intent(Otpsend.this,verifyOtp.class);
                intent.putExtra("VerificationCode",verificationId);
                startActivity(intent);

            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+number.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}