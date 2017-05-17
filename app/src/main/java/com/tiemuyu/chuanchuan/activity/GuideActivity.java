package com.tiemuyu.chuanchuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends AppCompatActivity {

	private Button button;
	private ViewPager view_pager;
	private ViewPagerAdapter adapter;
	private List<View> lists = new ArrayList<View>();

	private ImageView imageView;
	int[] imgsArr =
			new int[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_guide);
		//小米
	   /*imgsArr =
	            new int[]{R.drawable.one_mi, R.drawable.two_mi, R.drawable.three_mi,
                        R.drawable.four_mi, R.drawable.five_mi};*/
		imgsArr =
				new int[] {R.drawable.one, R.drawable.two, R.drawable.three,
						R.drawable.four, R.drawable.five};
		view_pager = (ViewPager) findViewById(R.id.view_pager);
		button = (Button) findViewById(R.id.button);


		for ( int i = 0; i < imgsArr.length; i++ ) {
			imageView = new ImageView(this);
			Picasso
					.with(this)
					.load(imgsArr[i])
					.into(imageView);
			lists.add(imageView);
		}

		adapter = new ViewPagerAdapter(lists);
		view_pager.setAdapter(adapter);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GuideActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// 如果是最后一个引导界面的话，就出现按钮
				// 如果不是最后一个的话，就不出现
				if (arg0 == lists.size() - 1) {
					button.setVisibility(View.VISIBLE);
				} else {
					button.setVisibility(View.GONE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
//	MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
//	MobclickAgent.onPause(this);
	}

	public class ViewPagerAdapter extends PagerAdapter {
		private List<View> pages;

		public ViewPagerAdapter(List<View> lists) {
			this.pages = lists;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(pages.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(pages.get(position));
			return pages.get(position);
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}
}
