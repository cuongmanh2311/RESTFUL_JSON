package com.example.dell.restful_json.Presenter2.XuLiGioHang;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSONBILL;
import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_DetailBill;
import com.example.dell.restful_json.Model.OOP.BIllDeatil;
import com.example.dell.restful_json.Model.OOP.Bill;
import com.example.dell.restful_json.View2.ThueSach.ThongBaoKQAddBill;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/24/2018.
 */

public class XuLiCartLogic implements XuLiCartImp {
    ThongBaoKQAddBill thongBaoKQAddBill;
    public XuLiCartLogic(ThongBaoKQAddBill thongBaoKQAddBill)
    {
        this.thongBaoKQAddBill=thongBaoKQAddBill;
    }
    @Override
    public void AddBill(Bill bill) {
        DowloadJSONBILL d=new DowloadJSONBILL();
        d.execute(bill);
        try {
            String kq=d.get();
            Log.d("bill",kq);
            JSONObject jsonObject=new JSONObject(kq.toString());
            boolean b=jsonObject.getBoolean("status");
            int bill_id=jsonObject.getInt("id");
            Log.d("bill_id",jsonObject.getInt("id")+"");
            thongBaoKQAddBill.NhanThongBao(bill_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void AddBillDetail(BIllDeatil bIllDeatil)  {
        DowloadJSON_DetailBill dowloadJSON_detailBill=new DowloadJSON_DetailBill();
        dowloadJSON_detailBill.equals(bIllDeatil);
        try {
            String kq=dowloadJSON_detailBill.get();
            JSONObject jsonObject=new JSONObject(kq.toString());
            boolean s=jsonObject.getBoolean("status");
            thongBaoKQAddBill.NhanThongBaoAddBillDetail(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/
}
