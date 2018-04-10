package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myfinance.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main_fragment extends Fragment {
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_transactions:
                    transaction.replace(R.id.fragment_frame,new Transaction_fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_categories:
                    transaction.replace(R.id.fragment_frame,new Categories_fragment());
                    transaction.commit();
                    return true;
//                case R.id.navigation_notifications:
//
//                    return true;
                case R.id.navigation_reports:
                    transaction.replace(R.id.fragment_frame, new Reports_fragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    public Main_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_main,container,false);

        BottomNavigationView navigation =  view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_transactions);
        return view;
    }

}
