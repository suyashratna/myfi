package com.example.lenovo.myfinance.Model;

/**
 * Created by lenovo on 5/6/2018.
 */

public class Tip {
    Long tip_id;
    String tip_title;
    String tip_description;

    public Tip(Long tip_id, String tip_title, String tip_description) {
        this.tip_id = tip_id;
        this.tip_title = tip_title;
        this.tip_description = tip_description;
    }

    public Long getTip_id() {
        return tip_id;
    }

    public void setTip_id(Long tip_id) {
        this.tip_id = tip_id;
    }

    public String getTip_title() {
        return tip_title;
    }

    public void setTip_title(String tip_title) {
        this.tip_title = tip_title;
    }

    public String getTip_description() {
        return tip_description;
    }

    public void setTip_description(String tip_description) {
        this.tip_description = tip_description;
    }
}
