package com.example.lenovo.myfinance.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.myfinance.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 4/30/2018.
 */

public class AddAccount_dialog extends AppCompatDialogFragment {
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

    private TextView txtscreen;
    // Represent whether the lastly pressed key is numeric or not
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;



    @BindView(R.id.account_name_edittext)
    EditText mAccountname_editext;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_account,null);
        ButterKnife.bind(this,view);
        txtscreen =  view.findViewById(R.id.income_transaction_edittext);

        // Find and set OnClickListener to numeric buttons
        setNumericOnClickListener();
       // Find and set OnClickListener to operator buttons, equal button and decimal point button
        setOperatorOnClickListener();
        builder.setView(view)
                .setTitle("Add new Account")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("INSERT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();

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
                    txtscreen.setText(button.getText());
                    stateError = false;
                } else {
                    // If not, already there is a valid expression so append to it
                    txtscreen.append(button.getText());
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
                    txtscreen.append(button.getText());
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
                    txtscreen.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        // Clear button
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtscreen.setText("");  // Clear the screen
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
            String txt = txtscreen.getText().toString();
            // Create an Expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                // Calculate the result and display
                double result = expression.evaluate();
                txtscreen.setText(Double.toString(result));

                lastDot = true; // Result contains a dot
            } catch (ArithmeticException ex) {
                // Display an error message
                txtscreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
