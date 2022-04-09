package com.teamphoenix.amarflat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.amarflat.Model.property;
import com.teamphoenix.amarflat.PostViewActivity;
import com.teamphoenix.amarflat.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        property property = propertyArrayList.get(position);
        holder.propertyPurposeType.setText(property.getProperty_type()+" For "+property.getPurpose());
        holder.propertyPrice.setText(property.getTotal_price());
        holder.propertyAddress.setText(property.getLocation());
        holder.totalBathroom.setText(property.getBathroom());
        holder.totalBedroom.setText(property.getBedrooms());
        holder.areaSize.setText(property.getArea_size());
        holder.postTime.setText(property.getTime());
        holder.postTime.setText(property.getTime());

        holder.propertyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostViewActivity.class);
                Map<String, property>  value= new HashMap<>();
                value.put("data",property);
                intent.putExtra("property",  property);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return propertyArrayList.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder{
        TextView propertyPrice, propertyAddress, totalBedroom, totalBathroom, areaSize, postTime, smsButton, callButton, propertyPurposeType;
        ImageView favoriteButton;
        CardView propertyCard;
        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            propertyPurposeType = itemView.findViewById(R.id.propertyPurposeType);
            propertyPrice = itemView.findViewById(R.id.propertyPrice);
            propertyAddress = itemView.findViewById(R.id.propertyAddress);
            totalBathroom = itemView.findViewById(R.id.totalBathroom);
            totalBedroom = itemView.findViewById(R.id.totalBedroom);
            areaSize = itemView.findViewById(R.id.areaSize);
            postTime = itemView.findViewById(R.id.postTime);
            smsButton = itemView.findViewById(R.id.smsButton);
            callButton = itemView.findViewById(R.id.callButton);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
            propertyCard = itemView.findViewById(R.id.propertyCard);
        }
    }
}

