package com.example.monica.environment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class WelcomePage extends AppCompatActivity {
    private int[] images = new int[]{
            R.drawable.welcome_1,R.drawable.welcome_2,
            R.drawable.welcome_3,R.drawable.welcome_4
    };
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        imageView = (ImageView)findViewById(R.id.welcomeImage);
        int image = (int)(Math.random()*3);
        imageView.setImageResource(images[image]);
        showSplash();
        isOK();
    }
    class Splashhandler implements Runnable {
        /**
         * 获取SharedPreferenced对象
         * 第一个参数是生成xml的文件名
         * 第二个参数是存储的格式
         */
        SharedPreferences preferences = getSharedPreferences("SomeInformation", Context.MODE_PRIVATE);

        public void run() {
            Intent intent;
            int count = preferences.getInt("count", 0); // 取出数据
            if (count == 0) {
                //获取到edit对象
                SharedPreferences.Editor edit = preferences.edit();
                // 通过editor对象写入数据
                edit.putInt("count", 1);
                //提交数据存入到xml文件中
                edit.commit();
                intent = new Intent(WelcomePage.this, GuidePage.class);
                startActivity(intent);
                finish();
            } else {
                intent = new Intent(WelcomePage.this, GuidePage.class);
                startActivity(intent);
                finish();
            }
        }
    }
    public void isOK(){
            if (Build.VERSION.SDK_INT>=23){
                String[] mPermissionList = new String[]{
                        INTERNET,WRITE_EXTERNAL_STORAGE,ACCESS_NETWORK_STATE,ACCESS_WIFI_STATE,READ_PHONE_STATE,ACCESS_COARSE_LOCATION
                };
                    ActivityCompat.requestPermissions(WelcomePage.this, mPermissionList,123);
            }
    }

    private Splashhandler splashhandler;
    private Handler handler;

    public void showSplash(){
        handler = new Handler();
        splashhandler = new Splashhandler();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(handler!=null&&splashhandler!=null){
            handler.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(handler!=null&&splashhandler!=null){
            handler.postDelayed(splashhandler, 3000);
        }
    }
}
