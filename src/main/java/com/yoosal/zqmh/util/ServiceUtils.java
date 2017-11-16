package com.yoosal.zqmh.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangankang on 16/1/18.
 */
public abstract class ServiceUtils {

    /**
     * 参数	            必须	        说明
     * appid	         是         公众号的唯一标识
     * redirect_uri	     是         	授权后重定向的回调链接地址
     * response_type     是         	返回类型，请填写code
     * scope	         是	        应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * state	         否	        重定向后会带上state参数，开发者可以填写任意参数值
     * #wechat_redirect	 否	        直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
     * <p/>
     * <p/>
     * 实例:
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcb9dd88f5753304d&redirect_uri=http://ctss.yoosal.cn/yaoyao/index.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
     */
    public static final String oauth2Uri = "https://open.weixin.qq.com/connect/oauth2/authorize";

    public static final String authUri = "https://api.weixin.qq.com/sns/oauth2/access_token";   //获得 access_token
    public static final String userInfoUri = "https://api.weixin.qq.com/sns/userinfo";          //获得 user_info
    public static final String appid = "wxcb9dd88f5753304d";
    public static final String secret = "78aab5f6c8302609abf56346990c0660";
    public static final String grant_type = "authorization_code";
    public static final String lang = "zh_CN";


    public static JSONObject getUserInfo(String code) {
        Map<String, String> authParams = new HashMap<String, String>();
        authParams.put("code", code);
        authParams.put("appid", appid);
        authParams.put("secret", secret);
        authParams.put("grant_type", grant_type);
        String authJson = HttpJsonClient.post(authUri, authParams);
        JSONObject authJsonObject = JSON.parseObject(authJson);

        Map<String, String> infoParams = new HashMap<String, String>();
        infoParams.put("access_token", authJsonObject.getString("access_token"));
        infoParams.put("openid", authJsonObject.getString("openid"));
        infoParams.put("lang", lang);
        String infoJson = HttpJsonClient.post(userInfoUri, infoParams);

        return JSON.parseObject(infoJson);
    }
}
