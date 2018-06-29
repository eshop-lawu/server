package com.lawu.eshop.beh.analyze.srv.service;

import com.lawu.eshop.beh.analyze.srv.bo.InviteAbnormalDecideRecordBO;

/**
 * 注册异常判定记录服务接口
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
public interface InviteAbnormalDecideRecordService {
    
    /**
     * 根据id查询注册异常判定记录
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    InviteAbnormalDecideRecordBO get(Long id);
    
    /**
     * 标识为不处理
     * 
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    void notProcessed(Long id);
    
    /**
     * 标识为冻结
     * 
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    void freeze(Long id);
}
