package com.tiemuyu.chuanchuan.activity.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tiemuyu.chuanchuan.activity.R;


/**
 * Created by 梁文硕 on 2017/3/20.
 */

public class ProgressHelper {
	private Activity context;
	private ViewGroup rootLayout;
	private TextView tv_page, tv_progress;
	private ProgressBar pb_progress;
	private int totalePage;

	public ProgressHelper(Context context,int totalePage) {
		this.context = (Activity) context;
		this.totalePage = totalePage;
		createGuideLayout();
	}

	/**
	 * @param @return
	 * @return ViewGroup
	 * @throws
	 * @Description: 创建引导图层
	 */
	private void createGuideLayout() {
		ViewGroup rootView = (ViewGroup) context.getWindow().getDecorView();
		LayoutInflater lf = context.getLayoutInflater();
		rootLayout = (ViewGroup) lf.inflate(R.layout.progress_helper, null);
		tv_page = (TextView) rootLayout.findViewById(R.id.tv_page);
		tv_progress = (TextView) rootLayout.findViewById(R.id.tv_progress);
		pb_progress = (ProgressBar) rootLayout.findViewById(R.id.pb_progress);
		pb_progress.setMax(100);
		tv_page.setText("1/"+totalePage);
		rootView.addView(rootLayout);
	}

	public void setView(int curPage,int progress) {

		Log.e("图层显示: ", curPage+":"+progress);
		tv_progress.setText(progress+"%");
		tv_page.setText(curPage+"/"+totalePage);
		pb_progress.setProgress(progress);
	}
	/**
	 * @param
	 * @return void
	 * @throws
	 * @Description: 打开引导层
	 */
	public void openGuide() {
		rootLayout.setVisibility(View.VISIBLE);
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Description: 关闭引导层
	 */
	public void closeGuide() {

		AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.2f);
		alphaAnim.setDuration(500);
		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnim.setDuration(500);
		AnimationSet AnimSet = new AnimationSet(false);
		AnimSet.setDuration(500);
		AnimSet.addAnimation(scaleAnim);
		AnimSet.addAnimation(alphaAnim);

		AnimSet.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				rootLayout.clearAnimation();
				rootLayout.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

		});

		rootLayout.startAnimation(AnimSet);
	}

}
