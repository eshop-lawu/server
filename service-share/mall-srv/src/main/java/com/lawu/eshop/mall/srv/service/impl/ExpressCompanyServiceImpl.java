package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.srv.bo.ExpressCompanyBO;
import com.lawu.eshop.mall.srv.converter.ExpressCompanyConverter;
import com.lawu.eshop.mall.srv.domain.ExpressCompanyDO;
import com.lawu.eshop.mall.srv.domain.ExpressCompanyDOExample;
import com.lawu.eshop.mall.srv.mapper.ExpressCompanyDOMapper;
import com.lawu.eshop.mall.srv.service.ExpressCompanyService;

/**
 * 
 * @author Sunny
 * @date 2017/3/27
 */
@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

	@Autowired
	ExpressCompanyDOMapper expressCompanyDOMapper;

	/**
	 * 查询全部快递公司，根据ordinal排序
	 *
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@Override
	public List<ExpressCompanyBO> list() {
		return list(null);
	}

	/**
	 * 查询快递公司，根据ordinal排序
	 *
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@Override
	public List<ExpressCompanyBO> list(Boolean isShow) {
		ExpressCompanyDOExample example = new ExpressCompanyDOExample();
		ExpressCompanyDOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
		if (isShow != null) {
			criteria.andIsShowEqualTo(isShow);
		}
		example.setOrderByClause("ordinal ASC");
		return ExpressCompanyConverter.convertBOS(expressCompanyDOMapper.selectByExample(example));
	}
	
	/**
	 * 根据快递公司id查询快递公司
	 * 
	 * @param id
	 *            快递公司id
	 * @return
	 */
	@Override
	public ExpressCompanyBO get(Integer id) {
		return ExpressCompanyConverter.convert(expressCompanyDOMapper.selectByPrimaryKey(id));
	}

	/**
	 * 根据第三方快递公司编号查询快递公司
	 * 
	 * @param code 快递公司编号
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@Override
	public ExpressCompanyBO code(String code) {
		ExpressCompanyBO rtn = null;
		ExpressCompanyDOExample example = new ExpressCompanyDOExample();
		ExpressCompanyDOExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<ExpressCompanyDO> expressCompanyDOList = expressCompanyDOMapper.selectByExample(example);
		if (expressCompanyDOList == null || expressCompanyDOList.isEmpty()) {
			return rtn;
		}
		rtn = ExpressCompanyConverter.convert(expressCompanyDOList.get(0));
		return rtn;
	}

	/**
	 * 根据第三方快递公司编号集合查询快递公司
	 * 
	 * @param codeList 快递公司编号集合
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@Override
	public List<ExpressCompanyBO> codeList(List<String> codeList) {
		ExpressCompanyDOExample example = new ExpressCompanyDOExample();
		ExpressCompanyDOExample.Criteria criteria = example.createCriteria();
		criteria.andCodeIn(codeList);
		List<ExpressCompanyDO> expressCompanyDOList = expressCompanyDOMapper.selectByExample(example);
		// 保证list的顺序与code一致
		Map<String, ExpressCompanyDO> expressCompanyDOMap = new HashMap<String, ExpressCompanyDO>();
		for (ExpressCompanyDO expressCompanyDO : expressCompanyDOList) {
			expressCompanyDOMap.put(expressCompanyDO.getCode(), expressCompanyDO);
		}
		List<ExpressCompanyDO> list = new ArrayList<>();
		for (String code : codeList) {
			list.add(expressCompanyDOMap.get(code));
		}
		return ExpressCompanyConverter.convertBOS(list);
	}

}
