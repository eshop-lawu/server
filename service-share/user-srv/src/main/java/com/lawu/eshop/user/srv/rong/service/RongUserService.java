package com.lawu.eshop.user.srv.rong.service;

import com.lawu.eshop.user.srv.rong.models.CheckOnlineResult;
import com.lawu.eshop.user.srv.rong.models.CodeSuccessResult;
import com.lawu.eshop.user.srv.rong.models.HistoryMessageResult;
import com.lawu.eshop.user.srv.rong.models.TokenResult;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
public interface RongUserService {

    /**
     * 获取token
     * @param userId
     * @param name
     * @param portraitUri
     * @return
     * @throws Exception
     */
    TokenResult getRongToken(String userId, String name, String portraitUri);

    /**
     * 刷新用户信息
     * @param userId
     * @param name
     * @param portraitUri
     * @return
     * @throws Exception
     */
    CodeSuccessResult refreshUserInfo(String userId, String name, String portraitUri);

    /**
     * 检查用户在线状态
     * @param userId
     * @return
     * @throws Exception
     */
    CheckOnlineResult checkOnline(String userId);

    HistoryMessageResult getHistory(String s);
}
