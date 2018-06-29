package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.game.dto.UserStarRankListDTO;
import com.lawu.eshop.game.srv.bo.RankListBO;
import com.lawu.eshop.game.srv.domain.UserStarRecordDO;

/**
 * @author zhangyong
 * @date 2018/3/10.
 */
public class UserStarRecordConverter {
    public static List<RankListBO> converterRankBOS(List<UserStarRecordDO> recordDOS) {
        if (recordDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<RankListBO> listBOS = new ArrayList<>();
        RankListBO listBO;
        for (UserStarRecordDO rankListDO : recordDOS) {
            listBO = new RankListBO();
            listBO.setUserNum(rankListDO.getUserNum());
            listBO.setMonthStarCount(rankListDO.getMonthStarCount());
            listBO.setId(rankListDO.getId());
            listBO.setStatus(rankListDO.getStatus());
            listBOS.add(listBO);
        }
        return listBOS;
    }

    public static List<UserStarRankListDTO> converterRankDTOS(List<RankListBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserStarRankListDTO> listDTOS = new ArrayList<>();
        UserStarRankListDTO listDTO;
        for (RankListBO listBO : records) {
            listDTO = new UserStarRankListDTO();
            listDTO.setUserNum(listBO.getUserNum());
            listDTO.setStarCount(listBO.getMonthStarCount());
            listDTO.setId(listBO.getId());
            listDTO.setStatus(listBO.getStatus());
            listDTOS.add(listDTO);
        }
        return listDTOS;
    }
}
