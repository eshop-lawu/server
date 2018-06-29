package com.lawu.eshop.user.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.LotteryGradeDTO;
import com.lawu.eshop.user.dto.UserGradeDTO;
import com.lawu.eshop.user.param.UserGradeQuery;
import com.lawu.eshop.user.param.UserGradeUpdateParam;
import com.lawu.eshop.user.srv.bo.UserGradeBO;
import com.lawu.eshop.user.srv.service.UserGradeService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 会员等级
 */
@RestController
@RequestMapping(value = "grade/")
public class UserGradeController extends BaseController {

	@Autowired
	private UserGradeService userGradeService;

	/**
	 * 根据会员等级值获枚举取获取该等级参与抽奖活动兑换积分
	 * 
	 * @param gradeValue 等级值
	 * @return
	 */
	@RequestMapping(value = "selectLotteryActivityPointByGradeValue", method = RequestMethod.GET)
	public Result<Integer> selectLotteryActivityPointByGradeValue(@RequestParam("gradeValue") Byte gradeValue) {

		Integer lotteryActivityPoint = userGradeService.selectLotteryActivityPointByGradeValue(gradeValue);

		return successGet(lotteryActivityPoint);
	}

	/**
	 * 查询用户等级对应的免费抽奖次数和活动等级对应的积分
	 *
	 * @param userId
	 * @param gradeValue
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "getLotteryGradeInfo", method = RequestMethod.GET)
	public Result<LotteryGradeDTO> getLotteryGradeInfo(@RequestParam Long userId, @RequestParam Byte gradeValue) {
		UserGradeBO userGradeBO = userGradeService.getLotteryGradeInfo(userId, gradeValue);
		if (userGradeBO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		LotteryGradeDTO gradeDTO = new LotteryGradeDTO();
		gradeDTO.setFreeLotteryCount(userGradeBO.getFreeLotteryCount());
		gradeDTO.setLotteryActivityPoint(userGradeBO.getLotteryActivityPoint());
		return successGet(gradeDTO);
	}

	/**
	 * 获取所有会员等级列表(权重值升序)
	 * @return
	 */
	@RequestMapping(value = "selectUserGradeList", method = RequestMethod.GET)
	public Result<List<UserGradeDTO>> selectUserGradeList() {

		List<UserGradeBO> gradeList = userGradeService.selectGradeList();
		List<UserGradeDTO> dtos = new ArrayList<>();
		for(UserGradeBO userGradeBO : gradeList){
			UserGradeDTO dto = new UserGradeDTO();
			dto.setId(userGradeBO.getId());
			dto.setGradeName(userGradeBO.getGradeName());
			dto.setGradeValue(userGradeBO.getGradeValue());
			dto.setGradeWeight(userGradeBO.getGradeWeight());
			dto.setLotteryActivityPoint(userGradeBO.getLotteryActivityPoint());
			dto.setMinGrowthValue(userGradeBO.getMinGrowthValue());
			dto.setGmtCreate(userGradeBO.getGmtCreate());
			dto.setGmtModified(userGradeBO.getGmtModified());
			dtos.add(dto);
		}
		return successGet(dtos);
	}

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	public Page<UserGradeDTO> page(@RequestBody UserGradeQuery query){
		Page<UserGradeBO> page = userGradeService.selectPage(query);
		List<UserGradeBO> bos = page.getRecords();
		List<UserGradeDTO> dtos = new ArrayList<>();
		for(UserGradeBO userGradeBO : bos){
			UserGradeDTO dto = new UserGradeDTO();
			dto.setId(userGradeBO.getId());
			dto.setGradeName(userGradeBO.getGradeName());
			dto.setGradeValue(userGradeBO.getGradeValue());
			dto.setGradeWeight(userGradeBO.getGradeWeight());
			dto.setFreeLotteryCount(userGradeBO.getFreeLotteryCount());
			dto.setLotteryActivityPoint(userGradeBO.getLotteryActivityPoint());
			dto.setNextFreeLotteryCount(userGradeBO.getNextFreeLotteryCount());
			dto.setMinGrowthValue(userGradeBO.getMinGrowthValue());
			dto.setGmtCreate(userGradeBO.getGmtCreate());
			dto.setGmtModified(userGradeBO.getGmtModified());
			dtos.add(dto);
		}
		Page<UserGradeDTO> rtnPage = new Page<>();
		rtnPage.setRecords(dtos);
		rtnPage.setCurrentPage(query.getCurrentPage());
		rtnPage.setTotalCount(page.getTotalCount());
		return rtnPage;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public UserGradeDTO selectUserGradeById(@PathVariable("id") Long id) {

		UserGradeBO userGradeBO = userGradeService.selectUserGradeById(id);
		UserGradeDTO dto = new UserGradeDTO();
		dto.setId(userGradeBO.getId());
		dto.setGradeName(userGradeBO.getGradeName());
		dto.setGradeValue(userGradeBO.getGradeValue());
		dto.setGradeWeight(userGradeBO.getGradeWeight());
		dto.setFreeLotteryCount(userGradeBO.getFreeLotteryCount());
		dto.setNextFreeLotteryCount(userGradeBO.getNextFreeLotteryCount());
		dto.setLotteryActivityPoint(userGradeBO.getLotteryActivityPoint());
		dto.setMinGrowthValue(userGradeBO.getMinGrowthValue());
		dto.setGmtCreate(userGradeBO.getGmtCreate());
		dto.setGmtModified(userGradeBO.getGmtModified());
		return dto;
	}

	/**
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result save(@RequestBody UserGradeUpdateParam param){
		int ret = userGradeService.save(param);
		return successCreated(ret);
	}

	/**
	 *
	 * @param id
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "updateById/{id}", method = RequestMethod.POST)
	public Result updateById(@PathVariable("id") Long id,@RequestBody UserGradeUpdateParam param){
		int ret = userGradeService.updateById(id,param);
		return successCreated(ret);
	}

	/**
	 * 更新下次抽奖次数到当前抽奖次数
	 *
	 * @author meishuquan
	 */
	@RequestMapping(value = "updateFreeLotteryCount", method = RequestMethod.PUT)
	public Result updateFreeLotteryCount() {
		userGradeService.updateFreeLotteryCount();
		return successCreated();
	}

}
