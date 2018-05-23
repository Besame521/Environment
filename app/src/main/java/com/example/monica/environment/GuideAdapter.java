package com.example.monica.environment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class GuideAdapter extends PagerAdapter {
    private List<View> viewList;
    public GuideAdapter(List<View> viewList){
        this.viewList = viewList;
    }
    /**
     * 获取当前要显示对象的数量
     */
    @Override
    public int getCount() {
        if(viewList != null) {
            return viewList.size();
        }
        return 0;
    }
    /**
     * 判断是否用对象生成界面
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    /**
     * 从ViewGroup中移除当前对象（图片）
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    /**
     * 当前要显示的（对象）图片
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
}

