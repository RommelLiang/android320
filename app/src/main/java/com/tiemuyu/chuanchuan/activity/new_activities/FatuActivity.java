package com.tiemuyu.chuanchuan.activity.new_activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tiemuyu.chuanchuan.activity.GetPathFromUri4kitkat;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.fragment.MineFragment;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DownloadService;
import com.tiemuyu.chuanchuan.activity.util.LogHelper;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ServerUtils;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.Utility;
import com.tiemuyu.chuanchuan.activity.view.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CC2.0 on 2017/1/16.
 */

public class FatuActivity extends BaseActivityG
{

//两个按钮



    @ViewInject(R.id.add_img1)
    private ImageView fatu_bt1;// 加载图片十字


    @ViewInject(R.id.add_img2)
    private ImageView fatu_bt2;// 加载图片十字

    @ViewInject(R.id.add_img3)
    private ImageView fatu_bt3;// 加载图片十字

    @ViewInject(R.id.add_img4)
    private ImageView fatu_bt4;// 加载图片十字

    @ViewInject(R.id.add_img5)
    private ImageView fatu_bt5;// 加载图片十字

    @ViewInject(R.id.add_img6)
    private ImageView fatu_bt6;// 加载图片十字

    @ViewInject(R.id.add_img7)
    private ImageView fatu_bt7;// 加载图片十字

    @ViewInject(R.id.add_img8)
    private ImageView fatu_bt8;// 加载图片十字





    @ViewInject(R.id.post_pic)
    private Button fabu_bt;// 发布按钮


    @ViewInject(R.id.PostPicGoBack)
    private LinearLayout bt_back;// 回退



    @ViewInject(R.id.customize_description)
    private EditText et_text;// 说明输入框



private   int CURRENT=1;

    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_FROM_ALBUM = 2;
    public Uri imageUri;
    public String result="";

    private IntentFilter myIntentFilter;

    private List<String> imagelist = new ArrayList<String>();



    private String TAG_FABU_MOMENT = "TAG_FABU_MOMENT";


//    private String[] imagelist;

//    private String


    private Handler handler1;
    private String TAG_FATU = "TAG_FATU";
    private String TAG_FABU_IMFO = "TAG_FABU_IMFO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_pic_layout);

        fatu_bt1=(ImageView) findViewById(R.id.add_img1);

        fatu_bt2=(ImageView) findViewById(R.id.add_img2);

        fatu_bt3=(ImageView) findViewById(R.id.add_img3);

        fatu_bt4=(ImageView) findViewById(R.id.add_img4);

        fatu_bt5=(ImageView) findViewById(R.id.add_img5);
        fatu_bt6=(ImageView) findViewById(R.id.add_img6);

        fatu_bt7=(ImageView) findViewById(R.id.add_img7);

        fatu_bt8=(ImageView) findViewById(R.id.add_img8);






        fabu_bt=(Button) findViewById(R.id.post_pic);

        bt_back=(LinearLayout)  findViewById(R.id.PostPicGoBack);

        et_text=(EditText)  findViewById(R.id.customize_description);



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
    protected void initListener() {
        // TODO Auto-generated method stub
        fatu_bt1.setOnClickListener(this);
        fabu_bt.setOnClickListener(this);
        bt_back.setOnClickListener(this);


        fatu_bt2.setOnClickListener(this);

        fatu_bt3.setOnClickListener(this);

        fatu_bt4.setOnClickListener(this);
        fatu_bt5.setOnClickListener(this);
        fatu_bt6.setOnClickListener(this);
        fatu_bt7.setOnClickListener(this);
        fatu_bt8.setOnClickListener(this);



        //todo   上下装套装选择。

    }

    protected void initData() {
//        // TODO Auto-generated method stub
//        c_type = getIntent().getStringExtra(ClassJumpTool.DATA_PACKET_NAME);
//        if (c_type != null)
//            coming_type = c_type;
//
//        isCollection = false;
    }





//高伟豪  更新ui
    protected void initUI()
    {
        // TODO Auto-generated method stub


        handler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:

                        setView(msg.obj);
                    break;

                }
            }
        };

    }




    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.add_img1:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==1)
                    exFatu();
                break;
            case R.id.add_img2:
                //todo 调用发图的函数
                Toast.makeText(getApplicationContext(),"#####调用发图按钮啊",Toast.LENGTH_SHORT);
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==2)
                    exFatu();

                break;
            case R.id.add_img3:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==3)
                    exFatu();

                break;
            case R.id.add_img4:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==4)
                    exFatu();

                break;
            case R.id.add_img5:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==5)
                    exFatu();

                break;
            case R.id.add_img6:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==6)
                    exFatu();

                break;
            case R.id.add_img7:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==7)
                    exFatu();

                break;
            case R.id.add_img8:
                //todo 调用发图的函数
                System.out.println("#####调用发图按钮啊");
                if(CURRENT==8)
                    exFatu();

                break;

            case R.id.post_pic:
                //todo 调用发布的函数
                System.out.println("#####调用发布按钮啊");

                exFabu();


                break;


            case R.id.PostPicGoBack:
                //starttobackactivity 的方法是 自动终结当前activity 会退到第二个acitvity 传递一个data 可以设置为null当没有的时候
                ClassJumpTool.startToBackActivity(this, MainActivity.class,null, 10);



                break;

        }
    }



//
//    @Override
//    public void onFinish() {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onFinish(String img_url) {
//        // TODO Auto-generated method stub
////        LogHelper.d("-----下载成功:本地地址" + img_url);
////            // UserImg=img_url;
////            url_ls.clear();
////            thirdLoginBean.setUserImg(img_url);
////        handler.sendEmptyMessage(8);
//
//    }


//    @Override
//    public void onFailed() {
//        // TODO Auto-generated method stub
//        LogHelper.d("-----下载失败");
//    }



    /**
     * @Title: setView
     * @Description: TODO 更新显示数据
     * @param @param user 设定文件
     * @return void 返回类型
     * @throws
     */
    private void setView(Object obj) {
        System.out.println("#####更新当前ui");


//        if (!StringUtil.isNull(obj.toString())
        if(CURRENT==1)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt1);
            fatu_bt2.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==2)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt2);
            fatu_bt3.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==3)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt3);
            fatu_bt4.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==4)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt4);
            fatu_bt5.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==5)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt5);
            fatu_bt6.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==6)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt6);
            fatu_bt7.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==7)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt7);
            fatu_bt8.setVisibility(View.VISIBLE);


        }
        else if(CURRENT==8)
        {
            ImageLoader.getInstance().displayImage(obj.toString(), fatu_bt8);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "最多只能上传八张图",Toast.LENGTH_SHORT);

        }

        System.out.println("当前的current"+CURRENT);
        CURRENT++;
        imagelist.add(obj.toString());

        //// TODO: 2017/1/16  要把上传的图片往后放
        System.out.println("######添加一张图片成功:"+obj.toString());



    }





    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("#####   回退到minefragment");

    }




    /**
     * 发布图片
     */
    public  void  exFabu()
    {


        if (Utility.isFastDoubleClick()) // 连续点击
            return;
        if (imagelist.size()==0)
        {
            ToastHelper.show(this, "请先上传图片");
            return;
        }
        String tag="上装";
        String text="";
        String imgs="";
        text = et_text.getText().toString().trim();


        if (imagelist.size()==1)
            imgs+=imagelist.get(0);
        else {
            for (int i = 0; i < imagelist.size() - 1; i++)
                imgs += imagelist.get(i) + ",";
            imgs+=imagelist.get(imagelist.size()-1);
        }


        System.out.println("#####发图"+text);
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                    TAG_FABU_MOMENT, Constant.REQUEST_POST, ParamsTools.fabu(
                    UrlManager.Fabu_Moment(), tag, text,imgs), FatuActivity.this,
                    "发图中...", false));




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
                        result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLMoment, path);
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
                        result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLMoment, newUri);
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
                setMsg(0,json_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("!!!!!!!!!!走完页面加载程序");
    }

    private void setMsg(int msg_id, Object object) {
        Message message = handler1.obtainMessage();
        message.what = msg_id;
        message.obj = object;
        handler1.sendMessage(message);
    }



/**********************继承的接口****************************/


    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
//        dissMissDialog(isShowDiolog);
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);

        ToastHelper.show(this, callBackMsg+"  来自tag  "+resultTag);

        if (resultTag.equals(TAG_FABU_MOMENT)) {
            System.out.println("######成功了callback");
            ClassJumpTool.startToBackActivity(this, MainActivity.class,null, 10);
            ToastHelper.show(this, "发布成功");
        }


    }


//    @Override
//    public void startCallBack(String resultTag, final boolean isShowDiolog,
//                              final String showTitle) {
//        // TODO Auto-generated method stub
//        this.runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
////                startDialog(isShowDiolog, showTitle);
//            }
//        });
//
//    }

//    @Override
//    public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
//        // TODO Auto-generated method stub
//        System.out.println("baseactivity里面---reLoginCallBack-");
//    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean,
                                 String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);

        // dissMissDialog(isShowDiolog);
//        if (resultTag.equals(TAG_UOLOAD)) {
////            upload_imag_fail(callBackMsg, resultTag);
//        }
        if (resultTag.equals(TAG_FABU_MOMENT)) {
            System.out.println("######failShowCallBack ");
            ToastHelper.show(this, "图片发布失败");
        }
    }

//    public void cancelCallBack(String resultTag) {
//        // TODO Auto-generated method stub
//    }

    public void failCallBack(Throwable arg0, String resultTag,
                             boolean isShowDiolog) {
        // TODO Auto-generated method stub
        super.failCallBack(arg0, resultTag, isShowDiolog);

        if (resultTag.equals(TAG_FABU_MOMENT) ) {
            System.out.println("#####失败:" + arg0.getMessage());
            ToastHelper.show(this, "失败");
        }


    }


/*************************************************/




}
