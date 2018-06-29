package com.lawu.eshop.mall.srv.service.impl;

import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.mall.srv.bo.SearchWordBO;
import com.lawu.eshop.mall.srv.converter.SearchWordConverter;
import com.lawu.eshop.mall.srv.domain.SearchWordDO;
import com.lawu.eshop.mall.srv.domain.SearchWordDOExample;
import com.lawu.eshop.mall.srv.mapper.SearchWordDOMapper;
import com.lawu.eshop.mall.srv.service.SearchWordService;
import com.lawu.framework.core.page.Page;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
@Service
public class SearchWordServiceImpl implements SearchWordService {

    @Autowired
    private SearchWordDOMapper searchWordDOMapper;

    @Override
    public void saveSearchWord(String word, SearchWordTypeEnum searchWordTypeEnum) {
        SearchWordDO searchWordDO = new SearchWordDO();
        searchWordDO.setWord(word);
        searchWordDO.setType(searchWordTypeEnum.val);
        searchWordDO.setGmtCreate(new Date());
        searchWordDOMapper.insertSelective(searchWordDO);
    }

    @Override
    public SearchWordBO getSearchWordById(Long id) {
        SearchWordDO searchWordDO = searchWordDOMapper.selectByPrimaryKey(id);
        return SearchWordConverter.convertBO(searchWordDO);
    }

    @Override
    public void deleteSearchWordById(Long id) {
        searchWordDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<SearchWordBO> listSearchWord(SearchWordParam searchWordParam) {
        SearchWordDOExample searchWordDOExample = new SearchWordDOExample();
        if (searchWordParam.getSearchWordTypeEnum() != null) {
            searchWordDOExample.createCriteria().andTypeEqualTo(searchWordParam.getSearchWordTypeEnum().val);
        }
        searchWordDOExample.setOrderByClause("id desc");
        RowBounds rowBounds = new RowBounds(searchWordParam.getOffset(), searchWordParam.getPageSize());

        Page<SearchWordBO> page = new Page<>();
        page.setTotalCount(searchWordDOMapper.countByExample(searchWordDOExample));
        page.setCurrentPage(searchWordParam.getCurrentPage());

        List<SearchWordDO> searchWordDOList = searchWordDOMapper.selectByExampleWithRowbounds(searchWordDOExample, rowBounds);
        page.setRecords(SearchWordConverter.convertBO(searchWordDOList));
        return page;
    }
}
