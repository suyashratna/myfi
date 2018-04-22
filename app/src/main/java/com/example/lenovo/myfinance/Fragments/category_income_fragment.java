package com.example.lenovo.myfinance.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.lenovo.myfinance.Interface.DeleteCategoryClickListener;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class category_income_fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Main_CategoryList_Adapter main_categoryList_adapter;
    List<Category> categoryList;
    @BindView(R.id.maincategory_income_recyclerview)
    RecyclerView mIncomeCategory_Recycler;

    @BindView(R.id.category_income_swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    DBHelper mydb;

    public category_income_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_income,container,false);
        ButterKnife.bind(this,view);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadincomerecyclerview();
            }
        });

       return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadincomerecyclerview();

    }

    @Override
    public void onRefresh() {
        loadincomerecyclerview();
    }

    public void loadincomerecyclerview(){

        swipeRefreshLayout.setRefreshing(true);
        mydb = new DBHelper(getActivity());
        categoryList = mydb.getIncomeCategories();

        main_categoryList_adapter = new Main_CategoryList_Adapter(categoryList, getContext(), new DeleteCategoryClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Delete Category?");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper = new DBHelper(getActivity());
                        final Category category = categoryList.get(position);

                        dbHelper.DeleteCategory(category.getCategory_id(),getActivity());
                        categoryList.remove(position);
                        mIncomeCategory_Recycler.removeViewAt(position);
                        main_categoryList_adapter.notifyItemRemoved(position);

                    }
                });
                alertDialog.show();
            }
        });

        categoryList.add(new Category(null,"file:///android_asset/business_icon.png","Business","income",null,null));
        categoryList.add(new Category(null,"file:///android_asset/salary_icon.png","Salary","income",null,null));

        mIncomeCategory_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIncomeCategory_Recycler.setAdapter(main_categoryList_adapter);
        mIncomeCategory_Recycler.smoothScrollToPosition(0);


        swipeRefreshLayout.setRefreshing(false);
    }
}


