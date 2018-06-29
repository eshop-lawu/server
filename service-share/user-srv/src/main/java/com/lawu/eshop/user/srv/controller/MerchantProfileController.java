package com.lawu.eshop.user.srv.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.user.dto.AbnormalAccountDTO;
import com.lawu.eshop.user.dto.MerchantInfoFromInviteFansDTO;
import com.lawu.eshop.user.dto.MerchantInfoFromPublishAdDTO;
import com.lawu.eshop.user.dto.MerchantProfileDTO;
import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.eshop.user.srv.bo.AbnormalAccountBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoFromInviteFansBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoFromPublishAdBO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.converter.MemberProfileConverter;
import com.lawu.eshop.user.srv.converter.MerchantProfileConverter;
import com.lawu.eshop.user.srv.service.MerchantProfileService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/03/24
 *
 */
@RestController
@RequestMapping(value = "merchantProfile/")
public class MerchantProfileController extends BaseController{
	
	@Resource
	private  MerchantProfileService merchantProfileService;
	
   
   @RequestMapping(value = "getMerchantProfile", method = RequestMethod.GET)
   public Result<MerchantProfileDTO> getMerchantProfile(@RequestParam Long merchantId){
	   MerchantProfileBO merchantProfileBO=merchantProfileService.getMerchantProfile(merchantId);
	   return successGet(MerchantProfileConverter.convertDTO(merchantProfileBO));
   }

   
   @RequestMapping(value = "getMerchantInfoFromPublishAd/{merchantId}", method = RequestMethod.POST)
   public Result<MerchantInfoFromPublishAdDTO> getMerchantInfoFromPublishAd(@PathVariable("merchantId") Long merchantId){
	   MerchantInfoFromPublishAdBO merchantInfoFromPublishAdBO=merchantProfileService.getMerchantInfoFromPublishAd(merchantId);
	   MerchantInfoFromPublishAdDTO dto = new MerchantInfoFromPublishAdDTO();
	   dto.setJdUrl(merchantInfoFromPublishAdBO.getJdUrl());
	   dto.setLogoUrl(merchantInfoFromPublishAdBO.getLogoUrl());
	   dto.setStoreName(merchantInfoFromPublishAdBO.getStoreName());
	   dto.setTaobaoUrl(merchantInfoFromPublishAdBO.getTbUrl());
	   dto.setTmallUrl(merchantInfoFromPublishAdBO.getTmUrl());
	   dto.setWebsiteUrl(merchantInfoFromPublishAdBO.getWebsiteUrl());
	   return successGet(dto);
   }
   
   
   @RequestMapping(value = "getMerchantInfoFromInviteFans/{merchantId}", method = RequestMethod.GET)
   public Result<MerchantInfoFromInviteFansDTO> getMerchantInfoFromInviteFans(@PathVariable("merchantId") Long merchantId){
	   MerchantInfoFromInviteFansBO merchantInfoFromInviteFansBO=merchantProfileService.getMerchantInfoFromInviteFans(merchantId);
	   MerchantInfoFromInviteFansDTO dto = new MerchantInfoFromInviteFansDTO();
	   dto.setMerchantStoreIntro(merchantInfoFromInviteFansBO.getMerchantStoreIntro());
	   dto.setMerchantStoreLogo(merchantInfoFromInviteFansBO.getMerchantStoreLogo());
	   dto.setMerchantStoreName(merchantInfoFromInviteFansBO.getMerchantStoreName());
	   dto.setMerchantStoreUrl(merchantInfoFromInviteFansBO.getMerchantStoreUrl());
	   dto.setMerchantStoreId(merchantInfoFromInviteFansBO.getMerchantStoreId());
	   return successGet(dto);
   }

	@RequestMapping(value = "merchantSameIpCount/{id}", method = RequestMethod.GET)
	public Result<List<Integer>> merchantSameIpCount(@PathVariable("id") Long id, @RequestParam("type") Byte type) {
		return successGet(merchantProfileService.merchantSameIpCount(id,type));
	}

	@RequestMapping(value = "abnormalMerchantList", method = RequestMethod.POST)
	public Result<List<AbnormalAccountDTO>> abnormalMerchantList(@RequestBody AbnormalJobParam param){
		List<AbnormalAccountBO> accountBOS = merchantProfileService.abnormalMerchantList(param);
		return successGet(MemberProfileConverter.convertAbnormalDTOS(accountBOS));
	}

	@RequestMapping(value = "inviteMerchantTotalCount/{id}", method = RequestMethod.GET)
	public Integer inviteMerchantTotalCount(@PathVariable("id") Long id, @RequestParam("type") Byte type) {
		return merchantProfileService.inviteMerchantTotalCount(id, type);
	}

	/**
	 * 更新为已助力瑞奇岛动力任务
	 *
	 * @param userNum
	 * @author meishuquan
	 */
	@RequestMapping(value = "updateHelpRichTask", method = RequestMethod.PUT)
	public Result updateHelpRichTask(@RequestParam String userNum) {
		merchantProfileService.updateHelpRichTask(userNum);
		return successCreated();
	}
   
}
