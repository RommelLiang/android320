package com.tiemuyu.chuanchuan.activity.util;

import android.os.Build;
import android.util.Log;

import com.tiemuyu.chuanchuan.activity.constant.Constant;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public final class LogHelper {
    private static final boolean LOG = Constant.DEBUG_MODEL; // Below ADT R17
    private static final String CLASS_METHOD_LINE_FORMAT = "%s.%s()  Line:%d";
    private static final String LOG_TAG = "hw";

    /**
     * 打印日志
     *
     * @param objects
     *            需要打印的项
     */
    public static void trace(Object... objects) {
        if (LOG) {
            StackTraceElement traceElement = Thread.currentThread()
                    .getStackTrace()[3];// 从堆栈信息中获取当前被调用的方法信息
            String logText = String.format(CLASS_METHOD_LINE_FORMAT,
                    traceElement.getClassName(), traceElement.getMethodName(),
                    traceElement.getLineNumber());
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    logText += "\n    Log:" + objects[i];
                }
            }
            Log.d(LOG_TAG, logText);// Log
        }
    }

    public static boolean isEmulator() {
        return (Build.MODEL.equals("sdk"))
                || (Build.MODEL.equals("google_sdk"));
    }

    /*
     * 自定义tag
     */
    public static void v(String tag, String msg) {
        if (LOG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (LOG) {
            android.util.Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (LOG) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (LOG) {
            android.util.Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (LOG) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (LOG) {
            android.util.Log.e(tag, msg, tr);
        }
    }

    /*
     * tag使用默认值
     */
    public static void v(String msg) {
        LogHelper.v(LOG_TAG, msg);
    }

    public static void v(String msg, Throwable tr) {
        LogHelper.v(LOG_TAG, msg, tr);
    }

    public static void d(String msg) {
        LogHelper.d(LOG_TAG, msg);
    }

    public static void d(String msg, Throwable tr) {
        LogHelper.d(LOG_TAG, msg, tr);
    }

    public static void i(String msg) {
        LogHelper.i(LOG_TAG, msg);
    }

    public static void i(String msg, Throwable tr) {
        LogHelper.i(LOG_TAG, msg, tr);
    }

    public static void w(String msg) {
        LogHelper.w(LOG_TAG, msg);
    }

    public static void w(String msg, Throwable tr) {
        LogHelper.w(LOG_TAG, msg, tr);
    }

    public static void e(String msg) {
        LogHelper.e(LOG_TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        LogHelper.e(LOG_TAG, msg, tr);
    }
}

