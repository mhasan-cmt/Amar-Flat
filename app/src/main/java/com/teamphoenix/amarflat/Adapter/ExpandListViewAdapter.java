package com.teamphoenix.amarflat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.teamphoenix.amarflat.AddFeature;
import com.teamphoenix.amarflat.Model.FeatureCategory;
import com.teamphoenix.amarflat.Model.features;
import com.teamphoenix.amarflat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpandListViewAdapter extends BaseExpandableListAdapter {
    ArrayList<FeatureCategory> featureCategoryArrayList;
    Map<String, ArrayList<features>> featureArrayList;
    Map<String, String> featureDataList = new HashMap<>();

    public ExpandListViewAdapter(ArrayList<FeatureCategory> featureCategoryArrayList, Map<String, ArrayList<features>> featureArrayList) {
        this.featureCategoryArrayList = featureCategoryArrayList;
        this.featureArrayList = featureArrayList;
    }

    @Override
    public int getGroupCount() {
        return featureCategoryArrayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return featureArrayList.get(featureCategoryArrayList.get(i).getFeatureCategoryId()).size();
    }

    @Override
    public Object getGroup(int i) {
        return featureCategoryArrayList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return featureArrayList.get(featureCategoryArrayList.get(i).getFeatureCategoryId()).get(i1).getFeature_name();
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_item,viewGroup,false);
        TextView textView= view1.findViewById(R.id.text1);
        textView.setText(featureCategoryArrayList.get(i).getFeatureCategoryName());
        return view1;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_demo,viewGroup,false);
        TextView textView= view1.findViewById(R.id.text1);
        Switch switch1 = view1.findViewById(R.id.switch1);
        textView.setText(getChild(i,i1).toString());
        featureDataList.put(getChild(i,i1).toString(),"No");
        ((AddFeature) viewGroup.getContext()).getFeatureData(featureDataList);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String switchValue = "";
                if (switch1.isChecked()){
                    switchValue= "Yes";
                }else {
                    switchValue = "No";
                }
                featureDataList.put(getChild(i,i1).toString(),switchValue);
                ((AddFeature) viewGroup.getContext()).getFeatureData(featureDataList);
            }
        });
        return view1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
