package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/13.
 */

public class SimilarProducts {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : {"SimilarProducts":[{"ProductId":70994,"ProductName":"缩水/固色高级聚酯","QuanMing":"女装2017年缩水/固色高级聚酯纤维-春夏连体裤裤子","Price":747,"MianPic":"http://f1.myappcc.com/zfs/7E1/1096/RIC/096174710574CABHCUVXAF.jpg"},{"ProductId":69900,"ProductName":"高级聚酯纤维-春夏","QuanMing":"女装2017年高级聚酯纤维-春夏连体裤裤子","Price":637,"MianPic":"http://f1.myappcc.com/zfs/7E1/1090/NHP/090140024239CABHDLYWGG.jpg"},{"ProductId":71566,"ProductName":"高级聚酯纤维-春夏","QuanMing":"女装2017年高级聚酯纤维-春夏连体裤裤子","Price":573,"MianPic":"http://f1.myappcc.com/zfs/7E1/1100/IBN/100090747747CABHNRDHEK.jpg"},{"ProductId":72073,"ProductName":"酵洗压褶高级聚酯纤","QuanMing":"女装2017年酵洗压褶高级聚酯纤维-春夏连体裤裤子","Price":698,"MianPic":"http://f1.myappcc.com/zfs/7E1/1103/MUH/103130817203CABHFYRBKF.jpg"},{"ProductId":71369,"ProductName":"面料数码印花(聚酯","QuanMing":"女装2017年面料数码印花(聚酯纤维)高级聚酯纤维-春夏连体裤裤子","Price":898,"MianPic":"http://f1.myappcc.com/zfs/7E1/1098/XXY/098234847643CABHQUPMVB.jpg"},{"ProductId":71574,"ProductName":"高级聚酯纤维-春夏","QuanMing":"女装2017年高级聚酯纤维-春夏连体裤裤子","Price":597,"MianPic":"http://f1.myappcc.com/zfs/7E1/1100/AZA/100092700950CABHNCKRSW.jpg"}]}
	 */

	private int Code;
	private String Msg;
	private DataBean Data;

	public static SimilarProducts objectFromData(String str) {

		return new Gson().fromJson(str, SimilarProducts.class);
	}

	public static SimilarProducts objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), SimilarProducts.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<SimilarProducts> arraySimilarProductsFromData(String str) {

		Type listType = new TypeToken<ArrayList<SimilarProducts>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<SimilarProducts> arraySimilarProductsFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<SimilarProducts>>() {
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
		private List<SimilarProductsBean> SimilarProducts;

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

		public List<SimilarProductsBean> getSimilarProducts() {
			return SimilarProducts;
		}

		public void setSimilarProducts(List<SimilarProductsBean> SimilarProducts) {
			this.SimilarProducts = SimilarProducts;
		}

		public static class SimilarProductsBean {
			/**
			 * ProductId : 70994
			 * ProductName : 缩水/固色高级聚酯
			 * QuanMing : 女装2017年缩水/固色高级聚酯纤维-春夏连体裤裤子
			 * Price : 747.0
			 * MianPic : http://f1.myappcc.com/zfs/7E1/1096/RIC/096174710574CABHCUVXAF.jpg
			 */

			private int ProductId;
			private String ProductName;
			private String QuanMing;
			private double Price;
			private String MianPic;

			public static SimilarProductsBean objectFromData(String str) {

				return new Gson().fromJson(str, SimilarProductsBean.class);
			}

			public static SimilarProductsBean objectFromData(String str, String key) {

				try {
					JSONObject jsonObject = new JSONObject(str);

					return new Gson().fromJson(jsonObject.getString(str), SimilarProductsBean.class);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			public static List<SimilarProductsBean> arraySimilarProductsBeanFromData(String str) {

				Type listType = new TypeToken<ArrayList<SimilarProductsBean>>() {
				}.getType();

				return new Gson().fromJson(str, listType);
			}

			public static List<SimilarProductsBean> arraySimilarProductsBeanFromData(String str, String key) {

				try {
					JSONObject jsonObject = new JSONObject(str);
					Type listType = new TypeToken<ArrayList<SimilarProductsBean>>() {
					}.getType();

					return new Gson().fromJson(jsonObject.getString(str), listType);

				} catch (JSONException e) {
					e.printStackTrace();
				}

				return new ArrayList();


			}

			public int getProductId() {
				return ProductId;
			}

			public void setProductId(int ProductId) {
				this.ProductId = ProductId;
			}

			public String getProductName() {
				return ProductName;
			}

			public void setProductName(String ProductName) {
				this.ProductName = ProductName;
			}

			public String getQuanMing() {
				return QuanMing;
			}

			public void setQuanMing(String QuanMing) {
				this.QuanMing = QuanMing;
			}

			public double getPrice() {
				return Price;
			}

			public void setPrice(double Price) {
				this.Price = Price;
			}

			public String getMianPic() {
				return MianPic;
			}

			public void setMianPic(String MianPic) {
				this.MianPic = MianPic;
			}
		}
	}
}
