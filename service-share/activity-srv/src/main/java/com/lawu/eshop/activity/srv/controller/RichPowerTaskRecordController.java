package com.lawu.eshop.activity.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.RichPowerTaskDTO;
import com.lawu.eshop.activity.dto.RichPowerTaskDetailDTO;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.RichPowerTaskDetailBO;
import com.lawu.eshop.activity.srv.servcie.RichPowerTaskRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
@RestController
@RequestMapping(value = "richPowerTaskRecord/")
public class RichPowerTaskRecordController extends BaseController{
	
	@Autowired
	private RichPowerTaskRecordService richPowerTaskRecordService;
	
	/**
	 * 更新动力任务完成记录
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveRichPowerTaskRecord", method = RequestMethod.POST)
    public Result saveRichPowerTaskRecord(@RequestBody RichPowerTaskRecordParam param) {
		richPowerTaskRecordService.saveRichPowerTaskRecord(param);
		return successCreated();
    }
	
	/**
	 * 获取所有动力任务
	 * 
	 * @param memberNum
	 * @return
	 */
	@RequestMapping(value = "getPowerTasks", method = RequestMethod.GET)
    public Result<RichPowerTaskDTO> getPowerTasks(@RequestParam String memberNum) {
		List<RichPowerTaskDetailBO> list = richPowerTaskRecordService.getPowerTasks(memberNum);
		RichPowerTaskDTO dto = new RichPowerTaskDTO();
		List<RichPowerTaskDetailDTO> listDTO = new ArrayList<>();
		for (RichPowerTaskDetailBO richPowerTaskDetailBO : list) {
			RichPowerTaskDetailDTO detailDTO = new RichPowerTaskDetailDTO();
			detailDTO.setIsFinish(richPowerTaskDetailBO.getIsFinish());
			detailDTO.setPowerCount(richPowerTaskDetailBO.getPowerCount());
			detailDTO.setTaskCount(richPowerTaskDetailBO.getTaskCount());
			detailDTO.setType(richPowerTaskDetailBO.getType());
			detailDTO.setBeginTime(richPowerTaskDetailBO.getBeginTime());
			detailDTO.setEndTime(richPowerTaskDetailBO.getEndTime());
			detailDTO.setStatus(richPowerTaskDetailBO.getStatus());
			detailDTO.setResidueCount(richPowerTaskDetailBO.getResidueCount());
			listDTO.add(detailDTO);
		}
		dto.setList(listDTO);
		return successGet(dto);
    }

	@RequestMapping(value = "resetTaskRecord", method = RequestMethod.PUT)
	public Result resetTaskRecord(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
		richPowerTaskRecordService.resetTaskRecord(currentPage,pageSize);
		return successCreated();
	}

	@RequestMapping(value = "getPowerTaskRecordListCount",method = RequestMethod.GET)
	public Result<Integer> getPowerTaskRecordListCount() {
		Integer total = richPowerTaskRecordService.getPowerTaskRecordListCount();
		return successGet(total);
	}

}
