package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.tiemuyu.chuanchuan.activity.util.ImageLoarderDetail;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class ImageDetailsActivity extends AppCompatActivity {

    private Banner banner;
    private RelativeLayout relativeLayout;
    private ArrayList<String> images;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Intent intent = getIntent();
        images = intent.getStringArrayListExtra("images");
        position = intent.getIntExtra("position", 0);
        banner = (Banner) findViewById(R.id.banner_image);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_image_details);
        banner.setImages(images).setImageLoader(new ImageLoarderDetail()).start();
    }
}
