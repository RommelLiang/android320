package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.R;

/**
 * Created by 梁文硕 on 2017/5/10.
 */

public class PicassoImageBase {
	private Context mContext;
	private static Picasso singleton;

	public PicassoImageBase(Context mContext) {
		this.mContext = mContext;
	}
	public static PicassoImageBase getIns(Context mContext){
		if (singleton == null) {
			synchronized (Picasso.class) {
				if (singleton == null) {
					singleton = new Picasso.Builder(mContext).build();
				}
			}
		}
		return new PicassoImageBase(mContext);
	}

	public void setImage(final ImageView mImage,String url) {
		singleton.with(mContext)
				.load(url)
				.transform(transformation)
				.placeholder(R.drawable.icon_morentupian2)
				.into(mImage);
	}

	public void setImageWithouRise(final ImageView mImage,String url) {
		singleton.with(mContext)
				.load(url)
				.placeholder(R.drawable.icon_morentupian2)
				.into(mImage);
	}

	public void setImageWithouPlac(final ImageView mImage,String url,int is) {
		singleton.with(mContext)
				.load(url)
				.placeholder(is)
				.into(mImage);
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
