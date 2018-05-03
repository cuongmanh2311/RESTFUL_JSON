package com.example.dell.restful_json.view3;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.restful_json.Adapter.CustomAdapter;
import com.example.dell.restful_json.Model.OOP.ThongTin;
import com.example.dell.restful_json.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 3/13/2018.
 */

public class SanPhamTheoDanhMuc extends AppCompatActivity {
    EditText edt;
    Button btn;
    ListView listView;
    List<ThongTin>list;
    CustomAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanpham_danhmuc);
        anhxa();
        SuKien();
    }

    private void SuKien() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               XuLiXemSanPham();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ThongTin t=adapter.getItem(i);
                XuLiXoaSanPham(t);
                return false;
            }
        });
    }
    private void XuLiXemSanPham(){
        String madm=edt.getText().toString();
        XemSPDM xxx=new XemSPDM();
        xxx.execute(madm);
    }
    private void XuLiXoaSanPham(final ThongTin t) {
        AlertDialog.Builder builder=new AlertDialog.Builder(SanPhamTheoDanhMuc.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa sản phẩm:  "+t.getTen()+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String masp=String.valueOf(t.getMa());
                XoaSP x=new XoaSP();
                x.execute(masp);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void anhxa() {
        edt=findViewById(R.id.edtSPDM);
        btn=findViewById(R.id.btnXemSPDM);
        listView=findViewById(R.id.lvSPDM);
        list=new ArrayList<>();
        adapter=new CustomAdapter(SanPhamTheoDanhMuc.this,R.layout.item_layout,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    class XemSPDM extends AsyncTask<String,Void,ArrayList<ThongTin>> {
        @Override
        protected ArrayList<ThongTin> doInBackground(String... strings) {
            ArrayList<ThongTin>ds=new ArrayList<>();
            try{
                URL url=new URL("http://192.168.174.2/qlspserver/api/product/?category_id="+strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                StringBuilder builder=new StringBuilder();
                while(line!=null)
                {
                    builder.append(line);
                    line=bufferedReader.readLine();
                }
                JSONArray jsonArray=new JSONArray(builder.toString());
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    ThongTin t=new ThongTin();
                    t.setMa(jsonObject.getInt("product_id"));
                    t.setTen(jsonObject.getString("product_name"));
                    t.setDongia(jsonObject.getInt("price"));
                    ds.add(t);
                }

            }catch (Exception e)
            {

            }
            return ds;
        }

        @Override
        protected void onPostExecute(ArrayList<ThongTin> thongTins) {
            super.onPostExecute(thongTins);
            adapter.clear();
            adapter.addAll(thongTins);
        }
    }
    class XoaSP extends  AsyncTask<String,Void,Boolean>
    {
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
                if(aBoolean.booleanValue()==true)
                {
                    XuLiXemSanPham();
                }
                else{
                    Toast.makeText(SanPhamTheoDanhMuc.this,"Xóa thất bại",Toast.LENGTH_LONG).show();
                }
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                URL url=new URL("http://192.168.174.2/qlspserver/api/product/?product_id="+strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("DELETE");
                httpURLConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                StringBuilder builder=new StringBuilder();
                while(line!=null)
                {
                    builder.append(line);
                    line=bufferedReader.readLine();
                }
                boolean kq=builder.toString().contains("true");
                return kq;

            }catch (Exception e)
            {

            }
            return null;
        }
    }
}
