package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lenovo.myfinance.Adapter.AccountList_Adapter;
import com.example.lenovo.myfinance.Dialogs.AddAccount_dialog;
import com.example.lenovo.myfinance.Model.Account;
import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Accounts_fragment extends Fragment {
    private AccountList_Adapter accountList_adapter;
    private List<Account> accountList;
    @BindView(R.id.account_recyclerview)
    RecyclerView mAcountRecyclerview;
    @BindView(R.id.add_account_button)
    Button mAddacount_Button;
    public Accounts_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadAccountData();
        mAddacount_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }
    public void LoadAccountData(){
        accountList = new ArrayList<Account>();
        accountList_adapter = new AccountList_Adapter(accountList,getContext());
        mAcountRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAcountRecyclerview.setAdapter(accountList_adapter);
        mAcountRecyclerview.smoothScrollToPosition(0);
        accountList.add(new Account(null,null,"Cash","0.0","0.0","0.0"));


    }

    public void openAddaccountDialog(){
        AddAccount_dialog addAccount_dialog = new AddAccount_dialog();
        addAccount_dialog.show(getFragmentManager(),"add account dialog");

    }
}
