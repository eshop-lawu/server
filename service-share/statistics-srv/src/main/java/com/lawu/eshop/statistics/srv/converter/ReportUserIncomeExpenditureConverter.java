package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.statistics.dto.ReportUserIncomeExpenditureDTO;
import com.lawu.eshop.statistics.srv.bo.ReportUserIncomeExpenditureBO;
import com.lawu.eshop.statistics.srv.domain.ReportUserIncomeExpenditureDO;
import com.lawu.framework.core.page.Page;

/**
 * ReportSales转换器
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class ReportUserIncomeExpenditureConverter {

    /**
     * ReportUserIncomeExpenditureDO转ReportUserIncomeExpenditureBO
     *
     * @param reportUserIncomeExpenditureDO
     * @return
     */
    public static ReportUserIncomeExpenditureBO convert(ReportUserIncomeExpenditureDO reportUserIncomeExpenditureDO) {
    	ReportUserIncomeExpenditureBO rtn = null;
    	if (reportUserIncomeExpenditureDO == null) {
    		return rtn;
    	}
    	rtn = new ReportUserIncomeExpenditureBO();
    	rtn.setAccount(reportUserIncomeExpenditureDO.getAccount());
    	rtn.setDifference(reportUserIncomeExpenditureDO.getDifference());
    	rtn.setExpenditure(reportUserIncomeExpenditureDO.getExpenditure());
    	rtn.setGmtCreate(reportUserIncomeExpenditureDO.getGmtCreate());
    	rtn.setGmtReport(reportUserIncomeExpenditureDO.getGmtReport());
    	rtn.setId(reportUserIncomeExpenditureDO.getId());
    	rtn.setIncome(reportUserIncomeExpenditureDO.getIncome());
    	rtn.setUserNum(reportUserIncomeExpenditureDO.getUserNum());
    	rtn.setUserType(UserTypeEnum.getEnum(reportUserIncomeExpenditureDO.getUserType()));
        return rtn;
    }
    
    /**
     * ReportUserIncomeExpenditureDOList转ReportUserIncomeExpenditureBOList
     *
     * @param reportUserIncomeExpenditureDOList
     * @return
     */
    public static List<ReportUserIncomeExpenditureBO> convertReportUserIncomeExpenditureBOList(List<ReportUserIncomeExpenditureDO> reportUserIncomeExpenditureDOList) {
    	List<ReportUserIncomeExpenditureBO> rtn = null;
    	if (reportUserIncomeExpenditureDOList == null || reportUserIncomeExpenditureDOList.isEmpty()) {
    		return rtn;
    	}
    	rtn = new ArrayList<>();
    	for (ReportUserIncomeExpenditureDO reportUserIncomeExpenditureDO : reportUserIncomeExpenditureDOList) {
    		rtn.add(convert(reportUserIncomeExpenditureDO));
    	}
        return rtn;
    }
    
    /**
     * ReportUserIncomeExpenditureDO转ReportUserIncomeExpenditureBO
     *
     * @param reportUserIncomeExpenditureBO
     * @return
     */
    public static ReportUserIncomeExpenditureDTO convert(ReportUserIncomeExpenditureBO reportUserIncomeExpenditureBO) {
    	ReportUserIncomeExpenditureDTO rtn = null;
    	if (reportUserIncomeExpenditureBO == null) {
    		return rtn;
    	}
    	rtn = new ReportUserIncomeExpenditureDTO();
    	rtn.setAccount(reportUserIncomeExpenditureBO.getAccount());
    	rtn.setDifference(reportUserIncomeExpenditureBO.getDifference());
    	rtn.setExpenditure(reportUserIncomeExpenditureBO.getExpenditure());
    	rtn.setGmtReport(reportUserIncomeExpenditureBO.getGmtReport());
    	rtn.setIncome(reportUserIncomeExpenditureBO.getIncome());
    	rtn.setUserNum(reportUserIncomeExpenditureBO.getUserNum());
    	rtn.setUserType(reportUserIncomeExpenditureBO.getUserType());
        return rtn;
    }
    
    /**
     * ReportUserIncomeExpenditureBOList转ReportUserIncomeExpenditureDTOList
     *
     * @param reportUserIncomeExpenditureBOList
     * @return
     */
    public static List<ReportUserIncomeExpenditureDTO> convertReportUserIncomeExpenditureDTOList(List<ReportUserIncomeExpenditureBO> reportUserIncomeExpenditureBOList) {
    	List<ReportUserIncomeExpenditureDTO> rtn = new ArrayList<>();
    	if (reportUserIncomeExpenditureBOList == null || reportUserIncomeExpenditureBOList.isEmpty()) {
    		return rtn;
    	}
    	for (ReportUserIncomeExpenditureBO reportUserIncomeExpenditureBO : reportUserIncomeExpenditureBOList) {
    		rtn.add(convert(reportUserIncomeExpenditureBO));
    	}
        return rtn;
    }
    
    /**
     * ReportUserIncomeExpenditureDOPage转ReportUserIncomeExpenditureBOPage
     *
     * @param reportUserIncomeExpenditureDOList
     * @return
     */
    public static Page<ReportUserIncomeExpenditureDTO> convert(Page<ReportUserIncomeExpenditureBO> page) {
    	Page<ReportUserIncomeExpenditureDTO> rtn = new Page<>();
    	rtn.setCurrentPage(page.getCurrentPage());
    	rtn.setRecords(convertReportUserIncomeExpenditureDTOList(page.getRecords()));
    	rtn.setTotalCount(page.getTotalCount());
        return rtn;
    }
    
}
