package com.tiemuyu.chuanchuan.activity.util;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.R;

import java.io.File;

/**
 * Created by 梁文硕 on 2017/3/16.
 */

public class PicassoImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Log.e("displayImage: ",path );
        Picasso.with(activity)//
                .load(new File(path))//
                .resize(width, height)//
                .centerInside()//
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)//
                .into(imageView);
    }
    public void displayImage(Activity activity, String path, ImageView imageView) {
        Picasso.with(activity)//
                .load(new File(path))//
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)//
                .error(R.drawable.home_d)
                .into(imageView);
    }
    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
