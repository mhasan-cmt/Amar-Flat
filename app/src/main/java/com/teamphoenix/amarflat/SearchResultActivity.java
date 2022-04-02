package com.teamphoenix.amarflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.teamphoenix.amarflat.Adapter.PostAdAdapter;
import com.teamphoenix.amarflat.Model.property;
import com.teamphoenix.amarflat.databinding.ActivityHomeBinding;
import com.teamphoenix.amarflat.databinding.ActivitySearchResultBinding;

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
    }
}