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
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.Main_CategoryList_Adapter;
import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Interface.DeleteCategoryClickListener;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class category_expense_fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_expense, container, false);
        ButterKnife.bind(this,view);
        swipeRefreshlayout.setOnRefreshListener(this);

        swipeRefreshlayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshlayout.setRefreshing(true);
                loadexpenseRecyclerview();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadexpenseRecyclerview();
    }

    public void loadexpenseRecyclerview(){
        swipeRefreshlayout.setRefreshing(true);
        mydb = new DBHelper(getActivity());
        expensecategoryList = mydb.getExpenseCategories();

        main_categoryList_adapter = new Main_CategoryList_Adapter(expensecategoryList, getContext(), new DeleteCategoryClickListener() {
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
                        final Category category = expensecategoryList.get(position);
                        if(category.getCategory_id() != null){
                            dbHelper.DeleteCategory(category.getCategory_id(),getActivity());
                            expensecategoryList.remove(position);
                            mExpenseCategory_Recycler.removeViewAt(position);
                            main_categoryList_adapter.notifyItemRemoved(position);
                        }
                        else {
                            Toast.makeText(getActivity(), " Cannot delete Default category ", Toast.LENGTH_LONG).show();
                        }


                    }
                });
                alertDialog.show();
            }
        });

//        expensecategoryList.add(new Category(null,"file:///android_asset/education_icon.png","Education","expense",null,null));
//        expensecategoryList.add(new Category(null,"file:///android_asset/electricity_icon.png","Electricity","expense",null,null));
//        expensecategoryList.add(new Category(null,"file:///android_asset/entertainment_icon.png","Entertainment","expense",null,null));
//        expensecategoryList.add(new Category(null,"file:///android_asset/grocery_icon.png","Grocery","expense",null,null));
//        expensecategoryList.add(new Category(null,"file:///android_asset/health_icon.png","Health","expense",null,null));
//        expensecategoryList.add(new Category(null,"file:///android_asset/restaurant_icon.png","Restaurant","expense",null,null));
//        expensecategoryList.add(new Category(null,"file:///android_asset/transportation_icon.png","Transportation","expense",null,null));

        mExpenseCategory_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mExpenseCategory_Recycler.setAdapter(main_categoryList_adapter);
        mExpenseCategory_Recycler.smoothScrollToPosition(0);



        swipeRefreshlayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadexpenseRecyclerview();
    }
}
