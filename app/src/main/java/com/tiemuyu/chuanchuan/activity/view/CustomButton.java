package com.tiemuyu.chuanchuan.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;

/**
 * describe :首页底部的button
 * Created by Benny on 2016/5/19.
 * PackageName com.e_business.view.
 * ProjectName E_Business.
 */

public class CustomButton extends RelativeLayout {

    private ImageView imgView;
    private TextView  textView;

    public CustomButton(Context context) {
        super(context,null);
    }

    public CustomButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.custom_btn_img, this,true);

        this.imgView = (ImageView)findViewById(R.id.img_btn);
        this.textView = (TextView)findViewById(R.id.tv_btn);

        this.setClickable(true);
        this.setFocusable(true);
    }

    public void setImgResource(int resourceID) {
        this.imgView.setImageResource(resourceID);
    }
    public void setBackgroundResource(int resourceID) {
        this.imgView.setBackgroundResource(resourceID);
    }

    public void setText(String text) {
        this.textView.setText(text);
    }

    public void setTextColor(int color) {
        this.textView.setTextColor(color);
    }

    public void setTextSize(float size) {
        this.textView.setTextSize(size);
    }

}