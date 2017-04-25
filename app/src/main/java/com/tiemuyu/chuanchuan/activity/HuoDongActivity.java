package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.HuDongBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.RoundProgressBar;

import org.xutils.http.RequestParams;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HuoDongActivity extends BaseActivityG {
	private int count = 0;
	private RoundProgressBar roundProgressBar;
	private static final String TAG_GETHUODONG = "TAG_GETHUODONG";
	private HuDongBean mHuDongBean;
	private HuDongBean.DataBean mDataBean;
	private List<HuDongBean.DataBean> mData;
	private ImageView im_huodong;
	private Date mStart;
	private Date mEnd;
	private int huo_dong_id;
	public static final String strDir = "/data/chuanchuan/";
	public static final String name = "huodong.jpg";
	public static final String image_url = "/storage/emulated/0/DCIM/data/chuanchuan/huodong.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		setContentView(R.layout.activity_huo_dong);
		roundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
		im_huodong = (ImageView) findViewById(R.id.im_huodong);
		roundProgressBar.setMax(4000);
		mc = new MyCountDownTimer(4000, 10);
		mc.start();
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_GETHUODONG,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.getstartpage()),
						this,
						"获取活动图片",
						false));
		roundProgressBar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				jump();
			}
		});
		Log.e("onCreate: ", Environment.getExternalStorageDirectory()+strDir+name);
		if (SPUtils.getHuoDongID() != 0) {
			Log.e("onCreate: ", isShowActivity() + "dsdsd");
			if (isShowActivity()) {
				Picasso.with(this)
						.load(new File(image_url))
						.error(R.drawable.splish)
						.into(im_huodong);
				im_huodong.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent;
						String huoDongUrl = SPUtils.getHuoDongUrl();
						int type = 10;
						Context nContext = HuoDongActivity.this;
						type = JudgmentLegal.checkBannerType(huoDongUrl);
						Log.e("onItemClick: ", type + "");
						if (type == 0) {
							String[] sourceStrArray = huoDongUrl.split("id=");
							huo_dong_id = Integer.parseInt(sourceStrArray[1]);
							Log.e("OnBannerClick: ", huo_dong_id + "");
							intent = new Intent(nContext, FindWaterActivity.class);
							intent.putExtra("id", (int) huo_dong_id);
							nContext.startActivity(intent);
						} else if (type == 1) {
							intent = new Intent(nContext, MyWebview.class);
							intent.putExtra("Intent_Data_Packet", huoDongUrl);
							intent.putExtra("title", SPUtils.getHuoDongMiashu());
							intent.putExtra("type", 1);
							intent.putExtra("img_url", SPUtils.getHuoDongImage());
							nContext.startActivity(intent);
						} else if (type == 2) {
							String[] sourceStrArray = huoDongUrl.split("id=");
							huo_dong_id = Integer.parseInt(sourceStrArray[1]);
							intent = new Intent(nContext, FindTopicActivity.class);
							intent.putExtra("id", (int) huo_dong_id);
							nContext.startActivity(intent);
						} else {
						}

					}
				});
			}
		}
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		if (resultTag.equals(TAG_GETHUODONG)) {
			Log.e("HuoDongActivity: ", callBackMsg);
			mHuDongBean = GsonUtils.fromData(callBackMsg, HuDongBean.class);
			mData = mHuDongBean.getData();
			if (mData != null) {
				mDataBean = mData.get(0);
				int huoDongID = SPUtils.getHuoDongID();
				if (huoDongID != mDataBean.getId()) {
					SPUtils.saveHuoDongID(mDataBean.getId());
					SPUtils.setStartTimeHuodong(mDataBean.getStartTime());
					SPUtils.setEndHuodong(mDataBean.getEndTime());
					SPUtils.setHuoDongUrl(mDataBean.getUrl());
					SPUtils.setHuoDongImage(mDataBean.getBigimg());
					SPUtils.setHuoDongMiashu(mDataBean.getMiaoshu());
					Log.e("download", "successCallBack: ");
					getBitmapFromUri(mDataBean.getBigimg());
				}
				if (guideCheck()) {
					Picasso.with(HuoDongActivity.this)
							.load(mDataBean.getBigimg())
							.error(R.drawable.splish)
							.into(im_huodong);
				}
			}
		}

	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		if (resultTag.equals(TAG_GETHUODONG)) {

		}
	}

	private MyCountDownTimer mc;

	/**
	 * 继承 CountDownTimer 防范
	 * <p>
	 * 重写 父类的方法 onTick() 、 onFinish()
	 */

	class MyCountDownTimer extends CountDownTimer {
		/**
		 * @param millisInFuture    表示以毫秒为单位 倒计时的总数
		 *                          <p>
		 *                          例如 millisInFuture=1000 表示1秒
		 * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
		 *                          <p>
		 *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
		 */
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		public void onFinish() {
			jump();
		}

		public void onTick(long millisUntilFinished) {
			count++;
			roundProgressBar.setProgress((int) (4000 - millisUntilFinished));
			if (count % 42 == 0) {
				roundProgressBar.setTime(5 - count / 42);
			}
		}

	}

	private void jump() {
		Intent mainIntent = new Intent(HuoDongActivity.this, MainActivity.class);
		mainIntent.putExtras(getIntent());
		startActivity(mainIntent);
		finish();
	}

	private boolean isShowActivity() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		long time = date.getTime();
		String format = sdf.format(new Date());
		Log.e("isShowActivity: ", format);
		String startTime = SPUtils.getStartTimeHuodong();
		String endTime = SPUtils.getEndHuodong();
		try {
			mStart = sdf.parse(startTime);
			mEnd = sdf.parse(endTime);
		} catch (ParseException mE) {
			return false;
		}
		if (mStart.before(date) && mEnd.after(date)) {
			return true;
		}
		return false;
	}

	private int GUIDE_VERSION_CODE = 1;
	private String GUIDE_VERSION_NAME = "is_first_" + R.string.versionCode;

	private boolean guideCheck() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int guideVer = sp.getInt(GUIDE_VERSION_NAME, 0);
		Log.e("guideCheck: ", "guideVer:" + guideVer + "  GUIDE_VERSION_CODE:" + GUIDE_VERSION_CODE);
		if (GUIDE_VERSION_CODE > 0 && GUIDE_VERSION_CODE > guideVer) {
			return true;
		} else {
			return false;
		}
	}


	private void getBitmapFromUri(String url) {
		Target target = new Target(){
			@Override
			public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
				File dcimFile = getDCIMFile();
				Log.e("bitmap=",bitmap+"");
				FileOutputStream ostream = null;
				try {
					ostream = new FileOutputStream(dcimFile);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
					ostream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Log.e("onBitmapLoaded: ", dcimFile+"");
			}

			@Override
			public void onBitmapFailed(Drawable errorDrawable) {

			}

			@Override
			public void onPrepareLoad(Drawable placeHolderDrawable) {

			}
		};
		//Picasso下载
		Picasso.with(this).load(url).into(target);
	}
	public  File getDCIMFile() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { // 文件可用
			File dirs = new File(Environment.getExternalStorageDirectory(),
					"DCIM"+strDir);
			if (!dirs.exists())
				dirs.mkdirs();

			File file = new File(Environment.getExternalStorageDirectory(),
					"DCIM"+strDir+name);
			if (!file.exists()) {
				try {
					//在指定的文件夹中创建文件
					file.createNewFile();
				} catch (Exception e) {
				}
			}
			return file;
		} else {
			return null;
		}

	}
}
