package com.lawu.eshop.product.srv.mapper;

import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.domain.ProductSpecDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProductSpecDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    long countByExample(ProductSpecDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int deleteByExample(ProductSpecDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int insert(ProductSpecDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int insertSelective(ProductSpecDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    List<ProductSpecDO> selectByExampleWithRowbounds(ProductSpecDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    List<ProductSpecDO> selectByExample(ProductSpecDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    ProductSpecDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ProductSpecDO record, @Param("example") ProductSpecDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ProductSpecDO record, @Param("example") ProductSpecDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductSpecDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_spec
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductSpecDO record);
}