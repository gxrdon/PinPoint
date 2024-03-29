package com.example.pinplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pinplay.R;

public class UserFragment extends DialogFragment implements View.OnClickListener {

    public UserFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static UserFragment newInstance(String title) {
        UserFragment frag = new UserFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container);
        ((TextView) v.findViewById(R.id.lbl_your_name)).setText(getArguments().getString("title"));
        Button b = (Button) v.findViewById(R.id.button2);
        ((Button) v.findViewById(R.id.button3)).setOnClickListener(this);
        b.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                MapsActivity.hideMarker();
                this.dismiss();
            case R.id.button3:
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("address", "12125551212");
                startActivity(sendIntent);
        }
    }
}