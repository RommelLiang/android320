package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;

/**
 * Created by Lonze on 2016/9/6.
 */
public class UpDataUtil {

    public static ArrayList<Contacts> updata(TreeSet<Contacts> list, ArrayList<Contacts> dataList, Contacts contacts, List<RecentContact> messages){
        list.clear();
        for (RecentContact r : messages) {
            contacts.setContent(r.getContent());
            contacts.setName(r.getFromNick());
            contacts.setAccid(r.getFromAccount());
            contacts.setTime(r.getTime());
            contacts.setTotal(r.getUnreadCount());
            list.add(contacts);
        }
        for(int i = 0; i < dataList.size(); i++){
            list.add(dataList.get(i));
        }
        dataList.clear();
        dataList.addAll(0,list);
        return dataList;
    }
}
