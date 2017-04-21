package com.tiemuyu.chuanchuan.activity.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class AppManager {



    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    //遍历所有Activity并finish
    public void exit(){
        for(Activity activity:activityStack) {
            activity.finish();
        }
        System.exit(0);
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取上一个Activity（堆栈中前一个压入的）
     */
    public Activity aboveActivity() {

        Activity activity = activityStack.get(activityStack.size()-2);
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定Activity后的所有Actiivty
     */
    public void finishActivityByPosition(Activity activity) {
        int position = activityStack.search(activity);
        position = (activityStack.size() - position);
        List<Activity> activities = new ArrayList<Activity>();
        for (int i = position + 1; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                Activity activity1 = activityStack.get(i);
                activity1.finish();
                activities.add(activity1);
            }
        }
        activityStack.removeAll(activities);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing())
                activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Stack<Activity> tempStack = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                if (!activity.isFinishing())
                    activity.finish();
                tempStack.add(activity);
            }
        }
        activityStack.removeAll(tempStack);
    }

    /**
     * 结束指定类名的Activity
     */
    public boolean hasCurrActivity(Class<?> cls) {
        //Stack<Activity> tempStack = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        //activityStack.removeAll(tempStack);
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        while (true) {
            if (activityStack.size() == 0) {
                break;
            }
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            finishActivity(activity);
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
//            MobclickAgent.onKillProcess(context);// 保存友盟统计事件
//            MyApplication.exs.clear();
//            MyApplication.poolManager.stop();// 停止轮询
//            SettingActivity.removeCookie(context);
//
//            ConnectReceiver.cancelToast();

            finishAllActivity();

            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            int currentVersion = android.os.Build.VERSION.SDK_INT;
            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startMain);
                activityMgr.killBackgroundProcesses(context.getPackageName());
            } else {// android2.1
                activityMgr.restartPackage(context.getPackageName());
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            activityMgr.killBackgroundProcesses(context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
         //   LogHelper.i("退出程序异常");//高伟豪
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    //用浏览器打开
    public static void openBrowser(Context context,String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static void setActivityStack(Stack<Activity> activityStack) {
        AppManager.activityStack = activityStack;
    }






}
