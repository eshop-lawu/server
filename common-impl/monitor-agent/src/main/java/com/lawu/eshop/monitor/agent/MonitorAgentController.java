package com.lawu.eshop.monitor.agent;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2017/10/19
 */
@RestController
@RequestMapping(value = "monitorAgent/")
public class MonitorAgentController extends BaseController {


    @RequestMapping(value = "check", method = RequestMethod.GET)
    public Result check(String rev) {
        return successGet(rev);
    }

}
