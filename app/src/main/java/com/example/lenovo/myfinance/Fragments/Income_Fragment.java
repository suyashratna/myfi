package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.IncomeListAdapter;
import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Model.Income;

import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Income_Fragment extends Fragment {
    DBHelper myDb;

    private IncomeListAdapter mIncomeListAdapter;
    private List<Income> incomeList;
    boolean amountadded;
    @BindView(R.id.income_recyclerview) RecyclerView mIncomeRecycler;



    public Income_Fragment() {
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
       View view = inflater.inflate(R.layout.fragment_income, container, false);
       ButterKnife.bind(this,view);

        myDb = new DBHelper(getActivity());
        incomeList = myDb.getIncomeData();

        mIncomeListAdapter = new IncomeListAdapter(incomeList);
        mIncomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIncomeRecycler.setAdapter(mIncomeListAdapter);
        mIncomeRecycler.smoothScrollToPosition(0);

//
//             incomeList.add(new Income("500","January,","Sunday",null,"12","2018"));
//             incomeList.add(new Income("600","February,","Tuesday",null,"20","2018"));

            mIncomeListAdapter.notifyDataSetChanged();
            amountadded = false;
            //  Bundle bundle = getArguments();
//          transactionList.add(new Transaction
//                (String.valueOf(bundle.getString("category")),
//                String.valueOf(bundle.getString("account")),
//                Integer.valueOf(bundle.getString("amount"))));


        return view;

    }






}
