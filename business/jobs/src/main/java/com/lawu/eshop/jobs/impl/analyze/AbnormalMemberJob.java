package com.lawu.eshop.jobs.impl.analyze;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.service.AbnormalAccountService;
import com.lawu.eshop.jobs.service.InviteAbnormalService;
import com.lawu.eshop.user.constants.UserInviterTypeEnum;
import com.lawu.eshop.user.dto.AbnormalAccountDTO;
import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/1/19.
 */
public class AbnormalMemberJob extends AbstractPageJob<AbnormalAccountDTO> {
    private static Logger logger = LoggerFactory.getLogger(AbnormalMemberJob.class);

    @Autowired
    private JobsConfig jobsConfig;

    @Autowired
    private AbnormalAccountService abnormalAccountService;

    @Autowired
    private InviteAbnormalService inviteAbnormalService;


    @Override
    public void executeSingle(AbnormalAccountDTO abnormalAccountDTO) {
        Result<List<Integer>> sameMemberIpCount = abnormalAccountService.memberSameIpCount(abnormalAccountDTO.getId(), UserInviterTypeEnum.INVITER_TYPE_MEMBER.val);
        Result<List<Integer>> sameMerchantIpCount = abnormalAccountService.merchantSameIpCount(abnormalAccountDTO.getId(), UserInviterTypeEnum.INVITER_TYPE_MEMBER.val);
        Integer memberTotal = abnormalAccountService.inviteMemberTotalCount(abnormalAccountDTO.getId(), UserInviterTypeEnum.INVITER_TYPE_MEMBER.val);
        Integer merchantTotal = abnormalAccountService.inviteMerchantTotalCount(abnormalAccountDTO.getId(), UserInviterTypeEnum.INVITER_TYPE_MEMBER.val);
        sameMemberIpCount.getModel().addAll(sameMerchantIpCount.getModel());
        List<Integer> totalAccount = sameMemberIpCount.getModel();
        Collections.sort(totalAccount);
        int starCount = 0;
        int total = memberTotal + merchantTotal;
        if (totalAccount.size() < jobsConfig.getSameIpCount() && totalAccount.size() > 0) {
            starCount = totalAccount.get(totalAccount.size() - 1);
        } else if (totalAccount.size() >= jobsConfig.getSameIpCount()) {
            for (int i = 1; i <= jobsConfig.getSameIpCount(); i++) {
                starCount += totalAccount.get(totalAccount.size() - i);
            }
        }

        if ((starCount / total) > jobsConfig.getSameIpRate()) {
            AbnormalJobAddParam param = new AbnormalJobAddParam();
            param.setUserNum(abnormalAccountDTO.getUserNum());
            param.setAccount(abnormalAccountDTO.getAccount());
            param.setType(UserTypeEnum.MEMBER);
            inviteAbnormalService.editAbnormalRecord(param);
        }
    }

    @Override
    public boolean isStatusData() {
        return false;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }

    @Override
    public List<AbnormalAccountDTO> queryPage(int offset, int pageSize) {
        AbnormalJobParam param = new AbnormalJobParam();
        param.setOffset(offset);
        param.setPageSize(pageSize);
        param.setStartTime(DateUtil.getBeforeHourDate(2));//查询前2个小时
        param.setEndTime(new Date());
        param.setTapeOutNumber(jobsConfig.getTapeOutNumber());
        //推荐人1级下线大于50人的下线记录
        Result<List<AbnormalAccountDTO>> memberDTOS = abnormalAccountService.abnormalMemberList(param);
        return memberDTOS.getModel();
    }
}
