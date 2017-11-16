package com.yoosal.zqmh.util;

import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClient implements java.io.Serializable {
    private static final long serialVersionUID = 3123685669790532766L;
    private static final int OK = 200;
    private static final int NOT_MODIFIED = 304;
    private static final int BAD_REQUEST = 400;
    private static final int NOT_AUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int NOT_ACCEPTABLE = 406;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;

    /**
     * TODO 暂不支持代理
     */
    private String proxyHost = null;
    private int proxyPort = 0;
    private String proxyAuthUser = null;
    private String proxyAuthPassword = null;

    public String getProxyHost() {
        return proxyHost;
    }


    public int getProxyPort() {
        return proxyPort;
    }


    public String getProxyAuthUser() {
        return proxyAuthUser;
    }

    public String getProxyAuthPassword() {
        return proxyAuthPassword;
    }

    org.apache.commons.httpclient.HttpClient client = null;

    public HttpClient() {
        this(150, 30000, 30000);
    }

    public HttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs) {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setDefaultMaxConnectionsPerHost(maxConPerHost);
        params.setConnectionTimeout(conTimeOutMs);
        params.setSoTimeout(soTimeOutMs);
        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        client = new org.apache.commons.httpclient.HttpClient(clientParams, connectionManager);
        if (proxyHost != null && !proxyHost.equals("")) {
            client.getHostConfiguration().setProxy(proxyHost, proxyPort);
            client.getParams().setAuthenticationPreemptive(true);
            if (proxyAuthUser != null && !proxyAuthUser.equals("")) {
                client.getState().setProxyCredentials(
                        AuthScope.ANY,
                        new UsernamePasswordCredentials(proxyAuthUser,
                                proxyAuthPassword));
            }
        }
    }

    /*
     * 对parameters进行encode处理
     */
    /*private static String encodeParameters(PostParameter[] postParams) {
        StringBuilder buf = new StringBuilder();
        for (int j = 0; j < postParams.length; j++) {
            if (j != 0) {
                buf.append("&");
            }
            try {
                buf.append(URLEncoder.encode(postParams[j].getName(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(postParams[j].getValue(),
                                "UTF-8"));
            } catch (java.io.UnsupportedEncodingException ignored) {
            }
        }

        return buf.toString();
    }*/

    /*public Response get(String url, PostParameter[] params) {
        if (null != params && params.length > 0) {
            String encodedParams = HttpClient.encodeParameters(params);
            if (!url.contains("?")) {
                url += "?" + encodedParams;
            } else {
                url += "&" + encodedParams;
            }
        }
        GetMethod getmethod = new GetMethod(url);
        return httpRequest(getmethod);

    }*/

    /**
     * 处理http post请求
     */
    /*public Response post(String url, PostParameter[] params) {
        return post(url, params, false);

    }

    public Response post(String url, PostParameter[] params,
                         Boolean WithTokenHeader) {
        PostMethod postMethod = new PostMethod(url);
        for (PostParameter param1 : params) {
            postMethod.addParameter(param1.getName(), param1.getValue());
        }
        HttpMethodParams param = postMethod.getParams();
        param.setContentCharset("UTF-8");
        if (WithTokenHeader) {
            return httpRequest(postMethod);
        } else {
            return httpRequest(postMethod, WithTokenHeader);
        }
    }

    public Response httpRequest(HttpMethod method) {
        //我们的后台不支持 authorization header
        return httpRequest(method, false);
    }

    public Response httpRequest(HttpMethod method, Boolean WithTokenHeader) {
        InetAddress ipaddr;
        int responseCode;
        try {
            ipaddr = InetAddress.getLocalHost();
            List<Header> headers = new ArrayList<Header>();
            if (WithTokenHeader) {
                //TODO hardcode for authorization header
                headers.add(new Header("Authorization", "OAuth2 " + ""));
                headers.add(new Header("API-RemoteIP", ipaddr.getHostAddress()));
                client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
            }

            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(3, false));
            client.executeMethod(method);
            //Header[] resHeader = method.getResponseHeaders();
            responseCode = method.getStatusCode();

            Response response = new Response();

            response.setResponseAsString(new String(method.getResponseBody(), "utf-8"));

            if (responseCode != OK) {
                return null;
            }
            return response;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return null;
    }*/

    private static String getCause(int statusCode) {
        String cause = null;
        switch (statusCode) {
            case NOT_MODIFIED:
                break;
            case BAD_REQUEST:
                cause = "请求无效";
                break;
            case NOT_AUTHORIZED:
                cause = "未获得授权";
                break;
            case FORBIDDEN:
                cause = "无权限访问当前资源";
                break;
            case NOT_FOUND:
                cause = "资源不存在";
                break;
            case NOT_ACCEPTABLE:
                cause = "请检查请求参数";
                break;
            case INTERNAL_SERVER_ERROR:
                cause = "服务器出错了";
                break;
            case BAD_GATEWAY:
                cause = "服务器出错了";
                break;
            case SERVICE_UNAVAILABLE:
                cause = "当前访问量过大，请稍后重试";
                break;
            default:
                cause = "";
        }
        return statusCode + ":" + cause;
    }
}
