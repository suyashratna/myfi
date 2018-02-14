package com.example.lenovo.myfinance.Model;

import android.text.format.DateUtils;

/**
 * Created by lenovo on 2/14/2018.
 */

public class Transaction {
    String category;
    String trans_day;
    DateUtils trans_date;
    int trans_time;
    int trans_year;
    String trans_account;
    int trans_amount;

    public Transaction(String category, String trans_day, DateUtils trans_date, int trans_time, int trans_year, String trans_account, int trans_amount) {
        this.category = category;
        this.trans_day = trans_day;
        this.trans_date = trans_date;
        this.trans_time = trans_time;
        this.trans_year = trans_year;
        this.trans_account = trans_account;
        this.trans_amount = trans_amount;
    }

    public String getCategory() {
        return category;
    }

    public String getTrans_day() {
        return trans_day;
    }

    public DateUtils getTrans_date() {
        return trans_date;
    }

    public int getTrans_time() {
        return trans_time;
    }

    public int getTrans_year() {
        return trans_year;
    }

    public String getTrans_account() {
        return trans_account;
    }

    public int getTrans_amount() {
        return trans_amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTrans_day(String trans_day) {
        this.trans_day = trans_day;
    }

    public void setTrans_date(DateUtils trans_date) {
        this.trans_date = trans_date;
    }

    public void setTrans_time(int trans_time) {
        this.trans_time = trans_time;
    }

    public void setTrans_year(int trans_year) {
        this.trans_year = trans_year;
    }

    public void setTrans_account(String trans_account) {
        this.trans_account = trans_account;
    }

    public void setTrans_amount(int trans_amount) {
        this.trans_amount = trans_amount;
    }
}
