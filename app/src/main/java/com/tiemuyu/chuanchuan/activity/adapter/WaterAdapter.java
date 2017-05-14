package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.DingzhiDetailsActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.LastPrice;
import com.tiemuyu.chuanchuan.activity.util.JudgmentLegal;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;
import com.tiemuyu.chuanchuan.activity.view.CircleImageView;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/17.
 */

public class WaterAdapter extends BaseAdapter {
	private final List<LastPrice.DataBean.RowsBean> rows;
	private Context context;
	private LayoutInflater layoutInflater;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private ImageView imageView;
	private PicassoWithImage mPicassoWithImage;

	private class ViewHolder {
		public TextView text_one;
		public TextView tv_user_name_left, tv_user_name_right;
		public CircleImageView im_user_left, im_user_right;
		public ImageView image_one;
		public TextView price_one;
		public TextView text;
		public ImageView image;
		public TextView price;
		public LinearLayout left;
		public LinearLayout right;
		public LinearLayout ll_left;
		public LinearLayout ll_right;
	}

	public WaterAdapter(List<LastPrice.DataBean.RowsBean> rows,
	                    Context context) {
		this.rows = rows;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.icon_morentupian2)
				.showImageForEmptyUri(R.drawable.icon_morentupian2)
				.showImageOnFail(R.drawable.icon_morentupian2)
				.cacheInMemory()
				.cacheOnDisc()
				.displayer(new RoundedBitmapDisplayer(20))
				.build();
		mPicassoWithImage = PicassoWithImage.getIns(context);
	}

	@Override
	public int getCount() {
		return rows.size() / 2;
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
			view = layoutInflater.inflate(R.layout.zhuan_ti_wather, parent, false);
			holder.text = (TextView) view.findViewById(R.id.new_price_pro_name);
			holder.price = (TextView) view.findViewById(R.id.new_price_txt);
			holder.tv_user_name_left = (TextView) view.findViewById(R.id.tv_user_name_left);
			holder.tv_user_name_right = (TextView) view.findViewById(R.id.tv_user_name_right);
			holder.image = (ImageView) view.findViewById(R.id.new_price_pic);
			holder.text_one = (TextView) view.findViewById(R.id.new_price_pro_name_one);
			holder.price_one = (TextView) view.findViewById(R.id.new_price_txt_one);
			holder.image_one = (ImageView) view.findViewById(R.id.new_price_pic_one);
			holder.left = (LinearLayout) view.findViewById(R.id.listview_item_left);
			holder.right = (LinearLayout) view.findViewById(R.id.listview_item_right);
			holder.im_user_left = (CircleImageView) view.findViewById(R.id.im_user_left);
			holder.im_user_right = (CircleImageView) view.findViewById(R.id.im_user_right);
			holder.ll_left = (LinearLayout) view.findViewById(R.id.ll_left);
			holder.ll_right = (LinearLayout) view.findViewById(R.id.ll_right);
			imageView = holder.image_one;
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.text.setText(JudgmentLegal.removeYear(rows.get((position + 1) * 2 - 1).getProductName()));
		holder.price.setText("￥" + rows.get((position + 1) * 2 - 1).getPrice() + "");
		mPicassoWithImage.setImage(holder.image,rows.get((position + 1) * 2 - 1).getMainImage());
		/*Picasso.with(context)
				.load(rows.get((position + 1) * 2 - 1).getMainImage())
				.transform(transformation)
				.into(holder.image);*/
		holder.text_one.setText(JudgmentLegal.removeYear(rows.get((position + 1) * 2 - 2).getProductName()));
		holder.price_one.setText("￥" + rows.get((position + 1) * 2 - 2).getPrice() + "");
		mPicassoWithImage.setImage(holder.image_one,rows.get((position + 1) * 2 - 2).getMainImage());
		/*Picasso.with(context)
				.load(rows.get((position + 1) * 2 - 2).getMainImage())
				.transform(transformation)
				.into(holder.image_one);*/
		holder.left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, DingzhiDetailsActivity.class);
				intent.putExtra("productid", rows.get((position + 1) * 2 - 2).getId());
				context.startActivity(intent);
			}
		});
		holder.right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, DingzhiDetailsActivity.class);
				intent.putExtra("productid", rows.get((position + 1) * 2 - 1).getId());
				context.startActivity(intent);
			}
		});
		holder.ll_left.setVisibility(View.VISIBLE);
		holder.ll_right.setVisibility(View.VISIBLE);
		holder.tv_user_name_left.setText(rows.get((position + 1) * 2 - 2).getPublishUser().getNickName());
		String userImg_let = rows.get((position + 1) * 2 - 2).getPublishUser().getUserImg();
		if (userImg_let==null || userImg_let.equals("")) {
			userImg_let = "http://a1.myappcc.com/images/ccdefault/user_img.jpg";
		}
		Picasso.with(context)
				.load(userImg_let)
				.transform(transformation)
				.into(holder.im_user_left);
		holder.tv_user_name_right.setText(rows.get((position + 1) * 2 - 1).getPublishUser().getNickName());
		Log.e("用户头像", rows.get((position + 1) * 2 - 1).getPublishUser().getUserImg()+":"+position);
		String userImg = rows.get((position + 1) * 2 - 1).getPublishUser().getUserImg();
		if (userImg==null || userImg.equals("")) {
			userImg = "http://a1.myappcc.com/images/ccdefault/user_img.jpg";
		}
		Picasso.with(context)
				.load(userImg)
				.transform(transformation)
				.into(holder.im_user_right);

		return view;
	}

	Transformation transformation = new Transformation() {

		@Override
		public Bitmap transform(Bitmap source) {

			int mTargetWidth = imageView.getWidth();
			int mTargetHeight = imageView.getHeight();


			if (source.getWidth() == 0) {
				return source;
			}
			if (source.getWidth() < source.getHeight()) {
				double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
				int targetHeight = (int) (mTargetWidth * aspectRatio);
				if (targetHeight != 0 && mTargetWidth != 0) {
					Bitmap result = Bitmap.createScaledBitmap(source, mTargetWidth, targetHeight, false);
					if (result != source) {
						// Same bitmap is returned if sizes are the same
						source.recycle();
					}
					return result;
				} else {
					return source;
				}
			} else {
				double aspectRatio = (double) source.getWidth() / (double) source.getHeight();
				int targetWidth = (int) (mTargetHeight * aspectRatio);
				if (targetWidth != 0 && targetWidth != 0) {
					Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, mTargetHeight, false);
					if (result != source) {
						// Same bitmap is returned if sizes are the same
						source.recycle();
					}
					return result;
				} else {
					return source;
				}
			}
		}

		@Override
		public String key() {
			return "transformation" + " desiredWidth";
		}
	};
}
