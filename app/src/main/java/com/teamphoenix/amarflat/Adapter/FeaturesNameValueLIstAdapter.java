package com.teamphoenix.amarflat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.amarflat.Model.FeatureNameValue;
import com.teamphoenix.amarflat.R;

import java.util.ArrayList;

public class FeaturesNameValueLIstAdapter extends RecyclerView.Adapter<FeaturesNameValueLIstAdapter.MyViewHOlder> {

    Context context;
    ArrayList<FeatureNameValue> arrayList;

    public FeaturesNameValueLIstAdapter(Context context, ArrayList<FeatureNameValue> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHOlder(LayoutInflater.from(context).inflate(R.layout.features_list_with_value,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHOlder holder, int position) {
        holder.featureName.setText(arrayList.get(position).getFeatureName());
        holder.featureValue.setText(arrayList.get(position).getFeatureValue());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder{
        TextView featureName, featureValue;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            featureName = itemView.findViewById(R.id.featureName);
            featureValue = itemView.findViewById(R.id.featureValue);
        }
    }
}
