package com.tiemuyu.chuanchuan.activity.view;

/**
 * 功能描述：数据请求链接【有标注为get的为get请求方式，没有标注的为post】
 * Created by 史力 on 2016/8/7.
 * ProjectName：chuanchuan.
 */
//
public class URL {
    public static final String BASE = "http://test.myappcc.com";//主机名
    //private static final String BASE2 = "http://a1.myappcc.com";//上传接口

    private static final String CC_OR_DEBUG = "/cc";//cc或者debug的区别
    public static final String UrlLaunchPage = BASE + "/h5/cc.launch.html";//入场画面（旧版才需要）
    public static String UrlHomeSearch(String searchKey) {//搜索
        return BASE + CC_OR_DEBUG + "/customize/squareshow?key=" + searchKey;
    }

    public static final String source="new59";

    public static  final String currentversion="3.0.60";

    public static boolean loginstatus=true;
    /**
     * Moment图片上传路径
     */
    public static final String UPLOAD_URLMoment="http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag=MOMENT";
    public static final String UPLOAD_URLUSERLOGO = "http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag=USERLOGO";
    public static final String UPLOAD_URLREFUND = "http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag=REFUND";
    public static final String UPLOAD_URLUSERIMG = "http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag=USERIMG";
    public static final String UPLOAD_URLBODYPIC = "http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag=BODYPIC";

    //穿友相关
    public static final String UrlHome = BASE + CC_OR_DEBUG + "/home/index";

    public static String UrlFriendsSearch(String friendName) {
        return BASE + CC_OR_DEBUG + "/friends/findfriend?name=" + friendName;
    }

    public static String UrlFriendsAdd(String friendName) {
        return BASE + CC_OR_DEBUG + "/friends/addfriend?name=" + friendName;
    }

    public static boolean testlogin = true;

    public static boolean testmine = false;
    //主要功能
    public static final String UrlFriends = BASE + CC_OR_DEBUG + "/friends/contacts";

    public static final String UrlDiscovery = BASE + CC_OR_DEBUG + "/Find/FindIndex";

    public static final String UrlMine = BASE + CC_OR_DEBUG + "/user/mine";

    public static final String UrlLogin = BASE + CC_OR_DEBUG + "/login/index";

    public static final String UrlRegister = BASE + CC_OR_DEBUG + "/register/index";

    public static final String UrlCustomize = BASE + CC_OR_DEBUG + "/customize/piccustomize";


    public static final String UrlWallet = BASE + CC_OR_DEBUG + "/MyWallet/MyWalletIndex";


    public static final String UrlOrder = BASE + CC_OR_DEBUG + "/myOrder/myOrderIndex";


    /***********钱包子页面网页调用*************/

    public static final String UrlAssetDetail = BASE + CC_OR_DEBUG + "/mywallet/AssetDetail";

    public static final String UrlAccountManage = BASE + CC_OR_DEBUG + "/mywallet/AccountManage";

    public static final String UrlPayPasswordManage = BASE + CC_OR_DEBUG + "/mywallet/PayPasswordManage";

    public static final String UrlCashExchange = BASE + CC_OR_DEBUG + "/mywallet/CashExchange";

    public static final String UrlCharge = BASE + CC_OR_DEBUG + "/mywallet/Charge";



    /***********协议页面网页调用*************/
    //注册协议
    public static final String UrlRegisterXieyi = BASE + "/h5/register.html";//CC_OR_DEBUG + "/mywallet/AssetDetail";

    //制衣协议
    public static final String UrlDingzhiXieyi = BASE + CC_OR_DEBUG + "/register/xieyi";




    public static String UrlRegisterUserInvited(String inviteuser) {
        return BASE + CC_OR_DEBUG + "/register/index?inviteuser=" + inviteuser;
    }

    public static String UrlRegisterUserActivity(String actcode) {
        return BASE + CC_OR_DEBUG + "/register/index?actcode=" + actcode;
    }

    public static String UrlPublish(String images) {
        return BASE + CC_OR_DEBUG + "/customize/piccustomize?images=" + images;
    }

    public static String UrlUserInfoScan(String userid) {
        return BASE + "/user/" + userid;
    }

    public static String UrlUserInfo(String userid) {
        return BASE + CC_OR_DEBUG + "/user/memberinfo/" + userid;
    }

    public static final String UrlMineInfo = BASE + CC_OR_DEBUG + "/user/basicinfo";

    public  static final class API {
        private  static final String APIAPP = BASE + "/api/app/";

        public static final String ApiUpdate = APIAPP + "update";
        public static final String ApiLogout = APIAPP + "loginout";

        public static String ApiUpload(String funFlag) {
            return APIAPP + "upload?sysNo=CC.APP&funFlag=" + funFlag;
        }

        public static final String ApiPassKey = APIAPP + "passkey";
    }
}