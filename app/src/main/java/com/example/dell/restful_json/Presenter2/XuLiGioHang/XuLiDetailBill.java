package com.example.dell.restful_json.Presenter2.XuLiGioHang;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_DetailBill;
import com.example.dell.restful_json.Model.OOP.BIllDeatil;
import com.example.dell.restful_json.View2.ThueSach.ThongBaoKQAddBill;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/24/2018.
 */

public class XuLiDetailBill implements XuLiAddBillDetail {
    ThongBaoKQAddBill thongBaoKQAddBill;
    public XuLiDetailBill(ThongBaoKQAddBill thongBaoKQAddBill)
    {
        this.thongBaoKQAddBill=thongBaoKQAddBill;
    }
    @Override
    public void AddBillDetail(BIllDeatil bIllDeatil) {
        DowloadJSON_DetailBill dowloadJSON_detailBill=new DowloadJSON_DetailBill();
        dowloadJSON_detailBill.execute(bIllDeatil);
        try {
            String kq=dowloadJSON_detailBill.get();
            JSONObject jsonObject=new JSONObject(kq.toString());
            boolean s=jsonObject.getBoolean("status");
            Log.d("kq",s+"");
            thongBaoKQAddBill.NhanThongBaoAddBillDetail(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
