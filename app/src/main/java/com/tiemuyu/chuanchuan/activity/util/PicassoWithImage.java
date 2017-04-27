package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.R;

/**
 * Created by 梁文硕 on 2017/4/24.
 */

public class PicassoWithImage {
	private Context mContext;
	private Picasso singleton;

	public PicassoWithImage(Context mContext) {
		this.mContext = mContext;
	}
	public Picasso getIns(){
		if (singleton == null) {
			synchronized (Picasso.class) {
				if (singleton == null) {
					singleton = new Picasso.Builder(mContext).build();
				}
			}
		}
		return singleton;
	}
	public void setImage(final ImageView mImage, final String url) {
		String promianpic = url;
		String[] split = promianpic.split("\\." + "jpg");
		split[0] += "_100x100";
		final String uso = split[0] + ".jpg";
		getIns().with(mContext)
				.load(uso)
				.transform(transformation)
				.placeholder(R.drawable.icon_morentupian2)
				.into(mImage, new Callback() {
					@Override
					public void onSuccess() {
						getIns().with(mContext).load(url)
								.transform(transformation)
								.noPlaceholder()
								.into(mImage);
					}

					@Override
					public void onError() {

					}
				});
	}

	private int mWidth;
	private int mHeight;
	Transformation transformation = new Transformation() {

		@Override
		public Bitmap transform(Bitmap source) {

			WindowManager wm = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);
			mWidth = wm.getDefaultDisplay().getWidth();
			mHeight = wm.getDefaultDisplay().getHeight();
			int mTargetWidth = mWidth * 3 / 4;
			int mTargetHeight = mHeight * 3 / 4;


			if (source.getWidth() == 0) {
				return source;
			}
			if (source.getWidth() < source.getHeight()) {
				double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
				int targetHeight = (int) (mTargetWidth * aspectRatio);
				if (targetHeight != 0 && mTargetWidth != 0) {
					Bitmap result = Bitmap.createScaledBitmap(source, mTargetWidth, targetHeight, false);
					if (result != source) {
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
