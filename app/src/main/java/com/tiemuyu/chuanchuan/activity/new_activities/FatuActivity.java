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
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.GetPathFromUri4kitkat;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.adapter.ChildAdapter;
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
import com.tiemuyu.chuanchuan.activity.util.PicassoImageLoader;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.ServerUtils;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.util.Utility;
import com.tiemuyu.chuanchuan.activity.view.ClearEditText;
import com.tiemuyu.chuanchuan.activity.view.HorizontalListVIew;

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

import it.sephiroth.android.library.util.ViewHelperFactory;

/**
 * Created by CC2.0 on 2017/1/16.
 */

public class FatuActivity extends BaseActivityG {
    private static final int IMAGE_PICKER = 255;

//两个按钮


    @ViewInject(R.id.post_pic)
    private Button fabu_bt;// 发布按钮


    @ViewInject(R.id.PostPicGoBack)
    private LinearLayout bt_back;// 回退


    @ViewInject(R.id.customize_description)
    private TextView et_text;// 说明输入框

    //private ImageView add_img1;
    private HorizontalListVIew horizontalListVIew;
    ArrayList<ImageItem> mImages;


    private int CURRENT = 1;
    private EditText tv_message;
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_FROM_ALBUM = 2;
    public Uri imageUri;
    public String result = "";

    private IntentFilter myIntentFilter;

    private List<String> imagelist = new ArrayList<String>();


    private String TAG_FABU_MOMENT = "TAG_FABU_MOMENT";


//    private String[] imagelist;

//    private String


    private Handler handler1;
    private String TAG_FATU = "TAG_FATU";
    private String TAG_FABU_IMFO = "TAG_FABU_IMFO";
    private ImagePicker imagePicker;
    private ImageView add_img1;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_pic_layout);

        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

        fabu_bt = (Button) findViewById(R.id.post_pic);

        bt_back = (LinearLayout) findViewById(R.id.PostPicGoBack);

        et_text = (TextView) findViewById(R.id.customize_description);

        add_img1 = (ImageView) findViewById(R.id.add_img1);
        add_img1.setOnClickListener(this);
        horizontalListVIew = (HorizontalListVIew) findViewById(R.id.listview_horizon);
        /*ImageItem imageItem = new ImageItem();
        mImages.add(imageItem)*/
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //  Constant.VERSION = Version.getAppVersionName(this);
        // _global = GlobalVariable.getInstance();
        tv_message = (EditText) findViewById(R.id.message);
        initProcess();


    }


    /**
     * 加载的流程
     */
    protected void initProcess() {
        initData();
        initAppAccess();
        initListener();
    }


    /**
     * 实例化访问记录
     */
    protected void initAppAccess() {

    }

    protected void initListener() {
        // TODO Auto-generated method stub
        fabu_bt.setOnClickListener(this);
        bt_back.setOnClickListener(this);


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


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {


            case R.id.post_pic:
                //todo 调用发布的函数
                System.out.println("#####调用发布按钮啊");

                exFabu();


                break;


            case R.id.PostPicGoBack:
                ClassJumpTool.startToBackActivity(this, MainActivity.class, null, 10);
                break;
            case R.id.add_img1:

                if (mImages != null && mImages.size() == 9) {
                    Toast.makeText(this, "你已经选择了九张图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
                break;

        }
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
    public void exFabu() {
        if (imagelist == null || mImages == null) {
            Toast.makeText(this, "请选择图片", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imagelist.size() < mImages.size()) {
            ToastHelper.show(this, "网络较差，请稍后重试");
            return;
        }
        if (Utility.isFastDoubleClick()) // 连续点击
            return;
        if (imagelist.size() == 0) {
            ToastHelper.show(this, "请先上传图片");
            return;
        }
        String tag = "上装";
        String text = "";
        String imgs = "";
        String s = String.valueOf(tv_message.getText());
        text = s.toString().trim() + "";


        if (imagelist.size() == 1)
            imgs += imagelist.get(0);
        else {
            for (int i = 0; i < mImages.size() - 1; i++)
                imgs += imagelist.get(i) + ",";
            imgs += imagelist.get(imagelist.size() - 1);
        }


        System.out.println("#####发图" + text);
        MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
                TAG_FABU_MOMENT, Constant.REQUEST_POST, ParamsTools.fabu(
                UrlManager.Fabu_Moment(), tag, text, imgs), FatuActivity.this,
                "发图中...", false));


    }


    /**
     * 发图
     */
    public void exFatu() {
        if (Utility.isFastDoubleClick()) // 连续点击
            return;
        System.out.println("**************调用photoMOMENT");
        AlertDialog.Builder multiDia = new AlertDialog.Builder(this);
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
        /*if (resultCode != Activity.RESULT_OK) {
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
        }*/
        //
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", requestCode + "onActivityResult: " + resultCode + data);
        if (resultCode == 1004) {
            Log.e("resultCode", "onActivityResult: " + "1111111");
            if (data != null && requestCode == 255) {
                ArrayList<ImageItem> list = new ArrayList<>();
                list = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (mImages == null) {
                    mImages = new ArrayList<>();
                    mImages.addAll(list);
                } else {
                    boolean isSelect;
                    for (int i = 0; i < list.size(); i++) {
                        isSelect = false;
                        for (int i1 = 0; i1 < mImages.size(); i1++) {
                            if (mImages.get(i1).path.equals(list.get(i).path)) {
                                isSelect = true;
                            }
                        }
                        if (!isSelect) {
                            mImages.add(list.get(i));
                        }
                    }
                }

                if (imagelist != null || imagelist.size() != 0) {
                    imagelist.clear();
                }
                Log.e("ArrayList", "onActivityResult: " + mImages.size());
                adapter = new MyAdapter(mImages);
                horizontalListVIew.setAdapter(adapter);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < mImages.size(); i++) {
                            Log.e("path", "onActivityResult: " + mImages.get(i).path);
                            /*String path = GetPathFromUri4kitkat.getPath(FatuActivity.this, Uri.parse(mImages.get(i).path));
                            Log.e("after path", "onActivityResult: "+path);*/
                            result = ServerUtils.formUpload(com.tiemuyu.chuanchuan.activity.view.URL.UPLOAD_URLMoment, mImages.get(i).path);
                            Log.e("result", "onActivityResult: " + result);
                            if (!TextUtils.isEmpty(result)) {
                                System.out.println("******进入非空判断");
                                handler.sendEmptyMessage(1);
                                System.out.println(result);//成功
                            } else {
                                handler.sendEmptyMessage(2);//失败
                            }
                        }
                    }
                }).start();

            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
        if (mImages != null && mImages.size() != 0) {
            add_img1.setVisibility(View.GONE);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    System.out.println("*******图片上传成功");
                    addPageLoad();
                    break;
                case 2:
                    Log.e("图片上传失败", "handleMessage: ");
                    System.out.println("*******图片上传失败");
                    break;
                default:
                    break;
            }
        }
    };

    public void addPageLoad() {
        if (!TextUtils.isEmpty(result)) {
            System.out.println(result + "@@@@基类@@成功上传图片");
            //String str = "{\"Code\":1,\"Msg\":\"OK\",\"Data\":{\"ImageUrl\":\"http://f1.myappcc.com/zfs/7E0/1240/RIC/240174344351CABGTOTZGT.jpg\"}}";
            try {
                JSONObject jsonObject = new JSONObject(result);
                String s_data = jsonObject.getString("Data");
                JSONObject jsonObject1 = new JSONObject(s_data);
                final String json_url = jsonObject1.getString("ImageUrl");
                System.out.println(json_url);
                //todo 把json_url发送给ui重置用个handler
                imagelist.add(json_url);
                setMsg(0, json_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("!!!!!!!!!!走完页面加载程序");
    }

    private void setMsg(int msg_id, Object object) {
        /*Message message = handler1.obtainMessage();
        message.what = msg_id;
        message.obj = object;
        handler1.sendMessage(message);*/
    }


    /**********************
     * 继承的接口
     ****************************/


    @Override
    public void successCallBack(String resultTag, BaseBean baseBean,
                                String callBackMsg, boolean isShowDiolog) {
        // TODO Auto-generated method stub
//        dissMissDialog(isShowDiolog);
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);

        if (resultTag.equals(TAG_FABU_MOMENT)) {
            System.out.println("######成功了callback");
            ClassJumpTool.startToBackActivity(this, MainActivity.class, null, 10);
            ToastHelper.show(this, "发布成功");
            finish();
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

        if (resultTag.equals(TAG_FABU_MOMENT)) {
            System.out.println("#####失败:" + arg0.getMessage());
            ToastHelper.show(this, "失败");
        }


    }


    /*************************************************/

    private class MyAdapter extends BaseAdapter {

        private List<ImageItem> items;

        public MyAdapter(List<ImageItem> items) {
            this.items = items;
        }

        public void setData(List<ImageItem> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return items.size() + 1;
        }

        @Override
        public ImageItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ChildHolder childHolder;
            int width = add_img1.getWidth();
            Log.e("width", "getView: " + width);
            if (convertView == null) {
                childHolder = new ChildHolder();
                convertView = LayoutInflater.from(FatuActivity.this).inflate(R.layout.image_layout, parent, false);
                childHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
                childHolder.delect = (ImageView) convertView.findViewById(R.id.delect);
                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }
            if (position == items.size()) {
                Picasso.with(FatuActivity.this)
                        .load(R.drawable.add_image)
                        .resize(width-30, width-30)
                        .into(childHolder.imageView);
                childHolder.delect.setVisibility(View.GONE);
                childHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mImages.size() != 9) {
                            Intent intent = new Intent(FatuActivity.this, ImageGridActivity.class);
                            startActivityForResult(intent, IMAGE_PICKER);
                        } else {
                            Toast.makeText(FatuActivity.this, "您已选择了九张图片", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return convertView;

            }
            if (position < items.size()) {
                //Picasso.
                (new PicassoImageLoader()).displayImage(FatuActivity.this, items.get(position).path, childHolder.imageView, width, width);

                childHolder.delect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("delect", "getView: "+mImages.size());
                        if (mImages.size() == 1) {
                            mImages.clear();
                            //add_img1.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        } else if (imagelist.size() == mImages.size()) {
                            mImages.remove(position);
                            imagelist.remove(position);
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastHelper.show(FatuActivity.this,"请稍候重试");
                        }

                    }
                });
            }
            return convertView;
        }


    }

    private static class ChildHolder {
        ImageView imageView, delect;
    }
}
