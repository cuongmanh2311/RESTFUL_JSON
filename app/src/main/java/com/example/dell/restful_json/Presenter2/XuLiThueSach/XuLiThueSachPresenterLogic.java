package com.example.dell.restful_json.Presenter2.XuLiThueSach;

import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_themsach;
import com.example.dell.restful_json.View2.ThueSach.ThongBaoKetQuaThue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/13/2018.
 */

public class XuLiThueSachPresenterLogic implements XuLiThemSachImp {
    ThongBaoKetQuaThue thongBaoKetQuaThue;
    public XuLiThueSachPresenterLogic(ThongBaoKetQuaThue thongBaoKetQuaThue)
    {
        this.thongBaoKetQuaThue=thongBaoKetQuaThue;
    }
    @Override
    public void KiemTraThueSach(int user_id, int masach) {
        DowloadJSON_themsach dowloadJSON_themsach=new DowloadJSON_themsach();
        dowloadJSON_themsach.execute(user_id,masach);
        try {
            String kq=dowloadJSON_themsach.get();
            if(kq!=null)
            {
                JSONObject jsonObject=new JSONObject(kq.toString());
                boolean kt=jsonObject.getBoolean("status");
                thongBaoKetQuaThue.ThongBaoKetQuaThueSach(kt);
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
