package com.example.lenovo.myfinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;
import android.widget.Toast;

import com.example.lenovo.myfinance.Model.Category;
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
    private  List<Category> fetchedCategory_list;


    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, transaction_amount TEXT, transaction_category TEXT, transaction_date TEXT, transaction_type TEXT, transaction_category_image TEXT)");
       // db.execSQL("create table "+ TABLE2_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, category_image TEXT,category_name TEXT, category_type TEXT)");


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
        String[] columns ={"ID","transaction_amount","transaction_category","transaction_date", "transaction_type","transaction_category_image"};
        Cursor CR = db.query(TABLE1_NAME,columns,null,null,null,null,null);
        CR.moveToFirst();


        fetchedTransaction_List = new ArrayList<Transaction>();
        if(CR.getCount()>0){
            do{
//                transaction = new Transaction();
//                transaction.setTransaction_id(CR.getLong(CR.getColumnIndex("ID")));
                fetchedTransaction_List.add(0,new Transaction(CR.getLong(0),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4),CR.getString(5)));
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
        db.execSQL("DELETE FROM "+TABLE1_NAME+" WHERE ID='"+id+"'");
        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

//    public List<Category> getIncomeCategories(){
//        SQLiteDatabase db = getReadableDatabase();
//        String columns[] ={"ID","category_image","category_name","category_type"};
//        Cursor cr = db.query(TABLE2_NAME,columns,null,null,null,columns[3]="income",null,null);
//        cr.moveToFirst();
//
//        fetchedCategory_list = new ArrayList<Category>();
//        if(cr.getCount()>0){
//            do{
//                fetchedCategory_list.add(0,new Category(cr.getLong(0),cr.getString(1),cr.getString(2),cr.getString(3),null,null));
//            }while (cr.moveToFirst());
//        }
//        else {
//            cr.moveToNext();
//        }
//        return fetchedCategory_list;
//    }
//    public List<Category> getExpenseCategories(){
//        SQLiteDatabase db = getReadableDatabase();
//        String columns[] ={"ID","category_image","category_name","category_type"};
//        Cursor cr = db.query(TABLE2_NAME,columns,null,null,null,columns[3]="expense",null,null);
//        cr.moveToFirst();
//
//        fetchedCategory_list = new ArrayList<Category>();
//        if(cr.getCount()>0){
//            do{
//                fetchedCategory_list.add(0,new Category(cr.getLong(0),cr.getString(1),cr.getString(2),cr.getString(3),null,null));
//            }while (cr.moveToFirst());
//        }
//        else {
//            cr.moveToNext();
//        }
//        return fetchedCategory_list;
//    }

    public void DeleteCategory(long id,Context context){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE2_NAME+" WHERE ID='"+id+"'");
        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

}
