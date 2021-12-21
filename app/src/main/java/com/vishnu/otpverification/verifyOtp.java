package com.vishnu.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verifyOtp extends AppCompatActivity {

    EditText inputnumber1,inputnumber2,inputnumber3,inputnumber4,inputnumber5,inputnumber6;
    Button verify;
    String verificationcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        verify=findViewById(R.id.verify);
        inputnumber1=findViewById(R.id.input1);
        inputnumber2=findViewById(R.id.input2);
        inputnumber3=findViewById(R.id.input3);
        inputnumber4=findViewById(R.id.input4);
        inputnumber5=findViewById(R.id.input5);
        inputnumber6=findViewById(R.id.input6);

        verificationcode=getIntent().getStringExtra("VerificationCode");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputnumber1.getText().toString().trim().isEmpty()  || inputnumber2.getText().toString().trim().isEmpty() ||  inputnumber3.getText().toString().trim().isEmpty() ||  inputnumber4.getText().toString().trim().isEmpty()  ||  inputnumber5.getText().toString().trim().isEmpty() || inputnumber6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(verifyOtp.this, "Please,enter valid otp", Toast.LENGTH_SHORT).show();
                }

                /////Verify the Otp Code
                String code=inputnumber1.getText().toString()+
                        inputnumber2.getText().toString()+inputnumber3.getText().toString()+
                        inputnumber4.getText().toString()+inputnumber5.getText().toString()+inputnumber6.getText().toString();

                if(verificationcode != null){
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(verificationcode,code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(verifyOtp.this, "OTP Verify Succesfully..!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(verifyOtp.this, "OTP Is Incorrect...!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        numbermovenext();
    }
        private void numbermovenext(){

            inputnumber1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().trim().isEmpty()){
                        inputnumber2.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            inputnumber2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().trim().isEmpty()){
                        inputnumber3.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            inputnumber3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().trim().isEmpty()){
                        inputnumber4.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            inputnumber4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().trim().isEmpty()){
                        inputnumber5.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            inputnumber5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().trim().isEmpty()){
                        inputnumber6.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }
