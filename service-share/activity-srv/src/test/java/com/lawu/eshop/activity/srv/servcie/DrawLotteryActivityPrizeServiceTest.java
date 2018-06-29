package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.param.DrawLotteryActivityPrizeParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityPrizeQuery;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityPrizeDOMapper;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class DrawLotteryActivityPrizeServiceTest {

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    @Autowired
    private DrawLotteryActivityPrizeDOMapper drawLotteryActivityPrizeDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPrizeDetail() {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setName("test");
        prizeDO.setImgPath("png");
        prizeDO.setStatus((byte) 1);
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);

        DrawLotteryActivityPrizeBO prizeBO = drawLotteryActivityPrizeService.getPrizeDetail(prizeDO.getId());
        Assert.assertNotNull(prizeBO);
        Assert.assertEquals(prizeDO.getName(), prizeBO.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getActivityAllPrize() {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setDrawLotteryActivityId(10L);
        prizeDO.setName("test");
        prizeDO.setImgPath("png");
        prizeDO.setStatus((byte) 1);
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);

        List<DrawLotteryActivityPrizeBO> prizeBOS = drawLotteryActivityPrizeService.getActivityAllPrize(prizeDO.getDrawLotteryActivityId());
        Assert.assertNotNull(prizeBOS);
        Assert.assertEquals(prizeDO.getName(), prizeBOS.get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveDrawLotteryActivityPrize() {
        DrawLotteryActivityPrizeParam param = new DrawLotteryActivityPrizeParam();
        param.setDrawLotteryActivityId(10L);
        param.setName("test");
        param.setImgPath("png");
        param.setPrice(BigDecimal.valueOf(10));
        param.setNumber(1);
        param.setStatusEnum(DrawLotteryActivityPrizeStatusEnum.VALID);
        param.setFreightPrice(BigDecimal.ZERO);
        param.setMerchantStoreId(100L);
        param.setAdImgPath("ad.png");
        param.setRate(BigDecimal.valueOf(1));
        drawLotteryActivityPrizeService.saveDrawLotteryActivityPrize(param);

        List<DrawLotteryActivityPrizeDO> prizeDOS = drawLotteryActivityPrizeDOMapper.selectByExample(null);
        Assert.assertNotNull(prizeDOS);
        Assert.assertEquals(1, prizeDOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorDrawLotteryActivityPrize() {
        DrawLotteryActivityPrizeParam param = new DrawLotteryActivityPrizeParam();
        param.setDrawLotteryActivityId(10L);
        param.setName("test");
        param.setImgPath("png");
        param.setPrice(BigDecimal.valueOf(10));
        param.setNumber(1);
        param.setStatusEnum(DrawLotteryActivityPrizeStatusEnum.VALID);
        param.setFreightPrice(BigDecimal.ZERO);
        param.setMerchantStoreId(100L);
        param.setAdImgPath("ad.png");
        param.setRate(BigDecimal.valueOf(1));
        drawLotteryActivityPrizeService.saveDrawLotteryActivityPrize(param);

        OperatorDrawLotteryActivityPrizeQuery query = new OperatorDrawLotteryActivityPrizeQuery();
        query.setDrawLotteryActivityId(param.getDrawLotteryActivityId());
        query.setName(param.getName());
        query.setStatusEnum(DrawLotteryActivityPrizeStatusEnum.VALID);
        Page<DrawLotteryActivityPrizeBO> prizeBOPage = drawLotteryActivityPrizeService.listOperatorDrawLotteryActivityPrize(query);
        Assert.assertNotNull(prizeBOPage.getRecords());
        Assert.assertEquals(param.getName(), prizeBOPage.getRecords().get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateDrawLotteryActivityPrizeStatus() {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setStatus(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);
        drawLotteryActivityPrizeService.updateDrawLotteryActivityPrizeStatus(prizeDO.getId(), DrawLotteryActivityPrizeStatusEnum.INVALID);

        DrawLotteryActivityPrizeDO activityPrizeDO = drawLotteryActivityPrizeDOMapper.selectByPrimaryKey(prizeDO.getId());
        Assert.assertNotNull(activityPrizeDO);
        Assert.assertEquals(DrawLotteryActivityPrizeStatusEnum.INVALID.getVal(), activityPrizeDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getLeftPrizeRate() {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setDrawLotteryActivityId(100L);
        prizeDO.setRate(BigDecimal.valueOf(10));
        prizeDO.setStatus(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);

        BigDecimal result = drawLotteryActivityPrizeService.getLeftPrizeRate(prizeDO.getDrawLotteryActivityId());
        Assert.assertNotNull(result);
        Assert.assertEquals(BigDecimal.valueOf(100).subtract(prizeDO.getRate()).intValue(), result.intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRandPrizeAdImg() {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setDrawLotteryActivityId(100L);
        prizeDO.setRate(BigDecimal.valueOf(10));
        prizeDO.setAdImgPath("png");
        prizeDO.setStatus(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);

        String result = drawLotteryActivityPrizeService.getRandPrizeAdImg(prizeDO.getDrawLotteryActivityId());
        Assert.assertNotNull(result);
        Assert.assertEquals(prizeDO.getAdImgPath(), result);
    }

}
