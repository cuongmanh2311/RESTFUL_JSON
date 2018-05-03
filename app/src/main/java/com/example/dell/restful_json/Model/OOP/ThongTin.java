package com.example.dell.restful_json.Model.OOP;

import java.io.Serializable;

/**
 * Created by Dell on 2/27/2018.
 */

public class ThongTin implements Serializable{
    private int ma;
    private int dongia;
    private String ten;
    private int madm;
    public ThongTin(){}

    public ThongTin(int ma, int dongia, String ten,int madm) {
        this.ma = ma;
        this.dongia = dongia;
        this.ten = ten;
        this.madm=madm;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return this.ma+"\n"+this.dongia+"\n"+this.ten;
    }

    public int getMadm() {
        return madm;
    }

    public void setMadm(int madm) {
        this.madm = madm;
    }
}
