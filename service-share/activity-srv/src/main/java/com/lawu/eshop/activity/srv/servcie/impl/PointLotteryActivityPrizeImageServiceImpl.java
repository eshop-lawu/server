package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.constants.PointLotteryActivityPrizeImageTypeEnum;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityPrizeImageBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDOExample;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityPrizeImageDOMapper;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityPrizeImageService;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@Service
public class PointLotteryActivityPrizeImageServiceImpl implements PointLotteryActivityPrizeImageService {

    @Autowired
    private PointLotteryActivityPrizeImageDOMapper pointLotteryActivityPrizeImageDOMapper;

    @Override
    public List<String> listImagePath(Long pointLotteryActivityId, PointLotteryActivityPrizeImageTypeEnum typeEnum) {
        PointLotteryActivityPrizeImageDOExample example = new PointLotteryActivityPrizeImageDOExample();
        example.createCriteria().andPointLotteryActivityIdEqualTo(pointLotteryActivityId).andTypeEqualTo(typeEnum.getVal());
        example.setOrderByClause("order_num asc");
        List<PointLotteryActivityPrizeImageDO> imageDOS = pointLotteryActivityPrizeImageDOMapper.selectByExample(example);
        List<String> imageList = new ArrayList<>();
        if (imageDOS.isEmpty()) {
            return imageList;
        }
        for (PointLotteryActivityPrizeImageDO imageDO : imageDOS) {
            imageList.add(imageDO.getImagePath());
        }
        return imageList;
    }

    @Override
    public List<PointLotteryActivityPrizeImageBO> listPointLotteryActivityPrizeImage(Long pointLotteryActivityId, PointLotteryActivityPrizeImageTypeEnum typeEnum) {
        PointLotteryActivityPrizeImageDOExample example = new PointLotteryActivityPrizeImageDOExample();
        example.createCriteria().andPointLotteryActivityIdEqualTo(pointLotteryActivityId).andTypeEqualTo(typeEnum.getVal());
        List<PointLotteryActivityPrizeImageDO> imageDOS = pointLotteryActivityPrizeImageDOMapper.selectByExample(example);

        List<PointLotteryActivityPrizeImageBO> imageBOS = new ArrayList<>();
        if (imageDOS.isEmpty()) {
            return imageBOS;
        }
        for (PointLotteryActivityPrizeImageDO imageDO : imageDOS) {
            PointLotteryActivityPrizeImageBO imageBO = new PointLotteryActivityPrizeImageBO();
            imageBO.setImagePath(imageDO.getImagePath());
            imageBO.setOrderNum(imageDO.getOrderNum());
            imageBOS.add(imageBO);
        }
        return imageBOS;
    }

}
