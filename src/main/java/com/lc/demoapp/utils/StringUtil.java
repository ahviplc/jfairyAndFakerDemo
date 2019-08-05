package com.lc.demoapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author
 * @version V1.0
 * @Title StringUtil.java
 * @Package
 * @Description Copyright: Copyright (c) 2019
 * Company:lmt
 * @date 2018年6月11日10:34:48
 * @updateTime 2019年8月1日14:33:08
 */

@SuppressWarnings("all")
public final class StringUtil {

    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    private static final String MOBILE_REGEX = "^[1][0-9]{10}$";

    private static final String IPv6_REGEX = "^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$";

    private static final String IPv4_REGEX = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

    private static final String IMG_REGEX = ".+\\.(jpg|jpeg|png|gif|bmp)$";

    private static final String NUMERIC_REGEX = "[0-9]*";

    private static final String AGE_REGEX = "120|((1[0-1]|\\d)?\\d)";

    /**
     * 将其他类型转成字符串
     *
     * @param obj - 其他类型对象
     * @return - 字符串
     */
    public static String toString(Object obj) {

        if (null != obj) {
            return obj.toString();
        }
        return null;
    }

    /**
     * 将属于字符串类型的对象转成Integer对象
     *
     * @param o - 字符串类型的对象
     * @return - Integer对象
     */
    public static Integer strToInteger(Object o) {

        if (null == o || o.equals("")) {
            return null;
        }
        if (o instanceof Integer) {
            return (Integer) o;
        }
        return toInteger(o.toString());
    }

    /**
     * 将字符串转成Integer对象
     *
     * @param o - 字符串
     * @return - Integer对象
     */
    public static Integer toInteger(String o) {

        if (null == o || "".equals(o.trim())) {
            return null;
        }
        Integer t = Integer.valueOf(o);
        return t;
    }

    /**
     * 将属于字符串类型的对象转成Long对象
     *
     * @param o - 字符串类型的对象
     * @return - Long对象
     */
    public static Long strToLong(Object o) {

        if (null == o || o.equals("")) {
            return null;
        }
        if (o instanceof Long) {
            return (Long) o;
        }
        if (o instanceof Integer) {
            Integer i = (Integer) o;
            return Long.valueOf(i.longValue());
        }
        if (o instanceof Double) {
            Double d = (Double) o;
            return d.longValue();
        }
        if (o instanceof Float) {
            Float f = (Float) o;
            return f.longValue();
        }
        return toLong(o.toString());
    }

    /**
     * 将字符串转成Long对象
     *
     * @param o - 字符串
     * @return - Long对象
     */
    public static Long toLong(String o) {

        if (null == o || "".equals(o.trim())) {
            return null;
        }
        Long t = Long.valueOf(o);
        return t;
    }

    /**
     * 半角转全角
     * <p>
     * 半角：Single Byte Character case,全角：Double Byte Character case
     * <p>
     * 全角空格为12288，半角空格为32
     * <p>
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param input - 半角字符串
     * @return - 全角字符串
     */
    public static String toDoubleCase(String input) {

        if (null != input) {
            char[] chars = input.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == 32) {
                    chars[i] = (char) 12288;
                } else if (c >= 33 && c <= 126) {
                    chars[i] = (char) ((int) c + 65248);
                }
            }
            return new String(chars);
        }
        return null;
    }

    /**
     * 全角转半角
     * <p>
     * 全角空格为12288，半角空格为32
     * <p>
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param input - 全角字符串
     * @return - 半角字符串
     */
    public static String toSingleCase(String input) {

        if (null != input) {
            char[] chars = input.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == 12288) {
                    chars[i] = (char) 32;
                } else if (c >= 65281 && c <= 65374) {
                    chars[i] = (char) ((int) c - 65248);
                }
            }
            return new String(chars);
        }
        return null;
    }

    /**
     * 编码转换,将源字符串转换成unicode字符串,和native2ascii 功能相同
     *
     * @param source - 源字符串,可以是任意字符,包括汉字
     * @return unicode - 转换之后的unicode字符串
     */
    public static String native2ascii(String source) {

        if (null == source) {
            return null;
        }
        StringBuilder unicode = new StringBuilder(source.length() * 5);
        for (char ch : source.toCharArray()) {
            unicode.append("\\u").append(Integer.toHexString(ch));

        }
        return unicode.toString();
    }

    /**
     * 判断字符串是否为空，null 或者 "" 或者 " "（只有空格）都算是空
     *
     * @param s - 字符串
     * @return - 为空返回true,否则返回false
     */
    public static boolean isEmpty(String s) {

        if (null == s || "".equals(s.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断值和对象是否为空
     *
     * @param obj
     * @return
     */

    public static boolean isEmpty(Collection obj) {

        if (obj == null) {
            return true;
        }

        return obj.isEmpty();
    }

    public static boolean isEmpty(List obj) {

        if (obj == null) {
            return true;
        }

        return obj.isEmpty();
    }

    public static boolean isEmpty(Map m) {

        if (null == m) {
            return true;
        }
        return m.isEmpty();
    }

    public static boolean isEmpty(Object o) {

        if (null == o) {
            return true;
        }
        if (o.getClass().isArray()) {
            return Array.getLength(o) == 0;
        }
        if (o instanceof String) {
            return isEmpty((String) o);
        }
        if (o instanceof List) {
            return isEmpty((List) o);
        }
        if (o instanceof Collection) {
            return isEmpty((Collection) o);
        }
        return false;
    }

    /**
     * 判断是否是纯数字([0,9])字符串
     *
     * @param o - 待判断字符串
     * @return - true, 数字字符串
     */
    public static boolean isDigitStr(Object o) {

        if (isEmpty(o)) {
            return false;
        }
        String str = o.toString();
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否是邮箱
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {

        if (isEmpty(str)) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = regex.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是手机号
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {

        if (isEmpty(str)) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(MOBILE_REGEX);
            Matcher matcher = regex.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是图片名
     *
     * @param str
     * @return
     */
    public static boolean isImg(String str) {

        if (isEmpty(str)) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(IMG_REGEX);
            Matcher matcher = regex.matcher(str.toLowerCase());
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否IPV4地址
     *
     * @param str
     * @return
     */
    public static boolean isIp(String str) {

        if (isEmpty(str)) {
            return false;
        }

        Pattern p1 = Pattern.compile(IPv4_REGEX);
        Matcher m1 = p1.matcher(str);
        if (m1.matches()) {
            return true;
        }

        Pattern p2 = Pattern.compile(IPv6_REGEX);
        Matcher m2 = p2.matcher(str);
        return m2.matches();
    }

    /**
     * 获取6位数字随机码
     *
     * @return - 6位数字随机码
     */
    public static String randomN6() {

        Random r = new Random();
        int x = r.nextInt(1000000);
        String rv = String.format("%06d", Integer.valueOf(x));
        return rv;
    }

    /**
     * 将邮箱部分用**加密
     *
     * @param email
     * @return
     */
    public static String decriptEmail(String email) {
        if (!isEmail(email)) {
            return null;
        }
        String[] emailCodes = email.split("@");
        email = emailCodes[0];
        StringBuffer decriptEmail = new StringBuffer("");
        char hideCode = '*';
        int emailLength = email.length();
        for (int i = 0; i < emailLength; i++) {
            if (i < 2 || i > emailLength - 2) {
                decriptEmail.append(email.charAt(i));
            } else {
                decriptEmail.append(hideCode);
            }
        }
        return decriptEmail.toString() + "@" + emailCodes[1];
    }

    /**
     * 将手机部分用**加密
     *
     * @param mobile
     * @return
     */
    public static String decriptMobile(String mobile) {
        if (!isMobile(mobile)) {
            return null;
        }
        StringBuffer decriptMobile = new StringBuffer("");
        char hideCode = '*';
        int mobileLength = mobile.length();
        for (int i = 0; i < mobileLength; i++) {
            if (i < 3 || i > mobileLength - 5) {
                decriptMobile.append(mobile.charAt(i));
            } else {
                decriptMobile.append(hideCode);
            }
        }
        return decriptMobile.toString();
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static void filterLabel() {


    }


    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    /**
     * @param str
     * @return String
     * @throws
     * @Description: 过滤所有以<开头 ，>结尾的标签
     * @author lmt
     * 2018年10月10日16:23:33
     */
    public static String filterHtml(String str) {
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static Double toDouble(Object o) {

        if (null == o || o.equals("")) {
            return null;
        }
        if (o instanceof Double) {
            return (Double) o;
        }
        if (o instanceof Integer) {
            Integer i = (Integer) o;
            return i.doubleValue();
        }
        if (o instanceof Float) {
            Float f = (Float) o;
            return f.doubleValue();
        }
        if (o instanceof String) {
            String s = (String) o;
            return Double.parseDouble(s);
        }
        return null;
    }

    /**
     * 判断字符串是否以数字组成
     *
     * @param str
     * @return
     * @auth lmt
     * @date 2018年10月10日16:23:06
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(NUMERIC_REGEX);
            Matcher matcher = regex.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否年龄
     *
     * @param str
     * @return
     * @auth lmt
     * @date 2018年10月10日16:23:18
     */
    public static boolean isAge(String str) {
        if (isEmpty(str)) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(AGE_REGEX);
            Matcher matcher = regex.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    //-------------------------------------------------------分割符
    //字符串判断是否含有中文等相关

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 过滤掉中文
     *
     * @param str 待过滤中文的字符串
     * @return 过滤掉中文后字符串
     */
    public static String filterChinese(String str) {
        // 用于返回结果
        String result = str;
        boolean flag = isContainChinese(str);
        if (flag) {// 包含中文
            // 用于拼接过滤中文后的字符
            StringBuffer sb = new StringBuffer();
            // 用于校验是否为中文
            boolean flag2 = false;
            // 用于临时存储单字符
            char chinese = 0;
            // 5.去除掉文件名中的中文
            // 将字符串转换成char[]
            char[] charArray = str.toCharArray();
            // 过滤到中文及中文字符
            for (int i = 0; i < charArray.length; i++) {
                chinese = charArray[i];
                flag2 = isChinese(chinese);
                if (!flag2) {// 不是中日韩文字及标点符号
                    sb.append(chinese);
                }
            }
            result = sb.toString();
        }
        return result;
    }


    /**
     * 校验一个字符是否是汉字
     *
     * @param c 被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChineseChar(char c) {
        try {
            return String.valueOf(c).getBytes("UTF-8").length > 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证字符串内容是否包含下列非法字符<br>
     * `~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆
     *
     * @param content 字符串内容
     * @return 't'代表不包含非法字符，otherwise代表包含非法字符。
     */
    public static char validateLegalString(String content) {
        String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";
        char isLegalChar = 't';
        L1:
        for (int i = 0; i < content.length(); i++) {
            for (int j = 0; j < illegal.length(); j++) {
                if (content.charAt(i) == illegal.charAt(j)) {
                    isLegalChar = content.charAt(i);
                    break L1;
                }
            }
        }
        return isLegalChar;
    }

    /**
     * 验证是否是汉字或者0-9、a-z、A-Z
     *
     * @param c 被验证的char
     * @return true代表符合条件
     */
    public static boolean isRightChar(char c) {
        return isChinese(c) || isWord(c);
    }

    /**
     * 校验某个字符是否是a-z、A-Z、_、0-9
     *
     * @param c 被校验的字符
     * @return true代表符合条件
     */
    public static boolean isWord(char c) {
        String regEx = "[\\w]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("" + c);
        return m.matches();
    }

    /**
     * 判定输入的是否是汉字
     *
     * @param c 被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 校验String是否全是中文
     *
     * @param name 被校验的字符串
     * @return true代表全是汉字
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    //------------------------------------------------------------------------------分隔符结束

    //------------------------------------------------------------------------------分割符2

    // Java操作字符串的工具类 - SummerChill - 博客园
    // https://www.cnblogs.com/DreamDrive/p/5760588.html

    /**
     * 过滤空NULL
     *
     * @param o
     * @return
     */
    public static String FilterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "";
    }

    /**
     * 是否为空
     * isEmpty2 上面有一个重复的 在line:com/common/utils/StringUtil.java:257
     *
     * @param o
     * @return
     */
    public static boolean isEmpty2(Object o) {
        if (o == null) {
            return true;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否不为空
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否可转化为数字
     *
     * @param o
     * @return
     */
    public static boolean isNum(Object o) {
        try {
            new BigDecimal(o.toString());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 是否可转化为Long型数字
     *
     * @param o
     * @return
     */
    public static boolean isLong(Object o) {
        try {
            new Long(o.toString());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 转化为Long型数字, 不可转化时返回0
     *
     * @param o
     * @return
     */
    public static Long toLong(Object o) {
        if (isLong(o)) {
            return new Long(o.toString());
        } else {
            return 0L;
        }
    }

    /**
     * 转化为int型数字, 不可转化时返回0
     *
     * @param o
     * @return
     */
    public static int toInt(Object o) {
        if (isNum(o)) {
            return new Integer(o.toString());
        } else {
            return 0;
        }
    }

    /**
     * 按字符从左截取固定长度字符串, 防止字符串超长, 默认截取50
     *
     * @param o
     * @return
     */
    public static String holdmaxlength(Object o) {
        int maxlength = 50;
        if (o == null) {
            return "";
        }
        return subStringByByte(o, maxlength);
    }

    /**
     * 从左截取固定长度字符串, 防止字符串超长, maxlength为0时默认50
     *
     * @param o
     * @return
     */
    public static String holdmaxlength(Object o, int maxlength) {
        maxlength = maxlength <= 0 ? 50 : maxlength;
        if (o == null) {
            return "";
        }
        return subStringByByte(o, maxlength);
    }

    /**
     * 按字节截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    private static String subStringByByte(Object o, int len) {
        if (o == null) {
            return "";
        }
        String str = o.toString();
        String result = null;
        if (str != null) {
            byte[] a = str.getBytes();
            if (a.length <= len) {
                result = str;
            } else if (len > 0) {
                result = new String(a, 0, len);
                int length = result.length();
                if (str.charAt(length - 1) != result.charAt(length - 1)) {
                    if (length < 2) {
                        result = null;
                    } else {
                        result = result.substring(0, length - 1);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 逗号表达式_添加
     *
     * @param commaexpress 原逗号表达式 如 A,B
     * @param newelement   新增元素 C
     * @return A, B, C
     */
    public static String comma_add(String commaexpress, String newelement) {
        return comma_rect(FilterNull(commaexpress) + "," + FilterNull(newelement));
    }

    /**
     * 逗号表达式_删除
     *
     * @param commaexpress 原逗号表达式 如 A,B,C
     * @param delelement   删除元素 C,A
     * @return B
     */
    public static String comma_del(String commaexpress, String delelement) {
        if ((commaexpress == null) || (delelement == null) || (commaexpress.trim().equals(delelement.trim()))) {
            return "";
        }
        String[] deletelist = delelement.split(",");
        String result = commaexpress;
        for (String delstr : deletelist) {
            result = comma_delone(result, delstr);
        }
        return result;
    }

    /**
     * 逗号表达式_单一删除
     *
     * @param commaexpress 原逗号表达式 如 A,B,C
     * @param delelement   删除元素 C
     * @return A, B
     */
    public static String comma_delone(String commaexpress, String delelement) {
        if ((commaexpress == null) || (delelement == null) || (commaexpress.trim().equals(delelement.trim()))) {
            return "";
        }
        String[] strlist = commaexpress.split(",");
        StringBuffer result = new StringBuffer();
        for (String str : strlist) {
            if ((!str.trim().equals(delelement.trim())) && (!"".equals(str.trim()))) {
                result.append(str.trim() + ",");
            }
        }
        return result.toString().substring(0, result.length() - 1 > 0 ? result.length() - 1 : 0);
    }

    /**
     * 逗号表达式_判断是否包含元素
     *
     * @param commaexpress 逗号表达式 A,B,C
     * @param element      C
     * @return true
     */
    public static boolean comma_contains(String commaexpress, String element) {
        boolean flag = false;
        commaexpress = FilterNull(commaexpress);
        element = FilterNull(element);
        if (!"".equals(commaexpress) && !"".equals(element)) {
            String[] strlist = commaexpress.split(",");
            for (String str : strlist) {
                if (str.trim().equals(element.trim())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 逗号表达式_取交集
     *
     * @param commaexpressA 逗号表达式1  A,B,C
     * @param commaexpressB 逗号表达式2  B,C,D
     * @return B, C
     */
    public static String comma_intersect(String commaexpressA, String commaexpressB) {
        commaexpressA = FilterNull(commaexpressA);
        commaexpressB = FilterNull(commaexpressB);
        StringBuffer result = new StringBuffer();
        String[] strlistA = commaexpressA.split(",");
        String[] strlistB = commaexpressB.split(",");
        for (String boA : strlistA) {
            for (String boB : strlistB) {
                if (boA.trim().equals(boB.trim())) {
                    result.append(boA.trim() + ",");
                }
            }
        }
        return comma_rect(result.toString());
    }

    /**
     * 逗号表达式_规范
     *
     * @param commaexpress 逗号表达式  ,A,B,B,,C
     * @return A, B, C
     */
    public static String comma_rect(String commaexpress) {
        commaexpress = FilterNull(commaexpress);
        String[] strlist = commaexpress.split(",");
        StringBuffer result = new StringBuffer();
        for (String str : strlist) {
            if (!("".equals(str.trim())) && !("," + result.toString() + ",").contains("," + str + ",") && !"null".equals(str)) {
                result.append(str.trim() + ",");
            }
        }
        return result.toString().substring(0, (result.length() - 1 > 0) ? result.length() - 1 : 0);
    }

    /**
     * 逗号表达式_反转
     *
     * @param commaexpress A,B,C
     * @return C, B, A
     */
    public static String comma_reverse(String commaexpress) {
        commaexpress = FilterNull(commaexpress);
        String[] ids = commaexpress.split(",");
        StringBuffer str = new StringBuffer();
        for (int i = ids.length - 1; i >= 0; i--) {
            str.append(ids[i] + ",");
        }
        return comma_rect(str.toString());
    }

    /**
     * 逗号表达式_获取首对象
     *
     * @param commaexpress A,B,C
     * @return A
     */
    public static String comma_first(String commaexpress) {
        commaexpress = FilterNull(commaexpress);
        String[] ids = commaexpress.split(",");
        System.out.println("length:" + ids.length);
        if ((ids != null) && (ids.length > 0)) {
            return ids[0];
        }
        return null;
    }

    /**
     * 逗号表达式_获取尾对象
     *
     * @param commaexpress A,B,C
     * @return C
     */
    public static String comma_last(String commaexpress) {
        commaexpress = FilterNull(commaexpress);
        String[] ids = commaexpress.split(",");
        if ((ids != null) && (ids.length > 0)) {
            return ids[(ids.length - 1)];
        }
        return null;
    }

    /**
     * 替换字符串,支持字符串为空的情形
     *
     * @param strData
     * @param regex
     * @param replacement
     * @return
     */
    public static String replace(String strData, String regex, String replacement) {
        return strData == null ? "" : strData.replaceAll(regex, replacement);
    }

    /**
     * 字符串转为HTML显示字符
     *
     * @param strData
     * @return
     */
    public static String String2HTML(String strData) {
        if (strData == null || "".equals(strData)) {
            return "";
        }
        strData = replace(strData, "&", "&amp;");
        strData = replace(strData, "<", "&lt;");
        strData = replace(strData, ">", "&gt;");
        strData = replace(strData, "\"", "&quot;");
        return strData;
    }

    /**
     * 把异常信息转换成字符串，以方便保存
     */
    public static String getexceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            e.printStackTrace(new PrintStream(baos));
        } finally {
            try {
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return baos.toString();
    }

    /**
     * 过滤特殊符号
     */
    public static String regex(String str) {
        Pattern pattern = Pattern.compile("[0-9-:/ ]");// 中文汉字编码区间
        Matcher matcher;
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            matcher = pattern.matcher(String.valueOf(array[i]));
            if (!matcher.matches()) {// 空格暂不替换
                str = str.replace(String.valueOf(array[i]), "");// 特殊字符用空字符串替换
            }
        }

        return str;
    }

    /**
     * comma_insert
     * 在commaexpress中插入字符串newelement
     *
     * @param commaexpress
     * @param newelement
     * @param index        插入位置 0代表头上
     * @return
     */
    public static String comma_insert(String commaexpress, String newelement, int index) {
        int length = commaexpress.length();
        if (index > length) {
            index = length;
        } else if (index < 0) {
            index = 0;
        }
        String result = commaexpress.substring(0, index) + newelement + commaexpress.substring(index, commaexpress.length());
        return result;
    }

    /**
     * 将"/"替换成"\"
     *
     * @param strDir
     * @return
     */
    public static String changeDirection(String strDir) {
        String s = "/";
        String a = "\\";
        if (strDir != null && !" ".equals(strDir)) {
            if (strDir.contains(s)) {
                strDir = strDir.replace(s, a);
            }
        }
        return strDir;
    }

    /**
     * 去除字符串中 头和尾的空格，中间的空格保留
     *
     * @return String
     * @throws
     * @Title: trim
     * @Description: TODO
     */
    public static String trim(String s) {
        int i = s.length();// 字符串最后一个字符的位置
        int j = 0;// 字符串第一个字符
        int k = 0;// 中间变量
        char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
        while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
            ++j;// 确定字符串前面的空格数
        while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
            --i;// 确定字符串后面的空格数
        return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
    }

    /**
     * 得到大括号中的内容
     *
     * @param str
     * @return
     */
    public static String getBrackets(String str) {
        int a = str.indexOf("{");
        int c = str.indexOf("}");
        if (a >= 0 && c >= 0 & c > a) {
            return (str.substring(a + 1, c));
        } else {
            return str;
        }
    }

    /**
     * 将字符串中所有的，替换成|
     *
     * @param str
     * @return
     */
    public static String commaToVerti(String str) {
        if (str != null && !"".equals(str) && str.contains(",")) {
            return str.replaceAll(",", "|");
        } else {
            return str;
        }
    }

    /**
     * 去掉字符串中、前、后的空格
     *
     * @param args
     * @throws IOException
     */
    public static String extractBlank(String name) {
        if (name != null && !"".equals(name)) {
            return name.replaceAll(" +", "");
        } else {
            return name;
        }
    }

    /**
     * 将null换成""
     *
     * @param str
     * @return
     */
    public static String ConvertStr(String str) {
        return str != null && !"null".equals(str) ? str.trim() : "";
    }
    //------------------------------------------------------------------------------分隔符结束2

    /**
     * 防止非法实例化
     */
    private StringUtil() {
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.isEmail("lc@126.com"));//true
        System.out.println(StringUtil.isNumeric("160510150529289450"));//true
        Pattern regex = Pattern.compile("^[\\s\\S]{0,19}$");
        Matcher matcher = regex.matcher("工工工工工工工工工工工工工工工工工工工");
        System.out.println(matcher.matches());//true
        System.out.println(StringUtil.decriptMobile("15898761980"));//158****1980
        System.out.println(StringUtil.decriptEmail("ahlc@126.com"));//ah*c@126.com

        System.out.println("--------------------------------");
        String fileName = "test，中文";
        String fileNameNoChinese = "test12345678910abcdef";
        System.out.println(filterChinese(fileName));//过滤掉中文---test
        System.out.println(isContainChinese(fileName));//true
        System.out.println(isContainChinese(fileNameNoChinese));//false

        String fileNameLegalString = "123";
        String fileNameLegalStringYes = "123}";//非法字符包括【`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆】
        System.out.println(StringUtil.validateLegalString(fileNameLegalString));//输出char---t
        System.out.println(String.valueOf(StringUtil.validateLegalString(fileNameLegalString)));//输出char转string---t
        System.out.println(String.valueOf(StringUtil.validateLegalString(fileNameLegalString)).equals("t"));//不包含非法字符---true
        System.out.println(String.valueOf(StringUtil.validateLegalString(fileNameLegalStringYes)).equals("t"));//包含非法字符---false

        char isLegalCharTemp = 'a';
        System.out.println(isLegalCharTemp);//a
        System.out.println(String.valueOf(isLegalCharTemp));//a

        System.out.println("--------------------------------");
        System.out.println(isNum("a"));//false
        System.out.println(isNum("-1"));//true
        System.out.println(isNum("01"));//true
        System.out.println(isNum("1E3"));//true
        System.out.println(isNum("1.a"));//false
        System.out.println(isLong("014650"));//true
        System.out.println(Long.parseLong("014650"));//14650

        String s1 = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        String s2 = "22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222";
        String s3 = "33";
        System.out.println(subStringByByte(s1 + s2 + s3, 128));//128字节 256字符 (16进制)
        System.out.println(subStringByByte(s1 + s2 + s3, 256));//128字节 256字符 (16进制)
        System.out.println(subStringByByte(s1 + s2 + s3, 257));//128字节 256字符 (16进制)

        System.out.println((s1+s2+s3).length());//258
        System.out.println((s1+s2+s3).length() / 128);//258除以128 商2余2
        System.out.println((s1+s2+s3).length() % 128);//258除以128 商2余2

        System.out.println("=========================");
        for (int i = 1; i < (s1 + s2 + s3).length() / 128 + 1; i++) {
            System.out.println(i);
            System.out.println((s1 + s2 + s3).substring(128 * (i-1), 128 * (i-1) + 128));
        }
        System.out.println("=========================");

        System.out.println(comma_insert("11", "2", 1));//121
    }
}
