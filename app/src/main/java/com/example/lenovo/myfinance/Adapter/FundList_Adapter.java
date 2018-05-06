package com.example.lenovo.myfinance.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myfinance.Interface.FundItemClickListener;
import com.example.lenovo.myfinance.Model.Fund;
import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 5/5/2018.
 */

public class FundList_Adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Fund> mFundList;
    private Context mContext;
    FundItemClickListener fundItemClickListener;

    public FundList_Adapter(List<Fund> mFundList, Context mContext, FundItemClickListener listener) {
        this.mFundList = mFundList;
        this.mContext = mContext;
        this.fundItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_fund_detail,parent,false);
        final FundViewHolder fundViewHolder = new FundViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fundItemClickListener.onFundItemClick(view,fundViewHolder.getAdapterPosition());
            }
        });
        return fundViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Fund fund = mFundList.get(position);
        ((FundViewHolder)holder).fund_name.setText(fund.getFund_name());
        ((FundViewHolder)holder).fund_amount.setText(fund.getFund_amount());
        ((FundViewHolder)holder).fund_date.setText(fund.getFund_added_date());
    }

    @Override
    public int getItemCount() {
        return mFundList == null ? 0 : mFundList.size();
    }

    public class FundViewHolder extends RecyclerView.ViewHolder{

        public TextView fund_name;
        public TextView fund_date;
        TextView fund_amount;

        public FundViewHolder(View itemView) {
            super(itemView);
            fund_name = itemView.findViewById(R.id.fund_name_textview);
            fund_date = itemView.findViewById(R.id.fund_date_textview);
            fund_amount = itemView.findViewById(R.id.fund_amount);
        }
    }
}
