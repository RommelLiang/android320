package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.DIngzhi;
import com.tiemuyu.chuanchuan.activity.util.PicassoWithImage;

import java.util.List;

import it.sephiroth.android.library.widget.AbsHListView;
import it.sephiroth.android.library.widget.HListView;

/**
 * Created by 梁文硕 on 2017/2/24.
 */

public class HeoizonListViewAdapter extends BaseAdapter {
	private List<DIngzhi.DataBean> dataBeanList;
	private Context context;
	private ChildAdapter childAdapter;

	private PicassoWithImage mPicassoWithImage;
	public HeoizonListViewAdapter(List<DIngzhi.DataBean> dataBeanList, Context context) {
		this.dataBeanList = dataBeanList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return dataBeanList.size();
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
		ParentHolder parentHolder = null;
		DIngzhi.DataBean dataBean = dataBeanList.get(position);
		if (convertView == null) {
			mPicassoWithImage = new PicassoWithImage(context);
			LayoutInflater userInflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = userInflater.inflate(R.layout.item_parent, null);
			convertView.setHorizontalScrollBarEnabled(true);

			parentHolder = new ParentHolder();
			convertView.setTag(parentHolder);
			parentHolder.brandName = (TextView) convertView.findViewById(R.id.text_brand);
			parentHolder.horizontalListVIew = (HListView) convertView.findViewById(R.id.hl_child);
		} else {
			parentHolder = (ParentHolder) convertView.getTag();
		}
		Log.e("brandName", "getGroupView: " + dataBean.getName());
		childAdapter = new ChildAdapter(dataBean.getAppdingzhilist(), context);
		parentHolder.horizontalListVIew.setAdapter(childAdapter);
		parentHolder.brandName.setText(dataBean.getName());

		parentHolder.horizontalListVIew.setOnScrollListener(new AbsHListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsHListView view, int scrollState) {
				switch (scrollState) {
					case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
						//设置为停止滚动
						childAdapter.setScrollState(false);
					case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动做出了抛的动作
						//设置为正在滚动
						childAdapter.setScrollState(true);
						break;
					case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动
						//设置为正在滚动
						childAdapter.setScrollState(true);
						break;
				}
			}

			@Override
			public void onScroll(AbsHListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

			}
		});
		return convertView;
	}

	private static class ParentHolder {
		TextView brandName;
		HListView horizontalListVIew;
	}
}
