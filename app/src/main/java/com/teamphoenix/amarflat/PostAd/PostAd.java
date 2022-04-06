package com.teamphoenix.amarflat.PostAd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.teamphoenix.amarflat.Adapter.PostAdPhotoViewRecycler;
import com.teamphoenix.amarflat.AddFeature;
import com.teamphoenix.amarflat.PostAd.Fragment.CommercialFragment;
import com.teamphoenix.amarflat.PostAd.Fragment.HomesFragment;
import com.teamphoenix.amarflat.PostAd.Fragment.PlotsFragment;
import com.teamphoenix.amarflat.R;
import com.teamphoenix.amarflat.databinding.ActivityPostAdBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostAd extends AppCompatActivity {

    Spinner size_type;
    ArrayList<String> sizeTypeList;
    ActivityResultLauncher<Intent> activityResultLauncher;
    Uri path;
    Bitmap bitmap;
    RecyclerView imageRecyclerView;
    ArrayList<Uri> postAdImageList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private ActivityPostAdBinding postAdBinding;
    String propertyType="";

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postAdBinding = com.teamphoenix.amarflat.databinding.ActivityPostAdBinding.inflate(getLayoutInflater());
        postAdBinding = ActivityPostAdBinding.inflate(getLayoutInflater());

//        For Activity Transition
        overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);

        setContentView(postAdBinding.getRoot());
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        editor = preferences.edit();


        postAdBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostAd.super.onBackPressed();
            }
        });
        postAdBinding.featureAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddFeature.class));
            }
        });

        //property type
        postAdBinding.propertyTypeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()){
                    case "Homes":
                        getSupportFragmentManager().beginTransaction().replace(R.id.propertyTypeFrame,new HomesFragment()).addToBackStack(null).commit();
                        break;
                    case "Plots":
                        getSupportFragmentManager().beginTransaction().replace(R.id.propertyTypeFrame,new PlotsFragment()).addToBackStack(null).commit();
                        break;
                    case "Commercial":
                        getSupportFragmentManager().beginTransaction().replace(R.id.propertyTypeFrame,new CommercialFragment()).addToBackStack(null).commit();
                        break;
                    default:

                        getSupportFragmentManager().beginTransaction().replace(R.id.propertyTypeFrame,new HomesFragment()).addToBackStack(null).commit();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //get size type and set adapter
        size_type = findViewById(R.id.size_type);
        sizeTypeList = new ArrayList<>();
        sizeTypeList.add("Sq. Ft.");
        sizeTypeList.add("Sq. M.");
        sizeTypeList.add("Sq. Yd.");
        sizeTypeList.add("Marla");
        sizeTypeList.add("Marla");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sizeTypeList);
        size_type.setAdapter(adapter);


        //recyclerView
        imageRecyclerView = findViewById(R.id.imageRecyclerView);
        postAdImageList = new ArrayList<>();
        PostAdPhotoViewRecycler postAdPhotoViewAdapter = new PostAdPhotoViewRecycler(getApplicationContext(), postAdImageList);
        imageRecyclerView.setAdapter(postAdPhotoViewAdapter);
        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        //get image
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    for (int i = 0; i < result.getData().getClipData().getItemCount(); i++) {
                        postAdImageList.add(result.getData().getClipData().getItemAt(i).getUri());
                    }
                    postAdPhotoViewAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void postAd(View view) {
        //dialog create
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        //dialog show
        dialog.show();



        Toast.makeText(this, Integer.toString(postAdBinding.bathroomRadio.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sloppy-mattress.000webhostapp.com/API/postAd.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(PostAd.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(PostAd.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> perms = new HashMap<>();
                perms.put("title",postAdBinding.propertyTitle.getText().toString());
                perms.put("location","Location");
                perms.put("area_size",postAdBinding.areaSize.getText().toString()+size_type.getSelectedItem().toString());
                perms.put("bedrooms",getBedroom());
                perms.put("bathrooms",getBathroom());
                perms.put("description",postAdBinding.propertyDescription.getText().toString());
                perms.put("property_type","Flat");
                perms.put("purpose",getPurpose());
                perms.put("total_price",postAdBinding.totalPrice.getText().toString());
                perms.put("email",postAdBinding.email.getText().toString());
                perms.put("phone",postAdBinding.phone.getText().toString());
                perms.put("user_id",preferences.getString("user_id","0"));
                return perms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String getPurpose() {
        if(postAdBinding.purposeRent.isChecked())
            return "Rent";
        else if(postAdBinding.purposeSell.isChecked())
            return "Sell";
        return "0";
    }

    private String getBathroom() {
        if(postAdBinding.bathroom1.isChecked())
            return "1";
        else if(postAdBinding.bathroom2.isChecked())
            return "2";
        else if(postAdBinding.bathroom3.isChecked())
            return "3";
        else if(postAdBinding.bathroom4.isChecked())
            return "4";
        return "0";
    }

    private String getBedroom() {
        if(postAdBinding.bedroom1.isChecked())
            return "1";
        else if(postAdBinding.bedroom2.isChecked())
            return "2";
        else if(postAdBinding.bedroom3.isChecked())
            return "3";
        else if(postAdBinding.bedroom4.isChecked())
            return "4";
        return "0";
    }

    public void picAdd(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }
    public void getProperyType(String value){
        propertyType = value;
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }
}