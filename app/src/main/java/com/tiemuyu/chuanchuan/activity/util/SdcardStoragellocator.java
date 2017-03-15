package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by CC2.0 on 2017/1/6.
 */

public class SdcardStoragellocator {
    private static final long LOW_STORAGE_THRESHOLD = 1024 * 1024 * 2;

    public static String getExternalStoragePath() {

        // 获取SdCard状态

        String state = android.os.Environment.getExternalStorageState();

        // 判断SdCard是否存在并且是可用的

        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {

            if (hasMoreStorage(LOW_STORAGE_THRESHOLD))
                return android.os.Environment.getExternalStorageDirectory()
                        .getPath();

        } else {
            File emmc = new File("mnt" + File.separator + "emmc"
                    + File.separator);
            if (emmc.isDirectory()) {
                return emmc.toString();
            }
        }
        return null;

    }

    public static String getSavePath(Context mContext) {
        if ((Environment.getExternalStorageState())
                .equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return mContext.getFilesDir().getAbsolutePath();
        }
    }



    /** 检查存储空间是否小于lowStorage */
    public static boolean hasMoreStorage(long lowStorage) {
        // Check available space only if we are writable
        String storageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        StatFs stat = new StatFs(storageDirectory);
        // 计算可用存储内存大小
        long remaining = (long) stat.getAvailableBlocks()
                * (long) stat.getBlockSize();
        if (remaining < lowStorage) {
            return false;
        }
        return true;
    }
}
