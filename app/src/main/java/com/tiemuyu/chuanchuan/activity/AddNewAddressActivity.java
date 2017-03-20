package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.Address;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AssetsUtils;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


import cn.qqtheme.framework.picker.AddressPicker;

public class AddNewAddressActivity extends BaseActivityG {

    EditText tvName;
    EditText tvPhone;
    TextView tv_save;
    TextView tvLocation;
    EditText tvLocationDetail;
    ImageView imDef;
    RelativeLayout rl_set_def;
    LinearLayout ll_location;
    private ArrayList<AddressPicker.Province> province;
    private String location, name, phone, locationDetail;
    private int isDef = 0;
    private AddressPicker picker;
    private final String ADD_ADDRESS = "ADD_ADDRESS";
    private final String UPDATE_ADDRESS = "UPDATE_ADDRESS";
    private boolean is_edit = false;
    private String mprovince;
    private String mcity;
    private String mcounty;
    private Address.DataBean address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        intiView();
        address = (Address.DataBean) getIntent().getSerializableExtra("address");
        if (address != null) {
            is_edit = true;
            tvName.setText(address.getContact());
            tvPhone.setText(address.getMobile());
            tvLocationDetail.setText(address.getAddress());
            tvLocation.setText(address.getProvince() + address.getCity() + address.getDistrict());
            mprovince = address.getProvince();
            mcity = address.getCity();
            mcounty = address.getDistrict();
        }
        ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
        String json = AssetsUtils.readText(this, "city.json");
        province = GsonUtils.jsonToArrayList(json, AddressPicker.Province.class);
        data.addAll(province);
        picker = new AddressPicker(this, data);
        picker.setSelectedItem("北京市", "北京", "东城区");
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(String province, String city, String county) {
                mprovince = province;
                mcity = city;
                mcounty = county;
                location = province + " " + city + " " + county;
                tvLocation.setText(location);
            }
        });
        rl_set_def.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        ll_location.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        findViewById(R.id.im_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_save:
                Log.e("tv_save: ", String.valueOf(is_edit));
                if (!is_edit) {
                    addAddress();
                    Log.e("onClick: ", "添加");
                } else {
                    Log.e("onClick: ", "更新");
                    updateAddress();
                }
                break;
            case R.id.rl_set_def:
                if (isDef == 1) {
                    imDef.setBackground(getResources().getDrawable(R.drawable.noselect));
                    isDef = 0;
                } else {
                    imDef.setBackground(getResources().getDrawable(R.drawable.select));
                    isDef = 1;
                }
                break;
            case R.id.ll_location:
                picker.show();
                break;
            case R.id.tv_location:
                picker.show();
                break;
        }
    }

    private void addAddress() {
        name = String.valueOf(tvName.getText());
        phone = String.valueOf(tvPhone.getText());
        locationDetail = String.valueOf(tvLocationDetail.getText());
        if (name.equals("") || phone.equals("") || locationDetail.equals("") ||
                mcounty.equals("")) {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e("------------------", "onClick: " + isDef);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(AddNewAddressActivity.this,
                        ADD_ADDRESS,
                        Constant.REQUEST_POST,
                        ParamsTools.addAddress(
                                UrlManager.ADD_ADDRESS(),name
                                , phone, mprovince, mcity, mcounty, locationDetail,isDef, ""),
                        AddNewAddressActivity.this, "添加收货地址",
                        false));
    }

    private void updateAddress() {
        name = String.valueOf(tvName.getText());
        phone = String.valueOf(tvPhone.getText());
        locationDetail = String.valueOf(tvLocationDetail.getText());

        if (name.equals("") || phone.equals("") || locationDetail.equals("") ||
                mcounty.equals("")) {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(AddNewAddressActivity.this,
                        UPDATE_ADDRESS,
                        Constant.REQUEST_POST,
                        ParamsTools.modifyAddress(
                                UrlManager.MODIFY_ADDRESS(),name
                                , phone, mprovince, mcity, mcounty, locationDetail, "", String.valueOf(address.getId()),isDef),
                        AddNewAddressActivity.this, "修改收货地址",
                        false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack1: ", callBackMsg);
        if (resultTag.equals(ADD_ADDRESS)) {
            Toast.makeText(this, "添加完成", Toast.LENGTH_SHORT).show();
            setResult(10, new Intent());
            finish();
        } else if (resultTag.equals(UPDATE_ADDRESS)) {
            Toast.makeText(this, "修改完成", Toast.LENGTH_SHORT).show();
            setResult(10, new Intent());
            finish();
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ", arg0.getLocalizedMessage());
    }

    private void intiView() {
        tvName = (EditText) findViewById(R.id.tv_name);
        tvPhone = (EditText) findViewById(R.id.tv_phone);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tvLocationDetail = (EditText) findViewById(R.id.tv_location_detail);
        imDef = (ImageView) findViewById(R.id.im_def);
        rl_set_def = (RelativeLayout) findViewById(R.id.rl_set_def);
        ll_location = (LinearLayout) findViewById(R.id.ll_location);
    }
}
