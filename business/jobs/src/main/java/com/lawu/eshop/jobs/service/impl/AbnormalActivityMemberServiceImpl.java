package com.lawu.eshop.jobs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketInviteAbnormalDTO;
import com.lawu.eshop.activity.dto.IdentifiedAsAbnormalDTO;
import com.lawu.eshop.activity.dto.IdentifiedAsAbnormalPackageDTO;
import com.lawu.eshop.activity.dto.SignAbnormalAccountDTO;
import com.lawu.eshop.activity.param.AbnormalInviteParam;
import com.lawu.eshop.activity.param.SignAbnormalParam;
import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.jobs.service.AbnormalActivityMemberService;
import com.lawu.eshop.jobs.service.HelpRedpacketAttendDetailService;
import com.lawu.eshop.jobs.service.InviteAbnormalService;
import com.lawu.eshop.jobs.service.MemberService;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
@Service
public class AbnormalActivityMemberServiceImpl implements AbnormalActivityMemberService {

    @Autowired
    private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;

    @Autowired
    private InviteAbnormalService inviteAbnormalService;

    @Autowired
    private MemberService memberService;

    @Override
    public void executeUpdateActivitySuspectedStatus() {
        helpRedpacketAttendDetailService.executeUpdateActivitySuspectedStatus();
    }

    @Override
    public void executeSignAbnormalMemberJob() {
        int activityId = 2;
        long attendId = 0;
        SignAbnormalParam param = new SignAbnormalParam();
        param.setCurrentPage(1);
        param.setPageSize(200);
        while (true){
            param.setAttendId(attendId);
            Result<List<SignAbnormalAccountDTO>> result = helpRedpacketAttendDetailService.queryAttendDetailList(param);
            if(result.getModel().isEmpty()){
                break;
            }

            for(int i = 0; i < 4; i++) {
                List<String> userNums = new ArrayList<>();
                List<String> accounts = new ArrayList<>();
                for (int j = 0; j <50; j++) {
                    int index = i * 50 + j;
                    if (index < result.getModel().size()) {

                        SignAbnormalAccountDTO abnormalAccountDTO = result.getModel().get(index);
                        boolean isAbnormal = abnormalActiveList(abnormalAccountDTO);
                        if (isAbnormal) {
                            userNums.add(abnormalAccountDTO.getUserNum());
                            accounts.add(abnormalAccountDTO.getAccount());
                        }
                    }
                }

                abnormalMember(userNums, accounts, activityId);
            }
            //保留最后一个ID
            attendId = result.getModel().get(result.getModel().size()-1).getId();
        }


    }




    private boolean abnormalActiveList(SignAbnormalAccountDTO abnormalAccountDTO){
        Date compareTime = DateUtil.stringToDate("2018-02-28 16:40:00");
        //2.28日16点40之前
        AbnormalInviteParam param = new AbnormalInviteParam();
        param.setUserNum(abnormalAccountDTO.getUserNum());
        param.setActiveId(abnormalAccountDTO.getActivityId());
        param.setAttendId(abnormalAccountDTO.getId());

        if (abnormalAccountDTO.getAttendTime().getTime() < compareTime.getTime()) {
            if (abnormalAccountDTO.getHelpCount() >= 10) {
                Result<HelpRedpacketInviteAbnormalDTO> abnormalDTO = helpRedpacketAttendDetailService.queryAbnormalInviteRecord(param);
                //最大时间和最小时间的间隔秒
                long interval = (abnormalDTO.getModel().getMaxInviteTime().getTime() - abnormalDTO.getModel().getMinInviteTime().getTime()) / 1000;
                if (interval <= 600) {
                    //10分钟内邀请10个助力的  标记异常
                    // abnormalMember(abnormalAccountDTO.getUserNum(),abnormalAccountDTO.getAccount(),abnormalAccountDTO.getActivityId());
                    return true;
                } else if ((600 < interval && interval <= 1800 && abnormalAccountDTO.getHelpCount() >= 10)
                        && abnormalAccountDTO.getStatusEnum() == AbnormalStatusEnum.NORMAL) {
                    //10分钟内邀请10个助力的 疑似异常账号
                    helpRedpacketAttendDetailService.updateActivityAbnormalStatus(abnormalAccountDTO.getId(), AbnormalStatusEnum.SUSPECTED.getVal());
                    return false;
                }
            } else {
                Result<Boolean> memberResult = memberService.isFinishInformation(abnormalAccountDTO.getUserNum());
                if (!memberResult.getModel()) {
                    //未完善资料 标记异常
                    //abnormalMember(abnormalAccountDTO.getUserNum(),abnormalAccountDTO.getAccount(),abnormalAccountDTO.getActivityId());
                    return true;
                }
            }

        } else {
            if (abnormalAccountDTO.getHelpCount() >= 10) {
                Result<HelpRedpacketInviteAbnormalDTO> abnormalDTO = helpRedpacketAttendDetailService.queryAbnormalInviteRecord(param);
                //最大时间和最小时间的间隔秒
                long interval = (abnormalDTO.getModel().getMaxInviteTime().getTime() - abnormalDTO.getModel().getMinInviteTime().getTime()) / 1000;

                if (interval <= 1800 ) {
                    //30分钟内邀请10个助力的  标记异常
                    // abnormalMember(abnormalAccountDTO.getUserNum(),abnormalAccountDTO.getAccount(),abnormalAccountDTO.getActivityId());
                    return true;
                } else if(abnormalAccountDTO.getStatusEnum() == AbnormalStatusEnum.NORMAL) {
                    helpRedpacketAttendDetailService.updateActivityAbnormalStatus(abnormalAccountDTO.getId(), AbnormalStatusEnum.SUSPECTED.getVal());
                    return false;
                }

            } else {
                Result<Boolean> memberResult = memberService.isFinishInformation(abnormalAccountDTO.getUserNum());
                if (!memberResult.getModel()) {
                    //未完善资料 标记异常
                    //abnormalMember(abnormalAccountDTO.getUserNum(),abnormalAccountDTO.getAccount(),abnormalAccountDTO.getActivityId());
                    return true;
                } else if(abnormalAccountDTO.getStatusEnum() == AbnormalStatusEnum.NORMAL){

                    helpRedpacketAttendDetailService.updateActivityAbnormalStatus(abnormalAccountDTO.getId(), AbnormalStatusEnum.SUSPECTED.getVal());
                    return false;
                }

            }
        }
        return false;
    }


    private void abnormalMember(List<String> userNums,List<String> accounts,int activityId) {
        Result<IdentifiedAsAbnormalPackageDTO> result = helpRedpacketAttendDetailService.identifiedAsAbnormal(userNums,activityId);
        List<IdentifiedAsAbnormalDTO> abnormalList = result.getModel().getAbnormalList();

        for(int i = 0; i < userNums.size(); i++) {

            IdentifiedAsAbnormalDTO old = new IdentifiedAsAbnormalDTO();
            old.setAccount(accounts.get(i));
            old.setUserNum(userNums.get(i));
            abnormalList.add(old);
        }
        if (!abnormalList.isEmpty()) {
            //增加异常信息
            for (int i = 0; i < abnormalList.size(); i++) {
                AbnormalJobAddParam addParam = new AbnormalJobAddParam();
                addParam.setUserNum(abnormalList.get(i).getUserNum());
                addParam.setAccount(abnormalList.get(i).getAccount());
                addParam.setType(UserTypeEnum.MEMBER);
                inviteAbnormalService.addActiveAbnormalInfo(addParam);
            }
        }
    }


}
