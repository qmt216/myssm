package com.yoosal.zqmh.util.springmvc;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by qinmingtao on 2016/7/13.
 * Desc controller层切面
 */
public class ControllerCommonAspect {
    //    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ControllerCommonAspect.class);

    /**
     * 切面 记录请求参数，输出参数，执行时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String uri = request.getRequestURI();

        //打印请求参数
        if (logger.isDebugEnabled()) {
            StringBuffer sb = new StringBuffer();
            sb.append("【request信息】接口 URI：")
                    .append(uri)
//                    .append( "，clientIP：").append(IpUtil.getIpAddr(request))
//                    .append("，UserAgent：").append(getUserAgent(request))
//                    .append("，Cookie：").append(getCookie(request))
            ;
            Map paramMap = request.getParameterMap();
            if (paramMap != null && paramMap.size() > 0) {
                sb.append("，请求参数：");
                for (Object k : paramMap.keySet()) {
                    sb.append(JSONObject.toJSONString(k)).append(":").append(JSONObject.toJSONString(paramMap.get(k))).append(";");
                }
            }
            logger.debug(sb);
        }

        //打印响应数据
        if (logger.isDebugEnabled()) {
            StringBuffer sb = new StringBuffer();
            sb.append("【response信息】接口 URI：").append(uri)
//                    .append("，clientIP：").append(IpUtil.getIpAddr(request))
//                    .append("，UserAgent：").append(getUserAgent(request))
//                    .append("，Cookie：").append(getCookie(request))
                    .append("，响应数据：").append(JSONObject.toJSONString(joinPoint.proceed()));
            logger.debug(sb.toString());
        }


        //记录请求响应时间
        if (logger.isDebugEnabled()) {
            long time = System.currentTimeMillis() - startTime;
            this.logger.warn("[response_timeout_" + uri + "_" + time + "]");
        }


        return joinPoint.proceed();
    }

    protected String getUserAgent(HttpServletRequest pReq) {
        String userAgent = pReq.getHeader("User-Agent");
        if (null == userAgent) {
            userAgent = "";
        }
        return userAgent;
    }

    /**
     * 取客户端 存入header中 Cookie
     *
     * @param pReq
     * @return
     */
    protected String getCookie(HttpServletRequest pReq) {
        String cookie = pReq.getHeader("Cookie");
        return cookie;
    }
}
