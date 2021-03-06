package com.lawu.eshop.product.srv.mapper;

import com.lawu.eshop.product.srv.domain.ProductModelInventoryDO;
import com.lawu.eshop.product.srv.domain.ProductModelInventoryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProductModelInventoryDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int countByExample(ProductModelInventoryDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int deleteByExample(ProductModelInventoryDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int insert(ProductModelInventoryDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int insertSelective(ProductModelInventoryDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    List<ProductModelInventoryDO> selectByExampleWithRowbounds(ProductModelInventoryDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    List<ProductModelInventoryDO> selectByExample(ProductModelInventoryDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    ProductModelInventoryDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ProductModelInventoryDO record, @Param("example") ProductModelInventoryDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ProductModelInventoryDO record, @Param("example") ProductModelInventoryDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductModelInventoryDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_model_inventory
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductModelInventoryDO record);
}