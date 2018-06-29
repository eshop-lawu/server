package com.lawu.eshop.beh.analyze.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.beh.analyze.dto.InviteAbnormalDecideRecordDTO;
import com.lawu.eshop.beh.analyze.srv.bo.InviteAbnormalDecideRecordBO;
import com.lawu.eshop.beh.analyze.srv.convert.InviteAbnormalDecideRecordConverter;
import com.lawu.eshop.beh.analyze.srv.service.InviteAbnormalDecideRecordService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 注册异常判定记录控制器
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@RestController
@RequestMapping(value = "inviteAbnormalDecideRecord/")
public class InviteAbnormalDecideRecordController extends BaseController {
    
    @Autowired
    private InviteAbnormalDecideRecordService inviteAbnormalDecideRecordService;
    
    /**
     * 根据id查询注册异常判定记录
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月19日
     * @updateDate 2018年1月19日
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result<InviteAbnormalDecideRecordDTO> get(@PathVariable("id") Long id) {
        InviteAbnormalDecideRecordBO inviteAbnormalDecideRecordBO = inviteAbnormalDecideRecordService.get(id);
        if (inviteAbnormalDecideRecordBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND, "注册异常判定记录不存在");
        }
        return successCreated(InviteAbnormalDecideRecordConverter.convert(inviteAbnormalDecideRecordBO));
    }
    
    /**
     * 标识为不处理
     * 
     * @author jiangxinjun
     * @createDate 2018年1月19日
     * @updateDate 2018年1月19日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "notProcessed/{id}", method = RequestMethod.PUT)
    public Result notProcessed(@PathVariable("id") Long id) {
        inviteAbnormalDecideRecordService.notProcessed(id);
        return successCreated();
    }
    
    /**
     * 标识为冻结
     * 
     * @author jiangxinjun
     * @createDate 2018年1月19日
     * @updateDate 2018年1月19日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "freeze/{id}", method = RequestMethod.PUT)
    public Result freeze(@PathVariable("id") Long id) {
        inviteAbnormalDecideRecordService.freeze(id);
        return successCreated();
    }
}
