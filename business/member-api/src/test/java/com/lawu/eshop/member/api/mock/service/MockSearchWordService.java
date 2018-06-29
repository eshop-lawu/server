package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.member.api.service.SearchWordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;


@Service
public class MockSearchWordService extends BaseController implements SearchWordService {

    @Override
    public Result<Page<SearchWordDTO>> listSearchWord(@ModelAttribute SearchWordParam searchWordParam) {
        SearchWordDTO dto = new SearchWordDTO();
        List<SearchWordDTO> list = new ArrayList<>();
        list.add(dto);
        Page<SearchWordDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setCurrentPage(10);
        page.setRecords(list);
        return successCreated(page);
    }
}
