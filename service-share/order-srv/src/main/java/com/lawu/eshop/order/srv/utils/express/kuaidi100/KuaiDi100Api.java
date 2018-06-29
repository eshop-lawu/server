package com.lawu.eshop.order.srv.utils.express.kuaidi100;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.order.srv.utils.express.kuaidi100.config.KuaiDi100Config;
import com.lawu.utils.HttpUtil;

@Component
public class KuaiDi100Api {
	
	private final static String CHARSET = "UTF-8";
	
	@Autowired
	private KuaiDi100Config config;

	/**
	 * 即时查询
	 *
	 * @throws Exception
	 */
	public String orderTraces(String expCode, String expNo) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", config.getTestKey());
		params.put("com", expCode);
		params.put("nu", expNo);
		params.put("show", "0");
		params.put("muti", "1");
		params.put("order", "desc");
		return HttpUtil.doGet(config.getReqUrl(), params);
	}
	
	/**
	 * 即时查询
	 *
	 * @throws Exception
	 */
	public String inquiries(String expCode, String expNo) throws Exception {
		String param = "{\"com\":\"" + expCode + "\",\"num\":\"" + expNo + "\",\"from\":\"\",\"to\":\"\",\"resultv2\":\"1\"}";
		String sign = md5(param + config.getKey() + config.getCustomer());
		Map<String, String> params = new HashMap<String, String>();
		params.put("param", param);
		params.put("sign", sign);
		params.put("customer", config.getCustomer());
		return HttpUtil.doPost(config.getInquiriesUrl(), params);
	}
	
	/**
	 * 单号归属公司智能判断
	 *
	 * @throws Exception
	 */
	public String recognition(String expNo) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("num", expNo);
		params.put("key", config.getKey());
		return HttpUtil.doGet(config.getRecognitionUrl(), params);
	}
	
    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    private String md5(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(CHARSET));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toUpperCase();
    }
}
