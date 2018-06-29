package com.lawu.eshop.game.srv.service.impl;

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

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameInformBO;
import com.lawu.eshop.game.srv.domain.GameInformDO;
import com.lawu.eshop.game.srv.domain.GameInformDOExample;
import com.lawu.eshop.game.srv.mapper.GameInformDOMapper;
import com.lawu.eshop.game.srv.service.GameInformService;
import com.lawu.framework.core.page.Page;

/**
 * 举报管理单元测试
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameInformServiceImplTest {
	
	@Autowired
	private GameInformService gameInformService;
	
	@Autowired
	private GameInformDOMapper gameInformDOMapper;
	
	@Transactional
    @Test
    @Rollback
    public void saveGameInform(){
		GameInformParam param = new GameInformParam();
		param.setAttendNum("PU975699441409327126");
		param.setCheat(true);
		param.setQuestionError(true);
		param.setResultError(false);
		param.setGameType(GameTypeEnum.MIND);
		gameInformService.saveGameInform("M944129901240254464",param);
		
		GameInformDOExample example = new GameInformDOExample();
		example.createCriteria().andStatusEqualTo(GameInformStatusEnum.ON_DEAL_WITH.getVal());
		List<GameInformDO> list = gameInformDOMapper.selectByExample(example);
		Assert.assertEquals(1,list.size());
    }
	
	@Transactional
    @Test
    @Rollback
    public void page(){
		GameInformQuery query = new GameInformQuery();
		query.setCurrentPage(1);
		query.setPageSize(20);
		Page<GameInformBO> page = gameInformService.page(query);
		Assert.assertEquals(1,page.getRecords().size());
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void dealInform(){
		GameInformDO record = new GameInformDO();
		record.setAttendNum("PU975699441409327126");
		record.setCheat(true);
		record.setGameType(GameTypeEnum.MIND.getVal());
		record.setQuestionError(true);
		record.setResultError(false);
		record.setStatus(GameInformStatusEnum.ON_DEAL_WITH.getVal());
		record.setUserNum("M944129901240254464");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		Integer id =gameInformDOMapper.insertSelective(record);
		
		DealInformParam param = new DealInformParam();
		param.setAuditorId(1);
		param.setAuditorName("test");
		param.setId(Long.parseLong(id.toString()));
		param.setRemark("test");
		param.setStatusEnum(GameInformStatusEnum.DEAL_WITH);
		gameInformService.dealInform(param);
		
		GameInformDOExample example = new GameInformDOExample();
		example.createCriteria().andStatusEqualTo(GameInformStatusEnum.DEAL_WITH.getVal());
		List<GameInformDO> list = gameInformDOMapper.selectByExample(example);
		Assert.assertEquals(1,list.size());
    }
	

}
