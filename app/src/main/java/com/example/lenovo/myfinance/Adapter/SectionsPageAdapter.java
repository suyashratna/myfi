package com.example.lenovo.myfinance.Adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.myfinance.Fragments.Expense_Fragment;
import com.example.lenovo.myfinance.Fragments.Income_Fragment;
import com.example.lenovo.myfinance.Fragments.Transfer_fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2/20/2018.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter{

    public SectionsPageAdapter(FragmentManager fm){
        super(fm);

    }


    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;
       switch (position) {
           case 0:
               fragment = new Income_Fragment();
               break;
           case 1:
               fragment = new Expense_Fragment();
               break;
           case 2:
               fragment = new Transfer_fragment();
               break;
            }
               return  fragment;
       }


    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
        case 0:
            title ="Income";
            break;

        case 1:
            title ="Expense";
            break;
        case 2:
             title="Transfer";
            break;

        }
        return title;
    }
}
