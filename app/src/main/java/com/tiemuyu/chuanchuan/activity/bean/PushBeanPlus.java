package com.tiemuyu.chuanchuan.activity.bean;

/**
 * Created by 梁文硕 on 2017/3/28.
 */

public class PushBeanPlus {
    private PushBean mPushBean;
    private boolean isShou;

    public PushBeanPlus(PushBean nPushBean, boolean nIsShou) {
        mPushBean = nPushBean;
        isShou = nIsShou;
    }

    public PushBean getPushBean() {
        return mPushBean;
    }

    public void setPushBean(PushBean nPushBean) {
        mPushBean = nPushBean;
    }

    public boolean isShou() {
        return isShou;
    }

    public void setShou(boolean nShou) {
        isShou = nShou;
    }
}
