package com.lawu.eshop.user.srv.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.eshop.user.srv.bo.QqLoginMemberBO;
import com.lawu.eshop.user.srv.domain.QqLoginMemberDO;
import com.lawu.eshop.user.srv.mapper.QqLoginMemberDOMapper;
import com.lawu.eshop.user.srv.service.QqLoginMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test.xml"})
public class QqUseMemberServiceImplTest {
	
	@Autowired
	private QqLoginMemberDOMapper qqLoginMemberDOMapper;
	
	@Autowired
	private QqLoginMemberService qqLoginMemberService;
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void weixinUserBind() {
		QqLoginMemberDO qqLoginMemberDO = new QqLoginMemberDO();
		qqLoginMemberDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		qqLoginMemberDO.setUserNum("M894378717298556928");
		qqLoginMemberDO.setNickname("E店用户");
		qqLoginMemberDO.setGender("男");
		qqLoginMemberDO.setFigureurl("http://q.qlogo.cn/qqapp/1106412506/E3C40482C9C1102C80F91E12FFEDDD14/40");
		qqLoginMemberDOMapper.insertSelective(qqLoginMemberDO);
		
		qqLoginMemberService.qqMemberBind(qqLoginMemberDO.getOpenid(), "18576620612", "head/img.jpg");
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveQQUser() {
		QqLoginMemberParam param = new QqLoginMemberParam();
		param.setOpenId("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		param.setNickname("E店用户");
		param.setFigureurl("http://q.qlogo.cn/qqapp/1106412506/E3C40482C9C1102C80F91E12FFEDDD14/40");
		param.setGender("男");
		qqLoginMemberService.QqLoginMemberSave(param);
		
		List<QqLoginMemberDO> list = qqLoginMemberDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void qqLogin() {
		
		QqLoginMemberDO qqLoginMemberDO = new QqLoginMemberDO();
		qqLoginMemberDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		qqLoginMemberDO.setUserNum("M894378717298556928");
		qqLoginMemberDO.setNickname("E店用户");
		qqLoginMemberDO.setGender("男");
		qqLoginMemberDO.setFigureurl("http://q.qlogo.cn/qqapp/1106412506/E3C40482C9C1102C80F91E12FFEDDD14/40");
		qqLoginMemberDOMapper.insertSelective(qqLoginMemberDO);
		
		QqLoginMemberBO qqLoginMemberBO = qqLoginMemberService.qqLogin(qqLoginMemberDO.getOpenid());
		Assert.assertNotNull(qqLoginMemberBO);
		
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void qqMemberBind() {
		QqLoginMemberDO qqLoginMemberDO = new QqLoginMemberDO();
		qqLoginMemberDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		qqLoginMemberDO.setNickname("E店用户");
		qqLoginMemberDO.setGender("男");
		qqLoginMemberDO.setFigureurl("http://q.qlogo.cn/qqapp/1106412506/E3C40482C9C1102C80F91E12FFEDDD14/40");
		qqLoginMemberDOMapper.insertSelective(qqLoginMemberDO);
		
		qqLoginMemberService.qqMemberBind(qqLoginMemberDO.getOpenid(), "18512412454", "head/img");
		
    }
	

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getHeadImg() {
		QqLoginMemberDO qqLoginMemberDO = new QqLoginMemberDO();
		qqLoginMemberDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		qqLoginMemberDO.setNickname("E店用户");
		qqLoginMemberDO.setGender("男");
		qqLoginMemberDO.setFigureurl("http://q.qlogo.cn/qqapp/1106412506/E3C40482C9C1102C80F91E12FFEDDD14/40");
		qqLoginMemberDOMapper.insertSelective(qqLoginMemberDO);
		
		String img = qqLoginMemberService.getHeadImg(qqLoginMemberDO.getOpenid());
		Assert.assertNotNull(img);
    }

	
}
