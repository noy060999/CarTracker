package com.example.cartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SignUp extends AppCompatActivity {

    //containers list
    EditText edt_signup_fullName;
    EditText edt_signup_email;
    EditText edt_signup_password;
    EditText edt_signup_carNumber;
    Spinner spinner_signup_carBrand;
    EditText edt_signup_carModel;
    EditText edt_signup_carYear;
    EditText edt_signup_carKm;
    EditText edt_lastCareKm;
    DatePicker datePicker_carLastCareDate;
    MaterialButton signUpBtn;
    ProgressBar progressBarSignUp;

    //firebase settings
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpClicked();
            }
        });
    }

    private void findViews() {
        //my list of cars
        String[] arraySpinner = new String[] {
                "Audi", "Toyota", "Nissan", "Honda", "Hyundai", "Peguet", "Mazda", "Kia"
        };
        edt_signup_fullName = findViewById(R.id.edt_signup_fullName);
        edt_signup_email = findViewById(R.id.edt_signup_email);
        edt_signup_password = findViewById(R.id.edt_signup_password);
        edt_signup_carModel = findViewById(R.id.edt_signup_carModel);
        edt_signup_carNumber = findViewById(R.id.edt_signup_carNumber);
        edt_signup_carKm = findViewById(R.id.edt_signup_carKm);
        edt_signup_carYear = findViewById(R.id.edt_signup_carYear);
        edt_lastCareKm = findViewById(R.id.edt_lastCareKm);
        datePicker_carLastCareDate = findViewById(R.id.datePicker_carLastCareDate);
        spinner_signup_carBrand = findViewById(R.id.spinner_signup_carBrand);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_signup_carBrand.setAdapter(adapter);
        signUpBtn = findViewById(R.id.signUpBtn_signup);
        progressBarSignUp = findViewById(R.id.signUp_progressBar);
    }

    private void signUpClicked() {
        String usrFullName = edt_signup_fullName.getText().toString();
        String usrEmail = edt_signup_email.getText().toString();
        String usrPassword = edt_signup_password.getText().toString();
        String usrCarModel = edt_signup_carModel.getText().toString();
        String usrCarNumber = edt_signup_carNumber.getText().toString();
        String usrCarKM = edt_signup_carKm.getText().toString();
        String usrCarYear = edt_signup_carYear.getText().toString();
        String usrCarBrand = spinner_signup_carBrand.getSelectedItem().toString();
        String usrLastCareKm = edt_lastCareKm.getText().toString();
        GregorianCalendar date = new GregorianCalendar(datePicker_carLastCareDate.getYear(), datePicker_carLastCareDate.getMonth(), datePicker_carLastCareDate.getDayOfMonth());
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        fmt.setCalendar(date);
        String usrLastCareDate = fmt.format(date.getTime());

        //log msg
        Log.d("ptttx", usrLastCareDate);

        /*if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(SignUp.this, HomeActivity.class));
            finish();
        }*/

        //check if all data exist
        if (TextUtils.isEmpty(usrFullName)){
            edt_signup_fullName.setError("Full Name is required");
            return;
        }

        if (TextUtils.isEmpty(usrLastCareKm)){
            edt_lastCareKm.setError("Last treatment km's is required");
            return;
        }

        if (TextUtils.isEmpty(usrEmail)){
            edt_signup_email.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(usrCarKM)){
            edt_signup_carKm.setError("Car Km's is required");
            return;
        }

        if (TextUtils.isEmpty(usrCarModel)){
            edt_signup_carModel.setError("Car Model is required");
            return;
        }

        if (TextUtils.isEmpty(usrCarYear)){
            edt_signup_carYear.setError("Car Manufactured Year is required");
            return;
        }

        if (TextUtils.isEmpty(usrCarNumber)){
            edt_signup_carNumber.setError("Car Number is required");
            return;
        }

        if (TextUtils.isEmpty(usrPassword)){
            edt_signup_password.setError("Password is required");
            return;
        }
        if (usrPassword.length() < 6){
            edt_signup_password.setError("Password must contain at least 6 characters");
            return;
        }
        progressBarSignUp.setVisibility(View.VISIBLE);

        //firebase sign up with email and password
        firebaseAuth.createUserWithEmailAndPassword(usrEmail, usrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                    User newUser = new User(usrEmail, usrFullName, new Car(usrCarBrand, usrCarModel, usrCarNumber, usrCarKM, usrCarYear,32.0,32.0, new CarTreatment(usrLastCareKm, usrLastCareDate)));
                    String uid = task.getResult().getUser().getUid();
                    myRef.child(uid).setValue(newUser);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    progressBarSignUp.setVisibility(View.INVISIBLE);
                    finish();
                }
                else {
                    Toast.makeText(SignUp.this, "Failed to create user" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBarSignUp.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}