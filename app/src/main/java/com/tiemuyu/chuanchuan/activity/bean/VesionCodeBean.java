package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/11.
 */

public class VesionCodeBean {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : 4.0.1
	 */

	private int Code;
	private String Msg;
	private String Data;

	public static VesionCodeBean objectFromData(String str) {

		return new Gson().fromJson(str, VesionCodeBean.class);
	}

	public static VesionCodeBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), VesionCodeBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<VesionCodeBean> arrayVesionCodeBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<VesionCodeBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<VesionCodeBean> arrayVesionCodeBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<VesionCodeBean>>() {
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

	public String getData() {
		return Data;
	}

	public void setData(String Data) {
		this.Data = Data;
	}
}
