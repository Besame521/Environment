package com.example.monica.environment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GuidePage extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager vp;
    private List<View> viewList;
    private ViewGroup vg;//放置圆点
    //实例化图片资源
    private int[] imageIdArray = new int[]{
            R.drawable.guide_1,R.drawable.guide_2,
            R.drawable.guide_3,R.drawable.guide_4
    };
    //实例化原点View
    private ImageView iv_point;
    private ImageView[] ivPointArray;
    //最后一页的按钮
    private TextView ib_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid_page);

        ib_start = (TextView)findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuidePage.this,MainActivity.class));
                finish();
            }
        });
        //加载ViewPager
        initViewPager();
        //加载底部原点
        initPoint();
    }
    private void initPoint(){
        //实例化LinearLayout
        vg  = (ViewGroup)findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray  = new ImageView[viewList.size()];
        //循环底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for(int i = 0; i <size; i++){
            iv_point = new ImageView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(30,30);
            params.setMarginEnd(40);
            iv_point.setLayoutParams(params);
            ivPointArray[i] = iv_point;
            if(i == 0){
                iv_point.setBackgroundResource(R.drawable.full_holo);
            }else{
                iv_point.setBackgroundResource(R.drawable.empty_holo);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }
    /**
     * 加载图片ViewPager
     */
    private void initViewPager(){
        vp  = (ViewPager)findViewById(R.id.guide_vp);
        viewList  = new ArrayList<View>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
        );
        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for(int i = 0; i < len; i++){
            //new ImageView并设置全屏图片和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);
            //将ImageView加入到集合
            viewList.add(imageView);
        }
        //设置Adapter
        vp.setAdapter(new GuideAdapter(viewList));
        //设置滑动监听
        vp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    /**
     * 滑动后的监听
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for(int i = 0; i < length; i++){
            ivPointArray[i].setBackgroundResource(R.drawable.empty_holo );
        }
            ivPointArray[position].setBackgroundResource(R.drawable.full_holo );
        //判断是否是最后一页，若是则显示按钮
        if(position == imageIdArray.length - 1){
            ib_start.setVisibility(View.VISIBLE);
        }else{
            ib_start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
