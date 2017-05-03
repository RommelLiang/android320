package com.tiemuyu.chuanchuan.activity.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/10.
 */

public class SocketHttpRequester {

	private static long start;
	private static long end;

	public static String post(String urlStr, List<String> filePaths) throws Exception {
		String rsp = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "|"; // request头和上传文件内容分隔符
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
		for ( int i = 0; i < filePaths.size(); i++ ) {
			File file = new File(filePaths.get(i));
			String filename = file.getName();
			String contentType = "";
			if (filename.endsWith(".png")) {
				contentType = "image/png";
			}
			if (filename.endsWith(".jpg")) {
				contentType = "image/jpg";
			}
			if (filename.endsWith(".gif")) {
				contentType = "image/gif";
			}
			if (filename.endsWith(".bmp")) {
				contentType = "image/bmp";
			}
			if (contentType == null || contentType.equals("")) {
				contentType = "application/octet-stream";
			}
			StringBuffer fileExplain = new StringBuffer();
			fileExplain.append("\r\n");
			fileExplain.append("--");
			fileExplain.append(BOUNDARY).append("\r\n");
			fileExplain.append("Content-Disposition: form-data;name=\"" + "android_image" + i + "jpg"
					+ "\";filename=\"" + filename + "\"\r\n");
			fileExplain.append("Content-Type: " + contentType + "\r\n\r\n");
			start = System.currentTimeMillis();
			out.write(fileExplain.toString().getBytes());
			Bitmap bitmap = decodeFile(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//得到输出流
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			//转输入流
			InputStream isBm = new ByteArrayInputStream(baos .toByteArray());
			DataInputStream in = new DataInputStream(isBm);
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
		}
		byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
		out.write(endData);
		out.flush();
		out.close();

		// 读取返回数据
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line).append("\n");
		}
		rsp = buffer.toString();
		end = System.currentTimeMillis();
		Log.e("post: " + String.valueOf(end - start), rsp);
		int responseCode = conn.getResponseCode();

		if(responseCode != 200){//读取web服务器返回的数据，判断请求码是否为200，如果不是200，代表请求失败
			rsp = "{\"Code\":0,\"Msg\":\"OK\"," +
					"\"Data\":{\"ImageUrl\":\"\"}}";
		}
		reader.close();
		return rsp;
	}
	private static Bitmap decodeFile(File f){
		Bitmap b = null;
		Bitmap bitmap = null;
		try {
			//Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;

			FileInputStream fis = new FileInputStream(f);
			BitmapFactory.decodeStream(fis, null, o);
			fis.close();
			int scale = 1;
			if (o.outHeight > 800 || o.outWidth > 800) {
				//scale = (int) Math.pow(2, Math.round(Math.log(200 / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
				scale = Math.max(o.outHeight, o.outWidth)/800;
				Log.e("decodeFile: ", scale+"");
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			fis = new FileInputStream(f);
			b = BitmapFactory.decodeStream(fis, null, o2);
			fis.close();
		} catch (Exception e) {
		}
		return b;
	}

}
