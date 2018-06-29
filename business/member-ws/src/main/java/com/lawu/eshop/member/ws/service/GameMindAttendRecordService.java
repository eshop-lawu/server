package com.lawu.eshop.member.ws.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.dto.GameMindParticipateGameDTO;
import com.lawu.eshop.game.param.ParticipateGameMindParam;
import com.lawu.framework.web.Result;

/**
 * 头脑PK参与记录服务接口
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@FeignClient(value = "game-srv", path = "gameMindAttendRecord/")
public interface GameMindAttendRecordService {

    /**
     * 两人或多人参与游戏生成参与记录,并且扣除积分
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "participateGame", method = RequestMethod.POST)
    Result<GameMindParticipateGameDTO> participateGame(@RequestBody ParticipateGameMindParam param);
    
    /**
     * 同步缓存中的数据到数据库
     *
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "synchronizeDataFormCache", method = RequestMethod.POST)
    Result synchronizeDataFormCache(@RequestParam("attendNum") String attendNum);
    
    /**
     * 用户退出, 清除数据库中和缓存中的数据<p>
     * 1.用户主动退出<p>
     * 2.用户杀掉进程<p>
     * 
     * @param userNum
     * @param groupNum
     * @author jiangxinjun
     * @createDate 2018年3月20日
     * @updateDate 2018年3月20日
     */
    @RequestMapping(value = "quit", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> quit(@RequestParam("userNum") String userNum, @RequestParam("groupNum") String groupNum);

    /**
     * 根据分组编号查询参与游戏的用户编号(完成动力任务)
     *
     * @param groupNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getAttendRecordUserNums", method = RequestMethod.GET)
    Result<List<String>> getAttendRecordUserNums(@RequestParam("groupNum") String groupNum);

}
