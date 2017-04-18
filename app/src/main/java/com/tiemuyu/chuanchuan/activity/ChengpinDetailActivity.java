package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.adapter.ClotherViewPager;
import com.tiemuyu.chuanchuan.activity.adapter.SimilarProductsAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.ViewPagerAdapter;
import com.tiemuyu.chuanchuan.activity.bean.Base;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.CostBean;
import com.tiemuyu.chuanchuan.activity.bean.DingZhiItem;
import com.tiemuyu.chuanchuan.activity.bean.SimilarProducts;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.fragment.TextMessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;
import com.tiemuyu.chuanchuan.activity.util.GlideImageLoader;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.view.CCListView;
import com.tiemuyu.chuanchuan.activity.view.WrapContentViewPager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChengpinDetailActivity extends BaseActivityG implements NetResponses {
	public String tag = "MESSAGE";
	private Intent mIntent;
	private int productid;
	private final String TAG_GET_buycustomize = "TAG_GET_buycustomize";
	private final String TAG_POST_Addfav = "TAG_POST_Addfav";
	private final String TAG_POST_Delfav = "TAG_POST_Delfav";
	/*private ClothesDetail clothesDetail;*/
	private ImageView im_user_header, im_back, im_must_know, im_main_image, im_share;
	private TextView tv_user_name, tv_price, tv_product_name,
			tv_kuanshi, tv_dingzhi;
	//private WebView webView;
	private Banner im_banner;
	private List<String> images, xijie;
	private boolean xuzhi_isExpend = false;
	private RelativeLayout rl_dingzhi;
	private LinearLayout ll_banner, ll_ke_fu, ll_shou_cang, ll_zhi_fu, ll_image;
	RelativeLayout findLl, find_aa;
	private int one;//单个水平动画位移
	private int width;
	private ViewPager vp_find_home;
	private ViewPagerAdapter pageAdapter;
	private int dx;// 动画图片偏移量
	private int x = 0;
	private int currIndex = 0;// 当前页卡编号
	private boolean isFav = false;
	private ImageView im_collect;
	private String title, url, img_url;
	private DingZhiItem dIngzhi;
	private TextView mianliao;
	private ArrayList<Contacts> dataList = new ArrayList<>();
	public static String OWN_HEADER_URL = "";//gao 在这里更改有效
	private DataSharedPress sharedPress;
	private boolean isLogIn = false;
	private ImageView imageView;
	private int mInt;

	private int mOne;
	private int mDx;// 动画图片偏移量
	private int mX = 0;
	private int mCurrIndex = 0;// 当前页卡编号
	private RelativeLayout mFindLl;
	private WrapContentViewPager vp_detail;
	private TextView tv_one, tv_two, tv_three, tv_shu_ju, tv_fu_wu, tv_shi_jian;
	private ScrollView sv_view;
	private ClotherViewPager mClotherViewPager;
	private WebView mWebView;
	private CCListView mCClistView;
	private String TAG_GET_GETSIMILAR = "TAG_GET_GETSIMILAR";
	private String TAG_GET_MeSSAGE = "TAG_GET_MeSSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();
		setContentView(R.layout.activity_chegnpin_layout);
		mIntent = getIntent();
		initView();
		//width = ll_image.getLayoutParams().width;
		Log.e("width", "onCreate: " + width);

		Log.e("URL", "onCreate: " + UrlManager.Get_DIngZhi() + productid);
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_GET_buycustomize,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.GetChengPin() + productid),
						this,
						"获取产品信息",
						false));
		im_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		setNetResponses(this);
		findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private NetResponses netResponses;

	public void setNetResponses(NetResponses netResponses) {
		this.netResponses = netResponses;
	}

	private void initView() {
		vp_detail = (WrapContentViewPager) findViewById(R.id.vp_detail);
		tv_one = (TextView) findViewById(R.id.tv_one);
		tv_two = (TextView) findViewById(R.id.tv_two);
		tv_three = (TextView) findViewById(R.id.tv_three);
		sv_view = (ScrollView) findViewById(R.id.sv_view);
		productid = mIntent.getIntExtra("productid", 0);
		vp_find_home = (ViewPager) findViewById(R.id.vp_find_home);
		findLl = (RelativeLayout) findViewById(R.id.find_ll);
		find_aa = (RelativeLayout) findViewById(R.id.find_aa);
		im_user_header = (ImageView) findViewById(R.id.im_user_image_detail);
		tv_user_name = (TextView) findViewById(R.id.tv_name_detail);
		tv_price = (TextView) findViewById(R.id.tv_jia_ge_detail);
		tv_product_name = (TextView) findViewById(R.id.tv_product_name);
		im_banner = (Banner) findViewById(R.id.im_banner);
		im_back = (ImageView) findViewById(R.id.im_back);
		//webView = (WebView) findViewById(R.id.web_view_price);
		im_main_image = (ImageView) findViewById(R.id.im_main_image);
		im_must_know = (ImageView) findViewById(R.id.im_must_know);
		rl_dingzhi = (RelativeLayout) findViewById(R.id.rl_dingzhi);

		ll_banner = (LinearLayout) findViewById(R.id.ll_banner);
		ll_ke_fu = (LinearLayout) findViewById(R.id.ll_ke_fu);
		ll_shou_cang = (LinearLayout) findViewById(R.id.ll_shou_cang);
		ll_zhi_fu = (LinearLayout) findViewById(R.id.ll_zhi_fu);
		ll_image = (LinearLayout) findViewById(R.id.ll_image);
		tv_kuanshi = (TextView) findViewById(R.id.tv_kuanshi);
		tv_dingzhi = (TextView) findViewById(R.id.tv_dingzhi);
		im_collect = (ImageView) findViewById(R.id.im_collect);
		im_share = (ImageView) findViewById(R.id.im_share);
		ArrayList<View> views = new ArrayList();
		View kuanshi_xiangqing = View.inflate(this, R.layout.kuanshi_xiangqing, null);
		View dingzhi_baozhang = View.inflate(this, R.layout.dingzhi_baozhang, null);
		mianliao = (TextView) kuanshi_xiangqing.findViewById(R.id.tv_mian_liao);
		views.add(kuanshi_xiangqing);
		views.add(dingzhi_baozhang);
		pageAdapter = new ViewPagerAdapter(views);
		vp_find_home.setAdapter(pageAdapter);
		setStripMove();
		setMStripMove();
		vp_find_home.setOnPageChangeListener(listener);
		tv_kuanshi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vp_find_home.setCurrentItem(0);
			}
		});
		tv_dingzhi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vp_find_home.setCurrentItem(1);
			}
		});
		setViewAdapter();
	}

	private void setViewAdapter() {
		ArrayList<View> views = new ArrayList();
		View one = View.inflate(this, R.layout.xiangsi_tongkuan, null);
		View two = View.inflate(this, R.layout.feiyong_layout, null);
		View three = View.inflate(this, R.layout.dingzhi_notice_layout, null);
		addViewpager(views, one, two, three);
	}

	private void addViewpager(ArrayList<View> mViews, View mOne, View mTwo, View mThree) {

		mCClistView = (CCListView) mOne.findViewById(R.id.cc_list_view);
		mWebView = (WebView) mTwo.findViewById(R.id.web_view_price);
		tv_shu_ju = (TextView) mThree.findViewById(R.id.tv_shu_ju);
		tv_fu_wu = (TextView) mThree.findViewById(R.id.tv_fu_wu);
		tv_shi_jian = (TextView) mThree.findViewById(R.id.tv_shi_jian);
		tv_shu_ju.setText(
				Html.fromHtml("可在" +
						"<font color=\"#450525\"><b>“我的”-“身体数据”</b></font>" +
						"中填写并保存\n<br/>可在下单后弹出的体型数据页面中填写")
		);
		tv_fu_wu.setText(
				Html.fromHtml("下单后" +
						"<font color=\"#450525\"><b>6小时内</b></font>" +
						"，可与客服沟通定制要求（面料、款式等）逾期将直接进入面料采购及制版流程<br/><br/>下单后" +
						"<font color=\"#450525\"><b>5日内</b></font>，如遇面料问题造成无法制衣，穿穿将全额退款并赔偿20元穿币<br/><br/>" +
						"收货后七天内，因穿穿所造成的制衣与尺码问题均可申请免费修改甚至重做。（显示器色差、与主观感觉不符等原因不再免费修改之列）")
		);
		tv_shi_jian.setText(
				Html.fromHtml("根据面料采购难度和制作工艺复杂度，自下单之日起" +
						"<font color=\"#450525\"><b>7-20</b></font>" +
						"内发货，以第三方物流记录的发货时间为准<br/><br/>" +
						"\n因用户信息确认等所造成的时间延误会直接在原发货时长基础上延长")
		);
		mViews.add(mOne);
		mViews.add(mTwo);
		mViews.add(mThree);
		mClotherViewPager = new ClotherViewPager(mViews);
		vp_detail.setAdapter(mClotherViewPager);
		//vp_detail.setOffscreenPageLimit(3);
		Log.e("setViewAdapter: ", vp_detail.getChildCount() + "count");
		setStripMove();
		vp_detail.setOnPageChangeListener(mListener);
		tv_one.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vp_detail.setCurrentItem(0);
			}
		});
		tv_two.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vp_detail.setCurrentItem(1);

			}
		});
		tv_three.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				vp_detail.setCurrentItem(2);
			}
		});
	}

	private ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			Log.e("onPageSelected: ", position + "");
			vp_detail.reMeasureCurrentPage(position);
			Animation animation = null;
			if (position - mCurrIndex == 1) {
				mDx = mX + mOne;
			} else if (position - mCurrIndex == 2) {
				mDx = mX + mOne * 2;
			} else if (position - mCurrIndex == 3) {
				mDx = mX + mOne * 3;
			} else if (position - mCurrIndex == -1) {
				mDx = mX - mOne;
			} else if (position - mCurrIndex == -2) {
				mDx = mX - mOne * 2;
			} else if (position - mCurrIndex == -3) {
				mDx = mX - mOne * 3;
			}
			switch (position) {
				case 0:
					animation = new TranslateAnimation(mX, mDx, 0, 0);
					break;
				case 1:
					animation = new TranslateAnimation(mX, mDx, 0, 0);
					break;
				case 2:
					animation = new TranslateAnimation(mX, mDx, 0, 0);
					break;
				case 3:
					animation = new TranslateAnimation(mX, mDx, 0, 0);
					break;
			}
			if (position - mCurrIndex == 1) {
				mX += mOne;
			} else if (position - mCurrIndex == 2) {
				mX = mX + mOne * 2;
			} else if (position - mCurrIndex == 3) {
				mX = mX + mOne * 3;
			} else if (position - mCurrIndex == -1) {
				mX = mX - mOne;
			} else if (position - mCurrIndex == -2) {
				mX = mX - mOne * 2;
			} else if (position - mCurrIndex == -3) {
				mX = mX - mOne * 3;
			}
			mCurrIndex = position;
			animation.setDuration(100);
			animation.setFillAfter(true);//
			find_aa.startAnimation(animation);
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		if (resultTag.equals(TAG_GET_buycustomize)) {
			Log.e("successCallBack", "successCallBack: " + callBackMsg);
			images = new ArrayList<>();
			dIngzhi = (new Gson()).fromJson(callBackMsg, DingZhiItem.class);
			mianliao.setText(dIngzhi.getData().getDingzhiItem().getMianliao());
			xijie = dIngzhi.getData().getDingzhiItem().getImgList();
			xijie.remove(0);
			MyApplication.poolManager.addAsyncTask(
					new ThreadPoolTaskHttp(this,
							TAG_GET_GETSIMILAR,
							Constant.REQUEST_GET,
							new RequestParams(
									UrlManager.getSimilar(dIngzhi.getData().getDingzhiItem().getProid() + "", dIngzhi.getData().getDingzhiItem().getProname())),
							this,
							"获取相似信息",
							false));
			MyApplication.poolManager.addAsyncTask(
					new ThreadPoolTaskHttp(this,
							TAG_GET_MeSSAGE,
							Constant.REQUEST_GET,
							new RequestParams(
									UrlManager.getProCost(dIngzhi.getData().getDingzhiItem().getProid()+"")),
							this,
							"获取费用明细",
							false));
			for ( int i = 0; i < xijie.size(); i++ ) {
				mInt = i;
				ImageView im = new ImageView(this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				/*if (i==0){
					continue;
				}*/
				if (imageView == null) {
					imageView = im;
				}
				Picasso.with(this).load(xijie.get(i)).transform(transformation).into(im);
				im.setTag(i);
				im.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int tag = (int) v.getTag();
						Intent intent = new Intent(ChengpinDetailActivity.this, ImageDetailsActivity.class);
						intent.putStringArrayListExtra("images", (ArrayList<String>) xijie);
						intent.putExtra("position", tag);
						startActivity(intent);
					}
				});
				ll_image.addView(im, lp);
			}

			images.add(dIngzhi.getData().getDingzhiItem().getPromianpic());
			//Log.e("clothesDetail", "successCallBack: " + clothesDetail.getData().getUser().getUserImg());
			Picasso.with(this).load(dIngzhi.getData().getDingzhiItem().getUserimg())
					.placeholder(R.drawable.circle_logo).into(im_user_header);
			tv_user_name.setText(dIngzhi.getData().getDingzhiItem().getUsername());
			tv_product_name.setText(dIngzhi.getData().getDingzhiItem().getProname());
			tv_price.setText("￥ " + dIngzhi.getData().getDingzhiItem().getPrice());
			Log.e("imageUrl: ", dIngzhi.getData().getDingzhiItem().getFirstXiJieImg());
			Picasso.with(this).load(dIngzhi.getData().getDingzhiItem().getFirstXiJieImg())
					.transform(transformation).into(im_main_image);
			im_main_image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ArrayList<String> images = new ArrayList<>();
					images.add(dIngzhi.getData().getDingzhiItem().getFirstXiJieImg());
					Intent intent = new Intent(ChengpinDetailActivity.this, ImageDetailsActivity.class);
					intent.putStringArrayListExtra("images", images);
					intent.putExtra("position", 0);
					startActivity(intent);
				}
			});
		    /*webView.loadDataWithBaseURL(null, "<table>" + clothesDetail.getData().getCostHtml() +
                            "</table>",
                    "text/html", "UTF-8", null);*/
			im_banner.setImageLoader(new GlideImageLoader())
					.setImages(images).start();

			ll_banner.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(ChengpinDetailActivity.this, ImageDetailsActivity.class);
					intent.putStringArrayListExtra("images", (ArrayList<String>) images);
					intent.putExtra("position", 0);
					startActivity(intent);
				}
			});

			ll_ke_fu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!PreferenceUtils.getPrefBoolean(ChengpinDetailActivity.this, Constant.CC_IFLOGIN, false)) {
						Toast.makeText(ChengpinDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
						return;
					}
					if (isLogIn) {
						kefu();
					} else {
						login();
					}
				}
			});
			im_share.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					title = dIngzhi.getData().getDingzhiItem().getProname();
					img_url = dIngzhi.getData().getDingzhiItem().getPromianpic();
					url = "http://ios.myappcc.com/cc/Find/DingZhiXiangQingAction?id="
							+ dIngzhi.getData().getDingzhiItem().getId() + "&type=3";
					Log.e("url", "onClick: " + url);
					share();
				}
			});
			isFav = dIngzhi.getData().getDingzhiItem().isIsFav();
			if (isFav) {
				im_collect.setBackground(getResources().getDrawable(R.drawable.heart));
			} else {
				im_collect.setBackground(getResources().getDrawable(R.drawable.tab_collect));
			}
			ll_shou_cang.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.e("onClick: ", isFav + "");
					if (!PreferenceUtils.getPrefBoolean(ChengpinDetailActivity.this, Constant.CC_IFLOGIN, false)) {
						Toast.makeText(ChengpinDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
						return;
					}
					if (!isFav) {
						addToFav();
					} else {
						delToFav();
					}

				}
			});
			ll_zhi_fu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						if (!PreferenceUtils.getPrefBoolean(ChengpinDetailActivity.this, Constant.CC_IFLOGIN, false)) {
							Toast.makeText(ChengpinDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
							return;
						}
						Intent intent = new Intent(ChengpinDetailActivity.this, PayMentActivity.class);
						intent.putExtra("productid", dIngzhi.getData().getDingzhiItem().getProid());
						intent.putExtra("image", dIngzhi.getData().getDingzhiItem().getPromianpic());
						intent.putExtra("detial", dIngzhi.getData().getDingzhiItem().getProname());
						intent.putExtra("price", dIngzhi.getData().getDingzhiItem().getPrice() + "");
						startActivity(intent);
					} catch (Exception e) {
						Log.e("Exception", "onClick: " + e.getLocalizedMessage());
					}
				}
			});
		} else if (resultTag.equals(TAG_POST_Addfav)) {
			Log.e("TAG_POST_Addfav", "successCallBack: " + callBackMsg);
			Base base = GsonUtils.fromData(callBackMsg, Base.class);
			if (base.getCode() == 1) {
				Toast.makeText(this, base.getMsg(), Toast.LENGTH_SHORT).show();
				im_collect.setBackground(getResources().getDrawable(R.drawable.heart));
				isFav = true;
			}
		} else if (resultTag.equals(TAG_POST_Delfav)) {
			Base base = GsonUtils.fromData(callBackMsg, Base.class);
			if (base.getCode() == 1) {
				Toast.makeText(this, base.getMsg(), Toast.LENGTH_SHORT).show();
				im_collect.setBackground(getResources().getDrawable(R.drawable.tab_collect));
				isFav = false;
			}
		} else if (resultTag.equals(TAG_GET_GETSIMILAR)) {
			Log.e("TAG_GET_GETSIMILAR: ", callBackMsg);
			SimilarProducts similarProducts = GsonUtils.fromData(callBackMsg, SimilarProducts.class);
			SimilarProductsAdapter waterAdapter = new SimilarProductsAdapter(similarProducts, ChengpinDetailActivity.this);
			mCClistView.setAdapter(waterAdapter);
		} else if (resultTag.equals(TAG_GET_MeSSAGE)) {
			CostBean costBean = GsonUtils.fromData(callBackMsg, CostBean.class);
			mWebView.loadDataWithBaseURL(null, "<table>" + costBean.getData().getCostHtml() +
							"</table>",
					"text/html", "UTF-8", null);
		}
	}

	private void kefu() {
		Intent intent1 = new Intent(ChengpinDetailActivity.this, TextMessageActivity.class);
		intent1.putExtra("sessionId", SPUtils.getKefuCode());
		intent1.putExtra("title", "定制助理（9:00-24:00）");
		startActivity(intent1);
		DataSharedPress sharedPress = DataSharedPress.getSharedPress(ChengpinDetailActivity.this);
		sharedPress.putInt(SPUtils.getKefuCode() + "unread", 0);
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		Log.e(resultTag, "failCallBack: " + arg0.getLocalizedMessage());
	}

	private void setStripMove() {

		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) findLl.getLayoutParams();
		linearParams.width = width / 2;
		Display currDisplay = this.getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
		int displayWidth = currDisplay.getWidth();
		one = displayWidth / 2; //设置水平动画平移大小
	}

	private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			Animation animation = null;
			if (position > currIndex) {
				dx = x + one;
			} else {
				dx = x - one;
			}
			switch (position) {
				case 0:
					animation = new TranslateAnimation(x, dx, 0, 0);
					break;
				case 1:
					animation = new TranslateAnimation(x, dx, 0, 0);
					break;
			}
			if (position > currIndex) {
				x += one;
			} else {
				x -= one;
			}
			currIndex = position;
			animation.setDuration(100);
			animation.setFillAfter(true);//
			findLl.startAnimation(animation);
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	private void addToFav() {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(ChengpinDetailActivity.this,
						TAG_POST_Addfav,
						Constant.REQUEST_POST,
						ParamsTools.AddFavorites(
								UrlManager.Addfav() +
										dIngzhi.getData().getDingzhiItem().getProid()),
						ChengpinDetailActivity.this, "添加收藏",
						false));
	}

	private void delToFav() {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(ChengpinDetailActivity.this,
						TAG_POST_Delfav,
						Constant.REQUEST_POST,
						ParamsTools.delFavorites(
								UrlManager.Delfav(), dIngzhi.getData().getDingzhiItem().getProid() + ""),
						ChengpinDetailActivity.this, "取消收藏",
						false));
	}

	public void share() {
		new ShareAction(this).setDisplayList(
				SHARE_MEDIA.SINA,
				SHARE_MEDIA.WEIXIN,
				SHARE_MEDIA.WEIXIN_CIRCLE,
				SHARE_MEDIA.WEIXIN_FAVORITE,
				SHARE_MEDIA.SMS,
				SHARE_MEDIA.MORE)
				.withTitle(title)
				.withText("穿穿，一座由你做主的时装定制工厂")
				.withTargetUrl(url)
				.withMedia(new UMImage(getApplicationContext(), img_url))
				.setCallback(umShareListener)
				.open();
		System.out.println("ShareAction opened in mainactivity..  saveload..........");
	}

	private UMShareListener umShareListener = new UMShareListener() {

		@Override
		public void onResult(SHARE_MEDIA platform) {
			com.umeng.socialize.utils.Log.d("plat", "platform" + platform);
			if (platform.name().equals("WEIXIN_FAVORITE")) {
				Toast.makeText(getApplicationContext(), "收藏成功啦", Toast.LENGTH_SHORT).show();
			} else {
				//Toast.makeText(getApplicationContext(), "分享成功啦", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			Toast.makeText(getApplicationContext(), "分享失败啦", Toast.LENGTH_SHORT).show();
			if (t != null) {
				Log.d("throw", "throw:" + t.getMessage());
			}
		}

		@Override
		public void onCancel(SHARE_MEDIA platform) {
			Toast.makeText(getApplicationContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
		}

	};

	@Override
	public void success(int type, JSONObject jsonObject) {
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
			kefu();
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

	}

	public void login() {
		//LoginInfo loginInfo = new LoginInfo("tmy1", "68c58f02597daa4fdc3ab86ed103e0c6");//测试账号
		if (PreferenceUtils.getPrefBoolean(this, Constant.CC_IFLOGIN, false) == false) {
			return;
		}
		User now = DBTools.getUser();
		if (now.getAccid() == null) {
			Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
		}
		LoginInfo loginInfo = new LoginInfo(now.getAccid(), now.getAccToken());
//        System.out.println("accid after new LoginInfo: " + accid);
//        System.out.println("acctoken after new LoginInfo: " + token);
		String getinfo = now.getUserImg();
		///sp.getString("userimag", "");
		//获取用户的im头像 gao
		OWN_HEADER_URL = getinfo;
		RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {

			@Override
			public void onSuccess(LoginInfo loginInfo) {

				isLogIn = true;
				if (isLogIn) {
					kefu();
				}
				request(Request.Method.GET, "http://imserver.myappcc.com/api/Getuseracc", null, "", tag, 100);

			}

			@Override
			public void onFailed(int i) {
				System.out.println("login failed===" + i);
			}

			@Override
			public void onException(Throwable throwable) {
			}
		};
		NIMClient.getService(AuthService.class).login(loginInfo).setCallback(callback);
	}

	public void request(int method, final String URL, Map map, final String session, String tag, final int type) {
		this.tag = tag;
		JSONObject jsonObject;
		if (map != null)
			jsonObject = new JSONObject(map);
		else {
			jsonObject = null;
		}
		JsonRequest jsonRequest = new JsonObjectRequest(method, URL, jsonObject, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				netResponses.success(type, jsonObject);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				netResponses.fail();
			}
		});

		jsonRequest.setTag(tag);
		// 将请求添加到队列中
		((MyApplication) getApplication()).getRequestQueue().add(jsonRequest);
	}

	Transformation transformation = new Transformation() {

		@Override
		public Bitmap transform(Bitmap source) {

			int mTargetWidth = width;


			if (source.getWidth() == 0) {
				return source;
			}
			double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
			int targetHeight = (int) (mTargetWidth * aspectRatio);
			if (targetHeight != 0 && mTargetWidth != 0) {
				Bitmap result = Bitmap.createScaledBitmap(source, mTargetWidth, targetHeight, false);
				if (result != source) {
					// Same bitmap is returned if sizes are the same
					source.recycle();
				}
				return result;
			} else {
				return source;
			}
		}

		@Override
		public String key() {
			return "transformation" + " desiredWidth";
		}
	};

	private void setMStripMove() {
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) find_aa.getLayoutParams();
		linearParams.width = width / 3;
		Display currDisplay = this.getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
		int displayWidth = currDisplay.getWidth();
		mOne = displayWidth / 3; //设置水平动画平移大小
	}
}
