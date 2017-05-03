package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.bean.NewShaituBean;
import com.tiemuyu.chuanchuan.activity.view.HorizontalListVIew;

import java.util.ArrayList;
import java.util.List;

public class MyShaiTuDetailActivity extends AppCompatActivity {
    private TextView tv_time, tv_shuoming;
    private Button chakandingzhi_btn;
    private HorizontalListVIew listview_horizon;
    NewShaituBean.DataBean.PagedataBean.RowsBean rowsBean;
    List<String> mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rowsBean = (NewShaituBean.DataBean.PagedataBean.RowsBean) getIntent().getSerializableExtra("data");
        Log.e("onCreate: ",rowsBean.toString() );
        setContentView(R.layout.activity_my_shai_tu_detail);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_shuoming = (TextView) findViewById(R.id.tv_shuoming);
        listview_horizon = (HorizontalListVIew) findViewById(R.id.listview_horizon);
        chakandingzhi_btn = (Button) findViewById(R.id.chakandingzhi_btn);
        tv_time.setText(rowsBean.getSharedTime());
        tv_shuoming.setText("定制说明："+rowsBean.getContentBrief() +"");
        mImage = new ArrayList<>();
        findViewById(R.id.goBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (rowsBean.getImage1() != null && !rowsBean.getImage1().equals("")) {
            mImage.add(rowsBean.getImage1());
        }

        if (rowsBean.getImage2() != null && !rowsBean.getImage2().equals("")) {
            mImage.add(rowsBean.getImage2());
        }

        if (rowsBean.getImage3() != null && !rowsBean.getImage3().equals("")) {
            mImage.add(rowsBean.getImage3());
        }

        if (rowsBean.getImage4() != null && !rowsBean.getImage4().equals("")) {
            mImage.add(rowsBean.getImage4());
        }

        if (rowsBean.getImage5() != null && !rowsBean.getImage5().equals("")) {
            mImage.add(rowsBean.getImage5());
        }

        if (rowsBean.getImage6() != null && !rowsBean.getImage6().equals("")) {
            mImage.add(rowsBean.getImage6());
        }

        if (rowsBean.getImage7() != null && !rowsBean.getImage7().equals("")) {
            mImage.add(rowsBean.getImage7());
        }

        if (rowsBean.getImage8() != null && !rowsBean.getImage8().equals("")) {
            mImage.add(rowsBean.getImage8());
        }

        if (rowsBean.getImage9() != null && !rowsBean.getImage9().equals("")) {
            mImage.add(rowsBean.getImage9());
        }
        MyAdapter myAdapter = new MyAdapter(mImage);
        listview_horizon.setAdapter(myAdapter);
        if (rowsBean.getIsOffer() == 1) {
            chakandingzhi_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyShaiTuDetailActivity.this, DingzhiDetailsActivity.class);
                    intent.putExtra("data",rowsBean);
                    startActivity(intent);
                }
            });
        } else {
            chakandingzhi_btn.setVisibility(View.GONE);
        }
        listview_horizon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyShaiTuDetailActivity.this, ImageDetailsActivity.class);
                intent.putStringArrayListExtra("images", (ArrayList<String>) mImage);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    private class MyAdapter extends BaseAdapter {

        private List<String> items;

        public MyAdapter(List<String> items) {
            this.items = items;
        }

        public void setData(List<String> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ChildHolder childHolder;
            int width = listview_horizon.getHeight();
            Log.e("width", "getView: " + width);
            if (convertView == null) {
                childHolder = new MyShaiTuDetailActivity.ChildHolder();
                convertView = LayoutInflater.from(MyShaiTuDetailActivity.this).inflate(R.layout.image_layout, parent, false);
                childHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
                childHolder.delect = (ImageView) convertView.findViewById(R.id.delect);
                convertView.setTag(childHolder);
            } else {
                childHolder = (MyShaiTuDetailActivity.ChildHolder) convertView.getTag();
            }
            childHolder.delect.setVisibility(View.GONE);
            Log.e("Image", "getView: "+items.get(position) );
            Picasso
                    .with(MyShaiTuDetailActivity.this)
                    .load(items.get(position))
                    .fit()
                    .centerInside()////
                    .into(childHolder.imageView);//
            return convertView;
        }


    }

    private static class ChildHolder {
        ImageView imageView, delect;
    }
}
