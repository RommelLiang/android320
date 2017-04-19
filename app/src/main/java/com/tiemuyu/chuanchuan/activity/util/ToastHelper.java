package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.R;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class ToastHelper {
    private static Toast toast;
    private static TextView tv;

    public static void show(Context ctx, String message) {
        if (ctx == null || TextUtils.isEmpty(message)) {
            return;
        }
        if (null == toast) {
            // toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
            toast = createdToast(ctx, message);
        } else {
            tv.setText(message);
        }
        toast.show();
    }

    public static void hide() {
        if (null != toast) {
            toast.cancel();
        }
    }

    /** 自定义toast */
    public static Toast createdToast(Context ctx, String msg) {
        View toastRoot = LinearLayout.inflate(ctx, R.layout.toast_layout, null);
        Toast to = new Toast(ctx);
        to.setView(toastRoot);
        to.setDuration(Toast.LENGTH_SHORT);
        tv = (TextView) toastRoot.findViewById(R.id.tv_toast);
        tv.setText(msg);
        return to;

    }

    public static void toastSetText(Context context, String msg) {
        if (toast != null)
            tv.setText(msg);
    }
}
