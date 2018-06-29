package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.user.constants.FreezeTypeEnum;
import org.apache.commons.lang.StringUtils;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MemberInfoForShoppingOrderDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.param.UserParam;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.framework.core.page.Page;

/**
 * 会员信息转换器
 *
 * @author Leach
 * @date 2017/3/13
 */
public class MemberConverter {

    /**
     * BO转换
     *
     * @param memberDO
     * @return
     */
    public static MemberBO convertBO(MemberDO memberDO) {
        if (memberDO == null) {
            return null;
        }

        MemberBO memberBO = new MemberBO();
        memberBO.setId(memberDO.getId());
        memberBO.setAccount(memberDO.getAccount());
        memberBO.setPwd(memberDO.getPwd());
        memberBO.setBirthday(memberDO.getBirthday());
        memberBO.setNum(memberDO.getNum());
        memberBO.setMobile(memberDO.getMobile());
        memberBO.setHeadimg(memberDO.getHeadimg());
        memberBO.setInviterId(memberDO.getInviterId());
        memberBO.setInviterType(memberDO.getInviterType());
        memberBO.setLevel(memberDO.getLevel());
        memberBO.setUserSex(UserSexEnum.getEnum(memberDO.getSex()));
        memberBO.setRegionPath(memberDO.getRegionPath());
        if(StringUtils.isEmpty(memberDO.getNickname())){
            memberBO.setNickname("E店用户");
        }else{
            memberBO.setNickname(memberDO.getNickname());
        }
        memberBO.setGtCid(memberDO.getGtCid());
        memberBO.setRyToken(memberDO.getRyToken());
        memberBO.setRegionName(memberDO.getRegionName());
        memberBO.setIsFreeze(memberDO.getIsFreeze());
        memberBO.setFreezeReason(memberDO.getFreezeReason());
        memberBO.setGmtCreate(memberDO.getGmtCreate());
        memberBO.setName(memberDO.getName());
        memberBO.setStatus(memberDO.getStatus());
        memberBO.setGrade(memberDO.getGrade());
        memberBO.setGrowthValue(memberDO.getGrowthValue());
        memberBO.setRegOrigin(memberDO.getRegOrigin());
        if (memberDO.getFrozenType() != null && memberDO.getIsFreeze()) {
            memberBO.setFreezeTypeEnum(FreezeTypeEnum.getEnum(memberDO.getFrozenType()));
        }
        return memberBO;
    }

    /**
     * DO转换
     *
     * @param memberBO
     * @return
     */
    public static MemberDO convertDO(MemberBO memberBO) {
        if (memberBO == null) {
            return null;
        }

        MemberDO memberDO = new MemberDO();
        memberDO.setId(memberBO.getId());
        memberDO.setAccount(memberBO.getAccount());
        memberDO.setBirthday(memberBO.getBirthday());
        memberDO.setNum(memberBO.getNum());
        memberDO.setPwd(memberBO.getPwd());
        memberDO.setMobile(memberBO.getMobile());
        memberDO.setGmtCreate(memberBO.getGmtCreate());
        memberDO.setGmtModified(memberBO.getGmtModified());
        memberDO.setHeadimg(memberBO.getHeadimg());
        memberDO.setInviterId(memberBO.getInviterId());
        memberDO.setInviterType(memberDO.getInviterType());
        memberDO.setLevel(memberDO.getLevel());
        memberDO.setStatus(memberDO.getStatus());
        memberDO.setSex(memberDO.getSex());
        memberDO.setRegionPath(memberDO.getRegionPath());
        memberDO.setNickname(memberDO.getNickname());
        memberDO.setName(memberDO.getName());
        return memberDO;
    }

    /**
     * DTO转换
     *
     * @param memberBO
     * @return
     */
    public static UserDTO convertDTO(MemberBO memberBO) {
        if (memberBO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(memberBO.getId());
        userDTO.setNum(memberBO.getNum());
        userDTO.setAccount(memberBO.getAccount());
        userDTO.setBirthday(memberBO.getBirthday());
        userDTO.setUserSex(memberBO.getUserSex());
        userDTO.setRegionPath(memberBO.getRegionPath());
        userDTO.setNickname(memberBO.getNickname());
        userDTO.setHeadimg(memberBO.getHeadimg());
        userDTO.setLevel(memberBO.getLevel());
        userDTO.setGtCid(memberBO.getGtCid());
        userDTO.setRyToken(memberBO.getRyToken());
        userDTO.setRegionName(memberBO.getRegionName());
        userDTO.setIsFreeze(memberBO.getIsFreeze());
        userDTO.setGradeEnum(MemberGradeEnum.getEnum(memberBO.getGrade()));
        userDTO.setGrade(memberBO.getGrade());
        return userDTO;
    }


    /**
     * param转DO
     *
     * @param userParam
     * @return
     */
    public static MemberDO convertDOOther(UserParam userParam) {
        if (userParam == null) {
            return null;
        }
        MemberDO memberDO = new MemberDO();
        memberDO.setNickname(userParam.getNickname());
        memberDO.setRegionPath(userParam.getRegionPath());
        if (userParam.getUserSexEnum() != null) {
            memberDO.setSex(userParam.getUserSexEnum().val);
        }
        memberDO.setBirthday(userParam.getBirthday());
        memberDO.setRegionName(userParam.getRegionName());
        if(userParam.getRegionName() != null){
            memberDO.setRegionName(userParam.getRegionName().replaceAll(" ","").replaceAll("-",""));
        }
        return memberDO;
    }


    /**
     * 描述：将DOS转成BOS
     *
     * @param memberDOS
     * @return
     * @author zhangrc
     * @date 2017/03/23
     */
    public static List<MemberBO> convertListBOS(List<MemberDO> memberDOS, List<MemberProfileDO> mpList) {
        if (memberDOS == null) {
            return null;
        }
        List<MemberBO> memberBOS = new ArrayList<MemberBO>();
        for (MemberDO memberDO : memberDOS) {
            MemberBO memberBO = new MemberBO();
            memberBO.setAccount(memberDO.getAccount());
            memberBO.setName(memberDO.getName());
            memberBO.setMobile(memberDO.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
            memberBO.setHeadimg(memberDO.getHeadimg());
            memberBO.setUserSex(UserSexEnum.getEnum(memberDO.getSex()));
            memberBO.setRegionPath(memberDO.getRegionPath());
            memberBO.setNickname(memberDO.getNickname());
            memberBO.setGmtCreate(memberDO.getGmtCreate());
            memberBO.setLevel(memberDO.getLevel());
            if (mpList.isEmpty()) {
                memberBO.setInviterCount(0);
            } else {
                for (MemberProfileDO memberProfileDO : mpList) {
                    if (memberDO.getId().equals(memberProfileDO.getId())) {
                        memberBO.setInviterCount(memberProfileDO.getInviteMemberCount() + memberProfileDO.getInviteMemberCount2());
                    }
                }
            }
            memberBOS.add(memberBO);
        }
        return memberBOS;
    }


    /**
     * DTO转换
     *
     * @param memberBO
     * @return
     */
    public static MemberDTO convertMDTO(MemberBO memberBO) {
        if (memberBO == null) {
            return null;
        }
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberBO.getId());
        memberDTO.setNum(memberBO.getNum());
        memberDTO.setAccount(memberBO.getAccount());
        memberDTO.setName(memberBO.getName());
        memberDTO.setNickname(memberBO.getNickname());
        memberDTO.setBirthday(memberBO.getBirthday());
        memberDTO.setHeadimg(memberBO.getHeadimg());
        memberDTO.setLevel(memberBO.getLevel());
        memberDTO.setMobile(memberBO.getMobile());
        memberDTO.setRegionPath(memberBO.getRegionPath());
        memberDTO.setRegionName(memberBO.getRegionName());
        memberDTO.setGmtCreate(memberBO.getGmtCreate());
        memberDTO.setUserSex(memberBO.getUserSex());
        memberDTO.setFreeze(memberBO.getIsFreeze());
        return memberDTO;
    }


    public static EfriendDTO convertEDTO(MemberBO memberBO) {
        if (memberBO == null) {
            return null;
        }
        EfriendDTO memberDTO = new EfriendDTO();
        if(memberBO.getNickname()==null){
        	memberDTO.setNickname("E店用户");
        }else{
        	memberDTO.setNickname(memberBO.getNickname());
        }
        memberDTO.setHeadimg(memberBO.getHeadimg());
        memberDTO.setMobile(memberBO.getMobile());
        memberDTO.setLevel(memberBO.getLevel());
        memberDTO.setGmtCreate(memberBO.getGmtCreate());
        memberDTO.setUserSex(memberBO.getUserSex());
        memberDTO.setInviterCount(memberBO.getInviterCount());
        return memberDTO;
    }

    /**
     * 描述：将BOS转成DTOS
     *
     * @param memberBOS
     * @return
     * @author zhangrc
     * @date 2017/03/23
     */
    public static List<EfriendDTO> convertListDOTS(List<MemberBO> memberBOS) {
        if (memberBOS == null) {
            return null;
        }
        List<EfriendDTO> memberDTOS = new ArrayList<EfriendDTO>();
        for (MemberBO MemberBO : memberBOS) {
            memberDTOS.add(convertEDTO(MemberBO));
        }
        return memberDTOS;
    }

    /**
     * 描述：将pageBOS转成pageDTOS
     *
     * @param pageMemberBOS
     * @return
     */
    public static Page<EfriendDTO> convertPageDOTS(Page<MemberBO> pageMemberBOS) {
        Page<EfriendDTO> pageDTO = new Page<EfriendDTO>();
        List<MemberBO> BOS = pageMemberBOS.getRecords();
        List<EfriendDTO> DTOS = pageDTO.getRecords();
        for (MemberBO memberBO : BOS) {
            DTOS.add(convertEDTO(memberBO));
        }
        pageDTO.setTotalCount(pageMemberBOS.getTotalCount());
        pageDTO.setCurrentPage(pageMemberBOS.getCurrentPage());
        return pageDTO;
    }
    
    /**
     * @param memberBO
     * @return
     */
    public static MemberInfoForShoppingOrderDTO convert(MemberBO memberBO) {
    	if (memberBO == null) {
    		return null;
    	}
        MemberInfoForShoppingOrderDTO rtn = new MemberInfoForShoppingOrderDTO();
        rtn.setNum(memberBO.getNum());
    	return rtn;
    }

    public static List<AccountDTO> convertAccountDOTS(List<MemberBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<AccountDTO> list = new ArrayList<>();
        AccountDTO accountDTO;
        for (MemberBO memberBO : records) {
            accountDTO = new AccountDTO();
            accountDTO.setAccount(memberBO.getAccount());
            accountDTO.setId(memberBO.getId());
            accountDTO.setNum(memberBO.getNum());
            accountDTO.setUserType(UserTypeEnum.MEMBER);
            accountDTO.setGmtCreate(memberBO.getGmtCreate());
            accountDTO.setFreeze(memberBO.getIsFreeze());
            accountDTO.setFreezeReason(memberBO.getFreezeReason());
            accountDTO.setFreezeTypeEnum(memberBO.getFreezeTypeEnum());
            list.add(accountDTO);
        }
        return list;
    }
}
