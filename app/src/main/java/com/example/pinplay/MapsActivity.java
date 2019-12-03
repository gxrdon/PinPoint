package com.example.pinplay;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

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
        mMap.setOnMarkerClickListener(this);

        // creating each of the markers individually
        createMarkers();

        setUpCamera();
    }

    private void createMarkers() {
        createMarker("Rebekah\nJust Chilling Until My Next Class\n6:30", R.mipmap.memoji_foreground, 38.987327, -76.944886);
        createMarker("Ali\nStraight Booling\n5:30", R.mipmap.test_foreground, 38.985782, -76.944423);
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
