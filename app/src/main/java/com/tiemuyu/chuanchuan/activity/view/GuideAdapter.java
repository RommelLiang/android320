package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * describe :引导页的适配器
 * Created by Benny on 2016/5/9.
 * PackageName com.mlz.mall.win.act.main.
 * ProjectName mlzMall.
 */
public  class GuideAdapter extends PagerAdapter {
    private final List<ImageView> imageList;

    public GuideAdapter(Context context, List<ImageView> imageList) {
        this.imageList=imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = imageList.get(position);
        // 1. 向ViewPager中添加一个view对象
        container.addView(iv);

        // 2. 返回当前添加的view对象
        return iv;
    }
}