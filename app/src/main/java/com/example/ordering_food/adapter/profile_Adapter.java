package com.example.ordering_food.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class profile_Adapter extends FragmentPagerAdapter {
    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listString = new ArrayList<>();


    public profile_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position) ;
    }

    @Override
    public int getCount() {
        return listString.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) { // lấy string
        return listString.get(position);
    }
    public void addFragment (Fragment fragment , String string)
    {
        listFragment.add(fragment);
        listString.add(string);
    }


}
