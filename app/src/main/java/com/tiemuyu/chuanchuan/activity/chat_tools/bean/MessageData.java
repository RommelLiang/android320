package com.tiemuyu.chuanchuan.activity.chat_tools.bean;

import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lonze on 2016/8/30.
 */
public class MessageData {

    private static ArrayList<IMMessage> data;

//    public MessageData(){
//        data = new ArrayList<>();
//    }
//
    public static void setData(IMMessage data1){
        data.add(data1);
    }
    public static void setDataList(ArrayList<IMMessage> data1){
        data = data1;
    }
    public static void addDataList(List<IMMessage> data1){

        for(int i = 0; i < data1.size(); i++){
            data.add(0,data1.get(i));
        }
    }
    public static ArrayList<IMMessage> getDataList(){
        return data;
    }

    public static IMMessage getData(int i){
        if(data.size() == 0)
            return null;
        return data.get(i);
    }
    public static void clearData(){
        data.clear();
    }

}
