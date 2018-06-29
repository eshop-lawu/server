package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryActivityRecordParam;
import com.lawu.eshop.activity.query.PointLotteryActivityQueryParam;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendRecordBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.converter.PointLotteryActivityRecordConverter;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryActivityRecordDOExtend;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.PointLotteryActivityRecordDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityRecordService;
import com.lawu.framework.core.page.Page;

/**
 * 参与抽奖
 *
 * @author zhangrc
 * @Description
 * @date 2018年2月1日
 */
@Service
public class PointLotteryActivityRecordServiceImpl implements PointLotteryActivityRecordService {

    @Autowired
    private PointLotteryActivityRecordDOMapper pointLotteryActivityRecordDOMapper;

		
	@Autowired
	private PointLotteryActivityRecordDOMapperExtend pointLotteryActivityRecordDOMapperExtend;
	
	@Autowired
	private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;
	
	@Override
	public Page<PointLotteryActivityAttendRecordBO> attendPrizePage(PointLotteryActivityRecordParam param) {
		
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<PointLotteryActivityRecordDOExtend> list = pointLotteryActivityRecordDOMapperExtend.attendPrizePage(param.getUserNum(),rowBounds);
		
		List<PointLotteryActivityAttendRecordBO> attendRecords = new ArrayList<>();
		for (PointLotteryActivityRecordDOExtend pointLotteryActivityRecordDOExtend : list) {
			
			PointLotteryActivityAttendRecordBO record = new PointLotteryActivityAttendRecordBO();
			record.setAttendCount(pointLotteryActivityRecordDOExtend.getAttendCount());
			record.setPointLotteryActivityId(pointLotteryActivityRecordDOExtend.getPointLotteryActivityId());
			record.setPrizeName(pointLotteryActivityRecordDOExtend.getPrizeName());
			record.setPrizeImagePath(pointLotteryActivityRecordDOExtend.getPrizeImagePath());
			record.setLotteryTime(pointLotteryActivityRecordDOExtend.getLotteryTime());
			PointLotteryActivityDO pointLotteryActivityDO =	pointLotteryActivityDOMapper.selectByPrimaryKey(pointLotteryActivityRecordDOExtend.getPointLotteryActivityId());
			//已开奖
			if (pointLotteryActivityDO.getStatus().equals(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal())) {
				//通过分组查询参入的抽奖活动查询该活动中奖情况
				PointLotteryActivityRecordDOExample example = new PointLotteryActivityRecordDOExample();
				example.createCriteria().andUserNumEqualTo(param.getUserNum()).andPointLotteryActivityIdEqualTo(pointLotteryActivityRecordDOExtend.getPointLotteryActivityId());
				List<PointLotteryActivityRecordDO> pointList = pointLotteryActivityRecordDOMapper.selectByExample(example);
				record.setStatusEnum(PointLotteryActivityRecordEnum.NO_WIN_LOTTERY);
				for (PointLotteryActivityRecordDO pointLotteryActivityRecordDO : pointList) {
					if (pointLotteryActivityRecordDO.getStatus()
							.equals(PointLotteryActivityRecordStatusEnum.WINNING.getVal())) {
						record.setStatusEnum(PointLotteryActivityRecordEnum.WIN_LOTTERY);
						break;
					}
				}
			}else{
				record.setStatusEnum(PointLotteryActivityRecordEnum.NO_OPEN_LOTTERY);
			}
			
			attendRecords.add(record);
		}
		Page<PointLotteryActivityAttendRecordBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		page.setTotalCount(pointLotteryActivityRecordDOMapperExtend.selectCount(param.getUserNum()));
		page.setRecords(attendRecords);
		
		return page;
	}

    @Override
    public Integer countPointLotteryActivityRecord(Long pointLotteryActivityId, String userNum) {
		if (StringUtils.isEmpty(userNum)) {
			return 0;
		}
		PointLotteryActivityRecordDOExample example = new PointLotteryActivityRecordDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andPointLotteryActivityIdEqualTo(pointLotteryActivityId);
        return (int) pointLotteryActivityRecordDOMapper.countByExample(example);
    }

    @Override
    public PointLotteryActivityRecordBO getPointLotteryActivityRecord(Long id) {
        PointLotteryActivityRecordDO recordDO = pointLotteryActivityRecordDOMapper.selectByPrimaryKey(id);
        return PointLotteryActivityRecordConverter.convertBO(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLotteryStatus(Long id, PointLotteryActivityRecordStatusEnum statusEnum) {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setId(id);
        recordDO.setStatus(statusEnum.getVal());
        recordDO.setGmtModified(new Date());
        pointLotteryActivityRecordDOMapper.updateByPrimaryKeySelective(recordDO);
    }

	@Override
	public List<PointLotteryRollView> listLatestLotteryInfo() {
		return pointLotteryActivityRecordDOMapperExtend.listLatestLotteryInfo();
	}

	@Override
    public Page<PointLotteryActivityRecordBO> page(Long pointLotteryActivityId, PointLotteryActivityQueryParam param) {
        Page<PointLotteryActivityRecordBO> rtn = new Page<>();
        rtn.setCurrentPage(param.getCurrentPage());
        PointLotteryActivityRecordDOExample example = new PointLotteryActivityRecordDOExample();
        PointLotteryActivityRecordDOExample.Criteria criteria = example.createCriteria().andPointLotteryActivityIdEqualTo(pointLotteryActivityId);
        if (param.getStatus() != null) {
            criteria.andStatusEqualTo(param.getStatus().getVal());
        }
        if (StringUtils.isNotBlank(param.getMobile())) {
            criteria.andMobileEqualTo(param.getMobile());
        }
        long count = pointLotteryActivityRecordDOMapper.countByExample(example);
        rtn.setTotalCount((int) count);
        if (count <= 0 || param.getOffset() > count) { 
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<PointLotteryActivityRecordDO> records = pointLotteryActivityRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(PointLotteryActivityRecordConverter.convert(records));
        return rtn;
    }

	@Override
	public PointLotteryActivityAttendDetailBO getPointLotteryActivityAttendDetail(String userNum, Long id) {
		PointLotteryActivityDO pointLotteryActivityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(id);
		if(pointLotteryActivityDO == null){
			return null;
		}
		PointLotteryActivityAttendDetailBO detail = new PointLotteryActivityAttendDetailBO();
		if(pointLotteryActivityDO.getStatus().equals(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal())){
			detail.setLotteryResultNums(pointLotteryActivityDO.getLotteryResultNums());
		}
		
		detail.setLotteryTime(pointLotteryActivityDO.getLotteryTime());
		detail.setPrizeImagePath(pointLotteryActivityDO.getPrizeImagePath());
		detail.setPrizeName(pointLotteryActivityDO.getPrizeName());
		detail.setPrizePrice(pointLotteryActivityDO.getPrizePrice());
		
		PointLotteryActivityRecordDOExample example = new PointLotteryActivityRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andPointLotteryActivityIdEqualTo(id);
		List<PointLotteryActivityRecordDO> pointList = pointLotteryActivityRecordDOMapper.selectByExample(example);
		detail.setAttendCount(pointList.size());
		detail.setLotteryPoint(pointLotteryActivityDO.getLotteryPoint()*pointList.size());
		StringBuilder attendNums = new StringBuilder();
		for (PointLotteryActivityRecordDO pointLotteryActivityRecordDO : pointList) {
			attendNums.append(pointLotteryActivityRecordDO.getLotteryNum()+"、");
		}
		detail.setAttendNums(attendNums.toString().substring(0, attendNums.toString().length()-1));
		if (pointLotteryActivityDO.getStatus().equals(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal())) {
			detail.setStatusEnum(PointLotteryActivityRecordEnum.NO_WIN_LOTTERY);
			for (PointLotteryActivityRecordDO pointLotteryActivityRecordDO : pointList) {
				if (pointLotteryActivityRecordDO.getStatus()
						.equals(PointLotteryActivityRecordStatusEnum.WINNING.getVal())) {
					detail.setStatusEnum(PointLotteryActivityRecordEnum.WIN_LOTTERY);
					break;
				}
			}
		}else{
			detail.setStatusEnum(PointLotteryActivityRecordEnum.NO_OPEN_LOTTERY);
		}
		return detail;
	}

	@Override
	public Integer getAttendCount(String userNum) {
		PointLotteryActivityRecordDOExample example = new PointLotteryActivityRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
		Long count = pointLotteryActivityRecordDOMapper.countByExample(example);
		return count != null ? count.intValue() : 0;
	}

}
