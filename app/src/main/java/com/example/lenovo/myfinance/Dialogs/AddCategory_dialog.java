package com.example.lenovo.myfinance.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.MenuPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lenovo.myfinance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 4/14/2018.
 */

public class AddCategory_dialog extends AppCompatDialogFragment{
 @BindView(R.id.category_name_edittext)
    EditText mCategory_name;
   @BindView(R.id.category_type_spinner)
    Spinner mCategory_type_spinner;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_category,null);
        ButterKnife.bind(this,view);

        builder.setView(view)
                .setTitle("Add Category")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.categorynames));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory_type_spinner.setAdapter(myAdapter);

            return builder.create();

    }

}
