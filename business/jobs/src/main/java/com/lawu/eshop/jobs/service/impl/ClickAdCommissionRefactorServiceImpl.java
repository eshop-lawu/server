package com.lawu.eshop.jobs.service.impl;

import java.util.List;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;
import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.AdSrvService;
import com.lawu.eshop.jobs.service.ClickAdCommissionRefactorService;
import com.lawu.eshop.jobs.service.CommissionCommonService;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.param.CommissionParam;
import com.lawu.framework.web.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClickAdCommissionRefactorServiceImpl implements ClickAdCommissionRefactorService {

    private static Logger logger = LoggerFactory.getLogger(ClickAdCommissionRefactorServiceImpl.class);

    @Autowired
    private AdSrvService adService;
    @Autowired
    private CommissionCommonService userCommonService;
    @Autowired
    private PropertySrvService propertySrvService;

    @Override
    public void executeRefactor(MemberAdRecodeCommissionDTO memberAdRecodeCommissionDTO) {

        if (memberAdRecodeCommissionDTO.getMemberNum() == null || "".equals(memberAdRecodeCommissionDTO.getMemberNum())) {
            logger.error("查询未计算提成的点广告记录用户编号为空！");
            throw new RuntimeException();
        }
        // 查询用户上3级推荐
        List<CommissionInvitersUserDTO> inviters = userCommonService.selectHigherLevelInviters(memberAdRecodeCommissionDTO.getMemberNum(), 3, false);

        if (inviters != null && !inviters.isEmpty()) {
            CommissionParam param = new CommissionParam();
            param.setId(memberAdRecodeCommissionDTO.getId());
            param.setTempId(memberAdRecodeCommissionDTO.getAdId());
            param.setActualAmount(memberAdRecodeCommissionDTO.getPoint());
            param.setMemberNum(memberAdRecodeCommissionDTO.getMemberNum());
            param.setInviters(inviters);
            param.setCommissionEnum(CommissionEnum.AD);
            Result result = propertySrvService.calculationRefactor(param);
            if (result.getRet() != ResultCode.SUCCESS) {// 所有上线提成计算成功才算成功
                logger.error("广告提成记录（id={}）计算上级提成时存在失败情况，异常信息：{}", memberAdRecodeCommissionDTO.getId(), result.getMsg());
                throw new RuntimeException();
            }
        }

        // 修改用户点击广告记录状态为已计算提成
        int retCode = adService.updateMemberAdRecardStatus(memberAdRecodeCommissionDTO.getId());
        if (ResultCode.SUCCESS != retCode) {
            logger.error("广告提成修改业务表状态为已计算提成异常,memberAdRecordId={},retCode={}", memberAdRecodeCommissionDTO.getId(), retCode);
            throw new RuntimeException();
        }

    }

}
