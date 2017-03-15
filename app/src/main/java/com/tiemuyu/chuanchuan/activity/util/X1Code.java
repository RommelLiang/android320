package com.tiemuyu.chuanchuan.activity.util;

public class X1Code {

	/**
	  * x1Encode(对密码进行x1加密)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: x1Encode
	  * @Description: TODO
	  * @param @param password
	  * @param @param userName
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	public static String x1Encode(String password, String userName)
    {
		MD5Helper md5Helper=new MD5Helper();
		
        String newPassword = "tmy@" + password + "@ymt";  //  tmy@admin@ymt
        String pass = md5Helper.getMD5ofStr(newPassword); //  ABCDEFGHIJKLMNOPQRSTUVWXYZ123456
        System.out.println("md5的数据"+pass);
        
       // md5的数据E2AC1E14A6B65CF0840E806D5AF27641
        
        
        // 拆分为两段
        char[] ch1 = new char[16];                        //  ACEGI....5           BDF......6
        char[] ch2 = new char[16];
        for (int i = 0; i < 16; i++)
        {
        	 ch1[i]=(char) pass.charAt(i * 2);
             ch2[i] = (char) pass.charAt(i * 2+1);
//            ch1[i] = pass[i * 2];
//            ch2[i] = pass[i * 2 + 1];
        }
        System.out.println("分拆后的数据1： "+new String(ch1));
        
        
//        分拆后的数据1： EA11AB5F80865F74
//        分拆后的数据2： 2CE466C04E0DA261
        
        
        System.out.println("分拆后的数据2： "+new String(ch2));

        String pass1 = new String(ch1) + userName;     
        String pass2 = new String(ch2) + userName;

        String passout = md5Helper.getMD5ofStr(pass1) + md5Helper.getMD5ofStr(pass2);
        System.out.println("分拆后的数据md5Helper.getMD5ofStr(pass1)： "+md5Helper.getMD5ofStr(pass1));
        
        
//
//分拆后的数据md5Helper.getMD5ofStr(pass1)： 43CBA86A688A3EF7D8720D1AF493D156
//分拆后的数据md5Helper.getMD5ofStr(pass2)： A4A1A89C816F21FDF2503AD22939172D
        
        System.out.println("分拆后的数据md5Helper.getMD5ofStr(pass2)： "+md5Helper.getMD5ofStr(pass2));

        
        return passout;
    }
}
