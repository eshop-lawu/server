package com.lawu.eshop.game.robot.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private final static String CHARSET = "UTF-8";

    private static CloseableHttpClient httpClient;

    private static RequestConfig requestConfig;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();

        // 设置请求参数
        requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000).build();
    }

    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, null);
    }

    public static String doPost(String url, Map<String, String> params, Map<String, String> headersMap) {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            pairList.add(pair);
        }
        HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(pairList, CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return doPost(url, entity, headersMap);
    }

    public static String doPost(String url, Object body, Map<String, String> headersMap) {
        HttpEntity entity = new StringEntity(JSON.toJSONString(body), CHARSET);
        return doPost(url, entity, headersMap);
    }

    public static String doPost(String url, HttpEntity entity, Map<String, String> headersMap) {
        String result = null;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            if (headersMap != null) {
                for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                    httpPost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(entity);
            httpPost.setConfig(requestConfig);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    String resBody = entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    HttpClientUtils.closeQuietly(response);

                    if (status >= 200 && status < 300) {
                        return resBody;
                    } else {
                        throw new IOException("Unexpected response status: " + status + ", message: " + resBody);
                    }
                }
            };
            result = httpClient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            logger.error("do post error, url {}, e {}", url, e.getMessage(), e);
        } finally {
            httpPost.reset();
        }
        return result;
    }
    
    public static String doPut(String url, HttpEntity entity, Map<String, String> headersMap) {
        String result = null;
        HttpPut httpPut = null;
        try {
            httpPut = new HttpPut(url);
            if (headersMap != null) {
                for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                    httpPut.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
                }
            }
            httpPut.setEntity(entity);
            httpPut.setConfig(requestConfig);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    String resBody = entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    HttpClientUtils.closeQuietly(response);

                    if (status >= 200 && status < 300) {
                        return resBody;
                    } else {
                        throw new IOException("Unexpected response status: " + status + ", message: " + resBody);
                    }
                }
            };
            result = httpClient.execute(httpPut, responseHandler);
        } catch (Exception e) {
            logger.error("do post error, url {}, e {}", url, e.getMessage(), e);
        } finally {
            httpPut.reset();
        }
        return result;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doGet(String url, Map<String, String> params) {
        String result = null;
        HttpGet httpGet = null;
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                pairList.add(pair);
            }
            String paramStr = URLEncodedUtils.format(pairList, CHARSET);
            if (url.indexOf("?") < 0) {
                url += "?";
            }
            url += paramStr;
        }
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    String resBody = entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    HttpClientUtils.closeQuietly(response);

                    if (status >= 200 && status < 300) {
                        return resBody;
                    } else {
                        throw new IOException("Unexpected response status: " + status + ", message: " + resBody);
                    }
                }
            };
            result = httpClient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            logger.error("do get error, url {}, body {}, e {}", url, params, e.getMessage(), e);
        } finally {
            httpGet.reset();
        }
        return result;
    }
}
