package com.tiemuyu.chuanchuan.activity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiemuyu.chuanchuan.activity.R;

/**
 * @项目名： android1128
 * @包名： com.tiemuyu.chuanchuan.activity.fragment
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/1/5
 * @version：
 */

public class DingzhichengpinFragment extends BaseFragment {

    SharedPreferences sp;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.dingzhichengpin_fragment, null);//需要根据fragment的布局进行修改
        return view;
    }

}
