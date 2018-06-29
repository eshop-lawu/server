package com.lawu.eshop.property.srv.controller;

import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.common.param.CommissionJobParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.param.CommissionParam;
import com.lawu.eshop.property.srv.service.CommissionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description: 计算提成
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月26日 下午8:22:53
 */
@RestController
@RequestMapping(value = "commission/")
public class CommissionController extends BaseController {

    @Autowired
    private CommissionService commissionService;

    /**
     * 计算提成
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "calculation", method = RequestMethod.POST)
    public int calculation(@RequestBody CommissionJobParam param) {
        int retCode = commissionService.calculation(param);
        return retCode;
    }

    /**
     * 计算提成（重构）
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "calculationRefactor", method = RequestMethod.POST)
    public Result calculationRefactor(@RequestBody CommissionParam param) {
        try {
            List<CommissionInvitersUserDTO> inviters = param.getInviters();
            CommissionEnum commissionEnum = param.getCommissionEnum();
            if (commissionEnum == CommissionEnum.AD) {
                commissionService.calculationAd(param, inviters,commissionEnum);
            } else if(commissionEnum == CommissionEnum.SHOPPING || commissionEnum == CommissionEnum.PAY){
                commissionService.calculationSalesAndVolume(param, inviters,commissionEnum);
            }
        }catch (Exception e){
            e.printStackTrace();
            return successCreated(ResultCode.FAIL,e.getMessage());
        }
        return successCreated(ResultCode.SUCCESS);
    }


}
