package com.lawu.eshop.activity.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.MerchantRichPowerTaskDTO;
import com.lawu.eshop.activity.dto.MerchantRichPowerTaskDetailDTO;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.MerchantRichPowerTaskDetailBO;
import com.lawu.eshop.activity.srv.servcie.RichMerchantPowerTaskRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月7日
 */
@RestController
@RequestMapping(value = "richMerchantPowerTaskRecord/")
public class RichMerchantPowerTaskRecordController extends BaseController{
	
	@Autowired
	private RichMerchantPowerTaskRecordService richMerchantPowerTaskRecordService;
	
	/**
	 * 更新动力任务完成记录
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveRichMerchantPowerTaskRecord", method = RequestMethod.POST)
    public Result saveRichMerchantPowerTaskRecord(@RequestBody RichMerchantPowerTaskRecordParam param) {
		richMerchantPowerTaskRecordService.saveRichPowerTaskRecord(param);
		return successCreated();
    }

	/**
	 * 查询商家完成动力情况总数
	 * @return
	 */
	@RequestMapping(value = "getMerchantPowerTaskRecordListCount",method = RequestMethod.GET)
	public Result<Integer> getMerchantPowerTaskRecordListCount() {
		Integer total = richMerchantPowerTaskRecordService.getMerchantPowerTaskRecordListCount();
		return successGet(total);
	}

	/**
	 * 每日都清空动力情况
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "resetMerchantTaskRecord", method = RequestMethod.PUT)
	public Result resetMerchantTaskRecord(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
		richMerchantPowerTaskRecordService.resetMerchantTaskRecord(currentPage,pageSize);
		return successCreated();
	}


	/**
	 * 商家端获取动力任务列表
	 * @param memberNum
	 * @return
	 */
	@RequestMapping(value = "getPowerTasks", method = RequestMethod.GET)
	public Result<MerchantRichPowerTaskDTO> getPowerTasks(@RequestParam("memberNum") String memberNum) {
		List<MerchantRichPowerTaskDetailBO> detailBOS = richMerchantPowerTaskRecordService.getPowerTasks(memberNum);
		MerchantRichPowerTaskDTO taskDTO = new MerchantRichPowerTaskDTO();
		if (detailBOS.isEmpty()) {
			taskDTO.setDetailDTOS(new ArrayList<>());
			return successGet(taskDTO);
		}
		List<MerchantRichPowerTaskDetailDTO> detailDTOS = new ArrayList<>();
		for (MerchantRichPowerTaskDetailBO detailBO : detailBOS) {
			MerchantRichPowerTaskDetailDTO detailDTO = new MerchantRichPowerTaskDetailDTO();
			detailDTO.setIsFinish(detailBO.getIsFinish());
			detailDTO.setPowerCount(detailBO.getPowerCount());
			detailDTO.setTaskCount(detailBO.getTaskCount());
			detailDTO.setType(detailBO.getType());
			detailDTO.setBeginTime(detailBO.getBeginTime());
			detailDTO.setEndTime(detailBO.getEndTime());
			detailDTO.setStatus(detailBO.getStatus());
			detailDTO.setResidueCount(detailBO.getResidueCount());
			detailDTOS.add(detailDTO);
		}
		taskDTO.setDetailDTOS(detailDTOS);
		return successGet(taskDTO);
	}
	
	
}
