package com.tiemuyu.chuanchuan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.LoginActivity;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.ConnectionUtil;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.umeng.analytics.MobclickAgent;

import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;

import java.io.File;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SettingActivity extends Activity implements View.OnClickListener, ThreadPoolTaskHttp.HttpCallBack {

	private LinearLayout goBack;
	private Switch switch1, switch2;
	private TextView out;
	private static final String APP_CACAHE_DIRNAME = "/webcache";
	private Handler handler;

	private String TAG_LOGIN_OUT = "TAG_LOGIN_OUT";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置状态栏为透明
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		setContentView(R.layout.activity_setting);


//        SharedPreferences sp = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
//        String userName = sp.getString("logincache", "");
//
//
//
//        System.out.println("@@@@@@@saved username: "+userName);
		initUI();


		final SDKOptions options = new SDKOptions();
		// 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
		final StatusBarNotificationConfig config = new StatusBarNotificationConfig();
		goBack = (LinearLayout) findViewById(R.id.goBack);
		out = (TextView) findViewById(R.id.out);
		//没有登陆则不可登出

		if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {
			System.out.println("#####未登录");
			out.setVisibility(View.GONE);
		} else
			out.setVisibility(View.VISIBLE);
		out.setOnClickListener(this);
		goBack.setOnClickListener(this);
		switch1 = (Switch) findViewById(R.id.switch1);
		switch2 = (Switch) findViewById(R.id.switch2);
		boolean ring = config.ring;
		boolean vibrate = config.vibrate;
		switch1.setChecked(SPUtils.getVibrate());
		switch2.setChecked(SPUtils.getRing());
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					config.vibrate = true;
				} else {
					config.vibrate = false;
				}
				options.statusBarNotificationConfig = config;
				SPUtils.saveVibrate(config.vibrate);
				NIMClient.updateStatusBarNotificationConfig(config);
			}
		});
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					config.ring = true;
				} else {
					config.ring = false;
				}
				options.statusBarNotificationConfig = config;
				SPUtils.saveRing(config.ring);
				NIMClient.updateStatusBarNotificationConfig(config);
			}
		});

	}


	protected void initUI() {


		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
//                    case 7:
//                        tv_cachesize.setText((String) msg.obj);
//                        break;
//
//                    case 8:
//                        if (progressBar != null) {
//                            progressBar.setVisibility(View.GONE);
//                        }
//                        tv_cachesize.setText("0k");
//                        break;
//                    case FOLLOW_MSG_SHOW_ERROR:
//                        Toast.makeText(SettingActivity.this, "关注穿穿微博失败",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case FOLLOW_MSG_SHOW_COMPLETE:
//                        Toast.makeText(SettingActivity.this, "关注穿穿微博成功",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case FOLLOW_MSG_SHOW_CANCEL:
//                        Toast.makeText(SettingActivity.this, "取消关注穿穿微博",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 9:
//                        Toast.makeText(SettingActivity.this, "推荐成功", 10000).show();
//                        break;
//                    case 10:
//                        // 失败
//                        Toast.makeText(SettingActivity.this, "推荐失败", 10000).show();
//                        break;
//                    case 11:
//                        // 取消
//                        Toast.makeText(SettingActivity.this, "推荐取消", 10000).show();
//                        break;

					case 12:
						clearcache();

//                        isLoginOut=true;
						// 退出

						//发送IM注销消息
//                        ImMessage imMessage=ImAllSend.createImMessage("下线了", 0, (byte)0, (byte)202,SettingActivity.this);
//                        if(msg!=null)
//                            UdpImSendMessage.getUdpImSendMessage(SettingActivity.this).addMsg(imMessage);
//
//                        //退出udp心跳
//                        UdpImSendHeartbeatMessage.isRegist=false;

//					//发送用户退出广播
//					setImbrocast(SettingActivity.this);

						//设置用户退出的接口回调   这个是旧版的im用的。。。
//                        if(iOnLoginOutListener!=null){
//                            iOnLoginOutListener.loginOut();
//                        }
						//高伟豪 单纯开个线程池多线程跑run
						ConnectionUtil.DbOffer(SettingActivity.this, new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								loginout();
							}
						});

						//Unity3DActivity.LogOut();//u3d注销 add 2016-1-27 14:49:05

						MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
								SettingActivity.this,

								TAG_LOGIN_OUT,
								Constant.REQUEST_GET,
								new RequestParams(UrlManager.LOGIN_OUT()),
								SettingActivity.this,
								"注销中...",
								false));
						ClassJumpTool.startToNextActivity(SettingActivity.this, LoginActivity.class, "1");
						SPUtils.clear(SettingActivity.this);
						finish();
						break;
					case 13:
//                        Toast.makeText(SettingActivity.this, "没有安装微信客户端", 10000).show();

						break;
				}
			}
		};


	}


	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.out:
//                URL.loginstatus=false;
//                NIMClient.getService(AuthService.class).logout();
//                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();


				AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
				builder.setTitle("确定退出当前账号？");
				builder.setMessage("退出当前账号");
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

						System.out.println("取消");
					}
				});
				builder.setPositiveButton("确认退出", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("确定");

						dialog.dismiss();
						Message message = handler.obtainMessage();
						message.what = 12;
						handler.sendMessage(message);


					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();


				//狗屁的promptdialog根本跑不了。操了。高伟豪。直接用默认的吧。

				break;


			case R.id.goBack:
				finish();
				break;
		}
	}


	private void loginout() {

		MineFragment.user = null;
		//MenuLeftFragment.isLogin = false;
		PreferenceUtils.setPrefBoolean(this, Constant.CC_IFLOGIN, false);

		/** 发送退出广播 */
		Intent intent = new Intent();
		intent.setAction(Constant.GBNAMERESP);
		sendBroadcast(intent);

		/** 发送更新首页广播 */
		Intent intent1 = new Intent();
		intent1.setAction(Constant.DATA_UPDATA_HOME);
		sendBroadcast(intent1);

		/** 清空数据 */
		DBTools.removeDb(this);
		removeCookie(this);
//        cleanThirdData();  清空第三方数据

		//发送用户退出广播
//        setImbrocast(SettingActivity.this);
	}

	/**
	 * @param @param context 设定文件
	 * @return void 返回类型
	 * @throws
	 * @Title: removeCookie
	 * @Description: TODO 清除所有的cookie
	 */
	public static void removeCookie(Context context) {
//		CookieSyncManager.createInstance(context);
//		CookieSyncManager.getInstance().startSync();
		//	CookieManager.getInstance().removeSessionCookie();


		DbCookieStore.INSTANCE.removeAll();
		//MyApplication.myCookieStore.setCookieString("");
		PreferenceUtils.setPrefString(context, Constant.CC_COOKIE, "");//清空cookie
//        PreferenceUtils.setPrefString(context, CcHandler.skey, "");//清空web session   跟现在的纯原生无关
		System.out.println("#####Constant.User=" + Constant.User);
		PreferenceUtils.setPrefString(context, Constant.User, "");//清空


	}


	public void clearcache() {
		clearCacheFolder(this.getCacheDir(), System.currentTimeMillis());
		clearCookies(this);
		clearWebViewCache();
		SharedPreferences sp = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove("logincache");
		editor.remove("accid");
		editor.remove("acctoken");
		editor.remove("userimag");
		editor.commit();
		System.out.println("中间setting");
	}

	private int clearCacheFolder(File dir, long numDays) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@进入clearcache");
		int deletedFiles = 0;
		if (dir != null && dir.isDirectory()) {
			try {
				for ( File child : dir.listFiles() ) {
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, numDays);
					}
					if (child.lastModified() < numDays) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}

	public static void clearCookies(Context context) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		CookieSyncManager.getInstance().sync();
	}

	/**
	 * 清除WebView缓存
	 */
	public void clearWebViewCache() {

		//清理Webview缓存数据库
		try {
			deleteDatabase("webview.db");
			deleteDatabase("webviewCache.db");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//WebView 缓存文件
		File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);

		File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");

		//删除webview 缓存目录
		if (webviewCacheDir.exists()) {
			deleteFile(webviewCacheDir);
		}
		//删除webview 缓存 缓存目录
		if (appCacheDir.exists()) {
			deleteFile(appCacheDir);
		}
	}

	/**
	 * 递归删除 文件/文件夹
	 *
	 * @param file
	 */
	public void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for ( int i = 0; i < files.length; i++ ) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		} else {
		}
	}


	@Override
	public void successCallBack(String resultTag, BaseBean baseBean,
	                            String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
//        dissMissDialog(isShowDiolog);
//        successMethod(callBackMsg, resultTag);
//        sucMethiod(callBackMsg, resultTag);

	}


	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
	}

	/**
	 * 请求失败
	 */

	@Override
	public void startCallBack(String resultTag, boolean isShowDiolog, String showTitle) {
	}

	/**
	 * 开始
	 */

	@Override
	public void cancelCallBack(String resultTag) {
	}

	/**
	 * 取消
	 */

	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
	}

	/**
	 * 请求成功，但code!=1
	 */

	@Override
	public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
	}/** 请求成功,但要重新登录 */


}
