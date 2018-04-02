package com.example.lenovo.myfinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lenovo.myfinance.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

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
    private List<Transaction> fetchedTransaction_List;
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
        db.execSQL("create table " + TABLE1_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, income_amount TEXT, income_category TEXT, income_date TEXT, transtype TEXT)");
//        db.execSQL("create table " + TABLE2_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, expense_amount DOUBLE, expense_category TEXT, expense_day TEXT,expense_dayNo INTEGER, expense_month TEXT,expense_year TEXT)");
//        db.execSQL("create table " + TABLE3_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, transfer_amount DOUBLE, expense_category TEXT, transfer_day TEXT,transfer_dayNo INTEGER, transfer_month TEXT,transfer_year TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        onCreate(db);

    }

    public boolean insertIncomeData(String income_amount, String income_category , String income_date ,String trans_type){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("income_amount",income_amount);
        contentValues.put("income_category",income_category);
        contentValues.put("income_date",income_date);
        contentValues.put("transtype",trans_type);

        long result = db.insert(TABLE1_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;


    }

    public List<Transaction> getTransactionData(){

        SQLiteDatabase db =  getReadableDatabase();
        String[] columns ={"income_amount","income_category","income_date", "transtype"};
        Cursor CR = db.query(TABLE1_NAME,columns,null,null,null,null,null);
        CR.moveToFirst();

        fetchedTransaction_List = new ArrayList<Transaction>();
        if(CR.getCount()>0){
            do{
                fetchedTransaction_List.add(0,new Transaction(CR.getString(0),CR.getString(1),CR.getString(2),CR.getString(3)));
            }while (CR.moveToNext());
        }
        else {
            CR.moveToNext();
        }
        return fetchedTransaction_List;
    }

    public void clearHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE1_NAME);


    }

}
