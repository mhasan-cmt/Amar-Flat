package com.teamphoenix.amarflat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamphoenix.amarflat.Adapter.ExpandListViewAdapter;
import com.teamphoenix.amarflat.Model.FeatureCategory;
import com.teamphoenix.amarflat.Model.features;
import com.teamphoenix.amarflat.databinding.ActivityAddFeatureBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFeature extends AppCompatActivity {
    ActivityAddFeatureBinding addFeatureBinding;
    ArrayList<FeatureCategory> featureCategoryArrayList = new ArrayList<>();
    Map<String, ArrayList<features>> featuresArrayList = new HashMap<>();
    ExpandListViewAdapter adapter;
    Map<String,String> featuresDataList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFeatureBinding = ActivityAddFeatureBinding.inflate(getLayoutInflater());
        setContentView(addFeatureBinding.getRoot());

        getFeatureCategoryList();

        Toast.makeText(this, Integer.toString(featureCategoryArrayList.size()), Toast.LENGTH_SHORT).show();

        //expandableListView
        adapter = new ExpandListViewAdapter(featureCategoryArrayList,featuresArrayList);
        addFeatureBinding.expandAbleListView.setAdapter(adapter);


        //toolbar
        addFeatureBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getFeatureCategoryList() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/categoryList.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        featureCategoryArrayList.add(new FeatureCategory(jsonObject.getString("feature_category_id"),jsonObject.getString("feature_category_name")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

                getFeatureList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddFeature.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getFeatureList() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/featureList.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int j = 0; j<featureCategoryArrayList.size(); j++){
                        ArrayList<features> features = new ArrayList<>();
                        for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (featureCategoryArrayList.get(j).getFeatureCategoryId().equals(jsonObject.getString("feature_category_id"))){
                                features.add(new features(jsonObject.getString("feature_id"),
                                        jsonObject.getString("feature_name"), jsonObject.getString("feature_icon"),
                                        jsonObject.getString("feature_category_id")));
                                featuresArrayList.put(featureCategoryArrayList.get(j).getFeatureCategoryId(), features);
                            }
                        }

                    }
                    Toast.makeText(AddFeature.this, Integer.toString(featureCategoryArrayList.size()), Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AddFeature.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddFeature.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void getFeatureData(Map<String,String> value){
        featuresDataList=value;
    }

    public void done(View view) {
        Intent intent = new Intent();
        intent.putExtra("data", (Serializable) featuresDataList);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}