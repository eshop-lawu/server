package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.RichDiamondRecordDTO;
import com.lawu.eshop.activity.query.DiamondRecordQueryParam;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.eshop.activity.srv.converter.RichDiamondRecordConvert;
import com.lawu.eshop.activity.srv.servcie.RichDiamondRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 钻石记录控制器
 * @author jiangxinjun
 * @createDate 2018年6月7日
 * @updateDate 2018年6月7日
 */
@RestController
@RequestMapping(value = "richDiamondRecord/")
public class RichDiamondRecordController extends BaseController {
    
    @Autowired
    private RichDiamondRecordService richDiamondRecordService;

    /**
     * 分页查询钻石分配记录
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年6月7日
     * @updateDate 2018年6月7日
     */
    @RequestMapping(value = "page",method = RequestMethod.PUT)
    public Result<Page<RichDiamondRecordDTO>> page(@RequestBody @Validated DiamondRecordQueryParam param){
        Page<RichDiamondRecordBO> page = richDiamondRecordService.page(param);
        Page<RichDiamondRecordDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(RichDiamondRecordConvert.convert(page.getRecords()));
        return successGet(model);
    }
}
