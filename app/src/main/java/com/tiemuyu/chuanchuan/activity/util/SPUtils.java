package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.tiemuyu.chuanchuan.activity.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferences封装类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class SPUtils {
    private static Context mContext;

    public static final String FILE_NAME = "sys_data";
    public static final String ADD_TIME = "add_time_" + R.string.versionCode;
    public static final String KEFU_CODE= "kefu_code";
    public static final String USER_ACCID = "user_accid";
    public static final String IS_NEW_VERSION = "is_new_version";
    public static final String IS_vibrate = "is_vibrate";
    public static final String IS_ring = "is_ring";
    public static final String IS_HUODONG = "is_huodong";
    public static final String START_TIME_HUODONG = "START_TIME_HUODONG";
    public static final String END_TIME_HUODONG = "END_TIME_HUODONG";
    public static final String HUO_DONG_URL = "HUO_DONG_URL";
    public static final String HUO_DONG_IMAGE = "HUO_DONG_IMAGE";
    public static final String HUO_DONG_MIASHU = "HUO_DONG_MIASHU";
    public static final String CRASH_URL = "CRASH_URL";
    public static final String CRASH_LOCAL_URL = "CRASH_LOCAL_URL";
    public static final String OPEN_SHAITU_TIME = "OPEN_SHAITU_TIME";
    public static final String IS_Force_UPDATE = "IS_Force_UPDATE";
    public static final String IS_NEW_SHATIU= "IS_NEW_SHATIU";

    public static void init(Context context) {
        mContext = context;
    }

    public static void saveAddTime(String time) {
        put(ADD_TIME,time);
    }

    public static String getAddTime(){
        return (String) get(ADD_TIME,"");
    }

    public static void saveVibrate(boolean isNew){
        put(IS_vibrate,isNew);
    }

    public static String getCrashUrl(){
        return (String) get(CRASH_URL,"");
    }

    public static void setCrashUrl(String url){
        put(CRASH_URL,url);
    }

    public static String getCrashLocalUrl(){
        return (String) get(CRASH_LOCAL_URL,"");
    }

    public static void setCrashLocalUrl(String url){
        put(CRASH_LOCAL_URL,url);
    }

    public static boolean getVibrate(){
        return (boolean) get(IS_ring,false);
    }

    public static void saveRing(boolean isNew){
        put(IS_ring,isNew);
    }

    public static boolean getRing(){
        return (boolean) get(IS_vibrate,false);
    }

    public static void setIsNewShatiu(boolean isNew){
        put(IS_NEW_SHATIU,isNew);
    }

    public static boolean getIsNewShatiu(){
        return (boolean) get(IS_NEW_SHATIU,false);
    }

    public static void saveIsVersion(boolean isNew){
        put(IS_NEW_VERSION,isNew);
    }

    public static boolean getIsVersion(){
        return (boolean) get(IS_NEW_VERSION,false);
    }

    public static void saveIsForceUp(boolean isNew){
        put(IS_Force_UPDATE,isNew);
    }

    public static boolean getIsForceUp(){
        return (boolean) get(IS_Force_UPDATE,false);
    }

    public static void saveAccid(String accid) {
        put(USER_ACCID,accid);
    }

    public static String getAccid(){
        return (String) get(USER_ACCID,"");
    }

    public static String getKefuCode() {
        return (String) get(KEFU_CODE,"");
    }

    public static void saveKefuCode(String code) {
        put(KEFU_CODE,code);
    }

    public static int getHuoDongID() {
        return (int) get(IS_HUODONG,0);
    }

    public static void saveHuoDongID(int id) {
        put(IS_HUODONG,id);
    }

    public static String getStartTimeHuodong() {
        return (String) get(START_TIME_HUODONG,"");
    }

    public static void setStartTimeHuodong(String code) {
        put(START_TIME_HUODONG,code);
    }
    public static String getEndHuodong() {
        return (String) get(END_TIME_HUODONG,"");
    }

    public static void setEndHuodong(String code) {
        put(END_TIME_HUODONG,code);
    }

    public static String getHuoDongUrl() {
        return (String) get(HUO_DONG_URL,"");
    }

    public static void setHuoDongUrl(String url) {
        put(HUO_DONG_URL,url);
    }

    public static String getHuoDongMiashu() {
        return (String) get(HUO_DONG_MIASHU,"");
    }

    public static void setHuoDongMiashu(String miaoshu) {
        put(HUO_DONG_MIASHU,miaoshu);
    }

    public static String getHuoDongImage() {
        return (String) get(HUO_DONG_IMAGE,"");
    }

    public static void setHuoDongImage(String url) {
        put(HUO_DONG_IMAGE,url);
    }

    public static String getOpenShaituTime() {
        return (String) get(OPEN_SHAITU_TIME,"");
    }

    public static void setOpenShaituTime(String time) {
        put(OPEN_SHAITU_TIME,time);
    }

    public static void put(String key, Object object) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
