package com.example.lenovo.myfinance.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.CategoryList_Adapter;
import com.example.lenovo.myfinance.Bottomsheet_dialog;
import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Interface.CategoryItemClickListener;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Expense_Fragment extends Fragment {

    private CategoryList_Adapter mCategorylistAdapter;
    private List<Category> expensecategorieslist;
    @BindView(R.id.expenseCategory_recyclerview)
    RecyclerView mIncomeCategory_Recycler;
    DBHelper mydb;
    public Expense_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_expense, container, false);
        ButterKnife.bind(this,view);
        mydb = new DBHelper(getContext());

        expensecategorieslist = mydb.getExpenseCategories();
        mCategorylistAdapter = new CategoryList_Adapter(expensecategorieslist,getContext(), new CategoryItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String category_name,String category_type, String category_image) {
                Bundle bundle = new Bundle();
                bundle.putString("categoryName",category_name);
                bundle.putString("categoryType",category_type);
                bundle.putString("categoryImage",category_image);

                Bottomsheet_dialog bottomsheetDialog = new Bottomsheet_dialog();
                bottomsheetDialog.setArguments(bundle);
                bottomsheetDialog.show(getFragmentManager(),"TAG");
            }
        });
        //  mIncomeCategory_Recycler = getActivity().findViewById(R.id.income_category_Recyclerview);
        mIncomeCategory_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIncomeCategory_Recycler.setAdapter(mCategorylistAdapter);

//        expensecategorieslist.add(new Category(null,"file:///android_asset/education_icon.png","Education","expense",null,null));
//        expensecategorieslist.add(new Category(null,"file:///android_asset/electricity_icon.png","Electricity","expense",null,null));
//        expensecategorieslist.add(new Category(null,"file:///android_asset/entertainment_icon.png","Entertainment","expense",null,null));
//        expensecategorieslist.add(new Category(null,"file:///android_asset/grocery_icon.png","Grocery","expense",null,null));
//        expensecategorieslist.add(new Category(null,"file:///android_asset/health_icon.png","Health","expense",null,null));
//        expensecategorieslist.add(new Category(null,"file:///android_asset/restaurant_icon.png","Restaurant","expense",null,null));
//        expensecategorieslist.add(new Category(null,"file:///android_asset/transportation_icon.png","Transportation","expense",null,null));

        return view;
    }


}
