package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.view.PinchImageView;

import java.util.ArrayList;

public class ImageDetailsActivity extends AppCompatActivity {

    private PinchImageView banner;
    private ArrayList<String> images;
    private int position;
    private ImageView back,go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Intent intent = getIntent();
        images = intent.getStringArrayListExtra("images");
        position = intent.getIntExtra("position", 0);
        Log.e( "onCreate: ", images.size()+":"+position);
        banner = (PinchImageView) findViewById(R.id.banner_image);
        back = (ImageView) findViewById(R.id.back);
        go = (ImageView) findViewById(R.id.go);
        if (images.size()==1) {
            go.setVisibility(View.GONE);
            back.setVisibility(View.INVISIBLE);
        }
        Picasso.with(this).load(images.get(position)).into(banner);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position += 1;
                if (position >= images.size()) {
                    position = 0;
                }
                Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(banner);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position -= 1;
                if (position < 0) {
                    position = images.size() - 1;
                }
                Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(banner);
            }
        });
        /*banner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(mCurPosY - mPosY)<4 ) {
                            if (mCurPosX - mPosX > 0
                                    && (Math.abs(mCurPosX - mPosX) > 25)) {
                                //向右滑動
                                Log.e("onTouch: ", "向右滑動");
                                position += 1;
                                if (position >= images.size()) {
                                    position = 0;
                                }
                                Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(banner);
                            } else if (mCurPosX - mPosX < 0
                                    && (Math.abs(mCurPosX - mPosX) > 25)) {
                                //向左滑动
                                Log.e("onTouch: ", "向左滑动");
                                position -= 1;
                                if (position < 0) {
                                    position = images.size() - 1;
                                }
                                Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(banner);
                            }
                        }
                        break;
                }
                return true;
            }
        });*/
    }
}
