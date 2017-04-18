package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiemuyu.chuanchuan.activity.adapter.BodyAdapter;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.BodyDataBean;
import com.tiemuyu.chuanchuan.activity.bean.BodysBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.inter.DeleteClick;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class MyBodyManngerActivity extends BaseActivityG implements PullToRefreshBase.OnRefreshListener2<ListView>,DeleteClick{

	private RelativeLayout add_body_message;
	private PullToRefreshListView pull_refresh_grid;
	private String TAG_GET_BODY = "TAG_GET_BODY";
	private String TAG_UPDATA_BODY = "TAG_UPDATA_BODY";
	private String TAG_GETBODYINFO = "TAG_GETBODYINFO";
	private ListView mRefreshableView;
	private BodysBean mBodysBean;
	private BodyAdapter mBodyAdapter;
	private BodysBean.DataBean.UserCCInfoListBean mUserCCInfoListBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_body_mannger);
		add_body_message = (RelativeLayout) findViewById(R.id.add_body_message);
		pull_refresh_grid = (PullToRefreshListView) findViewById(R.id.pull_refresh_grid);
		mRefreshableView = pull_refresh_grid.getRefreshableView();
		initIndicator(pull_refresh_grid);
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_GET_BODY,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.getBodys(DBTools.getUser().getUserId() + "")),
						this,
						"获取身体数据",
						false));
		add_body_message.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyBodyManngerActivity.this, MyBody.class);
				intent.putExtra("type",1);
				startActivity(intent);
			}
		});
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		Log.e("successCallBack:Manger ", resultTag + ":" + callBackMsg);
		if (resultTag.equals(TAG_GET_BODY)) {
			MyApplication.poolManager.addAsyncTask(
					new ThreadPoolTaskHttp(this,
							TAG_GETBODYINFO,
							Constant.REQUEST_GET,
							new RequestParams(
									UrlManager.GET_MYBODY()),
							this,
							"获取旧版身体数据",
							false
					));

			mBodysBean = GsonUtils.fromData(callBackMsg, BodysBean.class);
		} else if (resultTag.equals(TAG_UPDATA_BODY)) {
			pull_refresh_grid.onRefreshComplete();
			mBodysBean = GsonUtils.fromData(callBackMsg, BodysBean.class);
			if (mBodyAdapter == null) {
				mBodyAdapter = new BodyAdapter(MyBodyManngerActivity.this,this, mBodysBean.getData().getUserCCInfoList());
				mRefreshableView.setAdapter(mBodyAdapter);
			} else {
				mBodyAdapter.notifyDataSetChanged();
			}
			MyApplication.poolManager.addAsyncTask(
					new ThreadPoolTaskHttp(this,
							TAG_GETBODYINFO,
							Constant.REQUEST_GET,
							new RequestParams(
									UrlManager.GET_MYBODY()),
							this,
							"获取旧版身体数据",
							false
					));

		} else if (resultTag.equals(TAG_GETBODYINFO)){
			BodyDataBean bodyDataBean =GsonUtils.fromData(callBackMsg, BodyDataBean.class);
			BodysBean.DataBean.UserCCInfoListBean dataBean = new BodysBean.DataBean.UserCCInfoListBean();
			dataBean.setName("");
			dataBean.setGENDER(bodyDataBean.getData().getGender());
			dataBean.setWEIGHT(bodyDataBean.getData().getWeight());
			dataBean.setHEIGHT(bodyDataBean.getData().getHeight());
			dataBean.setAGE(bodyDataBean.getData().getAge());
			if (dataBean.getName() == null || dataBean.getName().equals("")){
				dataBean.setName(DBTools.getUser().getNickName()+"");
			}
			Log.e(TAG_GETBODYINFO, "successCallBack: "+dataBean.getName());
			mBodysBean.getData().getUserCCInfoList().add(0,dataBean);
			pull_refresh_grid.setOnRefreshListener(MyBodyManngerActivity.this);
			mBodyAdapter = new BodyAdapter(MyBodyManngerActivity.this, this,mBodysBean.getData().getUserCCInfoList());
			mRefreshableView.setAdapter(mBodyAdapter);
		} else if (resultTag.equals(TAG_DELETE_BODY_DATA)){
			Log.e("TAG_DELETE_BODY_DATA: ", callBackMsg);
			Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
			refreshBody();
		}
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		super.failCallBack(arg0, resultTag, isShowDiolog);
		Log.e("failCallBack: ", resultTag + ":" + arg0.getLocalizedMessage());
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		refreshBody();
	}

	private void refreshBody() {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_UPDATA_BODY,
						Constant.REQUEST_GET,
						new RequestParams(
								UrlManager.getBodys(DBTools.getUser().getUserId() + "")),
						this,
						"获取身体数据",
						false));
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pull_refresh_grid.onRefreshComplete();
	}

	//为瀑布流下拉定义外观
	private void initIndicator(PullToRefreshListView mp) {
		ILoadingLayout startLabels = mp.getLoadingLayoutProxy(true, false);
		startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("刷新中...");// 刷新时
		startLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示

		ILoadingLayout endLabels = mp.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		endLabels.setRefreshingLabel("正在刷新...");// 刷新时
		endLabels.setReleaseLabel("松开即可刷新...");// 下来达到一定距离时，显示的提示
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		refreshBody();
	}

	public static final String TAG_DELETE_BODY_DATA = "TAG_DELETE_BODY_DATA";
	@Override
	public void onDelete(int postion) {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(this,
						TAG_DELETE_BODY_DATA,
						Constant.REQUEST_POST,
						ParamsTools.delectBody(mBodysBean.getData().getUserCCInfoList().get(postion).getID()+""),
						this,
						"删除身体数据",
						false));
	}
}
