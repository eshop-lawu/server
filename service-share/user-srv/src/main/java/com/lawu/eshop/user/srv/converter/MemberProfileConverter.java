package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.user.dto.AbnormalAccountDTO;
import com.lawu.eshop.user.dto.MemberMineInfoDTO;
import com.lawu.eshop.user.srv.bo.AbnormalAccountBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MemberProfileBO;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.domain.extend.AbnormalAccountDOView;
import org.apache.commons.lang.StringUtils;

/**
 * @author Sunny
 * @date 2017年6月16日
 */
public class MemberProfileConverter {

    /**
     * BO转换
     *
     * @param memberProfileDO
     * @return
     */
    public static MemberProfileBO convert(MemberProfileDO memberProfileDO) {
        MemberProfileBO rtn = null;
        if (memberProfileDO == null || memberProfileDO.getId() == null || memberProfileDO.getId() <= 0) {
            return rtn;
        }

        rtn = new MemberProfileBO();
        rtn.setGmtCreate(memberProfileDO.getGmtCreate());
        rtn.setGmtModified(memberProfileDO.getGmtModified());
        rtn.setId(memberProfileDO.getId());
        rtn.setInviteMemberCount(memberProfileDO.getInviteMemberCount());
        rtn.setInviteMemberCount2(memberProfileDO.getInviteMemberCount2());
        rtn.setInviteMemberCount3(memberProfileDO.getInviteMemberCount3());
        rtn.setInviteMerchantCount(memberProfileDO.getInviteMerchantCount());
        rtn.setInviteMerchantCount2(memberProfileDO.getInviteMerchantCount2());
        rtn.setInviteMerchantCount3(memberProfileDO.getInviteMerchantCount3());
        return rtn;
    }

    /**
     * 转换
     *
     * @param memberProfileBO
     * @param memberBO
     * @return
     */
    public static MemberMineInfoDTO convert(MemberProfileBO memberProfileBO, MemberBO memberBO) {
        MemberMineInfoDTO rtn = null;
        if (memberProfileBO == null || memberBO == null) {
            return rtn;
        }
        rtn = new MemberMineInfoDTO();
        rtn.setHeadimg(memberBO.getHeadimg());
        rtn.setLevel(memberBO.getLevel());
        rtn.setNickname(memberBO.getNickname());
        rtn.setInviteMemberCount(memberProfileBO.getInviteMemberCount() + memberProfileBO.getInviteMemberCount2() + memberProfileBO.getInviteMemberCount3());
        rtn.setInviteMerchantCount(memberProfileBO.getInviteMerchantCount() + memberProfileBO.getInviteMerchantCount2() + memberProfileBO.getInviteMerchantCount3());
        rtn.setGrade(memberBO.getGrade());
        rtn.setGrowthValue(memberBO.getGrowthValue());
        if (StringUtils.isBlank(memberBO.getPwd())) {
            rtn.setLoginPwdIsExist(false);
        } else {
            rtn.setLoginPwdIsExist(true);
        }
        return rtn;
    }

    public static List<AbnormalAccountBO> convertAbnormalBOS(List<AbnormalAccountDOView> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<AbnormalAccountBO> accountBOS = new ArrayList<>();
        AbnormalAccountBO accountBO;
        for (AbnormalAccountDOView doView : list) {
            accountBO = new AbnormalAccountBO();
            accountBO.setId(doView.getId());
            accountBO.setUserNum(doView.getUserNum());
            accountBO.setAccount(doView.getAccount());
            accountBOS.add(accountBO);
        }
        return accountBOS;
    }

    public static List<AbnormalAccountDTO> convertAbnormalDTOS(List<AbnormalAccountBO> accountBOS) {
        if (accountBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<AbnormalAccountDTO> accountDTOS = new ArrayList<>();
        AbnormalAccountDTO accountDTO;
        for (AbnormalAccountBO accountBO : accountBOS) {
            accountDTO = new AbnormalAccountDTO();
            accountDTO.setId(accountBO.getId());
            accountDTO.setUserNum(accountBO.getUserNum());
            accountDTO.setAccount(accountBO.getAccount());
            accountDTOS.add(accountDTO);
        }
        return accountDTOS;
    }
}
