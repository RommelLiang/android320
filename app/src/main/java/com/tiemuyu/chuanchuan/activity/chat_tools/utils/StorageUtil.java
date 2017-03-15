package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

import com.tiemuyu.chuanchuan.activity.chat_tools.bean.StorageType;

public class StorageUtil {
	public final static long K = 1024;
	public final static long M = 1024 * 1024;
	// 外置存储卡默认预警临界值
    private static final long THRESHOLD_WARNING_SPACE = 100 * M;
	// 保存文件时所需的最小空间的默认值
	public static final long THRESHOLD_MIN_SPCAE = 20 * M;

	private static final char m_hexCodes[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static final int m_shifts[] = { 60, 56, 52, 48, 44, 40, 36, 32, 28,
			24, 20, 16, 12, 8, 4, 0 };

    public static void init(Context context, String rootPath) {
        ExternalStorage.getInstance().init(context, rootPath);
    }

	/**
	 * 获取文件保存路径，没有toast提示
	 *
	 * @param fileName
	 * @param fileType
	 * @return 可用的保存路径或者null
	 */
	public static String getWritePath(String fileName, StorageType fileType) {
		return getWritePath(null, fileName, fileType, false);
	}

	/**
	 * 获取文件保存路径
	 *
	 * @param fileName
	 *            文件全名
	 * @param tip
	 *            空间不足时是否给出默认的toast提示
	 * @return 可用的保存路径或者null
	 */
	private static String getWritePath(Context context, String fileName, StorageType fileType, boolean tip) {
		String path = ExternalStorage.getInstance().getWritePath(fileName, fileType);
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		File dir = new File(path).getParentFile();
		if (dir != null && !dir.exists()) {
			dir.mkdirs();
		}
		return path;
	}

	/**
	 * 判断能否使用外置存储
	 */
	public static boolean isExternalStorageExist() {
		return ExternalStorage.getInstance().isSdkStorageReady();
	}


    /**
     * 判断外部存储是否存在，以及是否有足够空间保存指定类型的文件
     *
     * @param context
     * @param fileType
     * @param tip  是否需要toast提示
     * @return false: 无存储卡或无空间可写, true: 表示ok
     */
    public static boolean hasEnoughSpaceForWrite(Context context, StorageType fileType, boolean tip) {
        if (!ExternalStorage.getInstance().isSdkStorageReady()) {
            return false;
        }

        long residual = ExternalStorage.getInstance().getAvailableExternalSize();
        if (residual < fileType.getStorageMinSize()) {
            return false;
        } else if (residual < THRESHOLD_WARNING_SPACE) {
        }

        return true;
    }
	/**
	 * 根据输入的文件名和类型，找到该文件的全路径。
	 *
	 * @param fileName
	 * @param fileType
	 * @return 如果存在该文件，返回路径，否则返回空
	 */
	public static String getReadPath(String fileName, StorageType fileType) {
		return ExternalStorage.getInstance().getReadPath(fileName, fileType);
	}

	/**
	 * 获取文件保存路径，空间不足时有toast提示
	 *
	 * @param context
	 * @param fileName
	 * @param fileType
	 * @return 可用的保存路径或者null
	 */
	public static String getWritePath(Context context, String fileName, StorageType fileType) {
		return getWritePath(context, fileName, fileType, true);
	}

	public static String getDirectoryByDirType(StorageType fileType) {
		return ExternalStorage.getInstance().getDirectoryByDirType(fileType);
	}

    public static String getSystemImagePath() {
        if (Build.VERSION.SDK_INT > 7) {
            String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            return picturePath + "/nim/";
        } else {
            String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            return picturePath + "/nim/";
        }
    }

	public static boolean isInvalidVideoFile(String filePath) {
		return filePath.toLowerCase().endsWith(".3gp")
				|| filePath.toLowerCase().endsWith(".mp4");
	}
	public static String getStreamMD5(String filePath) {
		String hash=null;
		byte[] buffer = new byte[4096];
		BufferedInputStream in=null;
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			in = new BufferedInputStream(new FileInputStream(filePath));
			int numRead = 0;
			while ((numRead = in.read(buffer)) > 0) {
				md5.update(buffer, 0, numRead);
			}
			in.close();
			hash = toHex(md5.digest(),0,md5.digest().length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return hash;
	}

	public static String toHex(final byte[] value, final int offset,
							   final int length) {
		StringBuilder retVal = new StringBuilder();

		int end = offset + length;
		for (int x = offset; x < end; x++)
			retVal.append(toHex(value[x],2));

		return retVal.toString();
	}
	private static String toHex(final long value, final int digitNum) {
		StringBuilder result = new StringBuilder(digitNum);

		for (int j = 0; j < digitNum; j++) {
			int index = (int) ((value >> m_shifts[j + (16 - digitNum)]) & 15);
			result.append(m_hexCodes[index]);
		}

		return result.toString();
	}
}
