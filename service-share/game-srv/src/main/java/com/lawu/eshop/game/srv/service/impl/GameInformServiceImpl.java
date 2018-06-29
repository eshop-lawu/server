package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.eshop.game.srv.bo.GameInformBO;
import com.lawu.eshop.game.srv.converter.GameInformConverter;
import com.lawu.eshop.game.srv.domain.GameInformDO;
import com.lawu.eshop.game.srv.domain.GameInformDOExample;
import com.lawu.eshop.game.srv.domain.GameInformDOExample.Criteria;
import com.lawu.eshop.game.srv.mapper.GameInformDOMapper;
import com.lawu.eshop.game.srv.service.GameInformService;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@Service
public class GameInformServiceImpl implements GameInformService{
	
	@Autowired
	private GameInformDOMapper gameInformDOMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGameInform(String userNum, GameInformParam param) throws WrongOperationException {
		GameInformDOExample example = new GameInformDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andAttendNumEqualTo(param.getAttendNum());
		int count = (int)gameInformDOMapper.countByExample(example);
		if (count > 0) {
			throw new WrongOperationException("已经举报该场游戏");
		}
		GameInformDO record = new GameInformDO();
		record.setAttendNum(param.getAttendNum());
		record.setCheat(param.getCheat());
		record.setGameType(param.getGameType().getVal());
		record.setQuestionError(param.getQuestionError());
		record.setResultError(param.getResultError());
		record.setStatus(GameInformStatusEnum.ON_DEAL_WITH.getVal());
		record.setUserNum(userNum);
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameInformDOMapper.insertSelective(record);
	}

	@Override
	public Page<GameInformBO> page(GameInformQuery query) {
		GameInformDOExample example = new GameInformDOExample();
		Criteria criteria = example.createCriteria();
		if (query.getStatusEnum() != null) {
			criteria.andStatusEqualTo(query.getStatusEnum().getVal());
		}
		if (query.getTypeEnum() != null) {
			criteria.andGameTypeEqualTo(query.getTypeEnum().getVal());
		}
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		int count = (int) (gameInformDOMapper.countByExample(example));
		
		List<GameInformDO> list = gameInformDOMapper.selectByExampleWithRowbounds(example, rowBounds);

		Page<GameInformBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count);
		page.setRecords(GameInformConverter.convertGameInformBOS(list));
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void dealInform(DealInformParam param) {
		
		GameInformDO record = new GameInformDO();
		record.setId(param.getId());
		record.setAuditorId(param.getAuditorId());
		record.setAuditorName(param.getAuditorName());
		record.setAuditTime(new Date());
		record.setGmtModified(new Date());
		record.setRemark(param.getRemark());
		record.setStatus(param.getStatusEnum().getVal());
		
		gameInformDOMapper.updateByPrimaryKeySelective(record);
		
	}

}
