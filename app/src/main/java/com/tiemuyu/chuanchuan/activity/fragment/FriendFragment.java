package com.tiemuyu.chuanchuan.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tiemuyu.chuanchuan.activity.view.URL;

/**
 * Created by Lonze on 2016/8/23.
 */
public class FriendFragment extends BaseFragment {
    //没用
    public boolean isFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isFirst) {
            webView.loadUrl(URL.UrlLogin);
            isFirst = false;
        }
    }

    public void goBack(){
        if(webView.canGoBack()){
            webView.goBack();
        }
    }

}
