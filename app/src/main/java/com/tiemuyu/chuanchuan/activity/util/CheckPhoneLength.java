package com.tiemuyu.chuanchuan.activity.util;

/**
 * Created by 梁文硕 on 2017/4/26.
 */

public class CheckPhoneLength {
	/**
	 * Author：rommel
	 * @param: String phone
	 * @return: int 0：太短; 1：正常 2：太长
	 * Desc:
	 */
	public static int cheeck(String phone) {
		if (phone.length()<11) {
			return 0;
		} else if (phone.length() == 11) {
			return 1;
		} else {
			return  2;
		}
	}
}
