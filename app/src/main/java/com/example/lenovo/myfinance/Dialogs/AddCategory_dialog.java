package com.example.lenovo.myfinance.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.lenovo.myfinance.DBHelper;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 4/14/2018.
 */

public class AddCategory_dialog extends AppCompatDialogFragment{
    private DialogInterface.OnDismissListener onDismissListener;
    @BindView(R.id.category_name_edittext)
    EditText mCategory_name;
    @BindView(R.id.category_type_spinner)
    Spinner mCategory_type_spinner;
    @BindView(R.id.category_images_gridview)
            GridView mCategory_images_gridview;
    DBHelper mydb;
//    @BindView(R.id.addcategory_recyclerview)
//    RecyclerView mAddcategory_recyclerview;
     String selectedItem;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

                        //insert into database
                        mydb = new DBHelper(getContext());
                        mydb.insertCategoryData(selectedItem,mCategory_name.getText().toString(),mCategory_type_spinner.getSelectedItem().toString());


                    }
                });
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.categorynames));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory_type_spinner.setAdapter(myAdapter);

        mCategory_images_gridview.setAdapter(new ImagegridAdapter());
        mCategory_images_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem =  adapterView.getItemAtPosition(i).toString();

            }
        });

        return  builder.create();
    }

   class ImagegridAdapter extends BaseAdapter{
        ArrayList<String> categoryicons;
        String[] categoryiconsrc = getResources().getStringArray(R.array.categoryicons);

        public ImagegridAdapter() {
            categoryicons = new ArrayList<>(Arrays.asList(categoryiconsrc));
        }

        @Override
        public int getCount() {
            return categoryicons.size();
        }

        @Override
        public Object getItem(int i) {
            return categoryicons.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        class ViewHolder
        {
            ImageView categoryicon;
            ViewHolder(View v){
                categoryicon = (ImageView) v.findViewById(R.id.grid_item_image);
            }

        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View row = view;
            ViewHolder holder = null;
            if (row == null){
                LayoutInflater inflater = getActivity().getLayoutInflater();
                row = inflater.inflate(R.layout.single_grid_item,null);
                holder = new ViewHolder(row);
                row.setTag(holder);
            }
            else{
                holder = (ViewHolder) row.getTag();
            }

            String category_anames = categoryicons.get(i);
            Picasso.with(getContext()).load(category_anames).placeholder(R.mipmap.ic_launcher_round).into(holder.categoryicon);

            return row;

        }


    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener){
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(onDismissListener != null){
            onDismissListener.onDismiss(dialog);
        }
    }
}
