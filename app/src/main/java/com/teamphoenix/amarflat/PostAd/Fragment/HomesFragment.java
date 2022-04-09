package com.teamphoenix.amarflat.PostAd.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.teamphoenix.amarflat.PostAd.PostAd;
import com.teamphoenix.amarflat.R;
import com.teamphoenix.amarflat.databinding.FragmentHomesBinding;


public class HomesFragment extends Fragment {
    FragmentHomesBinding homesBinding;

    public HomesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    String propertyType="";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        propertyType = "House";
        ((PostAd)getActivity()).getProperyType(propertyType);
        homesBinding.homesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (homesBinding.house.isChecked()){
                    propertyType = "House";
                }else if (homesBinding.flat.isChecked()){
                    propertyType = "Flat";
                }else if(homesBinding.upperPortion.isChecked()){
                    propertyType = "Upper Portion";
                }else if(homesBinding.lowerPortion.isChecked()){
                    propertyType = "Lower Portion";
                }else if(homesBinding.farmHouse.isChecked()){
                    propertyType = "Farm House";
                }else if(homesBinding.room.isChecked()){
                    propertyType = "Room";
                }else if(homesBinding.penthouse.isChecked()){
                    propertyType = "Penthouse";
                }else{
                    propertyType = "House";
                }
                ((PostAd)getActivity()).getProperyType(propertyType);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homesBinding = FragmentHomesBinding.inflate(inflater,container,false);
        return homesBinding.getRoot();
    }
}