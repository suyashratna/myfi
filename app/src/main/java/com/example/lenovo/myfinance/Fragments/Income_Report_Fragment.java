package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Income_Report_Fragment extends Fragment {
    @BindView(R.id.income_piechart_transactions)
    PieChart pieChart;
    @BindView(R.id.income_linechart_transactions)
    LineChart lineChart;
    DBHelper mydb;

    public Income_Report_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_income_report, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadIncomePiechart();
        loadIncomeLinechart();
    }
    public void loadIncomePiechart(){}
    public void loadIncomeLinechart(){}
}
