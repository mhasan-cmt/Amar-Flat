package com.teamphoenix.amarflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preferences.getString("user_name","0").equals("0")){
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
            }
        },2000);

    }
}