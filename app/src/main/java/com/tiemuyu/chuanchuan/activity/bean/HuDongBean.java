package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/21.
 */

public class HuDongBean {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : [{"id":5,"miaoshu":"测试","url":"https://www.baidu.com/","bigimg":"http://f1.myappcc.com/zfs/7E1/1108/KOG/108101900418CABHXHSFCN.jpg","StartTime":"2017-04-17 00:00:00","EndTime":"2017-04-19 00:00:00"}]
	 */

	private int Code;
	private String Msg;
	private List<DataBean> Data;

	public static HuDongBean objectFromData(String str) {

		return new Gson().fromJson(str, HuDongBean.class);
	}

	public static HuDongBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), HuDongBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<HuDongBean> arrayHuDongBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<HuDongBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<HuDongBean> arrayHuDongBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<HuDongBean>>() {
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

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

	public List<DataBean> getData() {
		return Data;
	}

	public void setData(List<DataBean> Data) {
		this.Data = Data;
	}

	public static class DataBean {
		/**
		 * id : 5
		 * miaoshu : 测试
		 * url : https://www.baidu.com/
		 * bigimg : http://f1.myappcc.com/zfs/7E1/1108/KOG/108101900418CABHXHSFCN.jpg
		 * StartTime : 2017-04-17 00:00:00
		 * EndTime : 2017-04-19 00:00:00
		 */

		private int id;
		private String miaoshu;
		private String url;
		private String bigimg;
		private String StartTime;
		private String EndTime;

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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMiaoshu() {
			return miaoshu;
		}

		public void setMiaoshu(String miaoshu) {
			this.miaoshu = miaoshu;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getBigimg() {
			return bigimg;
		}

		public void setBigimg(String bigimg) {
			this.bigimg = bigimg;
		}

		public String getStartTime() {
			return StartTime;
		}

		public void setStartTime(String StartTime) {
			this.StartTime = StartTime;
		}

		public String getEndTime() {
			return EndTime;
		}

		public void setEndTime(String EndTime) {
			this.EndTime = EndTime;
		}
	}
}
