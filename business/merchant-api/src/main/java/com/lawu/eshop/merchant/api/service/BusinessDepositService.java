package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.dto.BusinessDepositDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.BusinessDepositDetailDTO;
import com.lawu.eshop.property.dto.BusinessDepositInitDTO;
import com.lawu.eshop.property.param.BusinessDepositSaveDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositParam;
import com.lawu.framework.web.Result;

/**
 * <p>
 * Description: 商家保证金
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月15日 上午11:04:43
 */
@FeignClient(value = "property-srv")
public interface BusinessDepositService {

    /**
     * 初始化商家保证金记录
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "businessDeposit/save")
    Result<BusinessDepositInitDTO> save(@RequestBody BusinessDepositSaveDataParam param);

    /**
     * 商家我的保证金
     *
     * @param businessId
     * @return
     */
    @RequestMapping(value = "businessDeposit/selectDeposit/{businessId}", method = RequestMethod.GET)
    Result<BusinessDepositDetailDTO> selectDeposit(@PathVariable("businessId") String businessId);

    /**
     * 申请退保证金
     *
     * @param dparam
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "businessDeposit/refundDeposit", method = RequestMethod.POST)
    Result refundDeposit(@RequestBody BusinessRefundDepositDataParam dparam);

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "businessDeposit/getDepositValue", method = RequestMethod.GET)
    Result getDepositValue();

    /**
     * 查看保证金状态
     *
     * @param depositId
     * @return
     */
    @RequestMapping(value = "businessDeposit/getDepositStatusById/{depositId}", method = RequestMethod.GET)
    Result<BusinessDepositStatusEnum> getDepositStatusById(@PathVariable("depositId") Long depositId);

    @RequestMapping(value = "businessDeposit/getDepositById/{depositId}", method = RequestMethod.GET)
    Result<BusinessDepositDTO> getDepositById(@PathVariable("depositId") Long depositId);
}
