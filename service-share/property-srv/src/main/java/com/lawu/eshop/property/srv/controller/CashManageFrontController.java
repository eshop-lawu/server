package com.lawu.eshop.property.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.lawu.eshop.property.dto.CashFeeParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.dto.CashFeeDTO;
import com.lawu.eshop.property.dto.WithdrawCashDetailDTO;
import com.lawu.eshop.property.dto.WithdrawCashQueryDTO;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashChargeDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.eshop.property.srv.bo.WithdrawCashBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashDetailBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashQueryBO;
import com.lawu.eshop.property.srv.converter.WithdrawCashBOConverter;
import com.lawu.eshop.property.srv.service.CashManageFrontService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;

/**
 * <p>
 * Description: 前端用户提现管理
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月5日 下午2:44:35
 */
@RestController
@RequestMapping(value = "cashFront/")
public class CashManageFrontController extends BaseController {

    @Autowired
    private CashManageFrontService cashManageService;

    /**
     * 用户、商家提现操作
     *
     * @param cash
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody @Valid CashDataParam cash, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        return successCreated(cashManageService.save(cash));
    }

    /**
     * 用户、商家提现明细
     *
     * @param cparam
     * @return
     */
    @RequestMapping(value = "findCashList", method = RequestMethod.POST)
    public Result<Page<WithdrawCashQueryDTO>> findCashList(@RequestBody CashBillDataParam cparam) {
        Page<WithdrawCashQueryBO> page = cashManageService.findCashList(cparam);
        List<WithdrawCashQueryBO> cbos = page.getRecords();
        List<WithdrawCashQueryDTO> dtos = new ArrayList<WithdrawCashQueryDTO>();
        for (WithdrawCashQueryBO bo : cbos) {
            WithdrawCashQueryDTO dto = new WithdrawCashQueryDTO();
            dto.setId(bo.getId());
            dto.setTitle(bo.getTitle());
            dto.setCashMoney(bo.getCashMoney());
            dto.setCashStatusEnum(bo.getCashStatusEnum());
            dto.setCdate(bo.getCdate());
            dto.setAcceptDate(bo.getAcceptDate());
            dto.setFinishDate(bo.getFinishDate());
            dto.setRemark(bo.getRemark());
            dto.setActualCashMoney(bo.getActualCashMoney() == null ? null : bo.getActualCashMoney().setScale(2, BigDecimal.ROUND_DOWN));
            dto.setChargedMoney(bo.getChargedMoney());
            dtos.add(dto);
        }
        Page<WithdrawCashQueryDTO> pageResult = new Page<WithdrawCashQueryDTO>();
        pageResult.setTotalCount(page.getTotalCount());
        pageResult.setCurrentPage(page.getCurrentPage());
        pageResult.setRecords(dtos);
        return successCreated(pageResult);
    }

    /**
     * 用户、商家提现详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "cashDetail", method = RequestMethod.GET)
    public Result<WithdrawCashDetailDTO> cashDetail(@RequestParam Long id) throws Exception {
        if (id == null) {
            return successGet(ResultCode.ID_EMPTY);
        }
        WithdrawCashDetailBO cashDetailBO = cashManageService.cashDetail(id);
        if (cashDetailBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        WithdrawCashDetailDTO dto = new WithdrawCashDetailDTO();
        BeanUtil.copyProperties(cashDetailBO, dto);

        return successGet(dto);
    }

    /**
     * 查询交易明细 如果交易类型为提现，需要知道提现的状态 查询提现明细状态
     *
     * @param ids 提现id列表
     * @return
     */
    @RequestMapping(value = "findCashDetailStatus", method = RequestMethod.GET)
    public Result<List<WithdrawCashStatusDTO>> findCashDetailStatus(@RequestParam List<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return successGet(ResultCode.ID_EMPTY);
        }

        List<WithdrawCashBO> withdrawCashBOList = cashManageService.list(ids);

        if (withdrawCashBOList == null || withdrawCashBOList.isEmpty()) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }

        return successGet(WithdrawCashBOConverter.convertWithdrawCashStatusDTOList(withdrawCashBOList));
    }

    /**
     * 判断用户的银行卡是否存在提现申请
     *
     * @param userNum
     * @param bankAccountId
     * @return
     */
    @RequestMapping(value = "isExistCash", method = RequestMethod.GET)
    public Result<Boolean> isExistCash(@RequestParam String userNum, @RequestParam Long bankAccountId) {
        Boolean flag = cashManageService.isExistCash(userNum, bankAccountId);
        return successCreated(flag);
    }

    /**
     * 申请计算手续费
     *
     * @param cash
     * @param result
     * @return
     */
    @RequestMapping(value = "calculationFee", method = RequestMethod.POST)
    public Result<CashFeeDTO> calculationFee(@RequestBody @Valid CashChargeDataParam cash, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successCreated(WithdrawCashBOConverter.convert(cashManageService.calculationFee(cash)));
    }


    /**
     * 获取提现实时提醒手续费和服务费参数
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getCalculationFeeParam", method = RequestMethod.GET)
    public Result<CashFeeParamDTO> getCalculationFeeParam(@RequestParam String userNum) {
        return successCreated(WithdrawCashBOConverter.convert(cashManageService.getCalculationFeeParam(userNum)));
    }
}
