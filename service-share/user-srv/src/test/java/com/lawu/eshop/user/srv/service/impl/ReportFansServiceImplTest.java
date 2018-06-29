//package com.lawu.eshop.user.srv.service.impl;
//
//import com.lawu.eshop.user.constants.ReportFansRiseRateEnum;
//import com.lawu.eshop.user.dto.ReportRiseRateDTO;
//import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
//import com.lawu.eshop.user.param.ReportDataParam;
//import com.lawu.eshop.user.srv.domain.FansMerchantDO;
//import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
//import com.lawu.eshop.user.srv.service.ReportFansService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author meishuquan
// * @date 2017/7/12.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = UserSrvApplicationTest.class)
//public class ReportFansServiceImplTest {
//
//    @Autowired
//    private ReportFansService reportFansService;
//
//    @Autowired
//    private FansMerchantDOMapper fansMerchantDOMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    @Rollback
//    @Test
//    public void fansRiseRate() {
//        FansMerchantDO fansMerchantDO = new FansMerchantDO();
//        fansMerchantDO.setMerchantId(200L);
//        fansMerchantDO.setMemberId(100L);
//        fansMerchantDO.setGmtCreate(new Date());
//        fansMerchantDOMapper.insertSelective(fansMerchantDO);
//
//        ReportDataParam param = new ReportDataParam();
//        param.setMerchantId(200L);
//        param.setFlag(ReportFansRiseRateEnum.DAY);
//        ReportRiseRateDTO reportRiseRateDTO = reportFansService.fansRiseRate(param);
//        Assert.assertNotNull(reportRiseRateDTO);
//        Assert.assertEquals(1, reportRiseRateDTO.getX().size());
//
//        param.setFlag(ReportFansRiseRateEnum.MONTH);
//        reportRiseRateDTO = reportFansService.fansRiseRate(param);
//        Assert.assertNotNull(reportRiseRateDTO);
//        Assert.assertEquals(1, reportRiseRateDTO.getX().size());
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Rollback
//    @Test
//    public void fansRiseSource() {
//        FansMerchantDO fansMerchantDO = new FansMerchantDO();
//        fansMerchantDO.setMerchantId(200L);
//        fansMerchantDO.setMemberId(100L);
//        fansMerchantDO.setGmtCreate(new Date());
//        fansMerchantDOMapper.insertSelective(fansMerchantDO);
//
//        ReportDataParam param = new ReportDataParam();
//        param.setMerchantId(200L);
//        param.setFlag(ReportFansRiseRateEnum.DAY);
//        List<ReportRiseRerouceDTO> reportRiseRerouceDTOS = reportFansService.fansRiseSource(param);
//        Assert.assertNotNull(reportRiseRerouceDTOS);
//        Assert.assertEquals(1, reportRiseRerouceDTOS.size());
//
//        param.setFlag(ReportFansRiseRateEnum.MONTH);
//        reportRiseRerouceDTOS = reportFansService.fansRiseSource(param);
//        Assert.assertNotNull(reportRiseRerouceDTOS);
//        Assert.assertEquals(1, reportRiseRerouceDTOS.size());
//    }
//
//}
