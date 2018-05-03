package com.example.dell.restful_json.View.GetAllProduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.Presenter.GetAllProduct.ChiTietSPPresenter.DetailSPPresenterLogic;
import com.example.dell.restful_json.R;

/**
 * Created by Dell on 3/12/2018.
 */

public class ChiTietSanPhamView extends AppCompatActivity implements ChiTietSanPhamImp,View.OnClickListener {
    EditText ma_nhap,edtMasp,edtTen,edtGia;
    Button btnKq;
    DetailSPPresenterLogic detailSPPresenterLogic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_sanpham);
        anhxa();
        btnKq.setOnClickListener(this);

    }

    private void anhxa() {
        ma_nhap=findViewById(R.id.edtMaSpNhap);
        edtMasp=findViewById(R.id.edtMaSp);
        edtTen=findViewById(R.id.edtTenSp);
        edtGia=findViewById(R.id.edtGiaSp);
        btnKq=findViewById(R.id.btnKq);
    }



    @Override
    public void onClick(View view) {
        String masp = ma_nhap.getText().toString();
        if(masp==null||masp.equals(""))
        {
            Toast.makeText(ChiTietSanPhamView.this,"Mã sp không rỗng",Toast.LENGTH_LONG).show();
        }
            DetailSPPresenterLogic d = new DetailSPPresenterLogic(ChiTietSanPhamView.this);
            d.XuLiData(masp);


    }
    @Override
    public void GetDetailProduct(ThongTin thongTin) {

            edtMasp.setText(String.valueOf(thongTin.getMa()));
            edtTen.setText(thongTin.getTen());
            edtGia.setText(String.valueOf(thongTin.getDongia()));

    }

    @Override
    public void NhanKetQuaThatBai(String s) {
        Toast.makeText(ChiTietSanPhamView.this,s,Toast.LENGTH_LONG).show();
    }


}
