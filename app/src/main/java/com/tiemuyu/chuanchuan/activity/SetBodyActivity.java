package com.tiemuyu.chuanchuan.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.BodyDataBean;
import com.tiemuyu.chuanchuan.activity.bean.PersonInfoBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class SetBodyActivity extends BaseActivityG {
    private int edit_tag;//0表示按钮显示修改，1表示按钮显示保存
    private static final String TAG_GETBODYINFO = "TAG_GETBODYINFO";

    private BodyDataBean publicBodyDataBean;

    //private ProgressDialog pd;
    private LinearLayout go_bk;
    private TextView save_body_data;
    private ImageView male_btn;
    private ImageView female_btn;
    private TextView usr_age;
    private TextView usr_weight;
    private TextView usr_height;
    private Spinner size_spinner;
    private Spinner shape_spinner;
    private Button edit_body;

    //sl: 十个身体数据各自对应的文本输入框
    private EditText jiankuan;
    private EditText datuiwei;
    private EditText xiongwei;
    private EditText xiaotuiwei;
    private EditText yaowei;
    private EditText xiwei;
    private EditText tunwei;
    private EditText jiaowei;
    private EditText xiuchang;
    private EditText kuchang;
    private String id;
    private int gender;
    private String[] sizes;
    private String size;
    private String[] shapes;
    private String shape;
    private Button one,two,three;
    private int xiguan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_set_body);
        id = getIntent().getStringExtra("id");
        /*pd = new ProgressDialog(this);//加载的ProgressDialog
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//选择加载风格 这里是圆圈 STYLE_HORIZONTAL 是水平进度条
        pd.setMessage("加载中....");
        pd.show();
        xiguan = 1;*/
        //sl:给控件赋值
        go_bk = (LinearLayout) findViewById(R.id.body_back);
        save_body_data = (TextView) findViewById(R.id.body_size_save);
        male_btn = (ImageView) findViewById(R.id.male_btn);
        female_btn = (ImageView) findViewById(R.id.female_btn);
        usr_age = (TextView) findViewById(R.id.usr_age);
        usr_weight = (TextView) findViewById(R.id.usr_weight);
        usr_height = (TextView) findViewById(R.id.usr_height);
        size_spinner = (Spinner) findViewById(R.id.size_spinner);
        shape_spinner = (Spinner) findViewById(R.id.shape_spinner);
        edit_body = (Button) findViewById(R.id.edit_body);
        edit_tag = 0;

        //sl: 给10个身体数据文本框赋控件
        jiankuan = (EditText) findViewById(R.id.jiankuan);
        datuiwei = (EditText) findViewById(R.id.datuiwei);
        xiongwei = (EditText) findViewById(R.id.xiongwei);
        xiaotuiwei = (EditText) findViewById(R.id.xiaotuiwei);
        yaowei = (EditText) findViewById(R.id.yaowei);
        xiwei = (EditText) findViewById(R.id.xiwei);
        tunwei = (EditText) findViewById(R.id.tunwei);
        jiaowei = (EditText) findViewById(R.id.jiaowei);
        xiuchang = (EditText) findViewById(R.id.xiuchang);
        kuchang = (EditText) findViewById(R.id.kuchang);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        final Resources resources = this.getResources();
        final Drawable button = resources.getDrawable(R.drawable.button);
        final Drawable button_no = resources.getDrawable(R.drawable.button_no);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setBackground(button);
                two.setBackground(button_no);
                three.setBackground(button_no);
                one.setTextColor(resources.getColor(R.color.ColorLaunchBackground));
                two.setTextColor(resources.getColor(R.color.ColorTabbar));
                three.setTextColor(resources.getColor(R.color.ColorTabbar));
                xiguan = 1;
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setBackground(button_no);
                two.setBackground(button);
                three.setBackground(button_no);
                one.setTextColor(resources.getColor(R.color.ColorTabbar));
                two.setTextColor(resources.getColor(R.color.ColorLaunchBackground));
                three.setTextColor(resources.getColor(R.color.ColorTabbar));
                xiguan = 2;
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setBackground(button_no);
                two.setBackground(button_no);
                three.setBackground(button);
                one.setTextColor(resources.getColor(R.color.ColorTabbar));
                two.setTextColor(resources.getColor(R.color.ColorTabbar));
                three.setTextColor(resources.getColor(R.color.ColorLaunchBackground));
                xiguan = 3;
            }
        });
        go_bk.setOnClickListener(new BodyBackClickListener());
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        TAG_GETBODYINFO,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.GET_MYBODY()),
                        this,
                        "获取water",
                        false
                ));

        save_body_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonInfoBean infoBean = new PersonInfoBean();
                Log.e("tag", "userID in body: " + DBTools.getUser().getUserId());//测试是否可以输出userID，测试通过
                //todo 给infobean赋值
                BodyDataBean tmp = publicBodyDataBean;
                BodyDataBean.DataBean dataBean = new BodyDataBean.DataBean();
                dataBean.setAge(Integer.parseInt(String.valueOf(usr_age.getText())));
                dataBean.setGender(gender);
                dataBean.setWeight(Double.valueOf(String.valueOf(usr_weight.getText())));
                dataBean.setHeight(Double.valueOf(String.valueOf(usr_height.getText())));
                dataBean.setClothSize(size);
                dataBean.setPhysique(shape);
                dataBean.setShoulderBreadth(Double.valueOf(String.valueOf(jiankuan.getText())));
                dataBean.setBust(Double.valueOf(String.valueOf(xiongwei.getText())));
                dataBean.setWaist(Double.valueOf(String.valueOf(yaowei.getText())));
                dataBean.setHip(Double.valueOf(String.valueOf(tunwei.getText())));
                dataBean.setSleeve(Double.valueOf(String.valueOf(xiuchang.getText())));
                dataBean.setThighCirc(Double.valueOf(String.valueOf(datuiwei.getText())));
                dataBean.setCalfCirc(Double.valueOf(String.valueOf(xiaotuiwei.getText())));
                dataBean.setKneeCirc(Double.valueOf(String.valueOf(xiwei.getText())));
                dataBean.setArmCirc(Double.valueOf(String.valueOf(jiaowei.getText())));
                dataBean.setPants(Double.valueOf(String.valueOf(kuchang.getText())));
                dataBean.setTEBIESHUOMING("");
                dataBean.setCHUANYIXIGUAN(xiguan);
                dataBean.setISRADIO(0);
                tmp.setData(dataBean);
                editBodyData(tmp);
            }
        });
        gender = 1;
        male_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_btn.setImageResource(R.drawable.male_on);
                female_btn.setImageResource(R.drawable.female_off);
                gender = 1;
            }
        });

        female_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_btn.setImageResource(R.drawable.male_off);
                female_btn.setImageResource(R.drawable.female_on);
                gender = 2;
            }
        });

        size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sizes = getResources().getStringArray(R.array.body_size);
                size = sizes[position];
                Log.i("info", sizes[position] + " is clicked!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        shape_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shapes = getResources().getStringArray(R.array.body_shape);
                shape = shapes[position];
                Log.i("info", shapes[position] + " is clicked!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        edit_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_tag == 0){
                    edit_body.setText("保存");
                    edit_tag = 1;
                    jiankuan.setFocusable(true);
                    datuiwei.setFocusable(true);
                    xiongwei.setFocusable(true);
                    xiaotuiwei.setFocusable(true);
                    yaowei.setFocusable(true);
                    xiwei.setFocusable(true);
                    tunwei.setFocusable(true);
                    jiaowei.setFocusable(true);
                    xiuchang.setFocusable(true);
                    kuchang.setFocusable(true);

                    jiankuan.setFocusableInTouchMode(true);
                    datuiwei.setFocusableInTouchMode(true);
                    xiongwei.setFocusableInTouchMode(true);
                    xiaotuiwei.setFocusableInTouchMode(true);
                    yaowei.setFocusableInTouchMode(true);
                    xiwei.setFocusableInTouchMode(true);
                    tunwei.setFocusableInTouchMode(true);
                    jiaowei.setFocusableInTouchMode(true);
                    xiuchang.setFocusableInTouchMode(true);
                    kuchang.setFocusableInTouchMode(true);

                    jiankuan.requestFocus();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                } else if (edit_tag == 1) {
                    edit_body.setText("修改");
                    edit_tag = 0;
                    jiankuan.setFocusable(false);
                    datuiwei.setFocusable(false);
                    xiongwei.setFocusable(false);
                    xiaotuiwei.setFocusable(false);
                    yaowei.setFocusable(false);
                    xiwei.setFocusable(false);
                    tunwei.setFocusable(false);
                    jiaowei.setFocusable(false);
                    xiuchang.setFocusable(false);
                    kuchang.setFocusable(false);
                }
            }
        });
    }

    private void editBodyData(BodyDataBean bean) {
        BodyDataBean.DataBean data = bean.getData();
        String json = "{\"OrderId\":\"" + id +
                "\",\"SkinColor\":\"" + "0" +
                "\",\"WaistPostion\":\"" + 0 +
                "\",\"BodyPic\":\"" + "0" +
                "\",\"CustomerLogo\":\"" + "0" +
                "\",\"CustomerName\":\"" + "0" +
                "\",\"BodySummary\":\"" + "0" +
                "\",\"BodyOther\":\"" + "0" +
                "\",\"DressEffect\":\"" + 0 +
                "\",\"Bmi\":\"" + data.getBmi() +
                "\",\"Gender\":\"" + gender +
                "\",\"Age\":\"" + data.getAge() +
                "\",\"Weight\":\"" + data.getWeight() +
                "\",\"Height\":\"" + data.getHeight() +
                "\",\"ClothSize\":\"" + "" +
                "\",\"Physique\":\"" + "" +
                "\",\"ShoulderBreadth\":\"" + data.getShoulderBreadth() +
                "\",\"Bust\":\"" + data.getBust() +
                "\",\"Waist\":\"" + data.getWaist() +
                "\",\"Hip\":\"" + data.getHip() +
                "\",\"Sleeve\":\"" + data.getSleeve() +
                "\",\"ThighCirc\":\"" + data.getThighCirc() +
                "\",\"CalfCirc\":\"" + data.getCalfCirc() +
                "\",\"KneeCirc\":\"" + data.getKneeCirc() +
                "\",\"ArmCirc\":\"" + data.getArmCirc() +
                "\",\"Pants\":\"" + data.getPants() +
                "\"}";
        Log.e("json", "editBodyData: "+json );
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        HttpTools.TAG_SET_BODY_DATA,
                        Constant.REQUEST_POST,
                        ParamsTools.orderBody(json),
                        this,
                        "设置身体数据",
                        false));
        Log.e("body", "post sent.");//打印提示信息
    }

    private void BodyAction(BodyDataBean bodyDataBean) {
        Log.i("INFO", "enter BodyAction!!");
        if (bodyDataBean.getData().getGender() == 1) { //sl：设置男女按钮背景图片
            male_btn.setImageResource(R.drawable.male_on);
            female_btn.setImageResource(R.drawable.female_off);
        } else {
            male_btn.setImageResource(R.drawable.male_off);
            female_btn.setImageResource(R.drawable.female_on);
        }
        //
        usr_age.setText("" + bodyDataBean.getData().getAge());
        usr_weight.setText("" + bodyDataBean.getData().getWeight());
        usr_height.setText("" + bodyDataBean.getData().getHeight());
        String[] arr_tmp = getResources().getStringArray(R.array.body_size);//sl:临时数组存储size和shape
        int position = 0;
        for (int i = 0; i < arr_tmp.length; i++) {//sl：通过循环得到尺码在数组中对应的位置
            if (arr_tmp[i].equals(bodyDataBean.getData().getClothSize())) {
                position = i;
                break;
            }
        }
        size_spinner.setSelection(position);//sl:将spinner默认值设置到和网络数据一致
        position = 0;
        arr_tmp = getResources().getStringArray(R.array.body_shape);
        for (int i = 0; i < arr_tmp.length; i++) {
            if (arr_tmp[i].equals(bodyDataBean.getData().getPhysique())) {//sl：通过循环得到尺码在数组中对应的位置
                position = i;
                break;
            }
        }
        shape_spinner.setSelection(position);//sl:将spinner默认值设置到和网络数据一致
        //sl: 加载图消失

        jiankuan.setText(bodyDataBean.getData().getShoulderBreadth() + "");
        datuiwei.setText(bodyDataBean.getData().getThighCirc() + "");
        xiongwei.setText(bodyDataBean.getData().getBust() + "");
        xiaotuiwei.setText(bodyDataBean.getData().getCalfCirc() + "");
        yaowei.setText(bodyDataBean.getData().getWaist() + "");
        xiwei.setText(bodyDataBean.getData().getKneeCirc() + "");
        tunwei.setText(bodyDataBean.getData().getHip() + "");
        jiaowei.setText(bodyDataBean.getData().getArmCirc() + "");
        xiuchang.setText(bodyDataBean.getData().getSleeve() + "");
        kuchang.setText(bodyDataBean.getData().getPants() + "");
       // pd.dismiss();
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        if (resultTag.equals(HttpTools.TAG_SET_BODY_DATA)) {
            Log.e("body", "set body data callback failed!");
            Log.e("body", arg0.getMessage());//post请求出错，打印错误信息
        }
    }

    @Override
    public void startCallBack(String resultTag, boolean isShowDialog, String showTitle) {
        super.startCallBack(resultTag, isShowDialog, showTitle);
    }

    @Override
    public void cancelCallBack(String resultTag) {
        super.cancelCallBack(resultTag);
    }

    @Override
    public void reLoginCallBack(String resultTag, boolean isShowDialog) {
        super.reLoginCallBack(resultTag, isShowDialog);
    }

    // sl: 请求成功的回调函数
    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        if(resultTag.equals(TAG_GETBODYINFO)) {
            Log.e("body", "body data call back succeed: " + callBackMsg);
            Gson gson = new Gson();
            BodyDataBean bodyDataBean = gson.fromJson(callBackMsg, BodyDataBean.class);
            publicBodyDataBean = bodyDataBean;
            Log.i("INFO", "user phone num: " + bodyDataBean.getData().getUpdateUsername());//sl: 测试输出
            BodyAction(bodyDataBean);
        } if (resultTag.equals(HttpTools.TAG_SET_BODY_DATA)) {
            Log.e("BODY", "modify body callback success!");
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            ClassJumpTool.startToNextActivityForResult(this, UserOrderActivity.class, 10);
            finish();
        }
    }

    @Override
    public void failShowCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("failShowCallBack: ", callBackMsg);
    }

    class BodyBackClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.body_back)
                finish();
            else
                Log.e("TAG", "nothing happened");
        }
    }
}
