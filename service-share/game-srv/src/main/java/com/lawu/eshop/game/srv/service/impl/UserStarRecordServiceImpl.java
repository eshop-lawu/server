package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.game.constants.StarRecordStatusEnum;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.game.param.UserStarRecordParam;
import com.lawu.eshop.game.param.UserStartParam;
import com.lawu.eshop.game.srv.bo.RankListBO;
import com.lawu.eshop.game.srv.bo.UserRankBO;
import com.lawu.eshop.game.srv.converter.UserStarRecordConverter;
import com.lawu.eshop.game.srv.domain.UserStarRecordDO;
import com.lawu.eshop.game.srv.domain.UserStarRecordDOExample;
import com.lawu.eshop.game.srv.mapper.UserStarRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.extend.UserStarRecordDOMapperExtend;
import com.lawu.eshop.game.srv.service.UserStarRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
@Service
public class UserStarRecordServiceImpl implements UserStarRecordService {
    @Autowired
    private UserStarRecordDOMapper userStarRecordDOMapper;

    @Autowired
    private UserStarRecordDOMapperExtend userStarRecordDOMapperExtend;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserStartRecord(UserStarRecordParam param) {
        Date nowTime = new Date();
        UserStarRecordDOExample example = new UserStarRecordDOExample();
        example.createCriteria().andUserNumEqualTo(param.getUserNum()).andGmtReportEqualTo(DateUtil.getFirstDayOfMonth(nowTime));
        List<UserStarRecordDO> oldRecords = userStarRecordDOMapper.selectByExample(example);

        if (oldRecords.isEmpty()) {
            //新增记录
            UserStarRecordDO starRecordDO = new UserStarRecordDO();
            starRecordDO.setUserNum(param.getUserNum());
            starRecordDO.setAccount(param.getAccount());
            starRecordDO.setGmtCreate(nowTime);
            starRecordDO.setGmtReport(DateUtil.getFirstDayOfMonth(nowTime));
            starRecordDO.setGmtModified(nowTime);
            if (StarRecordStatusEnum.INCREASE == param.getStatus()) {
                starRecordDO.setMonthStarCount(param.getStarCount());
            } else {
                starRecordDO.setMonthStarCount(0);
            }
            userStarRecordDOMapper.insertSelective(starRecordDO);
        } else {
            UserStartParam startParam = new UserStartParam();
            startParam.setId(oldRecords.get(0).getId());
            startParam.setStarCount(param.getStarCount());
            startParam.setGmtModified(nowTime);
            if (StarRecordStatusEnum.INCREASE == param.getStatus()) {
                userStarRecordDOMapperExtend.addStarRecord(startParam);
            } else {
                try {
                    userStarRecordDOMapperExtend.reduceStarMonthRecord(startParam);
                } catch (Exception e) {
                    UserStarRecordDO monthRecord = new UserStarRecordDO();
                    monthRecord.setMonthStarCount(0);
                    monthRecord.setId(oldRecords.get(0).getId());
                    userStarRecordDOMapper.updateByPrimaryKeySelective(monthRecord);
                }

            }
        }

    }

    @Override
    public Page<RankListBO> getStarRankList(RankListParam param) {
        Page<RankListBO> page = new Page<>();
        UserStarRecordDOExample example = new UserStarRecordDOExample();
        UserStarRecordDOExample.Criteria criteria = example.createCriteria();
        criteria.andGmtReportEqualTo(param.getReportDate());
        if (param.getStatus() != null && param.getStatus()) {
            criteria.andStatusEqualTo(true);
        }
        if (StringUtils.isNotEmpty(param.getAccount())) {
            criteria.andAccountEqualTo(param.getAccount());
        }
        example.setOrderByClause("month_star_count desc,gmt_modified asc");
        RowBounds rowBounds = new RowBounds(param.getOffset(),param.getPageSize());

        page.setTotalCount((int) userStarRecordDOMapper.countByExample(example));
        page.setCurrentPage(param.getCurrentPage());
        List<UserStarRecordDO> recordDOS = userStarRecordDOMapper.selectByExampleWithRowbounds(example,rowBounds);
        page.setRecords(UserStarRecordConverter.converterRankBOS(recordDOS));
        return page;
    }

    @Override
    public UserRankBO currentUserRank(String userNum) {
        UserRankBO rankBO = new UserRankBO();
        Date reportDate = DateUtil.getFirstDayOfMonth(new Date());
        UserStarRecordDOExample example = new UserStarRecordDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andGmtReportEqualTo(reportDate).andStatusEqualTo(true);
        List<UserStarRecordDO> list = userStarRecordDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            rankBO.setStarCount(0);
            rankBO.setRank(0);
        } else {
            rankBO.setStarCount(list.get(0).getMonthStarCount());
            UserStarRecordDOExample example2 = new UserStarRecordDOExample();
            example2.createCriteria().andGmtReportEqualTo(reportDate).andMonthStarCountGreaterThan(list.get(0).getMonthStarCount());
            //星星数量大于当前用户总数
            int greaterStar = (int) userStarRecordDOMapper.countByExample(example2);
            //星星数量等于当前用户总数
            UserStarRecordDOExample example3 = new UserStarRecordDOExample();
            example3.createCriteria().andGmtReportEqualTo(reportDate)
                    .andMonthStarCountEqualTo(list.get(0).getMonthStarCount())
                    .andGmtModifiedLessThanOrEqualTo(list.get(0).getGmtModified());
            int lessStar = (int) userStarRecordDOMapper.countByExample(example3);
            rankBO.setRank(greaterStar + lessStar);
        }
        return rankBO;
    }

    @Override
    @Transactional
    public void editStarStatus(Long id, Boolean status) {
        UserStarRecordDO recordDO = new UserStarRecordDO();
        recordDO.setId(id);
        recordDO.setStatus(status);
        userStarRecordDOMapper.updateByPrimaryKeySelective(recordDO);
    }

    @Override
    @Transactional
    public void addStarById(Long id, Integer starCount) {
        UserStarRecordDO recordDO = new UserStarRecordDO();
        recordDO.setId(id);
        recordDO.setMonthStarCount(starCount);
        userStarRecordDOMapper.updateByPrimaryKeySelective(recordDO);
    }

}
