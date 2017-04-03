package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/28.
 */

public class PushBean {


    /**
     * history : [{"p_id":"69","p_title":"热剧扒款-《云巅之上》","p_image":"http://f1.myappcc.com/zfs/7E1/1073/LGU/073113527963CABHMSYSCF.jpg","p_link":"/{appname}/Home/fuzhuang?id=28","p_type":"广告","p_content":"热剧扒款","p_sendtime":"2017/3/31 12:15:11","p_attachmsg":"","p_text":"热剧扒款！"},{"p_id":"68","p_title":"因为遇见你","p_image":"http://f1.myappcc.com/zfs/7E1/1086/SCJ/086184417848CABHEUDVHL.jpg","p_link":"/{appname}/Home/fuzhuang?id=30","p_type":"活动","p_content":"送穿币","p_sendtime":"2017/3/31 11:24:35","p_attachmsg":"","p_text":"送穿币！"},{"p_id":"67","p_title":"穿穿两周年 定制享8折","p_image":"http://f1.myappcc.com/zfs/7E1/1089/PQF/089153839618CABHGEQHZY.jpg","p_link":"http://a1.myappcc.com/cc/Find/RankingList?id=170329110036737","p_type":"活动","p_content":"2周年庆","p_sendtime":"2017/3/31 10:49:32","p_attachmsg":"","p_text":"周年庆！"},{"p_id":"1","p_title":"测试标题","p_image":"http://f1.myappcc.com/img/361113254460CABDIOVUZZ.jpg","p_link":"firstpage","p_type":"1","p_content":"产品思密达","p_sendtime":"2017/3/31 9:00:00","p_attachmsg":"","p_text":"窗口提示语句"}]
     * nextstartpos : 1
     */

    private int nextstartpos;
    private List<HistoryBean> history;

    public static PushBean objectFromData(String str) {

        return new Gson().fromJson(str, PushBean.class);
    }

    public static PushBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PushBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PushBean> arrayPushBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PushBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PushBean> arrayPushBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PushBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getNextstartpos() {
        return nextstartpos;
    }

    public void setNextstartpos(int nextstartpos) {
        this.nextstartpos = nextstartpos;
    }

    public List<HistoryBean> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryBean> history) {
        this.history = history;
    }

    public static class HistoryBean {
        /**
         * p_id : 69
         * p_title : 热剧扒款-《云巅之上》
         * p_image : http://f1.myappcc.com/zfs/7E1/1073/LGU/073113527963CABHMSYSCF.jpg
         * p_link : /{appname}/Home/fuzhuang?id=28
         * p_type : 广告
         * p_content : 热剧扒款
         * p_sendtime : 2017/3/31 12:15:11
         * p_attachmsg :
         * p_text : 热剧扒款！
         */

        private String p_id;
        private String p_title;
        private String p_image;
        private String p_link;
        private String p_type;
        private String p_content;
        private String p_sendtime;
        private String p_attachmsg;
        private String p_text;

        public static HistoryBean objectFromData(String str) {

            return new Gson().fromJson(str, HistoryBean.class);
        }

        public static HistoryBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), HistoryBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<HistoryBean> arrayHistoryBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<HistoryBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<HistoryBean> arrayHistoryBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<HistoryBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getP_title() {
            return p_title;
        }

        public void setP_title(String p_title) {
            this.p_title = p_title;
        }

        public String getP_image() {
            return p_image;
        }

        public void setP_image(String p_image) {
            this.p_image = p_image;
        }

        public String getP_link() {
            return p_link;
        }

        public void setP_link(String p_link) {
            this.p_link = p_link;
        }

        public String getP_type() {
            return p_type;
        }

        public void setP_type(String p_type) {
            this.p_type = p_type;
        }

        public String getP_content() {
            return p_content;
        }

        public void setP_content(String p_content) {
            this.p_content = p_content;
        }

        public String getP_sendtime() {
            return p_sendtime;
        }

        public void setP_sendtime(String p_sendtime) {
            this.p_sendtime = p_sendtime;
        }

        public String getP_attachmsg() {
            return p_attachmsg;
        }

        public void setP_attachmsg(String p_attachmsg) {
            this.p_attachmsg = p_attachmsg;
        }

        public String getP_text() {
            return p_text;
        }

        public void setP_text(String p_text) {
            this.p_text = p_text;
        }
    }
}
