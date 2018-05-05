package com.example.lenovo.myfinance.Model;

/**
 * Created by lenovo on 5/5/2018.
 */

public class Fund {
    Long fund_id;
    String fund_name;
    String fund_added_date;
    String fund_lastadded_amount;
    String fund_amount;

    public Fund(Long fund_id, String fund_name, String fund_added_date, String fund_lastadded_amount, String fund_amount) {
        this.fund_id = fund_id;
        this.fund_name = fund_name;
        this.fund_added_date = fund_added_date;
        this.fund_lastadded_amount = fund_lastadded_amount;
        this.fund_amount = fund_amount;
    }

    public Long getFund_id() {
        return fund_id;
    }

    public void setFund_id(Long fund_id) {
        this.fund_id = fund_id;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getFund_added_date() {
        return fund_added_date;
    }

    public void setFund_added_date(String fund_added_date) {
        this.fund_added_date = fund_added_date;
    }

    public String getFund_lastadded_amount() {
        return fund_lastadded_amount;
    }

    public void setFund_lastadded_amount(String fund_lastadded_amount) {
        this.fund_lastadded_amount = fund_lastadded_amount;
    }

    public String getFund_amount() {
        return fund_amount;
    }

    public void setFund_amount(String fund_amount) {
        this.fund_amount = fund_amount;
    }
}
