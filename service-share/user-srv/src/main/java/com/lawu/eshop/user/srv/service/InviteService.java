package com.lawu.eshop.user.srv.service;

/**
 * @author zhangyong
 * @date 2018/1/16.
 */
public interface InviteService {

    void updateInviteUserProfileInfo(String userNum,Long id);

    /**
     * 冻结用户, 逻辑删除用户上下三级推荐关系
     * @param userNum
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    void deleteRelationship(String userNum);

    /**
     * 解冻用户, 还原用户上下三级推荐关系
     * @param userNum
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    void restoreRelationship(String userNum);
}
