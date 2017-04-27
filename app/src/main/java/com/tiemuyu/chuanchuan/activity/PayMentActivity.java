package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.bean.Address;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.BodyDataBean;
import com.tiemuyu.chuanchuan.activity.bean.BodysBean;
import com.tiemuyu.chuanchuan.activity.bean.OrdInfo;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class PayMentActivity extends BaseActivityG {

	private final String TAG_GETUSERMESSAGE = "TAG_GETUSERMESSAGE";
	private User user;
	private Intent mIntent;
	private int productid;
	private String image;
	private String detial;
	private String price;
	private TextView product_price, product_name, tv_total_price, num, tv_message,
			tv_current_balance, tv_balance, tv_big_balance, tv_check_agree, tv_name,
			tv_phone, tv_location;
	private ImageView product_image, add, reduce, usechuan, agree;
	private Button btn_ok;
	private int number = 1;
	private boolean isUseCHua, isAgree;
	private OrdInfo ordInfo;
	private RelativeLayout rl_adress;
	static final private int GET_CODE = 0;
	private Address address;
	private int addressId = 0;
	private Address.DataBean dataBean;
	private int max, chuan;
	private TextView tv_user_name;
	private TextView tv_gender;
	private TextView tv_age;
	private TextView tv_weiht;
	private TextView tv_height;
	private TextView product_price_total;
	private RelativeLayout rl_user_body;
	public static final String TAG_GETBODYINFO = "TAG_GETBODYINFO";
	private static final int REQUEST = 123;
	private static final int RESULT = 123;
	private BodysBean.DataBean.UserCCInfoListBean userCCInfoListBean;
	private int mType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_ment);
		user = DBTools.getUser();
		ordInfo = new OrdInfo();
		ordInfo.setCoin(0 + "");
		mIntent = getIntent();
		productid = mIntent.getIntExtra("productid", 0);
		mType = mIntent.getIntExtra("type", 0);
		Log.e("productid", "onCreate:--------------- " + productid);
		image = mIntent.getStringExtra("image");
		detial = mIntent.getStringExtra("detial");
		price = mIntent.getStringExtra("price");
		initView();
		getUserBody();
		getAddress(TAG_GETUSERMESSAGE);
		isAgree = isUseCHua = false;
		if (mType == 1) {
			product_price.setText("￥ " + (int) Math.ceil(Double.parseDouble(price)*0.9));
			tv_total_price.setText("￥ " + (int) Math.ceil(Double.parseDouble(price)*0.9));
			product_price_total.setVisibility(View.VISIBLE);
			product_price_total.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
			product_price_total.setText("￥ " + price);
		} else {
			product_price.setText("￥ " + price);
			tv_total_price.setText("￥ " + Double.parseDouble(price));
		}
		num.setText(number + "");
		product_name.setText(detial);
		Picasso.with(this).load(image).placeholder(R.drawable.circle_logo).into(product_image);

		tv_current_balance.setText(user.getCcCoin() - user.getFrzCcCoin() + "");
		max = (int) (Double.parseDouble(price) * 0.2);
		chuan = user.getCcCoin() - user.getFrzCcCoin();
		if (chuan > max) {
			chuan = max;
		}
		tv_balance.setText(chuan + "");
		tv_big_balance.setText(max + "");
		tv_check_agree.setText(Html.fromHtml("同意<font color=\"#450525\"><b>《定制协议》</b></font>"));
		add.setOnClickListener(this);
		reduce.setOnClickListener(this);
		agree.setOnClickListener(this);
		usechuan.setOnClickListener(this);
		tv_check_agree.setOnClickListener(this);
		btn_ok.setOnClickListener(this);
		rl_adress.setOnClickListener(this);
		findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		rl_user_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PayMentActivity.this,MyBodyManngerActivity.class);
				intent.putExtra("type",1);
				startActivityForResult(intent,REQUEST);
			}
		});
	}

	private void getUserBody() {
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
	}

	private void initView() {
		product_price = (TextView) findViewById(R.id.product_price);
		product_price_total = (TextView) findViewById(R.id.product_price_total);
		product_name = (TextView) findViewById(R.id.product_name);
		product_image = (ImageView) findViewById(R.id.product_image);
		add = (ImageView) findViewById(R.id.add);
		reduce = (ImageView) findViewById(R.id.reduce);
		tv_total_price = (TextView) findViewById(R.id.tv_total_price);
		num = (TextView) findViewById(R.id.num);
		tv_current_balance = (TextView) findViewById(R.id.tv_current_balance);
		tv_balance = (TextView) findViewById(R.id.tv_balance);
		tv_big_balance = (TextView) findViewById(R.id.tv_big_chuanbi);
		usechuan = (ImageView) findViewById(R.id.usechuan);
		agree = (ImageView) findViewById(R.id.agree);
		tv_check_agree = (TextView) findViewById(R.id.tv_check_agree);
		btn_ok = (Button) findViewById(R.id.ok);
		tv_message = (TextView) findViewById(R.id.tv_message);
		rl_adress = (RelativeLayout) findViewById(R.id.rl_adress);
		tv_name = (TextView) findViewById(R.id.name);
		tv_phone = (TextView) findViewById(R.id.phone);
		tv_location = (TextView) findViewById(R.id.addressnext);
		tv_user_name = (TextView) findViewById(R.id.tv_name);
		tv_gender = (TextView) findViewById(R.id.tv_gender);
		tv_age = (TextView) findViewById(R.id.tv_age);
		tv_weiht = (TextView) findViewById(R.id.tv_weiht);
		tv_height = (TextView) findViewById(R.id.tv_height);
		rl_user_body = (RelativeLayout) findViewById(R.id.rl_user_body);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case (R.id.add):
				number++;
				if (mType ==1) {
					tv_total_price.setText("￥ " + Math.ceil(Double.parseDouble(price) * number*0.9));
				} else {
					tv_total_price.setText("￥ " + Math.ceil(Double.parseDouble(price) * number));
				}
				num.setText(number + "");
				max = ((int) (Double.parseDouble(price) * 0.2))*number;
				tv_big_balance.setText(max + "");
				if (max > user.getCcCoin() - user.getFrzCcCoin()) {
					chuan = user.getCcCoin() - user.getFrzCcCoin();
					tv_balance.setText(chuan+"");
				} else {
					tv_balance.setText(max+"");
					chuan = max;
				}
				break;
			case (R.id.reduce):
				if (number > 1) {
					number--;
					if (mType ==1) {
						tv_total_price.setText("￥ " + Math.ceil(Double.parseDouble(price) * number*0.9));
					} else {
						tv_total_price.setText("￥ " + Math.ceil(Double.parseDouble(price) * number));
					}
					num.setText(number + "");
					max = ((int) (Double.parseDouble(price) * 0.2))*number;
					tv_big_balance.setText(max + "");
					if (max > user.getCcCoin() - user.getFrzCcCoin()) {
						chuan = user.getCcCoin() - user.getFrzCcCoin();
						tv_balance.setText(chuan+"");
					} else {
						tv_balance.setText(max+"");
						chuan = max;
					}
				}
				break;
			case (R.id.usechuan):
				if (isUseCHua) {
					isUseCHua = false;
					usechuan.setBackground(getResources().getDrawable(R.drawable.noselect));
					if (mType ==1) {
						tv_total_price.setText("￥ " + Math.ceil(Double.parseDouble(price) * number*0.9));
					} else {
						tv_total_price.setText("￥ " + Math.ceil(Double.parseDouble(price) * number));
					}
					ordInfo.setCoin(0 + "");
				} else {
					isUseCHua = true;
					usechuan.setBackground(getResources().getDrawable(R.drawable.select));
					chuan = user.getCcCoin() - user.getFrzCcCoin();
					if (chuan > max) {
						chuan = max;
					}
					if (mType ==1) {
						tv_total_price.setText("￥  " + (Math.ceil(Double.parseDouble(price) * number*0.9)- chuan));
					} else {
						tv_total_price.setText("￥  " + (Math.ceil(Double.parseDouble(price) * number)- chuan));
					}
					ordInfo.setCoin(chuan + "");
				}
				break;
			case (R.id.agree):
				if (isAgree) {
					isAgree = false;
					agree.setBackground(getResources().getDrawable(R.drawable.noselect));
				} else {
					isAgree = true;
					agree.setBackground(getResources().getDrawable(R.drawable.select));
				}
				break;
			case (R.id.tv_check_agree):
				Intent intent1 = new Intent(PayMentActivity.this, ProtocolActivity.class);
				intent1.putExtra("url", "http://ios.myappcc.com/h5/dingzhi.html");
				intent1.putExtra("title", "定制协议");
				startActivity(intent1);
				break;
			case (R.id.ok):
				if (!isAgree) {
					Toast.makeText(this, "请阅读《定制协议》并同意", Toast.LENGTH_SHORT).show();
				} else {
					ordInfo.setTotalNum(number + "");
					ordInfo.setTotalFee((Double.parseDouble(price) * number) + "");
					if (mType ==1) {
						if (isUseCHua) {
							ordInfo.setActualFee((Math.ceil(Double.parseDouble(price) * number*0.9) - chuan + ""));
						} else {
							ordInfo.setActualFee((Math.ceil(Double.parseDouble(price) * number*0.9) + ""));
						}
					} else {
						if (isUseCHua) {
							ordInfo.setActualFee((Double.parseDouble(price) * number - chuan + ""));
						} else {
							ordInfo.setActualFee((Double.parseDouble(price) * number + ""));
						}
					}
					String msg = String.valueOf(tv_message.getText());
					if (msg.equals("")) {
						msg = "0";
					}
					ordInfo.setDiscountedPrice(msg);
					ordInfo.setRegApp("00");
					Intent intent = new Intent(PayMentActivity.this, PaySelect.class);
					intent.putExtra("ordInfo", ordInfo);
					intent.putExtra("productid", productid);
					intent.putExtra("AddressId", addressId);
					intent.putExtra("body",userCCInfoListBean);
					intent.putExtra("type",mType);
					if (addressId == 0) {
						Toast.makeText(this, "请添加收获地址", Toast.LENGTH_SHORT).show();
						return;
					} else if(userCCInfoListBean==null){

					}else if (userCCInfoListBean.getGENDER() == 0) {
						Toast.makeText(this, "请添加定制人", Toast.LENGTH_SHORT).show();
						return;
					}
					startActivity(intent);
					finish();
				}
				break;
			case (R.id.rl_adress):
				Intent intent = new Intent(PayMentActivity.this, AddressMangerActivity.class);
				intent.putExtra("requestCode", 1);
				startActivityForResult(intent, GET_CODE);
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("onActivityResult: ", resultCode + " " + requestCode);
		if (requestCode == GET_CODE && resultCode == 12) {
			dataBean = (Address.DataBean) data.getSerializableExtra("address");
			addressId = dataBean.getId();
			tv_name.setText(dataBean.getContact());
			tv_phone.setText(dataBean.getMobile());
			tv_location.setText(dataBean.getProvince() + dataBean.getCity() + dataBean.getDistrict() + dataBean.getAddress());
		} else if (requestCode == REQUEST && resultCode ==  RESULT){
			userCCInfoListBean = (BodysBean.DataBean.UserCCInfoListBean) data.getSerializableExtra("body");
			tv_user_name.setText(userCCInfoListBean.getName());
			if (userCCInfoListBean.getGENDER() == 1) {
				tv_gender.setText("男");
			} else {
				tv_gender.setText("女");
			}
			tv_age.setText(userCCInfoListBean.getAGE() + "岁");
			tv_weiht.setText(userCCInfoListBean.getWEIGHT() + "kg");
			tv_height.setText(userCCInfoListBean.getHEIGHT() + "cm");
		}
	}

	private void getAddress(String tag) {
		Log.e("getAddress: ", tag);
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(PayMentActivity.this,
						tag,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.GET_ADDRESS()),
						PayMentActivity.this,
						"获取收货地址",
						false));
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		Log.e("successCallBack: 1", callBackMsg);
		if (resultTag.equals(TAG_GETUSERMESSAGE)) {
			address = GsonUtils.fromData(callBackMsg, Address.class);
			Address.DataBean dataBean = address.getData().get(0);
			Log.e("successCallBack: 2", dataBean.getAddress());
			Log.e("successCallBack: 2", dataBean.getContact());
			Log.e("successCallBack: 2", dataBean.getAddress());
			Log.e("successCallBack: 2", dataBean.getProvince());

			tv_name.setText(dataBean.getContact());
			tv_phone.setText(dataBean.getMobile() + "");
			tv_location.setText(dataBean.getProvince() + dataBean.getCity() + dataBean.getDistrict() +
					dataBean.getAddress());
			addressId = dataBean.getId();
		}
		if (resultTag.equals(TAG_GETBODYINFO)) {
			Gson gson = new Gson();
			BodyDataBean bodyDataBean = gson.fromJson(callBackMsg, BodyDataBean.class);
			tv_user_name.setText(DBTools.getUser().getNickName());
			if (bodyDataBean.getData().getGender() == 1) {
				tv_gender.setText("男");
			} else {
				tv_gender.setText("女");
			}
			tv_age.setText(bodyDataBean.getData().getAge() + "岁");
			tv_weiht.setText(bodyDataBean.getData().getWeight()+ "kg");
			tv_height.setText(bodyDataBean.getData().getHeight() + "cm");
			userCCInfoListBean = new BodysBean.DataBean.UserCCInfoListBean();
			userCCInfoListBean.setSHOULDER_BREADTH(bodyDataBean.getData().getShoulderBreadth());
			userCCInfoListBean.setLOGO("s");
			userCCInfoListBean.setCHUANYIXUG(bodyDataBean.getData().getCHUANYIXIGUAN());
			userCCInfoListBean.setBUST(bodyDataBean.getData().getBust());
			userCCInfoListBean.setKNEE_CIRC(bodyDataBean.getData().getKneeCirc());
			userCCInfoListBean.setWAIST(bodyDataBean.getData().getWaist());
			userCCInfoListBean.setGENDER(bodyDataBean.getData().getGender());
			userCCInfoListBean.setDRESS_EFFECT(bodyDataBean.getData().getDressEffect());
			userCCInfoListBean.setSLEEVE(bodyDataBean.getData().getSleeve());
			userCCInfoListBean.setName(DBTools.getUser().getNickName());
			userCCInfoListBean.setHEIGHT(bodyDataBean.getData().getHeight());
			userCCInfoListBean.setWAIST_POSTION(0);
			userCCInfoListBean.setCALF_CIRC(bodyDataBean.getData().getCalfCirc());
			userCCInfoListBean.setBMI(bodyDataBean.getData().getBmi());
			userCCInfoListBean.setAGE(bodyDataBean.getData().getAge());
			userCCInfoListBean.setISRADIO(bodyDataBean.getData().getISRADIO());
			userCCInfoListBean.setBODY_PIC(bodyDataBean.getData().getBodyPic());
			userCCInfoListBean.setUSER_ID((int) DBTools.getUser().getUserId());
			userCCInfoListBean.setCLOTH_SIZE(bodyDataBean.getData().getClothSize());
			userCCInfoListBean.setPHYSIQUE(bodyDataBean.getData().getPhysique());
			userCCInfoListBean.setPANTS(bodyDataBean.getData().getPants());
			userCCInfoListBean.setWEIGHT(bodyDataBean.getData().getWeight());
			userCCInfoListBean.setSKIN_COLOR(bodyDataBean.getData().getSkinColor());
			userCCInfoListBean.setBODY_PIC(bodyDataBean.getData().getBodyPic());
			userCCInfoListBean.setARM_CIRC(bodyDataBean.getData().getArmCirc());
			userCCInfoListBean.setTHIGH_CIRC(bodyDataBean.getData().getThighCirc());
			userCCInfoListBean.setTEBIESHUOMING(bodyDataBean.getData().getTEBIESHUOMING());
			userCCInfoListBean.setGEXINGMINGCHENG(bodyDataBean.getData().getGEXINGMINGCHENG());
		}
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		Log.e("failCallBack: 1", arg0.getLocalizedMessage());
		if (resultTag.equals(TAG_GETBODYINFO)) {
			tv_user_name.setText(DBTools.getUser().getNickName());
			tv_gender.setText("男");
			tv_age.setText(0 + "岁");
			tv_weiht.setText(0 + "kg");
			tv_height.setText(0 + "cm");
			userCCInfoListBean = new BodysBean.DataBean.UserCCInfoListBean();
			userCCInfoListBean.setName(DBTools.getUser().getNickName());
			userCCInfoListBean.setHEIGHT(0);
			userCCInfoListBean.setWEIGHT(0);
			userCCInfoListBean.setGENDER(0);
		}
	}

}
