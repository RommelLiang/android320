package com.tiemuyu.chuanchuan.activity.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by SHEN on 2015/10/29.
 */
public class BannerPagerAdapter extends PagerAdapter {
    private ArrayList<View> list;

    public BannerPagerAdapter(ArrayList<View> list){
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(list.get(position));
    }
}
