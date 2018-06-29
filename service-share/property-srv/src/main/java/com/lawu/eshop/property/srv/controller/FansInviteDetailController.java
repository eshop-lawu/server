package com.lawu.eshop.property.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.dto.FansInviteDetailDTO;
import com.lawu.eshop.property.param.ListFansInviteDetailParam;
import com.lawu.eshop.property.srv.bo.FansInviteDetailBO;
import com.lawu.eshop.property.srv.converter.FansInviteDetailConverter;
import com.lawu.eshop.property.srv.service.FansInviteDetailService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
@RestController
@RequestMapping(value = "fansInviteDetail/")
public class FansInviteDetailController extends BaseController {

    @Autowired
    private FansInviteDetailService fansInviteDetailService;

    /**
     * 商家邀请粉丝记录列表
     *
     * @param merchantId
     * @param listFansInviteDetailParam
     * @return
     */
    @RequestMapping(value = "listFansInviteDetail/{merchantId}", method = RequestMethod.POST)
    public Result<Page<FansInviteDetailDTO>> listFansInviteDetail(@PathVariable Long merchantId, @RequestBody ListFansInviteDetailParam listFansInviteDetailParam) {
        Page<FansInviteDetailBO> fansInviteDetailBOPage = fansInviteDetailService.listFansInviteDetail(merchantId, listFansInviteDetailParam);
        Page<FansInviteDetailDTO> page = new Page<>();
        page.setRecords(FansInviteDetailConverter.convertDTO(fansInviteDetailBOPage.getRecords()));
        page.setCurrentPage(fansInviteDetailBOPage.getCurrentPage());
        page.setTotalCount(fansInviteDetailBOPage.getTotalCount());
        return successGet(page);
    }

    /**
     * 根据积分编号查询邀请粉丝详情
     *
     * @param pointNum
     * @return
     */
    @RequestMapping(value = "getFansInviteDetail/{pointNum}", method = RequestMethod.GET)
    public Result<FansInviteDetailDTO> getFansInviteDetail(@PathVariable String pointNum) {
        FansInviteDetailBO fansInviteDetailBO = fansInviteDetailService.getFansInviteDetailByPointNum(pointNum);
        if (fansInviteDetailBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(FansInviteDetailConverter.convertDTO(fansInviteDetailBO));
    }
}
