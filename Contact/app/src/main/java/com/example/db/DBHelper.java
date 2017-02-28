package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Environment;
import android.widget.ListView;

import com.example.sql.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/7/25.
 * anthor:李诗毅
 *  功能：实验数据库的存储
 */
//初始化SQLiteDataBase
public class DBHelper extends SQLiteOpenHelper{
    public final static  String DB_NAME="contact";

    public final static int VERSION=1;

    private static DBHelper instance=null;

    private SQLiteDatabase db;

    public static DBHelper getInstance(Context context) {
        if(instance==null){
            instance=new DBHelper(context);

        }
        return instance;
    }
    private void openDatabase(){
        if(db==null){
            db=this.getWritableDatabase();
        }
    }


    public DBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer tablecreate=new StringBuffer();
        tablecreate.append("create table user (_id integer primary key autoincrement,")
                .append("name text,")
                .append("mobilephone text,")
                .append("phone text,")
                .append("work text,")
                .append("position text,")
                .append("email text,")
                .append("weixin text,")
                .append("qq text,")
                .append("remark text,")
                .append("imageid int )");

        db.execSQL(tablecreate.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists user";
        db.execSQL(sql);
        onCreate(db);

    }
    public long save(User user){
        openDatabase();
        ContentValues value=new ContentValues();
        value.put("name",user.name);
        value.put("mobilephone",user.mobilePhone);
        value.put("phone",user.phone);
        value.put("work",user.work);
        value.put("position",user.position);
        value.put("email",user.email);
        value.put("weixin",user.weixin);
        value.put("qq",user.qq);
        value.put("remark",user.remark);
        value.put("imageid",user.imageId);

       return db.insert("user",null,value);


    }
    public ArrayList getUserList(){
        openDatabase();
        Cursor cursor=db.query("user",null,null,null,null,null,null);
        ArrayList list=new ArrayList();
        while (cursor.moveToNext()){
            HashMap map=new HashMap();
            map.put("_id",cursor.getInt(cursor.getColumnIndex("_id")));
            map.put("phone",cursor.getInt(cursor.getColumnIndex("phone")));
            map.put("work",cursor.getInt(cursor.getColumnIndex("work")));
            map.put("position",cursor.getInt(cursor.getColumnIndex("position")));
            map.put("email",cursor.getInt(cursor.getColumnIndex("email")));
            map.put("weixin",cursor.getInt(cursor.getColumnIndex("weixin")));
            map.put("qq",cursor.getInt(cursor.getColumnIndex("qq")));
            map.put("remark",cursor.getInt(cursor.getColumnIndex("remark")));


            map.put("imageid",cursor.getInt(cursor.getColumnIndex("imageid")));
            map.put("name",cursor.getString(cursor.getColumnIndex("name")));
            map.put("mobilephone",cursor.getString(cursor.getColumnIndex("mobilephone")));
            list.add(map);
        }
        return list;
    }
}



