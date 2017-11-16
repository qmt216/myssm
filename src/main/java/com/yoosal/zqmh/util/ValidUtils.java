package com.yoosal.zqmh.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidUtils {

    public static boolean isEmpty(Object obj) {
        if (null == obj)
            return true;
        if ("".equals(obj.toString().trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {

        return !isEmpty(obj);
    }

    public static String getSequenceId() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getCurrentlyDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public static String transformDateTime(long t) {
        Date date = new Date(t);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String getCurrentlyDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    public static String getNowStr() {
        return transformDateTime(System.currentTimeMillis());
    }

    /**
     * 是否是手机号
     *
     * @param s
     * @return
     */
    public static boolean isPhoneNum(String s) {
        if (isEmpty(s)) {
            return false;
        }
        Pattern p = Pattern.compile("^(1[3|4|7|5|8])\\d{9}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean isUrl(String s) {
        if (isEmpty(s)) {
            return false;
        }
        Pattern p = Pattern.compile("^(http|www|ftp|)?(://)?(\\\\w+(-\\\\w+)*)(\\\\.(\\\\w+(-\\\\w+)*))*((:\\\\d+)?)(/(\\\\w+(-\\\\w+)*))*(\\\\.?(\\\\w)*)(\\\\?)?(((\\\\w*%)*(\\\\w*\\\\?)*(\\\\w*:)*(\\\\w*\\\\+)*(\\\\w*\\\\.)*(\\\\w*&)*(\\\\w*-)*(\\\\w*=)*(\\\\w*%)*(\\\\w*\\\\?)*(\\\\w*:)*(\\\\w*\\\\+)*(\\\\w*\\\\.)*(\\\\w*&)*(\\\\w*-)*(\\\\w*=)*)*(\\\\w*)*)$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static int toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int toInt(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }

    public static String convertNull(String s) {
        if (s == null) {
            return "";
        } else {
            return s;
        }
    }

    public static String convertNull(String s, String def) {
        if (s == null) {
            return def;
        } else {
            return s;
        }
    }

    /**
     * 上传的文件重命名
     *
     * @param name 原文件名
     * @return 新文件名
     */
    public synchronized static String dealImageName(String name) {
        if ("".equals(name) || name == null) {
            return "";
        }
        String houName = name.substring(name.lastIndexOf("."), name.length());
        return System.currentTimeMillis() + (int) (Math.random() * 99) + houName;
    }

    public static String cutString(String s, int start, int length) {
        if (s == null || s.length() <= start) {
            return s;
        }
        if (s.length() < (start + length) || length < 1) {
            return s.substring(start, s.length());
        } else {
            return s.substring(start, start + length);
        }
    }
    /**
     * 客户端将常用的 html转义字符 转为普通字符进行显示
     *
     * @param htmlSrc
     * @return
     */
    public static String convertCharEntities(String htmlSrc) {
        if (isEmpty(htmlSrc)) {
            return "";
        }
        htmlSrc = htmlSrc.replaceAll("&ensp;", " ");
        htmlSrc = htmlSrc.replaceAll("&emsp;", " ");
        htmlSrc = htmlSrc.replaceAll("&nbsp;", " ");
        htmlSrc = htmlSrc.replaceAll("&lt;", "<");
        htmlSrc = htmlSrc.replaceAll("&gt;", ">");
        htmlSrc = htmlSrc.replaceAll("&amp;", "&");
        htmlSrc = htmlSrc.replaceAll("&quot;", "'");
        htmlSrc = htmlSrc.replaceAll("&copy;", "©");
        htmlSrc = htmlSrc.replaceAll("&reg;", "®");
        htmlSrc = htmlSrc.replaceAll("™", "™");
        htmlSrc = htmlSrc.replaceAll("&times;", "×");
        htmlSrc = htmlSrc.replaceAll("&divide;", "÷");
        return htmlSrc;
    }

    /**
     * 过滤 html
     *
     * @param htmlStr
     * @return
     */
    public static String clearHtml(String htmlStr) {
        if (isEmpty(htmlStr)) {
            return "";
        }
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return convertCharEntities(htmlStr.trim());
    }
    /**
     * 将传入字符转化为float类型
     *
     * @param s 输入字符 "0.01"
     * @return
     */
    public static float toFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return 0f;
        }
    }

    /**
     * 加密
     *
     * @param pwd 加密前的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String pwd) {
        String code = null;
        try {
            byte[] enc = desEncrypt(pwd.getBytes("UTF-8"));
            code = byteArr2HexStr(enc);
        } catch (Exception e) {
            return null;
        }
        return code;
    }

    /**
     * 解密
     *
     * @param code 加密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String code) {
        if (isEmpty(code)) {
            return "";
        }
        byte[] des = null;
        String dec = "";
        try {
            des = desDecrypt(hexStr2ByteArr(code));
            dec = new String(des, "UTF-8");
        } catch (Exception e) {
            return null;
        }
        return dec;
    }

    /**
     * 3DES加密(byte[]).
     *
     * @param src byte[]
     * @return byte[]
     * @throws Exception
     */
    public static byte[] desEncrypt(byte[] src) throws Exception {
        SecretKey key = genDESKey("123456781234567812345678"
                .getBytes());
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(src);
    }

    /**
     * 生成3DES密钥.
     *
     * @param key_byte seed key
     * @return javax.crypto.SecretKey Generated DES key
     * @throws Exception
     */
    public static SecretKey genDESKey(byte[] key_byte)
            throws Exception {
        SecretKey k = null;
        k = new SecretKeySpec(key_byte, "DESede");
        return k;
    }

    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * 3DES 解密(byte[]).
     *
     * @param crypt byte[]
     * @return byte[]
     * @throws Exception
     */
    public static byte[] desDecrypt(byte[] crypt) throws Exception {
        SecretKey key = genDESKey("123456781234567812345678"
                .getBytes());
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(crypt);
    }

    /**
     * 检查版本 是否比传入的值大
     *
     * @param request
     * @param paraName
     * @param value
     * @return
     */
    public static boolean compareVc(HttpServletRequest request, String paraName, Object value) {
        if (ValidUtils.isEmpty(request.getParameter(paraName))) {
            return false;
        }
        if (value instanceof Integer) {
            return ValidUtils.toInt(request.getParameter(paraName)) > (Integer) value;
        }
        if (value instanceof String) {
            return ValidUtils.convertNull(request.getParameter(paraName), "").compareTo(value.toString()) > 0;
        }
        if (value instanceof Float) {
            return ValidUtils.toFloat(request.getParameter(paraName)) > (Float) value;
        }
        return false;
    }

    public static String getFrontPath(HttpServletRequest request) {
        return "http://" + request.getServerName() + request.getContextPath() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort());
    }

    /**
     * 将集合类型的字符串转化为一个字符串
     * @param array 集合
     * @param split 分割符
     * @return
     */
    public static String conArrayToString(Object array, String split) {
        if (array == null) {
            return "";
        }
        if (array instanceof String[]) {
            String result = "";
            for (String s : (String[]) array) {
                result += s + split;
            }
            if (ValidUtils.isEmpty(split)) {
                return result;
            } else {
                return result.substring(0, result.lastIndexOf(split) + split.length());
            }
        } else if (array instanceof List) {
            String result = "";
            for (Object o : (List) array) {
                result += o.toString() + split;
            }
            if (ValidUtils.isEmpty(split)) {
                return result;
            } else {
                return result.substring(0, result.lastIndexOf(split) + split.length());
            }
        } else {
            return "";
        }
    }

    /**
     * 简单过滤要执行的SQL防止SQL注入
     * @param str 输入字符串
     * @return 处理后的字符串
     */
    public static String transactSQLInjection(String str){
        if(ValidUtils.isEmpty(str)){
            return "";
        }
        return str.replaceAll(".*([';]+|(--)+).*", " ");
    }
}
