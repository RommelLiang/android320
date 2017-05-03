package com.tiemuyu.chuanchuan.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.chat_tools.view.CircleImageView;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PicassoImageLoader;
import com.tiemuyu.chuanchuan.activity.util.ServerUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.Utility;
import com.tiemuyu.chuanchuan.activity.view.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.tiemuyu.chuanchuan.activity.fragment.MineFragment.user;

/**
 * Created by Weihao Gao on 2017/1/27.
 */

public class MyNick extends BaseActivityG {


	@ViewInject(R.id.iv_touxiang)
	private CircleImageView iv_touxiang;


	@ViewInject(R.id.et_nicheng)
	private EditText et_nicheng;


	@ViewInject(R.id.nick_finish)
	private Button nick_finish;

	public Uri imageUri;

	private String usrImg = "";// 头像地址
	private String nc;
	public static final int TAKE_PHOTO = 1;
	public static final int SELECT_FROM_ALBUM = 2;
	public String result = "";
	ArrayList<ImageItem> list;

	private String TAG_REGIST_MODIFY = "TAG_REGIST_MODIFY";

	private static final int IMAGE_PICKER = 255;
	private ImagePicker imagePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_nick);
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		//  Constant.VERSION = Version.getAppVersionName(this);
		// _global = GlobalVariable.getInstance();
		initProcess();
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
		imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
		imagePicker.setShowCamera(true);  //显示拍照按钮
		imagePicker.setCrop(true);        //允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true); //是否按矩形区域保存
		imagePicker.setSelectLimit(1);    //选中数量限制
		imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
		imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
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
	}

	protected void initUI() {


		iv_touxiang = (CircleImageView) findViewById(R.id.iv_touxiang);

		et_nicheng = (EditText) findViewById(R.id.et_nicheng);

		et_nicheng.setText(user.getNickName());

		nick_finish = (Button) findViewById(R.id.nick_finish);

		ImageLoader.getInstance().displayImage(user.getUserImg(), iv_touxiang);


	}

	protected void initListener() {

		iv_touxiang.setOnClickListener(this);

		nick_finish.setOnClickListener(this);


	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.iv_touxiang:
				if (Utility.isFastDoubleClick()) // 连续点击
					return;
				//todo  调用拍照
				exFatu();


				break;

			case R.id.nick_finish:
				if (Utility.isFastDoubleClick()) // 连续点击
					return;
				nc = et_nicheng.getText().toString().trim();
				if (user != null) {
					if (!nc.equals(user.getNickName())
							|| !usrImg.equals(user.getUserImg())) {

						if (usrImg == "")
							usrImg = user.getUserImg();


						System.out.println("----user 设置");
						MyApplication.poolManager
								.addAsyncTask(new ThreadPoolTaskHttp(this,
										TAG_REGIST_MODIFY, Constant.REQUEST_POST,
										ParamsTools.modify(
												UrlManager.MODIFY_ZILIAO(), usrImg,
												nc), this, "设置中...", true));

					} else {
						System.out.println("----user 关闭");

						closeActivity();
//                        AppManager.getAppManager().finishActivity(
//                                MyInfo.class);
					}
				} else {
					System.out.println("----user is  null");
				}


				break;


		}
	}


	/**
	 * 发图
	 */
	public void exFatu() {
		Intent intent = new Intent(this, ImageGridActivity.class);
		startActivityForResult(intent, IMAGE_PICKER);

	}


	//史力：接收相机或相册返回图片uri
	@SuppressWarnings("deprecation")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.e("onActivityResult: ", requestCode + ":" + resultCode);
		if (resultCode == 1004) {
			if (data != null && requestCode == 255) {
				list = new ArrayList<>();
				list = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				new Thread(new Runnable() {
					@Override
					public void run() {
						//调用接口传图
						//根据funflag判断调用不同的url
						Log.e("image!!!", list.get(0).path);
						result = ServerUtils.formUpload(URL.UPLOAD_URLUSERIMG, list.get(0).path);
						System.out.println("*******result: " + result);
						if (!TextUtils.isEmpty(result)) {
							System.out.println("******进入非空判断");
							handler.sendEmptyMessage(1);
							System.out.println(result);//成功
						} else {
							handler.sendEmptyMessage(2);//失败
						}
					}
				}).start();
			} else {
				Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 1:
					Toast.makeText(getApplicationContext(), "点击完成保存修改", Toast.LENGTH_SHORT).show();
					System.out.println("*******图片上传成功");
					addPageLoad();
					break;
				case 2:
					Toast.makeText(getApplicationContext(), "图片上传失败", Toast.LENGTH_SHORT).show();
					System.out.println("*******图片上传失败");
					break;
				default:
					break;
			}

		}
	};

	public void addPageLoad() {
		if (!TextUtils.isEmpty(result)) {
			System.out.println(result + "@@@@基类@@成功上传图片");
			//String str = "{\"Code\":1,\"Msg\":\"OK\",\"Data\":{\"ImageUrl\":\"http://f1.myappcc.com/zfs/7E0/1240/RIC/240174344351CABGTOTZGT.jpg\"}}";
			try {
				JSONObject jsonObject = new JSONObject(result);
				String s_data = jsonObject.getString("Data");
				JSONObject jsonObject1 = new JSONObject(s_data);
				final String json_url = jsonObject1.getString("ImageUrl");
				System.out.println(json_url);
				//todo 把json_url发送给ui重置用个handler


				usrImg = json_url;
				Log.e("addPageLoad: ", usrImg);
				ImageLoader.getInstance().displayImage(usrImg, iv_touxiang);

//                setMsg(0,json_url);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		System.out.println("!!!!!!!!!!走完页面加载程序");
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
		if (resultTag.equals("TAG_REGIST_MODIFY")) {
			System.out.println("######更改用户昵称和头像回调:" + msg);
			// ToastHelper.show(getActivity(), "自动登录成功");
			ToastHelper.show(this, "设置成功");
			user.setUserImg(usrImg);
			user.setNickName(nc);

//            MineFragment.user = user;

			DBTools.loginDb(this, user);//修改数据库信息


//            setView(false, false, false);

			// ClassJumpTool.startToNextActivity(this,
			// RewardRegistActivity.class);

//            toRegistSuecc();

			AppManager.getAppManager().finishActivity(this);
			AppManager.getAppManager().finishActivity(MyInfo.class);


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


	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString("msg_camera_picname", String.valueOf(imageUri));
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e("onDestroy: ", "傻逼小米");
	}
}
