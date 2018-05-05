package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fund_Chart_Fragment extends Fragment {


    public Fund_Chart_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fund__chart, container, false);
    }

}
