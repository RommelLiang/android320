package com.tiemuyu.chuanchuan.activity.util;

import android.util.Log;

import com.tiemuyu.chuanchuan.activity.bean.KeFuBean;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by 梁文硕 on 2017/3/30.
 */

public class GetCustomer {
    public static void httpConnPostString(String accid, String token) {
        String paramsString = String.format("{\"userid\":\"%s\",\"acctoken\":\"%s\"}", accid, token);
        Log.e("httpConnPostString: ", paramsString);

        try {
            String resultString = null;
            HttpURLConnection conn = null;
            InputStream inputStream = null;
            ByteArrayOutputStream bos = null;
            String srcUrl = "http://imserver.myappcc.com/api/GetCustomer";
            URL url = new URL(srcUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            conn.connect();
            // 传入参数
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(paramsString.getBytes());

            int responseCode = conn.getResponseCode();
            Log.e("httpConnPost:", "" + responseCode);
            if (responseCode == 200 || true) {
                if (null != conn.getHeaderField("Content-Encoding")) {
                    inputStream = new GZIPInputStream(conn.getInputStream());
                } else {
                    inputStream = conn.getInputStream();
                }

                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[10240];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.flush();
                byte[] resultByte = bos.toByteArray();
                Log.e("httpUtils3", resultByte.length + "");
                resultString = new String(resultByte);
                Log.e("httpConnPostString: ", resultString.substring(1, resultString.length() - 1));
                KeFuBean keFuBean = GsonUtils.fromData(resultString.replace("\\",""),
                        KeFuBean.class);
                Log.e("keFuBean: ",keFuBean.getData().getAttachcontent()+"" );
                SPUtils.saveKefuCode(keFuBean.getData().getContent());
            } else {
                Log.e("httpUtils", "ResponseCode:" + responseCode);
            }
            Log.e("httpUtilsresultString1", resultString);
        } catch (Exception e) {
            Log.e("httpConnPostString2 ", e.getLocalizedMessage());
        }

    }
}
