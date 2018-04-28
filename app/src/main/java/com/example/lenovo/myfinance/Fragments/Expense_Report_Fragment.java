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

/**
 * A simple {@link Fragment} subclass.
 */
public class Expense_Report_Fragment extends Fragment {
    @BindView(R.id.piechart_transactions)
    PieChart pieChart;
    @BindView(R.id.linechart_transactions)
    LineChart lineChart;
    DBHelper mydb;
    private List<Float> yData;
    private List<String> xData;

    public Expense_Report_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_report, container, false);
        ButterKnife.bind(this, view);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadExpensePiechart();
    //    loadExpenseLinechart();

    }

    public void loadExpensePiechart(){

        mydb = new DBHelper(getActivity());
        yData = mydb.getexpenseChartdate();
        xData = mydb.getexpenseChartnames();
        if(yData != null){
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


            PieDataSet dataSet = new PieDataSet(yValues,"Categories");
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
            Toast.makeText(getActivity(), "no data to show", Toast.LENGTH_SHORT).show();
        }

    }
    public void loadExpenseLinechart(){
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(true);
        mydb = new DBHelper(getActivity());
        yData = mydb.getexpenseChartdate();
        xData = mydb.getexpenseChartnames();
        if(yData != null){
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
        else {
            Toast.makeText(getActivity(), "no data to show", Toast.LENGTH_SHORT).show();
        }



    }

}
