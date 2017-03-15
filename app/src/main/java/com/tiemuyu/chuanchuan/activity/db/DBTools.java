package com.tiemuyu.chuanchuan.activity.db;

import java.util.List;

import org.xutils.DbManager;
import org.xutils.x;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import android.content.Context;

import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.bean.AppAccessBean;
import com.tiemuyu.chuanchuan.activity.bean.CacheBean;
import com.tiemuyu.chuanchuan.activity.bean.ExBean;
import com.tiemuyu.chuanchuan.activity.bean.NewProductBean;
import com.tiemuyu.chuanchuan.activity.bean.UrlsBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.util.LogHelper;

//import com.tiemuyu.chuanchuan.base.MyApplication;
//import com.tiemuyu.chuanchuan.bean.AppAccessBean;
//import com.tiemuyu.chuanchuan.bean.CacheBean;
//import com.tiemuyu.chuanchuan.bean.ExBean;
//import com.tiemuyu.chuanchuan.bean.NewProductBean;
//import com.tiemuyu.chuanchuan.bean.UrlsBean;
//import com.tiemuyu.chuanchuan.bean.User;
//import com.tiemuyu.chuanchuan.constant.Constant;
//import com.tiemuyu.chuanchuan.utils.LogHelper;

public class DBTools {

	/**
	 * 登录
	 * */
	public static void loginDb(Context context, User loginBean) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			System.out.println("进入loginDb ");
			List<User>ls=db.findAll(User.class);
			if(ls!=null){
				db.delete(User.class);
				
			}
			db.save(loginBean);
			System.out.println("login信息有：  "+ loginBean.toString());
			
			
			LogHelper.d("------保存登录信息："+loginBean.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static User getUser(){
		DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
		User lb=new User();
		try {
			List<User>ls = db.findAll(User.class);
			
			if(ls!=null){
				//LogHelper.e("-----数据库登录信息size:"+ls.size());
				if(ls.size()>0){
					lb=ls.get(0);
					//LogHelper.e("-----数据库登录信息:"+lb.toString());
				}
					
				else
					lb=null;
			}else{
				lb=null;
				//LogHelper.e("-----数据库登录信息null:");
			}
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LogHelper.d("-----数据库中的登录信息user:"+lb.toString());
		return lb;
	}

	/** 移除登录数据 */
	public static void removeDb(Context context) {
		try {
			// DbUtils db = DbUtils.create(context);
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			if (db != null) {
				db.delete(User.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// LogHelper.d("-----移除登录信息移除----");
			e.printStackTrace();
		}
	}

	public static List<NewProductBean> getNewProductBeanDbData(Context context,
															   List<NewProductBean> newProductBeans) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			List<NewProductBean> ns = db.findAll(NewProductBean.class);
			if (ns != null)
				newProductBeans = ns;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return newProductBeans;
	}


	public static void getAppAccessList(Context context) {
		try {
			// DbUtils db = DbUtils.create(context);
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			List<AppAccessBean> ls = db.findAll(AppAccessBean.class);
			if (ls != null) {
//				if (ls.size() > 0)
//					Constant.aabs = ls;

			} 

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void removeAllAppAccessList(Context context) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			db.delete(AppAccessBean.class);
			// System.out.println("---清除数据库访问记录");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void saveAppAccessList(Context context, List<AppAccessBean> ls) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			if (ls.size() > 0)
				db.save(ls);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void getAppExAccessList(Context context) {
		try {
			// DbUtils db = DbUtils.create(context);
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			List<ExBean> ls = db.findAll(ExBean.class);
			if (ls != null) {
//				if (ls.size() > 0)
//					MyApplication.exs = ls;

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//保存异常记录
	public static void saveAppEXAccessList(Context context, List<ExBean> ls) {
		try {
			// LogHelper.d("----退出，保存异常记录");
			// LogHelper.d("----退出，保存异常记录1111:"+ls.size());
			// DbUtils db = DbUtils.create(context);
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			if (ls.size() > 0) {
				db.save(ls);
//				MyApplication.exs.clear();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void removeAllExAccessList(Context context) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			db.delete(ExBean.class);
			// LogHelper.e("---清除数据库中的异常记录");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void saveAppUrls(Context context, UrlsBean urlsBean) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			if (db != null) {

				db.delete(UrlsBean.class);
				// LogHelper.d("-----保存地址:"+urlsBean.toString());
				db.save(urlsBean);
				// LogHelper.e("-----保存地址");
			}

		} catch (Exception e) {
			// TODO: handle exception
			LogHelper.e("-----保存地址出现异常");
			e.printStackTrace();
		}
	}

	public static UrlsBean getAppUrls(Context context) {
		UrlsBean urlsBean = null;
		try {

			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			UrlsBean ub = db.findFirst(UrlsBean.class);
			if (ub != null) {
				urlsBean = ub;
				System.out.println("---高伟豪数据库有地址数据-->");
				System.out.println("---ccccccccccccccc新建啊啊1啊啊啊啊啊-->"+urlsBean.toString());

				 //LogHelper.d("---数据库有地址数据");
			}

			else {
				urlsBean = new UrlsBean();
				
				System.out.println("---高伟豪ccccccccccccccc新建啊啊1啊啊啊啊啊-->"+urlsBean.toString());
				//LogHelper.d("---数据库没有地址数据");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return urlsBean;
	}

	public static CacheBean getWebCache(Context context) {
		CacheBean urlsBean = null;
		try {

			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			CacheBean ub = db.findFirst(CacheBean.class);
			if (ub != null) {
				urlsBean = ub;
				// LogHelper.e("-----数据库有cashe数据");
			}

			else {
				urlsBean = new CacheBean();
				// LogHelper.e("-----数据库没有cashe数据");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return urlsBean;
	}

	public static void saveOrUpdataWebCache(Context context, CacheBean cacheBean) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			LogHelper.d("----保存web Cache");
			db.saveOrUpdate(cacheBean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void removeWebCache(Context context, CacheBean cacheBean) {
		try {
			DbManager db = x.getDb(DbConfig.getDbConfig().getDaoConfig());
			// LogHelper.d("----移除web Cache");
			db.delete(cacheBean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
