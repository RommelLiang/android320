package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.bean.AccountMannger;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.IDCard;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class AcountManngerActivity extends BaseActivityG {
    private final String TAG_GETMESSAGE = "TAG_GETMESSAGE";
    private final String TAG_UPDATEMESSAGE = "TAG_UPDATEMESSAGE";
    private EditText tv_name,tv_id,tv_ali_pay;
    private Button btn_commit;
    private AccountMannger accountMannger;
    private String name;
    private String id;
    private String pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_mannger);
        getMessage();
        tv_name = (EditText) findViewById(R.id.tv_name);
        tv_id = (EditText) findViewById(R.id.tv_id);
        tv_ali_pay = (EditText) findViewById(R.id.tv_ali_pay);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getMessage(){
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GETMESSAGE,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.accountManage()),
                        this,
                        "获取账户信息",
                        false));
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if (resultTag.equals(TAG_GETMESSAGE)) {
            Log.e(TAG_GETMESSAGE, "successCallBack: "+callBackMsg);
            accountMannger = GsonUtils.fromData(callBackMsg, AccountMannger.class);
            if (accountMannger.getData().getTrueName() != null && accountMannger.getData().getTrueName() != "") {
                btn_commit.setVisibility(View.GONE);
                String name = accountMannger.getData().getTrueName();
                String id = accountMannger.getData().getIdCard();
                String pay = accountMannger.getData().getCashAccount();
                tv_ali_pay.setText(pay);
                tv_ali_pay.setEnabled(false);
                tv_name.setText(name);
                tv_name.setEnabled(false);
                tv_id.setText(id);
                tv_id.setEnabled(false);
            } else {
                btn_commit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = String.valueOf(tv_name.getText());
                        id = String.valueOf(tv_id.getText());
                        pay = String.valueOf(tv_ali_pay.getText());
                        boolean cellPhone = JudgmentLegal.checkCellPhone(pay);
                        boolean email = JudgmentLegal.validateEmail(pay);
                        String cardValidate = (new IDCard()).IDCardValidate(id);

                        if (name.equals("")) {
                            Toast.makeText(AcountManngerActivity.this,
                                    "名字不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!cardValidate.equals("")) {
                            Toast.makeText(AcountManngerActivity.this, cardValidate, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!cellPhone && !email) {
                            Toast.makeText(AcountManngerActivity.this, "请输入正确的支付宝帐号", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        MyApplication.poolManager.addAsyncTask(
                                new ThreadPoolTaskHttp(AcountManngerActivity.this,
                                        TAG_UPDATEMESSAGE,
                                        Constant.REQUEST_POST,
                                        ParamsTools.updateAccount(
                                        UrlManager.upDateAccountManage(),name,id,pay),
                                        AcountManngerActivity.this, "完善账户信息",
                                        false));
                    }
                });
            }
        }
        if (resultTag.equals(TAG_UPDATEMESSAGE)) {
            Log.e(TAG_UPDATEMESSAGE, "successCallBack: " + callBackMsg);
            btn_commit.setVisibility(View.GONE);
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: " , arg0.getMessage());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMessage();
    }
}
