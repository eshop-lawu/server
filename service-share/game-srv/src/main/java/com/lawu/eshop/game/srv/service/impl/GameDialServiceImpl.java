package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.eshop.game.srv.bo.GameDialBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.converter.GameDialConverter;
import com.lawu.eshop.game.srv.converter.GameDialRecordConverter;
import com.lawu.eshop.game.srv.domain.GameDialDO;
import com.lawu.eshop.game.srv.domain.GameDialDOExample;
import com.lawu.eshop.game.srv.domain.GameDialRecordDO;
import com.lawu.eshop.game.srv.domain.GameDialRecordDOExample;
import com.lawu.eshop.game.srv.domain.GameDialRecordDOExample.Criteria;
import com.lawu.eshop.game.srv.mapper.GameDialDOMapper;
import com.lawu.eshop.game.srv.service.GameDialService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@Service
public class GameDialServiceImpl implements GameDialService {

    @Autowired
    private GameDialDOMapper gameDialDOMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGameDial(GameDialParam param) {
		GameDialDO record = new GameDialDO();
		record.setFreeCount(param.getFreeCount());
		record.setImgPath(param.getImgPath());
		record.setName(param.getName());
		record.setPoint(param.getPoint());
		record.setStatus(GameDialStatusEnum.ENABLED.getVal());
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		record.setShareAttendCount(param.getShareAttendCount());
		gameDialDOMapper.insertSelective(record);
	}

    @Override
    public GameDialBO getGameDial() {
        GameDialDOExample example = new GameDialDOExample();
        example.createCriteria().andStatusEqualTo(GameDialStatusEnum.ENABLED.getVal());
        List<GameDialDO> dialDOS = gameDialDOMapper.selectByExample(example);
        if (dialDOS.isEmpty()) {
            return null;
        }
        return GameDialConverter.converBO(dialDOS.get(0));
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateGameDial(Long id, GameDialParam param) {
		GameDialDO record = new GameDialDO();
		record.setId(id);
		record.setFreeCount(param.getFreeCount());
		record.setImgPath(param.getImgPath());
		record.setName(param.getName());
		record.setPoint(param.getPoint());
		record.setGmtModified(new Date());
		record.setShareAttendCount(param.getShareAttendCount());
		gameDialDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public GameDialBO getGameDialById(Long id) {
		GameDialDO record =gameDialDOMapper.selectByPrimaryKey(id);
		 return GameDialConverter.converBO(record);
	}

	@Override
	public Page<GameDialBO> page(GameDialQuery query) {
		
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		int count = (int) (gameDialDOMapper.countByExample(null));
		
		List<GameDialDO> list = gameDialDOMapper.selectByExampleWithRowbounds(null, rowBounds);

		Page<GameDialBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count);
		page.setRecords(GameDialConverter.converBOS(list));
		return page;
	}

	@Override
	public void setGameDial(Long id, GameDialStatusEnum statusEnum) {
		GameDialDO record = new GameDialDO();
		record.setId(id);
		record.setStatus(statusEnum.getVal());
		record.setGmtModified(new Date());
		gameDialDOMapper.updateByPrimaryKeySelective(record);
		
	}

}
