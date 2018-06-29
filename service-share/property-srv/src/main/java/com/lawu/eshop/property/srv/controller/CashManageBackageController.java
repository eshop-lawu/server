package com.lawu.eshop.property.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.lawu.eshop.property.dto.WithdrawCashTotalReportDTO;
import com.lawu.eshop.property.srv.bo.WithdrawCashTotalReportBO;
import com.lawu.eshop.property.srv.exception.ThirdCashTransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.CashOperEnum;
import com.lawu.eshop.property.dto.WithdrawCashBackageQueryDTO;
import com.lawu.eshop.property.dto.WithdrawCashBackageQuerySumDTO;
import com.lawu.eshop.property.dto.WithdrawCashReportDTO;
import com.lawu.eshop.property.param.AgentWithdrawCashReportParam;
import com.lawu.eshop.property.param.CashBackageOperDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDetailParam;
import com.lawu.eshop.property.param.CashBackageQuerySumParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQueryBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQuerySumBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashReportBO;
import com.lawu.eshop.property.srv.service.CashManageBackageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;

/**
 * <p>
 * Description: 运营后台提现管理
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月10日 下午2:08:11
 */
@RestController
@RequestMapping(value = "cashBackage/")
public class CashManageBackageController extends BaseController {

    @Autowired
    private CashManageBackageService cashManageBackageService;

    /**
     * 运营平台财务提现管理
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findCashInfo", method = RequestMethod.POST)
    public Result<Page<WithdrawCashBackageQueryDTO>> findCashInfo(@RequestBody CashBackageQueryDataParam param) throws Exception {
        Page<WithdrawCashBackageQueryBO> page = cashManageBackageService.findCashInfo(param);
        List<WithdrawCashBackageQueryBO> cbos = page.getRecords();
        List<WithdrawCashBackageQueryDTO> dtos = new ArrayList<WithdrawCashBackageQueryDTO>();
        for (WithdrawCashBackageQueryBO bo : cbos) {
            WithdrawCashBackageQueryDTO dto = new WithdrawCashBackageQueryDTO();
            BeanUtil.copyProperties(bo, dto);
            dtos.add(dto);
        }
        Page<WithdrawCashBackageQueryDTO> pageResult = new Page<WithdrawCashBackageQueryDTO>();
        pageResult.setTotalCount(page.getTotalCount());
        pageResult.setCurrentPage(page.getCurrentPage());
        pageResult.setRecords(dtos);
        return successCreated(pageResult);
    }

    /**
     * 运营平台提现管理统计成功总笔数和成功总金额
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getTotalNum", method = RequestMethod.POST)
    public Result<WithdrawCashBackageQuerySumDTO> getTotalNum(@RequestBody @Valid CashBackageQuerySumParam param, BindingResult result) throws Exception {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        WithdrawCashBackageQuerySumDTO dto = new WithdrawCashBackageQuerySumDTO();
        WithdrawCashBackageQuerySumBO bo = cashManageBackageService.getTotalNum(param);
        BeanUtil.copyProperties(bo, dto);
        if (dto.getSuccessMoney() == null) {
            dto.setSuccessMoney(new BigDecimal(0));
        }
        return successCreated(dto);
    }

    /**
     * 运营平台提现详情
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findCashInfoDetail", method = RequestMethod.POST)
    public Result<Page<WithdrawCashBackageQueryDTO>> findCashInfoDetail(@RequestBody @Valid CashBackageQueryDetailParam param, BindingResult result) throws Exception {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        Page<WithdrawCashBackageQueryBO> page = cashManageBackageService.findCashInfoDetail(param);
        List<WithdrawCashBackageQueryBO> cbos = page.getRecords();
        List<WithdrawCashBackageQueryDTO> dtos = new ArrayList<WithdrawCashBackageQueryDTO>();
        for (WithdrawCashBackageQueryBO bo : cbos) {
            WithdrawCashBackageQueryDTO dto = new WithdrawCashBackageQueryDTO();
            BeanUtil.copyProperties(bo, dto);
            dtos.add(dto);
        }
        Page<WithdrawCashBackageQueryDTO> pageResult = new Page<WithdrawCashBackageQueryDTO>();
        pageResult.setTotalCount(page.getTotalCount());
        pageResult.setCurrentPage(page.getCurrentPage());
        pageResult.setRecords(dtos);
        return successCreated(pageResult);
    }

    /**
     * 提现后台处理
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "updateWithdrawCash", method = RequestMethod.POST)
    public Result updateWithdrawCash(@RequestBody @Valid CashBackageOperDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        if (CashOperEnum.FAILURE.getVal().equals(param.getCashOperEnum().getVal())
                && (param.getFailReason() == null || "".equals(param.getFailReason()))) {
            return successCreated(ResultCode.CASH_BACKAGE_FAILURE_REASON_NULL);
        }
        try {
            int retCode = cashManageBackageService.updateWithdrawCash(param);
            return successCreated(retCode);
        } catch (ThirdCashTransferException tte) {
            tte.printStackTrace();
            String errMessage = tte.getMessage();
            cashManageBackageService.saveThirdplatformLog(param.getUserNum(),tte);
            return successCreated(ResultCode.FAIL, errMessage);
        }
    }


    // -------------------------------统计报表

    /**
     * 查询某天平台用户商家提现成功的记录
     *
     * @param param
     * @param result
     * @return
     * @throws Exception
     * @author yangqh
     * @date 2017年6月28日 下午4:32:19
     */
    @RequestMapping(value = "selectWithdrawCashListByDateAndStatus", method = RequestMethod.POST)
    public Result<List<WithdrawCashReportDTO>> selectWithdrawCashListByDateAndStatus(@RequestBody @Valid WithdrawCashReportParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        List<WithdrawCashReportDTO> dtos = new ArrayList<>();
        List<WithdrawCashReportBO> rntList = cashManageBackageService.selectWithdrawCashListByDateAndStatus(param);
        for (WithdrawCashReportBO bo : rntList) {
            WithdrawCashReportDTO dto = new WithdrawCashReportDTO();
            dto.setId(bo.getId());
            dto.setCashMoney(bo.getCashMoney());
            dto.setFinishDate(bo.getFinishDate());
            dto.setUserNum(bo.getUserNum());
            dtos.add(dto);
        }
        return successCreated(dtos);
    }

    @RequestMapping(value = "selectWithdrawCashTotalByDateAndStatus", method = RequestMethod.POST)
    public Result<WithdrawCashTotalReportDTO> selectWithdrawCashTotalByDateAndStatus(@RequestBody @Valid WithdrawCashReportParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        WithdrawCashTotalReportBO bo = cashManageBackageService.selectWithdrawCashTotalByDateAndStatus(param);
        WithdrawCashTotalReportDTO dto = new WithdrawCashTotalReportDTO();
        dto.setMemberCashMoney(bo.getMemberCashMoney());
        dto.setMerchantCashMoney(bo.getMerchantCashMoney());
        return successCreated(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "selectAgentWithdrawCashList")
    public Result<List<WithdrawCashReportDTO>> selectAgentWithdrawCashList(@RequestBody AgentWithdrawCashReportParam param) {
        List<WithdrawCashReportDTO> list = new ArrayList<>();
        WithdrawCashReportDTO cash;
        List<WithdrawCashReportBO> rntList = cashManageBackageService.selectAgentWithdrawCashList(param);
        for (WithdrawCashReportBO bo : rntList) {
            cash = new WithdrawCashReportDTO();
            cash.setId(bo.getId());
            cash.setCashMoney(bo.getCashMoney());
            cash.setFinishDate(bo.getFinishDate());
            cash.setUserNum(bo.getUserNum());
            list.add(cash);
        }
        return successCreated(list);
    }

    @RequestMapping(method = RequestMethod.POST, value = "selectAgentWithdrawCashTotal")
    public Result<WithdrawCashTotalReportDTO> selectAgentWithdrawCashTotal(@RequestBody AgentWithdrawCashReportParam param) {
        WithdrawCashTotalReportBO bo = cashManageBackageService.selectAgentWithdrawCashTotal(param);
        WithdrawCashTotalReportDTO dto = new WithdrawCashTotalReportDTO();
        dto.setMemberCashMoney(bo.getMemberCashMoney());
        dto.setMerchantCashMoney(bo.getMerchantCashMoney());
        return successCreated(dto);
    }

}
