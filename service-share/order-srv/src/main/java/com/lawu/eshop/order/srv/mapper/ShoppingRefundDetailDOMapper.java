package com.lawu.eshop.order.srv.mapper;

import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ShoppingRefundDetailDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    long countByExample(ShoppingRefundDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int deleteByExample(ShoppingRefundDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int insert(ShoppingRefundDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int insertSelective(ShoppingRefundDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    List<ShoppingRefundDetailDO> selectByExampleWithRowbounds(ShoppingRefundDetailDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    List<ShoppingRefundDetailDO> selectByExample(ShoppingRefundDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    ShoppingRefundDetailDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ShoppingRefundDetailDO record, @Param("example") ShoppingRefundDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ShoppingRefundDetailDO record, @Param("example") ShoppingRefundDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShoppingRefundDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_refund_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShoppingRefundDetailDO record);
}