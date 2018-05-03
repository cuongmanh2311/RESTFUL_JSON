package com.example.dell.restful_json.Model.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.restful_json.Model.OOP.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 4/23/2018.
 */

public class ModelCart {
    SQLiteDatabase sqLiteDatabase;
    public void OpenConnectSQL(Context context)
    {
        DBProduct dbProduct=new DBProduct(context);
        dbProduct.getWritableDatabase();
    }
    public boolean AddCart(Product product)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBProduct.TB_MASP,product.getId());
        contentValues.put(DBProduct.TB_GIA,product.getPrice());
        contentValues.put(DBProduct.TB_TENSP,product.getName());
        contentValues.put(DBProduct.TB_MASP,product.getHinhgiohang());
        long id=sqLiteDatabase.insert(DBProduct.TB_GIOHANG,null,contentValues);
        if(id>0)
        {
            return true;
        }else{
            return false;
        }

    }
    public List<Product>LayDanhSachSanPhamTrongCart(){
        List<Product>list=new ArrayList<>();
        String query="SELECT & FROM " + DBProduct.TB_GIOHANG;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int masp=cursor.getInt(cursor.getColumnIndex(DBProduct.TB_MASP));
            int price=cursor.getInt(cursor.getColumnIndex(DBProduct.TB_GIA));
            String name=cursor.getString(cursor.getColumnIndex(DBProduct.TB_TENSP));
            byte[]imnage=cursor.getBlob(cursor.getColumnIndex(DBProduct.TB_IMAGE));
            Product product=new Product();
            product.setId(masp);
            product.setPrice(price);
            product.setName(name);
            product.setHinhgiohang(imnage);
            list.add(product);
        }
        return list;
    }

}
