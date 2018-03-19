package com.example.lenovo.myfinance.Model;

/**
 * Created by lenovo on 3/18/2018.
 */

public class Category {
    String category_name;
    String category_type;

    public Category(String category_name, String category_type) {
        this.category_name = category_name;
        this.category_type = category_type;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }
}
