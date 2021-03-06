package com.lawu.eshop.user.srv.mapper;

import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.InviteRelationDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface InviteRelationDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int countByExample(InviteRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int deleteByExample(InviteRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int insert(InviteRelationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int insertSelective(InviteRelationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    List<InviteRelationDO> selectByExampleWithRowbounds(InviteRelationDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    List<InviteRelationDO> selectByExample(InviteRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    InviteRelationDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") InviteRelationDO record, @Param("example") InviteRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") InviteRelationDO record, @Param("example") InviteRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InviteRelationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InviteRelationDO record);
}