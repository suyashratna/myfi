package com.example.lenovo.myfinance.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.FundsPageAdapter;
import com.example.lenovo.myfinance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Funds_fragment extends Fragment {

//    @BindView(R.id.fund_tablayout)
//    TabLayout mTablayout;
//
//    @BindView(R.id.fund_viewpager)
//    ViewPager mViewPager;
    @BindView(R.id.fund_navigation) BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_myfunds:
                    transaction.replace(R.id.fund_fragment_frame, new MyFunds_Fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_fundcharts:
                    transaction.replace(R.id.fund_fragment_frame, new Fund_Chart_Fragment());
                    transaction.commit();
                    return true;
            }
            return false;

            }};

    public Funds_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_funds, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_myfunds);
//        mViewPager.setAdapter(new FundsPageAdapter(getChildFragmentManager()));
//        mTablayout.setSelectedTabIndicatorColor(Color.parseColor("#36b9e4"));
//        mTablayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#36b9e4"));
//        mTablayout.setupWithViewPager(mViewPager);
    }
}
