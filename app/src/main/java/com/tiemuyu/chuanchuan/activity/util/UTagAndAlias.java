package com.tiemuyu.chuanchuan.activity.util;

import android.util.Log;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.tag.TagManager;

import java.util.List;

/**
 * Created by 梁文硕 on 2017/5/9.
 */

public class UTagAndAlias {
	public static UTagAndAlias mInstance;
	private static PushAgent mPushAgent;
	private static final String TYPE = "CC";


	public UTagAndAlias() {

	}

	public static void init(PushAgent PushAgent) {
		mPushAgent = PushAgent;
		mInstance = new UTagAndAlias();
	}

	public static void addTag(String tag) {
		mPushAgent.getTagManager().delete(new TagManager.TCallBack() {
			@Override
			public void onMessage(final boolean isSuccess, final ITagManager.Result result) {
				Log.e("友盟TAG", "添加TAG" + isSuccess + ":"  + result.toString());
			}
		}, tag);
	}

	public static void delALlTag() {
		mPushAgent.getTagManager().reset(new TagManager.TCallBack() {
			@Override
			public void onMessage(boolean isSuccess, ITagManager.Result result) {
				Log.e("友盟TAG", "删除所有TAG: " + isSuccess + result.toString());
			}
		});
	}

	public static void delTag(String tag) {
		mPushAgent.getTagManager().delete(new TagManager.TCallBack() {
			@Override
			public void onMessage(final boolean isSuccess, final ITagManager.Result result) {
				Log.e("友盟TAG", "删除单个TAG: " + isSuccess+":" + result.toString());
			}
		}, tag);
	}

	public static void getAllTag() {
		mPushAgent.getTagManager().list(new TagManager.TagListCallBack() {
			@Override
			public void onMessage(boolean isSuccess, List<String> result) {
				Log.e("友盟TAG", "查看所有TAG: "+isSuccess);
				for ( String s : result ) {
					Log.e("友盟TAG", "查看所有单个TAG: "+result.get(0));
				}
			}
		});
	}

	public static void addAlia(String alia) {
		mPushAgent.addExclusiveAlias(alia, TYPE, new UTrack.ICallBack() {
			@Override
			public void onMessage(boolean isSuccess, String message) {
				Log.e("友盟TAG", "添加别名"+isSuccess+":"+message);
			}
		});
	}

	public static void delAlia(String alia) {
		mPushAgent.removeAlias(alia, TYPE, new UTrack.ICallBack(){
			@Override
			public void onMessage(boolean isSuccess, String message) {
				Log.e("友盟TAG", "移除别名"+isSuccess+":"+message);
			}
		});
	}

	public static void delAllAlia(String alia) {
		mPushAgent.removeAlias(alia, TYPE, new UTrack.ICallBack() {
			@Override
			public void onMessage(boolean isSuccess, String message) {

			}
		});
	}
}
