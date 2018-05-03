package com.example.dell.restful_json.Presenter2.XuLiHienThiSachDaThue;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_rent;
import com.example.dell.restful_json.View2.ThueSach.GioSachImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/16/2018.
 */

public class XuLiHienThiSachDaThuePresenterLogic implements XuLiHienThiSachDaThueImp {
        GioSachImp gioSachImp;
        public XuLiHienThiSachDaThuePresenterLogic (GioSachImp gioSachImp)
        {
            this.gioSachImp=gioSachImp;
        }
    @Override
    public void HienThiSachThue(int user_id) {
        List<Integer>list=new ArrayList<>();
        DowloadJSON_rent d=new DowloadJSON_rent();
        d.execute(user_id);

        try {
            String kq=d.get();
            Log.d("sach",kq);
            JSONArray jsonArray=new JSONArray(kq.toString());
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int pro_id=jsonObject.getInt("pro_id");
                int status=jsonObject.getInt("status");
                list.add(pro_id);
            }
            gioSachImp.NhanMaSach(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
