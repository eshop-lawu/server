package com.lawu.eshop.ad.srv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;
import com.lawu.eshop.ad.srv.bo.MemberAdRecodeCommissionBO;
import com.lawu.eshop.ad.srv.service.MemberAdRecordService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.BaseController;
import com.lawu.utils.BeanUtil;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月24日 下午5:55:03
 *
 */
@RestController
@RequestMapping(value = "commission/")
public class ClickAdCommissionController extends BaseController{
	
	@Resource
	private MemberAdRecordService memberAdRecordService;
	
	
	/**
	 * 查询未计算提成的用户点击广告记录
	 * @return
	 * @throws Exception 
	 * @author yangqh
	 */
	@RequestMapping(value = "getNoneCommissionAds", method = RequestMethod.GET)
    public List<MemberAdRecodeCommissionDTO> getNoneCommissionAds(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize ) {
		List<MemberAdRecodeCommissionBO> bos = memberAdRecordService.getNoneCommissionAds(offset,pageSize);
		List<MemberAdRecodeCommissionDTO> dtos = new ArrayList<MemberAdRecodeCommissionDTO>();
		for(MemberAdRecodeCommissionBO bo : bos){
			MemberAdRecodeCommissionDTO dto = new MemberAdRecodeCommissionDTO();
			dto.setStatus(bo.getStatus());
			dto.setAdId(bo.getId());
			dto.setId(bo.getId());
			dto.setMemberNum(bo.getMemberNum());
			dto.setPoint(bo.getPoint());
			dtos.add(dto);
		}
		return dtos;
    }
	
	/**
	 * 修改用户点击广告记录表状态为已计算提成
	 * @param id
	 * @return
	 * @author yangqh
	 */
	@RequestMapping(value = "updateMemberAdRecardStatus", method = RequestMethod.POST)
    public int updateMemberAdRecardStatus(@RequestParam Long id) {
		if(id == null){
			return ResultCode.ID_EMPTY;
		}
		return memberAdRecordService.updateMemberAdRecardStatus(id);
    }

}
