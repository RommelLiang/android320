package com.tiemuyu.chuanchuan.activity.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.sina.weibo.SinaWeibo;
//import cn.sharesdk.tencent.qq.QQ;
//import cn.sharesdk.wechat.friends.Wechat;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
//import com.tiemuyu.chuanchuan.activity.BaseActivity;
//import com.tiemuyu.chuanchuan.activity.WelcomeActivity;
//import com.tiemuyu.chuanchuan.activity.base.MyApplication;
import com.tiemuyu.chuanchuan.activity.bean.AccountBean;
import com.tiemuyu.chuanchuan.activity.bean.AccountResultBean;
import com.tiemuyu.chuanchuan.activity.bean.ActCpModelBean;
import com.tiemuyu.chuanchuan.activity.bean.AppAccessBean;
import com.tiemuyu.chuanchuan.activity.bean.AppStartBean;
import com.tiemuyu.chuanchuan.activity.bean.AwardBean;
import com.tiemuyu.chuanchuan.activity.bean.AwardResultBean;
import com.tiemuyu.chuanchuan.activity.bean.BannerDataBean;
import com.tiemuyu.chuanchuan.activity.bean.BaoJiaWater;
import com.tiemuyu.chuanchuan.activity.bean.BooleanDefaultBean;
import com.tiemuyu.chuanchuan.activity.bean.CashRollBean;
import com.tiemuyu.chuanchuan.activity.bean.CashRollDetilBean;
import com.tiemuyu.chuanchuan.activity.bean.CashRollResultBean;
import com.tiemuyu.chuanchuan.activity.bean.DefaultBean;
import com.tiemuyu.chuanchuan.activity.bean.ForgetDataBean;
import com.tiemuyu.chuanchuan.activity.bean.ForgetResultBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.bean.L1WaterItemBean;
import com.tiemuyu.chuanchuan.activity.bean.LoginAccountBean;
import com.tiemuyu.chuanchuan.activity.bean.LoginDataBean;
import com.tiemuyu.chuanchuan.activity.bean.LoginOnlineUserBean;
import com.tiemuyu.chuanchuan.activity.bean.LoginResultBean;
import com.tiemuyu.chuanchuan.activity.bean.NewProductBean;
import com.tiemuyu.chuanchuan.activity.bean.OauthsBean;
import com.tiemuyu.chuanchuan.activity.bean.OrderBackBean;
import com.tiemuyu.chuanchuan.activity.bean.PayBean;
import com.tiemuyu.chuanchuan.activity.bean.PayInfoBean;
import com.tiemuyu.chuanchuan.activity.bean.PayResultBean;
import com.tiemuyu.chuanchuan.activity.bean.SaveItemBean;
import com.tiemuyu.chuanchuan.activity.bean.ShaituWaterBean;
import com.tiemuyu.chuanchuan.activity.bean.ShareBean;
import com.tiemuyu.chuanchuan.activity.bean.SocialInfoBean;
import com.tiemuyu.chuanchuan.activity.bean.StringListBean;
import com.tiemuyu.chuanchuan.activity.bean.ThirdLoginBean;
import com.tiemuyu.chuanchuan.activity.bean.ThirdResultBean;
import com.tiemuyu.chuanchuan.activity.bean.TokenResultBean;
import com.tiemuyu.chuanchuan.activity.bean.TradeBean;
import com.tiemuyu.chuanchuan.activity.bean.TradeDetilBean;
import com.tiemuyu.chuanchuan.activity.bean.TradeResultBean;
import com.tiemuyu.chuanchuan.activity.bean.UpImgBean;
import com.tiemuyu.chuanchuan.activity.bean.UpImgResultBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.bean.VersionBean;
import com.tiemuyu.chuanchuan.activity.bean.WaterBeanData;
import com.tiemuyu.chuanchuan.activity.bean.WaterResultBean;
import com.tiemuyu.chuanchuan.activity.bean.WebBackBean;
import com.tiemuyu.chuanchuan.activity.bean.WebSimpleDialogBean;
import com.tiemuyu.chuanchuan.activity.bean.WebTvBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterBean;
import com.tiemuyu.chuanchuan.activity.bean.ZhuantiWaterExtBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp.HttpCallBack;
//import com.tiemuyu.chuanchuan.activity.view.PromptDialog;

/**
 * @author hw
 * 
 *         一些数据操作
 */
public class DataContoler {
	public static AppAccessBean lastAccessBean;
	public static String last_time;
	public static String IMA_600 = "600x";

	/**
	 * @Title: getSid
	 * @Description: TODO(获取sid)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getSid() {
		String ss = UUID.randomUUID().toString().replaceAll("-", "");
		String sid = ss.toUpperCase();// 转大写
		return sid;
	}











	/**
	 * getLoginV(得到经过加密的v) TODO(这里描述这个方法适用条件 – 用于登录) TODO(这里描述这个方法的执行流程 – 可选)
	 * TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 – 可选)
	 * 
	 * @Title: getLoginV
	 * @Description: TODO
	 * @param @param name
	 * @param @param pass
	 * @param @param passkey
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getLoginV(String name, String pass, String passkey) {

		String x1 = name + "," + X1Code.x1Encode(pass, name);
		// System.out.println("------AEC加密前的字符串:"+x1+",passkey:"+passkey);
		String vs = "";
		try {
			// vs = QEncodeUtil.aesEncrypt(x1, passkey);
			vs = AESHelper.getAesString(x1, passkey);
			// vs = AESHelper.encrypt1(x1, passkey);
			// System.out.println("------AEC加密后的字符串:"+vs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("-----抛异常:");
			e.printStackTrace();
		}

		return vs;
	}

	public static String getModifyV(String new_pass, String name, String pass,
			String passkey) {

		String x1 = new_pass + "," + X1Code.x1Encode(pass, name);
		// System.out.println("------AEC加密前的字符串:"+x1+",passkey:"+passkey);
		String vs = "";
		try {
			// vs = QEncodeUtil.aesEncrypt(x1, passkey);
			vs = AESHelper.getAesString(x1, passkey);
			// vs = AESHelper.encrypt1(x1, passkey);
			// System.out.println("------AEC加密后的字符串:"+vs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("-----抛异常:");
			e.printStackTrace();
		}

		return vs;
	}

	/**
	 * @Title: parseLoginMsg
	 * @Description: TODO 登录信息的解析(普通登录)
	 * @param @param msg 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static User parseLoginMsgAndSetUser(String msg, String pass,
			String oauthId) {
		LoginResultBean resultBean = JsonTools.fromJson(msg,
				LoginResultBean.class);
		User user = new User();
		if (resultBean != null) {
			LoginDataBean dataBean = resultBean.getDataBean();
			if (dataBean != null) {
				LoginOnlineUserBean onlineUserBean = dataBean
						.getOnlineUserBean();
				// LoginUserInfoBean userInfoBean=dataBean.getUserInfoBean();



				LoginAccountBean accountBean = dataBean.getAccountBean();

				SocialInfoBean socialInfoBean = dataBean.getSocialInfoBean();

				if (onlineUserBean != null) {
					user.setUsername(onlineUserBean.getUsername());
					user.setUserId(onlineUserBean.getUserId());
					// LogHelper.d("----注册返回的邮箱地址:"+onlineUserBean.getEmail());
					user.setNickName(onlineUserBean.getNickName());
					user.setUserImg(onlineUserBean.getUserImg());
					user.setInviteCode(onlineUserBean.getInviteCode());
				}
				if (accountBean != null) {
					user.setCcCoin(accountBean.getCcCoin());
					user.setVoucher(accountBean.getVoucher());
					user.setAccVoucher(accountBean.getAccVoucher());
					user.setFrzVoucher(accountBean.getFrzVoucher());
					user.setFrzCcCoin(accountBean.getFrzCcCoin());
					user.setAmounts(accountBean.getAmounts());
					user.setFrzAmounts(accountBean.getFrzAmounts());
				}
				if (socialInfoBean != null) {
					user.setUserIdentity(socialInfoBean.getUserIdentity());
					// user.setInviteCode(socialInfoBean.getInviteCode());
					user.setProvince(socialInfoBean.getProvince());
					user.setCity(socialInfoBean.getCity());
					user.setDistrict(socialInfoBean.getDistrict());
					user.setSignature(socialInfoBean.getSignature());
				}

				OauthsBean oauthsBean=dataBean.getOauths();
				if(oauthsBean!=null){
					user.setEmail(oauthsBean.getEmail());
					user.setQQ(oauthsBean.getQQ());
					user.setWechat(oauthsBean.getWechat());
					user.setWeibo(oauthsBean.getWeibo());
				}
				user.setAccid(dataBean.getAccid());
				user.setAccToken(dataBean.getAccToken());
				user.setPass(pass);
				user.setOauthId(oauthId);
				user.setOauthName("");
				user.setOauthOpenId("");
				user.setOauthToken("");
				user.setIsThird(0);
			}
		}
		return user;
	}
//第三方登录
	public static User parseThirdLoginMsgAndSetUser(String msg, String pass,
			String oauthId, ThirdLoginBean thirdLoginBean) {
		LoginResultBean resultBean = JsonTools.fromJson(msg,
				LoginResultBean.class);
		User user = new User();
		if (resultBean != null) {
			LoginDataBean dataBean = resultBean.getDataBean();
			if (dataBean != null) {
				LoginOnlineUserBean onlineUserBean = dataBean
						.getOnlineUserBean();
				LoginAccountBean accountBean = dataBean.getAccountBean();
				if (onlineUserBean != null) {
					user.setUsername(onlineUserBean.getUsername());
					user.setUserId(onlineUserBean.getUserId());
					// LogHelper.d("----注册返回的邮箱地址:"+onlineUserBean.getEmail());
					user.setNickName(onlineUserBean.getNickName());
					user.setUserImg(onlineUserBean.getUserImg());
					user.setInviteCode(onlineUserBean.getInviteCode());
				}
				if (accountBean != null) {
					user.setCcCoin(accountBean.getCcCoin());
					user.setVoucher(accountBean.getVoucher());
					user.setAccVoucher(accountBean.getAccVoucher());
					user.setFrzVoucher(accountBean.getFrzVoucher());
					user.setFrzCcCoin(accountBean.getFrzCcCoin());
					user.setAmounts(accountBean.getAmounts());
					user.setFrzAmounts(accountBean.getFrzAmounts());
				}

				SocialInfoBean socialInfoBean = dataBean.getSocialInfoBean();
				if (socialInfoBean != null) {
					user.setUserIdentity(socialInfoBean.getUserIdentity());
					// user.setInviteCode(socialInfoBean.getInviteCode());
					user.setProvince(socialInfoBean.getProvince());
					user.setCity(socialInfoBean.getCity());
					user.setDistrict(socialInfoBean.getDistrict());
					user.setSignature(socialInfoBean.getSignature());
				}

				OauthsBean oauthsBean=dataBean.getOauths();
				if(oauthsBean!=null){
					user.setEmail(oauthsBean.getEmail());
					user.setQQ(oauthsBean.getQQ());
					user.setWechat(oauthsBean.getWechat());
					user.setWeibo(oauthsBean.getWeibo());
				}
				
				
				user.setPass(pass);
				user.setOauthId(oauthId);
				user.setOauthName(thirdLoginBean.getOauthName());
				user.setOauthOpenId(thirdLoginBean.getOauthOpenId());
				user.setOauthToken(thirdLoginBean.getOauthToken());
				user.setIsThird(1);// 第三方登录标识
			}
		}
		return user;
	}

	public static User parseThirdLoginMsgAndSetUser(String msg,
			ThirdLoginBean thirdLoginBean) {
		LoginResultBean resultBean = JsonTools.fromJson(msg,
				LoginResultBean.class);
		User user = new User();
		if (resultBean != null) {
			LoginDataBean dataBean = resultBean.getDataBean();
			if (dataBean != null) {
				LoginOnlineUserBean onlineUserBean = dataBean
						.getOnlineUserBean();
				LoginAccountBean accountBean = dataBean.getAccountBean();
				if (onlineUserBean != null) {
					user.setUsername(onlineUserBean.getUsername());
					user.setUserId(onlineUserBean.getUserId());
					// LogHelper.d("----注册返回的邮箱地址:"+onlineUserBean.getEmail());
					user.setNickName(onlineUserBean.getNickName());
					user.setUserImg(onlineUserBean.getUserImg());
					user.setInviteCode(onlineUserBean.getInviteCode());
				}
				if (accountBean != null) {
					user.setCcCoin(accountBean.getCcCoin());
					user.setVoucher(accountBean.getVoucher());
					user.setAccVoucher(accountBean.getAccVoucher());
					user.setFrzVoucher(accountBean.getFrzVoucher());
					user.setFrzCcCoin(accountBean.getFrzCcCoin());
					user.setAmounts(accountBean.getAmounts());
					user.setFrzAmounts(accountBean.getFrzAmounts());
				}

				SocialInfoBean socialInfoBean = dataBean.getSocialInfoBean();
				if (socialInfoBean != null) {
					user.setUserIdentity(socialInfoBean.getUserIdentity());
					// user.setInviteCode(socialInfoBean.getInviteCode());
					user.setProvince(socialInfoBean.getProvince());
					user.setCity(socialInfoBean.getCity());
					user.setDistrict(socialInfoBean.getDistrict());
					user.setSignature(socialInfoBean.getSignature());
				}

				OauthsBean oauthsBean=dataBean.getOauths();
				if(oauthsBean!=null){
					user.setEmail(oauthsBean.getEmail());
					user.setQQ(oauthsBean.getQQ());
					user.setWechat(oauthsBean.getWechat());
					user.setWeibo(oauthsBean.getWeibo());
				}
				
				
				user.setPass("");
				user.setOauthName(thirdLoginBean.getOauthName());
				user.setOauthOpenId(thirdLoginBean.getOauthOpenId());
				user.setOauthToken(thirdLoginBean.getOauthToken());
				user.setIsThird(1);// 设置为第三方登录标识

			}
		}
		return user;
	}

	/**
	 * 自动登录更新数据库
	 * 
	 * @param msg
	 *  user
	 */
	public static User upDb(String msg, User us) {
		User user = new User();
		LoginResultBean resultBean = JsonTools.fromJson(msg,
				LoginResultBean.class);
		if (resultBean != null) {
			LoginDataBean dataBean = resultBean.getDataBean();
			if (dataBean != null) {
				LoginOnlineUserBean onlineUserBean = dataBean
						.getOnlineUserBean();
				// LoginUserInfoBean userInfoBean=dataBean.getUserInfoBean();
				LoginAccountBean accountBean = dataBean.getAccountBean();
				if (onlineUserBean != null) {
					user.setUsername(onlineUserBean.getUsername());
					user.setUserId(onlineUserBean.getUserId());
					// LogHelper.d("----注册返回的邮箱地址:"+onlineUserBean.getEmail());
					user.setNickName(onlineUserBean.getNickName());
					user.setUserImg(onlineUserBean.getUserImg());
					user.setInviteCode(onlineUserBean.getInviteCode());
				}
				if (accountBean != null) {
					user.setCcCoin(accountBean.getCcCoin());
					user.setVoucher(accountBean.getVoucher());
					user.setAccVoucher(accountBean.getAccVoucher());
					user.setFrzVoucher(accountBean.getFrzVoucher());
					user.setFrzCcCoin(accountBean.getFrzCcCoin());
					user.setAmounts(accountBean.getAmounts());
					user.setFrzAmounts(accountBean.getFrzAmounts());
				}

				SocialInfoBean socialInfoBean = dataBean.getSocialInfoBean();
				if (socialInfoBean != null) {
					user.setUserIdentity(socialInfoBean.getUserIdentity());
					// user.setInviteCode(socialInfoBean.getInviteCode());
					user.setProvince(socialInfoBean.getProvince());
					user.setCity(socialInfoBean.getCity());
					user.setDistrict(socialInfoBean.getDistrict());
					user.setSignature(socialInfoBean.getSignature());
				}

				OauthsBean oauthsBean=dataBean.getOauths();
				if(oauthsBean!=null){
					user.setEmail(oauthsBean.getEmail());
					user.setQQ(oauthsBean.getQQ());
					user.setWechat(oauthsBean.getWechat());
					user.setWeibo(oauthsBean.getWeibo());
				}
				
				user.setPass(us.getPass());
				user.setOauthId(us.getOauthId());
				user.setOauthName(us.getOauthName());
				user.setOauthOpenId(us.getOauthOpenId());
				user.setOauthToken(us.getOauthToken());
				user.setIsThird(us.getIsThird());
			}

		}
		return user;

	}

	/**
	 * @Title: upUser
	 * @Description: TODO 更新user中的资金数据
	 * @param @param msg
	 * @param @param us
	 * @param @return 设定文件
	 * @return User 返回类型
	 * @throws
	 */
	public static User upUser(String msg, User us) {

		User user = new User();
		user = us;
		AccountResultBean resultBean = JsonTools.fromJson(msg,
				AccountResultBean.class);

		// LogHelper.d(""+accountBean.toString());
		if (resultBean != null) {
			AccountBean accountBean = resultBean.getAccountBean();
			if (accountBean != null) {
				user.setUserId(accountBean.getUserId());
				user.setCcCoin(accountBean.getCcCoin());
				user.setVoucher(accountBean.getVoucher());
				user.setAccVoucher(accountBean.getAccVoucher());
				user.setFrzCcCoin(accountBean.getFrzCcCoin());
				user.setFrzVoucher(accountBean.getFrzVoucher());
				user.setAmounts(accountBean.getAmounts());
				user.setFrzAmounts(accountBean.getFrzAmounts());
			}

		}

		return user;

	}

	/**
	 * @Title: thirdLoginNoregist
	 * @Description: TODO 第三方登录是否注册过
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean thirdLoginNoregist(String msg) {
		LoginResultBean resultBean = JsonTools.fromJson(msg,
				LoginResultBean.class);
		if (resultBean != null) {
			LoginDataBean dataBean = resultBean.getDataBean();
			if (dataBean != null) {
				int logincode = dataBean.getLoginCode();
				if (logincode == 6) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @Title: parsePasskey
	 * @Description: TODO 解析passkey
	 * @param @param msg
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String parsePasskey(String msg) {
		GetPassKey key = JsonTools.fromJson(msg, GetPassKey.class);
		String passkey = null;
		if (key != null) {
			passkey = key.getData().getPassKey();
		}
		return passkey;
	}


	/**
	 * @Title: parseCashDetail
	 * @Description: TODO 解析交易明细
	 * @param @param msg
	 * @param @return 设定文件
	 * @return List<CashRollBean> 返回类型
	 * @throws
	 */
	public static List<TradeBean> parseTradeDetail(String msg) {
		List<TradeBean> list = new ArrayList<TradeBean>();
		TradeResultBean cashRollResultBean = JsonTools.fromJson(msg,
				TradeResultBean.class);
		if (cashRollResultBean != null) {
			TradeDetilBean detilBean = cashRollResultBean.getData();
			if (detilBean != null) {
				list = detilBean.getCash_list();
			}
		}
		return list;
	}

	/**
	 * @Title: parseCashDetail
	 * @Description: TODO 解析现金卷明细
	 * @param @param msg
	 * @param @return 设定文件
	 * @return List<CashRollBean> 返回类型
	 * @throws
	 */
	public static List<CashRollBean> parseCashDetail(String msg) {
		List<CashRollBean> list = new ArrayList<CashRollBean>();
		CashRollResultBean cashRollResultBean = JsonTools.fromJson(msg,
				CashRollResultBean.class);
		if (cashRollResultBean != null) {
			CashRollDetilBean detilBean = cashRollResultBean.getData();
			if (detilBean != null) {
				list = detilBean.getCash_list();
			}
		}
		return list;
	}

	/**
	 * @Title: parseBooleanMsg
	 * @Description: TODO 成功是否
	 * @param @param msg
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean parseBooleanMsg(String msg) {
		BooleanDefaultBean bean = JsonTools.fromJson(msg,
				BooleanDefaultBean.class);
		if (bean.isData())
			return true;
		else
			return false;
	}

	/**
	 * @Title: parseToken
	 * @Description: TODO 获取 token
	 * @param @param msg
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String parseToken(String msg) {
		String token = null;

		TokenResultBean bean = JsonTools.fromJson(msg, TokenResultBean.class);
		token = bean.getData().getToken();
		return token;
	}


	/**
	 * @Title: imag_address
	 * @Description: TODO 获取拼接的图片地址
	 * @param @param ls
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String imag_address(List<String> ls) {
		String as = "";
		for (int i = 0; i < ls.size(); i++) {
			String s = ls.get(i);
			if (i == 0) {
				as = as + s + ",";
			} else {
				as = as + s;
			}
		}
		return as;
	}

	/**
	 * @Title: alRegist
	 * @Description: TODO 是否注册
	 * @param @param msg
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean alRegist(String msg) {
		// 您的手机号码已在穿穿注册,请直接登录
		boolean isb = msg.startsWith("您的手机");// 判断字符串是否已您的二字开头
		return isb;
	}


	public static String getNewImag(String url, int width, int hight) {
		String nu = null;
		int index = url.lastIndexOf(".");
		String exsg = url.substring(0, index);
		String exend = url.substring(index);

		String new_dev = null;
		if (width == 0) {

			new_dev = "x" + hight;
		} else if (hight == 0) {
			new_dev = width + "x";
		} else {

			new_dev = width + "x" + hight;
		}
		nu = exsg + "_" + new_dev + exend;
		// LogHelper.d("----banner 地址:"+nu);
		return nu;
	}

	/**
	 * @param version
	 * @param url
	 * @return 版本信息、关于我们地址
	 */
	public static String getVersionUrl(String version, String url) {
		String versionInfoUrl = null;
		int index = version.lastIndexOf(".");
		String exsg = version.substring(0, index);

		versionInfoUrl = url.replace("{0}", exsg);
		LogHelper.d("---版本信息、关于我们地址->" + versionInfoUrl);

		return versionInfoUrl;
	}

	public static String[] getNewproducts(List<NewProductBean> newProductBeans) {
		int size = newProductBeans.size();
		String[] ls = new String[size];
		String ul = null;
		NewProductBean bean = null;
		for (int i = 0; i < size; i++) {
			bean = newProductBeans.get(i);
			ul = bean.getMainImg();
			if (!StringUtil.isNull(ul)) {
				ls[i] = ul;
			}
		}
		return ls;

	}

	/**
	 * @Title: getDoubleNumber
	 * @Description: TODO 保留小数点后两位
	 * @param @param count
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getDoubleNumber(double count) {
		String nc = "";
		DecimalFormat df = new DecimalFormat("######0.00");
		nc = df.format(count);
		return nc;
	}

	public static void addAppAccess(Context context, AppAccessBean bean) {
//		if (bean != null) {
//			// LogHelper.d("----- 添加");
//			Constant.aabs.add(bean);
//			// LogHelper.d("----- 添加后：" + Constant.aabs.size());
//			Constant.aabs_count = Constant.aabs_count + 1;
//
//		}

	}

	public static void sendAppaccSucc(Context context) {
//		Constant.aabs.removeAll(Constant.aabs);
//		Constant.aabs_count = 0;
//		DBTools.removeAllAppAccessList(context);
//		LogHelper.d("---发送访问记录成功,并清除");
	}

	public static List<StringListBean> getls(List<String> lt) {
		List<StringListBean> ls = new ArrayList<StringListBean>();
		StringListBean bean = null;
		for (int i = 0; i < lt.size(); i++) {
			bean = new StringListBean();
			// LogHelper.d("--s:"+s);
			// StringListBean bean=new StringListBean();
			bean.setSname(lt.get(i));
			ls.add(bean);
		}
		return ls;
	}

	public static List<String> getShareData(String msg) {

		String[] ss = msg.split("&");

		List<String> ls = new ArrayList<String>();
		for (int i = 0; i < ss.length; i++) {
			String[] s = ss[i].split("=");
			LogHelper.d("----分享字段:" + s[1]);
			ls.add(s[1]);
		}

		LogHelper.d("----分享字段:" + ls.size());
		return ls;
	}

	/**
	 * @Title: getPayBean
	 * @Description: TODO 解析H5的微信支付数据
	 * @param @return 设定文件
	 * @return PayBean 返回类型
	 * @throws
	 */
	public static PayBean getPayBean(String msg) {
		PayBean payBean = new PayBean();
		PayBean payBean1 = null;
		payBean1 = JsonTools.fromJson(msg, PayBean.class);

		if (payBean1 != null) {
			payBean = payBean1;
			payBean.setAyType("1");
		}

		LogHelper.d("----支付实体类:" + payBean.toString());
		return payBean;
	}

//	public static void pay(final Context context, final IWXAPI msgApi,
//			final Handler handler, final PayTask alipay, final String payType,
//			final String payMsg) {
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//				LogHelper.d("---支付解码前:" + payType);
//				// String type = getPayType(url);
//				String type = payType;
//				LogHelper.d("---支付类型:" + type);
//				if (type.equals("1")) {
//					// 微信支付
//
//					if (!PayTool.isWXAppInstalledAndSupported(context, msgApi)) {
//						// ToastHelper.show(context, "未安装微信");
//						Message message = handler.obtainMessage();
//						message.what = 3;
//						handler.sendMessage(message);
//						return;
//					}
//					// String wx = getPaymg(url);
//					String wx = payMsg;
//					LogHelper.d("----微信支付解码后:" + wx);
//					PayBean payBean = DataContoler.getPayBean(wx);
//					PayReq req = PayTool.setWXPayReq(payBean);
//
//					PayTool.sendPayReq(msgApi, req);
//
//				} else {
//					// 支付宝支付
//					// 调用支付接口，获取支付结果
//
//					// boolean isExist=alipay.checkAccountIfExist();
//					// if(!isExist){
//					//
//					// Message message = handler.obtainMessage();
//					// message.what = 4;
//					// handler.sendMessage(message);
//					// return;
//					// }
//
//					// String zfb = getPaymg(url);
//					String zfb = payMsg;
//					LogHelper.d("----支付宝支付解码后:" + zfb);
//					String result = alipay.pay(zfb);
//					PayResult payResult = new PayResult(result);
//
//					PayResultBean payResultBean = new PayResultBean();
//					payResultBean.setResultStatus(payResult.getResultStatus());
//					payResultBean.setMemo(payResult.getMemo());
//					payResultBean.setResult(payResult.getResult());
//
//					if (TextUtils.equals(payResult.getResultStatus(), "9000")) {
//						// LogHelper.d("---支付宝支付成功");
//
//						sendHandlerMsg(handler, 0, payResultBean);
//
//					} else {
//						if (TextUtils.equals(payResult.getResultStatus(),
//								"8000")) {
//							// LogHelper.d("---支付宝支付结果确认中");
//							sendHandlerMsg(handler, 1, payResultBean);
//						} else {
//
//							// LogHelper.d("---支付宝支付失败："
//							// + payResult.getResultStatus());
//							sendHandlerMsg(handler, 2, payResultBean);
//						}
//					}
//					// LogHelper.d("---支付宝:" + zfb);
//				}
//
//			}
//		}).start();
//
//		// String paystr=StringUtil.getH5Pay(url);
//
//	}

	public static WebBackBean getWebBack(String msg) {
		WebBackBean bean = new WebBackBean();
		String ss = msg.substring(msg.lastIndexOf("?") + 1, msg.length());
		if (ss != null) {
			String[] s = ss.split("&");
			String si[] = s[0].split("=");
			bean.setShow(si[1]);

			String sii[] = s[1].split("=");
			if (sii.length == 2) {
				if (StringUtil.isNull(sii[1])) {
					bean.setUrl("");
				} else {
					String sig;
					try {
						sig = URLDecoder.decode(sii[1], "UTF-8");
						bean.setUrl(sig);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				bean.setUrl("");
			}

		}
		return bean;
	}

	public static WebTvBean getWebTvBean(String msg) {
		WebTvBean bean = new WebTvBean();
		String ss = msg.substring(msg.lastIndexOf("?") + 1, msg.length());
		if (ss != null) {
			String[] s = ss.split("&");

			String si[] = s[0].split("=");
			if (si.length == 2)
				bean.setShow(si[1]);// 显示、隐藏

			String sii[] = s[2].split("=");
			if (sii.length == 2) {
				if (StringUtil.isNull(sii[1])) {
					bean.setUrl("");
				} else {
					String sig;
					try {
						sig = URLDecoder.decode(sii[1], "UTF-8");
						bean.setUrl(sig);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				bean.setUrl("");
			}

			String sText[] = s[1].split("=");
			if (sText.length == 2) {
				if (StringUtil.isNull(sText[1])) {
					bean.setText("");
				} else {
					String sig;
					try {
						sig = URLDecoder.decode(sText[1], "UTF-8");
						bean.setText(sig);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				bean.setText("");
			}

		}
		return bean;
	}

	public static void sendHandlerMsg(Handler handler, int index,
			PayResultBean payResultBean) {
		Message message = handler.obtainMessage();
		message.what = index;
		message.obj = payResultBean;
		handler.sendMessage(message);
	}

	/**
	 * @Title: showToast
	 * @Description: TODO html toast提示
	 * @param @param context
	 * @param @param msg 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void showToast(Context context, String msg) {
		String s = getSpiltmsg(msg);
		if (s != null) {
			String[] ns = s.split("=");
			if (ns != null) {
				try {
					if (ns.length == 2) {
						String si = URLDecoder.decode(ns[1], "UTF-8");
						ToastHelper.show(context, si);
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static ShareBean getShare(Context context, String msg) {
		String s = getSpiltmsg(msg);
		// List<String> ls = new ArrayList<String>();
		ShareBean shareBean = new ShareBean();
		if (s != null) {
			String[] ns = s.split("&");
			if (ns != null) {
				try {
					for (int i = 0; i < ns.length; i++) {
						String sg[] = ns[i].split("=");
						if (sg.length == 2) {
							if (sg[0].equals("title")) {
								if (!StringUtil.isNull(sg[1])) {
									String si = URLDecoder.decode(sg[1],
											"UTF-8");
									shareBean.setTitle(si);
								}
							}
							if (sg[0].equals("content")) {
								if (!StringUtil.isNull(sg[1])) {
									String si = URLDecoder.decode(sg[1],
											"UTF-8");
									shareBean.setContent(si);
								}
							}
							if (sg[0].equals("image")) {
								if (!StringUtil.isNull(sg[1])) {
									String si = URLDecoder.decode(sg[1],
											"UTF-8");
									shareBean.setImagUrl(si);
								}
							}
							if (sg[0].equals("url")) {
								if (!StringUtil.isNull(sg[1])) {
									String si = URLDecoder.decode(sg[1],
											"UTF-8");
									shareBean.setUrl(si);
								}
							}

							// LogHelper.d("---分享解码前:" + sg[1]);
							// String si = URLDecoder.decode(sg[1], "UTF-8");
							// LogHelper.d("---分享解码后:" + si);

						}

					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return shareBean;
	}

	public static String getSpiltmsg(String msg) {
		String ss = msg.substring(msg.lastIndexOf("?") + 1, msg.length());
		// LogHelper.d("---截取后:" + ss);
		return ss;
	}

	public static String[] getPayLsString(String msg) {
		String s = getSpiltmsg(msg);
		String[] ss = s.split("&");

		return ss;
	}

	/**
	 * @Title: getPayType
	 * @Description: TODO 获取支付类型
	 * @param @param ms
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getPayType(String ms) {
		String s1 = getPayLsString(ms)[0];

		String[] type = s1.split("=");

		return type[1];
	}

	/**
	 * @Title: getPaymg
	 * @Description: TODO 获取支付信息
	 * @param @param ms
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getPaymg(String ms) {
		String s1 = getPayLsString(ms)[1];
		String[] type_msg = s1.split("=");
		String msg = null;
		try {
			msg = URLDecoder.decode(type_msg[1], "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LogHelper.d("----解码后:"+msg);
		return msg;
	}

	/**
	 * @Title: getUpimgUrl
	 * @Description: TODO 获取上传头像成功后的地址
	 * @param @param msg
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getUpimgUrl(String msg) {
		UpImgResultBean resultBean = JsonTools.fromJson(msg,
				UpImgResultBean.class);
		String url = null;
		if (resultBean != null) {
			UpImgBean imgBean = resultBean.getData();
			if (imgBean != null) {
				url = imgBean.getImageUrl();
			}
		}

		return url;
	}

	public static String getThird(String msg) {
		String thirdBean = null;
		ThirdResultBean bean = JsonTools.fromJson(msg, ThirdResultBean.class);
		if (bean != null) {
			thirdBean = bean.getData().getOauthId();
		}

		return thirdBean;
	}

	/**
	 * @Title: cleanThirdData
	 * @Description: TODO 清空第三方数据
	 *  设定文件
	 * @return void 返回类型
	 * @throws
	 */
//	public static void cleanThirdData(Context context) {
//		Platform qq = ShareSDK.getPlatform(context, QQ.NAME);
//		Platform wx = ShareSDK.getPlatform(context, Wechat.NAME);
//		Platform xl = ShareSDK.getPlatform(context, SinaWeibo.NAME);
//		if (qq.isValid()) {
//			clean(qq);
//		} else if (wx.isValid()) {
//			clean(wx);
//		} else if (xl.isValid()) {
//			clean(xl);
//		}
//
//	}
//
//	public static void clean(Platform platform) {
//		platform.SSOSetting(false);
//		platform.removeAccount();
//		// ShareSDK.removeCookieOnAuthorize(true);
//	}

	/**
	 * @Title: isColl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param accessBean
	 * @param @param isco 设定文件
	 * @return void 返回类型
	 * @throws
	 */
//	public static void isColl(AppAccessBean accessBean, boolean isco) {
//		if (isco) {
//			// 进入的时间
//			accessBean.setStartTime(TimeUtil.getNowCurrtimename());
//			// if (TimeUtil.getTimeCut(MyApplication.StartTime,
//			// TimeUtil.getNowCurrtimename())) {
//			// // sendCurrAppAccess();
//			// }
//		}
//	}

	/**
	 * @Title: addColl
	 * @Description: TODO 添加统计
	 * @param @param accessBean
	 * @param @param isco
	 * @param @param context 设定文件
	 * @return void 返回类型
	 * @throws
	 */
//	public static void addColl(AppAccessBean accessBean, boolean isco,
//			Context context) {
//		if (isco) {
//			accessBean.setExitTime(TimeUtil.getNowCurrtimename());
//			DataContoler.addAppAccess(context, accessBean);
//			// LogHelper.d("---添加记录:" + accessBean.toString());
//			// LogHelper.d("---添加后记录数:" + Constant.aabs_count);
//			Constant.LasetViewId = accessBean.getViewId();
//			// if (Constant.aabs_count >= 100) {
//			// // sendCurrAppAccess();
//			// }
//		}
//	}

//	public static void parseMyCode(String msg) {
//		DefaultBean bean = JsonTools.fromJson(msg, DefaultBean.class);
//		if (bean != null) {
//			MyApplication.my_code = bean.getData();
//		}
//	}

//	public static void ShareData(Activity context, String url, boolean isYq) {
//
//		ShareBean shareBean = DataContoler.getShare(context, url);
//		if (shareBean != null) {
//			ShareTool.showShare(context, shareBean, isYq);
//		}
//	}

//	public static void shareData(Activity activity, String title,
//			String content, String image, String url, String types, boolean isYq) {
//
//		ShareBean shareBean = new ShareBean();
//		shareBean.setContent(content);
//		shareBean.setImagUrl(image);
//		shareBean.setTitle(title);
//		shareBean.setTypes(types);
//		shareBean.setUrl(url);
//
//		if (shareBean != null) {
//			ShareTool.showShare(activity, shareBean, isYq);
//		}
//	}

	/**
	 * @Title: addAccess
	 * @Description: TODO
	 * @param @param nowId 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void addAccess(Activity activity, String nowId) {
//		if (lastAccessBean == null) {
//			lastAccessBean = new AppAccessBean();
//
//			last_time = TimeUtil.getNowCurrtimename();
//			lastAccessBean.setPreviousViewId("0");
//			lastAccessBean.setExitTime("1900-01-01");
//			lastAccessBean.setViewId("0");
//			lastAccessBean.setStartTime(TimeUtil.getNowCurrtimename());
//		}
//
//		// 发送上一次的数据
//		AppAccessBean appBean = new AppAccessBean();
//		appBean.setViewId(lastAccessBean.getViewId());
//		appBean.setExitTime(TimeUtil.getNowCurrtimename());
//		appBean.setPreviousViewId(lastAccessBean.getPreviousViewId());
//		appBean.setStartTime(lastAccessBean.getStartTime());
//
//		// 利用当前数据，设置上一条数据
//		lastAccessBean.setPreviousViewId(lastAccessBean.getViewId());
//		lastAccessBean.setViewId(nowId);
//		lastAccessBean.setExitTime("1900-01-01");
//		lastAccessBean.setStartTime(TimeUtil.getNowCurrtimename());
//
//		if (appBean.getViewId().equals("0")) {
//			// LogHelper.d("------忽略这条访问记录");
//			return;
//		}
//
//		Constant.aabs.add(appBean);
//		// LogHelper.e("----- 添加的数据：" + appBean.toString());
//		// LogHelper.d("----- 添加后：" + Constant.aabs.size());
//		Constant.aabs_count = Constant.aabs_count + 1;
//
//		if (Constant.aabs_count >= UrlManager.appAccess_count()
//				|| TimeUtil
//						.getTimeCut(TimeUtil.getNowCurrtimename(), last_time)) {
//
//			last_time = TimeUtil.getNowCurrtimename();
//			// 往服务端发送事件请求
//			sendCurrAppAccess(activity);
//		}

	}

//	public static void sendCurrAppAccess(Activity activity) {
//		if (ConnectionUtil.isConn(activity)) {
//			if (Constant.aabs.size() > 0) {
//				Constant.aabs_count = Constant.aabs.size();
//				final String aabs_string = JsonTools.toJson(Constant.aabs);
//				LogHelper.e("----- 发送添加的访问数据");
//				MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
//						activity, HttpTools.TAG_APPACCESS,
//						Constant.REQUEST_POST, ParamsTools.appAccess(
//								UrlManager.APP_ACCESS(), aabs_string),
//						BaseActivity.mainHttpCallBack, "", false));
//			}
//		}
//
//	}

//	public static void sendExAccess(BaseActivity activity, HttpCallBack callBack) {
//		if (MyApplication.exs.size() > 0) {
//			// LogHelper.e("----发送错误信息-");
//			String ex_string = JsonTools.toJson(MyApplication.exs);
//			// LogHelper.e("----发送错误信息:"+ex_string);
//
//			MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
//					activity, HttpTools.TAG_APPEX, Constant.REQUEST_POST,
//					ParamsTools.appEx(UrlManager.APP_EX(), ex_string),
//					callBack, "", false));
//		}
//	}

	/**
	 * @Title: parseInvideInfo
	 * @Description: TODO 解析奖励信息
	 * @param @param msg
	 * @param @return 设定文件
	 * @return List<ActCpModelBean> 返回类型
	 * @throws
	 */
	public static List<ActCpModelBean> parseInvideInfo(String msg) {
		List<ActCpModelBean> acs = new ArrayList<ActCpModelBean>();
		AwardResultBean awardResultBean = JsonTools.fromJson(msg,
				AwardResultBean.class);
		if (awardResultBean != null) {
			AwardBean bean = awardResultBean.getData();
			if (bean != null) {
				List<ActCpModelBean> as = bean.getActCpModels();
				if (as != null && as.size() > 0) {
					acs = as;
				}
			}
		}
		return acs;
	}

	public static AwardBean parseInvideInfo1(String msg) {
		AwardBean bean = new AwardBean();
		AwardResultBean awardResultBean = JsonTools.fromJson(msg,
				AwardResultBean.class);
		if (awardResultBean != null) {
			bean = awardResultBean.getData();
		}
		return bean;
	}

	public static ActCpModelBean getRegistAwInfo(List<ActCpModelBean> ls) {
		ActCpModelBean acs = new ActCpModelBean();
		int num = 0;
		if (ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				ActCpModelBean bean = ls.get(i);
				if (bean.getCpType() == 1) {
					num = num + bean.getParValue();
				}

			}
			acs.setParValue(num);
		}
		return acs;
	}

	public static WebSimpleDialogBean parseWebSimpleDialog(String msg) {
		WebSimpleDialogBean bean = JsonTools.fromJson(msg,
				WebSimpleDialogBean.class);
		return bean;
	}

	public static AwardBean parseAward(String msg, User user) {
		// LogHelper.d("----邀请前00000："+msg);
		AwardBean awardBean = new AwardBean();
		AwardResultBean resultBean = JsonTools.fromJson(msg,
				AwardResultBean.class);
		if (resultBean != null) {
			awardBean = resultBean.getData();

			// par

			String content = awardBean.getShareContent();
			// LogHelper.d("----邀请前11111："+content);

			String invide = user.getInviteCode();
			// LogHelper.d("----邀请前,我的邀请码："+invide);
			if (!StringUtil.isNull(invide)) {
				String mg = content.replace("{{INVITECODE}}", invide);
				awardBean.setShareContent(mg);
				// LogHelper.d("----邀请替换后："+awardBean.toString());
			}

		}

		return awardBean;
	}

	public static AwardBean parseAward1(AwardBean bean, User user) {
		String content = bean.getShareContent();
		// LogHelper.d("----邀请前："+content);
		String invide = user.getInviteCode();
		// LogHelper.d("----邀请前,我的邀请码："+invide);
		// String mg=content.format("", invide);
		String mg = content.replace("{{INVITECODE}}", invide);
		bean.setShareContent(mg);
		// LogHelper.d("----邀请替换后："+mg);
		return bean;
	}

	public static String web_title(String msg) {
		String sg = null;
		String ss = msg.substring(msg.lastIndexOf("?") + 1, msg.length());
		String s[] = ss.split("=");
		if (s.length == 2) {
			try {
				sg = URLDecoder.decode(s[1], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sg;
	}

	public static String getTitle(String title) {
		String mTitle = null;
		if (title.length() > 10) {
			mTitle = title.substring(0, 10) + "...";
		} else {
			mTitle = title;
		}

		return mTitle;
	}

	// 更新dialog
	public static void showUpdataDialog(VersionBean versionBean, Context context) {

		if (versionBean.getDescription() != null) {
			if (versionBean.isObsolete())
				setForceDialog(versionBean, context);
			else
				setDialog(versionBean, context);
		}

	}

	// 普通更新
	public static void setDialog(final VersionBean versionBean,
			final Context context) {
//		new PromptDialog.Builder(context)
//				.setMessage(versionBean.getDescription(), null)
//				.setTitle("发现新版本" + versionBean.getVersionCode())
//				.setButton1("立即更新", new PromptDialog.OnClickListener() {
//
//					@Override
//					public void onClick(Dialog dialog, int which) {
//						// TODO Auto-generated method stub
//						dialog.dismiss();
//						AppManager.openBrowser(context,
//								versionBean.getDownloadUrl());
//					}
//				}).setButton2("以后再说", new PromptDialog.OnClickListener() {
//
//					@Override
//					public void onClick(Dialog dialog, int which) {
//						// TODO Auto-generated method stub
//						dialog.dismiss();
//					}
//				}).show();
	}

	// 强制更新
	public static void setForceDialog(final VersionBean versionBean,
			final Context context) {
//		new PromptDialog.Builder(context)
//				.setMessage(versionBean.getDescription(), null)
//				.setTitle("发现新版本" + versionBean.getVersionCode())
//				.setButton1("立即更新", new PromptDialog.OnClickListener() {
//
//					@Override
//					public void onClick(Dialog dialog, int which) {
//						// TODO Auto-generated method stub
//						AppManager.openBrowser(context,
//								versionBean.getDownloadUrl());
//
//					}
//				})
//				// .setButton2("退出程序", new PromptDialog.OnClickListener() {
//				//
//				// @Override
//				// public void onClick(Dialog dialog, int which) {
//				// // TODO Auto-generated method stub
//				// dialog.dismiss();
//				// AppManager.getAppManager().AppExit(context);
//				// }
//				// })
//				.setCancelable(false).setCanceledOnTouchOutside(false).show();
	}

	/**
	 * 同步一下cookie
	 */
	public static void synCookies(Context context, String url) {
		try {
			CookieSyncManager.createInstance(context);
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.setAcceptCookie(true);
			String H5Cookie = PreferenceUtils.getPrefString(context, Constant.CC_COOKIE, "")
					+ "; domain=" + UrlManager.DOMAIN + "; path=/";
			LogHelper.e("---h5的cookie:" + H5Cookie);
			cookieManager.setCookie(url, H5Cookie);
			CookieSyncManager.getInstance().sync();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static String getLocation(double data) {
		String locString = null;
		DecimalFormat df = new DecimalFormat("######0.00");
		locString = df.format(data);
		return locString;
	}

	public static void sendAppStart(Activity activity,String RegSource) {

		// TODO Auto-generated method stub
//		AppStartBean appStartBean = new AppStartBean();
//		appStartBean.setStartTime(WelcomeActivity.StartTime);
//		appStartBean.setResolution(DeviceUtils.getDeviceDisplay(activity));
//		appStartBean.setDeviceLanguage(DeviceUtils.getDeviceLanguage(activity));
//		appStartBean.setNetworkType(DeviceUtils.getCurrentNetType(activity));
//		appStartBean.setNetworkProvider(DeviceUtils.getProvider(activity));
//		appStartBean.setLocalTime(TimeUtil.getNowCurrtimename());
//		appStartBean.setLocalTimeArea(DeviceUtils.getTimezone());
//		appStartBean.setSysNo("CC_ANDROID");
//		appStartBean.setRegSource(RegSource);
//		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(activity,
//				ParamsTools.TAG_APPSTART, Constant.REQUEST_POST, ParamsTools
//						.appStart(UrlManager.APP_START(), appStartBean),
//				BaseActivity.mainHttpCallBack, "", false));

	}

	
	public static String getJsonData(String msg) {
		String data = null;
		try {
			JSONObject jsonObject = new JSONObject(msg);
			data = jsonObject.getString("Data");
			LogHelper.d("------登录的data数据:" + data);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	//js 更新用户信息
	public static User upDbUser(User us, String msg) {
		User user = new User();
		LoginDataBean dataBean = JsonTools.fromJson(msg, LoginDataBean.class);

		LoginOnlineUserBean onlineUserBean = dataBean.getOnlineUserBean();
		// LoginUserInfoBean userInfoBean=dataBean.getUserInfoBean();
		LoginAccountBean accountBean = dataBean.getAccountBean();
		if (onlineUserBean != null) {
			user.setUsername(onlineUserBean.getUsername());
			user.setUserId(onlineUserBean.getUserId());
			// LogHelper.d("----注册返回的邮箱地址:"+onlineUserBean.getEmail());
			user.setNickName(onlineUserBean.getNickName());
			user.setUserImg(onlineUserBean.getUserImg());
			user.setInviteCode(onlineUserBean.getInviteCode());

		}
		if (accountBean != null) {
			user.setCcCoin(accountBean.getCcCoin());
			user.setVoucher(accountBean.getVoucher());
			user.setAccVoucher(accountBean.getAccVoucher());
			user.setFrzVoucher(accountBean.getFrzVoucher());
			user.setFrzCcCoin(accountBean.getFrzCcCoin());
			user.setAmounts(accountBean.getAmounts());
			user.setFrzAmounts(accountBean.getFrzAmounts());

		}

		SocialInfoBean socialInfoBean = dataBean.getSocialInfoBean();
		if (socialInfoBean != null) {
			user.setUserIdentity(socialInfoBean.getUserIdentity());
			// user.setInviteCode(socialInfoBean.getInviteCode());
			user.setProvince(socialInfoBean.getProvince());
			user.setCity(socialInfoBean.getCity());
			user.setDistrict(socialInfoBean.getDistrict());
			user.setSignature(socialInfoBean.getSignature());
		}

		OauthsBean oauthsBean=dataBean.getOauths();
		if(oauthsBean!=null){
			user.setEmail(oauthsBean.getEmail());
			user.setQQ(oauthsBean.getQQ());
			user.setWechat(oauthsBean.getWechat());
			user.setWeibo(oauthsBean.getWeibo());
		}
		
		user.setPass(us.getPass());
		user.setOauthId(us.getOauthId());
		user.setOauthName(us.getOauthName());
		user.setOauthOpenId(us.getOauthOpenId());
		user.setOauthToken(us.getOauthToken());
		user.setIsThird(us.getIsThird());
		user.setInviteCode(us.getInviteCode());

		return user;
	}

	public static String  getSid(String session){
		if(StringUtil.isNull(session))
			return "";
		
		String ssion[] =session.split(
				"=");
		String si = "";
		if (ssion != null){
			if(ssion.length>=2){
				String sg = ssion[1];
				if(sg.contains(";")){
					String [] kg=sg.split(";");
					si=kg[0];
				}else{
					si=sg;
				}
				
			}
		}
		return si;
	}




	// 高伟豪  添加忘记密码的方法


	/**
	 * @Title: parseLoginMsg
	 * @Description: TODO 登录信息的解析(普通登录)
	 * @param @param msg 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static User parseForgetPasswordAndSetUser(String msg, String pass,
											   String oauthId) {
		ForgetResultBean resultBean = JsonTools.fromJson(msg,
				ForgetResultBean.class);
		ForgetDataBean dataBean = resultBean.getDataBean();


		User user = new User();

		if (dataBean != null) {
			user.setUsername(dataBean.getUserName());
			user.setUserId(dataBean.getUserId());
			// LogHelper.d("----注册返回的邮箱地址:"+onlineUserBean.getEmail());
			user.setUserImg(dataBean.getUserImg());
//			user.setUserImg(onlineUserBean.getUserImg());
//			user.setInviteCode(onlineUserBean.getInviteCode());
		}


				user.setPass(pass);
				user.setUsername(oauthId);
				user.setOauthName("");
				user.setOauthOpenId("");
				user.setOauthToken("");
				user.setIsThird(0);


		return user;
	}







	public static ZhuantiWaterExtBean parseZhuantiWaterDetail(String msg) {
		ZhuantiWaterExtBean zhuantidata = new ZhuantiWaterExtBean();
		try {
			JSONObject jsonObject = new JSONObject(msg);
			String data_j = jsonObject.getString("Data");
			jsonObject = new JSONObject(data_j);
			data_j = jsonObject.getString("Topics");
			System.out.println("water fall data: " + data_j);

			JSONObject jsonObject2 = new JSONObject(data_j);

			zhuantidata.setImg(jsonObject2.getString("img"));

			zhuantidata.setMiaoshu(jsonObject2.getString("miaoshu"));


			zhuantidata.setName(jsonObject2.getString("name"));

			zhuantidata.setBigimg(jsonObject2.getString("bigimg"));


		} catch (JSONException e) {
			e.printStackTrace();
		}

		return  zhuantidata;



	}

	/**********先把msg解析成为bannerresultbean 然后分块bannerdatabean 最后放入到盛放banner的list里面**************************************/

	/**
	 * @Title: parseCashDetail
	 * @Description: TODO 解析Banner
	 * @param @param msg
	 * @param @return 设定文件
	 * @return List<CashRollBean> 返回类型
	 * @throws
	 */
	public static List<BannerDataBean> parseBannerDetail(String msg) {
		List<BannerDataBean> list = new ArrayList<>();//list变量承载所需数据，最后作为返回值
		//String  list=new String();
		try {
			JSONObject jsonObject = new JSONObject(msg);
			String s_data = jsonObject.getString("Data");
			System.out.println("s_data: " + s_data);//测试json
			//string转化为对象数组
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = parser.parse(s_data).getAsJsonArray();
			Gson gson = new Gson();
			for (JsonElement user : jsonArray) {
				//使用GSON，直接转成Bean对象
				BannerDataBean userBean = gson.fromJson(user, BannerDataBean.class);
				list.add(userBean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}


	//史力：将webapi得到的产品json解析称首页瀑布流所需的格式
	public static List<L1WaterItemBean> parseL1WaterfallDetail(String msg) {
		List<L1WaterItemBean> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(msg);
			String data_j = jsonObject.getString("Data");
			jsonObject = new JSONObject(data_j);
			data_j = jsonObject.getString("Rows");
			System.out.println("water fall data: " + data_j);
			//史力：Rows是包括方括号的，因此下面需要解析json包含的数组
			JSONArray arr = new JSONArray(data_j);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject tmp = (JSONObject) arr.get(i);
				L1WaterItemBean tmp_water_item = new L1WaterItemBean();
				tmp_water_item.setProd_name(tmp.getString("ProductName"));
				System.out.println("ProductName " + i + ": " + tmp.getString("ProductName"));
				tmp_water_item.setImg_url(tmp.getString("MainImage"));
				System.out.println("MainImage " + i + ": " + tmp.getString("MainImage"));
				tmp_water_item.setOrig_price(tmp.getString("OriginalPrice"));
				System.out.println("OriginalPrice " + i + ": " + tmp.getString("OriginalPrice"));
				list.add(tmp_water_item);
				System.out.println("===============================");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}







	//史力：将webapi得到的产品json解析称首页瀑布流所需的格式
	public static List<ZhuantiWaterBean> parseZhuantiWaterfallDetail(String msg) {
		List<ZhuantiWaterBean> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(msg);
			String data_j = jsonObject.getString("Data");
			jsonObject = new JSONObject(data_j);
			data_j = jsonObject.getString("List");
			System.out.println("water fall data: " + data_j);
			//史力：Rows是包括方括号的，因此下面需要解析json包含的数组
			JSONArray arr = new JSONArray(data_j);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject tmp = (JSONObject) arr.get(i);
				ZhuantiWaterBean tmp_water_item = new ZhuantiWaterBean();
				tmp_water_item.setProductid(tmp.getString("productid"));
//				System.out.println("ProductName " + i + ": " + tmp.getString("ProductName"));

				tmp_water_item.setProductmainpic(tmp.getString("productmainpic"));


				tmp_water_item.setProductname(tmp.getString("productname"));

//				System.out.println("MainImage " + i + ": " + tmp.getString("MainImage"));

				tmp_water_item.setPrice(tmp.getString("price"));

//				System.out.println("OriginalPrice " + i + ": " + tmp.getString("OriginalPrice"));
				list.add(tmp_water_item);
				System.out.println("===============================");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}





//晒图数据解析
	public static List<ShaituWaterBean> parseShaituWaterDetail(String msg) {
		List<ShaituWaterBean> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(msg);
			String data_j = jsonObject.getString("Data");
			jsonObject = new JSONObject(data_j);
			data_j = jsonObject.getString("Pagedata");
			System.out.println("water fall data: " + data_j);
			jsonObject = new JSONObject(data_j);
			data_j = jsonObject.getString("Rows");
			System.out.println("water fall data: " + data_j);


			//史力：Rows是包括方括号的，因此下面需要解析json包含的数组
			JSONArray arr = new JSONArray(data_j);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject tmp = (JSONObject) arr.get(i);
				ShaituWaterBean tmp_water_item = new ShaituWaterBean();


				tmp_water_item.setImage1(tmp.getString("Image1"));
//				System.out.println("ProductName " + i + ": " + tmp.getString("ProductName"));

				tmp_water_item.setProductId(tmp.getString("ProductId"));


//				System.out.println("OriginalPrice " + i + ": " + tmp.getString("OriginalPrice"));
				list.add(tmp_water_item);
				System.out.println("===============================");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}






	//我的收藏数据解析
	public static List<SaveItemBean> parseSaveItemDetail(String msg) {
		List<SaveItemBean> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(msg);
			String data_j = jsonObject.getString("Data");

			//史力：Rows是包括方括号的，因此下面需要解析json包含的数组
			JSONArray arr = new JSONArray(data_j);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject tmp = (JSONObject) arr.get(i);
				SaveItemBean tmp_water_item = new SaveItemBean();

				tmp_water_item.setProductId(tmp.getString("ProductId"));
				tmp_water_item.setMainImage(tmp.getString("MainImage"));
				tmp_water_item.setPrice(tmp.getString("Price"));
				tmp_water_item.setProductName(tmp.getString("ProductName"));

				list.add(tmp_water_item);
				System.out.println("===============================");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}





	/**
	 * @Title: parseCashDetail
	 * @Description: TODO 发送添加订单之后回调信息。接受之后分解出来新的orderid
	 * @param @param msg  {"Code":1,"Msg":"OK","Data":{"PayMomeny":100.0,"OrderId":9850}}
	 * @param @return 设定文件
	 * @return string 返回类型
	 * @throws
	 */


	public static String parseOrderId(String msg) {
		String orderid="";
		try{

			JSONObject jsonObject = new JSONObject(msg);
			String s_data = jsonObject.getString("Data");


			OrderBackBean resultBean = JsonTools.fromJson(s_data,
					OrderBackBean.class);


			if (resultBean != null) {
				orderid=resultBean.getOrderId();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}



		return orderid;


	}



	/**
	 * @Title: parseIdandSignStr
	 * @Description: TODO 解析
	 * @param @param msg
	 * @param @return 设定文件
	 * @return List<CashRollBean> 返回类型
	 * @throws
	 */

	public static PayInfoBean parseIdandSignStr(String msg) {

		PayInfoBean res=new PayInfoBean();
		try {

			JSONObject jsonObject = new JSONObject(msg);
			String s_data = jsonObject.getString("Data");


			JSONObject jsonObject1 = new JSONObject(s_data);
			String SignStr=jsonObject1.getString("SignStr");

			res.setSignStr(SignStr);




			JSONObject jsonObject2 = new JSONObject(s_data);
			String PayInfos=jsonObject2.getString("PayInfos");



			JSONObject jsonObject3 = new JSONObject(PayInfos);
			String OrderId=jsonObject3.getString("OrderId");

			JSONObject jsonObject4 = new JSONObject(PayInfos);
			String OrderType=jsonObject4.getString("OrderType");

			res.setOrderId(OrderId);
			res.setOrderType(OrderType);


		}
		catch (JSONException e) {
			e.printStackTrace();
		}



		return res;





	}











		/**
         * @Title: parseCashDetail
         * @Description: TODO 解析交易明细
         * @param @param msg
         * @param @return 设定文件
         * @return List<CashRollBean> 返回类型
         * @throws
         */
	public static List<BaoJiaWater> parseWaterDetail2(String msg) {

		System.out.println("#####in"+msg);

		List<BaoJiaWater> list = new ArrayList<>();
		WaterResultBean cashRollResultBean = JsonTools.fromJson(msg,
				WaterResultBean.class);
		System.out.println("#####in"+cashRollResultBean.toString());

		if (cashRollResultBean != null) {
			WaterBeanData detilBean = cashRollResultBean.getData();
			System.out.println("#####in"+detilBean.toString());

			if (detilBean != null) {
				list = detilBean.getRows();
				System.out.println("#####in"+list.get(0).toString());
			}
		}
		return list;
	}

	public static List<BaoJiaWater> parseWaterDetail(String msg) {
		System.out.println("#####in1" + msg);
		List<BaoJiaWater> list = new ArrayList<BaoJiaWater>();

		try {
//			List<BaoJiaWater> list = new ArrayList<BaoJiaWater>();
			JSONObject jsonObject = new JSONObject(msg);
			String s_data = jsonObject.getString("Data");
			JSONObject jsonObject2 = new JSONObject(s_data);
			String s_data2 = jsonObject2.getString("Rows");

			System.out.println("#####in2"+s_data2);

			JsonParser parser = new JsonParser();
			JsonArray jsonArray = parser.parse(s_data2).getAsJsonArray();
			Gson gson = new Gson();
			for (JsonElement user : jsonArray) {
				//使用GSON，直接转成Bean对象
				BaoJiaWater userBean = gson.fromJson(user, BaoJiaWater.class);
				System.out.println("#####in3"+userBean.getDescription());

				list.add(userBean);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("#####in4"+list.size());
		return list;
	}




}
