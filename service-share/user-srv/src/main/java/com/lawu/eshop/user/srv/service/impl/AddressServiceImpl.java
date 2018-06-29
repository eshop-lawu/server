package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.eshop.user.param.OperatorAddressParam;
import com.lawu.eshop.user.srv.bo.AddressBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.converter.AddressConverter;
import com.lawu.eshop.user.srv.domain.AddressDO;
import com.lawu.eshop.user.srv.domain.AddressDOExample;
import com.lawu.eshop.user.srv.domain.AddressDOExample.Criteria;
import com.lawu.eshop.user.srv.mapper.AddressDOMapper;
import com.lawu.eshop.user.srv.service.AddressService;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.MerchantService;
import com.lawu.framework.core.page.Page;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDOMapper addressDOMapper;
	
	@Autowired 
	private MemberService memberService;
	
	@Autowired 
	private MerchantService merchantService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer update(AddressParam address, Long id, String userNum) {
		AddressDO addressDO = AddressConverter.convertDO(address);
		addressDO.setGmtModified(new Date());

		AddressDOExample example = new AddressDOExample();
		example.createCriteria().andIdEqualTo(id).andUserNumEqualTo(userNum);
		Integer i = addressDOMapper.updateByExampleSelective(addressDO, example);
		return i;
	}

	@Override
	public AddressBO get(Long id) {
		AddressDO address = addressDOMapper.selectByPrimaryKey(id);
		return AddressConverter.convertBO(address);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer remove(Long id) {
		AddressDOExample example = new AddressDOExample();
		example.createCriteria().andIdEqualTo(id);
		AddressDO address = new AddressDO();
		address.setStatus(new Byte("0"));
		Integer i = addressDOMapper.updateByExampleSelective(address, example);
		return i;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateDefault(Long id, String userNum) {
		
		AddressDO addressDO = new AddressDO();
		AddressDOExample example = new AddressDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
		addressDOMapper.countByExample(example);
		addressDO.setIsDefault(false);
		addressDO.setGmtModified(new Date());
		addressDOMapper.updateByExampleSelective(addressDO, example);
		addressDO.setIsDefault(true);
		AddressDOExample example2 = new AddressDOExample();
		example2.createCriteria().andIdEqualTo(id);
		Integer i = addressDOMapper.updateByExampleSelective(addressDO, example2);
		return i;

	}

	/**
	 * 根据用户编号 查询用户所有地址
	 * 
	 * @param userNum
	 *            用户编号
	 * @return
	 * @author Sunny
	 */
	@Override
	public List<AddressBO> selectByUserNum(String userNum) {
		AddressDOExample example = new AddressDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(StatusEnum.VALID.getValue());
		example.setOrderByClause("is_default desc");

		List<AddressDO> list = addressDOMapper.selectByExample(example);

		return AddressConverter.convertListBOS(list);
	}

	/**
	 * 根据用户编号 添加收货地址
	 * 
	 * @param userNum
	 *            用户编号
	 * @param param
	 *            保存地址参数
	 * @author Sunny
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveWithUserNum(String userNum, AddressParam param) {

		AddressDO addressDO = AddressConverter.convertDO(param);

		addressDO.setUserNum(userNum);
		addressDO.setStatus(StatusEnum.VALID.getValue());
		addressDO.setMobile(param.getMobile());
		addressDO.setGmtCreate(new Date());
		addressDO.setGmtModified(new Date());

		AddressDOExample example = new AddressDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(new Byte("1"));
		Long count = addressDOMapper.countByExample(example);
		if (count == 0) {
			addressDO.setIsDefault(true);
		}else{
			addressDO.setIsDefault(false);
		}
		int result = addressDOMapper.insertSelective(addressDO);

		if (result <= 0) {
			return ResultCode.SAVE_FAIL;
		}

		return ResultCode.SUCCESS;
	}

	@Override
	public boolean isCheckAddress(Long id, String userNum) {
		AddressDOExample example = new AddressDOExample();
		example.createCriteria().andIdEqualTo(id).andUserNumEqualTo(userNum);
		Long count = addressDOMapper.countByExample(example);
		return count.intValue() > 0;
	}

	@Override
	public Page<AddressBO> operatorAddressPage(OperatorAddressParam param) {
		AddressDOExample example = new AddressDOExample();
		Criteria criteria  = example.createCriteria();
		criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
		if(StringUtils.isNotBlank(param.getAccount())){
			if(param.getUserType() == UserTypeEnum.MEMBER){
				MemberBO member = memberService.getMemberByAccount(param.getAccount());
				if(member !=null){
					criteria.andUserNumEqualTo(member.getNum());
				}
			}else{
				MerchantBO merchant = merchantService.getMerchantByAccount(param.getAccount());
				if(merchant !=null){
					criteria.andUserNumEqualTo(merchant.getNum());
				}
			}
			
		}
		if(StringUtils.isNotBlank(param.getMobile())){
			criteria.andMobileEqualTo(param.getMobile());
		}
		
		if(StringUtils.isNotBlank(param.getName())){
			criteria.andNameEqualTo(param.getName());
		}
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		
		List<AddressDO> list = addressDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		Page<AddressBO> page = new Page<>();
		page.setRecords(AddressConverter.convertListBOS(list));
		page.setCurrentPage(param.getCurrentPage());
		page.setTotalCount((int)addressDOMapper.countByExample(example));
		
		return page;
	}

}
