package com.example.lenovo.myfinance.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Bottomsheet_dialog;
import com.example.lenovo.myfinance.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 3/13/2018.
 */


public class ChooseCategory_fragment extends BottomSheetDialogFragment{
    private DialogInterface.OnDismissListener onDismissListener;
    ViewPager mViewPager;
    TabLayout mTablayout;
    SectionsPageAdapter sectionsPageAdapter;

    public ChooseCategory_fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choosecategory,null);
        ButterKnife.bind(this, view);

        mTablayout = view.findViewById(R.id.chooser_tab);
        mViewPager = view.findViewById(R.id.chooser_viewpager);


        mViewPager.setAdapter(new SectionsPageAdapter(getChildFragmentManager()));
        mTablayout.setSelectedTabIndicatorColor(Color.parseColor("#36b9e4"));
        mTablayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#36b9e4"));
        mTablayout.post(new Runnable() {
            @Override
            public void run() {
                mTablayout.setupWithViewPager(mViewPager);
            }
        });
        return view;

    }
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener){
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(onDismissListener!= null){
            onDismissListener.onDismiss(dialog);
        }
    }
    //    @Override
//    public void setupDialog(Dialog dialog, int style) {
//        super.setupDialog(dialog, style);
//        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_choosecategory,null);
//
//        dialog.setContentView(contentView);
//
//
//    }
}
