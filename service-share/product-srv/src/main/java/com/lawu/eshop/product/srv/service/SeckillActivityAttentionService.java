package com.lawu.eshop.product.srv.service;

import java.util.List;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.product.param.SeckillActivityProductAttentionParam;
import com.lawu.eshop.product.srv.bo.SeckillActivityAttentionBO;

/**
 * 抢购活动关注服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public interface SeckillActivityAttentionService {
    
    /**
     * 根据活动商品id添加关注记录以及增加商品关注人数
     * 
     * @param activityProductId 活动商品id
     * @param memberId 用户id
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    void attention(Long activityProductId, SeckillActivityProductAttentionParam param) throws DataNotExistException;
    
    /**
     * 分页查询<p>
     * 即将开始的秒杀活动的关注列表<p>
     * 用于发送用户提醒<p>
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    List<SeckillActivityAttentionBO> selectAboutStartSeckillActivityAttention(int offset, int pageSize);
    
    /**
     * 提示活动即将开始
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    void promptSeckillActivityAboutStart(SeckillActivityAttentionBO seckillActivityAttentionBO) throws DataNotExistException;
    
    /**
     * 是否已经关注过这件抢购商品
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月29日
     * @updateDate 2017年11月29日
     */
    Boolean isAttention(Long seckillActivityProductId, Long memberId);
}
