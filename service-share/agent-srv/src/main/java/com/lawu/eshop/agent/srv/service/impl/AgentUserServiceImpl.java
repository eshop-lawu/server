package com.lawu.eshop.agent.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.agent.param.AgentUserListParam;
import com.lawu.eshop.agent.param.AgentUserParam;
import com.lawu.eshop.agent.srv.bo.AgentUserBO;
import com.lawu.eshop.agent.srv.converter.AgentUserConverter;
import com.lawu.eshop.agent.srv.domain.AgentUserDO;
import com.lawu.eshop.agent.srv.domain.AgentUserDOExample;
import com.lawu.eshop.agent.srv.mapper.AgentUserDOMapper;
import com.lawu.eshop.agent.srv.service.AgentUserService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.PwdUtil;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
@Service
public class AgentUserServiceImpl implements AgentUserService {

    @Autowired
    private AgentUserDOMapper agentUserDOMapper;

    @Override
    public AgentUserBO find(String account, String pwd) {
        AgentUserDOExample example = new AgentUserDOExample();
        example.createCriteria().andAccountEqualTo(account).andStatusEqualTo(StatusEnum.VALID.getValue());
        List<AgentUserDO> userDOS = agentUserDOMapper.selectByExample(example);
        if (!userDOS.isEmpty()) {
            if (PwdUtil.verify(pwd, userDOS.get(0).getPwd())) {
                return AgentUserConverter.convertBO(userDOS.get(0));
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAgentUser(AgentUserParam param) {
        String userNum = IdWorkerHelperImpl.generate(BizIdType.AGENT);
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setMobile(param.getMobile());
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setGmtModified(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionName(param.getRegionName());
        agentUserDO.setAccount(param.getAccount());
        agentUserDO.setName(param.getName());
        agentUserDO.setRegionPath(param.getRegionPath());
        agentUserDO.setNum(userNum);
        agentUserDO.setPwd(PwdUtil.generate(param.getPwd()));
        agentUserDOMapper.insertSelective(agentUserDO);
    }

    @Override
    public boolean findUserByAccount(String account) {
        AgentUserDOExample example = new AgentUserDOExample();
        example.createCriteria().andAccountEqualTo(account).andStatusEqualTo(StatusEnum.VALID.getValue());
        List<AgentUserDO> userDOS = agentUserDOMapper.selectByExample(example);
        if(!userDOS.isEmpty()){
            //存在
            return true;
        }
        return false;
    }

    @Override
    public boolean findUserByMobile(String mobile) {
        AgentUserDOExample example = new AgentUserDOExample();
        example.createCriteria().andMobileEqualTo(mobile).andStatusEqualTo(StatusEnum.VALID.getValue());
        List<AgentUserDO> userDOS = agentUserDOMapper.selectByExample(example);
        if(!userDOS.isEmpty()){
            //存在
            return true;
        }
        return false;
    }

    @Override
    public Page<AgentUserBO> getAgentUserList(AgentUserListParam param) {
        AgentUserDOExample example = new AgentUserDOExample();
        if (StringUtils.isNotEmpty(param.getAccount())) {
            example.createCriteria().andAccountEqualTo(param.getAccount());
        }
        example.setOrderByClause("id desc");
        Page<AgentUserBO> pages = new Page<>();
        pages.setCurrentPage(param.getCurrentPage());
        pages.setTotalCount(agentUserDOMapper.countByExample(example));
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

        List<AgentUserDO> list = agentUserDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (list.isEmpty()) {
            pages.setRecords(new ArrayList<>());
            return pages;
        }
        List<AgentUserBO> agentUserBOS = new ArrayList<>();
        for (AgentUserDO agentUserDO : list) {
            AgentUserBO agentUserBO = AgentUserConverter.convertBO(agentUserDO);
            agentUserBOS.add(agentUserBO);
        }
        pages.setRecords(agentUserBOS);
        return pages;
    }

    @Override
    public AgentUserBO getAgentUser(Long id) {
        AgentUserDO agentUserDO = agentUserDOMapper.selectByPrimaryKey(id);
        return AgentUserConverter.convertBO(agentUserDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editAgentUser(Long id, AgentUserParam param) {
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setRegionPath(param.getRegionPath());
        agentUserDO.setRegionName(param.getRegionName());
        agentUserDO.setName(param.getName());
        agentUserDO.setAccount(param.getAccount());
        agentUserDO.setMobile(param.getMobile());
        agentUserDO.setGmtModified(new Date());
        agentUserDO.setId(id);
        agentUserDOMapper.updateByPrimaryKeySelective(agentUserDO);
    }

    @Override
    public boolean findUserByAccountAndId(Long id, String account) {
        AgentUserDOExample example = new AgentUserDOExample();
        example.createCriteria().andAccountEqualTo(account).andStatusEqualTo(StatusEnum.VALID.getValue()).andIdNotEqualTo(id);
        List<AgentUserDO> userDOS = agentUserDOMapper.selectByExample(example);
        if(!userDOS.isEmpty()){
            //存在
            return true;
        }
        return false;
    }

    @Override
    public boolean findUserByMobileAndId(Long id, String mobile) {
        AgentUserDOExample example = new AgentUserDOExample();
        example.createCriteria().andMobileEqualTo(mobile).andStatusEqualTo(StatusEnum.VALID.getValue()).andIdNotEqualTo(id);
        List<AgentUserDO> userDOS = agentUserDOMapper.selectByExample(example);
        if(!userDOS.isEmpty()){
            //存在
            return true;
        }
        return false;
    }
}
