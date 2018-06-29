package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.dto.MemberProductCommentDTO;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentMerchantParam;
import com.lawu.eshop.member.api.service.CommentMerchantService;
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
public class MockCommentMerchantService extends BaseController implements CommentMerchantService {

    @Override
    public Result saveCommentMerchantInfo(@PathVariable("memberId") Long memberId, @ModelAttribute CommentMerchantParam param, @RequestParam("commentPic") String commentPic) {
        return successCreated();
    }

    @Override
    public Result<Page<CommentDTO>> getCommentMerchantAllList(@ModelAttribute CommentMerchantListParam listParam) {
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
    public Result<Page<CommentDTO>> getCommentMerchantListWithImgs(@ModelAttribute CommentMerchantListParam listParam) {
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
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("merchantId") Long merchantId) {
        return successCreated();
    }

    @Override
    public Result<List<MemberProductCommentDTO>> geNewlyProductComment(@RequestParam("productId") Long productId) {
        MemberProductCommentDTO dto = new MemberProductCommentDTO();
        dto.setProductModelId(1L);
        dto.setMemberId(1L);
        dto.setIsAnonymous(true);
        List<MemberProductCommentDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<Integer> getProductCommentCount(@RequestParam("productId") Long productId) {
        return successCreated(new Integer(1));
    }

    @Override
    public Byte getGradeByOrderId(@RequestParam("id") Long id, @RequestParam("memberId") Long memberId) {
        return new Byte("0");
    }
}
