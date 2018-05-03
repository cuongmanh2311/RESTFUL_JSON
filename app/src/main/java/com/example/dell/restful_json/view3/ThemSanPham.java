package com.example.dell.restful_json.view3;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.restful_json.Model.OOP.DanhMuc;
import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Dell on 3/12/2018.
 */

public class ThemSanPham extends AppCompatActivity {
    EditText edtten,edtDonGia;
    Spinner spinner;
    ArrayAdapter<DanhMuc>arrayAdapter;

    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsanpham_layout);
        anhxa();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiThemSanPham();
            }
        });

    }

    private void XuLiThemSanPham() {
        ThongTin t=new ThongTin();
        DanhMuc d= (DanhMuc) spinner.getSelectedItem();
        t.setMadm(d.getMadm());
        t.setTen(edtten.getText().toString());
        t.setDongia(Integer.parseInt(edtDonGia.getText().toString()));
        SaveSP s=new SaveSP();
        s.execute(t);
    }

    private void anhxa() {
        edtten=findViewById(R.id.edtAddTenSp);
        edtDonGia=findViewById(R.id.edtAddDonGia);
        btn=findViewById(R.id.btnAdd);
        spinner=findViewById(R.id.spinner);

        arrayAdapter=new ArrayAdapter<DanhMuc>(ThemSanPham.this,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        GetCategory getCategory=new GetCategory();
        getCategory.execute();

    }
    class GetCategory extends AsyncTask<String,Void,ArrayList<DanhMuc>>
    {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<DanhMuc> doInBackground(String... strings) {
            ArrayList<DanhMuc>ds=new ArrayList<>();
            try {
                URL url = new URL("http://192.168.174.2/qlspserver/api/category");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONArray jsonArray = new JSONArray(builder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DanhMuc t = new DanhMuc();
                    t.setMadm(jsonObject.getInt("category_id"));
                    t.setTendm(jsonObject.getString("category_name"));
                    ds.add(t);
                }

            }catch (Exception e)
            {

            }
            return ds;
        }
        @Override
        protected void onPostExecute(ArrayList<DanhMuc> danhMucs) {
            super.onPostExecute(danhMucs);
            arrayAdapter.clear();
            arrayAdapter.addAll(danhMucs);
        }
    }
    class SaveSP extends AsyncTask<ThongTin,Void,Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue()==true)
            {
                Toast.makeText(ThemSanPham.this,"Thêm sản phẩm thành công",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(ThemSanPham.this,"Thêm sản phẩm thất bại",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(ThongTin... thongTins) {
            try{
                ThongTin t=thongTins[0];
                URL url=new URL("http://192.168.174.2/qlspserver/api/product/?category_id="
                        +t.getMadm()+"&product_name="+ URLEncoder.encode(t.getTen())+"&price="+t.getDongia());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                boolean kq=builder.toString().contains("true");
                return true;
            }catch(Exception e)
            {


            }
            return null;
        }
    }
}
