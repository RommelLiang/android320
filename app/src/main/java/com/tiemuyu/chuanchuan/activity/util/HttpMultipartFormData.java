package com.tiemuyu.chuanchuan.activity.util;

/**
 * Created by CC2.0 on 2016/8/25.
 */

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
//import java.util.zip.Inflater;
//import android.util.Xml.Encoding;



//如果funflag=“userimg” 换头像调用这个funflag 调用函数client.Upload(url, data, fileName, contentType);
//string Upload(string url, byte[] data, string fileName, string contentType, int timeout = 10000);
//其中fileName="header.jpg"
//contentType="image/jpg"
//也就是说只用调用流格式的图片byte和url则可
//调用addfile函数


//如果不是userimg则为一切其他传图。只用知道url和path则可。属于传文件性质post
//public string Upload(string url, string path, int timeout = 10000)
//{
//    this.Timeout = timeout;
//    var result = UploadFile(url, "POST", path);
//    return System.Text.Encoding.UTF8.GetString(result);
//}


public class HttpMultipartFormData {

    byte result[] = new byte[0];
    ByteArrayOutputStream ms = new ByteArrayOutputStream(1);

    UUID uuid = UUID.randomUUID();
    String boundary = "----" + uuid.toString();//Guid.NewGuid().ToString("N");这是给直接变成了数字

    public void AddParam(String key, String value) {
        //todo
    }


    public void AddFile(byte[] data, String name, String filename, String contentType) {
        String bd = "--" + boundary + "\r\n";

        byte[] bbyte = null;
        try {
            bbyte = bd.getBytes("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //byte[] bbyte = Encoding.UTF_8.GetBytes(bd);
        ms.write(bbyte, 0, bbyte.length);
        //写入Content-Disposition
        String cd = "Content-Disposition:";
        cd += "form-data;";
        cd += "name=\"" + name + "\"";
        cd += "filename=\"" + filename + "\"";
        cd += "\r\n";
        cd += "Content-Type: " + contentType;
        cd += "\r\n\r\n";

        byte[] obyte = null;
        try {
            obyte = cd.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ms.write(obyte, 0, obyte.length);
        //写入数据
        ms.write(data, 0, data.length);
        String fenge = "\r\n";
        byte[] newlineBytes = null;
        try {
            newlineBytes = fenge.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ms.write(newlineBytes, 0, newlineBytes.length);
    }


}


