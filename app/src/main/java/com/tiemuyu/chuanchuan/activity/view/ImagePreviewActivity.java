package com.tiemuyu.chuanchuan.activity.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import com.tiemuyu.chuanchuan.activity.R;

/**
 * describe :
 * Created by Pm on 2016/5/30.
 * PackageName com.mlz.mlzmall.ui.activity.act.main.
 * ProjectName mlzmall_android.
 */

public class ImagePreviewActivity extends Activity{
    ImageView imageView;
    TextView tv_item;
    //   ImageLoader imageLoader;
    //   DisplayImageOptions options;

    String imageUrl;
    String[] urls;
    int item;
    int total;
    public DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    List<ImageView> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepreview);
        Intent intent = getIntent();
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.back)
                .showImageOnFail(R.mipmap.back)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        //初始化
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 480)  // 缓存在内存的图片的宽和高度
                .discCacheExtraOptions(480, 480, Bitmap.CompressFormat.PNG, 85, null)   //CompressFormat.PNG类型，70质量（0-100）
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)   // 缓存到内存的最大数据
                .discCacheSize(50 * 1024 * 1024)    // 缓存到文件的最大数据
                .discCacheFileCount(1000)           // 文件数量
                .defaultDisplayImageOptions(options)// 上面的options对象，一些属性配置
                .build();
        imageLoader.init(config);

        final String urlFrPrev = intent.getStringExtra("imgUrl");
        urls = urlFrPrev.split(",");

//        Bundle bundle = intent.getExtras();
//        imageUrl = bundle.getString("imageurl");
//        urls = bundle.getStringArray("urlimages");
//        item = bundle.getInt("item");

        item = 0;
        total = urls.length;
        tv_item = (TextView)findViewById(R.id.tv_item);
        tv_item.setText(item+1+"/"+total);
        imageView = (ImageView)findViewById(R.id.imageView);
        images = new ArrayList<>();
        for(int i = 0;i < total;i++){
            ImageView imageView = new ImageView(this);
            //   imageLoader.displayImage(urls[i], imageView, options);
            imageLoader.displayImage(urls[i], imageView,options);
            images.add(imageView);
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(item);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_item.setText(position + 1 + "/" + total);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));

            return images.get(position);
        }
    }
}