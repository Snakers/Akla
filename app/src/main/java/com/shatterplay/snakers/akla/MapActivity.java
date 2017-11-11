package com.shatterplay.snakers.akla;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener,OnMapReadyCallback {
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private MapView mapView;
    private GoogleMap googleMap;
    private int GEGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        googleApiClient= new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5*1000);
Bundle bundle = getIntent().getExtras();
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rateBar);
        double rate = bundle.getDouble("rate");

        ratingBar.setRating((float)rate);
ratingBar.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
});


        mapView =(MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Bundle bundle1 = getIntent().getExtras();

        assert bundle1 != null;
        double lat = bundle1.getDouble("lat");
        double lng = bundle1.getDouble("lng");
//        lat = location.getLatitude();
//         lng = location.getLongitude();
        LatLng myPlace = new LatLng(lat, lng);

        //      String gps = String.valueOf(lat)+ " -- "+ String.valueOf(lng);
        //        Toast.makeText(this,gps,Toast.LENGTH_SHORT).show();
        MarkerOptions markerOptions = new MarkerOptions();
        // markerOptions.position(new LatLng(lat,lng));
        markerOptions.position(myPlace);
        markerOptions.title("here");
        googleMap.clear();
        googleMap.setMapType(2);

        googleMap.addMarker(markerOptions);
        if(GEGE ==1) {



            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 20.0f));

            GEGE = 0;
        }
        //   googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)));
    }
    private void getLocation(){
        int check = ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        if(check ==PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},111);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==111&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getLocation();
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapActivity.this,"you Pressed on zeze",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

}
