package com.example.rudra.xahar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng userlocation;
    String address;
    String country;
    String postalCode;
    String city;
    String knownName;
    String state;
    String addressLine;
    String society;
    Marker myMarker;
    String addresses;
    String thoroughfare;
    //Adding New Code, check this if error


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LocationManager locationManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
        LocationListener locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("Xahar",location.toString());
                //mMap.clear();
                userlocation=new LatLng(location.getLatitude(),location.getLongitude());


                myMarker=mMap.addMarker(new MarkerOptions().position(userlocation).title("Your location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userlocation));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userlocation,10));
                Geocoder geocoder=new Geocoder(getApplicationContext(),Locale.getDefault());
                try {
                    List<Address>uselocation=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                     thoroughfare=uselocation.get(0).getAddressLine(0);
                    Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
                    intent.putExtra("user",thoroughfare);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };







        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }
        else {
            //if error change assert line
           // assert locationManager != null;
            //check with this if


                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);
                
        }

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Your Order"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                Geocoder geocoder=new Geocoder(getApplicationContext(),Locale.getDefault());
                List<Address>addressList;
                try {
                    addressList=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    addressLine=addressList.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
                intent.putExtra("Address",addressLine);
                intent.putExtra("Society",society);

                startActivity(intent);


            }
        });








    }



}
