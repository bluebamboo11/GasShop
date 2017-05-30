package com.example.blue.gasshop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by blue on 27/02/2017.
 */

public class Database {
    public  static final   String DATA_NAME="db_GasShop.sqlite";
    public  static final   String TAB_THONGKE="tbl_Thongke";
    public  static final   String Ten_KH="Ten_KH";
    public  static final   String Ten_SP="Ten_SP";
    public  static final   String Ngay_Mua="Ngay_Mua";
    public  static final   String Dia_Chi="Dia_Chi";
    public  static final   String  SDT="SDT";
    public  static final   String  X="X";
    public  static final   String  Y="Y";
    public  static final   String ID_SP="ID_SP";
    public  static final   String Tong_tien="Tong_tien";


    // tạo một SQLiteDatabase lấy dữ liệu từ file assets
    public static SQLiteDatabase initDatabase(Context context, String databaseName){
        try {
            String outFileName = context.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if(!f.exists()) {
                InputStream e = context.getAssets().open(databaseName);
                File folder = new File(context.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}
