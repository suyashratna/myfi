package com.example.lenovo.myfinance.Fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Adapter.IncomeListAdapter;
import com.example.lenovo.myfinance.Bottomsheet_dialog;


import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Dialogs.TransactionItem_dialog;
import com.example.lenovo.myfinance.Interface.TransactionItemClickListener;
import com.example.lenovo.myfinance.Model.Income;
import com.example.lenovo.myfinance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Transaction_fragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Animation uptodown;
    Animation downtoup;
    Animation rotate;

    ViewPager mViewPager;
    TabLayout mTablayout;
    SectionsPageAdapter sectionsPageAdapter;

    @BindView(R.id.addtransaction_button)
    Button maddtransaction_button ;

    @BindView(R.id.trans_image)ImageView transactions;

    @BindView(R.id.TopInfo_relative)
    RelativeLayout mtoprelative;


    DBHelper myDb;

    private IncomeListAdapter mIncomeListAdapter;
    private List<Income> incomeList;

    @BindView(R.id.income_recyclerview)
    RecyclerView mIncomeRecycler;

    @BindView(R.id.swiperefresh_layout)
    SwipeRefreshLayout mSwipefreshlayout;



    public Transaction_fragment() {
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
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        ButterKnife.bind(this,view);

        mSwipefreshlayout.setOnRefreshListener(this);
        loadRecyclerViewData();


//      add transaction based on the selected tab
        maddtransaction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseCategory_fragment chooseCategory_fragment = new ChooseCategory_fragment();
                chooseCategory_fragment.show(getFragmentManager(),"chooser");
                chooseCategory_fragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        loadRecyclerViewData();
                    }
                });

            }
        });

        mSwipefreshlayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipefreshlayout.setRefreshing(true);

                // Fetching data from server
                loadRecyclerViewData();
            }
        });
         return view;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cleartransactions) {
            myDb.clearHistory();
            incomeList.clear();
            mIncomeListAdapter = new IncomeListAdapter(incomeList, new TransactionItemClickListener() {
                @Override
                public void OnTransItemClick(View view, int position) {
                    TransactionItem_dialog item_dialog = new TransactionItem_dialog();
                    item_dialog.show(getFragmentManager(),"ITEM");
                }
            });
            mIncomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            mIncomeRecycler.setAdapter(mIncomeListAdapter);
            mIncomeRecycler.smoothScrollToPosition(0);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        loadRecyclerViewData();

    }
    public void loadRecyclerViewData(){
        mSwipefreshlayout.setRefreshing(true);
        myDb = new DBHelper(getActivity());
        incomeList = myDb.getIncomeData();

        mIncomeListAdapter = new IncomeListAdapter(incomeList, new TransactionItemClickListener() {
            @Override
            public void OnTransItemClick(View view, int position) {
                TransactionItem_dialog item_dialog = new TransactionItem_dialog();
                item_dialog.show(getFragmentManager(),"ITEM");

            }
        });
        mIncomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIncomeRecycler.setAdapter(mIncomeListAdapter);
        mIncomeRecycler.smoothScrollToPosition(0);
        mSwipefreshlayout.setRefreshing(false);
    }
}
