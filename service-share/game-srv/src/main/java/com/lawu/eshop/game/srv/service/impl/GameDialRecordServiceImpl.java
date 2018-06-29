package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.GameDialPrizeTypeEnum;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.param.TakeLotteryParam;
import com.lawu.eshop.game.param.TakePartLotteryParam;
import com.lawu.eshop.game.query.GameDailRecordPageQuery;
import com.lawu.eshop.game.query.GameDialRecordUserQuery;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordInfoBO;
import com.lawu.eshop.game.srv.converter.GameDialRecordConverter;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDO;
import com.lawu.eshop.game.srv.domain.GameDialRecordDO;
import com.lawu.eshop.game.srv.domain.GameDialRecordDOExample;
import com.lawu.eshop.game.srv.domain.GameDialRecordDOExample.Criteria;
import com.lawu.eshop.game.srv.mapper.GameDialPrizeDOMapper;
import com.lawu.eshop.game.srv.mapper.GameDialRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.extend.GameAccountDOMapperExtend;
import com.lawu.eshop.game.srv.mapper.extend.GameDialAccountDOMapperExtend;
import com.lawu.eshop.game.srv.mapper.extend.GameDialPrizeDOMapperExtend;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GameDialRecordService;
import com.lawu.eshop.mq.dto.game.reply.GameDialReply;
import com.lawu.eshop.mq.dto.game.reply.GameDialTakeLotteryReply;
import com.lawu.framework.core.page.Page;


/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@Service
public class GameDialRecordServiceImpl implements GameDialRecordService {

    @Autowired
    private GameDialRecordDOMapper gameDialRecordDOMapper;

    @Autowired
    private GameDialPrizeDOMapper gameDialPrizeDOMapper;

    @Autowired
    private GameDialPrizeDOMapperExtend gameDialPrizeDOMapperExtend;

    @Autowired
    private GameAccountDOMapperExtend gameAccountDOMapperExtend;

    @Autowired
    private GameDialAccountDOMapperExtend gameDialAccountDOMapperExtend;

    @Autowired
    @Qualifier("gameDialTransactionMainServiceImpl")
    private TransactionMainService<GameDialReply> gameDialTransactionMainServiceImpl;

    @Autowired
    @Qualifier("gameDialTakeLotteryTransactionMainServiceImpl")
    private TransactionMainService<GameDialTakeLotteryReply> gameDialTakeLotteryTransactionMainServiceImpl;

    @Autowired
    private GameAccountService gameAccountService;

    @Override
    public Page<GameDialRecordInfoBO> listGameDialRecord(GameDialRecordUserQuery query) {
        GameDialRecordDOExample example = new GameDialRecordDOExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andUserNumEqualTo(query.getUserNum()).andGameDialIdEqualTo(query.getGameDialId()).andStatusBetween(GameDialRecordStatusEnum.GET_LOTTERY.getVal(), GameDialRecordStatusEnum.SEND_LOTTERY.getVal());

        List<GameDialRecordInfoBO> infoBOS = new ArrayList<>();
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<GameDialRecordDO> recordDOS = gameDialRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!recordDOS.isEmpty()) {
            for (GameDialRecordDO recordDO : recordDOS) {
                GameDialPrizeDO prizeDO = gameDialPrizeDOMapper.selectByPrimaryKey(recordDO.getGameDialPrizeId());
                GameDialRecordInfoBO infoBO = new GameDialRecordInfoBO();
                infoBO.setName(prizeDO == null ? null : prizeDO.getName());
                infoBO.setImgPath(prizeDO == null ? null : prizeDO.getImgPath());
                infoBO.setId(recordDO.getId());
                infoBO.setStatus(recordDO.getStatus());
                infoBO.setGmtCreate(recordDO.getGmtCreate());
                infoBOS.add(infoBO);
            }
        }
        Page<GameDialRecordInfoBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) gameDialRecordDOMapper.countByExample(example));
        page.setRecords(infoBOS);
        return page;
    }

    @Override
    public Long getTakePartLottery(Long gameDialId, String userNum, GameDialRecordStatusEnum statusEnum) {
        GameDialRecordDOExample example = new GameDialRecordDOExample();
        example.createCriteria().andGameDialIdEqualTo(gameDialId).andUserNumEqualTo(userNum).andStatusEqualTo(statusEnum.getVal());
        List<GameDialRecordDO> recordDOS = gameDialRecordDOMapper.selectByExample(example);
        if (recordDOS.isEmpty()) {
            return 0L;
        }
        return recordDOS.get(0).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveGameDialRecord(TakePartLotteryParam param) throws Exception {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setUserId(param.getUserId());
        recordDO.setUserNum(param.getUserNum());
        recordDO.setUserAccount(param.getUserAccount());
        recordDO.setGameDialId(param.getGameDialId());
        recordDO.setStatus(param.getStatusEnum().getVal());
        recordDO.setPayPoint(param.getPayPoint());
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        gameDialRecordDOMapper.insertSelective(recordDO);

        if (param.getStatusEnum().equals(GameDialRecordStatusEnum.TAKE_PART_LOTTERY)) {
            gameAccountService.reduceFreeCount(param.getUserNum(), GameTypeEnum.DIAL);
        } else if (param.getStatusEnum().equals(GameDialRecordStatusEnum.POINT_DEDUCT_ING) && param.getPayPoint().compareTo(BigDecimal.ZERO) == 1) {
            gameDialTransactionMainServiceImpl.sendNotice(recordDO.getId());
        }
        return recordDO.getId();
    }

    @Override
    public GameDialRecordBO getGameDialRecord(Long id) {
        GameDialRecordDO recordDO = gameDialRecordDOMapper.selectByPrimaryKey(id);
        return GameDialRecordConverter.converBO(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGameDialRecordStatus(Long id, GameDialRecordStatusEnum statusEnum) {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setId(id);
        recordDO.setStatus(statusEnum.getVal());
        recordDO.setGmtModified(new Date());
        gameDialRecordDOMapper.updateByPrimaryKeySelective(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void takeLottery(TakeLotteryParam param) {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setId(param.getId());
        recordDO.setStatus(GameDialRecordStatusEnum.TAKE_LOTTERY.getVal());
        recordDO.setConsigneeName(param.getConsigneeName());
        recordDO.setConsigneeMobile(param.getConsigneeMobile());
        recordDO.setConsigneeAddress(param.getConsigneeAddress());
        recordDO.setGmtModified(new Date());
        gameDialRecordDOMapper.updateByPrimaryKeySelective(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer winLottery(Long id, Long gameDialPrizeId) throws Exception {
        GameDialPrizeDO prizeDO = gameDialPrizeDOMapper.selectByPrimaryKey(gameDialPrizeId);
        if (prizeDO == null || prizeDO.getInventory() == 0) {
            return ResultCode.FAIL;
        }
        //更新奖品剩余库存
        gameDialPrizeDOMapperExtend.updateGameDialPrizeInventory(gameDialPrizeId);
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setId(id);
        recordDO.setGameDialPrizeId(gameDialPrizeId);
        recordDO.setPrizeName(prizeDO.getName());
        recordDO.setGmtModified(new Date());

        if (prizeDO.getIsSendPrize() && (prizeDO.getPrizeType().byteValue() == GameDialPrizeTypeEnum.MONEY.getVal() || prizeDO.getPrizeType().byteValue() == GameDialPrizeTypeEnum.POINT.getVal())) {
            //更新为已领奖
            recordDO.setStatus(GameDialRecordStatusEnum.TAKE_LOTTERY.getVal());
            gameDialRecordDOMapper.updateByPrimaryKeySelective(recordDO);
            gameDialTakeLotteryTransactionMainServiceImpl.sendNotice(id);
        } else if (prizeDO.getIsSendPrize() && prizeDO.getPrizeType().byteValue() == GameDialPrizeTypeEnum.STAR.getVal()) {
            GameDialRecordDO dialRecordDO = gameDialRecordDOMapper.selectByPrimaryKey(id);
            List<GameAccountAllotParam> list = new ArrayList<>();
            GameAccountAllotParam allotParam = new GameAccountAllotParam();
            allotParam.setUserNum(dialRecordDO.getUserNum());
            allotParam.setStar(prizeDO.getPrice().intValue());
            list.add(allotParam);
            GameAccountStarParam param = new GameAccountStarParam();
            param.setGameType(GameTypeEnum.DIAL);
            param.setRecordEnum(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
            param.setList(list);
            gameAccountService.dealStar(param);
            //更新为已发放
            recordDO.setStatus(GameDialRecordStatusEnum.SEND_LOTTERY.getVal());
            gameDialRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        } else if (prizeDO.getPrizeType().byteValue() == GameDialPrizeTypeEnum.PRODUCT.getVal()) {
            //更新为已中奖
            recordDO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
            gameDialRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        } else {
            //更新为已领奖
            recordDO.setStatus(GameDialRecordStatusEnum.TAKE_LOTTERY.getVal());
            gameDialRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        }
        return ResultCode.SUCCESS;
    }

	@Override
	public Page<GameDialRecordBO> page(GameDailRecordPageQuery query) {
		GameDialRecordDOExample example = new GameDialRecordDOExample();
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(query.getUserAccount())) {
			criteria.andUserAccountEqualTo(query.getUserAccount());
		}
        if (StringUtils.isNotEmpty(query.getPrizeName())) {
            criteria.andPrizeNameLike("%" + query.getPrizeName() + "%");
        }
        if (StringUtils.isNotEmpty(query.getConsigneeName())) {
            criteria.andConsigneeNameLike("%" + query.getConsigneeName() + "%");
        }
		if (StringUtils.isNotEmpty(query.getConsigneeMobile())) {
			criteria.andConsigneeMobileEqualTo(query.getConsigneeMobile());
		}
		if (query.getStatusEnum() != null) {
			criteria.andStatusEqualTo(query.getStatusEnum().getVal());
		}
        if (query.getPayPoint() == 0) {
            criteria.andPayPointEqualTo(BigDecimal.ZERO);
        }
        if (query.getPayPoint() > 0) {
            criteria.andPayPointGreaterThan(BigDecimal.ZERO);
        }

		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		int count = (int) (gameDialRecordDOMapper.countByExample(example));

        List<GameDialRecordDO> list = gameDialRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Page<GameDialRecordBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(count);
        page.setRecords(GameDialRecordConverter.converBOS(list));
        return page;
    }

}
