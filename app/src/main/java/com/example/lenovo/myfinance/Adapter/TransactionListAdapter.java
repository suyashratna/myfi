package com.example.lenovo.myfinance.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Model.Transaction;

import java.util.List;

/**
 * Created by lenovo on 3/6/2018.
 */

public class TransactionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Transaction> mTransactionList;

    public  TransactionListAdapter(List<Transaction> transactions){
        this.mTransactionList = transactions;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mTransactionList.get(position).getTransaction_type()){
            case "income":
                return 1;
            case "expense":
                return 2;
            case "transfer":
                return 3;
            default:
                return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder{
        public IncomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
        public ExpenseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder{
        public TransferViewHolder(View itemView) {
            super(itemView);
        }
    }
}
