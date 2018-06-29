package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年1月24日
 * @updateDate 2018年1月24日
 */
public interface InviteRelationDOExtendMapper {
    
    /**
     * 根据当前用户编号删除上三级与下三级的关联 
     * @param num
     * @author jiangxinjun
     * @createDate 2018年1月24日
     * @updateDate 2018年1月24日
     */
    void deleteRelationship(@Param("num") String num);
    
    /**
     * 根据当前用户编号以及下级用户编号恢复关联 
     * @param num
     * @param lowerLevelNums
     * @author jiangxinjun
     * @createDate 2018年1月24日
     * @updateDate 2018年1月24日
     */
    void restoreRelationship(@Param("num") String num, @Param("lowerLevelNums") List<String> lowerLevelNums);
    
    /**
     * 根据用户编号查询下一级未被冻结的用户编号
     * @param userNums
     * @param depth
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月25日
     * @updateDate 2018年1月25日
     */
    List<String> lowerLevelNumByDepth(@Param("userNums") List<String> userNums);
}
