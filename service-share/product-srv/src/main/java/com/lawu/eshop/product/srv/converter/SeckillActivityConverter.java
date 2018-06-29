package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.dto.SeckillActivityDetailsDTO;
import com.lawu.eshop.product.dto.SeckillActivityInfoDTO;
import com.lawu.eshop.product.dto.SeckillActivityThatDayDTO;
import com.lawu.eshop.product.srv.bo.SeckillActivityBO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;

/**
 * 抢购活动转换类
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public class SeckillActivityConverter {
	
    /**
     * SeckillActivityDO List转SeckillActivityBO List
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
	public static List<SeckillActivityBO> convertSeckillActivityBOList(List<SeckillActivityDO> list) {
	    if (list == null || list.isEmpty()) {
	        return null;
	    }
	    List<SeckillActivityBO> rtn = new ArrayList<>();
	    for (SeckillActivityDO item : list) {
	        rtn.add(convert(item));
	    }
        return rtn;
    }
	
    /**
     * SeckillActivityDO转SeckillActivityBO
     * @param source
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    public static SeckillActivityBO convert(SeckillActivityDO source) {
        if (source == null) {
            return null;
        }
        SeckillActivityBO rtn = new SeckillActivityBO();
        rtn.setActivityStatus(ActivityStatusEnum.getEnum(source.getActivityStatus()));
        rtn.setId(source.getId());
        rtn.setEndDate(source.getEndDate());
        rtn.setAttentEndDate(source.getAttentEndDate());
        rtn.setMemberLevel(MemberGradeEnum.getEnum(source.getMemberLevel()));
        rtn.setName(source.getName());
        rtn.setPicture(source.getPicture());
        rtn.setHomePicture(source.getHomePicture());
        rtn.setProductValidCount(source.getProductValidCount());
        rtn.setSellingPrice(source.getSellingPrice());
        rtn.setStartDate(source.getStartDate());
        rtn.setRemark(source.getRemark());
        return rtn;
    }
	
	/**
	 * SeckillActivityBO List转SeckillActivityThatDayDTO List
	 * @param list
	 * @return
	 * @author jiangxinjun
	 * @createDate 2017年11月23日
	 * @updateDate 2017年11月23日
	 */
    public static List<SeckillActivityThatDayDTO> convertSeckillActivityThatDayDTOList(List<SeckillActivityBO> list) {
        List<SeckillActivityThatDayDTO> rtn = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return rtn;
        }
        for (SeckillActivityBO item : list) {
            SeckillActivityThatDayDTO seckillActivityThatDayDTO = new SeckillActivityThatDayDTO();
            seckillActivityThatDayDTO.setActivityStatus(item.getActivityStatus());
            seckillActivityThatDayDTO.setId(item.getId());
            seckillActivityThatDayDTO.setMemberLevel(item.getMemberLevel());
            seckillActivityThatDayDTO.setName(item.getName());
            seckillActivityThatDayDTO.setPicture(item.getPicture());
            seckillActivityThatDayDTO.setStartDate(item.getStartDate());
            seckillActivityThatDayDTO.setDate(item.getStartDate());
            seckillActivityThatDayDTO.setSellingPrice(item.getSellingPrice());
            rtn.add(seckillActivityThatDayDTO);
        }
        return rtn;
    }
    
    /**
     * SeckillActivityBO List转SeckillActivityInfoDTO List
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    public static List<SeckillActivityInfoDTO> convertSeckillActivityInfoDTOList(List<SeckillActivityBO> list) {
        List<SeckillActivityInfoDTO> rtn = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return rtn;
        }
        for (SeckillActivityBO item : list) {
            SeckillActivityInfoDTO entry = new SeckillActivityInfoDTO();
            entry.setActivityStatus(item.getActivityStatus());
            entry.setId(item.getId());
            entry.setMemberLevel(item.getMemberLevel());
            entry.setName(item.getName());
            entry.setPicture(item.getPicture());
            entry.setStartDate(item.getStartDate());
            entry.setSellingPrice(item.getSellingPrice());
            entry.setEndDate(item.getEndDate());
            entry.setAttentEndDate(item.getAttentEndDate());
            rtn.add(entry);
        }
        return rtn;
    }
    
    /**
     * SeckillActivityBO转SeckillActivityDetailsDTO
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    public static SeckillActivityDetailsDTO convert(SeckillActivityBO source) {
        if (source == null) {
            return null;
        }
        SeckillActivityDetailsDTO rtn = new SeckillActivityDetailsDTO();
        rtn.setActivityStatus(source.getActivityStatus());
        rtn.setId(source.getId());
        rtn.setMemberLevel(source.getMemberLevel());
        rtn.setName(source.getName());
        rtn.setPicture(source.getPicture());
        rtn.setHomePicture(source.getHomePicture());
        rtn.setStartDate(source.getStartDate());
        rtn.setSellingPrice(source.getSellingPrice());
        rtn.setEndDate(source.getEndDate());
        rtn.setAttentEndDate(source.getAttentEndDate());
        rtn.setProductValidCount(source.getProductValidCount());
        rtn.setRemark(source.getRemark());
        return rtn;
    }
}
