package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/4/26.
 */
public class DataSharedPress {

    private SharedPreferences preferences;
    private static DataSharedPress sharedPress;
    private Context context;
    private DataSharedPress(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("CC",Context.MODE_PRIVATE);
    }
    public static DataSharedPress getSharedPress(Context context){
        if(sharedPress == null){
            sharedPress = new DataSharedPress(context);
        }
        return sharedPress;
    }



    public void putString(String key,String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public String getString(String key){
        return preferences.getString(key,"");
    }
    public void putInt(String key,int value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public int getInt(String key){
        return preferences.getInt(key, 0);
    }
    public void putLong(String key,long value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public long getLong(String key){
        return preferences.getLong(key, System.currentTimeMillis()-2000);
    }
    public void putBoolean(String key,boolean value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public boolean getBoolean(String key){
        return preferences.getBoolean(key,false);
    }

}
