package com.example.dell.restful_json.View2.ChiTietSanPham;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.GioHang;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.Presenter2.XuLiHienThiChiTietSanPham.XuLiDisplayCTSPPresenterlogic;
import com.example.dell.restful_json.Presenter2.XuLiThueSach.XuLiThueSachPresenterLogic;
import com.example.dell.restful_json.Presenter2.XuLiThueSach.XuLiTrangThaiSachPresenterlogic;
import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View2.HienThiMenuLoaiSP.Trangchu_activity;
import com.example.dell.restful_json.View2.Login.Login_activity;
import com.example.dell.restful_json.View2.ThueSach.GioHang_activity;
import com.example.dell.restful_json.View2.ThueSach.ThongBaoKetQuaThue;
import com.example.dell.restful_json.View2.ThueSach.TrangThaiSachImp;
import com.example.dell.restful_json.view3.MainActivity;
import com.example.dell.restful_json.view3.SanPhamTheoDanhMuc;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Dell on 4/11/2018.
 */

public class ChiTietSanPhamActivity extends AppCompatActivity implements ChiTietSanPhamImp2 ,TrangThaiSachImp,ThongBaoKetQuaThue{
    TextView txtTen,txtGia,txtTT;
    ImageView imageView,imgXemCT;
    int masp;
    boolean check=false;
    Button btnThue,btnCheckSP;
    ImageButton btnMua;
    Spinner spinner;
    XuLiDisplayCTSPPresenterlogic xuLiDisplayCTSPPresenterlogic;
    int user_id;
    int pro_id;
    String img;
    int status;
    int giatien;
    String name;
    XuLiTrangThaiSachPresenterlogic xuLiTrangThaiSachPresenterlogic;
    Product productlist;
    Toolbar toolbar;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        txtTen=findViewById(R.id.txtTenSanPhamCT);
        txtGia=findViewById(R.id.txtGiaTienSPCT);
        imageView=findViewById(R.id.viewpagerSlider);
        imgXemCT=findViewById(R.id.imXemThemChiTiet);
        txtTT=findViewById(R.id.txtThongTinChiTiet);
        btnThue=findViewById(R.id.btnThueSach);
        btnMua=findViewById(R.id.imThemGioHang);
        btnCheckSP=findViewById(R.id.btnCheckSP);
        spinner=findViewById(R.id.spinner);
        toolbar=findViewById(R.id.toolbar);
        Intent intent=getIntent();
        masp=intent.getIntExtra("masp",0);
        Log.d("masp1",masp+"");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle(name);

        xuLiDisplayCTSPPresenterlogic=new XuLiDisplayCTSPPresenterlogic(ChiTietSanPhamActivity.this);
        xuLiDisplayCTSPPresenterlogic.GetChiTietSanPham(masp);

        SharedPreferences sharedPreferences=this.getSharedPreferences("tenlogin", Context.MODE_PRIVATE);
        String user_name=sharedPreferences.getString("name2","");
         user_id=sharedPreferences.getInt("id2",0);
        Log.d("cuong4",user_name+" "+user_id);


        SuKienSpinner();
        btnCheckSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiKiemTraTrangThai();
            }
        });
        btnThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiThueSach();
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiThemGioHang();
            }
        });


    }

    private void SuKienSpinner() {
        Integer[]soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void XuLiThemGioHang() {
       if(Trangchu_activity.gioHangArrayList.size()>0)
       {
            int sl=Integer.parseInt(spinner.getSelectedItem().toString());
            boolean exists=false;
            for(int i=0;i<Trangchu_activity.gioHangArrayList.size();i++)
            {
                if(Trangchu_activity.gioHangArrayList.get(i).getPro_id()==pro_id)
                {
                    Trangchu_activity.gioHangArrayList.get(i).setQuantity(Trangchu_activity.gioHangArrayList.get(i).getQuantity()+sl);
                    if(Trangchu_activity.gioHangArrayList.get(i).getQuantity()>=10)
                    {
                        Trangchu_activity.gioHangArrayList.get(i).setQuantity(10);
                    }
                    Trangchu_activity.gioHangArrayList.get(i).setPrice(giatien*Trangchu_activity.gioHangArrayList.get(i).getQuantity());
                    exists=true;
                }
            }
            //nếu không trùng sp trong cart
            if(exists==false)
            {
                int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                int giamoi=soluong*giatien;
                Trangchu_activity.gioHangArrayList.add(new GioHang(pro_id,name,giamoi,img,soluong));
            }

       }else{
           int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
           int giamoi=soluong*giatien;
           Trangchu_activity.gioHangArrayList.add(new GioHang(pro_id,name,giamoi,img,soluong));
       }
       Intent in=new Intent(ChiTietSanPhamActivity.this,GioHang_activity.class);
       in.putExtra("user_id",user_id);
       startActivity(in);

    }

    //Kiểm tra tình trạng sách đã mượn --hàm này k cần thiết
    private void XuLiKiemTraTrangThai() {
            xuLiTrangThaiSachPresenterlogic=new XuLiTrangThaiSachPresenterlogic(ChiTietSanPhamActivity.this);
            xuLiTrangThaiSachPresenterlogic.KiemTraTrangThaiSach(user_id,pro_id);
             Log.d("pro_id",pro_id+"");
    }

    private void XuLiThueSach() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ChiTietSanPhamActivity.this);
        builder.setTitle(R.string.thongbaothue);
        builder.setMessage(R.string.xacnhanthue);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                XuLiThueSachPresenterLogic  x=new XuLiThueSachPresenterLogic(ChiTietSanPhamActivity.this);
                x.KiemTraThueSach(user_id,pro_id);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();

    }
    @Override
    public void ThongTinChiTiet(final Product product) {
        Log.d("hinh2",product.getImage());
         pro_id=product.getId();
         productlist=product;
         txtTen.setText(product.getName());
         name=product.getName();
         giatien = product.getPrice();
         status=product.getStatus();
         img=product.getImage();

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGia.setText(gia+" VNĐ");
        txtTT.setText(product.getDescription());


        if(product.getDescription().length()<50)
        {
            imgXemCT.setVisibility(View.GONE);
        }else{
            imgXemCT.setVisibility(View.VISIBLE);
            imgXemCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check=!check;
                    if(check)
                    {
                        //sau khi mo ra

                        imgXemCT.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                    }else{
                        //sau khi dong lai

                        imgXemCT.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    }
                }
            });
        }
        Picasso.with(ChiTietSanPhamActivity.this).load(product.getImage()).into(imageView);

    }

    //Kiểm tra tình trạng sách đã mượn --hàm này k cần thiết
    @Override
    public void ThongBaoTinhTrangMuon(String s) {
        Log.d("hh",s);
        if(s.equals("fail"))
        {
            Toast.makeText(ChiTietSanPhamActivity.this, R.string.sachchuatra,Toast.LENGTH_SHORT).show();

        } else if(s.equals("ok")){
            btnThue.setVisibility(View.VISIBLE);
            Toast.makeText(ChiTietSanPhamActivity.this, R.string.sachduocmuon,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void ThongBao(String s1) {
        if(s1.equals("tmh"))
        {
            btnThue.setVisibility(View.VISIBLE);
            Toast.makeText(ChiTietSanPhamActivity.this,R.string.sachduocmuon,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void ThongBaoKetQuaThueSach(Boolean s) {
        if(s.booleanValue()==true)
        {
            Toast.makeText(ChiTietSanPhamActivity.this,R.string.thuethanhcong,Toast.LENGTH_SHORT).show();
            btnThue.setVisibility(View.GONE);
        }else{
            Toast.makeText(ChiTietSanPhamActivity.this,R.string.thuethatbai,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id2=item.getItemId();
        switch (id2)
        {
            case R.id.itemCart:
                Intent iCart=new Intent(ChiTietSanPhamActivity.this, GioHang_activity.class);
                startActivity(iCart);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
