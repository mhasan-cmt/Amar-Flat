package com.teamphoenix.amarflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.teamphoenix.amarflat.databinding.ActivityContactUsBinding;

public class ContactUsActivity extends AppCompatActivity {
    ActivityContactUsBinding contactUsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactUsBinding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(contactUsBinding.getRoot());

        contactUsBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    public void updateProfile(View view) {
    }

    public void messageSend(View view) {
    }
}