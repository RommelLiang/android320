package com.tiemuyu.chuanchuan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.AssetsUtils;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.Utility;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.AddressPicker;

/**
 * Created by CC2.0 on 2017/1/23.
 */

public class MyInfo extends BaseActivityG {


    @ViewInject(R.id.yaoqingma)
    private RelativeLayout yaoqingma;


    @ViewInject(R.id.nicheng_text)
    private TextView nicheng_text;

    @ViewInject(R.id.change_nicheng)
    private RelativeLayout change_nicheng;

    @ViewInject(R.id.touxiang)
    private ImageView touxiang;

    @ViewInject(R.id.change_tx)
    private RelativeLayout change_touxiang;

    @ViewInject(R.id.invitation_codetext)
    private TextView yaoqingma_text;

    @ViewInject(R.id.countryid)
    private TextView countryid;

    private RelativeLayout rl_address, rl_location;


    String GET_INVITECODE = "GET_INVITECODE";
    String UPDATE_ADDRESS = "UPDATE_ADDRESS";

    private ProgressDialog pd;
    private ArrayList<AddressPicker.Province> province;
    private AddressPicker picker;
    private String mprovince;
    private String mcity;
    private String mcounty;

    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_info_layout);
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = AssetsUtils.readText(this, "city.json");
        province = GsonUtils.jsonToArrayList(json, AddressPicker.Province.class);
        data.addAll(province);
        picker = new AddressPicker(this, data);
        picker.setSelectedItem("北京市", "北京", "东城区");
        countryid = (TextView) findViewById(R.id.countryid);
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(String province, String city, String county) {
                mprovince = province;
                mcity = city;
                mcounty = county;
                location = province + " " + city + " " + county;
                countryid.setText(location);
                MyApplication.poolManager.addAsyncTask(
                        new ThreadPoolTaskHttp(MyInfo.this,
                                UPDATE_ADDRESS,
                                Constant.REQUEST_POST,
                                ParamsTools.changeArea(mprovince, mcity, mcounty),
                                MyInfo.this, "修改收货地址",
                                false));
            }
        });

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();

        init();
        pd.show();

        initProcess();
        User user = MineFragment.user;
        if (user.getProvince() != null && !user.getProvince().equals("")) {
            countryid.setText(user.getProvince() + " " + user.getCity() + "" + user.getDistrict());
        }
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();
        initAppAccess();
        initUI();
        initListener();
    }


    /**
     * 实例化访问记录
     */
    protected void initAppAccess() {

    }

    protected void initData() {
//        // TODO Auto-generated method stub


        getMyInfo();


    }


    private void setView() {

//        PreferenceUtils.setPrefBoolean(getActivity(), "firstload", true);
//
//        initBannerView(view1);//因为banner在view1里面，所以要用view1作为参数传递到initView里面
//        initBannerView2(view2);


        pd.dismiss();


    }


    private void init() {
        pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("加载中....");
    }


    protected void initUI() {


        yaoqingma_text = (TextView) findViewById(R.id.invitation_codetext);

        yaoqingma = (RelativeLayout) findViewById(R.id.yaoqingma);

        nicheng_text = (TextView) findViewById(R.id.nicheng_text);

        change_nicheng = (RelativeLayout) findViewById(R.id.change_nicheng);

        touxiang = (ImageView) findViewById(R.id.touxiang);

        change_touxiang = (RelativeLayout) findViewById(R.id.change_tx);
        rl_location = (RelativeLayout) findViewById(R.id.rl_location);


        rl_address = (RelativeLayout) findViewById(R.id.rl_address);
        nicheng_text.setText(MineFragment.user.getNickName());
        ImageLoader.getInstance().displayImage(MineFragment.user.getUserImg(), touxiang);
        yaoqingma_text.setText(MineFragment.user.getInviteCode());
        countryid.setText(MineFragment.user.getProvince());


        rl_address.setOnClickListener(this);
        rl_location.setOnClickListener(this);

    }

    protected void initListener() {
        // TODO Auto-generated method stub
//        bt_login.setOnClickListener(this);
//        bt_back.setOnClickListener(this);
//        //todo   添加 注册按钮和注册跳转。
//
//        bt_zhuce.setOnClickListener(this);


        yaoqingma.setOnClickListener(this);

        change_touxiang.setOnClickListener(this);

        change_nicheng.setOnClickListener(this);

    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.yaoqingma:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                Intent intent = new Intent(this, MyInviteCode.class);
                intent.putExtra("accid", MineFragment.user.getAccid());
                startActivity(intent);
                break;
            case R.id.change_tx:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                ClassJumpTool.startToNextActivityForResult(this,
                        MyNick.class, 10);

                break;
            case R.id.change_nicheng:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                ClassJumpTool.startToNextActivityForResult(this,
                        MyNick.class, 10);

                break;

            case R.id.rl_address:
                Intent intent2 = new Intent(MyInfo.this, AddressMangerActivity.class);
                startActivity(intent2);
                break;

            case R.id.rl_location:
                picker.show();
                break;

        }
    }


    /**
     * @param @param tag 设定文件
     * @return void 返回类型
     * @throws
     * @Title: getPasskeyMethod
     * @Description: TODO 获取passkey
     */
    protected void getMyInfo() {
//        LogHelper.d("-----passkey请求的地址:" + UrlManager
//                .GET_PASSKEY());

//        String tag="GET_INVITECODE";
        System.out.println("#####进入passkey");
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                GET_INVITECODE, Constant.REQUEST_GET, new RequestParams(UrlManager
                .GET_MYINVITECODE()), this, "获取我的邀请码", false));
    }


    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        // LogHelper.e("-----侧滑栏请求成功回调"+resultTag);
        System.out.println("successCallBack");
        successParse(callBackMsg, resultTag);

    }


    private void successParse(String msg, String resultTag) {
        if (resultTag.equals("GET_INVITECODE")) {
            System.out.println("######获取了邀请码--:" + msg);
            // ToastHelper.show(getActivity(), "自动登录成功");

            // 设置数据
            String data = DataContoler.getJsonData(msg);

            System.out.println("######获取了邀请码--:" + data);
            pd.dismiss();
//            setView();

//            loginSuc(msg);

        } else if (resultTag.equals(UPDATE_ADDRESS)) {
            Log.e("UPDATE_ADDRESS", "successParse: " + msg);
            Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show();
        }


//        else if (resultTag.equals(HttpTools.TAG_GETPASSKEY)) {
//            // LogHelper.d("---获取pass->"+msg);
//            System.out.println("########################################OOOOOOOOOOOOOOOOM<<<<<<<<<<<GGGGGG");
//            GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
//            if (key != null) {
//                // LogHelper.d("---获取pass1111111->");
//
//                passkey = key.getData().getPassKey();
//                PreferenceUtils.setPrefString(getActivity(), Constant.PASSKEY,
//                        passkey);
//                loginMethod();
//            }
//
//        }

    }


}
