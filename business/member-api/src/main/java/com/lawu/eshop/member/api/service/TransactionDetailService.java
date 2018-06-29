package com.lawu.eshop.member.api.service;

import com.lawu.eshop.property.dto.TransactionDetailInfoMemberDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.MonthlyBillDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMemberDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMemberDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForMemberParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMemberForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/29
 */
@FeignClient(value = "property-srv", path = "transactionDetail/")
public interface TransactionDetailService {

    /**
     * 根据用户编号和查询参数查询交易明细
     * 
     * @param userNum
     *            用户编号
     * @param param
     *            查询参数
     * @return
     */
    @Deprecated
    @RequestMapping(value = "findPageByUserNumForMember/{userNum}", method = RequestMethod.POST)
    public Result<Page<TransactionDetailToMemberDTO>> findPageByUserNumForMember(
            @PathVariable("userNum") String userNum, @RequestBody TransactionDetailQueryForMemberParam param);

    /**
     * 根据会员编号和查询参数分页查询交易明细
     * 
     * @param userNum 会员编号
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    @RequestMapping(value = "page/member/{userNum}", method = RequestMethod.POST)
    Result<Page<TransactionDetailOfMemberDTO>> page(@PathVariable("userNum") String userNum, @RequestBody TransactionDetailQueryForMemberForeignParam param);
    
    /**
     * 根据会员编号和查询参数月结账单
     * 
     * @param userNum 会员编号
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    @RequestMapping(value = "monthlyBill/member/{userNum}", method = RequestMethod.POST)
    Result<MonthlyBillDTO> monthlyBill(@PathVariable("userNum") String userNum, @RequestBody TransactionDetailMonthlyBillOfMemberForeignParam param);

    /**
     * 交易详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getById/member/{id}", method = RequestMethod.GET)
    Result<TransactionDetailInfoMemberDTO> getById(@PathVariable("id") Long id);
}
