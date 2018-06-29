package com.lawu.eshop.order.srv.utils.express.kdniao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.order.srv.utils.express.kdniao.config.KdniaoConfig;
import com.lawu.utils.HttpUtil;

@Component
public class KdniaoTrackQueryAPI {
	
	@Autowired
	private KdniaoConfig config;
	
	/**
     * 即时查询
     *
     * @throws Exception
     */
    public String orderTraces(String expCode, String expNo) throws Exception {
        String requestData = "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";
        return send(requestData, "1002");
    }
    
    /**
     * 单号识别
	 * @throws Exception 
     */
	public String recognition(String expNo) throws Exception{
		String requestData= "{'LogisticCode':'" + expNo + "'}";
		return send(requestData, "2002");
	}
	
	 /**
     * 组装请求参数
	 * @throws Exception 
     */
	public String send(String requestData, String requestType) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", config.getEBusinessId());
		params.put("RequestType", requestType);
		String dataSign=encrypt(requestData, config.getAppKey(), "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		return sendPost(config.getReqUrl(), params);
	}
    
    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    private String md5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = base64Encode(str.getBytes(charset));
        return encoded;
    }

    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     *
     * @param content  内容
     * @param keyValue Appkey
     * @param charset  编码方式
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    private String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(md5(content + keyValue, charset), charset);
        }
        return base64(md5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求的参数集合
     * @return 远程资源的响应结果
     */
    private String sendPost(String url, Map<String, String> params) {
    	Map<String, String> headersMap = new HashMap<>();
    	headersMap.put("accept", "*/*");
    	headersMap.put("connection", "Keep-Alive");
    	headersMap.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    	headersMap.put("Content-Type", "application/x-www-form-urlencoded");
    	return HttpUtil.doPost(url, params, headersMap);
    }


    private static char[] base64EncodeChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    public static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }
}
