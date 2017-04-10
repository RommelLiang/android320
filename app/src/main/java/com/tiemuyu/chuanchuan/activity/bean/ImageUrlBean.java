package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/10.
 */

public class ImageUrlBean {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : {"ImageUrl":"http://f1.myappcc.com/zfs/7E1/1100/MUH/100120552559CABHRGZNUU.jpg,http://f1.myappcc.com/zfs/7E1/1100/MUH/100120552590CABHYGMFXL.jpg,http://f1.myappcc.com/zfs/7E1/1100/MUH/100120552606CABHTKGNVK.jpg"}
	 */

	private int Code;
	private String Msg;
	private DataBean Data;

	public static ImageUrlBean objectFromData(String str) {

		return new Gson().fromJson(str, ImageUrlBean.class);
	}

	public static ImageUrlBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), ImageUrlBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<ImageUrlBean> arrayImageUrlBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<ImageUrlBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<ImageUrlBean> arrayImageUrlBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<ImageUrlBean>>() {
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

	public DataBean getData() {
		return Data;
	}

	public void setData(DataBean Data) {
		this.Data = Data;
	}

	public static class DataBean {
		/**
		 * ImageUrl : http://f1.myappcc.com/zfs/7E1/1100/MUH/100120552559CABHRGZNUU.jpg,http://f1.myappcc.com/zfs/7E1/1100/MUH/100120552590CABHYGMFXL.jpg,http://f1.myappcc.com/zfs/7E1/1100/MUH/100120552606CABHTKGNVK.jpg
		 */

		private String ImageUrl;

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

		public String getImageUrl() {
			return ImageUrl;
		}

		public void setImageUrl(String ImageUrl) {
			this.ImageUrl = ImageUrl;
		}
	}
}
