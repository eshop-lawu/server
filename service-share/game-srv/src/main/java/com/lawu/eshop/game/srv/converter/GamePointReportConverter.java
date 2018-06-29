package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.GamePointReportDTO;
import com.lawu.eshop.game.srv.bo.GamePointReportBO;
import com.lawu.eshop.game.srv.domain.extend.GamePointReportView;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
public class GamePointReportConverter {
	
	
	public static GamePointReportBO converBO(List<GamePointReportView> list) {
		
		 GamePointReportBO report = new GamePointReportBO();
    	 report.setStandAloneExpendPoint(new BigDecimal(0));
	     report.setStandAloneIncomePoint(new BigDecimal(0));
	     report.setRandomExpendPoint(new BigDecimal(0));
	     report.setRandomIncomePoint(new BigDecimal(0));
	     report.setManyPeopleExpendPoint(new BigDecimal(0));
	     report.setManyPeopleIncomePoint(new BigDecimal(0));
	     
        for (GamePointReportView gamePointReportView : list) {
        	if(gamePointReportView.getAttendType() == null){
				report.setTotalIncomePoint(gamePointReportView.getRewardSumPoint());
        		continue;
        	}
			if(AttendTypeEnum.getEnum(gamePointReportView.getAttendType()) == AttendTypeEnum.STANDALONE){
				 report.setStandAloneExpendPoint(gamePointReportView.getRewardSumPoint());
			     report.setStandAloneIncomePoint(gamePointReportView.getAttendSumPoint());
			}else if(AttendTypeEnum.getEnum(gamePointReportView.getAttendType()) == AttendTypeEnum.RANDOM){
				 report.setRandomExpendPoint(gamePointReportView.getRewardSumPoint());
			     report.setRandomIncomePoint(gamePointReportView.getAttendSumPoint());
			}else{
				 report.setManyPeopleExpendPoint(gamePointReportView.getRewardSumPoint());
			     report.setManyPeopleIncomePoint(gamePointReportView.getAttendSumPoint());
			}
		}
      
        return report;
    }
	
	
	public static GamePointReportDTO converDTO(GamePointReportBO view) {
        if (view == null) {
            return null;
        }
        GamePointReportDTO report = new GamePointReportDTO();
        report.setGameType(view.getGameType());
        report.setStandAloneExpendPoint(view.getStandAloneExpendPoint());
        report.setStandAloneIncomePoint(view.getStandAloneIncomePoint());
        report.setRandomExpendPoint(view.getRandomExpendPoint());
        report.setRandomIncomePoint(view.getRandomIncomePoint());
        report.setManyPeopleExpendPoint(view.getManyPeopleExpendPoint());
        report.setManyPeopleIncomePoint(view.getManyPeopleIncomePoint());
		report.setTotalExpendPoint(view.getTotalExpendPoint());
		report.setTotalIncomePoint(view.getTotalIncomePoint());
        return report;
    }

}
