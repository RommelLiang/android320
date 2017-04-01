package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.tiemuyu.chuanchuan.activity.adapter.AnswerBean;
import com.tiemuyu.chuanchuan.activity.bean.CustomerKey;
import com.tiemuyu.chuanchuan.activity.chat_tools.adapter.MessageAdapter;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.MessageData;
import com.tiemuyu.chuanchuan.activity.inter.MessageClick;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * Created by SHILI on 2016/6/15.
 * PackageName com.tiemuyu.chuanchuan.activity.chat_tools.utils.
 * ProjectName chuanchuan
 */
public class HistoryMessage implements MessageClick {
	int asd = 0;
	private String TAG = "HistoryMessage";
	private MessageAdapter adapter;
	int achour;
	private IMMessage immessage;
	boolean isLast;
	private PullToRefreshListView pullToRefreshListView;
	private ArrayList<IMMessage> imMessageArrayList;
	private Activity context;
	private MessageItemClick messageItemClick;
	private MessageShowUtil messageShowUtil;
	private String sessionId;
	ListView listViewShow;
	private CustomerKey mKeFuBean;

	public HistoryMessage() {
	}

	public HistoryMessage(final Activity context, PullToRefreshListView listView, String sessionId, CustomerKey mKeFuBean) {
		this.pullToRefreshListView = listView;
		imMessageArrayList = new ArrayList<>();
		MessageData.setDataList(imMessageArrayList);
		this.context = context;
		messageItemClick = new MessageItemClick(context);
		messageShowUtil = new MessageShowUtil(context, this);
		this.sessionId = sessionId;
		this.mKeFuBean = mKeFuBean;
	}

	public void aaaa(List<IMMessage> imMessages, final int count, boolean isGET) {
		if (isGET)
			imMessageArrayList.clear();
		if (imMessages == null) {
			ToastHelper.show(context,"没有消息");
			return ;
		}
		for ( int i = imMessages.size() - 1; i >= 0; i-- ) {

			isLast = true;
			imMessageArrayList.add(imMessages.get(i));
		}
		if (isLast) {
			if (adapter == null) {
				for ( int i = 0; i < imMessageArrayList.size(); i++ ) {
					Log.e(TAG + "ArrayList", "aaaa: " + imMessageArrayList.get(i).getContent() + ":" + imMessageArrayList.get(i).getFromAccount());
					IMMessage imMessage = imMessageArrayList.get(i);
					imMessage.toString();
				}
				adapter = new MessageAdapter(imMessageArrayList, context, mKeFuBean, this);
				adapter.setOnClickMessageItemListener(new MessageAdapter.OnClickMessageItemListener() {
					@Override
					public void onClickMessageItem(ImageView view, IMMessage imMessage, int d) {
						//messageItemClick.itemOnClick(view, imMessage, d);
					}
				});
				pullToRefreshListView.setAdapter(adapter);
			}
			adapter.notifyDataSetChanged();
			listViewShow = pullToRefreshListView.getRefreshableView();
			listViewShow.setSelection(pullToRefreshListView.getBottom());
		}
		System.out.println("====pullMessageFromLocal===" + imMessageArrayList.size());
		return ;
	}

	public void addListData() {
		imMessageArrayList = MessageData.getDataList();
		if (adapter == null) {
			adapter = new MessageAdapter(imMessageArrayList, context, mKeFuBean, this);
			adapter.setOnClickMessageItemListener(new MessageAdapter.OnClickMessageItemListener() {
				@Override
				public void onClickMessageItem(ImageView view, IMMessage imMessage, int d) {
					//messageItemClick.itemOnClick(view, imMessage, d);
				}
			});
			pullToRefreshListView.setAdapter(adapter);
		}
		adapter.notifyDataSetChanged();
		listViewShow = pullToRefreshListView.getRefreshableView();
		listViewShow.setSelection(pullToRefreshListView.getBottom());
	}

	public void pullMessageFromCloud(final IMMessage imMessage, final int count, final boolean isGET) {
		Log.e(TAG, "pullMessageFromCloud: " + imMessage.getContent());

		NIMClient
				.getService(MsgService.class)
				.queryMessageListEx(imMessage, QueryDirectionEnum.QUERY_NEW, count, true)
				.setCallback(new RequestCallback<List<IMMessage>>() {
					@Override
					public void onSuccess(List<IMMessage> param) {
						for ( int i = 0; i < param.size(); i++ ) {
							Log.e("queryMessageListEx", "onSuccess: " + param.get(i).getContent());
						}
						//aaaa(param, count, isGET);
					}

					@Override
					public void onFailed(int code) {
						Log.e(TAG, "onFailed: " + code);
					}

					@Override
					public void onException(Throwable exception) {
						Log.e(TAG, "onException: " + exception.getLocalizedMessage());
					}
				});
		NIMClient.getService(MsgService.class)
				.queryMessageList(sessionId, SessionTypeEnum.P2P, 0, count)
				.setCallback(new RequestCallbackWrapper<List<IMMessage>>() {
					@Override
					public void onResult(int i, List<IMMessage> imMessages, Throwable throwable) {

						Log.e(TAG, "onResult:asd " + asd);
						aaaa(imMessages, count, isGET);
						asd++;
					}
				});
	}

	// 史力：发送消息成功后执行的回调函数sendSuccessRefresh
	public IMMessage sendSuccessRefresh(IMMessage message) {
		imMessageArrayList.add(message);
		if (adapter == null) {
			adapter = new MessageAdapter(imMessageArrayList, context, mKeFuBean, this);
			adapter.setOnClickMessageItemListener(new MessageAdapter.OnClickMessageItemListener() {
				@Override
				public void onClickMessageItem(ImageView view, IMMessage imMessage, int d) {
					//messageItemClick.itemOnClick(view, imMessage, d);
				}
			});
			pullToRefreshListView.setAdapter(adapter);
		}
		adapter.notifyDataSetChanged();
		listViewShow = pullToRefreshListView.getRefreshableView();
		listViewShow.setSelection(pullToRefreshListView.getBottom());
		return message;
	}

	public void receiveMessageRefresh(final List<IMMessage> imMessages) {
		for ( IMMessage message : imMessages ) {
			imMessageArrayList.add(message);
		}
		if (adapter == null) {
			adapter = new MessageAdapter(imMessageArrayList, context, mKeFuBean, this);
			pullToRefreshListView.setAdapter(adapter);
		}
		context.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						adapter.notifyDataSetChanged();
						listViewShow = pullToRefreshListView.getRefreshableView();
						listViewShow.setSelection(pullToRefreshListView.getBottom());
					}
				}, 10);

			}
		});
	}

	private ShowImage showImage;

	@Override
	public void getAnsewr(String s) {
		Log.e(TAG, "getAnsewr: " + s);
		String word ="";
		try {
			word = URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException mE) {
			mE.printStackTrace();
		} finally {
			StringRequest stringRequest = new StringRequest("http://imserver.myappcc.com/api/GetCustomer?key="+word , new Response.Listener<String>() {
				@Override
				public void onResponse(String mS) {
					AnswerBean answerBean = GsonUtils.fromData(mS, AnswerBean.class);
					Log.e("onResponse: ", answerBean.getData().getAttachcontent());
					Log.e("onResponse: ", mS);
					IMMessage imMessage = MessageBuilder.createTextMessage("9000", SessionTypeEnum.P2P, answerBean.getData().getAttachcontent());
					imMessage.setDirect(MsgDirectionEnum.In);
					imMessageArrayList.add(imMessage);
					adapter.notifyDataSetChanged();
					listViewShow.setSelection(pullToRefreshListView.getBottom());
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError mVolleyError) {
					IMMessage imMessage = MessageBuilder.createTextMessage("9000", SessionTypeEnum.P2P, "穿穿出了点问题啊");
					imMessage.setDirect(MsgDirectionEnum.In);
					imMessageArrayList.add(imMessage);
					adapter.notifyDataSetChanged();
					listViewShow.setSelection(pullToRefreshListView.getBottom());
				}
			});
			RequestQueue mQueue = Volley.newRequestQueue(context);
			mQueue.add(stringRequest);
		}
	}

	public interface ShowImage {
		void showImage(IMMessage message, ImageView view, MessageAdapter adapter);
	}

	public void setShowImage(ShowImage showImage) {
		this.showImage = showImage;
	}

}
