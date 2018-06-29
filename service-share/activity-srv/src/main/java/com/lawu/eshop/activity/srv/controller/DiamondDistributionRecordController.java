package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.DiamondDistributionRecordDTO;
import com.lawu.eshop.activity.query.DiamondDistributionRecordQueryParam;
import com.lawu.eshop.activity.srv.bo.DiamondDistributionRecordBO;
import com.lawu.eshop.activity.srv.converter.DiamondDistributionRecordConverter;
import com.lawu.eshop.activity.srv.servcie.DiamondDistributionRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 钻石分配记录控制器
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
@RestController
@RequestMapping(value = "diamondDistributionRecord/")
public class DiamondDistributionRecordController extends BaseController {
    
    @Autowired
    private DiamondDistributionRecordService diamondDistributionRecordService;

    /**
     * 分页查询钻石分配记录
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    @RequestMapping(value = "page",method = RequestMethod.PUT)
    public Result<Page<DiamondDistributionRecordDTO>> page(@RequestBody @Validated DiamondDistributionRecordQueryParam param){
        Page<DiamondDistributionRecordBO> page = diamondDistributionRecordService.page(param);
        Page<DiamondDistributionRecordDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(DiamondDistributionRecordConverter.convertDTOList(page.getRecords()));
        return successGet(model);
    }
}
