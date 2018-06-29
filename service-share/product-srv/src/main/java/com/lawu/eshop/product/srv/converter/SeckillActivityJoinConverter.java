package com.lawu.eshop.product.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.constant.SeckillActivityProductEnum;
import com.lawu.eshop.product.dto.SeckillActivityDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityJoinDTO;
import com.lawu.eshop.product.dto.SeckillActivityManageDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityManagerDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductManageDTO;
import com.lawu.eshop.product.srv.bo.SeckillActivityDetailBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityJoinBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageDetailBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductManageBO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.domain.extend.SeckillActivityDOView;
import com.lawu.utils.DateUtil;

/**
 * 参加活动转换类、
 * 
 * @author zhangrc
 * @date 2017/11/23
 */
public class SeckillActivityJoinConverter {
	
	
	public static List<SeckillActivityJoinBO> seckillActivityJoinBOConverter(List<SeckillActivityDO> list){
		List<SeckillActivityJoinBO>  joinList = new ArrayList<>();
		if(list.isEmpty()){
			return joinList;
		}
		
		for (SeckillActivityDO SeckillActivityDO : list) {
			SeckillActivityJoinBO seckillActivityJoinBO = new SeckillActivityJoinBO();
			seckillActivityJoinBO.setId(SeckillActivityDO.getId());
			seckillActivityJoinBO.setName(SeckillActivityDO.getName());
			seckillActivityJoinBO.setPicture(SeckillActivityDO.getPicture());
			seckillActivityJoinBO.setSellingPrice(SeckillActivityDO.getSellingPrice());
			seckillActivityJoinBO.setStartDate(SeckillActivityDO.getStartDate());
			seckillActivityJoinBO.setEndDate(SeckillActivityDO.getEndDate());
			joinList.add(seckillActivityJoinBO);
		}
		
		return joinList;
		
	}
	
	public static List<SeckillActivityJoinDTO> seckillActivityJoinDTOConverter(List<SeckillActivityJoinBO> list){
		List<SeckillActivityJoinDTO>  joinList = new ArrayList<>();
		if(list.isEmpty()){
			return joinList;
		}
		
		for (SeckillActivityJoinBO seckillActivityBO : list) {
			SeckillActivityJoinDTO seckillActivityJoinDTO = new SeckillActivityJoinDTO();
			seckillActivityJoinDTO.setId(seckillActivityBO.getId());
			seckillActivityJoinDTO.setName(seckillActivityBO.getName());
			seckillActivityJoinDTO.setPicture(seckillActivityBO.getPicture());
			seckillActivityJoinDTO.setSellingPrice(seckillActivityBO.getSellingPrice());
			seckillActivityJoinDTO.setStartDate(seckillActivityBO.getStartDate());
			seckillActivityJoinDTO.setEndDate(seckillActivityBO.getEndDate());
			joinList.add(seckillActivityJoinDTO);
		}
		
		return joinList;
		
	}

	public static List<SeckillActivityManageBO> seckillActivityJoinManageBOConverter(List<SeckillActivityDOView> list) {
		List<SeckillActivityManageBO>  joinList = new ArrayList<>();
		if(list.isEmpty()){
			return joinList;
		}
		
		for (SeckillActivityDOView seckillActivityDOView : list) {
			SeckillActivityManageBO seckillActivityManageBO = new SeckillActivityManageBO();
			seckillActivityManageBO.setId(seckillActivityDOView.getId());
			seckillActivityManageBO.setName(seckillActivityDOView.getName());
			seckillActivityManageBO.setPicture(seckillActivityDOView.getPicture());
			seckillActivityManageBO.setSellingPrice(seckillActivityDOView.getSellingPrice());
			seckillActivityManageBO.setStartDate(seckillActivityDOView.getStartDate());
			seckillActivityManageBO.setEndDate(seckillActivityDOView.getEndDate());
			seckillActivityManageBO.setActivityStatusEnum(ActivityStatusEnum.getEnum(seckillActivityDOView.getActivityStatus()));
			joinList.add(seckillActivityManageBO);
		}
		
		return joinList;
	}
	
	
	public static List<SeckillActivityManagerDTO> seckillActivityJoinManageDTOConverter(List<SeckillActivityManageBO> list) {
		List<SeckillActivityManagerDTO>  joinList = new ArrayList<>();
		if(list.isEmpty()){
			return joinList;
		}
		
		for (SeckillActivityManageBO seckillActivityManageBO : list) {
			SeckillActivityManagerDTO seckillActivityManagerDTO = new SeckillActivityManagerDTO();
			seckillActivityManagerDTO.setId(seckillActivityManageBO.getId());
			seckillActivityManagerDTO.setName(seckillActivityManageBO.getName());
			seckillActivityManagerDTO.setPicture(seckillActivityManageBO.getPicture());
			seckillActivityManagerDTO.setSellingPrice(seckillActivityManageBO.getSellingPrice());
			seckillActivityManagerDTO.setStartDate(seckillActivityManageBO.getStartDate());
			seckillActivityManagerDTO.setEndDate(seckillActivityManageBO.getEndDate());
			seckillActivityManagerDTO.setActivityStatusEnum(seckillActivityManageBO.getActivityStatusEnum());
			joinList.add(seckillActivityManagerDTO);
		}
		
		return joinList;
	}
	
	
	public static SeckillActivityDetailBO seckillActivityJoinDetailBOConverter(SeckillActivityDO seckillActivityDO) {
		SeckillActivityDetailBO  seckillActivityDetailBO = new  SeckillActivityDetailBO();
		if(seckillActivityDO==null){
			return seckillActivityDetailBO;
		}
		
		seckillActivityDetailBO.setId(seckillActivityDO.getId());
		seckillActivityDetailBO.setName(seckillActivityDO.getName());
		seckillActivityDetailBO.setPicture(seckillActivityDO.getPicture());
		seckillActivityDetailBO.setSellingPrice(seckillActivityDO.getSellingPrice());
		seckillActivityDetailBO.setStartDate(seckillActivityDO.getStartDate());
		seckillActivityDetailBO.setEndDate(seckillActivityDO.getEndDate());
		seckillActivityDetailBO.setAttentEndDate(seckillActivityDO.getAttentEndDate());
		seckillActivityDetailBO.setMemberLevelEnum(MemberGradeEnum.getEnum(seckillActivityDO.getMemberLevel()));
		seckillActivityDetailBO.setProductValidCount(seckillActivityDO.getProductValidCount());
		seckillActivityDetailBO.setRemark(seckillActivityDO.getRemark());
		return seckillActivityDetailBO;
	}
	
	
	public static SeckillActivityDetailDTO seckillActivityJoinDetailDTOConverter(SeckillActivityDetailBO seckillActivityDetailBO) {
		SeckillActivityDetailDTO  seckillActivityDetailDTO = new  SeckillActivityDetailDTO();
		if(seckillActivityDetailBO==null){
			return seckillActivityDetailDTO;
		}
		
		seckillActivityDetailDTO.setId(seckillActivityDetailBO.getId());
		seckillActivityDetailDTO.setName(seckillActivityDetailBO.getName());
		seckillActivityDetailDTO.setPicture(seckillActivityDetailBO.getPicture());
		seckillActivityDetailDTO.setSellingPrice(seckillActivityDetailBO.getSellingPrice());
		seckillActivityDetailDTO.setStartDate(seckillActivityDetailBO.getStartDate());
		seckillActivityDetailDTO.setEndDate(seckillActivityDetailBO.getEndDate());
		seckillActivityDetailDTO.setMemberLevelEnum(seckillActivityDetailBO.getMemberLevelEnum());
		seckillActivityDetailDTO.setProductValidCount(seckillActivityDetailBO.getProductValidCount());
		seckillActivityDetailDTO.setJoinCount(seckillActivityDetailBO.getJoinCount());
		seckillActivityDetailDTO.setRemark(seckillActivityDetailBO.getRemark());
		
		Date newTime = seckillActivityDetailBO.getAttentEndDate();
		Long after = newTime.getTime();
		Long before = new Date().getTime();
		if (after - before > 0) {
			seckillActivityDetailDTO.setCountDown(after - before);
		} else {
			seckillActivityDetailDTO.setCountDown(0l);
		}
		return seckillActivityDetailDTO;
	}
	

	public static SeckillActivityManageDetailBO seckillActivityManageDetailBOConverter(SeckillActivityDO seckillActivityDO) {
		SeckillActivityManageDetailBO  seckillActivityDetailBO = new  SeckillActivityManageDetailBO();
		if(seckillActivityDO==null){
			return seckillActivityDetailBO;
		}
		
		seckillActivityDetailBO.setId(seckillActivityDO.getId());
		seckillActivityDetailBO.setName(seckillActivityDO.getName());
		seckillActivityDetailBO.setPicture(seckillActivityDO.getPicture());
		seckillActivityDetailBO.setSellingPrice(seckillActivityDO.getSellingPrice());
		seckillActivityDetailBO.setActivityStatusEnum(ActivityStatusEnum.getEnum(seckillActivityDO.getActivityStatus()));
		seckillActivityDetailBO.setStartDate(seckillActivityDO.getStartDate());
		seckillActivityDetailBO.setEndDate(seckillActivityDO.getEndDate());
		//未开始
		if(seckillActivityDO.getActivityStatus()==ActivityStatusEnum.NOT_STARTED.getValue() || seckillActivityDO.getActivityStatus()==ActivityStatusEnum.PUBLISHED.getValue()
				|| seckillActivityDO.getActivityStatus()==ActivityStatusEnum.IN_REVIEW.getValue()){
			Date newTime = seckillActivityDO.getStartDate();
			Long after = newTime.getTime();
			Long before = new Date().getTime();
			if (after - before > 0) {
				seckillActivityDetailBO.setCountDown(after - before);
			} else {
				seckillActivityDetailBO.setCountDown(0l);
			}
		//进行中
		}else if(seckillActivityDO.getActivityStatus()==ActivityStatusEnum.PROCESSING.getValue()){
			Date endTime = seckillActivityDO.getEndDate();
			Long after = endTime.getTime();
			Long before = new Date().getTime();
			if (after - before > 0) {
				seckillActivityDetailBO.setCountDown(after - before);
			} else {
				seckillActivityDetailBO.setCountDown(0l);
			}
		}
		return seckillActivityDetailBO;
	}
	
	
	public static SeckillActivityManageDetailDTO seckillActivityManageDetailDTOConverter(SeckillActivityManageDetailBO seckillActivityManageDetailBO) {
		SeckillActivityManageDetailDTO  seckillActivityManageDetailDTO = new  SeckillActivityManageDetailDTO();
		if(seckillActivityManageDetailBO==null){
			return seckillActivityManageDetailDTO;
		}
		
		seckillActivityManageDetailDTO.setId(seckillActivityManageDetailBO.getId());
		seckillActivityManageDetailDTO.setName(seckillActivityManageDetailBO.getName());
		seckillActivityManageDetailDTO.setPicture(seckillActivityManageDetailBO.getPicture());
		seckillActivityManageDetailDTO.setSellingPrice(seckillActivityManageDetailBO.getSellingPrice());
		seckillActivityManageDetailDTO.setActivityStatusEnum(seckillActivityManageDetailBO.getActivityStatusEnum());
		seckillActivityManageDetailDTO.setEndDate(seckillActivityManageDetailBO.getEndDate());
		seckillActivityManageDetailDTO.setCountDown(seckillActivityManageDetailBO.getCountDown());
		seckillActivityManageDetailDTO.setStartDate(seckillActivityManageDetailBO.getStartDate());
		
		return seckillActivityManageDetailDTO;
	}
	
	
	public static List<SeckillActivityProductManageBO> seckillActivityProductManageBOConverter(List<SeckillActivityProductDO> list,BigDecimal moeney) {
		List<SeckillActivityProductManageBO>  productList = new ArrayList<>();
		if(list.isEmpty()){
			return productList;
		}
		
		for (SeckillActivityProductDO seckillActivityProductDO : list) {
			SeckillActivityProductManageBO seckillActivityProductManageBO = new SeckillActivityProductManageBO();
			seckillActivityProductManageBO.setModelCount(seckillActivityProductDO.getProductModelCount());
			seckillActivityProductManageBO.setProductId(seckillActivityProductDO.getProductId());
			seckillActivityProductManageBO.setProductPicture(seckillActivityProductDO.getProductPicture());
			seckillActivityProductManageBO.setProductName(seckillActivityProductDO.getProductName());
			if(seckillActivityProductDO.getSaleMoney() !=null){
				seckillActivityProductManageBO.setSaleMoney(BigDecimal.valueOf(seckillActivityProductDO.getProductModelCount()-seckillActivityProductDO.getLeftCount()).multiply(seckillActivityProductDO.getSaleMoney()));
			}else{
				seckillActivityProductManageBO.setSaleMoney(BigDecimal.valueOf(seckillActivityProductDO.getProductModelCount()-seckillActivityProductDO.getLeftCount()).multiply(moeney));
			}
			seckillActivityProductManageBO.setSaleCount(seckillActivityProductDO.getProductModelCount()-seckillActivityProductDO.getLeftCount());
			seckillActivityProductManageBO.setStatusEnum(SeckillActivityProductEnum.getEnum(seckillActivityProductDO.getStatus()));
			seckillActivityProductManageBO.setReasons(seckillActivityProductDO.getReasons());
			productList.add(seckillActivityProductManageBO);
		}
		
		return productList;
	}
	
	public static List<SeckillActivityProductManageDTO> seckillActivityProductManageDTOConverter(List<SeckillActivityProductManageBO> list) {
		List<SeckillActivityProductManageDTO>  productList = new ArrayList<>();
		if(list.isEmpty()){
			return productList;
		}
		
		for (SeckillActivityProductManageBO seckillActivityProductManageBO : list) {
			SeckillActivityProductManageDTO seckillActivityProductManageDTO = new SeckillActivityProductManageDTO();
			seckillActivityProductManageDTO.setModelCount(seckillActivityProductManageBO.getModelCount());
			seckillActivityProductManageDTO.setProductId(seckillActivityProductManageBO.getProductId());
			seckillActivityProductManageDTO.setProductPicture(seckillActivityProductManageBO.getProductPicture());
			seckillActivityProductManageDTO.setProductName(seckillActivityProductManageBO.getProductName());
			seckillActivityProductManageDTO.setSaleMoney(seckillActivityProductManageBO.getSaleMoney());
			seckillActivityProductManageDTO.setSaleCount(seckillActivityProductManageBO.getSaleCount());
			seckillActivityProductManageDTO.setReasons(seckillActivityProductManageBO.getReasons());
			seckillActivityProductManageDTO.setStatusEnum(seckillActivityProductManageBO.getStatusEnum());
			productList.add(seckillActivityProductManageDTO);
		}
		
		return productList;
	}
}
