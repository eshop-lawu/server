package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.statistics.dto.ReportDataDTO;
import com.lawu.eshop.statistics.dto.ReportGroupDTO;
import com.lawu.eshop.statistics.dto.ReportSalesDTO;
import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;
import com.lawu.utils.DateUtil;

/**
 * ReportSales转换器
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class ReportSalesConverter {

    /**
     * ReportSalesBO转ReportSalesDTO
     *
     * @param reportSalesBO
     * @return
     */
    public static ReportSalesDTO convert(ReportSalesBO reportSalesBO) {
    	ReportSalesDTO rtn = new ReportSalesDTO();
    	rtn.setPayOrderAmount(new BigDecimal(0));
    	rtn.setShoppingOrderAmount(new BigDecimal(0));
    	if (reportSalesBO == null) {
    		return rtn;
    	}
        rtn.setPayOrderAmount(reportSalesBO.getPayOrderAmount());
        rtn.setShoppingOrderAmount(reportSalesBO.getShoppingOrderAmount());
        return rtn;
    }
    
    /**
     * ReportSalesBO转ReportDataDTO
     *
     * @param reportSalesBO
     * @return
     */
    public static ReportDataDTO convert(List<ReportSalesBO> reportSalesBOList, PlatformTotalSalesQueryParam param) {
    	ReportDataDTO rtn = new ReportDataDTO();
    	rtn.setData(new ArrayList<>());
    	if (reportSalesBOList == null || reportSalesBOList.isEmpty()) {
    		return rtn;
    	}
    	// 把reportSalesBOList按照X轴坐标分组
    	for (ReportSalesBO reportSalesBO : reportSalesBOList) {
    		ReportGroupDTO<ReportSalesDTO> reportGroupDTO = new ReportGroupDTO<>();
    		ReportSalesDTO reportSalesDTO = convert(reportSalesBO);
    		reportGroupDTO.setY(reportSalesDTO);
    		switch(param.getType()) {
				case DAILY:
					reportGroupDTO.setX(DateUtil.getDateFormat(reportSalesBO.getGmtReport(), DateUtil.DATE_MM_DD_FORMAT));
					break;
				case MONTH:
					reportGroupDTO.setX(DateUtil.getDateFormat(reportSalesBO.getGmtReport(), DateUtil.DATE_YYYY_MM_FORMAT));
					break;
				default:
					break;
    		}
    		rtn.getData().add(reportGroupDTO);
    	}
        return rtn;
    }
}
