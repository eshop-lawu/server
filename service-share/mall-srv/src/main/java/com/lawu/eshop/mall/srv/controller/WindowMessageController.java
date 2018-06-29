package com.lawu.eshop.mall.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.dto.WindowMessageListDTO;
import com.lawu.eshop.mall.dto.WindowMessageOperatorDTO;
import com.lawu.eshop.mall.param.WindowMessageParam;
import com.lawu.eshop.mall.query.OperatorWindowMessageQuery;
import com.lawu.eshop.mall.srv.bo.WindowMessageBO;
import com.lawu.eshop.mall.srv.converter.WindowMessageConverter;
import com.lawu.eshop.mall.srv.service.WindowMessageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
@RestController
@RequestMapping(value = "windowMessage/")
public class WindowMessageController extends BaseController {

    @Autowired
    private WindowMessageService windowMessageService;

    /**
     * 添加弹窗消息
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveWindowMessage", method = RequestMethod.POST)
    public Result saveWindowMessage(@RequestBody WindowMessageParam param) {
        windowMessageService.saveWindowMessage(param);
        return successCreated();
    }

    /**
     * 运营平台弹窗列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorWindowMessage", method = RequestMethod.POST)
    public Result<Page<WindowMessageOperatorDTO>> listOperatorWindowMessage(@RequestBody OperatorWindowMessageQuery query) {
        Page<WindowMessageBO> messageBOPage = windowMessageService.listOperatorWindowMessage(query);
        Page<WindowMessageOperatorDTO> page = new Page<>();
        page.setCurrentPage(messageBOPage.getCurrentPage());
        page.setTotalCount(messageBOPage.getTotalCount());
        page.setRecords(WindowMessageConverter.convertOperatorDTOS(messageBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据id查询弹窗详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getWindowMessage/{id}", method = RequestMethod.GET)
    public Result<WindowMessageOperatorDTO> getWindowMessage(@PathVariable Long id) {
        WindowMessageBO messageBO = windowMessageService.getWindowMessage(id);
        return successGet(WindowMessageConverter.convertOperatorDTO(messageBO));
    }

    /**
     * 更新弹窗消息状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateWindowMessageStatus/{id}", method = RequestMethod.PUT)
    public Result updateWindowMessageStatus(@PathVariable Long id, @RequestParam WindowMessageStatusEnum statusEnum) {
        windowMessageService.updateWindowMessageStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 弹窗消息列表
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listWindowMessage", method = RequestMethod.GET)
    public Result<WindowMessageListDTO> listWindowMessage() {
        List<WindowMessageBO> messageBOS = windowMessageService.listWindowMessage();
        WindowMessageListDTO listDTO = new WindowMessageListDTO();
        listDTO.setMessageDTOS(WindowMessageConverter.convertDTOS(messageBOS));
        return successGet(listDTO);
    }

}
