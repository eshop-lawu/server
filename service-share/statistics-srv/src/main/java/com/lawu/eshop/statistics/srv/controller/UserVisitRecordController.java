package com.lawu.eshop.statistics.srv.controller;

import com.lawu.eshop.statistics.param.UserVisitRecordParam;
import com.lawu.eshop.statistics.srv.service.UserVisitRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
@RestController
@RequestMapping(value = "visitRecord/")
public class UserVisitRecordController extends BaseController{

    @Autowired
    private UserVisitRecordService userVisitRecordService;

    @RequestMapping(value = "addUserVisitRecord" ,method = RequestMethod.POST)
    Result addUserVisitRecord(@RequestBody UserVisitRecordParam userVisitRecordParam){
        userVisitRecordService.addUserVisitRecord(userVisitRecordParam);
       return successCreated();
    }
}
