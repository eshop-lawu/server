package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.IncomeSummaryBO;
import com.lawu.eshop.property.srv.domain.IncomeDailySummaryDO;
import com.lawu.eshop.property.srv.mapper.IncomeDailySummaryDOMapper;
import com.lawu.eshop.property.srv.service.IncomeSummaryService;
import com.lawu.utils.DateUtil;

/**
 * @author yangqh
 * @date 2018/3/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class IncomeSummaryServiceImplTest {

    @Autowired
    private IncomeSummaryService incomeSummaryService;

    @Autowired
    private IncomeDailySummaryDOMapper incomeDailySummaryDOMapper;

    @Test
    public void getIncomeSummary(){

        IncomeDailySummaryDO incomeDailySummaryDO = new IncomeDailySummaryDO();
        incomeDailySummaryDO.setUserType(UserTypeEnum.MEMBER.getValue());
        incomeDailySummaryDO.setGmtReport(DateUtil.formatDate(DateUtil.getDateFormat(new Date(),DateUtil.DATE_DEFAULT_FORMAT),DateUtil.DATE_DEFAULT_FORMAT));
        incomeDailySummaryDO.setGmtCreate(new Date());
        incomeDailySummaryDO.setIncomeType(PayTypeEnum.BALANCE.getVal());
        incomeDailySummaryDO.setMoney(new BigDecimal(10));
        incomeDailySummaryDO.setUserNum("M00000001");
        incomeDailySummaryDOMapper.insertSelective(incomeDailySummaryDO);

        List<IncomeSummaryBO> rtnList = incomeSummaryService.getIncomeSummary("M00000001");
        Assert.assertNotNull(rtnList);
        Assert.assertTrue(rtnList.size() == 1);
        Assert.assertEquals(rtnList.get(0).getMoney().intValue(),new BigDecimal(10).intValue());
        Assert.assertEquals(rtnList.get(0).getIncomeType(),PayTypeEnum.BALANCE.getVal());
    }

}
