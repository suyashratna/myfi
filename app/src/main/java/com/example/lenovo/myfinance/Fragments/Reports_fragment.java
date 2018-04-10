package com.example.lenovo.myfinance.Fragments;

import android.app.Fragment;
import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.myfinance.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Reports_fragment extends android.support.v4.app.Fragment {


    Animation uptodown;


    @BindView(R.id.report_image)
    ImageView analytics;


    @BindView(R.id.piechart_transactions) PieChart pieChart;

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




//        uptodown = AnimationUtils.loadAnimation(getActivity(),R.anim.uptodown);
//        view.setAnimation(uptodown);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,5,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(40f);

        ArrayList<PieEntry > yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"Restaurant"));
        yValues.add(new PieEntry(23f,"Electricity"));
        yValues.add(new PieEntry(14f,"Health"));
        yValues.add(new PieEntry(44f,"Communication"));
        yValues.add(new PieEntry(35f,"Transportation"));

        PieDataSet dataSet = new PieDataSet(yValues,"Expenses");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);



        return view;

    }
}


