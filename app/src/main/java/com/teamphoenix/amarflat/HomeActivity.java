package com.teamphoenix.amarflat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.teamphoenix.amarflat.Fragment.FavoritesFragment;
import com.teamphoenix.amarflat.Fragment.HomeFragment;
import com.teamphoenix.amarflat.Fragment.ProfileFragment;
import com.teamphoenix.amarflat.Fragment.ProjectsFragment;
import com.teamphoenix.amarflat.PostAd.PostAd;
import com.teamphoenix.amarflat.databinding.ActivityHomeBinding;
import com.teamphoenix.amarflat.util.LanguageUtil;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ActivityHomeBinding homeBinding;
    SharedPreferences sharedPreferences;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        setContentView(homeBinding.getRoot());
        AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.WRAP_CONTENT,
                AppBarLayout.LayoutParams.WRAP_CONTENT
        );

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                homeBinding.homeDrawer,
                homeBinding.mainToolbar,
                R.string.Open_Drawer,
                R.string.Close_Drawer);
        homeBinding.homeDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        homeBinding.mainNavView.setCheckedItem(R.id.nav_home);
        homeBinding.mainNavView.setNavigationItemSelectedListener(this);
        View navViewHeader = homeBinding.mainNavView.getHeaderView(0);
        TextView userNameNavHeader = navViewHeader.findViewById(R.id.nav_header_userName);

        userNameNavHeader.setText(sharedPreferences.getString("user_name","0"));
        userNameNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(profileFragment==null){
                    profileFragment = new ProfileFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,profileFragment).addToBackStack(null).commit();

            }
        });
        homeBinding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        homeBinding.imageTitle.setContentDescription("Home");
                        fragment = new HomeFragment();
                        break;
                    case R.id.project:
//                        homeBinding.imageTitle.setContentDescription("Project");
//                        fragment = new ProjectsFragment();
//                        break;
                        startActivity(new Intent(getApplicationContext(),SearchResultActivity.class));
                        return false;
                    case R.id.favorites:
                        homeBinding.imageTitle.setContentDescription("Favorites");
                        fragment = new FavoritesFragment();
                        break;
                    case R.id.profile:
                        homeBinding.imageTitle.setContentDescription("Profile");
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,fragment).addToBackStack(null).commit();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStackImmediate();
        if (homeBinding.homeDrawer.isDrawerOpen(GravityCompat.START)) {
            homeBinding.homeDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_property:
                startActivity(new Intent(this, PostAd.class));
                break;
            case R.id.nav_contact:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;
            case R.id.nav_search:
                startActivity(new Intent(this, SearchFilterActivity.class));
                break;
            case R.id.nav_language:
                if(homeBinding.mainToolbar.getTitle().equals("Amar Flat")){
                    LanguageUtil.setLanguage(this,"bn");
                    startActivity(new Intent(this, SplashScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }else{
                    LanguageUtil.setLanguage(this,"en");
                    startActivity(new Intent(this, SplashScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
                break;
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(homeBinding.homeFragmentContainer.getId(), homeFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }

        return true;
    }
}