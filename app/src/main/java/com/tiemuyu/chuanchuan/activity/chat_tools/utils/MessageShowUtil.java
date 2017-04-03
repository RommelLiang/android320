package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.tiemuyu.chuanchuan.activity.ClothesDetailsActivity;
import com.tiemuyu.chuanchuan.activity.adapter.MessageAdapter;
import com.tiemuyu.chuanchuan.activity.bean.CustomerKey;
import com.tiemuyu.chuanchuan.activity.inter.MessageClick;

import java.util.ArrayList;
import java.util.List;

public class MessageShowUtil {
	private IViewHolder viewHolder;
	private Context mContext;
	private MessageClick mMessageClick;
	private String proid;

	public MessageShowUtil(Context context, MessageClick mMessageClick) {
		this.mContext = context;
		this.mMessageClick = mMessageClick;
	}

	public void setCheckMessageShowType(IViewHolder viewHolder, int type, IMMessage imMessage, View[] views, CustomerKey mKeFuBean) {
		this.viewHolder = viewHolder;
		Log.e("type:" + type, "setCheckMessageShowType: ");
		switch (type / 10) {
			case 1:
				viewHolder.ly_left.setVisibility(View.GONE);
				viewHolder.ly_right.setVisibility(View.VISIBLE);
				break;
			case 2:
				viewHolder.ly_right.setVisibility(View.GONE);
				viewHolder.ly_left.setVisibility(View.VISIBLE);
				break;
		}
		switch (type) {
			case 10:
				checkShow(views[1], views[3], views[5], views[7]);
				final String content = imMessage.getContent();
				viewHolder.message_content_right.setText(content);
				break;
			case 11:
				checkShow(views[5], views[1], views[3], views[7]);
				SaveObjectUtil.setImage(mContext, viewHolder, imMessage, 1);
				break;
			case 12:
				checkShow(views[3], views[1], views[5], views[7]);
				long time = ((AudioAttachment) imMessage.getAttachment()).getDuration();
				String dd = TimeUtil.secToTime((int) time / 1000, "''");
				viewHolder.message_audio_time_right.setText(dd);

				break;
			case 13:
				checkShow(views[7], views[3], views[5], views[1]);
				SaveObjectUtil.setVideo(mContext, viewHolder, imMessage, 1);

				break;
			case 20:
				checkShow(views[0], views[2], views[4], views[6]);
				String content1 = imMessage.getContent();
				if (content1.startsWith("CCDATA")) {
					int split = 0;
					String substring = content1;
					if (content1.contains("|")) {
						split = content1.indexOf("|");
						substring = content1.substring(0, split);
						proid = content1.substring(split + 1, content1.length());
					}

					Log.e("content1: ", content1);
					List<CustomerKey.DataBeanX> data = mKeFuBean.getData();
					List<String> colors = new ArrayList<>();
					for ( int i1 = 0; i1 < data.size(); i1++ ) {
						Log.e("CheckMessageShowType: ", data.get(i1).getMsg());
						if (substring.equals(data.get(i1).getMsg())) {
							Log.e("CheckMessageShowType: ", "waht");
							final List<String> string = new ArrayList<>();
							List<CustomerKey.DataBeanX.DataBean.AttachcontentcolorBean>
									attachcontentcolor = data.get(i1).getData().getAttachcontentcolor();
							String attachconten = data.get(i1).getData().getAttachcontent().replace("n", "n");
							if (attachcontentcolor.size() != 0) {
								for ( int i2 = 0; i2 < attachcontentcolor.size() + 1; i2++ ) {
									if (i2 == 0) {
										string.add(attachconten.substring(0, attachcontentcolor.get(0).getStartpos()).replace("n", "\n"));
									} else {
										string.add(
												attachconten.substring(attachcontentcolor.get(i2 - 1).getStartpos()
														, attachcontentcolor.get(i2 - 1).getLength()
																+ attachcontentcolor.get(i2 - 1).getStartpos()));
										colors.add(data.get(i1).getData().getAttachcontentcolor().get(i2 - 1).getColor());
									}
								}
							} else {
								string.add(attachconten);
							}
							checkShow(views[8]);
							viewHolder.cc_message_left_tv.setText(string.get(0));

							if (split != 0) {
								viewHolder.cc_message_left_tv.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										Intent intent2 = new Intent(mContext, ClothesDetailsActivity.class);
										intent2.putExtra("productid", Integer.parseInt(proid));
										mContext.startActivity(intent2);
									}
								});
							}
							string.remove(0);
							for ( int i2 = 0; i2 < string.size(); i2++ ) {
								Log.e("CheckMessageShowType: ", string.get(i2));
							}
							MessageAdapter adapter = new MessageAdapter(string, mContext, colors);
							viewHolder.cc_message_left_lv.setAdapter(adapter);
							setListViewHeightBasedOnChildren(viewHolder.cc_message_left_lv);
							viewHolder.cc_message_left_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
								@Override
								public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
									mMessageClick.getAnsewr(string.get(position));
								}
							});
							viewHolder.message_content_left.setText("");
							break;
						}
					}
				} else {
					views[8].setVisibility(View.GONE);
					viewHolder.message_content_left.setText(content1);
				}

				break;
			case 21:
				checkShow(views[4], views[2], views[0], views[6]);
				SaveObjectUtil.setImage(mContext, viewHolder, imMessage, 2);

				break;
			case 22:
				checkShow(views[2], views[0], views[4], views[6]);
				long time1 = ((AudioAttachment) imMessage.getAttachment()).getDuration();
				String dd1 = TimeUtil.secToTime((int) time1 / 1000, "''");
				viewHolder.message_audio_time_left.setText(dd1);
				break;
			case 23:
				checkShow(views[6], views[2], views[4], views[0]);
				SaveObjectUtil.setVideo(mContext, viewHolder, imMessage, 2);

				break;
			case 25:
				checkShow(views[0], views[2], views[4], views[6]);
				viewHolder.message_content_left.setText("  查看订单  ");

				break;
		}
	}

	private void checkShow(View v, View... views) {
		for ( View view : views ) {
			view.setVisibility(View.GONE);
		}
		v.setVisibility(View.VISIBLE);
	}

	public void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;

		for ( int i = 0; i < listAdapter.getCount(); i++ ) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() + 1));

		((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

		listView.setLayoutParams(params);
	}
}
