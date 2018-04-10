package com.example.lenovo.myfinance.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.myfinance.Fragments.Expense_Fragment;
import com.example.lenovo.myfinance.Fragments.Income_Fragment;
import com.example.lenovo.myfinance.Fragments.Transfer_fragment;
import com.example.lenovo.myfinance.Fragments.category_expense_fragment;
import com.example.lenovo.myfinance.Fragments.category_income_fragment;

/**
 * Created by lenovo on 4/6/2018.
 */

public class CategoriesPageAdapter extends FragmentPagerAdapter {
    public CategoriesPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new category_income_fragment();
                break;
            case 1:
                fragment = new category_expense_fragment();
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
        String title = "";
        switch (position) {
            case 0:
                title = "Income";
                break;

            case 1:
                title = "Expense";
                break;
        }
        return title;
    }
}