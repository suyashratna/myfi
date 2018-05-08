package com.example.lenovo.myfinance.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myfinance.Interface.TransactionItemClickListener;
import com.example.lenovo.myfinance.Model.Transaction;

import com.example.lenovo.myfinance.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2/14/2018.
 */

public class IncomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  List<Transaction>   mTransactionlist;
    TransactionItemClickListener transactionItemClickListener;
    private Context mContext;
    public IncomeListAdapter(List<Transaction> incomelist,Context context, TransactionItemClickListener listener) {
       this.mTransactionlist = incomelist;
       this.transactionItemClickListener =listener;
       this.mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_transaction_detail,parent,false);
         final IncomeTrans incomeTrans = new IncomeTrans(view);
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 transactionItemClickListener.OnTransItemClick(view,incomeTrans.getPosition());


             }
         });
         return incomeTrans;}
         else if (viewType == 2){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_transaction_detail,parent,false);
            final IncomeTrans incomeTrans = new IncomeTrans(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    transactionItemClickListener.OnTransItemClick(view,incomeTrans.getPosition());
        }

    });return incomeTrans;}
    else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_transaction_detail,parent,false);
            final IncomeTrans incomeTrans = new IncomeTrans(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    transactionItemClickListener.OnTransItemClick(view,incomeTrans.getPosition());
                }

            });return incomeTrans;

        }}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         final Transaction transaction = mTransactionlist.get(position);
        ((IncomeTrans)holder).transaction_amount.setText(mTransactionlist.get(position).getTransaction_amount());
        ((IncomeTrans)holder).transaction_date.setText(String.valueOf(mTransactionlist.get(position).getTransaction_date()));
        ((IncomeTrans)holder).transaction_category.setText(mTransactionlist.get(position).getTransaction_category());
        ((IncomeTrans)holder).transaction_account.setText(mTransactionlist.get(position).getTransaction_account());

        Picasso.with(mContext).load(transaction.getTransaction_category_image()).placeholder(R.mipmap.ic_launcher_round).into(((IncomeTrans)holder).transaction_category_image);
    }

    @Override
    public int getItemViewType(int position) {
        switch (mTransactionlist.get(position).getTransaction_type()){
            case "income":
                return 1;
            case "expense":
                return 2;
            case "transfer":
                return 3;
            default:
                return 1;
        }}

    @Override
    public int getItemCount() {
        return mTransactionlist == null ? 0 : mTransactionlist.size() ;
    }


    public class IncomeTrans extends RecyclerView.ViewHolder{
        //public ImageView category_image;
        public TextView transaction_amount;
        public TextView transaction_date;
        public ImageView transaction_category_image;
        public TextView transaction_category;
        public TextView transaction_account;

        public IncomeTrans(View itemView) {
            super(itemView);
          //  category_image = itemView.findViewById(R.id.category_image);
            transaction_amount = itemView.findViewById(R.id.transfer_total);
            transaction_date= itemView.findViewById(R.id.date_textview);
            transaction_category_image = itemView.findViewById(R.id.income_category_image);
            transaction_category = itemView.findViewById(R.id.category_name_textview);
            transaction_account = itemView.findViewById(R.id.transaction_account_name);

//            itemView.setOnClickListener();
        }
    }


}
