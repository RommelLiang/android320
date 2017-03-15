package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;

import com.netease.nimlib.sdk.media.player.AudioPlayer;
import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import com.tiemuyu.chuanchuan.activity.R;

/**
 * describe :
 * Created by FUYING on 2016/6/17.
 * PackageName com.guanjia800.clientApp.chat_tools.utils.
 * ProjectName HouseKeeper_android.
 */
public class PlayAudioUtil {

    private IMMessage message;
    private String audioFilePath;
    private Context mContext;
    private AudioPlayer currentAudioPlayer;
    private Handler mHandler = new Handler();
    public PlayAudioUtil(Context mContext){
        this.mContext = mContext;
    }

    public void startPlay(IMMessage message){
        audioFilePath = ((AudioAttachment) message.getAttachment()).getPath();
        if(TextUtils.isEmpty(audioFilePath)) {
            return ;
        }
        currentAudioPlayer = new AudioPlayer(mContext);
        currentAudioPlayer.setDataSource(audioFilePath);

        mHandler.postDelayed(playRunnable, 500);
    }

    Runnable playRunnable = new Runnable() {

        @Override
        public void run() {
            if (currentAudioPlayer == null) {
                return;
            }
            currentAudioPlayer.start(0);
        }
    };

    public static void play(Context context, ImageView view, String type) {
        if (view.getBackground() instanceof AnimationDrawable) {

            if("SELF".equals(type)) {
                System.out.println("&&&&&&&&&&&&&&&&&SELF");
               final AnimationDrawable ad = (AnimationDrawable)view.getBackground();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        ad.start();
                        Looper.loop();
                    }
                }).start();

            }else {
                AnimationDrawable ad = (AnimationDrawable) context.getResources().getDrawable(
                        R.drawable.nim_audio_animation_list_left);
                view.setBackgroundDrawable(ad);
                ad.start();
            }
        }
    }

    public static void stop(Context context,ImageView view,String type) {
        if (view.getBackground() instanceof AnimationDrawable) {
            if("SELF".equals(type)) {
                AnimationDrawable ad = (AnimationDrawable) context.getResources().getDrawable(
                        R.drawable.nim_audio_animation_list_right);
                view.setBackgroundDrawable(ad);
                ad.stop();
                ad.selectDrawable(2);
            }else {
                AnimationDrawable ad = (AnimationDrawable) context.getResources().getDrawable(
                        R.drawable.nim_audio_animation_list_left);
                view.setBackgroundDrawable(ad);
                ad.stop();
                ad.selectDrawable(2);
            }
        }
    }
}
