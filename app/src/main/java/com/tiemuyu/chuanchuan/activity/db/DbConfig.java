package com.tiemuyu.chuanchuan.activity.db;

import com.tiemuyu.chuanchuan.activity.util.LogHelper;

import java.io.File;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.xutils.DbManager;

//import com.tiemuyu.chuanchuan.constant.Constant;
//import com.tiemuyu.chuanchuan.utils.LogHelper;
//import com.tmy.cc.im.UdpClientSocket;

public class DbConfig {
	private DbManager.DaoConfig daoConfig;
	private static volatile DbConfig dbConfig = null;

	private DbConfig() {

		LogHelper.e("----初始化数据库");
		daoConfig = new DbManager.DaoConfig()
				.setDbName("cc_db1")
				//.setDbDir(new File(Constant.BASE_DBPATH))
				.setDbVersion(3)
				.setAllowTransaction(true)
				.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
					@Override
					public void onUpgrade(DbManager db, int oldVersion,
							int newVersion) {
						// TODO: ...
						// db.addColumn(...);
						// db.dropTable(...);
						// ...
					}
				});

	}

	public static DbConfig getDbConfig() {
		if (dbConfig == null) {
			synchronized (DbConfig.class) {
				if (dbConfig == null) {
					dbConfig = new DbConfig();
				}
			}

		}
		return dbConfig;
	}

	public DbManager.DaoConfig getDaoConfig() {
		return daoConfig;
	}

	public void setDaoConfig(DbManager.DaoConfig daoConfig) {
		this.daoConfig = daoConfig;
	}

	
	
}
