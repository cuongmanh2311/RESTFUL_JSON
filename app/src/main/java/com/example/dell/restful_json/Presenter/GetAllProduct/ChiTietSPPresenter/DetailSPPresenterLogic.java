package com.example.dell.restful_json.Presenter.GetAllProduct.ChiTietSPPresenter;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadAllProduct.DowloadDetailSp;
import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.View.GetAllProduct.ChiTietSanPhamImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 3/12/2018.
 */

public class DetailSPPresenterLogic implements DetailSPPresenterImp {
    ChiTietSanPhamImp chiTietSanPhamImp;

    public DetailSPPresenterLogic(ChiTietSanPhamImp c)
    {
        this.chiTietSanPhamImp=c;

    }
    @Override
    public void XuLiData(String masp) {
        DowloadDetailSp d=new DowloadDetailSp();
        d.execute(masp);
        try {
            String kq=d.get();
            if(kq!=null)
            {
                JSONObject jsonObject=new JSONObject(kq.toString());
                ThongTin t=new ThongTin();
                t.setMa(jsonObject.getInt("product_id"));
                t.setTen(jsonObject.getString("product_name"));
                t.setDongia(jsonObject.getInt("price"));
                chiTietSanPhamImp.GetDetailProduct(t);
                Log.d("kt",kq);
            }else{

                String kq1="Mã sản phẩm này không có thông tin";
                Log.d("kt",kq1);
                chiTietSanPhamImp.NhanKetQuaThatBai(kq1);
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
