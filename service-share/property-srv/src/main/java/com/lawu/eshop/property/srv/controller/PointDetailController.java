package com.lawu.eshop.property.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.lawu.eshop.property.constants.PayTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.dto.AreaPointConsumeDTO;
import com.lawu.eshop.property.dto.IncomeMsgDTO;
import com.lawu.eshop.property.dto.PointConsumeReportDTO;
import com.lawu.eshop.property.dto.PointDetailBackageDTO;
import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.dto.ReportAdPointGroupByAreaDTO;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.eshop.property.param.PointDetailReportParam;
import com.lawu.eshop.property.param.PointDetailSaveDataParam;
import com.lawu.eshop.property.param.ReportAdPointParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.param.TransactionDetailQueryForBackageParam;
import com.lawu.eshop.property.srv.bo.AreaPointConsumeBO;
import com.lawu.eshop.property.srv.bo.IncomeMsgBO;
import com.lawu.eshop.property.srv.bo.PointConsumeReportBO;
import com.lawu.eshop.property.srv.bo.PointDetailBO;
import com.lawu.eshop.property.srv.bo.ReportAdPointGroupByAreaBO;
import com.lawu.eshop.property.srv.converter.PointDetailConverter;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author Sunny
 * @date 2017/3/30
 */
@RestController
@RequestMapping(value = "pointDetail/")
public class PointDetailController extends BaseController {

    @Autowired
    private PointDetailService pointDetailService;

    /**
     * 根据用户编号和查询参数查询交易明细
     * 
     * @param userNum 用户编号
     * @param pointDetailQueryParam 查询参数
     * @return
     */
    @RequestMapping(value = "findPageByUserNum/{userNum}", method = RequestMethod.POST)
    public Result<Page<PointDetailDTO>> findPageByUserNum(@PathVariable("userNum") String userNum, @RequestBody PointDetailQueryParam pointDetailQueryParam) {
    	
    	Page<PointDetailBO> pointDetailBOPage = pointDetailService.findPageByUserNum(userNum, pointDetailQueryParam);
    	
    	Page<PointDetailDTO> pointDetailDTOPage = PointDetailConverter.convertDTOPage(pointDetailBOPage);
    	pointDetailBOPage = null;
        return successGet(pointDetailDTOPage);
    }
    
    /**
     * 保存积分明细记录
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result save(@RequestBody PointDetailSaveDataParam param) {
		return successCreated(pointDetailService.save(param));
	}

    /**
     * 查询运营后台充值积分记录
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "getBackagePointPageList", method = RequestMethod.POST)
    public Result<Page<PointDetailBackageDTO>> getBackagePointPageList(@RequestBody TransactionDetailQueryForBackageParam param) {
        Page<PointDetailBO> pointDetailBOPage = pointDetailService.getBackagePointPageList(param);
        return successGet(PointDetailConverter.convertBackageDTOPage(pointDetailBOPage));
    }

    /**
     * 
     * @param param
     * @param result
     * @return
     * @author yangqh
     * @date 2017年6月30日 下午2:34:30
     */
    @RequestMapping(value = "selectPointDetailListByDateAndDirection", method = RequestMethod.POST)
	public Result<PointConsumeReportDTO> selectPointDetailListByDateAndDirection(@RequestBody @Valid PointDetailReportParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	PointConsumeReportBO bo = pointDetailService.selectPointDetailListByDateAndDirection(param);
		PointConsumeReportDTO dto = new PointConsumeReportDTO();
		dto.setMemberRechargeMoney(bo.getMemberRechargeMoney());
		dto.setMerchantRechargeMoney(bo.getMerchantRechargeMoney());
		dto.setSumRechargeMoney(bo.getSumRechargeMoney());
		return successCreated(dto);
	}
    
    @RequestMapping(value = "selectPointDetailListByDateAndDirectionAndPointType", method = RequestMethod.POST)
	public Result<PointConsumeReportDTO> selectPointDetailListByDateAndDirectionAndPointType(@RequestBody @Valid PointDetailReportParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	PointConsumeReportBO bo = pointDetailService.selectPointDetailListByDateAndDirectionAndPointType(param);
		PointConsumeReportDTO dto = new PointConsumeReportDTO();
		dto.setMemberRechargeMoney(bo.getMemberRechargeMoney());
		dto.setMerchantRechargeMoney(bo.getMerchantRechargeMoney());
		dto.setSumRechargeMoney(bo.getSumRechargeMoney());
		return successCreated(dto);
	}
    
    
    @RequestMapping(value = "getReportAdPointGroupByArea", method = RequestMethod.POST)
	public Result<List<ReportAdPointGroupByAreaDTO>> getReportAdPointGroupByArea(@RequestBody ReportAdPointParam param) {
    	List<ReportAdPointGroupByAreaBO> list = pointDetailService.getReportAdPointGroupByArea(param);
    	List<ReportAdPointGroupByAreaDTO> rtnList = new ArrayList<ReportAdPointGroupByAreaDTO>();
    	for(ReportAdPointGroupByAreaBO BO : list) {
    		ReportAdPointGroupByAreaDTO dto = new ReportAdPointGroupByAreaDTO();
    		dto.setAreaId(BO.getAreaId());
    		dto.setTotalPoint(BO.getTotalPoint());
    		rtnList.add(dto);
    	}
		return successCreated(rtnList);
	}
    
    @RequestMapping(value = "getAreaPointConsume", method = RequestMethod.POST)
    public Result<List<AreaPointConsumeDTO>> getAreaPointConsume(@RequestBody ReportAgentAreaPointParam param) {
        List<AreaPointConsumeBO> list = pointDetailService.getAreaPointConsume(param);
        List<AreaPointConsumeDTO> rtnList = new ArrayList<>();
        for(AreaPointConsumeBO BO : list) {
            AreaPointConsumeDTO dto = new AreaPointConsumeDTO();
            dto.setAreaId(BO.getAreaId());
            dto.setTotalPoint(BO.getTotalPoint());
            dto.setCityId(BO.getCityId());
            dto.setProvinceId(BO.getProvinceId());
            dto.setType(BO.getType());
            rtnList.add(dto);
        }
        return successCreated(rtnList);
    }
    
    @RequestMapping(value = "getAreaPointRefund", method = RequestMethod.POST)
    public Result<List<AreaPointConsumeDTO>> getAreaPointRefund(@RequestBody ReportAgentAreaPointParam param) {
        List<AreaPointConsumeBO> list = pointDetailService.getAreaPointRefund(param);
        List<AreaPointConsumeDTO> rtnList = new ArrayList<>();
        for(AreaPointConsumeBO BO : list) {
            AreaPointConsumeDTO dto = new AreaPointConsumeDTO();
            dto.setAreaId(BO.getAreaId());
            dto.setTotalPoint(BO.getTotalPoint());
            dto.setCityId(BO.getCityId());
            dto.setProvinceId(BO.getProvinceId());
            dto.setType(BO.getType());
            rtnList.add(dto);
        }
        return successCreated(rtnList);
    }

    @RequestMapping(value = "getIncomeMsgDataList", method = RequestMethod.GET)
    public Result<List<IncomeMsgDTO>> getIncomeMsgDataList(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        String date = DateUtil.getDateFormat(new Date(),"yyyy-MM-dd");
        String begin = date + " 00:00:00";
        String end = date + " 23:59:59";
        List<IncomeMsgBO> userIncomeExpenditureBOList = pointDetailService.getIncomeMsgDataList(begin,end,offset,pageSize);
        List<IncomeMsgDTO> dtos = new ArrayList<>();
        for(IncomeMsgBO bo : userIncomeExpenditureBOList){
            IncomeMsgDTO dto = new IncomeMsgDTO();
            dto.setUserNum(bo.getUserNum());
            dto.setMoney(bo.getMoney());
            dto.setType(bo.getType());
            dto.setMsgType(2);
            dtos.add(dto);
        }
        return successCreated(dtos);
    }

    @RequestMapping(value = "getIncomeMsgTotalDataList", method = RequestMethod.GET)
    public Result<List<IncomeMsgDTO>> getIncomeMsgTotalDataList() {
        String date = DateUtil.getDateFormat(new Date(),"yyyy-MM-dd");
        String begin = date + " 00:00:00";
        String end = date + " 23:59:59";
        List<IncomeMsgBO> userIncomeExpenditureBOList = pointDetailService.getIncomeMsgTotalDataList(begin,end);
        List<IncomeMsgDTO> dtos = new ArrayList<>();
        for(IncomeMsgBO bo : userIncomeExpenditureBOList){
            IncomeMsgDTO dto = new IncomeMsgDTO();
            dto.setUserNum(bo.getUserNum());
            dto.setMoney(bo.getMoney());
            dto.setPayTypeEnum(PayTypeEnum.POINT);
            dtos.add(dto);
        }
        return successCreated(dtos);
    }

    /**
     * 根据用户编号和业务ID查询是否存在记录
     *
     * @param userNum
     * @param bizId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "existsPointDetailByUserNumAndBizId", method = RequestMethod.GET)
    public Result<Boolean> existsPointDetailByUserNumAndBizId(@RequestParam String userNum, @RequestParam String bizId) {
        boolean result = pointDetailService.existsPointDetailByUserNumAndBizId(userNum, bizId);
        return successGet(result);
    }

}
