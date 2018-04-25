package com.example.lenovo.myfinance.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myfinance.Model.Account;
import com.example.lenovo.myfinance.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 4/25/2018.
 */

public class AccountList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Account> mAccountList;
    private Context mContext;

    public AccountList_Adapter(List<Account> mAccountList, Context mContext) {
        this.mAccountList = mAccountList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_account_detail,parent,false);
        final  AccountViewHolder accountViewHolder = new AccountViewHolder(view);
        return accountViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Account account = mAccountList.get(position);
        ((AccountViewHolder)holder).account_name.setText(account.getAccount_name());
        ((AccountViewHolder)holder).account_income.setText(account.getAccount_income());
        ((AccountViewHolder)holder).account_expense.setText(account.getAccount_expense());
        ((AccountViewHolder)holder).account_balance.setText(account.getAccount_balance());
        Picasso.with(mContext).load(account.getAccount_image()).placeholder(R.mipmap.ic_launcher_round).into(((AccountViewHolder)holder).account_image);

    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{

        public TextView account_name;
        public ImageView account_image;
        public TextView account_income;
        public TextView account_expense;
        public TextView account_balance;

        public AccountViewHolder(View itemView) {
            super(itemView);
            account_name = itemView.findViewById(R.id.accountname_textview);
            account_image = itemView.findViewById(R.id.accounticon_imageview);
            account_income = itemView.findViewById(R.id.account_income_amount);
            account_expense = itemView.findViewById(R.id.account_expense_amount);
            account_balance = itemView.findViewById(R.id.account_balance_amount);

        }
    }
}
