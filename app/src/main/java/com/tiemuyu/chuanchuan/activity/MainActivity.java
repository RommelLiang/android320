package com.tiemuyu.chuanchuan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppWakeUpListener;
import com.fm.openinstall.model.AppData;
import com.fm.openinstall.model.Error;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.tiemuyu.chuanchuan.activity.bean.CacheBean;
import com.tiemuyu.chuanchuan.activity.bean.Jump;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.MessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.NetworkActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.fragment.TextMessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.UnReadChange;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.FindFragment;
import com.tiemuyu.chuanchuan.activity.fragment.FriendFragment;
import com.tiemuyu.chuanchuan.activity.fragment.HomeFragment;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.helper.GuideHelper;
import com.tiemuyu.chuanchuan.activity.new_activities.FatuActivity;
import com.tiemuyu.chuanchuan.activity.share_auth.ShareAdapter;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.ViewTools;
import com.tiemuyu.chuanchuan.activity.view.CustomButton;
import com.tiemuyu.chuanchuan.activity.view.GuideView;
import com.tiemuyu.chuanchuan.activity.web.ExampleWebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.shareboard.SnsPlatform;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

//import com.tiemuyu.chuanchuan.activity.new_activities.FatuActivity;

/**
 * Created by Administrator on 2016/8/4.
 * 这个页面用于呈现主页面
 */
public class MainActivity extends NetworkActivity implements View.OnClickListener, NetResponses, UnReadChange, AppWakeUpListener {

	// 史力：以下三个用于友盟分享：
	private ListView listView;
	private ShareAdapter shareAdapter;
	public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
	private RelativeLayout header, LinearLayout_search;
	private LinearLayout goBackButton, top_right_linearlayout;
	private ImageView im_setting;
	private HomeFragment homeFragment;
	private FindFragment findFragment;
	private FriendFragment friendFragment;
	private MineFragment mineFragment;
	private FragmentManager manager = getFragmentManager();
	private FragmentTransaction transaction;
	private LinearLayout search, bottom, button_fatuxunjia;
	private CustomButton button_chuanchuan, button_kefu, button_faxian, button_wode;
	private CustomButton[] buttons = new CustomButton[4];
	private EditText search_btn;
	private ExampleWebViewClient client;
	private TextView textView2;
	public static CacheBean cacheBean = new CacheBean();// cashe数据
	private GuideView guideView;

	//以下变量为FY所加
	private TreeSet<Contacts> data1 = new TreeSet<>();
	private ArrayList<Contacts> dataList = new ArrayList<>();
	Contacts contacts;
	private TextView total;
	private boolean isIMLogin = false;

	public static boolean isTopTask = true;
	private int flag = 1;
	public static String OWN_HEADER_URL = "";//gao 在这里更改有效
	private DataSharedPress sharedPress;
	private String pushmessage = "pushmessage";
	private boolean isJump = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences jsp = PreferenceManager.getDefaultSharedPreferences(this);
		String json = jsp.getString(pushmessage, "");
		if (!json.equals("")) {
			Intent intent = new Intent(this, PushHistoryActivity.class);
			startActivity(intent);
		}
		GuideHelper guideHelper = new GuideHelper(this);
		guideHelper.openGuide();
		//史力：设置键盘上抬
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		//设置状态栏为透明
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		//gao 上面用哪个？
		setContentView(R.layout.activity_main);
		//一键跳转
		onNewIntent(getIntent());
		//以下by FY：与IM有关
		setNetResponses(this);
		MessageActivity messageActivity = new MessageActivity();
		sharedPress = DataSharedPress.getSharedPress(this);
		login();
		//以下三行代码：sp保存了用户名密码，每次启动app时toast出来用于检测
		SharedPreferences sp = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
		//Toast.makeText(getBaseContext(), "saved logincache: " + sp.getString("logincache", ""), Toast.LENGTH_SHORT).show();//for test only
		System.out.println("@@@onCreate()------");
		bottom = (LinearLayout) findViewById(R.id.bottom);
		client = ExampleWebViewClient.getInstance(this);
		registerObservers(incomingMessageObserver, true);
		header = (RelativeLayout) findViewById(R.id.header);
		search = (LinearLayout) findViewById(R.id.search);
		initView();
		client.setHeader(header, search, im_setting, bottom);
		ViewTools.showView(button_chuanchuan, buttons);
		transaction = manager.beginTransaction();
		homeFragment = new HomeFragment();
		homeFragment.setHeader(header, search, im_setting, bottom);
		transaction.add(R.id.fra_webView, homeFragment);
		transaction.show(homeFragment);
		transaction.commit();
		button_chuanchuan.setOnClickListener(this);
		button_kefu.setOnClickListener(this);
		button_fatuxunjia.setOnClickListener(this);
		button_faxian.setOnClickListener(this);
		button_wode.setOnClickListener(this);
		LinearLayout_search.setOnClickListener(this);
		top_right_linearlayout.setOnClickListener(this);
		search_btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					Log.e("tag", "search get foucus!");
					Intent intent = new Intent(MainActivity.this, NewSearchActivity.class);
					startActivity(intent);
				} else {
					Log.e("tag", "search lose focus!");
				}
			}
		});
		search_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewSearchActivity.class);
				startActivity(intent);
			}
		});

		textView2 = (TextView) ((RelativeLayout) header).findViewById(R.id.right_text);

		textView2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				System.out.println("click event");
				System.out.println("click saved");
				switch (flag) {
					case 1:
						System.out.println("1");
						//homeFragment.saveload();
						break;
					case 4:
						System.out.println("2");
						if (textView2.getText().equals("保存"))
							mineFragment.saveload();
						else//只有保存和不保存，不保存统一设为分享。并且目前只有minefragment有
							mineFragment.share();
						break;
				}
			}
		});

		goBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (flag) {
					case 1:
						//homeFragment.goBack();
						break;
					case 2:
						friendFragment.goBack();
						break;
					case 3:
						findFragment.goBack();
						break;
					case 4:
						mineFragment.goBack();
						break;
				}
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		change(sharedPress.getInt("unreadTotal"));
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		search_btn.clearFocus();
	}

	public void onStop() {
		super.onStop();
		System.out.println("重新启动");
		SharedPreferences sp = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);

		SharedPreferences.Editor editor = sp.edit();
		editor.putString("homebutton", "truesave");      //logincache字符串对应了savestr这个键值对，都存储在了UserInfo.xml这个文件里面
		editor.commit();
	}

	private void registerObservers(Observer<List<IMMessage>> immessage, boolean register) {
		MsgServiceObserve service = NIMClient.getService(MsgServiceObserve.class);
		service.observeReceiveMessage(immessage, register);
	}

	/**
	 * 消息接收观察者
	 */
	boolean isKuFu = false;
	Observer<List<IMMessage>> incomingMessageObserver = new Observer<List<IMMessage>>() {
		@Override
		public void onEvent(List<IMMessage> messages) {
			if (messages == null || messages.isEmpty()) {
				return;
			}
			for ( IMMessage message : messages ) {
				String sessionId = message.getSessionId();
				Log.e("MainActivity", "onEvent: " + sessionId);
				isKuFu = sessionId.startsWith("90") ? true : false;
				if (message.getTime() > sharedPress.getLong(sessionId + "Time") && isKuFu) {
					Log.e("MainActivity", "onEvent: " + sharedPress.getLong(sessionId + "Time") + ":" + isKuFu);
					sharedPress.putInt(sessionId + "unread", sharedPress.getInt(sessionId + "unread") + 1);
					sharedPress.putInt("unreadTotal", sharedPress.getInt("unreadTotal") + 1);
					sharedPress.putString(sessionId + "content", message.getContent());
					sharedPress.putLong(sessionId + "Time", message.getTime());
				}
			}
			change(sharedPress.getInt("unreadTotal"));
		}
	};

	void initView() {
		LinearLayout_search = (RelativeLayout) findViewById(R.id.ly_search);
		top_right_linearlayout = (LinearLayout) findViewById(R.id.message_top_right);
		button_chuanchuan = (CustomButton) findViewById(R.id.main_ccbtn);
		button_kefu = (CustomButton) findViewById(R.id.main_kfbtn);
		button_fatuxunjia = (LinearLayout) findViewById(R.id.main_ccftxj);
		button_faxian = (CustomButton) findViewById(R.id.main_ccfx);
		button_wode = (CustomButton) findViewById(R.id.main_ccwd);
		im_setting = (ImageView) findViewById(R.id.im_setting);
		total = (TextView) findViewById(R.id.message_total);
		goBackButton = (LinearLayout) findViewById(R.id.goBack);
		search_btn = (EditText) findViewById(R.id.ed_search);//首页上面的搜索框
		buttons[0] = button_chuanchuan;
		buttons[1] = button_kefu;
		buttons[2] = button_faxian;
		buttons[3] = button_wode;
		search.setOnClickListener(this);
		button_chuanchuan.setBackgroundResource(R.drawable.tab_home);
		button_faxian.setBackgroundResource(R.drawable.tab_find);
		button_wode.setBackgroundResource(R.drawable.tab_mine);
		button_kefu.setBackgroundResource(R.drawable.tab_frend);
		button_chuanchuan.setText("穿穿");
		button_kefu.setText("客服");
		button_faxian.setText("发现");
		button_wode.setText("我的");
		initUrlsData();

	}

	private long agoTime = 0L;

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!isTopTask && (keyCode == KeyEvent.KEYCODE_BACK)) {
			System.out.println("*****@@@onKeyDown()------");
			switch (flag) {
				case 1:
					//homeFragment.goBack();
					break;
				case 2:
					friendFragment.goBack();
					break;
				case 3:
					findFragment.goBack();
					break;
				case 4:
					mineFragment.goBack();
					break;
			}
			return true;
		} else {
			long currentTime = System.currentTimeMillis();
			if (currentTime - agoTime < 2000) {
				System.exit(0);
			} else {
				agoTime = currentTime;
				Toast.makeText(MainActivity.this, "再按一次返回键将退出程序", Toast.LENGTH_LONG).show();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private void initUrlsData() {
		//urlsBean = DBTools.getAppUrls(MainActivity.this);
		cacheBean = DBTools.getWebCache(MainActivity.this);
	}

	@Override
	public void onClick(View v) {
		isTopTask = true;
		transaction = manager.beginTransaction();
		SharedPreferences sp = getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
		String usernameFromSP = sp.getString("logincache", "");
		System.out.println("logincache after onclick: " + usernameFromSP);
		switch (v.getId()) {
			case R.id.ly_search://点击上面的文字输入框进入搜索activity
				Intent intent3 = new Intent(MainActivity.this, SearchActivity.class);
				startActivity(intent3);
				break;
			//穿穿
			case R.id.main_ccbtn:
				flag = 1;
				im_setting.setVisibility(View.GONE);
				ViewTools.showView(button_chuanchuan, buttons);
				ViewTools.changeTitle(header, search, null);
				transaction.show(homeFragment);
				//homeFragment.refreshPage();
				if (findFragment != null) {
					transaction.hide(findFragment);
				}
				if (mineFragment != null) {
					transaction.hide(mineFragment);
				}
				if (friendFragment != null) {
					transaction.hide(friendFragment);
				}
				break;
			//客服
			case R.id.message_top_right:
				if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {
					Toast.makeText(this, "没登录", Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(MainActivity.this, PushHistoryActivity.class);
					startActivity(intent);
				}
				break;
			case R.id.main_kfbtn:


				flag = 2;

				if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {
					Toast.makeText(this, "没登录", Toast.LENGTH_SHORT).show();
				} else if (isIMLogin) {
					keFu();
				} else {
					isJump = true;
					ToastHelper.show(MainActivity.this, "正在登录");
					login();
				}/*else if (isIMLogin) {
		            //Toast.makeText(getApplicationContext(), "第一个！", Toast.LENGTH_SHORT).show();
                    System.out.println("accid after kefu is clicked: " + sp.getString("accid", ""));
                    System.out.println("acctoken after kefu is clicked: " + sp.getString("acctoken", ""));
                    Intent intent1 = new Intent(MainActivity.this, MessageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("data", dataList);
                    intent1.putExtra("bundle", bundle);
                    startActivity(intent1);
                } else {
                    login();
                }*/


				break;


			//发现
			case R.id.main_ccfx:
				flag = 3;
				im_setting.setVisibility(View.GONE);
				ViewTools.showView(button_faxian, buttons);
				ViewTools.changeTitle(header, search, "发现", false);
				ViewTools.showText(header, search, "omg", false);
				if (findFragment == null) {
					findFragment = new FindFragment();
					findFragment.setHeader(header, search, im_setting, bottom);
					transaction.add(R.id.fra_webView, findFragment);
				}
				transaction.show(findFragment);
				transaction.hide(homeFragment);
				if (friendFragment != null) {
					transaction.hide(friendFragment);
				}
				if (mineFragment != null) {
					transaction.hide(mineFragment);
				}
				break;
			//发图询价
			case R.id.main_ccftxj:


				if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {

					AlertDialog.Builder multiDia = new AlertDialog.Builder(MainActivity.this);
					multiDia.setTitle("亲，您还没登录哦！ ");
					multiDia.setPositiveButton("现在登录", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast toast = Toast.makeText(getApplicationContext(), "点击右下角\"我的\"登录！", Toast.LENGTH_LONG);//.show();
							toast.setGravity(Gravity.CENTER, 0, 10);
							toast.show();
						}
					});
					multiDia.setNeutralButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stubs
						}
					});
					multiDia.create().show();

				} else {


					ClassJumpTool.startToNextActivityForResult(this,
							FatuActivity.class, 10);


				}

				break;
			//我的
			case R.id.main_ccwd:
				flag = 4;
				ViewTools.showView(button_wode, buttons);
				ViewTools.changeTitle(header, search, "");
				if (mineFragment == null) {
					mineFragment = new MineFragment();
					mineFragment.setHeader(header, search, im_setting, bottom);
					transaction.add(R.id.fra_webView, mineFragment);
				} else {
					mineFragment.www();
				}
				transaction.show(mineFragment);
				im_setting.setVisibility(View.VISIBLE);
				transaction.hide(homeFragment);
				if (friendFragment != null) {
					transaction.hide(friendFragment);
				}
				if (findFragment != null) {
					transaction.hide(findFragment);
				}
				break;
		}
		System.out.println("!!!ddd        minefragment got");
		transaction.commit();
	}

	private void keFu() {
		Intent intent1 = new Intent(MainActivity.this, TextMessageActivity.class);
		intent1.putExtra("sessionId", SPUtils.getKefuCode());
		intent1.putExtra("title", "定制助理（9:00-24:00）");
		startActivity(intent1);
		DataSharedPress sharedPress = DataSharedPress.getSharedPress(this);
		int total = sharedPress.getInt("unreadTotal");
		sharedPress.putInt("unreadTotal", 0);
	}

	private void getIMLoginParameter(String id) {
		request(Request.Method.GET, "http://imserver.myappcc.com/api/Getuseracc?value=" + id, null, "", tag, 200);
	}

	public void login() {
		//LoginInfo loginInfo = new LoginInfo("tmy1", "68c58f02597daa4fdc3ab86ed103e0c6");//测试账号
		if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {
			return;
		}
		String accid = SPUtils.getAccid();
		User now = DBTools.getUser();
		Log.e("login: ", now + "!");
		if (now.getAccid() == null) {
			Toast.makeText(this, "身份信息异常", Toast.LENGTH_SHORT).show();
		}
		LoginInfo loginInfo = new LoginInfo(now.getAccid(), now.getAccToken());
//
		String getinfo = now.getUserImg();
		///sp.getString("userimag", "");
		//获取用户的im头像 gao
		OWN_HEADER_URL = getinfo;
		RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {

			@Override
			public void onSuccess(LoginInfo loginInfo) {
				Log.e("onSuccess: ", loginInfo.getToken());
				isIMLogin = true;
				if (isJump) {
					keFu();
					isJump = false;
				}
				request(Request.Method.GET, "http://imserver.myappcc.com/api/Getuseracc", null, "", tag, 100);
			}

			@Override
			public void onFailed(int i) {
				System.out.println("login failed===" + i);
				Log.e("onFailed: ", i + "!!!!!!!!!!!");
				if (isJump) {
					switch (i) {
						case 408:
							Toast.makeText(MainActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
							break;
					}
				}
			}

			@Override
			public void onException(Throwable throwable) {
				Log.e("onFailed: ", throwable.getLocalizedMessage());
			}
		};
		NIMClient.getService(AuthService.class).login(loginInfo).setCallback(callback);
	}

	@Override
	public void success(int type, JSONObject jsonObject) {
		Log.e("success: ", type + "");
		Contacts contacts;
		if (type == 100) {
			JSONArray array = jsonObject.optJSONArray("kefulist");
			Log.e("array", "success: " + array);
			for ( int i = 0; i < array.length(); i++ ) {
				contacts = new Contacts(Parcel.obtain());
				JSONObject object = array.optJSONObject(i);
				contacts.setName(object.optString("nickname"));
				contacts.setAccid(object.optString("accid"));
				contacts.setHeader(object.optString("userimg"));
				dataList.add(contacts);
			}
		} else if (type == 200) {
			System.out.println("*********" + jsonObject.toString());
			sharedPress.putString("accid", jsonObject.optString("accid"));
			sharedPress.putString("token", jsonObject.optString("token"));
			OWN_HEADER_URL = jsonObject.optString("userimg");
			login();
		}
	}

	@Override
	public void fail() {
		System.out.println("*********request failed");
	}

	public void clearcache() {
		clearCacheFolder(this.getCacheDir(), System.currentTimeMillis());
		clearCookies(this);
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

	public void information(String validcode) {
		mineFragment.loadinformation(validcode);
	}

	public void sessionweb(String x, String x1) {
		System.out.println(x + "zaban");
		mineFragment.sessionweb(x, x1);
	}

	private void refresh() {
		finish();
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
		startActivity(intent);
	}

	//关闭回退的方法
	public void hideback() {
		System.out.println("hideback");
		goBackButton.setVisibility(View.GONE);
	}

	public void share_in_android_in_mainactivity(String title, String text, String image_url, String share_url, String type) {
		System.out.println("进入share_in_android_in_main");
		mineFragment.share();
	}


	//与js交互类
	public class WebAppInterface {
		Context mContext;

		//初始化类
		WebAppInterface(Context c) {
			mContext = c;
		}
	}

	@Override
	public void change(int sum) {
		if (sum > 0) {
			total.setVisibility(View.VISIBLE);
			total.setText(sum + "");
		} else {
			total.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** attention to this below ,must add this**/
		UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
		com.umeng.socialize.utils.Log.d("result", "onActivityResult");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.e("onNewIntent", "onNewIntent: " + intent.toString());
		setIntent(intent);
		OpenInstall.getWakeUp(this, this);
	}

	@Override
	public void onWakeUpFinish(AppData appData, Error error) {
		if (error == null) {
			Log.e("MainActivity", "OpenInstall wakeup-data : " + appData.toString());
			Jump jump = GsonUtils.fromData(appData.getData(), Jump.class);
			switch (jump.getType()) {
				case 1:
					//专题瀑布流

					break;
				case 2:
					// 产品详情
					Intent intent2 = new Intent(MainActivity.this, ClothesDetailsActivity.class);
					intent2.putExtra("productid", jump.getUid());
					startActivity(intent2);
					break;
				case 3:
					//定制详情
					Intent intent3 = new Intent(MainActivity.this, ChengpinDetailActivity.class);
					intent3.putExtra("productid", jump.getUid());
					startActivity(intent3);
					break;
				case 4:
					//文章

					break;
			}
		} else {
			Log.e("MainActivity", "error : " + error.toString());
		}
	}
}
