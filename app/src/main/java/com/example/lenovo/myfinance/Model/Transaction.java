package com.example.lenovo.myfinance.Model;

import android.text.format.DateUtils;

/**
 * Created by lenovo on 2/14/2018.
 */

public class Transaction {
    String category;
    String trans_account;
    int trans_amount;

    public Transaction(String category, String trans_account, int trans_amount) {
        this.category = category;

        this.trans_account = trans_account;
        this.trans_amount = trans_amount;
    }

    public String getCategory() {
        return category;
    }



    public String getTrans_account() {
        return trans_account;
    }

    public int getTrans_amount() {
        return trans_amount;
    }



    public void setTrans_account(String trans_account) {
        this.trans_account = trans_account;
    }

    public void setTrans_amount(int trans_amount) {
        this.trans_amount = trans_amount;
    }
}
