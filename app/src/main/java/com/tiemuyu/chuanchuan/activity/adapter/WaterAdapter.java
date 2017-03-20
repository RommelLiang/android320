package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.ClothesDetailsActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.ZhuantiWaterActivity;
import com.tiemuyu.chuanchuan.activity.bean.L1WaterItemBean;
import com.tiemuyu.chuanchuan.activity.bean.LastPrice;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/17.
 */

public class WaterAdapter extends BaseAdapter {
    private final List<LastPrice.DataBean.RowsBean> rows;
    private Context context;
    private ImageLoadingListener animateFirstListener = new ZhuantiWaterActivity.AnimateFirstDisplayListener();
    private LayoutInflater layoutInflater;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    private class ViewHolder {
        public TextView text_one;
        public ImageView image_one;
        public TextView price_one;
        public TextView text;
        public ImageView image;
        public TextView price;
        public LinearLayout left;
        public LinearLayout right;
    }

    public WaterAdapter(List<LastPrice.DataBean.RowsBean> rows,
                        Context context) {
        this.rows = rows;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.icon_morentupian2)
                .showImageForEmptyUri(R.drawable.icon_morentupian2)
                .showImageOnFail(R.drawable.icon_morentupian2)
                .cacheInMemory()
                .cacheOnDisc()
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }

    @Override
    public int getCount() {
        return rows.size() / 2;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.zhuan_ti_wather, parent, false);
            holder.text = (TextView) view.findViewById(R.id.new_price_pro_name);
            holder.price = (TextView) view.findViewById(R.id.new_price_txt);
            holder.image = (ImageView) view.findViewById(R.id.new_price_pic);
            holder.text_one = (TextView) view.findViewById(R.id.new_price_pro_name_one);
            holder.price_one = (TextView) view.findViewById(R.id.new_price_txt_one);
            holder.image_one = (ImageView) view.findViewById(R.id.new_price_pic_one);
            holder.left = (LinearLayout) view.findViewById(R.id.listview_item_left);
            holder.right = (LinearLayout) view.findViewById(R.id.listview_item_right);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(rows.get((position + 1) * 2 - 1).getProductName());
        holder.price.setText("￥" + rows.get((position + 1) * 2 - 1).getPrice() + "");
        Picasso.with(context).load(rows.get((position + 1) * 2 - 1).getMainImage()).into(holder.image);
        holder.text_one.setText(rows.get((position + 1) * 2 - 2).getProductName());
        holder.price_one.setText("￥" + rows.get((position + 1) * 2 - 2).getPrice() + "");
        Picasso.with(context).load(rows.get((position + 1) * 2 - 2).getMainImage()).into(holder.image_one);
        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClothesDetailsActivity.class);
                intent.putExtra("productid",rows.get((position + 1) * 2 - 2).getId());
                context.startActivity(intent);
            }
        });
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClothesDetailsActivity.class);
                intent.putExtra("productid",rows.get((position + 1) * 2 - 1).getId());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
