package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Transformation;
import com.tiemuyu.chuanchuan.activity.DingzhiDetailsActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.ZhuanTiMessage;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/2/17.
 */

public class ZhuanTiWaterAdapter extends BaseAdapter {
    private ImageView imageView;
    private PicassoWithImage mPicassoWithImage;
    private List<ZhuanTiMessage.DataBean.ListBean> messageList;
    private LayoutInflater layoutInflater;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private Context context;

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

    public ZhuanTiWaterAdapter(List<ZhuanTiMessage.DataBean.ListBean> messageList,
                               Context context) {
        this.messageList = messageList;
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
        mPicassoWithImage = PicassoWithImage.getIns(context);
    }

    @Override
    public int getCount() {
        return messageList.size() / 2;
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
            imageView = holder.image;
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text_one.setText(messageList.get((position + 1) * 2 - 2).getProductname());
        holder.price_one.setText("￥ " + messageList.get((position + 1) * 2 - 2).getPrice() + "");
        mPicassoWithImage.setImage(holder.image_one,messageList.get((position + 1) * 2 - 2).getProductmainpic());
        holder.text.setText(messageList.get((position + 1) * 2 - 1).getProductname());
        holder.price.setText("￥ " + messageList.get((position + 1) * 2 - 1).getPrice() + "");
        mPicassoWithImage.setImage(holder.image,messageList.get((position + 1) * 2 - 1).getProductmainpic());
        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("left", "onClick: " + messageList.get((position + 1) * 2 - 2).getProductmainpic());
                Intent intent = new Intent(context, DingzhiDetailsActivity.class);
                intent.putExtra("productid", messageList.get((position + 1) * 2 - 2).getProductid());
                context.startActivity(intent);
            }
        });
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("left", "onClick: " + messageList.get((position + 1) * 2 - 1).getProductmainpic());
                Intent intent = new Intent(context, DingzhiDetailsActivity.class);
                intent.putExtra("productid", messageList.get((position + 1) * 2 - 1).getProductid());
                context.startActivity(intent);
            }
        });
        return view;
    }

    Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {

            int mTargetWidth = imageView.getWidth();
            int mTargetHeight = imageView.getHeight();


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
                double aspectRatio = (double) source.getWidth()/ (double) source.getHeight();
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
