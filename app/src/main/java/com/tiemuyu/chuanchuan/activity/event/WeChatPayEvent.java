package com.tiemuyu.chuanchuan.activity.event;

/**
 * Created by 梁文硕 on 2017/3/23.
 */

public class WeChatPayEvent {
    private int mMsg;
    public WeChatPayEvent(int msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public int getMsg(){
        return mMsg;
    }
}
