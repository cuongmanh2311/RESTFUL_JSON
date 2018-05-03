package com.example.dell.restful_json.Presenter2.XuLiTimKiemSanPham;

import android.util.Log;
import android.widget.ListView;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_timkiem;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.View2.TimKiemSanPham.TimkiemImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/20/2018.
 */

public class SearchSPPresnterLogic implements SearchSPPresenterImp {
    TimkiemImp timkiemImp;
    public SearchSPPresnterLogic (TimkiemImp timkiemImp)
    {
        this.timkiemImp=timkiemImp;
    }
    @Override
    public void SearchSP(String pro_name) {
        List<Product>productList=new ArrayList<>();
        DowloadJSON_timkiem d=new DowloadJSON_timkiem();
        d.execute(pro_name);
        try {
            String kq=d.get();
            Log.d("kq",kq);
            JSONArray jsonArray=new JSONArray(kq.toString());
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Product product=new Product();
                product.setId(jsonObject.getInt("id"));
                product.setName(jsonObject.getString("name"));
                product.setPrice(jsonObject.getInt("price"));
                product.setImage(jsonObject.getString("image"));
                productList.add(product);
                timkiemImp.getListTimKiem(productList);
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
