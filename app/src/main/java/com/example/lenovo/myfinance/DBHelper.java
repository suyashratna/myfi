package com.example.lenovo.myfinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.FontsContract;
import android.widget.Toast;

import com.example.lenovo.myfinance.Fragments.Categories_fragment;
import com.example.lenovo.myfinance.Model.Category;
import com.example.lenovo.myfinance.Model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 3/8/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "transactions.db";
    public static final String TABLE1_NAME = "Transaction_Table";
    public static final String TABLE2_NAME = "Category_Table";
    public static final String TABLE3_NAME ="Account_Table";

    private List<Transaction> fetchedTransaction_List;
    private  List<Category> fetchedCategory_list;

    private  List<String> ExpenseXdata;
    private  List<Float> ExpenseYdata;

    private  List<String> IncomeXdata;
    private  List<Float> IncomeYdata;

    private List<String> Expensedates;
    private List<Float> ExpenseAmounts;



    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null,  3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, account_ID INTEGER, transaction_amount TEXT, transaction_category TEXT, transaction_date TEXT, transaction_type TEXT, transaction_category_image TEXT,transaction_description TEXT, FOREIGN KEY(account_ID) REFERENCES Account_table(account_ID))");
        db.execSQL("create table " + TABLE2_NAME +" (category_ID INTEGER PRIMARY KEY AUTOINCREMENT, category_image TEXT,category_name TEXT, category_type TEXT, category_amount TEXT, category_savinggoal)");
        db.execSQL("create table " + TABLE3_NAME +" (account_ID INTEGER PRIMARY KEY AUTOINCREMENT, account_name TEXT, account_balance TEXT, account_totalincome TEXT, account_totalexpense TEXT, account_icon TEXT)");
        setDefaultCategories(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE3_NAME);
        onCreate(db);

    }

    //-------------TRANSACTIONS------------------
    public boolean insertIncomeData(String transaction_amount, String transaction_category , String transaction_date ,String transaction_type,String transaction_category_image,String transaction_description){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("transaction_amount",transaction_amount);
        contentValues.put("transaction_category",transaction_category);
        contentValues.put("transaction_date",transaction_date);
        contentValues.put("transaction_type",transaction_type);
        contentValues.put("transaction_category_image",transaction_category_image);
        contentValues.put("transaction_description",transaction_description);

        long result = db.insert(TABLE1_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;


    }

    public List<Transaction> getTransactionData(){

        SQLiteDatabase db =  getReadableDatabase();
        String[] columns ={"ID","transaction_amount","transaction_category","transaction_date", "transaction_type","transaction_category_image","transaction_description"};
        Cursor CR = db.query(TABLE1_NAME,columns,null,null,null,null,null);
        CR.moveToFirst();


        fetchedTransaction_List = new ArrayList<Transaction>();
        if(CR.getCount()>0){
            do{
//                transaction = new Transaction();
//                transaction.setTransaction_id(CR.getLong(CR.getColumnIndex("ID")));
                fetchedTransaction_List.add(0,new Transaction(CR.getLong(0),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4),CR.getString(5),CR.getString(6)));
            }while (CR.moveToNext());
        }
        else {
            CR.moveToNext();
        }
        CR.close();
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

    //-------------CATEGORIES--------------------

    public void setDefaultCategories(SQLiteDatabase db){

        //Income Default Categories
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (1,'file:///android_asset/business_icon.png','Business','income',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (2,'file:///android_asset/salary_icon.png','Salary','income',null,null)");

        //Expense Default Categories
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (3,'file:///android_asset/education_icon.png','Education','expense',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (4,'file:///android_asset/electricity_icon.png','Electricity','expense',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (5,'file:///android_asset/entertainment_icon.png','Entertainment','expense',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (6,'file:///android_asset/grocery_icon.png','Grocery','expense',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (7,'file:///android_asset/health_icon.png','Health','expense',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (8,'file:///android_asset/restaurant_icon.png','Restaurant','expense',null,null)");
        db.execSQL("INSERT INTO " + TABLE2_NAME +" (category_ID,category_image,category_name,category_type,category_amount,category_savinggoal) VALUES (9,'file:///android_asset/transportation_icon.png','Transportation','expense',null,null)");



    }

    public List<Category> getIncomeCategories(){
        SQLiteDatabase db = getReadableDatabase();

        String columns[] ={"category_ID","category_image","category_name","category_type"};
        String whereClause = "category_type = ?";
        String whereArgs[] = new String[]{"income"};
        String columns2[] ={"SUM(transaction_amount)","transaction_type","transaction_category"};
        String whereClause2 = "transaction_type = ?";
        String whereArgs2[] = new String[]{"income"};
        Cursor cr2 = db.query(TABLE1_NAME,columns2,whereClause2,whereArgs2,"transaction_category",null,null,null);
        Cursor cr = db.query(TABLE2_NAME,columns,whereClause,whereArgs,null,null,null,null);
        cr.moveToFirst();

        double totalincategory = 0;
        fetchedCategory_list = new ArrayList<Category>();
        if(cr.getCount()>0){
            do{

                cr2.moveToFirst();
                if( cr2.getCount()>0 ){
                    do{
                        if( cr.getString(2).equals(cr2.getString(2))){
                            totalincategory = Double.parseDouble(cr2.getString(0));}
                        //     db.execSQL("UPDATE " + TABLE2_NAME + " (category_amount) VALUES ('" + String.valueOf(totalincategory) + "') WHERE category_name = ('" + cr2.getString(2)+ "')");
                    }
                    while (cr2.moveToNext());

                }else {cr2.moveToNext(); }

                fetchedCategory_list.add(0,new Category(cr.getLong(0),cr.getString(1),cr.getString(2),cr.getString(3),String.valueOf(totalincategory),null));
                totalincategory = 0;

            }while (cr.moveToNext());
        }
        else {
            cr.moveToNext();
        }
        return fetchedCategory_list;
    }

    public List<Category> getExpenseCategories(){
        SQLiteDatabase db = getReadableDatabase();
        String columns[] ={"category_ID","category_image","category_name","category_type","category_amount"};
        String whereClause = "category_type = ?";
        String whereArgs[] = new String[]{"expense"};
        String columns2[] ={"SUM(transaction_amount)","transaction_type","transaction_category"};
        String whereClause2 = "transaction_type = ?";
        Cursor cr2 = db.query(TABLE1_NAME,columns2,whereClause2,whereArgs,"transaction_category",null,null,null);
      //  Cursor cr2 = db.rawQuery("SELECT SUM("+"transaction_amount"+") FROM "+ TABLE1_NAME+" where "+ "transaction_type = expense",null);
        Cursor cr = db.query(TABLE2_NAME,columns,whereClause,whereArgs,null,null,null,null);
        cr.moveToFirst();

        double totalincategory = 0;
        fetchedCategory_list = new ArrayList<Category>();
        if( cr.getCount()>0){
            do{
                cr2.moveToFirst();
                if( cr2.getCount()>0 ){
                    do{
                        if( cr.getString(2).equals(cr2.getString(2))){
                        totalincategory = Double.parseDouble(cr2.getString(0));}
                    //     db.execSQL("UPDATE " + TABLE2_NAME + " (category_amount) VALUES ('" + String.valueOf(totalincategory) + "') WHERE category_name = ('" + cr2.getString(2)+ "')");
                    }
                    while (cr2.moveToNext());

                }else {cr2.moveToNext(); }

                    fetchedCategory_list.add(0,new Category(cr.getLong(0),cr.getString(1),cr.getString(2),cr.getString(3),String.valueOf(totalincategory),null));
                    totalincategory = 0;



            }while (cr.moveToNext());
        }
        else {
            cr.moveToNext();
        }
        cr2.close();
        cr.close();
        return fetchedCategory_list;

    }

    public boolean insertCategoryData(String category_image,String category_name,String category_type,String category_amount,String category_savinggoal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_image",category_image);
        contentValues.put("category_name",category_name);
        contentValues.put("category_type",category_type);
        contentValues.put("category_amount",category_amount);
        contentValues.put("category_savinggoal",category_savinggoal);

        long result = db.insert(TABLE2_NAME,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;

    }

    public void DeleteCategory(long id,Context context){
        SQLiteDatabase db = getWritableDatabase();

//
            db.execSQL("DELETE FROM "+TABLE2_NAME+" WHERE category_ID='"+id+"'");
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//
    }

    //--------FUNCTIONS--------------------------

    public Double GetBalance(){
        SQLiteDatabase mdb = getReadableDatabase();

        String columns[]={"transaction_amount"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"expense"};
        double totalexpense = 0;
        double totalincome =0;
        double balance = 0;
        Cursor expensecursor = mdb.query(TABLE1_NAME,columns,whereClause,whereArgs,null,null,null);
        expensecursor.moveToFirst();
        if(expensecursor != null && expensecursor.moveToFirst()){

            if(expensecursor.getCount()>0){
                do{
                    double expensetrans = Double.parseDouble(expensecursor.getString(0));
                    totalexpense = totalexpense + expensetrans;
                }
                while (expensecursor.moveToNext());
            }else{
                expensecursor.moveToNext();
            }
            expensecursor.close();
        }

        String whereArgs2[] = new String[]{"income"};
        Cursor incomeCursor = mdb.query(TABLE1_NAME,columns,whereClause,whereArgs2,null,null,null);
        incomeCursor.moveToFirst();
        if(incomeCursor != null && incomeCursor.moveToFirst()){
            if(incomeCursor.getCount()>0){
                do{
                    double incometrans = Double.parseDouble(incomeCursor.getString(0));
                    totalincome = totalincome+ incometrans;
                }
                while (incomeCursor.moveToNext());
            }
            else {
                incomeCursor.moveToNext();
            }
            incomeCursor.close();
        }

        balance = totalincome - totalexpense ;

        return balance;
    }

    public Double getIncometotal(){
        SQLiteDatabase mdb = getReadableDatabase();

        String columns[]={"transaction_amount"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"income"};
        double totalincome =0;
        Cursor incomeCursor = mdb.query(TABLE1_NAME,columns,whereClause,whereArgs,null,null,null);
        incomeCursor.moveToFirst();
        if(incomeCursor != null && incomeCursor.moveToFirst()){
            if(incomeCursor.getCount()>0){
                do{
                    double incometrans = Double.parseDouble(incomeCursor.getString(0));
                    totalincome = totalincome+ incometrans;
                }
                while (incomeCursor.moveToNext());
            }
            else {
                incomeCursor.moveToNext();
            }
            incomeCursor.close();
        }
        return totalincome;
    }

    public Double getExpenseTotal(){
        SQLiteDatabase mdb = getReadableDatabase();

        String columns[]={"transaction_amount"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"expense"};
        double totalexpense = 0;
        Cursor expensecursor = mdb.query(TABLE1_NAME,columns,whereClause,whereArgs,null,null,null);
        expensecursor.moveToFirst();
        if(expensecursor != null && expensecursor.moveToFirst()){

            if(expensecursor.getCount()>0){
                do{
                    double expensetrans = Double.parseDouble(expensecursor.getString(0));
                    totalexpense = totalexpense + expensetrans;
                }
                while (expensecursor.moveToNext());
            }else{
                expensecursor.moveToNext();
            }
            expensecursor.close();
        }
        return  totalexpense;
    }

    //------------PIE CHART-& BARCHART-----------------
    public List<String> getexpenseChartnames(){
        SQLiteDatabase db = getReadableDatabase();
        String Columns [] = {"SUM(transaction_amount)","transaction_category","transaction_type"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"expense"};
        Cursor expenseChart = db.query(TABLE1_NAME,Columns,whereClause,whereArgs,"transaction_category",null,null);
        expenseChart.moveToFirst();
       // float chartvalue = 0;
        if(expenseChart != null && expenseChart.moveToFirst()){
            ExpenseXdata = new ArrayList<String>();
            if(expenseChart.getCount()>0){
                do{
                    if(expenseChart.getDouble(0) > 0)
                    {
                       ExpenseXdata.add(0,new String(expenseChart.getString(1)));
                    }
                }
                while (expenseChart.moveToNext());
            }else{
                expenseChart.moveToNext();
            }
            expenseChart.close();
        }
        return ExpenseXdata;

    }

    public List<String> getincomeChartnames(){
        SQLiteDatabase db = getReadableDatabase();
        String Columns [] = {"SUM(transaction_amount)","transaction_category","transaction_type"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"income"};
        Cursor incomechart = db.query(TABLE1_NAME,Columns,whereClause,whereArgs,"transaction_category",null,null);
        incomechart.moveToFirst();
        // float chartvalue = 0;
        if(incomechart != null && incomechart.moveToFirst()){
            IncomeXdata = new ArrayList<String>();
            if(incomechart.getCount()>0){
                do{
                    if(incomechart.getDouble(0) > 0)
                    {
                        IncomeXdata.add(0,new String(incomechart.getString(1)));
                    }
                }
                while (incomechart.moveToNext());
            }else{
                incomechart.moveToNext();
            }
            incomechart.close();
        }
        return IncomeXdata;

    }

    public List<Float> getexpenseChartdata(){
        SQLiteDatabase db = getReadableDatabase();
        String Columns [] = {"SUM(transaction_amount)","transaction_type","transaction_category"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"expense"};

        Cursor expenseChart = db.query(TABLE1_NAME,Columns,whereClause,whereArgs,"transaction_category",null,null);
        expenseChart.moveToFirst();
        // float chartvalue = 0;
        if(expenseChart != null && expenseChart.moveToFirst()){
            ExpenseYdata = new ArrayList<Float>();
            if(expenseChart.getCount()>0){
                do{
                    //float initialvalue = Float.parseFloat(expenseChart.getString(0));
                    ExpenseYdata.add(0,new Float(Float.parseFloat(expenseChart.getString(0))));
                }
                while (expenseChart.moveToNext());
            }else{
                expenseChart.moveToNext();
            }
            expenseChart.close();
        }
        return ExpenseYdata;

    }

    public List<Float> getincomeChartdata(){
        SQLiteDatabase db = getReadableDatabase();
        String Columns [] = {"SUM(transaction_amount)","transaction_type","transaction_category"};
        String whereClause = "transaction_type = ?";
        String whereArgs[] = new String[]{"income"};

        Cursor incomeChart = db.query(TABLE1_NAME,Columns,whereClause,whereArgs,"transaction_category",null,null);
        incomeChart.moveToFirst();
        // float chartvalue = 0;
        if(incomeChart != null && incomeChart.moveToFirst()){
            IncomeYdata = new ArrayList<Float>();
            if(incomeChart.getCount()>0){
                do{
                    //float initialvalue = Float.parseFloat(expenseChart.getString(0));
                    IncomeYdata.add(0,new Float(Float.parseFloat(incomeChart.getString(0))));
                }
                while (incomeChart.moveToNext());
            }else{
                incomeChart.moveToNext();
            }
            incomeChart.close();
        }
        return IncomeYdata;

    }
    //-------------END OF PIE CHART------------







}
