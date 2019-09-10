package com.example.finalapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg)).getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(1);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(new LatLng(37.4219999d, -122.0862462d)).zoom(10.0f).bearing(0.0f).tilt(45.0f).build()), 10000, null);
                MarkerOptions title = new MarkerOptions().position(new LatLng(37.4219999d, -122.0862462d)).title("Spider Man");
                MapsFragment mapsFragment = MapsFragment.this;
                mMap.addMarker(title.icon(mapsFragment.bitmapDescriptorFromVector(mapsFragment.getActivity(), R.drawable.bb)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.4629101d, -122.2449094d)).title("Iron Man").snippet("His Talent : Plenty of money"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.3092293d, -122.1136845d)).title("Captain America"));
            }
        });
        return rootView;
    }

    /* access modifiers changed from: private */
    public BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Config.ARGB_8888);
        vectorDrawable.draw(new Canvas(bitmap));
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
