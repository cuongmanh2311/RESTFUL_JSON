package com.example.dell.restful_json.Model.OOP;

/**
 * Created by Dell on 4/24/2018.
 */

public class BIllDeatil {
    private int id;
    private int bill_id;
    private int pro_id;
    private int price;
    private int quantity;
    private long total;
    public BIllDeatil(){}

    public BIllDeatil(int id, int bill_id, int pro_id, int price, int quantity, long total) {
        this.id = id;
        this.bill_id = bill_id;
        this.pro_id = pro_id;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
