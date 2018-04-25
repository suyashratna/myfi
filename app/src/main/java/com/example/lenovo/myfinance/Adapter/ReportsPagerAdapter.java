package com.example.lenovo.myfinance.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.myfinance.Fragments.Expense_Report_Fragment;
import com.example.lenovo.myfinance.Fragments.Income_Report_Fragment;

/**
 * Created by lenovo on 4/25/2018.
 */

public class ReportsPagerAdapter extends FragmentPagerAdapter {
    public ReportsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Expense_Report_Fragment();
                break;
            case 1:
                fragment = new Income_Report_Fragment();
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
        switch (position)
        {
            case 0:
                title ="expense";
                break;
            case 1:
                title ="income";
                break;
        }
        return title;
    }
}
