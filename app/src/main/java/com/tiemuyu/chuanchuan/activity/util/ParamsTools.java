package com.tiemuyu.chuanchuan.activity.util;

import android.util.Log;

import com.tiemuyu.chuanchuan.activity.bean.AppStartBean;
import com.tiemuyu.chuanchuan.activity.bean.BodyDataBean;
import com.tiemuyu.chuanchuan.activity.bean.PersonInfoBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;

import org.xutils.http.RequestParams;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName: ParamsTools
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hw
 * @date 2015-6-16
 */
public class ParamsTools {

	public static String TAG_APPSTART = "TAG_APPSTART";

	/**
	 * 获取passkey ---弃用
	 * */
	public static RequestParams getPasskey(String url, String sid, String IP,
			String LogonSys, String ClientType, String ClientVer) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.APP_IP, IP);
		params.addBodyParameter(Constant.LOGONSYS, LogonSys);
		params.addBodyParameter(Constant.CLIENTTYPE, ClientType);
		params.addBodyParameter(Constant.CLIENTVER, ClientVer);
		return params;
	}

	/**
	 * @Title: login
	 * @Description: TODO(登录传的参数)
	 * @param @param v
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams login(String url, String v, String oauthId) {
		System.out.println("用手机登录调用最后一步,String url, String v, String oauthId："+url+"   "+ v+" "+oauthId);
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.LOGIN_V, v);
		params.addBodyParameter(Constant.OAUTHID, oauthId);
		return params;
	}

	/**
	 * @Title: third_login
	 * @Description: TODO 第三方登录
	 * @param @param OauthName
	 * @param @param OauthOpenId
	 * @param @param OauthToken
	 * @param @param UserImg
	 * @param @param NickName
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams third_login(String url, String OauthName,
			String OauthOpenId, String OauthToken, String UserImg,
			String NickName) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.LOGIN_OAUTHNAME, OauthName);
		params.addBodyParameter(Constant.LOGIN_OAUTHOPENID, OauthOpenId);
		params.addBodyParameter(Constant.LOGIN_OAUTHTOKEN, OauthToken);
		params.addBodyParameter(Constant.LOGIN_USERIMG, UserImg);
		params.addBodyParameter(Constant.LOGIN_NICKNAME, NickName);
		return params;
	}

	/**
	 * @Title: getCode
	 * @Description: TODO 获取注册验证码
	 * @param @param mobile
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getCode(String url, String mobile,
			String invitedCode) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.MOBILE, mobile);
		if (!StringUtil.isNull(invitedCode)) {
			params.addBodyParameter(Constant.INVITEDCODE, invitedCode);
		}
		return params;
	}
	
	
	/**
	 * @Title: getCode
	 * @Description: TODO 测试一个参数的post方法
	 * @param @param mobile
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams trytry(String url, String addid
			) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.ADDRESS_ID, addid);
//		if (!StringUtil.isNull(invitedCode)) {
//			params.addBodyParameter(Constant.INVITEDCODE, invitedCode);
//		}

		return params;
	}
	
	
	
	
	
	
	
	

	/**
	 * @Title: regist
	 * @Description: TODO 注册
	 * @param @param v
	 * @param @param code
	 * @param @param invitedCode
	 * @param @param oauthId
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams regist(String url, String v, String code,
			String invitedCode, String oauthId, String token) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.LOGIN_V, v);
		params.addBodyParameter(Constant.CODE, code);
		params.addBodyParameter(Constant.INVITEDCODE, invitedCode);
		params.addBodyParameter(Constant.OAUTHID, oauthId);
		params.addBodyParameter(Constant.TOKEN, token);
		return params;
	}









	/**
	 * 上传图片、头像-单张
	 * */
	public static RequestParams upLodingImag(String url, String file_path) {
		RequestParams params = new RequestParams(url);
		// params.addBodyParameter("fileData", new File(file_path));
		params.setMultipart(true);
		params.addBodyParameter("fileData", new File(file_path), "image/jpeg");
		return params;
	}

	/**
	 * 上传u3d头像
	 * */
	public static RequestParams upLoadingU3dImag(String url, String file_path) {
		RequestParams params = new RequestParams(url);
		params.setMultipart(true);
		params.addBodyParameter("fileData", new File(file_path), "image/jpeg");
		return params;
	}
	
	/**
	 * 生成u3d头像
	 * */
	public static RequestParams createheadmodel(String url, String points) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter("points", points);
		return params;
	}
	
	
	/**
	 * 上传图片、头像-多张
	 * */
	public static RequestParams upLodingImag(String url, List<String> path) {
		RequestParams params = new RequestParams(url);
		params.setMultipart(true);
		for (int i = 0; i < path.size(); i++) {
			params.addBodyParameter("fileData" + i, new File(path.get(i)),
					"image/jpeg","多文件"+i+".jpg");
		}
		return params;
	}

	/**
	 * @Title: modify
	 * @Description: TODO 修改用户昵称和头像
	 * @param @param usrImg
	 * @param @param nickName
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams modify(String url, String usrImg,
			String nickName) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.USERIMG, usrImg);
		params.addBodyParameter(Constant.NICKNAME, nickName);
		return params;
	}

	/**
	 * @Title: testCode
	 * @Description: TODO 验证验证码
	 * @param @param code
	 * @param @param type
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams testCode(String url, String code, String type,
			String token) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.CODE, code);
		params.addBodyParameter(Constant.TYPE, type);
		params.addBodyParameter(Constant.TOKEN, token);
		return params;
	}

	/**
	 * @Title: reset_pass
	 * @Description: TODO 重置密码
	 * @param @param v
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams resetPass(String url,String mobile, String v) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.MOBILE, mobile);
		params.addBodyParameter(Constant.LOGIN_V, v);
		return params;
	}

	/**
	 * @Title: getUsername
	 * @Description: TODO 获取用户名
	 * @param @param email
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getUsername(String url, String email) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.EMAIL, email);
		return params;
	}

	/**
	 * @Title: getCodeForEmail
	 * @Description: TODO 根据邮箱获取验证码
	 * @param @param email
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getCodeForEmail(String url, String email) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.EMAIL, email);
		return params;
	}

	/**
	 * @Title: modifyPass
	 * @Description: TODO 修改密码
	 * @param @param v
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams modifyPass(String url, String v) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.LOGIN_V, v);
		return params;
	}

	/**
	 * @Title: getAccountContact
	 * @Description: TODO 获取账号关联
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getAccountContact(String url) {
		RequestParams params = new RequestParams(url);
		return params;
	}

	/**
	 * @Title: getAddressList
	 * @Description: TODO 获取收件人地址
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getAddressList(String url) {
		RequestParams params = new RequestParams(url);
		return params;
	}

	/**
	 * @Title: addPassword
	 * @Description: TODO 添加支付密码
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams addPassword(String url,String payPasswordNew) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter("payPasswordNew", payPasswordNew);
		return params;
	}

	/**
	 * @Title: addPassword
	 * @Description: TODO 校验支付密码
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams chexkPassword(String url,String depMoney,String passEncode,String type) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter("depMoney", depMoney);
		params.addBodyParameter("passEncoded", passEncode);
		params.addBodyParameter("type", type);
		return params;
	}

	/**
	 * @Title: addAddress
	 * @Description: TODO 添加地址
	 * @param @param contact
	 * @param @param mobile
	 * @param @param province
	 * @param @param city
	 * @param @param district
	 * @param @param address
	 * @param @param postcode
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams addAddress(String url, String contact,
			String mobile, String province, String city, String district,
			String address,int isDefault, String postcode) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.CONTACT, contact);
		params.addBodyParameter(Constant.MOBILE_D, mobile);
		params.addBodyParameter(Constant.PROVINCE, province);
		params.addBodyParameter(Constant.CITY, city);
		params.addBodyParameter(Constant.DISTRICT, district);
		params.addBodyParameter(Constant.ADDRESS, address);
		params.addBodyParameter(Constant.POSTCODE, postcode);
		params.addBodyParameter(Constant.isDefault, String.valueOf(isDefault));
		return params;
	}

	/**
	 * @Title: modifyAddress
	 * @Description: TODO 修改地址
	 * @param @param contact
	 * @param @param mobile
	 * @param @param province
	 * @param @param city
	 * @param @param district
	 * @param @param address
	 * @param @param postcode
	 * @param @param id
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams modifyAddress(String url, String contact,
			String mobile, String province, String city, String district,
			String address, String postcode, String id,int isDefault) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.MODIFY_ID, id);
		params.addBodyParameter(Constant.CONTACT, contact);
		params.addBodyParameter(Constant.MOBILE_D, mobile);
		params.addBodyParameter(Constant.PROVINCE, province);
		params.addBodyParameter(Constant.CITY, city);
		params.addBodyParameter(Constant.DISTRICT, district);
		params.addBodyParameter(Constant.ADDRESS, address);
		params.addBodyParameter(Constant.POSTCODE, postcode);
		params.addBodyParameter(Constant.POSTCODE, postcode);
		params.addBodyParameter(Constant.isDefault, String.valueOf(isDefault));
		return params;
	}

	/**
	 * @Title: removeAddress
	 * @Description: TODO 删除地址
	 * @param @param id
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams removeAddress(String url, String id) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.ADDRESS_ID,id);
		return params;
	}

	/**
	 * @Title: updateAccount
	 * @Description: TODO 完善账户信息
	 * @param @param id
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams updateAccount(String url, String tureName,String idCard,String cashAccount) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter("tureName",tureName);
		params.addBodyParameter("idCard",idCard);
		params.addBodyParameter("cashAccount",cashAccount);
		return params;
	}

	/**
	 * @Title: getCoin
	 * @Description: TODO 获取穿币详情
	 * @param @param id
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getCoin(String url) {
		RequestParams params = new RequestParams(url);;
		return params;
	}
	
	
	/**
	 * @Title: removeAddress
	 * @Description: TODO 删除地址
	 * @param @param id
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams showOrder(String url, String status, int pageindex, int pagesize) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.STATUS, status);
		params.addQueryStringParameter(Constant.PAGE_INDEX, String.valueOf(pageindex));
		params.addQueryStringParameter(Constant.PAGE_SIZE, String.valueOf(pagesize));
		return params;
	}
	
	
	
	
	
	
	/**
	 * @Title: setDefaultAddress
	 * @Description: TODO 设置默认收货地址
	 * @param @param id
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams setDefaultAddress(String url, String id) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.ADDRESS_ID, id);
		return params;
	}

	/**
	 * @Title: getCashDetail
	 * @Description: TODO 现金卷交易明细
	 * @param @param pageIndex
	 * @param @param pagesize
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getCashDetail(String url, String pageIndex,
			String pagesize) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.PAGEINDEX, pageIndex);
		params.addQueryStringParameter(Constant.PAGESIZE, pagesize);
		return params;
	}

	public static RequestParams getTradeDetail(String url, String pageIndex,
			String pagesize, String inExType, String tradeType) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.INEXTYPE, inExType);
		params.addQueryStringParameter(Constant.TRADETYPE, tradeType);
		params.addQueryStringParameter(Constant.PAGEINDEX, pageIndex);
		params.addQueryStringParameter(Constant.PAGESIZE, pagesize);
		return params;
	}

	/**
	 * @Title: getAward
	 * @Description: TODO 获取奖励信息
	 * @param @param code
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams getAward(String url, String code) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.CODE, code);
		return params;
	}

	public static RequestParams addFeedback(String url, String EquipmentType,
			String FbContent, String imgNameArr) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.SYSTYPE, "CC_ANDROID");
		params.addBodyParameter(Constant.EQUIPMENTTYPE, EquipmentType);
		params.addBodyParameter(Constant.FBCONTENT, FbContent);
		params.addBodyParameter(Constant.IMGNAMEARR, imgNameArr);
		return params;
	}

	public static RequestParams appStart(String url, AppStartBean appStartBean) {

		// LogHelper.e("-----请求的地址0000000:"+url);
		RequestParams params = new RequestParams(url);
		// LogHelper.d("----->appStartBean.getStartTime:"+appStartBean.getStartTime());
		params.addBodyParameter(Constant.APPSTART_STARTTIME, appStartBean
				.getStartTime().toString());
		params.addBodyParameter(Constant.APPSTART_ROSOLUTION,
				appStartBean.getResolution());
		params.addBodyParameter(Constant.APPSTART_LANGUAGE,
				appStartBean.getDeviceLanguage());
		params.addBodyParameter(Constant.APPSTART_NETWORKTYPE,
				appStartBean.getNetworkType());
		params.addBodyParameter(Constant.APPSTART_NETWORTPROVIDER,
				appStartBean.getNetworkProvider());
		// params.addBodyParameter(Constant.APPSTART_LOCALTIME,
		// appStartBean.getLocalTime());
		params.addBodyParameter(Constant.APPSTART_LOCALTIMEAREA, "8");
		
		params.addBodyParameter("RegSource", appStartBean.getRegSource());//渠道
		// params.addBodyParameter(Constant.APPSTART_SYSNO,
		// appStartBean.getSysNo());
		return params;
	}

	/**
	 * @Title: appAccess
	 * @Description: TODO APP访问记录
	 * @param @param string
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams appAccess(String url, String string) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.APPSTART_JSONSTR, string);
		return params;
	}

	/**
	 * @param string
	 *            异常记录
	 * @return
	 */
	public static RequestParams appEx(String url, String string) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.APPSTART_JSONSTR, string);
		return params;
	}

	/**
	 * @Title: modifyCcinfo
	 * @Description: TODO 修改穿衣数据
	 * @param @param infoBean
	 * @param @return 设定文件
	 * @return RequestParams 返回类型
	 * @throws
	 */
	public static RequestParams modifyCcinfo(String url, PersonInfoBean infoBean) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.HEIGHT,
				String.valueOf(infoBean.getHeight()));
		params.addBodyParameter(Constant.WEIGHT,
				String.valueOf(infoBean.getWeight()));
		params.addBodyParameter(Constant.AGE,
				String.valueOf(infoBean.getAge()));
		params.addBodyParameter(Constant.PHYSIQUE,
				String.valueOf(infoBean.getPhysique()));
		params.addBodyParameter(Constant.HIP,
				String.valueOf(infoBean.getHip()));
		params.addBodyParameter(Constant.WAIST,
				String.valueOf(infoBean.getWaist()));
		params.addBodyParameter(Constant.CTTYPENAME,
				String.valueOf(infoBean.getCtTypeName()));
		params.addBodyParameter(Constant.BUST,
				String.valueOf(infoBean.getBust()));
		params.addBodyParameter(Constant.PANTS,
				String.valueOf(infoBean.getPants()));
		params.addBodyParameter(Constant.SLEEVE,
				String.valueOf(infoBean.getSleeve()));
		return params;
	}

	/**
	 *
	 * @param url
	 * @param bean
     * @return
     */
	public static RequestParams modifyCcinfo2(String url, BodyDataBean bean) {
		Log.e("modifyCcinfo2: ",bean.getData().toString() );
		BodyDataBean.DataBean data = bean.getData();
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.GENDER,
				String.valueOf(data.getGender()));
		params.addBodyParameter(Constant.AGE,
				String.valueOf(data.getAge()));
		params.addBodyParameter(Constant.WEIGHT,
				String.valueOf((int) data.getWeight()));
		params.addBodyParameter(Constant.HEIGHT,
				String.valueOf((int) data.getHeight()));
		params.addBodyParameter(Constant.CLOTHSIZE,
				String.valueOf(data.getClothSize()));
		params.addBodyParameter(Constant.PHYSIQUE,
				String.valueOf(data.getPhysique()));
		params.addBodyParameter(Constant.SHOULDERBREADTH,
				String.valueOf((int) data.getShoulderBreadth()));
		params.addBodyParameter(Constant.BUST,
				String.valueOf((int) data.getBust()));
		params.addBodyParameter(Constant.WAIST,
				String.valueOf((int) data.getWaist()));
		params.addBodyParameter(Constant.HIP,
				String.valueOf((int) data.getHip()));
		params.addBodyParameter(Constant.SLEEVE,
				String.valueOf((int) data.getSleeve()));
		params.addBodyParameter(Constant.THIGHCIRC,
				String.valueOf((int) data.getThighCirc()));
		params.addBodyParameter(Constant.CALFCIRC,
				String.valueOf((int) data.getCalfCirc()));
		params.addBodyParameter(Constant.KNEECIRC,
				String.valueOf((int) data.getKneeCirc()));
		params.addBodyParameter(Constant.ARMCIRC,
				String.valueOf((int) data.getArmCirc()));
		params.addBodyParameter(Constant.PANTS,
				String.valueOf((int) data.getPants()));
		params.addBodyParameter(Constant.TEBIESHUOMING,"1");
		params.addBodyParameter(Constant.GEXINGNAME,"1");
		params.addBodyParameter(Constant.CHUANYIXIGUAN,"1");
		params.addBodyParameter(Constant.ISRADIO,"1");
		return params;
	}

	/**
	 *支付回调
	 * @return
	 */
	public static RequestParams orderBody(String json) {
		RequestParams params = new RequestParams(UrlManager.SaveBodyData());
		params.addBodyParameter("BodyData",json);
		return params;
	}

		/*** im */

	public static RequestParams getImUserInfo(String url, String id) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.IMIDS, id);
		return params;
	}

	/**
	 * 发送长文本
	 * 
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams sendLongText(String url, long sendTime,
			long sendUserId, long receiverId, String receiverType,
			String content) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.ID, String.valueOf(sendTime));
		params.addBodyParameter(Constant.RECVID, String.valueOf(receiverId));
		params.addBodyParameter(Constant.SENDUSERID, String.valueOf(sendUserId));
		params.addBodyParameter(Constant.RECVTYPE, receiverType);
		params.addBodyParameter(Constant.MSG, content);
		return params;
	}

	/**
	 * 拉取长文本消息
	 * 
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams getLongText(String url, long sendTime,
			long senUserId) {
		RequestParams params = new RequestParams(url);
		params.addQueryStringParameter(Constant.RID, String.valueOf(sendTime));
		params.addQueryStringParameter(Constant.RSENDUSERID,
				String.valueOf(senUserId));
		return params;
	}

	/**
	 * 拉取众筹群组列表
	 * 
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams getGroupList(String url) {
		RequestParams params = new RequestParams(url);
		return params;
	}




	/***************************************高伟豪新添加***************************************************************************/


	/**
	 * //    param.tag = self.tag;
	 //    param.detail = self.text;
	 //    param.imgs = self.imgs;  sendPay
	 *
	 * 发布图片 高伟豪
	 *
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams fabu(String url, String tag, String text, String imgs) {
		System.out.println("#####发布图片"+tag+"   "+ text+" "+imgs);
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.TAG, tag);
		params.addBodyParameter(Constant.TEXT, text);
		params.addBodyParameter(Constant.IMGS, imgs);
		return params;
	}





	/**
	 * //   param.tag = self.tag;
	 //    param.detail = self.text;
	 //    param.imgs = self.imgs;  sendPay
	 *
	 * 发布图片 高伟豪
	 *
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams sendPay(String url, String addressId, String proId, String ordInfo) {
		System.out.println("#####添加订单"+addressId+"   "+ proId+" "+ordInfo);
		RequestParams params = new RequestParams(url);
		params.addBodyParameter(Constant.PROID, proId);
		params.addBodyParameter(Constant.ADDRESS_ID, addressId);
		params.addBodyParameter(Constant.ORDINFO, ordInfo);
		return params;
	}



	/**
	 * 梁文硕
	 * 添加加到我的收藏
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams AddFavorites(String url) {
		RequestParams params = new RequestParams(url);
		return params;
	}

	/**
	 * 梁文硕
	 * 添加拉取客服
	 * @param json
	 *  id
	 * @return
	 */
	public static RequestParams getCustomer(String json) {
		RequestParams params = new RequestParams("http://imserver.myappcc.com/api/GetCustomer");
		params.setAsJsonContent(true);
		try {
			params.setBodyContent(String.valueOf(json.getBytes("UTF-8")));
			Log.e("getCustomer: ", String.valueOf(json.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException nE) {
			Log.e("getCustomer:",nE.getLocalizedMessage() );
		}
		return params;
	}
	/**
	 * 梁文硕
	 * 删除我的收藏
	 * @param url
	 *  id
	 * @return
	 */
	public static RequestParams delFavorites(String url,String favid) {
		RequestParams params = new RequestParams(url);
		params.addBodyParameter("favid", favid);
		return params;
	}


	/**
	 * 梁文硕
	 * 删除订单
	 *  id
	 * @return
	 */
	public static RequestParams deleteord(String orderId) {
		RequestParams params = new RequestParams(UrlManager.deleteord());
		params.addBodyParameter("orderId", orderId);
		return params;
	}


	/**
	 * 梁文硕
	 * 取消订单
	 *  id
	 * @return
	 */
	public static RequestParams cancelOrder(String orderId) {
		RequestParams params = new RequestParams(UrlManager.cancelOrder());
		params.addBodyParameter("orderId", orderId);
		return params;
	}

	/**
	 * 梁文硕
	 * 确认收获
	 *  id
	 * @return
	 */
	public static RequestParams confirmReceive(String orderId) {
		RequestParams params = new RequestParams(UrlManager.confirmReceive());
		params.addBodyParameter("orderId", orderId);
		return params;
	}

	/**
	 * 梁文硕
	 * 确认收获
	 *  id
	 * @return
	 */
	public static RequestParams refund(String orderId) {
		RequestParams params = new RequestParams(UrlManager.refund());
		params.addBodyParameter("orderId", orderId);
		return params;
	}




	/**
	 * 梁文硕
	 * 更改地区
	 *  id
	 * @return
	 */
	public static RequestParams changeArea(String province,String city,String district) {
		RequestParams params = new RequestParams(UrlManager.changeArea());
		params.addBodyParameter("province", province);
		params.addBodyParameter("city", city);
		params.addBodyParameter("district", district);
		return params;
	}
	/**
	 * Author：rommel
	 * @param:
	 * @return:
	 * Desc:
	 */
	public static RequestParams deletePush(String userid, String umengids) {
		RequestParams params = new RequestParams(UrlManager.deletePush());
		params.addBodyParameter("userid", userid);
		params.addBodyParameter("umengids", umengids);
		return params;
	}

}
