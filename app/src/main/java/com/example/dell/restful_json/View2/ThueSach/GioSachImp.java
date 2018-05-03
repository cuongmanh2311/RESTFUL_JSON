package com.example.dell.restful_json.View2.ThueSach;

import com.example.dell.restful_json.Model.OOP.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/16/2018.
 */

public interface GioSachImp {
  //  void NhanMaSach(int pro_id,int status);
    void NhanMaSach(List<Integer>ds);
    void NhanThongTinSach(ArrayList<Product>list);


}
