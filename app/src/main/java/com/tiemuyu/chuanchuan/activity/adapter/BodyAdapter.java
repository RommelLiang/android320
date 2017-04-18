package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.MyBody;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BodysBean;
import com.tiemuyu.chuanchuan.activity.inter.DeleteClick;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/17.
 */

public class BodyAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater layoutInflater;
	private DeleteClick mDeleteClick;
	private List<BodysBean.DataBean.UserCCInfoListBean> mUserCCInfoList;

	public BodyAdapter(Context mContext,DeleteClick mDeleteClick,
	                   List<BodysBean.DataBean.UserCCInfoListBean> mUserCCInfoList) {
		this.mContext = mContext;
		this.mUserCCInfoList = mUserCCInfoList;
		layoutInflater = LayoutInflater.from(mContext);
		this.mDeleteClick = mDeleteClick;
	}

	@Override
	public int getCount() {
		return mUserCCInfoList.size();
	}

	@Override
	public Object getItem(int position) {
		return mUserCCInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convetView, ViewGroup parent) {
		View view = convetView;
		ViewHolder holder;
		if (convetView == null) {
			holder = new ViewHolder();
			view = layoutInflater.inflate(R.layout.body_item, parent, false);
			holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
			holder.tv_gender = (TextView) view.findViewById(R.id.tv_gender);
			holder.tv_age = (TextView) view.findViewById(R.id.tv_age);
			holder.tv_weiht = (TextView) view.findViewById(R.id.tv_weiht);
			holder.tv_height = (TextView) view.findViewById(R.id.tv_height);
			holder.tv_edit = (TextView) view.findViewById(R.id.tv_edit);
			holder.tv_delete = (TextView) view.findViewById(R.id.tv_delete);
			holder.im_select = (ImageView) view.findViewById(R.id.im_select);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.tv_name.setText(mUserCCInfoList.get(position).getName());
		if (mUserCCInfoList.get(position).getGENDER() == 1) {
			holder.tv_gender.setText("男");
		} else {
			holder.tv_gender.setText("女");
		}
		holder.tv_age.setText(mUserCCInfoList.get(position).getAGE()+"岁");
		holder.tv_weiht.setText(mUserCCInfoList.get(position).getWEIGHT()+"kg");
		holder.tv_height.setText(mUserCCInfoList.get(position).getHEIGHT()+"cm");
		if (position == 0){
			holder.tv_delete.setVisibility(View.GONE);
			holder.tv_edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, MyBody.class);
					//旧版接口
					intent.putExtra("type",0);
					mContext.startActivity(intent);
				}
			});
		} else {
			holder.tv_delete.setVisibility(View.VISIBLE);
			holder.tv_delete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mDeleteClick.onDelete(position);
				}
			});
			holder.tv_edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, MyBody.class);
					//旧版接口
					intent.putExtra("type",1);
					intent.putExtra("id",mUserCCInfoList.get(position).getID());
					mContext.startActivity(intent);
				}
			});
		}

		return view;
	}
	private class ViewHolder {
		public TextView tv_name;
		public TextView tv_gender;
		public TextView tv_age;
		public TextView tv_weiht;
		public TextView tv_height;
		public TextView tv_edit;
		public TextView tv_delete;
		public ImageView im_select;
	}
}
