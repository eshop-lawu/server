package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.param.LotteryRecordParam;
import com.lawu.eshop.mall.query.LotteryRecordQuery;
import com.lawu.eshop.mall.query.OperatorLotteryRecordQuery;
import com.lawu.eshop.mall.srv.bo.LotteryInfoBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordOperatorBO;
import com.lawu.eshop.mall.srv.converter.LotteryRecordConverter;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDOExample;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDO;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDOExample;
import com.lawu.eshop.mall.srv.mapper.LotteryActivityDOMapper;
import com.lawu.eshop.mall.srv.mapper.LotteryRecordDOMapper;
import com.lawu.eshop.mall.srv.service.LotteryRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;
import com.lawu.utils.StringUtil;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@Service
public class LotteryRecordServiceImpl implements LotteryRecordService {

    @Autowired
    private LotteryRecordDOMapper lotteryRecordDOMapper;

    @Autowired
    private LotteryActivityDOMapper lotteryActivityDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLotteryRecord(LotteryRecordParam param) {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserId(param.getUserId());
        recordDO.setUserNum(param.getUserNum());
        recordDO.setAccount(param.getAccount());
        recordDO.setLotteryActivityId(param.getLotteryActivityId());
        recordDO.setPrizeName(param.getPrizeName());
        recordDO.setLotteryCount(1);
        recordDO.setGmtCreate(new Date());
        lotteryRecordDOMapper.insertSelective(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLotteryCountByUserNumAndLotteryActivityId(String userNum, Long lotteryActivityId) {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setLotteryCount(2);
        LotteryRecordDOExample example = new LotteryRecordDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andLotteryActivityIdEqualTo(lotteryActivityId);
        lotteryRecordDOMapper.updateByExampleSelective(recordDO, example);
    }

    @Override
    public List<LotteryInfoBO> listLotteryInfo() {
        String dateStr = DateUtil.getDate();
        Date date = DateUtil.formatDate(dateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date lastDate = new Date();

        LotteryRecordDOExample example = new LotteryRecordDOExample();
        example.createCriteria().andGmtModifiedLessThan(date).andLotteryResultEqualTo(true);
        example.setOrderByClause("gmt_modified desc");
        List<LotteryRecordDO> recordDOS = lotteryRecordDOMapper.selectByExample(example);
        if (!recordDOS.isEmpty()) {
            lastDate = recordDOS.get(0).getGmtModified();
        }
        String lastDateStr = DateUtil.getDateFormat(lastDate, "yyyy-MM-dd");
        Date lastBeginDate = DateUtil.formatDate(lastDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date lastEndDate = DateUtil.formatDate(lastDateStr + " 23:59:59", "yyyy-MM-dd HH:mm:ss");

        example = new LotteryRecordDOExample();
        example.createCriteria().andGmtModifiedGreaterThanOrEqualTo(lastBeginDate).andGmtModifiedLessThanOrEqualTo(lastEndDate).andLotteryResultEqualTo(true);
        example.setOrderByClause("gmt_modified desc");
        recordDOS = lotteryRecordDOMapper.selectByExample(example);

        List<LotteryInfoBO> recordBOS = new ArrayList<>();
        for (LotteryRecordDO recordDO : recordDOS) {
            LotteryInfoBO infoBO = new LotteryInfoBO();
            infoBO.setPrizeName(recordDO.getPrizeName());
            infoBO.setAccount(recordDO.getAccount());
            recordBOS.add(infoBO);
        }
        return recordBOS;
    }

    @Override
    public Page<LotteryRecordBO> listLotteryRecord(LotteryRecordQuery query) {
        String dateStr = DateUtil.getDate();
        Date date = DateUtil.formatDate(dateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date lastDate = new Date();

        LotteryActivityDOExample example = new LotteryActivityDOExample();
        example.createCriteria().andGmtModifiedLessThan(date).andStatusEqualTo(LotteryActivityStatusEnum.LOTTERYED.getVal());
        example.setOrderByClause("gmt_modified desc");
        List<LotteryActivityDO> activityDOS = lotteryActivityDOMapper.selectByExample(example);
        if (!activityDOS.isEmpty()) {
            lastDate = activityDOS.get(0).getGmtModified();
        }
        String lastDateStr = DateUtil.getDateFormat(lastDate, "yyyy-MM-dd");
        Date lastBeginDate = DateUtil.formatDate(lastDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date lastEndDate = DateUtil.formatDate(lastDateStr + " 23:59:59", "yyyy-MM-dd HH:mm:ss");

        example = new LotteryActivityDOExample();
        example.createCriteria().andGmtModifiedGreaterThanOrEqualTo(lastBeginDate).andGmtModifiedLessThanOrEqualTo(lastEndDate).andStatusEqualTo(LotteryActivityStatusEnum.LOTTERYED.getVal());
        example.setOrderByClause("gmt_modified desc");
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        activityDOS = lotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<LotteryRecordBO> recordDOS = new ArrayList<>();
        for (LotteryActivityDO activityDO : activityDOS) {
            LotteryRecordBO recordBO = LotteryRecordConverter.converBO(activityDO);

            //查询参与人数
            LotteryRecordDOExample recordDOExample = new LotteryRecordDOExample();
            LotteryRecordDOExample.Criteria criteria = recordDOExample.createCriteria();
            criteria.andLotteryActivityIdEqualTo(activityDO.getId());
            int lotteryNumber = lotteryRecordDOMapper.countByExample(recordDOExample);
            recordBO.setLotteryNumber(lotteryNumber);

            //查询中奖列表
            criteria.andLotteryResultEqualTo(true);
            recordDOExample.setOrderByClause("gmt_modified desc");
            List<LotteryRecordDO> lotteryRecordDOS = lotteryRecordDOMapper.selectByExample(recordDOExample);
            List<String> lotteryAccountList = new ArrayList<>();
            for (LotteryRecordDO recordDO : lotteryRecordDOS) {
                lotteryAccountList.add(StringUtil.hideUserAccount(recordDO.getAccount()));
            }
            recordBO.setLotteryAccountList(lotteryAccountList);
            recordDOS.add(recordBO);
        }

        Page<LotteryRecordBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(lotteryActivityDOMapper.countByExample(example));
        page.setRecords(recordDOS);
        return page;
    }

    @Override
    public Page<LotteryRecordOperatorBO> listOperatorLotteryRecord(OperatorLotteryRecordQuery query) {
        LotteryRecordDOExample example = new LotteryRecordDOExample();
        LotteryRecordDOExample.Criteria criteria = example.createCriteria();
        criteria.andLotteryActivityIdEqualTo(query.getLotteryActivityId());
        if (query.getLotteryResult() != null) {
            criteria.andLotteryResultEqualTo(query.getLotteryResult());
        }
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<LotteryRecordDO> recordDOS = lotteryRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<LotteryRecordOperatorBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(lotteryRecordDOMapper.countByExample(example));
        page.setRecords(LotteryRecordConverter.converOperatorBOS(recordDOS));
        return page;
    }

    @Override
    public Boolean lotteryRecord(Long lotteryActivityId, String userNum) {
        LotteryRecordDOExample example = new LotteryRecordDOExample();
        example.createCriteria().andLotteryActivityIdEqualTo(lotteryActivityId).andUserNumEqualTo(userNum);
        int count = lotteryRecordDOMapper.countByExample(example);
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateLotteryResult(Long lotteryActivityId, String account) {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setLotteryResult(true);
        LotteryRecordDOExample example = new LotteryRecordDOExample();
        example.createCriteria().andLotteryActivityIdEqualTo(lotteryActivityId).andAccountEqualTo(account);
        int result = lotteryRecordDOMapper.updateByExampleSelective(recordDO, example);
        if (result == 0) {
            return ResultCode.FAIL;
        }
        return ResultCode.SUCCESS;
    }

}
