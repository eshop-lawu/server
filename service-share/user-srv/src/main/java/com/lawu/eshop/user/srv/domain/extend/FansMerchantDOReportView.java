package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午3:18:29
 *
 */
public class FansMerchantDOReportView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String keyTxt;

    private String num;

	public String getKeyTxt() {
		return keyTxt;
	}

	public void setKeyTxt(String keyTxt) {
		this.keyTxt = keyTxt;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

   
}
