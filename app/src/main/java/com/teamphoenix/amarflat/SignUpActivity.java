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
import com.teamphoenix.amarflat.databinding.ActivitySignUpBinding;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.teamphoenix.amarflat.util.LanguageUtil;

import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signUpBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void signUp(View view) {

        //dialog create
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        //dialog show
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/signUp.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_SHORT).show();
                if (response.equals("Yes")){
                    editor.putString("user_name",signUpBinding.name.getText().toString());
                    editor.putString("email",signUpBinding.email.getText().toString());
                    editor.putString("password",signUpBinding.password.getText().toString());
                    editor.putString("phone",signUpBinding.phone.getText().toString());
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> perms = new HashMap<>();
                perms.put("user_name",signUpBinding.name.getText().toString());
                perms.put("email",signUpBinding.email.getText().toString());
                perms.put("password",signUpBinding.password.getText().toString());
                perms.put("phone",signUpBinding.phone.getText().toString());
                return perms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }
}