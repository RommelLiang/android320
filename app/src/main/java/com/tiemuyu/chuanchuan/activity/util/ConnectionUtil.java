package com.tiemuyu.chuanchuan.activity.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class ConnectionUtil {
    /*
     * 判断网络连接是否已开 ; true: 已打开 ,false: 未打开
     */
    public static boolean isConn(Context context) {
        boolean bisConnFlag = false;
        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            bisConnFlag = conManager.getActiveNetworkInfo().isAvailable()
                    && conManager.getActiveNetworkInfo().isConnected();
        }
        return bisConnFlag;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap网络3：net网络
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /*
     * 打开WIFI设置界面
     */
    public static void setWifiNetwork(final Context context, String message,
                                      DialogInterface.OnClickListener onclick, String btnCancle) {
    }

    /**
     * 打开网络设置界面
     */
    public static void setNetworkMethod(final Activity context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            // 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
            context.startActivity(new Intent(
                    android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(
                    android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * 点击请求网络接口{检测网络是否连接}
     *
     * @param context
     * @param runnable
     */
    public static void RequestNetInterface(Context context, Runnable runnable) {
        if (Utility.isFastDoubleClick()) // 连续点击
            return;
        if (isConn(context))
            ThreadPoolUtils.execute(runnable);
        else
            ToastHelper.show(context, "无网络,请检查设备网络情况");

    }

    public static void DbOffer(Context context, Runnable runnable) {
        if (Utility.isFastDoubleClick()) // 连续点击
            return;
        ThreadPoolUtils.execute(runnable);
    }

    /**
     * 请求网络接口{检测网络是否连接}
     *
     * @param context
     * @param runnable
     */
    public static void DefRequestNetInterface(Context context, Runnable runnable) {
        if (isConn(context))
            ThreadPoolUtils.execute(runnable);
        else
            ToastHelper.show(context, "亲，网络断了哦，请检查网络设置");

    }

}

