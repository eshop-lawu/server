package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.eshop.user.srv.bo.WeixinUserBindBO;
import com.lawu.eshop.user.srv.bo.WeixinUserInfoBO;
import com.lawu.eshop.user.srv.bo.WxLoginMemberBO;
import com.lawu.eshop.user.srv.domain.WeixinUserDO;
import com.lawu.eshop.user.srv.mapper.WeixinUserDOMapper;
import com.lawu.eshop.user.srv.service.WeixinUserService;
import com.lawu.eshop.user.srv.service.WxLoginMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test.xml"})
public class WeixinUserServiceImplTest {
	
	@Autowired
	private WeixinUserDOMapper weixinUserDOMapper;
	
	@Autowired
	private WeixinUserService weixinUserService;
	
	@Autowired
	private WxLoginMemberService wxLoginMemberService;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void weixinUserService() {
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		weixinUserDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		weixinUserDO.setNickname("E店用户");
		weixinUserDO.setUserNum("M894378717298556928");
		weixinUserDO.setSex(1);
		weixinUserDO.setCity("");
		weixinUserDO.setProvince("安道尔");
		weixinUserDO.setCountry("");
		weixinUserDO.setHeadimgurl("");
		weixinUserDO.setLanguage("zh_CN");
		weixinUserDO.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		weixinUserDO.setGroupid(0l);
		weixinUserDO.setSubscribe(1);
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
		WeixinUserInfoBO  weixinUserInfoBO = weixinUserService.getWeixinUserInfo("M894378717298556928");
        Assert.assertNotNull(weixinUserInfoBO);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getWeixinUserIsBind() {
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		weixinUserDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		weixinUserDO.setNickname("E店用户");
		weixinUserDO.setUserNum("M894378717298556928");
		weixinUserDO.setSex(1);
		weixinUserDO.setCity("");
		weixinUserDO.setProvince("安道尔");
		weixinUserDO.setCountry("");
		weixinUserDO.setHeadimgurl("");
		weixinUserDO.setLanguage("zh_CN");
		weixinUserDO.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		weixinUserDO.setGroupid(0l);
		weixinUserDO.setSubscribe(1);
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
		WeixinUserBindBO  weixinUserBindBO = weixinUserService.getWeixinUserIsBind(weixinUserDO.getOpenid());
        Assert.assertNotNull(weixinUserBindBO);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void weixinUserBind() {
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		weixinUserDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		weixinUserDO.setNickname("E店用户");
		weixinUserDO.setUserNum("M894378717298556928");
		weixinUserDO.setSex(1);
		weixinUserDO.setCity("");
		weixinUserDO.setProvince("安道尔");
		weixinUserDO.setCountry("");
		weixinUserDO.setHeadimgurl("");
		weixinUserDO.setLanguage("zh_CN");
		weixinUserDO.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		weixinUserDO.setGroupid(0l);
		weixinUserDO.setSubscribe(1);
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
		WeixinUserBindParam bindParam = new WeixinUserBindParam();
		bindParam.setAccount("18576620612");
		bindParam.setOpenId(weixinUserDO.getOpenid());
		bindParam.setPwd("123456");
		Boolean  flag = weixinUserService.weixinUserBind(bindParam);
        Assert.assertTrue(!flag);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveWeixinUser() {
		WeixinUserParam param = new WeixinUserParam();
		param.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		param.setNickname("E店用户");
		param.setSex(1);
		param.setCity("");
		param.setProvince("安道尔");
		param.setCountry("");
		param.setHeadimgurl("");
		param.setLanguage("zh_CN");
		param.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		param.setGroupid(0l);
		weixinUserService.saveWeixinUser(param);
		
		List<WeixinUserDO> list = weixinUserDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void wxLogin() {
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		weixinUserDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		weixinUserDO.setNickname("E店用户");
		weixinUserDO.setUserNum("M894378717298556928");
		weixinUserDO.setSex(1);
		weixinUserDO.setCity("");
		weixinUserDO.setProvince("安道尔");
		weixinUserDO.setCountry("");
		weixinUserDO.setHeadimgurl("");
		weixinUserDO.setLanguage("zh_CN");
		weixinUserDO.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		weixinUserDO.setGroupid(0l);
		weixinUserDO.setSubscribe(1);
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
		
		WxLoginMemberBO wxLoginMemberBO = wxLoginMemberService.wxLogin(weixinUserDO.getOpenid());
		Assert.assertNotNull(wxLoginMemberBO);
		
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void wxMemberBind() {
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		weixinUserDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		weixinUserDO.setNickname("E店用户");
		weixinUserDO.setSex(1);
		weixinUserDO.setCity("");
		weixinUserDO.setProvince("安道尔");
		weixinUserDO.setCountry("");
		weixinUserDO.setHeadimgurl("");
		weixinUserDO.setLanguage("zh_CN");
		weixinUserDO.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		weixinUserDO.setGroupid(0l);
		weixinUserDO.setSubscribe(1);
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
		
		wxLoginMemberService.wxMemberBind(weixinUserDO.getOpenid(), "18512412454", "head/img");
		
    }
	

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getHeadImg() {
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		weixinUserDO.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		weixinUserDO.setNickname("E店用户");
		weixinUserDO.setSex(1);
		weixinUserDO.setCity("");
		weixinUserDO.setProvince("安道尔");
		weixinUserDO.setCountry("");
		weixinUserDO.setHeadimgurl("");
		weixinUserDO.setLanguage("zh_CN");
		weixinUserDO.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		weixinUserDO.setGroupid(0l);
		weixinUserDO.setSubscribe(1);
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
		
		String img = wxLoginMemberService.getHeadImg(weixinUserDO.getOpenid());
		Assert.assertNotNull(img);
    }

	
}
