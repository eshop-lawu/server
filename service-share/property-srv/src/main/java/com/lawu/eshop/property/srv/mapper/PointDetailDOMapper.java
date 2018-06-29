package com.lawu.eshop.property.srv.mapper;

import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.eshop.property.srv.domain.PointDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PointDetailDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int countByExample(PointDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int deleteByExample(PointDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int insert(PointDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int insertSelective(PointDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    List<PointDetailDO> selectByExampleWithRowbounds(PointDetailDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    List<PointDetailDO> selectByExample(PointDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    PointDetailDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PointDetailDO record, @Param("example") PointDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PointDetailDO record, @Param("example") PointDetailDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PointDetailDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PointDetailDO record);
}