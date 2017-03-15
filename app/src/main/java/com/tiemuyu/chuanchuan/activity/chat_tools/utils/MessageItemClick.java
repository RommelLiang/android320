package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import com.tiemuyu.chuanchuan.activity.chat_tools.activity.ImageShowActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.WatchVideoActivity;
import com.tiemuyu.chuanchuan.activity.view.ImagePreviewActivity;

/**
 * describe :
 * Created by FUYING on 2016/6/30.
 * PackageName com.guanjia800.clientApp.chat_tools.utils.
 * ProjectName HouseKeeper_android.
 */
public class MessageItemClick {

    private Context context;

    public MessageItemClick(Context context) {
        this.context = context;
    }

    public void itemOnClick(ImageView view, IMMessage imMessage, int d) {
        System.out.println("!!!!!!!!IM pic is clicked!!!!!!!!!!!!");
        System.out.println("The IMMessage is: " + imMessage.getAttachment().toString());
        // 史力：关闭掉点击图片的事件
        if (imMessage.getMsgType() == MsgTypeEnum.image) {
            String path;
            String Uri;
            int type;
            if (d == 1) {
                path = ((ImageAttachment) imMessage.getAttachment()).getThumbPathForSave();
                Uri = ((ImageAttachment) imMessage.getAttachment()).getUrl();
                type = 1;
            } else {
                path = ((ImageAttachment) imMessage.getAttachment()).getPathForSave();
                Uri = ((ImageAttachment) imMessage.getAttachment()).getUrl();
                type = 2;
            }
            System.out.println("!!!!!!!!!!path is: " + path + " in MessageItemClick!!!!!!");
            System.out.println("!!!!!!!!!!Uri is: " + Uri + " in MessageItemClick!!!!!!");
            Intent intent = new Intent(context, ImagePreviewActivity.class);
            intent.putExtra("imgUrl", Uri);
            context.startActivity(intent);
        }
    }

//    public void itemOnLongClick(View view){
//        PopupMenu popupMenu = new PopupMenu(context,view,Gravity.LEFT);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//        popupMenu.inflate();
//        popupMenu.setGravity(Gravity.LEFT);
//    }

}

