package com.yoosal.zqmh.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qinmingtao on 2016/7/13.
 * Desc
 */
public class IpUtil {
    //获取客户端的IP地址
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && !"".equals(ip) && ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if (ip == null || ("").equals(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ("").equals(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ("").equals(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}