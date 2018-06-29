package com.lawu.eshop.user.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.dto.FansMerchantDTO;
import com.lawu.eshop.user.dto.FansMerchantQueryDTO;
import com.lawu.eshop.user.param.FansMerchantQueryParam;
import com.lawu.eshop.user.param.FensCountQuery;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.bo.FansMerchantQueryBO;
import com.lawu.eshop.user.srv.converter.FansMerchantConverter;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
@RestController
@RequestMapping(value = "fansMerchant/")
public class FansMerchantController extends BaseController {

	@Autowired
	private FansMerchantService fansMerchantService;

	/**
	 * 查询可邀请的会员
	 *
	 * @param merchantId
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "listInviteFans/{merchantId}", method = RequestMethod.POST)
	public Result<List<FansMerchantDTO>> listInviteFans(@PathVariable Long merchantId, @RequestBody ListInviteFansParam param) {
		List<FansMerchantBO> fansMerchantBOS = fansMerchantService.listInviteFans(merchantId, param);
		if (fansMerchantBOS == null || fansMerchantBOS.isEmpty()) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		return successGet(FansMerchantConverter.convertDTO(fansMerchantBOS));
	}

	/**
	 * 查询可邀请的会员
	 *
	 * @param merchantId
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "listInviteFansWithContent/{merchantId}", method = RequestMethod.POST)
	public Result<List<FansMerchantDTO>> listInviteFans(@PathVariable Long merchantId, @RequestBody ListInviteFansWithContentParam param) {
		List<FansMerchantBO> fansMerchantBOS = fansMerchantService.listInviteFansWithContent(merchantId, param);
		if (fansMerchantBOS == null || fansMerchantBOS.isEmpty()) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		return successGet(FansMerchantConverter.convertDTO(fansMerchantBOS));
	}

	/**
	 * 分页查询可邀请的会员
	 *
	 * @param merchantId
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "pageListInviteFans/{merchantId}", method = RequestMethod.POST)
	public Result<Page<FansMerchantDTO>> pageListInviteFans(@PathVariable Long merchantId, @RequestBody PageListInviteFansParam param) {
		Page<FansMerchantBO> fansMerchantBOPage = fansMerchantService.pageListInviteFans(merchantId, param);
		Page<FansMerchantDTO> page = new Page<>();
		page.setCurrentPage(fansMerchantBOPage.getCurrentPage());
		page.setTotalCount(fansMerchantBOPage.getTotalCount());
		page.setRecords(FansMerchantConverter.convertDTO(fansMerchantBOPage.getRecords()));
		return successGet(page);
	}

	/**
	 * 粉丝列表
	 *
	 * @param merchantId
	 * @param listFansParam
	 * @return
	 */
	@RequestMapping(value = "listFans/{merchantId}", method = RequestMethod.POST)
	public Result<Page<FansMerchantDTO>> listFans(@PathVariable Long merchantId, @RequestBody ListFansParam listFansParam) {
		Page<FansMerchantBO> fansMerchantBOPage = fansMerchantService.listFans(merchantId, listFansParam);
		Page<FansMerchantDTO> page = new Page<>();
		page.setCurrentPage(fansMerchantBOPage.getCurrentPage());
		page.setTotalCount(fansMerchantBOPage.getTotalCount());
		page.setRecords(FansMerchantConverter.convertDTO(fansMerchantBOPage.getRecords()));
		return successGet(page);
	}

	/**
	 * 查询会员是否是商家粉丝
	 *
	 * @param memberId
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "isFansMerchant/{merchantId}", method = RequestMethod.GET)
	public Result<Boolean> isFansMerchant(@PathVariable Long merchantId, @RequestParam Long memberId) {
		FansMerchantBO fansMerchantBO = fansMerchantService.getFansMerchant(memberId, merchantId);
		if (fansMerchantBO == null) {
			return successGet(false);
		}
		return successGet(true);
	}

	/**
	 * 查询商家粉丝数量
	 *
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "countFans/{merchantId}", method = RequestMethod.GET)
	public Result<Integer> countFans(@PathVariable Long merchantId) {
		return successGet(fansMerchantService.findFensCount(merchantId));
	}

	/**
	 * 粉丝列表
	 *
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "findMerchant", method = RequestMethod.GET)
	public List<Long> listFans(@RequestParam Long memberId) {
		List<Long> merchantIds = fansMerchantService.findMerchant(memberId);
		return merchantIds;
	}

	/**
	 * 当前用户属于的商家
	 *
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "findFensCount", method = RequestMethod.POST)
	public Integer findFensCount(@RequestBody FensCountQuery query) {
		Integer count = fansMerchantService.findMerchantFensCount(query);
		return count;
	}

	/**
	 * 成为商家粉丝
	 *
	 * @param merchantId
	 * @param memberId
	 * @param channelEnum
	 * @return
	 */
	@RequestMapping(value = "saveFansMerchant/{merchantId}", method = RequestMethod.PUT)
	public Result saveFansMerchant(@PathVariable Long merchantId, @RequestParam Long memberId,
			@RequestParam FansMerchantChannelEnum channelEnum) {
		fansMerchantService.saveFansMerchant(merchantId, memberId, channelEnum);
		return successCreated();
	}

	/**
	 * 取消成为商家粉丝
	 * 
	 * @param merchantId
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "removeFansMerchant/{merchantId}", method = RequestMethod.PUT)
	public Result removeFansMerchant(@PathVariable Long merchantId, @RequestParam Long memberId) {
		fansMerchantService.removeFansMerchant(merchantId, memberId);
		return successCreated();
	}

	/**
	 * 用户处理粉丝邀请
	 *
	 * @param merchantId
	 * @param memberId
	 * @param dealWay
	 * @return
	 */
	@RequestMapping(value = "saveFansMerchantFromInvite/{merchantId}", method = RequestMethod.PUT)
	public Result saveFansMerchantFromInvite(@PathVariable Long merchantId, @RequestParam Long memberId, @RequestParam Long messageId,
			@RequestParam Boolean dealWay) {
		fansMerchantService.saveFansMerchantFromInvite(merchantId, memberId, messageId, dealWay);
		return successCreated();
	}

	/**
	 * 查询关注的店铺总数
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "getAttentionMerchantCount", method = RequestMethod.GET)
	public Result<Integer> getAttentionMerchantCount(@RequestParam("memberId") Long memberId) {
		Integer count = fansMerchantService.getAttentionMerchantCount(memberId);
		return successGet(count);
	}

	@RequestMapping(value = "getFansMerchantList", method = RequestMethod.POST)
	public Result<Page<FansMerchantQueryDTO>> getFansMerchantList(@RequestParam Long memberId,
			@RequestBody FansMerchantQueryParam pageQuery) {
		Page<FansMerchantQueryBO> pageBO = fansMerchantService.getFansMerchantList(memberId, pageQuery);
		List<FansMerchantQueryBO> list = pageBO.getRecords();
		List<FansMerchantQueryDTO> listDTO = new ArrayList<>();
		for (FansMerchantQueryBO favoriteMerchantBO : list) {
			FansMerchantQueryDTO dto = FansMerchantConverter.convertFansMerchantQueryDTO(favoriteMerchantBO);
			listDTO.add(dto);
		}
		Page<FansMerchantQueryDTO> page = new Page<FansMerchantQueryDTO>();
		page.setCurrentPage(pageBO.getCurrentPage());
		page.setTotalCount(pageBO.getTotalCount());
		page.setRecords(listDTO);
		return successGet(page);
	}
}
