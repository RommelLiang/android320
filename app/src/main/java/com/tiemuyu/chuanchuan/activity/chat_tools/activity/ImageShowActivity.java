package com.tiemuyu.chuanchuan.activity.chat_tools.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.R;

/**
 * describe :
 * Created by FUYING on 2016/6/8.
 * PackageName com.guanjia800.clientApp.chat_tools.activity.
 * ProjectName HouseKeeper_android.
 */
public class ImageShowActivity extends Activity {

    private View rootView;
    ImageView imageView;
    TextView textView;
    ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_image_big);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        final int type = intent.getIntExtra("type",0);
        final String uri = intent.getStringExtra("Uri");
        imageView = (ImageView) findViewById(R.id.image_show);
        textView = (TextView) findViewById(R.id.preview_Original);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.out.println("@!@!==Url====="+uri);
                    //imageLoader.displayImage(uri, imageView);
                    imageLoader.displayImage(uri, imageView);
            }
        });
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        imageView.setImageBitmap(bitmap);
    }
}