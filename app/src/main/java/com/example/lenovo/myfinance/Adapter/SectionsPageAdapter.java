package com.example.lenovo.myfinance.Adapter;


import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2/20/2018.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    public SectionsPageAdapter(android.support.v4.app.FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitleList.get(position);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position){
        return mFragmentList.get(position);
    }

    @Override
    public int getCount(){
        return mFragmentList.size();
    }


}
