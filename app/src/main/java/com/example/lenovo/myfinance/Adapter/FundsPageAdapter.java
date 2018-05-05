package com.example.lenovo.myfinance.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.myfinance.Fragments.Fund_Chart_Fragment;
import com.example.lenovo.myfinance.Fragments.MyFunds_Fragment;

/**
 * Created by lenovo on 5/5/2018.
 */

public class FundsPageAdapter extends FragmentPagerAdapter{
    public FundsPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       Fragment fragment = null;
       switch(position){
           case 0:
               fragment = new MyFunds_Fragment();
               break;
           case 1:
               fragment = new Fund_Chart_Fragment();
               break;
       }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       String title ="";
       switch (position){
           case 0:
               title = "My funds";
               break;
           case 1:
               title = "fund chart";
               break;
       }
       return title;
    }
}
