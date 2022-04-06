package com.teamphoenix.amarflat.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamphoenix.amarflat.ContactUsActivity;
import com.teamphoenix.amarflat.LoginActivity;
import com.teamphoenix.amarflat.PostAd.PostAd;
import com.teamphoenix.amarflat.ProfileSetting;
import com.teamphoenix.amarflat.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding profileBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = getContext().getSharedPreferences("user_data",MODE_PRIVATE);
        editor = preferences.edit();

        profileBinding.userName.setText(preferences.getString("user_name","0"));

        profileBinding.contactUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ContactUsActivity.class));
            }
        });


        profileBinding.postAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PostAd.class));
            }
        });
        profileBinding.profileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfileSetting.class));
            }
        });
        profileBinding.logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return profileBinding.getRoot();
    }
}