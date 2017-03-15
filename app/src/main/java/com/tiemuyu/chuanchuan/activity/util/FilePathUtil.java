package com.tiemuyu.chuanchuan.activity.util;

import android.os.Environment;

import java.io.File;

public class FilePathUtil {
private static FilePathUtil util = new FilePathUtil();
	public static final String APP_DIR = "/builday/";
	private String fileRootPath;

private static final String FILE_CREATE_DIR = "creation";
private static final String FILE_SHARE_DIR = "share";
private static final String FILE_ASSETS_DIR = "assets";

private static final String TMP = "tmp";
private static final String RELEASE = "release";

private static final String FILE_VIDEO = "video";
private static final String FILE_AUDIO = "audio";
private static final String FILE_IMG = "img";
private static final String PREVIEW = "preview";
private static final String FILE_MUSIC= "music";

private String creation_img_tmp;
private String creation_img_release;
private String creation_video_tmp;
private String creation_video_release;
private String creation_video_preview;
private String creation_audio_release;
private String creation_effect_bg_music;

private String assets_resource_dir;

private String tmp_audio_path;
private final String tmpImageForShare;


private FilePathUtil()
{
	fileRootPath = Environment.getExternalStorageDirectory()+APP_DIR;
	creation_img_tmp = fileRootPath+FILE_CREATE_DIR+File.separator+FILE_IMG+File.separator+TMP;
	creation_img_release = fileRootPath+FILE_CREATE_DIR+File.separator+FILE_IMG+File.separator+RELEASE;
	creation_video_tmp = fileRootPath+FILE_CREATE_DIR+File.separator+FILE_VIDEO+File.separator+TMP;
	creation_video_release = fileRootPath+FILE_CREATE_DIR+File.separator+FILE_VIDEO+File.separator+RELEASE;
	creation_video_preview = fileRootPath+FILE_CREATE_DIR+File.separator+FILE_VIDEO+File.separator+PREVIEW;
	creation_audio_release = fileRootPath+FILE_CREATE_DIR+File.separator+FILE_AUDIO+File.separator+RELEASE;
	creation_effect_bg_music=fileRootPath+FILE_CREATE_DIR+File.separator+FILE_MUSIC;

	assets_resource_dir=fileRootPath+FILE_ASSETS_DIR;

	tmp_audio_path = fileRootPath+TMP+File.separator+FILE_AUDIO;
	tmpImageForShare = fileRootPath+FILE_SHARE_DIR+File.separator+TMP+File.separator;
}

public static FilePathUtil getInstant()
{
	
	return util;
}

public String getCreation_img_tmp()
{
	return creation_img_tmp;
}

public String getCreation_img_release()
{
	return creation_img_release;
}

public String getCreation_video_tmp()
{
	return creation_video_tmp;
}

public String getEffectBgDir()
{
	return creation_effect_bg_music;
}

public  String getFileAssetsDir()
{
	return  assets_resource_dir;
}


public String getCreation_video_release()
{
	return creation_video_release;
}

public String getCreation_video_preview() {
	// TODO Auto-generated method stub
	return creation_video_preview;
}

public String getCreation_audio_release()
{
	return creation_audio_release;
}

public String getTmpAudioPath() {
	return tmp_audio_path;
}



public String getCreationDir()
	{
		return fileRootPath+FILE_CREATE_DIR+File.separator;
	}
}
