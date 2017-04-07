package com.tiemuyu.chuanchuan.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.OrderActivity;
import com.tiemuyu.chuanchuan.activity.PaySelect;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.TuiKuanActivity;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.OrdInfo;
import com.tiemuyu.chuanchuan.activity.bean.Order;
import com.tiemuyu.chuanchuan.activity.bean.OrderBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.chat_tools.activity.MessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.inter.NetResponses;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁文硕 on 2017/2/27.
 */

public class OrderAdapter extends BaseAdapter implements NetResponses, ThreadPoolTaskHttp.HttpCallBack {

	private IDataChanger idatachange;
	private ArrayList<Contacts> dataList = new ArrayList<>();
	public static String OWN_HEADER_URL = "";//gao 在这里更改有效
	public String tag = "MESSAGE";
	private DataSharedPress sharedPress;
	private boolean isLogIn = false;
	private List<OrderBean.DataBean.RowsBean> rowsAll;
	private OrderBean.DataBean.RowsBean rowsBen;
	private Context context;
	private LayoutInflater layoutInflater;
	private OrdInfo ordInfo;
	private int p;

	public OrderAdapter(List<OrderBean.DataBean.RowsBean> rowsAll, Context context, IDataChanger idatachange) {
		this.rowsAll = rowsAll;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		setNetResponses(this);
		this.idatachange = idatachange;
	}

	@Override
	public int getCount() {
		return rowsAll.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			view = layoutInflater.inflate(R.layout.order_detial_layout, parent, false);
			holder.tv_order_number = (TextView) view.findViewById(R.id.order_number);
			holder.tv_order_status = (TextView) view.findViewById(R.id.order_status);
			holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
			holder.tv_miaoshu = (TextView) view.findViewById(R.id.tv_miaoshu);
			holder.tv_conut = (TextView) view.findViewById(R.id.tv_conut);
			holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
			holder.tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
			holder.image_one = (ImageView) view.findViewById(R.id.iv_order_image);
			holder.btn_one = (Button) view.findViewById(R.id.btn_one);
			holder.btn_two = (Button) view.findViewById(R.id.btn_two);
			holder.btn_three = (Button) view.findViewById(R.id.btn_three);
			holder.ll_detaili = (LinearLayout) view.findViewById(R.id.ll_detail);
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.tv_order_number.setText(rowsAll.get(position).getOrderNo());
		holder.tv_order_status.setText(rowsAll.get(position).getStatusClientName());
		holder.tv_price.setText("￥" + subZeroAndDot(rowsAll.get(position).getItems().get(0).getPrice()) + "");
		holder.tv_miaoshu.setText(rowsAll.get(position).getOrderSubject());
		holder.tv_conut.setText("x" + rowsAll.get(position).getTotalNum());
		holder.tv_time.setText("创建时间：" + rowsAll.get(position).getOrderTime());
		holder.ll_detaili.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				rowsBen = rowsAll.get(position);
				Order order = new Order();
				Log.e("!!!!!", "onClick: " + rowsBen.toString());
				order.setProId(rowsBen.getItems().get(0).getOrderId() + "");
				order.setCoin(rowsBen.getCoin() + "");
				order.setCount(rowsBen.getTotalNum() + "");
				order.setDetail(rowsBen.getOrderSubject());
				order.setName(rowsBen.getOrderAddress().getContact());
				order.setLocation(rowsBen.getOrderAddress().getPrivonce() +
						rowsBen.getOrderAddress().getAddress() +
						rowsBen.getOrderAddress().getDistrict() +
						rowsBen.getOrderAddress().getAddress());
				order.setPrice(rowsBen.getItems().get(0).getPrice() + "");
				order.setTotal(rowsBen.getActualFee());
				order.setTime(rowsBen.getCreatorDate());
				order.setImage(rowsBen.getItems().get(0).getMainImage());
				order.setStatus(rowsBen.getStatus());
				order.setEx(rowsBen.getOrderExpress());
				Intent intent = new Intent(context, OrderActivity.class);
				intent.putExtra("order", order);
				context.startActivity(intent);
			}
		});
		if (rowsAll.get(position).getCoin() != 0) {
			holder.tv_total_price.setText(
					Html.fromHtml(
							"合计:" + "<font color=\"#450525\"><b>￥" +
									subZeroAndDot(rowsAll.get(position).getActualFee()) + "</b></font>" + " " +
									"穿币抵扣:" + "<font color=\"#450525\"><b>￥" +
									rowsAll.get(position).getCoin() + "</b></font>"
					)
			);
		} else {
			holder.tv_total_price.setText(
					Html.fromHtml(
							"合计:" + "<font color=\"#450525\"><b>￥" +
									subZeroAndDot(rowsAll.get(position).getActualFee()) + "</b></font>"
					)
			);
		}
		Picasso.with(context)
				.load(rowsAll.get(position).getItems().get(0).getMainImage())
				.resize(100, 100).into(holder.image_one);
		Log.e("adapter", "getView: nmb:" + rowsAll.get(position).getStatus());
		OrderBean.DataBean.RowsBean rowsBean = rowsAll.get(position);
		Log.e("getView:", rowsBean.getId()+":"+rowsAll.get(position).getStatus());
		holder.btn_one.setVisibility(View.VISIBLE);
		holder.btn_two.setVisibility(View.VISIBLE);
		holder.btn_three.setVisibility(View.VISIBLE);
		if (rowsBean.getStatus() == 11) {
			holder.btn_one.setText("付款");
			holder.btn_one.setTextSize(18);
			holder.btn_two.setText("联系客服");
			holder.btn_two.setTextSize(18);
			holder.btn_three.setText("取消订单");
			holder.btn_three.setTextSize(18);
		} else if (rowsBean.getStatus() == 12) {
			if (rowsBean.getIsAllowRefund() == 1) {
				holder.btn_one.setText("申请退款");
				holder.btn_one.setTextSize(18);
				holder.btn_two.setText("联系客服");
				holder.btn_two.setTextSize(18);
				holder.btn_three.setVisibility(View.GONE);
			} else {
				holder.btn_one.setText("联系客服");
				holder.btn_one.setTextSize(18);
				holder.btn_two.setVisibility(View.GONE);
				holder.btn_three.setVisibility(View.GONE);
			}
		} else if (rowsBean.getStatus() == 21) {
			if (rowsBean.getIsAllowRefund() == 1) {
				holder.btn_one.setText("确认收货");
				holder.btn_two.setText("联系客服");
				holder.btn_two.setTextSize(18);
				holder.btn_three.setText("退款退货");
				holder.btn_three.setTextSize(18);
			} else {
				holder.btn_one.setText("确认收货");
				holder.btn_one.setTextSize(18);
				holder.btn_two.setText("联系客服");
				holder.btn_two.setTextSize(18);
				holder.btn_three.setVisibility(View.GONE);
			}
		} else if (rowsBean.getStatus() == 31) {
			holder.btn_one.setText("查看退款");
			holder.btn_one.setTextSize(18);
			holder.btn_two.setText("联系客服");
			holder.btn_two.setTextSize(18);
			holder.btn_three.setVisibility(View.GONE);
		} else if (rowsBean.getStatus() == 92) {
			holder.btn_one.setText("删除");
			holder.btn_one.setTextSize(18);
			holder.btn_two.setVisibility(View.GONE);
			holder.btn_three.setVisibility(View.GONE);
		} else {
			holder.btn_one.setText("联系客服");
			holder.btn_one.setTextSize(18);
			holder.btn_two.setVisibility(View.GONE);
			holder.btn_three.setVisibility(View.GONE);
		}

		holder.btn_one.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				p = position;
				click((Button) v, position);
			}
		});
		holder.btn_three.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				p = position;
				click((Button) v, position);
			}
		});
		holder.btn_two.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				p = position;
				click((Button) v, position);
			}
		});
		return view;
	}


	private void click(Button v, int po) {
		Button s = v;
		String text = String.valueOf(s.getText());
		if (text.equals("联系客服")) {
			kefu();
		} else if (text.equals("删除")) {
			delectOrder(rowsAll.get(po).getId());
		} else if (text.equals("查看退款")) {
			Intent intent = new Intent(context, TuiKuanActivity.class);
			intent.putExtra("order", rowsAll.get(po));
			context.startActivity(intent);
		} else if (text.equals("确认收货")) {
			confirmReceive(rowsAll.get(po).getId());
		} else if (text.equals("申请退款")) {
			refund(rowsAll.get(po).getId());
		} else if (text.equals("付款")) {
			ordInfo = new OrdInfo();
			ordInfo.setTotalNum(rowsAll.get(po).getTotalNum() + "");
			ordInfo.setTotalFee((rowsAll.get(po).getTotalFee() + ""));
			ordInfo.setActualFee(rowsAll.get(po).getActualFee() + "");
			String msg = String.valueOf("");
			ordInfo.setDiscountedPrice(msg);
			ordInfo.setRegApp("android");
			Intent intent = new Intent(context, PaySelect.class);
			intent.putExtra("ordInfo", ordInfo);
			intent.putExtra("productid", rowsAll.get(po).getProductId() + "");
			intent.putExtra("AddressId", rowsAll.get(po).getOrderAddress().getPkValue());
			context.startActivity(intent);
		} else if (text.equals("取消订单")) {
			cancelOrder(rowsAll.get(po).getId());
		}
	}

	@Override
	public void success(int type, JSONObject jsonObject) {
		Contacts contacts;
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

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
		Log.e("OrderAdapter", "successCallBack: " + callBackMsg);
		if (resultTag.equals(TAG_delectOrder)) {
			Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
			idatachange.dtaChange(p);
		}
		if (resultTag.equals(TAG_cancelOrder)) {
			Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show();
			idatachange.dtaChange(p);
		}
		if (resultTag.equals(TAG_confirmReceive)) {
			Toast.makeText(context, "确认成功", Toast.LENGTH_SHORT).show();
			//idatachange.dtaChange(p);
		}
		if (resultTag.equals(TAG_refund)) {
			Toast.makeText(context, "申请成功", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
		Log.e("order:" + resultTag, "failCallBack: " + arg0.getLocalizedMessage());
	}

	@Override
	public void startCallBack(String resultTag, boolean isShowDiolog, String showTitle) {

	}

	@Override
	public void cancelCallBack(String resultTag) {

	}

	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {

	}

	@Override
	public void reLoginCallBack(String resultTag, boolean isShowDiolog) {

	}

	private class ViewHolder {
		public TextView tv_order_number,
				tv_order_status, tv_price, tv_miaoshu, tv_conut,
				tv_time, tv_total_price;
		public ImageView image_one;
		public Button btn_one, btn_two, btn_three;
		public LinearLayout ll_detaili;
	}

	private void kefu() {
		if (!PreferenceUtils.getPrefBoolean(context, Constant.CC_IFLOGIN, false)) {
			Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
			return;
		}
		if (isLogIn) {
			Intent intent = new Intent(context, MessageActivity.class);
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("data", dataList);
			intent.putExtra("bundle", bundle);
			context.startActivity(intent);
		} else {
			login();
		}
	}

	public void login() {
		//LoginInfo loginInfo = new LoginInfo("tmy1", "68c58f02597daa4fdc3ab86ed103e0c6");//测试账号
		if (PreferenceUtils.getPrefBoolean(context, Constant.CC_IFLOGIN, false) == false) {
			return;
		}
		User now = DBTools.getUser();
		LoginInfo loginInfo = new LoginInfo(now.getAccid(), now.getAccToken());
//        System.out.println("accid after new LoginInfo: " + accid);
//        System.out.println("acctoken after new LoginInfo: " + token);
		String getinfo = now.getUserImg();
		///sp.getString("userimag", "");
		//获取用户的im头像 gao
		OWN_HEADER_URL = getinfo;
		RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {

			@Override
			public void onSuccess(LoginInfo loginInfo) {
				isLogIn = true;
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
		((MyApplication) ((Activity) context).getApplication()).getRequestQueue().add(jsonRequest);
	}

	private NetResponses netResponses;

	public void setNetResponses(NetResponses netResponses) {
		this.netResponses = netResponses;
	}

	private String TAG_delectOrder = "TAG_delectOrder";
	private String TAG_cancelOrder = "TAG_cancelOrder";
	private String TAG_confirmReceive = "TAG_confirmReceive";
	private String TAG_refund = "TAG_refund";

	private void delectOrder(int id) {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(context,
						TAG_delectOrder,
						Constant.REQUEST_POST,
						ParamsTools.deleteord(id + ""),
						this,
						"删除订单",
						false));
	}

	private void cancelOrder(int id) {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(context,
						TAG_cancelOrder,
						Constant.REQUEST_POST,
						ParamsTools.cancelOrder(id + ""),
						this,
						"删除订单",
						false));
	}

	private void confirmReceive(int id) {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(context,
						TAG_confirmReceive,
						Constant.REQUEST_POST,
						ParamsTools.confirmReceive(id + ""),
						this,
						"删除订单",
						false));
	}

	private void refund(int id) {
		MyApplication.poolManager.addAsyncTask(
				new ThreadPoolTaskHttp(context,
						TAG_refund,
						Constant.REQUEST_POST,
						ParamsTools.refund(id + ""),
						this,
						"删除订单",
						false));
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 *
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}
}
