package com.example.lenovo.myfinance.Model;

/**
 * Created by lenovo on 3/13/2018.
 */

public class Transaction {

    String transaction_amount;
    String transaction_Month;
    String transaction_Day;
    String transaction_category;
    String transaction_type;
    int transaction_DayNo;
    int year;

    public Transaction(String transaction_amount, String transaction_Month, String transaction_Day, String transaction_category, String transaction_type, int transaction_DayNo, int year) {
        this.transaction_amount = transaction_amount;
        this.transaction_Month = transaction_Month;
        this.transaction_Day = transaction_Day;
        this.transaction_category = transaction_category;
        this.transaction_type = transaction_type;
        this.transaction_DayNo = transaction_DayNo;
        this.year = year;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_Month() {
        return transaction_Month;
    }

    public void setTransaction_Month(String transaction_Month) {
        this.transaction_Month = transaction_Month;
    }

    public String getTransaction_Day() {
        return transaction_Day;
    }

    public void setTransaction_Day(String transaction_Day) {
        this.transaction_Day = transaction_Day;
    }

    public String getTransaction_category() {
        return transaction_category;
    }

    public void setTransaction_category(String transaction_category) {
        this.transaction_category = transaction_category;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getTransaction_DayNo() {
        return transaction_DayNo;
    }

    public void setTransaction_DayNo(int transaction_DayNo) {
        this.transaction_DayNo = transaction_DayNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
