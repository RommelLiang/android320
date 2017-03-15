package com.tiemuyu.chuanchuan.activity.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.tiemuyu.chuanchuan.activity.constant.Constant;

@SuppressLint("NewApi")
public class BitmapUtil {

	/**
	 * 图片圆角化
	 * 
	 * @param map
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomDrawable(Bitmap map, int w, int h) {
		int width = map.getWidth();
		int height = map.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(map, 0, 0, width, height, matrix,
				true);
		newbmp = getRCB(newbmp, w);
		return newbmp;
	}

	/**
	 * 图片圆角化
	 * 
	 * @param map
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomDrawableRoundCocer(Bitmap map, int w, int h, int px) {
		int width = map.getWidth();
		int height = map.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(map, 0, 0, width, height, matrix,
				true);
		newbmp = getRCB(newbmp, px);
		return newbmp;
	}

	public static Bitmap scaleBitmap(Bitmap map, int w, int h) {

		return map = Bitmap.createScaledBitmap(map, w, h, false);
	}

	private static Bitmap getRCB(Bitmap bitmap, float roundPX) {
		Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(dstbmp);
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(0xff424242);
		canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// 画个圆
		paint.setStyle(Paint.Style.STROKE);// 设置空心
		paint.setColor(Color.rgb(130, 183, 53));
		canvas.drawCircle(roundPX / 2, roundPX / 2, roundPX / 2, paint);

		// TODO 暂时先注释
		// if (bitmap != null && !bitmap.isRecycled() && bitmap != dstbmp) {
		// bitmap.recycle();
		// }
		return dstbmp;
	}

	/**
	 * 将bitmap转成Base64编码
	 * 
	 * @param bm
	 * @return
	 */
	@SuppressLint("NewApi")
	public static String bitmaptoBase64(Bitmap bm) {

		if (bm == null) {
			return null;
		}
		String string = null;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bm.compress(CompressFormat.PNG, 100, bStream);
		byte[] bytes = bStream.toByteArray();
		string = Base64.encodeToString(bytes, Base64.DEFAULT);
		return string;
	}

	/**
	 * 将bitmap转成Base64编码
	 * 
	 *  bm
	 * @return
	 */
	@SuppressLint("NewApi")
	public static String bitmaptoBase64(String file) {

		if (file == null) {
			return null;
		}
		Bitmap bmp = BitmapFactory.decodeFile(file);
		String string = null;
		if (bmp != null) {
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			bmp.compress(CompressFormat.PNG, 100, bStream);
			byte[] bytes = bStream.toByteArray();
			string = Base64.encodeToString(bytes, Base64.DEFAULT);
		}

		return string;
	}

	/**
	 * 压缩sdcard图片处理
	 * 
	 *  bitmapByte
	 * @param param
	 * @return
	 */
	public static Bitmap getSampleBitmap(String pathName, BitmapParam param) {
		Bitmap bitmap = null;
		if (pathName != null) {
			if (param == null) {
				bitmap = BitmapFactory.decodeFile(pathName);
			} else {
				BitmapFactory.Options option = new BitmapFactory.Options();
				option.inJustDecodeBounds = true;
				bitmap = BitmapFactory.decodeFile(pathName, option);
				int scaleSize = calculateInSampleSize(option,
						param.getDesWidth(), param.getDesHeight());
				option.inJustDecodeBounds = false;
				option.inSampleSize = scaleSize;
				bitmap = BitmapFactory.decodeFile(pathName, option);
			}
		}
		return bitmap;
	}

	/**
	 * 压缩图片处理
	 * 
	 * @param bitmapByte
	 * @param param
	 * @return
	 */
	public static Bitmap getSampleBitmap(byte[] bitmapByte, BitmapParam param) {
		Bitmap bitmap = null;
		if (bitmapByte != null) {
			if (param == null) {
				bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0,
						bitmapByte.length);
			} else {
				BitmapFactory.Options option = new BitmapFactory.Options();
				option.inJustDecodeBounds = true;
				bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0,
						bitmapByte.length, option);
				int scaleSize = calculateInSampleSize(option,
						param.getDesWidth(), param.getDesHeight());
				option.inJustDecodeBounds = false;
				option.inSampleSize = scaleSize;
				bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0,
						bitmapByte.length, option);
			}
		}
		return bitmap;
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (reqWidth > 0 && reqHeight > 0) {
			if (height > reqHeight || width > reqWidth) {

				final int halfHeight = height / 2;
				final int halfWidth = width / 2;

				// Calculate the largest inSampleSize value that is a power of 2
				// and
				// keeps both
				// height and width larger than the requested height and width.
				while ((halfHeight / inSampleSize) > reqHeight
						&& (halfWidth / inSampleSize) > reqWidth) {
					inSampleSize *= 2;
				}
			}
		}
		return inSampleSize;
	}

	/**
	 * 旋转
	 * 
	 * @param bitmapOrg
	 * @param angle
	 * @return
	 */
	public static Bitmap rotate(Bitmap bitmapOrg, int angle, boolean recycleOrg) {

		// 创建新的图片
		Bitmap resizedBitmap = bitmapOrg;
		try {
			// 获取这个图片的宽和高

			// 创建操作图片用的matrix对象
			Matrix matrix = new Matrix();

			// 旋转图片 动作
			matrix.postRotate(angle);

			resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,
					bitmapOrg.getWidth(), bitmapOrg.getHeight(), matrix, true);
			if (recycleOrg)
				bitmapOrg.recycle();
		} catch (OutOfMemoryError e) {
			resizedBitmap = bitmapOrg;
		}

		return resizedBitmap;
	}

	/**
	 * 旋转并缩放
	 * 
	 * @param bitmapOrg
	 * @param w
	 * @param h
	 * @param angle
	 * @return
	 */
	public static Bitmap resizeAndRotate(Bitmap bitmapOrg, float w, float h,
			int angle, boolean recycleOrg) {

		// 创建新的图片
		Bitmap resizedBitmap = bitmapOrg;
		try {
			// 获取这个图片的宽和高
			int width = bitmapOrg.getWidth();
			int height = bitmapOrg.getHeight();

			// 计算缩放率，新尺寸除原始尺寸
			float scaleWidth = w / (float) width;
			float scaleHeight = h / (float) height;

			// 创建操作图片用的matrix对象
			Matrix matrix = new Matrix();

			// 缩放图片动作
			matrix.postScale(scaleWidth, scaleHeight);

			// 旋转图片 动作
			matrix.postRotate(angle);

			resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height,
					matrix, true);
			if (recycleOrg)
				bitmapOrg.recycle();
		} catch (OutOfMemoryError e) {
			resizedBitmap = bitmapOrg;
		}

		return resizedBitmap;
	}

	/**
	 * 缩放
	 * 
	 * @param bitmapOrg
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap resize(Bitmap bitmapOrg, float w, float h,
			boolean recycleOrg) {
		Bitmap resizedBitmap = bitmapOrg;
		try {
			// 加载需要操作的图片

			// 获取这个图片的宽和高
			int width = bitmapOrg.getWidth();
			int height = bitmapOrg.getHeight();

			// 计算缩放率，新尺寸除原始尺寸
			float scaleWidth = w / (float) width;
			float scaleHeight = h / (float) height;

			// 创建操作图片用的matrix对象
			Matrix matrix = new Matrix();

			// 缩放图片动作
			matrix.postScale(scaleWidth, scaleHeight);

			resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height,
					matrix, true);
			if (recycleOrg)
				bitmapOrg.recycle();
		} catch (OutOfMemoryError e) {
			resizedBitmap = bitmapOrg;
		}

		return resizedBitmap;
	}

	/**
	 * 灰色效果
	 * 
	 * @param drawable
	 */
	public void setColorFilter(Drawable drawable) {
		// Make this drawable mutable.
		// A mutable drawable is guaranteed to not share its state with any
		// other drawable.
		drawable.mutate();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter cf = new ColorMatrixColorFilter(cm);
		drawable.setColorFilter(cf);
	}

	/**
	 * Bitmap 图像圆角化.
	 * 
	 * @param bitmap
	 * @param round
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int round,
			boolean recycleOrg) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		// final int color =Color.RED;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = round;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		if (recycleOrg)
			bitmap.recycle();
		return output;
	}

	/***
	 * 图片分割
	 * 
	 * @param g
	 *            ：画布
	 * @param paint
	 *            ：画笔
	 * @param imgBit
	 *            ：图片
	 * @param x
	 *            ：X轴起点坐标
	 * @param y
	 *            ：Y轴起点坐标
	 * @param w
	 *            ：单一图片的宽度
	 * @param h
	 *            ：单一图片的高度
	 * @param line
	 *            ：第几列
	 * @param row
	 *            ：第几行
	 */
	public final void cuteImage(Canvas g, Paint paint, Bitmap imgBit, int x,
			int y, int w, int h, int line, int row) {
		g.clipRect(x, y, x + w, h + y);
		g.drawBitmap(imgBit, x - line * w, y - row * h, paint);
		g.restore();
	}

	/***
	 * 绘制带有边框的文字
	 * 
	 * @param strMsg
	 *            ：绘制内容
	 * @param g
	 *            ：画布
	 * @param paint
	 *            ：画笔
	 * @param setx
	 *            ：：X轴起始坐标
	 * @param sety
	 *            ：Y轴的起始坐标
	 * @param fg
	 *            ：前景色
	 * @param bg
	 *            ：背景色
	 */
	public void drawText(String strMsg, Canvas g, Paint paint, int setx,
			int sety, int fg, int bg) {
		paint.setColor(bg);
		g.drawText(strMsg, setx + 1, sety, paint);
		g.drawText(strMsg, setx, sety - 1, paint);
		g.drawText(strMsg, setx, sety + 1, paint);
		g.drawText(strMsg, setx - 1, sety, paint);
		paint.setColor(fg);
		g.drawText(strMsg, setx, sety, paint);
		g.restore();
	}

	/**
	 * 将Drawable转化为Bitmap
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;

	}

	/**
	 * 获得带倒影的图片方法
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		return bitmapWithReflection;
	}

	/**
	 * create the bitmap from a byte array 生成水印图片
	 * 
	 * @param src
	 *            the bitmap object you want proecss
	 * @param watermark
	 *            the water mark above the src
	 * @return return a bitmap object ,if paramter's length is 0,return null
	 */
	public static Bitmap createBitmap(Bitmap src, Bitmap watermark) {
		if (src == null) {
			return null;
		}

		int w = src.getWidth();
		int h = src.getHeight();
		int ww = watermark.getWidth();
		int wh = watermark.getHeight();
		// create the new blank bitmap
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(newb);
		// draw src into
		cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
		// draw watermark into
		cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// 在src的右下角画入水印
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// store
		cv.restore();// 存储
		return newb;
	}

	/**
	 * 重新编码Bitmap
	 * 
	 * @param src
	 *            需要重新编码的Bitmap
	 * 
	 * @param format
	 *            编码后的格式（目前只支持png和jpeg这两种格式）
	 * 
	 * @param quality
	 *            重新生成后的bitmap的质量
	 * 
	 * @return 返回重新生成后的bitmap
	 */
	public static Bitmap codec(Bitmap src, CompressFormat format,
			int quality) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		src.compress(format, quality, os);

		byte[] array = os.toByteArray();
		return BitmapFactory.decodeByteArray(array, 0, array.length);
	}

	/**
	 * 把一个View的对象转换成bitmap
	 */
	public static Bitmap getViewBitmap(View v) {

		v.clearFocus();
		v.setPressed(false);

		// 能画缓存就返回false
		boolean willNotCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);
		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);
		if (color != 0) {
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();
		Bitmap cacheBitmap = v.getDrawingCache();
		if (cacheBitmap == null) {
			return null;
		}
		Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
		// Restore the view
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willNotCache);
		v.setDrawingCacheBackgroundColor(color);
		return bitmap;
	}

	public static byte[] toByteArray(Bitmap bitmap, boolean isRecycle) {
		ByteArrayOutputStream bOutStream = null;
		bOutStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, bOutStream);
		if (isRecycle)
			bitmap.recycle();
		return bOutStream.toByteArray();
	}

	public static byte[] compressBitmap(Bitmap b, int maxSize) {
		// BitmapUitl.resize(b, 80, h);
		if (b.getWidth() > maxSize || b.getHeight() > maxSize) {
			if (b.getWidth() > b.getHeight()) {
				b = resize(b, maxSize,
						b.getHeight() * maxSize / (float) b.getWidth(), false);
			} else {
				b = resize(b, b.getWidth() * maxSize / (float) b.getHeight(),
						maxSize, false);
			}

		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		b.compress(CompressFormat.PNG, 100, bout);
		return bout.toByteArray();
	}

	/**
	 * 图片灰度处理
	 * 
	 * @param bmSrc
	 * @return
	 */
	public static Bitmap bitmap2GrayProcess(Bitmap bmSrc) {
		int width, height;
		height = bmSrc.getHeight();
		width = bmSrc.getWidth();
		Bitmap bmpGray = null;
		bmpGray = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas c = new Canvas(bmpGray);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmSrc, 0, 0, paint);
		return bmpGray;
	}

	// 特殊处理
	public static Bitmap DayDayTitleBitmap(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		int x = 11;
		int y = 52;
		int with = 218;
		int height = 136;
		if (with > bitmap.getWidth()) {
			return null;
		}

		if (height > bitmap.getHeight()) {
			return null;
		}
		return Bitmap.createBitmap(bitmap, x, y, with, height, matrix, true);
	}

	// 特殊处理
	public static Bitmap DayDayContentBitmap(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		int x = 7;
		int y = 7;
		int with = 196;
		int height = 129;
		if (with > bitmap.getWidth()) {
			return null;
		}

		if (height > bitmap.getHeight()) {
			return null;
		}
		return Bitmap.createBitmap(bitmap, x, y, with, height, matrix, true);
	}

	/**
	 * 获取适应屏幕大小的图
	 */
	public static Bitmap sacleBitmap(Context context, Bitmap bitmap) {
		// 适配屏幕大小
		if (bitmap == null) {
			return null;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int screenWidth = metrics.widthPixels;
		float aspectRatio = (float) screenWidth / (float) width;
		int scaledHeight = (int) (height * aspectRatio);
		Bitmap scaledBitmap = null;
		try {
			scaledBitmap = Bitmap.createScaledBitmap(bitmap, screenWidth,
					scaledHeight, false);
		} catch (OutOfMemoryError e) {
		}
		return scaledBitmap;
	}

	/**
	 * 圆角图片 add huangwu -2014.9.27
	 * */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 压缩图片 - 质量压缩
	 * */
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			image.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		System.out.println("----图片质量压缩:" + (baos.toByteArray().length / 1024)
				+ "kb");
		return bitmap;
	}

	/**
	 * 压缩图片 - 根据路径获取图片并按比例大小压缩
	 * */
	public static Bitmap getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
		// System.out.println("----88888:"+bitmap);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;

		System.out.println("-----图片缩放比例:" + be);
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		try {
			// System.out.println("----10101010:");
			bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
			// System.out.println("----9999:"+bitmap);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (bitmap == null) {
			return null;
		} else {
			return compressImage(bitmap);
		}

		// return null;
		// System.out.println("----9999:"+bitmap);
		// return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 压缩图片 - 根据路径获取图片并按比例大小压缩
	 * */
	public static Bitmap getimage1(String srcPath, int pixelW, int pixelH) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
		// System.out.println("----88888:"+bitmap);

		newOpts.inJustDecodeBounds = false;
		// 原图宽高
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;

		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		// float hh = 800f;// 这里设置高度为800f
		// float ww = 480f;// 这里设置宽度为480f

		float hh = pixelH;//
		float ww = pixelW;//

		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;

		System.out.println("-----拍照缩放比例:" + be);
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		try {
			// System.out.println("----10101010:");
			bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
			// System.out.println("----9999:"+bitmap);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (bitmap == null) {
			return null;
		} else {
			return compressImage(bitmap);
		}

		// return null;
		// System.out.println("----9999:"+bitmap);
		// return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * @Title: getAdd
	 * @Description: TODO根据原图地址返回压缩后的图片地址
	 * @param @param srcPath
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getImgAddress(String srcPath) {
		String cli_path = null;

		try {
			Bitmap bitmap2 = Bimp.revitionImageSize(srcPath);
			String p = Constant.BASE_IMAGE_NEW + "/"
					+ UUID.randomUUID().toString() + ".jpg";
			cli_path = BitmapUtil.saveMyBitmap(bitmap2, p);// 保存到SD卡

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String p = Constant.BASE_IMAGE_NEW + "/"
		// + UUID.randomUUID().toString() + ".jpg";
		// cli_path = BitmapUtil.saveMyBitmap(getimage(srcPath), p);// 保存到SD卡

		return cli_path;
	}

	/**
	 * @Title: getImgAddress1
	 * @Description: TODO 返回图片地址
	 * @param @param bitmap
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getDownLoadUrl(Bitmap bitmap) {
		String cli_path = null;

		String p = Constant.BASE_IMAGE_NEW + "/" + UUID.randomUUID().toString()
				+ ".jpg";
		cli_path = BitmapUtil.saveMyBitmap(bitmap, p);// 保存到SD卡

		return cli_path;
	}

	/**
	 * 压缩图片 - 图片按比例大小压缩方法（根据Bitmap图片压缩）
	 * */
	public static Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// float hh = 1280f;// 这里设置高度为1280f
		// float ww = 720f;// 这里设置宽度为720f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;

		System.out.println("-----相册图片缩放比例:" + be);
		newOpts.inSampleSize = be;// 设置缩放比例
		newOpts.inPreferredConfig = Config.RGB_565;// 降低图片从ARGB888到RGB565
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	public static Bitmap comp1(Bitmap image, int pixelW, int pixelH) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		// float hh = 800f;// 这里设置高度为800f
		// float ww = 480f;// 这里设置宽度为480f
		float hh = pixelH;// 这里设置高度为1280f
		float ww = pixelW;// 这里设置宽度为720f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;

		System.out.println("-----相册图片缩放比例:" + be);
		newOpts.inSampleSize = be;// 设置缩放比例
		newOpts.inPreferredConfig = Config.RGB_565;// 降低图片从ARGB888到RGB565
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩

	}

	/**
	 * 保存图片
	 * */
	public static String saveMyBitmap(Bitmap mBitmap, String bitName) {
		File f = new File(bitName);
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(CompressFormat.JPEG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitName;
	}

	// /**
	// * 获取原图url
	// *
	// * @param imgKey
	// * @return 获取原图url
	// */
	// public static String getSourceUrl(String imgKey) {
	// if (imgKey == null || "".equals(imgKey)) {
	// return null;
	// }
	//
	// if (imgKey.startsWith("/")) {
	// return URL.BASE_IMG_URL + imgKey;
	// } else {
	// return URL.BASE_IMG_URL + "/" + imgKey;
	// }
	// }

	/************************************************/

	// 计算图片的缩放值
	public static int calculateInSampleSize1(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	// 根据路径获得图片并压缩，返回bitmap用于显示
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	// 根据路径获得图片并压缩，返回bitmap用于显示
	public static Bitmap getSmallBitmap1(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(filePath);
		if (bitmap == null)
			return null;
		BitmapFactory.decodeFile(filePath, options);
		int width = 0;
		int higth = 0;
		LogHelper.d("-----前bitmap:宽:" + bitmap.getWidth() + ",高:"
				+ bitmap.getHeight());
		if (bitmap.getWidth() >= 1280) {
			width = 1280;
			higth = (1280 * bitmap.getHeight() / bitmap.getWidth());
		} else {
			width = bitmap.getWidth();
			higth = bitmap.getHeight();
		}

		LogHelper.d("-----目标bitmap:宽:" + width + ",高:" + higth);
		// Calculate inSampleSize
		// options.inSampleSize = calculateInSampleSize(options, 480, 800);
		options.inSampleSize = calculateInSampleSize(options, width, higth);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		// Bitmap bp=BitmapFactory.decodeFile(filePath, options);
		Bitmap bp = BitmapFactory.decodeFile(filePath, options);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int optionss = 40;
		bp.compress(CompressFormat.JPEG, 100, baos);
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			optionss -= 10;
			bp.compress(CompressFormat.JPEG, optionss, baos);
		}

		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bmp = BitmapFactory.decodeStream(isBm, null, null);

		return bmp;
	}

	// 把bitmap转换成String
	public static String bitmapToString(String filePath) {

		Bitmap bm = getSmallBitmap(filePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.JPEG, 40, baos);
		byte[] b = baos.toByteArray();
		return Base64.encodeToString(b, Base64.DEFAULT);
	}

	/*
	 * 压缩图片，处理某些手机拍照角度旋转的问题
	 */
	public static String compressImage(String filePath, int q)
			throws FileNotFoundException {

		Bitmap bm = getSmallBitmap(filePath);

		int degree = readPictureDegree(filePath);

		if (degree != 0) {// 旋转照片角度
			bm = rotateBitmap(bm, degree);
		}

		// File imageDir = SDCardUtils.getSDCardPath(context);
		//
		// File outputFile=new File(imageDir,fileName);
		File outputFile = new File(filePath);

		FileOutputStream out = new FileOutputStream(outputFile);

		bm.compress(CompressFormat.JPEG, q, out);

		return outputFile.getPath();
	}

	public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
		if (bitmap != null) {
			Matrix m = new Matrix();
			m.postRotate(degress);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), m, true);
			return bitmap;
		}
		return bitmap;
	}

	/****************************/

	// 根据uri获得图片真实地址
	public static String getPath(Uri uri, Context context) {
		String[] proj = { MediaStore.Images.Media.DATA };
		ContentResolver cr = context.getContentResolver();

		Cursor cursor = cr.query(uri, proj, null, null, null);

		cursor.moveToFirst();

		int actual_image_column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		return cursor.getString(actual_image_column_index);

	}

	// byte[]转bitmap
	public static Bitmap Bytes2Bitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}
		return null;
	}

	//
	/**
	 * 从文件中加载图片并压缩成指定大小 先通过BitmapFactory.decodeStream方法，创建出一个bitmap，
	 * 再调用上述方法将其设为ImageView的 source。decodeStream最大的秘密在
	 * 于其直接调用JNI>>nativeDecodeAsset()来完成decode，无需再使用java层的createBitmap，
	 * 从而节省了java层的空间
	 * 
	 * @param pathName
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap decodeSampledBitmapFromFile(String pathName,
			int reqWidth, int reqHeight) {
		// 加载位图
		InputStream is = null;
		try {
			is = new FileInputStream(pathName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		System.out.println("---压缩的比例:" + options.inSampleSize);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		// 为位图设置100K的缓存
		options.inTempStorage = new byte[100 * 1024];
		// 设置位图颜色显示优化方式
		// ALPHA_8：每个像素占用1byte内存（8位）
		// ARGB_4444:每个像素占用2byte内存（16位）
		// ARGB_8888:每个像素占用4byte内存（32位）
		// RGB_565:每个像素占用2byte内存（16位）
		// options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPreferredConfig = Config.ARGB_8888;
		// 设置图片可以被回收，创建Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
		options.inPurgeable = true;
		// 设置解码位图的尺寸信息
		options.inInputShareable = true;
		return BitmapFactory.decodeStream(is, null, options);
	}

	/**
	 * 旋转图片
	 * 
	 * @param angle
	 * @param mBitmap
	 * @return
	 */
	public static Bitmap rotaingImageBitmap(int angle, Bitmap mBitmap) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		Bitmap b = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
				mBitmap.getHeight(), matrix, true);
		if (mBitmap != null && !mBitmap.isRecycled()) {
			mBitmap.recycle();
			mBitmap = null;
		}
		return b;
	}

	public static String getSavePath(Context context, byte[] bts) {
		String path = "";
		Bitmap bitmap = BitmapFactory.decodeByteArray(bts, 0, bts.length);
		Bitmap bm = sacleBitmap(context, bitmap);

		String p = Constant.BASE_IMAGE_NEW + "/" + TimeUtil.getCurrtimename()
				+ ".jpg";
		path = BitmapUtil.saveMyBitmap(bm, p);// 保存到SD卡
		return path;
	}

	/**
	 * 将拍下来的照片存放在SD卡中
	 * 
	 * @param data
	 * @throws IOException
	 */
	public static String saveToSDCard(byte[] data) throws IOException {
		// Date date = new Date();
		// SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); //
		// 格式化时间
		// String filename = format.format(date) + ".jpg";
		// File fileFolder = new File(Environment.getExternalStorageDirectory()
		// + "/finger/");
		// if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
		// fileFolder.mkdir();
		// }

		String p = Constant.BASE_IMAGE_NEW + "/" + TimeUtil.getCurrtimename()
				+ ".jpg";
		File jpgFile = new File(p);
		FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流
		outputStream.write(data); // 写入sd卡中
		outputStream.close(); // 关闭输出流
		return p;
	}

	public static Bitmap comp2(Bitmap image, int pixelW, int pixelH) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, 80, baos);// 这里压缩20%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		System.out.println("-----000000" + w + "," + h);
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		// float hh = 800f;// 这里设置高度为800f
		// float ww = 480f;// 这里设置宽度为480f
		float hh = pixelH;// 这里设置高度为1280f
		float ww = pixelW;// 这里设置宽度为720f
		System.out.println("-----00000011111" + ww + "," + hh);
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放

		if (h > hh && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		}

		if (be <= 0)
			be = 1;

		System.out.println("-----相册图片缩放比例:" + be);
		newOpts.inSampleSize = be;// 设置缩放比例
		newOpts.inPreferredConfig = Config.ARGB_8888;// 降低图片从ARGB888到RGB565
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return bitmap;// 压缩好比例大小后再进行质量压缩

	}

	// 翻转图片-水平
	public static Bitmap convertBmp(Bitmap bmp) {
		int w = bmp.getWidth();
		int h = bmp.getHeight();

		Matrix matrix = new Matrix();
		matrix.postScale(-1, 1); // 镜像水平翻转
		Bitmap convertBmp = Bitmap.createBitmap(bmp, 0, 0, w, h, matrix, true);

		return convertBmp;
	}

	public static Bitmap cQuality(Bitmap bitmap) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		int beginRate = 100;
		// 第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差 ，第三个参数：保存压缩后的数据的流
		bitmap.compress(CompressFormat.JPEG, 100, bOut);
		while (bOut.size() / 1024 / 1024 > 100) { // 如果压缩后大于100Kb，则提高压缩率，重新压缩
			beginRate -= 10;
			bOut.reset();
			bitmap.compress(CompressFormat.JPEG, beginRate, bOut);
		}
		ByteArrayInputStream bInt = new ByteArrayInputStream(bOut.toByteArray());
		Bitmap newBitmap = BitmapFactory.decodeStream(bInt);
		if (newBitmap != null) {
			return newBitmap;
		} else {
			return bitmap;
		}
	}

	public static Bitmap getBm(String filePath) {
		Bitmap bm=BitmapFactory.decodeFile(filePath);
		bm=cQuality(bm);
		return bm;
	}

	public static void getBM(byte[] data,String path){
		Bitmap bm=BitmapFactory.decodeByteArray(data, 0, data.length);
		bm=cQuality(bm);//质量压缩
		FileUtils.saveBitmap1(bm, path);//s
	}
	
	
	
	
}
