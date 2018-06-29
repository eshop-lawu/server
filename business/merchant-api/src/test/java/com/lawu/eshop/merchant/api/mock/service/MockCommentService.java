package com.lawu.eshop.merchant.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.dto.CommentProductIdDTO;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.merchant.api.service.CommentService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockCommentService extends BaseController implements CommentService {
    @Override
    public Result replyProductComment(@PathVariable("commentId") Long commentId, @RequestParam("replyContent") String replyContent, @RequestParam("merchantId") Long merchantId) {
        return successCreated();
    }

    @Override
    public Result replyMerchantComment(@PathVariable("commentId") Long commentId, @RequestParam("replyContent") String replyContent, @RequestParam("merchantId") Long merchantId) {
        return successCreated();
    }

    @Override
    public Result<Page<CommentDTO>> getProductCommentListByMerchantId(@ModelAttribute CommentMerchantListParam pageParam) {
        CommentDTO dto = new CommentDTO();
        dto.setAnonymous(true);

        List<CommentDTO> list = new ArrayList<>();
        list.add(dto);

        Page<CommentDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<CommentProductIdDTO>> getProductCommentIdsByMerchantId(@ModelAttribute CommentMerchantListParam pageParam) {
        CommentProductIdDTO dto = new CommentProductIdDTO();
        dto.setProductId(10L);
        dto.setProductModelId(10L);

        List<CommentProductIdDTO> list = new ArrayList<>();
        list.add(dto);

        Page<CommentProductIdDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<CommentDTO>> getCommentProducts(@ModelAttribute CommentProductListParam listParam) {
        CommentDTO dto = new CommentDTO();
        dto.setAnonymous(true);

        List<CommentDTO> list = new ArrayList<>();
        list.add(dto);

        Page<CommentDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<CommentDTO>> getCommentMerchantAllList(@ModelAttribute CommentMerchantListParam listParam) {
        CommentDTO dto = new CommentDTO();
        dto.setAnonymous(true);

        List<CommentDTO> list = new ArrayList<>();
        list.add(dto);

        Page<CommentDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("productId") Long productId) {
        return null;
    }
}
