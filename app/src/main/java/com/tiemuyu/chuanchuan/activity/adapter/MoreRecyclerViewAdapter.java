package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.ChengpinDetailActivity;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.MoreBean;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;

/**
 * Created by 梁文硕 on 2017/4/27.
 */

public class MoreRecyclerViewAdapter extends RecyclerView.Adapter<MoreRecyclerViewAdapter.MyViewHolder> {

	private Context mContext;
	private MoreBean mMoreBean;
	private ImageView imageView;
	private PicassoWithImage mPicassoWithImage;
	private View mHeaderView;
	private View mFooterView;
	public static final int TYPE_HEADER = 0;  //说明是带有Header的
	public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
	public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

	public void setHeaderView(View headerView) {
		mHeaderView = headerView;
		notifyItemInserted(0);
	}
	public void setFooterView(View footerView) {
		mFooterView = footerView;
		notifyItemInserted(getItemCount()-1);
	}
	public MoreRecyclerViewAdapter(Context mContext, MoreBean mMoreBean) {
		this.mContext = mContext;
		this.mMoreBean = mMoreBean;
		mPicassoWithImage = new PicassoWithImage(mContext);
	}
	@Override
	public MoreRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(mHeaderView != null && viewType == TYPE_HEADER) {
			return new MyViewHolder(mHeaderView);
		}
		if(mFooterView != null && viewType == TYPE_FOOTER){
			return new MyViewHolder(mFooterView);
		}
		MyViewHolder myViewHolder =
				new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_more, parent, false));
		return myViewHolder;
	}

	@Override
	public void onBindViewHolder(MoreRecyclerViewAdapter.MyViewHolder holder, final int position) {
		if (getItemViewType(position) == TYPE_NORMAL) {
			String promianpic = mMoreBean.getData().get(0).getAppdingzhilist().get(position).getPromianpic();
			holder.name.setText(mMoreBean.getData().get(0).getAppdingzhilist().get(position).getProname());
			holder.price.setText(Html.fromHtml("定制价" +
					"<font color=\"#450525\"><b>￥ " + mMoreBean.getData().get(0).getAppdingzhilist().get(position).getPrice() + "</b></font>"));
			mPicassoWithImage.setImage(holder.imageView, promianpic);
			holder.imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, ChengpinDetailActivity.class);
					intent.putExtra("productid", mMoreBean.getData().get(0).getAppdingzhilist().get(position).getId());
					mContext.startActivity(intent);
				}
			});
			return;
		} else {
			return;
		}

	}

	@Override
	public int getItemCount() {
		if(mHeaderView == null && mFooterView == null){
			return mMoreBean.getData().get(0).getAppdingzhilist().size();
		}else if(mHeaderView == null && mFooterView != null){
			return mMoreBean.getData().get(0).getAppdingzhilist().size() + 1;
		}else if (mHeaderView != null && mFooterView == null){
			return mMoreBean.getData().get(0).getAppdingzhilist().size() + 1;
		}else {
			return mMoreBean.getData().get(0).getAppdingzhilist().size() + 2;
		}
	}

	@Override
	public int getItemViewType(int position) {
		if (mHeaderView == null && mFooterView == null){
			return TYPE_NORMAL;
		}
		if (position == 0){
			//第一个item应该加载Header
			return TYPE_HEADER;
		}
		if (position == getItemCount()-1){
			//最后一个,应该加载Footer
			return TYPE_FOOTER;
		}
		return TYPE_NORMAL;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		ImageView imageView;
		TextView name;
		TextView price;
		public MyViewHolder(View itemView) {
			super(itemView);
			imageView = (ImageView) itemView.findViewById(R.id.im_image);
			name = (TextView) itemView.findViewById(R.id.tv_name);
			price = (TextView) itemView.findViewById(R.id.tv_price);
		}
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
		RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
		if(manager instanceof GridLayoutManager) {
			final GridLayoutManager gridManager = ((GridLayoutManager) manager);
			gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
				@Override
				public int getSpanSize(int position) {
					return getItemViewType(position) == TYPE_HEADER
							? gridManager.getSpanCount() : 1;
				}
			});
		}
	}
}
