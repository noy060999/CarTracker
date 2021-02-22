package com.example.cartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    //firebase settings
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    //containers list
    TextView emailTxt, carBrandTxt, carModelTxt, carYearTxt, carKmTxt, carLastTreatmentKmTxt, carLastTreatmentDateTxt, currentLocationHome, helloUsr_lbl;
    MaterialButton logoutBtn;

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("pttx","bbbb");
        findViews();
        initTextViews();

        //check permissions
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    HomeActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            startLocationService();
        }

        //when logout clicked - stop service and logout from db
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                stopLocationService();
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startLocationService();
                Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //a function to check whether the service is running or not
    private boolean isLocationServiceRunning() {
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo service :
                    activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (LocationService.class.getName().equals(service.service.getClassName())) {
                    if (service.foreground) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void startLocationService() {
        if (!isLocationServiceRunning()) {
            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constants.ACTION_START_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(this, "Location Service Started", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopLocationService() {
        if (isLocationServiceRunning()) {
            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constants.ACTION_STOP_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(this, "Location Service Stopped", Toast.LENGTH_SHORT).show();
        }
    }


    private void initTextViews() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userUid = user.getUid();
            myRef.child(userUid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //get the real time db values
                    User currentUser = snapshot.getValue(User.class);
                    helloUsr_lbl.setText("Hello " + currentUser.getFullName());
                    emailTxt.setText("User Email: " + currentUser.getEmail());
                    carBrandTxt.setText("Car Brand: " + currentUser.getCar().getCarBrand());
                    carModelTxt.setText("Car Model: " + currentUser.getCar().getCarModel());
                    carYearTxt.setText("Car Manufacture Year: " + currentUser.getCar().getCarManufactureYear());
                    carKmTxt.setText("Car Current Km's: " + currentUser.getCar().getCarKM());
                    carLastTreatmentKmTxt.setText("Car Last Treatment Km's: " + currentUser.getCar().getCarTreatment().getLastTreatmentKM());
                    String date = currentUser.getCar().getCarTreatment().getLastTreatmentDate();
                    carLastTreatmentDateTxt.setText("Car Last Treatment Date: " + date);
                    currentLocationHome.setText("Current Distance: \n" + currentUser.getDistance());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }

    private void findViews() {
        emailTxt = findViewById(R.id.email_txtHome);
        carBrandTxt = findViewById(R.id.carBrand_txtHome);
        carModelTxt = findViewById(R.id.carModel_txtHome);
        carYearTxt = findViewById(R.id.carYear_txtHome);
        carKmTxt = findViewById(R.id.carKm_txtHome);
        carLastTreatmentKmTxt = findViewById(R.id.carTreatmentKm_txtHome);
        carLastTreatmentDateTxt = findViewById(R.id.carTreatmentDate_txtHome);
        logoutBtn = findViewById(R.id.logout_homeActivity);
        currentLocationHome = findViewById(R.id.currentLocationHome);
        helloUsr_lbl = findViewById(R.id.helloUsr_lbl);
    }
}



