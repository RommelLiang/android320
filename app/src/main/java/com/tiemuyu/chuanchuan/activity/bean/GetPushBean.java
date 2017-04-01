package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/31.
 */

public class GetPushBean {

	/**
	 * p_text : 周年庆！
	 * p_type : 活动
	 * p_id : 67
	 * p_attachmsg :
	 * p_content : 2周年庆
	 * p_sendtime : 2017/3/31 10:49:32
	 * p_image : http://f1.myappcc.com/zfs/7E1/1089/PQF/089153839618CABHGEQHZY.jpg
	 * p_link : http://a1.myappcc.com/cc/Find/RankingList?id=170329110036737
	 * p_title : 穿穿两周年 定制享8折
	 */

	private String p_text;
	private String p_type;
	private String p_id;
	private String p_attachmsg;
	private String p_content;
	private String p_sendtime;
	private String p_image;
	private String p_link;
	private String p_title;

	public static GetPushBean objectFromData(String str) {

		return new Gson().fromJson(str, GetPushBean.class);
	}

	public static GetPushBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), GetPushBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<GetPushBean> arrayGetPushBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<GetPushBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<GetPushBean> arrayGetPushBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<GetPushBean>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getString(str), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList();


	}

	public String getP_text() {
		return p_text;
	}

	public void setP_text(String p_text) {
		this.p_text = p_text;
	}

	public String getP_type() {
		return p_type;
	}

	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getP_attachmsg() {
		return p_attachmsg;
	}

	public void setP_attachmsg(String p_attachmsg) {
		this.p_attachmsg = p_attachmsg;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_sendtime() {
		return p_sendtime;
	}

	public void setP_sendtime(String p_sendtime) {
		this.p_sendtime = p_sendtime;
	}

	public String getP_image() {
		return p_image;
	}

	public void setP_image(String p_image) {
		this.p_image = p_image;
	}

	public String getP_link() {
		return p_link;
	}

	public void setP_link(String p_link) {
		this.p_link = p_link;
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
}
