package com.tiemuyu.chuanchuan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.BodyBeanNew;
import com.tiemuyu.chuanchuan.activity.bean.BodyDataBean;
import com.tiemuyu.chuanchuan.activity.bean.SendBodyBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.BodyEditText;

import org.xutils.http.RequestParams;

/**
 * Created by Weihao Gao on 2017/1/28.
 */

public class MyBody extends BaseActivityG {

	private int edit_tag;//0表示按钮显示修改，1表示按钮显示保存
	private static final String TAG_GETBODYINFO = "TAG_GETBODYINFO";
	private static final String TAG_SET_OLD__BODY_DATA = "TAG_SET_OLD__BODY_DATA";
	private static final String TAG_GETBODY_BY_ID = "TAG_GETBODY_BY_ID";

	private BodyDataBean publicBodyDataBean;

	private ProgressDialog pd;
	private LinearLayout go_bk;
	private TextView save_body_data;
	private ImageView male_btn;
	private ImageView female_btn;
	private TextView usr_age;
	private TextView usr_weight;
	private TextView usr_height;
	private Spinner size_spinner;
	private Spinner shape_spinner;
	private Button edit_body;

	//sl: 十个身体数据各自对应的文本输入框
	private BodyEditText jiankuan;
	private BodyEditText datuiwei;
	private BodyEditText xiongwei;
	private BodyEditText xiaotuiwei;
	private BodyEditText yaowei;
	private BodyEditText xiwei;
	private BodyEditText tunwei;
	private BodyEditText jiaowei;
	private BodyEditText xiuchang;
	private BodyEditText kuchang;
	private EditText usr_name;
	private int gender;
	private String size;
	private String shape;
	private int xiguan;
	private ImageView im_body_gender;
	private SendBodyBean mSendBodyBean;
	private Intent mIntent;
	private int mType;
	private int mId;
	private BodyBeanNew mBodyBeanNew;
	private Button btn_jinshen,btn_kuansong,btn_heti;
	private int chuangyixiguan = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_body);
		pd = new ProgressDialog(this);//加载的ProgressDialog
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
		pd.setMessage("保存中....");
		mIntent = getIntent();
		mType = mIntent.getIntExtra("type", 0);

		findViewById(R.id.rl_jiaocheng).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ClassJumpTool.startToNextActivity(MyBody.this, MyWebview.class, "http://ios.myappcc.com/h5/liangyi.html");
			}
		});
		//sl:给控件赋值
		go_bk = (LinearLayout) findViewById(R.id.body_back);
		save_body_data = (TextView) findViewById(R.id.body_size_save);
		male_btn = (ImageView) findViewById(R.id.male_btn);
		female_btn = (ImageView) findViewById(R.id.female_btn);
		usr_age = (TextView) findViewById(R.id.usr_age);
		usr_weight = (TextView) findViewById(R.id.usr_weight);
		usr_height = (TextView) findViewById(R.id.usr_height);
		size_spinner = (Spinner) findViewById(R.id.size_spinner);
		shape_spinner = (Spinner) findViewById(R.id.shape_spinner);
		edit_body = (Button) findViewById(R.id.edit_body);
		im_body_gender = (ImageView) findViewById(R.id.im_body_gender);
		btn_heti = (Button) findViewById(R.id.btn_heti);
		btn_jinshen = (Button) findViewById(R.id.btn_jinshen);
		btn_kuansong = (Button) findViewById(R.id.btn_kuansong);
		btn_heti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chuangyixiguan = 1;
				btn_heti.setBackgroundResource(R.drawable.button_background_xiguan);
				btn_jinshen.setBackgroundResource(R.drawable.button_background_xiguan_no);
				btn_kuansong.setBackgroundResource(R.drawable.button_background_xiguan_no);
			}
		});
		btn_jinshen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chuangyixiguan = 0;
				btn_heti.setBackgroundResource(R.drawable.button_background_xiguan_no);
				btn_jinshen.setBackgroundResource(R.drawable.button_background_xiguan);
				btn_kuansong.setBackgroundResource(R.drawable.button_background_xiguan_no);
			}
		});
		btn_kuansong.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chuangyixiguan = 2;
				btn_heti.setBackgroundResource(R.drawable.button_background_xiguan_no);
				btn_jinshen.setBackgroundResource(R.drawable.button_background_xiguan_no);
				btn_kuansong.setBackgroundResource(R.drawable.button_background_xiguan);
			}
		});
		edit_tag = 0;
		ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,
				R.array.body_size, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> adapter_shape = ArrayAdapter.createFromResource(this,
				R.array.body_shape, android.R.layout.simple_spinner_item);
		size_spinner.setAdapter(adapter_size);
		shape_spinner.setAdapter(adapter_shape);
		//sl: 给10个身体数据文本框赋控件
		jiankuan = (BodyEditText) findViewById(R.id.jiankuan);
		datuiwei = (BodyEditText) findViewById(R.id.datuiwei);
		xiongwei = (BodyEditText) findViewById(R.id.xiongwei);
		xiaotuiwei = (BodyEditText) findViewById(R.id.xiaotuiwei);
		yaowei = (BodyEditText) findViewById(R.id.yaowei);
		xiwei = (BodyEditText) findViewById(R.id.xiwei);
		tunwei = (BodyEditText) findViewById(R.id.tunwei);
		jiaowei = (BodyEditText) findViewById(R.id.jiaowei);
		xiuchang = (BodyEditText) findViewById(R.id.xiuchang);
		kuchang = (BodyEditText) findViewById(R.id.kuchang);
		usr_name = (EditText) findViewById(R.id.usr_name);
		if (mType == 0) {
			pd.show();
			MyApplication.poolManager.addAsyncTask(
					new ThreadPoolTaskHttp(this,
							TAG_GETBODYINFO,
							Constant.REQUEST_GET,
							new RequestParams(
									UrlManager.GET_MYBODY()),
							this,
							"获取water",
							false
					));
			usr_name.setEnabled(false);
			usr_name.setText(DBTools.getUser().getNickName());
		} else {
			mId = mIntent.getIntExtra("id", 0);
			if (mId != 0) {
				MyApplication.poolManager.addAsyncTask(
						new ThreadPoolTaskHttp(this,
								TAG_GETBODY_BY_ID,
								Constant.REQUEST_GET,
								new RequestParams(
										UrlManager.getBodyById(mId+"")),
								this,
								"获取water",
								false
						));
			}
		}
		jiankuan.setUnderLine(false);
		datuiwei.setUnderLine(false);
		xiongwei.setUnderLine(false);
		xiaotuiwei.setUnderLine(false);
		yaowei.setUnderLine(false);
		xiwei.setUnderLine(false);
		tunwei.setUnderLine(false);
		jiaowei.setUnderLine(false);
		xiuchang.setUnderLine(false);
		kuchang.setUnderLine(false);
		go_bk.setOnClickListener(new BodyBackClickListener());
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		gender = 1;
		save_body_data.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pd.show();
				if (mType == 0) {
					Log.e("tag", "userID in body: " + DBTools.getUser().getUserId());//测试是否可以输出userID，测试通过
					//todo 给infobean赋值
					BodyDataBean tmp = publicBodyDataBean;
					BodyDataBean.DataBean dataBean = new BodyDataBean.DataBean();
					dataBean.setAge(Integer.parseInt(String.valueOf(usr_age.getText())));
					dataBean.setGender(gender);
					if (String.valueOf(usr_weight.getText()).equals("") || String.valueOf(usr_height.getText()).equals("")) {
						Toast.makeText(MyBody.this, "身高和体重不能为空", Toast.LENGTH_SHORT).show();
					} else {
						dataBean.setWeight(Double.parseDouble(String.valueOf(usr_weight.getText())));
						dataBean.setHeight(Double.parseDouble(String.valueOf(usr_height.getText())));
					}
					if (shape == null) {
						dataBean.setClothSize("L");
					} else {
						dataBean.setClothSize(size);
					}
					if (shape == null) {
						dataBean.setClothSize("S型");
					} else {
						dataBean.setPhysique(shape);
					}
					dataBean.setPhysique(shape);
					dataBean.setShoulderBreadth(jiankuan.getUserInput());
					dataBean.setBust(xiongwei.getUserInput());
					dataBean.setWaist(yaowei.getUserInput());
					dataBean.setHip(tunwei.getUserInput());
					dataBean.setSleeve(xiuchang.getUserInput());
					dataBean.setThighCirc(datuiwei.getUserInput());
					dataBean.setCalfCirc(xiaotuiwei.getUserInput());
					dataBean.setKneeCirc(xiwei.getUserInput());
					dataBean.setArmCirc(jiaowei.getUserInput());
					dataBean.setPants(kuchang.getUserInput());
					dataBean.setTEBIESHUOMING("");
					dataBean.setCHUANYIXIGUAN(chuangyixiguan);
					dataBean.setISRADIO(0);
					dataBean.setBmi(0.0);
					tmp.setData(dataBean);
					editBodyData(tmp);
				} else {
					mSendBodyBean = new SendBodyBean();
					mSendBodyBean.setSHOULDER_BREADTH(jiankuan.getUserInput());
					mSendBodyBean.setLOGO("s");
					mSendBodyBean.setCHUANYIXUG(chuangyixiguan);
					mSendBodyBean.setBUST(xiongwei.getUserInput());
					mSendBodyBean.setBodyOther("s");
					mSendBodyBean.setKNEE_CIRC(xiwei.getUserInput());
					mSendBodyBean.setWAIST(yaowei.getUserInput());
					mSendBodyBean.setGENDER(gender);
					mSendBodyBean.setDRESS_EFFECT(0);
					mSendBodyBean.setSLEEVE(xiuchang.getUserInput());
					String name = String.valueOf(usr_name.getText());
					if (name == null || name.equals("")) {
						name = DBTools.getUser().getNickName();
					}
					if (String.valueOf(usr_weight.getText()).equals("") || String.valueOf(usr_height.getText()).equals("")) {
						Toast.makeText(MyBody.this, "身高和体重不能为空", Toast.LENGTH_SHORT).show();
						pd.dismiss();
						return;
					}
					mSendBodyBean.setName(name);
					mSendBodyBean.setHEIGHT(Double.parseDouble(String.valueOf(usr_height.getText())));
					mSendBodyBean.setWAIST_POSTION(0);
					mSendBodyBean.setCALF_CIRC(xiaotuiwei.getUserInput());
					mSendBodyBean.setBMI(0.0);
					mSendBodyBean.setAGE(Integer.parseInt(String.valueOf(usr_age.getText())));
					mSendBodyBean.setISRADIO(0);
					mSendBodyBean.setBODY_PIC("s");
					mSendBodyBean.setUSER_ID((int) DBTools.getUser().getUserId());
					mSendBodyBean.setBodySummary("s");
					if (size == null) {
						mSendBodyBean.setCLOTH_SIZE("L");
					} else {
						mSendBodyBean.setCLOTH_SIZE(size);
					}
					mSendBodyBean.setID(mId);
					mSendBodyBean.setHIP(tunwei.getUserInput());
					if (shape == null) {
						mSendBodyBean.setPHYSIQUE("S型");
					} else {
						mSendBodyBean.setPHYSIQUE(shape);
					}
					mSendBodyBean.setPANTS(kuchang.getUserInput());
					mSendBodyBean.setWEIGHT(Double.parseDouble(String.valueOf(usr_weight.getText())));
					mSendBodyBean.setSKIN_COLOR("s");
					mSendBodyBean.setBODY_PIC("s");
					mSendBodyBean.setARM_CIRC(jiaowei.getUserInput());
					mSendBodyBean.setTHIGH_CIRC(datuiwei.getUserInput());
					mSendBodyBean.setTEBIESHUOMING("s");
					mSendBodyBean.setGEXINGMINGCHENG("s");
					editBodyData();
				}
			}
		});
		male_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gender = 1;
				male_btn.setImageResource(R.drawable.male_on);
				female_btn.setImageResource(R.drawable.female_off);
				im_body_gender.setBackground(getResources().getDrawable(R.drawable.man_s));
			}
		});
		female_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gender = 2;
				male_btn.setImageResource(R.drawable.male_off);
				female_btn.setImageResource(R.drawable.female_on);
				im_body_gender.setBackground(getResources().getDrawable(R.drawable.meal));
			}
		});
		size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String[] sizes = getResources().getStringArray(R.array.body_size);
				Log.i("info", sizes[position] + " is clicked!");
				size = sizes[position];
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		shape_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String[] shapes = getResources().getStringArray(R.array.body_shape);
				Log.i("info", shapes[position] + " is clicked!");
				shape = shapes[position];
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		edit_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (edit_tag == 0) {
					edit_body.setText("保存");
					edit_tag = 1;
					jiankuan.setFocusable(true);
					datuiwei.setFocusable(true);
					xiongwei.setFocusable(true);
					xiaotuiwei.setFocusable(true);
					yaowei.setFocusable(true);
					xiwei.setFocusable(true);
					tunwei.setFocusable(true);
					jiaowei.setFocusable(true);
					xiuchang.setFocusable(true);
					kuchang.setFocusable(true);

					jiankuan.setFocusableInTouchMode(true);
					datuiwei.setFocusableInTouchMode(true);
					xiongwei.setFocusableInTouchMode(true);
					xiaotuiwei.setFocusableInTouchMode(true);
					yaowei.setFocusableInTouchMode(true);
					xiwei.setFocusableInTouchMode(true);
					tunwei.setFocusableInTouchMode(true);
					jiaowei.setFocusableInTouchMode(true);
					xiuchang.setFocusableInTouchMode(true);
					kuchang.setFocusableInTouchMode(true);

					jiankuan.requestFocus();
					getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
					jiankuan.setUnderLine(true);
					datuiwei.setUnderLine(true);
					xiongwei.setUnderLine(true);
					xiaotuiwei.setUnderLine(true);
					yaowei.setUnderLine(true);
					xiwei.setUnderLine(true);
					tunwei.setUnderLine(true);
					jiaowei.setUnderLine(true);
					xiuchang.setUnderLine(true);
					kuchang.setUnderLine(true);


				} else if (edit_tag == 1) {
					edit_body.setText("修改");
					edit_tag = 0;
					jiankuan.setFocusable(false);
					datuiwei.setFocusable(false);
					xiongwei.setFocusable(false);
					xiaotuiwei.setFocusable(false);
					yaowei.setFocusable(false);
					xiwei.setFocusable(false);
					tunwei.setFocusable(false);
					jiaowei.setFocusable(false);
					xiuchang.setFocusable(false);
					kuchang.setFocusable(false);

					jiankuan.setUnderLine(false);
					datuiwei.setUnderLine(false);
					xiongwei.setUnderLine(false);
					xiaotuiwei.setUnderLine(false);
					yaowei.setUnderLine(false);
					xiwei.setUnderLine(false);
					tunwei.setUnderLine(false);
					jiaowei.setUnderLine(false);
					xiuchang.setUnderLine(false);
					kuchang.setUnderLine(false);

				}
			}
		});
	}

	/*private void editBodyData(BodyDataBean bean) {
		pd = new ProgressDialog(this);//加载的ProgressDialog
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
		pd.setMessage("保存中....");
		pd.show();
		MyApplication.poolManager.addAsyncTask(//此post请求出错！！！！！！！！！
				new ThreadPoolTaskHttp(this,
						HttpTools.TAG_SET_BODY_DATA,
						Constant.REQUEST_POST,
						ParamsTools.modifyCcinfo2(UrlManager.MODIFY_CCINFO(), bean),
						this,
						"设置身体数据",
						false));
		Log.e("body", "post sent.");//打印提示信息
	}*/

	private void BodyAction(BodyDataBean bodyDataBean) {
		Log.i("INFO", "enter BodyAction!!");
		if (bodyDataBean.getData().getGender() == 1) {
			gender = 1;
			male_btn.setImageResource(R.drawable.male_on);
			female_btn.setImageResource(R.drawable.female_off);
			im_body_gender.setBackground(getResources().getDrawable(R.drawable.man_s));
		} else {
			gender = 2;
			male_btn.setImageResource(R.drawable.male_off);
			female_btn.setImageResource(R.drawable.female_on);
			im_body_gender.setBackground(getResources().getDrawable(R.drawable.meal));
		}
		//
		usr_age.setText("" + bodyDataBean.getData().getAge());
		usr_weight.setText("" + bodyDataBean.getData().getWeight());
		usr_height.setText("" + bodyDataBean.getData().getHeight());
		String[] arr_tmp = getResources().getStringArray(R.array.body_size);//sl:临时数组存储size和shape
		int position = 0;
		for ( int i = 0; i < arr_tmp.length; i++ ) {//sl：通过循环得到尺码在数组中对应的位置
			if (arr_tmp[i].equals(bodyDataBean.getData().getClothSize())) {
				position = i;
				break;
			}
		}
		size_spinner.setSelection(position);//sl:将spinner默认值设置到和网络数据一致
		position = 0;
		arr_tmp = getResources().getStringArray(R.array.body_shape);
		for ( int i = 0; i < arr_tmp.length; i++ ) {
			if (arr_tmp[i].equals(bodyDataBean.getData().getPhysique())) {//sl：通过循环得到尺码在数组中对应的位置
				position = i;
				break;
			}
		}
		shape_spinner.setSelection(position);//sl:将spinner默认值设置到和网络数据一致
		//sl: 加载图消失

		jiankuan.setText(bodyDataBean.getData().getShoulderBreadth() + "");
		datuiwei.setText(bodyDataBean.getData().getThighCirc() + "");
		xiongwei.setText(bodyDataBean.getData().getBust() + "");
		xiaotuiwei.setText(bodyDataBean.getData().getCalfCirc() + "");
		yaowei.setText(bodyDataBean.getData().getWaist() + "");
		xiwei.setText(bodyDataBean.getData().getKneeCirc() + "");
		tunwei.setText(bodyDataBean.getData().getHip() + "");
		jiaowei.setText(bodyDataBean.getData().getArmCirc() + "");
		xiuchang.setText(bodyDataBean.getData().getSleeve() + "");
		kuchang.setText(bodyDataBean.getData().getPants() + "");
		int chuanyixiguan = bodyDataBean.getData().getCHUANYIXIGUAN();
		if (chuanyixiguan == 0) {
			btn_jinshen.setBackgroundResource(R.drawable.button_background_xiguan);
		} else if(chuanyixiguan == 1){
			btn_heti.setBackgroundResource(R.drawable.button_background_xiguan);
		} else if(chuanyixiguan == 2){
			btn_kuansong.setBackgroundResource(R.drawable.button_background_xiguan);
		}
		pd.dismiss();
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		if (resultTag.equals(HttpTools.TAG_SET_BODY_DATA)) {
			Log.e("body", "set body data callback failed!");
			Log.e("body", arg0.getMessage() + "");//post请求出错，打印错误信息
			pd.dismiss();
			Toast.makeText(this, "保存失败，请检查网络", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void startCallBack(String resultTag, boolean isShowDialog, String showTitle) {
		super.startCallBack(resultTag, isShowDialog, showTitle);
	}

	@Override
	public void cancelCallBack(String resultTag) {
		super.cancelCallBack(resultTag);
	}

	@Override
	public void reLoginCallBack(String resultTag, boolean isShowDialog) {
		super.reLoginCallBack(resultTag, isShowDialog);
	}

	// sl: 请求成功的回调函数
	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		if (resultTag.equals(TAG_GETBODYINFO)) {
			Log.e("body", "body data call back succeed: " + callBackMsg);
			Gson gson = new Gson();
			BodyDataBean bodyDataBean = gson.fromJson(callBackMsg, BodyDataBean.class);
			publicBodyDataBean = bodyDataBean;
			Log.i("INFO", "user phone num: " + bodyDataBean.getData().getUpdateUsername());//sl: 测试输出
			BodyAction(bodyDataBean);
			if (bodyDataBean.getData().getGender() == 0) {

			}
		}
		if (resultTag.equals(HttpTools.TAG_SET_BODY_DATA)) {
			Log.e("BODY", "modify body callback success!");
			pd.dismiss();
			Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
		} else if (resultTag.equals(TAG_SET_OLD__BODY_DATA)) {
			pd.dismiss();
			Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
		} else if (resultTag.equals(TAG_GETBODY_BY_ID)) {
			Log.e(TAG_GETBODY_BY_ID, "successCallBack: "+callBackMsg );
			mBodyBeanNew = GsonUtils.fromData(callBackMsg, BodyBeanNew.class);
			BodyAction(mBodyBeanNew);
		}
	}
	private void BodyAction(BodyBeanNew bodyDataBean) {
		Log.i("INFO", "enter BodyAction!!");
		if (bodyDataBean.getData().getUserCCInfoList().getGENDER() == 1) {
			gender = 1;
			male_btn.setImageResource(R.drawable.male_on);
			female_btn.setImageResource(R.drawable.female_off);
			im_body_gender.setBackground(getResources().getDrawable(R.drawable.man_s));
		} else {
			gender = 2;
			male_btn.setImageResource(R.drawable.male_off);
			female_btn.setImageResource(R.drawable.female_on);
			im_body_gender.setBackground(getResources().getDrawable(R.drawable.meal));
		}
		//
		usr_name.setText(bodyDataBean.getData().getUserCCInfoList().getName());
		usr_age.setText("" + bodyDataBean.getData().getUserCCInfoList().getAGE());
		usr_weight.setText("" + bodyDataBean.getData().getUserCCInfoList().getWEIGHT());
		usr_height.setText("" + bodyDataBean.getData().getUserCCInfoList().getHEIGHT());
		String[] arr_tmp = getResources().getStringArray(R.array.body_size);//sl:临时数组存储size和shape
		int position = 0;
		for ( int i = 0; i < arr_tmp.length; i++ ) {//sl：通过循环得到尺码在数组中对应的位置
			if (arr_tmp[i].equals(bodyDataBean.getData().getUserCCInfoList().getCLOTH_SIZE())) {
				position = i;
				break;
			}
		}
		size_spinner.setSelection(position);//sl:将spinner默认值设置到和网络数据一致
		position = 0;
		arr_tmp = getResources().getStringArray(R.array.body_shape);
		for ( int i = 0; i < arr_tmp.length; i++ ) {
			if (arr_tmp[i].equals(bodyDataBean.getData().getUserCCInfoList().getPHYSIQUE())) {//sl：通过循环得到尺码在数组中对应的位置
				position = i;
				break;
			}
		}
		shape_spinner.setSelection(position);//sl:将spinner默认值设置到和网络数据一致
		//sl: 加载图消失

		jiankuan.setText(bodyDataBean.getData().getUserCCInfoList().getSHOULDER_BREADTH() + "");
		datuiwei.setText(bodyDataBean.getData().getUserCCInfoList().getTHIGH_CIRC() + "");
		xiongwei.setText(bodyDataBean.getData().getUserCCInfoList().getBUST() + "");
		xiaotuiwei.setText(bodyDataBean.getData().getUserCCInfoList().getCALF_CIRC() + "");
		yaowei.setText(bodyDataBean.getData().getUserCCInfoList().getWAIST() + "");
		xiwei.setText(bodyDataBean.getData().getUserCCInfoList().getKNEE_CIRC() + "");
		tunwei.setText(bodyDataBean.getData().getUserCCInfoList().getHIP()+ "");
		jiaowei.setText(bodyDataBean.getData().getUserCCInfoList().getARM_CIRC() + "");
		xiuchang.setText(bodyDataBean.getData().getUserCCInfoList().getSLEEVE() + "");
		kuchang.setText(bodyDataBean.getData().getUserCCInfoList().getPANTS() + "");
		int chuanyixiguan = bodyDataBean.getData().getUserCCInfoList().getCHUANYIXUG();
		if (chuanyixiguan == 0) {
			btn_jinshen.setBackgroundResource(R.drawable.button_background_xiguan);
		} else if(chuanyixiguan == 1){
			btn_heti.setBackgroundResource(R.drawable.button_background_xiguan);
		} else if(chuanyixiguan == 2){
			btn_kuansong.setBackgroundResource(R.drawable.button_background_xiguan);
		}
		pd.dismiss();
	}
	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
	}

	class BodyBackClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.body_back)
				finish();
			else
				Log.e("TAG", "nothing happened");
		}
	}

	private void editBodyData() {
		//新版身体数据
		Gson gson = new Gson();
		String toJson = gson.toJson(mSendBodyBean);
		Log.e("json", "editBodyData: " + toJson);
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						HttpTools.TAG_SET_BODY_DATA,
						Constant.REQUEST_POST,
						ParamsTools.setOrUpdataBody(toJson),
						this,
						"设置身体数据",
						false));
		Log.e("body", "post sent.");//打印提示信息
	}

	private void editBodyData(BodyDataBean bean) {
		//旧版身体数据
		MyApplication.poolManager.addAsyncTask(//此post请求出错！！！！！！！！！
				new ThreadPoolTaskHttp(this,
						TAG_SET_OLD__BODY_DATA,
						Constant.REQUEST_POST,
						ParamsTools.modifyCcinfo2(UrlManager.MODIFY_CCINFO(), bean),
						this,
						"设置身体数据",
						false));
		Log.e("body", "post sent.");//打印提示信息
	}
}