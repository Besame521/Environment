package com.example.monica.environment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private PopupMenu popup = null;
    private TextView main_title_menu;
    private Button seeinfo,login;
    private LinearLayout main,main2,main3,home,home2,local,local2,my,my2;
    private MapView mapView;
    private ImageView my_main_advice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载界面组件
        initView();
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        //加载地图
        initMap();
        initListener();
    }
    //加载界面组件
    public void initView(){
        main_title_menu = (TextView)findViewById(R.id.main_title_menu);//菜单
        seeinfo = (Button)findViewById(R.id.main_device_info_see);//按钮
        login = (Button)findViewById(R.id.my_login) ;
        main = (LinearLayout)findViewById(R.id.main);
        main2 = (LinearLayout)findViewById(R.id.main2);
        main3 = (LinearLayout)findViewById(R.id.main3);
        //获取底部菜单
        home = (LinearLayout)findViewById(R.id.navigation_home);
        home2 = (LinearLayout)findViewById(R.id.navigation_home2);
        local = (LinearLayout)findViewById(R.id.navigation_localtion);
        local2 = (LinearLayout)findViewById(R.id.navigation_localtion2);
        my = (LinearLayout)findViewById(R.id.navigation_my);
        my2 = (LinearLayout)findViewById(R.id.navigation_my2);
        my_main_advice = (ImageView)findViewById(R.id.my_main_advice);
        //获取地图
        mapView = (MapView)findViewById(R.id.mapView);
    }
    //加载监听器
    public void initListener(){
        main_title_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPopupButtonClick(main_title_menu);
            }
        });
        home.setOnClickListener(this);
        home2.setOnClickListener(this);
        local.setOnClickListener(this);
        local2.setOnClickListener(this);
        my.setOnClickListener(this);
        my2.setOnClickListener(this);
        login.setOnClickListener(this);
        my_main_advice.setOnClickListener(this);
    }
    //地图操作
    public void initMap(){
        AMap aMap = null;
        if(aMap == null) {
            aMap = mapView.getMap();
        }
        //显示定位蓝点
        MyLocationStyle myLocationStyle;
        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒
        myLocationStyle.interval(2000);
        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(8));
        //设置定位蓝点的style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
        //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息
        //MyLocationStyle showMyLocation(boolean visible);
        //设置定位蓝点的icon图标方法，需要用到BitmapDescriptor类对象作为参数。
        //MyLocationStyle myLocationIcon(BitmapDescriptor myLocationIcon);

    }
    public void onMyLocationChange(android.location.Location location) {
        //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取（获取地址描述数据章节有介绍）

    }
    //底部菜单项监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigation_home:
                home2.setVisibility(View.VISIBLE);
                home.setVisibility(View.GONE);
                local.setVisibility(View.VISIBLE);
                local2.setVisibility(View.GONE);
                my.setVisibility(View.VISIBLE);
                my2.setVisibility(View.GONE);
                main.setVisibility(View.VISIBLE);
                main2.setVisibility(View.GONE);
                main3.setVisibility(View.GONE);
                main_title_menu.setVisibility(View.VISIBLE);
                break;
            case R.id.navigation_localtion:
                home.setVisibility(View.VISIBLE);
                home2.setVisibility(View.GONE);
                local2.setVisibility(View.VISIBLE);
                local.setVisibility(View.GONE);
                my.setVisibility(View.VISIBLE);
                my2.setVisibility(View.GONE);
                main.setVisibility(View.GONE);
                main2.setVisibility(View.VISIBLE);
                main3.setVisibility(View.GONE);
                main_title_menu.setVisibility(View.GONE);
                break;
            case R.id.navigation_my:
                home.setVisibility(View.VISIBLE);
                home2.setVisibility(View.GONE);
                local.setVisibility(View.VISIBLE);
                local2.setVisibility(View.GONE);
                my2.setVisibility(View.VISIBLE);
                my.setVisibility(View.GONE);
                main.setVisibility(View.GONE);
                main2.setVisibility(View.GONE);
                main3.setVisibility(View.VISIBLE);
                main_title_menu.setVisibility(View.GONE);
                break;
            case R.id.my_login:
                Intent intent1 = new Intent(MainActivity.this,LoginPage.class);
                startActivity(intent1);
                break;
            case R.id.my_main_advice:
                Intent intent2 = new Intent(MainActivity.this,SuggestionPage.class);
                startActivity(intent2);
                break;
        }
    }

    public void onPopupButtonClick(View view){
        //创建PopupMenu对象
        popup = new PopupMenu(this,view);
        //加载菜单资源到popup菜单中
        getMenuInflater().inflate(R.menu.main_title_menu_item,popup.getMenu());
        //为Popup菜单项的单击事件绑定监听事件
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_title_menu_item1:
                        //隐藏该对话框
                        popup.dismiss();
                        break;
                    case R.id.main_title_menu_item2:
                        //隐藏该对话框
                        popup.dismiss();
                        break;
                    case R.id.main_title_menu_item3:
                        //隐藏该对话框
                        popup.dismiss();
                        break;
                    case R.id.main_title_menu_item4:
                        //隐藏该对话框
                        popup.dismiss();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_OK){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }
}

