package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.srv.bo.BankBO;

/**
 * 银行数据
 * @author zhangrc
 * @date 2017/03/29
 *
 */
public interface BankService {
	
	/**
	 * 查询所有银行
	 * @return
	 */
	List<BankBO> findBank();

}
