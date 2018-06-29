package com.lawu.eshop.member.ws.service;

import java.util.List;

import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.framework.web.Result;

public interface UserInfoService {
    
    /**
     * 通过用户编号集合<p>
     * 查找头脑PK所需要的用户信息
     * 
     * @param userNums
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    Result<List<GameUserInfoDTO>> findUserInfo(List<String> userNums);
    
}
