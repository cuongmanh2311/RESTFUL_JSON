package com.example.dell.restful_json.Presenter2.XuLiHienThiChiTietSanPham;

import android.content.Context;
import android.util.Log;

import com.example.dell.restful_json.Model.Cart.ModelCart;
import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_ProductCT;
import com.example.dell.restful_json.Model.DowloadJSON.DowloadJSON_product;
import com.example.dell.restful_json.Model.LinkAPI;
import com.example.dell.restful_json.Model.OOP.Product;
import com.example.dell.restful_json.View2.ChiTietSanPham.ChiTietSanPhamImp2;
import com.example.dell.restful_json.View2.ThueSach.GioSachImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 4/11/2018.
 */

public class XuLiDisplayCTSPPresenterlogic implements XuLiDisplayCTSPImp {
    ChiTietSanPhamImp2 chiTietSanPhamImp;
    GioSachImp gioSachImp;
    ModelCart modelCart;
    public XuLiDisplayCTSPPresenterlogic (ChiTietSanPhamImp2 chiTietSanPhamImp)
    {
        this.chiTietSanPhamImp=chiTietSanPhamImp;
        modelCart=new ModelCart();
    }
        public XuLiDisplayCTSPPresenterlogic(GioSachImp gioSachImp)
       {
           this.gioSachImp=gioSachImp;
       }

     //get chi tiết ản phẩm truyển vào mã sp lấy từ trang sp theo danh mục
    @Override
    public void GetChiTietSanPham(int masp) {
        DowloadJSON_ProductCT dowloadJSON_product=new DowloadJSON_ProductCT();
        dowloadJSON_product.execute(masp);
        ArrayList<Product>products=new ArrayList<>();
        try {
            String data=dowloadJSON_product.get();
            Log.d("kt",data);
            if(data!=null)
            {
                JSONObject jsonObject=new JSONObject(data);
                Product product=new Product();
                product.setId(jsonObject.getInt("id"));
                product.setName(jsonObject.getString("name"));
                product.setPrice(jsonObject.getInt("price"));
                product.setImage(jsonObject.getString("image"));
                product.setIntro(jsonObject.getString("intro"));
                product.setDescription(jsonObject.getString("description"));
                Log.d("hinh",product.getImage());
                chiTietSanPhamImp.ThongTinChiTiet(product);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GetSanPham(List<Integer> ds) {
        ArrayList<Product>list=new ArrayList<>();
        for(int i=0;i<ds.size();i++)
        {
            DowloadJSON_ProductCT dowloadJSON_product=new DowloadJSON_ProductCT();
            dowloadJSON_product.execute(ds.get(i));
            try {
                String data=dowloadJSON_product.get();
                Log.d("kt",data);
                if(data!=null)
                {
                    JSONObject jsonObject=new JSONObject(data);
                    Product product=new Product();
                    product.setId(jsonObject.getInt("id"));
                    product.setName(jsonObject.getString("name"));
                    product.setPrice(jsonObject.getInt("price"));
                    product.setImage(jsonObject.getString("image"));
                    product.setIntro(jsonObject.getString("intro"));
                    product.setDescription(jsonObject.getString("description"));
                    product.setCreated_at(jsonObject.getString("created_at"));
                    Log.d("hinh",product.getImage());
                    list.add(product);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        gioSachImp.NhanThongTinSach(list);
    }
    //Gọi đền hàm add product vào giỏ hàng
   /* @Override
    public void AddProIntoCart(Product product, Context context) {
        modelCart.OpenConnectSQL(context);
        boolean s=modelCart.AddCart(product);
        if(s)
        {
            chiTietSanPhamImp.AddSuccess();
        }else{
            chiTietSanPhamImp.AddFaill();
        }
    }*/
}




