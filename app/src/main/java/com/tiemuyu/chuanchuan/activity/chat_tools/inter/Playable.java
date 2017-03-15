package com.tiemuyu.chuanchuan.activity.chat_tools.inter;

public interface Playable {
	long getDuration();
	String getPath();
	boolean isAudioEqual(Playable audio);
}