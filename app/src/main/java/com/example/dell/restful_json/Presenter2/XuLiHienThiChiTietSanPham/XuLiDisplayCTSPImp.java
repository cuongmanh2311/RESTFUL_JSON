package com.example.dell.restful_json.Presenter2.XuLiHienThiChiTietSanPham;

import android.content.Context;

import com.example.dell.restful_json.Model.OOP.Product;

import java.util.List;

/**
 * Created by Dell on 4/11/2018.
 */

public interface XuLiDisplayCTSPImp {
    void GetChiTietSanPham(int masp);
     void GetSanPham(List<Integer>ds);
     //void AddProIntoCart(Product product, Context context);
}
