package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.IdentityInfoDTO;
import com.lawu.eshop.activity.dto.PersonalRichAccountDTO;
import com.lawu.eshop.activity.dto.RichDetailDTO;
import com.lawu.eshop.activity.dto.RichMyDiamondRecordInfoDTO;
import com.lawu.eshop.activity.dto.RichPowerInfoRecordDTO;
import com.lawu.eshop.activity.param.ReceiveDiamondsParam;
import com.lawu.eshop.activity.srv.bo.IdentityInfoBO;
import com.lawu.eshop.activity.srv.bo.PersonalRichAccountBO;
import com.lawu.eshop.activity.srv.bo.RichDetailBO;
import com.lawu.eshop.activity.srv.bo.RichMyDiamondRecordInfoBO;
import com.lawu.eshop.activity.srv.bo.RichPowerInfoRecordBO;
import com.lawu.eshop.activity.srv.converter.RichAccountConvert;
import com.lawu.eshop.activity.srv.servcie.RichAccountService;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author lihj
 * @date 2018/5/2
 */
@RestController
@RequestMapping(value = "richAccount/")
public class RichAccountController extends BaseController{
    @Autowired
    private RichAccountService richAccountService;

    /**
     * 获取首页个人E钻信息
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getPersonalRichAccountInfo",method = RequestMethod.GET)
    public Result<PersonalRichAccountDTO> getPersonalRichAccountInfo(@RequestParam String userNum,@RequestParam UserTypeEnum userType){
        if(null ==userNum){
            return successGet(new PersonalRichAccountDTO());
        }else{
            PersonalRichAccountBO bo = richAccountService.getPersonalRichAccountInfo(userNum,userType);
            PersonalRichAccountDTO dto = RichAccountConvert.convertPersonalRichAccountDTO(bo);
            return successGet(dto);
        }
    }
    
    /**
     * 获取首页瑞奇岛信息
     * @return
     */
    @RequestMapping(value = "getRichInfo",method = RequestMethod.GET)
    public Result<RichDetailDTO> getRichInfo(@RequestParam UserTypeEnum userType){
    	RichDetailBO rich = richAccountService.getRichInfo(userType);
    	RichDetailDTO dto = RichAccountConvert.convertRichDetailDTO(rich);
    	return successGet(dto);
    }
    
    /**
     * 获取瑞奇岛当前动力值以及动力收支明细TOP10
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getRichPowerInfoRecord",method = RequestMethod.GET)
    public Result<RichPowerInfoRecordDTO> getRichPowerInfoRecord(@RequestParam("userNum") String userNum){
    	RichPowerInfoRecordBO richBo = richAccountService.getRichPowerInfoRecord(userNum);
    	RichPowerInfoRecordDTO dto = RichAccountConvert.convertRichPowerInfoRecordDTO(richBo);
    	return successGet(dto);
    }
    
    /**
     * 我的数字资产(E钻总数和领取记录TOP10)
     * @param userNum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "getMyRichDiamondRecordInfo")
    public Result<RichMyDiamondRecordInfoDTO> getMyRichDiamondRecordInfo(@RequestParam("userNum") String userNum){
    	RichMyDiamondRecordInfoBO bo = richAccountService.getMyRichDiamondRecordInfo(userNum);
    	RichMyDiamondRecordInfoDTO dto =RichAccountConvert.convertRichMyDiamondRecordInfoDTO(bo);
    	return successGet(dto);
    }
    
    /**
     * 我的身份
     * 
     * @param memberNum
     * @return
     */
    @RequestMapping(value = "getIdentityInfo",method = RequestMethod.GET)
    public Result<IdentityInfoDTO> getIdentityInfo(@RequestParam("memberNum") String memberNum){
    	IdentityInfoBO richBo = richAccountService.getIdentityInfo(memberNum);
    	IdentityInfoDTO dto = new IdentityInfoDTO();
    	dto.setIdCardNum(richBo.getIdCardNum());
    	dto.setName(richBo.getName());
    	return successGet(dto);
    }
    
    /**
     * 领取钻石
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月4日
     * @updateDate 2018年5月4日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.PUT, value = "receiveDiamonds")
    public Result receiveDiamonds(@RequestParam("memberNum") String memberNum,
            @RequestBody @Validated ReceiveDiamondsParam param) {
        try {
            richAccountService.receiveDiamonds(memberNum, param);
            return successCreated();
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (IllegalOperationException e) {
            return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.PUT, value = "bindAlipayInfo")
    public Result bindAlipayInfo(@RequestParam("memberNum") String memberNum, @RequestParam("alipayUserId") String alipayUserId) {
        richAccountService.bindAlipayInfo(memberNum,alipayUserId);
        return successCreated();
    }
}
