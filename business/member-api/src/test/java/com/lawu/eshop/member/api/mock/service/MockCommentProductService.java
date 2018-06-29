package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.mall.param.CommentProductParam;
import com.lawu.eshop.member.api.service.CommentProductService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MockCommentProductService extends BaseController implements CommentProductService {

    @Override
    public Result saveCommentProductInfo(@PathVariable("memberId") Long memberId, @ModelAttribute CommentProductParam param, @RequestParam("headImg") String headImg) {
        return successCreated();
    }

    @Override
    public Result<Page<CommentDTO>> getCommentProducts(@ModelAttribute CommentProductListParam listParam) {
        CommentDTO dto = new CommentDTO();
        dto.setAnonymous(true);
        dto.setAvgGrade(Float.parseFloat("1"));
        dto.setAvgSpend(new BigDecimal("1"));
        dto.setContent("content");
        dto.setGmtCreate(new Date());
        dto.setGrade(new Byte("1"));
        dto.setId(1L);
        List<String> imgList = new ArrayList<>();
        imgList.add("1.jpg");
        dto.setImgUrls(imgList);
        dto.setMemberId(1L);
        dto.setProductId(1L);
        dto.setProductModelId(1L);
        dto.setReplyContent("replycontent");
        List<CommentDTO> list = new ArrayList<>();
        list.add(dto);
        Page<CommentDTO> page = new Page();
        page.setRecords(list);
        page.setCurrentPage(1);
        page.setTotalCount(100);
        return successCreated(page);
    }

    @Override
    public Result<Page<CommentDTO>> getCommentProductsWithImgs(@ModelAttribute CommentProductListParam listParam) {
        CommentDTO dto = new CommentDTO();
        dto.setAnonymous(true);
        dto.setAvgGrade(Float.parseFloat("1"));
        dto.setAvgSpend(new BigDecimal("1"));
        dto.setContent("content");
        dto.setGmtCreate(new Date());
        dto.setGrade(new Byte("1"));
        dto.setId(1L);
        List<String> imgList = new ArrayList<>();
        imgList.add("1.jpg");
        dto.setImgUrls(imgList);
        dto.setMemberId(1L);
        dto.setProductId(1L);
        dto.setProductModelId(1L);
        dto.setReplyContent("replycontent");
        List<CommentDTO> list = new ArrayList<>();
        list.add(dto);
        Page<CommentDTO> page = new Page();
        page.setRecords(list);
        page.setCurrentPage(1);
        page.setTotalCount(100);
        return successCreated(page);
    }

    @Override
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("productId") Long productId) {
        return successCreated();
    }
}
