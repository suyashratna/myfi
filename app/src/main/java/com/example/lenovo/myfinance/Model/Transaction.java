package com.example.lenovo.myfinance.Model;

import android.text.format.DateUtils;

/**
 * Created by lenovo on 3/6/2018.
 */

public class Transaction {
    String transaction_amount;
    String transaction_category;
    String transaction_date;
    String transaction_type;

    public Transaction(String transaction_amount, String transaction_category, String transaction_date, String transaction_type) {
        this.transaction_amount = transaction_amount;
        this.transaction_category = transaction_category;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_category() {
        return transaction_category;
    }

    public void setTransaction_category(String transaction_category) {
        this.transaction_category = transaction_category;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
