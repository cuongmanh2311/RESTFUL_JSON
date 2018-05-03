package com.example.dell.restful_json.Presenter2.XuLiHienThiSanPhamTheoDanhMuc;


import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_product;
import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Categories;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.HienThiMenuImp;
import com.example.dell.restful_json.View2.HienThiSPTheoDM.HienThiSPTheoDMImp;
import com.example.dell.restful_json.view3.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/10/2018.
 */

public class XuLiSPTheoDMPresenterLogic implements XuLiSPTheoDMImp {
    HienThiSPTheoDMImp thiSPTheoDMImp;
    public XuLiSPTheoDMPresenterLogic(HienThiSPTheoDMImp thiSPTheoDMImp) {
        this.thiSPTheoDMImp = thiSPTheoDMImp;
    }
    @Override
    public void GetSpTheoDm(int maloai) {
            List<Product> list=new ArrayList<>();
             DowloadJSON_product d=new DowloadJSON_product(LinkAPI.link_hienthisptheodm);
             d.execute(maloai);
            try {
                String data=d.get();
                if(data!=null)
                {
                    JSONArray jsonArray=new JSONArray(data);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Product product=new Product();
                        product.setId(jsonObject.getInt("id"));
                        product.setName(jsonObject.getString("name"));
                        product.setPrice(jsonObject.getInt("price"));
                        product.setImage(jsonObject.getString("image"));
                        product.setView(jsonObject.getInt("view"));
                        list.add(product);
                        thiSPTheoDMImp.LayDanhSachSPTheoDm(list);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

