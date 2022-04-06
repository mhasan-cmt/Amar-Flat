package com.teamphoenix.amarflat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamphoenix.amarflat.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {
    Context context;
    ArrayList<String> cities;
    ArrayList<String> citiesAll;

    public CityAdapter(Context context, ArrayList<String> cities) {
        this.context = context;
        this.cities = cities;
        this.citiesAll = cities;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(context).inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.textCity.setText(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void filter(String text) {
        if(text.isEmpty()){
            cities.clear();
            cities.addAll(citiesAll);
        }else{
            ArrayList<String > result = new ArrayList<>();
            text = text.toLowerCase();
            for (String city: citiesAll){
                if(city.toLowerCase().contains(text)){
                    result.add(city);
                }
            }
            cities.clear();
            cities.addAll(result);
        }
        notifyDataSetChanged();
    }

}

class CityViewHolder extends RecyclerView.ViewHolder {
    public TextView textCity;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        textCity = itemView.findViewById(R.id.txtCity);
    }

}
