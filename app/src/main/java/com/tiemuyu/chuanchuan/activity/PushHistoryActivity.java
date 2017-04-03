package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tiemuyu.chuanchuan.activity.adapter.PushMessageAdapter;
import com.tiemuyu.chuanchuan.activity.bean.PushBean;
import com.tiemuyu.chuanchuan.activity.bean.PushBeanPlus;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.inter.SelectInterFace;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.SPUtils;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushHistoryActivity extends BaseActivityG implements SelectInterFace {


	private PushBean pushBean;
	private ListView mListView;
	private TextView tv_edit;
	private Button btn_delete, btn_select_all;
	private LinearLayout ll_change;
	private boolean is_show_select = false;
	private PushBeanPlus mPushBeanPlus;
	List<Integer> mIntegers;
	private PushMessageAdapter mPushMessageAdapter;
	private List<String> mStrings;
	private boolean isall = false;
	private String umengids;
	public final String TAG_DELETE_PUSH = "TAG_DELETE_PUSH";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push_history);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		String addTime = SPUtils.getAddTime();
		if (addTime.equals("")) {
			ToastHelper.show(this, "没有历史消息");
		}
		mListView = (ListView) findViewById(R.id.lv_message);
		tv_edit = (TextView) findViewById(R.id.tv_edit);
		ll_change = (LinearLayout) findViewById(R.id.ll_change);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_select_all = (Button) findViewById(R.id.btn_select_all);
		getHistory();
		Log.e("onCreate: ", UrlManager.getPush(MineFragment.user.getUserId() + "", addTime, "max", 200));
	}

	private void getHistory() {
		StringRequest stringRequest = new StringRequest(UrlManager.getPush(MineFragment.user.getUserId() + "", "2017-03-01", "max", 200),
				new Response.Listener<String>() {
					@Override
					public void onResponse(String mS) {
						Log.e("onResponse", "onResponse: " + mS);
						setView(mS);
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError mVolleyError) {
				Log.e("onErrorResponse: ", mVolleyError.getLocalizedMessage());
			}
		});
		RequestQueue mQueue = Volley.newRequestQueue(this);
		mQueue.add(stringRequest);
	}

	private void setView(String callBackMsg) {
		pushBean = GsonUtils.fromData(callBackMsg, PushBean.class);
		List<PushBean.HistoryBean> history = pushBean.getHistory();
		mIntegers = new ArrayList<>();
		for ( PushBean.HistoryBean historyBean : history ) {
			mIntegers.add(0);
		}
		mPushBeanPlus = new PushBeanPlus(pushBean, is_show_select, mIntegers);
		mPushMessageAdapter = new PushMessageAdapter(PushHistoryActivity.this, mPushBeanPlus, PushHistoryActivity.this);
		mListView.setAdapter(mPushMessageAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent;
				int type = 10;
				Context nContext = PushHistoryActivity.this;
				type = JudgmentLegal.checkBannerType(pushBean.getHistory().get(position).getP_link());
				Log.e("onItemClick: ", type+"");
				if (type == 0) {
					String[] sourceStrArray = pushBean.getHistory().get(position).getP_link().split("id=");
					id = Integer.parseInt(sourceStrArray[1]);
					Log.e("OnBannerClick: ", id + "");
					intent = new Intent(nContext, FindWaterActivity.class);
					intent.putExtra("id", id);
					nContext.startActivity(intent);
				} else if (type == 1) {
					intent = new Intent(nContext, MyWebview.class);
					intent.putExtra("Intent_Data_Packet", pushBean.getHistory().get(position).getP_link());
					intent.putExtra("title", pushBean.getHistory().get(position).getP_title());
					intent.putExtra("type", 1);
					intent.putExtra("img_url", pushBean.getHistory().get(position).getP_image());
					nContext.startActivity(intent);
				} else if (type == 2) {
					String[] sourceStrArray = pushBean.getHistory().get(position).getP_link().split("id=");
					id = Integer.parseInt(sourceStrArray[1]);
					intent = new Intent(nContext, FindTopicActivity.class);
					intent.putExtra("id", id);
					nContext.startActivity(intent);
				} else if (type == 10){
					finish();
				}
			}
		});
		mStrings = new ArrayList<>();
		tv_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (is_show_select) {
					is_show_select = false;
					mPushBeanPlus.setShou(is_show_select);
					tv_edit.setText("编辑");
					ll_change.setVisibility(View.GONE);
				} else {
					is_show_select = true;
					mPushBeanPlus.setShou(is_show_select);
					tv_edit.setText("保存");
					ll_change.setVisibility(View.VISIBLE);
				}
				mPushMessageAdapter.notifyDataSetChanged();
			}
		});
		btn_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mStrings.size() == 0) {
					ToastHelper.show(PushHistoryActivity.this, "请先选择");
					return;
				}
				umengids = "[";
				for ( int i = 0; i < mStrings.size(); i++ ) {
					if (i != mStrings.size() - 1) {
						umengids += pushBean.getHistory().get(Integer.parseInt(mStrings.get(i))).getP_id() + ",";
					} else {
						umengids += pushBean.getHistory().get(Integer.parseInt(mStrings.get(i))).getP_id();
					}
				}
				umengids += "]";
				Log.e("onClick: ", umengids);
				RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

				StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlManager.deletePush(),
						new Response.Listener<String>() {
							@Override
							public void onResponse(String response) {
								Log.e("onResponse:操作完成 ", response);
								getHistory();
								is_show_select = false;
								mPushBeanPlus.setShou(is_show_select);
								tv_edit.setText("编辑");
								ll_change.setVisibility(View.GONE);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Log.e("onErrorResponse: ", error.getLocalizedMessage());
							}
						}) {
					@Override
					protected Map<String, String> getParams() {
						//在这里设置需要post的参数
						Map<String, String> params = new HashMap<String, String>();
						params.put("userid", MineFragment.user.getUserId() + "");
						params.put("umengids", umengids);
						return params;
					}
				};
				requestQueue.add(stringRequest);
			}
		});
		btn_select_all.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<Integer> integers = mPushBeanPlus.getIntegers();
				mStrings.clear();
				if (isall) {
					for ( int i = 0; i < integers.size(); i++ ) {
						mPushBeanPlus.getIntegers().set(i, 0);
					}
					isall = false;
					btn_select_all.setText("全选");
				} else {
					for ( int i = 0; i < integers.size(); i++ ) {
						mPushBeanPlus.getIntegers().set(i, 1);
						mStrings.add(i + "");
					}
					btn_select_all.setText("取消全选");
					isall = true;
				}
				mPushMessageAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onSelect(int postion) {
		mPushBeanPlus.getIntegers().set(postion, 1);
		mPushMessageAdapter.notifyDataSetChanged();
		mStrings.add(postion + "");
	}

	@Override
	public void onUnSelect(int postion) {
		mPushBeanPlus.getIntegers().set(postion, 0);
		mPushMessageAdapter.notifyDataSetChanged();
		mStrings.remove(postion + "");
	}
}
