package com.example.dell.restful_json.view3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dell.restful_json.R;
import com.example.dell.restful_json.View.GetAllProduct.DanhSachSanPhamView;
import com.example.dell.restful_json.View.GetAllProduct.ChiTietSanPhamView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button btnAllSP,btnDetailSP,btnLuuSP,btnSPDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        btnAllSP.setOnClickListener(this);
        btnDetailSP.setOnClickListener(this);
        btnLuuSP.setOnClickListener(this);
        btnSPDM.setOnClickListener(this);
    }

    private void anhxa() {
        btnAllSP=findViewById(R.id.btnToanBoSP);
        btnDetailSP=findViewById(R.id.btnDetailSp);
        btnLuuSP=findViewById(R.id.btnLuuSP);
        btnSPDM=findViewById(R.id.btnSPDM);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.btnToanBoSP:
                Intent intent=new Intent(MainActivity.this, DanhSachSanPhamView.class);
                startActivity(intent);
                break;
            case R.id.btnDetailSp:
                Intent intent1=new Intent(MainActivity.this, ChiTietSanPhamView.class);
                startActivity(intent1);
                break;
            case R.id.btnLuuSP:
                Intent intent3=new Intent(MainActivity.this, ThemSanPham.class);
                startActivity(intent3);
                break;
            case R.id.btnSPDM:
                Intent intent4=new Intent(MainActivity.this, SanPhamTheoDanhMuc.class);
                startActivity(intent4);
                break;

        }


    }
}

