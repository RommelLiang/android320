

package com.tiemuyu.chuanchuan.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tiemuyu.chuanchuan.activity.InterfacePac.JsInterface;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.NetworkUtil;
import com.tiemuyu.chuanchuan.activity.util.ServerUtils;
import com.tiemuyu.chuanchuan.activity.view.ImagePreviewActivity;
import com.tiemuyu.chuanchuan.activity.view.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import net.tsz.afinal.FinalBitmap;


/**
 * Created by Administrator on 2016/8/4.
 */
public class MyOrder extends AppCompatActivity {

    private LinearLayout goBack;
    private WebView im_webView;
    String result="";
    private JsInterface JSInterface2 = new JsInterface();
    boolean yes=true;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_FROM_ALBUM = 2;
    public static   int task=0;

    private String base = URL.BASE;
    public String funFlagStr = "";

    public String lasturl;

    private String orderxx;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏为透明
        setContentView(R.layout.activity_present);
        goBack = (LinearLayout) findViewById(R.id.goBack);
        im_webView = (WebView) findViewById(R.id.im_webView);
        im_webView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        if (NetworkUtil.isConnected(this))
            im_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        else
            im_webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        im_webView.getSettings().setDomStorageEnabled(true);
        im_webView.getSettings().setDatabaseEnabled(true);
        im_webView.getSettings().setAppCacheEnabled(true);

        im_webView.getSettings().setBlockNetworkImage(false);
        im_webView.getSettings().setJavaScriptEnabled(true);
        im_webView.addJavascriptInterface(JSInterface2, "JSInterface2");
        im_webView.setWebViewClient(new ExampleWebViewClient());
        DataContoler.synCookies(this, URL.UrlOrder);

        im_webView.post(new Runnable() {
            @Override
            public void run() {
                im_webView.loadUrl(URL.UrlOrder);
            }
        });
        System.out.println("!!@@@@@@@@@@@@@@@进入发图求价格");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(task==0)
                {
                    finish();

                }
                else
                {
                    task--;
                    im_webView.goBack();


                }

                //finish();
            }
        });
    }

    public void addPageLoad() {
        if(!TextUtils.isEmpty(result)) {
            System.out.println(result + "@@@@@@成功上传图片");
            //String str = "{\"Code\":1,\"Msg\":\"OK\",\"Data\":{\"ImageUrl\":\"http://f1.myappcc.com/zfs/7E0/1240/RIC/240174344351CABGTOTZGT.jpg\"}}";
            try {
                JSONObject jsonObject = new JSONObject(result);
                String s_data = jsonObject.getString("Data");
                //System.out.println(s_data);
                JSONObject jsonObject1 = new JSONObject(s_data);
                String json_url = jsonObject1.getString("ImageUrl");
                System.out.println(json_url);
                String call = "javascript:funcphoto(\"" + json_url+ "\")";
                im_webView.loadUrl(call);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("!!!!!!!!!!走完页面加载程序");
    }

    class ExampleWebViewClient extends WebViewClient {





        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Build.VERSION.SDK_INT >= 19) {
                view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
            task++;
            view.loadUrl(url);
            //view.loadUrl("http://test.myappcc.com/cc/customize/piccustomize");
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            JSInterface2.setWvClientClickListener(new webviewClick());
            System.out.println("**************加載完成" + url);
            if (yes == true) {
                System.out.println("**************里层加载");
                im_webView.loadUrl(URL.UrlOrder);
                yes = false;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }
    }

    class webviewClick implements JsInterface.wvClientClickListener {

        /*以下函数用来调用ImagePreviewActivity从而显示图片的大图*/
        @Override
        public void wvHasClickEnvent(String imgUrl) {
            // TODO Auto-generated method stub
            //Toast.makeText(getActivity(), "图片的URL是：" + imgUrl, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MyOrder.this, ImagePreviewActivity.class);
            intent.putExtra("imgUrl", imgUrl);
            startActivityForResult(intent, 100);
        }

        //接受一个字符串参数toastText然后toast到手机屏幕上
        @Override
        public void AndroidToast(String toastText) {
            //Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            System.out.println("AndroidToast##########################" + toastText);
        }

        @Override
        public void testToast(String key) {
            Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();
            System.out.println("TEST##########################" + key);
        }

        //拍照相关
        @Override
        public void photo(String funflag, int num, int type) {
            //史力：将web接收到的url传给程序
            funFlagStr = funflag;
            //史力：选择照相还是相册。以下为相册的方法：
            System.out.println("**************调用photo");
            System.out.println("**************调用photoMOMENT");
            AlertDialog.Builder multiDia=new AlertDialog.Builder(getApplicationContext());
            multiDia.setTitle("选择照片： ");
            multiDia.setPositiveButton("相册", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    System.out.println("********进入相册");
                    Toast.makeText(getApplicationContext(), "点击相册", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");    //设置新的intent是用来从相册里面选取照片
                    intent.setType("image/*");
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECT_FROM_ALBUM);                  //开始相册的activity，设置标签为SELECT_FROM_ALBUM
                }
            });
            multiDia.setNeutralButton("照相机", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    System.out.println("**********进入照相机");
                    Toast.makeText(getApplicationContext(), "点击照相机", Toast.LENGTH_SHORT).show();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date date = new Date();
                    String fileName = format.format(date);
                    File outputImage = new File("/storage/emulated/0/DCIM/Camera/", fileName + ".jpg");
                    System.out.println("******path:" + outputImage.toString());
                    try {
                        if (outputImage.exists()) {
                            outputImage.delete();
                        }
                        outputImage.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageUri = Uri.fromFile(outputImage);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                }
            });
            multiDia.create().show();
        }

        @Override
        public void navigatorback(String key) {
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^进入navigatorback" + key);
//            if(key.equals("finish"))
//            {
//                System.out.println("one");
//                im_webView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MineFragment.isFirst = true;
//                        //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//                        im_webView.loadUrl(URL.UrlMine);
//                    }
//                });
//            }
//            else {
            System.out.println("two");

            im_webView.post(new Runnable() {
                @Override
                public void run() {
                    MineFragment.isFirst = true;
                    //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    im_webView.goBack();
                }
            });
//            }
        }

        @Override
        public void navigatoropen(String url) {
            //判断是否登陆
            System.out.println("**************进入navigatoropen" + url);
            System.out.println("**************调用" + url);
            System.out.println("**************当前url:" + lasturl);
            if (url.substring(0, 1).equals("/")) {
                final String newurl1 = base + url;
                System.out.println("分支一**************含有/则Base相加" + newurl1);
                im_webView.post(new Runnable() {
                    @Override
                    public void run() {
                        im_webView.loadUrl(newurl1);
                    }
                });
            } else {
                System.out.println("分支二**************不含/则现有相加" + url);
                //http://test.myappcc.com/cc/user/mine
                String[] temp = null;
                temp = lasturl.split("/");
                System.out.println("分支二**************不含/则现有相加" + temp[0] + "temp0" + temp[1] + "temp1" + temp[2] + "temp2" + temp[3] + "temp3");
                final String newurl2 = temp[0] + "/" + temp[1] + "/" + temp[2] + "/" + temp[3] + "/" + temp[4] + "/" + url;
                System.out.println("分支二**************不含/则现有相加" + newurl2);
                im_webView.post(new Runnable() {
                    @Override
                    public void run() {
                        im_webView.loadUrl(newurl2);
                    }
                });
            }
        }

        /// <summary>
        /// 支付
        /// </summary>
        /// <param name="signString"></param>对应了order string
        /// <param name="type"></param>默认为2 为阿里支付
        /// <param name="scheme"></param>
        /// <param name="completeHandler"></param>
        @Override
        public void payfunction(String signString, long type, String orderid) {

            System.out.println("**********************payfunction orderid" + orderid);
            //String scheme = "cc2";//无用
            final String payinfo = signString;
            pay(payinfo, orderid);
        }

        @Override
        public void ToastUsrPwd(String logincache, String savestr, String accid, String acctoken) {//再加上acc
//            System.out.println(sp.getString(logincache, ""));
//            System.out.println("accid right after ToastUsrPwd:" + accid);
//            System.out.println("acctoken right after ToastUsrPwd: " + acctoken);
//
//            if(accid.equals("wrong")||accid.equals("noneed")) {System.out.println("777");}
//            else {
//                System.out.println("7997");
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString(logincache, savestr);      //logincache字符串对应了savestr这个键值对，都存储在了UserInfo.xml这个文件里面
//                editor.commit();
//                //史力：以下用来检测logincache键值对、accid、acctoken的值,如果accid和acctoken都为空，则都写死成gao的accid和acctoken
//                if (accid.equals("") && acctoken.equals("")) {
//                    System.out.println("accid and acctoken are empty! ");
//                    return;
//                }
//                //史力：测试accid和acctoken是否取出
//                System.out.println(sp.getString(logincache, ""));
//                System.out.println("accid:" + accid);
//                System.out.println("acctoken: " + acctoken);
//                //史力：将accid和acctoken都写入到sp中
//                editor.putString("accid", accid).commit();
//                editor.putString("acctoken", acctoken).commit();
//                //史力：通过println测试accid和acctoken是否被成功地写入到了sp中去
//                System.out.println("accid in SP: " + sp.getString("accid", ""));
//                System.out.println("acctoken in SP: " + sp.getString("acctoken", ""));
//                System.out.println("username after editor.putString: " + sp.getString(logincache, ""));
//            }
        }


        @Override
        public void headersettingtext(String text, String script) {
//            System.out.println("**********************headersettingtext text" + text);
//            savepage=true;
//            pagetext=text;

        }

        //无用
        @Override
        public void headerbackhide(){
//            System.out.println("**********************headerbackhide");
//            MainActivity parentActivity = (MainActivity) getActivity();
//            parentActivity.hideback();
        }

        //无用
        @Override
        public void funcsetlogin(String userid, String accid, String acctoken){}

        @Override
        public void sessionset(String id,String session){
//            SharedPreferences.Editor editor = sp.edit();
//            //System.out.println("狗屁"+session);
//            editor.putString(id, session);      //logincache字符串对应了savestr这个键值对，都存储在了UserInfo.xml这个文件里面
//            editor.commit();
        }

        @Override
        public void sessionget(String id  ,String x1){
//            String getinfo = sp.getString(id, "");
//            MainActivity parentActivity = (MainActivity) getActivity();
//            parentActivity.sessionweb(getinfo, x1);
        }

        @Override
        public void copyText(String text) {
//            System.out.println("准备复制：" + text);
//            myClipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
//            myClip = ClipData.newPlainText("text", text);
//            myClipboard.setPrimaryClip(myClip);
//            System.out.println("完成copyText");
        }

        @Override
        public void InforPass(String validcode) {
//            MainActivity parentActivity = (MainActivity) getActivity();
//            parentActivity.information(validcode);
        }

        @Override
        public void userimag(String path){
//            sp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("userimag", path);      //logincache字符串对应了savestr这个键值对，都存储在了UserInfo.xml这个文件里面
//            editor.commit();
//            System.out.println("闭嘴" + path);
        }
        //实践证明share函数只能在原生里面做
        @Override
        public void shareInAndroid(String title, String text, String image_url, String share_url, String type) {
        }





    }







    //重新搞一下。不用post了。直接搞了在app解析了然后判断。
    private void pay(final String payInfo, String orderid) {
        orderxx = orderid;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(MyOrder.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                //把result 按照；分开然后变成
                String[] resultlist = result.split(";");
                System.out.println("==============resultlist=============================="+resultlist[2]);
                String[] xxx=resultlist[0].split("=");
                String xxxafter=xxx[1].substring(1, xxx[1].length()-1);
                System.out.println("==============================xxxafter=============="+xxxafter);
                Message msg = new Message();
                if (xxxafter.equals("9000"))
                {
                    msg.what =1;
                }
                else          if(xxxafter.equals("4000")||xxxafter.equals("6002"))       msg.what =2;
                else msg.what=3;
                msg.obj =result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    Toast.makeText(getApplicationContext(), "支付成功!", Toast.LENGTH_SHORT).show();
                    System.out.println("test.myappcc.com/cc/customize/buyerbodydata?id="+orderxx);
                    im_webView.post(new Runnable() {
                        @Override
                        public void run() {
                            im_webView.loadUrl("http://test.myappcc.com/cc/customize/buyerbodydata?id="+orderxx);
                        }
                    });
                    break;
                }
                case 2: {
                    Toast.makeText(getApplicationContext(), "支付错误请重试!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 3: {
                    Toast.makeText(getApplicationContext(), "支付退出请重试!", Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };














    @SuppressWarnings("deprecation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        //Uri imageUri = null;
        switch (requestCode) {
            //以下，调用摄像头发图
            case TAKE_PHOTO:// 摄像头
                System.out.println("***********进入TAKE_PHOTO选择");
                if (data != null)
                    imageUri = data.getData();
                //以下代码是将uri转换为绝对文件路径（已废弃，因为只适用于旧版安卓的uri路径格式）
//                System.out.println("********执行完语句\"imageUrl = data.getData();\"");
//                String[] proj = { MediaStore.Images.Media.DATA };
//                Cursor cursor = managedQuery(imageUri, proj, null, null, null);
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor.moveToFirst();
//                final String path = cursor.getString(column_index);// 图片在的路径
//                System.out.println("column_index="+column_index);
//                System.out.println("path="+path);
//                System.out.println("*******imageUrl: " + imageUri);
//                System.out.println("*******imageUrl to path: " + path);
                System.out.println("imageUrl: " + imageUri);
                final String path = imageUri.toString().substring(7);
                //史力：下为旋转代码（安卓在有些情況下預覽的圖片是旋轉了一個角度的，以下程序再把圖片正回來）
                int degree = 0;
                try {
                    //史力：从指定路径下读取图片，并获取其EXIF信息
                    ExifInterface exifInterface = new ExifInterface(path);
                    // 史力：获取图片的旋转旋轉角度，保存在degree裡面
                    int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
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
                System.out.println("@@@@@@@@@@图片旋转了：" + degree);
                //史力：以下是通過URI獲得bitmap
                Bitmap bm = null;
                try
                {   //史力：读取uri所在的图片
                    bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                }
                catch (Exception e)
                {
                    //do nothing
                }
                //史力：以下是旋轉圖片
                Bitmap returnBm = null;
                Matrix matrix = new Matrix();
                matrix.postRotate(degree);
                try {
                    //史力：将原始图片按照旋转矩阵进行旋转，并得到新的图片
                    bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
                } catch (OutOfMemoryError e) {
                    //do nothing here
                }
                if (returnBm == null) {
                    returnBm = bm;
                }
                if (bm != returnBm) {
                    bm.recycle();
                }
                //史力：以下為保存圖片代碼
                File file = new File(path);
                try {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    returnBm.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("*******imageUrl to path: " + path);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //调用接口传图
                        System.out.println("*******传入web的path：" + path);
                        result = ServerUtils.formUpload(URL.UPLOAD_URLMoment, path);
                        System.out.println("*******result: "+result);
                        if(!TextUtils.isEmpty(result)){
                            System.out.println("******进入非空判断");
                            handler.sendEmptyMessage(1);
                            System.out.println(result);//成功
                        }else{
                            handler.sendEmptyMessage(2);//失败
                        }
                    }
                }).start();
                break;
            //以下：当选择从相册发图时候执行
            case SELECT_FROM_ALBUM:
                System.out.println("***********进入SELECT_FROM_ALBUM选择");
//                以下代码是旧版本的uri和绝对路径互相之间的转换，但是已经弃用
//                uri = data.getData();
//                String[] proj = { MediaStore.Images.Media.DATA };
//                Cursor cursor = managedQuery(uri, proj, null, null, null);
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor.moveToFirst();
                if (data == null) {
                    return;
                }
                imageUri = data.getData();
                System.out.println("old Uri: " + data.getData());
                //史力：GetPathFromUri4kitkat.getPath方法是新版的uri与绝对路径的转换方法，适合不同安卓版本的uri
                final String newUri = GetPathFromUri4kitkat.getPath(getApplicationContext(), data.getData());
                //史力：下为旋转代码
                int degree2 = 0;
                try {
                    // 史力：从指定路径下读取图片，并获取其EXIF信息
                    ExifInterface exifInterface = new ExifInterface(newUri);
                    // 史力：获取图片的旋转信息
                    int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL);
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            degree2 = 90;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            degree2 = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            degree2 = 270;
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("@@@@@@@@@@图片旋转了：" + degree2);
                //史力：以下是通過URI獲得bitmap
                Bitmap bm2 = null;
                try
                {   // 史力：读取uri所在的图片
                    bm2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                }
                catch (Exception e)
                {
                    //do nothing
                }
                // 史力：以下是旋轉圖片
                Bitmap returnBm2 = null;
                Matrix matrix2 = new Matrix();
                matrix2.postRotate(degree2);
                try {
                    // 史力：将原始图片按照旋转矩阵进行旋转，并得到新的图片
                    bm2 = Bitmap.createBitmap(bm2, 0, 0, bm2.getWidth(), bm2.getHeight(), matrix2, true);
                } catch (OutOfMemoryError e) {
                    //do nothing here
                }
                if (returnBm2 == null) {
                    returnBm2 = bm2;
                }
                if (bm2 != returnBm2) {
                    bm2.recycle();
                }
                //史力：以下為保存圖片代碼
                File file2 = new File(newUri);
                try {
                    file2.createNewFile();
                    FileOutputStream fos2 = new FileOutputStream(file2);
                    returnBm2.compress(Bitmap.CompressFormat.JPEG, 50, fos2);
                    fos2.flush();
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Uri after getPath: " + newUri);
                //以下是将相册选择得到的ur转换为绝对路径，然后传到下一步里面去（然而华为无法使用以下代码，因为一些版本的uri形式和其它版本不一样，所以以下方法不适用所有的安卓版本）
//                imageUri = data.getData();
//                System.out.println("********执行完语句\"imageUrl = data.getData();\"");
//                System.out.println("uri after getData: " + imageUri.toString());
//                String[] proj2 = { MediaStore.Images.Media.DATA };
//                Cursor cursor2 = managedQuery(imageUri, proj2, null, null, null);
//                int column_index2 = cursor2.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor2.moveToFirst();
//                final String path2 = cursor2.getString(column_index2);
//                System.out.println("column_index="+column_index2);
//                System.out.println("path="+path2);
//                System.out.println("*******imageUrl: " + imageUri);
//                System.out.println("*******imageUrl to path: " + path2);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //调用接口传图
                        System.out.println("*******传入web的path：" + newUri);
                        result = ServerUtils.formUpload(URL.UPLOAD_URLMoment, newUri);
                        System.out.println("*******result: "+result);
                        if(!TextUtils.isEmpty(result)){
                            System.out.println("******进入非空判断");
                            handler.sendEmptyMessage(1);
                            System.out.println(result);//成功
                        }else{
                            handler.sendEmptyMessage(2);//失败
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplicationContext(), "图片上传成功", Toast.LENGTH_SHORT).show();
                    System.out.println("*******图片上传成功");
                    addPageLoad();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "图片上传失败", Toast.LENGTH_SHORT).show();
                    System.out.println("*******图片上传失败");
                    break;
                default:
                    break;
            }
        }
    };

}