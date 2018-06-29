package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.GetPlatformRedPacketDTO;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.MessageService;
import com.lawu.eshop.member.api.service.PlatformRedPacketService;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：平台红包
 *
 * @author zhangrc
 * @date 2017/10/19
 */
@Api(tags = "platformRedPacket")
@RestController
@RequestMapping(value = "platformRedPacket/")
public class PlatformRedPacketController extends BaseController{
	
	
	@Autowired
	private PlatformRedPacketService platformRedPacketService;
	
	@Autowired
    private MemberService memberService;

    @Autowired
    private  MerchantStoreService merchantStoreService;
    
    @Autowired
    private MessageService  messageService;

	@Audit(date = "2017-10-20", reviewer = "杨清华")
	@ApiOperation(value = "领取平台红包", notes = "领取平台红包,[]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRedPacket", method = RequestMethod.POST)
    public Result<GetPlatformRedPacketDTO> getRedPacket(@RequestParam @ApiParam(required = true, value = "账号") String account,@RequestParam @ApiParam(required = true, value = "商家id") Long merchantId) {
		
		Result<MerchantStoreDTO> resultStore = merchantStoreService.selectMerchantStoreByMId(merchantId);
		
		if(resultStore.getModel()!=null){
			
			if(resultStore.getModel().getManageType().val!=ManageTypeEnum.ENTITY.getVal()){
				GetPlatformRedPacketDTO dto = new GetPlatformRedPacketDTO();
				dto.setEntity(false);
				return successCreated(dto);
			}
			
		}
		Result<MemberDTO> accountResult = memberService.getMemberByAccount(account);
		if(!isSuccess(accountResult)){
			return successCreated(accountResult.getRet());
		}
		Result<GetPlatformRedPacketDTO>  result =new Result<>() ;
		
		if(accountResult.getModel()!=null){
			 result = platformRedPacketService.getRedPacket(accountResult.getModel().getNum());
			 result.getModel().setEntity(true);
			 //领取成功发送消息
			 if(result.getModel().getMoney()!=null && result.getModel().getMoney().compareTo(BigDecimal.valueOf(0))==1){
				 MessageInfoParam messageInfoParam = new MessageInfoParam();
				 MessageTempParam messageTempParam = new MessageTempParam();
				 messageTempParam.setEarningAmount(result.getModel().getMoney());
				 messageInfoParam.setRelateId(result.getModel().getId());
				 messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PLAT_RED_PACKET);
				 messageInfoParam.setIsPush(false);
				 messageInfoParam.setMessageParam(messageTempParam);
					 
				 messageService.saveMessage(accountResult.getModel().getNum(), messageInfoParam); 
			 }
		}
		
		return result;
    }

	
}
