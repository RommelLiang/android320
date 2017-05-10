package com.tiemuyu.chuanchuan.activity.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.fragment.TextMessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.db.DBTools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 梁文硕 on 2017/5/9.
 */

public class JumpToKeFU implements NetResponses{
	//那么多跳转客服，傻逼不知道封装
	private JumpToKeFU instance;
	private Context mActivity;
	private boolean isLogIn = false;
	public String OWN_HEADER_URL = "";
	public String tag = "MESSAGE";
	private NetResponses netResponses;
	private ArrayList<Contacts> dataList = new ArrayList<>();
	private DataSharedPress sharedPress;
	public void getInstance(Context mActivity) {
		instance = new JumpToKeFU();
		instance.mActivity = mActivity;
		setNetResponses(this);
		if (isLogIn) {
			kefu();
		} else {
			login();
		}
	}

	private void kefu() {
		Intent intent1 = new Intent(instance.mActivity, TextMessageActivity.class);
		intent1.putExtra("sessionId", SPUtils.getKefuCode());
		intent1.putExtra("title", "定制助理（9:00-24:00）");
		instance.mActivity.startActivity(intent1);

		sharedPress = DataSharedPress.getSharedPress(instance.mActivity);
		sharedPress.putInt(SPUtils.getKefuCode() + "unread", 0);
		((Activity)instance.mActivity).finish();
	}

	private void login() {

		User now = DBTools.getUser();
		if (now.getAccid() == null) {
			Toast.makeText(instance.mActivity, "网络异常", Toast.LENGTH_SHORT).show();
		}
		LoginInfo loginInfo = new LoginInfo(now.getAccid(), now.getAccToken());
		String getinfo = now.getUserImg();
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
		((MyApplication) ((Activity)instance.mActivity).getApplication()).getRequestQueue().add(jsonRequest);
	}
	private void setNetResponses(NetResponses netResponses) {
		this.netResponses = netResponses;
	}

	@Override
	public void success(int type, JSONObject jsonObject) {
		Contacts contacts;
		Log.e("ClothesDetail", "success: ");
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
}
