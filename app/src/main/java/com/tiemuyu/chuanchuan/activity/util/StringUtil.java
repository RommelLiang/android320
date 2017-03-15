package com.tiemuyu.chuanchuan.activity.util;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CC2.0 on 2017/1/6.
 */
public class StringUtil {

    private static final char[] upperCaseNumber = { '一', '二', '三', '四', '五',
            '六', '七', '八', '九', '十' };
    private static final char[] lowCaseNumber = { '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '0' };

    public static boolean isNull(String str) {
        return str == null || str.equals("");
    }

    public static boolean isNullWithTrim(String str) {
        return str == null || str.trim().equals("");
    }

    /** null or empty return true , else return false **/
    public static boolean isNULLorEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 判断字符串是否为正整数
     *
     * @param str
     * @return
     */
    public static boolean isZhengshu(String str) {
        boolean arg = false;
        if (str != null)
            arg = str.matches("[0-9]+");
        return arg;
    }

    public static String decodeHtml(String value) {
        if ((value == null) || (value.length() == 0)) {
            return value;
        }
        StringBuffer result = new StringBuffer(value.length());
        String v = null;

        int index = 0;
        while (index < value.length()) {
            char c = value.charAt(index);
            if (c == '&') {
                v = value.substring(index);
                if (v.startsWith("&lt;")) {
                    index += "&lt;".length();
                    c = '<';
                } else if (v.startsWith("&gt;")) {
                    index += "&gt;".length();
                    c = '>';
                } else if (v.startsWith("&amp;")) {
                    index += "&amp;".length();
                    c = '&';
                } else if (v.startsWith("&quot;")) {
                    index += "&quot;".length();
                    c = '"';
                } else if (v.startsWith("&#39;")) {
                    index += "&#39;".length();
                    c = '\'';
                } else if (v.startsWith("&frasl;")) {
                    index += "&frasl;".length();
                    c = '/';
                } else if (v.startsWith("&le;")) {
                    index += "&le;".length();
                    c = '≤';
                } else if (v.startsWith("&ge;")) {
                    index += "&ge;".length();
                    c = '≥';
                } else if (v.startsWith("&t;")) {
                    index += "&t;".length();
                    c = '<';
                } else if (v.startsWith("&#x000A;")) {
                    index += "&#x000A;".length();
                    c = '\n';
                } else if (v.startsWith("&#8260;")) {
                    index += "&#8260;".length();
                    c = '/';
                } else if (v.startsWith("&nbsp;")) {
                    index += "&nbsp;".length();
                    c = ' ';
                } else {
                    ++index;
                }
            } else {
                ++index;
            }
            result.append(c);
        }
        return ((result == null) ? value : result.toString());
    }

    public static String decodeHtmlWithMask(String value) {
        if ((value == null) || (value.length() == 0)) {
            return value;
        }
        StringBuffer result = new StringBuffer(value.length());
        String v = null;

        int index = 0;
        while (index < value.length()) {
            char c = value.charAt(index);
            if (c == '&') {
                v = value.substring(index);
                if (v.startsWith("&lt;")) {
                    index += "&lt;".length();
                    c = '《';
                } else if (v.startsWith("&gt;")) {
                    index += "&gt;".length();
                    c = '》';
                } else if (v.startsWith("&amp;")) {
                    index += "&amp;".length();
                    c = '&';
                } else if (v.startsWith("&quot;")) {
                    index += "&quot;".length();
                    c = '"';
                } else if (v.startsWith("&#39;")) {
                    index += "&#39;".length();
                    c = '\'';
                } else if (v.startsWith("&frasl;")) {
                    index += "&frasl;".length();
                    c = '/';
                } else if (v.startsWith("&le;")) {
                    index += "&le;".length();
                    c = '≤';
                } else if (v.startsWith("&ge;")) {
                    index += "&ge;".length();
                    c = '≥';
                } else if (v.startsWith("&t;")) {
                    index += "&t;".length();
                    c = '《';
                } else if (v.startsWith("&#x000A;")) {
                    index += "&#x000A;".length();
                    c = '\n';
                } else if (v.startsWith("&#8260;")) {
                    index += "&#8260;".length();
                    c = '/';
                } else if (v.startsWith("&nbsp;")) {
                    index += "&nbsp;".length();
                    c = ' ';
                } else {
                    ++index;
                }
            } else {
                ++index;
            }
            result.append(c);
        }
        return ((result == null) ? value : result.toString());
    }

    public static String encryptTel(String tel) {
        if (tel == null || tel.length() != 11)
            return tel;
        int index = 0;
        StringBuilder buf = new StringBuilder();
        while (index < tel.length()) {
            char c;
            if (index > 2 && index < 7) {
                c = '*';
            } else {
                c = tel.charAt(index);
            }
            buf.append(c);
            index++;
        }
        return buf.toString();
    }

    public static String formartText(String text) {
        if (text == null)
            return text;
        int index = 0;
        StringBuilder buf = new StringBuilder();
        while (index < text.length()) {
            char c = text.charAt(index);
            int prePos = index - 1;
            char pre = ' ';
            if (prePos >= 0)
                pre = text.charAt(prePos);
            boolean isUpper = isUppercaseNumber(c);
            boolean isLower = isLowercaseNumber(c);
            // Log.i(TAG, String.valueOf(pre));
            if ((isUpper)
                    || (isLower && (pre == '.' || pre == '。' || pre == '：'
                    || pre == ':' || pre == ' '))) {
                // Log.i(TAG, "ok.. pre:" + pre + ", c=" + c);
                int nextPos = index + 1;
                if (nextPos < text.length()) {
                    StringBuilder digital = new StringBuilder();
                    digital.append(c);
                    char next = text.charAt(nextPos);
                    if (isUpper) {
                        while (nextPos < text.length()
                                && isUppercaseNumber(next)) {
                            digital.append(next);
                            nextPos++;
                            index++;
                            next = text.charAt(nextPos);
                        }

                    } else if (isLower) {
                        while (nextPos < text.length()
                                && isLowercaseNumber(next)) {
                            digital.append(next);
                            nextPos++;
                            index++;
                            next = text.charAt(nextPos);
                        }
                    }
                    if (next == '、') {
                        if (!(c == '一' && digital.length() == 1))
                            buf.append("\n\n");
                        if (isLowercaseNumber(c))
                            buf.append('\t');
                        buf.append(digital.toString()).append(next);
                        index += 2;
                        continue;
                    }
                }
            }
            buf.append(c);
            index++;
        }
        return buf.toString();
    }

    public static boolean isUppercaseNumber(char c) {
        for (char number : upperCaseNumber) {
            if (c == number)
                return true;
        }
        return false;
    }

    public static boolean isLowercaseNumber(char c) {
        for (char number : lowCaseNumber) {
            if (c == number)
                return true;
        }
        return false;
    }

    public static String backToAngleBrackets(String text) {

        return text.replaceAll("《", "<").replaceAll("》", ">");

    }

    /**
     * 身份证验证
     *
     * @param IDNumber
     * @return
     */
    public static boolean isLegal(String IDNumber) {
        boolean result = IDNumber.matches("[0-9]{15}|[0-9]{17}[0-9Xx]");
        if (result) {
            int year, month, date;
            if (IDNumber.length() == 15) {
                year = Integer.parseInt(IDNumber.substring(6, 8));
                month = Integer.parseInt(IDNumber.substring(8, 10));
                date = Integer.parseInt(IDNumber.substring(10, 12));
            } else {
                year = Integer.parseInt(IDNumber.substring(6, 10));
                month = Integer.parseInt(IDNumber.substring(10, 12));
                date = Integer.parseInt(IDNumber.substring(12, 14));
            }
            switch (month) {
                case 2:
                    result = (date >= 1)
                            && (year % 4 == 0 ? date <= 29 : date <= 28);
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    result = (date >= 1) && (date <= 31);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    result = (date >= 1) && (date <= 31);
                    break;
                default:
                    result = false;
                    break;
            }
        }
        return result;
    }

    /**
     * 从URI中获取文件名
     */
    public static String getFileNameFromUrl(final String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * 保留指定位小数点
     *
     * @param x
     * @param count
     * @return
     */
    public static String fractionDigits(double x, int count) {
        NumberFormat df = NumberFormat.getNumberInstance();
        df.setMaximumFractionDigits(count);
        df.setMinimumFractionDigits(count);
        String value = df.format(x);
        return value;
    }

    public static boolean isDataFormat(String str) {
        boolean flag = false;
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否是数字 (含小数点)
     */
    public static boolean isNum(String str) {
        boolean arg = false;
        if (!StringUtil.isNullWithTrim(str)) {
            str = str.replace(".", "");
            arg = isNumeric(str);
        }
        return arg;

    }

    /**
     * 判断是否是合法密码，true 是.
     */
    public static boolean isValidPass(String str) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,16}");
        return pattern.matcher(str).matches();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String valueOfNull(String str) {
        return str == null ? " " : str;
    }

    /**
     * @param mobile
     *            ：手机号字符串
     * @return 是11位的数字，返回true,其他情况返回false
     */
    public static boolean isPhoneNumber(String mobile) {

        // 如果说为空的话
        if (StringUtil.isNullWithTrim(mobile)) {
            return false;
        }

        if (!mobile.substring(0, 1).equals(1) && mobile.length() != 11) {
            return false;
        }
        return true;
    }

    public static boolean isEmail(String email) {
        String str = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static String bytesTohexString(final byte[] data) {
        if (data != null && data.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(data.length);
            for (byte byteChar : data)
                stringBuilder.append(String.format("%02X ", byteChar));
            return stringBuilder.toString().replaceAll(" ", "");
        } else {
            return null;
        }
    }

    /**
     * 截取时间前半部分
     * */
    public static String getTimeString(String time) {
        String[] arrstr = time.split(" ");

        return arrstr[0];
    }

    /**
     * 替换时间格式
     * */
    public static String turnTimeString(String time) {
        String ss = time.replaceAll("T", " ");
        return ss;
    }

//    public static String isH5Msg(String msg) {
//
//        String is = "";
//        if (msg.startsWith("cc://")) {
//            if (msg.startsWith("cc://login")) {
//                is = Constant.CC_LOGIN;
//            } else if (msg.startsWith("cc://home")) {
//                is = Constant.CC_HOME;
//            } else if (msg.startsWith("cc://back")) {
//                is = Constant.CC_BACK;
//            } else if (msg.startsWith("cc://loadingStart")) {
//                is = Constant.CC_LOADINGSTART;
//            } else if (msg.startsWith("cc://loadingEnd")) {
//                is = Constant.CC_LOADINGEND;
//            } else if (msg.startsWith("cc://timeout")) {
//                is = Constant.CC_TIMEOUT;
//            } else if (msg.startsWith("cc://pay")) {
//                is = Constant.CC_PAY;
//            } else if (msg.startsWith("cc://toast")) {
//                is = Constant.CC_TOAST;
//            } else if (msg.startsWith("cc://address/add")) {
//                is = Constant.CC_ADDRESS;
//            } else if (msg.startsWith("cc://address/modify")) {
//                is = Constant.CC_MODIFY_ADDRESS;
//            } else if (msg.startsWith("cc://feedback")) {
//                is = Constant.CC_FEEDBACK;
//            } else if (msg.startsWith("cc://share")) {
//                is = Constant.CC_SHARE;
//            } else if (msg.startsWith("cc://updateAccount")) {
//                // 更新资金账户
//                is = Constant.CC_UPDATA_ACCOUNT;
//            } else if (msg.startsWith("cc://uploadImage")) {
//                is = Constant.CC_UPLOAD_IMAGE;
//            } else if (msg.startsWith("cc://invite")) {
//                is = Constant.CC_INVITE;
//            }else if(msg.startsWith("cc://isLogin")){
//                is=Constant.CC_ISLOGIN;
//            }
//            else if(msg.startsWith("cc://header/left/backBtn")){
//                is=Constant.CC_BACK_BTN;
//            }else if(msg.startsWith("cc://header/title")){
//
//                is=Constant.CC_MODIFY_TITLE;
//            }else if(msg.startsWith("cc://prev")){
//                is=Constant.CC_PREV;
//            }else if(msg.startsWith("cc://header/right/textBtn")){
//                is=Constant.CC_TVRIGHT;
//            }else if(msg.startsWith("cc://register")){
//                is=Constant.CC_REGIST;
//            }
//            else {
//                is = "";
//            }
//        }
//
//        return is;
//    }

    public static String getH5Toast(String msg) {
        String ss = null;
        ss = msg.substring(19);
        return ss;
    }

    public static String getH5Share(String msg) {
        String ss = null;
        // String []s=msg.split("?");
        ss = msg.substring(11);
        // System.out.println("-----截取分享>"+s+",s[]"+s.length);
        // ss=s[1];
        return ss;
    }

    public static String getH5Pay(String msg) {
        String ss = null;
        ss = msg.substring(10);
        return ss;
    }

    public static String[] getNewString(String data, String split) {
        String[] s = data.split(split);
        return s;
    }
}