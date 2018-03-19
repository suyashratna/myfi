package com.example.lenovo.myfinance;


import android.app.Dialog;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.myfinance.Fragments.ChooseCategory_fragment;
import com.example.lenovo.myfinance.Fragments.Income_Fragment;
import com.example.lenovo.myfinance.Fragments.Transaction_fragment;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lenovo on 2/15/2018.
 */

public class Bottomsheet_dialog extends BottomSheetDialogFragment {

    // IDs of all the numeric buttons
    private int[] numericButtons = {R.id.buttonZero, R.id.buttonOne, R.id.buttonTwo, R.id.buttonThree, R.id.buttonFour, R.id.buttonFive, R.id.buttonSix, R.id.buttonSeven, R.id.buttonEight, R.id.buttonNine};


    // IDs of all the operator buttons
    private int[] operatorButtons = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide};
    // TextView used to display the output
    private TextView txtScreen;
    // Represent whether the lastly pressed key is numeric or not
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;

    private double valueOne = Double.NaN;
    private double valueTwo;

    float mValueOne , mValueTwo ;
    boolean mAddition , mSubtract ,mMultiplication ,mDivision ;
    boolean amountadded;

    Bundle mbundle;

    @BindView(R.id.buttonZero) Button buttonZero;
    @BindView(R.id.buttonOne) Button buttonOne;
    @BindView(R.id.buttonTwo) Button buttonTwo;
    @BindView(R.id.buttonThree) Button buttonThree;
    @BindView(R.id.buttonFour) Button buttonFour;
    @BindView(R.id.buttonFive) Button buttonfive;
    @BindView(R.id.buttonSix) Button buttonSix;
    @BindView(R.id.buttonSeven) Button buttonSeven;
    @BindView(R.id.buttonEight) Button buttonEight;
    @BindView(R.id.buttonNine) Button buttonNine;
    @BindView(R.id.buttonDot) Button buttonDot;
    @BindView(R.id.buttonAdd) Button buttonAdd;
    @BindView(R.id.buttonSubtract) Button buttonSubtract;
    @BindView(R.id.buttonMultiply) Button buttonMultiply;
    @BindView(R.id.buttonDivide) Button buttonDivide;
    @BindView(R.id.buttonClear) Button buttonClear;
    @BindView(R.id.buttonEqual) Button buttonEqual;
    @BindView(R.id.insert_button) Button mInsertButton;


    DBHelper myDb;
    private DialogInterface.OnDismissListener onDismissListener;



    public Bottomsheet_dialog() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}

////
////
////
////
////        mInsertButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                //bottomtransactionList.add(new Transaction("Health","Cash",)));
////                //mTransactionListAdapter.notifyDataSetChanged();
////

////
////
////            }


    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_transaction,null);
        ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);

        txtScreen =  contentView.findViewById(R.id.income_transaction_edittext);

        myDb = new DBHelper(getContext());


        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  myDb.insertIncomeData(txtScreen.getText().toString(),"March","Tuesday",12,2018);
                  dialog.dismiss();
//
            }
        });
        // Find and set OnClickListener to numeric buttons
           setNumericOnClickListener();
//        // Find and set OnClickListener to operator buttons, equal button and decimal point button
           setOperatorOnClickListener();
    }



    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

//        android.support.v4.app.FragmentManager fm = getFragmentManager();
//        Transaction_fragment transaction_fragment = (Transaction_fragment) fm.findFragmentById(R.id.transaction_fragment);
//        transaction_fragment.onRefresh();

        ChooseCategory_fragment chooseCategory_fragment = (ChooseCategory_fragment)Bottomsheet_dialog.this.getParentFragment();
        chooseCategory_fragment.dismiss();
    }



    /**
     * Find and set OnClickListener to numeric buttons.
     */
    private void setNumericOnClickListener() {
        // Create a common OnClickListener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of clicked button
                Button button = (Button) v;
                if (stateError) {
                    // If current state is Error, replace the error message
                    txtScreen.setText(button.getText());
                    stateError = false;
                } else {
                    // If not, already there is a valid expression so append to it
                    txtScreen.append(button.getText());
                }
                // Set the flag
                lastNumeric = true;
            }
        };
        // Assign the listener to all the numeric buttons
//        for (int id : numericButtons) {
//            getActivity().findViewById(id).setOnClickListener(listener);
//        }

        buttonZero.setOnClickListener(listener);
        buttonOne.setOnClickListener(listener);
        buttonTwo.setOnClickListener(listener);
        buttonThree.setOnClickListener(listener);
        buttonFour.setOnClickListener(listener);
        buttonfive.setOnClickListener(listener);
        buttonSix.setOnClickListener(listener);
        buttonSeven.setOnClickListener(listener);
        buttonEight.setOnClickListener(listener);
        buttonNine.setOnClickListener(listener);
    }

    /**
     * Find and set OnClickListener to operator buttons, equal button and decimal point button.
     */
    private void setOperatorOnClickListener() {
        // Create a common OnClickListener for operators
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the current state is Error do not append the operator
                // If the last input is number only, append the operator
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    txtScreen.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;    // Reset the DOT flag
                }
            }
        };
        // Assign the listener to all the operator buttons
//        for (int id : operatorButtons) {
//            getActivity().findViewById(id).setOnClickListener(listener);
//        }
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (lastNumeric && !stateError) {
//
//                    mValueOne = Float.parseFloat(txtScreen.getText() + "");
//                    mAddition = true;
//
//                }
//            }
//        });

        buttonAdd.setOnClickListener(listener);
        buttonSubtract.setOnClickListener(listener);
        buttonMultiply.setOnClickListener(listener);
        buttonDivide.setOnClickListener(listener);
        // Decimal point
      buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    txtScreen.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        // Clear button
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtScreen.setText("");  // Clear the screen
                // Reset all the states and flags
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });
        // Equal button
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    /**
     * Logic to calculate the solution.
     */
    private void onEqual() {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            String txt = txtScreen.getText().toString();
            // Create an Expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                // Calculate the result and display
                double result = expression.evaluate();
                txtScreen.setText(Double.toString(result));

                lastDot = true; // Result contains a dot
            } catch (ArithmeticException ex) {
                // Display an error message
                txtScreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}





