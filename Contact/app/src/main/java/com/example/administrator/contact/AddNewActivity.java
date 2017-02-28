package com.example.administrator.contact;
/*
时间：2016/7/24
author:李诗毅
显示图像选择框
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.db.DBHelper;
import com.example.sql.User;

import java.util.ArrayList;
import java.util.HashMap;

public class AddNewActivity extends Activity {
    GridView gv_buttom_menu;
    ListView lv_userList;
    SimpleAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_add_new);
        loadUserList();
    }


    private void loadUserList() {
        lv_userList = (ListView) findViewById(R.id.lv_userlist);
        ArrayList data = DBHelper.getInstance(this).getUserList();
        adapter = new SimpleAdapter(this,
                data, R.layout.list_item,
                new String[]{"imageid", "name", "mobilephone"},
                new int[]{R.id.user_image, R.id.tv_showname, R.id.tv_showmobilephone});
        lv_userList.setAdapter(adapter);

        lv_userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap map = (HashMap) parent.getItemAtPosition(position);
                Intent intent = new Intent(AddNewActivity.this, DetailActivity.class);
                intent.putExtra("usermap", map);
                //dangreuestCode为3时代表请求转向页面
                startActivityForResult(intent, 3);


            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (gv_buttom_menu == null) {
                loadButtomMenu();
            }
            if (gv_buttom_menu.getVisibility() == View.GONE) {
                gv_buttom_menu.setVisibility(View.VISIBLE);
            } else {
                gv_buttom_menu.setVisibility(View.GONE);
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadButtomMenu() {
        gv_buttom_menu = (GridView) this.findViewById(R.id.gv_button_menu);
        gv_buttom_menu.setBackgroundResource(R.drawable.aaa);
        gv_buttom_menu.setNumColumns(5);
        gv_buttom_menu.setGravity(Gravity.CENTER);
        gv_buttom_menu.setVerticalSpacing(10);
        gv_buttom_menu.setHorizontalSpacing(10);

        ArrayList data = new ArrayList();

        HashMap map = new HashMap();
        map.put("itemImage", R.drawable.ls1);
        map.put("itemText", "增加");
        data.add(map);

        map = new HashMap();
        map.put("itemImage", R.drawable.ls2);
        map.put("itemText", "查找");
        data.add(map);

        map = new HashMap();
        map.put("itemImage", R.drawable.ls3);
        map.put("itemText", "删除");
        data.add(map);


        map = new HashMap();
        map.put("itemImage", R.drawable.ls4);
        map.put("itemText", "菜单");
        data.add(map);

        map = new HashMap();
        map.put("itemImage", R.drawable.ls5);
        map.put("itemText", "退出");
        data.add(map);


        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_menu, new String[]{"itemImage", "itemText"}, new int[]{R.id.item_image, R.id.item_text});
        gv_buttom_menu.setAdapter(adapter);

        gv_buttom_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        /*Intent intent = new Intent(AddNewActivity.this, MainActivity.class);
                        //0代表是请求跳转到添加界面
                        startActivity(intent);*/
                        break;
                    }
                    case 1: {
                        break;
                    }

                    case 2: {
                        break;
                    }
                    case 3: {
                        break;
                    }
                    case 4: {
                        break;
                    }
                }


            }
        });
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data) {
        if (requesCode == 0) {
            if (resultCode == 1) {
                //增加用户成功 进行刷新
                ArrayList userdata = DBHelper.getInstance(this).getUserList();
                adapter = new SimpleAdapter(this,
                        userdata, R.layout.list_item,
                        new String[]{"imageid", "name", "mobilephone"},
                        new int[]{R.id.user_image, R.id.tv_showname, R.id.tv_showmobilephone});
                lv_userList.setAdapter(adapter);

            } else if (resultCode == 2) {
                //失败，不刷新

            }
        }
        super.onActivityResult(requesCode, resultCode, data);
    }
}
