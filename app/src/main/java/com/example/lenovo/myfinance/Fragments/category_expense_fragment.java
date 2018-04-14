package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.Main_CategoryList_Adapter;
import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class category_expense_fragment extends Fragment {
    Main_CategoryList_Adapter main_categoryList_adapter;
    List<Category> expensecategoryList;
    @BindView(R.id.maincategory_expense_recyclerview)
    RecyclerView mExpenseCategory_Recycler;

    @BindView(R.id.category_expense_swiperefresh)
    SwipeRefreshLayout swipeRefreshlayout;

    DBHelper mydb;


    public category_expense_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_expense, container, false);
        ButterKnife.bind(this,view);

        swipeRefreshlayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshlayout.setRefreshing(true);

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // loadexpenseRecyclerview();
    }

//    public void loadexpenseRecyclerview(){
//        swipeRefreshlayout.setRefreshing(true);
//        mydb = new DBHelper(getActivity());
//        expensecategoryList = mydb.getExpenseCategories();
//
//        main_categoryList_adapter = new Main_CategoryList_Adapter(expensecategoryList);
//        mExpenseCategory_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mExpenseCategory_Recycler.setAdapter(main_categoryList_adapter);
//        mExpenseCategory_Recycler.smoothScrollToPosition(0);
//
//        swipeRefreshlayout.setRefreshing(false);
//    }
}
