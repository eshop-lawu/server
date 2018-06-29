package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.OperatorGamePuzzlePicQuery;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.converter.GamePuzzlePicConverter;
import com.lawu.eshop.game.srv.domain.GamePuzzlePicDO;
import com.lawu.eshop.game.srv.domain.GamePuzzlePicDOExample;
import com.lawu.eshop.game.srv.mapper.GamePuzzlePicDOMapper;
import com.lawu.eshop.game.srv.service.GamePuzzlePicService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
@Service
public class GamePuzzlePicServiceImpl implements GamePuzzlePicService {

	@Autowired
	private GamePuzzlePicDOMapper gamePuzzlePicDOMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGamePuzzlePic(GamePuzzleParam param) {
		GamePuzzlePicDO picDO = new GamePuzzlePicDO();
		picDO.setUserNum(param.getUserNum());
		picDO.setUserNickname(param.getUserNickname());
		picDO.setImgPath(param.getImgPath());
		picDO.setType(param.getTypeEnum().getVal());
		picDO.setIsSimple(param.getIsSimple());
		picDO.setIsCommon(param.getIsCommon());
		picDO.setIsHard(param.getIsHard());
		picDO.setStatus(param.getPicStatusEnum().getVal());
		picDO.setGmtModified(new Date());

		if (param.getImgPath().contains(",")) {
			picDO.setGmtCreate(new Date());
			String[] imgPathArr = param.getImgPath().split(",");
			for (String imgPath : imgPathArr) {
				picDO.setImgPath(imgPath);
				gamePuzzlePicDOMapper.insertSelective(picDO);
			}
			return;
		}

		if (param.getId() != null && param.getId() > 0) {
			picDO.setId(param.getId());
			gamePuzzlePicDOMapper.updateByPrimaryKeySelective(picDO);
		} else {
			picDO.setGmtCreate(new Date());
			gamePuzzlePicDOMapper.insertSelective(picDO);
		}
	}

	@Override
	public GamePuzzlePicBO getGamePuzzlePic(Long id) {
		GamePuzzlePicDO picDO = gamePuzzlePicDOMapper.selectByPrimaryKey(id);
		return GamePuzzlePicConverter.converBO(picDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateGamePuzzlePicStatus(Long id, GamePuzzlePicStatusEnum statusEnum) {
		GamePuzzlePicDO picDO = new GamePuzzlePicDO();
		picDO.setId(id);
		picDO.setStatus(statusEnum.getVal());
		picDO.setGmtModified(new Date());
		gamePuzzlePicDOMapper.updateByPrimaryKeySelective(picDO);
	}

	@Override
	public Page<GamePuzzlePicBO> listOperatorGamePuzzlePic(OperatorGamePuzzlePicQuery query) {
		GamePuzzlePicDOExample example = new GamePuzzlePicDOExample();
		example.setOrderByClause("id desc");
		GamePuzzlePicDOExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(GamePuzzleTypeEnum.PUZZLE.getVal());
		if (StringUtils.isNotEmpty(query.getUserNickname())) {
			criteria.andUserNicknameLike("%" + query.getUserNickname() + "%");
		}
		if (StringUtils.isNotEmpty(query.getBeginTime())) {
			Date beginDate = DateUtil.formatDate(query.getBeginTime() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			criteria.andGmtCreateGreaterThanOrEqualTo(beginDate);
		}
		if (StringUtils.isNotEmpty(query.getEndTime())) {
			Date endDate = DateUtil.formatDate(query.getEndTime() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
		if (query.getStatusEnum() != null) {
			criteria.andStatusEqualTo(query.getStatusEnum().getVal());
		}

		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		List<GamePuzzlePicDO> picDOS = gamePuzzlePicDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		Page<GamePuzzlePicBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount((int) gamePuzzlePicDOMapper.countByExample(example));
		page.setRecords(GamePuzzlePicConverter.converBOS(picDOS));
		return page;
	}

	/**
	 * 根据困难程度随机获取图片
	 *
	 * @param level
	 * @return
	 */
	@Override
	public List<GamePuzzlePicBO> getRandomGamePuzzlePic(GameHardLevelEnum level, int picCount) {
		List<GamePuzzlePicBO> returnList = new ArrayList<GamePuzzlePicBO>();
		GamePuzzlePicDOExample example = new GamePuzzlePicDOExample();
		GamePuzzlePicDOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(GamePuzzlePicStatusEnum.ENABLED.getVal());
		criteria.andTypeEqualTo(GamePuzzleTypeEnum.PUZZLE.getVal());
		if (level == GameHardLevelEnum.SIMPLE) {
			criteria.andIsSimpleEqualTo(true);
		} else if (level == GameHardLevelEnum.COMMONLY) {
			criteria.andIsCommonEqualTo(true);
		} else {
			criteria.andIsHardEqualTo(true);
		}
		List<GamePuzzlePicDO> list = gamePuzzlePicDOMapper.selectByExample(example);
		if (null == list || list.isEmpty()) {
			return null;
		}
		for (int i = 0; i < picCount; i++) {
			Collections.shuffle(list, new Random());
			GamePuzzlePicDO pbo = list.get(0);
			GamePuzzlePicBO bo = GamePuzzlePicConverter.converBO(pbo);
			list.remove(pbo);
			returnList.add(bo);
		}
		return returnList;
	}

	@Override
	public GamePuzzlePicBO getRandomGamePuzzlePicSimg(GameHardLevelEnum level, List lt) {
		GamePuzzlePicDOExample example = new GamePuzzlePicDOExample();
		GamePuzzlePicDOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(GamePuzzlePicStatusEnum.ENABLED.getVal());
		if (level == GameHardLevelEnum.SIMPLE) {
			criteria.andIsSimpleEqualTo(true);
		} else if (level == GameHardLevelEnum.COMMONLY) {
			criteria.andIsCommonEqualTo(true);
		} else {
			criteria.andIsHardEqualTo(true);
		}
		if(!lt.isEmpty()){
            criteria.andIdNotIn(lt);
        }
		List<GamePuzzlePicDO> list = gamePuzzlePicDOMapper.selectByExample(example);
		if (null == list || list.isEmpty()) {
			return null;
		}
		Collections.shuffle(list, new Random());
		GamePuzzlePicBO bo = GamePuzzlePicConverter.converBO(list.get(0));
		return bo;
	}

}
