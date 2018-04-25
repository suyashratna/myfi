package com.example.lenovo.myfinance.Fragments;

import android.app.Fragment;
import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.ReportsPagerAdapter;
import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Reports_fragment extends android.support.v4.app.Fragment {

    Animation uptodown;
    @BindView(R.id.reports_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.reports_viewpager)
    ViewPager mViewPager;
    ReportsPagerAdapter reportsPagerAdapter;

    public Reports_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        ButterKnife.bind(this, view);

         return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setAdapter(new ReportsPagerAdapter(getChildFragmentManager()));
        mTablayout.setSelectedTabIndicatorColor(Color.parseColor("#36b9e4"));
        mTablayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#36b9e4"));
        mTablayout.setupWithViewPager(mViewPager);

    }



    class MyTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
         return null;
        }
    }




}


