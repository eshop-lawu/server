package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.srv.bo.DiscountPackagePurchaseNotesBO;
import com.lawu.eshop.mall.srv.converter.DiscountPackagePurchaseNotesConverter;
import com.lawu.eshop.mall.srv.domain.DiscountPackagePurchaseNotesDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackagePurchaseNotesDOExample;
import com.lawu.eshop.mall.srv.mapper.DiscountPackagePurchaseNotesDOMapper;
import com.lawu.eshop.mall.srv.service.DiscountPackagePurchaseNotesService;

/**
 * 优惠套餐服务接口实现类
 * 
 * @author Sunny
 * @date 2017/06/26
 */
@Service
public class DiscountPackagePurchaseNotesServiceImpl implements DiscountPackagePurchaseNotesService {

	@Autowired
	DiscountPackagePurchaseNotesDOMapper discountPackagePurchaseNotesDOMapper;

	/**
	 * 查询所有的购买须知
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月31日
	 */
	@Override
	public List<DiscountPackagePurchaseNotesBO> list() {
		List<DiscountPackagePurchaseNotesDO> list = discountPackagePurchaseNotesDOMapper.selectByExample(null);
		return DiscountPackagePurchaseNotesConverter.convertDiscountPackagePurchaseNotesBOList(list);
	}

	/**
	 * 根据id字符串查询购买须知
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月31日
	 */
	@Override
	public List<DiscountPackagePurchaseNotesBO> list(String idsStr) {
		DiscountPackagePurchaseNotesDOExample example = new DiscountPackagePurchaseNotesDOExample();
		List<Integer> idList = new ArrayList<>();
		List<String> idStrList = Arrays.asList(StringUtils.split(idsStr, ","));
		for (String idStr : idStrList) {
			idList.add(Integer.valueOf(idStr));
		}
		example.createCriteria().andIdIn(idList);
		List<DiscountPackagePurchaseNotesDO> list = discountPackagePurchaseNotesDOMapper.selectByExample(example);
		return DiscountPackagePurchaseNotesConverter.convertDiscountPackagePurchaseNotesBOList(list);
	}

}
