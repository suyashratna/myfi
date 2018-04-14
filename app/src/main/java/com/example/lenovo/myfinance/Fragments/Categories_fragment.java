package com.example.lenovo.myfinance.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.CategoriesPageAdapter;
import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Dialogs.AddCategory_dialog;
import com.example.lenovo.myfinance.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Categories_fragment extends android.support.v4.app.Fragment {
    ViewPager mViewPager;
    TabLayout mTablayout;
    CategoriesPageAdapter categoriesPageAdapter;
    @BindView(R.id.addCategory_button)
    Button add_category_button;

    public Categories_fragment() {
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
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this,view);

//        rotate = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
//        categories_imageview.setAnimation(rotate);

        mTablayout = view.findViewById(R.id.categories_tablayout);
        mViewPager = view.findViewById(R.id.categories_viewpager);


        mViewPager.setAdapter(new CategoriesPageAdapter(getChildFragmentManager()));
        mTablayout.setSelectedTabIndicatorColor(Color.parseColor("#36b9e4"));
        mTablayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#36b9e4"));
        mTablayout.post(new Runnable() {
            @Override
            public void run() {
                mTablayout.setupWithViewPager(mViewPager);
            }
        });

        add_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openDialog();
            }
        });

         return view;
    }

    public void openDialog(){

        AddCategory_dialog addCategory_dialog = new AddCategory_dialog();
        addCategory_dialog.show(getFragmentManager(),"add category dialog");
    }
//
}
