package com.teamphoenix.amarflat.PostAd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.teamphoenix.amarflat.Adapter.FeaturesNameValueLIstAdapter;
import com.teamphoenix.amarflat.Adapter.PostAdPhotoViewRecycler;
import com.teamphoenix.amarflat.AddFeature;
import com.teamphoenix.amarflat.Model.FeatureNameValue;
import com.teamphoenix.amarflat.PostAd.Fragment.CommercialFragment;
import com.teamphoenix.amarflat.PostAd.Fragment.HomesFragment;
import com.teamphoenix.amarflat.PostAd.Fragment.PlotsFragment;
import com.teamphoenix.amarflat.R;
import com.teamphoenix.amarflat.databinding.ActivityPostAdBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostAd extends AppCompatActivity {

    Spinner size_type;
    ArrayList<String> sizeTypeList;
    ActivityResultLauncher<Intent> activityResultLauncher;
    RecyclerView imageRecyclerView;
    ArrayList<Uri> postAdImageList;
    ArrayList<Bitmap> postAdImageListBitmap;
    ArrayList<FeatureNameValue> featureNameValueArrayList = new ArrayList<>();
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
                Intent intent = new Intent(getApplicationContext(),AddFeature.class);
                startActivityForResult(intent,1111);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.propertyTypeFrame,new HomesFragment()).addToBackStack(null).commit();

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
        postAdImageListBitmap = new ArrayList<>();
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
                        Bitmap bitmap = null;
                        try {
                            bitmap = Bitmap.createBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(),result.getData().getClipData().getItemAt(i).getUri()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        postAdImageListBitmap.add(bitmap);
                    }
                    postAdPhotoViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<FeatureNameValue> arrayList = new ArrayList<>();
        if (requestCode==1111 && resultCode==RESULT_OK){
            Map<String, String> getList = new HashMap<>();
            getList = (Map<String, String>) data.getSerializableExtra("data");
            for ( Map.Entry<String, String> entry : getList.entrySet()) {
                String key = entry.getKey();
                String getData = entry.getValue();
                arrayList.add(new FeatureNameValue(key,getData));
            }
            setFeatureRecyclerView(arrayList);
        }
    }

    public void setFeatureRecyclerView(ArrayList<FeatureNameValue> value){
        featureNameValueArrayList = value;
        postAdBinding.featureShowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FeaturesNameValueLIstAdapter adapter = new FeaturesNameValueLIstAdapter(getApplicationContext(),value);
        postAdBinding.featureShowRecyclerView.setAdapter(adapter);
    }

    public void postAd(View view) {
        //dialog create
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        //dialog show
        dialog.show();




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
                perms.put("property_type",propertyType);
                perms.put("purpose",getPurpose());
                perms.put("total_price",postAdBinding.totalPrice.getText().toString());
                perms.put("email",postAdBinding.email.getText().toString());
                perms.put("phone",postAdBinding.phone.getText().toString());
                perms.put("user_id",preferences.getString("user_id","0"));

                Map<String, String> map = new HashMap<>();

                for (int i = 0; i<featureNameValueArrayList.size(); i++){
                    map.put(featureNameValueArrayList.get(i).getFeatureName(),featureNameValueArrayList.get(i).getFeatureValue());
                }
                JSONObject jsonObject = new JSONObject(map);
                perms.put("features",jsonObject.toString());

                JSONArray care_type = new JSONArray();
                for(int i=0; i < postAdImageListBitmap.size(); i++) {
                    care_type.put(getBitmapToString(postAdImageListBitmap.get(i)));
                }
                perms.put("arr",care_type.toString());
                return perms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String getBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
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
        return "";
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
        return "";
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