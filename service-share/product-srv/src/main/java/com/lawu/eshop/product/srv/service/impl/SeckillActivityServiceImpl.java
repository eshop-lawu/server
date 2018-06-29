/**
 * Administrator
 * 2017年11月23日
 */
package com.lawu.eshop.product.srv.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.product.constant.ActivityProductStatusEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.param.SeckillActivityPageQueryParam;
import com.lawu.eshop.product.param.SeckillActivitySaveParam;
import com.lawu.eshop.product.param.SeckillActivityUpdateParam;
import com.lawu.eshop.product.srv.bo.RecentlySeckillActivityBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityBO;
import com.lawu.eshop.product.srv.converter.SeckillActivityConverter;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDOExample;
import com.lawu.eshop.product.srv.mapper.SeckillActivityDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductDOMapper;
import com.lawu.eshop.product.srv.service.SeckillActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * 抢购服务接口实现类
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月27日
 */
@Service
public class SeckillActivityServiceImpl implements SeckillActivityService {

    @Autowired
    private SeckillActivityDOMapper seckillActivityDOMapper;
    
    @Autowired
    private SeckillActivityProductDOMapper seckillActivityProductDOMapper;
    
    @Override
    public Page<SeckillActivityBO> page(SeckillActivityPageQueryParam param) {
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        if (param.getActivityStatus() != null) {
            criteria.andActivityStatusEqualTo(param.getActivityStatus().getValue());
        }
        if (param.getMemberLevel() != null) {
            criteria.andMemberLevelEqualTo(param.getMemberLevel().getVal());
        }
        if (param.getStartDateLeft() != null && param.getStartDateRight() != null) {
            criteria.andStartDateBetween(param.getStartDateLeft(), param.getStartDateRight());
        } else if (param.getStartDateLeft() != null) {
            criteria.andStartDateGreaterThanOrEqualTo(param.getStartDateLeft());
        } else if (param.getStartDateRight() != null) {
            criteria.andStartDateLessThanOrEqualTo(param.getStartDateRight());
        }
        Page<SeckillActivityBO> rtn = new Page<>();
        rtn.setCurrentPage(param.getCurrentPage());
        long count = seckillActivityDOMapper.countByExample(example);
        rtn.setTotalCount((int) count);
        if (count <= 0 || param.getOffset() >= count) {
            rtn.setRecords(new ArrayList<>());
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(SeckillActivityConverter.convertSeckillActivityBOList(list));
        return rtn;
    }

    @Override
    public List<SeckillActivityBO> thatDayList() {
        List<SeckillActivityBO> rtn = thatDaylist(new Date());
        if (rtn != null && !rtn.isEmpty()) {
            return rtn;
        }
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        /*
         *  查询离今天最近的一个活动
         */
        // 查询近期预告的活动
        rtn = recentlyList();
        if (rtn != null && !rtn.isEmpty()) {
            return rtn;
        }
        // 查询已经结束的活动
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 已结束
        criteria.andActivityStatusEqualTo(ActivityStatusEnum.END.getValue());
        // 获取当前时间日期的格式化
        String todayDate = DateUtil.getDateFormat(new Date());
        // 获取开始时间小于今天的活动
        criteria.andStartDateLessThanOrEqualTo(DateUtil.formatDate(todayDate + " 23:59:59", DateUtil.DATETIME_DEFAULT_FORMAT));
        // 按开始时间倒序
        example.setOrderByClause("start_date desc");
        // 获取第一条记录
        RowBounds rowBounds = new RowBounds(0, 1);
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        // 最近没有活动直接返回
        if (list == null || list.isEmpty()) {
            return null;
        }
        return thatDaylist(list.get(0).getStartDate());
    }
    
    @Override
    public List<SeckillActivityBO> recentlyList() {
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        // 查询离今天最近的一个活动
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 未开始
        criteria.andActivityStatusEqualTo(ActivityStatusEnum.NOT_STARTED.getValue());
        // 获取当前时间日期的格式化
        String todayDate = DateUtil.getDateFormat(new Date());
        // 获取开始时间大于今天的活动
        criteria.andStartDateGreaterThanOrEqualTo(DateUtil.formatDate(todayDate + " 23:59:59", DateUtil.DATETIME_DEFAULT_FORMAT));
        // 按照开始时间排序
        example.setOrderByClause("start_date asc");
        // 获取第一条记录
        RowBounds rowBounds = new RowBounds(0, 1);
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        // 最近没有活动直接返回
        if (list == null || list.isEmpty()) {
            return null;
        }
        // 取出最近活动的开始时间
        return thatDaylist(list.get(0).getStartDate());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void seckillActivityStart() {
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 未开始
        criteria.andActivityStatusEqualTo(ActivityStatusEnum.NOT_STARTED.getValue());
        // 已经到开始时间了
        criteria.andStartDateLessThanOrEqualTo(new Date());
        // 按照开始时间正序
        example.setOrderByClause("start_date asc");
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExample(example);
        for (SeckillActivityDO item : list) {
            SeckillActivityDO seckillActivityDO = new SeckillActivityDO();
            seckillActivityDO.setId(item.getId());
            seckillActivityDO.setActivityStatus(ActivityStatusEnum.PROCESSING.getValue());
            seckillActivityDO.setGmtModified(new Date());
            seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityDO);
        }
    }
    
    @Override
    public void seckillActivityEnd() {
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 正在进行中的
        criteria.andActivityStatusEqualTo(ActivityStatusEnum.PROCESSING.getValue());
        // 已经到结束时间了
        criteria.andEndDateLessThanOrEqualTo(new Date());
        // 按照开始时间正序
        example.setOrderByClause("end_date asc");
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExample(example);
        for (SeckillActivityDO item : list) {
            SeckillActivityDO seckillActivityDO = new SeckillActivityDO();
            seckillActivityDO.setId(item.getId());
            seckillActivityDO.setActivityStatus(ActivityStatusEnum.END.getValue());
            seckillActivityDO.setGmtModified(new Date());
            seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityDO);
        }
    }
    
    @Override
    public SeckillActivityBO get(Long id) throws DataNotExistException {
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
        if (seckillActivityDO == null) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        return SeckillActivityConverter.convert(seckillActivityDO);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) throws DataNotExistException, WrongOperationException {
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
        if (seckillActivityDO == null || StatusEnum.INVALID.getValue().equals(seckillActivityDO.getStatus())) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        if (!ActivityStatusEnum.UNPUBLISHED.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动发布之后不能删除");
        }
        SeckillActivityDO seckillActivityUpdateDO = new SeckillActivityDO();
        seckillActivityUpdateDO.setId(seckillActivityDO.getId());
        seckillActivityUpdateDO.setStatus(StatusEnum.INVALID.getValue());
        seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityUpdateDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void release(Long id) throws DataNotExistException, WrongOperationException {
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
        if (seckillActivityDO == null || StatusEnum.INVALID.getValue().equals(seckillActivityDO.getStatus())) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        if (!ActivityStatusEnum.UNPUBLISHED.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动已发布");
        }
        SeckillActivityDO seckillActivityUpdateDO = new SeckillActivityDO();
        seckillActivityUpdateDO.setId(seckillActivityDO.getId());
        seckillActivityUpdateDO.setActivityStatus(ActivityStatusEnum.PUBLISHED.getValue());
        seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityUpdateDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void down(Long id) throws DataNotExistException, WrongOperationException {
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
        if (seckillActivityDO == null || StatusEnum.INVALID.getValue().equals(seckillActivityDO.getStatus())) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        if (ActivityStatusEnum.UNPUBLISHED.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动还未发布不能下架");
        }
        if (ActivityStatusEnum.END.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动已经结束");
        }
        SeckillActivityDO seckillActivityUpdateDO = new SeckillActivityDO();
        seckillActivityUpdateDO.setId(seckillActivityDO.getId());
        seckillActivityUpdateDO.setActivityStatus(ActivityStatusEnum.END.getValue());
        seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityUpdateDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Long id, SeckillActivityUpdateParam param) throws DataNotExistException, WrongOperationException {
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
        if (seckillActivityDO == null || StatusEnum.INVALID.getValue().equals(seckillActivityDO.getStatus())) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        if (!ActivityStatusEnum.UNPUBLISHED.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动已经发布不能编辑");
        }
        SeckillActivityDO seckillActivityUpdateDO = new SeckillActivityDO();
        seckillActivityUpdateDO.setId(seckillActivityDO.getId());
        seckillActivityUpdateDO.setEndDate(param.getEndDate());
        seckillActivityUpdateDO.setAttentEndDate(param.getAttentEndDate());
        seckillActivityUpdateDO.setGmtModified(new Date());
        seckillActivityUpdateDO.setMemberLevel(param.getMemberLevel().getVal());
        seckillActivityUpdateDO.setName(param.getName());
        seckillActivityUpdateDO.setPicture(param.getPicture());
        seckillActivityUpdateDO.setHomePicture(param.getHomePicture());
        seckillActivityUpdateDO.setProductValidCount(param.getProductValidCount());
        seckillActivityUpdateDO.setStartDate(param.getStartDate());
        seckillActivityUpdateDO.setRemark(param.getRemark());
        seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityUpdateDO);
    }
    

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void seckillActivityRegistrationEnd() {
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 正在进行中的
        criteria.andActivityStatusEqualTo(ActivityStatusEnum.PUBLISHED.getValue());
        // 快到活动开始时间了，结束抢购活动报名
        criteria.andAttentEndDateLessThanOrEqualTo(new Date());
        // 按照开始时间正序
        example.setOrderByClause("start_date asc");
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExample(example);
        for (SeckillActivityDO item : list) {
            SeckillActivityDO seckillActivityDO = new SeckillActivityDO();
            seckillActivityDO.setId(item.getId());
            seckillActivityDO.setActivityStatus(ActivityStatusEnum.IN_REVIEW.getValue());
            seckillActivityDO.setGmtModified(new Date());
            seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityDO);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void audit(Long id) throws DataNotExistException, WrongOperationException {
        SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
        if (seckillActivityDO == null || StatusEnum.INVALID.getValue().equals(seckillActivityDO.getStatus())) {
            throw new DataNotExistException("抢购活动数据不存在");
        }
        if (!ActivityStatusEnum.IN_REVIEW.getValue().equals(seckillActivityDO.getActivityStatus())) {
            throw new WrongOperationException("抢购活动不在审核中");
        }
        SeckillActivityProductDOExample seckillActivityProductDOExample = new SeckillActivityProductDOExample();
        seckillActivityProductDOExample.createCriteria().andActivityIdEqualTo(id).andStatusEqualTo(ActivityProductStatusEnum.AUDITED.getValue());
        // 查询通过当前活动通过审核的商品数量
        Long count = seckillActivityProductDOMapper.countByExample(seckillActivityProductDOExample);
        if (count <= 0) {
            throw new WrongOperationException("当前抢购活动下没有通过审核的商品");
        }
        SeckillActivityDO seckillActivityUpdateDO = new SeckillActivityDO();
        seckillActivityUpdateDO.setId(seckillActivityDO.getId());
        seckillActivityUpdateDO.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
        seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityUpdateDO);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SeckillActivitySaveParam param) {
        SeckillActivityDO seckillActivityUpdateDO = new SeckillActivityDO();
        seckillActivityUpdateDO.setEndDate(param.getEndDate());
        seckillActivityUpdateDO.setAttentEndDate(param.getAttentEndDate());
        seckillActivityUpdateDO.setGmtModified(new Date());
        seckillActivityUpdateDO.setMemberLevel(param.getMemberLevel().getVal());
        seckillActivityUpdateDO.setName(param.getName());
        seckillActivityUpdateDO.setPicture(param.getPicture());
        seckillActivityUpdateDO.setHomePicture(param.getHomePicture());
        seckillActivityUpdateDO.setProductValidCount(param.getProductValidCount());
        seckillActivityUpdateDO.setStartDate(param.getStartDate());
        seckillActivityUpdateDO.setStatus(StatusEnum.VALID.getValue());
        seckillActivityUpdateDO.setActivityStatus(ActivityStatusEnum.UNPUBLISHED.getValue());
        seckillActivityUpdateDO.setIsRemind(false);
        seckillActivityUpdateDO.setGmtCreate(new Date());
        seckillActivityUpdateDO.setGmtModified(new Date());
        seckillActivityUpdateDO.setRemark(param.getRemark());
        seckillActivityDOMapper.insertSelective(seckillActivityUpdateDO);
    }

    @Override
    public RecentlySeckillActivityBO recentlyActivity() {
        RecentlySeckillActivityBO rtn = new RecentlySeckillActivityBO();
        // 查询当天所有活动
        List<SeckillActivityBO> thatDaylist = thatDayList();
        if (thatDaylist == null || thatDaylist.isEmpty()) {
            return rtn;
        }
        List<SeckillActivityBO> notStartedActivityList = new ArrayList<>();
        List<SeckillActivityBO> processingActivityList = new ArrayList<>();
        List<SeckillActivityBO> endActivityList = new ArrayList<>();
        // 按照活动状态排序
        thatDaylist.forEach(item -> {
            switch (item.getActivityStatus()) {
                case NOT_STARTED:
                    notStartedActivityList.add(item);
                    break;
                case PROCESSING:
                    processingActivityList.add(item);
                    break;
                case END:
                    endActivityList.add(item);
                    break;
                default:
                    break;
            }
        });
        SeckillActivityBO recentlyActivity = null;
        if (!processingActivityList.isEmpty()) {
            recentlyActivity = processingActivityList.get(0);
        } else if (!notStartedActivityList.isEmpty()) {
            recentlyActivity = notStartedActivityList.get(0);
        } else if (!endActivityList.isEmpty()) {
            recentlyActivity = endActivityList.get(endActivityList.size() - 1);
        }
        rtn.setActivityStatus(recentlyActivity.getActivityStatus());
        rtn.setHomePicture(recentlyActivity.getHomePicture());
        switch (recentlyActivity.getActivityStatus()) {
            case NOT_STARTED:
                rtn.setCountdown(DateUtil.interval(new Date(), recentlyActivity.getStartDate(), Calendar.MILLISECOND));
                break;
            case PROCESSING:
                rtn.setCountdown(DateUtil.interval(new Date(), recentlyActivity.getEndDate(), Calendar.MILLISECOND));
                break;
            default:
                break;
        }
        rtn.setId(recentlyActivity.getId());
        return rtn;
    }
    
    /**********************************************************************
     * PRIVATE METHOD
     * **********************************************************************/
    /**
     * 根据传入的日期查询当天的所有活动
     * 
     * @param day
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    private List<SeckillActivityBO> thatDaylist(Date day) {
        SeckillActivityDOExample example = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria criteria = example.createCriteria();
        // 有效的记录
        criteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 获取当前时间日期的格式化
        String date = DateUtil.getDateFormat(day);
        // 获取当天的所有抢购活动。
        Date startDatetime = DateUtil.formatDate(date + " 00:00:00", DateUtil.DATETIME_DEFAULT_FORMAT);
        Date endDatetime = DateUtil.formatDate(date + " 23:59:59", DateUtil.DATETIME_DEFAULT_FORMAT);
        criteria.andStartDateBetween(startDatetime, endDatetime);
        List<Byte> activityStatusList = new ArrayList<>();
        activityStatusList.add(ActivityStatusEnum.NOT_STARTED.getValue());
        activityStatusList.add(ActivityStatusEnum.PROCESSING.getValue());
        activityStatusList.add(ActivityStatusEnum.END.getValue());
        criteria.andActivityStatusIn(activityStatusList);
        // 按照开始时间正序
        example.setOrderByClause("start_date asc");
        List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExample(example);
        return SeckillActivityConverter.convertSeckillActivityBOList(list);
    }

}
