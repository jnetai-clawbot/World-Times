package com.jnetai.worldtimes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jnetai.worldtimes.fragments.AboutFragment;
import com.jnetai.worldtimes.fragments.ClockFragment;
import com.jnetai.worldtimes.fragments.SearchFragment;
import com.jnetai.worldtimes.fragments.UtcBlocksFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment activeFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    private final ClockFragment clockFragment = new ClockFragment();
    private final UtcBlocksFragment utcBlocksFragment = new UtcBlocksFragment();
    private final SearchFragment searchFragment = new SearchFragment();
    private final AboutFragment aboutFragment = new AboutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.fragment_container, clockFragment, "clock");
            transaction.add(R.id.fragment_container, utcBlocksFragment, "utc");
            transaction.add(R.id.fragment_container, searchFragment, "search");
            transaction.add(R.id.fragment_container, aboutFragment, "about");
            transaction.hide(utcBlocksFragment);
            transaction.hide(searchFragment);
            transaction.hide(aboutFragment);
            transaction.commit();
            activeFragment = clockFragment;
        } else {
            activeFragment = fragmentManager.findFragmentByTag("clock");
        }

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment selected = null;
            if (id == R.id.nav_clock) selected = clockFragment;
            else if (id == R.id.nav_utc) selected = utcBlocksFragment;
            else if (id == R.id.nav_search) selected = searchFragment;
            else if (id == R.id.nav_about) selected = aboutFragment;

            if (selected != null && selected != activeFragment) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.hide(activeFragment);
                ft.show(selected);
                ft.commit();
                activeFragment = selected;
            }
            return true;
        });
    }
}
