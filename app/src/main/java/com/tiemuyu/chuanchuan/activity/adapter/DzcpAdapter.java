package com.tiemuyu.chuanchuan.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.view.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @项目名： SLGWHandroid0123
 * @包名： com.tiemuyu.chuanchuan.activity.adapter
 * @类描述：
 * @创建人： hr
 * @创建时间： 2017/2/9
 * @version：
 */

public class DzcpAdapter extends BaseAdapter {

    private Context context;//运行上下文
    private LayoutInflater layoutInflater;//试图容器
    private List<List<Map<String, Object>>> listItems;//商品信息合集
    private static MyHorizontalScrollView myHorizontalScrollView;
    private HorizontalScrollViewAdapter horizontalScrollViewAdapter;

    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d
            ));
    private List<String> dzcp_names;
    private List<String> dzcp_prices;

    public DzcpAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.dzcp_container_layout, null);
        myHorizontalScrollView = (MyHorizontalScrollView) view.findViewById(R.id.dzcp_horizontalScrollView);
        dzcp_names = new ArrayList<>();
        dzcp_prices = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dzcp_names.add("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
            dzcp_prices.add("111");
        }
        horizontalScrollViewAdapter = new HorizontalScrollViewAdapter(this.context, mDatas, dzcp_names, dzcp_prices);
        myHorizontalScrollView.initDatas(horizontalScrollViewAdapter);
        return view;
    }

//    private Class ViewHolder {
//        ImageView dzcp_img;
//        TextView dzcp_name;
//        TextView dzcp_price;
//    }

}
