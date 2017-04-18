package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.view.PinchImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class ImageDetailsActivity extends AppCompatActivity {

	private ViewPager viewpager;
	private ArrayList<String> images;
	private int position;
	private int mType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_details);
		Intent intent = getIntent();
		images = intent.getStringArrayListExtra("images");
		position = intent.getIntExtra("position", 0);
		mType = intent.getIntExtra("type", 0);
		Log.e("onCreate: ",mType+"" );
		Log.e("onCreate: ", images.size() + ":" + position);
		final LinkedList<PinchImageView> viewCache = new LinkedList<PinchImageView>();
		viewpager = (ViewPager) findViewById(R.id.pager);
		viewpager.setAdapter(new PagerAdapter() {
			@Override
			public int getCount() {
				return images.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				PinchImageView piv;
				if (viewCache.size() > 0) {
					piv = viewCache.remove();
					piv.reset();
				} else {
					piv = new PinchImageView(ImageDetailsActivity.this);
				}
				if (mType == 0) {
					Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(piv);
				} else {
					Picasso.with(ImageDetailsActivity.this).load(new File(images.get(position))).into(piv);
				}
				piv.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
				container.addView(piv);
				return piv;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				PinchImageView piv = (PinchImageView) object;
				container.removeView(piv);
				viewCache.add(piv);
			}

			@Override
			public void setPrimaryItem(ViewGroup container, int position, Object object) {
				PinchImageView piv = (PinchImageView) object;
				if (mType == 0) {
					Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(piv);
				} else {
					Picasso.with(ImageDetailsActivity.this).load(new File(images.get(position))).into(piv);
				}
			}
		});
		if (position != 0) {
			viewpager.setCurrentItem(position);
		}
	    /*back = (ImageView) findViewById(R.id.back);
        go = (ImageView) findViewById(R.id.go);
        if (images.size()==1) {
            go.setVisibility(View.GONE);
            back.setVisibility(View.INVISIBLE);
        }
        Picasso.with(this).load(images.get(position)).into(banner);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position += 1;
                if (position >= images.size()) {
                    position = 0;
                }
                Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(banner);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position -= 1;
                if (position < 0) {
                    position = images.size() - 1;
                }
                Picasso.with(ImageDetailsActivity.this).load(images.get(position)).into(banner);
            }
        });*/
	}
}
