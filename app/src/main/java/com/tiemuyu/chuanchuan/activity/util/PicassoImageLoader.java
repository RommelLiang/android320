package com.tiemuyu.chuanchuan.activity.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.R;

import java.io.File;

/**
 * Created by 梁文硕 on 2017/3/16.
 */

public class PicassoImageLoader implements ImageLoader {
    private ImageView mImageView;
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

    public void displayImage(Activity activity, String path, ImageView imageView,int width) {
        Log.e("displayImage: ", path);
        mImageView = imageView;
        Picasso.with(activity)//
                .load(new File(path))//
                .transform(transformation)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)//
                .into(imageView);
    }
    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
    Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {

            int mTargetWidth = mImageView.getWidth();
            int mTargetHeight = mImageView.getHeight();


            if (source.getWidth() == 0) {
                return source;
            }
            if (source.getWidth() < source.getHeight()) {
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (mTargetWidth * aspectRatio);
                if (targetHeight != 0 && mTargetWidth != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, mTargetWidth, targetHeight, false);
                    if (result != source) {
                        // Same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }
            } else {
                double aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                int targetWidth = (int) (mTargetHeight * aspectRatio);
                if (targetWidth != 0 && targetWidth != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, mTargetHeight, false);
                    if (result != source) {
                        // Same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }
            }
        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };
}
