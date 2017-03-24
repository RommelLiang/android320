package com.tiemuyu.chuanchuan.activity.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tiemuyu.chuanchuan.activity.R;


/**
 * Created by 梁文硕 on 2017/3/20.
 */

public class GuideHelper {
    private Activity context;
    private ViewGroup rootLayout;
    private static final String GUIDE_VERSION_NAME = "GUIDEVERSION";
    private static final int  GUIDE_VERSION_CODE = 1;
    private int count = 1;
    private ImageView im_one,im_two,im_three,im_four,im_five;
    private RelativeLayout rl_all;
    public GuideHelper(Context context){
        this.context = (Activity)context;
        createGuideLayout();
        initGuideView();
    }

    /**
     * @Description: 创建引导图层
     * @param @return
     * @return ViewGroup
     * @throws
     */
    private void createGuideLayout(){
        ViewGroup rootView = (ViewGroup) context.getWindow().getDecorView();
        LayoutInflater lf = context.getLayoutInflater();
        rootLayout = (ViewGroup) lf.inflate(R.layout.guide_helper, null);
        rl_all = (RelativeLayout) rootLayout.findViewById(R.id.rl_all);
        im_one = (ImageView) rootLayout.findViewById(R.id.im_one);
        im_two = (ImageView) rootLayout.findViewById(R.id.im_two);
        im_three = (ImageView) rootLayout.findViewById(R.id.im_three);
        im_four = (ImageView) rootLayout.findViewById(R.id.im_four);
        im_five = (ImageView) rootLayout.findViewById(R.id.im_five);
        rootView.addView(rootLayout);
    }

    /**
     * @Description: 初始化引导视图
     * @param
     * @return void
     * @throws
     */
    public void initGuideView() {
        rl_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1) {
                    count++;
                    im_one.setVisibility(View.GONE);
                    im_two.setVisibility(View.GONE);
                    im_three.setVisibility(View.VISIBLE);
                    im_four.setVisibility(View.VISIBLE);
                    im_five.setVisibility(View.VISIBLE);
                } else {
                    closeGuide();
                }
            }
        });
    }

    /**
     * @Description: 生成每个引导视图
     * @param @param resId
     * @param @return
     * @return View
     * @throws
     */
    public View makeGuideView(int resId){
        ImageView guideView = new ImageView(context);
        guideView.setImageResource(resId);
        guideView.setPadding(10, 10, 10, 10);
        return guideView;
    }

    /**
     * @Description: 打开引导层
     * @param
     * @return void
     * @throws
     */
    public void openGuide(){
        if(guideCheck()){
            rootLayout.setVisibility(View.VISIBLE);
        } else {
            rootLayout.setVisibility(View.GONE);
        }
    }

    /**
     * @Description: 关闭引导层
     * @param
     * @return void
     * @throws
     */
    public void closeGuide(){

        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.2f);
        alphaAnim.setDuration(500);
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(500);
        AnimationSet AnimSet = new AnimationSet(false);
        AnimSet.setDuration(500);
        AnimSet.addAnimation(scaleAnim);
        AnimSet.addAnimation(alphaAnim);

        AnimSet.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rootLayout.clearAnimation();
                rootLayout.setVisibility(View.GONE);
                saveGuideVersion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        rootLayout.startAnimation(AnimSet);
    }

    /**
     * @Description: 保存引导版本记录
     * @param
     * @return void
     * @throws
     */
    private void saveGuideVersion() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(GUIDE_VERSION_NAME, GUIDE_VERSION_CODE);
        edit.commit();
    }

    /**
     * @Description: 检测引导图版本，判断是否启动引导
     * @param @return
     * @return boolean
     * @throws
     */
    private boolean guideCheck(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        int guideVer = sp.getInt(GUIDE_VERSION_NAME, 0);
        Log.e("guideCheck: ", "guideVer:" + guideVer+"  GUIDE_VERSION_CODE:" + GUIDE_VERSION_CODE);
        if(GUIDE_VERSION_CODE > 0 && GUIDE_VERSION_CODE > guideVer){
            return true;
        } else {
            return false;
        }
    }
}
