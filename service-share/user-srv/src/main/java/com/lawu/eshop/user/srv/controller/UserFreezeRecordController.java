package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.user.param.UserFreezeRecordParam;
import com.lawu.eshop.user.srv.service.UserFreezeRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/9/11.
 */
@RestController
@RequestMapping(value = "userFreezeRecord/")
public class UserFreezeRecordController extends BaseController {

    @Autowired
    private UserFreezeRecordService userFreezeRecordService;

    /**
     * 保存用户冻结记录
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveUserFreezeRecord", method = RequestMethod.POST)
    public Result saveUserFreezeRecord(@RequestBody UserFreezeRecordParam param) {
        userFreezeRecordService.saveUserFreezeRecord(param);
        return successCreated();
    }
}
