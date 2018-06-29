package com.lawu.eshop.activity.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.AttendCountDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendRecordDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.dto.PointLotteryRollDTO;
import com.lawu.eshop.activity.param.PointLotteryActivityRecordParam;
import com.lawu.eshop.activity.query.PointLotteryActivityQueryParam;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendRecordBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.converter.PointLotteryActivityRecordConverter;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityRecordService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 参与活动记录接口
 *
 * @author zhangrc
 * @Description
 * @date 2018年2月1日
 */
@RestController
@RequestMapping(value = "pointLotteryActivityRecord/")
public class PointLotteryActivityRecordController extends BaseController {

    @Autowired
    private PointLotteryActivityRecordService pointLotteryActivityRecordService;
	
	/**
     * 分页查询当前活动的参与记录
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @RequestMapping(value = "page/{pointLotteryActivityId}", method = RequestMethod.PUT)
    public Result<Page<PointLotteryActivityRecordPageDTO>> page(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId, @RequestBody @Validated PointLotteryActivityQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Page<PointLotteryActivityRecordBO> page = pointLotteryActivityRecordService.page(pointLotteryActivityId, param);
        Page<PointLotteryActivityRecordPageDTO> model = new Page<>();
        model.setCurrentPage(page.getCurrentPage());
        model.setTotalCount(page.getTotalCount());
        model.setRecords(PointLotteryActivityRecordConverter.convertPointLotteryActivityRecordPageDTOList(page.getRecords()));
        return successGet(model);
    }
	
    /**
     * 最新20条开奖信息
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listLatestLotteryInfo", method = RequestMethod.GET)
    public Result<List<PointLotteryRollDTO>> listPointLotteryActivity() {
        List<PointLotteryRollView> rollViews = pointLotteryActivityRecordService.listLatestLotteryInfo();
        List<PointLotteryRollDTO> rollDTOS = PointLotteryActivityRecordConverter.convertRollDTOS(rollViews);
        return successGet(rollDTOS);
    }
    
    /**
     * 我的抽奖列表
     * 
     * @param param 
     * @return
     * @author zhangrc
     */
    @RequestMapping(value = "attendPrizePage", method = RequestMethod.POST)
    public Result<Page<PointLotteryActivityAttendRecordDTO>> attendPrizePage(@RequestBody PointLotteryActivityRecordParam param) {
        Page<PointLotteryActivityAttendRecordBO> recordPage = pointLotteryActivityRecordService.attendPrizePage(param);
        List<PointLotteryActivityAttendRecordDTO> dtos = PointLotteryActivityRecordConverter.convertAttendRecordDTOS(recordPage.getRecords());
        Page<PointLotteryActivityAttendRecordDTO> pageDTO = new Page<>();
        pageDTO.setCurrentPage(recordPage.getCurrentPage());
        pageDTO.setTotalCount(recordPage.getTotalCount());
        pageDTO.setRecords(dtos);
        return successCreated(pageDTO);
    }
    
    /**
     * 我的抽奖详情
     * @param userNum
     * @param id
     * @return
     */
    @RequestMapping(value = "getPointLotteryActivityAttendDetail", method = RequestMethod.GET)
    public Result<PointLotteryActivityAttendDetailDTO> getPointLotteryActivityAttendDetail(@RequestParam String userNum, @RequestParam Long id){
    	PointLotteryActivityAttendDetailBO detail = pointLotteryActivityRecordService.getPointLotteryActivityAttendDetail(userNum, id);
    	if(detail == null){
    		successCreated(ResultCode.NOT_FOUND_DATA);
    	}
    	PointLotteryActivityAttendDetailDTO dto = PointLotteryActivityRecordConverter.convertAttendRecordDetail(detail);
    	return successGet(dto);
    }
    
    
    /**
     * 统计参与的抽奖次数
     * 
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getAttendCount", method = RequestMethod.GET)
    public Result<AttendCountDTO> getAttendCount(@RequestParam String userNum) {
    	Integer count = pointLotteryActivityRecordService.getAttendCount(userNum);
    	AttendCountDTO dto = new AttendCountDTO();
    	dto.setCount(count);
    	return successGet(dto);
    }

}
