package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/30.
 */

public class CustomerKey {

	/**
	 * Code : 200
	 * Data : [{"Code":200,"Data":{"attachcontentcolor":[{"startpos":72,"length":11,"color":"0x571333"},{"startpos":84,"length":11,"color":"0x571333"},{"startpos":96,"length":13,"color":"0x571333"},{"startpos":110,"length":9,"color":"0x571333"},{"startpos":120,"length":9,"color":"0x571333"},{"startpos":130,"length":13,"color":"0x571333"},{"startpos":144,"length":7,"color":"0x571333"}],"content":"9030","attachcontent":"我的穿主，欢迎开启你的定制之旅。偷偷告诉你：在页面顶部的\u201c定制成品\u201d、\u201c裙装\u201d等分类或\u201c发现\u201d频道，有最新最热款哦！\n\n点击以下问题获得回复：\n1.穿穿APP怎么用？\n2.定制流程是怎样的？\n3.衣服能做到一模一样吗？\n4.如何确认面料？\n5.如何试穿样衣？\n6.需要提供哪些身材数据？\n7.能退款吗？","isneedColor":1},"Msg":"CCDATA[QA_KnowCC]"},{"Code":200,"Data":{"attachcontentcolor":[{"startpos":51,"length":12,"color":"0x571333"},{"startpos":64,"length":11,"color":"0x571333"},{"startpos":76,"length":16,"color":"0x571333"},{"startpos":93,"length":9,"color":"0x571333"},{"startpos":103,"length":9,"color":"0x571333"},{"startpos":113,"length":11,"color":"0x571333"}],"content":"9030","attachcontent":"亲爱的穿主，你的服装核价已出。点击\u201c我的\u201d-\u201c我的晒图\u201d即可\u201c查看定制\u201d。\n\n点击以下问题获得回复：\n1.价格是怎么核算出的？\n2.如何查看价格明细？\n3.如何查看面料材质、面料等级？\n4.如何获得优惠？\n5.如何定制付款？\n6.定制流程是怎样的？","isneedColor":1},"Msg":"CCDATA[QA_KnowBuy]"},{"Code":200,"Data":{"attachcontentcolor":[{"startpos":54,"length":5,"color":"0x571333"}],"content":"9030","attachcontent":"穿主，你的定制已启动，即将在5-7个工作日内完成样衣制作，请添加你的定制助理个人微信哦！\n*请确保已经提供了所有细节图用于制版，6小时后添加新的细节图片或更改细节需支付50-150元改版费用。","isneedColor":1},"Msg":"CCDATA[ORDER_PAYSUCCESS]"},{"Code":200,"Data":{"attachcontentcolor":[],"content":"9030","attachcontent":"穿主，你的衣服面料已完成了初步匹配，请及时与定制助理确认面料哦！","isneedColor":2},"Msg":"CCDATA[ORDER_MATERIALCOMPLETED]"},{"Code":200,"Data":{"attachcontentcolor":[{"startpos":22,"length":6,"color":"0x571333"}],"content":"9030","attachcontent":"穿主，你的面料小样及样衣已寄出，请注意查收并填写试穿反馈，反馈提交后方可开始成衣制作哦！","isneedColor":1},"Msg":"CCDATA[ORDER_SPLSEND]"},{"Code":200,"Data":{"attachcontentcolor":[],"content":"9030","attachcontent":"穿主，你的样衣试穿反馈已提交，我们将于5-7个工作日左右完成成衣制作，如有任何问题请联系你的定制助理。","isneedColor":2},"Msg":"CCDATA[ORDER_SPLRECIVE]"},{"Code":200,"Data":{"attachcontentcolor":[{"startpos":36,"length":9,"color":"0x571333"}],"content":"9030","attachcontent":"穿主，你定制的衣服已乘着顺丰向你飞奔而去，物流信息可以在订单详情中查看。定制助理的悄悄话：每一次全新的时装定制，都是对您和穿穿的创造力和观察力的综合考验，定制也是一项精度要求极高的产品，非常感谢您能给予穿穿这份信任，与我们一起定制你想要的衣服~！收货后，如果您对款式、尺码方面仍有修改需求，仍可与我联系，我们将竭力为您解决哦！","isneedColor":1},"Msg":"CCDATA[ORDER_SLOPWORKSEND]"},{"Code":200,"Data":{"attachcontentcolor":[],"content":"9030","attachcontent":"穿主，你的修改衣已寄出，请注意查收哦！收货后，如果您对款式、尺码方面仍有修改需求，还是可与我联系，我们将竭力为您解决哦！","isneedColor":2},"Msg":"CCDATA[ORDER_REVISESEND]"}]
	 * Msg : 8
	 */

	private int Code;
	private String Msg;
	private List<DataBeanX> Data;

	public static CustomerKey objectFromData(String str) {

		return new Gson().fromJson(str, CustomerKey.class);
	}

	public static CustomerKey objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), CustomerKey.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<CustomerKey> arrayCustomerKeyFromData(String str) {

		Type listType = new TypeToken<ArrayList<CustomerKey>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<CustomerKey> arrayCustomerKeyFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<CustomerKey>>() {
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

	public List<DataBeanX> getData() {
		return Data;
	}

	public void setData(List<DataBeanX> Data) {
		this.Data = Data;
	}

	public static class DataBeanX {
		/**
		 * Code : 200
		 * Data : {"attachcontentcolor":[{"startpos":72,"length":11,"color":"0x571333"},{"startpos":84,"length":11,"color":"0x571333"},{"startpos":96,"length":13,"color":"0x571333"},{"startpos":110,"length":9,"color":"0x571333"},{"startpos":120,"length":9,"color":"0x571333"},{"startpos":130,"length":13,"color":"0x571333"},{"startpos":144,"length":7,"color":"0x571333"}],"content":"9030","attachcontent":"我的穿主，欢迎开启你的定制之旅。偷偷告诉你：在页面顶部的\u201c定制成品\u201d、\u201c裙装\u201d等分类或\u201c发现\u201d频道，有最新最热款哦！\n\n点击以下问题获得回复：\n1.穿穿APP怎么用？\n2.定制流程是怎样的？\n3.衣服能做到一模一样吗？\n4.如何确认面料？\n5.如何试穿样衣？\n6.需要提供哪些身材数据？\n7.能退款吗？","isneedColor":1}
		 * Msg : CCDATA[QA_KnowCC]
		 */

		private int Code;
		private DataBean Data;
		private String Msg;

		public static DataBeanX objectFromData(String str) {

			return new Gson().fromJson(str, DataBeanX.class);
		}

		public static DataBeanX objectFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);

				return new Gson().fromJson(jsonObject.getString(str), DataBeanX.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		public static List<DataBeanX> arrayDataBeanXFromData(String str) {

			Type listType = new TypeToken<ArrayList<DataBeanX>>() {
			}.getType();

			return new Gson().fromJson(str, listType);
		}

		public static List<DataBeanX> arrayDataBeanXFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);
				Type listType = new TypeToken<ArrayList<DataBeanX>>() {
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
			 * attachcontentcolor : [{"startpos":72,"length":11,"color":"0x571333"},{"startpos":84,"length":11,"color":"0x571333"},{"startpos":96,"length":13,"color":"0x571333"},{"startpos":110,"length":9,"color":"0x571333"},{"startpos":120,"length":9,"color":"0x571333"},{"startpos":130,"length":13,"color":"0x571333"},{"startpos":144,"length":7,"color":"0x571333"}]
			 * content : 9030
			 * attachcontent : 我的穿主，欢迎开启你的定制之旅。偷偷告诉你：在页面顶部的“定制成品”、“裙装”等分类或“发现”频道，有最新最热款哦！

			 点击以下问题获得回复：
			 1.穿穿APP怎么用？
			 2.定制流程是怎样的？
			 3.衣服能做到一模一样吗？
			 4.如何确认面料？
			 5.如何试穿样衣？
			 6.需要提供哪些身材数据？
			 7.能退款吗？
			 * isneedColor : 1
			 */

			private String content;
			private String attachcontent;
			private int isneedColor;
			private List<AttachcontentcolorBean> attachcontentcolor;

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

			public List<AttachcontentcolorBean> getAttachcontentcolor() {
				return attachcontentcolor;
			}

			public void setAttachcontentcolor(List<AttachcontentcolorBean> attachcontentcolor) {
				this.attachcontentcolor = attachcontentcolor;
			}

			public static class AttachcontentcolorBean {
				/**
				 * startpos : 72
				 * length : 11
				 * color : 0x571333
				 */

				private int startpos;
				private int length;
				private String color;

				public static AttachcontentcolorBean objectFromData(String str) {

					return new Gson().fromJson(str, AttachcontentcolorBean.class);
				}

				public static AttachcontentcolorBean objectFromData(String str, String key) {

					try {
						JSONObject jsonObject = new JSONObject(str);

						return new Gson().fromJson(jsonObject.getString(str), AttachcontentcolorBean.class);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					return null;
				}

				public static List<AttachcontentcolorBean> arrayAttachcontentcolorBeanFromData(String str) {

					Type listType = new TypeToken<ArrayList<AttachcontentcolorBean>>() {
					}.getType();

					return new Gson().fromJson(str, listType);
				}

				public static List<AttachcontentcolorBean> arrayAttachcontentcolorBeanFromData(String str, String key) {

					try {
						JSONObject jsonObject = new JSONObject(str);
						Type listType = new TypeToken<ArrayList<AttachcontentcolorBean>>() {
						}.getType();

						return new Gson().fromJson(jsonObject.getString(str), listType);

					} catch (JSONException e) {
						e.printStackTrace();
					}

					return new ArrayList();


				}

				public int getStartpos() {
					return startpos;
				}

				public void setStartpos(int startpos) {
					this.startpos = startpos;
				}

				public int getLength() {
					return length;
				}

				public void setLength(int length) {
					this.length = length;
				}

				public String getColor() {
					return color;
				}

				public void setColor(String color) {
					this.color = color;
				}
			}
		}
	}
}
