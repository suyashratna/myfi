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

        expensecategorieslist = new ArrayList<Category>();
        mCategorylistAdapter = new CategoryList_Adapter(expensecategorieslist, new CategoryItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String category_name,String category_type) {
                Bundle bundle = new Bundle();
                bundle.putString("categoryName",category_name);
                bundle.putString("categoryType",category_type);

                Bottomsheet_dialog bottomsheetDialog = new Bottomsheet_dialog();
                bottomsheetDialog.setArguments(bundle);
                bottomsheetDialog.show(getFragmentManager(),"TAG");
            }
        });
        //  mIncomeCategory_Recycler = getActivity().findViewById(R.id.income_category_Recyclerview);
        mIncomeCategory_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIncomeCategory_Recycler.setAdapter(mCategorylistAdapter);

        expensecategorieslist.add(new Category("Education","Expense"));
        expensecategorieslist.add(new Category("Health","Expense"));
        expensecategorieslist.add(new Category("Transportation","Expense"));
        expensecategorieslist.add(new Category("Electricity","Expense"));


        return view;
    }


}
