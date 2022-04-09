package com.teamphoenix.amarflat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.teamphoenix.amarflat.Model.property;
import com.teamphoenix.amarflat.databinding.ActivityPostViewBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostViewActivity extends AppCompatActivity {
    ActivityPostViewBinding postViewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewBinding = ActivityPostViewBinding.inflate(getLayoutInflater());
        setContentView(postViewBinding.getRoot());

        postViewBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        property propertyData =(property) intent.getSerializableExtra("property");

        postViewBinding.propertyId.setText(propertyData.getProperty_id());
        postViewBinding.propertyTitle.setText(propertyData.getTitle());
        postViewBinding.location.setText(propertyData.getLocation());
        postViewBinding.areaSize.setText(propertyData.getArea_size());
        postViewBinding.area.setText(propertyData.getArea_size());
        postViewBinding.beds.setText(propertyData.getBedrooms());
        postViewBinding.totalBedroom.setText(propertyData.getBedrooms()+" Beds");
        postViewBinding.baths.setText(propertyData.getBathroom());
        postViewBinding.totalBathroom.setText(propertyData.getBathroom()+" Baths");
        postViewBinding.description.setText(propertyData.getDescription());
        postViewBinding.propertyType.setText(propertyData.getProperty_type());
        postViewBinding.purpose.setText("For"+propertyData.getPurpose());
        postViewBinding.price.setText(propertyData.getTotal_price());
        postViewBinding.priceTitle.setText("BDT "+propertyData.getTotal_price());


        //dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/getFeatureListByPropertyID.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        postViewBinding.servantQuarters.setText("Servant Quarters: "+jsonObject.get("Servant Quarters").toString());
                        postViewBinding.drawing.setText("Drawing Room: "+jsonObject.get("Drawing Room").toString());
                        postViewBinding.dining.setText("Dining Room: "+jsonObject.get("Dining Room").toString());
                        postViewBinding.kitchen.setText("Kitchen: "+jsonObject.get("Kitchen").toString());
                        postViewBinding.schools.setText("Nearby Schools: "+jsonObject.get("Nearby Schools").toString());
                        postViewBinding.hospital.setText("Nearby Hospital: "+jsonObject.get("Nearby Hospital").toString());
                        postViewBinding.shoppingMall.setText("Nearby Shopping Mall: "+jsonObject.get("Nearby Shopping Mall").toString());
                        postViewBinding.restaurants.setText("Nearby Restaurants: "+jsonObject.get("Nearby Restaurants").toString());
                        postViewBinding.distanceAirport.setText("Distance From Airport: "+jsonObject.get("Distance From Airport").toString());
                        postViewBinding.publicTransport.setText("Nearby Public Transport: "+jsonObject.get("Nearby Public Transport").toString());
                        postViewBinding.lawnOrGarden.setText("Lawn or Garden: "+jsonObject.get("Lawn or Garden").toString());
                        postViewBinding.swimmingPool.setText("Swimming Pool: "+jsonObject.get("Swimming Pool").toString());
//                        postViewBinding..setText("Sauna: "+jsonObject.get("Sauna"));
                        postViewBinding.jacuzzi.setText("Jacuzzi: "+jsonObject.get("Jacuzzi").toString());
                        postViewBinding.maintenanceStaff.setText("Maintenance Staff: "+jsonObject.get("Maintenance Staff").toString());
                        postViewBinding.securityStaff.setText("Security Staff: "+jsonObject.get("Security Staff").toString());
                        postViewBinding.facilitiesForDisables.setText("Facilities For Disables: "+jsonObject.get("Facilities For Disables").toString());
                        postViewBinding.broadbandInternet.setText("Broadband Internet Access: "+jsonObject.get("Broadband Internet Access").toString());
                        postViewBinding.satelliteCable.setText("Satellite or Cable Tv Ready: "+jsonObject.get("Satellite or Cable Tv Ready").toString());
                        postViewBinding.intercom.setText("Intercom: "+jsonObject.get("Intercom").toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(PostViewActivity.this, "Something Wrong. Go Back And Try Again", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> perms = new HashMap<>();
                perms.put("property_id",propertyData.getProperty_id());
                return perms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}