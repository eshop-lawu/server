package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.GenerateRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.GenerateLargeRedpacketBO;
import com.lawu.eshop.activity.srv.bo.GenerateRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDOExample;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.event.GenerateNormalRedpacketEvent;
import com.lawu.eshop.activity.srv.event.GenerateNormalRedpacketEventHandle;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class HelpRedpacketActivityServiceImplTest {
    
    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
    
    @Autowired
    private HelpRedpacketActivityDOMapper helpRedpacketActivityDOMapper;
    
    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
    
    @Autowired
    private GenerateNormalRedpacketEventHandle generateNormalRedpacketEventHandle;
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void get() {
        HelpRedpacketActivityDO expected = new HelpRedpacketActivityDO();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(80));
        expected.setMinRedpacket(new BigDecimal(1));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setIsOpen(true);
        expected.setIsCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setTotalAutoAmount(new BigDecimal(0));
        expected.setTotalManualAmount(new BigDecimal(0));
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(expected);
        
        HelpRedpacketActivityBO actual = helpRedpacketActivityService.get(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getEndTime(), actual.getEndTime());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getIsOpen(), actual.getIsOpen());
        Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), actual.getMaxRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMinRedpacket().doubleValue(), actual.getMinRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMultiple().doubleValue(), actual.getMultiple().doubleValue(), 0D);
        Assert.assertEquals(expected.getRegEndTime(), actual.getRegEndTime());
        Assert.assertEquals(expected.getRegStartTime(), actual.getRegStartTime());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertNotNull(actual.getStatus());
        Assert.assertEquals(expected.getTotalAutoAmount().doubleValue(), actual.getTotalAutoAmount().doubleValue(), 0D);
        Assert.assertEquals(expected.getTotalManualAmount().doubleValue(), actual.getTotalManualAmount().doubleValue(), 0D);
        Assert.assertEquals(expected.getWxActName(), actual.getWxActName());
        Assert.assertEquals(expected.getWxRemark(), actual.getWxRemark());
        Assert.assertEquals(expected.getWxSendName(), actual.getWxSendName());
        Assert.assertEquals(expected.getWxWishing(), actual.getWxWishing());
        Assert.assertEquals(expected.getStartPic(), actual.getStartPic());
        Assert.assertEquals(expected.getEndPic(), actual.getEndPic());
        Assert.assertEquals(expected.getEndUrl(), actual.getEndUrl());
        Assert.assertEquals(expected.getIsCloseEntry(), actual.getIsCloseEntry());
        Assert.assertEquals(expected.getRedpacketType(), actual.getRedpacketType());
        
        // 测试活动状态
        // 未开始
        expected.setRegStartTime(DateUtil.add(new Date(), 10, Calendar.SECOND));
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(expected);
        actual = helpRedpacketActivityService.get(expected.getId());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.NOT_STARTED, actual.getStatus());
        // 报名中
        expected.setRegStartTime(new Date());
        expected.setRegEndTime(DateUtil.add(new Date(), 10, Calendar.SECOND));
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(expected);
        actual = helpRedpacketActivityService.get(expected.getId());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.REGISTING, actual.getStatus());
        // 报名结束
        expected.setRegStartTime(new Date());
        expected.setRegEndTime(new Date());
        expected.setStartTime(DateUtil.add(new Date(), 10, Calendar.SECOND));
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(expected);
        actual = helpRedpacketActivityService.get(expected.getId());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.REGIST_END, actual.getStatus());
        // 开抢中
        expected.setRegStartTime(new Date());
        expected.setRegEndTime(new Date());
        expected.setStartTime(new Date());
        expected.setEndTime(DateUtil.add(new Date(), 10, Calendar.SECOND));
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(expected);
        actual = helpRedpacketActivityService.get(expected.getId());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.BEGINNING, actual.getStatus());
        // 开抢中
        expected.setRegStartTime(new Date());
        expected.setRegEndTime(new Date());
        expected.setStartTime(new Date());
        expected.setEndTime(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(expected);
        actual = helpRedpacketActivityService.get(expected.getId());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.END, actual.getStatus());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void update() {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setStartPic("startPic.jpg");
        helpRedpacketActivityDO.setEndPic("endPic.jpg");
        helpRedpacketActivityDO.setEndUrl("endUrl");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        HelpRedpacketActivityUpdateParam expected = new HelpRedpacketActivityUpdateParam();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(0));
        expected.setMinRedpacket(new BigDecimal(0));
        expected.setMultiple(new BigDecimal(0));
        expected.setOpen(true);
        expected.setCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        helpRedpacketActivityService.update(helpRedpacketActivityDO.getId(), expected);
        
        HelpRedpacketActivityBO actual = helpRedpacketActivityService.get(helpRedpacketActivityDO.getId());
        Assert.assertEquals(expected.getActivityTheme(), actual.getActivityTheme());
        Assert.assertEquals(expected.getEndTime(), actual.getEndTime());
        Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), actual.getMaxRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMinRedpacket().doubleValue(), actual.getMinRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMultiple().doubleValue(), actual.getMultiple().doubleValue(), 0D);
        Assert.assertEquals(expected.getOpen(), actual.getIsOpen());
        Assert.assertEquals(expected.getRegEndTime(), actual.getRegEndTime());
        Assert.assertEquals(expected.getRegStartTime(), actual.getRegStartTime());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(expected.getWxActName(), actual.getWxActName());
        Assert.assertEquals(expected.getWxRemark(), actual.getWxRemark());
        Assert.assertEquals(expected.getWxSendName(), actual.getWxSendName());
        Assert.assertEquals(expected.getWxWishing(), actual.getWxWishing());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.END, actual.getStatus());
        Assert.assertEquals(expected.getStartPic(), actual.getStartPic());
        Assert.assertEquals(expected.getEndPic(), actual.getEndPic());
        Assert.assertEquals(expected.getEndUrl(), actual.getEndUrl());
        Assert.assertEquals(expected.getCloseEntry(), actual.getIsCloseEntry());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void generateLargeRedpacket() {
        // 活动还未结束
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        HelpRedpacketAttendDetailDO record1 = new HelpRedpacketAttendDetailDO();
        record1.setAccount("123457");
        record1.setActivityId(helpRedpacketActivityDO.getId());
        record1.setGmtCreate(new Date());
        record1.setGmtModified(new Date());
        record1.setHeadimg("test.jpg");
        record1.setHelpCount(0);
        record1.setNickname("test");
        record1.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record1.setUserNum("M00002");
        helpRedpacketAttendDetailDOMapper.insert(record1);
        recordList.add(record1);
        
        GenerateLargeRedpacketParam param = new GenerateLargeRedpacketParam();
        param.setList(new ArrayList<>());
        GenerateRedpacketParam item = new GenerateRedpacketParam();
        // 生成1个100块的大奖红包
        item.setRedpacketAmount(new BigDecimal(100));
        item.setRedpacketIdx(0);
        item.setRedpacketQuantity(1);
        param.getList().add(item);
        try {
            helpRedpacketActivityService.generateLargeRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("活动报名还未结束");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  已经抽取过大奖了
         */
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        try {
            helpRedpacketActivityService.generateLargeRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("已经生成过大奖了");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  活动还未结束
         *  还原测试数据
         */
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setTotalManualAmount(null);
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        GenerateLargeRedpacketBO generateLargeRedpacketBO = helpRedpacketActivityService.generateLargeRedpacket(helpRedpacketActivityDO.getId(), param);
        Assert.assertNotNull(generateLargeRedpacketBO);
        GenerateRedpacketBO generateRedpacketBO = generateLargeRedpacketBO.getRedpacketList().get(0);
        Assert.assertNotNull(generateRedpacketBO);
        recordList.forEach(recordItem -> {
            if (!generateRedpacketBO.getAttendDetailId().equals(recordItem.getId())) {
                BigDecimal recordMaxRedpacketAmount = helpRedpacketActivityDO.getMaxRedpacket().multiply(new BigDecimal(1 + recordItem.getHelpCount() * helpRedpacketActivityDO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
                Assert.assertEquals(recordMaxRedpacketAmount.doubleValue(), generateLargeRedpacketBO.getExpectedMaxRedpacketAmount().doubleValue(), 0D);
                BigDecimal recordMinRedpacketAmount = helpRedpacketActivityDO.getMinRedpacket().multiply(new BigDecimal(1 + recordItem.getHelpCount() * helpRedpacketActivityDO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
                Assert.assertEquals(recordMinRedpacketAmount.doubleValue(), generateLargeRedpacketBO.getExpectedMinRedpacketAmount().doubleValue(), 0D);
                return;
            }
            Assert.assertEquals(recordItem.getAccount(), generateRedpacketBO.getAccount());
            Assert.assertEquals(item.getRedpacketAmount().doubleValue(), generateRedpacketBO.getOriginalMoney().doubleValue(), 0D);
            Assert.assertEquals(item.getRedpacketAmount().multiply(new BigDecimal(1 + recordItem.getHelpCount() * helpRedpacketActivityDO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP), generateRedpacketBO.getFinalMoney());
            Assert.assertEquals(item.getRedpacketIdx(), generateRedpacketBO.getGenerateLargeRedpacketIndex());
            Assert.assertEquals(recordItem.getHelpCount(), generateRedpacketBO.getHelpCount());
            Assert.assertEquals(recordItem.getUserNum(), generateRedpacketBO.getUserNum());
            Assert.assertEquals(recordItem.getHeadimg(), generateRedpacketBO.getHeadimg());
        });
        
        HelpRedpacketActivityDO actual = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(JSONObject.toJSONString(param), actual.getManualInfo());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void saveLargeRedpacket() {
        GenerateLargeRedpacketParam param = new GenerateLargeRedpacketParam();
        param.setList(new ArrayList<>());
        GenerateRedpacketParam item = new GenerateRedpacketParam();
        // 生成1个100块的大奖红包
        item.setRedpacketAmount(new BigDecimal(100));
        item.setRedpacketIdx(0);
        item.setRedpacketQuantity(1);
        param.getList().add(item);
        
        // 活动已经结束
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        helpRedpacketAttendDetailDOMapper.insert(record);
        
        List<SaveRedpacketParam> params = new ArrayList<>();
        SaveRedpacketParam saveRedpacketParam = new SaveRedpacketParam();
        saveRedpacketParam.setAttendDetailId(record.getId());
        saveRedpacketParam.setGenerateLargeRedpacketIndex(item.getRedpacketIdx());
        params.add(saveRedpacketParam);
        
        try {
            helpRedpacketActivityService.saveLargeRedpacket(helpRedpacketActivityDO.getId(), params);
            Assert.fail("活动报名还未结束");
        } catch (WrongOperationException e) {
        }
        
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        try {
            helpRedpacketActivityService.saveLargeRedpacket(helpRedpacketActivityDO.getId(), params);
            Assert.fail("还未生成大额红包");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  已经保存过大奖了
         */
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setManualInfo(JSONObject.toJSONString(param));
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        try {
            helpRedpacketActivityService.saveLargeRedpacket(helpRedpacketActivityDO.getId(), params);
            Assert.fail("已经保存过大奖了");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  活动还未结束
         *  还原测试数据
         */
        helpRedpacketActivityDO.setTotalManualAmount(null);
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        helpRedpacketActivityService.saveLargeRedpacket(helpRedpacketActivityDO.getId(), params);
        
        HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(record.getId());
        Assert.assertNotNull(actual.getAllotTime());
        Assert.assertEquals(item.getRedpacketAmount().doubleValue(), actual.getOriginalMoney().doubleValue(), 0D);
        Assert.assertEquals(item.getRedpacketAmount().multiply(new BigDecimal(1 + actual.getHelpCount() * helpRedpacketActivityDO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100, actual.getFinalMoney().intValue(), 0D);
        Assert.assertEquals(true, actual.getIsLucky());
        Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
        
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(actualHelpRedpacketActivityDO.getTotalManualAmount().doubleValue(), actual.getFinalMoney().doubleValue() / 100, 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void generateNormalRedpacket() {
        GenerateNormalRedpacketParam param = new GenerateNormalRedpacketParam();
        param.setTotalAutoAmount(new BigDecimal(50));
        // 活动已经结束
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        try {
            helpRedpacketActivityService.generateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("活动报名还未结束");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  还未抽取大奖
         */
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        try {
            helpRedpacketActivityService.generateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("还未抽取大奖");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  已经抽取过普通红包了
         */
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(80));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        try {
            helpRedpacketActivityService.generateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("已经抽取过普通红包了");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  活动还未结束
         *  还原测试数据
         */
        helpRedpacketActivityDO.setTotalAutoAmount(null);
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        helpRedpacketActivityService.generateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(new Object());
        event.setActivityId(helpRedpacketActivityDO.getId());
        event.setTotalAutoAmount(param.getTotalAutoAmount());
        generateNormalRedpacketEventHandle.execute(event);
        
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), actualHelpRedpacketActivityDO.getTotalAutoAmount().doubleValue(), 0D);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getId());
            Assert.assertNotNull(actual.getAllotTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
            Assert.assertNotNull(actual.getOriginalMoney());
            Assert.assertNotNull(actual.getFinalMoney());
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(actual.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void againGenerateNormalRedpacket() {
        GenerateNormalRedpacketParam param = new GenerateNormalRedpacketParam();
        param.setTotalAutoAmount(new BigDecimal(50));
        // 活动已经结束
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        try {
            helpRedpacketActivityService.againGenerateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("活动报名还未结束");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  还未抽取大奖
         */
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        try {
            helpRedpacketActivityService.againGenerateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
            Assert.fail("还未抽取大奖");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  活动还未结束
         *  还原测试数据
         */
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(50));
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        helpRedpacketActivityService.againGenerateNormalRedpacket(helpRedpacketActivityDO.getId(), param);
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(new Object());
        event.setActivityId(helpRedpacketActivityDO.getId());
        event.setTotalAutoAmount(param.getTotalAutoAmount());
        generateNormalRedpacketEventHandle.execute(event);
        
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), actualHelpRedpacketActivityDO.getTotalAutoAmount().doubleValue(), 0D);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getId());
            Assert.assertNotNull(actual.getAllotTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
            Assert.assertNotNull(actual.getOriginalMoney());
            Assert.assertNotNull(actual.getFinalMoney());
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(actual.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void continueGenerateNormalRedpacket() {
        // 活动已经结束
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        try {
            helpRedpacketActivityService.continueGenerateNormalRedpacket(helpRedpacketActivityDO.getId());
            Assert.fail("活动报名还未结束");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  还未抽取大奖
         */
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        try {
            helpRedpacketActivityService.continueGenerateNormalRedpacket(helpRedpacketActivityDO.getId());
            Assert.fail("还未抽取大奖");
        } catch (WrongOperationException e) {
        }
        
        /*
         *  活动还未结束
         *  还原测试数据
         */
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(50));
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDOMapper.updateByPrimaryKey(helpRedpacketActivityDO);
        
        helpRedpacketActivityService.continueGenerateNormalRedpacket(helpRedpacketActivityDO.getId());
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(new Object());
        event.setActivityId(helpRedpacketActivityDO.getId());
        generateNormalRedpacketEventHandle.execute(event);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getId());
            Assert.assertNotNull(actual.getAllotTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
            Assert.assertNotNull(actual.getOriginalMoney());
            Assert.assertNotNull(actual.getFinalMoney());
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(actual.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(helpRedpacketActivityDO.getTotalAutoAmount().doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void getNormalRedpacketTotalAmount() {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        record.setFinalMoney(10000);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        record.setFinalMoney(10000);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        BigDecimal actual = helpRedpacketActivityService.getNormalRedpacketTotalAmount(helpRedpacketActivityDO.getId());
        Assert.assertNotNull(actual);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(item.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(actual.doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void list() {
        Map<Integer, HelpRedpacketActivityDO> map = new HashMap<>();
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRedpacketType(RedPacketTypeEnum.BALANCE.getVal());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        map.put(helpRedpacketActivityDO.getId(), helpRedpacketActivityDO);
        
        RedpacketActivityQueryParam param = new RedpacketActivityQueryParam();
        param.setCurrentPage(0);
        param.setPageSize(10);
        Page<HelpRedpacketActivityBO> page = helpRedpacketActivityService.list(param);
        Assert.assertNotNull(page);
        Assert.assertNotNull(page.getRecords());
        Assert.assertNotNull(page.getTotalCount());
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
        for (HelpRedpacketActivityBO item : page.getRecords()) {
            HelpRedpacketActivityDO actual = map.get(item.getId());
            if (actual == null) continue;
            Assert.assertNotNull(item);
            Assert.assertEquals(actual.getEndTime(), item.getEndTime());
            Assert.assertEquals(actual.getId(), item.getId());
            Assert.assertEquals(actual.getIsOpen(), item.getIsOpen());
            Assert.assertEquals(actual.getMaxRedpacket().doubleValue(), item.getMaxRedpacket().doubleValue(), 0D);
            Assert.assertEquals(actual.getMinRedpacket().doubleValue(), item.getMinRedpacket().doubleValue(), 0D);
            Assert.assertEquals(actual.getMultiple().doubleValue(), item.getMultiple().doubleValue(), 0D);
            Assert.assertEquals(actual.getRegEndTime(), item.getRegEndTime());
            Assert.assertEquals(actual.getRegStartTime(), item.getRegStartTime());
            Assert.assertEquals(actual.getStartTime(), item.getStartTime());
            Assert.assertNotNull(item.getStatus());
            Assert.assertEquals(actual.getWxActName(), item.getWxActName());
            Assert.assertEquals(actual.getWxRemark(), item.getWxRemark());
            Assert.assertEquals(actual.getWxSendName(), item.getWxSendName());
            Assert.assertEquals(actual.getWxWishing(), item.getWxWishing());
            Assert.assertEquals(actual.getStartPic(), item.getStartPic());
            Assert.assertEquals(actual.getEndPic(), item.getEndPic());
            Assert.assertEquals(actual.getEndUrl(), item.getEndUrl());
            Assert.assertEquals(actual.getIsCloseEntry(), item.getIsCloseEntry());
            Assert.assertEquals(actual.getRedpacketType(), item.getRedpacketType().getVal());
        }
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void save() {
        HelpRedpacketActivitySaveParam expected = new HelpRedpacketActivitySaveParam();
        expected.setActivityTheme("testSave");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(0));
        expected.setMinRedpacket(new BigDecimal(0));
        expected.setMultiple(new BigDecimal(0));
        expected.setOpen(true);
        expected.setCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        expected.setRedpacketType(RedPacketTypeEnum.BALANCE);
        helpRedpacketActivityService.save(expected);
        
        HelpRedpacketActivityDOExample example = new HelpRedpacketActivityDOExample();
        example.createCriteria().andActivityThemeEqualTo(expected.getActivityTheme());
        HelpRedpacketActivityDO actual = helpRedpacketActivityDOMapper.selectByExample(example).get(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getEndTime(), actual.getEndTime());
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(expected.getOpen(), actual.getIsOpen());
        Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), actual.getMaxRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMinRedpacket().doubleValue(), actual.getMinRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMultiple().doubleValue(), actual.getMultiple().doubleValue(), 0D);
        Assert.assertEquals(expected.getRegEndTime(), actual.getRegEndTime());
        Assert.assertEquals(expected.getRegStartTime(), actual.getRegStartTime());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(expected.getWxActName(), actual.getWxActName());
        Assert.assertEquals(expected.getWxRemark(), actual.getWxRemark());
        Assert.assertEquals(expected.getWxSendName(), actual.getWxSendName());
        Assert.assertEquals(expected.getWxWishing(), actual.getWxWishing());
        Assert.assertEquals(expected.getStartPic(), actual.getStartPic());
        Assert.assertEquals(expected.getEndPic(), actual.getEndPic());
        Assert.assertEquals(expected.getEndUrl(), actual.getEndUrl());
        Assert.assertEquals(expected.getCloseEntry(), actual.getIsCloseEntry());
        Assert.assertEquals(expected.getRedpacketType().getVal(), actual.getRedpacketType());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void openActivityList() {
        Map<Integer, HelpRedpacketActivityDO> map = new HashMap<>();
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRedpacketType(RedPacketTypeEnum.BALANCE.getVal());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        map.put(helpRedpacketActivityDO.getId(), helpRedpacketActivityDO);
        
        List<HelpRedpacketActivityBO> list = helpRedpacketActivityService.openActivityList();
        
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        for (HelpRedpacketActivityBO item : list) {
            HelpRedpacketActivityDO expected = map.get(item.getId());
            Assert.assertNotNull(item);
            Assert.assertEquals(expected.getEndTime(), item.getEndTime());
            Assert.assertEquals(expected.getId(), item.getId());
            Assert.assertEquals(expected.getIsOpen(), item.getIsOpen());
            Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), item.getMaxRedpacket().doubleValue(), 0D);
            Assert.assertEquals(expected.getMinRedpacket().doubleValue(), item.getMinRedpacket().doubleValue(), 0D);
            Assert.assertEquals(expected.getMultiple().doubleValue(), item.getMultiple().doubleValue(), 0D);
            Assert.assertEquals(expected.getRegEndTime(), item.getRegEndTime());
            Assert.assertEquals(expected.getRegStartTime(), item.getRegStartTime());
            Assert.assertEquals(expected.getStartTime(), item.getStartTime());
            Assert.assertNotNull(item.getStatus());
            Assert.assertEquals(expected.getWxActName(), item.getWxActName());
            Assert.assertEquals(expected.getWxRemark(), item.getWxRemark());
            Assert.assertEquals(expected.getWxSendName(), item.getWxSendName());
            Assert.assertEquals(expected.getWxWishing(), item.getWxWishing());
            Assert.assertEquals(expected.getStartPic(), item.getStartPic());
            Assert.assertEquals(expected.getEndPic(), item.getEndPic());
            Assert.assertEquals(expected.getEndUrl(), item.getEndUrl());
            Assert.assertEquals(expected.getIsCloseEntry(), item.getIsCloseEntry());
            Assert.assertEquals(expected.getRedpacketType(), item.getRedpacketType().getVal());
        }
    }
}
