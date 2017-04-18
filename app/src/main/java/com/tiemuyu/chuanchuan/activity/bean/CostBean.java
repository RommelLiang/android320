package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/14.
 */

public class CostBean {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : {"CostHtml":"\n                                <div style=\"width:100%;text-align:left;font-weight:600;height:40px;line-height:40px;margin-left:16px\">面辅料<\/div>\n                                <table style=\"border-collapse:collapse;text-align:center;width:100%\">\n                                    <tbody><tr style=\"background-color:#f0f0f0\">\n                                        <td>项目<\/td>\n                                        <td>基本用料<\/td>\n                                        <td>款式设计加料<\/td>\n                                    <\/tr>\n                                    <tr>\n                                        <td>类别<\/td>\n                                        <td><span id=\"jibenleibie\">长款<\/span><\/td>\n                                        <td><span id=\"jialiaoleibie\">单层<\/span><\/td>\n                                    <\/tr>\n                                    <tr style=\"background-color:#f0f0f0\">\n                                        <td>面料材质<\/td>\n                                        <td><span id=\"mianliaocaizhi\">梭织棉<\/span><\/td>\n                                        <td><span id=\"mianliaocaizhi1\">梭织棉<\/span><\/td>\n                                    <\/tr>\n                                    <tr>\n                                        <td>面料等级<\/td>\n                                        <td><span id=\"mianliaodengji\">高级<\/span><\/td>\n                                        <td><span id=\"mianliaodengji1\">高级<\/span><\/td>\n                                    <\/tr>\n                                    <tr style=\"background-color:#f0f0f0\">\n                                        <td>单价<\/td>\n                                        <td><span id=\"jibendanjia\">75<\/span><\/td>\n                                        <td><span id=\"jibendanjia1\">75<\/span><\/td>\n                                    <\/tr>\n                                    <tr>\n                                        <td>数量<\/td>\n                                        <td><span id=\"jibenyongliang\">1.5<\/span><\/td>\n                                        <td><span id=\"jialiaoyongliang\">0<\/span><\/td>\n                                    <\/tr>\n                                    <tr style=\"background-color:#f0f0f0\">\n                                        <td>小计<\/td>\n                                        <td><span id=\"jibenxiaoji\">112.5<\/span><\/td>\n                                        <td><span id=\"jialiaoxiaoji\">0<\/span><\/td>\n                                    <\/tr>\n                                <\/tbody><\/table>\n                                <div style=\"float:right;height:40px;line-height:40px;margin-right:16px\">面料价格合计:￥ <span id=\"mianliaoheji\">112.5<\/span> <\/div>\n                                <br>\n                                <div style=\"width:100%;text-align:left;font-weight:600;height:40px;line-height:40px;margin-left:16px\">工艺<\/div>\n                                <table style=\"border-top:1px #000000 solid;text-align:center;width:100%;\">\n                                    <tbody><tr style=\"height:40px\">\n                                        <td>加工费<\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\">基本加工<\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><span id=\"jibenjiagongfei\">140<\/span><\/td>\n                                    <\/tr>\n                                    <tr style=\"height:40px\">\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\">款式工艺<\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><span id=\"kuanshigongyi\">5<\/span><\/td>\n                                    <\/tr>\n                                    <tr style=\"height:40px\">\n                                        <td style=\"border-bottom:1px solid #f0f0f0\">后处理工艺<\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><span id=\"houchulis\">洗水,酵素洗<\/span><\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><span id=\"houchulijiage\">100<\/span><\/td>\n                                    <\/tr>\n                                    <tr style=\"height:40px\">\n                                        <td style=\"border-bottom:1px solid #f0f0f0\">管理费用分摊<\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><\/td>\n                                        <td style=\"border-bottom:1px solid #f0f0f0\"><span id=\"guanlifei\">71.5<\/span><\/td>\n                                    <\/tr>\n                                <\/tbody><\/table>\n                                <div style=\"float:right;color:#581332;height:40px;line-height:40px;font-size:16px;margin-right:16px\">量身定制：￥ <span id=\"jiage\">429<\/span> <\/div>\n                            "}
	 */

	private int Code;
	private String Msg;
	private DataBean Data;

	public static CostBean objectFromData(String str) {

		return new Gson().fromJson(str, CostBean.class);
	}

	public static CostBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), CostBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<CostBean> arrayCostBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<CostBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<CostBean> arrayCostBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<CostBean>>() {
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
		 * CostHtml :
		 <div style="width:100%;text-align:left;font-weight:600;height:40px;line-height:40px;margin-left:16px">面辅料</div>
		 <table style="border-collapse:collapse;text-align:center;width:100%">
		 <tbody><tr style="background-color:#f0f0f0">
		 <td>项目</td>
		 <td>基本用料</td>
		 <td>款式设计加料</td>
		 </tr>
		 <tr>
		 <td>类别</td>
		 <td><span id="jibenleibie">长款</span></td>
		 <td><span id="jialiaoleibie">单层</span></td>
		 </tr>
		 <tr style="background-color:#f0f0f0">
		 <td>面料材质</td>
		 <td><span id="mianliaocaizhi">梭织棉</span></td>
		 <td><span id="mianliaocaizhi1">梭织棉</span></td>
		 </tr>
		 <tr>
		 <td>面料等级</td>
		 <td><span id="mianliaodengji">高级</span></td>
		 <td><span id="mianliaodengji1">高级</span></td>
		 </tr>
		 <tr style="background-color:#f0f0f0">
		 <td>单价</td>
		 <td><span id="jibendanjia">75</span></td>
		 <td><span id="jibendanjia1">75</span></td>
		 </tr>
		 <tr>
		 <td>数量</td>
		 <td><span id="jibenyongliang">1.5</span></td>
		 <td><span id="jialiaoyongliang">0</span></td>
		 </tr>
		 <tr style="background-color:#f0f0f0">
		 <td>小计</td>
		 <td><span id="jibenxiaoji">112.5</span></td>
		 <td><span id="jialiaoxiaoji">0</span></td>
		 </tr>
		 </tbody></table>
		 <div style="float:right;height:40px;line-height:40px;margin-right:16px">面料价格合计:￥ <span id="mianliaoheji">112.5</span> </div>
		 <br>
		 <div style="width:100%;text-align:left;font-weight:600;height:40px;line-height:40px;margin-left:16px">工艺</div>
		 <table style="border-top:1px #000000 solid;text-align:center;width:100%;">
		 <tbody><tr style="height:40px">
		 <td>加工费</td>
		 <td style="border-bottom:1px solid #f0f0f0">基本加工</td>
		 <td style="border-bottom:1px solid #f0f0f0"><span id="jibenjiagongfei">140</span></td>
		 </tr>
		 <tr style="height:40px">
		 <td style="border-bottom:1px solid #f0f0f0"></td>
		 <td style="border-bottom:1px solid #f0f0f0">款式工艺</td>
		 <td style="border-bottom:1px solid #f0f0f0"><span id="kuanshigongyi">5</span></td>
		 </tr>
		 <tr style="height:40px">
		 <td style="border-bottom:1px solid #f0f0f0">后处理工艺</td>
		 <td style="border-bottom:1px solid #f0f0f0"><span id="houchulis">洗水,酵素洗</span></td>
		 <td style="border-bottom:1px solid #f0f0f0"><span id="houchulijiage">100</span></td>
		 </tr>
		 <tr style="height:40px">
		 <td style="border-bottom:1px solid #f0f0f0">管理费用分摊</td>
		 <td style="border-bottom:1px solid #f0f0f0"></td>
		 <td style="border-bottom:1px solid #f0f0f0"><span id="guanlifei">71.5</span></td>
		 </tr>
		 </tbody></table>
		 <div style="float:right;color:#581332;height:40px;line-height:40px;font-size:16px;margin-right:16px">量身定制：￥ <span id="jiage">429</span> </div>

		 */

		private String CostHtml;

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

		public String getCostHtml() {
			return CostHtml;
		}

		public void setCostHtml(String CostHtml) {
			this.CostHtml = CostHtml;
		}
	}
}
