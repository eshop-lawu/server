/**
 * 
 */
package com.lawu.eshop.member.api.service;

import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.UserRedPacketAddReturnDTO;
import com.lawu.eshop.ad.dto.UserRedPacketDTO;
import com.lawu.eshop.ad.dto.UserRedpacketMaxMoneyDTO;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectNumParam;

/**
 * @author lihj
 * @date 2017年8月3日
 */
@FeignClient(value = "ad-srv")
public interface UserRedPacketService {

	/**
	 * 保存新增红包记录
	 * 
	 * @param saveParam
	 * @return
	 */
	@RequestMapping(value = "userRedPacket/addUserRedPacket", method = RequestMethod.POST)
	Result<UserRedPacketAddReturnDTO> addUserRedPacket(@RequestBody UserRedPacketSaveParam saveParam);

	/**
	 * 查询用户红包列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value="userRedPacket/selectUserRedPacketList",method=RequestMethod.POST)
	Result<Page<UserRedPacketDTO>> selectUserRedPacketList(@RequestBody UserRedPacketSelectNumParam param);

	/**
	 * 判断是否还有红包可以领取
	 * @param redPacketId
	 * @return
	 */
	@RequestMapping(value="userRedPacket/isExistsRedPacket/{redPacketId}",method=RequestMethod.GET)
	Result<IsExistsRedPacketDTO> isExistsRedPacket(@PathVariable("redPacketId") Long redPacketId);

	/**
	 * 领取红包接口
	 * @param redPacketId 红包id
	 * @param userNum 领取人num
	 * @return
	 */
	@RequestMapping(value="userRedPacket/getUserRedpacketMoney",method=RequestMethod.POST)
	Result<UserRedpacketMaxMoneyDTO> getUserRedpacketMoney(@RequestParam("redPacketId") Long redPacketId, @RequestParam("userNum") String userNum);

	/**
	 * 
	 * @param redPacketId
	 * @return
	 */
	@RequestMapping(value="userRedPacket/getUserRedpacketMaxMoney",method=RequestMethod.POST)
	Result<UserRedpacketMaxMoneyDTO> getUserRedpacketMaxMoney(@RequestParam("redPacketId") Long redPacketId);

	/**
	 * 获取需要充值的金额
	 *
	 * @param redPacketId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "userRedPacket/selectUserRedPacketInfoForThrid")
	Result<ThirdPayCallBackQueryPayOrderDTO> selectUserRedPacketInfoForThrid(@RequestParam("redPacketId") Long redPacketId);
}
