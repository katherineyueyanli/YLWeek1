package com.example.ylweek1;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    TextView latitude, longitude, distanceText;
    Button reset;
    Double latitudeLast, longitudeLast,latitudeCurrent, longitudeCurrent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        reset=findViewById(R.id.bttReset);
        distanceText=findViewById(R.id.distance);
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        // get current location

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("Permission accept");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    System.out.println("On Success");
                    if (location != null) {
                        latitude.setText(String.valueOf(location.getLatitude()));
                        longitude.setText(String.valueOf(location.getLongitude()));
                        latitudeLast=location.getLatitude();
                        longitudeLast=location.getLatitude();

                    }
                    else{
                        System.out.println("Location is NULL");
                    }
                }
            });
        } else {

            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location != null) {

                                // get current location
                                latitude.setText(String.valueOf(location.getLatitude()));
                                longitude.setText(String.valueOf(location.getLongitude()));
                                latitudeCurrent=location.getLatitude();
                                longitudeCurrent=location.getLatitude();
                                System.out.println(longitudeCurrent+latitudeCurrent);


                                // set distance
                                Location startPoint=new Location("locationA");
                                startPoint.setLatitude(latitudeLast);
                                startPoint.setLongitude(longitudeLast);

                                Location endPoint=new Location("locationA");
                                endPoint.setLatitude(latitudeCurrent);
                                endPoint.setLongitude(longitudeCurrent);

                                double distance=startPoint.distanceTo(endPoint);
                                distanceText.setText(String.valueOf(distance) + " Kms");

                                // set the current location as latest
                                latitudeLast=location.getLatitude();
                                longitudeLast=location.getLongitude();
                                System.out.println(latitudeLast+longitudeLast);

                            }
                            else{
                                System.out.println("Location is NULL");
                            }
                        }
                    });
                } else {

                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                }

            }
        });






    }


    private void getLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("Permission accept");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    System.out.println("On Success");
                    if (location != null) {
                        latitude.setText(String.valueOf(location.getLatitude()));
                        longitude.setText(String.valueOf(location.getLongitude()));

                    }
                    else{
                        System.out.println("Location is NULL");
                    }
                }
            });
        } else {

            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(LocationActivity.this, "Please provide required Permission!", Toast.LENGTH_SHORT).show();
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}