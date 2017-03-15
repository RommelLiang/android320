package com.tiemuyu.chuanchuan.activity.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.fragment.HomeFragment;
import com.tiemuyu.chuanchuan.activity.view.CustomButton;

/**
 * Created by Lonze on 2016/8/20.
 */
public class ViewTools {

    public static void showView(CustomButton button,CustomButton[] arr){
        button.setSelected(true);
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != button){
               arr[i].setSelected(false);
            }
        }
    }

    public static void changeTitle(View header,View search, String title){
        //去掉header的方法在我的页面调用了
        if("".equals(title)){
            System.out.println("@@@@我的");
            search.setVisibility(View.GONE);
            header.setVisibility(View.GONE);
        }
        else if(title != null) {
            header.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
            TextView textView = (TextView) ((RelativeLayout) header).getChildAt(1);
            textView.setText(title);
        }
        else {
            search.setVisibility(View.VISIBLE);
            header.setVisibility(View.GONE);
        }
    }

    public static void changeTitle(View header,View search, String title,boolean back){
        if("".equals(title)){
            search.setVisibility(View.GONE);
            header.setVisibility(View.GONE);
        }
        else if(title != null){
            header.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
            TextView textView = (TextView) ((RelativeLayout)header).getChildAt(1);
            LinearLayout imageView = (LinearLayout) ((RelativeLayout)header).getChildAt(0);
            if(back) {
                imageView.setVisibility(View.VISIBLE);
            } else
                imageView.setVisibility(View.GONE);
            textView.setText(title);
        }
        else {
            search.setVisibility(View.VISIBLE);
            header.setVisibility(View.GONE);
        }
    }

    public static boolean isTopTask(String[] strs,String str){
        boolean is = false;
        for(int i = 0; i < strs.length; i++){
            if(str.equals(strs[i])){
                is = true;
            }
        }
        return is;
    }
    public static void showSetting(String[] strs,String str,View view){
       if(str.equals(strs[3])){
           view.setVisibility(View.VISIBLE);
       }else {
           view.setVisibility(View.GONE);
       }
    }

    //gao 添加右上角文字方法
    public static void showText   (View header,View search, String title,boolean yes){
        if (yes) {
            System.out.println("&&&&&&&&&进入viewtools的 showtext");
            TextView textView2 = (TextView) ((RelativeLayout) header).findViewById(R.id.right_text);//right_text
            textView2.setText(title);
            textView2.setVisibility(View.VISIBLE);
        }
        else {
            System.out.println("show text equal false");
            TextView textView2 = (TextView) ((RelativeLayout) header).findViewById(R.id.right_text);//right_text
            textView2.setVisibility(View.GONE);
        }
    }

}
