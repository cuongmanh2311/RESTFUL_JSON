package com.example.dell.restful_json.view3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Dell on 3/13/2018.
 */

public class SuaSanPham extends AppCompatActivity {
    EditText edtSuaTen,edtSuaGia,edtMaSp;
    Button btnLuu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suasanpham_layout);
        anhxa();
        Intent intent=getIntent();
        ThongTin t= (ThongTin) intent.getSerializableExtra("sanpham");
        edtMaSp.setText(String.valueOf(t.getMa()));
        edtMaSp.setEnabled(false);
        edtSuaTen.setText(t.getTen());
        edtSuaGia.setText(String.valueOf(t.getDongia()));
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuliLuu();
            }
        });
    }

    private void XuliLuu() {

        ThongTin thongTin=new ThongTin();
        thongTin.setMa(Integer.parseInt(edtMaSp.getText().toString()));
        thongTin.setTen(edtSuaTen.getText().toString());
        thongTin.setDongia(Integer.parseInt(edtSuaGia.getText().toString()));
        UpdateSanPham u=new UpdateSanPham();
        u.execute(thongTin);
    }

    private void anhxa() {
        edtSuaTen=findViewById(R.id.edtSuaTenSanPham);
        edtMaSp=findViewById(R.id.edtSuaMaSP);
        edtSuaGia=findViewById(R.id.edtSuaDonGia);
        btnLuu=findViewById(R.id.btnSua);
    }
    class UpdateSanPham extends AsyncTask<ThongTin,Void,Boolean>
    {

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue()==true)
            {
                /*Intent intent=new Intent();
                intent.putExtra("kt",aBoolean);
                setResult(999,intent);*/
                Toast.makeText(SuaSanPham.this,"Update thành công",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(SuaSanPham.this,"Update thành công",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(ThongTin... thongTins) {
            try {
                ThongTin t = thongTins[0];
                URL url = new URL("http://192.168.174.2/qlspserver/api/product/?product_id="
                        + t.getMa() + "&product_name=" + URLEncoder.encode(t.getTen()) + "&price=" + t.getDongia());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("PUT");
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                boolean kq = builder.toString().contains("true");
                return true;
            }catch (Exception e){}
            return null;
        }
    }
}
