package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiemuyu.chuanchuan.activity.adapter.MoreRecyclerViewAdapter;
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

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


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
	private RecyclerView recycler_view;
	private MoreRecyclerViewAdapter mMoreRecyclerViewAdapter;
	private BGARefreshLayout mSwipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_dingzhi);
		mIntent = getIntent();
		mId = mIntent.getIntExtra("id", 0);
		recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
		mSwipeRefreshLayout = (BGARefreshLayout)findViewById(R.id.layout_swipe_refresh);
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
						img_url =mMoreBean.getData().get(0).getImg();
						url = "http://ios.myappcc.com/cc/Home/customizeListShow?id="+
								mId
								+"&from=singlemessage&isappinstalled=0";
						Log.e("url", "onClick: " + url);
						share();
					}
				});
			} else {
				if (page == 1) {
					mMoreBean = GsonUtils.fromData(callBackMsg, MoreBean.class);
				} else {
					mMoreBean.getData().get(0).getAppdingzhilist().addAll(
							GsonUtils.fromData(callBackMsg, MoreBean.class).getData().get(0).getAppdingzhilist()
					);
				}
				mMoreRecyclerViewAdapter.notifyDataSetChanged();
			}
			mMoreRecyclerViewAdapter = new MoreRecyclerViewAdapter(MoreDingzhiActivity.this, mMoreBean);
			recycler_view.setLayoutManager(new GridLayoutManager(MoreDingzhiActivity.this,2));
			recycler_view.setAdapter(mMoreRecyclerViewAdapter);
			mInstance.dismiss();
			ImageView imageView = new ImageView(MoreDingzhiActivity.this);
			imageView.setBackground(getResources().getDrawable(R.drawable.home_friend));

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

}
