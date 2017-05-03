package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.CoinBean;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/28.
 */

public class ZiChanAdapter extends BaseAdapter {
    private Context context;
    private List<CoinBean.DataBean.RowsBean> rowsBeen;
    private LayoutInflater layoutInflater;

    public ZiChanAdapter(Context context, List<CoinBean.DataBean.RowsBean> rowsBeen) {
        this.context = context;
        this.rowsBeen = rowsBeen;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return rowsBeen.size();
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
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.zi_chan_detail, parent, false);
            view.setTag(holder);
            holder.tv_change = (TextView) view.findViewById(R.id.tv_change);
            holder.tv_detail = (TextView) view.findViewById(R.id.tv_detail);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_detail.setText(rowsBeen.get(position).getSummary());
        if (rowsBeen.get(position).getTradeCoin() < 0) {
            holder.tv_change.setText(rowsBeen.get(position).getTradeCoin() + "");
        } else {
            holder.tv_change.setText("+" + rowsBeen.get(position).getTradeCoin() + "");
        }
        holder.tv_time.setText(rowsBeen.get(position).getTradeTime());
        return view;
    }
    private class ViewHolder {
        public TextView tv_change;
        public TextView tv_detail;
        public TextView tv_time;
    }
}
