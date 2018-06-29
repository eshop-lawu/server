package com.lawu.eshop.property.srv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.dto.BusinessDepositDTO;
import com.lawu.eshop.property.srv.bo.BusinessDepositBO;
import com.lawu.eshop.property.srv.converter.BusinessDepositConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.BusinessDepositOperEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.dto.BusinessDepositDetailDTO;
import com.lawu.eshop.property.dto.BusinessDepositInitDTO;
import com.lawu.eshop.property.dto.BusinessDepositQueryDTO;
import com.lawu.eshop.property.param.BusinessDepositOperDataParam;
import com.lawu.eshop.property.param.BusinessDepositQueryDataParam;
import com.lawu.eshop.property.param.BusinessDepositSaveDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.srv.bo.BusinessDepositDetailBO;
import com.lawu.eshop.property.srv.bo.BusinessDepositQueryBO;
import com.lawu.eshop.property.srv.service.BusinessDepositService;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;

/**
 * <p>
 * Description: 商家保证金
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月15日 上午10:57:34
 */
@RestController
@RequestMapping(value = "businessDeposit/")
public class BusinessDepositController extends BaseController {

    @Autowired
    private BusinessDepositService businessDepositService;
    @Autowired
    private PropertyService propertyService;

    /**
     * 初始化保证金记录
     *
     * @param param
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result<BusinessDepositInitDTO> save(@RequestBody @Valid BusinessDepositSaveDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        String deposit = propertyService.getValue(PropertyType.MERCHANT_BONT);
        param.setDeposit(deposit);
        BusinessDepositInitDTO dto = businessDepositService.save(param);
        return successCreated(dto);
    }

    /**
     * 处理第三方支付回调
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "doHandleDepositNotify", method = RequestMethod.POST)
    public Result doHandleDepositNotify(@RequestBody @Valid NotifyCallBackParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        return businessDepositService.doHandleDepositNotify(param);
    }

    /**
     * 运营平台查询保证金
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectDepositList", method = RequestMethod.POST)
    public Result<Page<BusinessDepositQueryDTO>> selectDepositList(@RequestBody BusinessDepositQueryDataParam param, BindingResult result) throws Exception {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        Page<BusinessDepositQueryBO> page = businessDepositService.selectDepositList(param);
        List<BusinessDepositQueryBO> list = page.getRecords();
        List<BusinessDepositQueryDTO> newList = new ArrayList<BusinessDepositQueryDTO>();
        for (BusinessDepositQueryBO bo : list) {
            BusinessDepositQueryDTO dto = new BusinessDepositQueryDTO();
            BeanUtil.copyProperties(bo, dto);
            newList.add(dto);
        }
        Page<BusinessDepositQueryDTO> pageResult = new Page<BusinessDepositQueryDTO>();
        pageResult.setTotalCount(page.getTotalCount());
        pageResult.setCurrentPage(page.getCurrentPage());
        pageResult.setRecords(newList);
        return successCreated(pageResult);
    }

    /**
     * 运营平台处理：核实、受理、成功、失败
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "updateBusinessDeposit", method = RequestMethod.POST)
    public Result updateBusinessDeposit(@RequestBody BusinessDepositOperDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        if (BusinessDepositOperEnum.REFUND_FAILURE.getVal().equals(param.getBusinessDepositOperEnum().getVal())
                && (param.getFailReason() == null || "".equals(param.getFailReason()))) {
            return successCreated(ResultCode.CASH_BACKAGE_FAILURE_REASON_NULL);
        }
        int retCode = businessDepositService.updateBusinessDeposit(param);
        return successCreated(retCode);

    }

    /**
     * 商家我的保证金
     *
     * @param businessId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectDeposit/{businessId}", method = RequestMethod.GET)
    public Result<BusinessDepositDetailDTO> selectDeposit(@PathVariable("businessId") String businessId) throws Exception {
        BusinessDepositDetailBO bo = businessDepositService.selectDeposit(businessId);
        if (bo == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        BusinessDepositDetailDTO dto = new BusinessDepositDetailDTO();
        BeanUtil.copyProperties(bo, dto);
        return successCreated(dto);

    }

    /**
     * 商家申请退保证金
     *
     * @param dparam
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "refundDeposit", method = RequestMethod.POST)
    public Result refundDeposit(@RequestBody BusinessRefundDepositDataParam dparam, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        int retCode = businessDepositService.refundDeposit(dparam);
        return successCreated(retCode);

    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getDepositValue", method = RequestMethod.GET)
    public Result getDepositValue() {
        String deposit = propertyService.getValue(PropertyType.MERCHANT_BONT);
        return successCreated(deposit);

    }

    @RequestMapping(value = "getDepositStatusById/{depositId}", method = RequestMethod.GET)
    public Result<BusinessDepositStatusEnum> getDepositStatusById(@PathVariable("depositId") Long depositId) {
        BusinessDepositStatusEnum businessDepositStatusEnum = businessDepositService.getDepositStatusById(depositId);
        return successCreated(businessDepositStatusEnum);

    }

    @RequestMapping(value = "getDepositById/{depositId}", method = RequestMethod.GET)
    public Result<BusinessDepositDTO> getDepositById(@PathVariable("depositId") Long depositId) {
        BusinessDepositBO businessDepositBO = businessDepositService.getDepositById(depositId);
        return successCreated(BusinessDepositConverter.convert(businessDepositBO));

    }
}
