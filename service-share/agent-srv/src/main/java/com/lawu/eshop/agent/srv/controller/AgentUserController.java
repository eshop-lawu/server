package com.lawu.eshop.agent.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.agent.dto.AgentUserListDTO;
import com.lawu.eshop.agent.dto.LoginUserDTO;
import com.lawu.eshop.agent.param.AgentUserListParam;
import com.lawu.eshop.agent.param.AgentUserParam;
import com.lawu.eshop.agent.srv.bo.AgentUserBO;
import com.lawu.eshop.agent.srv.converter.AgentUserConverter;
import com.lawu.eshop.agent.srv.service.AgentUserService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/9
 */
@RestController
@RequestMapping(value = "user/")
public class AgentUserController extends BaseController {

    @Autowired
    private AgentUserService agentUserService;

    @RequestMapping(value = "withPwd/{account}", method = RequestMethod.POST)
    public Result<LoginUserDTO> login(@PathVariable("account") String account, @RequestParam("pwd") String pwd) {
        AgentUserBO userBO = agentUserService.find(account, pwd);
        if (userBO == null) {
            return successGet(ResultCode.MEMBER_WRONG_PWD);
        }
        return successCreated(AgentUserConverter.convertDTO(userBO));
    }

    /**
     * 分配代理商账号
     * @param param
     * @return
     */
    @RequestMapping(value = "addAgentUser", method = RequestMethod.POST)
    public Result addAgentUser(@RequestBody AgentUserParam param){
        boolean isExitAccount = agentUserService.findUserByAccount(param.getAccount());
        if(isExitAccount){
            //存在
            return successCreated(ResultCode.AGENT_ACCOUNT_EXIST);
        }
        boolean isExitMobile = agentUserService.findUserByMobile(param.getMobile());
        if(isExitMobile){
            //存在
            return successCreated(ResultCode.AGENT_MOBILE_EXIST);
        }
        agentUserService.addAgentUser(param);
        return successCreated();
    }

    /**
     * 查询代理商列表
     * @param param
     * @return
     */
    @RequestMapping(value = "getAgentUserList", method = RequestMethod.POST)
    public Result<Page<AgentUserListDTO>> getAgentUserList(@RequestBody AgentUserListParam param) {

        Page<AgentUserBO> userBOPage = agentUserService.getAgentUserList(param);
        Page<AgentUserListDTO> userListDTOPage = new Page<>();
        List<AgentUserListDTO> userListDTOS = AgentUserConverter.convertDTOS(userBOPage.getRecords());
        userListDTOPage.setCurrentPage(userBOPage.getCurrentPage());
        userListDTOPage.setTotalCount(userBOPage.getTotalCount());
        userListDTOPage.setRecords(userListDTOS);
        return successCreated(userListDTOPage);
    }

    /**
     * 查询单个代理商信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getAgentUser/{id}", method = RequestMethod.GET)
    public Result<AgentUserListDTO> getAgentUser(@PathVariable("id") Long id) {
        AgentUserBO agentUserBO = agentUserService.getAgentUser(id);
        if (agentUserBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        List<AgentUserBO> list = new ArrayList<>();
        list.add(agentUserBO);
        return successGet(AgentUserConverter.convertDTOS(list).get(0));
    }

    @RequestMapping(value = "editAgentUser/{id}", method = RequestMethod.PUT)
    public Result editAgentUser(@PathVariable("id") Long id, @RequestBody AgentUserParam param){
        boolean isExitAccount = agentUserService.findUserByAccountAndId(id,param.getAccount());
        if(isExitAccount){
            //存在
            return successCreated(ResultCode.AGENT_ACCOUNT_EXIST);
        }
        boolean isExitMobile = agentUserService.findUserByMobileAndId(id,param.getMobile());
        if(isExitMobile){
            //存在
            return successCreated(ResultCode.AGENT_MOBILE_EXIST);
        }
        agentUserService.editAgentUser(id,param);
        return successCreated();
    }


}
