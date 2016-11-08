package com.nihal.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Checkin extends AppCompatActivity {







    Button Continue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        Showlocation mShowlocation = new Showlocation();


//        LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(Checkin.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Checkin.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//
//
//
//        loc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mShowlocation);
//        loc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mShowlocation);
//        Toast.makeText(Checkin.this, "Latitude:"+Showlocation.mLatitude+"Longitude:"+Showlocation.mLongitude, Toast.LENGTH_SHORT).show();
//








        Continue=(Button)findViewById(R.id.btncntn);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i=new Intent(Checkin.this,Nearby.class);
                startActivity(i);




            }
        });
    }
}
