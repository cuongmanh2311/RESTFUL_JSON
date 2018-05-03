package com.example.dell.restful_json.Presenter2.XuLiHienThiToanBoSanPham;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_categories;
import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.HienThiAllSpImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/17/2018.
 */

public class HienThiToanBoSpPresenterLogic implements HienThiToanBoSpImp {
    HienThiAllSpImp hienThiAllSpImp;
    public HienThiToanBoSpPresenterLogic (HienThiAllSpImp hienThiAllSpImp)
    {
        this.hienThiAllSpImp=hienThiAllSpImp;
    }
    @Override
    public void HienThiSp() {
        List<Product> list=new ArrayList<>();
        DowloadJSON_categories d=new DowloadJSON_categories(LinkAPI.link_toanbosach);
        d.execute();
        try {
            String kq=d.get();
            Log.d("an",kq);
            if(kq!=null)
            {
                JSONArray jsonArray=new JSONArray(kq.toString());
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


                    }
                hienThiAllSpImp.GetAllBook(list);
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
