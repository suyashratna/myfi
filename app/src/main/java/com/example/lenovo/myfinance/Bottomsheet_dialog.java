package com.example.lenovo.myfinance;


import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lenovo.myfinance.Fragments.ChooseCategory_fragment;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lenovo on 2/15/2018.
 */

public class Bottomsheet_dialog extends BottomSheetDialogFragment  {

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
    public String category_name;
    Bundle mbundle;
    private BottomSheetBehavior mBehaviour;

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

 //    public @BindView(R.id.categoryname_indialog) TextView mCategoryName;
     @BindView(R.id.current_date_textview) TextView mCurrentDate;
     @BindView(R.id.change_date_button) Button mChangedate;


    int monthh ,dayy;
    public Bottomsheet_dialog() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}



    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_transaction,null);
        ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);

        mBehaviour = BottomSheetBehavior.from((View)contentView.getParent());


        txtScreen =  contentView.findViewById(R.id.income_transaction_edittext);
         final Calendar c = Calendar.getInstance();
        //get the category name from the category selection fragment

       // mCategoryName.setText(b.getString("categoryName"));

        myDb = new DBHelper(getContext());

        final DateFormat month = new SimpleDateFormat("MMMM");
        final DateFormat Day = new SimpleDateFormat("EEEE");
        final DateFormat DayNO = new SimpleDateFormat("dd");

        final Date date = new Date();
        Log.d("Month",month.format(date));

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);

        String totaldate = DayNO.format(date)+ " " + month.format(date)+ " " + year+ ", "+Day.format(date);
        mCurrentDate.setText(totaldate);

        Calendar myCalendar = Calendar.getInstance();

        monthh = myCalendar.get(Calendar.MONTH);
        dayy = myCalendar.get(Calendar.DAY_OF_MONTH);

        mChangedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        c.set(Calendar.YEAR,i);
                        c.set(Calendar.MONTH,i1);
                        c.set(Calendar.DAY_OF_MONTH,i2);
                        final DateFormat dateFormat = new SimpleDateFormat("dd MMMM YYYY, EEEE");
                        String currentDateString = dateFormat.format(c.getTime());
                        mCurrentDate.setText(currentDateString);
                    }
                },year,monthh,dayy);
                datePickerDialog.show();
            }
        });

        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtScreen.getText().toString().trim().length() == 0){
                    return;
                }
                  Bundle b = getArguments();
                  myDb.insertIncomeData(txtScreen.getText().toString(),b.getString("categoryName"),mCurrentDate.getText().toString(),b.getString("categoryType"),b.getString("categoryImage"));
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
    public void onStart() {
        super.onStart();
        mBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
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





