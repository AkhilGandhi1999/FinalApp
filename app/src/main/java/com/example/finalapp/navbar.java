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
        setContentView((int) R.layout.activity_navbar);
        ((BottomNavigationView) findViewById(R.id.nav_view)).setOnNavigationItemSelectedListener(this);
        loadfragment(new HomeFragment());
    }

    private boolean loadfragment(Fragment fragment) {
        if (fragment == null) {
            return false;
        }

        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_dashboard /*2131230914*/:
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
