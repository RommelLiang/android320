package com.tiemuyu.chuanchuan.activity.constant;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class UrlManager {


//	         正式环境    http://m.myappcc.com
//		集成测试    http://m.integ.chinachuanchuan.com
//		测试环境    http://m.test.chinachuanchuan.com
//		开发环境    http://m.dev.chinachuanchuan.com
//		调试环境    http://m.debug.chinachuanchuan.com


//	public static  String DEFAULT_BASEURL="http://m.dev.chinachuanchuan.com/";//默认开发环境
//	public static  String DEFAULT_DOMAIN=".dev.chinachuanchuan.com";//
//	public static  String DEFAULT_HJ_NAME="开发环境";//

    public static String DEFAULT_BASEURL = "http://test.myappcc.com/";//正式环境
    public static String DEFAULT_DOMAIN = ".myappcc.com";//
    public static String DEFAULT_HJ_NAME = "";//


//	public static  String DEFAULT_BASEURL="http://m.debug.chinachuanchuan.com/";//调试环境
//	public static  String DEFAULT_DOMAIN=".debug.chinachuanchuan.com";//
//	public static  String DEFAULT_HJ_NAME="调试环境";//

//	public static  String DEFAULT_BASEURL="http://m.test.chinachuanchuan.com/";//默认开发环境
//	public static  String DEFAULT_DOMAIN=".test.chinachuanchuan.com";//
//	public static  String DEFAULT_HJ_NAME="测试环境";//

//	public static  String BASEURL="";//
//	public static  String DOMAIN="";//
//	public static  String HJ_NAME="";

    public static String BASEURL = DEFAULT_BASEURL;//
    public static String DOMAIN = DEFAULT_DOMAIN;//
    public static String HJ_NAME = DEFAULT_HJ_NAME;

    public static String USERIMG = "USERIMG";
    public static String FEEDBACK = "FEEDBACK";
    public static String COLLOCATION = "COLLOCATION";

    public static String VERSION_NAME = "1.5.42";//默认版本
    public static int VERSION_CODE = 42;//默认版本号
    public static String DEFAULT_UA = "CC/1.5.42 (LENOVO Lenovo K30-T; Android 4.4.4; IMEI/867527024370464; Longitude/0.00; Latitude/0.00) Serial/42";

    /**
     * 环境地址
     * */
    public static String FORMAL_URL="http://m1.myappcc.com/";



    public static String getBASEURL() {
        return BASEURL;
    }
    public static void setBASEURL(String bASEURL) {
        BASEURL = bASEURL;
    }
    public static String getDOMAIN() {
        return DOMAIN;
    }
    public static void setDOMAIN(String dOMAIN) {
        DOMAIN = dOMAIN;
    }

    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\apiCommon\LoginController.cs(16):        ///     【弃用】获取PassKey,请使用/api/login?passkey接口

    public static  String GET_PASSKEY(){
        return BASEURL+"api/loginapi?passkey";
    }

    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\api\LoginController.cs(21):        /// <url method="post">/api/login?login</url>

    public static  String LOGIN(){
        //登录
        return BASEURL+"api/loginapi?login";
    }



    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\api\LoginController.cs(211):        #region 第三方平台登录                        POST api/login?oauth
    public static  String OAUTH_LOGIN(){
        return BASEURL+"api/loginapi?oauth";
    }//第三方登录

    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\apiCommon\LoginController.cs(85):        /// <url method="get">/api/login?logout=true</url>
    public static  String LOGIN_OUT(){
        return BASEURL+"api/loginapi?logout=true";
    }//注销


    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\api\RegisterController.cs(135):        #region 发送注册验证码                      POST api/register?verifyCode&sid=xxx
    public static  String GET_CODE(){
        return BASEURL+"api/registerapi?sendvalidcode";//获取注册验证码
    }



    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\api\RegisterController.cs(22):        #region 用户注册                            POST api/register?sid=xxx&regSource=yyy
    public static  String REGIST(){
        return BASEURL+"api/registerapi?regsource=";//注册
    }

    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\apiCommon\FileUploadController.cs(25):        /// <url method="post">/api/fileUpload?image</url>
    //USERIMG
    public static  String UPIMG(String tag){
        //	return BASEURL+"api/fileUpload?image&sysNo=CC_ANDROID&funFlag="+tag;//上传头像
        return "http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag="+tag;//上传头像USERIMG

    }




//	public static  String UP_FEEDBACK_IMG(){
//	 return	BASEURL+"api/fileUpload?image&sysNo=CC_ANDROID&funFlag=FEEDBACK";//上传反馈图片
//	}



    // E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\api\UserController.cs(364):        #region 修改用户昵称头像                        POST api/user?modify
    public static  String MODIFY_HEAD_IMG(){
        return BASEURL+"api/userapi?modify=userImg";//修改头像
    }

    //
    /// <summary>
    ///     修改用户昵称头像
    /// </summary>
    /// <url method="post">/api/user?modify</url>
    /// <get>
    ///     <param name="modify" type="string" required="true" range="userImg:用户头像,nickName:用户昵称,:空" default="[空]">修改的字段</param>
    /// </get>

    public static  String MODIFY_NICKNAME(){
        return BASEURL+"api/userapi?modify=nickName";//修改昵称
    }




    public static  String MODIFY_ZILIAO(){
        return BASEURL+"api/userapi?modify";//修改资料;
    }

    /// <summary>
    ///     根据邮箱获取用户名
    /// </summary>
    /// <url method="get">/api/login?getusername</url>
    public static  String GET_USERNAME(){
        return BASEURL+"api/loginapi?getUsername";//获取用户名
    }


    /// <summary>
    ///     发送重置短信验证码
    /// </summary>
    /// <url method="post">/api/login?smsreset</url>
    public static  String SMS_CODE(){
        return BASEURL+"api/loginapi?smsreset";//重置短信验证码
    }


    /// <summary>
    ///     发送重置邮箱验证码
    /// </summary>
    /// <url method="post">/api/login?emailreset</url>

    public static  String EMAIL_CODE(){
        return BASEURL+"api/loginapi?emailreset";//重置邮箱验证码
    }

    /**
     * @Title: appAccess_count
     * @Description: TODO 记录数
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     */
    public static int appAccess_count(){
        if(UrlManager.BASEURL.equals(UrlManager.FORMAL_URL)){
            return 100;
        }else{
            return 1;
        }

    }


    //  E:\MyWorks\CC2.0\SourceCode\Web.V2.0\CC.Admin\_Code\api\LoginController.cs(165):        /// <url method="post">/api/login?checkcode</url>
    public static  String TEST_CODE(){
        return BASEURL+"api/loginapi?checkCode";//验证验证码
    }

    public static  String RESET_PASS(){
        return BASEURL+"api/loginapi?resetpwd";//重置密码
    }

    public static  String MODIFY_PASS(){
        return BASEURL+"api/userapi?changepwd";//修改密码
    }
    public static  String MODIFY_CCINFO(){
        return BASEURL+"api/userapi?modifybody";//修改穿衣数据
        //return BASEURL+"api/userapi?bodydata";//修改穿衣数据

    }

    public static  String GET_PERSON_INFO(){
        return BASEURL+"api/userapi?bodydata";//获取用户资料

        //	return BASEURL+"api/userapi?getCcInfo";//获取用户资料


    }

    public static  String EMAILTEST_GET_CODE(){
        return BASEURL+"api/userapi?authcode";//邮箱认证-获取邮箱验证码
    }

    public static  String EMAILTEST_BIND(){
        return BASEURL+"api/userapi?emailauth";//邮箱认证-绑定邮箱
    }

    public static  String ACCOUNT_CONTACT(){
        return BASEURL+"api/userapi?getoauths";//账号关联
    }

    /**收货地址*/
    public static  String GET_ADDRESS_LIST(){
        return BASEURL+"api/userapi?listaddr";//获取收货地址列表
    }


    public static  String ADD_ADDRESS(){
        return BASEURL+"api/userapi?addaddr";//添加收货地址;
    }

    public static  String MODIFY_ADDRESS(){
        return BASEURL+"api/userapi?modifyaddr";//修改收货地址
    }

    public static  String SET_DEFAULT_ADDRESS(){
        return BASEURL+"api/userapi?defaultaddr";//设置默认收货地址
    }



    //post 需要参数addressid
    public static  String REMOVE_ADDRESS(){
        return BASEURL+"api/userapi?deleteaddr";//删除收货地址
    }


    //get 需要参数
    //test.myappcc.com/api/userapi?getorder&status=11&pageindex=1&pagesize=10

    public static  String SHOW_ORDER(){
        return BASEURL+"api/userapi?getorder";//删除收货地址
    }




    /**我的钱包*/
    public static  String GET_INVITE_CODE(){
        return BASEURL+"api/userapi?inviteCode";//获取当前用户邀请码
    }

    public static  String GET_CASHROLL_DETAIL(){
        return BASEURL+"api/account?voucherList";///获取现金券明细
    }

    public static  String GET_TRADE_DETAIL(){
        return BASEURL+"api/account?tradeList";//获取交易明细
    }


    /**问题反馈*/
    public static  String ADD_FEEDBACK(){
        return BASEURL+"api/hlp?fbadd";//添加反馈信息
    }


    /**
     * 首页
     */
    public static String GET_HOME_DATA() {
        return BASEURL + "api/apppi";//获取首页数据
    }


    /// <summary>
    ///     APP 更新检测
    /// </summary>
    /// <url method="get">/api/app?update</url>


    //gao 为了跟之前的app区分开最先版本的api借口改成了apppi
    public static String getAPP_CHECK_UPDATA() {
        System.out.println("get请求地址为： " + BASEURL + "api/apppi?update");
        return BASEURL + "api/apppi?update";//APP更新检测
    }

    public static String GET_ACCOUNT() {
        //return BASEURL+"api/account";//获取当前用户的现金券和穿币余额   gao 测试开始

        //	return BASEURL+"api/apppi?GetBanner";//获取当前用户的现金券和穿币余额   gao 测试开始

        //  return BASEURL+"api/userapi?myfav&pageindex=1&pagesize=10";//获取当前用户的现金券和穿币余额   gao 测试开始
        return BASEURL + "api/apppi?buycustomize&id=47159";

    }


    public static String GET_userpage() {
        //return BASEURL+"api/account";//获取当前用户的现金券和穿币余额   gao 测试开始

        //	return BASEURL+"api/apppi?GetBanner";//获取当前用户的现金券和穿币余额   gao 测试开始

        //return BASEURL+"api/loginapi?getonlineuser";//获取当前用户的现金券和穿币余额   gao 测试开始

        // return BASEURL+"api//userapi?usermine";//获取当前用户的现金券和穿币余额   gao 测试开始


        //	return BASEURL+"api/loginapi?getonlineuser";//获取当前用户的现金券和穿币余额   gao 测试开始

        return BASEURL + "api/userapi?myinvitecode";//获取当前用户的现金券和穿币余额   gao 测试开始


    }


    public static String GET_GETREWARD() {
        return BASEURL + "api/activity?getreward";//获取奖励信息
    }


    /**
     * APP访问记录
     */
    public static String APP_START() {
        return BASEURL + "api/AppAccess?start";//APP启动记录
    }


    public static String APP_EVENT() {
        return BASEURL + "api/AppAccess?event";//APP事件记录
    }

    public static String APP_ACCESS() {
        return BASEURL + "api/AppAccess?access";//APP访问记录
    }

    public static String APP_EX() {
        return BASEURL + "api/AppAccess?ex";//APP异常记录
    }

    public static String otherUrl() {
        return DEFAULT_BASEURL.substring(0, 15);
    }

    /*******
     * IM
     *****/
    public static String getImUserInfo() {
        return BASEURL + "api/user?getusers";//
    }

    public static String sendLongText() {
        return BASEURL + "api/im?sendlongmsg";//发送长文本消息
    }

    public static String getLongText() {
        return BASEURL + "api/im?getlongmsg";//拉取长文本消息
    }

    public static String getCfgroups() {
        return BASEURL + "api/im?getcfgroups";//获取众筹群组列表
    }

    public static String getImagepoints() {
        return BASEURL + "api/u3d?getimagepoints";//U3D上传图片并生成点集合
    }

    public static String createHeadmodel() {
        return BASEURL + "api/u3d?createheadmodel";//U3D根据点集合生成头模型
    }


    /*******高伟豪添加20170116*****/


    /**
     * Moment图片上传路径
     */
    public static final String UPLOAD_URLMoment = "http://a1.myappcc.com/api/app/upload?sysNo=CC.APP&funFlag=MOMENT";

    /**
     * Moment发布接口
     */
    public static final String Fabu_Moment() {
        return BASEURL + "api/apppi?piccustomize";
    }


    /**
     * Moment发布接口
     */
    public static final String GET_MYINVITECODE() {
        return BASEURL + "api/userapi?myinvitecode";
    }


    /**
     * 获取我的晒图
     */
    public static final String GET_MYSHAITU() {
        return BASEURL + "api/userapi?Mypic?stype=mine";
    }


    /**
     * 获取我的身体数据
     */
    public static final String GET_MYBODY() {
        return BASEURL + "api/userapi?bodydata";
    }

    /**
     * 获取我的页面数据
     */
    public static final String GET_MYPAGEDATA() {
        return BASEURL + "api/userapi?getmyinfo";
    }


    /**
     * 获取忘记密码短信
     */
    public static final String GET_ForgetCode() {
        return BASEURL + "api/loginapi?SendResetValidCode&mobile=";
    }

//    http://test.myappcc.com/api/loginapi? SendResetValidCode&mobile=xx
//    http://test.myappcc.com/api/loginapi?ResetPassword
//    http://test.myappcc.com/api/loginapi? resetPasswordMobile&validcode=xx&token=xx&mobile=xx

    /**
     * 获取忘记密码发布
     */
    public static String Reset_Password() {
        return BASEURL + "api/loginapi?ResetPassword";//注册
    }

    /**
     * 重置密码的验证码检验
     */
    public static String Reset_YanzhengCode() {
        return BASEURL + "api/loginapi?resetPasswordMobile&validcode=";//xx&token=xx&mobile=xx";//注册
    }


    /**
     * gwh 在这里添加的接口banner
     */
    public static final String Get_Banner() {
        return BASEURL + "api/apppi?GetBanner";//获取banner
    }

    /**
     * gwh 在这里添加的接口 water
     */
    public static final String Get_Water() {
        return BASEURL + "api/apppi?getindexNewlist&pageIndex=1&pageSize=10";//获取banner
    }

    public static final String Add_Water() {
        return BASEURL + "api/apppi?getindexNewlist&pageIndex=";//2&pageSize=10";//获取banner
    }

    //        test.myappcc.com/api/apppi?zhuanti&pageindex=1&id=7

    /**
     * gwh 在这里添加的接口 专题瀑布流
     */
    public static final String Get_ZhuantiWater() {
        return BASEURL + "api/apppi?zhuanti&pageindex=1&id=7";//获取专题
    }


    public static final String Get_ZhuantiMoreWater() {
        return BASEURL + "api/apppi?zhuanti&pageindex=";//+1&id=7";//获取专题
    }


//    http://test.myappcc.com/api/ccorderapi?AddNewOrder


    public static final String Send_Pay() {
        return BASEURL + "api/ccorderapi?AddNewOrder";//+1&id=7";//获取专题
    }


    // http://test.myappcc.com/api/userapi?mypic&page=1&stype=mine  获取晒图


    public static final String Get_Shaitu() {
        return BASEURL + "api/userapi?mypic&page=1&stype=mine";//+1&id=7";//获取晒图
    }


//    http://test.myappcc.com/api/userapi?myfav&pageindex=1&pagesize=10


//  {"Code":1,"Msg":"OK","Data":
// {"Pagedata":
// {"CurrentPage":1,"PageSize":8,"Total":70,"Rows":
// [{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-02-11 17:16:28","Images":["http://f1.myappcc.com/zfs/7E1/1042/RIC/042171608352CABHHRASJZ.jpg"],"Id":75644,"UserId":29122,"OrderId":0,"ProductId":60011,"SharedTime":"2017-02-11 17:16:28","Content":"","ReviewTimes":0,"ViewTimes":11,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-02-11 17:20:53","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1042/RIC/042171608352CABHHRASJZ.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":75644},
// {"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-02-09 19:36:10","Images":["http://f1.myappcc.com/zfs/7E1/1040/TJK/040193705547CABHCPFVTD.jpg"],"Id":75140,"UserId":29122,"OrderId":0,"ProductId":59595,"SharedTime":"2017-02-09 19:36:10","Content":"","ReviewTimes":0,"ViewTimes":12,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-02-09 19:36:37","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1040/TJK/040193705547CABHCPFVTD.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":75140},
// {"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-02-03 00:39:32","Images":["http://f1.myappcc.com/zfs/7E1/1034/AZA/034004016543CABHEUSXZU.jpg"],"Id":73713,"UserId":29122,"OrderId":0,"ProductId":58468,"SharedTime":"2017-02-03 00:39:32","Content":"","ReviewTimes":0,"ViewTimes":119,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-02-03 09:37:48","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1034/AZA/034004016543CABHEUSXZU.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":73713},
// {"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-02-02 15:15:23","Images":["http://f1.myappcc.com/zfs/7E1/1033/PQF/033151551247CABHWZMXDD.jpg"],"Id":73581,"UserId":29122,"OrderId":0,"ProductId":0,"SharedTime":"2017-02-02 15:15:23","Content":"","ReviewTimes":0,"ViewTimes":4,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":2,"OfferTime":"0001-01-01 00:00:00","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1033/PQF/033151551247CABHWZMXDD.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":73581},
// {"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-01-22 19:53:55","Images":["http://f1.myappcc.com/zfs/7E1/1022/TJK/022195413869CABHVXGNER.jpg","http://f1.myappcc.com/zfs/7E1/1022/TJK/022195420228CABHMVKVID.jpg"],"Id":71962,"UserId":29122,"OrderId":0,"ProductId":0,"SharedTime":"2017-01-22 19:53:55","Content":"","ReviewTimes":0,"ViewTimes":67,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":2,"OfferTim


//    {"Code":1,"Msg":"OK","Data":
//        {"Pagedata":
//            {"CurrentPage":1,"PageSize":8,"Total":70,"Rows":
//                [{"MomentTypeDesc":"定制发图","UserName":null,"SharedDay":null,"SharedMonth":0,"ReviewSum":0,"FriendReview":null,"FriendReviewTop3":null,"UserImg":null,"UserNickName":null,"ProductPrice":0.0,"ProductName":null,"ContentBrief":"","IsPraise":false,"SharedTimeStr":"2017-02-11 17:16:28","Images":["http://f1.myappcc.com/zfs/7E1/1042/RIC/042171608352CABHHRASJZ.jpg"],"Id":75644,"UserId":29122,"OrderId":0,"ProductId":60011,"SharedTime":"2017-02-11 17:16:28","Content":"","ReviewTimes":0,"ViewTimes":11,"PraiseTimes":0,"MomentType":1,"IsPrivate":0,"IsOffer":1,"OfferTime":"2017-02-11 17:20:53","Tag":"上装","ImageNum":1,"Image1":"http://f1.myappcc.com/zfs/7E1/1042/RIC/042171608352CABHHRASJZ.jpg","Image2":"","Image3":"","Image4":"","Image5":"","Image6":"","Image7":"","Image8":"","Image9":"","RegApp":"","PkValue":75644}]}}}


    //第一次加载我的收藏

    public static final String Get_SaveItem() {
        return BASEURL + "api/userapi?myfav&pageindex=1&pagesize=10";//+1&id=7";//获取我的收藏
    }


    //顺序加载我的收藏
    public static final String Get_MoreSaveItem() {
        return BASEURL + "api/userapi?myfav&pageindex=";//+1+"&pagesize=10";//+1&id=7";//获取我的收藏
    }


//    {"Code":1,"Msg":"OK","Data":
//        [{"Id":7289,"UserId":29122,"ProductId":52987,"ProductName":"女装2017年高级仿皮草双层中款外套","MainImage":"http://f1.myappcc.com/zfs/7E1/1005/LGU/005110731125CABHCKFSXY.jpg","Price":780.0000,"CollectTime":"2017-01-05 12:38:07","NewPrice":780.0000,"PkValue":7289}]}


//{"Code":1,"Msg":"OK","Data":{"PayMomeny":100.0,"OrderId":9850}}


    //开始调用支付  http://test.myappcc.com/api/ccorderapi?Pay&orderid=xxx&payType
    public static final String Get_PAYACTION() {
        return BASEURL + "api/ccorderapi?Pay&orderid=";//xxx&payType";
    }

    public static final String weChat_Pay_OK(String orderid) {
        return BASEURL + "api/ccorderapi?Paids&orderId="+orderid+"&payType=1";//xxx&payType";
    }


    /**
     * lws 在这里添加的接口zhuanti
     */
    public static final String Get_ZhuanTi() {
        return BASEURL + "api/apppi?zhuanti&pageindex=";
    }

    /**
     * lws 在这里添加的接口产品详情
     */
    public static final String Get_DIngZhi() {
        return BASEURL + "api/apppi?buycustomize&id=";
    }

    /**
     * lws 在这里添加的接口定制详情
     */
    public static final String GetChengPin() {
        return BASEURL + "api/apppi?dingzhiitem&id=";
    }

    /**
     * lws 在这里添加的接口获取个人信息
     */
    public static final String Get_UserGerenXinXi() {
        return BASEURL + "api/userapi?GetUserGerenXinXi";
    }

    /**
     * lws 在这里添加的接口获取协议
     */
    public static final String GET_Protocol() {
        return "http://ios.myappcc.com/h5/dingzhi.html";
    }

    /**
     * lws 在这里添加的接口获取收货地址
     */
    public static final String GET_ADDRESS() {
        return BASEURL + "api/userapi?listaddr";
    }

    /**
     * lws 在这里添加的接口设置默认地址
     */
    public static final String set_defaultaddr() {
        return BASEURL + "api/userapi?defaultaddr";
    }

    /**
     * lws 在这里添加的添加到收藏
     */
    public static final String Addfav() {
        return BASEURL + "api/userapi?Addfav&productid=";
    }

    /**
     * lws 在这里添加的获取定制成品
     */
    public static final String Getdingzhilist(String page) {
        return BASEURL + "api/apppi?getdingzhilist&pageIndex=" + page + "&pageSize=10";
    }

    /**
     * lws 在这里添加的获取定制成品
     */
    public static final String GetOederlist(int status, int page) {
        return BASEURL + "api/userapi?getorder&status=" + status + "&pageIndex=" + page + "&pageSize=50";
    }

    /**
     * lws 在这里添加的获取资产
     */
    public static final String GetTradelist(int page, int typeID) {
        return BASEURL + "api/userapi?GetAssetTradeList&typeid=" + typeID
                + "&pageIndex=" + page;
    }

    /**
     * lws 在这里添加的获取账户信息
     */
    public static final String accountManage() {
        return BASEURL + "api/userapi?accountManage";
    }

    /**
     * lws 在这里添加的获取更新账户
     */
    public static final String upDateAccountManage() {
        return BASEURL + "api/userapi?updateAccount";
    }

    /**
     * lws 在这里添加的支付密码
     */
    public static final String addPayPassword() {
        return BASEURL + "api/userapi?AddPayPassword";
    }

    /**
     * lws 在这里添加的修改支付密码
     */
    public static final String ResetPassword() {
        return BASEURL + "api/userapi?ResetPassword";
    }

    /**
     * lws 在这里添加的支付密码
     */
    public static final String cashMoney() {
        return BASEURL + "api/userapi?cashMoney";
    }

    /**
     * lws 在这里添加的充值
     */
    public static final String excuteCharger(double chargeAmount, int type, String regApp) {
        return BASEURL + "api/userapi?ExecuteCharge&chargeAmount=" + chargeAmount + "&type=" + type + "&regApp=" + regApp;
    }

    /**
     * lws 在这里添加的充值成功回调
     */
    public static final String paid(int payStatus, int chargeId) {
        return BASEURL + "api/userapi?Paid&payStatus=" + payStatus + "&chargeId=" + chargeId;
    }

    /**
     * lws 在这里添加的取消收藏
     */
    public static final String Delfav() {
        return BASEURL + "api/userapi?Delfav";
    }


    /**
     * lws 在这里添加的是否有支付密码
     */
    public static final String checkpaypwd() {
        return BASEURL + "api/userapi?checkpaypwd";
    }

    /**
     * lws 在这里添加的删除订单
     */
    public static final String deleteord() {
        return BASEURL + "api/ccorderapi?DeleteOrder";
    }

    /**
     * c
     * lws 在这里添加的取消订单
     */
    public static final String cancelOrder() {
        return BASEURL + "api/ccorderapi?CancelOrder";
    }

    /**
     * c
     * lws 在这里添加的确认收获
     */
    public static final String confirmReceive() {
        return BASEURL + "api/ccorderapi?confirmReceive";
    }

    /**
     * c
     * lws 在这里添加的退款
     */
    public static final String refund() {
        return BASEURL + "api/ccorderapi?refund";
    }

    /**
     * c
     * lws 在这里添加的退款查询
     */
    public static final String reviewRefound(String id) {
        return BASEURL + "api/ccorderapi?reviewRefound&orderId=" + id;
    }

    /**
     * c
     * lws 在这里添加的订单
     */
    public static final String orderDetail(String id) {
        return BASEURL + "api/ccorderapi?orderDetail&id=" + id;
    }
    /**
     * c
     * lws 在这里添加的SaveBodyData
     */
    public static final String SaveBodyData() {
        return BASEURL + "api/ccorderapi?SaveBodyDataNew";
    }
    /**
     * c
     * lws 在这里添加的更改地区
     */
    public static final String changeArea() {
        return BASEURL + "api/userapi?ChangeArea";
    }

    public static final String keyWordSearch(String key_word, int page, String order_by) {
        return "http://test.myappcc.com/api/apppi?getsearch&key=" + key_word + "&page=" + page + "&orderby=" + order_by;
    }

    public static final String findHeader() {
        return BASEURL + "api/apppi?Gettopsix";
    }

    /**
     * 史力：发现页面加载瀑布流
     *
     * @param pageIndex 页码
     * @return
     */
    public static final String findWater(int pageIndex) {
        return BASEURL + "api/apppi?getFaXianTopics&pageIndex=" + pageIndex;
    }

    public static final String getSearchArticles(String guanjianci, int topicID) {
        return BASEURL + "api/apppi?getWenZhangByName&guanjianzi=" + guanjianci + "&state=1&topicsId=" + topicID;
    }

    public static final String testGWH() {
        return BASEURL + "api/apppi?getmyyaoqingfirend";
    }

    public static final String findSecondWater(int topicID, int state, int index, String key_word) {
        return "http://test.myappcc.com/api/apppi?GetCoattpPage&topicsId=" + topicID +
                "&state=" + state + "&pageindex=" + index +
                "&guanjianzi=" + key_word;
    }

    public static final String getPush(String token,String time) {
        return BASEURL + "api/apppi?umenghistory&usertoken="+token+"&timerequest="+time;
    }
}
