package com.tiemuyu.chuanchuan.activity.bean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/7.
 */

public class DingZhiItem {

    /**
     * Code : 1
     * Msg : OK
     * Data : {"DingzhiItem":{"id":663,"daohangid":89,"proid":64789,"mianliao":"梭织棉","xijieimg":null,"status":0,"proname":"女装高级梭织棉单层袖口拉链拼接长袖中长款衬衣","promianpic":"http://f1.myappcc.com/zfs/7E1/1065/PQF/065155428354CABHMLBUZJ.jpg","createtime":"2017-03-06 17:06:51","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1065/RIC/065170547416CABHSHMQTT.jpg","price":522,"username":"A-line","userimg":"http://f1.myappcc.com/zfs/7E0/1004/QFI/004164317142CABGJNAQVR.jpg","imgList":["http://f1.myappcc.com/zfs/7E1/1065/RIC/065170547416CABHSHMQTT.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170549338CABHIMFKLD.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170550994CABHRHVWHL.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170552854CABHCFOEYT.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170555072CABHZHNVBH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170556932CABHZTLPKH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170607244CABHGGZTGH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170610385CABHOGJJQK.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170612073CABHNNCNBL.jpg"],"fatushuo":"","IsFav":false,"plList":null}}
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
        /**
         * DingzhiItem : {"id":663,"daohangid":89,"proid":64789,"mianliao":"梭织棉","xijieimg":null,"status":0,"proname":"女装高级梭织棉单层袖口拉链拼接长袖中长款衬衣","promianpic":"http://f1.myappcc.com/zfs/7E1/1065/PQF/065155428354CABHMLBUZJ.jpg","createtime":"2017-03-06 17:06:51","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1065/RIC/065170547416CABHSHMQTT.jpg","price":522,"username":"A-line","userimg":"http://f1.myappcc.com/zfs/7E0/1004/QFI/004164317142CABGJNAQVR.jpg","imgList":["http://f1.myappcc.com/zfs/7E1/1065/RIC/065170547416CABHSHMQTT.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170549338CABHIMFKLD.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170550994CABHRHVWHL.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170552854CABHCFOEYT.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170555072CABHZHNVBH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170556932CABHZTLPKH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170607244CABHGGZTGH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170610385CABHOGJJQK.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170612073CABHNNCNBL.jpg"],"fatushuo":"","IsFav":false,"plList":null}
         */

        private DingzhiItemBean DingzhiItem;

        public DingzhiItemBean getDingzhiItem() {
            return DingzhiItem;
        }

        public void setDingzhiItem(DingzhiItemBean DingzhiItem) {
            this.DingzhiItem = DingzhiItem;
        }

        public static class DingzhiItemBean {
            /**
             * id : 663
             * daohangid : 89
             * proid : 64789
             * mianliao : 梭织棉
             * xijieimg : null
             * status : 0
             * proname : 女装高级梭织棉单层袖口拉链拼接长袖中长款衬衣
             * promianpic : http://f1.myappcc.com/zfs/7E1/1065/PQF/065155428354CABHMLBUZJ.jpg
             * createtime : 2017-03-06 17:06:51
             * firstXiJieImg : http://f1.myappcc.com/zfs/7E1/1065/RIC/065170547416CABHSHMQTT.jpg
             * price : 522.0
             * username : A-line
             * userimg : http://f1.myappcc.com/zfs/7E0/1004/QFI/004164317142CABGJNAQVR.jpg
             * imgList : ["http://f1.myappcc.com/zfs/7E1/1065/RIC/065170547416CABHSHMQTT.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170549338CABHIMFKLD.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170550994CABHRHVWHL.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170552854CABHCFOEYT.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170555072CABHZHNVBH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170556932CABHZTLPKH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170607244CABHGGZTGH.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170610385CABHOGJJQK.jpg","http://f1.myappcc.com/zfs/7E1/1065/RIC/065170612073CABHNNCNBL.jpg"]
             * fatushuo :
             * IsFav : false
             * plList : null
             */

            private int id;
            private int daohangid;
            private int proid;
            private String mianliao;
            private Object xijieimg;
            private int status;
            private String proname;
            private String promianpic;
            private String createtime;
            private String firstXiJieImg;
            private double price;
            private String username;
            private String userimg;
            private String fatushuo;
            private boolean IsFav;
            private Object plList;
            private List<String> imgList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDaohangid() {
                return daohangid;
            }

            public void setDaohangid(int daohangid) {
                this.daohangid = daohangid;
            }

            public int getProid() {
                return proid;
            }

            public void setProid(int proid) {
                this.proid = proid;
            }

            public String getMianliao() {
                return mianliao;
            }

            public void setMianliao(String mianliao) {
                this.mianliao = mianliao;
            }

            public Object getXijieimg() {
                return xijieimg;
            }

            public void setXijieimg(Object xijieimg) {
                this.xijieimg = xijieimg;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getProname() {
                return proname;
            }

            public void setProname(String proname) {
                this.proname = proname;
            }

            public String getPromianpic() {
                return promianpic;
            }

            public void setPromianpic(String promianpic) {
                this.promianpic = promianpic;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getFirstXiJieImg() {
                return firstXiJieImg;
            }

            public void setFirstXiJieImg(String firstXiJieImg) {
                this.firstXiJieImg = firstXiJieImg;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUserimg() {
                return userimg;
            }

            public void setUserimg(String userimg) {
                this.userimg = userimg;
            }

            public String getFatushuo() {
                return fatushuo;
            }

            public void setFatushuo(String fatushuo) {
                this.fatushuo = fatushuo;
            }

            public boolean isIsFav() {
                return IsFav;
            }

            public void setIsFav(boolean IsFav) {
                this.IsFav = IsFav;
            }

            public Object getPlList() {
                return plList;
            }

            public void setPlList(Object plList) {
                this.plList = plList;
            }

            public List<String> getImgList() {
                return imgList;
            }

            public void setImgList(List<String> imgList) {
                this.imgList = imgList;
            }
        }
    }
}
