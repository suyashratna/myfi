package com.example.lenovo.myfinance.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fund_Chart_Fragment extends Fragment {

    @BindView(R.id.piechart_fund)
    PieChart pieChart;
    @BindView(R.id.Barchart_fund)
    BarChart barChart;

    DBHelper mydb;
    private List<Float> yData;
    private List<String> xData;

    public Fund_Chart_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fund__chart, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadFundPiechart();
        LoadFundBarChart();
    }
    public void LoadFundPiechart(){
        mydb = new DBHelper(getActivity());
        xData =  mydb.getFundNames();
        yData = mydb.getFundYdata();
        if(yData != null){
            Object[] yArray = yData.toArray();
            Object[] xArray = xData.toArray();

            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5,5,15,5);

            pieChart.setDragDecelerationFrictionCoef(0.95f);
            pieChart.setCenterText("Fund pie");
            pieChart.setCenterTextColor(Color.RED);
            pieChart.setCenterTextSize(10);
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleColor(Color.WHITE);
            pieChart.setHoleRadius(30f);
            pieChart.setTransparentCircleRadius(40f);

            ArrayList<PieEntry > yValues = new ArrayList<>();
            ArrayList<String> xValues = new ArrayList<>();

            for(int i=0;i< yData.size();i++){
                yValues.add(new PieEntry((float) yArray[i],(String) xArray[i]));
            }
//            for(int i=0;i< xData.size();i++){
//                xValues.add();
//            }


            PieDataSet dataSet = new PieDataSet(yValues,"FUND NAMES");
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
        else {
            Toast.makeText(getActivity(), "no fund data to show", Toast.LENGTH_SHORT).show();
        }

    }
    public void LoadFundBarChart(){
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        mydb = new DBHelper(getActivity());
        yData =mydb.getFundYdata();

        if(yData != null){
            Object[] yArray = yData.toArray();
            String[] xArray = new String[xData.size()];
            xArray = xData.toArray(xArray);

            ArrayList<BarEntry> yvalues = new ArrayList<>();
            //   Collections.sort(yvalues,new EntryXComparator());

            for(int i=0; i< yData.size();i++){
                yvalues.add(new BarEntry(i,(float) yArray[i]));
            }


            BarDataSet set1 = new BarDataSet(yvalues,"fund amounts");
            set1.setColors(ColorTemplate.JOYFUL_COLORS);


            BarData data = new BarData(set1);
            data.setBarWidth(0.2f);


            barChart.setData(data);
            barChart.setFitBars(true);
            barChart.invalidate();

//            XAxis xAxis = barChart.getXAxis();
//            xAxis.setValueFormatter(new MyXAxisValueFormatter(xArray));




        }
        else {
            Toast.makeText(getActivity(), "no fund amount data to show", Toast.LENGTH_SHORT).show();
        }
    }
}
