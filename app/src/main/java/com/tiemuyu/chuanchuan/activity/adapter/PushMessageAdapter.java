package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.PushBean;
import com.tiemuyu.chuanchuan.activity.bean.PushBeanPlus;
import com.tiemuyu.chuanchuan.activity.inter.SelectInterFace;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/3/28.
 */

public class PushMessageAdapter extends BaseAdapter {
    private final List<PushBean.HistoryBean> mHistory;
    private Context mContext;
    private PushBeanPlus mPushBeanPlus;
    private Holder mHolder;
    private SelectInterFace mSelectInterFace;

    public PushMessageAdapter(Context nContext, PushBeanPlus nPushBeanPlus, SelectInterFace mSelectInterFace) {
        mContext = nContext;
        mPushBeanPlus = nPushBeanPlus;
        mHistory = mPushBeanPlus.getPushBean().getHistory();
        this.mSelectInterFace = mSelectInterFace;
    }

    @Override
    public int getCount() {
        return mHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return mHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.push_mesage_layout, parent, false);
            mHolder.imageView = (ImageView) convertView.findViewById(R.id.im_image);
            mHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            mHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            mHolder.detail = (TextView) convertView.findViewById(R.id.tv_detail);
            mHolder.iv_select = (ImageView) convertView.findViewById(R.id.iv_select);
            convertView.setTag(mHolder);
        } else {
            mHolder = (Holder) convertView.getTag();
        }
        if (mPushBeanPlus.isShou()) {
            mHolder.iv_select.setVisibility(View.VISIBLE);
            mHolder.iv_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPushBeanPlus.getIntegers().get(position) == 1) {
                        mSelectInterFace.onUnSelect(position);
                    } else {
                        mSelectInterFace.onSelect(position);
                    }
                }
            });
            if (mPushBeanPlus.getIntegers().get(position) == 0) {
                mHolder.iv_select.setBackground(mContext.getResources().getDrawable(R.drawable.noselect));
            } else {
                mHolder.iv_select.setBackground(mContext.getResources().getDrawable(R.drawable.select));
            }

        } else {
            mHolder.iv_select.setVisibility(View.GONE);
        }
        mHolder.title.setText(mHistory.get(position).getP_title());
        mHolder.detail.setText(mHistory.get(position).getP_content());
        mHolder.time.setText(mHistory.get(position).getP_sendtime());
        if (!mHistory.get(position).getP_image().equals("")) {
            Picasso.with(mContext)
                    .load(mHistory.get(position).getP_image())
                    .into(mHolder.imageView);
        }

        return convertView;
    }

    private static class Holder {
        ImageView imageView;
        ImageView iv_select;
        TextView title;
        TextView detail;
        TextView time;
    }
}
