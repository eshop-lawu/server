package com.lawu.eshop.pay.sdk.alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2018/2/1 19:09
 */
public class ParamUtil {

    public static void parseAlipayResMap(Map<String, String> params, Map<String, String[]> requestParams) {
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
    }

    public static SortedMap<String, String> buildAuthInfoMap(String pid, String app_id, String target_id, String signType) {
        SortedMap<String,String> keyValues = new TreeMap<String,String>(); // SortedMap接收TreeMap的实例
        // 商户签约拿到的app_id，如：2013081700024223
        keyValues.put("app_id", app_id);
        // 商户签约拿到的pid，如：2088102123816631
        keyValues.put("pid", pid);
        // 服务接口名称， 固定值
        keyValues.put("apiname", "com.alipay.account.auth");
        // 商户类型标识， 固定值
        keyValues.put("app_name", "mc");
        // 业务类型， 固定值
        keyValues.put("biz_type", "openservice");
        // 产品码， 固定值
        keyValues.put("product_id", "APP_FAST_LOGIN");
        // 授权范围， 固定值
        keyValues.put("scope", "kuaijie");
        // 商户唯一标识，如：kkkkk091125
        keyValues.put("target_id", target_id);
        // 授权类型， 固定值
        keyValues.put("auth_type", "AUTHACCOUNT");
        // 签名类型
        keyValues.put("sign_type", signType);
        return keyValues;
    }

    public static String buildOrderParam(SortedMap<String, String> map) {
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

    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }
}
