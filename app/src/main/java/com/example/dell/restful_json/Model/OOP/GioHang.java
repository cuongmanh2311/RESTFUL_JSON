package com.example.dell.restful_json.Model.OOP;

/**
 * Created by Dell on 4/13/2018.
 */

public class GioHang {

    private int pro_id;
    private String pro_name;
    private int price;
    private String image;
    private int quantity;


    public GioHang() {
    }

    public GioHang(int pro_id, String pro_name, int price, String image, int quantity) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}



