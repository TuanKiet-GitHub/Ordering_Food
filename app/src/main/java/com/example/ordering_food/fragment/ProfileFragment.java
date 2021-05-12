package com.example.ordering_food.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.ordering_food.R;
import com.example.ordering_food.adapter.profile_Adapter;
import com.google.android.material.tabs.TabLayout;


public class ProfileFragment extends Fragment {
     TabLayout tabLayout;
     ViewPager viewPager;
     profile_Adapter adapter ;
     View view ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        // dòng view để ánh xạ đến các phần tử
        view =inflater.inflate(R.layout.fragment_profile, container , false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        addFragment(viewPager);
        /*
        * tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
          tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
          tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        * */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#F65555"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    void addFragment(ViewPager viewPager)
    {
        adapter = new profile_Adapter(getChildFragmentManager());
        String login = getString(R.string.login);
        String signUp = getString(R.string.signup);
        adapter.addFragment(new LoginFragment(), "" + login);
        adapter.addFragment(new SignUpFragment() , "" + signUp);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}