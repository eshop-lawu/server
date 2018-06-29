package com.lawu.eshop.product.srv.service;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.product.param.SeckillActivityProductAuditParam;
import com.lawu.eshop.product.param.SeckillActivityProductNotPassedParam;
import com.lawu.eshop.product.param.SeckillActivityProductPageQueryParam;
import com.lawu.eshop.product.param.SeckillActivityProductPageSearchParam;
import com.lawu.eshop.product.param.SeckillActivityProductUpdateParam;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductExtendBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductPartBO;
import com.lawu.eshop.product.srv.bo.ShareSeckillActivityProductBO;
import com.lawu.framework.core.page.Page;

/**
 * 抢购活动商品服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public interface SeckillActivityProductService {

    /**
     * 根据抢购活动id和查询参数分页查询抢购活动商品列表
     * 用于用户端
     * 
     * @param id 抢购活动id
     * @param param 分页查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    Page<SeckillActivityProductBO> page(Long id, SeckillActivityProductPageQueryParam param);
    
    /**
     * 根据抢购活动商品id查询活动资料以及商品型号库存信息
     * 用于会员端
     * 
     * @param id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月24日
     */
     SeckillActivityProductExtendBO information(Long id) throws DataNotExistException;
     
     /**
      * 根据抢购活动id和查询参数分页查询抢购活动商品列表
      * 用于用户端
      * 
      * @param id 抢购活动id
      * @param param 分页查询参数
      * @return
      * @author jiangxinjun
      * @createDate 2017年11月27日
      * @updateDate 2017年11月27日
      */
     Page<SeckillActivityProductBO> page(Long id, SeckillActivityProductPageSearchParam param);
     
     /**
      * 审核抢购活动商品
      * 
      * @param id 抢购活动商品id
      * @return
      * @author jiangxinjun
      * @createDate 2017年11月27日
      * @updateDate 2017年11月27日
      */
     void audit(Long id, SeckillActivityProductAuditParam param) throws DataNotExistException, WrongOperationException;
     
     /**
      * 抢购活动商品
      * 审核不通过
      * 
      * @param id 抢购活动商品id
      * @param param 反馈参数
      * @return
      * @author jiangxinjun
      * @createDate 2017年11月27日
      * @updateDate 2017年11月27日
      */
     void notPassed(Long id, SeckillActivityProductNotPassedParam param) throws DataNotExistException, WrongOperationException;
     
     /**
      * 查询抢购商品型号库存
      * 
      * @param id 抢购活动商品型号id
      * @throws DataNotExistException 数据不存在
      * @author jiangxinjun
      * @createDate 2017年11月30日
      * @updateDate 2017年11月30日
      */
     Integer getInventory(Long seckillActivityProductModelId) throws DataNotExistException, WrongOperationException;

    /**
     * 趣乐购首页三件商品
     *
     * @return
     * @author meishuquan
     */
     SeckillActivityProductPartBO getRecommendSeckillActivityProduct();

    /**
     * 根据分享消息查询抢购活动商品信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    ShareSeckillActivityProductBO getShareSeckillActivityProduct(Long id);
    
    /**
     * 更新抢购活动商品
     * @param activityProductId
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月23日
     * @updateDate 2018年5月23日
     */
    void update(Long activityProductId, SeckillActivityProductUpdateParam param);
}
