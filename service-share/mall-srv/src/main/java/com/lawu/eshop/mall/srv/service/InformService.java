/**
 * 
 */
package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.param.InformQueryParam;
import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.mall.srv.bo.InformBO;
import com.lawu.eshop.mall.srv.bo.InformEditBO;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月27日
 */
public interface InformService {

	/**
	 * @param param
	 * @return
	 */
	Integer addInform(InformSaveParam param);

	/**
	 * @param param
	 * @return
	 */
	Page<InformBO> selectInformList(InformQueryParam param);

	/**
	 * @param edit
	 * @return
	 */
	void editInform(InformEditBO edit);


}
