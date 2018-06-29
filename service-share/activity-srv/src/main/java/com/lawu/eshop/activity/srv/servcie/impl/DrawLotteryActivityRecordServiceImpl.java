package com.lawu.eshop.activity.srv.servcie.impl;

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
import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.TakeLotteryParam;
import com.lawu.eshop.activity.param.TakePartLotteryParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.converter.DrawLotteryActivityRecordConverter;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDOExample;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDOExample;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityMonthRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityPrizeDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.DrawLotteryActivityPrizeDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityRecordService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mq.dto.activity.reply.DrawLotteryReply;
import com.lawu.eshop.mq.dto.activity.reply.TakeLotteryReply;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
@Service
public class DrawLotteryActivityRecordServiceImpl implements DrawLotteryActivityRecordService {

    @Autowired
    private DrawLotteryActivityRecordDOMapper drawLotteryActivityRecordDOMapper;

    @Autowired
    private DrawLotteryActivityPrizeDOMapper drawLotteryActivityPrizeDOMapper;

    @Autowired
    @Qualifier("drawLotteryTransactionMainServiceImpl")
    private TransactionMainService<DrawLotteryReply> drawLotteryTransactionMainServiceImpl;

    @Autowired
    private DrawLotteryActivityPrizeDOMapperExtend drawLotteryActivityPrizeDOMapperExtend;

    @Autowired
    private DrawLotteryActivityDOMapper drawLotteryActivityDOMapper;

    @Autowired
    private DrawLotteryActivityMonthRecordDOMapper drawLotteryActivityMonthRecordDOMapper;

    @Autowired
    @Qualifier("takeLotteryTransactionMainServiceImpl")
    private TransactionMainService<TakeLotteryReply> takeLotteryTransactionMainServiceImpl;

    @Override
    public Long getTakePartLottery(Long drawLotteryActivityId, String userNum, DrawLotteryActivityRecordStatusEnum statusEnum) {
        DrawLotteryActivityRecordDOExample recordDOExample = new DrawLotteryActivityRecordDOExample();
        recordDOExample.createCriteria().andUserNumEqualTo(userNum).andDrawLotteryActivityIdEqualTo(drawLotteryActivityId).andStatusEqualTo(statusEnum.getVal());
        List<DrawLotteryActivityRecordDO> recordDOS = drawLotteryActivityRecordDOMapper.selectByExample(recordDOExample);
        if (recordDOS.isEmpty()) {
            return 0L;
        }
        return recordDOS.get(0).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long takePartLottery(TakePartLotteryParam param) {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setUserId(param.getUserId());
        recordDO.setUserNum(param.getUserNum());
        recordDO.setUserAccount(param.getUserAccount());
        recordDO.setDrawLotteryActivityId(param.getDrawLotteryActivityId());
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY.getVal());
        if (param.getChannelEnum().equals(DrawLotteryActivityRecordChannelEnum.POINT_LOTTERY)) {
            recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.POINT_DEDUCT_ING.getVal());
        }
        recordDO.setChannel(param.getChannelEnum().getVal());
        recordDO.setPayPoint(param.getPayPoint());
        recordDO.setGmtCreate(new Date());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        //查询用户当月抽奖记录
        String nowDate = DateUtil.getYearMonthDate();
        DrawLotteryActivityMonthRecordDOExample recordDOExample = new DrawLotteryActivityMonthRecordDOExample();
        recordDOExample.createCriteria().andUserNumEqualTo(param.getUserNum()).andRecordDateEqualTo(nowDate);
        List<DrawLotteryActivityMonthRecordDO> monthRecordDOS = drawLotteryActivityMonthRecordDOMapper.selectByExample(recordDOExample);

        DrawLotteryActivityMonthRecordDO monthRecordDO = new DrawLotteryActivityMonthRecordDO();
        if (param.getChannelEnum().equals(DrawLotteryActivityRecordChannelEnum.FREE_LOTTERY)) {
            monthRecordDO.setFreeTimes(1);
            monthRecordDO.setPointTimes(0);
        } else if (param.getChannelEnum().equals(DrawLotteryActivityRecordChannelEnum.POINT_LOTTERY)) {
            monthRecordDO.setFreeTimes(0);
            monthRecordDO.setPointTimes(0);
        }

        //新增或更新当月抽奖记录
        if (monthRecordDOS.isEmpty()) {
            monthRecordDO.setUserNum(param.getUserNum());
            monthRecordDO.setFreeTimes(monthRecordDO.getFreeTimes());
            monthRecordDO.setPointTimes(monthRecordDO.getPointTimes());
            monthRecordDO.setRecordDate(nowDate);
            monthRecordDO.setGmtCreate(new Date());
            drawLotteryActivityMonthRecordDOMapper.insertSelective(monthRecordDO);
        } else {
            if (param.getChannelEnum().equals(DrawLotteryActivityRecordChannelEnum.FREE_LOTTERY)) {
                monthRecordDO.setId(monthRecordDOS.get(0).getId());
                monthRecordDO.setFreeTimes(monthRecordDOS.get(0).getFreeTimes() + monthRecordDO.getFreeTimes());
                monthRecordDO.setGmtModified(new Date());
                drawLotteryActivityMonthRecordDOMapper.updateByPrimaryKeySelective(monthRecordDO);
            }
        }

        //积分抽奖发消息扣除积分
        if (param.getChannelEnum().equals(DrawLotteryActivityRecordChannelEnum.POINT_LOTTERY)) {
            drawLotteryTransactionMainServiceImpl.sendNotice(recordDO.getId());
        }
        return recordDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLotteryStatus(Long id, DrawLotteryActivityRecordStatusEnum statusEnum) {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setId(id);
        recordDO.setStatus(statusEnum.getVal());
        recordDO.setGmtModified(new Date());
        drawLotteryActivityRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        if (statusEnum.equals(DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY)) {
            //当月积分抽奖次数加一
            DrawLotteryActivityRecordDO activityRecordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(id);
            String nowDate = DateUtil.getYearMonthDate();
            DrawLotteryActivityMonthRecordDOExample recordDOExample = new DrawLotteryActivityMonthRecordDOExample();
            recordDOExample.createCriteria().andUserNumEqualTo(activityRecordDO.getUserNum()).andRecordDateEqualTo(nowDate);
            List<DrawLotteryActivityMonthRecordDO> monthRecordDOS = drawLotteryActivityMonthRecordDOMapper.selectByExample(recordDOExample);
            DrawLotteryActivityMonthRecordDO monthRecordDO = new DrawLotteryActivityMonthRecordDO();
            monthRecordDO.setId(monthRecordDOS.get(0).getId());
            monthRecordDO.setPointTimes(monthRecordDOS.get(0).getPointTimes() + 1);
            monthRecordDO.setGmtModified(new Date());
            drawLotteryActivityMonthRecordDOMapper.updateByPrimaryKeySelective(monthRecordDO);
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendPrize(Long id, String expressNum) {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setId(id);
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.SEND_LOTTERY.getVal());
        recordDO.setGmtModified(new Date());
        recordDO.setExpressNum(expressNum);
        drawLotteryActivityRecordDOMapper.updateByPrimaryKeySelective(recordDO);
       
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer winLottery(Long id, Long drawLotteryActivityPrizeId) throws Exception {
        DrawLotteryActivityPrizeDO prizeDO = drawLotteryActivityPrizeDOMapper.selectByPrimaryKey(drawLotteryActivityPrizeId);
        if (prizeDO == null || prizeDO.getInventory() == 0) {
            return ResultCode.FAIL;
        }
        //更新奖品剩余库存
        drawLotteryActivityPrizeDOMapperExtend.updateDrawLotteryActivityPrizeInventory(drawLotteryActivityPrizeId);
        //更新中奖信息
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setId(id);
        recordDO.setDrawLotteryActivityPrizeId(drawLotteryActivityPrizeId);
        recordDO.setPrizeName(prizeDO.getName());
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal());
        recordDO.setGmtModified(new Date());
        drawLotteryActivityRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        //更新抽奖活动状态
        DrawLotteryActivityPrizeDOExample prizeDOExample = new DrawLotteryActivityPrizeDOExample();
        prizeDOExample.createCriteria().andDrawLotteryActivityIdEqualTo(prizeDO.getDrawLotteryActivityId()).andStatusEqualTo(DrawLotteryActivityPrizeStatusEnum.VALID.getVal()).andInventoryGreaterThan(0);
        List<DrawLotteryActivityPrizeDO> prizeDOS = drawLotteryActivityPrizeDOMapper.selectByExample(prizeDOExample);
        if (prizeDOS.isEmpty()) {
            DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
            activityDO.setId(prizeDO.getDrawLotteryActivityId());
            activityDO.setStatus(DrawLotteryActivityStatusEnum.FINISHED.getVal());
            activityDO.setGmtModified(new Date());
            drawLotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void takeLottery(TakeLotteryParam param) {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setId(param.getId());
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.TAKE_LOTTERY.getVal());
        recordDO.setConsigneeName(param.getConsigneeName());
        recordDO.setConsigneeMobile(param.getConsigneeMobile());
        recordDO.setConsigneeAddress(param.getConsigneeAddress());
        recordDO.setGmtModified(new Date());
        drawLotteryActivityRecordDOMapper.updateByPrimaryKeySelective(recordDO);

        recordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(param.getId());
        DrawLotteryActivityPrizeDO prizeDO = drawLotteryActivityPrizeDOMapper.selectByPrimaryKey(recordDO.getDrawLotteryActivityPrizeId());
        if (prizeDO.getIsSendPrize() && (prizeDO.getPrizeType().byteValue() == DrawLotteryActivityPrizeTypeEnum.MONEY.getVal() || prizeDO.getPrizeType().byteValue() == DrawLotteryActivityPrizeTypeEnum.POINT.getVal())) {
            takeLotteryTransactionMainServiceImpl.sendNotice(param.getId());
        }
    }

    @Override
    public DrawLotteryActivityRecordBO getDrawLotteryActivityRecord(Long id) {
        DrawLotteryActivityRecordDO recordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(id);
        return DrawLotteryActivityRecordConverter.convertBO(recordDO);
    }

    @Override
    public Page<DrawLotteryActivityRecordBO> listOperatorDrawLotteryActivityRecord(OperatorDrawLotteryActivityRecordQuery query) {
        DrawLotteryActivityRecordDOExample example = new DrawLotteryActivityRecordDOExample();
        example.setOrderByClause("id desc");
        DrawLotteryActivityRecordDOExample.Criteria criteria = example.createCriteria();
        criteria.andDrawLotteryActivityIdEqualTo(query.getDrawLotteryActivityId());
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

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<DrawLotteryActivityRecordDO> recordDOS = drawLotteryActivityRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<DrawLotteryActivityRecordBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) drawLotteryActivityRecordDOMapper.countByExample(example));
        page.setRecords(DrawLotteryActivityRecordConverter.convertBOS(recordDOS));
        return page;
    }

	@Override
	public Page<DrawLotteryActivityRecordBO> listDrawLotteryActivityRecord(String memberNum,DrawLotteryActivityRecordQuery query) {
		 DrawLotteryActivityRecordDOExample example = new DrawLotteryActivityRecordDOExample();
	     example.setOrderByClause("id desc");
	     List<Byte> status = new ArrayList<>();
	     status.add(DrawLotteryActivityRecordStatusEnum.TAKE_LOTTERY.getVal());
	     status.add(DrawLotteryActivityRecordStatusEnum.SEND_LOTTERY.getVal());
	     DrawLotteryActivityRecordDOExample.Criteria criteria = example.createCriteria();
	     criteria.andUserNumEqualTo(memberNum).andStatusIn(status);
	     RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
	     List<DrawLotteryActivityRecordDO> recordDOS = drawLotteryActivityRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);

	     List<DrawLotteryActivityRecordBO> recordBOS = new ArrayList<>();
	     for (DrawLotteryActivityRecordDO drawLotteryActivityRecordDO : recordDOS) {
	    	 
	    	 DrawLotteryActivityRecordBO bo =  DrawLotteryActivityRecordConverter.convertBO(drawLotteryActivityRecordDO);
	    	 DrawLotteryActivityPrizeDO prize = drawLotteryActivityPrizeDOMapper.selectByPrimaryKey(drawLotteryActivityRecordDO.getDrawLotteryActivityPrizeId());
	    	 bo.setPrizeType(DrawLotteryActivityPrizeTypeEnum.getEnum(prize.getPrizeType()));
	    	 bo.setPrizeImg(prize.getImgPath());
	    	 recordBOS.add(bo);
		 }
	    
	     Page<DrawLotteryActivityRecordBO> page = new Page<>();
	     page.setCurrentPage(query.getCurrentPage());
	     page.setTotalCount((int) drawLotteryActivityRecordDOMapper.countByExample(example));
	     page.setRecords(recordBOS);
		 return page;
	}

}
