package com.teamphoenix.amarflat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamphoenix.amarflat.databinding.ActivityProfileSettingBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileSetting extends AppCompatActivity {
    ActivityProfileSettingBinding profileSettingBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileSettingBinding = ActivityProfileSettingBinding.inflate(getLayoutInflater());
        setContentView(profileSettingBinding.getRoot());
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        editor = preferences.edit();
        setValue();

        profileSettingBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setValue() {
        profileSettingBinding.name.setText(preferences.getString("user_name","0"));
        profileSettingBinding.password.setText(preferences.getString("password","0"));
        profileSettingBinding.email.setText(preferences.getString("email","0"));
        profileSettingBinding.phone.setText(preferences.getString("phone","0"));
    }

    public void updateProfile(View view) {
        //dialog create
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        //dialog show
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/update_profile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(ProfileSetting.this, response, Toast.LENGTH_SHORT).show();
                if (response.equals("No") || response.equals("Input Blank")){
                    Toast.makeText(ProfileSetting.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }else if(response.equals("Yes")){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        editor.putString("user_name",profileSettingBinding.name.getText().toString());
                        editor.putString("email",profileSettingBinding.email.getText().toString());
                        editor.putString("password",profileSettingBinding.password.getText().toString());
                        editor.putString("phone",profileSettingBinding.phone.getText().toString());
                        editor.commit();
                        setValue();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(ProfileSetting.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> perms = new HashMap<>();
                perms.put("user_id",preferences.getString("user_id","0"));
                perms.put("user_name",profileSettingBinding.name.getText().toString());
                perms.put("email",profileSettingBinding.email.getText().toString());
                perms.put("password",profileSettingBinding.password.getText().toString());
                perms.put("phone",profileSettingBinding.phone.getText().toString());
                return perms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}