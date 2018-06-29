package com.lawu.eshop.game.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.dto.GamePointReportDTO;
import com.lawu.eshop.game.srv.bo.GamePointReportBO;
import com.lawu.eshop.game.srv.converter.GamePointReportConverter;
import com.lawu.eshop.game.srv.service.GamePointReportService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
@RestController
@RequestMapping(value = "gamePointReport/")
public class GamePointReportController extends BaseController{
	
	@Autowired
	private GamePointReportService gamePointReportService;
	
	@RequestMapping(value = "getGamePointReport", method = RequestMethod.GET)
    public Result<List<GamePointReportDTO>> getGamePointReport(@RequestParam String today) {
        List<GamePointReportBO> list = gamePointReportService.getGamePointReport(today);
        
        List<GamePointReportDTO> dtos = new ArrayList<>();
        for (GamePointReportBO gamePointReportBO : list) {
        	 GamePointReportDTO  dto = GamePointReportConverter.converDTO(gamePointReportBO);
        	 dtos.add(dto);
		}
       
        return successGet(dtos);
    }


}
