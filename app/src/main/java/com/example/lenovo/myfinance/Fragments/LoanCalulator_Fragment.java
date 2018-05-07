package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.myfinance.R;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanCalulator_Fragment extends Fragment {

    @BindView(R.id.loan_amount_edittext)
    EditText mloanamount;
    @BindView(R.id.loan_interest_edittext) EditText mLoaninterest;
    @BindView(R.id.loan_months_edittext) EditText mLoanMonthEdittext;
    @BindView(R.id.calulate_button)
    Button mCalulatebutton;
    @BindView(R.id.totalpayment_textview)
    TextView mTotalPayment;
    @BindView(R.id.monthly_payment_textview)
    TextView mMonthlyPayment;
    @BindView(R.id.clearall_button)
    Button mClear_button;

    public LoanCalulator_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_calulator, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCalulatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double loanAmount = Integer.parseInt(mloanamount.getText().toString());
                double interestRate = Integer.parseInt(mLoaninterest.getText().toString());
                double loanperiod = Integer.parseInt(mLoanMonthEdittext.getText().toString());
                double r = interestRate/1200;
                double r1 = Math.pow(r+1,loanperiod);

                double monthlyPayment = (double)((r+(r/(r1-1))) * loanAmount);
                double totalPayment = monthlyPayment * loanperiod;

                mMonthlyPayment.setText(new DecimalFormat("##.##").format(monthlyPayment));
                mTotalPayment.setText(new DecimalFormat("##.##").format(totalPayment));
            }
        });

        mClear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mloanamount.setText("");
                mLoaninterest.setText("");
                mLoanMonthEdittext.setText("");
                mTotalPayment.setText("");
                mMonthlyPayment.setText("");
            }
        });

    }
}
