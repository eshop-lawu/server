package com.lawu.eshop.agent.srv.service;

import com.lawu.eshop.agent.param.AgentUserListParam;
import com.lawu.eshop.agent.param.AgentUserParam;
import com.lawu.eshop.agent.srv.bo.AgentUserBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
public interface AgentUserService {

    AgentUserBO find(String account, String pwd);

    void addAgentUser(AgentUserParam param);

    boolean findUserByAccount(String account);

    boolean findUserByMobile(String mobile);

    Page<AgentUserBO> getAgentUserList(AgentUserListParam param);

    AgentUserBO getAgentUser(Long id);

    void editAgentUser(Long id, AgentUserParam param);

    boolean findUserByAccountAndId(Long id, String account);

    boolean findUserByMobileAndId(Long id, String mobile);
}
