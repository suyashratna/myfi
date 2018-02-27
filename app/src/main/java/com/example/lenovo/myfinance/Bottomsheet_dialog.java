package com.example.lenovo.myfinance;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Adapter.TransactionListAdapter;
import com.example.lenovo.myfinance.Fragments.Expense_Fragment;
import com.example.lenovo.myfinance.Fragments.Income_Fragment;
import com.example.lenovo.myfinance.Fragments.Transaction_fragment;
import com.example.lenovo.myfinance.Fragments.Transfer_fragment;
import com.example.lenovo.myfinance.Model.Transaction;

import java.util.List;


/**
 * Created by lenovo on 2/15/2018.
 */

public class Bottomsheet_dialog extends BottomSheetDialogFragment {
    ViewPager mViewPager;
    TabLayout mTablayout;
    EditText mTransactionAmount;
    Button mInsertButton;
    TransactionListAdapter mTransactionListAdapter;
    List<Transaction> bottomtransactionList;
    RecyclerView mTransactionRecycler;
    Bundle mbundle;

    public Bottomsheet_dialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mbundle = new Bundle();
       // mTransactionRecycler =  getActivity().findViewById(R.id.trans_recycler);
        mTransactionAmount = getActivity().findViewById(R.id.transaction_amount_editText);
        mInsertButton = getActivity().findViewById(R.id.insertamount_button);



        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomtransactionList.add(new Transaction("Health","Cash",)));
                //mTransactionListAdapter.notifyDataSetChanged();

                mbundle.putString("category","Health");
                mbundle.putString("account","cash");
                mbundle.putInt("amount",Integer.parseInt( mTransactionAmount.getText().toString()));


            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Transaction_fragment mfragment = new Transaction_fragment();
        mfragment.setArguments(mbundle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottomsheet_transaction, container, false);




    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        mViewPager = getActivity().findViewById(R.id.chooser_viewpager);
        setupViewPager(mViewPager);


        mTablayout = getActivity().findViewById(R.id.chooser_tab);
        mTablayout.setupWithViewPager(mViewPager);
       // mlayout = view.findViewById(R.id.Chooser_trans);

//        FragmentTransaction transaction = null;
//        FragmentManager fragmentManager;
//        fragmentManager = getFragmentManager();
//
//        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.Chooser_trans, new checkFragment()); //id of ViewPager
//        transaction.commit();


    }


    private void setupViewPager(ViewPager mViewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new Income_Fragment(),"INCOME");
        adapter.addFragment(new Expense_Fragment(),"EXPENSE");
        adapter.addFragment(new Transfer_fragment(),"TRANSFER");
        mViewPager.setAdapter(adapter);
    }
}