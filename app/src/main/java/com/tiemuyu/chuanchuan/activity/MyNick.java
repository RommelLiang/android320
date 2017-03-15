package com.tiemuyu.chuanchuan.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.chat_tools.view.CircleImageView;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ServerUtils;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tiemuyu.chuanchuan.activity.fragment.MineFragment.user;

/**
 * Created by Weihao Gao on 2017/1/27.
 */

public class MyNick extends BaseActivityG {


    @ViewInject(R.id.iv_touxiang)
    private CircleImageView iv_touxiang;


    @ViewInject(R.id.et_nicheng)
    private EditText et_nicheng;


    @ViewInject(R.id.nick_finish)
    private Button nick_finish;

    public Uri imageUri;

    private String usrImg = "";// 头像地址
    private String  nc;
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_FROM_ALBUM = 2;
    public String result="";


    private String TAG_REGIST_MODIFY = "TAG_REGIST_MODIFY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_nick);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();
        initProcess();


    }



    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();
        initAppAccess();
        initUI();
        initListener();
    }




    /**
     * 实例化访问记录
     */
    protected void initAppAccess() {
    }

    protected void initData() {
//        // TODO Auto-generated method stub
    }

    protected void initUI() {


        iv_touxiang=(CircleImageView) findViewById(R.id.iv_touxiang);

        et_nicheng=(EditText) findViewById(R.id.et_nicheng);

        et_nicheng.setText(user.getNickName());

        nick_finish=(Button) findViewById(R.id.nick_finish);

        ImageLoader.getInstance().displayImage(user.getUserImg(), iv_touxiang);




    }

    protected void initListener() {

        iv_touxiang.setOnClickListener(this);

        nick_finish.setOnClickListener(this);




    }





    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.iv_touxiang:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                //todo  调用拍照
                exFatu();




                break;

            case R.id.nick_finish:
                if (Utility.isFastDoubleClick()) // 连续点击
                    return;
                nc = et_nicheng.getText().toString().trim();
                if (user != null) {
                    if (!nc.equals(user.getNickName())
                            || !usrImg.equals(user.getUserImg())) {

                        if(usrImg=="")
                            usrImg=user.getUserImg();



                        System.out.println("----user 设置");
                        MyApplication.poolManager
                                .addAsyncTask(new ThreadPoolTaskHttp(this,
                                        TAG_REGIST_MODIFY, Constant.REQUEST_POST,
                                        ParamsTools.modify(
                                                UrlManager.MODIFY_ZILIAO(), usrImg,
                                                nc), this, "设置中...", true));

                    } else {
                        System.out.println("----user 关闭");

                        closeActivity();
//                        AppManager.getAppManager().finishActivity(
//                                MyInfo.class);
                    }
                } else {
                    System.out.println("----user is  null");
                }




                break;


        }
    }



    /**
     * 发图
     */
    public  void  exFatu()
    {
        if (Utility.isFastDoubleClick()) // 连续点击
            return;
        System.out.println("**************调用photoMOMENT");
        AlertDialog.Builder multiDia=new AlertDialog.Builder(this);
        multiDia.setTitle("选择照片： ");
        multiDia.setPositiveButton("相册", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                System.out.println("********进入相册");
//                    ToastHelper.show(this, "点击相册");

//                    Toast.makeText(, "点击相册", Toast.LENGTH_SHORT).show();
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
//                    ToastHelper.show(this, "点击照相机");

//                    Toast.makeText(getActivity(), "点击照相机", Toast.LENGTH_SHORT).show();
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


    //史力：接收相机或相册返回图片uri
    @SuppressWarnings("deprecation")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            //以下，调用摄像头发图
            case TAKE_PHOTO:// 摄像头
                System.out.println("@@@@@@@@@@@进入TAKE_PHOTO选择");
                if (data != null)
                    imageUri = data.getData();
                System.out.println("imageUrl: " + imageUri);
                final String path = imageUri.toString().substring(7);
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
                        result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLUSERIMG, path);
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
            //史力：以下：当选择从相册发图时候执行
            case SELECT_FROM_ALBUM:
                System.out.println("***********进入SELECT_FROM_ALBUM选择");
                if (data == null) {
                    return;
                }
                imageUri = data.getData();
                System.out.println("old Uri: " + data.getData());
                //GetPathFromUri4kitkat.getPath方法是新版的uri与绝对路径的转换方法，适合不同安卓版本的uri
                final String newUri = GetPathFromUri4kitkat.getPath(this, data.getData());
                System.out.println("Uri after getPath: " + newUri);
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //调用接口传图
                        System.out.println("*******传入web的path：" + newUri);
                        //根据funflag判断调用不同的url
                        result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLUSERIMG, newUri);
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

    public void addPageLoad() {
        if(!TextUtils.isEmpty(result)) {
            System.out.println(result + "@@@@基类@@成功上传图片");
            //String str = "{\"Code\":1,\"Msg\":\"OK\",\"Data\":{\"ImageUrl\":\"http://f1.myappcc.com/zfs/7E0/1240/RIC/240174344351CABGTOTZGT.jpg\"}}";
            try {
                JSONObject jsonObject = new JSONObject(result);
                String s_data = jsonObject.getString("Data");
                JSONObject jsonObject1 = new JSONObject(s_data);
                final String json_url = jsonObject1.getString("ImageUrl");
                System.out.println(json_url);
                //todo 把json_url发送给ui重置用个handler


                usrImg=json_url;
                ImageLoader.getInstance().displayImage(usrImg, iv_touxiang);

//                setMsg(0,json_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("!!!!!!!!!!走完页面加载程序");
    }





    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        // LogHelper.e("-----侧滑栏请求成功回调"+resultTag);
        System.out.println("successCallBack");
        successParse(callBackMsg, resultTag);
    }


    private void successParse(String msg, String resultTag) {
        if (resultTag.equals("TAG_REGIST_MODIFY")) {
            System.out.println("######更改用户昵称和头像回调:" + msg);
            // ToastHelper.show(getActivity(), "自动登录成功");
            ToastHelper.show(this, "设置成功");
            user.setUserImg(usrImg);
            user.setNickName(nc);

//            MineFragment.user = user;

            DBTools.loginDb(this, user);//修改数据库信息


//            setView(false, false, false);

            // ClassJumpTool.startToNextActivity(this,
            // RewardRegistActivity.class);

//            toRegistSuecc();

            AppManager.getAppManager().finishActivity(this);
            AppManager.getAppManager().finishActivity(MyInfo.class);


        }


//        else if (resultTag.equals(HttpTools.TAG_GETPASSKEY)) {
//            // LogHelper.d("---获取pass->"+msg);
//            System.out.println("########################################OOOOOOOOOOOOOOOOM<<<<<<<<<<<GGGGGG");
//            GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
//            if (key != null) {
//                // LogHelper.d("---获取pass1111111->");
//
//                passkey = key.getData().getPassKey();
//                PreferenceUtils.setPrefString(getActivity(), Constant.PASSKEY,
//                        passkey);
//                loginMethod();
//            }
//
//        }

    }

















}
