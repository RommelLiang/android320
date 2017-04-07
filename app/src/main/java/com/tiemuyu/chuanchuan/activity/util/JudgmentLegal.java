package com.tiemuyu.chuanchuan.activity.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class JudgmentLegal {
    /**
     * yyyy-MM-dd HH:mm:ss
     */

    public static final SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);

    /**
     * yyyyMMddHHmmss
     */
    public static final SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

    /**
     * 判断是否纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null || "".equals(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 会员帐号 5-15 不能是纯数字 不能包含特殊符号除"_"
     */
    public static boolean memberNo(String str) {
        boolean temp = false;
        Pattern parrert = Pattern.compile("\\w{5,15}");
        Matcher matcher = parrert.matcher(str);
        if (matcher.matches()) {
            return temp;
        } else {
            return temp = true;
        }
    }

    /**
     * 判断字符串是否包含字符
     *
     * @param StrName
     *            路径
     * @return
     */
    public static boolean isChinseName(String StrName) {
        char[] charArray = StrName.toCharArray();
        for (int i = 0; i < StrName.length(); i++) {
            if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 手机号码
     *
     * @param phone
     * @return
     */
    public static boolean validatePhoneNumber(String phone) {
        if (phone == null)
            return false;
        //Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,5-9]))\\d{8}$");
        //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9])|(170))\\d{8}$");
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 邮件
     *
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        if (email == null)
            return false;
        Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhoneOrEmail(String acc){
        if(acc==null)
            return false;
        if(validatePhoneNumber(acc))
            return true;
        if(validateEmail(acc))
            return true;
        return false;
    }

    /**
     * 获取文件名字
     *
     * @param fname
     * @return
     */
    public static String getDatedFName(String fname) {
        StringBuilder result = new StringBuilder();
        String dateSuffix = "_" + dateFormat3.format(new Date());
        int idx = fname.lastIndexOf('.');
        if (idx != -1) {
            result.append(fname.substring(0, idx));
            result.append(dateSuffix);
            result.append(fname.substring(idx));
        } else {
            result.append(fname);
            result.append(dateSuffix);
        }
        return result.toString();
    }

    /**
     * 格式化金额 8866.34 => 8,866.34
     *
     * @param money
     * @return
     */
    public static String formatMoney(String money) {
        int index = money.indexOf(".");
        String yuan = money.substring(0, index);// 4353342
        String fen = money.substring(index);
        if (yuan.length() <= 3) {
            return money;
        }
        StringBuilder result = new StringBuilder(money);
        result.reverse();
        index = result.indexOf(".");
        yuan = result.substring(index + 1);
        result.delete(0, result.length());
        int len = yuan.length();
        for (int i = 1; i <= len; i++) {
            result.append(yuan.charAt(i - 1));
            if (i % 3 == 0 && i != 1 && i != len) {
                result.append(",");
            }
        }
        return result.reverse().append(fen).toString();
    }

    // /**
    // * 判断是否包含汉语名字
    // *
    // * @param StrName
    // * 路径
    // * @return
    // */
    // private boolean isChinseName(String StrName) {
    // int index = StrName.lastIndexOf("/");
    // String name = StrName.substring(index + 1, StrName.length());
    // char[] charArray = name.toCharArray();
    // for (int i = 0; i < charArray.length; i++) {
    // if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
    // return true;
    // }
    // }
    // return false;
    //
    // }

    //
    // /**
    // * 会员是否合法
    // */
    // public static boolean isMemberNo(String str) {
    // boolean temp = true;
    // // 纯数字
    // if (!isNumeric(str)) {
    // temp = false;
    // } else if (isPassword(str)) {
    // // 长度
    // temp = isPassword(str);
    // } else if (str.contains("_")) {
    // // 特殊字符
    // temp = true;
    //
    // }
    // return temp;
    //
    // }

    /**
     * 判断是否钱币字符合法
     *
     * @param str
     * @return
     */
    public static boolean isMoneyLegal(String str) {
        Pattern pattern1 = Pattern.compile("\\d{1,3}(,{1}\\d{3})*(\\.\\d+)?"); // 匹配11,222.33
        Pattern pattern2 = Pattern.compile("\\d*(\\.\\d+)?"); // 匹配11222.33
        Pattern pattern3 = Pattern.compile("[0]+\\d+(\\.\\d+)?");
        if (pattern3.matcher(str).matches()) {
            return false;
        }
        return pattern1.matcher(str).matches() || pattern2.matcher(str).matches();
    }

    /**
     * 返回是的字符串的格式是每四个一组中间加个空格
     *
     * @param str
     * @return
     */
    public static String toFourEachRow(String str) {
        String strPattern = "00000000000000000000";
        StringBuffer s = new StringBuffer(str);
        if (str.length() < 20) {
            s.append(strPattern, 0, 20 - str.length());
        }
        int temp = 20;
        while (temp > 4) {
            temp -= 4;
            s.insert(temp, " ");
        }
        return s.toString().substring(0, str.length() + (20 / 4 - 1));
    }

    /**
     * 返回是的字符串的格式是每四个一组中间加个空格
     *
     * @param str
     * @return
     */
    public static String formatBankNumber(String str) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(str);
        for (int i = 0; i < buffer.length(); i++) {
            if (i % 5 == 0) {
                buffer.insert(i, " ");
            }
        }
        if (buffer.length() < 2) {
            return buffer.substring(0);
        } else {
            return buffer.substring(1);
        }
    }

    /**
     * 返回是的字符串的格式是格式化的银行卡账号，每四个一组，中间是星号
     *
     * @param str
     * @return
     */
    public static String toBankNumberStar(String str) {
        String strPattern = " **** **** **** ";
        String s = toFourEachRow(str);
        String res;
        res = s.substring(0, 4) + strPattern + s.substring(s.length() - 4, s.length());
        return res;
    }

    /**
     * 订单管理 订单号中间使用****
     */
    public static String toOrderOrdIdStar(String str) {
        String strPattern = "**";
        String res;
        if (str.length() > 16) {
            int index = str.length() / 2;
            res = str.substring(0, index - 2) + strPattern + str.substring(index + 2, str.length());
        } else {
            res = str;
        }
        return res;

    }

    /**
     * 返回是的字符串的格式是格式化的手机号码，前后四个一组，中间是星号
     *
     * @param str
     * @return
     */
    public static String toPhoneNumberStar(String str) {
        String res = str.substring(0, 3) + "****" + str.substring(7, 11);
        return res;
    }

    /**
     * 返回是是否只有一个小数点
     *
     * @param str
     * @return
     */
    public static boolean isOnlyOneDot(String str) {
        boolean res;
        int temp = str.indexOf(".");
        if (temp != -1 && str.lastIndexOf(".") != temp) {
            res = false;
        } else {
            res = true;
        }
        return res;
    }

    /**
     * 判定QQ号是否有效
     *
     * @param str
     * @return
     */
    public static boolean isQQNumberLegal(String str) {
        boolean res;

        // if (!isNumeric(str)) {
        // res = false;
        // } else {
        res = true;
        if (str.length() < 5) {
            res = false;
        }
        // }
        return res;
    }

    /**
     * 判断信用卡是否有效
     *
     * @param cardNumber
     * @return
     */
    public static boolean isCreditCardLegal(String cardNumber) {
        String digitsOnly = getDigitsOnly(cardNumber);
        int sum = 0;
        int digit = 0;
        int addend = 0;
        boolean timesTwo = false;

        for (int i = digitsOnly.length() - 1; i >= 0; i--) {
            digit = Integer.parseInt(digitsOnly.substring(i, i + 1));
            if (timesTwo) {
                addend = digit * 2;
                if (addend > 9) {
                    addend -= 9;
                }
            } else {
                addend = digit;
            }
            sum += addend;
            timesTwo = !timesTwo;
        }

        int modulus = sum % 10;
        return modulus == 0;
    }

    private static String getDigitsOnly(String s) {
        StringBuffer digitsOnly = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (Character.isDigit(c)) {
                digitsOnly.append(c);
            }
        }
        return digitsOnly.toString();
    }

    /**
     * 判定银行卡号是否有效
     *
     * @param cardId
     * @return
     */
    public static boolean isBankCardNumberLegal(String cardId) {
        if (cardId.length() < 16) {
            return false;
        }
        if (!isNumeric(cardId)) {
            return false;
        }
        // if (cardId.length() == 16 || cardId.length() == 19) {
        // LogHelper.i("cardId:" + cardId);
        // char bit = getBankCardCheckCode(cardId.substring(0,
        // cardId.length() - 1));
        // LogHelper.i("bit:" + bit);
        // if (bit == 'N') {
        // return false;
        // }
        // return cardId.charAt(cardId.length() - 1) == bit;
        // }
        return true;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    // public static boolean isBankCardNumberLegal(String str) {
    // boolean res;
    // if (str.length() > 25 || str.length() < 1 || !isNumeric(str)) {
    // res = false;
    // } else {
    // res = true;
    // }
    // Pattern pattern = Pattern.compile("(\\d{4}\\ ){4}\\d{4}");
    // if (pattern.matcher(str).matches()) {
    // res = true;
    // }
    // return res;
    // }

    /**
     * 判定手机号码是否有效
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumberLegal(String str) {
        boolean res;
        String MOBILE = "^1\\d{10}$";
        Pattern pt = Pattern.compile(MOBILE);
        if (pt.matcher(str).matches()) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * 所有输入框均不能为空（特别提示除外）
     *
     * @param str
     * @return true是空值，false是非空值
     */
    public static boolean isNull(String str) {
        boolean res;
        if (str == null) {
            res = true;
        } else if ("".equals(str)) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * 返回字符串是否为全0
     *
     * @param str
     * @return
     */
    public static boolean isAllZero(String str) {
        boolean res;
        int i;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                break;
            }
        }
        if (i < str.length()) {
            res = false;
        } else {
            res = true;
        }
        return res;
    }

    /**
     * 判断金额是否为空
     *
     * @param str
     * @return
     */
    public static boolean isMoneyNull(String str) {
        return isAllZero(str) || isNull(str);
    }

    /**
     * 格式化金额（返回元）
     *
     * @param formatStr
     *            格式化参数（0.00）
     * @param money
     * @param weight
     *            比重（分：100 (除以100)）
     * @return
     */
    public static String formatMoney(String formatStr, String money, double weight) {
        if (!JudgmentLegal.isNumeric(money))
            return "0.00";
        DecimalFormat format = new DecimalFormat(formatStr);
        return format.format(Double.parseDouble(money) / weight);
    }

    /**
     * 格式化金额（￥）
     *
     */
    public static CharSequence formatMoneyStyle(String formatStr, String money, double weight) {
        String orderSum;
        if (!JudgmentLegal.isNumeric(money))
            return "0.00";
        DecimalFormat format = new DecimalFormat(formatStr);
        orderSum = String.format("￥" + format.format(Double.parseDouble(money) / weight));

        SpannableString span = new SpannableString(orderSum);
        int sindex = orderSum.indexOf("￥");
        span.setSpan(new ForegroundColorSpan(Color.RED), sindex, orderSum.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // 红色高亮
        int eindex = orderSum.lastIndexOf(".");
        span.setSpan(new RelativeSizeSpan(1.5f), sindex + 1, eindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    /**
     * 可用金额：0.00（0.00为高红）
     *
     * @param str
     * @param price
     * @return
     */
    public static SpannableString formatMoneyState(Context context, int str, String price) {
        String banle = String.format(context.getString(str) + "￥");
        String money = formatMoney("0.00", price, 100);
        SpannableString span = new SpannableString(banle + money);
        int sindex = banle.indexOf("￥");
        span.setSpan(new ForegroundColorSpan(Color.RED), sindex, banle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // 红色高亮
        return span;
    }

    /**
     * 格式化名字z******5
     *
     * @param context
     * @param str
     * @return
     */
    public static String formatName(Context context, String str) {
        if (str == null)
            return null;
        String strPattern = "**";
        String res;
        res = str.substring(0, 1) + strPattern + str.substring(str.length() - 1, str.length());
        return res;
    }

    /**
     * 会员账号长度限制6-15位，不能为纯数字
     *
     * @param str
     * @return 0是没问题，1是不符合账号长度限制5-15位，2是不符合不能为纯数字 3 特殊字符错误
     */
    public static int isMemberAccount(String str) {
        int res = 0;
        Pattern parrert = Pattern.compile("\\w{6,15}");
        Matcher matcher = parrert.matcher(str);
        if (str.length() < 6 || str.length() > 15) {
            res = 1;
        } else if (isNumeric(str)) {
            res = 2;
        } else if (matcher.matches()) {
            res = 0;
        } else {
            res = 3;
        }
        return res;
    }

    /**
     * 3. 密码长度限制6-16位
     *
     * @param str
     * @return true是密码合法，false是密码不合法
     */
    public static boolean isPassword(String str) {
        boolean res;

        if (str.length() < 6 || str.length() > 16) {
            res = false;
        } else {
            res = true;
        }
        return res;
    }
    /**
     * 密码长度限制6-16位,含字母和数字
     *
     * @param str
     * @return true是密码合法，false是密码不合法
     */

    public static boolean isPass(String str){
        //String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
//		Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
//		Matcher m = p.matcher(str);
//		return m.matches();
        return true;
    }


    /**
     * 4. 手机号码长度限制11位，纯数字
     *
     * @param str
     * @return 0是没问题，1是不符合账号长度限制11位，2是不符合纯数字
     */
    public static int isPhoneNumber(String str) {
        int res = 0;

        if (str.length() != 11) {
            res = 1;
        } else if (isNumeric(str)) {
            res = 2;
        }
        return res;
    }

    /**
     * 5. 验证码长度限制6位，纯数字
     *
     * @param str
     * @return 0是没问题，1是不符合验证码长度限制6位，2是不符合纯数字
     */
    public static int isCaptcha(String str) {
        int res = 0;

        if (str.length() != 6) {
            res = 1;
        } else if (!isNumeric(str)) {
            res = 2;
        }
        return res;
    }

    public static float getFee(float money) {
        float fee = money * 8 / 1000;
        if (fee < 1.5) {
            fee = 1.5f;
        }
        return fee;
    }

    /**
     * 6. 银行卡长度限制最大20未，所有银行卡输入需格式化（6225 3223 2221 0000 0000）
     *
     * @param str
     * @return null是长度超过20，最后返回格式化输入的银行卡号
     */
    public static String showBankCardnumberInput(String str) {
        String res;

        Pattern pattern = Pattern.compile("(\\d{4}\\ ){4}\\d{4}");
        if (pattern.matcher(str).matches()) {
            return str;
        }
        if (str != null && str.length() > 20) {
            res = null;
        } else {
            res = toFourEachRow(str);
        }
        return res;
    }

    /**
     * 7.1 银行卡输出格式化（6225 **** **** ***** 0000）
     *
     * @param str
     * @return null是长度超过20，最后返回格式化输出的银行卡号
     */
    public static String showBankCardNumberOutput(String str) {
        String res;

        if (str != null && str.length() > 20) {
            res = null;
        } else {
            res = toBankNumberStar(str);
        }
        return res;
    }

    /**
     * 我的订单 订单号输出格式 （62256225**62250000）
     *
     * @param str
     * @returnnull是长度超过18
     */

    public static String showOrderOrdidNumberOutput(String str) {
        String res;

        if (str != null && str.length() > 18) {
            res = null;
        } else {
            res = toBankNumberStar(str);
        }
        return res;
    }

    // 8. 金额输出格式化，以元为单位，两位小数（2.10、2,000.00）
    // return null是str为空，最后返回格式化输出的手机号码
    // public static String showMoneyOutput(String str) {
    //
    // StringBuffer s = new StringBuffer(str);
    // int temp = str.indexOf(".");
    //
    // if (temp == -1) {
    // s.append(".00");
    // temp = str.length();
    // } else if (str.length() - temp == 1) {
    // s.append("00");
    // } else if (str.length() - temp - 1 == 1) {
    // s.append("0");
    // } else if (str.length() - temp > 3) {
    // s.delete(temp + 3, str.length());
    // }
    // Pattern pattern = Pattern.compile("\\d{1,3}(,{1}\\d{3})*(\\.\\d+)?");
    // if (pattern.matcher(s.toString()).matches()) {
    // return s.toString();
    // }
    // while (temp > 3) {
    // temp -= 3;
    // s.insert(temp, ",");
    // }
    // return s.toString();
    // }
    /**
     * 金额输出格式化，以元为单位，纯整数，三位加逗号。
     *
     * @param str
     * @return
     */
    public static String showMoneyOutput(String str) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                break;
            }
        }
        if (i < str.length()) {
            str = str.substring(i);
        }
        int temp = str.length();
        StringBuffer s = new StringBuffer(str);
        while (temp > 3) {
            temp -= 3;
            s.insert(temp, ",");
        }
        return s.toString();
    }

    /**
     * 元 ---分，分开格式
     *
     * @return
     */
    public static String[] showYuanAndFen(String money, String count) {
        if (money == null) {
            return null;
        }
        double allMoney;
        String yuan = null;
        String fen = null;
        String[] str;
        allMoney = Double.parseDouble(money);

        if (count != null && !count.equals("0")) {
            allMoney = allMoney * Integer.parseInt(count);
        }
        String tempMoney = String.valueOf((allMoney / 100) != 0 ? (allMoney / 100) : "0");
        yuan = tempMoney.substring(0, tempMoney.indexOf("."));
        fen = tempMoney.substring(tempMoney.indexOf(".") + 1, tempMoney.length());
        str = new String[] { yuan, fen };
        return str;
    }

    /**
     * 身份证号码长度的判断
     *
     * @param idCard
     * @return
     */
    public static boolean iDCardNumberLegthVerify(String idCard) {
        if (null != idCard) {
            if (idCard.length() == 15 || idCard.length() == 18) {
                return true;
            }
        }
        return false;
    }

    /***
     * 手机号码 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188 联通：130,131,132,152,155,156,185,186 电信：133,1349,153,180,189
     */

    public static boolean checkCellPhone(String phone) {
        /**
         * 中国移动：China Mobile
         **/
        String MOBILE = "^1(3[0-9]|5[0-35-9]|8[0253-9])\\d{8}$";
        /**
         * 中国联通：China Unicom
         **/
        String CM = "^1(34[0-8]|(3[5-9]|5[017-9]|8[23678])\\d)\\d{7}$";
        /**
         * 中国电信：China Telecom
         **/
        String CU = "^1(3[0-2]|5[256]|8[56])\\d{8}$";
        Pattern patternMOBILE = Pattern.compile(MOBILE);
        Matcher matcherMOBILE = patternMOBILE.matcher(phone);

        Pattern patternCM = Pattern.compile(CM);
        Matcher matcherCM = patternCM.matcher(phone);

        Pattern patternCU = Pattern.compile(CU);
        Matcher matcherCU = patternCU.matcher(phone);

        if (matcherMOBILE.matches() || matcherCM.matches() || matcherCU.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //验证邮政编码
    public static boolean checkPost(String post){
        if(post.matches("[1-9]\\d{5}(?!\\d)")){
            return true;
        }else{
            return false;
        }
    }

    //ua 正则表达式
    public static boolean checkUa(String ua){
        String pattern = "^CC\\/(\\d+(\\.\\d+){1,2})\\s*\\((.+);\\s*(Android|iOS)\\s*(.+);\\s*IMEI\\/(.+);\\s*Longitude\\/(\\-?\\d+(\\.\\d+)?);\\s*Latitude\\/(\\-?\\d+(\\.\\d+)?)\\)\\s*Serial\\/(\\d+)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ua);
        boolean bl=m.matches();
        return bl;
    }




    //判断banner type  高伟豪
    public static int checkBannerType(String url)
    {
        //三种类型：
        // RankingList代表网页文章。要用网页加载 http://a1.myappcc.com/cc/Find/RankingList?id=161226130014718
        // fuzhuang代表瀑布流  http://ios.myappcc.com/cc/Home/fuzhuang?id=5
        // GetWenZhangListIndex代表专题页面 http://ios.myappcc.com/cc/Home/GetWenZhangListIndex?id=14
        //当type：1=网页， 0=瀑布， 2=专题, -1=错误
        int type;
        String url_low=url.toLowerCase();

        int web=url_low.indexOf("rankinglist");
        int water=url_low.indexOf("fuzhuang");
        int page=url_low.indexOf("getwenzhanglistindex");


        if(web>0&&water<0&&page<0) //网页
        {
            type=1;

        }
        else  if(web<0&&water>0&&page<0)  //瀑布流
        {
            type=0;


        }
        else if(web<0&&water<0&&page>0)  // 专题
        {
            type=2;
            String[] sourceStrArray = url_low.split("id=");
            String  id=sourceStrArray[1];
        }
        else type=-1;
        return type;
    }

    //判断从web的文章页面跳转到产品  高伟豪
    public static boolean checkPageProduct(String url)
    {
        //三种类型：
        // RankingList代表网页文章。要用网页加载 http://android.myappcc.com/cc/Customize/buyCustomize?id=53050
        //return true 是product false 不是product
        String url_low=url.toLowerCase();

        int product=url_low.indexOf("buycustomize");


        if(product>0) //产品
        {
            return true;
        }
        else//非产品
        {
            return false;

        }
    }






}