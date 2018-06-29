package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.dto.SearchWordDTO;
import com.lawu.eshop.mall.srv.bo.SearchWordBO;
import com.lawu.eshop.mall.srv.domain.SearchWordDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public class SearchWordConverter {

    /**
     * BO转换
     *
     * @param searchWordDO
     * @return
     */
    public static SearchWordBO convertBO(SearchWordDO searchWordDO) {
        if (searchWordDO == null) {
            return null;
        }

        SearchWordBO searchWordBO = new SearchWordBO();
        searchWordBO.setId(searchWordDO.getId());
        searchWordBO.setWord(searchWordDO.getWord());
        searchWordBO.setType(searchWordDO.getType());
        searchWordBO.setGmtCreate(searchWordDO.getGmtCreate());
        return searchWordBO;
    }

    /**
     * DTO转换
     *
     * @param searchWordBO
     * @return
     */
    public static SearchWordDTO convertDTO(SearchWordBO searchWordBO) {
        if (searchWordBO == null) {
            return null;
        }

        SearchWordDTO searchWordDTO = new SearchWordDTO();
        searchWordDTO.setId(searchWordBO.getId());
        searchWordDTO.setWord(searchWordBO.getWord());
        searchWordDTO.setSearchWordTypeEnum(SearchWordTypeEnum.getEnum(searchWordBO.getType()));
        searchWordDTO.setGmtCreate(searchWordBO.getGmtCreate());
        return searchWordDTO;
    }

    /**
     * BO转换
     *
     * @param searchWordDOList
     * @return
     */
    public static List<SearchWordBO> convertBO(List<SearchWordDO> searchWordDOList) {
        List<SearchWordBO> searchWordBOS = new ArrayList<>();
        if (searchWordDOList == null || searchWordDOList.isEmpty()) {
            return searchWordBOS;
        }

        for (SearchWordDO searchWordDO : searchWordDOList) {
            SearchWordBO searchWordBO = new SearchWordBO();
            searchWordBO.setId(searchWordDO.getId());
            searchWordBO.setWord(searchWordDO.getWord());
            searchWordBO.setType(searchWordDO.getType());
            searchWordBO.setGmtCreate(searchWordDO.getGmtCreate());
            searchWordBOS.add(searchWordBO);
        }
        return searchWordBOS;
    }

    /**
     * DTO转换
     *
     * @param searchWordBOList
     * @return
     */
    public static List<SearchWordDTO> convertDTO(List<SearchWordBO> searchWordBOList) {
        List<SearchWordDTO> searchWordDTOS = new ArrayList<>();
        if (searchWordBOList == null || searchWordBOList.isEmpty()) {
            return searchWordDTOS;
        }

        for (SearchWordBO searchWordBO : searchWordBOList) {
            SearchWordDTO searchWordDTO = new SearchWordDTO();
            searchWordDTO.setId(searchWordBO.getId());
            searchWordDTO.setWord(searchWordBO.getWord());
            searchWordDTO.setSearchWordTypeEnum(SearchWordTypeEnum.getEnum(searchWordBO.getType()));
            searchWordDTO.setGmtCreate(searchWordBO.getGmtCreate());
            searchWordDTOS.add(searchWordDTO);
        }
        return searchWordDTOS;
    }
}
