package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.param.LotteryActivityParam;
import com.lawu.eshop.mall.query.ListLotteryActivityQuery;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.eshop.mall.srv.bo.LotteryActivityBO;
import com.lawu.eshop.mall.srv.converter.LotteryActivityConverter;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDOExample;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDO;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDOExample;
import com.lawu.eshop.mall.srv.mapper.LotteryActivityDOMapper;
import com.lawu.eshop.mall.srv.mapper.LotteryRecordDOMapper;
import com.lawu.eshop.mall.srv.service.LotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@Service
public class LotteryActivityServiceImpl implements LotteryActivityService {

    @Autowired
    private LotteryActivityDOMapper lotteryActivityDOMapper;

    @Autowired
    private LotteryRecordDOMapper lotteryRecordDOMapper;

    @Override
    public Page<LotteryActivityBO> listLotteryActivity(LotteryActivityRealQuery query) {
        LotteryActivityDOExample example = new LotteryActivityDOExample();
        example.createCriteria().andStatusGreaterThanOrEqualTo(LotteryActivityStatusEnum.LOTTERYING.getVal()).andStatusLessThanOrEqualTo(LotteryActivityStatusEnum.FINISHED.getVal());
        example.setOrderByClause("status asc,grade asc");

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<LotteryActivityDO> activityDOS = lotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<LotteryActivityBO> activityBOS = new ArrayList<>();
        if (!activityDOS.isEmpty()) {
            for (LotteryActivityDO activityDO : activityDOS) {
                LotteryActivityBO activityBO = LotteryActivityConverter.converBO(activityDO);

                LotteryRecordDOExample recordDOExample = new LotteryRecordDOExample();
                LotteryRecordDOExample.Criteria criteria = recordDOExample.createCriteria();
                //查询参与人数
                criteria.andLotteryActivityIdEqualTo(activityDO.getId());
                int lotteryNumber = lotteryRecordDOMapper.countByExample(recordDOExample);
                activityBO.setLotteryNumber(lotteryNumber);

                //查询参与次数
                if (StringUtils.isNotEmpty(query.getUserNum())) {
                    criteria.andUserNumEqualTo(query.getUserNum());
                    List<LotteryRecordDO> recordDOS = lotteryRecordDOMapper.selectByExample(recordDOExample);
                    if (recordDOS.isEmpty()) {
                        activityBO.setLotteryCount(0);
                    } else {
                        activityBO.setLotteryCount(recordDOS.get(0).getLotteryCount());
                    }
                } else {
                    activityBO.setLotteryCount(0);
                }
                activityBOS.add(activityBO);
            }
        }

        Page<LotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(lotteryActivityDOMapper.countByExample(example));
        page.setRecords(activityBOS);
        return page;
    }

    @Override
    public LotteryActivityBO getLotteryActivityById(Long id) {
        LotteryActivityDO activityDO = lotteryActivityDOMapper.selectByPrimaryKey(id);
        return LotteryActivityConverter.converBO(activityDO);
    }

    @Override
    public Page<LotteryActivityBO> listOperatorLotteryActivity(ListLotteryActivityQuery query) {
        LotteryActivityDOExample example = new LotteryActivityDOExample();
        LotteryActivityDOExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        if (StringUtils.isNotEmpty(query.getPrizeName())) {
            criteria.andPrizeNameLike("%" + query.getPrizeName() + "%");
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
        List<LotteryActivityDO> activityDOS = lotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<LotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(lotteryActivityDOMapper.countByExample(example));
        page.setRecords(LotteryActivityConverter.converBOS(activityDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLotteryActivityStatus(Long id, LotteryActivityStatusEnum statusEnum) {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setId(id);
        activityDO.setStatus(statusEnum.getVal());
        activityDO.setGmtModified(new Date());
        lotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLotteryActivity(LotteryActivityParam param) {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setPrizeName(param.getPrizeName());
        activityDO.setPrizePrice(param.getPrizePrice());
        activityDO.setPrizeNumber(param.getPrizeNumber());
        activityDO.setImagePath(param.getImagePath());
        activityDO.setBeginTime(DateUtil.formatDate(param.getBeginTime() + ":00", "yyyy-MM-dd HH:mm:ss"));
        activityDO.setEndTime(DateUtil.formatDate(param.getEndTime() + ":00", "yyyy-MM-dd HH:mm:ss"));
        activityDO.setGrade(param.getGrade());
        activityDO.setStatus(param.getStatusEnum().getVal());
        if (param.getId() != null && param.getId() > 0) {
            activityDO.setId(param.getId());
            activityDO.setGmtModified(new Date());
            lotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);
        } else {
            activityDO.setGmtCreate(new Date());
            lotteryActivityDOMapper.insertSelective(activityDO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeUpdateLotteryActivityStatus() {
        Date date = new Date();
        //进行中更新为结束
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.FINISHED.getVal());
        activityDO.setGmtModified(new Date());
        LotteryActivityDOExample example = new LotteryActivityDOExample();
        example.createCriteria().andStatusEqualTo(LotteryActivityStatusEnum.LOTTERYING.getVal()).andEndTimeLessThanOrEqualTo(date);
        lotteryActivityDOMapper.updateByExampleSelective(activityDO, example);

        //即将开始更新为进行中
        activityDO.setStatus(LotteryActivityStatusEnum.LOTTERYING.getVal());
        example = new LotteryActivityDOExample();
        example.createCriteria().andStatusEqualTo(LotteryActivityStatusEnum.PUBLISHED.getVal()).andBeginTimeLessThanOrEqualTo(date);
        lotteryActivityDOMapper.updateByExampleSelective(activityDO, example);
    }

}
