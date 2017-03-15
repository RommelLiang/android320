package com.tiemuyu.chuanchuan.activity.util;

/**
 * Created by CC2.0 on 2016/9/5.
 */
public class ISDKBindings {

    public enum PayStatus
    {
        /// <summary>
        /// 支付成功
        /// </summary>
        Success,
        /// <summary>
        /// 支付处理中
        /// </summary>
        Handling,
        /// <summary>
        /// 支付失败
        /// </summary>
        Fail,
        /// <summary>
        /// 用户取消
        /// </summary>
        UserCancel,
        /// <summary>
        /// 网络异常
        /// </summary>
        NetworkError,
        /// <summary>
        /// 不支持
        /// </summary>
        NotSupport,
        /// <summary>
        /// 没创建
        /// </summary>
        NoBuild,
        /// <summary>
        /// 没有安装
        /// </summary>
        NotInstalled,
    }
    /// <summary>
    /// 支付类型
    /// </summary>
    public class PayType
    {
        public long Alipay=2 ;
        public long WXPay=1 ;
    }


    /// <summary>
    /// 支付结果
    /// </summary>
    public class PayResult
    {
        public String Message ;
        public PayStatus Status ;
        public String SignInfo ;
    }
    /// <summary>
    /// 支付状态
    /// </summary>
    public static class PayStatusParser
    {
        public static PayStatus Alipay(int code)
        {
            switch (code)
            {
                case 9000:
                    return PayStatus.Success;
                case 8000:
                    return PayStatus.Handling;
                case 4000:
                    return PayStatus.Fail;
                case 6001:
                    return PayStatus.UserCancel;
                case 6002:
                    return PayStatus.NetworkError;
            }
            return PayStatus.Fail;
        }
        public static PayStatus WXPay(int code)
        {
            switch (code)
            {
                case 0:
                    return PayStatus.Success;
                case -100:
                    return PayStatus.NotInstalled;
                case -1:
                    return PayStatus.Fail;
                case -2:
                    return PayStatus.UserCancel;
            }
            return PayStatus.Fail;
        }
    }







}
