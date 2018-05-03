package com.example.dell.restful_json.Model.Cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 4/23/2018.
 */

public class DBProduct extends SQLiteOpenHelper {
    public static String TB_GIOHANG="GIOHANG";
    public static String TB_MASP="PRO_ID";
    public static String TB_TENSP="PRO_NAME";
    public static String TB_GIA="PRICE";
    public static String TB_IMAGE="IMAGE";


    public DBProduct(Context context) {
        super(context, "DBProduct", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String dbGioHang=" CREATE TABLE " + TB_GIOHANG + " ( " + TB_MASP + "INTEGER PRIMARY KEY ,"
                    + TB_TENSP + " TEXT, " + TB_GIA + " REAL,"  + TB_IMAGE + " BLOB ); ";
            sqLiteDatabase.execSQL(dbGioHang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
