package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.PointLotteryActivityPrizeImageTypeEnum;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityPrizeImageBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDO;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityPrizeImageDOMapper;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2018/2/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class PointLotteryActivityPrizeImageServiceImplTest {

    @Autowired
    private PointLotteryActivityPrizeImageService pointLotteryActivityPrizeImageService;

    @Autowired
    private PointLotteryActivityPrizeImageDOMapper pointLotteryActivityPrizeImageDOMapper;

    @Transactional
    @Rollback
    @Test
    public void listImagePath() {
        PointLotteryActivityPrizeImageDO imageDO = new PointLotteryActivityPrizeImageDO();
        imageDO.setPointLotteryActivityId(100L);
        imageDO.setType(DataTransUtil.intToByte(1));
        pointLotteryActivityPrizeImageDOMapper.insertSelective(imageDO);

        List<String> list = pointLotteryActivityPrizeImageService.listImagePath(100L, PointLotteryActivityPrizeImageTypeEnum.ACTIVITY_INTRODUCTIONS);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
    }

    @Transactional
    @Rollback
    @Test
    public void listPointLotteryActivityPrizeImage() {
        PointLotteryActivityPrizeImageDO imageDO = new PointLotteryActivityPrizeImageDO();
        imageDO.setPointLotteryActivityId(100L);
        imageDO.setType(DataTransUtil.intToByte(2));
        pointLotteryActivityPrizeImageDOMapper.insertSelective(imageDO);

        List<PointLotteryActivityPrizeImageBO> imageBOS = pointLotteryActivityPrizeImageService.listPointLotteryActivityPrizeImage(100L, PointLotteryActivityPrizeImageTypeEnum.LOTTERY_INFO);
        Assert.assertNotNull(imageBOS);
        Assert.assertEquals(imageBOS.size(), 1);
    }
}
