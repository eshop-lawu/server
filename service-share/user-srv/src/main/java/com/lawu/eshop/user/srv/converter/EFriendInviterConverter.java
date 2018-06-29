package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.srv.bo.EFriendInviterBO;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2017/7/28 11:19
 */
public class EFriendInviterConverter {

    public static List<EFriendInviterDTO> converter(List<EFriendInviterBO> records) {
        List<EFriendInviterDTO> rtnList = new ArrayList<>();
        if(records == null){
            return null;
        }
        for(EFriendInviterBO bo : records){
            EFriendInviterDTO dto = new EFriendInviterDTO();
            dto.setGmtCreate(bo.getGmtCreate());
            dto.setTitleName(bo.getTitleName());
            dto.setHeadImg(bo.getHeadImg());
            dto.setMerchantStatus(bo.getMerchantStatus());
            dto.setAccount(bo.getAccount());
            dto.setInviterCount(bo.getInviterCount());
            dto.setLevel(bo.getLevel());
            dto.setName(bo.getName());
            dto.setRegionAddress(bo.getRegionAddress());
            dto.setTotalInviteCount(bo.getTotalInviteCount());
            dto.setUserNum(bo.getUserNum());
            rtnList.add(dto);
        }
        return rtnList;
    }
}
