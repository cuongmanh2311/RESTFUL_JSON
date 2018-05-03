package com.example.dell.restful_json.Presenter2.XuLiThueSach;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_rent;
import com.example.dell.restful_json.View2.ThueSach.TrangThaiSachImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/13/2018.
 */

public class XuLiTrangThaiSachPresenterlogic implements XuLiTrangThaiSachImp{
    TrangThaiSachImp trangThaiSachImp;
    public XuLiTrangThaiSachPresenterlogic(TrangThaiSachImp trangThaiSachImp)
    {
        this.trangThaiSachImp=trangThaiSachImp;
    }
    @Override
    public void KiemTraTrangThaiSach(int user_id, int masach) {
        DowloadJSON_rent dowloadJSON_rent = new DowloadJSON_rent();
        dowloadJSON_rent.execute(user_id);
        try {
            String kq = dowloadJSON_rent.get();
            Log.d("result", kq.toString());
            if (kq.equals("[]")) {
                trangThaiSachImp.ThongBaoTinhTrangMuon("ok");
            } else {
                JSONArray jsonArray = new JSONArray(kq.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("id")) {
                        int pro_id = jsonObject.getInt("pro_id");
                        Log.d("cc", pro_id + "  " + masach);
                        if (pro_id == masach) {
                            trangThaiSachImp.ThongBaoTinhTrangMuon("fail");
                            break;
                        } else {
                            trangThaiSachImp.ThongBaoTinhTrangMuon("ok");
                        }
                    }
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
