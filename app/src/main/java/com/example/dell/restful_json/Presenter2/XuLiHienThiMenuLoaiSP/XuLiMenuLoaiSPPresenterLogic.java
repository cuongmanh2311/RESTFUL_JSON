package com.example.dell.restful_json.Presenter2.XuLiHienThiMenuLoaiSP;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_categories;
import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Categories;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.HienThiMenuImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 3/31/2018.
 */

public class XuLiMenuLoaiSPPresenterLogic implements XuLiMenuLoaiSPImp {
    HienThiMenuImp hienThiMenuImp;

    public XuLiMenuLoaiSPPresenterLogic(HienThiMenuImp hienThiMenuImp) {
        this.hienThiMenuImp = hienThiMenuImp;
    }

    @Override
    public void GetDataCategories()  {
        List<Categories>list=new ArrayList<>();
        DowloadJSON_categories d=new DowloadJSON_categories(LinkAPI.link_categories);
        d.execute();
        try {
            String data=d.get();
            if(data!=null)
            {
                JSONArray jsonArray=new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Categories categories=new Categories();
                    categories.setId(jsonObject.getInt("id"));
                    categories.setName(jsonObject.getString("name"));
                    categories.setParent_id(jsonObject.getInt("parent_id"));
                    list.add(categories);
                    hienThiMenuImp.GetMenuCategories(list);
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
