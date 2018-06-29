package com.lawu.eshop.statistics.srv.converter;

import com.lawu.eshop.statistics.dto.ReportUserRegAreaDTO;
import com.lawu.eshop.statistics.dto.ReportUserRegDTO;
import com.lawu.eshop.statistics.srv.bo.ReportUserRegAreaBO;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegAreaDO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportUserRegDOView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/6/30.
 */
public class ReportUserRegConverter {

    /**
     * DO转BO
     *
     * @param regAreaDOList
     * @return
     */
    public static List<ReportUserRegAreaBO> convertAreaBO(List<ReportUserRegAreaDO> regAreaDOList) {
        List<ReportUserRegAreaBO> regAreaBOList = new ArrayList<>();
        if (regAreaDOList == null || regAreaDOList.isEmpty()) {
            return regAreaBOList;
        }
        for (ReportUserRegAreaDO regAreaDO : regAreaDOList) {
            ReportUserRegAreaBO regAreaBO = new ReportUserRegAreaBO();
            regAreaBO.setMemberCount(regAreaDO.getMemberCount());
            regAreaBO.setMerchantCount(regAreaDO.getMerchantCount());
            regAreaBO.setMerchantCommonCount(regAreaDO.getMerchantCommonCount());
            regAreaBO.setMerchantEntityCount(regAreaDO.getMerchantEntityCount());
            regAreaBO.setCityId(regAreaDO.getCityId());
            regAreaBO.setCityName(regAreaDO.getCityName());
            regAreaBOList.add(regAreaBO);
        }
        return regAreaBOList;
    }

    /**
     * BO转DTO
     *
     * @param regAreaBOList
     * @return
     */
    public static List<ReportUserRegAreaDTO> convertAreaDTO(List<ReportUserRegAreaBO> regAreaBOList) {
        List<ReportUserRegAreaDTO> regAreaDTOList = new ArrayList<>();
        if (regAreaBOList == null || regAreaBOList.isEmpty()) {
            return regAreaDTOList;
        }
        for (ReportUserRegAreaBO regAreaBO : regAreaBOList) {
            ReportUserRegAreaDTO regAreaDTO = new ReportUserRegAreaDTO();
            regAreaDTO.setMemberCount(regAreaBO.getMemberCount());
            regAreaDTO.setMerchantCount(regAreaBO.getMerchantCount());
            regAreaDTO.setMerchantCommonCount(regAreaBO.getMerchantCommonCount());
            regAreaDTO.setMerchantEntityCount(regAreaBO.getMerchantEntityCount());
            regAreaDTO.setCityId(regAreaBO.getCityId());
            regAreaDTO.setCityName(regAreaBO.getCityName());
            regAreaDTOList.add(regAreaDTO);
        }
        return regAreaDTOList;
    }

    /**
     * View转DTO
     *
     * @param regDOViewList
     * @return
     */
    public static List<ReportUserRegDTO> convertDTO(List<ReportUserRegDOView> regDOViewList) {
        List<ReportUserRegDTO> regDTOList = new ArrayList<>();
        if (regDOViewList == null || regDOViewList.isEmpty()) {
            return regDTOList;
        }
        for (ReportUserRegDOView regDOView : regDOViewList) {
            ReportUserRegDTO regDTO = new ReportUserRegDTO();
            regDTO.setMemberCount(regDOView.getMemberCount());
            regDTO.setMerchantCount(regDOView.getMerchantCount());
            regDTO.setGmtReport(regDOView.getGmtReport());
            regDTOList.add(regDTO);
        }
        return regDTOList;
    }

}
