package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;

import java.util.List;

/**
 * @项目名： android1128
 * @包名： com.tiemuyu.chuanchuan.activity.adapter
 * @类描述：
 * @创建人： hr
 * @创建时间： 2016/12/27
 * @version：
 */

public class HorizontalScrollViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mDatas;
    private List<String> dzcp_names;
    private List<String> dzcp_prices;

    public HorizontalScrollViewAdapter(Context context, List<Integer> mDatas, List<String> dzcp_names, List<String> dzcp_prices) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount()
    {
        return mDatas.size();
    }

    public Object getItem(int position)
    {
        return mDatas.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.dzcp_single_item, parent, false);
            viewHolder.mainImg = (ImageView) convertView
                    .findViewById(R.id.dzcp_img);
            viewHolder.prodName = (TextView) convertView
                    .findViewById(R.id.dzcp_name);
            viewHolder.priceTag = (TextView) convertView
                    .findViewById(R.id.dzcp_price_tag);

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mainImg.setImageResource(mDatas.get(position));
        viewHolder.prodName.setText("some info");
        viewHolder.priceTag.setText("1111");
        return convertView;
    }

    private class ViewHolder
    {
        ImageView mainImg;
        TextView prodName;
        TextView priceTag;
    }

}
