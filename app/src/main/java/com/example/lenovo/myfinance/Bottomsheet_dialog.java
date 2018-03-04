package com.example.lenovo.myfinance;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Adapter.TransactionListAdapter;
import com.example.lenovo.myfinance.Fragments.Expense_Fragment;
import com.example.lenovo.myfinance.Fragments.Income_Fragment;
import com.example.lenovo.myfinance.Fragments.Transaction_fragment;
import com.example.lenovo.myfinance.Fragments.Transfer_fragment;
import com.example.lenovo.myfinance.Model.Transaction;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lenovo on 2/15/2018.
 */

public class Bottomsheet_dialog extends BottomSheetDialogFragment {



    Bundle mbundle;



    public Bottomsheet_dialog() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}
////         mbundle = new Bundle();
////       // mTransactionRecycler =  getActivity().findViewById(R.id.trans_recycler);
////        mTransactionAmount = getActivity().findViewById(R.id.transaction_amount_editText);
////        mInsertButton = getActivity().findViewById(R.id.insertamount_button);
////
////
////
////        mInsertButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                //bottomtransactionList.add(new Transaction("Health","Cash",)));
////                //mTransactionListAdapter.notifyDataSetChanged();
////
////                mbundle.putString("category","Health");
////                mbundle.putString("account","cash");
////                mbundle.putInt("amount",Integer.parseInt( mTransactionAmount.getText().toString()));
////
////
////            }
////        });
//    }

//    @Override
//    public void onDismiss(DialogInterface dialog) {
//        super.onDismiss(dialog);
//        Transaction_fragment mfragment = new Transaction_fragment();
//        mfragment.setArguments(mbundle);
//
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.bottomsheet_transaction, container);
//          return root;
//    }

       @Override
    public void setupDialog(Dialog dialog, int style) {
           super.setupDialog(dialog, style);

           View contentView = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_transaction, null);
           ButterKnife.bind(this, contentView);

           FragmentManager fragmentManager = getFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           Calculator_Fragment fragment = new Calculator_Fragment();
           fragmentTransaction.add(R.id.calculator_holder, fragment);
           fragmentTransaction.commit();

       }



}