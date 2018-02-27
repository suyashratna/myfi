package com.example.lenovo.myfinance.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myfinance.Model.Transaction;
import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 2/14/2018.
 */

public class TransactionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Transaction> mTransactionlist;

    public TransactionListAdapter(List<Transaction> transactions) {
        this.mTransactionlist = transactions;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_transaction_detail,parent,false);
       return  new IndividualTrans(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IndividualTrans)holder).category_name.setText(mTransactionlist.get(position).getCategory());
        ((IndividualTrans)holder).account_name.setText(mTransactionlist.get(position).getTrans_account());
        ((IndividualTrans)holder).transaction_amount.setText(mTransactionlist.get(position).getTrans_amount());

    }

    @Override
    public int getItemCount() {
        return mTransactionlist == null ? 0 : mTransactionlist.size() ;
    }


    public class IndividualTrans extends RecyclerView.ViewHolder{
        //public ImageView category_image;
        public TextView category_name;
        public TextView account_name;
        public TextView transaction_amount;

        public IndividualTrans(View itemView) {
            super(itemView);
          //  category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
            account_name = itemView.findViewById(R.id.account_name);
            transaction_amount = itemView.findViewById(R.id.transaction_amount);
        }
    }
}
