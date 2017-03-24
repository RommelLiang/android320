package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.bean.Base;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.ClothesDetail;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.MessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;
import com.tiemuyu.chuanchuan.activity.util.GlideImageLoader;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClothesDetailsActivity extends BaseActivityG implements NetResponses {
    public String tag = "MESSAGE";
    private Intent mIntent;
    private int productid;
    private final String TAG_GET_buycustomize = "TAG_GET_buycustomize";
    private final String TAG_POST_Addfav = "TAG_POST_Addfav";
    private final String TAG_POST_Delfav = "TAG_POST_Delfav";
    private ClothesDetail clothesDetail;
    private ImageView im_user_header, im_back, im_suggest, im_must_know,im_share;
    private TextView tv_user_name, tv_price, tv_product_name, tv_suggest, tv_shu_ju, tv_fu_wu, tv_shi_jian;
    private WebView webView;
    private Banner banner;
    private List<String> images;
    private boolean suggest_isExpend = false;
    private boolean xuzhi_isExpend = false;
    private RelativeLayout rl_dingzhi;
    private LinearLayout ll_nei_rong,ll_banner,ll_ke_fu,ll_shou_cang,ll_zhi_fu;
    private boolean isFav = false;
    private ImageView im_collect;
    private String title,url,img_url;
    private ArrayList<Contacts> dataList = new ArrayList<>();
    public static String OWN_HEADER_URL = "";//gao 在这里更改有效
    private DataSharedPress sharedPress;
    private boolean isLogIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_detail);
        mIntent = getIntent();
        initView();
        Log.e("URL", "onCreate: " + UrlManager.Get_DIngZhi() + productid);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GET_buycustomize,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.Get_DIngZhi() + productid),
                        this,
                        "获取产品信息",
                        false));
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_nei_rong.setVisibility(View.GONE);
        setNetResponses(this);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        productid = mIntent.getIntExtra("productid",0);
        Log.e("productid", "initView: "+productid);
        im_user_header = (ImageView) findViewById(R.id.im_user_image_detail);
        tv_user_name = (TextView) findViewById(R.id.tv_name_detail);
        tv_price = (TextView) findViewById(R.id.tv_jia_ge_detail);
        tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        tv_suggest = (TextView) findViewById(R.id.tv_suggest);
        banner = (Banner) findViewById(R.id.clothes_banner);
        im_back = (ImageView) findViewById(R.id.im_back);
        webView = (WebView) findViewById(R.id.web_view_price);
        im_suggest = (ImageView) findViewById(R.id.im_suggest);
        im_must_know = (ImageView) findViewById(R.id.im_must_know);
        rl_dingzhi = (RelativeLayout) findViewById(R.id.rl_dingzhi);
        ll_nei_rong = (LinearLayout) findViewById(R.id.ll_nei_rong);
        tv_shu_ju = (TextView) findViewById(R.id.tv_shu_ju);
        tv_fu_wu = (TextView) findViewById(R.id.tv_fu_wu);
        tv_shi_jian = (TextView) findViewById(R.id.tv_shi_jian);
        ll_banner = (LinearLayout) findViewById(R.id.ll_banner);
        ll_ke_fu = (LinearLayout) findViewById(R.id.ll_ke_fu);
        ll_shou_cang = (LinearLayout) findViewById(R.id.ll_shou_cang);
        ll_zhi_fu = (LinearLayout) findViewById(R.id.ll_zhi_fu);
        im_collect = (ImageView) findViewById(R.id.im_collect);
        im_share = (ImageView) findViewById(R.id.im_share);
        tv_shu_ju.setText(
                Html.fromHtml("可在" +
                        "<font color=\"#450525\"><b>“我的”-“身体数据”</b></font>" +
                        "中填写并保存\n可在下单后弹出的体型数据页面中填写")
        );
        tv_fu_wu.setText(
                Html.fromHtml("下单后" +
                        "<font color=\"#450525\"><b>6小时内</b></font>" +
                        "，可与客服沟通定制要求（面料、款式等）逾期将直接进入面料采购及制版流程下单后" +
                        "<font color=\"#450525\"><b>5日内</b></font>，如遇面料问题造成无法制衣，穿穿将全额退款并赔偿20元穿币")
        );
        tv_shi_jian.setText(
                Html.fromHtml("根据面料采购难度和制作工艺复杂度，自下单之日起" +
                        "<font color=\"#450525\"><b>7-20</b></font>" +
                        "内发货，以第三方物流记录的发货时间为准" +
                        "\n因用户信息确认等所造成的时间延误会直接在原发货时长基础上延长")
        );
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, final boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_GET_buycustomize)) {
            Log.e("successCallBack", "successCallBack: " + callBackMsg);
            images = new ArrayList<>();
            clothesDetail = (new Gson()).fromJson(callBackMsg, ClothesDetail.class);
            for (ClothesDetail.DataBean.ImagesBean imagesBean : clothesDetail.getData().getImages()) {
                images.add(imagesBean.getImage());
            }
            Log.e("clothesDetail", "successCallBack: " + clothesDetail.getData().getUser().getUserImg());
            Picasso.with(this).load(clothesDetail.getData().getUser().getUserImg()).placeholder(R.drawable.circle_logo).into(im_user_header);
            tv_user_name.setText(clothesDetail.getData().getUser().getNickName());
            tv_product_name.setText(clothesDetail.getData().getProduct().getProductName());
            tv_price.setText("￥ " + clothesDetail.getData().getProduct().getPrice());
            final String textStr1 = "<font color=\"#450525\"><b>报价师留言:</b></font>";
            String textStr2 = "<font color=\"#5A5959\">"
                    + clothesDetail.getData().getProduct().getDescription() +
                    "</font>";
            tv_suggest.setText(Html.fromHtml(textStr1 + textStr2));
            im_suggest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!suggest_isExpend) {
                        tv_suggest.setMinLines(0);
                        tv_suggest.setMaxLines(Integer.MAX_VALUE);
                        im_suggest.setBackground(getResources().getDrawable(R.drawable.home_up));
                        suggest_isExpend = true;
                    } else {
                        tv_suggest.setMaxLines(3);
                        im_suggest.setBackground(getResources().getDrawable(R.drawable.home_down));
                        suggest_isExpend = false;
                    }
                }
            });
            rl_dingzhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!xuzhi_isExpend) {
                        ll_nei_rong.setVisibility(View.VISIBLE);
                        xuzhi_isExpend = true;
                        im_must_know.setBackground(getResources().getDrawable(R.drawable.home_up));
                    } else {
                        ll_nei_rong.setVisibility(View.GONE);
                        xuzhi_isExpend = false;
                        im_must_know.setBackground(getResources().getDrawable(R.drawable.home_down));
                    }
                }
            });
            banner.setImageLoader(new GlideImageLoader())
                    .setImages(images).start();
            if (images.size() == 1) {
                ll_banner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ClothesDetailsActivity.this,ImageDetailsActivity.class);
                        intent.putStringArrayListExtra("images", (ArrayList<String>) images);
                        intent.putExtra("position",0);
                        startActivity(intent);
                    }
                });
            }
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Log.e("OnBannerClick", "OnBannerClick: "+position);
                    Intent intent = new Intent(ClothesDetailsActivity.this,ImageDetailsActivity.class);
                    intent.putStringArrayListExtra("images", (ArrayList<String>) images);
                    intent.putExtra("position",position - 1);
                    startActivity(intent);
                }
            });
            webView.loadDataWithBaseURL(null, "<table>" + clothesDetail.getData().getCostHtml() +
                            "</table>",
                    "text/html", "UTF-8", null);


            ll_ke_fu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kefu();
                }
            });
            ll_zhi_fu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!PreferenceUtils.getPrefBoolean(ClothesDetailsActivity.this, Constant.CC_IFLOGIN, false))
                    {
                        Toast.makeText(ClothesDetailsActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(ClothesDetailsActivity.this, PayMentActivity.class);
                    intent.putExtra("productid",productid);
                    intent.putExtra("image",clothesDetail.getData().getProduct().getMainImage());
                    intent.putExtra("detial",clothesDetail.getData().getProduct().getProductName());
                    intent.putExtra("price",clothesDetail.getData().getProduct().getPrice() +"");
                    startActivity(intent);
                }
            });
            im_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    title = clothesDetail.getData().getProduct().getProductName();
                    img_url = clothesDetail.getData().getProduct().getMainImage();
                    url = "http://ios.myappcc.com/cc/Customize/BuyCustomize?id="+ clothesDetail.getData().getProduct().getId() +"&type=2";
                    share();
                }
            });
            isFav = clothesDetail.getData().isIsFve();
            if (isFav) {
                im_collect.setBackground(getResources().getDrawable(R.drawable.heart));
            } else {
                im_collect.setBackground(getResources().getDrawable(R.drawable.tab_collect));
            }
            ll_shou_cang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!PreferenceUtils.getPrefBoolean(ClothesDetailsActivity.this, Constant.CC_IFLOGIN, false))
                    {
                        Toast.makeText(ClothesDetailsActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.e("isFav", "onClick: "+isFav);
                    if (!isFav) {
                        addToFav();
                    } else {
                        delToFav();
                    }
                }
            });
        } else if(resultTag.equals(TAG_POST_Addfav)) {
            Log.e("TAG_POST_Addfav", "successCallBack: "+callBackMsg );
            Base base = GsonUtils.fromData(callBackMsg, Base.class);
            if (base.getCode() == 1) {
                Toast.makeText(this, base.getMsg(), Toast.LENGTH_SHORT).show();
                im_collect.setBackground(getResources().getDrawable(R.drawable.heart));
                isFav = true;
            }
        } else if (resultTag.equals(TAG_POST_Delfav)) {
            Base base = GsonUtils.fromData(callBackMsg, Base.class);
            if (base.getCode() == 1) {
                Toast.makeText(this, base.getMsg(), Toast.LENGTH_SHORT).show();
                im_collect.setBackground(getResources().getDrawable(R.drawable.tab_collect));
                isFav = false;
            }
        }
    }
    private int count = 0;
    private void kefu() {
        if (!PreferenceUtils.getPrefBoolean(ClothesDetailsActivity.this, Constant.CC_IFLOGIN, false))
        {
            Toast.makeText(ClothesDetailsActivity.this, "请登录", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isLogIn) {
            Intent intent = new Intent(ClothesDetailsActivity.this, MessageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", dataList);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }else {
            login();
        }
    }

    private void addToFav() {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(ClothesDetailsActivity.this,
                        TAG_POST_Addfav,
                        Constant.REQUEST_POST,
                        ParamsTools.AddFavorites(
                                UrlManager.Addfav()+
                                productid),
                        ClothesDetailsActivity.this,"添加收藏",
                        false));
    }
    private void delToFav() {
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(ClothesDetailsActivity.this,
                        TAG_POST_Delfav,
                        Constant.REQUEST_POST,
                        ParamsTools.delFavorites(
                                UrlManager.Delfav(),productid+""),
                        ClothesDetailsActivity.this, "取消收藏",
                        false));
    }
    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack", "failCallBack: " + arg0.getLocalizedMessage());
    }
    public void share() {
        new ShareAction(this).setDisplayList(
                SHARE_MEDIA.SINA,
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SMS,
                SHARE_MEDIA.MORE)
                .withTitle(title)
                .withText("穿穿，一座由你做主的时装定制工厂")
                .withTargetUrl(url)
                .withMedia(new UMImage(getApplicationContext(), img_url))
                .setCallback(umShareListener)
                .open();
        System.out.println("ShareAction opened in mainactivity..  saveload..........");
    }

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onResult(SHARE_MEDIA platform) {
            com.umeng.socialize.utils.Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(getApplicationContext(), "收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(getApplicationContext(), " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getApplicationContext(), "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getApplicationContext(), "分享取消了", Toast.LENGTH_SHORT).show();
        }

    };

    public void login() {
        //LoginInfo loginInfo = new LoginInfo("tmy1", "68c58f02597daa4fdc3ab86ed103e0c6");//测试账号
        if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false)==false)
        {
            return;
        }
        User now= DBTools.getUser();
        if (now.getAccid() == null) {
            Toast.makeText(this,  "网络异常" , Toast.LENGTH_SHORT).show();
        }
        LoginInfo loginInfo = new LoginInfo(now.getAccid(), now.getAccToken());
//        System.out.println("accid after new LoginInfo: " + accid);
//        System.out.println("acctoken after new LoginInfo: " + token);
        String getinfo = now.getUserImg();
        ///sp.getString("userimag", "");
        //获取用户的im头像 gao
        OWN_HEADER_URL=getinfo;
        RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {

            @Override
            public void onSuccess(LoginInfo loginInfo) {
                isLogIn = true;
                request(Request.Method.GET, "http://imserver.myappcc.com/api/Getuseracc", null, "", tag, 100);

            }

            @Override
            public void onFailed(int i) {
                System.out.println("login failed===" + i);
            }

            @Override
            public void onException(Throwable throwable) {}
        };
        NIMClient.getService(AuthService.class).login(loginInfo).setCallback(callback);
    }

    public void request(int method, final String URL, Map map, final String session, String tag, final int type) {
        this.tag = tag;
        JSONObject jsonObject;
        if (map != null)
            jsonObject = new JSONObject(map);

        else {
            jsonObject = null;
        }
        JsonRequest jsonRequest = new JsonObjectRequest(method, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                netResponses.success(type, jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netResponses.fail();
            }
        });

        jsonRequest.setTag(tag);
        // 将请求添加到队列中
        ((MyApplication) getApplication()).getRequestQueue().add(jsonRequest);

    }
    private NetResponses netResponses;
    public void setNetResponses(NetResponses netResponses) {
        this.netResponses = netResponses;
    }

    @Override
    public void success(int type, JSONObject jsonObject) {
        Contacts contacts;
        Log.e("ClothesDetail", "success: ");
        if (type == 100) {
            JSONArray array = jsonObject.optJSONArray("kefulist");
            Log.e("array", "success: "+array);
            for (int i = 0; i < array.length(); i++) {
                contacts = new Contacts(Parcel.obtain());
                JSONObject object = array.optJSONObject(i);
                contacts.setName(object.optString("nickname"));
                contacts.setAccid(object.optString("accid"));
                contacts.setHeader(object.optString("userimg"));
                dataList.add(contacts);
            }
            kefu();
        }
        else if(type == 200){
            System.out.println("*********"+jsonObject.toString());
            sharedPress.putString("accid",jsonObject.optString("accid"));
            sharedPress.putString("token",jsonObject.optString("token"));
            OWN_HEADER_URL = jsonObject.optString("userimg");
            login();
        }
    }

    @Override
    public void fail() {

    }
}
