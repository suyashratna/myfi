package com.example.lenovo.myfinance.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lenovo.myfinance.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mortgage_Calculator_Fragment extends Fragment {

    @BindView(R.id.Homevalue_edittext)
    EditText homeValue;
    @BindView(R.id.downpayment_edittext) EditText downPayment;
    @BindView(R.id.interestrate_edittext) EditText interestRate;
    @BindView(R.id.propertytax_edittextr) EditText propertyTax;
    @BindView(R.id.terms_spinner)
    Spinner spinner;
    @BindView(R.id.calulate_button)
    Button calculateButton;
    @BindView(R.id.clearall_button) Button clearall;
    @BindView(R.id.totaltax_textview)
    TextView TotalTax;
    @BindView(R.id.totalinterest_textview) TextView TotalInterest;
    @BindView(R.id.monthlypayment_textview) TextView MonthlyPayment;
    @BindView(R.id.payoffdate_textview) TextView PayoffDate;

    public Mortgage_Calculator_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mortgage__calculator, container, false);
        ButterKnife.bind(this,view);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String term = String.valueOf(spinner.getSelectedItem());
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateMortgage();
            }
        });

        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearallfields();
            }
        });
    }

    public void calculateMortgage(){

        double doubleHomeValue=0;
        double doubleDownPayment=0;
        double doubleInterestRate=0;
        double doublePropertyTaxRate=0;
        double terms=0;

        doubleHomeValue = Double.parseDouble(homeValue.getText().toString());
        doubleDownPayment = Double.parseDouble(downPayment.getText().toString());
        doubleInterestRate = Double.parseDouble(interestRate.getText().toString());
        doublePropertyTaxRate = Double.parseDouble(propertyTax.getText().toString());
        terms = Double.parseDouble(spinner.getSelectedItem().toString());

        // finding monthly mortgage Payment
        double monthlyPayment = 0.0;
        double P = doubleHomeValue - doubleDownPayment;
        double i = doubleInterestRate / (1200); // 12 for months & 100 for percentage.
        double n = terms * 12;

        double Numerator = i * Math.pow((1 + i), n);
        double Denominator = Math.pow((1 + i), n) - 1;

        DecimalFormat df = new DecimalFormat("##.##");

        monthlyPayment = P * Numerator / Denominator;
        // end of finding monthly mortgage Payment

        // finding Total Interest Paid
        double totalInterestPaid = monthlyPayment * n - P;
        //


        // finding property tax
        double propertyTax = doublePropertyTaxRate / 1200 * doubleHomeValue;            //monthly
        double totalPropertyTax = propertyTax * n;        //In all property Tax
        // end of finding property tax

        // find payoffDate
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        String month = sdf.format(cal.getTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");

        cal.add(Calendar.YEAR,(int)terms);
        String year = sdf1.format(cal.getTime());

        String date = month + " " + year;
        // end of payOffDate calculation

        monthlyPayment += propertyTax;
        propertyTax*=n;

        TotalTax.setText(String.valueOf(totalPropertyTax));
        TotalInterest.setText(String.valueOf(totalInterestPaid));
        MonthlyPayment.setText(String.valueOf(monthlyPayment));
        PayoffDate.setText(String.valueOf(date));




    }
    public void  clearallfields(){
        homeValue.setText("");
        downPayment.setText("");
        interestRate.setText("");
        propertyTax.setText("");
        TotalTax.setText("");
        TotalInterest.setText("");
        MonthlyPayment.setText("");
        PayoffDate.setText("");
    }
}
