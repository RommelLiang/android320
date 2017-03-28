package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.ChengpinDetailActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.DIngzhi;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/24.
 */

public class ChildAdapter extends BaseAdapter {
    private final int width;
    private final int height;
    private List<DIngzhi.DataBean.AppdingzhilistBean> listBean;
    private Context context;
    private ChildHolder childHolder;
    private ImageView imageView;

    public ChildAdapter(List<DIngzhi.DataBean.AppdingzhilistBean> listBean, Context context) {
        this.listBean = listBean;
        this.context = context;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @Override
    public int getCount() {
        return listBean.size();
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
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false);
            childHolder.imageView = (ImageView) convertView.findViewById(R.id.im_image);
            childHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            childHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            imageView = childHolder.imageView;
            convertView.setTag(childHolder);

        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        Log.e("ChildHolder", "getView: " + listBean.get(position).getFirstXiJieImg());
        Picasso.with(context)
                .load(listBean.get(position).getPromianpic())
                .transform(transformation)
                .into(childHolder.imageView);
        childHolder.name.setText(listBean.get(position).getProname());
        childHolder.price.setText(Html.fromHtml("定制价" +
                "<font color=\"#450525\"><b>￥ " + listBean.get(position).getPrice() + "</b></font>"));
        childHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChengpinDetailActivity.class);
                intent.putExtra("productid", listBean.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class ChildHolder {
        ImageView imageView;
        TextView name;
        TextView price;
    }

    Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {
            int mTargetWidth = width*3/4;
            int mTargetHeight = height*3/4;


            if (source.getWidth() == 0) {
                return source;
            }
            if (source.getWidth() < source.getHeight()) {
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (mTargetWidth * aspectRatio);
                if (targetHeight != 0 && mTargetWidth != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, mTargetWidth, targetHeight, false);
                    if (result != source) {
                        // Same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }
            } else {
                double aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                int targetWidth = (int) (mTargetHeight * aspectRatio);
                if (targetWidth != 0 && targetWidth != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, mTargetHeight, false);
                    if (result != source) {
                        // Same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }
            }
        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };
}
