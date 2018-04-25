package com.example.lenovo.myfinance.Fragments;

import android.app.Fragment;
import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

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
    @BindView(R.id.piechart_transactions) PieChart pieChart;
    @BindView(R.id.linechart_transactions)
    LineChart lineChart;
    DBHelper mydb;
    private List<Float> yData;
    private List<String> xData;

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




        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadpiechart();
        loadlinechart();

    }

    public void loadpiechart(){

        mydb = new DBHelper(getActivity());
        yData = mydb.getexpenseChartdate();
        xData = mydb.getexpenseChartnames();
        Object[] yArray = yData.toArray();
        Object[] xArray = xData.toArray();

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,5,15,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterText("expense pie");
        pieChart.setCenterTextColor(Color.RED);
        pieChart.setCenterTextSize(10);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(40f);

        ArrayList<PieEntry > yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        for(int i=0;i< yData.size();i++){
            yValues.add(new PieEntry((float) yArray[i],i));
        }
        for(int i=0;i< xData.size();i++){
            xValues.add((String)xArray[i]);
        }


        PieDataSet dataSet = new PieDataSet(yValues,"Expenses");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateXY(800,800);
    }
    public void loadlinechart(){
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        mydb = new DBHelper(getActivity());
        yData = mydb.getexpenseChartdate();
        xData = mydb.getexpenseChartnames();
        Object[] yArray = yData.toArray();
        Object[] xArray = xData.toArray();

        ArrayList<Entry> yvalues = new ArrayList<>();

        for(int i=0;i< yData.size();i++){
            yvalues.add(new Entry((float) yArray[i],i));
        }

        LineDataSet set1 = new LineDataSet(yvalues,"Expenses");

        set1.setFillAlpha(110);

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

       LineData data = new LineData(set1);



        lineChart.setData(data);


    }

    class MyTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
         return null;
        }
    }




}


