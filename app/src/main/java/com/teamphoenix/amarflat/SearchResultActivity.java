package com.teamphoenix.amarflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamphoenix.amarflat.Adapter.PostAdAdapter;
import com.teamphoenix.amarflat.Model.property;
import com.teamphoenix.amarflat.databinding.ActivityHomeBinding;
import com.teamphoenix.amarflat.databinding.ActivitySearchResultBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    ActivitySearchResultBinding searchResultBinding;
    PostAdAdapter postAdAdapter;
    ArrayList<property> postAdArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResultBinding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        setContentView(searchResultBinding.getRoot());

        postAdArrayList = new ArrayList<>();
        postAdAdapter = new PostAdAdapter(getApplicationContext(),postAdArrayList);
        searchResultBinding.postList.setLayoutManager(new LinearLayoutManager(this));
        searchResultBinding.postList.setAdapter(postAdAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/propertyList.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i =0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        postAdArrayList.add( new property(jsonObject.getString("property_id"),jsonObject.getString("title"),jsonObject.getString("location"),jsonObject.getString("area_size"),jsonObject.getString("bedrooms"),jsonObject.getString("bathrooms"),jsonObject.getString("description"),jsonObject.getString("property_type"),jsonObject.getString("purpose"),jsonObject.getString("total_price"),jsonObject.getString("email"),jsonObject.getString("phone"),jsonObject.getString("user_id"),jsonObject.getString("time")));
                    }
                    postAdAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(SearchResultActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}