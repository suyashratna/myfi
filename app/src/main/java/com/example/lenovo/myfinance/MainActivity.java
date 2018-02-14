package com.example.lenovo.myfinance;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ReportFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.myfinance.Fragments.Categories_fragment;
import com.example.lenovo.myfinance.Fragments.Reports_fragment;
import com.example.lenovo.myfinance.Fragments.Transaction_fragment;

public class MainActivity extends AppCompatActivity {




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_transactions:
                    transaction.replace(R.id.fragment_frame,new Transaction_fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_categories:
                    transaction.replace(R.id.fragment_frame,new Categories_fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:

                    return true;
                case R.id.navigation_reports:
                    transaction.replace(R.id.fragment_frame, new Reports_fragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_transactions);



    }
}
