package com.tiemuyu.chuanchuan.activity.util;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 梁文硕 on 2017/3/27.
 */

public class ImageLoarderDetail extends ImageLoader {
    private ImageView imageView;
    private int widthPixels;
    private int heightPixels;

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Log.e("图片的路径",(String) path);
        this.imageView = imageView;
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;
        Picasso.with(context).load((String) path).transform(transformation).into(imageView);

    }
    Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {

            int mTargetWidth = widthPixels;
            int mTargetHeight = heightPixels;


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
                double aspectRatio = (double) source.getWidth()/ (double) source.getHeight();
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
