package com.lawu.eshop.agent.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.agent.param.AgentUserListParam;
import com.lawu.eshop.agent.param.AgentUserParam;
import com.lawu.eshop.agent.srv.AgentSrvApplicationTest;
import com.lawu.eshop.agent.srv.bo.AgentUserBO;
import com.lawu.eshop.agent.srv.domain.AgentUserDO;
import com.lawu.eshop.agent.srv.mapper.AgentUserDOMapper;
import com.lawu.eshop.agent.srv.service.AgentUserService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgentSrvApplicationTest.class)
public class AgentUserServiceImplTest {

    @Autowired
    private AgentUserDOMapper agentUserDOMapper;

    @Autowired
    private AgentUserService agentUserService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void find(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        AgentUserBO agentUserBO = agentUserService.find("111","123456");
        Assert.assertNotNull(agentUserBO);
        Assert.assertTrue("name".equals(agentUserBO.getName()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void addAgentUser(){
        AgentUserParam param = new AgentUserParam();
        param.setPwd("123456");
        param.setMobile("123456");
        param.setRegionPath("44/4403");
        param.setRegionName("广东省深圳市");
        param.setAccount("a123456");
        param.setName("张三");
        agentUserService.addAgentUser(param);
        List<AgentUserDO> list = agentUserDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(1 == list.size());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findUserByAccount(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        boolean flag = agentUserService.findUserByAccount("111");
        Assert.assertEquals(true,flag);
        boolean flag2 = agentUserService.findUserByAccount("222");
        Assert.assertEquals(false,flag2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findUserByMobile(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        boolean flag = agentUserService.findUserByMobile("213123");
        Assert.assertEquals(true,flag);
        boolean flag2 = agentUserService.findUserByMobile("222");
        Assert.assertEquals(false,flag2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findUserByAccountAndId(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        boolean flag = agentUserService.findUserByAccountAndId(2L,"111");
        Assert.assertEquals(true,flag);
        boolean flag2 = agentUserService.findUserByAccountAndId(2L,"222");
        Assert.assertEquals(false,flag2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findUserByMobileAndId(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        boolean flag = agentUserService.findUserByMobileAndId(2L,"213123");
        Assert.assertEquals(true,flag);
        boolean flag2 = agentUserService.findUserByMobileAndId(2L,"222");
        Assert.assertEquals(false,flag2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAgentUserList() {
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        AgentUserListParam param = new AgentUserListParam();
        param.setAccount("111");
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<AgentUserBO> page = agentUserService.getAgentUserList(param);
        Assert.assertTrue(1 == page.getTotalCount());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void getAgentUser(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        AgentUserBO userBO = agentUserService.getAgentUser(agentUserDO.getId());
        Assert.assertTrue("name".equals(userBO.getName()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void editAgentUser(){
        AgentUserDO agentUserDO = new AgentUserDO();
        agentUserDO.setAccount("111");
        agentUserDO.setName("name");
        agentUserDO.setNum("213");
        agentUserDO.setPwd("a37a5d82db1ea8957812c03bc9e51c81d33435dc4cd06279");
        agentUserDO.setMobile("213123");
        agentUserDO.setGmtCreate(new Date());
        agentUserDO.setStatus(StatusEnum.VALID.getValue());
        agentUserDO.setRegionPath("44/4403");
        agentUserDO.setRegionName("广东省深圳市");
        agentUserDOMapper.insertSelective(agentUserDO);
        AgentUserParam param = new AgentUserParam();
        param.setMobile("123456");
        param.setRegionPath("44/4403");
        param.setRegionName("广东省深圳市");
        param.setAccount("a123456");
        param.setName("张三");
        agentUserService.editAgentUser(agentUserDO.getId(), param);

        List<AgentUserDO> list = agentUserDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(1 == list.size());
        Assert.assertTrue("张三".equals(list.get(0).getName()));

    }
}
