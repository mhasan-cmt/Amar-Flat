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
import com.teamphoenix.amarflat.databinding.FragmentPlotsBinding;

public class PlotsFragment extends Fragment {


    FragmentPlotsBinding plotsBinding;
    public PlotsFragment() {
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
        propertyType = "Residential Plot";
        ((PostAd)getActivity()).getProperyType(propertyType);
        plotsBinding.plotsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (plotsBinding.residentialPlot.isChecked()){
                    propertyType = "Residential Plot";
                }else if (plotsBinding.commercialPlot.isChecked()){
                    propertyType = "Commercial Plot";
                }else if(plotsBinding.agriculturalLand.isChecked()){
                    propertyType = "Agricultural Land";
                }else if(plotsBinding.industrialLand.isChecked()){
                    propertyType = "Industrial Land";
                }else if(plotsBinding.plotFile.isChecked()){
                    propertyType = "Plot File";
                }else if(plotsBinding.plotForm.isChecked()){
                    propertyType = "Plot Form";
                }else{
                    propertyType = "Residential Plot";
                }
                ((PostAd)getActivity()).getProperyType(propertyType);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        plotsBinding = FragmentPlotsBinding.inflate(inflater,container,false);
        return plotsBinding.getRoot();
    }
}