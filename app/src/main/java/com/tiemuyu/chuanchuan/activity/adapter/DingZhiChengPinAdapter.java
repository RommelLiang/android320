package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.DIngzhi;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/23.
 */

public class DingZhiChengPinAdapter extends BaseExpandableListAdapter {
    private List<DIngzhi.DataBean> dIngzhiData;
    private Context context;

    public DingZhiChengPinAdapter(List<DIngzhi.DataBean> dIngzhiData, Context context) {
        this.dIngzhiData = dIngzhiData;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return dIngzhiData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dIngzhiData.get(groupPosition).getAppdingzhilist().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentHolder parentHolder = null;
        DIngzhi.DataBean dataBean = dIngzhiData.get(groupPosition);
        if(convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.item_parent, null);
            convertView.setHorizontalScrollBarEnabled(true);

            parentHolder = new ParentHolder();
            convertView.setTag(parentHolder);

        } else {
            parentHolder = (ParentHolder) convertView.getTag();
        }
        Log.e("brandName", "getGroupView: " +dataBean.getName());
        parentHolder.brandName = (TextView) convertView.findViewById(R.id.text_brand);
        parentHolder.brandName.setText(dataBean.getName());
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    private static class ParentHolder {
        TextView brandName;
    }
}
