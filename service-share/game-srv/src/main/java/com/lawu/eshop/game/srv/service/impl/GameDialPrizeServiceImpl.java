package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.param.GameDailPrizeParam;
import com.lawu.eshop.game.query.GameDailPrizeQuery;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.converter.GameDialPrizeConverter;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDO;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDOExample;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDOExample.Criteria;
import com.lawu.eshop.game.srv.mapper.GameDialPrizeDOMapper;
import com.lawu.eshop.game.srv.service.GameDialPrizeService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@Service
public class GameDialPrizeServiceImpl implements GameDialPrizeService {

    @Autowired
    private GameDialPrizeDOMapper gameDialPrizeDOMapper;

	@Override
	public List<GameDialPrizeBO> listGameDialPrize(Long gameDialId, Integer minInventory) {
		GameDialPrizeDOExample example = new GameDialPrizeDOExample();
		Criteria criteria = example.createCriteria();
		criteria.andGameDialIdEqualTo(gameDialId).andStatusEqualTo(GameDialPrizeStatusEnum.VALID.getVal());
		if (minInventory != null) {
			criteria.andInventoryGreaterThan(minInventory);
		}
		List<GameDialPrizeDO> prizeDOS = gameDialPrizeDOMapper.selectByExample(example);
		return GameDialPrizeConverter.converBOS(prizeDOS);
	}

    @Override
    public GameDialPrizeBO getGameDialPrize(Long id) {
        GameDialPrizeDO prizeDO = gameDialPrizeDOMapper.selectByPrimaryKey(id);
        return GameDialPrizeConverter.converBO(prizeDO);
    }

	@Override
	public Page<GameDialPrizeBO> page(GameDailPrizeQuery query) {
		GameDialPrizeDOExample example = new GameDialPrizeDOExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(query.getName())) {
			criteria.andNameEqualTo(query.getName());
		}
		if (query.getStatusEnum() != null) {
			criteria.andStatusEqualTo(query.getStatusEnum().getVal());
		}
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		int count = (int) (gameDialPrizeDOMapper.countByExample(example));
		
		List<GameDialPrizeDO> list = gameDialPrizeDOMapper.selectByExampleWithRowbounds(example, rowBounds);

		Page<GameDialPrizeBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count);
		page.setRecords(GameDialPrizeConverter.converBOS(list));
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateGameDialPrize(GameDailPrizeParam param) throws WrongOperationException{
		GameDialPrizeDO record = new GameDialPrizeDO();
		record.setFreightPrice(param.getFreightPrice());
		record.setGameDialId(param.getGameDialId());
		record.setImgPath(param.getImgPath());
		record.setIsAddress(param.getIsAddress());
		record.setIsSendPrize(param.getIsSendPrize());
		record.setName(param.getName());
		record.setNumber(param.getNumber());
		record.setPrice(param.getPrice());
		record.setPrizeType(param.getPrizeType().getVal());
		record.setRate(param.getRate());
		record.setGmtModified(new Date());
		record.setInventory(param.getNumber());
		if (param.getId() != null && param.getId().longValue() > 0) {
			record.setId(param.getId());
			gameDialPrizeDOMapper.updateByPrimaryKeySelective(record);
			return;
		}
		GameDialPrizeDOExample example = new GameDialPrizeDOExample();
		example.createCriteria().andStatusEqualTo(GameDialPrizeStatusEnum.VALID.getVal());
		int count = (int)gameDialPrizeDOMapper.countByExample(example);
		if (count >= 7) { //启用的商品只能为7个
			 throw new WrongOperationException("启用的商品只能为7个，请先禁用其他商品,再添加");
		}
		record.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
		record.setGmtCreate(new Date());
		gameDialPrizeDOMapper.insertSelective(record);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void setGameDialPrizeStatus(Long id, GameDialPrizeStatusEnum statusEnum) {
		GameDialPrizeDO record = new GameDialPrizeDO();
		record.setId(id);
		record.setStatus(statusEnum.getVal());
		record.setGmtModified(new Date());
		if(statusEnum == GameDialPrizeStatusEnum.VALID){
			GameDialPrizeDOExample example = new GameDialPrizeDOExample();
			example.createCriteria().andStatusEqualTo(GameDialPrizeStatusEnum.VALID.getVal());
			int count = (int)gameDialPrizeDOMapper.countByExample(example);
			if (count >= 7) { //启用的商品只能为7个
				 throw new WrongOperationException("启用的商品只能为7个，请先禁用其他商品,再添加");
			}
		}
		gameDialPrizeDOMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public BigDecimal getLeftPrizeRate(Long gameDialId) {
		 BigDecimal leftPrizeRate = BigDecimal.valueOf(100);
		 GameDialPrizeDOExample example = new GameDialPrizeDOExample();
	        example.createCriteria().andGameDialIdEqualTo(gameDialId).andStatusEqualTo(GameDialPrizeStatusEnum.VALID.getVal());
	        List<GameDialPrizeDO> prizeDOS = gameDialPrizeDOMapper.selectByExample(example);
	        if (prizeDOS.isEmpty()) {
	            return leftPrizeRate;
	        }
	        for (GameDialPrizeDO prizeDO : prizeDOS) {
	            leftPrizeRate = leftPrizeRate.subtract(prizeDO.getRate());
	        }
	        return leftPrizeRate;
	}
}
