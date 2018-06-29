package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.dto.WindowMessageOperatorDTO;
import com.lawu.eshop.mall.param.WindowMessageParam;
import com.lawu.eshop.mall.query.OperatorWindowMessageQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
@FeignClient(value = "mall-srv", path = "windowMessage")
public interface WindowMessageService {

    /**
     * 添加弹窗消息
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveWindowMessage", method = RequestMethod.POST)
    Result saveWindowMessage(@RequestBody WindowMessageParam param);

    /**
     * 弹窗列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorWindowMessage", method = RequestMethod.POST)
    Result<Page<WindowMessageOperatorDTO>> listOperatorWindowMessage(@RequestBody OperatorWindowMessageQuery query);

    /**
     * 根据id查询弹窗详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getWindowMessage/{id}", method = RequestMethod.GET)
    Result<WindowMessageOperatorDTO> getWindowMessage(@PathVariable("id") Long id);

    /**
     * 更新弹窗消息状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateWindowMessageStatus/{id}", method = RequestMethod.PUT)
    Result updateWindowMessageStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") WindowMessageStatusEnum statusEnum);

}
