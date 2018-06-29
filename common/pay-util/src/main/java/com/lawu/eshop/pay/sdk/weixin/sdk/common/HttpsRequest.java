package com.lawu.eshop.pay.sdk.weixin.sdk.common;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.WxResponseData;
import com.lawu.eshop.pay.sdk.weixin.sdk.service.IServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月13日 下午1:45:36
 */
public class HttpsRequest implements IServiceRequest {

    private static Logger logger = LoggerFactory.getLogger(HttpsRequest.class);

    public interface ResultListener {

        public void onConnectionPoolTimeoutError();

    }

//	private static Log log = new Log(LoggerFactory.getLogger(HttpsRequest.class));

    // 表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    // 连接超时时间，默认10秒
    private int socketTimeout = 10000;

    // 传输超时时间，默认30秒
    private int connectTimeout = 30000;

    // 请求器的配置
    private RequestConfig requestConfig;

    // HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpsRequest() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, IOException {
        //init();
    }

    private void init(WxPayConfigParam wxPayConfigParam) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyManagementException {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        Resource resource = new ClassPathResource(wxPayConfigParam.getWxpayCertBasePath());
//        FileInputStream instream = new FileInputStream(new File("F:\\1\\workspace\\lawu-server\\server\\service-share\\property-srv\\src\\main\\resources\\apiclient_cert-member.p12"));// 加载本地的证书进行https加密传输
        try {
            keyStore.load(resource.getInputStream(), wxPayConfigParam.getWxpayCertPasswordMember().toCharArray());// 设置证书密码
//            keyStore.load(instream, wxPayConfigParam.getWxpayCertPasswordMember().toCharArray());// 设置证书密码
        } catch (CertificateException e) {
            logger.error("", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("", e);
        } finally {
//            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, wxPayConfigParam.getWxpayCertPasswordMember().toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();

        hasInit = true;
    }

    /**
     * 通过Https往API post xml数据
     *
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */

    public String sendPost(String url, Object xmlObj, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws IOException, KeyStoreException,
            UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

        if (!hasInit) {
            init(wxPayConfigParam);
        }

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        // 解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        // 将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        postDataXML = postDataXML.replaceAll("com.lawu.eshop.pay.sdk.weixin.sdk.protocol.RefundReqData", "xml");
        postDataXML = postDataXML.replaceAll("com.lawu.eshop.pay.sdk.weixin.sdk.protocol.MmpaymkttransfersReqData", "xml");

        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        // 设置请求器的配置
        httpPost.setConfig(requestConfig);

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
            WxResponseData wxResponseData = (WxResponseData) Util.getObjectFromXML(result, WxResponseData.class);
            String consoleMsg = wxPayConfigParam.getBizTypeEnum().getName() + "成功";
            if ("FAIL".equals(wxResponseData.getReturn_code())) {
                consoleMsg = wxPayConfigParam.getBizTypeEnum().getName() + "API系统返回失败，" + wxResponseData.getReturn_msg();
                jsonResult.setSuccess(false);
            } else {
                if ("FAIL".equals(wxResponseData.getResult_code())) {
                    consoleMsg = wxPayConfigParam.getBizTypeEnum().getName() + "异常，错误码：" + wxResponseData.getErr_code() + "，错误信息：" + wxResponseData.getErr_code_des();
                    jsonResult.setSuccess(false);
                } else {
                    jsonResult.setSuccess(true);
                    jsonResult.setData(wxResponseData);
                }
            }
            jsonResult.setMessage(consoleMsg);
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
            jsonResult.setSuccess(false);
        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException", e);
            jsonResult.setSuccess(false);
        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException", e);
            jsonResult.setSuccess(false);
        } catch (Exception e) {
            logger.error("http get throw Exception", e);
            jsonResult.setSuccess(false);
        } finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig() {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig 设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
