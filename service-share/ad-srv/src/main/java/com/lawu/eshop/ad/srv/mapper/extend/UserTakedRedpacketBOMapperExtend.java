/**
 * 
 */
package com.lawu.eshop.ad.srv.mapper.extend;

import com.lawu.eshop.ad.srv.domain.extend.UserRedpacketMaxMoney;

/**
 * @author lihj
 * @date 2017年8月7日
 */
public interface UserTakedRedpacketBOMapperExtend {

	UserRedpacketMaxMoney getMaxMoney(Long userRedId);
	
	UserRedpacketMaxMoney getSumMoney(Long userRedId);
}
