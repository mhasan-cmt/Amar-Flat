package com.teamphoenix.amarflat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.teamphoenix.amarflat.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    private Marker currentMarker = null;
    private String locationString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        binding.mapToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.mapDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(locationString.equals("") | locationString.isEmpty()){
                    Toast.makeText(MapsActivity.this, "Search for a location First!", Toast.LENGTH_SHORT).show();
                }else{
                    sendSelectedLocation(locationString.toUpperCase());
                }
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setMapStyle();
        runTimePermission();
        setSearchViewListener();
    }

    private void setSearchViewListener() {
        binding.searchLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ProgressDialog progressDialog = new ProgressDialog(MapsActivity.this);
                progressDialog.setTitle("Please wait");
                progressDialog.setMessage("Searching for your location");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                String locationInput = binding.searchLocation.getQuery().toString();
                List<Address> addresses = null;

                if (locationInput != null || !locationInput.equals("")) {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        progressDialog.dismiss();
                        addresses = geocoder.getFromLocationName(locationInput, 1);

                        Address address = addresses.get(0);
                        locationString =s.trim();
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        if (currentMarker != null) {
                            currentMarker.remove();
                            currentMarker = null;
                        }

                        if (currentMarker == null) {
                            currentMarker = mMap.addMarker(new MarkerOptions().position(latLng).
                                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        }
                    } catch (IOException e) {
                        progressDialog.dismiss();
                        Toast.makeText(MapsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void setMapStyle() {
        try {
            boolean successStyleLoading = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.styled_map));
            if (!successStyleLoading) {
                Toast.makeText(this, "Cant Parse the json file", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void runTimePermission() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                    mMap.getUiSettings().setZoomControlsEnabled(true);
                                    mMap.getUiSettings().setMapToolbarEnabled(true);
                                    mMap.getUiSettings().setAllGesturesEnabled(true);
                                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
                                    currentMarker = mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location!"));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16));
                                } else {
                                    Toast.makeText(MapsActivity.this, "Cant Find any location!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public void sendSelectedLocation(String location) {
        Intent returnIntent = new Intent(MapsActivity.this, SearchFilterActivity.class);
        returnIntent.putExtra("CityName", location);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}