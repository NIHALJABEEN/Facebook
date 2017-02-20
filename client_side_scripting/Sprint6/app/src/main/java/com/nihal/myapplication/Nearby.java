package com.nihal.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Nearby extends AppCompatActivity {

    Geocoder geocoder;
    List<Address> addresses;
    final int maxResult = 5;
    String addressList[] = new String[maxResult];


    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);


        final ListView myAddressList = (ListView) findViewById(R.id.lv1);

        Showlocation mShowlocation = new Showlocation();
        LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(Nearby.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Nearby.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        loc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mShowlocation);
        loc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mShowlocation);



        Toast.makeText(Nearby.this, "Latitude:" + Showlocation.mLatitude + "Longitude:" + Showlocation.mLongitude, Toast.LENGTH_SHORT).show();


        geocoder = new Geocoder(Nearby.this, Locale.getDefault());

        try{
            final List<Address> addresses;

            addresses=geocoder.getFromLocation(Showlocation.mLatitude,Showlocation.mLongitude,maxResult);

            if (addresses != null&&addresses.size()>0) {

                for (int j=0;j<maxResult;j++){
                    Address returnedAddress=addresses.get(j);
                    StringBuilder strReturnedAddress=new StringBuilder();
                    for(int i=0;i<returnedAddress.getMaxAddressLineIndex();i++){
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    }
                    addressList[j]=strReturnedAddress.toString();


                }
                adapter = new ArrayAdapter<String>(Nearby.this,android.R.layout.simple_list_item_1, addressList);


                myAddressList.setAdapter(adapter);

                myAddressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item=(String) (myAddressList.getItemAtPosition(i));

                        Intent intent=new Intent(Nearby.this,StatusCheckin.class);
                        intent.putExtra("Strg",item);
                        startActivity(intent);


                    }
                });


            }

        }
        catch (IOException e1) {
            e1.printStackTrace();

        }
    }
    }
