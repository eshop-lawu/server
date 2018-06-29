package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.srv.bo.GamePointReportBO;
import com.lawu.eshop.game.srv.converter.GamePointReportConverter;
import com.lawu.eshop.game.srv.domain.extend.GamePointReportView;
import com.lawu.eshop.game.srv.domain.extend.PointReportView;
import com.lawu.eshop.game.srv.mapper.extend.GameAttendRecordDOMapperExtend;
import com.lawu.eshop.game.srv.service.GamePointReportService;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
@Service
public class GamePointReportServiceImpl implements GamePointReportService{
	
	@Autowired
	private GameAttendRecordDOMapperExtend gameAttendRecordDOMapperExtend;

	@Override
	public List<GamePointReportBO> getGamePointReport(String today) {
		String begin= today + " 00:00:00";
        String end= today + " 23:59:59";
        PointReportView view = new PointReportView();
        view.setBeginDate(begin);
        view.setEndDate(end);
        
        List<GamePointReportBO> list = new ArrayList<>();
        List<GamePointReportView> mindView = gameAttendRecordDOMapperExtend.getReportGameMindAttendRecord(view);
        GamePointReportBO mindBO = GamePointReportConverter.converBO(mindView);
        mindBO.setGameType(GameTypeEnum.MIND);
        list.add(mindBO);
        
        List<GamePointReportView> puzzleView = gameAttendRecordDOMapperExtend.getReportGamePuzzleAttendRecord(view);
        GamePointReportBO puzzleBO = GamePointReportConverter.converBO(puzzleView);
        puzzleBO.setGameType(GameTypeEnum.PUZZLE);
        list.add(puzzleBO);

        List<GamePointReportView> dialView = gameAttendRecordDOMapperExtend.getReportGameDialAttendRecord(view);
        GamePointReportBO dialBO = GamePointReportConverter.converBO(dialView);
        dialBO.setGameType(GameTypeEnum.DIAL);
        list.add(dialBO);
        
		return list;
	}

}
