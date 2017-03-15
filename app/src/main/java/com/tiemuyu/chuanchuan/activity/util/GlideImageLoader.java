package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 梁文硕 on 2017/2/17.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Log.e("图片的路径",(String) path);
        Picasso.with(context).load((String) path).into(imageView);
    }
}
