package com.example.ordering_food.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.ordering_food.R;
import com.example.ordering_food.fragment.AccountFragment;
import com.example.ordering_food.fragment.FavoritesFragment;
import com.example.ordering_food.fragment.HomeFragment;
import com.example.ordering_food.fragment.OrderFragment;
import com.example.ordering_food.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.ordering_food.fragment.LoginFragment.check;

public class Home extends AppCompatActivity {
    BottomNavigationView navigationView;
    Fragment fragment;
    NestedScrollView nestedScrollView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        fragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragment = null ;
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.Favorite :
                        fragment = new FavoritesFragment();
                        break ;
                    case R.id.Home :
                        fragment = new HomeFragment();
                        break;
                    case R.id.Profile :
                        if (check == true )
                        {
                            fragment = new AccountFragment();
                        }
                        else
                        {
                            fragment = new ProfileFragment();
                        }

                        break;

                    case R.id.order :
                        fragment = new OrderFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                        fragment).commit();
                return true;
            }
        });


    }
}