package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.dto.RechargeSaveDTO;
import com.lawu.eshop.property.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.param.AgentReportRechargeQueryParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.RechargeQueryDataParam;
import com.lawu.eshop.property.param.RechargeReportParam;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.srv.bo.AgentReportRechargeQueryBO;
import com.lawu.eshop.property.srv.bo.AreaRechargePointBO;
import com.lawu.eshop.property.srv.bo.BalanceAndPointListQueryBO;
import com.lawu.eshop.property.srv.bo.RechargeReportBO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月12日 下午8:50:30
 */
public interface RechargeService {

    /**
     * 用户商家第三方充值余额积分保存充值记录
     *
     * @param param
     * @return
     * @throws Exception
     */
    RechargeSaveDTO save(RechargeSaveDataParam param);

    /**
     * 用户商家充值余额积分回调
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    Result doHandleRechargeNotify(NotifyCallBackParam param);

    /**
     * 调用第三方支付时获取需要支付的金额
     *
     * @param rechargeId
     * @return
     */
    ThirdPayCallBackQueryPayOrderDTO getRechargeMoney(String rechargeId);

    /**
     * 运营平台充值
     *
     * @param dparam
     * @return
     * @author yangqh
     * @date 2017年5月16日 下午4:00:48
     */
    Page<BalanceAndPointListQueryBO> selectPropertyinfoList(RechargeQueryDataParam dparam);

    /**
     * 根据ID查询充值方式
     *
     * @param id
     * @return
     */
    String getRechargePayType(Long id);

    /**
     * 统计报表按日查询某天充值月成功记录
     *
     * @param param
     * @return
     * @author yangqh
     * @date 2017年6月29日 下午5:28:21
     */
    RechargeReportBO selectWithdrawCashListByDateAndStatus(RechargeReportParam param);

    /**
     * 根据充值ID查询充值状态
     * @param id
     * @return
     */
    ThirdPayStatusEnum getRechargeById(Long id);

    /**
     * 代理商区域统计，获取某天第三方充值记录，分组查询
     * @param param
     * @return
     */
    List<AgentReportRechargeQueryBO> selectAgentAreaReportRechargeListByDate(AgentReportRechargeQueryParam param);
    
    
    /**
     * 查询区域充值积分的记录
     * @param param
     * @return
     */
    List<AreaRechargePointBO> selectAreaRechargePoint(ReportAgentAreaPointParam param);
}
