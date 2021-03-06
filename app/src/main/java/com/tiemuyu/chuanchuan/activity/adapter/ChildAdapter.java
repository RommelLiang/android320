package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.ChengpinDetailActivity;
import com.tiemuyu.chuanchuan.activity.MoreDingzhiActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.DIngzhi;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;

/**
 * Created by 梁文硕 on 2017/2/24.
 */

public class ChildAdapter extends BaseAdapter {
	private final int width;
	private final int height;
	private DIngzhi.DataBean listBean;
	private Context context;
	private ChildHolder childHolder;
	private ImageView imageView;
	private PicassoWithImage mPicassoWithImage;

	public ChildAdapter(DIngzhi.DataBean listBean, Context context) {
		this.listBean = listBean;
		this.context = context;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
		mPicassoWithImage = PicassoWithImage.getIns(context);
	}

	@Override
	public int getCount() {
		if (listBean.getAppdingzhilist().size() < 6) {
			return listBean.getAppdingzhilist().size();
		} else {
			return 6;
		}
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
		if (convertView == null) {
			childHolder = new ChildHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false);
			childHolder.imageView = (ImageView) convertView.findViewById(R.id.im_image);
			childHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
			childHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
			childHolder.rl_item = (RelativeLayout) convertView.findViewById(R.id.rl_item);
			imageView = childHolder.imageView;
			convertView.setTag(childHolder);

		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}
		Log.e("ChildHolder", "getView: " + position);

		if (position == 5) {
			childHolder.imageView.setPadding(80, 80, 80, 80);
			childHolder.imageView.setImageBitmap(null);
			childHolder.imageView.setImageBitmap(null);
			Drawable adddrawable = context.getResources().getDrawable(R.drawable.image_null);
			childHolder.rl_item.setBackground(adddrawable);
			Picasso.with(context)
					.load(R.drawable.more)
					.placeholder(R.drawable.more)
					.error(R.drawable.more)
					.into(childHolder.imageView);
			childHolder.name.setText("");
			childHolder.price.setText("");
		}
		if (position < 5) {
			childHolder.imageView.setPadding(1, 1, 1, 1);
			Drawable adddrawable = context.getResources().getDrawable(R.drawable.image);
			childHolder.rl_item.setBackground(adddrawable);
			String promianpic = listBean.getAppdingzhilist().get(position).getPromianpic();
			childHolder.imageView.setTag(promianpic);
			ImageLoader.getInstance().displayImage((String) childHolder.imageView.getTag(),childHolder.imageView);
			childHolder.name.setText(listBean.getAppdingzhilist().get(position).getProname());
			childHolder.price.setText(Html.fromHtml("定制价" +
					"<font color=\"#450525\"><b>￥ " + listBean.getAppdingzhilist().get(position).getPrice() + "</b></font>"));
		}
		childHolder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (position < 5) {
					Intent intent = new Intent(context, ChengpinDetailActivity.class);
					intent.putExtra("productid", listBean.getAppdingzhilist().get(position).getId());
					context.startActivity(intent);
				} else {
					Intent intent = new Intent(context, MoreDingzhiActivity.class);
					intent.putExtra("id", listBean.getId());
					context.startActivity(intent);
				}
			}
		});
		return convertView;
	}


	private static class ChildHolder {
		ImageView imageView;
		TextView name;
		TextView price;
		RelativeLayout rl_item;
	}
}
