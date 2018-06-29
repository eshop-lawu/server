package com.lawu.eshop.jobs.impl.agent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.property.dto.AreaPointConsumeDTO;
import com.lawu.eshop.property.dto.AreaRechargePointDTO;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeDailyParam;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageResultJob;
import com.lawu.utils.DateUtil;

public class ReportAreaPointConsumeDailyJob extends AbstractPageResultJob {

    private static List<AreaPointConsumeDTO> consumePointDTOS = new ArrayList<>();

    private static List<AreaPointConsumeDTO> refundPointDTOS = new ArrayList<>();

    private static List<AreaRechargePointDTO> rechargePointDTOS = new ArrayList<>();

    @Autowired
    private PropertySrvService propertySrvService;

    @Autowired
    private StatisticsSrvService statisticsSrvService;

    @Override
    public boolean isStatusData() {
        return false;
    }

    @Override
    public Object executePage(List list) {
        return list.get(0);
    }

    @Override
    public void executeSummary(List list) {
        if (consumePointDTOS.size() == 0 && refundPointDTOS.size() == 0 && rechargePointDTOS.size() == 0) {
            return;
        }

        Set<Integer> areaIdsSet = new HashSet<>();
        for (AreaPointConsumeDTO consumePointDTO : consumePointDTOS) {
            areaIdsSet.add(consumePointDTO.getAreaId());
        }
        for (AreaPointConsumeDTO refundPointDTO : refundPointDTOS) {
            areaIdsSet.add(refundPointDTO.getAreaId());
        }
        for (AreaRechargePointDTO rechargePointDTO : rechargePointDTOS) {
            areaIdsSet.add(rechargePointDTO.getAreaId());
        }

        ReportAreaPointConsumeDailyParam param;
        Date nowDate = new Date();
        Date beforeDate = DateUtil.getDayBefore(nowDate);
        for (int areaId : areaIdsSet) {
            param = new ReportAreaPointConsumeDailyParam();
            param.setAreaId(areaId);
            param.setMemberPoint(new BigDecimal(0));
            param.setMerchantPoint(new BigDecimal(0));
            param.setMemberRechargePoint(new BigDecimal(0));
            param.setMerchantRechargePoint(new BigDecimal(0));
            param.setGmtReport(beforeDate);
            param.setGmtCreate(nowDate);
            for (AreaPointConsumeDTO consumePointDTO : consumePointDTOS) {
                param.setProvinceId(consumePointDTO.getProvinceId());
                param.setCityId(consumePointDTO.getCityId());
                if (consumePointDTO.getAreaId() == areaId && UserCommonConstant.MEMBER_NUM_TAG.equals(consumePointDTO.getType())) {
                    param.setMemberPoint(consumePointDTO.getTotalPoint());
                    break;
                } else if (consumePointDTO.getAreaId() == areaId && UserCommonConstant.MERCHANT_NUM_TAG.equals(consumePointDTO.getType())) {
                    param.setMerchantPoint(consumePointDTO.getTotalPoint());
                    break;
                }
            }
            for (AreaPointConsumeDTO refundPointDTO : refundPointDTOS) {
                param.setProvinceId(refundPointDTO.getProvinceId());
                param.setCityId(refundPointDTO.getCityId());
                if (refundPointDTO.getAreaId() == areaId && UserCommonConstant.MEMBER_NUM_TAG.equals(refundPointDTO.getType())) {
                    param.setMemberPoint(param.getMemberPoint().subtract(refundPointDTO.getTotalPoint()));
                    break;
                } else if (refundPointDTO.getAreaId() == areaId && UserCommonConstant.MERCHANT_NUM_TAG.equals(refundPointDTO.getType())) {
                    param.setMerchantPoint(param.getMerchantPoint().subtract(refundPointDTO.getTotalPoint()));
                    break;
                }
            }
            for (AreaRechargePointDTO rechargePointDTO : rechargePointDTOS) {
                param.setProvinceId(rechargePointDTO.getProvinceId());
                param.setCityId(rechargePointDTO.getCityId());
                if (rechargePointDTO.getAreaId() == areaId && UserCommonConstant.MEMBER_NUM_TAG.equals(rechargePointDTO.getType())) {
                    param.setMemberRechargePoint(rechargePointDTO.getTotalMoney());
                    break;
                } else if (rechargePointDTO.getAreaId() == areaId && UserCommonConstant.MERCHANT_NUM_TAG.equals(rechargePointDTO.getType())) {
                    param.setMerchantRechargePoint(rechargePointDTO.getTotalMoney());
                    break;
                }
            }

            param.setTotalPoint(param.getMemberPoint().add(param.getMerchantPoint()));
            param.setTotalRechargePoint(param.getMemberRechargePoint().add(param.getMerchantRechargePoint()));
            statisticsSrvService.insertReportAreaPointConsumeDaily(param);
        }
    }

    @Override
    public List queryPage(int offset, int pageSize) {
        Date beforeDate = DateUtil.getDayBefore(new Date());
        String beginDate = DateUtil.getDateFormat(beforeDate) + " 00:00:00";
        String endDate = DateUtil.getDateFormat(beforeDate) + " 23:59:59";

        ReportAgentAreaPointParam param = new ReportAgentAreaPointParam();
        param.setOffset(offset);
        param.setPageSize(pageSize);
        param.setBeginDate(beginDate);
        param.setEndDate(endDate);

        Result<List<AreaPointConsumeDTO>> consumeResult = propertySrvService.getAreaPointConsume(param);
        Result<List<AreaPointConsumeDTO>> refundResult = propertySrvService.getAreaPointRefund(param);
        Result<List<AreaRechargePointDTO>> rechargeResult = propertySrvService.selectAreaRechargePoint(param);
        if (consumeResult.getModel().isEmpty() && refundResult.getModel().isEmpty() && rechargeResult.getModel().isEmpty()) {
            return null;
        }
        if (!consumeResult.getModel().isEmpty()) {
            consumePointDTOS.addAll(consumeResult.getModel());
        }
        if (!refundResult.getModel().isEmpty()) {
            refundPointDTOS.addAll(refundResult.getModel());
        }
        if (!rechargeResult.getModel().isEmpty()) {
            rechargePointDTOS.addAll(rechargeResult.getModel());
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        return list;
    }

}
