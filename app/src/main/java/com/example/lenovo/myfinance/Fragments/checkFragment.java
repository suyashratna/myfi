package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class checkFragment extends Fragment {

    ViewPager mViewPager;
    TabLayout mTablayout;

    public checkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mViewPager =(ViewPager) getActivity().findViewById(R.id.chooser_viewpager);
//        setupViewPager(mViewPager);
//
//
//        mTablayout = (TabLayout) getActivity().findViewById(R.id.chooser_tab);
//        mTablayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new Income_Fragment(),"INCOME");
        adapter.addFragment(new Expense_Fragment(),"EXPENSE");
        adapter.addFragment(new Transfer_fragment(),"TRANSFER");
        mViewPager.setAdapter(adapter);
    }


}
