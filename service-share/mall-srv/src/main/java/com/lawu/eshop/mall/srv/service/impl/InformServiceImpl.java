/**
 * 
 */
package com.lawu.eshop.mall.srv.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.lawu.eshop.mall.param.InformQueryParam;
import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.mall.srv.bo.InformBO;
import com.lawu.eshop.mall.srv.bo.InformEditBO;
import com.lawu.eshop.mall.srv.converter.InformConverter;
import com.lawu.eshop.mall.srv.domain.InformDO;
import com.lawu.eshop.mall.srv.domain.InformDOExample;
import com.lawu.eshop.mall.srv.domain.InformDOExample.Criteria;
import com.lawu.eshop.mall.srv.mapper.InformDOMapper;
import com.lawu.eshop.mall.srv.service.InformService;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月27日
 */
@Service
public class InformServiceImpl implements InformService {

	@Autowired
	private InformDOMapper informDOMapper;

	/**
	 * 保存举报记录
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer addInform(InformSaveParam param) {
		InformDO info = InformConverter.converDO(param);
		Integer row = informDOMapper.insert(info);
		return row;
	}

	/**
	 * 查询举报记录
	 */
	@Override
	public Page<InformBO> selectInformList(InformQueryParam param) {
		InformDOExample example = new InformDOExample();
		Criteria cr = example.createCriteria();
		if (!StringUtils.isEmpty(param.getContent())) {
			cr.andContentLike("%" + param.getContent().trim() + "%");
		}
		if (!StringUtils.isEmpty(param.getInformtItemName())) {

			cr.andInformtItemNameLike("%" + param.getInformtItemName().trim() + "%");
		}
		if (param.getStatus() != null) {
			cr.andStatusEqualTo(param.getStatus().getVal());
		}
		if (param.getInformType() != null) {
			cr.andInformTypeEqualTo(param.getInformType().getVal());
		}
		example.setOrderByClause("gmt_create desc");
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<InformDO> list = informDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<InformBO> listbo = Lists.newArrayList();
		for (InformDO info : list) {
			InformBO bo = InformConverter.converBO(info);
			listbo.add(bo);
		}
		Page<InformBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		page.setTotalCount(informDOMapper.countByExample(example));
		page.setRecords(listbo);
		return page;
	}

	@Override
	public void editInform(InformEditBO edit) {
		InformDO bo =new InformDO();
		bo.setId(edit.getId());
		bo.setStatus(edit.getStatus());
		bo.setAuditorId(edit.getAuditorId());
		bo.setAuditorName(edit.getAuditorName());
		bo.setRemark(edit.getRemark());
		bo.setAuditTime(edit.getAuditTime());
		bo.setGmtModified(edit.getGmtModified());
		informDOMapper.updateByPrimaryKeySelective(bo);
	}

}
