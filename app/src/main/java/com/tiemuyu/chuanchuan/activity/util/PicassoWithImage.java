package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.R;

/**
 * Created by 梁文硕 on 2017/4/24.
 */

public class PicassoWithImage {
	private static Context mContext;
	private Picasso singleton;
	private static PicassoWithImage mPicassoWithImage;

	public PicassoWithImage(Context mContext) {
		this.mContext = mContext;
		singleton = new Picasso.Builder(mContext).build();
	}

	public static PicassoWithImage getIns(Context mcontext) {
		mContext = mcontext;
		if (mPicassoWithImage == null) {
			synchronized (Picasso.class) {
				if (mPicassoWithImage == null) {
					mPicassoWithImage = new PicassoWithImage(mContext);
				}
			}
		}
		return mPicassoWithImage;
	}


	public void setImage(ImageView mImage, final String url) {
		String promianpic = url;
		if (url.equals("")) {
			promianpic = (String) mImage.getTag();
		}
		String[] split = promianpic.split("\\." + "jpg");
		split[0] += "_100x100";
		final String uso = split[0] + ".jpg";
		final ImageSize mImageSize = new ImageSize(mImage.getWidth(), mImage.getHeight());
		ImageLoader.getInstance().displayImage(promianpic,mImage);
		/*ImageLoader.getInstance().loadImage(uso, mImageSize, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String mS, View mView) {
				mImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_morentupian2));
			}

			@Override
			public void onLoadingFailed(String mS, View mView, FailReason mFailReason) {
				mImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_morentupian2));
			}

			@Override
			public void onLoadingComplete(String mS, View mView, final Bitmap mBitmap) {
				mImage.setImageBitmap(mBitmap);
				ImageLoader.getInstance().loadImage(url, mImageSize, new ImageLoadingListener() {
					@Override
					public void onLoadingStarted(String mS, View mView) {
						mImage.setImageBitmap(mBitmap);
					}

					@Override
					public void onLoadingFailed(String mS, View mView, FailReason mFailReason) {

					}

					@Override
					public void onLoadingComplete(String mS, View mView, Bitmap bitmap) {
						mImage.setImageBitmap(bitmap);
					}

					@Override
					public void onLoadingCancelled(String mS, View mView) {

					}
				});
			}

			@Override
			public void onLoadingCancelled(String mS, View mView) {

			}
		});*/
	}


	public void setImageWithPlaceholder(final ImageView mImage, final String url, final int id) {
		final ImageSize mImageSize = new ImageSize(mImage.getWidth(), mImage.getHeight());
		ImageLoader.getInstance().loadImage(url, mImageSize, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String mS, View mView) {
				if (id != 0) {
					mImage.setImageDrawable(mContext.getResources().getDrawable(id));
				} else {
					mImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_morentupian2));
				}
			}

			@Override
			public void onLoadingFailed(String mS, View mView, FailReason mFailReason) {

			}

			@Override
			public void onLoadingComplete(String mS, View mView, Bitmap mBitmap) {
				mImage.setImageBitmap(mBitmap);
			}

			@Override
			public void onLoadingCancelled(String mS, View mView) {

			}
		});
	}

	public void setImageDrable(final ImageView mImage, final int id) {
		final ImageSize mImageSize = new ImageSize(mImage.getWidth(), mImage.getHeight());
		ImageLoader.getInstance().loadImage("", mImageSize, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String mS, View mView) {
				mImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),id));
			}

			@Override
			public void onLoadingFailed(String mS, View mView, FailReason mFailReason) {
				mImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),id));
			}

			@Override
			public void onLoadingComplete(String mS, View mView, Bitmap mBitmap) {
				mImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),id));
			}

			@Override
			public void onLoadingCancelled(String mS, View mView) {
				mImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),id));
			}
		});
	}

}
