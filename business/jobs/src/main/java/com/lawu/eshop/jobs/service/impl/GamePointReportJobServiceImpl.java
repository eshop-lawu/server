package com.lawu.eshop.jobs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.game.dto.GamePointReportDTO;
import com.lawu.eshop.jobs.service.GamePointReportJobService;
import com.lawu.eshop.jobs.service.GamePointReportService;
import com.lawu.eshop.jobs.service.ReportGamePointService;
import com.lawu.eshop.statistics.constants.GameTypeEnum;
import com.lawu.eshop.statistics.param.GamePointReportParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
@Service
public class GamePointReportJobServiceImpl implements GamePointReportJobService {
	
	@Autowired
	private GamePointReportService gamePointReportService;
	
	@Autowired 
	private ReportGamePointService reportGamePointService;

	@Override
	public void executeGamePointReportDaily() {
		
		Result<Date> result = reportGamePointService.getDaily();
		Date lastDay = result.getModel();
		
		if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getSomeDay(new Date(), -2))) {
			int betweenDay = DateUtil.daysOfTwo(lastDay);
			for(int i = 0; i < betweenDay - 1; i++) {
				String today = DateUtil.getDateFormat(DateUtil.getDayBefore(DateUtil.getSomeDay(lastDay, 2 + i)),"yyyy-MM-dd");
				Result<List<GamePointReportDTO>> pointsRs = gamePointReportService.getGamePointReport(today);
				List<GamePointReportDTO> listPoints = pointsRs.getModel();
				List<GamePointReportParam> list = new ArrayList<>();
				for (GamePointReportDTO dto : listPoints) {
					GamePointReportParam  param = new GamePointReportParam();
					param.setGameType(GameTypeEnum.getEnum(dto.getGameType().getVal()));
					param.setStandAloneExpendPoint(dto.getStandAloneExpendPoint());
					param.setStandAloneIncomePoint(dto.getStandAloneIncomePoint());
					param.setRandomExpendPoint(dto.getRandomExpendPoint());
					param.setRandomIncomePoint(dto.getRandomIncomePoint());
					param.setManyPeopleExpendPoint(dto.getManyPeopleExpendPoint());
					param.setManyPeopleIncomePoint(dto.getManyPeopleIncomePoint());
					param.setTotalExpendPoint(dto.getTotalExpendPoint());
					param.setTotalIncomePoint(dto.getTotalIncomePoint());
					if (param.getGameType().equals(GameTypeEnum.MIND) || param.getGameType().equals(GameTypeEnum.PUZZLE)) {
						param.setTotalExpendPoint(param.getStandAloneExpendPoint().add(param.getRandomExpendPoint()).add(param.getManyPeopleExpendPoint()));
						param.setTotalIncomePoint(param.getStandAloneIncomePoint().add(param.getRandomIncomePoint()).add(param.getManyPeopleIncomePoint()));
					}
					param.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
					list.add(param);
				}
				
				Result rs = reportGamePointService.saveReportGamePointDaily(list);
				
			}
				
		} else {
			String today = DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),"yyyy-MM-dd");
			Result<List<GamePointReportDTO>> pointsRs = gamePointReportService.getGamePointReport(today);
			List<GamePointReportDTO> listPoints = pointsRs.getModel();
			List<GamePointReportParam> list = new ArrayList<>();
			for (GamePointReportDTO dto : listPoints) {
				GamePointReportParam  param = new GamePointReportParam();
				param.setGameType(GameTypeEnum.getEnum(dto.getGameType().getVal()));
				param.setStandAloneExpendPoint(dto.getStandAloneExpendPoint());
				param.setStandAloneIncomePoint(dto.getStandAloneIncomePoint());
				param.setRandomExpendPoint(dto.getRandomExpendPoint());
				param.setRandomIncomePoint(dto.getRandomIncomePoint());
				param.setManyPeopleExpendPoint(dto.getManyPeopleExpendPoint());
				param.setManyPeopleIncomePoint(dto.getManyPeopleIncomePoint());
				param.setTotalExpendPoint(dto.getTotalExpendPoint());
				param.setTotalIncomePoint(dto.getTotalIncomePoint());
				if (param.getGameType().equals(GameTypeEnum.MIND) || param.getGameType().equals(GameTypeEnum.PUZZLE)) {
					param.setTotalExpendPoint(param.getStandAloneExpendPoint().add(param.getRandomExpendPoint()).add(param.getManyPeopleExpendPoint()));
					param.setTotalIncomePoint(param.getStandAloneIncomePoint().add(param.getRandomIncomePoint()).add(param.getManyPeopleIncomePoint()));
				}
				param.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
				list.add(param);
			}
			
			Result rs = reportGamePointService.saveReportGamePointDaily(list);
			
		}
		
		
		
	}
		


}
