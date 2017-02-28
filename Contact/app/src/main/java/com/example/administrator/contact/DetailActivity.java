package com.example.administrator.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.db.DBHelper;
import com.example.sql.User;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/7/28.
 * author:李诗毅
 * 功能：查看联系人详细信息，并做修改
 */
public class DetailActivity extends Activity{
    ImageButton btn_img;

    EditText et_name;

    EditText et_mobilephone;

    EditText et_phone;

    EditText et_work;

    EditText et_position;

    EditText et_email;

    EditText et_weixin;

    EditText et_qq;

    EditText et_remark;

    HashMap map;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent=getIntent();
        map=(HashMap)intent.getSerializableExtra("usermap");
        initWidget();

        DisplayData();

    }
    public void initWidget(){
        et_name=(EditText)findViewById(R.id.et_name);
        et_mobilephone=(EditText)findViewById(R.id.et_mobilephone);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_work=(EditText)findViewById(R.id.et_work);
        et_position=(EditText)findViewById(R.id.et_position);
        et_email=(EditText)findViewById(R.id.et_email);
        et_weixin=(EditText)findViewById(R.id.et_weixin);
        et_qq=(EditText)findViewById(R.id.et_QQ);
        et_remark=(EditText)findViewById(R.id.et_remark);

        btn_img= (ImageButton) findViewById(R.id.btn_img);
    }
    private void DisplayData(){
        et_name.setText(String.valueOf(map.get("name")));
        et_mobilephone.setText(String.valueOf(map.get("mobilephone")));
        et_phone.setText(String.valueOf(map.get("phone")));
        et_work.setText(String.valueOf(map.get("work")));
        et_position.setText(String.valueOf(map.get("position")));
        et_email.setText(String.valueOf(map.get("email")));
        et_weixin.setText(String.valueOf(map.get("weixin")));
        et_qq.setText(String.valueOf(map.get("qq")));
        et_remark.setText(String.valueOf(map.get("remark")));
        btn_img.setImageResource(Integer.parseInt(String.valueOf(map.get("imageid"))));

    }

}
