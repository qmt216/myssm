package com.yoosal.zqmh.control;

import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: ibm
 * Date: 14-1-8
 * Time: 上午11:50
 * To change this template use File | Settings | File Templates.
 */
public class MessageUtil {
    public static class CallBackType {
        public final static String closeCurrent = "closeCurrent";
        public final static String refreshCurrent = "refreshCurrent";
    }

    public static class Message {
        public final static String insertSuccess = "添加成功！";
        public final static String insertError = "添加失败！";
        public final static String editSuccess = "修改成功！";
        public final static String editError = "修改失败！";
        public final static String dropSuccess = "删除成功！";
        public final static String dropError = "删除失败！";
        public final static String operateSuccess = "操作成功！";
        public final static String operateError = "操作失败！";
    }

    private static JSONObject writeMessage(String msg, int status, int statusCode, String navTabId, String callbackType) {
        JSONObject result = new JSONObject();
        result.put("message", msg);
        result.put("status", status);
        result.put("statusCode", statusCode);
        result.put("navTabId", navTabId);
        result.put("callbackType", callbackType);
        return result;
    }

    public static JSONObject writeMessage(String key, String value) {
        JSONObject result = new JSONObject();
        result.put(key, value);
        return result;
    }

    public static JSONObject successMessage(String msg, String navTabId, String callbackType) {
        return writeMessage(msg, 1, 1, navTabId, callbackType);
    }

    public static JSONObject errorMessage(String msg, String navTabId, String callbackType) {
        return writeMessage(msg, 0, 0, navTabId, callbackType);
    }
}
