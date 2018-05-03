package com.example.dell.restful_json.View.GetAllProduct;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dell.restful_json.Adapter.CustomAdapter;
import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.Presenter.GetAllProduct.GetAllProductPresenterLogic;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.view3.SuaSanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 3/11/2018.
 */

public class DanhSachSanPhamView extends AppCompatActivity implements DanhSachSanPhamImp {
    ListView lv;
    GetAllProductPresenterLogic getAllProductPresenterLogic;
    List<ThongTin> list=new ArrayList<>();
 //   ArrayAdapter<ThongTin>adapter;
    CustomAdapter adapter;
   // ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listproduct_layout);
        anhxa();
        sukien();
        Init();

    }

    private void sukien() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ThongTin thongTin=adapter.getItem(i);
                Intent intent=new Intent(DanhSachSanPhamView.this, SuaSanPham.class);
                intent.putExtra("sanpham",thongTin);
               // startActivityForResult(intent,100);
                startActivity(intent);
            }
        });
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            if(resultCode==999)
            {
                Intent intent=data;
                boolean kq=intent.getBooleanExtra("kt",false);
                if(kq)
                {

                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }*/

    private void Init() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.d("ip",ip);
        String duongdan="http://192.168.174.2/qlspserver/api/product";
        getAllProductPresenterLogic=new GetAllProductPresenterLogic(DanhSachSanPhamView.this,duongdan);
        getAllProductPresenterLogic.GetData();
    }

    private void anhxa() {
        lv=findViewById(R.id.lvListSP);
        list=new ArrayList<>();
        adapter=new CustomAdapter(DanhSachSanPhamView.this,R.layout.item_layout,list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
       /* progressDialog=new ProgressDialog(DanhSachSanPhamView.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Loading....");

        progressDialog.show();*/
    }

    @Override
    public void GetResultData(ThongTin thongTin) {

        adapter.addAll(thongTin);
        adapter.notifyDataSetChanged();

    }
}
