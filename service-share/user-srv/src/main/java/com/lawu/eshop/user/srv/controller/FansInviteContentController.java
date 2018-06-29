package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.user.constants.FansInviteResultEnum;
import com.lawu.eshop.user.dto.FansInviteContentDTO;
import com.lawu.eshop.user.param.FansInviteContentExtendParam;
import com.lawu.eshop.user.srv.bo.FansInviteContentBO;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.converter.FansInviteContentConverter;
import com.lawu.eshop.user.srv.service.FansInviteContentService;
import com.lawu.eshop.user.srv.service.FansInviteResultService;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;
import com.lawu.eshop.user.srv.service.MerchantStoreProfileService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "fansInviteContent/")
public class FansInviteContentController extends BaseController {

	@Autowired
	private FansInviteContentService fansInviteContentService;
	
	@Autowired
	private FansInviteResultService fansInviteResultService;
	
	@Autowired
	private MerchantStoreInfoService merchantStoreInfoService;
	
	@Autowired
	private MerchantStoreProfileService merchantStoreProfileService;

	@Autowired
	private FansMerchantService fansMerchantService;
	
	/**
	 * 保存邀请内容
	 * @param param
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveInviteContentService", method = RequestMethod.POST)
    public Result listInviteFans(@RequestBody FansInviteContentExtendParam param) {
    	Long i = fansInviteContentService.saveInviteContentService(param);
		return successCreated((Object)i);
    }
    
    /**
	 * 保存邀请内容
	 * @param param
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveInviteContentExtendService", method = RequestMethod.POST)
    public Result saveInviteContentExtendService(@RequestBody FansInviteContentExtendParam param) {
    	Long i = fansInviteContentService.saveInviteContentExtendService(param);
		return successCreated((Object)i);
    }
    
    /**
     * 查询邀请内容
     * @param id
     * @return
     */
    @RequestMapping(value = "selectInviteContentById/{id}/{relateId}", method = RequestMethod.POST)
    public Result<FansInviteContentDTO> selectInviteContentById(@PathVariable("id") Long id, @PathVariable("relateId") Long relateId, @RequestParam Long memberId) {
    	FansInviteContentBO bo = fansInviteContentService.selectInviteContentById(relateId);
    	FansInviteContentDTO dto = FansInviteContentConverter.converterBoToDto(bo);
    	int i = fansInviteResultService.selectInviteResultById(id);
    	dto.setFansInviteResultEnum(FansInviteResultEnum.getEnum((byte)i));
    	MerchantStoreInfoBO merchantStoreInfoBO = merchantStoreInfoService.selectMerchantStoreByMId(dto.getMerchantId());
    	MerchantStoreProfileBO profileBO = merchantStoreProfileService.findMerchantStoreInfo(dto.getMerchantId());
		FansMerchantBO fansMerchantBO = fansMerchantService.getFansMerchant(memberId, bo.getMerchantId());
		dto.setManageTypeEnum(ManageTypeEnum.getEnum(profileBO.getManageType()));
    	dto.setMerchantStoreId(merchantStoreInfoBO.getMerchantStoreId());
		dto.setIsFans(fansMerchantBO != null);
		return successGet(dto);
    }

	/**
	 * 处理过期的粉丝邀请(更改过期状态，删除粉丝记录，退还商家积分)
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "dealOverdueFansInvite", method = RequestMethod.GET)
	public Result dealOverdueFansInvite(@RequestParam Integer pageSize) {
		fansInviteContentService.dealOverdueFansInvite(pageSize);
		return successGet();
	}
}
