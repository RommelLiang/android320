package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/31.
 */

public class MessageAdapter extends BaseAdapter {
	private List<String> mStrings;
	private Context mContext;
	private List<String> colors;

	public MessageAdapter(List<String> mStrings,Context mContext,List<String> colors) {
		this.mStrings = mStrings;
		this.mContext = mContext;
		this.colors = colors;
	}

	@Override
	public int getCount() {
		return mStrings.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			childHolder = new ChildHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.message_adapter_layout, parent, false);
			childHolder.message = (TextView) convertView.findViewById(R.id.tv_message);
			convertView.setTag(childHolder);
		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}
		childHolder.message.setText(mStrings.get(position));
		String replace;
		if (colors.get(position)!=null && !colors.get(position).equals("")) {
			replace = colors.get(position).replace("0x", "#");
		} else {
			replace = colors.get(0).replace("0x","#");
		}
		Log.e("getView: ", replace);
		int i = Color.parseColor(replace);
		Log.e("getView: ", i+"");
		childHolder.message.setTextColor(i);
		return convertView;
	}

	private ChildHolder childHolder;
	private static class ChildHolder {
		TextView message;
	}

}
