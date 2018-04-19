package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


public class Income_Fragment extends Fragment {

    private CategoryList_Adapter mCategorylistAdapter;
    private List<Category> categorieslist;

    @BindView(R.id.income_category_Recyclerview) RecyclerView mIncomeCategory_Recycler;
//    @BindView(R.id.category_income)
    TextView income_category_textview;
    DBHelper mydb;
    public Income_Fragment() {
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
       View view = inflater.inflate(R.layout.fragment_income, container, false);
       ButterKnife.bind(this,view);

        mydb = new DBHelper(getActivity());
       //categorieslist = new ArrayList<Category>();
       categorieslist = mydb.getIncomeCategories();
       mCategorylistAdapter = new CategoryList_Adapter(categorieslist,getContext() ,new CategoryItemClickListener() {
           @Override
           public void OnItemClick(View view, int position, String category_name,String category_type,String category_image) {

           Bundle bundle = new Bundle();
           bundle.putString("categoryName",category_name);
           bundle.putString("categoryType",category_type);
           bundle.putString("categoryImage",category_image);

           Bottomsheet_dialog bottomsheetDialog = new Bottomsheet_dialog();
           bottomsheetDialog.setArguments(bundle);

           bottomsheetDialog.show(getFragmentManager(),"TAG");


           }
       });

         return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIncomeCategory_Recycler.setAdapter(mCategorylistAdapter);
        mIncomeCategory_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        categorieslist.add(new Category(null,"file:///android_asset/business_icon.png","Business","income",null,null));
        // categorieslist.add(new Category("Business","income",""));

    }
}
