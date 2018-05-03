package com.example.dell.restful_json.Presenter2.XuLiRegister;

import android.util.Log;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_register;
import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.View2.Register.XuLiRegisterImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 3/29/2018.
 */

public class XuLiRegisterPresenterLogic implements XuLiRegisterPresenterImp {
    XuLiRegisterImp xuLiRegisterImp;
    private final String LOG_D="tmh";
    public XuLiRegisterPresenterLogic(XuLiRegisterImp xuLiRegisterImp) {
        this.xuLiRegisterImp = xuLiRegisterImp;
    }

    @Override
    public void XuLiRegister(Customer customer) {
        DowloadJSON_register dowloadJSON_register=new DowloadJSON_register();
        dowloadJSON_register.execute(customer);
        try {
            String kq=dowloadJSON_register.get();
            if(kq!=null)
            {
                JSONObject jsonObject=new JSONObject(kq.toString());
                Boolean result=jsonObject.getBoolean("status");
                xuLiRegisterImp.NhanThongBao(result);
                Log.d(LOG_D,kq);
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
