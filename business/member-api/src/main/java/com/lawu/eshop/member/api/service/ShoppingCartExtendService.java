package com.lawu.eshop.member.api.service;

import java.util.List;

import com.lawu.concurrentqueue.bizctrl.BusinessExecuteException;
import com.lawu.eshop.order.dto.foreign.ShoppingCartQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingCartSettlementDTO;
import com.lawu.eshop.order.param.ShoppingCartParam;
import com.lawu.eshop.order.param.foreign.BatchCreateOrderParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderBuyNowCreateOrderForeignParam;
import com.lawu.framework.web.Result;

/**
 * 购物车服务接口
 * 
 * @author Sunny
 * @date 2017/04/06
 */
public interface ShoppingCartExtendService {
	
	/**
	 * 保存到购物车
	 * 
	 * @param param
	 * @return
	 */
	Result<Long> save(Long memberId, ShoppingCartParam param);
	
	/**
	 * 根据memberId查询用户的购物车列表。
	 * 
	 * @param memberId 会员id
	 * @return
	 */
	Result<ShoppingCartQueryDTO> findListByMemberId(Long memberId);
	
	/**
	 * 根据购物车id列表生成结算数据
	 * 
	 * @param idList 购物车id列表
	 * @param memberNum 用户编号
	 * @return 返回结算数据
	 */
	Result<ShoppingCartSettlementDTO> settlement(List<Long> idList, String memberNum, Long memberId);
	
	/**
	 * 根据结算参数列表批量创建订单
	 * 
	 * @param params 订单参数列表
	 * @return 返回订单的id列表
	 */
	Result<List<Long>> createOrder(Long memberId, BatchCreateOrderParam param, String memberNum);
	
	/**
	 * 立即购买
	 * @param param 购物参数
	 * @return 返回订单的结算数据
	 */
	Result<ShoppingCartSettlementDTO> buyNow(Long memberId, String memberNum, ShoppingCartParam param);
	
	/**
	 * 立即购买,创建订单
	 * @param memberId 用户id
	 * @param param 创建订单参数
	 * @return
	 * @author Sunny
	 */
	Result<Long> buyNowCreateOrder(Long memberId, ShoppingOrderBuyNowCreateOrderForeignParam param, String memberNum);
	
	/**
	 * 立即抢购活动商品，创建订单
	 * 
	 * @param memberId 用户id
	 * @param param 创建订单参数
	 * @param seckillActivityProductModelId 秒杀活动商品型号id
	 * @return
	 * @author jiangxinjun
	 * @createDate 2017年11月30日
	 * @updateDate 2017年11月30日
	 */
    Result<Long> buyNowCreateOrder(Long memberId, ShoppingOrderBuyNowCreateOrderForeignParam param, Long seckillActivityProductModelId, String memberNum) throws BusinessExecuteException;
}
