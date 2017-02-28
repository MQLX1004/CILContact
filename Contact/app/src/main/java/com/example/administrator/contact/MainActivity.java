package com.example.administrator.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.db.DBHelper;
import com.example.sql.User;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/7/26.
 * author:李诗毅
 * 功能：主界面的绘制
 */
public class MainActivity extends Activity {
    ImageButton btn_img;
    AlertDialog imageChooseDialog;

    Gallery gallery;
    ImageSwitcher is;

    int imagePosition;
    EditText et_name;

    EditText et_mobilephone;

    EditText et_phone;

    EditText et_work;

    EditText et_position;

    EditText et_email;

    EditText et_weixin;

    EditText et_qq;

    EditText et_remark;

    Button btn_save;

    Button btn_return;

    private int[] images = {R.mipmap.ic_launcher, R.drawable.image1,
            R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image5, R.drawable.image6, R.drawable.image7,
            R.drawable.image8, R.drawable.image9};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);
        initWidget();
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                if (name.equals("")) {

                    Toast.makeText(MainActivity.this, "姓名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                String mobilePhone = et_mobilephone.getText().toString();
                String phone = et_phone.getText().toString();
                String email = et_email.getText().toString();
                String work = et_work.getText().toString();
                String qq = et_qq.getText().toString();
                String weixin = et_weixin.getText().toString();
                String position = et_position.getText().toString();
                String remark = et_remark.getText().toString();
                int imageId = images[imagePosition];

                User user = new User();
                user.mobilePhone = mobilePhone;
                user.phone = phone;
                user.work = work;
                user.position = position;
                user.email = email;
                user.weixin = weixin;
                user.qq = qq;
                user.name = name;
                user.remark = remark;
                user.imageId = imageId;

                long success = DBHelper.getInstance(MainActivity.this).save(user);
                if (success != -1) {
                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                    setResult(1);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "添加错误，请重新操作!", Toast.LENGTH_LONG).show();
                    setResult(2);
                    finish();
                }
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_img = (ImageButton) findViewById(R.id.btn_img);

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initImageChooseDialog();
                imageChooseDialog.show();
            }


        });


    }

    public void initWidget() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_mobilephone = (EditText) findViewById(R.id.et_mobilephone);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_work = (EditText) findViewById(R.id.et_work);
        et_position = (EditText) findViewById(R.id.et_position);
        et_email = (EditText) findViewById(R.id.et_email);
        et_weixin = (EditText) findViewById(R.id.et_weixin);
        et_qq = (EditText) findViewById(R.id.et_QQ);
        et_remark = (EditText) findViewById(R.id.et_remark);

        btn_img = (ImageButton) findViewById(R.id.btn_img);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_return = (Button) findViewById(R.id.btn_return);
    }

    private void initImageChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择头像");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                btn_img.setImageResource(images[imagePosition % images.length]);

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.image, null);
        gallery = (Gallery) findViewById(R.id.img_gallery);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setSelection(images.length / 2);
        is = (ImageSwitcher) findViewById(R.id.image_switcher);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imagePosition = position;
                is.setImageResource(images[position % images.length]);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        is.setFactory(new MyViewFactory(this));
        builder.setView(view);
        imageChooseDialog = builder.create();

    }

    class ImageAdapter extends BaseAdapter {

        private Context context;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(images[position % images.length]);
            iv.setAdjustViewBounds(true);
            iv.setLayoutParams(new Gallery.LayoutParams(80, 80));
            iv.setPadding(15, 10, 15, 10);
            return iv;

        }
    }

    class MyViewFactory implements ViewSwitcher.ViewFactory {

        private Context context;

        public MyViewFactory(Context context) {
            this.context = context;
        }

        @Override
        public View makeView() {
            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new ImageSwitcher.LayoutParams(90, 90));
            return iv;
        }
    }

}