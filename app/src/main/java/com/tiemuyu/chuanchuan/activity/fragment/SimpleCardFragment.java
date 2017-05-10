package com.tiemuyu.chuanchuan.activity.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.FindTopicActivity;
import com.tiemuyu.chuanchuan.activity.FindWaterActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.MyWebview;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.adapter.HeoizonListViewAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.WaterAdapter;
import com.tiemuyu.chuanchuan.activity.adapter.ZhuanTiWaterAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.DIngzhi;
import com.tiemuyu.chuanchuan.activity.bean.HomeBanner;
import com.tiemuyu.chuanchuan.activity.bean.LastPrice;
import com.tiemuyu.chuanchuan.activity.bean.ZhuanTiMessage;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.proxy.LoadingProxy;
import com.tiemuyu.chuanchuan.activity.util.GlideImageLoader;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.youth.banner.listener.OnBannerClickListener;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends HomeFragmentBasw implements PullToRefreshBase.OnRefreshListener2<ListView>, ThreadPoolTaskHttp.HttpCallBack {
	private String mTitle;
	private PullToRefreshListView mPullRefreshListView;
	private ListView mRefreshableView;
	private static final String TAG_GETBANNER = "TAG_GETBANNER";
	private static final String TAG_GETWATER = "TAG_GETWATER";
	private static final String TAG_GET_LIST = "TAG_GET_LIST";
	private static final String TAG_ADD_GET_LIST = "TAG_ADD_GET_LIST";
	private static final String TAG_ADDWATER = "TAG_ADDWATER";
	private static final String TAG = "SimpleCardFragment";
	private WaterAdapter wateradaper;
	private List<HomeBanner.DataBean> homeBannerData;
	private ArrayList<String> imagesList;
	private View headerView;
	private com.youth.banner.Banner headerBanner;
	private LastPrice lastPrice;
	private List<LastPrice.DataBean.RowsBean> rows;
	private HomeBanner homeBanner;
	private int page, zhuanti;
	private ZhuanTiMessage mZhuanTiMessage;
	private Context mContext;
	private ZhuanTiWaterAdapter mZhuanTiWaterAdapter;
	private View headerImage;
	private ImageView image;
	private TextView tv_miaoshu;
	private DIngzhi mDIngzhi;
	private List<DIngzhi.DataBean> mDIngzhiChengPin;
	private HeoizonListViewAdapter mHeoizonListViewAdapter;
	private String mGet_water = "";
	private LoadingProxy mInstance;
	private boolean isGetHttp = true;
	private int mHeight;
	private HomeFragmentR mHomeFragmentR;

	public static SimpleCardFragment getInstance(String title, Activity mContext, HomeFragmentR mHomeFragmentR) {
		SimpleCardFragment sf = new SimpleCardFragment();
		sf.mTitle = title;
		sf.mContext = mContext;
		sf.mInstance = LoadingProxy.getInstance(mContext);
		sf.mHomeFragmentR = mHomeFragmentR;
		return sf;
	}


	@Override
	public int getLayout() {
		Log.e(TAG, "声明周期getLayout"+mTitle );
		return R.layout.fr_simple_card;
	}

	@Override
	public void initView(View view) {
		mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_grid);
		mRefreshableView = mPullRefreshListView.getRefreshableView();
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mHeight = wm.getDefaultDisplay().getHeight();
	}

	@Override
	public void onInVisible() {
		Log.e(TAG, "声明周期onInVisible"+mTitle+isVisible);
		isVisible = false;
		mInstance.dismiss();
	}

	@Override
	public void loadData() {
		Log.e(TAG, "声明周期loadData "+mTitle );
		if (mTitle.equals("定制成品")) {

		} else if (mTitle.equals("最新报价")) {

		} else if (mTitle.equals("裙装")) {
			zhuanti = 20;
		} else if (mTitle.equals("外套")) {
			zhuanti = 19;
		} else if (mTitle.equals("上衣")) {
			zhuanti = 22;
		} else if (mTitle.equals("裤装")) {
			zhuanti = 21;
		} else if (mTitle.equals("套装")) {
			zhuanti = 23;
		}
		page = 1;
		setView();
	}




	private void setView() {
		Log.e(TAG, "声明周期setView "+mTitle );
		if (mTitle.equals("定制成品") || mTitle.equals("最新报价")) {
			getBanner();
		} else {
			getZhuanTi(zhuanti, page, TAG_GET_LIST);
		}
	}

	private void getBanner() {
		if (isGetHttp) {
			if (isVisible) {
				Log.e("getBanner: ", mTitle);
				try {
					mInstance.show();
				} catch (Exception e){
					Log.e("getBannerException: ", mTitle+e.getLocalizedMessage());
				}
			}
		}
		//获取banner
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(mContext,
						TAG_GETBANNER,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.Get_Banner()),
						this,
						"获取banner",
						false));
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e(TAG, "声明周期onResume "+mTitle +isVisible );
	}

	private void getLastPrice(String tag) {
		if (mTitle.equals("最新报价")) {
			mGet_water = UrlManager.Add_Water() + page + "&pageSize=10";
		} else if (mTitle.equals("定制成品")) {
			mGet_water = UrlManager.Getdingzhilist(String.valueOf(page));
		}
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(mContext,
						tag,
						Constant.REQUEST_GET,
						new RequestParams(mGet_water),
						this,
						"获取最新报价",
						false));
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		if (resultTag.equals(TAG_GETBANNER)) {
			afterGetBanner(callBackMsg);
		} else if (resultTag.equals(TAG_GETWATER)) {
			mInstance.dismiss();
			isGetHttp = true;
			afterGetListWater(callBackMsg, resultTag);
		} else if (resultTag.equals(TAG_GET_LIST)) {
			mInstance.dismiss();
			isGetHttp = true;
			setListviewData(callBackMsg);
		} else if (resultTag.equals(TAG_ADD_GET_LIST)) {
			mInstance.dismiss();
			setListDataUpdata(callBackMsg);
		} else if (resultTag.equals(TAG_ADDWATER)) {
			setDataUpdata(callBackMsg);
		}
	}

	private void setDataUpdata(String callBackMsg) {
		mPullRefreshListView.onRefreshComplete();
		try {
			if (mTitle.equals("最新报价")) {
				rows.addAll(GsonUtils.fromData(callBackMsg, LastPrice.class).getData().getRows());
				wateradaper.notifyDataSetChanged();
			} else if (mTitle.equals("定制成品")) {
				mDIngzhiChengPin.addAll(GsonUtils.fromData(callBackMsg, DIngzhi.class).getData());
				mHeoizonListViewAdapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			Log.e(TAG, "Exception: " + e.getLocalizedMessage());
		}
	}

	private void setListDataUpdata(String callBackMsg) {
		mZhuanTiMessage.getData().getList().addAll(GsonUtils.fromData(callBackMsg, ZhuanTiMessage.class).getData().getList());
		mZhuanTiWaterAdapter.notifyDataSetChanged();
		mPullRefreshListView.onRefreshComplete();
	}

	private void setListviewData(String callBackMsg) {
		if (mZhuanTiMessage != null) {
			mPullRefreshListView.onRefreshComplete();
		}
		mZhuanTiMessage = GsonUtils.fromData(callBackMsg, ZhuanTiMessage.class);
		mZhuanTiWaterAdapter = new ZhuanTiWaterAdapter(mZhuanTiMessage.getData().getList(), mContext);
		mRefreshableView.setAdapter(mZhuanTiWaterAdapter);
		int headerViewsCount = mRefreshableView.getHeaderViewsCount();
		if (headerViewsCount == 0) {
			headerImage = View.inflate(mContext, R.layout.header_image, null);
			image = (ImageView) headerImage.findViewById(R.id.header_image);
			ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
			layoutParams.height = (int) (mHeight / 3.5);
			tv_miaoshu = (TextView) headerImage.findViewById(R.id.tv_miaoshu);
			Picasso.with(mContext).load(mZhuanTiMessage.getData().getTopics().getBigimg()).into(image);
			tv_miaoshu.setText(mZhuanTiMessage.getData().getTopics().getMiaoshu());
			Log.e("refreshableView6", "setBanner: " + "refreshableView6");
			mRefreshableView.addHeaderView(headerImage);
		}
		mPullRefreshListView.setOnRefreshListener(this);
	}


	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		isGetHttp = false;
		if (mTitle.equals("定制成品") || mTitle.equals("最新报价")) {
			getBanner();
		} else {
			getZhuanTi(zhuanti, page, TAG_GET_LIST);
		}
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		if (mTitle.equals("定制成品") || mTitle.equals("最新报价")) {
			getLastPrice(TAG_ADDWATER);
		} else {
			getZhuanTi(zhuanti, page, TAG_ADD_GET_LIST);
		}
	}


	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		Log.e(TAG, "failCallBack: " + arg0.getLocalizedMessage());
		mInstance.dismiss();
	}

	@Override
	public void startCallBack(String resultTag, boolean isShowDiolog, String showTitle) {

	}

	@Override
	public void cancelCallBack(String resultTag) {
		mInstance.dismiss();
	}

	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		Log.e(TAG, "failCallBack: " + resultTag);
		mInstance.dismiss();
	}

	@Override
	public void reLoginCallBack(String resultTag, boolean isShowDiolog) {

	}

	private void getZhuanTi(int id, int count, String TAG) {
		if (isGetHttp) {
			if (isVisible) {
				Log.e("getZhuanTi: ", mTitle);
				try {
					mInstance.show();
				} catch (Exception e){
					Log.e("getZhuanTiException: ", mTitle+e.getLocalizedMessage());
				}
			}
		}
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(mContext,
						TAG,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.Get_ZhuanTi() + count + "&id=" + id),
						this,
						"获取裙装",
						false));
	}

	private void afterGetListWater(String callBackMsg, String requestTag) {
		if (wateradaper != null || mHeoizonListViewAdapter != null) {
			mPullRefreshListView.onRefreshComplete();
		}
		if (mTitle.equals("最新报价")) {
			lastPrice = GsonUtils.fromData(callBackMsg, LastPrice.class);
			rows = lastPrice.getData().getRows();
			wateradaper = new WaterAdapter(rows, mContext);
			mPullRefreshListView.setAdapter(wateradaper);
		} else if (mTitle.equals("定制成品")) {

			Log.e(TAG, mTitle + callBackMsg);
			mDIngzhi = GsonUtils.fromData(callBackMsg, DIngzhi.class);
			Log.e(TAG, mTitle + mDIngzhi.getMsg());
			mDIngzhiChengPin = mDIngzhi.getData();
			mHeoizonListViewAdapter = new HeoizonListViewAdapter(mDIngzhiChengPin, mContext);
			mRefreshableView = mPullRefreshListView.getRefreshableView();
			mPullRefreshListView.setAdapter(mHeoizonListViewAdapter);
		}
		if (requestTag.equals(TAG_GETWATER)) {
			mPullRefreshListView.setOnRefreshListener(this);
		}
		setBannner();
	}

	private void setBannner() {
		int headerViewsCount = mRefreshableView.getHeaderViewsCount();
		if (headerViewsCount == 0) {
			headerView = View.inflate(mContext, R.layout.header_banner, null);
			headerBanner = (com.youth.banner.Banner) headerView.findViewById(R.id.banner);
			ViewGroup.LayoutParams layoutParams = headerBanner.getLayoutParams();
			layoutParams.height = (int) (mHeight / 3.5);
			headerBanner.setImageLoader(new GlideImageLoader())
					.setImages(imagesList).start();
			mRefreshableView.addHeaderView(headerView);
		}
		headerBanner.setOnBannerClickListener(new OnBannerClickListener() {
			@Override
			public void OnBannerClick(int position) {
				int id;
				int type = JudgmentLegal.checkBannerType(homeBannerData.get(position - 1).getLinkUrl());
				if (type == 0) {
					String[] sourceStrArray = homeBannerData.get(position - 1).getLinkUrl().split("id=");
					id = Integer.parseInt(sourceStrArray[1]);
					Log.e("OnBannerClick: ", id + "");
					Intent intent = new Intent(mContext, FindWaterActivity.class);
					intent.putExtra("id", id);
					startActivity(intent);
				} else if (type == 1) {
					Intent intent = new Intent(mContext, MyWebview.class);
					intent.putExtra("Intent_Data_Packet", homeBannerData.get(position - 1).getLinkUrl());
					intent.putExtra("title", homeBannerData.get(position - 1).getTitle());
					intent.putExtra("type", 1);
					intent.putExtra("img_url", homeBannerData.get(position - 1).getImgUrl());
					startActivity(intent);
				} else if (type == 2) {
					String[] sourceStrArray = homeBannerData.get(position - 1).getLinkUrl().split("id=");
					id = Integer.parseInt(sourceStrArray[1]);
					Intent intent = new Intent(mContext, FindTopicActivity.class);
					intent.putExtra("id", id);
					startActivity(intent);
				} else {
					id = 0;
				}

			}
		});
	}

	private void afterGetBanner(String callBackMsg) {
		Log.e("TAG_GETBANNER", "HomeAction: " + callBackMsg);
		homeBanner = GsonUtils.fromData(callBackMsg, HomeBanner.class);
		homeBannerData = homeBanner.getData();
		getLastPrice(TAG_GETWATER);
		imagesList = new ArrayList<>();
		for ( int i = 0; i < homeBannerData.size(); i++ ) {
			imagesList.add(homeBannerData.get(i).getImgUrl());
		}
		headerView = View.inflate(mContext, R.layout.header_banner, null);
		headerBanner = (com.youth.banner.Banner) headerView.findViewById(R.id.banner);
		headerBanner.setImageLoader(new GlideImageLoader())
				.setImages(imagesList).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "声明周期onDestroy: "+mTitle );
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e(TAG, "声明周期onDestroyView: "+mTitle );
		wateradaper = null;
		mZhuanTiWaterAdapter = null;
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e(TAG, "声明周期onPause: "+mTitle +isVisible);
	}
}