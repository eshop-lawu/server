package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendRecordDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.dto.PointLotteryRollDTO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendRecordBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityLotteryInfoBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;
import com.lawu.utils.StringUtil;

/**
 * 抽奖活动参与转换类
 *
 * @author zhangrc
 * @Description
 * @date 2018年2月1日
 */
public class PointLotteryActivityRecordConverter {
	
	/**
     * 隐藏构造方法
     */
    private PointLotteryActivityRecordConverter() {
        throw new IllegalAccessError("Utility class");
    }
    
    public static List<PointLotteryActivityRecordBO> convert(List<PointLotteryActivityRecordDO> source) {
        if (source == null) {
            return null;
        }
        List<PointLotteryActivityRecordBO> target = new ArrayList<>();
        for (PointLotteryActivityRecordDO item : source) {
            PointLotteryActivityRecordBO pointLotteryActivityRecordBO = new PointLotteryActivityRecordBO();
            pointLotteryActivityRecordBO.setLotteryNum(item.getLotteryNum());
            pointLotteryActivityRecordBO.setMobile(item.getMobile());
            pointLotteryActivityRecordBO.setPrizeName(item.getPrizeName());
            pointLotteryActivityRecordBO.setStatus(PointLotteryActivityRecordStatusEnum.getEnum(item.getStatus()));
            pointLotteryActivityRecordBO.setUserNum(item.getUserNum());
            target.add(pointLotteryActivityRecordBO);
        }
        return target;
    }
    
    public static List<PointLotteryActivityRecordPageDTO> convertPointLotteryActivityRecordPageDTOList(List<PointLotteryActivityRecordBO> source) {
        if (source == null) {
            return null;
        }
        List<PointLotteryActivityRecordPageDTO> target = new ArrayList<>();
        for (PointLotteryActivityRecordBO item : source) {
            PointLotteryActivityRecordPageDTO pointLotteryActivityRecordPageDTO  = new PointLotteryActivityRecordPageDTO();
            pointLotteryActivityRecordPageDTO.setLotteryNum(item.getLotteryNum());
            pointLotteryActivityRecordPageDTO.setMobile(item.getMobile());
            pointLotteryActivityRecordPageDTO.setPrizeName(item.getPrizeName());
            pointLotteryActivityRecordPageDTO.setStatus(item.getStatus());
            pointLotteryActivityRecordPageDTO.setUserNum(item.getUserNum());
            target.add(pointLotteryActivityRecordPageDTO);
        }
        return target;
    }
	
    public static PointLotteryActivityLotteryInfoBO convertInfoBO(PointLotteryActivityRecordDO recordDO) {
        if (recordDO == null) {
            return null;
        }

        PointLotteryActivityLotteryInfoBO recordBO = new PointLotteryActivityLotteryInfoBO();
        recordBO.setPrizeName(recordDO.getPrizeName());
        recordBO.setMobile(recordDO.getMobile());
        return recordBO;
    }

    public static List<PointLotteryActivityLotteryInfoBO> convertBOS(List<PointLotteryActivityRecordDO> recordDOS) {
        List<PointLotteryActivityLotteryInfoBO> recordBOS = new ArrayList<>();
        if (recordDOS == null || recordDOS.isEmpty()) {
            return recordBOS;
        }

        for (PointLotteryActivityRecordDO recordDO : recordDOS) {
            recordBOS.add(convertInfoBO(recordDO));
        }
        return recordBOS;
    }

    public static List<PointLotteryRollDTO> convertRollDTOS(List<PointLotteryRollView> rollViews) {
        List<PointLotteryRollDTO> rollDTOS = new ArrayList<>();
        if (rollViews == null || rollViews.isEmpty()) {
            return rollDTOS;
        }

        for (PointLotteryRollView rollView : rollViews) {
            PointLotteryRollDTO rollDTO = new PointLotteryRollDTO();
            rollDTO.setMobile(StringUtil.hideUserAccount(rollView.getMobile()));
            rollDTO.setPrizeName(rollView.getPrizeName());
            rollDTOS.add(rollDTO);
        }
        return rollDTOS;
    }

    public static PointLotteryActivityRecordBO convertBO(PointLotteryActivityRecordDO recordDO) {
        if (recordDO == null) {
            return null;
        }

        PointLotteryActivityRecordBO recordBO = new PointLotteryActivityRecordBO();
        recordBO.setId(recordDO.getId());
        recordBO.setUserNum(recordDO.getUserNum());
        recordBO.setMobile(recordDO.getMobile());
        recordBO.setLotteryNum(recordDO.getLotteryNum());
        recordBO.setPointLotteryActivityId(recordDO.getPointLotteryActivityId());
        recordBO.setPrizeName(recordDO.getPrizeName());
        recordBO.setStatus(PointLotteryActivityRecordStatusEnum.getEnum(recordDO.getStatus()));
        recordBO.setGmtModified(recordDO.getGmtModified());
        recordBO.setGmtCreate(recordDO.getGmtCreate());
        return recordBO;
    }
    
    /**
     * 我的抽奖列表
     * @param records
     * @return
     * @author zhangrc
     */
	public static List<PointLotteryActivityAttendRecordDTO> convertAttendRecordDTOS(
			List<PointLotteryActivityAttendRecordBO> records) {
		List<PointLotteryActivityAttendRecordDTO> list = new ArrayList<>();
		if(records == null){
			return list;
		}
		for (PointLotteryActivityAttendRecordBO pointLotteryActivityAttendRecordBO : records) {
			PointLotteryActivityAttendRecordDTO record = new PointLotteryActivityAttendRecordDTO();
			record.setAttendCount(pointLotteryActivityAttendRecordBO.getAttendCount());
			record.setLotteryTime(pointLotteryActivityAttendRecordBO.getLotteryTime());
			record.setPointLotteryActivityId(pointLotteryActivityAttendRecordBO.getPointLotteryActivityId());
			record.setPrizeImagePath(pointLotteryActivityAttendRecordBO.getPrizeImagePath());
			record.setPrizeName(pointLotteryActivityAttendRecordBO.getPrizeName());
			record.setStatusEnum(pointLotteryActivityAttendRecordBO.getStatusEnum());
			list.add(record);
		}
		return list;
	}

	public static PointLotteryActivityAttendDetailDTO convertAttendRecordDetail(
			PointLotteryActivityAttendDetailBO detail) {
		PointLotteryActivityAttendDetailDTO detailDTO = new PointLotteryActivityAttendDetailDTO();
		if(detail == null){
			return detailDTO;
		}
		detailDTO.setPrizeName(detail.getPrizeName());
		detailDTO.setPrizePrice(detail.getPrizePrice());
		detailDTO.setPrizeImagePath(detail.getPrizeImagePath());
		detailDTO.setLotteryTime(detail.getLotteryTime());
		detailDTO.setLotteryPoint(detail.getLotteryPoint());
		detailDTO.setLotteryResultNums(detail.getLotteryResultNums());
		detailDTO.setAttendCount(detail.getAttendCount());
		detailDTO.setAttendNums(detail.getAttendNums());
		detailDTO.setLotteryPoint(detail.getLotteryPoint());
		detailDTO.setStatusEnum(detail.getStatusEnum());
		return detailDTO;
	}

}
