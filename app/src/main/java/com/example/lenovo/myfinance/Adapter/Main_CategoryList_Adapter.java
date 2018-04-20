package com.example.lenovo.myfinance.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Interface.DeleteCategoryClickListener;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 4/10/2018.
 */

public class Main_CategoryList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Category> mCategoryList;
    private Context mContext;
    DeleteCategoryClickListener deleteCategoryClickListener;

    public Main_CategoryList_Adapter(List<Category> categoryList,Context context,DeleteCategoryClickListener listener) {
        this.mCategoryList= categoryList;
        this.mContext =context;
        this.deleteCategoryClickListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_category_detail,parent,false);
        final CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCategoryClickListener.OnItemClick(view,categoryViewHolder.getPosition());

            }
        });
        return categoryViewHolder;
    }

    @Override
    public int getItemCount() {
      return mCategoryList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);

        Picasso.with(mContext).load(category.getCategory_image()).placeholder(R.mipmap.ic_launcher_round).into(((CategoryViewHolder)holder).category_icon);
        ((CategoryViewHolder)holder).category_name.setText(mCategoryList.get(position).getCategory_name());
        ((CategoryViewHolder)holder).category_type = (mCategoryList.get(position).getCategory_type());

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        public ImageView category_icon;
        public TextView category_name;
        public String category_type;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            category_icon = itemView.findViewById(R.id.main_category_image);
            category_name = itemView.findViewById(R.id.category_name_textview);

        }
    }
}
