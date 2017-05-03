package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.ChengpinDetailActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.MoreBean;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;

/**
 * Created by 梁文硕 on 2017/4/26.
 */

public class MoreAdapter extends BaseAdapter {
	private Context mContext;
	private MoreBean mMoreBean;
	private ChildHolder childHolder;
	private ImageView imageView;
	private PicassoWithImage mPicassoWithImage;

	public MoreAdapter(Context mContext, MoreBean mMoreBean) {
		this.mContext = mContext;
		this.mMoreBean = mMoreBean;
		mPicassoWithImage = new PicassoWithImage(mContext);
	}

	@Override
	public int getCount() {
		return mMoreBean.getData().get(0).getAppdingzhilist().size();
	}

	@Override
	public Object getItem(int position) {
		return mMoreBean.getData().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			childHolder = new ChildHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_more, parent, false);
			childHolder.imageView = (ImageView) convertView.findViewById(R.id.im_image);
			childHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
			childHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
			childHolder.rl_item_more = (RelativeLayout) convertView.findViewById(R.id.rl_item_more);
			imageView = childHolder.imageView;
			convertView.setTag(childHolder);

		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}
		String promianpic = mMoreBean.getData().get(0).getAppdingzhilist().get(position).getPromianpic();
		childHolder.name.setText(mMoreBean.getData().get(0).getAppdingzhilist().get(position).getProname());
		childHolder.price.setText(Html.fromHtml("定制价" +
				"<font color=\"#450525\"><b>￥ " + mMoreBean.getData().get(0).getAppdingzhilist().get(position).getPrice() + "</b></font>"));
		mPicassoWithImage.setImage(childHolder.imageView, promianpic);
		childHolder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, ChengpinDetailActivity.class);
				intent.putExtra("productid", mMoreBean.getData().get(0).getAppdingzhilist().get(position).getId());
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

	private static class ChildHolder {
		ImageView imageView;
		TextView name;
		TextView price;
		RelativeLayout rl_item_more;
	}
}
