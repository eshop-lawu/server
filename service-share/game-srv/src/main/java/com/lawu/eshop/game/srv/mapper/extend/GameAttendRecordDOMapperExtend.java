package com.lawu.eshop.game.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.game.srv.domain.extend.GamePointReportView;
import com.lawu.eshop.game.srv.domain.extend.PointReportView;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
public interface GameAttendRecordDOMapperExtend {
	
	
	List<GamePointReportView> getReportGameMindAttendRecord(PointReportView view);
	
	
	List<GamePointReportView> getReportGamePuzzleAttendRecord(PointReportView view);


	List<GamePointReportView> getReportGameDialAttendRecord(PointReportView view);
	
	
}
