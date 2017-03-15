package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.TiuKuanMessage;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/10.
 */

public class TuiKuanJinDuAdapter extends BaseAdapter {
    private List<TiuKuanMessage.DataBean.OrderRefundRecordBean> orderRefundRecord;
    private Context context;
    private ChildHolder childHolder;

    public TuiKuanJinDuAdapter(List<TiuKuanMessage.DataBean.OrderRefundRecordBean> orderRefundRecord, Context context) {
        this.orderRefundRecord = orderRefundRecord;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orderRefundRecord.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.jin_du_layout, parent, false);
            childHolder.one = (TextView) convertView.findViewById(R.id.tv_reson);
            childHolder.two = (TextView) convertView.findViewById(R.id.tv_time);
            childHolder.v_one = convertView.findViewById(R.id.view_one);
            childHolder.v_two = convertView.findViewById(R.id.view_two);
            convertView.setTag(childHolder);

        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        if (position == 0) {
            childHolder.v_one.setVisibility(View.GONE);
        } else {
            childHolder.v_one.setVisibility(View.VISIBLE);
        }
        if (position == orderRefundRecord.size() - 1) {
            childHolder.v_two.setVisibility(View.GONE);
        } else {
            childHolder.v_two.setVisibility(View.VISIBLE);
        }
        childHolder.one.setText(orderRefundRecord.get(position).getRecord());
        childHolder.two.setText(orderRefundRecord.get(position).getActionTime());
        return convertView;
    }

    private static class ChildHolder {
        TextView one;
        TextView two;
        View v_one;
        View v_two;
    }
}
