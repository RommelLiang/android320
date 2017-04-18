package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.tiemuyu.chuanchuan.activity.ImageDetailsActivity;
import com.tiemuyu.chuanchuan.activity.util.ScreenUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * describe :
 * Created by FUYING on 2016/6/17.
 * PackageName com.guanjia800.clientApp.chat_tools.utils.
 * ProjectName HouseKeeper_android.
 */
public class SaveObjectUtil {


    private static ArrayList<String> images;

    public static void loadImage(Context context, ImageAttachment attachment, String path){
        int heightY = attachment.getHeight();
        int widthY = attachment.getWidth();
        attachment.setWidth(ScreenUtils.getScreenWidth(context) / 45 * 10);
        int widthN = attachment.getWidth();
        attachment.setHeight(widthN*heightY/widthY);
        attachment.setPath(path);
    }

    public static void setImage(final Context context, IViewHolder viewHolder, IMMessage imMessage, int a) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        ImageAttachment imageAttachment = (ImageAttachment) imMessage.getAttachment();
        images = new ArrayList<>();
        Log.e("setImage: ", imageAttachment.getThumbPath()+"");
        if (a == 2) {
            String path = imageAttachment.getThumbPath();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            viewHolder.message_item_image_left.setImageBitmap(bitmap);
            viewHolder.message_item_image_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageDetailsActivity.class);
                    intent.putStringArrayListExtra("images", images);
                    intent.putExtra("position", 0);
                    intent.putExtra("type", 1);
                    context.startActivity(intent);
                }
            });
        } else {
            String path = imageAttachment.getPathForSave();
            Log.e("setImage: ", path+"");
            images.add(path);
            viewHolder.message_item_image_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageDetailsActivity.class);
                    intent.putStringArrayListExtra("images", images);
                    intent.putExtra("position", 0);
                    intent.putExtra("type", 1);
                    context.startActivity(intent);
                }
            });
            File file = new File(path);
            String thumbPath = imageAttachment.getThumbPath();
            if (thumbPath == null && path != null) {
                if (file.length() > 100 * 1024) {
                    BitmapFactory.decodeFile(path, options);
                    options.inJustDecodeBounds = true;
                    int outWidth = options.outWidth;
                    int samle = outWidth / (ScreenUtils.getScreenWidth(context) / 45 * 10);
                    options.inJustDecodeBounds = false;
                    if (samle > 1)
                        options.inSampleSize = samle;
                    else
                        options.inSampleSize = 1;
                    Bitmap bitmap2 = BitmapFactory.decodeFile(path, options);
                    try {
                        if (bitmap2.getByteCount() > 10)
                            bitmap2.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(new File(path)));
                    } catch (Exception e) {
                    }
                    viewHolder.message_item_image_right.setImageBitmap(bitmap2);
                } else {
                    Bitmap bitmap1;
                    bitmap1 = BitmapFactory.decodeFile(path, options);
                    viewHolder.message_item_image_right.setImageBitmap(bitmap1);
                }
            } else if (!file.exists() && thumbPath != null) {
                try {
                    Bitmap B = BitmapFactory.decodeFile(thumbPath, options);
                    B.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(new File(path)));
                }catch (Exception e ){}
            } else {
                File thumb = new File(thumbPath);
                System.out.println("*********thumbPath.length********"+thumb.length());
                viewHolder.message_item_image_right.setImageBitmap(BitmapFactory.decodeFile(thumbPath, options));
            }
        }
    }

    public static void setVideo(Context context,IViewHolder viewHolder, IMMessage imMessage, int a) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        VideoAttachment imageAttachment = (VideoAttachment) imMessage.getAttachment();

        String path = imageAttachment.getPath();
        String thumb = imageAttachment.getThumbPathForSave();
        String sss = ThumbImageUtil.extractThumbnail(path, thumb);

        if (a == 2) {
            ThumbImageUtil.loadThumbnailImage(context,sss,viewHolder.message_item_video_left);
        } else {
            ThumbImageUtil.loadThumbnailImage(context,sss,viewHolder.message_item_video_right);
        }
    }
}
