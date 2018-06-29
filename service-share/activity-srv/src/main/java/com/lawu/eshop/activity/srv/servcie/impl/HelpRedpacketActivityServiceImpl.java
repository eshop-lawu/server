package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.GenerateRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.eshop.activity.srv.bo.ExpectedRedpacketAmountBO;
import com.lawu.eshop.activity.srv.bo.GenerateLargeRedpacketBO;
import com.lawu.eshop.activity.srv.bo.GenerateRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketActivityConverter;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDOExample;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDOExample;
import com.lawu.eshop.activity.srv.domain.extend.GetRedpacketAmountParam;
import com.lawu.eshop.activity.srv.domain.extend.RedpacketAmountParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdateMinRedpacketAmountParam;
import com.lawu.eshop.activity.srv.event.EventPublisher;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.HelpRedpacketAttendDetailDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketActivityService;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.framework.core.page.Page;

@Service
public class HelpRedpacketActivityServiceImpl implements HelpRedpacketActivityService {

    @Autowired
    private HelpRedpacketActivityDOMapper helpRedpacketActivityDOMapper;
    
    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
    
    @Autowired
    private HelpRedpacketAttendDetailDOMapperExtend helpRedpacketAttendDetailDOMapperExtend;
    
    @Autowired
    private EventPublisher eventPublisher;
    
    @SuppressWarnings("deprecation")
    @Override
    public HelpRedpacketActivityBO get(Integer id) {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityDO helpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(id);
        return HelpRedpacketActivityConverter.convert(helpRedpacketActivityDO);
    }

    @SuppressWarnings("deprecation")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Integer id, HelpRedpacketActivityUpdateParam param) {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityDO updateDO = new HelpRedpacketActivityDO();
        updateDO.setId(id);
        if (StringUtils.isNotBlank(param.getActivityTheme())) {
            updateDO.setActivityTheme(param.getActivityTheme());
        }
        if (param.getEndTime() != null) {
            updateDO.setEndTime(param.getEndTime());
        }
        if (param.getOpen() != null) {
            updateDO.setIsOpen(param.getOpen());
        }
        if (param.getCloseEntry() != null) {
            updateDO.setIsCloseEntry(param.getCloseEntry());
        }
        if (param.getMaxRedpacket() != null) {
            updateDO.setMaxRedpacket(param.getMaxRedpacket());
        }
        if (param.getMinRedpacket() != null) {
            updateDO.setMinRedpacket(param.getMinRedpacket());
        }
        if (param.getMultiple() != null) {
            updateDO.setMultiple(param.getMultiple());
        }
        if (param.getRegEndTime() != null) {
            updateDO.setRegEndTime(param.getRegEndTime());
        }
        if (param.getRegStartTime() != null) {
            updateDO.setRegStartTime(param.getRegStartTime());
        }
        if (param.getStartTime() != null) {
            updateDO.setStartTime(param.getStartTime());
        }
        if (StringUtils.isNotBlank(param.getStartPic())) {
            updateDO.setStartPic(param.getStartPic());
        }
        if (StringUtils.isNotBlank(param.getEndPic())) {
            updateDO.setEndPic(param.getEndPic());
        }
        if (StringUtils.isNotBlank(param.getEndUrl())) {
            updateDO.setEndUrl(param.getEndUrl());
        }
        if (param.getType() != null) {
            updateDO.setType(param.getType().getVal());
        }
        if (param.getRedpacketType() != null) {
            updateDO.setRedpacketType(param.getRedpacketType().getVal());
        }
        if (StringUtils.isNotBlank(param.getWxActName())) {
            updateDO.setWxActName(param.getWxActName());
        }
        if (StringUtils.isNotBlank(param.getWxRemark())) {
            updateDO.setWxRemark(param.getWxRemark());
        }
        if (StringUtils.isNotBlank(param.getWxSendName())) {
            updateDO.setWxSendName(param.getWxSendName());
        }
        if (StringUtils.isNotBlank(param.getWxWishing())) {
            updateDO.setWxWishing(param.getWxWishing());
        }
        if (StringUtils.isNotBlank(param.getPuzzlePic())) {
            updateDO.setAdPic(param.getPuzzlePic());
        }
        if (param.getRules() != null && !param.getRules().isEmpty()) {
            updateDO.setRules(JSONObject.toJSONString(param.getRules()));
        }
        if(param.getSentAmount() != null){
        	updateDO.setSentAmount(param.getSentAmount());
        }
        updateDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    @SuppressWarnings("deprecation")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GenerateLargeRedpacketBO generateLargeRedpacket(Integer id, GenerateLargeRedpacketParam param) throws WrongOperationException {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityBO helpRedpacketActivityBO = get(id);
        if (helpRedpacketActivityBO == null) {
            throw new DataNotExistException("活动不存在");
        }
        if (!HelpRedpacketActivityStatusEnum.REGIST_END.equals(helpRedpacketActivityBO.getStatus())) {
            throw new WrongOperationException("活动报名还未结束");
        }
        if (helpRedpacketActivityBO.getTotalManualAmount() != null) {
            throw new WrongOperationException("已经生成过大额红包了");
        }
        GenerateLargeRedpacketBO rtn = new GenerateLargeRedpacketBO();
        Map<Integer, Integer> lotteryMap = new HashMap<>();
        // 获取总抽奖用户数量
        HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
        List<Byte> normalList = new ArrayList<Byte>();
        normalList.add(AbnormalStatusEnum.NORMAL.getVal());
        normalList.add(AbnormalStatusEnum.WHITE_LIST.getVal());
        example.createCriteria().andActivityIdEqualTo(id).andAbnormalStatusIn(normalList);
        long count = helpRedpacketAttendDetailDOMapper.countByExample(example);
        // 判断抽取的大奖的人数是否小于当前参与活动的人数
        Integer lotteryNum = 0;
        for(GenerateRedpacketParam item : param.getList()) {
            lotteryNum += item.getRedpacketQuantity();
        }
        if (lotteryNum > count) {
            throw new WrongOperationException("抽取的大奖的人数大于当前参与活动的人数");
        }
        Random random = new Random();
        // 循环生成方案
        for(int i = 0; i < param.getList().size(); i++) {
            GenerateRedpacketParam item = param.getList().get(i);
            // 按数量循环抽奖
            for (int j = 0; j < item.getRedpacketQuantity();) {
                int randomIndex = (int) (random.nextDouble() * count);
                if (!lotteryMap.containsKey(randomIndex)) {
                    lotteryMap.put(randomIndex, i);
                    j++;
                }
            }
        }
        List<Long> ids = new ArrayList<>();
        List<GenerateRedpacketBO> generateRedpacketList = new ArrayList<>();
        rtn.setTotalManualAmount(new BigDecimal(0));
        // 通过下标获取助力详情，以及组装生成红包数据
        lotteryMap.forEach((index, paramIdx) -> {
            Integer helpRedpacketAttendDetailId = helpRedpacketAttendDetailDOMapperExtend.getIdWithIndex(index, helpRedpacketActivityBO.getId(), null);
            HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(Long.valueOf(helpRedpacketAttendDetailId));
            GenerateRedpacketParam paramItem = param.getList().get(paramIdx);
            GenerateRedpacketBO generateRedpacketBO = new GenerateRedpacketBO();
            generateRedpacketBO.setGenerateLargeRedpacketIndex(paramIdx);
            generateRedpacketBO.setAccount(helpRedpacketAttendDetailDO.getAccount());
            generateRedpacketBO.setAttendDetailId(helpRedpacketAttendDetailDO.getId());
            generateRedpacketBO.setOriginalMoney(paramItem.getRedpacketAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            BigDecimal finalMoney = new BigDecimal(generateRedpacketBO.getOriginalMoney().doubleValue() * (1 + helpRedpacketActivityBO.getMultiple().doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_HALF_UP);
            generateRedpacketBO.setFinalMoney(finalMoney);
            rtn.setTotalManualAmount(rtn.getTotalManualAmount().add(generateRedpacketBO.getFinalMoney()));
            generateRedpacketBO.setHeadimg(helpRedpacketAttendDetailDO.getHeadimg());
            generateRedpacketBO.setUserNum(helpRedpacketAttendDetailDO.getUserNum());
            generateRedpacketBO.setHelpCount(helpRedpacketAttendDetailDO.getHelpCount());
            generateRedpacketList.add(generateRedpacketBO);
            ids.add(helpRedpacketAttendDetailDO.getId());
        });
        rtn.setRedpacketList(generateRedpacketList);
        ExpectedRedpacketAmountBO expectedRedpacketAmountBO = expectedRedpacketAmount(id, ids);
        rtn.setExpectedMaxRedpacketAmount(expectedRedpacketAmountBO.getExpectedMaxRedpacketAmount());
        rtn.setExpectedMinRedpacketAmount(expectedRedpacketAmountBO.getExpectedMinRedpacketAmount());
        // 更新红包生成方案到数据库
        HelpRedpacketActivityDO updateDO = new HelpRedpacketActivityDO();
        updateDO.setId(id);
        updateDO.setManualInfo(JSONObject.toJSONString(param));
        updateDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(updateDO);
        return rtn;
    }

    @SuppressWarnings("deprecation")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLargeRedpacket(Integer id, List<SaveRedpacketParam> params) throws WrongOperationException {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityBO helpRedpacketActivityBO = get(id);
        if (helpRedpacketActivityBO == null) {
            throw new DataNotExistException("活动不存在");
        }
        if (!HelpRedpacketActivityStatusEnum.REGIST_END.equals(helpRedpacketActivityBO.getStatus())) {
            throw new WrongOperationException("活动报名还未结束");
        }
        if (StringUtils.isBlank(helpRedpacketActivityBO.getManualInfo())) {
            throw new WrongOperationException("还未生成大额红包了");
        }
        if (helpRedpacketActivityBO.getTotalManualAmount() != null) {
            throw new WrongOperationException("已经保存过大额红包了");
        }
        GenerateLargeRedpacketParam generateLargeRedpacketParam = JSONObject.parseObject(helpRedpacketActivityBO.getManualInfo(), GenerateLargeRedpacketParam.class);
        Map<Integer, GenerateRedpacketParam> generateRedpacketMap = new HashMap<>();
        generateLargeRedpacketParam.getList().forEach(item -> {
            generateRedpacketMap.put(item.getRedpacketIdx(), item);
        });
        HelpRedpacketActivityDO helpRedpacketActivityUpdateDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityUpdateDO.setId(helpRedpacketActivityBO.getId());
        helpRedpacketActivityUpdateDO.setTotalManualAmount(new BigDecimal(0));
        params.forEach(item -> {
            GenerateRedpacketParam generateRedpacketParam =  generateRedpacketMap.get(item.getGenerateLargeRedpacketIndex());
            if (generateRedpacketParam == null) return;
            HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getAttendDetailId());
            HelpRedpacketAttendDetailDO helpRedpacketAttendDetailUpdateDO = new HelpRedpacketAttendDetailDO();
            helpRedpacketAttendDetailUpdateDO.setId(item.getAttendDetailId());
            helpRedpacketAttendDetailUpdateDO.setOriginalMoney(generateRedpacketParam.getRedpacketAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            BigDecimal finalMoney = new BigDecimal(helpRedpacketAttendDetailUpdateDO.getOriginalMoney().doubleValue() * (1 + helpRedpacketActivityBO.getMultiple().doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_HALF_UP);
            helpRedpacketAttendDetailUpdateDO.setFinalMoney((int) (finalMoney.doubleValue() * 100));
            helpRedpacketAttendDetailUpdateDO.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
            helpRedpacketAttendDetailUpdateDO.setAllotTime(new Date());
            helpRedpacketAttendDetailUpdateDO.setGmtModified(new Date());
            helpRedpacketAttendDetailUpdateDO.setIsLucky(true);
            helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(helpRedpacketAttendDetailUpdateDO);
            helpRedpacketActivityUpdateDO.setTotalManualAmount(helpRedpacketActivityUpdateDO.getTotalManualAmount().add(finalMoney));
        });
        helpRedpacketActivityUpdateDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(helpRedpacketActivityUpdateDO);
    }

    @SuppressWarnings("deprecation")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateNormalRedpacket(Integer id, GenerateNormalRedpacketParam param) throws WrongOperationException {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityBO helpRedpacketActivityBO = get(id);
        if (helpRedpacketActivityBO == null) {
            throw new DataNotExistException("活动不存在");
        }
        if (!HelpRedpacketActivityStatusEnum.REGIST_END.equals(helpRedpacketActivityBO.getStatus())) {
            throw new WrongOperationException("活动报名还未结束");
        }
        if (helpRedpacketActivityBO.getTotalManualAmount() == null) {
            throw new WrongOperationException("请先生成大额红包");
        }
        if (helpRedpacketActivityBO.getTotalAutoAmount() != null) {
            throw new WrongOperationException("已经生成过普通红包了");
        }
        ExpectedRedpacketAmountBO expectedRedpacketAmountBO = expectedRedpacketAmount(id, null);
        if (expectedRedpacketAmountBO.getExpectedMaxRedpacketAmount().compareTo(param.getTotalAutoAmount()) < 0 || expectedRedpacketAmountBO.getExpectedMinRedpacketAmount().compareTo(param.getTotalAutoAmount()) > 0) {
            throw new WrongOperationException("自动分配红包总额必须介于期望值以内");
        }
        // 更新生成普通红包总金额到数据库
        HelpRedpacketActivityDO updateDO = new HelpRedpacketActivityDO();
        updateDO.setId(id);
        updateDO.setTotalAutoAmount(param.getTotalAutoAmount());
        updateDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(updateDO);
        
        // 分配疑似异常账号0.01
        UpdateMinRedpacketAmountParam updateMinRedpacketAmountParam = new UpdateMinRedpacketAmountParam();
        updateMinRedpacketAmountParam.setActivityId(id);
        updateMinRedpacketAmountParam.setMinRedpacketAmount(new BigDecimal(0.01));
        updateMinRedpacketAmountParam.setMultiple(helpRedpacketActivityBO.getMultiple());
        List<Byte> suspectedList = new ArrayList<Byte>();
        suspectedList.add(AbnormalStatusEnum.SUSPECTED.getVal());
        updateMinRedpacketAmountParam.setAbnormalStatus(suspectedList);
        helpRedpacketAttendDetailDOMapperExtend.updateMinRedpacketAmount(updateMinRedpacketAmountParam);
        
        // 分配最低红包金额
        updateMinRedpacketAmountParam = new UpdateMinRedpacketAmountParam();
        updateMinRedpacketAmountParam.setActivityId(id);
        updateMinRedpacketAmountParam.setMinRedpacketAmount(helpRedpacketActivityBO.getMinRedpacket());
        updateMinRedpacketAmountParam.setMultiple(helpRedpacketActivityBO.getMultiple());
        List<Byte> normalList = new ArrayList<Byte>();
        normalList.add(AbnormalStatusEnum.NORMAL.getVal());
        normalList.add(AbnormalStatusEnum.WHITE_LIST.getVal()); 
        updateMinRedpacketAmountParam.setAbnormalStatus(normalList);
        helpRedpacketAttendDetailDOMapperExtend.updateMinRedpacketAmount(updateMinRedpacketAmountParam);
        
        // 获取已经分配的普通红包金额
        GetRedpacketAmountParam getRedpacketAmountParam = new GetRedpacketAmountParam();
        getRedpacketAmountParam.setActivityId(id);
        getRedpacketAmountParam.setAbnormalStatus(normalList);
        BigDecimal expectedMinRedpacketAmount = helpRedpacketAttendDetailDOMapperExtend.getRedpacketTotalAmount(getRedpacketAmountParam);
        
        // 发送异步事件生成并保存普通红包
        eventPublisher.publishGenerateNormalRedpacketEvent(id, param, expectedMinRedpacketAmount);
    }
    
    /**
     * 批量保存红包
     * 
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSaveNormalRedpacket(List<HelpRedpacketAttendDetailDO> batchList) {
        for(HelpRedpacketAttendDetailDO item : batchList) {
            helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(item);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal getNormalRedpacketTotalAmount(Integer id) {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        List<Byte> normalList = new ArrayList<Byte>();
        normalList.add(AbnormalStatusEnum.NORMAL.getVal());
        normalList.add(AbnormalStatusEnum.WHITE_LIST.getVal());
        GetRedpacketAmountParam getRedpacketAmountParam = new GetRedpacketAmountParam();
        getRedpacketAmountParam.setActivityId(id);
        getRedpacketAmountParam.setAbnormalStatus(normalList);
        return helpRedpacketAttendDetailDOMapperExtend.getRedpacketTotalAmount(getRedpacketAmountParam);
    }
    
    @SuppressWarnings("deprecation")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void againGenerateNormalRedpacket(Integer id, GenerateNormalRedpacketParam param) {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityBO helpRedpacketActivityBO = get(id);
        if (helpRedpacketActivityBO == null) {
            throw new DataNotExistException("活动不存在");
        }
        if (!HelpRedpacketActivityStatusEnum.REGIST_END.equals(helpRedpacketActivityBO.getStatus())) {
            throw new WrongOperationException("活动报名还未结束");
        }
        if (helpRedpacketActivityBO.getTotalManualAmount() == null) {
            throw new WrongOperationException("请先生成大额红包");
        }
        ExpectedRedpacketAmountBO expectedRedpacketAmountBO = expectedRedpacketAmount(id, null);
        if (expectedRedpacketAmountBO.getExpectedMaxRedpacketAmount().compareTo(param.getTotalAutoAmount()) < 0 || expectedRedpacketAmountBO.getExpectedMinRedpacketAmount().compareTo(param.getTotalAutoAmount()) > 0) {
            throw new WrongOperationException("自动分配红包总额必须介于期望值以内");
        }
        // 更新生成普通红包总金额到数据库
        HelpRedpacketActivityDO updateDO = new HelpRedpacketActivityDO();
        updateDO.setId(id);
        updateDO.setTotalAutoAmount(param.getTotalAutoAmount());
        updateDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.updateByPrimaryKeySelective(updateDO);
        
        List<Byte> normalList = new ArrayList<Byte>();
        normalList.add(AbnormalStatusEnum.NORMAL.getVal());
        normalList.add(AbnormalStatusEnum.WHITE_LIST.getVal());
        // 分配最低红包金额
        UpdateMinRedpacketAmountParam updateMinRedpacketAmountParam = new UpdateMinRedpacketAmountParam();
        updateMinRedpacketAmountParam.setActivityId(id);
        updateMinRedpacketAmountParam.setMinRedpacketAmount(helpRedpacketActivityBO.getMinRedpacket());
        updateMinRedpacketAmountParam.setMultiple(helpRedpacketActivityBO.getMultiple());
        updateMinRedpacketAmountParam.setAbnormalStatus(normalList);
        helpRedpacketAttendDetailDOMapperExtend.updateMinRedpacketAmount(updateMinRedpacketAmountParam);
        
        // 获取已经分配的普通红包金额
        GetRedpacketAmountParam getRedpacketAmountParam = new GetRedpacketAmountParam();
        getRedpacketAmountParam.setActivityId(id);
        getRedpacketAmountParam.setAbnormalStatus(normalList);
        BigDecimal expectedMinRedpacketAmount = helpRedpacketAttendDetailDOMapperExtend.getRedpacketTotalAmount(getRedpacketAmountParam);
        
        // 发送异步事件生成并保存普通红包
        eventPublisher.publishGenerateNormalRedpacketEvent(id, param, expectedMinRedpacketAmount);
    }

    @SuppressWarnings("deprecation")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void continueGenerateNormalRedpacket(Integer id) {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketActivityBO helpRedpacketActivityBO = get(id);
        if (helpRedpacketActivityBO == null) {
            throw new DataNotExistException("活动不存在");
        }
        if (!HelpRedpacketActivityStatusEnum.REGIST_END.equals(helpRedpacketActivityBO.getStatus())) {
            throw new WrongOperationException("活动报名还未结束");
        }
        if (helpRedpacketActivityBO.getTotalManualAmount() == null) {
            throw new WrongOperationException("请先生成大额红包");
        }
        
        List<Byte> normalList = new ArrayList<Byte>();
        normalList.add(AbnormalStatusEnum.NORMAL.getVal());
        normalList.add(AbnormalStatusEnum.WHITE_LIST.getVal());
        GetRedpacketAmountParam getRedpacketAmountParam = new GetRedpacketAmountParam();
        getRedpacketAmountParam.setActivityId(id);
        getRedpacketAmountParam.setAbnormalStatus(normalList);
        // 获取已经分配的普通红包金额
        BigDecimal expectedMinRedpacketAmount = helpRedpacketAttendDetailDOMapperExtend.getRedpacketTotalAmount(getRedpacketAmountParam);
        
        GenerateNormalRedpacketParam param = new GenerateNormalRedpacketParam();
        param.setTotalAutoAmount(helpRedpacketActivityBO.getTotalAutoAmount());
        // 发送异步事件生成并保存普通红包
        eventPublisher.publishGenerateNormalRedpacketEvent(id, param, expectedMinRedpacketAmount);
    }

    @Override
    public Page<HelpRedpacketActivityBO> list(RedpacketActivityQueryParam param) {
        Page<HelpRedpacketActivityBO> rtn = new Page<>();
        rtn.setCurrentPage(param.getCurrentPage());
        Long count = helpRedpacketActivityDOMapper.countByExample(null);
        rtn.setTotalCount(count.intValue());
        if (count <= 0 || param.getOffset() >= count) {
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<HelpRedpacketActivityDO> list = helpRedpacketActivityDOMapper.selectByExampleWithRowbounds(null, rowBounds);
        rtn.setRecords(HelpRedpacketActivityConverter.convert(list));
        return rtn;
    }

    @Override
    public void save(HelpRedpacketActivitySaveParam param) {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        if (StringUtils.isNotBlank(param.getActivityTheme())) {
            helpRedpacketActivityDO.setActivityTheme(param.getActivityTheme());
        }
        if (param.getEndTime() != null) {
            helpRedpacketActivityDO.setEndTime(param.getEndTime());
        }
        if (param.getOpen() != null) {
            helpRedpacketActivityDO.setIsOpen(param.getOpen());
        }
        if (param.getCloseEntry() != null) {
            helpRedpacketActivityDO.setIsCloseEntry(param.getCloseEntry());
        }
        if (param.getMaxRedpacket() != null) {
            helpRedpacketActivityDO.setMaxRedpacket(param.getMaxRedpacket());
        }
        if (param.getMinRedpacket() != null) {
            helpRedpacketActivityDO.setMinRedpacket(param.getMinRedpacket());
        }
        if (param.getMultiple() != null) {
            helpRedpacketActivityDO.setMultiple(param.getMultiple());
        }
        if (param.getRegEndTime() != null) {
            helpRedpacketActivityDO.setRegEndTime(param.getRegEndTime());
        }
        if (param.getRegStartTime() != null) {
            helpRedpacketActivityDO.setRegStartTime(param.getRegStartTime());
        }
        if (param.getStartTime() != null) {
            helpRedpacketActivityDO.setStartTime(param.getStartTime());
        }
        if (StringUtils.isNotBlank(param.getStartPic())) {
            helpRedpacketActivityDO.setStartPic(param.getStartPic());
        }
        if (StringUtils.isNotBlank(param.getEndPic())) {
            helpRedpacketActivityDO.setEndPic(param.getEndPic());
        }
        if (StringUtils.isNotBlank(param.getEndUrl())) {
            helpRedpacketActivityDO.setEndUrl(param.getEndUrl());
        }
        if (param.getType() != null) {
            helpRedpacketActivityDO.setType(param.getType().getVal());
        }
        if (param.getRedpacketType() != null) {
            helpRedpacketActivityDO.setRedpacketType(param.getRedpacketType().getVal());
        }
        if (StringUtils.isNotBlank(param.getWxActName())) {
            helpRedpacketActivityDO.setWxActName(param.getWxActName());
        }
        if (StringUtils.isNotBlank(param.getWxRemark())) {
            helpRedpacketActivityDO.setWxRemark(param.getWxRemark());
        }
        if (StringUtils.isNotBlank(param.getWxSendName())) {
            helpRedpacketActivityDO.setWxSendName(param.getWxSendName());
        }
        if (StringUtils.isNotBlank(param.getWxWishing())) {
            helpRedpacketActivityDO.setWxWishing(param.getWxWishing());
        }
        if (StringUtils.isNotBlank(param.getPuzzlePic())) {
            helpRedpacketActivityDO.setAdPic(param.getPuzzlePic());
        }
        if (param.getRules() != null && !param.getRules().isEmpty()) {
            helpRedpacketActivityDO.setRules(JSONObject.toJSONString(param.getRules()));
        }
        if(param.getSentAmount() != null){
        	helpRedpacketActivityDO.setSentAmount(param.getSentAmount());
        }
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDOMapper.insertSelective(helpRedpacketActivityDO);
    }

    @Override
    public List<HelpRedpacketActivityBO> openActivityList() {
        HelpRedpacketActivityDOExample example = new HelpRedpacketActivityDOExample();
        example.createCriteria().andIsOpenEqualTo(true);
        List<HelpRedpacketActivityDO> list = helpRedpacketActivityDOMapper.selectByExample(example);
        return HelpRedpacketActivityConverter.convert(list);
    }

    @Override
    public ExpectedRedpacketAmountBO expectedRedpacketAmount(Integer activityId, List<Long> excludeIds) {
        ExpectedRedpacketAmountBO rtn = new ExpectedRedpacketAmountBO();
        HelpRedpacketActivityBO helpRedpacketActivityBO = get(activityId);
        RedpacketAmountParam redpacketAmountParam = new RedpacketAmountParam();
        redpacketAmountParam.setActivityId(activityId);
        if (excludeIds != null && !excludeIds.isEmpty()) {
            redpacketAmountParam.setIds(excludeIds);
        }
        redpacketAmountParam.setMultiple(helpRedpacketActivityBO.getMultiple());
        // 获取红包金额的最小的范围
        redpacketAmountParam.setRedpacketAmount(helpRedpacketActivityBO.getMinRedpacket());
        BigDecimal expectedMinRedpacketAmount = helpRedpacketAttendDetailDOMapperExtend.getExpectedRedpacketAmount(redpacketAmountParam);
        rtn.setExpectedMinRedpacketAmount(expectedMinRedpacketAmount);
        // 获取红包金额的最大的范围
        redpacketAmountParam.setRedpacketAmount(helpRedpacketActivityBO.getMaxRedpacket());
        BigDecimal expectedMaxRedpacketAmount = helpRedpacketAttendDetailDOMapperExtend.getExpectedRedpacketAmount(redpacketAmountParam);
        rtn.setExpectedMaxRedpacketAmount(expectedMaxRedpacketAmount);
        
        
        return rtn;
    }

    @Override
    public BigDecimal getAbnormalRedpacketTotalAmount(Integer id) {
        if (id == null) {
            id = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        List<Byte> normalList = new ArrayList<Byte>();
        normalList.add(AbnormalStatusEnum.SUSPECTED.getVal());
        GetRedpacketAmountParam getRedpacketAmountParam = new GetRedpacketAmountParam();
        getRedpacketAmountParam.setActivityId(id);
        getRedpacketAmountParam.setAbnormalStatus(normalList);
        return helpRedpacketAttendDetailDOMapperExtend.getRedpacketTotalAmount(getRedpacketAmountParam);
    }
}
