package com.teamphoenix.amarflat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.amarflat.Model.property;
import com.teamphoenix.amarflat.R;

import java.util.ArrayList;

public class PostAdAdapter extends RecyclerView.Adapter<PostAdAdapter.MyViewAdapter> {

    Context context;
    ArrayList<property> propertyArrayList;

    public PostAdAdapter(Context context, ArrayList<property> propertyArrayList) {
        this.context = context;
        this.propertyArrayList = propertyArrayList;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewAdapter(LayoutInflater.from(context).inflate(R.layout.single_post,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder{
        TextView propertyPrice, propertyAddress, totalBedroom, totalBathroom, areaSize, postTime, smsButton, callButton;
        ImageView favoriteButton;
        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            propertyPrice = itemView.findViewById(R.id.propertyPrice);
            propertyAddress = itemView.findViewById(R.id.propertyAddress);
            totalBathroom = itemView.findViewById(R.id.totalBathroom);
            totalBedroom = itemView.findViewById(R.id.totalBedroom);
            areaSize = itemView.findViewById(R.id.areaSize);
            postTime = itemView.findViewById(R.id.postTime);
            smsButton = itemView.findViewById(R.id.smsButton);
            callButton = itemView.findViewById(R.id.callButton);

            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}

