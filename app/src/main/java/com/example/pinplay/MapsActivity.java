package com.example.pinplay;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private static Marker currentMarker;
    public static GoogleMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Button addPin = findViewById(R.id.pinButton);
        addPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogFragment newFragment = PinFragment.newInstance("test");
                newFragment.show(getSupportFragmentManager(), "dialog");

            }
        });

//        ImageView dragPin = findViewById(R.id.dragPin);
//        dragPin.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public void onLongClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                DialogFragment newFragment = PinFragment.newInstance("test");
//                newFragment.show(getSupportFragmentManager(), "dialog");
//
//            }
//        });
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
        aMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        // creating each of the markers individually
        createMarkers();

        setUpCamera();
    }

    private void createMarkers() {
        createMarker("Rebekah\nJust Chilling Until My Next Class\n6:30-8:00", R.mipmap.memoji_foreground, 38.989654, -76.939940);
        createMarker("CMSC434\nLet's work on GA6\n5:30-7:30", R.mipmap.thegang_foreground, 38.985823, -76.944997);
        createMarker("Billy\nWho wants to boogie\n7-1:30am", R.mipmap.billy_foreground, 38.982838, -76.942960);
        createMarker("Jamie\nGettin' some Sun\n7-1:30am", R.mipmap.jamie_foreground, 38.985907, -76.942123);
//        createMarker("Stephen\nIt's time to fill my belly\n5:30-7:45", R.mipmap.stephen_foreground, 38.989176, -76.936409);
        createMarker("ARCH GANG\nPROJECT DUE\n2:00-11:00am", R.mipmap.arch_foreground, 38.984311, -76.947500);
        createMarker("Ellie\nStudy???\n7:30-11:00", R.mipmap.ellie_foreground, 38.981370, -76.936125);
        createMarker("Marathon Deli\nMonday Discount\nAll Day", R.mipmap.marathon_foreground, 38.981603, -76.938613);
        createMarker("Vigilante\nLatte 50% off\nAfter 5", R.mipmap.vig_foreground, 38.991967, -76.933892);
        createMarker("Chick Fil A\nCome Try Spicy Chicken\nAfter 5", R.mipmap.chick_foreground, 38.988103, -76.944616);



    }

    private void setUpCamera() {
        // Lat-Long for STAMP
        LatLng stamp = new LatLng(38.987327, -76.944886);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stamp));
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(16.0f);
    }

    /*
    The second parameter is the resource ID
     */
    private Marker createMarker(String name, int resource, double lat, double lng) {
        LatLng location = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions().position(location).title(name)
                .icon(BitmapDescriptorFactory.fromResource(resource));
        Marker marker = mMap.addMarker(markerOptions);
        return marker;
    }
    public boolean onMarkerClick(final Marker marker) {
        currentMarker = marker;
        DialogFragment newFragment = UserFragment.newInstance(marker.getTitle());
        newFragment.show(this.getSupportFragmentManager(), "dialog");
        return true;
    }

    public static void hideMarker() {
        currentMarker.setVisible(false);
    }
}
