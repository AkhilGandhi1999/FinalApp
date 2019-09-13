package com.example.finalapp;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class navbar extends AppCompatActivity implements OnNavigationItemSelectedListener {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
        loadfragment(new HomeFragment());
    }

    private boolean loadfragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fg1,fragment).commit();
            return true;
        }

        return false;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_dashboard :
                fragment = new GraphFragment();
                break;
            case R.id.navigation_home /*2131230916*/:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_notifications /*2131230917*/:
                fragment = new MapsFragment();
                break;
        }
        return loadfragment(fragment);
    }
}
