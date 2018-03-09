package com.example.lenovo.myfinance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 3/8/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "transactions.db";
    public static final String TABLE1_NAME ="Income_Table";
    public static final String TABLE2_NAME ="Expense_Table";
    public static final String TABLE3_NAME ="Transfer_Table";
    public static final String TABLE5_NAME ="User_Table";
    public static final String TABLE6_NAME ="Account_Table";
//    public static final String income_COL_1="income_ID";
//    public static final String income_COL_2="income_amount";
//    public static final String income_COL_3="income_category";
//    public static final String income_COL_4="income_day";
//    public static final String income_COL_5="income_dayNo";
//    public static final String income_COL_6="income_month";
//    public static final String income_COL_7="income_year";


    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, income_amount DOUBLE, income_category TEXT, income_day TEXT,income_dayNo INTEGER,income_month TEXT,income_year TEXT)");
        db.execSQL("create table " + TABLE2_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, expense_amount DOUBLE, expense_category TEXT, expense_day TEXT,expense_dayNo INTEGER, expense_month TEXT,expense_year TEXT)");
        db.execSQL("create table " + TABLE3_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, transfer_amount DOUBLE, expense_category TEXT, transfer_day TEXT,transfer_dayNo INTEGER, transfer_month TEXT,transfer_year TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        onCreate(db);

    }

}
