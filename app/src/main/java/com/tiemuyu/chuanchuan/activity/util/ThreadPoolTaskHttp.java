package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;

import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;

import org.xutils.http.RequestParams;

//import com.lidroid.xutils.http.RequestParams;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class ThreadPoolTaskHttp extends ThreadPoolTask {
    private HttpCallBack callBack;
    private Context context;
    private boolean isShowDialog;
    private String title;

    /**
     * @param context
     *  url 请求地址
     * @param tag 请求标识
     * @param action 请求方式,1 get;0 post;
     * @param params 请求参数
     * @param callBack 请求回调
     * @param title 请求对话框显示的title
     * @param isShowDialog 是否显示对话框
     */
    public ThreadPoolTaskHttp(Context context, String tag, int action,
                              RequestParams params, HttpCallBack callBack, String title, boolean isShowDialog) {
        super( tag, action, params);
        // TODO Auto-generated constructor stub
        this.callBack=callBack;
        this.context=context;
        this.isShowDialog=isShowDialog;
        this.title=title;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (!ConnectionUtil.isConn(context)) {
            ToastHelper.show(context, "亲，网络断了哦，请检查网络设置");
            return;
        }
        new HttpTools(context,callBack, tag, params, action,title,isShowDialog);
    }


    public interface HttpCallBack{

        //gao 创建了父类的接口
        public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog);/** 请求成功 */
//		public void loadingCallBack(long total, long current, boolean isUploading,
//				String resultTag);/** 加载中 */
        public void failCallBack(Throwable arg0, String resultTag,boolean isShowDiolog);/**请求失败*/
        public void startCallBack(String resultTag,boolean isShowDiolog,String showTitle); /** 开始*/
        public void cancelCallBack(String resultTag);/**取消*/
        public void failShowCallBack(String resultTag, BaseBean baseBean,String callBackMsg,boolean isShowDiolog);/** 请求成功，但code!=1 */

        public void reLoginCallBack(String resultTag,boolean isShowDiolog);/** 请求成功,但要重新登录 */
    }


}
