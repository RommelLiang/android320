package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;

public class MessageShowUtil {
    private IViewHolder viewHolder;
    private Context context;

    public MessageShowUtil(Context context) {
        this.context = context;
    }

    public void setCheckMessageShowType(IViewHolder viewHolder, int type, IMMessage imMessage,View[] views) {
        this.viewHolder = viewHolder;
        Log.e("type:" + type, "setCheckMessageShowType: ");
        switch (type / 10) {
            case 1:
                viewHolder.ly_left.setVisibility(View.GONE);
                viewHolder.ly_right.setVisibility(View.VISIBLE);
                break;
            case 2:
                viewHolder.ly_right.setVisibility(View.GONE);
                viewHolder.ly_left.setVisibility(View.VISIBLE);
                break;
        }
        switch (type) {
            case 10:
                checkShow(views[1],views[3],views[5],views[7]);
                final String content = imMessage.getContent();
                viewHolder.message_content_right.setText(content);
                break;
            case 11:
                checkShow(views[5],views[1],views[3],views[7]);
                SaveObjectUtil.setImage(context,viewHolder, imMessage, 1);
                break;
            case 12:
                checkShow(views[3],views[1],views[5],views[7]);
                long time = ((AudioAttachment) imMessage.getAttachment()).getDuration();
                String dd = TimeUtil.secToTime((int) time / 1000, "''");
                viewHolder.message_audio_time_right.setText(dd);

                break;
            case 13:
                checkShow(views[7],views[3],views[5],views[1]);
                SaveObjectUtil.setVideo(context,viewHolder,imMessage,1);

                break;
            case 20:
                checkShow(views[0],views[2],views[4],views[6]);
                String content1 = imMessage.getContent();
                viewHolder.message_content_left.setText(content1);
                break;
            case 21:
                checkShow(views[4],views[2],views[0],views[6]);
                SaveObjectUtil.setImage(context,viewHolder, imMessage, 2);

                break;
            case 22:
                checkShow(views[2],views[0],views[4],views[6]);
                long time1 = ((AudioAttachment) imMessage.getAttachment()).getDuration();
                String dd1 = TimeUtil.secToTime((int) time1 / 1000, "''");
                viewHolder.message_audio_time_left.setText(dd1);
                break;
            case 23:
                checkShow(views[6],views[2],views[4],views[0]);
                SaveObjectUtil.setVideo(context,viewHolder,imMessage,2);

                break;
            case 25:
                checkShow(views[0],views[2],views[4],views[6]);
                viewHolder.message_content_left.setText("  查看订单  ");

                break;
        }
    }

    private void checkShow(View v,View ... views){
        for(View view : views){
            view.setVisibility(View.GONE);
        }
        v.setVisibility(View.VISIBLE);
    }

}
