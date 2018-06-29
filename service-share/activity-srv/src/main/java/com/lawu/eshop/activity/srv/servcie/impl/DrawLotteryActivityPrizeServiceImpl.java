package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.param.DrawLotteryActivityPrizeParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityPrizeQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.converter.DrawLotteryActivityPrizeConverter;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDOExample;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityPrizeDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.DrawLotteryActivityPrizeDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityPrizeService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@Service
public class DrawLotteryActivityPrizeServiceImpl implements DrawLotteryActivityPrizeService {

    @Autowired
    private DrawLotteryActivityPrizeDOMapper drawLotteryActivityPrizeDOMapper;

    @Autowired
    private DrawLotteryActivityPrizeDOMapperExtend drawLotteryActivityPrizeDOMapperExtend;

    @Autowired
    private DrawLotteryActivityRecordDOMapper drawLotteryActivityRecordDOMapper;

    @Override
    public DrawLotteryActivityPrizeBO getPrizeDetail(Long id) {
        DrawLotteryActivityPrizeDO prizeDO = drawLotteryActivityPrizeDOMapper.selectByPrimaryKey(id);
        return DrawLotteryActivityPrizeConverter.convertBO(prizeDO);
    }

    @Override
    public List<DrawLotteryActivityPrizeBO> getActivityAllPrize(Long drawLotteryActivityId) {
        DrawLotteryActivityPrizeDOExample prizeDOExample = new DrawLotteryActivityPrizeDOExample();
        prizeDOExample.createCriteria().andDrawLotteryActivityIdEqualTo(drawLotteryActivityId).andStatusEqualTo(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        List<DrawLotteryActivityPrizeDO> prizeDOS = drawLotteryActivityPrizeDOMapper.selectByExample(prizeDOExample);
        return DrawLotteryActivityPrizeConverter.convertBOS(prizeDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDrawLotteryActivityPrize(DrawLotteryActivityPrizeParam param) {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setDrawLotteryActivityId(param.getDrawLotteryActivityId());
        prizeDO.setName(param.getName());
        prizeDO.setImgPath(param.getImgPath());
        prizeDO.setPrice(param.getPrice());
        prizeDO.setNumber(param.getNumber());
        prizeDO.setInventory(param.getNumber());
        prizeDO.setStatus(param.getStatusEnum().getVal());
        prizeDO.setFreightPrice(param.getFreightPrice());
        prizeDO.setMerchantStoreId(param.getMerchantStoreId());
        prizeDO.setAdImgPath(param.getAdImgPath());
        prizeDO.setRate(param.getRate());
        prizeDO.setIsAddress(param.getIsAddress());
        prizeDO.setIsSendPrize(param.getIsSendPrize());
        prizeDO.setPrizeType(param.getPrizeTypeEnum().getVal());
        if (param.getId() != null && param.getId() > 0) {
            prizeDO.setId(param.getId());
            prizeDO.setGmtModified(new Date());
            drawLotteryActivityPrizeDOMapper.updateByPrimaryKeySelective(prizeDO);
        } else {
            prizeDO.setGmtCreate(new Date());
            drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);
        }
    }

    @Override
    public Page<DrawLotteryActivityPrizeBO> listOperatorDrawLotteryActivityPrize(OperatorDrawLotteryActivityPrizeQuery query) {
        DrawLotteryActivityPrizeDOExample example = new DrawLotteryActivityPrizeDOExample();
        example.setOrderByClause("id desc");
        DrawLotteryActivityPrizeDOExample.Criteria criteria = example.createCriteria();
        criteria.andDrawLotteryActivityIdEqualTo(query.getDrawLotteryActivityId());
        if (StringUtils.isNotEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (query.getStatusEnum() != null) {
            criteria.andStatusEqualTo(query.getStatusEnum().getVal());
        }

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<DrawLotteryActivityPrizeDO> prizeDOS = drawLotteryActivityPrizeDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<DrawLotteryActivityPrizeBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) drawLotteryActivityPrizeDOMapper.countByExample(example));
        page.setRecords(DrawLotteryActivityPrizeConverter.convertBOS(prizeDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDrawLotteryActivityPrizeStatus(Long id, DrawLotteryActivityPrizeStatusEnum statusEnum) {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setId(id);
        prizeDO.setStatus(statusEnum.getVal());
        prizeDO.setGmtModified(new Date());
        drawLotteryActivityPrizeDOMapper.updateByPrimaryKeySelective(prizeDO);
    }

    @Override
    public BigDecimal getLeftPrizeRate(Long drawLotteryActivityId) {
        BigDecimal leftPrizeRate = BigDecimal.valueOf(100);
        DrawLotteryActivityPrizeDOExample example = new DrawLotteryActivityPrizeDOExample();
        example.createCriteria().andDrawLotteryActivityIdEqualTo(drawLotteryActivityId).andStatusEqualTo(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        List<DrawLotteryActivityPrizeDO> prizeDOS = drawLotteryActivityPrizeDOMapper.selectByExample(example);
        if (prizeDOS.isEmpty()) {
            return leftPrizeRate;
        }
        for (DrawLotteryActivityPrizeDO prizeDO : prizeDOS) {
            leftPrizeRate = leftPrizeRate.subtract(prizeDO.getRate());
        }
        return leftPrizeRate;
    }

    @Override
    public String getRandPrizeAdImg(Long drawLotteryActivityId) {
        return drawLotteryActivityPrizeDOMapperExtend.getRandPrizeAdImg(drawLotteryActivityId);
    }

    @Override
    public DrawLotteryActivityPrizeBO getPrizeByRecordId(Long recordId) {
        DrawLotteryActivityRecordDO recordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(recordId);
        DrawLotteryActivityPrizeDO prizeDO = drawLotteryActivityPrizeDOMapper.selectByPrimaryKey(recordDO.getDrawLotteryActivityPrizeId());
        return DrawLotteryActivityPrizeConverter.convertBO(prizeDO);
    }

}
