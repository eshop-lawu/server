package com.lawu.eshop.property.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.dto.BroadcastDTO;
import com.lawu.eshop.property.dto.BroadcastListDTO;
import com.lawu.eshop.property.param.BroadcastListParam;
import com.lawu.eshop.property.srv.bo.BroadcastListBO;
import com.lawu.eshop.property.srv.bo.IncomeSummaryBO;
import com.lawu.eshop.property.srv.service.IncomeSummaryService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 汇总收益
 */
@RestController
@RequestMapping(value = "incomeSummary/")
public class IncomeSummaryController extends BaseController {

    @Autowired
    private IncomeSummaryService incomeSummaryService;

    @RequestMapping(value = "getIncomeSummary/{userNum}", method = RequestMethod.GET)
    public Result<BroadcastDTO> getIncomeSummary(@PathVariable("userNum") String userNum) {
        List<IncomeSummaryBO> incomeSummaryBOList = incomeSummaryService.getIncomeSummary(userNum);
        if(incomeSummaryBOList == null || incomeSummaryBOList.isEmpty()){
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        BroadcastDTO broadcastDTO = new BroadcastDTO();
        for (IncomeSummaryBO incomeSummaryBO : incomeSummaryBOList){
            if(PayTypeEnum.BALANCE.getVal().equals(incomeSummaryBO.getIncomeType())){
                broadcastDTO.setBalanceIncome(incomeSummaryBO.getMoney());
            } else if(PayTypeEnum.POINT.getVal().equals(incomeSummaryBO.getIncomeType())){
                broadcastDTO.setPointIncome(incomeSummaryBO.getMoney());
            }
        }
        return successGet(broadcastDTO);
    }

    /**
     * 播报详情列表
     * @param userNum
     * @param broadcastListParam
     * @return
     */
    @RequestMapping(value = "getBroadcastList/{userNum}", method = RequestMethod.POST)
    public Result<Page<BroadcastListDTO>> getBroadcastList(@PathVariable("userNum") String userNum, @RequestBody BroadcastListParam broadcastListParam) {
        Page<BroadcastListBO> broadcastListBOPage = incomeSummaryService.getBroadcastList(userNum,broadcastListParam);

        Page<BroadcastListDTO> page = new Page<>();
        page.setCurrentPage(broadcastListBOPage.getCurrentPage());
        page.setTotalCount(broadcastListBOPage.getTotalCount());

        List<BroadcastListBO> broadcastListBOList = broadcastListBOPage.getRecords();
        if(broadcastListBOList == null || broadcastListBOList.isEmpty()){
            return successGet(page);
        }

        List<BroadcastListDTO> broadcastListDTOList = new ArrayList<>();
        for (BroadcastListBO broadcastListBO : broadcastListBOList){
            BroadcastListDTO broadcastListDTO = new BroadcastListDTO();
            broadcastListDTO.setDate(broadcastListBO.getDate());
            broadcastListDTO.setIncome(broadcastListBO.getIncome());
            broadcastListDTO.setPayTypeEnum(broadcastListBO.getPayTypeEnum());
            if(PayTypeEnum.BALANCE.getVal().equals(broadcastListBO.getPayTypeEnum().getVal())){
                broadcastListDTO.setTitle("今日收益结算");
                broadcastListDTO.setUnit("元");
            } else if(PayTypeEnum.POINT.getVal().equals(broadcastListBO.getPayTypeEnum().getVal())){
                broadcastListDTO.setTitle("积分收益结算");
                broadcastListDTO.setUnit("积分");
            }
            broadcastListDTOList.add(broadcastListDTO);
        }
        page.setRecords(broadcastListDTOList);
        return successGet(page);
    }

}
