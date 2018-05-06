package com.example.lenovo.myfinance.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myfinance.Model.Tip;
import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 5/6/2018.
 */

public class TipsList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Tip> mTiplist;

    public TipsList_Adapter(List<Tip> tipList){
        this.mTiplist = tipList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_tip_detail,parent,false);
        final TipViewholder tipViewholder = new TipViewholder(view);
        return tipViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final  Tip tip = mTiplist.get(position);
        ((TipViewholder)holder).tip_title.setText(tip.getTip_title());
        ((TipViewholder)holder).tip_description.setText(tip.getTip_description());
    }

    @Override
    public int getItemCount() {
        return mTiplist.size();
    }
    public class TipViewholder extends RecyclerView.ViewHolder{

        public TextView tip_title;
        public TextView tip_description;
        public TipViewholder(View itemView){
            super(itemView);
            tip_title = itemView.findViewById(R.id.tip_name_textview);
            tip_description = itemView.findViewById(R.id.tip_description_textview);
        }
    }
}
