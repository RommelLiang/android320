package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/29.
 */

public class KeFuBean {

    /**
     * Code : 201
     * Data : {"attachcontentcolor":[],"content":"9000","attachcontent":"定制助理正在赶来的路上，不如先去看看别的？","isneedColor":2}
     * Msg : 客服9000处于离线状态，工作状态为正常
     */

    private int Code;
    private DataBean Data;
    private String Msg;

    public static KeFuBean objectFromData(String str) {

        return new Gson().fromJson(str, KeFuBean.class);
    }

    public static KeFuBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), KeFuBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<KeFuBean> arrayKeFuBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<KeFuBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<KeFuBean> arrayKeFuBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<KeFuBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public static class DataBean {
        /**
         * attachcontentcolor : []
         * content : 9000
         * attachcontent : 定制助理正在赶来的路上，不如先去看看别的？
         * isneedColor : 2
         */

        private String content;
        private String attachcontent;
        private int isneedColor;
        private List<?> attachcontentcolor;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAttachcontent() {
            return attachcontent;
        }

        public void setAttachcontent(String attachcontent) {
            this.attachcontent = attachcontent;
        }

        public int getIsneedColor() {
            return isneedColor;
        }

        public void setIsneedColor(int isneedColor) {
            this.isneedColor = isneedColor;
        }

        public List<?> getAttachcontentcolor() {
            return attachcontentcolor;
        }

        public void setAttachcontentcolor(List<?> attachcontentcolor) {
            this.attachcontentcolor = attachcontentcolor;
        }
    }
}
