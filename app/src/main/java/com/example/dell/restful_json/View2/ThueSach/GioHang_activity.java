package com.example.dell.restful_json.View2.ThueSach;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.restful_json.Adapter.AdapterThueSach;
import com.example.dell.restful_json.Adapter.CustomAdapterCart;
import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.BIllDeatil;
import com.example.dell.restful_json.Model.OOP.Bill;
import com.example.dell.restful_json.Model.OOP.GioHang;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Presenter2.XuLiGioHang.XuLiCartLogic;
import com.example.dell.restful_json.Presenter2.XuLiGioHang.XuLiDetailBill;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;
import com.example.dell.restful_json.View2.HienThiSPTheoDM.HienThiSanPhamTheoMaDanhMuc;
import com.example.dell.restful_json.view3.SanPhamTheoDanhMuc;
import com.example.dell.restful_json.view3.ThemSanPham;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/13/2018.
 */

public class GioHang_activity extends AppCompatActivity implements ThongBaoKQAddBill {
  ListView listView;
  TextView txtThongBao;static TextView txtTotal;
  Button btnThanhToan,btnMuaTiep;
  Toolbar toolbar;
  CustomAdapterCart adapterCart;
  int user_id;
  int bill_id;
    XuLiCartLogic xuLiCartLogic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tongsach);
        AnhXa();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        user_id=intent.getIntExtra("user_id",0);
        Log.d("cuong5",user_id+"");
        CheckData();
        Event();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int vitri, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(GioHang_activity.this);
                builder.setTitle(R.string.xoasp);
                builder.setMessage(R.string.messageXoaSp);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            if (Trangchu_activity.gioHangArrayList.size()<=0)
                            {
                                txtThongBao.setVisibility(View.VISIBLE);
                            }else{
                                Trangchu_activity.gioHangArrayList.remove(vitri);
                                adapterCart.notifyDataSetChanged();
                                Event();
                                if(Trangchu_activity.gioHangArrayList.size()<=0)
                                {
                                    txtThongBao.setVisibility(View.VISIBLE);
                                }else {
                                    txtThongBao.setVisibility(View.INVISIBLE);
                                    adapterCart.notifyDataSetChanged();
                                    Event();
                                }
                            }
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterCart.notifyDataSetChanged();
                        Event();
                    }
                });
                builder.show();
                return false;
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiThanhToan();
            }
        });
        btnMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiMuaTiepHang();
            }
        });
}

    private void XuLiMuaTiepHang() {
        Intent iTrangChu=new Intent(GioHang_activity.this,ChiTietSanPhamActivity.class);
        startActivity(iTrangChu);
    }

    private void XuLiThanhToan() {
        if(Trangchu_activity.gioHangArrayList.size()<=0)
        {
            Toast.makeText(this, R.string.ksp, Toast.LENGTH_SHORT).show();
        }else{
            Bill bill=new Bill();
            bill.setMember_id(user_id);
            bill.setStatus(0);
            bill.setTotal(Long.parseLong(txtTotal.getText().toString()));
            xuLiCartLogic=new XuLiCartLogic(GioHang_activity.this);
            xuLiCartLogic.AddBill(bill);
        }
    }

    public static void Event() {
        int total=0;
        for(int i=0;i<Trangchu_activity.gioHangArrayList.size();i++)
        {
            total+=Trangchu_activity.gioHangArrayList.get(i).getPrice();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(total));
    }
    //kiểm tra màng cart có data k
    private void CheckData() {
        if(Trangchu_activity.gioHangArrayList.size()<=0)
        {
            adapterCart.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else{
            adapterCart.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void AnhXa() {
        listView=findViewById(R.id.lvGioHang);
        txtThongBao=findViewById(R.id.txtThongBao);
        txtTotal=findViewById(R.id.txtToTal);
        btnThanhToan=findViewById(R.id.btnThanhToan);
        btnMuaTiep=findViewById(R.id.btnMuatiep);
        toolbar=findViewById(R.id.toolbarCart);
        adapterCart=new CustomAdapterCart(GioHang_activity.this,R.layout.dong_giohang,Trangchu_activity.gioHangArrayList);
        listView.setAdapter(adapterCart);
    }

    @Override
    public void NhanThongBao(int id) {
        XuLiDetailBill xuLiDetailBill=new XuLiDetailBill(GioHang_activity.this);
            BIllDeatil bIllDeatil=new BIllDeatil();
            bIllDeatil.setBill_id(id);

            for(int i=0;i<Trangchu_activity.gioHangArrayList.size();i++)
            {
                bIllDeatil.setPro_id(Trangchu_activity.gioHangArrayList.get(i).getPro_id());
                bIllDeatil.setPrice(Trangchu_activity.gioHangArrayList.get(i).getPrice());
                bIllDeatil.setQuantity(Trangchu_activity.gioHangArrayList.get(i).getQuantity());
                bIllDeatil.setTotal( Trangchu_activity.gioHangArrayList.get(i).getPrice());
                xuLiDetailBill.AddBillDetail(bIllDeatil);
            }

            Log.d("bill_de",bIllDeatil.getPrice()+"--"+bIllDeatil.getTotal()+"--"+bIllDeatil.getPro_id());
    }

    @Override
    public void NhanThongBaoAddBillDetail(Boolean s) {

            Toast.makeText(this,R.string.themtccart,Toast.LENGTH_LONG).show();
            Trangchu_activity.gioHangArrayList.clear();

    }


}
