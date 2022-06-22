package com.s4team.java.musiachivem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.s4team.java.musiachivem.ui.view.account.AccountFragment;
import com.s4team.java.musiachivem.ui.view.explore.ExploreFragment;
import com.s4team.java.musiachivem.ui.view.home.HomeFragment;
import com.s4team.java.musiachivem.ui.view.radio.RadioFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                int id = item.getItemId();
                if(id == R.id.bottom_nav_home) {
                    loadFragment(new HomeFragment());
                    return true;
                } else if(id == R.id.bottom_nav_explore) {
                    loadFragment(new ExploreFragment());
                    return true;
                } else if(id == R.id.bottom_nav_radio) {
                    loadFragment(new RadioFragment());
                    return true;
                } else if(id == R.id.bottom_nav_user) {
                    loadFragment(new AccountFragment());
                    return true;
                }
                return true;
            };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setReorderingAllowed(true);
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
    }

}