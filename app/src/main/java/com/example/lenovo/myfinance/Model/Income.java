package com.example.lenovo.myfinance.Model;

import android.text.format.DateUtils;

/**
 * Created by lenovo on 3/6/2018.
 */

public class Income {
    String income_amount;
    String transaction_Month;
    String transaction_Day;
    int transaction_DayNo;
    int year;

    public Income(String income_amount, String transaction_Month, String transaction_Day,  int transaction_DayNo, int year) {
        this.income_amount = income_amount;
        this.transaction_Month = transaction_Month;
        this.transaction_Day = transaction_Day;

        this.transaction_DayNo = transaction_DayNo;
        this.year = year;
    }

    public String getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(String income_amount) {
        this.income_amount = income_amount;
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
