package com.example.lenovo.myfinance.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myfinance.Model.Income;

import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 2/14/2018.
 */

public class IncomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  List<Income>   mIncomelist;

    public IncomeListAdapter(List<Income> incomelist) {
       this.mIncomelist = incomelist;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_transaction_detail,parent,false);
       return  new IncomeTrans(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((IncomeTrans)holder).income_amount.setText(mIncomelist.get(position).getIncome_amount());
        ((IncomeTrans)holder).transaction_day.setText(mIncomelist.get(position).getTransaction_Day());
        ((IncomeTrans)holder).transaction_dayNo.setText(String.valueOf(mIncomelist.get(position).getTransaction_DayNo()));
        ((IncomeTrans)holder).transaction_month.setText(mIncomelist.get(position).getTransaction_Month());
        ((IncomeTrans)holder).transaction_year.setText(String.valueOf(mIncomelist.get(position).getYear()));

    }


    @Override
    public int getItemCount() {
        return mIncomelist == null ? 0 : mIncomelist.size() ;
    }


    public class IncomeTrans extends RecyclerView.ViewHolder{
        //public ImageView category_image;
        public TextView income_amount;
        public TextView transaction_month;
        public TextView transaction_day;
        public TextView transaction_dayNo;
        public TextView transaction_year;

        public IncomeTrans(View itemView) {
            super(itemView);
          //  category_image = itemView.findViewById(R.id.category_image);
            income_amount = itemView.findViewById(R.id.transfer_total);
            transaction_dayNo = itemView.findViewById(R.id.day_no);
            transaction_month = itemView.findViewById(R.id.month_text);
            transaction_day = itemView.findViewById(R.id.day_text);
            transaction_year = itemView.findViewById(R.id.year_no);
//            itemView.setOnClickListener();
        }
    }
}
