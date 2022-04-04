package com.teamphoenix.amarflat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.teamphoenix.amarflat.databinding.ActivitySearchFilterBinding;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class SearchFilterActivity extends AppCompatActivity {

    private static final int LOCATION_RECEIVE_CODE =101 ;
    private ActivitySearchFilterBinding searchFilterBinding;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOCATION_RECEIVE_CODE){
            if(resultCode == RESULT_OK){
                searchFilterBinding.txt2.setText(data.getStringExtra("CityName"));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchFilterBinding = ActivitySearchFilterBinding.inflate(getLayoutInflater());
        setContentView(searchFilterBinding.getRoot());
        settingUpRangeSeekbars();

        searchFilterBinding.searchBtnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SearchFilterActivity.this, MapsActivity.class), LOCATION_RECEIVE_CODE);
            }
        });
        searchFilterBinding.searchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchFilterActivity.this, CityActivity.class));
            }
        });

    }

    private void settingUpRangeSeekbars() {
        searchFilterBinding.searchPriceRangeSeekbar.setRangeValues(100, 100000000,10);
        searchFilterBinding.searchPriceRangeSeekbar.setSelectedMaxValue(100000000);
        searchFilterBinding.searchPriceRangeSeekbar.setSelectedMinValue(0);

        searchFilterBinding.searchAreaRangeSeekbar.setRangeValues(50, 100000,10);
        searchFilterBinding.searchAreaRangeSeekbar.setSelectedMaxValue(100000000);
        searchFilterBinding.searchAreaRangeSeekbar.setSelectedMinValue(0);

        searchFilterBinding.searchSeekbarAreaMaxValue.setText(searchFilterBinding.searchAreaRangeSeekbar.getSelectedMaxValue()+ "Sq");
        searchFilterBinding.searchSeekbarAreaMinValue.setText(searchFilterBinding.searchAreaRangeSeekbar.getSelectedMinValue()+" Sq ");

        searchFilterBinding.searchAreaRangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                searchFilterBinding.searchSeekbarAreaMaxValue.setText(searchFilterBinding.searchAreaRangeSeekbar.getSelectedMaxValue()+ "Sq");
                searchFilterBinding.searchSeekbarAreaMinValue.setText(searchFilterBinding.searchAreaRangeSeekbar.getSelectedMinValue()+" Sq ");
            }
        });

        searchFilterBinding.searchSeekbarMaxValue.setText(searchFilterBinding.searchPriceRangeSeekbar.getSelectedMaxValue()+ "BDT");
        searchFilterBinding.searchSeekbarMinValue.setText(searchFilterBinding.searchPriceRangeSeekbar.getSelectedMinValue()+" BDT");

        searchFilterBinding.searchPriceRangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                searchFilterBinding.searchSeekbarMaxValue.setText(searchFilterBinding.searchPriceRangeSeekbar.getSelectedMaxValue()+" BDT");
                searchFilterBinding.searchSeekbarMinValue.setText(searchFilterBinding.searchPriceRangeSeekbar.getSelectedMinValue()+" BDT");
            }
        });
    }
}