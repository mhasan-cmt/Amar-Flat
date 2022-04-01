package com.teamphoenix.amarflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.teamphoenix.amarflat.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

    }
}