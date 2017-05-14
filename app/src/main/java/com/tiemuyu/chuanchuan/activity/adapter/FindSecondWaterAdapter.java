package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
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
import com.tiemuyu.chuanchuan.activity.bean.FindSecondWaterBean;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;

/**
 * @项目名： 227androidpay-master
 * @包名： com.tiemuyu.chuanchuan.activity.adapter
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/3/3
 * @version：
 */

public class FindSecondWaterAdapter extends BaseAdapter {

    FindSecondWaterBean findSecondWaterBean;

    private Context context;
    private LayoutInflater layoutInflater;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private int mType;
    private ImageView imageView;

    private int pos;

    private PicassoWithImage mPicassoWithImage;
    private class ViewHolder {
        public TextView text_one;
        public ImageView image_one;
        public TextView price_one;
        public TextView price_one_total;
        public TextView text;
        public ImageView image;
        public TextView price_total;
        public TextView price;
        public LinearLayout left;
        public LinearLayout right;
    }

    public FindSecondWaterAdapter(FindSecondWaterBean findSecondWaterBean, Context context) {
        this.findSecondWaterBean = findSecondWaterBean;
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
        mType =0;
    }
    public FindSecondWaterAdapter(FindSecondWaterBean findSecondWaterBean, Context context,int mType) {
        this.findSecondWaterBean = findSecondWaterBean;
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
        this.mType = mType;
        mPicassoWithImage = new PicassoWithImage(context);
    }
    @Override
    public int getCount() {
        return findSecondWaterBean.getData().getData().size() / 2;
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
        pos = position;
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.zhuan_ti_wather_huodong, parent, false);
            holder.text = (TextView) view.findViewById(R.id.new_price_pro_name);
            holder.price = (TextView) view.findViewById(R.id.new_price_txt);
            holder.price_total = (TextView) view.findViewById(R.id.new_price_txt_total);
            holder.image = (ImageView) view.findViewById(R.id.new_price_pic);
            holder.text_one = (TextView) view.findViewById(R.id.new_price_pro_name_one);
            holder.price_one_total = (TextView) view.findViewById(R.id.new_price_txt_one_total);
            holder.price_one = (TextView) view.findViewById(R.id.new_price_txt_one);
            holder.image_one = (ImageView) view.findViewById(R.id.new_price_pic_one);
            holder.left = (LinearLayout) view.findViewById(R.id.listview_item_left);
            holder.right = (LinearLayout) view.findViewById(R.id.listview_item_right);
            imageView = holder.image;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getProductname());
        /*Picasso.with(context)
                .load(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getProductmainpic())
                .transform(transformation)
                .into(holder.image);*/
        mPicassoWithImage.setImage(holder.image,
                findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1 ).getProductmainpic());
        holder.text_one.setText(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductname());
        mPicassoWithImage.setImage(holder.image_one,
                findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductmainpic());
        /*Picasso.with(context)
                .load(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductmainpic())
                .transform(transformation)
                .into(holder.image_one);*/
        Log.e("getView: ",mType+"");
        if (mType == 1) {
            holder.price.setText("￥" +(int) Math.ceil(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getPrice()*0.9) + "");
            holder.price_one.setText("￥" + (int) Math.ceil(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getPrice()*0.9) + "");
            holder.price_total.setText("￥" + (int)findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getPrice());
            holder.price_one_total.setText("￥" + (int)findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getPrice());
            holder.price_total.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
            holder.price_one_total.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
        } else {
            holder.price_one_total.setVisibility(View.GONE);
            holder.price_total.setVisibility(View.GONE);
            holder.price.setText("￥" + (int)findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getPrice() + "");
            holder.price_one.setText("￥" + (int)findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getPrice() + "");
        }
        // todo 点击跳转到产品详情页面
        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "left is clicked!");
                Intent intent = new Intent(context, DingzhiDetailsActivity.class);
                intent.putExtra("type",mType);
                intent.putExtra("productid", findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductid());
                context.startActivity(intent);
            }
        });
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "right is clicked!");
                Intent intent = new Intent(context, DingzhiDetailsActivity.class);
                intent.putExtra("productid", findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getProductid());
                intent.putExtra("type",mType);
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
