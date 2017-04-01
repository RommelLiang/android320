package com.tiemuyu.chuanchuan.activity.chat_tools.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.CustomerKey;
import com.tiemuyu.chuanchuan.activity.chat_tools.utils.HistoryMessage;
import com.tiemuyu.chuanchuan.activity.chat_tools.utils.IViewHolder;
import com.tiemuyu.chuanchuan.activity.chat_tools.utils.MessageShowUtil;
import com.tiemuyu.chuanchuan.activity.chat_tools.view.CircleImageView;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.inter.MessageClick;

import java.util.List;

/**
 * describe :
 * Created by FUYING on 2016/6/14.
 * PackageName com.guanjia800.clientApp.chat_tools.adapter.
 * ProjectName HouseKeeper_android.
 */
public class MessageAdapter extends BaseAdapter {

	private List<IMMessage> data;
	private Context context;
	private LayoutInflater inflater;
	private IViewHolder viewHolder;
	private MessageShowUtil messageShowUtil;
	private HistoryMessage historyMessage;
	private View[] views;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private CustomerKey mKeFuBean;
	MessageClick mMessageClick;

	public MessageAdapter(List data, Context context, CustomerKey mKeFuBean, MessageClick mMessageClick) {
		this.data = data;
		this.context = context;
		this.mKeFuBean = mKeFuBean;
		inflater = LayoutInflater.from(context);
		viewHolder = new IViewHolder();
		views = new View[] {viewHolder.ly_message_content_left, viewHolder.ly_message_content_right,
				viewHolder.ly_message_audio_left, viewHolder.ly_message_audio_right, viewHolder.ly_message_image_left,
				viewHolder.ly_message_image_right, viewHolder.ly_message_video_left, viewHolder.ly_message_video_right,
				viewHolder.cc_message_left_rl
		};
		messageShowUtil = new MessageShowUtil(context, mMessageClick);
		historyMessage = new HistoryMessage();
		for ( int i = 0; i < data.size(); i++ ) {
			Log.e("data", "MessageAdapter: " + this.data.get(i).getContent() + this.data.get(i).getMsgType().getValue());
		}
		this.mMessageClick = mMessageClick;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		int type = data.get(position).getMsgType().getValue();
		if (data.get(position).getDirect().getValue() == 0) {
			if (type == 1) {
				return 11;
			} else if (type == 2) {
				return 12;
			} else if (type == 3) {
				return 13;
			} else return 10;
		} else {
			if (type == 1) {
				return 21;
			} else if (type == 2) {
				return 22;
			} else if (type == 3) {
				return 23;
			} else if (type == 100) {
				return 25;
			}
			return 20;
		}
	}

	private void setOnclick(final View[] views, final int position) {
		for ( int i = 0; i < views.length; i++ ) {
			if (i % 2 == 0) {
				views[i].setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onClickMessageItemListener.onClickMessageItem(null, data.get(position), 1);
					}
				});
			} else {
				views[i].setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onClickMessageItemListener.onClickMessageItem(null, data.get(position), 2);
					}
				});
			}
		}
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		int showType = getItemViewType(position);
		convertView = inflater.inflate(R.layout.message_item, null);
		viewHolder.ly_left = (RelativeLayout) convertView.findViewById(R.id.ly_left);
		viewHolder.ly_right = (RelativeLayout) convertView.findViewById(R.id.ly_right);
		views[0] = convertView.findViewById(R.id.ly_message_content_left);
		views[1] = convertView.findViewById(R.id.ly_message_content_right);
		views[2] = convertView.findViewById(R.id.ly_message_audio_left);
		views[3] = convertView.findViewById(R.id.ly_message_audio_right);
		views[4] = convertView.findViewById(R.id.ly_message_image_left);
		views[5] = convertView.findViewById(R.id.ly_message_image_right);
		views[6] = convertView.findViewById(R.id.ly_message_video_left);
		views[7] = convertView.findViewById(R.id.ly_message_video_right);
		views[8] = convertView.findViewById(R.id.cc_message_left_rl);
		viewHolder.message_audio_time_right = (TextView) convertView.findViewById(R.id.message_audio_time_right);
		viewHolder.message_audio_time_left = (TextView) convertView.findViewById(R.id.message_audio_time_left);
		viewHolder.message_content_right = (TextView) convertView.findViewById(R.id.message_content_right);
		viewHolder.message_content_left = (TextView) convertView.findViewById(R.id.message_content_left);
		viewHolder.message_portrait_right = (CircleImageView) convertView.findViewById(R.id.message_portrait_right);
		viewHolder.message_portrait_left = (CircleImageView) convertView.findViewById(R.id.message_portrait_left);
		viewHolder.message_item_image_left = (ImageView) convertView.findViewById(R.id.message_item_image_left);
		viewHolder.message_item_image_right = (ImageView) convertView.findViewById(R.id.message_item_image_right);
		viewHolder.message_item_video_left = (ImageView) convertView.findViewById(R.id.message_item_video_left);
		viewHolder.message_item_video_right = (ImageView) convertView.findViewById(R.id.message_item_video_right);
		viewHolder.message_audio_left = (ImageView) convertView.findViewById(R.id.message_audio_left);
		viewHolder.message_audio_right = (ImageView) convertView.findViewById(R.id.message_audio_right);
		viewHolder.cc_message_left_tv = (TextView) convertView.findViewById(R.id.cc_message_left_tv);
		viewHolder.cc_message_left_lv = (ListView) convertView.findViewById(R.id.cc_message_left_lv);

		convertView.setTag(viewHolder);
		setOnclick(views, position);
		messageShowUtil.setCheckMessageShowType(viewHolder, showType, data.get(position), views, mKeFuBean);

		imageLoader.displayImage("http://f1.myappcc.com/zfs/7E1/1061/MUH/061123626277CABHQWMGPS.jpg", viewHolder.message_portrait_left);
		if (!"".equals(MineFragment.user.getUserImg()))
			imageLoader.displayImage(MineFragment.user.getUserImg(), viewHolder.message_portrait_right);

		return convertView;
	}

	private OnClickMessageItemListener onClickMessageItemListener;

	public void setOnClickMessageItemListener(OnClickMessageItemListener onClickMessageItemListener) {
		this.onClickMessageItemListener = onClickMessageItemListener;
	}

	public interface OnClickMessageItemListener {
		void onClickMessageItem(ImageView view, IMMessage imMessage, int d);
	}

}
