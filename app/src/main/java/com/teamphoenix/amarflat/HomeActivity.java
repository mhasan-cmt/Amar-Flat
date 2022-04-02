package com.teamphoenix.amarflat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.teamphoenix.amarflat.Fragment.FavoritesFragment;
import com.teamphoenix.amarflat.Fragment.HomeFragment;
import com.teamphoenix.amarflat.Fragment.ProfileFragment;
import com.teamphoenix.amarflat.Fragment.ProjectsFragment;
import com.teamphoenix.amarflat.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                homeBinding.homeDrawer,
                homeBinding.mainToolbar,
                R.string.Open_Drawer,
                R.string.Close_Drawer);
        homeBinding.homeDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        homeBinding.mainNavView.setCheckedItem(R.id.nav_home);
        homeBinding.mainNavView.setNavigationItemSelectedListener(this);

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
                        homeBinding.imageTitle.setContentDescription("Project");
                        fragment = new ProjectsFragment();
                        break;
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
                Toast.makeText(this, "Set Contact Action Here", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_search:
                Toast.makeText(this, "set search action Here", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_language:
                Toast.makeText(this, "set language action here", Toast.LENGTH_SHORT).show();
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