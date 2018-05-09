package com.example.lenovo.myfinance.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 5/7/2018.
 */

public class CreateFund_dialog extends AppCompatDialogFragment{
    private DialogInterface.OnDismissListener onDismissListener;
    @BindView(R.id.create_fundname_editext)
    EditText mFundname_editext;
    @BindView(R.id.fund_amount_edittext)
    EditText mFundamount_editext;
    @BindView(R.id.current_date_textview)
    TextView mCurrentDate;
    @BindView(R.id.change_date_button)
    Button mChangedate;
    int monthh ,dayy;
    DBHelper db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_createfund,null);
        ButterKnife.bind(this,view);

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
        builder.setView(view)
                .setTitle("create new Fund")

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db = new DBHelper(getActivity());
                        db.insertFundData(mFundname_editext.getText().toString(),mFundamount_editext.getText().toString(),mCurrentDate.getText().toString(),mFundamount_editext.getText().toString());
                    }
                });
        return  builder.create();
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
}
