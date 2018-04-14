package com.example.lenovo.myfinance.Model;

/**
 * Created by lenovo on 3/18/2018.
 */

public class Category {
    Long category_id;
    String category_image;
    String category_name;
    String category_type;
    String category_latest_date;
    String category_total;

    public Category(Long category_id, String category_image, String category_name, String category_type, String category_latest_date, String category_total) {
        this.category_id = category_id;
        this.category_image = category_image;
        this.category_name = category_name;
        this.category_type = category_type;
        this.category_latest_date = category_latest_date;
        this.category_total = category_total;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
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

    public String getCategory_latest_date() {
        return category_latest_date;
    }

    public void setCategory_latest_date(String category_latest_date) {
        this.category_latest_date = category_latest_date;
    }

    public String getCategory_total() {
        return category_total;
    }

    public void setCategory_total(String category_total) {
        this.category_total = category_total;
    }
}
