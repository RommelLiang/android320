package com.tiemuyu.chuanchuan.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.BodysBean;
import com.tiemuyu.chuanchuan.activity.bean.CheckPassword;
import com.tiemuyu.chuanchuan.activity.bean.OrdInfo;
import com.tiemuyu.chuanchuan.activity.bean.PayInfoBean;
import com.tiemuyu.chuanchuan.activity.bean.SignWeChat;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.bean.WeChatPay;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import org.xutils.http.RequestParams;

/**
 * Created by CC2.0 on 2017/2/10.
 */

public class PaySelect extends BaseActivityG {


	private Button ps_ok;  //专题头部image
	private TextView tv_total_price, tv_lingqian;
	private String TAG_CHECKPASSWORD = "TAG_CHECKPASSWORD";
	private String TAG_SENDPAY_Discounted = "TAG_SENDPAY_Discounted";
	private String productid;
	PayInfoBean payinfo = new PayInfoBean();
	private ImageView iv_ling, iv_zhifubao, iv_wechat;
	private int payType = 0;
	private User user;
	private double lingqian;
	private int requestCode = 222;

	private String TAG_SENDPAY = "TAG_SENDPAY";   //获取添加订单


	private static final String TAG_GETPASSKEY = "TAG_GETPASSKEY";

	private String TAG_PAYACTION = "TAG_PAYACTION";   //获取添加支付

	private OrdInfo ordInfo;
	private String addressId;
	private String id;
	private static IWXAPI iwxapi;
	private Receiver receiver;
	public static final String TAG_MINEFRESH = "TAG_MINEFRESH";
	private BodysBean.DataBean.UserCCInfoListBean mUserCCInfoListBean;
	private int mType;
	private int mOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.register_layout);

		this.setContentView(R.layout.activity_pay);
		tv_total_price = (TextView) findViewById(R.id.tv_total_price);

		final String information = getIntent().getStringExtra("Intent_Data_Packet");//.getStringExtra("et1");

		ordInfo = (OrdInfo) getIntent().getSerializableExtra("ordInfo");
		productid = String.valueOf(getIntent().getIntExtra("productid", 0));
		addressId = String.valueOf(getIntent().getIntExtra("AddressId", 0));
		mOrder = getIntent().getIntExtra("order", 0);
		mType = getIntent().getIntExtra("type", 0);
		mUserCCInfoListBean = (BodysBean.DataBean.UserCCInfoListBean) getIntent().getSerializableExtra("body");
		tv_total_price.setText(ordInfo.getActualFee() + "");
		user = MineFragment.user;
		lingqian = user.getAmounts() - user.getFrzAmounts();
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		//  Constant.VERSION = Version.getAppVersionName(this);
		// _global = GlobalVariable.getInstance();
		findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		initProcess();
		regToWx();
	}

	private void regToWx() {
		//将微信支付接入应用
		iwxapi = WXAPIFactory.createWXAPI(this, Constant.WE_chat_APP_ID, true);
		iwxapi.registerApp(Constant.WE_chat_APP_ID);
	}

	/**
	 * 加载的流程
	 */
	protected void initProcess() {
		initUI();

		initListener();
	}


	protected void initUI() {


		ps_ok = (Button) findViewById(R.id.ps_ok);
		iv_ling = (ImageView) findViewById(R.id.iv_ling);
		iv_wechat = (ImageView) findViewById(R.id.iv_wechat);
		iv_zhifubao = (ImageView) findViewById(R.id.iv_zhifubao);
		tv_lingqian = (TextView) findViewById(R.id.tv_lingqian);
		iv_ling.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Double.parseDouble(ordInfo.getActualFee()) > lingqian) {
					Toast.makeText(PaySelect.this, "您的零钱余额不足", Toast.LENGTH_SHORT).show();
				} else {
					iv_ling.setBackground(getResources().getDrawable(R.drawable.select));
					iv_zhifubao.setBackground(getResources().getDrawable(R.drawable.noselect));
					iv_wechat.setBackground(getResources().getDrawable(R.drawable.noselect));
					payType = 5;
				}
			}
		});
		iv_zhifubao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_zhifubao.setBackground(getResources().getDrawable(R.drawable.select));
				iv_ling.setBackground(getResources().getDrawable(R.drawable.noselect));
				iv_wechat.setBackground(getResources().getDrawable(R.drawable.noselect));
				payType = 2;
			}
		});
		iv_wechat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_wechat.setBackground(getResources().getDrawable(R.drawable.select));
				iv_ling.setBackground(getResources().getDrawable(R.drawable.noselect));
				iv_zhifubao.setBackground(getResources().getDrawable(R.drawable.noselect));
				payType = 1;
			}
		});
		tv_lingqian.setText("零钱￥" + lingqian);

	}


	protected void initListener() {
		// TODO Auto-generated method stub

		ps_ok.setOnClickListener(this);


	}

	private String orderId;

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.ps_ok:
				if (payType == 0) {
					Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
					return;
				}
				orderId = ordInfo.getOrderId();
				Log.e("onClick: ", orderId + "HHHH");
				initPd();
				if (orderId != null && !orderId.equals("")) {
					//从我的订单跳转支付
					pd.show();
					if (payType != 5) {
						paytheOrder(orderId);
						return;
					} else if (payType == 5) {
						MyApplication.poolManager.addAsyncTask(
								new ThreadPoolTaskHttp(this,
										TAG_CHECKPASSWORD,
										Constant.REQUEST_GET,
										new RequestParams(
												UrlManager.checkpaypwd()),
										this,
										"获取产品信息",
										false));
						return;
					}
				}
				pd.show();
				if (mType == 0) {
					sendpayment();
				} else if (mType == 1) {
					sendpaymentDiscounted();
				}
				break;
		}


	}


	/**
	 * @return void 返回类型
	 * @throws
	 * @Title: getCodeMethod
	 * @Description: TODO 添加新的订单
	 * 设定文件
	 */
	private void sendpayment() {
		ordInfo.setCustomerRmk("good");
		Log.e("sendpayment: ", addressId + ":" + productid);
		String json = "{\"TotalNum\":\"" + ordInfo.getTotalNum() +
				"\",\"TotalFee\":\"" + ((int) Double.parseDouble(ordInfo.getTotalFee())) +
				"\",\"Coin\":\"" + ordInfo.getCoin() +
				"\"," + "\"DiscountedPrice\":\"" + ordInfo.getDiscountedPrice() +
				"\",\"ActualFee\":\"" + ((int) Double.parseDouble(ordInfo.getActualFee())) +
				"\",\"CustomerRmk\":\"good\",\"RegApp\":\"00\"}";
		Log.e("json", "sendpayment: " + json);
		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
				TAG_SENDPAY, Constant.REQUEST_POST, ParamsTools.sendPay(
				UrlManager.Send_Pay(), addressId, productid, json), this, "发送新订单", true));
	}

	private void sendpaymentDiscounted() {
		ordInfo.setCustomerRmk("good");
		Log.e("sendpayment: ", addressId + ":" + productid);
		double totalFee = Double.parseDouble(ordInfo.getTotalFee());
		ordInfo.setDiscountedPrice(String.valueOf(totalFee - (int) Math.ceil(totalFee * 0.9)));
		String json = "{\"TotalNum\":\"" + ordInfo.getTotalNum() +
				"\",\"TotalFee\":\"" + ((int) Double.parseDouble(ordInfo.getTotalFee())) +
				"\",\"Coin\":\"" + ordInfo.getCoin() +
				"\"," + "\"DiscountedPrice\":\"" + ordInfo.getDiscountedPrice() +
				"\",\"ActualFee\":\"" + ordInfo.getActualFee() +
				"\",\"CustomerRmk\":\"good\",\"RegApp\":\"00\"}";
		Log.e("json", "sendpaymentDiscounted: " + json);
		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
				TAG_SENDPAY_Discounted, Constant.REQUEST_POST, ParamsTools.sendPayDiscounted(
				addressId, productid, json), this, "发送新订单", true));
	}

	/**
	 * @return void 返回类型
	 * http://test.myappcc.com/api/ccorderapi?Pay&orderid=xxx&payType
	 * 1微信2支付宝5零钱
	 * @throws
	 * @Title: getCodeMethod
	 * @Description: TODO 添加新的订单
	 * 设定文件
	 */
	private void paytheOrder(String orderid) {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_PAYACTION,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.Get_PAYACTION() + orderid + "&payType=" + payType),
						this,
						"调用支付",
						false));

	}


	/// <summary>
	/// 支付
	/// </summary>
	/// <param name="signString"></param>对应了order string
	/// <param name="type"></param>默认为2 为阿里支付
	/// <param name="scheme"></param>
	/// <param name="completeHandler"></param>
	public void payfunction(String signString) {
		pay(signString);
	}


	//
//    //重新搞一下。不用post了。直接搞了在app解析了然后判断。
	private void pay(final String payInfo) {
		Log.e("pay: ", payInfo);
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(PaySelect.this);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);
				//把result 按照；分开然后变成
				String[] resultlist = result.split(";");
				System.out.println("==============resultlist==============================" + resultlist[2]);
				String[] xxx = resultlist[0].split("=");
				String xxxafter = xxx[1].substring(1, xxx[1].length() - 1);
				System.out.println("==============================xxxafter==============" + xxxafter);
				Message msg = new Message();
				if (xxxafter.equals("9000")) {
					msg.what = 1;
				} else if (xxxafter.equals("4000") || xxxafter.equals("6002")) msg.what = 2;
				else msg.what = 3;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};
		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	private void weChatPay(SignWeChat signWeChat) {
		IntentFilter intentFilter = new IntentFilter("tiemuyu.cc.wechat.pay");
		receiver = new Receiver();
		registerReceiver(receiver, intentFilter);
		PayReq req = new PayReq();
		req.appId = signWeChat.getAppid();
		req.partnerId = signWeChat.getPartnerid();
		req.prepayId = signWeChat.getPrepayid();
		req.nonceStr = signWeChat.getNoncestr();
		req.timeStamp = signWeChat.getTimestamp();
		req.packageValue = "Sign=WXPay";
		req.sign = signWeChat.getSign();
		iwxapi.sendReq(req);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			Log.e("handleMessage: ", msg + "!!!!!code" + msg.what);
			switch (msg.what) {
				case 1: {
					Log.e("handleMessage: ", "支付陈功");
						Toast.makeText(PaySelect.this, "下单成功", Toast.LENGTH_SHORT).show();
						ClassJumpTool.startToNextActivityForResult(PaySelect.this, UserOrderActivity.class, 10);
						finish();
					break;
				}
				case 2: {
					Log.e("handleMessage: ", "您的手机尚未安装支付宝");
					Toast.makeText(getApplicationContext(), "您的手机尚未安装支付宝!", Toast.LENGTH_SHORT).show();
					boolean showing = pd.isShowing();
					if (showing) {
						pd.dismiss();
					}
					break;
				}
				case 3: {
					Toast.makeText(getApplicationContext(), "支付退出请重试!", Toast.LENGTH_SHORT).show();
					boolean showing = pd.isShowing();
					if (showing) {
						pd.dismiss();
					}
					break;
				}
				default:
					break;
			}
		}
	};


	/********************************
	 * 高伟豪添加
	 ********************************/
	public void PaySelectAction(String msg, String resultTag) {
		if (resultTag.equals(TAG_SENDPAY)) {
			System.out.println("ZHUANTI message is: " + msg + "; " + resultTag);
			//TODO  数据解析
//            {"Code":1,"Msg":"OK","Data":{"PayMomeny":100.0,"OrderId":9850}}
			id = DataContoler.parseOrderId(msg);

			Log.e("PaySelectAction: ", msg);
			System.out.println("###" + id);
			//得到了订单id之后走支付流程
			if (payType == 5) {
				MyApplication.poolManager.addAsyncTask(
						new ThreadPoolTaskHttp(this,
								TAG_CHECKPASSWORD,
								Constant.REQUEST_GET,
								new RequestParams(
										UrlManager.checkpaypwd()),
								this,
								"获取产品信息",
								false));
				return;
			}
			MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
					TAG_MINEFRESH, Constant.REQUEST_GET, new RequestParams(UrlManager
					.GET_MYPAGEDATA()), this, "获取我的页面信息", false));
			if (mUserCCInfoListBean == null) {
				paytheOrder(id);
			} else {
				editBodyData();
			}
		} else if (resultTag.equals(TAG_PAYACTION)) {
			Log.e("TAG_PAYACTION ", msg);
			if (payType == 2) {
				payinfo = DataContoler.parseIdandSignStr(msg);
				Log.e("PaySelectAction: ", payinfo.getSignStr() + ":|||:" + payinfo.getOrderId());
				payfunction(payinfo.getSignStr());
			} else if (payType == 1) {
				WeChatPay weChatPay = GsonUtils.fromData(msg, WeChatPay.class);
				SignWeChat signWeChat = GsonUtils.fromData(weChatPay.getData().getSignStr(), SignWeChat.class);
				weChatPay(signWeChat);
			}

		} else if (resultTag.equals("TAG_CHECKPASSWORD")) {
			CheckPassword checkPassword = GsonUtils.fromData(msg, CheckPassword.class);
			if (checkPassword.isData()) {
				Intent intent = new Intent(PaySelect.this, LingQianPayActivity.class);
				if (orderId != null && !orderId.equals("")) {
					id = orderId;
				}
				intent.putExtra("orderid", id);
				startActivityForResult(intent, requestCode);
			} else {
				Toast.makeText(this, "您尚未设置支付密码", Toast.LENGTH_SHORT).show();
			}
			Log.e("lingqian", "PaySelectAction: " + msg);
		} else if (resultTag.equals(we_chat_ok)) {
			//微信支付完成
			Log.e("PaySelectAction: ", msg);
			Toast.makeText(PaySelect.this, "下单成功", Toast.LENGTH_SHORT).show();
			ClassJumpTool.startToNextActivityForResult(PaySelect.this, UserOrderActivity.class, 10);
			finish();
		} else if (resultTag.equals(TAG_MINEFRESH)) {
			User user = DataContoler.parseLoginMsgAndSetUser(msg, DBTools.getUser().getPass(), "");
			if (user != null) {
				DBTools.loginDb(PaySelect.this, user);
			}
		} else if (resultTag.equals(HttpTools.TAG_SET_BODY_DATA)) {
			Log.e("setBody: ",msg );
			paytheOrder(id);
		} else if (resultTag.equals(TAG_SENDPAY_Discounted)) {
			id = DataContoler.parseOrderId(msg);
			Log.e("PaySelectAction: ", msg);
			System.out.println("###" + id);
			//得到了订单id之后走支付流程
			if (payType == 5) {
				MyApplication.poolManager.addAsyncTask(
						new ThreadPoolTaskHttp(this,
								TAG_CHECKPASSWORD,
								Constant.REQUEST_GET,
								new RequestParams(
										UrlManager.checkpaypwd()),
								this,
								"获取产品信息",
								false));
				return;
			}
			MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
					TAG_MINEFRESH, Constant.REQUEST_GET, new RequestParams(UrlManager
					.GET_MYPAGEDATA()), this, "获取我的页面信息", false));
			if (mUserCCInfoListBean == null) {
				paytheOrder(id);
			} else {
				editBodyData();
			}
		}
	}


	/********************************
	 * 添加的接口
	 ********************************/
	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		System.out.println("http succeed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Log.e("successCallBack: ", resultTag);
		PaySelectAction(callBackMsg, resultTag);

	}

	//		public void loadingCallBack(long total, long current, boolean isUploading,
//				String resultTag);/** 加载中 */
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!failed callback!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Log.e("failCallBack: " + resultTag, arg0.getLocalizedMessage() + "");
		if (resultTag.equals(TAG_SENDPAY)) {
			ToastHelper.show(PaySelect.this, "获取订单信息失败");
		} else if (resultTag.equals(TAG_PAYACTION)) {
			ToastHelper.show(PaySelect.this, "获取支付信息失败");
		} else if (resultTag.equals("TAG_CHECKPASSWORD")) {
			ToastHelper.show(PaySelect.this, "校验失败");
		} else if (resultTag.equals(HttpTools.TAG_SET_BODY_DATA)) {
			Toast.makeText(this, "提交身体数据失败，请联系客服", Toast.LENGTH_LONG).show();
		}
		if (pd.isShowing()) {
			pd.dismiss();
		}
	}

	/**
	 * 请求失败
	 */

//    public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle){} /** 开始*/
//    public void cancelCallBack(String resultTag){}/**取消*/
	public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!succeed but code != 1 !!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	/**
	 * 请求成功，但code!=1
	 */

	public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
		System.out.println("!!!!!!!!!!!!!!request succeed! but need relogin!!!!!!!!!!!!!!!!!");
	}/** 请求成功,但要重新登录 */
	/****************************************************************/

	@Override
	protected void onActivityResult(int mrequestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == mrequestCode && resultCode == requestCode) {
			Toast.makeText(PaySelect.this, "下单成功", Toast.LENGTH_SHORT).show();
			ClassJumpTool.startToNextActivityForResult(PaySelect.this, UserOrderActivity.class, 10);
			finish();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (pd != null) {
			pd.dismiss();
		}
		Log.e("onPause: ", "activity onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}

	private ProgressDialog pd;

	private void initPd() {
		pd = new ProgressDialog(this);//加载的ProgressDialog
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
		pd.setMessage("跳转中....");
	}

	private final String we_chat_ok = "we_chat_ok";

	public class Receiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			int code = intent.getIntExtra("code", 1);
			if (orderId != null && !orderId.equals("")) {
				id = orderId;
			}
			Log.e("onReceive: ", code + "");
			if (code == 0) {
				MyApplication.poolManager.addAsyncTask(
						new ThreadPoolTaskHttp(PaySelect.this,
								we_chat_ok,
								Constant.REQUEST_GET,
								new RequestParams(
										UrlManager.weChat_Pay_OK(id)),
								PaySelect.this,
								"微信支付完成",
								false));
			}
		}
	}

	private void editBodyData() {
		String json = "{\"OrderId\":\"" + id +
				"\",\"SkinColor\":\"" + mUserCCInfoListBean.getSKIN_COLOR() +
				"\",\"WaistPostion\":\"" + mUserCCInfoListBean.getWAIST_POSTION() +
				"\",\"BodyPic\":\"" + mUserCCInfoListBean.getBODY_PIC() +
				"\",\"CustomerLogo\":\"" + mUserCCInfoListBean.getLOGO() +
				"\",\"CustomerName\":\"" + mUserCCInfoListBean.getName() +
				"\",\"BodySummary\":\"" + "0" +
				"\",\"BodyOther\":\"" + "0" +
				"\",\"DressEffect\":\"" + mUserCCInfoListBean.getCHUANYIXUG() +
				"\",\"Bmi\":\"" + mUserCCInfoListBean.getBMI() +
				"\",\"Gender\":\"" + mUserCCInfoListBean.getGENDER() +
				"\",\"Age\":\"" + mUserCCInfoListBean.getAGE() +
				"\",\"Weight\":\"" + mUserCCInfoListBean.getWEIGHT() +
				"\",\"Height\":\"" + mUserCCInfoListBean.getHEIGHT() +
				"\",\"ClothSize\":\"" + mUserCCInfoListBean.getCLOTH_SIZE() +
				"\",\"Physique\":\"" + mUserCCInfoListBean.getPHYSIQUE() +
				"\",\"ShoulderBreadth\":\"" + mUserCCInfoListBean.getSHOULDER_BREADTH() +
				"\",\"Bust\":\"" + mUserCCInfoListBean.getBUST() +
				"\",\"Waist\":\"" + mUserCCInfoListBean.getWAIST() +
				"\",\"Hip\":\"" + mUserCCInfoListBean.getHIP() +
				"\",\"Sleeve\":\"" + mUserCCInfoListBean.getSLEEVE() +
				"\",\"ThighCirc\":\"" + mUserCCInfoListBean.getTHIGH_CIRC() +
				"\",\"CalfCirc\":\"" + mUserCCInfoListBean.getCALF_CIRC() +
				"\",\"KneeCirc\":\"" + mUserCCInfoListBean.getKNEE_CIRC() +
				"\",\"ArmCirc\":\"" + mUserCCInfoListBean.getARM_CIRC() +
				"\",\"Pants\":\"" + mUserCCInfoListBean.getPANTS() +
				"\"}";
		Log.e("json", "editBodyData: " + json);
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						HttpTools.TAG_SET_BODY_DATA,
						Constant.REQUEST_POST,
						ParamsTools.orderBody(json),
						this,
						"设置身体数据",
						false));
		Log.e("body", "post sent.");//打印提示信息
	}
}
