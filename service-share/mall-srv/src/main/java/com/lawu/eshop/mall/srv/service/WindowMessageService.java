package com.lawu.eshop.mall.srv.service;

import java.util.List;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.param.WindowMessageParam;
import com.lawu.eshop.mall.query.OperatorWindowMessageQuery;
import com.lawu.eshop.mall.srv.bo.WindowMessageBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public interface WindowMessageService {

    /**
     * 添加弹窗消息
     *
     * @param param
     * @author meishuquan
     */
    void saveWindowMessage(WindowMessageParam param);

    /**
     * 运营平台弹窗列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<WindowMessageBO> listOperatorWindowMessage(OperatorWindowMessageQuery query);

    /**
     * 根据id查询弹窗详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    WindowMessageBO getWindowMessage(Long id);

    /**
     * 更新弹窗消息状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateWindowMessageStatus(Long id, WindowMessageStatusEnum statusEnum);

    /**
     * 弹窗消息列表
     *
     * @return
     * @author meishuquan
     */
    List<WindowMessageBO> listWindowMessage();

}
