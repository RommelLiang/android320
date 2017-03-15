package com.tiemuyu.chuanchuan.activity.util;

/**
 * Created by CC2.0 on 2017/1/6.
 */

/**
 * @ClassName: Utility
 * @Description: TODO  里面只有防止重复点击的函数
 * @author hw
 * @date 2015-6-16
 */
public class Utility {

    private static long lastClickTime;

    private static String endString = "-small";

    /**
     * 防止用户连续点击
     *
     * @return true:连续点击 false：单次点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

//	/**
//	 * 获取缩略图片地址
//	 *
//	 * @param img
//	 * @return
//	 */
//	public static String getSmallImg(String img) {
//		if (JudgmentLegal.isNull(img))
//			return img;
//		if (img.lastIndexOf(".") == -1)
//			return img;
//		String prevStr = img.substring(0, img.lastIndexOf("."));
//		String suffixStr = img.substring(img.lastIndexOf("."));
//		String smallImg = prevStr.endsWith(endString) ? prevStr.endsWith(endString + endString) ? (prevStr.substring(0, prevStr.lastIndexOf(endString)) + suffixStr) : img : (prevStr + endString + suffixStr);
//		return smallImg;
//	}
//
//	/**
//	 * 获取大图地址
//	 *
//	 * @param img
//	 * @return
//	 */
//	public static String getLargeImg(String img) {
//		if (JudgmentLegal.isNull(img))
//			return img;
//		if (img.lastIndexOf(".") == -1)
//			return img;
//		String tempString = img.substring(0, img.lastIndexOf("."));
//		if (!tempString.endsWith(endString))
//			return img;
//		String prevStr = img.substring(0, img.lastIndexOf("-"));
//		String suffixStr = img.substring(img.lastIndexOf("."));
//		return prevStr + suffixStr;
//	}
//
//	/**
//	 * 拆分XML
//	 *
//	 * @param 返回的XML
//	 * @param 开始字段
//	 * @return
//	 */
//	public static String[] judgeRespone(String respone, String... columns) {
//		String[] judgeRespones;
//		if (JudgmentLegal.isNull(respone))
//			return new String[] { respone };
//		else {
//			judgeRespones = new String[columns.length];
//			for (int i = 0; i < columns.length; i++) {
//				String string = columns[i];
//				int index = respone.lastIndexOf("/" + columns[i]);
//				judgeRespones[i] = respone.substring(0, index) + "/" + string + "></content>";
//				StringBuffer buffer = new StringBuffer(respone);
//				buffer.delete(0, judgeRespones[i].length() - 10);
//				respone = "<content>" + buffer.toString();
//				LogHelper.e(judgeRespones[i]);
//			}
//		}
//		return judgeRespones;
//
//	}
}
