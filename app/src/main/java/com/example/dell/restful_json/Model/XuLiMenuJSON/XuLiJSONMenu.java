package com.example.dell.restful_json.Model.XuLiMenuJSON;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSONMenu;
import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_categories;
import com.example.dell.restful_json.Model.OOP.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 3/31/2018.
 */

public class XuLiJSONMenu {
    public List<Categories>GetLoaiSPTheoMaLoaiCha(int parent_id)
    {
        List<Categories>ds=new ArrayList<>();
        DowloadJSONMenu dowloadJSONMenu=new DowloadJSONMenu();
        dowloadJSONMenu.execute(String.valueOf(parent_id));
        try {
            String data=dowloadJSONMenu.get();
            JSONArray jsonArray=new JSONArray(data.toString());
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Categories categories=new Categories();
                categories.setId(jsonObject.getInt("id"));
                categories.setName(jsonObject.getString("name"));
                categories.setParent_id(jsonObject.getInt("parent_id"));
                ds.add(categories);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
