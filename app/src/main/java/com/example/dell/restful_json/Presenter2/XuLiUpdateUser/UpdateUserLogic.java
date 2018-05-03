package com.example.dell.restful_json.Presenter2.XuLiUpdateUser;

import com.example.dell.restful_json.Model.DowloadJSON.Dowload_JSON_updateUser;
import com.example.dell.restful_json.Model.OOP.Customer;
import com.example.dell.restful_json.View2.HienThiThongTinCustomer.UpdateUserImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/18/2018.
 */

public class UpdateUserLogic implements UpdateUserPresenterImp {
    UpdateUserImp updateUserImp;
    public UpdateUserLogic(UpdateUserImp updateUserImp)
    {
        this.updateUserImp=updateUserImp;
    }
    @Override
    public void UpdateUser(Customer customer) {
        Dowload_JSON_updateUser d=new Dowload_JSON_updateUser();
        d.execute(customer);
        try {
            String kq=d.get();
            if(kq!=null)
            {
                JSONObject jsonObject=new JSONObject(kq.toString());
                boolean result=jsonObject.getBoolean("status");
                updateUserImp.thongbaosuauser(result);
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
