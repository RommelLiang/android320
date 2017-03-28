package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/28.
 */

public class PushBean {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"umenghistory":[{"id":62,"title":"百度","image":"http://a1.myappcc.com/images/ccdefault/user_img.jpg","url":"https://www.baidu.com/","type":0,"neirong":"你要加多一个字段","create_user":"gao","sendtime":"2017-03-15 11:54:39","status":1,"miaoshu":"描述"},{"id":63,"title":"测试","image":"","url":"","type":1,"neirong":"你要加多一个字段","create_user":"gao","sendtime":"2017-03-14 11:54:39","status":1,"miaoshu":"管理"},{"id":64,"title":"测试2","image":"","url":"","type":0,"neirong":"你要加多一个字段","create_user":"gao","sendtime":"2017-03-13 11:54:39","status":1,"miaoshu":"查找"},{"id":65,"title":"1","image":"2","url":"3","type":4,"neirong":"5","create_user":"thhhh","sendtime":"2017-03-17 18:06:41","status":1,"miaoshu":"6"}]}
     */

    private int Code;
    private String Msg;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private List<UmenghistoryBean> umenghistory;

        public List<UmenghistoryBean> getUmenghistory() {
            return umenghistory;
        }

        public void setUmenghistory(List<UmenghistoryBean> umenghistory) {
            this.umenghistory = umenghistory;
        }

        public static class UmenghistoryBean {
            /**
             * id : 62
             * title : 百度
             * image : http://a1.myappcc.com/images/ccdefault/user_img.jpg
             * url : https://www.baidu.com/
             * type : 0
             * neirong : 你要加多一个字段
             * create_user : gao
             * sendtime : 2017-03-15 11:54:39
             * status : 1
             * miaoshu : 描述
             */

            private int id;
            private String title;
            private String image;
            private String url;
            private int type;
            private String neirong;
            private String create_user;
            private String sendtime;
            private int status;
            private String miaoshu;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getNeirong() {
                return neirong;
            }

            public void setNeirong(String neirong) {
                this.neirong = neirong;
            }

            public String getCreate_user() {
                return create_user;
            }

            public void setCreate_user(String create_user) {
                this.create_user = create_user;
            }

            public String getSendtime() {
                return sendtime;
            }

            public void setSendtime(String sendtime) {
                this.sendtime = sendtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getMiaoshu() {
                return miaoshu;
            }

            public void setMiaoshu(String miaoshu) {
                this.miaoshu = miaoshu;
            }
        }
    }
}
