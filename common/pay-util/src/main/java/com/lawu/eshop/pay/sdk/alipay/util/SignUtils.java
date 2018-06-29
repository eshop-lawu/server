package com.lawu.eshop.pay.sdk.alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawu.eshop.pay.sdk.alipay.sign.Base64;

public class SignUtils {

	private static Logger logger = LoggerFactory.getLogger(SignUtils.class);
			
	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	private static String getAlgorithms(boolean rsa2) {
		return rsa2 ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
	}

	public static String sign(String content, String privateKey, boolean rsa2) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature.getInstance(getAlgorithms(rsa2));

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			logger.error("支付宝签名异常",e);
//			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Map转换String
	 * @param map
	 * @return
	 */
	public static String buildMapToString(SortedMap<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }
        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));
        return sb.toString();
    }

    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        try {
            sb.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
        	logger.error("支付宝封装参数异常",e);
            sb.append(value);
        }
        return sb.toString();
    }
	
	public static String getSign(SortedMap<String, String> map, String rsaKey, boolean rsa2) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

//        logger.error("待签名字符串：{}" , authInfo.toString());
//        logger.error("私钥：{}" , rsaKey);
        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
//        logger.error("sign：{}" , oriSign);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	logger.error("支付宝获取签名异常",e);
//            e.printStackTrace();
        }
        return "sign=" + encodedSign;
    }

}
