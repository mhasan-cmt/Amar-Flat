package com.teamphoenix.amarflat.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamphoenix.amarflat.Adapter.PostAdAdapter;
import com.teamphoenix.amarflat.Model.property;
import com.teamphoenix.amarflat.R;
import com.teamphoenix.amarflat.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {
    FragmentFavoritesBinding favoritesBinding;
    PostAdAdapter postAdAdapter;
    ArrayList<property> propertyArrayList;
    public FavoritesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritesBinding.postList.setLayoutManager(new LinearLayoutManager(getContext()));
        propertyArrayList = new ArrayList<>();
        postAdAdapter = new PostAdAdapter(getContext(),propertyArrayList);
        favoritesBinding.postList.setAdapter(postAdAdapter);

//        getActivity().getActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return favoritesBinding.getRoot();
    }
}