package com.example.pinplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class PinFragment extends DialogFragment implements View.OnClickListener{

    private GoogleMap mMap;
    private static Marker currentMarker;
    public PinFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static PinFragment newInstance(String title) {
        PinFragment frag = new PinFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pin, container);
        //((TextView) v.findViewById(R.id.lbl_your_name)).setText(getArguments().getString("title"));
        Button b = (Button) v.findViewById(R.id.addPinButton);
        ((Button) v.findViewById(R.id.addPinButton)).setOnClickListener(this);
        b.setOnClickListener(this);
        return v;
    }

    private Marker createMarker(String name, int resource, double lat, double lng) {
        LatLng location = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions().position(location).title(name)
                .icon(BitmapDescriptorFactory.fromResource(resource));
        Marker marker = MapsActivity.aMap.addMarker(markerOptions);
        return marker;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addPinButton:
                EditText title   = (EditText)getView().findViewById(R.id.pin_title);
                EditText time = (EditText)getView().findViewById(R.id.pin_time);
                EditText contacts = (EditText)getView().findViewById(R.id.pin_contacts);
                EditText loc = (EditText)getView().findViewById(R.id.pin_location);
                createMarker(title.getText() +"\n" + time, R.mipmap.stephen_foreground, 38.989160, -76.936406);
                this.dismiss();
        }
    }
}
