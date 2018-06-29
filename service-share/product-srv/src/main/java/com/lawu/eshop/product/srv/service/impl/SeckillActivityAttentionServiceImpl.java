package com.lawu.eshop.product.srv.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.mq.dto.product.SeckillActivityAboutStartNoticeNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.param.SeckillActivityProductAttentionParam;
import com.lawu.eshop.product.srv.bo.SeckillActivityAttentionBO;
import com.lawu.eshop.product.srv.constants.PropertyConstant;
import com.lawu.eshop.product.srv.converter.SeckillActivityAttentionConverter;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityAttentionDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityAttentionDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityAttentionDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.SeckillActivityProductDOExtendMapper;
import com.lawu.eshop.product.srv.service.SeckillActivityAttentionService;
import com.lawu.mq.message.MessageProducerService;
import com.lawu.utils.DateUtil;

/**
 * 抢购活动关注服务接口实现类
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@Service
public class SeckillActivityAttentionServiceImpl implements SeckillActivityAttentionService {
    
    @Autowired
    private SeckillActivityDOMapper seckillActivityDOMapper;
    
    @Autowired
    private SeckillActivityProductDOMapper seckillActivityProductDOMapper;
    
    @Autowired
    private SeckillActivityProductDOExtendMapper seckillActivityProductDOExtendMapper;
    
    @Autowired
    private SeckillActivityAttentionDOMapper seckillActivityAttentionDOMapper;
    
    @Autowired
    private ProductDOMapper productDOMapper;
    
    @Autowired
    private MessageProducerService messageProducerService;
    
    @Override
    public void attention(Long activityProductId, SeckillActivityProductAttentionParam param) throws DataNotExistException {
        // 查询商品信息
        SeckillActivityProductDO seckillActivityProductDO = seckillActivityProductDOMapper.selectByPrimaryKey(activityProductId);
        if (seckillActivityProductDO == null) {
            throw new DataNotExistException("抢购活动商品数据不存在");
        }
        SeckillActivityAttentionDO seckillActivityAttentionDO = new SeckillActivityAttentionDO();
        seckillActivityAttentionDO.setActivityId(seckillActivityProductDO.getActivityId());
        seckillActivityAttentionDO.setActivityProductId(seckillActivityProductDO.getId());
        seckillActivityAttentionDO.setGmtCreate(new Date());
        seckillActivityAttentionDO.setGmtModified(new Date());
        seckillActivityAttentionDO.setMemberId(param.getMemberId());
        seckillActivityAttentionDO.setMemberNum(param.getMemberNum());
        seckillActivityAttentionDO.setProductId(seckillActivityProductDO.getProductId());
        seckillActivityAttentionDOMapper.insert(seckillActivityAttentionDO);
        
        // 增加关注人数
        seckillActivityProductDOExtendMapper.increaseAttentionCount(activityProductId);
    }
    
    @Override
    public List<SeckillActivityAttentionBO> selectAboutStartSeckillActivityAttention(int offset, int pageSize) {
        SeckillActivityDOExample seckillActivityDOExample = new SeckillActivityDOExample();
        SeckillActivityDOExample.Criteria seckillActivityDOExampleCriteria = seckillActivityDOExample.createCriteria();
        // 有效的记录
        seckillActivityDOExampleCriteria.andStatusEqualTo(StatusEnum.VALID.getValue());
        // 未开始状态的
        seckillActivityDOExampleCriteria.andActivityStatusEqualTo(ActivityStatusEnum.NOT_STARTED.getValue());
        // 开始时间在十分钟以后的
        seckillActivityDOExampleCriteria.andStartDateLessThanOrEqualTo(DateUtil.add(new Date(), PropertyConstant.PROMPT_SECKILL_ACTIVITY_ABOUT_START_TIME, Calendar.MINUTE));
        // 未提醒过的
        seckillActivityDOExampleCriteria.andIsRemindEqualTo(false);
        // 根据开始时间排序
        seckillActivityDOExample.setOrderByClause("start_date asc");
        // 只查第一条记录
        RowBounds seckillActivityDORowBounds = new RowBounds(0, 1);
        List<SeckillActivityDO> seckillActivityDOList = seckillActivityDOMapper.selectByExampleWithRowbounds(seckillActivityDOExample, seckillActivityDORowBounds);
        if (seckillActivityDOList == null || seckillActivityDOList.isEmpty()) {
            return null;
        }
        SeckillActivityAttentionDOExample seckillActivityAttentionDOExample = new SeckillActivityAttentionDOExample();
        SeckillActivityAttentionDOExample.Criteria seckillActivityAttentionDOExampleCriteria = seckillActivityAttentionDOExample.createCriteria();
        seckillActivityAttentionDOExampleCriteria.andActivityIdEqualTo(seckillActivityDOList.get(0).getId());
        RowBounds seckillActivityAttentionDORowBounds = new RowBounds(offset, pageSize);
        List<SeckillActivityAttentionDO> seckillActivityAttentionDOList = seckillActivityAttentionDOMapper.selectByExampleWithRowbounds(seckillActivityAttentionDOExample, seckillActivityAttentionDORowBounds);
        /*
         *  如果已经查到最后一页
         *  更新抢购活动为已经提醒
         */
        if (seckillActivityAttentionDOList == null || seckillActivityAttentionDOList.isEmpty()) {
            SeckillActivityDO seckillActivityDO = new SeckillActivityDO();
            seckillActivityDO.setId(seckillActivityDOList.get(0).getId());
            seckillActivityDO.setIsRemind(true);
            seckillActivityDO.setGmtModified(new Date());
            seckillActivityDOMapper.updateByPrimaryKeySelective(seckillActivityDO);
        }
        return SeckillActivityAttentionConverter.convertSeckillActivityAttentionBOList(seckillActivityAttentionDOList);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void promptSeckillActivityAboutStart(SeckillActivityAttentionBO seckillActivityAttentionBO) throws DataNotExistException {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(seckillActivityAttentionBO.getProductId());
        if (productDO == null) {
            throw new DataNotExistException("商品数据不存在");
        }
        SeckillActivityAboutStartNoticeNotification notification = new SeckillActivityAboutStartNoticeNotification();
        notification.setSeckillActivityAttentionId(seckillActivityAttentionBO.getId());
        notification.setActivityProductId(seckillActivityAttentionBO.getActivityProductId());
        notification.setMemberNum(seckillActivityAttentionBO.getMemberNum());
        notification.setProductId(seckillActivityAttentionBO.getProductId());
        notification.setProductName(productDO.getName());
        messageProducerService.sendMessage(MqConstant.TOPIC_PRODUCT_SRV, MqConstant.TAG_SECKILL_ACTIVITY_ABOUT_START_NOTICE, notification);
    }

    @Override
    public Boolean isAttention(Long seckillActivityProductId, Long memberId) {
        SeckillActivityAttentionDOExample example = new SeckillActivityAttentionDOExample();
        SeckillActivityAttentionDOExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        criteria.andActivityProductIdEqualTo(seckillActivityProductId);
        Long count = seckillActivityAttentionDOMapper.countByExample(example);
        return count <= 0 ? false : true;
    }
}
