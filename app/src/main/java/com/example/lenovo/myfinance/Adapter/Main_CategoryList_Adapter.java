package com.example.lenovo.myfinance.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 4/10/2018.
 */

public class Main_CategoryList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Category> mCategoryList;


    public Main_CategoryList_Adapter(List<Category> categoryList) {
        this.mCategoryList= categoryList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_category_layout,parent,false);
        final CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public int getItemCount() {
      return mCategoryList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CategoryViewHolder)holder).Category_name.setText(mCategoryList.get(position).getCategory_name());
        ((CategoryViewHolder)holder).category_type = (mCategoryList.get(position).getCategory_type());

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView Category_name;
        public String category_type;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            Category_name = itemView.findViewById(R.id.income_category_name);
        }
    }
}
