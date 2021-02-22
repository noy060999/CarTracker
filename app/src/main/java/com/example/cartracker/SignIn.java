package com.example.cartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText edt_signin_email;
    EditText edt_signin_password;
    EditText edt_signin_carNumber;
    MaterialButton signInBtn_signin;
    ProgressBar signIn_progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findViews();
        firebaseAuth = FirebaseAuth.getInstance();
        signInBtn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInClicked();
            }
        });
    }

    private void signInClicked() {
        String usrEmail = edt_signin_email.getText().toString();
        String usrPassword = edt_signin_password.getText().toString();
        String usrCarNum = edt_signin_carNumber.getText().toString();

        if (TextUtils.isEmpty(usrEmail)){
            edt_signin_email.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(usrPassword)){
            edt_signin_password.setError("Password is Required.");
            return;
        }

        if (TextUtils.isEmpty(usrCarNum)){
            edt_signin_carNumber.setError("Car number is Required.");
            return;
        }

        signIn_progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(usrEmail, usrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignIn.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this, HomeActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(SignIn.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findViews() {
        edt_signin_email = findViewById(R.id.edt_signin_email);
        edt_signin_password = findViewById(R.id.edt_signin_password);
        edt_signin_carNumber = findViewById(R.id.edt_signin_carNumber);
        signInBtn_signin = findViewById(R.id.signInBtn_signin);
        signIn_progressBar = findViewById(R.id.signIn_progressBar);
    }
}