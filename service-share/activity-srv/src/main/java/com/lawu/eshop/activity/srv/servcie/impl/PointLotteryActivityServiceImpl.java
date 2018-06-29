package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.GenerateBasicNumberParam;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityBO;
import com.lawu.eshop.activity.srv.converter.PointLotteryActivityConverter;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDOExample;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDOExample;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityPrizeImageDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityService;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@Service
public class PointLotteryActivityServiceImpl implements PointLotteryActivityService {

    @Autowired
    private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;

    @Autowired
    private PointLotteryActivityRecordDOMapper pointLotteryActivityRecordDOMapper;

    @Autowired
    private PointLotteryActivityPrizeImageDOMapper pointLotteryActivityPrizeImageDOMapper;

    @Override
    public Page<PointLotteryActivityBO> listPointLotteryActivity(PointLotteryActivityQuery query) {
        Date threeMonthBeforeDate = DateUtil.add(new Date(), -3, Calendar.MONTH);
        PointLotteryActivityDOExample example = new PointLotteryActivityDOExample();
        example.createCriteria().andStatusGreaterThanOrEqualTo(PointLotteryActivityStatusEnum.PROCESSING.getVal()).andStatusLessThanOrEqualTo(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal()).andGmtCreateGreaterThanOrEqualTo(threeMonthBeforeDate);
        example.setOrderByClause("status asc");
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<PointLotteryActivityDO> activityDOS = pointLotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        List<PointLotteryActivityDO> processingActivityDOS = new ArrayList<>();
        List<PointLotteryActivityDO> publishActivityDOS = new ArrayList<>();
        List<PointLotteryActivityDO> finishActivityDOS = new ArrayList<>();
        List<PointLotteryActivityDO> lotteryedActivityDOS = new ArrayList<>();
        for (PointLotteryActivityDO activityDO : activityDOS) {
            if (activityDO.getStatus().byteValue() == PointLotteryActivityStatusEnum.PROCESSING.getVal()) {
                processingActivityDOS.add(activityDO);
            } else if (activityDO.getStatus().byteValue() == PointLotteryActivityStatusEnum.PUBLISHED.getVal()) {
                publishActivityDOS.add(activityDO);
            } else if (activityDO.getStatus().byteValue() == PointLotteryActivityStatusEnum.PARTICIPATION_END.getVal()) {
                finishActivityDOS.add(activityDO);
            } else if (activityDO.getStatus().byteValue() == PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal()) {
                lotteryedActivityDOS.add(activityDO);
            }
        }
        if (!publishActivityDOS.isEmpty()) {
            Collections.sort(publishActivityDOS, new Comparator<PointLotteryActivityDO>() {
                @Override
                public int compare(PointLotteryActivityDO o1, PointLotteryActivityDO o2) {
                    return o1.getBeginTime().compareTo(o2.getBeginTime());
                }
            });
        }
        if (!finishActivityDOS.isEmpty()) {
            Collections.sort(finishActivityDOS, new Comparator<PointLotteryActivityDO>() {
                @Override
                public int compare(PointLotteryActivityDO o1, PointLotteryActivityDO o2) {
                    return o2.getEndTime().compareTo(o1.getEndTime());
                }
            });
        }
        if (!lotteryedActivityDOS.isEmpty()) {
            Collections.sort(lotteryedActivityDOS, new Comparator<PointLotteryActivityDO>() {
                @Override
                public int compare(PointLotteryActivityDO o1, PointLotteryActivityDO o2) {
                    return o2.getLotteryTime().compareTo(o1.getLotteryTime());
                }
            });
        }
        List<PointLotteryActivityDO> allActivityDOS = new ArrayList<>();
        allActivityDOS.addAll(processingActivityDOS);
        allActivityDOS.addAll(publishActivityDOS);
        allActivityDOS.addAll(finishActivityDOS);
        allActivityDOS.addAll(lotteryedActivityDOS);

        Page<PointLotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) pointLotteryActivityDOMapper.countByExample(example));
        page.setRecords(PointLotteryActivityConverter.convertBOS(allActivityDOS));
        return page;
    }

    @Override
    public PointLotteryActivityBO getPointLotteryActivity(Long id) {
        PointLotteryActivityDO activityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(id);
        return PointLotteryActivityConverter.convertBO(activityDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer generateBasicNumber(Long snatchPrizesActivityId, GenerateBasicNumberParam param) {
        //判断活动的状态
        PointLotteryActivityDO pointLotteryActivityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(snatchPrizesActivityId);

        if (!PointLotteryActivityStatusEnum.PARTICIPATION_END.equals(PointLotteryActivityStatusEnum.getEnum(pointLotteryActivityDO.getStatus()))) {
            throw new WrongOperationException("参与还未结束");
        }

        //查询所有参与改活动的人数
        Integer count = pointLotteryActivityDO.getAttentNumber();

        /*
         * 计算基数
         * 1.开奖日收盘时的上证指数 × 每日收盘时的深证成指 × 10000 = 12位数。（指数以证交所公布数字为准）；
         * 2.此12位数的前6位数字与后6位数字调换位置，倒序排列后（如首位是0，则直接抹去），再除以活动结束时的参与人数（每个抽奖号为一个人数），得到的余数加1即为获奖号码
         */
        String componentIndex = new BigDecimal(param.getShanghaiCompositeIndex()).multiply(new BigDecimal(param.getShenzhenComponentIndex())).multiply(new BigDecimal(10000)).setScale(0).toString();
        // 交换数值的前后部分
        int median = componentIndex.length() / 2;
        int remainder = componentIndex.length() % 2;
        String leftPart = componentIndex.substring(0, median);
        String rightPart = componentIndex.substring(componentIndex.length() - median);
        componentIndex = rightPart.concat(remainder != 0 ? String.valueOf(componentIndex.charAt(median + remainder)) : "").concat(leftPart);
        // 倒序排列
        StringBuilder reverseNum = new StringBuilder();
        for (int i = componentIndex.length() - 1; i >= 0; i--) {
            reverseNum.append(componentIndex.charAt(i));
        }
        // 计算中奖基数
        Integer lotteryBaseNum = new BigDecimal(reverseNum.toString()).remainder(new BigDecimal(count)).add(new BigDecimal(1)).intValue();

        // 保存抽奖参数和基本抽奖号码
        PointLotteryActivityDO record = new PointLotteryActivityDO();
        record.setId(pointLotteryActivityDO.getId());
        record.setLotteryParam(JSONObject.toJSONString(param));
        record.setLotteryBaseNum(lotteryBaseNum);
        record.setGmtModified(new Date());
        pointLotteryActivityDOMapper.updateByPrimaryKeySelective(record);
        return lotteryBaseNum;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWinningNumber(Long snatchPrizesActivityId, String prizeNumbers) {
        //判断活动的状态
        PointLotteryActivityDO pointLotteryActivityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(snatchPrizesActivityId);

        if (!PointLotteryActivityStatusEnum.PARTICIPATION_END.equals(PointLotteryActivityStatusEnum.getEnum(pointLotteryActivityDO.getStatus()))) {
            throw new WrongOperationException("参与还未结束");
        }
        // 判断
        String[] prizeNumArry = prizeNumbers.split(",");
        if (prizeNumArry.length != pointLotteryActivityDO.getLotteryCount()) {
            throw new WrongOperationException("请填写" + pointLotteryActivityDO.getLotteryCount() +  "个有效的中奖号码");
        }
        Set<String> prizeNumSet = new HashSet<>(prizeNumbers.length());
        List<String> abnormalNumbers = new ArrayList<>();
        for (String item : prizeNumArry) {
            if (Integer.valueOf(item) > pointLotteryActivityDO.getAttentNumber() || Integer.valueOf(item) <= 0) {
                abnormalNumbers.add(item);
            }
            prizeNumSet.add(item);
        }
        if (abnormalNumbers.size() > 0) {
            throw new WrongOperationException(StringUtils.join(abnormalNumbers, ",") + "不是一个有效的中奖号码");
        }
        if (prizeNumArry.length != prizeNumSet.size()) {
            throw new WrongOperationException("存在相同的中奖号码");
        }
        for (String prizeNum : prizeNumSet) {
            PointLotteryActivityRecordDOExample example = new PointLotteryActivityRecordDOExample();
            example.createCriteria().andPointLotteryActivityIdEqualTo(pointLotteryActivityDO.getId()).andLotteryNumEqualTo(Integer.valueOf(prizeNum));
            PointLotteryActivityRecordDO record = new PointLotteryActivityRecordDO();
            record.setPrizeName(pointLotteryActivityDO.getPrizeName());
            record.setStatus(PointLotteryActivityRecordStatusEnum.WINNING.getVal());
            record.setGmtModified(new Date());
            pointLotteryActivityRecordDOMapper.updateByExampleSelective(record, example);
        }

        // 保存中奖号码
        PointLotteryActivityDO record = new PointLotteryActivityDO();
        record.setId(pointLotteryActivityDO.getId());
        record.setLotteryResultNums(prizeNumbers);
        record.setDrawTime(new Date());
        record.setGmtModified(new Date());
        pointLotteryActivityDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePointLotteryActivity(PointLotteryActivityParam param) {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName(param.getPrizeName());
        activityDO.setPrizePrice(param.getPrizePrice());
        activityDO.setPrizeImagePath(param.getPrizeImagePath());
        activityDO.setBeginTime(param.getBeginTime());
        activityDO.setEndTime(param.getEndTime());
        activityDO.setLotteryTime(param.getLotteryTime());
        activityDO.setLotteryPoint(param.getLotteryPoint());
        activityDO.setLotteryCount(param.getLotteryCount());
        activityDO.setHotNumber(param.getHotNumber());
        activityDO.setStatus(param.getStatusEnum().getVal());
        if (param.getId() != null && param.getId() > 0) {
            PointLotteryActivityDO pointLotteryActivityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(param.getId());
            if (!pointLotteryActivityDO.getPrizeName().equals(param.getPrizeName())) {
                //更新中奖记录的奖品名称
                PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
                recordDO.setPrizeName(param.getPrizeName());
                recordDO.setGmtModified(new Date());
                PointLotteryActivityRecordDOExample recordDOExample = new PointLotteryActivityRecordDOExample();
                recordDOExample.createCriteria().andPointLotteryActivityIdEqualTo(param.getId()).andStatusEqualTo(PointLotteryActivityRecordStatusEnum.WINNING.getVal());
                pointLotteryActivityRecordDOMapper.updateByExampleSelective(recordDO, recordDOExample);
            }

            activityDO.setId(param.getId());
            activityDO.setGmtModified(new Date());
            pointLotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);

            //删除抽奖活动图片
            PointLotteryActivityPrizeImageDOExample imageDOExample = new PointLotteryActivityPrizeImageDOExample();
            imageDOExample.createCriteria().andPointLotteryActivityIdEqualTo(activityDO.getId());
            pointLotteryActivityPrizeImageDOMapper.deleteByExample(imageDOExample);
        } else {
            activityDO.setGmtCreate(new Date());
            pointLotteryActivityDOMapper.insertSelective(activityDO);
        }

        if (StringUtils.isNotEmpty(param.getImagePath())) {
            String[] imagePathArr = param.getImagePath().split(",");
            String[] orderNumArr = param.getOrderNum().split(",");
            String[] typeArr = param.getType().split(",");
            for (int i = 0; i < imagePathArr.length; i++) {
                PointLotteryActivityPrizeImageDO imageDO = new PointLotteryActivityPrizeImageDO();
                imageDO.setPointLotteryActivityId(activityDO.getId());
                imageDO.setImagePath(imagePathArr[i]);
                imageDO.setOrderNum(Integer.valueOf(orderNumArr[i]));
                imageDO.setType(Byte.valueOf(typeArr[i]));
                imageDO.setGmtCreate(new Date());
                pointLotteryActivityPrizeImageDOMapper.insertSelective(imageDO);
            }
        }
    }

    @Override
    public Page<PointLotteryActivityBO> listOperatorPointLotteryActivity(OperatorPointLotteryActivityQuery query) {
        PointLotteryActivityDOExample example = new PointLotteryActivityDOExample();
        PointLotteryActivityDOExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        if (StringUtils.isNotEmpty(query.getPrizeName())) {
            criteria.andPrizeNameLike("%" + query.getPrizeName() + "%");
        }
        if (StringUtils.isNotEmpty(query.getBeginTime())) {
            Date beginDate = DateUtil.formatDate(query.getBeginTime() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            criteria.andGmtCreateGreaterThanOrEqualTo(beginDate);
        }
        if (StringUtils.isNotEmpty(query.getEndTime())) {
            Date endDate = DateUtil.formatDate(query.getEndTime() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            criteria.andGmtCreateLessThanOrEqualTo(endDate);
        }
        if (query.getStatusEnum() != null) {
            criteria.andStatusEqualTo(query.getStatusEnum().getVal());
        }
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<PointLotteryActivityDO> activityDOS = pointLotteryActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<PointLotteryActivityBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) pointLotteryActivityDOMapper.countByExample(example));
        page.setRecords(PointLotteryActivityConverter.convertBOS(activityDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePointLotteryActivityStatus(Long id, PointLotteryActivityStatusEnum statusEnum) {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setId(id);
        activityDO.setStatus(statusEnum.getVal());
        activityDO.setGmtModified(new Date());
        pointLotteryActivityDOMapper.updateByPrimaryKeySelective(activityDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeUpdatePointLotteryActivityStatus() {
        Date date = new Date();
        //进行中更新为结束
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setGmtModified(date);
        activityDO.setStatus(PointLotteryActivityStatusEnum.PARTICIPATION_END.getVal());
        PointLotteryActivityDOExample example = new PointLotteryActivityDOExample();
        example.createCriteria().andStatusEqualTo(PointLotteryActivityStatusEnum.PROCESSING.getVal()).andEndTimeLessThanOrEqualTo(date);
        pointLotteryActivityDOMapper.updateByExampleSelective(activityDO, example);

        //即将开始更新为进行中
        activityDO.setStatus(PointLotteryActivityStatusEnum.PROCESSING.getVal());
        example = new PointLotteryActivityDOExample();
        example.createCriteria().andStatusEqualTo(PointLotteryActivityStatusEnum.PUBLISHED.getVal()).andBeginTimeLessThanOrEqualTo(date);
        pointLotteryActivityDOMapper.updateByExampleSelective(activityDO, example);
    }

    @Override
    public List<PointLotteryActivityBO> listRelatePointLotteryActivity() {
        PointLotteryActivityDOExample example = new PointLotteryActivityDOExample();
        example.createCriteria().andStatusGreaterThanOrEqualTo(PointLotteryActivityStatusEnum.PROCESSING.getVal()).andStatusLessThanOrEqualTo(PointLotteryActivityStatusEnum.PUBLISHED.getVal());
        example.setOrderByClause("status asc");
        List<PointLotteryActivityDO> activityDOS = pointLotteryActivityDOMapper.selectByExample(example);
        return PointLotteryActivityConverter.convertBOS(activityDOS);
    }

}
