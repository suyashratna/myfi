package com.example.lenovo.myfinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.lenovo.myfinance.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 3/8/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "transactions.db";
    public static final String TABLE1_NAME ="Transaction_Table";
    public static final String TABLE2_NAME = "Category_Table";
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
        db.execSQL("create table " + TABLE1_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, transaction_amount TEXT, transaction_category TEXT, transaction_date TEXT, transaction_type TEXT, transaction_category_image TEXT)");
      //  db.execSQL("create table "+ TABLE2_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,category_name TEXT, category_type TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
    //    db.execSQL("DROP TABLE IF EXISTS "+ TABLE2_NAME);
        onCreate(db);

    }

    public boolean insertIncomeData(String transaction_amount, String transaction_category , String transaction_date ,String transaction_type,String transaction_category_image){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("transaction_amount",transaction_amount);
        contentValues.put("transaction_category",transaction_category);
        contentValues.put("transaction_date",transaction_date);
        contentValues.put("transaction_type",transaction_type);
        contentValues.put("transaction_category_image",transaction_category_image);

        long result = db.insert(TABLE1_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;


    }

    public List<Transaction> getTransactionData(){

        SQLiteDatabase db =  getReadableDatabase();
        String[] columns ={"transaction_amount","transaction_category","transaction_date", "transaction_type","transaction_category_image"};
        Cursor CR = db.query(TABLE1_NAME,columns,null,null,null,null,null);
        CR.moveToFirst();

        fetchedTransaction_List = new ArrayList<Transaction>();
        if(CR.getCount()>0){
            do{
                fetchedTransaction_List.add(0,new Transaction(CR.getString(0),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4)));
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

    public void DeleteTransaction(long id,Context context){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE1_NAME+"WHERE ID='"+id+"'");
        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

}
