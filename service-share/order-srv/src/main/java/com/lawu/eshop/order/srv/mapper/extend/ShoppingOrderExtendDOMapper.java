package com.lawu.eshop.order.srv.mapper.extend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.order.param.ShoppingOrderReportDataParam;
import com.lawu.eshop.order.srv.domain.extend.NotShippedDO;
import com.lawu.eshop.order.srv.domain.extend.ReportFansSaleTransFormDO;
import com.lawu.eshop.order.srv.domain.extend.ReportRiseRateView;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderExtendDOExample;
import com.lawu.eshop.order.srv.domain.extend.UpdateMerchantActualIncomeParam;

/**
 * 关联查询订单和订单项表
 * 
 * @author Sunny
 * @date 2017/04/07
 */
public interface ShoppingOrderExtendDOMapper {
	
	/**
	 * 分页查询订单表以及级联订单项表
	 * 
	 * @param example 查询参数
	 * @param rowBounds 分页参数
	 * @return
	 */
	List<ShoppingOrderExtendDO> selectByExampleWithRowbounds(ShoppingOrderExtendDOExample example, RowBounds rowBounds);
	
	/**
	 * 根据参数查询总记录数
	 * 订单表与订单项表关联会有重复记录，需要distinct操作
	 * 
	 * @param example 查询参数
	 * @return
	 */
    long countByExample(ShoppingOrderExtendDOExample example);
    
    /**
     * 根据id查询购物订单以及订单项
     * 
     * @param id 订单id
     * @return
     */
    ShoppingOrderExtendDO selectByPrimaryKey(Long id);
    
	/**
	 * 查询订单表
	 * 
	 * @param example 查询参数
	 * @return
	 */
	List<ShoppingOrderExtendDO> selectByExample(ShoppingOrderExtendDOExample example);
	
	/**
	 * 查询交易数据
	 * 
	 * @param example 查询参数
	 * @return
	 */
    List<ReportRiseRateView> selectByTransactionData(ShoppingOrderReportDataParam param);
    
	/**
	 * 查询交易数据
	 * 
	 * @param example 查询参数
	 * @return
	 */
    BigDecimal selectByTransactionTotalAmount(ShoppingOrderReportDataParam param);
    
	/**
	 * 查询交易数据
	 * 
	 * @param example 查询参数
	 * @return
	 */
    List<ReportFansSaleTransFormDO> selectByFansSaleTransForm(ShoppingOrderReportDataParam param);
    
	/**
	 * 根据商家编号分组查询待发货的数量
	 * 
	 * @param time 提醒买家发货时间
	 * @return
	 */
    List<NotShippedDO> selectByNotShipped(Date time);
    
	/**
	 * 根据查询条件查询订单id
	 * 
	 * @param example 查询参数
	 * @return
	 */
	List<Long> selectIdByExample(ShoppingOrderExtendDOExample example, RowBounds rowBounds);
	
	/**
	 * 更新商家实际收入金额
	 * 
	 * @param param
	 * @author jiangxinjun
	 * @createDate 2017年11月13日
	 * @updateDate 2017年11月13日
	 */
	void updateMerchantActualIncome(UpdateMerchantActualIncomeParam param);
}