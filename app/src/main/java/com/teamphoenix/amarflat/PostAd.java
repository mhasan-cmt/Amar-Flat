package com.teamphoenix.amarflat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamphoenix.amarflat.Adapter.PostAdPhotoViewRecycler;
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

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postAdBinding = ActivityPostAdBinding.inflate(getLayoutInflater());
        setContentView(postAdBinding.getRoot());
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        editor = preferences.edit();


        postAdBinding.postAddToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostAd.super.onBackPressed();
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
        
    }

    public void picAdd(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }
}