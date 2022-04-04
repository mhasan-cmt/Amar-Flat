package com.teamphoenix.amarflat;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.teamphoenix.amarflat.Adapter.CityAdapter;
import com.teamphoenix.amarflat.databinding.ActivityCityBinding;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    ActivityCityBinding cityBinding;
    private CityAdapter cityAdapter;
    private ArrayList cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityBinding = ActivityCityBinding.inflate(getLayoutInflater());

        setContentView(cityBinding.getRoot());
        cities = new ArrayList();


        cityAdapter = new CityAdapter(this, cities);
        cityBinding.recyclerCity.setLayoutManager(new LinearLayoutManager(this));
        cityBinding.recyclerCity.setAdapter(cityAdapter);

        cityBinding.citySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                cityAdapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                cityAdapter.filter(s);
                return true;
            }
        });


    }
}