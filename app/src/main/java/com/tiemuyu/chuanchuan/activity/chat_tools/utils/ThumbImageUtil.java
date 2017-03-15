package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.tiemuyu.chuanchuan.activity.R;

/**
 * describe :
 * Created by FUYING on 2016/6/29.
 * PackageName com.guanjia800.clientApp.chat_tools.utils.
 * ProjectName HouseKeeper_android.
 */
public class ThumbImageUtil {

    public static void loadThumbnailImage(Context context, String thumbPath, ImageView thumbnail) {
        if (thumbPath != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeFile(thumbPath,options);
            thumbnail.setImageBitmap(bitmap);
        } else {
            thumbnail.setImageResource(R.mipmap.nim_image_default);
        }
    }

    public static String extractThumbnail(String videoPath, String thumbPath) {
        if (!isFileExist(thumbPath)) {
            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MINI_KIND);
            if (thumbnail != null) {
                saveBitmap(thumbnail, thumbPath, true);
                return thumbPath;
            }
        }
        return thumbPath;
    }

    public static boolean isFileExist(String path) {
        if (!TextUtils.isEmpty(path) && new File(path).exists()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean saveBitmap(Bitmap bitmap, String path, boolean recyle) {
        System.out.println("#######saveBitmap###"+path+"%%%%%%"+bitmap.getByteCount());
        if (bitmap == null || TextUtils.isEmpty(path)) {
            return false;
        }

        BufferedOutputStream bos = null;
        try {
            FileOutputStream fos = new FileOutputStream(path);
            bos = new BufferedOutputStream(fos);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, bos);
            return true;

        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                }
            }
            if (recyle) {
                bitmap.recycle();
            }
        }
    }
}
