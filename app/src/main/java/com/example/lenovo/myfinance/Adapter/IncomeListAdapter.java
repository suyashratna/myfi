package com.example.lenovo.myfinance.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myfinance.Interface.TransactionItemClickListener;
import com.example.lenovo.myfinance.Model.Transaction;

import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 2/14/2018.
 */

public class IncomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  List<Transaction>   mTransactionlist;
    TransactionItemClickListener transactionItemClickListener;

    public IncomeListAdapter(List<Transaction> incomelist, TransactionItemClickListener listener) {
       this.mTransactionlist = incomelist;
       this.transactionItemClickListener =listener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_transaction_detail,parent,false);
         final IncomeTrans incomeTrans = new IncomeTrans(view);
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 transactionItemClickListener.OnTransItemClick(view,incomeTrans.getPosition());
             }
         });
         return incomeTrans;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((IncomeTrans)holder).transaction_amount.setText(mTransactionlist.get(position).getTransaction_amount());
        ((IncomeTrans)holder).transaction_date.setText(String.valueOf(mTransactionlist.get(position).getTransaction_date()));
        ((IncomeTrans)holder).transaction_category.setText(mTransactionlist.get(position).getTransaction_category());

    }


    @Override
    public int getItemCount() {
        return mTransactionlist == null ? 0 : mTransactionlist.size() ;
    }


    public class IncomeTrans extends RecyclerView.ViewHolder{
        //public ImageView category_image;
        public TextView transaction_amount;
        public TextView transaction_date;

        public TextView transaction_category;

        public IncomeTrans(View itemView) {
            super(itemView);
          //  category_image = itemView.findViewById(R.id.category_image);
            transaction_amount = itemView.findViewById(R.id.transfer_total);
            transaction_date= itemView.findViewById(R.id.date_textview);

            transaction_category = itemView.findViewById(R.id.category_name_textview);

//            itemView.setOnClickListener();
        }
    }
}
