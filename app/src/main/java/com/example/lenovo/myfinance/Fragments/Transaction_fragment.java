package com.example.lenovo.myfinance.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Adapter.IncomeListAdapter;


import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Dialogs.TransactionItem_dialog;
import com.example.lenovo.myfinance.Interface.TransactionItemClickListener;
import com.example.lenovo.myfinance.Model.Transaction;
import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Transaction_fragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Animation uptodown;
    Animation downtoup;
    Animation rotate;



    @BindView(R.id.addtransaction_button)
    Button maddtransaction_button ;

    @BindView(R.id.trans_image)ImageView transactions;

    @BindView(R.id.TopInfo_relative)
    RelativeLayout mtoprelative;

    @BindView(R.id.balance_amount)
    TextView mBalanceamount;


    DBHelper myDb;

    private IncomeListAdapter mIncomeListAdapter;
    private List<Transaction> transactionList;

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
//        getItemlist getItemlist = new getItemlist();
//        getItemlist.execute();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        ButterKnife.bind(this,view);

        mSwipefreshlayout.setOnRefreshListener(this);
        return view;

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadRecyclerViewData();
        maddtransaction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseCategory_fragment chooseCategory_fragment = new ChooseCategory_fragment();
                chooseCategory_fragment.show(getFragmentManager(),"chooser");
                chooseCategory_fragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                        mIncomeListAdapter.notifyDataSetChanged();
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
    }



    @Override
    public void onRefresh() {
        mIncomeListAdapter.notifyDataSetChanged();
//        getItemlist getItemlist = new getItemlist();
//        getItemlist.execute();
        loadRecyclerViewData();

    }
    public void loadRecyclerViewData(){
        mSwipefreshlayout.setRefreshing(true);


        myDb = new DBHelper(getActivity());

        mBalanceamount.setText(myDb.GetBalance().toString());
        transactionList = myDb.getTransactionData();

        mIncomeListAdapter = new IncomeListAdapter(transactionList,getContext(), new TransactionItemClickListener() {
            @Override
            public void OnTransItemClick(View view, final int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

                alertDialog.setTitle("Delete the transaction?");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper = new DBHelper(getActivity());
                        final Transaction transaction = transactionList.get(position);
                        dbHelper.DeleteTransaction(transaction.getTransaction_id(),getActivity());
                        // mIncomeRecycler.removeViewAt(position);
//                        transactionList.remove(position);
//                        mIncomeRecycler.removeViewAt(position);
                        mIncomeListAdapter.notifyDataSetChanged();
                        loadRecyclerViewData();




                    }
                });
                alertDialog.show();

            }
        });
        mIncomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIncomeRecycler.setAdapter(mIncomeListAdapter);
        mIncomeRecycler.smoothScrollToPosition(0);
        mSwipefreshlayout.setRefreshing(false);



    }

//    class getItemlist extends AsyncTask<Void,Void,Void>{
//        private ProgressDialog dialog;
//
//        public getItemlist() {
//            dialog = new ProgressDialog(getActivity());
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            dialog.setMessage("please wait...");
//            dialog.setIndeterminate(true);
//            dialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//
//            dialog.dismiss();
//        }
//    }
}
