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
import com.teamphoenix.amarflat.databinding.ActivityLoginBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void signUp(View view) {
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    public void login(View view) {
        //dialog create
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        //dialog show
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                if (response.equals("No") || response.equals("Input Blank")){
                    Toast.makeText(LoginActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        editor.putString("user_id",jsonObject.getString("user_id"));
                        editor.putString("user_name",jsonObject.getString("user_name"));
                        editor.putString("email",jsonObject.getString("email"));
                        editor.putString("password",jsonObject.getString("password"));
                        editor.putString("phone",jsonObject.getString("phone"));
                        editor.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> perms = new HashMap<>();
                perms.put("email",loginBinding.email.getText().toString());
                perms.put("password",loginBinding.password.getText().toString());
                return perms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}