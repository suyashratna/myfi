package com.example.lenovo.myfinance.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 5/5/2018.
 */

public class UpdateCategory_dialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_category,null);
        ButterKnife.bind(this,view);
        builder.setView(view)
                .setTitle("Add new Account")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
//                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        DBHelper dbHelper = new DBHelper(getActivity());
//                        final Category category = categoryList.get(position);
//                        if(category.getCategory_id() != null){
//                            dbHelper.DeleteCategory(category.getCategory_id(),getActivity());
////                        categoryList.remove(position);
////                        mIncomeCategory_Recycler.removeViewAt(position);
//                            main_categoryList_adapter.notifyDataSetChanged();
//                    }
//                });
        return builder.create();

    }
}
