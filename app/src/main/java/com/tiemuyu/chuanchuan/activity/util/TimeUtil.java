package com.tiemuyu.chuanchuan.activity.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {

	public static String subTime="62135625943000";
	public static String converTime(long timestamp) {
		long currentSeconds = System.currentTimeMillis() / 1000;
		long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
		String timeStr = null;
		if (timeGap > 24 * 60 * 60) {// 1天以上
			timeStr = timeGap / (24 * 60 * 60) + "天前";
		} else if (timeGap > 60 * 60) {// 1小时-24小时
			timeStr = timeGap / (60 * 60) + "小时前";
		} else if (timeGap > 60) {// 1分钟-59分钟
			timeStr = timeGap / 60 + "分钟前";
		} else {// 1秒钟-59秒钟
			timeStr = "刚刚";
		}
		return timeStr;
	}

	public static String getSubTime(String date1, String data2) {
		// String fromDate = "2013-04-16 08:29:12";
		// String toDate = "2013-04-20 09:44:29";
		String timeCurr = null;
		String timeEnd = null;
		if (date1.contains("T")) {
			timeCurr = date1.replace("T", " ");
		} else {
			timeCurr = date1;
		}

		if (data2.contains("T")) {
			timeEnd = data2.replace("T", " ");
		} else {
			timeEnd = data2;
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String subTime = null;
		try {
			// 当前的时间
			Date fd = df.parse(timeCurr);
			// 结束时间
			Date td = df.parse(timeEnd);

			long time1 = fd.getTime();
			long time2 = td.getTime();
			if (time1 >= time2) {
				LogHelper.d("----");
				return subTime = "已结束";
			}

			// 两时间差,精确到毫秒
			long diff = td.getTime() - fd.getTime();
			long day = diff / 86400000; // 以天数为单位取整
			long hour = diff % 86400000 / 3600000; // 以小时为单位取整
			long min = diff % 86400000 % 3600000 / 60000; // 以分钟为单位取整
			long seconds = diff % 86400000 % 3600000 % 60000 / 1000; // 以秒为单位取整
			// 天时分秒
			subTime = day + "天" + hour + "小时" + min + "分";
			// System.out.println("两时间差---> "
			// +day+"天"+hour+"小时"+min+"分"+seconds+"秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return subTime;
	}

	
	//
	public static long getTimeNaSub() {
		int s=(int) (Math.random() * 9000 + 1000);
		long ss=System.currentTimeMillis()+Long.parseLong(subTime);
		String ks=String.valueOf(ss)+String.valueOf(s);
		long time=Long.parseLong(ks);
		return time;
	}
	

	/**
	 * @Title: getTimeCut
	 * @Description: TODO 时间差--10分钟
	 * @param @param data1
	 * @param @param data2
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean getTimeCut(String data1, String data2) {
		if (StringUtil.isNull(data1) || StringUtil.isNull(data2)) {
			return false;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(data1);
			Date d2 = df.parse(data2);
			long diff = d2.getTime() - d1.getTime();
			long sec = diff / (1000 * 60);
			if (sec > 10)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getStandardTime(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
		Date date = new Date(timestamp * 1000);
		sdf.format(date);
		return sdf.format(date);
	}

	public static String getCurrTime(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timestamp);
		sdf.format(date);
		return sdf.format(date);
	}
	
	public static String getCurrtimename() {
		String fName = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		return fName;
	}

	public static String getMsgKey() {

		return UUID.randomUUID().toString();
	}

	public static String getDfCurrtimename() {
		String fName = new SimpleDateFormat("yyyyMMddHHmmssSSSS")
				.format(new Date());
		return fName;
	}

	public static String getPhoneCurrtimename() {
		// 20150710
		String fName = new SimpleDateFormat("yyyyMMdd")
				.format(new Date());
		return fName;
	}

	public static String getNowCurrtimename() {
		String fName = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		return fName;
	}

	public static String getMCurrtimename() {
		// String fName = new SimpleDateFormat("MM月dd日 HH:mm:ss")
		// .format(new java.util.Date());
		// return fName;

		return System.currentTimeMillis() + "";
	}

	public static int getRemainTime(String day) {
		int days = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String curr = new SimpleDateFormat("yyyy-MM-dd ")
				.format(new Date());
		try {
			Date date = sdf.parse(curr);
			Date dateBegin = sdf.parse(day);
			long betTime = dateBegin.getTime() - date.getTime();
			days = (int) (betTime / 1000 / 60 / 60 / 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return days;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	public static String getDatatime(long time) {
		Date dt = new Date(time);
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日 HH:mm:ss");
		String sDateTime = df.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
		return sDateTime;
	}

	/**
	 * 得到当前的年月 2015-05
	 * */
	public static String getYM() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String yh;
		yh = dateString.substring(0, 7);
		return yh;
	}

	/**
	 * 得到当前的年份 2015
	 * */
	public static String getYear() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String year;
		year = dateString.substring(0, 4);
		return year;
	}

	/**
	 * 得到当前的月份 05
	 * */
	public static String getMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String year;
		year = dateString.substring(5, 7);
		return year;
	}

	public static long getBothMsTime(String dateStart, String dateStop) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		long diff = 0;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// 毫秒ms
			diff = d2.getTime() - d1.getTime();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return diff;
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static String getDateCN() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;// 2012年10月03日 23:41:31
	}

}
