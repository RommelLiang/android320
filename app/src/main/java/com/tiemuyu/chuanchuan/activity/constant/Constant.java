package com.tiemuyu.chuanchuan.activity.constant;

import com.tiemuyu.chuanchuan.activity.util.SdcardStoragellocator;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class Constant {
    /**
     * 测试模式标记（true：测试环境； false：正式环境）
     */
    public static boolean DEBUG_MODEL = false;
    /**
     * URL地址 false ：外网地址； true：内网地址(测试环境修改此选项)
     */
    public static boolean URL_Debug_Model = false;// (URL)

    /**
     * 请求返回的code
     */
    public final static int REQUEST_NOPOWER = -1;//无权限
    public final static int REQUEST_FAIL = 0;//操作失败
    public final static int REQUEST_OK = 1;//成功


    /**
     * 版本说明
     */
    public static String VERSION;

    /**
     * 历史用户记录文件名
     */
    public static String SHARED_HISTORY_USER = "com.tiemuyu.chuanchuan.history_users";
    /**
     * 数据记录文件名
     */
    public static String SHARE_DATA = "com.tiemuyu.chuanchuan.client_preferences";
    /**
     * 程序MdData记录文件名
     */
    public static String SHARE_MDNUM = "com.tiemuyu.chuanchuan.mdNum";
    /**
     * 用户是否第一次进入
     */
    public static String FIRST_ENTER = "com.tiemuyu.chuanchuan.first_enter";

    public static String SHARE_NOTIFY = "com.tiemuyu.chuanchuan.notify_preferences";

    public static String GBNAMERESP = "MSG_ACTIONRESP";

    public static String LOGINMSG = "LOGINMSG";
    public static String AULOGINMSG = "AULOGINMSG";//自动登录
    public static String REGISTMSG = "REGISTMSG";

    public static String UPUSERDATA = "UPUSERDATA";

    public static String THIRD_ALLOGIN = "THIRD_ALLOGIN";

    //public static String AU_LOGINMSG = "AU_LOGINMSG";

    public static String APPCHECK = "APPCHECK";

    public static String AULOGIN_ACTION = "AULOGIN_ACTION";
    public static String RESETDATA = "RESETDATA";//重新设置数据
    public static String RESETIMDATA = "RESETIMDATA";//重新设置im登录

    public static String APPNETCHOOSE = "APPNETCHOOSE";

    public static String DATA_VERSION="DataVersion";

    public static String DATA_UPDATA_HOME="DATA_UPDATA_HOME";

    public static String UPDATA_ACCOUNT="UPDATA_ACCOUNT";//获取现金券与穿币余额

    public static String IMNONET="IMNONET";//IM断网
    public static String IMCONNET="IMCONNET";//IM联网

    public static String TOHOME = "TOHOME";//跳转到首页
    public static String TOMESSAGE = "TOMESSAGE";//跳转到消息列表
    public static String TODISCOVERY = "TODISCOVERY";//跳转到发现
    public static String TOSHOOL = "TOSHOOL";//跳转到学堂
    public static String TOCHAT = "TOCHAT";//跳转到聊天界面
    public static String IMMSG = "IMMSG";//im通知
    public static String REFRESH_LISTVIEW = "REFRESH_LISTVIEW";//刷新listwebview
    public static String U3DPHOTO_OK = "U3DPHOTO_OK";//u3d拍照成功后

    public static String HTMLPAGE = "HTMLPAGE";//HTML页面

    public final static String LOGIN_OK ="LOGIN_OK";//登录成功

    /**
     * 访问记录
     * */
   // public static List<AppAccessBean>aabs=new ArrayList<AppAccessBean>();//访问记录集合  gao 暂时停止
    public static int aabs_count;//访问记录数
    public static String LasetViewId="0";//访问记录-上一视图

    public static String PASSKEY="PASSKEY";//passkey
    public static String IPPORT="IPPORT";//IPPORT

    /**
     * h5交互
     * */
    public static String CC_LOGIN = "CC_LOGIN";//
    public static String CC_REGIST = "CC_REGIST";//注册
    public static String CC_HOME = "CC_HOME";//
    public static String CC_BACK = "CC_BACK";//
    public static String CC_LOADINGSTART = "CC_LOADINGSTART";//
    public static String CC_LOADINGEND = "CC_LOADINGEND";//
    public static String CC_TIMEOUT = "CC_TIMEOUT";//
    public static String CC_PAY = "CC_PAY";//
    public static String CC_TOAST = "CC_TOAST";
    public static String CC_ADDRESS = "CC_ADDRESS";//
    public static String CC_MODIFY_ADDRESS = "CC_MODIFY_ADDRESS";//
    public static String CC_FEEDBACK = "CC_FEEDBACK";//
    public static String CC_SHARE = "CC_SHARE";//
    public static String CC_UPDATA_ACCOUNT="CC_UPDATA_ACCOUNT";//
    public static String CC_UPLOAD_IMAGE="CC_UPLOAD_IMAGE";//
    public static String CC_RESULT="CC_RESULT";//
    public static String CC_INVITE="CC_INVITE";//邀请
    public static String CC_ISLOGIN="CC_ISLOGIN";//是否登录
    public static String CC_BACK_BTN="CC_BACK_BTN";
    public static String CC_MODIFY_TITLE="CC_MODIFY_TITLE";
    public static String CC_PREV="CC_PREV";//prev
    public static String CC_TVRIGHT="CC_TVRIGHT";//

    public static String CC_UA="CC_UA";//UA
    public static String CC_COOKIE="CC_COOKIE";//cookie
    public static String CC_IFLOGIN="CC_IFLOGIN";//登录与否
    public static String CC_IMOPEN="CC_IMOPEN";//im服务
    public static String CC_LOGINOUT="CC_LOGINOUT";//用户退出

    /**
     * 文件地址
     * */
    // SDCard路径
    public static final String SD_PATH = SdcardStoragellocator.getExternalStoragePath();

    // 项目SDCard路径
    public static final String BASE_PATH = SD_PATH + "/CC";
    // 图片路径
    public static final String BASE_IMAGE = BASE_PATH + "/images";
    // 缓存图片路径(如拍照的原图)
    public static final String BASE_IMAGE_CACHE = BASE_IMAGE + "/cache";
    // 裁剪后的图片路径
    public static final String BASE_IMAGE_NEW = BASE_IMAGE + "/clipimages";

    public static final String BASE_IMAGE_U3D = BASE_IMAGE + "/u3ds";

    // imagerlode
    public static final String BASE_IMAGE_IMAGELODE_CACHE = "/CC/images/imageloadercache";

    // imagerlode
    public static final String BASE_IMAGE_IMAGE = "/CC/images/image";

    //下载路径
    public static final String BASE_IMAGE_DOWNLOAD = BASE_IMAGE + "/download";

    public static final String BASE_DBPATH = BASE_PATH + "/CCDB";

    public static final String IMG_HEAD1="file:/";
    public static final String BASE_DRAWABLE="drawable://";

    public static final String BASE_URL_NAME="BASE_URL_NAME";
    public static final String BASE_DOMAIN_NAME="BASE_DOMAIN_NAME";
    public static final String BASE_HJ_NAME="BASE_HJ_NAME";


    /**请求action    get 1    ;post  2*/
    public static final int REQUEST_GET=1;
    public static final int REQUEST_POST=2;


    /**resultCode*/
    public static final int REQUEST_RESULTCODE1=101;
    public static final int REQUEST_RESULTCODE2=102;
    public static final int REQUEST_RESULTCODE3=103;

    /**请求字段*/
    public static final String APP_IP="IP";
    public static final String LOGONSYS="LogonSys";
    public static final String SID="sid";
    public static final String CLIENTTYPE="ClientType";
    public static final String CLIENTVER="ClientVer";
    public static final String LOGIN_V="v";
    public static final String MOBILE="mobile";
    public static final String INVITE="inviteCode";
    public static final String EMAIL="email";

    public static final String CODE="code";
    public static final String INVITEDCODE="inviteCode";
    public static final String OAUTHID="oauthId";
    public static final String TOKEN="token";

    public static final String GETPASSKEY="PassKey";//PassKey
    public static final String USERIMG="userImg";//用户头像地址
    public static final String NICKNAME="nickName";//昵称

    public static final String TYPE="type";//验证码类型

    public static final String CONTACT="Contact";//联系人
    public static final String PROVINCE="Province";//省份
    public static final String CITY="City";//城市
    public static final String DISTRICT="District";//区县
    public static final String ADDRESS="Address";//详细地址
    public static final String POSTCODE="PostCode";//邮编
    public static final String MOBILE_D="Mobile";
    public static final String MODIFY_ID="Id";
    public static final String ADDRESS_ID="addressId";
    public static final String User="User";
    public static final String isDefault="isdefault";


    public static final String ORDINFO="ordInfo";

    public static final String PROID="proId";





    //发布图片  高伟豪
    public static final String TAG="tag";
    public static final String TEXT="detail";
    public static final String IMGS="imgs";









    // order
    public static final String STATUS="status";
    public static final String PAGE_INDEX="pageindex";
    public static final String PAGE_SIZE="pagesize";




    public static final String PAGEINDEX="pageIndex";
    public static final String PAGESIZE="pageSize";
    public static final String INEXTYPE="inExType";
    public static final String TRADETYPE="tradeType";

    public static final String SYSTYPE="SysType";
    public static final String EQUIPMENTTYPE="EquipmentType";
    public static final String FBCONTENT="FbContent";
    public static final String IMGNAMEARR="imgNameArr";
    public static final String IMIDS="ids";


    public static final String LOGIN_OAUTHNAME="OauthName";
    public static final String LOGIN_OAUTHOPENID="OauthOpenId";
    public static final String LOGIN_OAUTHTOKEN="OauthToken";
    public static final String LOGIN_USERIMG="UserImg";
    public static final String LOGIN_NICKNAME="NickName";

    public static final String APPSTART_STARTTIME="StartTime";
    public static final String APPSTART_ROSOLUTION="Resolution";
    public static final String APPSTART_LANGUAGE="DeviceLanguage";
    public static final String APPSTART_NETWORKTYPE="NetworkType";
    public static final String APPSTART_NETWORTPROVIDER="NetworkProvider";
    public static final String APPSTART_LOCALTIME="LocalTime";
    public static final String APPSTART_LOCALTIMEAREA="LocalTimeArea";
    public static final String APPSTART_SYSNO="SysNo";


    public static final String RE_INVITE="invite";
    public static final String RE_REGIST="register";
    public static final String RE_CTTEST="cttest";


    public static final String APPSTART_JSONSTR="JsonStr";



    /**测试添加*/







    /** IM发送长消息参数 */
    public static final String MSG="Msg";//消息内容
    public static final String ID="Id";//消息id
    public static final String SENDUSERID="SendUserId";//发送用户id
    public static final String RECVID="RecvId";//接收者id
    public static final String RECVTYPE="RecvType";//接收者类型

    /** IM拉取长消息参数 */
    public static final String RID="id";//消息id
    public static final String RSENDUSERID="sendUserId";//发送者id


    /** 微信支付 */
    // appid
    public static  String APP_ID = "";// yum
    // 商户号
    public static final String MCH_ID = "";// yum
    // API密钥，在商户平台设置
    public static final String API_KEY = "";// yum


    /**
     * 穿衣相关字段
     * */
    public static final String GENDER="Gender";//性别
    public static final String AGE="Age";//年龄
    public static final String WEIGHT="Weight";//体重
    public static final String HEIGHT="Height";//身高
    public static final String CLOTHSIZE="ClothSize";//您现在穿的尺码
    public static final String PHYSIQUE="Physique";//体型
    public static final String SHOULDERBREADTH="ShoulderBreadth";//肩宽
    public static final String BUST="Bust";//胸围
    public static final String WAIST="Waist";//腰围
    public static final String HIP="Hip";//臀围
    public static final String SLEEVE="Sleeve";//袖长
    public static final String THIGHCIRC="ThighCirc";//大腿围
    public static final String CALFCIRC="CalfCirc";//小腿围
    public static final String KNEECIRC="KneeCirc";//膝围
    public static final String ARMCIRC="ArmCirc";//臂围
    public static final String PANTS="Pants";//裤长
    public static final String TEBIESHUOMING="teBieShuoMing";//特别说明
    public static final String CHUANYIXIGUAN="chuanYiXiGuan";//穿衣习惯
    public static final String GEXINGNAME="geXingName";//个性名称
    public static final String ISRADIO="isRadio";//私人时装品牌标识

    public static final String CTTYPE="CtType";//衣型测试类型
    public static final String CTTYPENAME="CtTypeName";//衣型测试类型




    /**
     * 账号关联的字段
     * */
    public static final String QQ="QQ";//QQ
    public static final String Wechat="Wechat";//微信
    public static final String Weibo="Weibo";//微博
    public static final String Email="Email";//邮箱

    /**
     * 渠道
     * */
    public static final String UMENG_CHANNEL="UMENG_CHANNEL";


    /**
     * 高伟豪新加
     * */
    public static final String FATU_REFRESH="FATU_REFRESH";//裤长








}
