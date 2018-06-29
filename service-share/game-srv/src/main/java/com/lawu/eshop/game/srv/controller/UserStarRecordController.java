package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.dto.RankListDTO;
import com.lawu.eshop.game.dto.UserStarRankListDTO;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.game.param.UserStarRecordParam;
import com.lawu.eshop.game.srv.bo.RankListBO;
import com.lawu.eshop.game.srv.bo.UserRankBO;
import com.lawu.eshop.game.srv.converter.UserStarRecordConverter;
import com.lawu.eshop.game.srv.service.UserStarRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
@RestController
@RequestMapping(value = "userStar/")
public class UserStarRecordController extends BaseController {

    @Autowired
    private UserStarRecordService userStarRecordService;

    /**
     * 用户星星增量记录表记录新增
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "addUserStartRecord", method = RequestMethod.POST)
    public Result addUserStartRecord(@RequestBody UserStarRecordParam param) {
        userStarRecordService.addUserStartRecord(param);
        return successCreated();
    }

    /**
     * 查询用户星星排行榜
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "getStarRankList", method = RequestMethod.POST)
    public Result<Page<UserStarRankListDTO>> getStarRankList(@RequestBody RankListParam param) {
        Page<RankListBO> rankListBOPage = userStarRecordService.getStarRankList(param);
        Page<UserStarRankListDTO> page = new Page<>();
        page.setCurrentPage(rankListBOPage.getCurrentPage());
        page.setTotalCount(rankListBOPage.getTotalCount());
        page.setRecords(UserStarRecordConverter.converterRankDTOS(rankListBOPage.getRecords()));
        return successGet(page);
    }

    /**
     * 获取当前用户排名
     * @return
     */
    @RequestMapping(value = "currentUserRank", method = RequestMethod.GET)
    public Result<RankListDTO> currentUserRank(@RequestParam("userNum") String userNum) {
        UserRankBO rankBO = userStarRecordService.currentUserRank(userNum);
        RankListDTO listDTO = new RankListDTO();
        listDTO.setStarCount(rankBO.getStarCount());
        listDTO.setRank(rankBO.getRank());
        return successGet(listDTO);
    }

    @RequestMapping(value = "editStarStatus/{id}", method = RequestMethod.PUT)
    public Result editStarStatus(@PathVariable("id") Long id , @RequestParam("status") Boolean status){
        userStarRecordService.editStarStatus(id,status);
        return successCreated();
    }

    /**
     * 设置排行榜星星数量
     * @param id
     * @param starCount
     * @return
     */
    @RequestMapping(value = "addStarById/{id}", method = RequestMethod.PUT)
    public Result addStarById(@PathVariable("id") Long id, @RequestParam("starCount") Integer starCount) {
        userStarRecordService.addStarById(id, starCount);
        return successCreated();
    }
}
