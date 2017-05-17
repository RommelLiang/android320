package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.tiemuyu.chuanchuan.activity.adapter.MoreAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.MoreBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.proxy.LoadingProxy;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.xutils.http.RequestParams;




public class MoreDingzhiActivity extends BaseActivityG {


	private Intent mIntent;
	private int mId;
	private static final String TAG_GET_MORE = "TAG_GET_MORE";
	private int page = 1;
	private MoreBean mMoreBean;
	private TextView tv_title;
	private ImageView im_share;
	private String title, url, img_url;
	private LoadingProxy mInstance;
	private MoreAdapter mMoreAdapter;
	private PullToRefreshGridView pull_refresh_grid;
	private GridView mRefreshableView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_dingzhi);
		mIntent = getIntent();
		mId = mIntent.getIntExtra("id", 0);
		pull_refresh_grid = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
		tv_title = (TextView) findViewById(R.id.tv_title);
		im_share = (ImageView) findViewById(R.id.im_share);
		mInstance = LoadingProxy.getInstance(MoreDingzhiActivity.this);
		getMore();
		if (mMoreBean == null) {
			mInstance.show();
		}
		findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void getMore() {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_GET_MORE,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.getMore(mId, page)),
						this,
						"获取版本信息",
						false));

	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		if (resultTag.equals(TAG_GET_MORE)) {
			Log.e("successCallBack: ", callBackMsg);
			if (mMoreBean == null) {
				mMoreBean = GsonUtils.fromData(callBackMsg, MoreBean.class);
				tv_title.setText(mMoreBean.getData().get(0).getName() + "选");
				im_share.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						title = mMoreBean.getData().get(0).getName() + "选";
						img_url =mMoreBean.getData().get(0).getAppdingzhilist().get(0).getPromianpic();
						url = "http://testios.myappcc.com/product/Cus_Product?id="+
								mId
								+"&type=1";
						Log.e("url", "onClick: " + url);
						share();
					}
				});
				pull_refresh_grid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
					@Override
					public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
						page =1;
						getMore();
					}

					@Override
					public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
						page++;
						getMore();
					}
				});
			} else {
				pull_refresh_grid.onRefreshComplete();
				if (page == 1) {
					mMoreBean = GsonUtils.fromData(callBackMsg, MoreBean.class);

				} else {
					mMoreBean.getData().get(0).getAppdingzhilist().addAll(
							GsonUtils.fromData(callBackMsg, MoreBean.class).getData().get(0).getAppdingzhilist()
					);
				}
				mMoreAdapter.notifyDataSetChanged();
			}
			mMoreAdapter = new MoreAdapter(MoreDingzhiActivity.this, mMoreBean);
			mRefreshableView = pull_refresh_grid.getRefreshableView();
			mRefreshableView.setNumColumns(2);
			//mMoreAdapter.setLayoutManager(new GridLayoutManager(MoreDingzhiActivity.this,2));
			mRefreshableView.setAdapter(mMoreAdapter);
			mInstance.dismiss();
			initIndicator(pull_refresh_grid);

		}
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		if (resultTag.equals(TAG_GET_MORE)) {
			Log.e("failCallBack: ", arg0.getLocalizedMessage());
			mInstance.dismiss();
		}
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
	//为瀑布流下拉定义外观
	private void initIndicator(PullToRefreshGridView mp) {
		ILoadingLayout startLabels = mp.getLoadingLayoutProxy(true, false);
		startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("刷新中...");// 刷新时
		startLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示

		ILoadingLayout endLabels = mp.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		endLabels.setRefreshingLabel("正在刷新...");// 刷新时
		endLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示

	}
}
