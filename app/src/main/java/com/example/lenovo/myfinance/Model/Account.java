package com.example.lenovo.myfinance.Model;

/**
 * Created by lenovo on 3/9/2018.
 */

public class Account {

    Long account_id;
    String account_image;
    String account_name;
    String account_income;
    String account_expense;
    String account_balance;

    public Account(Long account_id, String account_image, String account_name, String account_income, String account_expense, String account_balance) {
        this.account_id = account_id;
        this.account_image = account_image;
        this.account_name = account_name;
        this.account_income = account_income;
        this.account_expense = account_expense;
        this.account_balance = account_balance;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getAccount_image() {
        return account_image;
    }

    public void setAccount_image(String account_image) {
        this.account_image = account_image;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_income() {
        return account_income;
    }

    public void setAccount_income(String account_income) {
        this.account_income = account_income;
    }

    public String getAccount_expense() {
        return account_expense;
    }

    public void setAccount_expense(String account_expense) {
        this.account_expense = account_expense;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }
}
