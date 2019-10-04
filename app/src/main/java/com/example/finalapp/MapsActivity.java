package com.example.finalapp;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

public class MapsActivity extends Fragment implements OnMapReadyCallback  {


    GoogleMap map;
    GoogleMap m1;
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 1000;
    SearchView searchView;
    Location currentloc;

    Button b1,b2;

    FusedLocationProviderClient fusedLocationProviderClient;

    public static final int Request_code = 1;
    public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 100;


    public MapsActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_maps, container, false);
        b1 = rootView.findViewById(R.id.hos);
        b2 = rootView.findViewById(R.id.phar);
        Places.initialize(getContext(),"AIzaSyA3I7QCOGXO4XQBFlhfZYL65J7b03rdpqo");
        PlacesClient placesClient = Places.createClient(getContext());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
           // Toast.makeText(getContext(), "in1", Toast.LENGTH_LONG).show();
            loc_checker();
        } else {
            requestPermissions();
        }

        return rootView;
    }


    private void loc_checker() {

        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            fetchLocation();
        }
    }

    private void requestPermissions() {
            // Toast.makeText(getContext(), "in", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION}, Request_code);

            loc_checker();

    }

    private void fetchLocation() {


        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentloc = location;
                    Toast.makeText(getContext(), currentloc.getLatitude() + " ," + currentloc.getLongitude(), Toast.LENGTH_LONG).show();
                    final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap mMap) {
                            map = mMap;

                            LatLng latLng = new LatLng(currentloc.getLatitude(), currentloc.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am Here");

                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                            mMap.addMarker(markerOptions);

                            map.setMyLocationEnabled(true);

                            b1.setOnClickListener(new View.OnClickListener() {
                                String Restaurant = "hospital";
                                @Override
                                public void onClick(View view) {
                                    map.clear();
                                    String url = getUrl(currentloc.getLatitude(), currentloc.getLongitude(), Restaurant);
                                    Object[] DataTransfer = new Object[3];
                                    DataTransfer[0] = map;
                                    DataTransfer[1] = url;
                                    DataTransfer[2] = getContext();
                                    Toast.makeText(getContext(),url,Toast.LENGTH_LONG).show();
                                    GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                                    getNearbyPlacesData.execute(DataTransfer);
                                }
                            });
                            b2.setOnClickListener(new View.OnClickListener() {
                                String Restaurant = "pharmacy";
                                @Override
                                public void onClick(View view) {
                                    map.clear();
                                    String url = getUrl(currentloc.getLatitude(), currentloc.getLongitude(), Restaurant);
                                    Object[] DataTransfer = new Object[3];
                                    DataTransfer[0] = map;
                                    DataTransfer[1] = url;
                                    DataTransfer[2] = getContext();
                                    Toast.makeText(getContext(),url,Toast.LENGTH_LONG).show();
                                    GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                                    getNearbyPlacesData.execute(DataTransfer);
                                }
                            });

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case PERMISSIONS_REQUEST_ENABLE_GPS:
              //  Toast.makeText(getContext(),"out1",Toast.LENGTH_SHORT).show();

                fetchLocation();
                break;
        }
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyA3I7QCOGXO4XQBFlhfZYL65J7b03rdpqo");
        return (googlePlacesUrl.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(),"out",Toast.LENGTH_SHORT).show();
                        loc_checker();
                }
                break;
        }
    }
}
