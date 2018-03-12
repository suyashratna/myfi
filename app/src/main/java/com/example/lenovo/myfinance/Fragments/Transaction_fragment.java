package com.example.lenovo.myfinance.Fragments;

import android.os.AsyncTask;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.SectionsPageAdapter;
import com.example.lenovo.myfinance.Adapter.IncomeListAdapter;
import com.example.lenovo.myfinance.Bottomsheet_dialog;


import com.example.lenovo.myfinance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Transaction_fragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Animation uptodown;
    Animation downtoup;
    Animation rotate;

    ViewPager mViewPager;
    TabLayout mTablayout;
    SectionsPageAdapter sectionsPageAdapter;

    @BindView(R.id.addtransaction_button)
    Button maddtransaction_button ;

    @BindView(R.id.trans_image)ImageView transactions;

    @BindView(R.id.TopInfo_relative)
    RelativeLayout mtoprelative;

    IncomeListAdapter mIncomeListAdapter;



//    @BindView(R.id.trans_recycler)RecyclerView mTransactionRecycler;

//    @BindView(R.id.bottomsheet_layout)
//    LinearLayout layout;

//    BottomSheetBehavior bab;




    public Transaction_fragment() {
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
         View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        ButterKnife.bind(this,view);





        mTablayout = view.findViewById(R.id.chooser_tab);
        mViewPager = view.findViewById(R.id.chooser_viewpager);


        mViewPager.setAdapter(new SectionsPageAdapter(getChildFragmentManager()));
        mTablayout.post(new Runnable() {
            @Override
            public void run() {
                mTablayout.setupWithViewPager(mViewPager);
            }
        });


//
//      add transaction based on the selected tab
        maddtransaction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mTablayout.getSelectedTabPosition()){
                    case 0:
                        Bottomsheet_dialog bottomSheetDialog = new Bottomsheet_dialog();
                        bottomSheetDialog.show(getFragmentManager(),"TAG");

                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Expense", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Transfer", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }

            }
        });

//
//


//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
//                View parentView = getActivity().getLayoutInflater().inflate(R.layout.bottomsheet_transaction,null);
//                bottomSheetDialog.setContentView(parentView);
//                bottomSheetDialog.show();

//

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                uptodown = AnimationUtils.loadAnimation(getActivity(),R.anim.uptodownquick);
                downtoup= AnimationUtils.loadAnimation(getActivity(),R.anim.downtoupquick);
//                mtoprelative.setAnimation(uptodown);
                mTablayout.setAnimation(downtoup);
            }
        });


        return view;

    }




   }
