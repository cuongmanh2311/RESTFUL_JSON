package com.example.dell.restful_json.Model.OOP;

/**
 * Created by Dell on 3/12/2018.
 */

public class DanhMuc {
    private int madm;
    private String tendm;
    public DanhMuc(){}
    public DanhMuc(int madm, String tendm) {
        this.madm = madm;
        this.tendm = tendm;
    }

    public int getMadm() {
        return madm;
    }

    public void setMadm(int madm) {
        this.madm = madm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    @Override
    public String toString() {
        return madm+" - "+tendm;
    }
}
