package com.lawu.eshop.game.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.game.srv.mapper.GamePointAllotDOMapper;
import com.lawu.eshop.game.srv.service.GamePointAllotService;


/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@Service
public class GamePointAllotServiceImpl implements GamePointAllotService {
	
	
	@Autowired
	private GamePointAllotDOMapper gamePointAllotDOMapper;

}
