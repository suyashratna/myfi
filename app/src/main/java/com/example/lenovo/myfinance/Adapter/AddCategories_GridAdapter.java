package com.example.lenovo.myfinance.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 4/18/2018.
 */

public class AddCategories_GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] categoryicons;


    public AddCategories_GridAdapter(String[] categoryicons) {
        this.categoryicons = categoryicons;

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
        return categoryicons.length;
    }

    public class CategoryIconViewholder extends RecyclerView.ViewHolder{
        ImageView img;

        public CategoryIconViewholder(View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.grid_item_image);
        }
    }
}
