package com.tiemuyu.chuanchuan.activity.new_activities;

import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.MyWebview;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.bean.TokenResultBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.util.AESHelper;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.CheckPhoneLength;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.MyCountTimer;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SetNotificationBarColer;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.Utility;
import com.tiemuyu.chuanchuan.activity.view.URL;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

/**
 * Created by CC2.0 on 2017/1/17.
 */

//public class TestActivity extends AppCompatActivity

public class RegisterActivity extends BaseActivityG implements OnItemClickListener {


	/**
	 * 布局一
	 */


	@ViewInject(R.id.login_name)
	private EditText et_account;// 请输入手机号码

	@ViewInject(R.id.login_password)
	private EditText et_pass;// 注册密码输入

	@ViewInject(R.id.ivcode_new)
	private EditText et_yaoqingma;// 邀请码输入

	@ViewInject(R.id.login1)
	private Button bt_next_one;// 下一步

	@ViewInject(R.id.zhuce_lay)
	private RelativeLayout zhuce_lay;//立即注册框体

	@ViewInject(R.id.zhuce_lay2)
	private RelativeLayout zhuce_lay2;//密码输入框体

	@ViewInject(R.id.zhuce_lay3)
	private RelativeLayout zhuce_lay3;//邀请码框体

	@ViewInject(R.id.zhuce_lay5)
	private RelativeLayout zhuce_lay5;//下一步框体


	@ViewInject(R.id.ivcode_yanzheng)
	private EditText et_yanzhengma;// 验证码输入reg_showinfo1


	@ViewInject(R.id.reg_showinfo1)
	private TextView reg_showinfo1;// 已同意《用户注册协议》


	/**
	 * 布局二
	 */


	@ViewInject(R.id.zhuce_lay4)
	private RelativeLayout zhuce_lay4;// 验证码框体


	@ViewInject(R.id.zhuce_lay6)
	private RelativeLayout zhuce_lay6;//完成框体


	@ViewInject(R.id.login2)
	private Button bt_next_two;// 完成


	@ViewInject(R.id.reg_getyanzheng)
	private Button reg_getyanzheng;// 获取验证码按钮reg_showinfo2

	@ViewInject(R.id.reg_showinfo2)
	private TextView reg_showinfo2;// 验证码已经发送给请注意查收

	private TextView no_code;

	private MyCountTimer timeCount;

	private String name;// 账号
	private String pass;// 密码
	private String yaoqingma;// 邀请码
	private String TAG_GETCODE = "TAG_GETCODE";
	private String token;
	private String get_code;
	private String yanzhengma;
	private String passkey;
	private String v1;// 用户登录信息

	private String TAG_REGIST_MODIFY = "TAG_REGIST_MODIFY";
	private String TAG_REGIST = "TAG_REGIST";

	private String TAG_GETPASSKEY = "TAG_GETPASSKEY";
	private User user;
	private String s[] = {"拨打客服电话", "复制客服微信", "呼叫"};
	private AlertView build;
	private AlertView build1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.register_layout);

		setContentView(R.layout.register_layout);
		SetNotificationBarColer.init(this);
		SetNotificationBarColer.setTranslucent();

		bt_next_one = (Button) findViewById(R.id.login1);
		et_account = (EditText) findViewById(R.id.login_name);
		et_pass = (EditText) findViewById(R.id.login_password);

		et_yaoqingma = (EditText) findViewById(R.id.ivcode_new);

		zhuce_lay = (RelativeLayout) findViewById(R.id.zhuce_lay);

		zhuce_lay2 = (RelativeLayout) findViewById(R.id.zhuce_lay2);
		zhuce_lay3 = (RelativeLayout) findViewById(R.id.zhuce_lay3);

		zhuce_lay4 = (RelativeLayout) findViewById(R.id.zhuce_lay4);

		zhuce_lay5 = (RelativeLayout) findViewById(R.id.zhuce_lay5);

		zhuce_lay6 = (RelativeLayout) findViewById(R.id.zhuce_lay6);


		et_yanzhengma = (EditText) findViewById(R.id.ivcode_yanzheng);

		bt_next_two = (Button) findViewById(R.id.login2);

		reg_getyanzheng = (Button) findViewById(R.id.reg_getyanzheng);

		timeCount = new MyCountTimer(reg_getyanzheng);


		reg_showinfo1 = (TextView) findViewById(R.id.reg_showinfo1);

		reg_showinfo2 = (TextView) findViewById(R.id.reg_showinfo2);

		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		//  Constant.VERSION = Version.getAppVersionName(this);
		// _global = GlobalVariable.getInstance();

		initProcess();
		findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.reg_showinfo1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ClassJumpTool.startToNextActivity(RegisterActivity.this,
						MyWebview.class,
						"http://ios.myappcc.com/h5/register.html");
			}
		});
		build1 = new AlertView.Builder().setContext(RegisterActivity.this)
				.setStyle(AlertView.Style.ActionSheet)
				.setTitle("发送验证码失败")
				.setMessage(null)
				.setCancelText("取消")
				.setDestructive(s[0], s[1])
				.setOthers(null)
				.setOnItemClickListener(this)
				.build();
		no_code = (TextView) findViewById(R.id.no_code);
		no_code.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				build1.show();
			}
		});
	}


	/**
	 * 加载的流程
	 */
	protected void initProcess() {
		initUI();

		initListener();
	}


	protected void initUI() {
	}

	protected void initListener() {
		// TODO Auto-generated method stub
		bt_next_one.setOnClickListener(this);
		bt_next_two.setOnClickListener(this);
		reg_getyanzheng.setOnClickListener(this);
		reg_showinfo1.setOnClickListener(this);


	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.login1:

				getCodeMethod();
				break;
			case R.id.login2:

				get_code = String.valueOf(et_yanzhengma.getText());
				if (StringUtil.isNull(get_code)) {
					Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
					return;
				}

				getPasskeyMeth(TAG_GETPASSKEY);
				break;
			case R.id.reg_getyanzheng:
				if (Utility.isFastDoubleClick()) // 连续点击
					return;
				//重新获取邀请码
				MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,

						TAG_GETCODE, Constant.REQUEST_POST, ParamsTools.getCode(
						UrlManager.GET_CODE(), name, yaoqingma), this, "获取验证码中", true));
				break;
			case R.id.reg_showinfo1:
				if (Utility.isFastDoubleClick()) // 连续点击
					return;


				ClassJumpTool.startToNextActivity(this, MyWebview.class, URL.UrlRegisterXieyi);


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
	protected void getPasskeyMeth(String tag) {
		System.out.println("#####getPasskeyMeth");
		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
				tag, Constant.REQUEST_GET, new RequestParams(UrlManager
				.GET_PASSKEY()), this, "获取passkey", false));
	}


	/**
	 * @return void 返回类型
	 * @throws
	 * @Title: getCodeMethod
	 * @Description: TODO 获取验证码
	 * 设定文件
	 */
	private void getCodeMethod() {
		name = et_account.getText().toString().trim();
		pass = et_pass.getText().toString().trim();
		yaoqingma = et_yaoqingma.getText().toString().trim();

		if (StringUtil.isNull(name)) {
			Toast.makeText(this, "请输入账号", Toast.LENGTH_LONG).show();
			return;
		}

		if (StringUtil.isNull(pass)) {
			Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
			return;
		}

		int cheeck = CheckPhoneLength.cheeck(name);
		if (cheeck == 0) {
			ToastHelper.show(this, "手机号码过短");
			return;
		} else if(cheeck == 2) {
			ToastHelper.show(this, "手机号码过长");
			return;
		}
		if (!JudgmentLegal.validatePhoneNumber(name)) {
			Toast.makeText(this, "格式不对，请输入手机号", Toast.LENGTH_LONG).show();

//            ToastHelper.show(this, "格式不对，请输入手机号");
			return;
		}
		if (!JudgmentLegal.isPass(pass)) {
			Toast.makeText(this, "请输入6-16位密码", Toast.LENGTH_LONG).show();

//            ToastHelper.show(this, "请输入6-16位密码");
			return;
		}


		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,

				TAG_GETCODE, Constant.REQUEST_POST, ParamsTools.getCode(
				UrlManager.GET_CODE(), name, yaoqingma), this, "获取验证码中", true));
	}


	@Override
	public void failCallBack(Throwable arg0, String resultTag,
	                         boolean isShowDiolog) {
		// TODO Auto-generated method stub
		super.failCallBack(arg0, resultTag, isShowDiolog);
		if (resultTag.equals(TAG_GETCODE)) {
			System.out.println("---获取验证失败->" + arg0.toString());
			ToastHelper.show(this, "获取验证码失败");
			build1.show();
		} else if (resultTag.equals(TAG_REGIST)) {
			Log.e("TAG_REGIST", "failCallBack: " + arg0.getLocalizedMessage());
			ToastHelper.show(this, "注册失败");
		} else if (resultTag.equals(TAG_REGIST_MODIFY)) {
			ToastHelper.show(this, "修改资料失败");
		}

	}

	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean,
	                             String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
		super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		Log.e("failShowCallBack: ", resultTag + ":" + callBackMsg);
		if (resultTag.equals(TAG_GETCODE)) {
			int code = baseBean.getCode();
			if (code == 0) {
				ToastHelper.show(RegisterActivity.this, "您的手机号码已在穿穿注册，请直接登录");
				return;
			} else if (code != 1) {
				build1 = new AlertView.Builder().setContext(RegisterActivity.this)
						.setStyle(AlertView.Style.ActionSheet)
						.setTitle("发送验证码失败")
						.setMessage(null)
						.setCancelText("取消")
						.setDestructive(s[0], s[1])
						.setOthers(null)
						.setOnItemClickListener(this)
						.build();
				build1.show();
			}

		} else if (resultTag.equals(TAG_REGIST)
				|| resultTag.equals(TAG_REGIST_MODIFY)) {
			System.out.println("######-muji:" + callBackMsg);
			ToastHelper.show(this, baseBean.getMsg());
		}

	}


	@Override
	public void successCallBack(String resultTag, BaseBean baseBean,
	                            String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		Log.e("successCallBack: ", callBackMsg);
		successParse(callBackMsg, resultTag);
	}


	private void successParse(String msg, String resultTag) {


		if (resultTag.equals(TAG_GETCODE)) {
			// 获取验证码
			codeResult(msg);

		} else if (resultTag.equals(TAG_GETPASSKEY)) {

			System.out.println("#####注册获取passkey->" + msg);
			getPasskeyAndRegist(msg);

		} else if (resultTag.equals(TAG_REGIST)) {
			// 注册
			registResult(msg);
		}


	}


	/**
	 * @param @param msg 设定文件
	 * @return void 返回类型
	 * @throws
	 * @Title: registResult
	 * @Description: TODO 注册结果操作
	 */
	private void registResult(String msg) {
		if (isOk(msg)) {
			Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
//            isRegist = true;
			System.out.println("----注册返回信息:" + msg);
//            btn_left.setVisibility(View.GONE);
			user = DataContoler.parseLoginMsgAndSetUser(msg, pass, "");
			// bean=resultBean.getData();
			if (user != null) {

//                ImageLoader.getInstance().displayImage(user.getUserImg(), iv);
//                usrImg = user.getUserImg();
//                et_nicheng.setText(user.getNickName());
				System.out.println("----注册后user返回信息:" + user.getEmail());
				//MenuLeftFragment.isLogin = true;
				PreferenceUtils.setPrefBoolean(this, Constant.CC_IFLOGIN, true);


				//设置数据
				String data = DataContoler.getJsonData(msg);
				if (data != null)
					PreferenceUtils.setPrefString(RegisterActivity.this,
							Constant.User, data);//

//                setView(false, false, true);
//                setRightButton("跳过");

				MineFragment.user = user;
				DBTools.loginDb(RegisterActivity.this, user);

				Intent intent = new Intent();
				intent.setAction(Constant.LOGINMSG);
				sendBroadcast(intent);

				AppManager.getAppManager().finishActivity(this);
				overridePendingTransition(R.anim.out_from_left, R.anim.out_from_right);
				AppManager.getAppManager().finishActivity(
						LoginActivity.class);


				// intent.setAction(Constant.LOGINMSG);
				// sendBroadcast(intent);
			}

		} else {
			BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
			Toast.makeText(this, baseBean.getMsg(), Toast.LENGTH_LONG).show();

//            ToastHelper.show(this, baseBean.getMsg());
			System.out.println("-----注册失败" + baseBean.getMsg());
		}
	}

	private boolean isOk(String msg) {
		BaseBean baseBean = JsonTools.fromJson(msg, BaseBean.class);
		if (baseBean.getCode() == Constant.REQUEST_OK) {
			return true;
		}
		return false;
	}


	// 获取passkey并注册
	private void getPasskeyAndRegist(String msg) {
		GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
		if (key != null && key.getCode() == Constant.REQUEST_OK) {
			passkey = key.getData().getPassKey();
			// System.out.println("----passkey--" + passkey);
			String x1 = name + "," + pass;
			try {
				v1 = AESHelper.getAesString(x1, passkey);
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!StringUtil.isNull(v1)) {

				System.out.println("#####getPasskeyAndRegist");

				MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
						this,

						TAG_REGIST, Constant.REQUEST_POST, ParamsTools.regist(
						UrlManager.REGIST() + "android",//MyApplication.mChannel,
						v1, get_code, yaoqingma, "", token), this,
						"正在注册...", true));
			}

		}
	}


	/**
	 * @param @param msg 设定文件
	 * @return void 返回类型
	 * @throws
	 * @Title: codeResult
	 * @Description: TODO 获取验证码结果操作
	 */
	private void codeResult(String msg) {
		System.out.println("---获取验证成功->" + msg);
		TokenResultBean bean = JsonTools.fromJson(msg, TokenResultBean.class);
		Log.e("codeResult: ", msg);
		if (bean != null) {
			token = bean.getData().getToken();
//// TODO: 2017/1/17 跳转   发送成功跳转到第二个界面
			setView(false, true);
			reg_showinfo2.setText("亲,短信验证码已发送至" + name + ",请注意查收");
			timeCount.start();
//            tv_tishi.setText("亲,短信验证码已发送至" + name + ",请注意查收");
//            timeCount.start();
		}


	}


	/**
	 * 布局的显示隐藏
	 **/
	private void setView(boolean one_show, boolean two_show) {
		if (one_show) {
			zhuce_lay.setVisibility(View.VISIBLE);
			zhuce_lay2.setVisibility(View.VISIBLE);
			zhuce_lay3.setVisibility(View.VISIBLE);
			zhuce_lay4.setVisibility(View.GONE);
			zhuce_lay5.setVisibility(View.VISIBLE);
			zhuce_lay6.setVisibility(View.GONE);


		} else {

			zhuce_lay.setVisibility(View.GONE);
			zhuce_lay2.setVisibility(View.GONE);
			zhuce_lay3.setVisibility(View.GONE);
			zhuce_lay4.setVisibility(View.VISIBLE);
			zhuce_lay5.setVisibility(View.GONE);
			zhuce_lay6.setVisibility(View.VISIBLE);


		}


	}


	@Override
	public void onItemClick(Object o, int position) {
		switch (position) {
			case 0:
				Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "0755-86678543"));
				startActivity(intent);
				break;
			case 1:
				ToastHelper.show(RegisterActivity.this, "客服微信已复制到剪贴板");
				ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				cmb.setText("myappcc");
				break;

		}
	}

}
