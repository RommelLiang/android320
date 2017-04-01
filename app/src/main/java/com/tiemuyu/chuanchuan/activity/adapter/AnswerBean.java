package com.tiemuyu.chuanchuan.activity.adapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/1.
 */

public class AnswerBean {

	/**
	 * Code : 200
	 * Data : {"attachcontentcolor":[],"content":"9030","attachcontent":"点击\u201c我的\u201d-\u201c我的晒图\u201d-\u201c查看定制\u201d，并下拉页面，可以在\u201c定制费用明细\u201d中看到价格明细。","isneedColor":2}
	 * Msg :
	 */

	private int Code;
	private DataBean Data;
	private String Msg;

	public static AnswerBean objectFromData(String str) {

		return new Gson().fromJson(str, AnswerBean.class);
	}

	public static AnswerBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), AnswerBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<AnswerBean> arrayAnswerBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<AnswerBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<AnswerBean> arrayAnswerBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<AnswerBean>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getString(str), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList();


	}

	public int getCode() {
		return Code;
	}

	public void setCode(int Code) {
		this.Code = Code;
	}

	public DataBean getData() {
		return Data;
	}

	public void setData(DataBean Data) {
		this.Data = Data;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

	public static class DataBean {
		/**
		 * attachcontentcolor : []
		 * content : 9030
		 * attachcontent : 点击“我的”-“我的晒图”-“查看定制”，并下拉页面，可以在“定制费用明细”中看到价格明细。
		 * isneedColor : 2
		 */

		private String content;
		private String attachcontent;
		private int isneedColor;
		private List<?> attachcontentcolor;

		public static DataBean objectFromData(String str) {

			return new Gson().fromJson(str, DataBean.class);
		}

		public static DataBean objectFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);

				return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		public static List<DataBean> arrayDataBeanFromData(String str) {

			Type listType = new TypeToken<ArrayList<DataBean>>() {
			}.getType();

			return new Gson().fromJson(str, listType);
		}

		public static List<DataBean> arrayDataBeanFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);
				Type listType = new TypeToken<ArrayList<DataBean>>() {
				}.getType();

				return new Gson().fromJson(jsonObject.getString(str), listType);

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return new ArrayList();


		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getAttachcontent() {
			return attachcontent;
		}

		public void setAttachcontent(String attachcontent) {
			this.attachcontent = attachcontent;
		}

		public int getIsneedColor() {
			return isneedColor;
		}

		public void setIsneedColor(int isneedColor) {
			this.isneedColor = isneedColor;
		}

		public List<?> getAttachcontentcolor() {
			return attachcontentcolor;
		}

		public void setAttachcontentcolor(List<?> attachcontentcolor) {
			this.attachcontentcolor = attachcontentcolor;
		}
	}
}
