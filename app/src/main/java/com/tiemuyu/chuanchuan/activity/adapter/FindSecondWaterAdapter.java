package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tiemuyu.chuanchuan.activity.ClothesDetailsActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.ZhuantiWaterActivity;
import com.tiemuyu.chuanchuan.activity.bean.FindSecondWaterBean;

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
    private ImageLoadingListener animateFirstListener = new ZhuantiWaterActivity.AnimateFirstDisplayListener();
    private LayoutInflater layoutInflater;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    private int pos;

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
        holder.text.setText(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getProductname());
        holder.price.setText("￥" + findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getPrice() + "");
        imageLoader.displayImage(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getProductmainpic(), holder.image, options, animateFirstListener);
        holder.text_one.setText(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductname());
        holder.price_one.setText("￥" + findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getPrice() + "");
        imageLoader.displayImage(findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductmainpic(), holder.image_one, options, animateFirstListener);

        // todo 点击跳转到产品详情页面
        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "left is clicked!");
                Intent intent = new Intent(context, ClothesDetailsActivity.class);
                intent.putExtra("productid", findSecondWaterBean.getData().getData().get((position + 1) * 2 - 2).getProductid());
                context.startActivity(intent);
            }
        });
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "right is clicked!");
                Intent intent = new Intent(context, ClothesDetailsActivity.class);
                intent.putExtra("productid", findSecondWaterBean.getData().getData().get((position + 1) * 2 - 1).getProductid());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
