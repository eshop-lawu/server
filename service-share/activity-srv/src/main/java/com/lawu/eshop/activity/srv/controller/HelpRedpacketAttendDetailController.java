package com.lawu.eshop.activity.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendPageDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketUserDTO;
import com.lawu.eshop.activity.dto.IdentifiedAsAbnormalDTO;
import com.lawu.eshop.activity.dto.IdentifiedAsAbnormalPackageDTO;
import com.lawu.eshop.activity.dto.InviteRecordDTO;
import com.lawu.eshop.activity.dto.SignAbnormalAccountDTO;
import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.eshop.activity.param.HelpRedpacketUserParam;
import com.lawu.eshop.activity.param.SignAbnormalParam;
import com.lawu.eshop.activity.srv.bo.AttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendPageBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketUserBO;
import com.lawu.eshop.activity.srv.bo.IdentifiedAsAbnormalBO;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;
import com.lawu.eshop.activity.srv.bo.SignAbnormalAccountBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketAttendDetailConverter;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketInviteRecordConverter;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketActivityService;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketAttendDetailService;
import com.lawu.eshop.activity.srv.servcie.RedpacketSendRecordService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 红包活动报名接口
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@RestController
@RequestMapping(value = "helpRedpacketAttendDetail/")
public class HelpRedpacketAttendDetailController extends BaseController{

	@Autowired
	private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;

	@Autowired
	private HelpRedpacketActivityService helpRedpacketActivityService;

	@Autowired
	private RedpacketSendRecordService redpacketSendRecordService;

	private static Logger logger = LoggerFactory.getLogger(HelpRedpacketAttendDetailController.class);

	/**
	 * 活动详情
	 * @param userNum
	 * @return
	 */
	@SuppressWarnings("deprecation")
    @RequestMapping(value = "helpRedpacketAttendDetail", method = RequestMethod.GET)
	public Result<HelpRedpacketAttendDTO> helpRedpacketAttendDetail(@RequestParam(name = "activityId", required = false) Integer activityId, @RequestParam String userNum, @RequestParam Integer helpCount){
	    try {
			HelpRedpacketAttendBO helpRedpacketAttendBO = helpRedpacketAttendDetailService.helpRedpacketAttendDetail(activityId == null ? PropertyConstant.HELP_REDPACKET_ACTIVITY_ID : activityId, userNum, helpCount);
			HelpRedpacketAttendDTO helpRedpacketAttendDTO = HelpRedpacketAttendDetailConverter.converterDTO(helpRedpacketAttendBO);
			if (helpRedpacketAttendBO.getInviteList() != null && !helpRedpacketAttendBO.getInviteList().isEmpty()){
				List<InviteRecordDTO> records = HelpRedpacketInviteRecordConverter.helpRedpacketInviteRecordConverterDTOS(helpRedpacketAttendBO.getInviteList());
				helpRedpacketAttendDTO.setInviteList(records);
			}
			return successGet(helpRedpacketAttendDTO);
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		}

	}

	/**
	 * 参与报名
	 * @param attendParam
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
    @RequestMapping(value = "helpRedpacketAttend", method = RequestMethod.POST)
	public Result helpRedpacketAttend(@RequestBody HelpRedpacketAttendParam attendParam){
		//判断活动状态
		HelpRedpacketActivityBO activityBO = helpRedpacketActivityService.get(attendParam.getActivityId() == null ? PropertyConstant.HELP_REDPACKET_ACTIVITY_ID : attendParam.getActivityId());
		if(activityBO == null){
			 return successCreated(ResultCode.NOT_FOUND_DATA);
		}
		HelpRedpacketActivityStatusEnum activityStatusEnum = activityBO.getStatus();

		if(activityStatusEnum != HelpRedpacketActivityStatusEnum.REGISTING){
			return successCreated(ResultCode.HELP_ACTIVITY_STATUS_NOT_REGISTING);
		}
		AttendBO attenBO = helpRedpacketAttendDetailService.helpRedpacketAttend(attendParam);
		if (attenBO.getIsAttend()) {
			return successCreated(ResultCode.HELP_ACTIVITY_STATUS_ATTEND);
		}
		return successCreated();
	}
 
	/**
	 * 运营后台查询报名列表
	 * @param detailParam
	 * @return
	 */
	@RequestMapping(value = "helpRedpacketAttendPageOperator", method = RequestMethod.POST)
	public Result<Page<HelpRedpacketAttendPageDTO>> helpRedpacketAttendPageOperator(@RequestBody HelpRedpacketDetailOperatorParam detailParam){

		Page<HelpRedpacketAttendPageBO> page = helpRedpacketAttendDetailService.helpRedpacketAttendPageOperator(detailParam);
		Page<HelpRedpacketAttendPageDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(page.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(HelpRedpacketAttendDetailConverter.converterOperatorDTO(page.getRecords()));

		return successCreated(pageDTO);
	}


	
	/**
	 * 领取红包
	 * @param userNum
	 * @return
	 */
	@SuppressWarnings("deprecation")
    @RequestMapping(value = "getHelpRedpacket", method = RequestMethod.PUT)
	public Result<HelpRedpacketDTO> getHelpRedpacket(@RequestParam(name = "activityId", required = false) Integer activityId, @RequestParam String userNum){
	    if (activityId == null) {
	        activityId = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
	    }
        HelpRedpacketActivityBO activityBO = helpRedpacketActivityService.get(activityId);
        if (activityBO == null) {
            return successCreated(ResultCode.NOT_FOUND_DATA);
        }
        HelpRedpacketActivityStatusEnum activityStatusEnum = activityBO.getStatus();
        if (activityStatusEnum == HelpRedpacketActivityStatusEnum.NOT_STARTED) {
            return successCreated(ResultCode.HELP_ACTIVITY_STATUS_NOT_START);
        }
        if (activityStatusEnum == HelpRedpacketActivityStatusEnum.REGISTING) {
            return successCreated(ResultCode.HELP_ACTIVITY_STATUS_NOT_START);
        }
        if (activityStatusEnum == HelpRedpacketActivityStatusEnum.REGIST_END) {
            return successCreated(ResultCode.HELP_ACTIVITY_STATUS_NOT_START);
        }
        if (activityStatusEnum == HelpRedpacketActivityStatusEnum.END) {
            return successCreated(ResultCode.HELP_ACTIVITY_STATUS_END);
        }
        HelpRedpacketBO helpRedpacketBO = helpRedpacketAttendDetailService.getHelpRedpacket(activityId, userNum);
        if (helpRedpacketBO == null) { 
            return successCreated(ResultCode.HELP_ACTIVITY_GET_ERROR);
        }
		HelpRedpacketDTO helpRedpacketDTO = new HelpRedpacketDTO();
		helpRedpacketDTO.setMoney(helpRedpacketBO.getMoney());
		helpRedpacketDTO.setMultiple(helpRedpacketBO.getMultiple());
		helpRedpacketDTO.setOriginalMoney(helpRedpacketBO.getOriginalMoney());
		return successCreated(helpRedpacketDTO);
	}

    /**
     * 查询待发放的红包详情记录
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listSendRedpacketAttendDetail", method = RequestMethod.GET)
    public Result<List<HelpRedpacketAttendSendDTO>> listSendRedpacketAttendDetail(@RequestParam int offset, @RequestParam int pageSize) {
        List<HelpRedpacketAttendDetailBO> attendDetailBOS = helpRedpacketAttendDetailService.listSendRedpacketAttendDetail(offset, pageSize);
        List<HelpRedpacketAttendSendDTO> attendSendDTOS = HelpRedpacketAttendDetailConverter.converterDTOS(attendDetailBOS);
        if (!attendSendDTOS.isEmpty()) {
            HelpRedpacketActivityBO activityBO = helpRedpacketActivityService.get(attendSendDTOS.get(0).getActivityId());
            for (HelpRedpacketAttendSendDTO attendSendDTO : attendSendDTOS) {
                attendSendDTO.setSendName(activityBO.getWxSendName());
                attendSendDTO.setWishing(activityBO.getWxWishing());
                attendSendDTO.setActName(activityBO.getWxActName());
                attendSendDTO.setRemark(activityBO.getWxRemark());
            }
        }
        return successGet(attendSendDTOS);
    }
	
	/**
	 * 获取已经领到红包的用户
	 * @param param
	 * @return
	 */
	@SuppressWarnings("deprecation")
    @RequestMapping(value = "getHelpRedpacketUser", method = RequestMethod.POST)
	public Result<Page<HelpRedpacketUserDTO>> getHelpRedpacketUser(@RequestBody @Validated HelpRedpacketUserParam param, BindingResult bindingResult){
	    String message = validate(bindingResult);
	    if (message != null) {
	        return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
	    }
        if (param.getActivityId() == null) {
            param.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
        }
		Page<HelpRedpacketUserBO> page = helpRedpacketAttendDetailService.getHelpRedpacketUser(param);
		Page<HelpRedpacketUserDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(page.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(HelpRedpacketAttendDetailConverter.converterUserDTO(page.getRecords()));
		return successCreated(pageDTO);
	}

	/**
	 * 根据id查询发送红包信息
	 *
	 * @param id
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "getSendRedpacketAttendDetail/{id}", method = RequestMethod.GET)
	public Result<HelpRedpacketAttendSendDTO> getSendRedpacketAttendDetail(@PathVariable Long id, @RequestParam String mchBillno) {
		HelpRedpacketAttendDetailBO detailBO = helpRedpacketAttendDetailService.selectByPrimaryKey(id);
		if (detailBO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		HelpRedpacketAttendSendDTO attendSendDTO = new HelpRedpacketAttendSendDTO();
		attendSendDTO.setWxOpenid(detailBO.getWxOpenid());
		attendSendDTO.setFinalMoney(detailBO.getFinalMoney().intValue());

		HelpRedpacketActivityBO activityBO = helpRedpacketActivityService.get(detailBO.getActivityId());
		attendSendDTO.setSendName(activityBO.getWxSendName());
		attendSendDTO.setWishing(activityBO.getWxWishing());
		attendSendDTO.setActName(activityBO.getWxActName());
		attendSendDTO.setRemark(activityBO.getWxRemark());

		RedpacketSendRecordBO recordBO = redpacketSendRecordService.getRedpacketSendRecord(id, mchBillno);
		if (recordBO != null) {
			attendSendDTO.setSendRecordId(recordBO.getId());
		}
		return successGet(attendSendDTO);
	}


	@RequestMapping(value = "executeUpdateActivitySuspectedStatus", method = RequestMethod.PUT)
	public Result executeUpdateActivitySuspectedStatus(){
		helpRedpacketAttendDetailService.executeUpdateActivitySuspectedStatus();
		return successCreated();
	}

	@RequestMapping(value = "queryAttendDetailList", method = RequestMethod.POST)
	public Result<List<SignAbnormalAccountDTO>> queryAttendDetailList(@RequestBody SignAbnormalParam param) {
		List<SignAbnormalAccountBO> list = helpRedpacketAttendDetailService.queryAttendDetailList(param);
		return successGet(HelpRedpacketAttendDetailConverter.converterAbnormalDTOS(list));
	}

	@RequestMapping(value = "updateActivityAbnormalStatus/{id}", method = RequestMethod.PUT)
	public Result updateActivityAbnormalStatus(@PathVariable("id") Long id,@RequestParam("status") Byte status){
		helpRedpacketAttendDetailService.updateActivityAbnormalStatus(id,status);
		return successCreated();
	}


    /**
     * 标识当前用户活动参与记录为异常<p>
     * 以及下级参与记录也标识为异常
     *
     * @param userNums
     * @param activityId
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月1日
     * @updateDate 2018年3月1日
     */
	@RequestMapping(value = "identifiedAsAbnormal",method = RequestMethod.PUT)
    public Result<IdentifiedAsAbnormalPackageDTO> identifiedAsAbnormal(@RequestBody List<String> userNums,@RequestParam("activityId") Integer activityId) {
        IdentifiedAsAbnormalPackageDTO model = new IdentifiedAsAbnormalPackageDTO();
        List<IdentifiedAsAbnormalDTO> abnormalList = new ArrayList<>();
        List<IdentifiedAsAbnormalBO> inviteUserList = helpRedpacketAttendDetailService.identifiedAsAbnormal(userNums,activityId);
        inviteUserList.forEach(item -> {
            IdentifiedAsAbnormalDTO identifiedAsAbnormalDTO = new IdentifiedAsAbnormalDTO();
            identifiedAsAbnormalDTO.setAccount(item.getAccount());
            identifiedAsAbnormalDTO.setUserNum(item.getUserNum());
            abnormalList.add(identifiedAsAbnormalDTO);
        });
        model.setAbnormalList(abnormalList);
        return successCreated(model);
    }
}
