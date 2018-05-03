package com.example.dell.restful_json.Presenter.GetAllProduct;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadAllProduct.DowloadAllProduct;
import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.View.GetAllProduct.DanhSachSanPhamImp;
import com.example.dell.restful_json.View.GetAllProduct.DanhSachSanPhamView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 3/11/2018.
 */

public class GetAllProductPresenterLogic implements GetAllProductPresenter {
    DanhSachSanPhamImp danhSachSanPhamImp;
    String url;
    public GetAllProductPresenterLogic(DanhSachSanPhamView danhSachSanPhamImp,String url)
    {
        this.danhSachSanPhamImp= (DanhSachSanPhamImp) danhSachSanPhamImp;
        this.url=url;
    }
    @Override
    public void GetData() {
        DowloadAllProduct download=new DowloadAllProduct();
        download.execute(url);
        try {
            String data=download.get();
            Log.d("kt",data);
            JSONArray jsonArray=new JSONArray(data);
          for(int i=0;i<jsonArray.length();i++)
          {
              JSONObject jsonObject=jsonArray.getJSONObject(i);
              ThongTin t=new ThongTin();
              t.setMa(jsonObject.getInt("product_id"));
              t.setTen(jsonObject.getString("product_name"));
              t.setDongia(jsonObject.getInt("price"));
              danhSachSanPhamImp.GetResultData(t);
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
