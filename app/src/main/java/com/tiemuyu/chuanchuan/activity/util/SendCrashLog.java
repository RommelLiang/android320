package com.tiemuyu.chuanchuan.activity.util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 梁文硕 on 2017/5/4.
 */

public class SendCrashLog {

	public static String post(String urlStr, String filePath) {
		Log.e( "SendCrashLog", urlStr);
		Log.e( "SendCrashLog", filePath);
		String rsp = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "|"; // request头和上传文件内容分隔符
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			File file = new File(filePath);
			String filename = file.getName();
			String contentType = "application/octet-stream";
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"" + "android"
					+ "\"; filename=\"" + filename + "\"\r\n");
			strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
			Log.e("SendCrashLog", String.valueOf(strBuf));
			out.write(strBuf.toString().getBytes());
			FileInputStream in = new FileInputStream(file);
			Log.e("SendCrashLog",in.toString() );
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			Log.e("SendCrashLog",out.toString());
			out.flush();
			Log.e("SendCrashLog",out.toString());
			out.close();
			Log.e("SendCrashLog","end");
			// 读取返回数据
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			Log.e("SendCrashLog", reader.toString());
			Log.e("SendCrashLog", conn.getResponseCode()+"");
			if (conn.getResponseCode() == 200) {
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					String path = Environment.getExternalStorageDirectory().getPath() + "/crash/cc/";
					File targetFile = new File(path);
					targetFile.delete();
				}
			}
			reader.close();
			Log.e("formUpload: ", rsp);
			reader = null;
		} catch (Exception e) {
			Log.e("ExceptionPost: ", e.getLocalizedMessage());
		} finally {
			try {
				int responseCode = conn.getResponseCode();
				Log.e("SendCrashLog", responseCode+"");
			} catch (IOException mE) {
				Log.e("ExceptionPost: ", mE.getLocalizedMessage());
			}
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return rsp;
	}
}
