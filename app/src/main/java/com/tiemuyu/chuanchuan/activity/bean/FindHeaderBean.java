package com.tiemuyu.chuanchuan.activity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @项目名： 215androidpay
 * @包名： com.tiemuyu.chuanchuan.activity.bean
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/2/24
 * @version：
 */

public class FindHeaderBean implements Serializable {
    /**
     * Code : 1
     * Msg : OK
     * Data : [{"id":18,"name":"综艺爆款","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104443208CABHTNJZAD.jpg","createtime":"2017-01-10 17:36:40","miaoshu":"综艺节目向来是明星们大秀衣品的好平台，同时也是很多爆款潮装的生产基地，爱时尚和欢乐的你可别错过！","guanjianzi":"一年级,我们十七岁,火星情报局,快乐大本营","morenwenben":"梦想的声音","status":0,"statusname":"文章类型","GuanJianList":["一年级","我们十七岁","火星情报局","快乐大本营"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1023/QFI/023162403865CABHKLTPUC.jpg","SumLook":40577},{"id":8,"name":"明星专场","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104539302CABHGQPSNZ.jpg","createtime":"2016-11-17 10:29:10","miaoshu":"想知道最潮的明星都穿哪些美衣？怎么才能拥有一件你喜欢的明星同款？小穿君来给你介绍，把你最爱的衣服都做给你穿！","guanjianzi":"倪妮,杨幂,宋佳,孙红雷,郑爽 ","morenwenben":"倪妮","status":0,"statusname":"文章类型","GuanJianList":["倪妮","杨幂","宋佳","孙红雷","郑爽 "],"bigimg":"http://f1.myappcc.com/zfs/7E1/1023/QFI/023162644631CABHYJIYGY.jpg","SumLook":44457},{"id":9,"name":"热剧扒款","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104521286CABHJFMMOE.jpg","createtime":"2016-11-17 10:51:09","miaoshu":"那件衣服电视剧里的她穿着好美，好想拥有怎么办？去哪里找到同款？小穿君这就帮你扒出来。","guanjianzi":"放弃我抓紧我,如果蜗牛有爱情,嫉妒的化身","morenwenben":"放弃我抓紧我","status":0,"statusname":"文章类型","GuanJianList":["放弃我抓紧我","如果蜗牛有爱情","嫉妒的化身"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1023/QFI/023162735490CABHYWNVUO.jpg","SumLook":43773},{"id":27,"name":"大牌面料","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104410708CABHKERAAB.jpg","createtime":"2017-01-23 12:51:31","miaoshu":"面料是影响一件时装的造型和效果的核心。大牌其实并不奢侈，选对了面料再讲究一点款式，你的level定会提升不少。","guanjianzi":"真丝,蕾丝,小香风,羊绒","morenwenben":"真丝","status":0,"statusname":"文章类型","GuanJianList":["真丝","蕾丝","小香风","羊绒"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1023/TJK/023190138724CABHKQVKQB.jpg","SumLook":245133},{"id":7,"name":"今日开团","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104558786CABHMYGNKY.jpg","createtime":"2016-11-15 19:29:36","miaoshu":"选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！","guanjianzi":"大衣,裙,外套,衬衣,上衣,羽绒服,裤","morenwenben":"选衣服好似选饭馆，葛优大爷有名言曰：哪家人多我找哪家！赶快来看看穿友们正在定制的热款吧！最重要的是：以下款式均可联系客服享九折特惠！","status":1,"statusname":"瀑布流形式","GuanJianList":["大衣","裙","外套","衬衣","上衣","羽绒服","裤"],"bigimg":"http://f1.myappcc.com/zfs/7E0/1322/SCJ/322185833350CABGRTUHAI.jpg","SumLook":0},{"id":16,"name":"秀场速报","url":"#","img":"http://f1.myappcc.com/zfs/7E1/1047/KOG/047104455786CABHRDSYDN.jpg","createtime":"2017-01-05 17:42:35","miaoshu":"最前沿的时装潮流！最顶尖的大牌时装秀！想成为时尚达人，那就看一场秀来寻找穿搭灵感吧！","guanjianzi":"Dior,LV,PEACE BIRD","morenwenben":"PEACE BIRD","status":0,"statusname":"文章类型","GuanJianList":["Dior","LV","PEACE BIRD"],"bigimg":"http://f1.myappcc.com/zfs/7E1/1005/RIC/005174226639CABHPLZWFV.jpg","SumLook":11913}]
     */

    private int Code;
    private String Msg;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {

        /**
         * id : 18
         * name : 综艺爆款
         * url : #
         * img : http://f1.myappcc.com/zfs/7E1/1047/KOG/047104443208CABHTNJZAD.jpg
         * createtime : 2017-01-10 17:36:40
         * miaoshu : 综艺节目向来是明星们大秀衣品的好平台，同时也是很多爆款潮装的生产基地，爱时尚和欢乐的你可别错过！
         * guanjianzi : 一年级,我们十七岁,火星情报局,快乐大本营
         * morenwenben : 梦想的声音
         * status : 0
         * statusname : 文章类型
         * GuanJianList : ["一年级","我们十七岁","火星情报局","快乐大本营"]
         * bigimg : http://f1.myappcc.com/zfs/7E1/1023/QFI/023162403865CABHKLTPUC.jpg
         * SumLook : 40577
         */

        private int id;
        private String name;
        private String url;
        private String img;
        private String createtime;
        private String miaoshu;
        private String guanjianzi;
        private String morenwenben;
        private int status;
        private String statusname;
        private String bigimg;
        private int SumLook;
        private List<String> GuanJianList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getMiaoshu() {
            return miaoshu;
        }

        public void setMiaoshu(String miaoshu) {
            this.miaoshu = miaoshu;
        }

        public String getGuanjianzi() {
            return guanjianzi;
        }

        public void setGuanjianzi(String guanjianzi) {
            this.guanjianzi = guanjianzi;
        }

        public String getMorenwenben() {
            return morenwenben;
        }

        public void setMorenwenben(String morenwenben) {
            this.morenwenben = morenwenben;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusname() {
            return statusname;
        }

        public void setStatusname(String statusname) {
            this.statusname = statusname;
        }

        public String getBigimg() {
            return bigimg;
        }

        public void setBigimg(String bigimg) {
            this.bigimg = bigimg;
        }

        public int getSumLook() {
            return SumLook;
        }

        public void setSumLook(int SumLook) {
            this.SumLook = SumLook;
        }

        public List<String> getGuanJianList() {
            return GuanJianList;
        }

        public void setGuanJianList(List<String> GuanJianList) {
            this.GuanJianList = GuanJianList;
        }

    }

}
