package com.lawu.eshop.ad.srv.controller;

import com.lawu.eshop.ad.dto.PointPoolDTO;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.service.PointPoolService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhangrc
 * @date 2017/4/11
 *
 */
@RestController
@RequestMapping(value = "pointPool/")
public class PointPoolController extends BaseController{

	@Autowired
	private PointPoolService pointPoolService;
	
	/**
	 * 根据广告id 查询e赞前三名会员
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectMemberList/{id}", method = RequestMethod.GET)
    public Result<List<PointPoolDTO>> selectMemberList(@PathVariable Long id) {
		List<PointPoolDO> memberIds = pointPoolService.selectMemberList(id);
		List<PointPoolDTO> list=new ArrayList<>();
		for (PointPoolDO pointPoolDO : memberIds) {
			PointPoolDTO dto=new PointPoolDTO();
			dto.setMemberId(pointPoolDO.getMemberId());
			dto.setPoint(pointPoolDO.getPoint());
			list.add(dto);
		}
		return  successAccepted(list);
    }
}
