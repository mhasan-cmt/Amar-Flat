package com.teamphoenix.amarflat.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.amarflat.R;

import java.util.ArrayList;

public class PostAdPhotoViewRecycler extends RecyclerView.Adapter<PostAdPhotoViewRecycler.MyViewHolder> {
    Context context;
    ArrayList<Uri> postAdImageList;

    public PostAdPhotoViewRecycler(Context context, ArrayList<Uri> postAdImageList) {
        this.context = context;
        this.postAdImageList = postAdImageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.post_ad_photo_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.image.setImageURI(postAdImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return postAdImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
