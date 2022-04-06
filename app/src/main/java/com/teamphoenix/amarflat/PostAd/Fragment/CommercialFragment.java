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
import com.teamphoenix.amarflat.databinding.FragmentCommercialBinding;

public class CommercialFragment extends Fragment {
    FragmentCommercialBinding commercialBinding;

    public CommercialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commercialBinding.commercialRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String propertyType="";
                if (commercialBinding.office.isChecked()){
                    propertyType = "Office";
                }else if (commercialBinding.shop.isChecked()){
                    propertyType = "Shop";
                }else if(commercialBinding.warehouse.isChecked()){
                    propertyType = "Warehouse";
                }else if(commercialBinding.factory.isChecked()){
                    propertyType = "Factory";
                }else if(commercialBinding.building.isChecked()){
                    propertyType = "Building";
                }else if(commercialBinding.other.isChecked()){
                    propertyType = "Other";
                }else{
                    propertyType = "Office";
                }
                ((PostAd)getActivity()).getProperyType(propertyType);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        commercialBinding = FragmentCommercialBinding.inflate(inflater,container,false);
        return commercialBinding.getRoot();
    }
}