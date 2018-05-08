package com.example.lenovo.myfinance.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.FundList_Adapter;
import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Dialogs.AddFund_dialog;
import com.example.lenovo.myfinance.Dialogs.CreateFund_dialog;
import com.example.lenovo.myfinance.Interface.FundItemClickListener;
import com.example.lenovo.myfinance.Model.Fund;
import com.example.lenovo.myfinance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFunds_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.add_fund_button)Button mAddfund_button;
    @BindView(R.id.create_fund_button) Button mCreatefund_button;

    @BindView(R.id.myfund_recyclerview)
    RecyclerView mMyfund_recyclerview;
    private FundList_Adapter fundList_adapter;

    private List<Fund> mFundList;

    @BindView(R.id.swiperefresh_layout)
    SwipeRefreshLayout mSwipefreshlayout;
    DBHelper myDb;

    public MyFunds_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_fund, container, false);
        ButterKnife.bind(this,view);
        mSwipefreshlayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadFundRecyclerView();

        mAddfund_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFund_dialog addFund_dialog = new AddFund_dialog();
                addFund_dialog.show(getFragmentManager(),"addfund");

            }
        });

        mCreatefund_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateFund_dialog createFund_dialog = new CreateFund_dialog();
                createFund_dialog.show(getFragmentManager(),"createfund");

            }
        });
    }

    public void loadFundRecyclerView(){

        mSwipefreshlayout.setRefreshing(true);
        myDb = new DBHelper(getActivity());
        mFundList = myDb.getFundData();
        if (mFundList == null){
            Toast.makeText(getContext(), "null list", Toast.LENGTH_SHORT).show();
        }
        fundList_adapter = new FundList_Adapter(mFundList, getContext(), new FundItemClickListener() {
            @Override
            public void onFundItemClick(View view, int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

                alertDialog.setTitle("Delete the Fund?");
                alertDialog.setMessage("All the fund amount will be lost");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                alertDialog.show();
            }

        });

        mMyfund_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyfund_recyclerview.setAdapter(fundList_adapter);
        mMyfund_recyclerview.smoothScrollToPosition(0);
        mSwipefreshlayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {

        fundList_adapter.notifyDataSetChanged();
        loadFundRecyclerView();


    }
}
