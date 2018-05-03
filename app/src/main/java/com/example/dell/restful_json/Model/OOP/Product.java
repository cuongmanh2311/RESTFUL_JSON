package com.example.dell.restful_json.Model.OOP;

import android.graphics.Bitmap;

/**
 * Created by Dell on 4/8/2018.
 */

public class Product {
    private int id;
    private String name;
    private int cate_id;
    private int price;
    private String intro;
    private String description;
    private String image;
    private int status;
    private String created_at;
    private String updated_at;
    private int view;
    private Bitmap bitmap;
    private byte[]hinhgiohang;

    public byte[] getHinhgiohang() {
        return hinhgiohang;
    }

    public void setHinhgiohang(byte[] hinhgiohang) {
        this.hinhgiohang = hinhgiohang;
    }

    public Product(int id, String name, int cate_id, int price, String intro, String description, String image, int status, int view, Bitmap bitmap) {
        this.id = id;
        this.name = name;
        this.cate_id = cate_id;
        this.price = price;
        this.intro = intro;
        this.description = description;
        this.image = image;
        this.status = status;
        this.view=view;
        this.bitmap=bitmap;

    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
