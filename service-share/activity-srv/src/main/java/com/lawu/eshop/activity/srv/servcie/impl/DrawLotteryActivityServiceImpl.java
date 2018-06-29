package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.DrawLotteryActivityParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityBO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.bo.LotteryInfoBO;
import com.lawu.eshop.activity.srv.converter.DrawLotteryActivityConverter;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDOExample;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDOExample;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityPrizeDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@Service
public class DrawLotteryActivityServiceImpl implements DrawLotteryActivityService {

    @Autowired
    private DrawLotteryActivityDOMapper drawLotteryActivityDOMapper;

    @Autowired
    private DrawLotteryActivityPrizeDOMapper drawLotteryActivityPrizeDOMapper;

    @Autowired
    private DrawLotteryActivityRecordDOMapper drawLotteryActivityRecordDOMapper;

    @Override
    public Page<DrawLotteryActivityBO> listDrawLotteryActivity(DrawLotteryActivityQuery query) {
        DrawLotteryActivityDOExample example = new DrawLotteryActivityDOExample();
        example.createCriteria().andStatusGreaterThanOrEqualTo(DrawLotteryActivityStatusEnum.LOTTERYING.getVal()).andStatusLessThanOrEqualTo(DrawLotteryActivityStatusEnum.FINISHED.getVal());
        example.setOrderByClause("status asc,grade asc");
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<DrawLotteryActivityDO> activityDOS = drawLotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        List<DrawLotteryActivityDO> lotteryActivityDOS = new ArrayList<>();
        List<DrawLotteryActivityDO> publishActivityDOS = new ArrayList<>();
        List<DrawLotteryActivityDO> finishActivityDOS = new ArrayList<>();
        for (DrawLotteryActivityDO activityDO : activityDOS) {
            if (activityDO.getStatus().byteValue() == DrawLotteryActivityStatusEnum.LOTTERYING.getVal()) {
                lotteryActivityDOS.add(activityDO);
            } else if (activityDO.getStatus().byteValue() == DrawLotteryActivityStatusEnum.PUBLISHED.getVal()) {
                publishActivityDOS.add(activityDO);
            } else if (activityDO.getStatus().byteValue() == DrawLotteryActivityStatusEnum.FINISHED.getVal()) {
                finishActivityDOS.add(activityDO);
            }
        }
        if (!publishActivityDOS.isEmpty()) {
            Collections.sort(publishActivityDOS, new Comparator<DrawLotteryActivityDO>() {
                @Override
                public int compare(DrawLotteryActivityDO o1, DrawLotteryActivityDO o2) {
                    return o1.getBeginTime().compareTo(o2.getBeginTime());
                }
            });
        }
        if (!finishActivityDOS.isEmpty()) {
            Collections.sort(finishActivityDOS, new Comparator<DrawLotteryActivityDO>() {
                @Override
                public int compare(DrawLotteryActivityDO o1, DrawLotteryActivityDO o2) {
                    return o2.getEndTime().compareTo(o1.getEndTime());
                }
            });
        }
        List<DrawLotteryActivityDO> allActivityDOS = new ArrayList<>();
        allActivityDOS.addAll(lotteryActivityDOS);
        allActivityDOS.addAll(publishActivityDOS);
        allActivityDOS.addAll(finishActivityDOS);

        Page<DrawLotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) drawLotteryActivityDOMapper.countByExample(example));
        page.setRecords(DrawLotteryActivityConverter.convertBOS(allActivityDOS));
        return page;
    }

    @Override
    public DrawLotteryActivityBO getDrawLotteryActivityDetail(Long id, String userNum) {
        DrawLotteryActivityDO activityDO = drawLotteryActivityDOMapper.selectByPrimaryKey(id);
        if (activityDO == null) {
            return null;
        }

        int prizeNumber = 0;
        List<DrawLotteryActivityPrizeBO> prizeBOS = new ArrayList<>();
        DrawLotteryActivityPrizeDOExample prizeDOExample = new DrawLotteryActivityPrizeDOExample();
        prizeDOExample.createCriteria().andDrawLotteryActivityIdEqualTo(id).andStatusEqualTo(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        List<DrawLotteryActivityPrizeDO> prizeDOS = drawLotteryActivityPrizeDOMapper.selectByExample(prizeDOExample);
        for (DrawLotteryActivityPrizeDO prizeDO : prizeDOS) {
            if (prizeDO.getNumber() != null && prizeDO.getNumber() > 0) {
                prizeNumber += prizeDO.getNumber();
            }
            DrawLotteryActivityPrizeBO prizeBO = new DrawLotteryActivityPrizeBO();
            prizeBO.setId(prizeDO.getId());
            prizeBO.setName(prizeDO.getName());
            prizeBO.setImgPath(prizeDO.getImgPath());
            prizeBO.setPrice(prizeDO.getPrice());
            prizeBO.setNumber(prizeDO.getNumber());
            prizeBO.setMerchantStoreId(prizeDO.getMerchantStoreId());
            prizeBOS.add(prizeBO);
        }

        DrawLotteryActivityRecordDOExample recordDOExample = new DrawLotteryActivityRecordDOExample();
        recordDOExample.createCriteria().andUserNumEqualTo(userNum).andDrawLotteryActivityIdEqualTo(id).andChannelEqualTo(DrawLotteryActivityRecordChannelEnum.FREE_LOTTERY.getVal());
        int freeLotteryCount = (int) drawLotteryActivityRecordDOMapper.countByExample(recordDOExample);

        DrawLotteryActivityBO activityBO = DrawLotteryActivityConverter.convertBO(activityDO);
        activityBO.setPrizeNumber(prizeNumber);
        activityBO.setPrizeBOS(prizeBOS);
        activityBO.setFreeLotteryCount(freeLotteryCount > 0 ? 0 : 1);
        recordDOExample = new DrawLotteryActivityRecordDOExample();
        recordDOExample.createCriteria().andUserNumEqualTo(userNum).andDrawLotteryActivityIdEqualTo(id).andStatusEqualTo(DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY.getVal());
        List<DrawLotteryActivityRecordDO> recordDOS = drawLotteryActivityRecordDOMapper.selectByExample(recordDOExample);
        if (!recordDOS.isEmpty()) {
            activityBO.setLotteryChannel(recordDOS.get(0).getChannel());
        }
        return activityBO;
    }

    @Override
    public Page<DrawLotteryActivityBO> listDrawLotteryActivityNotice(DrawLotteryActivityQuery query) {
        DrawLotteryActivityDOExample example = new DrawLotteryActivityDOExample();
        List<Byte> statusList = new ArrayList<>();
        statusList.add(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        statusList.add(DrawLotteryActivityStatusEnum.FINISHED.getVal());
        statusList.add(DrawLotteryActivityStatusEnum.CANCEL.getVal());
        example.createCriteria().andStatusIn(statusList);
        example.setOrderByClause("gmt_modified desc");

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<DrawLotteryActivityDO> activityDOS = drawLotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        List<DrawLotteryActivityBO> activityBOS = new ArrayList<>();
        List<Byte> recordStatusList = new ArrayList<>();
        recordStatusList.add(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal());
        recordStatusList.add(DrawLotteryActivityRecordStatusEnum.GIVE_UP_TAKE_LOTTERY.getVal());
        recordStatusList.add(DrawLotteryActivityRecordStatusEnum.TAKE_LOTTERY.getVal());
        recordStatusList.add(DrawLotteryActivityRecordStatusEnum.SEND_LOTTERY.getVal());
        for (DrawLotteryActivityDO activityDO : activityDOS) {
            DrawLotteryActivityRecordDOExample recordDOExample = new DrawLotteryActivityRecordDOExample();
            recordDOExample.createCriteria().andDrawLotteryActivityIdEqualTo(activityDO.getId()).andStatusIn(recordStatusList);
            recordDOExample.setOrderByClause("gmt_modified desc");
            List<DrawLotteryActivityRecordDO> recordDOS = drawLotteryActivityRecordDOMapper.selectByExample(recordDOExample);
            List<LotteryInfoBO> lotteryInfoBOS = new ArrayList<>();
            for (DrawLotteryActivityRecordDO recordDO : recordDOS) {
                LotteryInfoBO lotteryInfoBO = new LotteryInfoBO();
                lotteryInfoBO.setAccount(recordDO.getUserAccount());
                lotteryInfoBO.setPrizeName(recordDO.getPrizeName());
                lotteryInfoBOS.add(lotteryInfoBO);
            }
            DrawLotteryActivityBO activityBO = DrawLotteryActivityConverter.convertBO(activityDO);
            activityBO.setLotteryInfoBOS(lotteryInfoBOS);
            activityBOS.add(activityBO);
        }

        Page<DrawLotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) drawLotteryActivityDOMapper.countByExample(example));
        page.setRecords(activityBOS);
        return page;
    }

    @Override
    public DrawLotteryActivityBO getDrawLotteryActivityById(Long id) {
        DrawLotteryActivityDO activityDO = drawLotteryActivityDOMapper.selectByPrimaryKey(id);
        return DrawLotteryActivityConverter.convertBO(activityDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDrawLotteryActivity(DrawLotteryActivityParam param) {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName(param.getName());
        activityDO.setImgPath(param.getImgPath());
        activityDO.setGrade(param.getGrade());
        activityDO.setStatus(param.getStatusEnum().getVal());
        activityDO.setRemark(param.getRemark());
        activityDO.setBeginTime(DateUtil.formatDate(param.getBeginTime() + ":00", "yyyy-MM-dd HH:mm:ss"));
        activityDO.setEndTime(DateUtil.formatDate(param.getEndTime() + ":00", "yyyy-MM-dd HH:mm:ss"));
        if (param.getId() != null && param.getId() > 0) {
            activityDO.setId(param.getId());
            activityDO.setGmtModified(new Date());
            drawLotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);
        } else {
            activityDO.setGmtCreate(new Date());
            drawLotteryActivityDOMapper.insertSelective(activityDO);
        }
    }

    @Override
    public Page<DrawLotteryActivityBO> listOperatorDrawLotteryActivity(OperatorDrawLotteryActivityQuery query) {
        DrawLotteryActivityDOExample example = new DrawLotteryActivityDOExample();
        DrawLotteryActivityDOExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        if (StringUtils.isNotEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
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
        List<DrawLotteryActivityDO> activityDOS = drawLotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<DrawLotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) drawLotteryActivityDOMapper.countByExample(example));
        page.setRecords(DrawLotteryActivityConverter.convertBOS(activityDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDrawLotteryActivityStatus(Long id, DrawLotteryActivityStatusEnum statusEnum) {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setId(id);
        activityDO.setStatus(statusEnum.getVal());
        activityDO.setGmtModified(new Date());
        drawLotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeUpdateDrawLotteryActivityStatus() {
        Date date = new Date();
        //进行中更新为结束
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setStatus(DrawLotteryActivityStatusEnum.FINISHED.getVal());
        activityDO.setGmtModified(date);
        DrawLotteryActivityDOExample example = new DrawLotteryActivityDOExample();
        example.createCriteria().andStatusEqualTo(DrawLotteryActivityStatusEnum.LOTTERYING.getVal()).andEndTimeLessThanOrEqualTo(date);
        drawLotteryActivityDOMapper.updateByExampleSelective(activityDO, example);

        //即将开始更新为进行中
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        example = new DrawLotteryActivityDOExample();
        example.createCriteria().andStatusEqualTo(DrawLotteryActivityStatusEnum.PUBLISHED.getVal()).andBeginTimeLessThanOrEqualTo(date);
        drawLotteryActivityDOMapper.updateByExampleSelective(activityDO, example);
    }

}
