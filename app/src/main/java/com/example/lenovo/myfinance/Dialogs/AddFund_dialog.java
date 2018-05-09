package com.example.lenovo.myfinance.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 5/6/2018.
 */

public class AddFund_dialog extends BottomSheetDialogFragment {
    private DialogInterface.OnDismissListener onDismissListener;
    private TextView txtScreen;
    // Represent whether the lastly pressed key is numeric or not
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;

    @BindView(R.id.buttonZero)
    Button buttonZero;
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

    @BindView(R.id.current_date_textview) TextView mCurrentDate;
    @BindView(R.id.change_date_button) Button mChangedate;
    @BindView(R.id.fund_name_spinner)

    Spinner mFundname_spinner;
    int monthh ,dayy;
    DBHelper mydb;
    private BottomSheetBehavior mBehaviour;

    public AddFund_dialog(){}

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_addfund,null);
        ButterKnife.bind(this,view);
        dialog.setContentView(view);
        mBehaviour = BottomSheetBehavior.from((View)view.getParent());
        txtScreen =  view.findViewById(R.id.income_transaction_edittext);
        mydb = new DBHelper(getContext());

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,mydb.getFundNames());
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFundname_spinner.setAdapter(myAdapter);

        final Calendar c = Calendar.getInstance();
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
                mydb = new DBHelper(getActivity());
                mydb.updatefundData(mFundname_spinner.getSelectedItem().toString(),mCurrentDate.getText().toString(),txtScreen.getText().toString());
                dialog.dismiss();

            }
        });
        // Find and set OnClickListener to numeric buttons
        setNumericOnClickListener();
        // Find and set OnClickListener to operator buttons, equal button and decimal point button
        setOperatorOnClickListener();
    }


    @Override
    public void onStart() {
        super.onStart();
        mBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
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
