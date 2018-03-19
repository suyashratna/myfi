package com.example.lenovo.myfinance.Adapter;

import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;

import com.example.lenovo.myfinance.Bottomsheet_dialog;
import com.example.lenovo.myfinance.Interface.CategoryItemClickListener;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import java.util.List;

/**
 * Created by lenovo on 3/18/2018.
 */

public class CategoryList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Category> mCategoryList;
    CategoryItemClickListener listener;

    public CategoryList_Adapter(List<Category> categoryList, CategoryItemClickListener listener){
        this.mCategoryList = categoryList;
        this.listener = listener;

    }

    @Override
    public int getItemViewType(int position) {
        switch (mCategoryList.get(position).getCategory_type()){
            case "Income":
                return 1;
            case "Expense":
                return 2;
            case "Transfer":
                return 3;
            default:
                return 1;

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//       if(viewType == 1){
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_category_layout,parent,false);
           final incomeCategoryViewHolder mIncomeviewholder = new incomeCategoryViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(view, mIncomeviewholder.getPosition() );
                }
            });

           return mIncomeviewholder;
       }
//       else if(viewType == 2) {
//           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_category_layout, parent, false);
//           return new ExpenseCategoryViewHolder(view);
//
//       }
//       else {
//           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_category_layout,parent,false);
//           return new TransferCategoryViewHolder(view);
//       }
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof incomeCategoryViewHolder){
        ((incomeCategoryViewHolder)holder).Category_name.setText(mCategoryList.get(position).getCategory_name());}
//        else if (holder instanceof ExpenseCategoryViewHolder){
//            ((ExpenseCategoryViewHolder)holder).expense_category_name.setText(mCategoryList.get(position).getCategory_name());
//        }


//    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class incomeCategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView Category_name;
        public incomeCategoryViewHolder(View itemView) {
            super(itemView);
            Category_name = itemView.findViewById(R.id.income_category_name);

        }
    }

    public class ExpenseCategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView expense_category_name;
        public ExpenseCategoryViewHolder(View itemView) {
            super(itemView);
            expense_category_name = itemView.findViewById(R.id.income_category_name);
        }
    }

    public class TransferCategoryViewHolder extends RecyclerView.ViewHolder{
        public TransferCategoryViewHolder(View itemView) {
            super(itemView);
        }
    }

}
