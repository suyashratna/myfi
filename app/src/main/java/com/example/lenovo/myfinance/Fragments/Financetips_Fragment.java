package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.TipsList_Adapter;
import com.example.lenovo.myfinance.Model.Tip;
import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Financetips_Fragment extends Fragment {

    @BindView(R.id.tips_recyclerview)
    RecyclerView mTipRecyclerView;
    private TipsList_Adapter mTipListAdapter;
    private List<Tip> TipList;


    public Financetips_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financetips, container, false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadTipdata();
    }

    public void LoadTipdata(){
        TipList = new ArrayList<Tip>() ;
        TipList.add(new Tip(null,"Develop a Workable Household Budget"," evaluate your spending habits before creating your budget. Track every penny that crosses your path, incoming and outgoing, for a month."));
        TipList.add(new Tip(null,"Set Up an Emergency Fund"," An emergency fund can help defray the impact of the unforeseen on your monthly budget. Aim to set aside six months' worth of living expenses, but if that seems insurmountable, start with a smaller goal."));
        TipList.add(new Tip(null,"Cultivate a Debt-Free Lifestyle","Once you've fully funded your retirement plans and your emergency fund, tackle your debt and pay off loan and credit card balances ahead of schedule."));
        mTipListAdapter = new TipsList_Adapter(TipList);
        mTipRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTipRecyclerView.setAdapter(mTipListAdapter);
        mTipRecyclerView.smoothScrollToPosition(0);
    }
}
