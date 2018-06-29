package com.lawu.eshop.product.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.property.DownProductNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductDOExample;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 款成功操作后，发送消息修改下架 - 从事务
 *
 * @author yangqh
 * @date 2017/8/16
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_DEPOSIT_DOWN_PRODUCT)
public class RefundDepositRefundSuccessDownProductTransactionFollowServiceImpl extends AbstractTransactionFollowService<DownProductNotification, Reply> {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDOMapper productDOMapper;

    /**
     * 下架商品
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(DownProductNotification notification) {
        ProductDOExample example = new ProductDOExample();
        example.createCriteria().andMerchantIdEqualTo(notification.getMerchantId()).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
        List<ProductDO> productDOList = productDOMapper.selectByExample(example);
        if(productDOList == null || productDOList.isEmpty()){
            return;
        }
        StringBuilder ids = new StringBuilder();
        for(ProductDO product : productDOList){
            ids.append(product.getId()).append(",");
        }
        String idsStr = ids.toString();
        idsStr = idsStr.substring(0,idsStr.length()-1);
        productService.updateProductStatus(idsStr,ProductStatusEnum.PRODUCT_STATUS_DOWN,notification.getMerchantId(),null);
    }
}
