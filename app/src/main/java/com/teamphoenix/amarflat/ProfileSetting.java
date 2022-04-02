package com.teamphoenix.amarflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.teamphoenix.amarflat.databinding.ActivityProfileSettingBinding;

public class ProfileSetting extends AppCompatActivity {
    ActivityProfileSettingBinding profileSettingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileSettingBinding = ActivityProfileSettingBinding.inflate(getLayoutInflater());
        setContentView(profileSettingBinding.getRoot());

        profileSettingBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void updateProfile(View view) {
    }
}