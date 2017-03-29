package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/28.
 */

public class PushBeanPlus {
    private PushBean mPushBean;
    private boolean isShou;
    private List<Integer> mIntegers;

    public PushBeanPlus(PushBean nPushBean, boolean nIsShou, List<Integer> mIntegers) {
        mPushBean = nPushBean;
        isShou = nIsShou;
        this.mIntegers = mIntegers;
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

    public List<Integer> getIntegers() {
        return mIntegers;
    }

    public void setIntegers(List<Integer> nIntegers) {
        mIntegers = nIntegers;
    }
}
