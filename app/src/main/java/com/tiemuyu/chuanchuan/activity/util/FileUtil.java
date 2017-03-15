package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Terry on 2015/12/15.
 */
public class FileUtil {
    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
     *
     * @param context
     */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * * 按名字清除本应用数据库 * *
     *
     * @param context
     * @param dbName
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * *
     *
     * @param context
     */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * *
     *
     * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * * 清除本应用所有的数据 * *
     *
     * @param context
     * @param filepath
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        if (filepath == null) {
            return;
        }
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
     *
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    public static String getCacheSize(File file) throws Exception {
        return getFormatSize(getFolderSize(file));
    }

    /**
     * 取得所有图片类型的缓存大小
     * @return
     * @throws Exception
     */
    public static long getAllImgCacheSize(Context context) throws Exception {
        long size=0;
        FilePathUtil filePathUtil = FilePathUtil.getInstant();
        File img_release = new File(filePathUtil.getCreation_img_release());
        File img_tmp = new File(filePathUtil.getCreation_img_tmp());
        File effect = new File(filePathUtil.getEffectBgDir());
        File share=new File("/data/data/" + context.getPackageName() + "/shared_prefs");
        size=getFolderSize(img_release)+getFolderSize(img_tmp)+getFolderSize(effect)+getFolderSize(share);
        return size;
    }/**
     * 取得所有视频类型的缓存大小
     * @return
     * @throws Exception
     */
    public static long getAllVideoCacheSize() throws Exception {
        long size=0;
        FilePathUtil filePathUtil = FilePathUtil.getInstant();
        File video_tmp = new File(filePathUtil.getCreation_video_tmp());
        File video_preview = new File(filePathUtil.getCreation_video_preview());
        File video_release = new File(filePathUtil.getCreation_video_release());
        File audio_release = new File(filePathUtil.getCreation_audio_release());
        File audio = new File(filePathUtil.getTmpAudioPath());
        size=getFolderSize(video_tmp)+getFolderSize(video_preview)+getFolderSize(video_release)+getFolderSize(audio_release)+getFolderSize(audio);
        return size;
    }

    /**
     * 清除所有图片缓存
     */
    public static void  clearAllPicCoach(Context context){
        FilePathUtil filePathUtil = FilePathUtil.getInstant();
        deleteFolderFile(filePathUtil.getCreation_img_release(),true);
        deleteFolderFile(filePathUtil.getCreation_img_tmp(),true);
        deleteFolderFile(filePathUtil.getEffectBgDir(),true);
        cleanSharedPreference(context);

    }
    /**
     * 清除所有图片缓存
     */
    public static void  clearAllVideoCoach(){
        FilePathUtil filePathUtil = FilePathUtil.getInstant();
        deleteFolderFile(filePathUtil.getCreation_video_tmp(),true);
        deleteFolderFile(filePathUtil.getCreation_video_preview(), true);
        deleteFolderFile(filePathUtil.getCreation_video_release(),true);
        deleteFolderFile(filePathUtil.getCreation_audio_release(),true);
        deleteFolderFile(filePathUtil.getTmpAudioPath(),true);

    }

    //移动文件的路径
    public static boolean move(String srcFilePath, String dstFilePath) {
        if (TextUtils.isEmpty(srcFilePath) || TextUtils.isEmpty(dstFilePath)) {
            return false;
        }

        File srcFile = new File(srcFilePath);
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }

        File dstFile = new File(dstFilePath);
        if(dstFile.getParentFile() == null) {
            return false;
        }

        if (!dstFile.getParentFile().exists()) {// 如果不存在上级文件夹
            dstFile.getParentFile().mkdirs();
        }

        return srcFile.renameTo(dstFile);
    }
}
