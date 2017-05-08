package com.tiemuyu.chuanchuan.activity;

import android.os.Bundle;

import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.view.SearchView;

public class SerachActivity extends BaseActivityG {
	private SearchView search_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serach);
		search_layout = (SearchView) findViewById(R.id.search_layout);

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		search_layout.dataChange();
	}
}
