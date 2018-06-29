package com.lawu.eshop.property.srv.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lawu.eshop.property.param.TestQuery1Param;
import com.lawu.eshop.property.param.TestQueryParam;
import com.lawu.eshop.property.srv.bo.PropertyBO;
import com.lawu.eshop.property.srv.bo.QueryPropertyBO;
import com.lawu.framework.core.page.Page;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月5日 下午6:49:51
 *
 */
public interface PropertyService {
   
	String getValue(String key);
	
	List<String> getValues(String key);

    List<PropertyBO> getAll();

    Map<String,BigDecimal> getAdCommissionPropertys();

    Map<String,BigDecimal> getSaleCommissionPropertys();

//	Page<QueryPropertyBO> query(TestQuery1Param param);
//
//	int save(TestQueryParam param);
//
//	int delete(String propertyIds);
//
//	QueryPropertyBO get(Long propertyId);
}
