package com.teamphoenix.amarflat;

import androidx.appcompat.app.ActionBarDrawerToggle;
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

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                homeBinding.homeDrawer,
                homeBinding.mainToolbar,
                R.string.Open_Drawer,
                R.string.Close_Drawer);
        homeBinding.homeDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }
}