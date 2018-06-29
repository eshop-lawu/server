package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.eshop.game.srv.GameSrvConfig;
import com.lawu.eshop.game.srv.bo.GameRoomBO;
import com.lawu.eshop.game.srv.bo.GameRoomCreateBO;
import com.lawu.eshop.game.srv.bo.StartTheGameBO;
import com.lawu.eshop.game.srv.converter.GameRoomConverter;
import com.lawu.eshop.game.srv.domain.GameRoomDO;
import com.lawu.eshop.game.srv.domain.GameRoomDOExample;
import com.lawu.eshop.game.srv.mapper.GameRoomDOMapper;
import com.lawu.eshop.game.srv.service.GameRoomCacheService;
import com.lawu.eshop.game.srv.service.GameRoomService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@Service
public class GameRoomServiceImpl extends BaseController implements GameRoomService {

    @Autowired
    private GameRoomCacheService gameRoomCacheService;

    @Autowired
    private GameRoomDOMapper gameRoomDOMapper;

    @Autowired
    private GameSrvConfig gameSrvConfig;
    Logger logger = LoggerFactory.getLogger(GameRoomServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomCreateBO saveGameRoom(GameRoomParam param) {
        GameRoomDOExample roomDOExample = new GameRoomDOExample();
        roomDOExample.createCriteria().andUserNumEqualTo(param.getUserNum()).andTypeEqualTo(param.getTypeEnum().getVal()).andStatusNotEqualTo(GameRoomStatusEnum.FINISHED.getVal());
        List<GameRoomDO> roomDOS = gameRoomDOMapper.selectByExample(roomDOExample);
        if (!roomDOS.isEmpty()) {
            for (GameRoomDO roomDO : roomDOS) {
                roomDO.setPlayerInfo("");
                roomDO.setStatus(GameRoomStatusEnum.FINISHED.getVal());
                roomDO.setGmtModified(new Date());
                gameRoomDOMapper.updateByPrimaryKeySelective(roomDO);
                gameRoomCacheService.recycleGameRoomNum(GameTypeEnum.getEnum(roomDO.getType()).toString(), roomDO.getRoomNum());
            }
        }

        Result<String> roomNumResult;
        Boolean saveRoomResult;
        while (true) {
            roomNumResult = gameRoomCacheService.getGameRoomNum(param.getTypeEnum().toString(), gameSrvConfig.getGameRoomPageNumber());
            saveRoomResult = saveGameRoom(param, roomNumResult.getModel());
            if (saveRoomResult) {
                break;
            }
        }

        roomDOExample = new GameRoomDOExample();
        roomDOExample.createCriteria().andUserNumEqualTo(param.getUserNum()).andRoomNumEqualTo(roomNumResult.getModel()).andTypeEqualTo(param.getTypeEnum().getVal());
        roomDOS = gameRoomDOMapper.selectByExample(roomDOExample);
        GameRoomCreateBO bo = new GameRoomCreateBO();
        bo.setRoomId(roomDOS.get(0).getId());
        bo.setRoomNum(roomDOS.get(0).getRoomNum());
        return bo;
    }

    @Override
    public Page<GameRoomBO> listGameRoom(GameRoomQuery query) {
        GameRoomDOExample example = new GameRoomDOExample();
        example.setOrderByClause("gmt_modified desc");
        GameRoomDOExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(query.getTypeEnum().getVal()).andStatusEqualTo(GameRoomStatusEnum.WAITING.getVal());
        if (StringUtils.isNotEmpty(query.getRoomNum())) {
            criteria.andRoomNumEqualTo(query.getRoomNum());
        }
        if (query.getMinPoint() != null) {
            criteria.andPointGreaterThanOrEqualTo(query.getMinPoint());
        }
        if (query.getMaxPoint() != null) {
            criteria.andPointLessThanOrEqualTo(query.getMaxPoint());
        }
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());

        List<GameRoomDO> roomDOS = gameRoomDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Page<GameRoomBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) gameRoomDOMapper.countByExample(example));
        page.setRecords(GameRoomConverter.converBOS(roomDOS));
        return page;
    }

    @Override
    public GameRoomBO getGameRoom(Long id) {
        GameRoomDO roomDO = gameRoomDOMapper.selectByPrimaryKey(id);
        return GameRoomConverter.converBO(roomDO);
    }

    @Override
    public Boolean verifyGameRoomPwd(Long id, String pwd) {
        GameRoomDO roomDO = gameRoomDOMapper.selectByPrimaryKey(id);
        if (roomDO == null) {
            return false;
        }
        if (pwd.equals(roomDO.getPwd())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomDetailsDTO joinGameRoom(JoinGameRoomParam param) {
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        GameRoomDOExample gameRoomDOExample = new GameRoomDOExample();
        gameRoomDOExample.createCriteria().andRoomNumEqualTo(param.getRoomNum())
        .andTypeEqualTo(param.getGameType().getVal())
        .andStatusIn(Arrays.asList(GameRoomStatusEnum.WAITING.getVal(), GameRoomStatusEnum.PLAYING.getVal()));
        List<GameRoomDO> roomDOList = gameRoomDOMapper.selectByExample(gameRoomDOExample);
        if (roomDOList.isEmpty()) {
            logger.info("加入房间的时候房间不存在joinGameRoom");
            throw new DataNotExistException("房间不存在");
        }
        GameRoomDO roomDO = roomDOList.get(0);
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setGameType(param.getGameType());
        gameRoomOperationParam.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg(param.getHeadImg());
        gameRoomPlayerInfoDTO.setUserNum(param.getUserNum());
        boolean isHomeowner = roomDO.getUserNum().equals(param.getUserNum());
        gameRoomPlayerInfoDTO.setIsRoomHost(isHomeowner);
        gameRoomPlayerInfoDTO.setNickName(param.getNickName());
        gameRoomPlayerInfoDTO.setRegionName(param.getRegionName());
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        
        GameRoomDO gameRoomUpdateDO = new GameRoomDO();
        // 如果是房主
        if (isHomeowner) {
            // 房主默认是准备状态
            gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
            Result<GameRoomDetailsDTO> roomResult = gameRoomCacheService.createGameRoom(gameRoomOperationParam);
            if (!isSuccess(roomResult)) {
                throw new WrongOperationException(roomResult.getRet(), roomResult.getMsg());
            }
            rtn = roomResult.getModel();
            // 如果当前房间的状态是游戏中, 则更改当前房间的状态为等待中
            if (GameRoomStatusEnum.PLAYING == GameRoomStatusEnum.getEnum(roomDO.getStatus())) {
                gameRoomUpdateDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
            }
        } else {
            // 成员加入房间默认是未准备状态
            gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
            Result<GameRoomDetailsDTO> playerInfoResult = gameRoomCacheService.joinGameRoom(gameRoomOperationParam);
            if (!isSuccess(playerInfoResult)) {
                throw new WrongOperationException(playerInfoResult.getRet(), playerInfoResult.getMsg());
            }
            rtn = playerInfoResult.getModel();
        }
        // 根据加入时间排序
        rtn.getPlayerInfos().sort((o1, o2) -> {
            return o1.getJoinTime().compareTo(o2.getJoinTime());
        });
        // 更新用户信息到数据库
        gameRoomUpdateDO.setId(roomDO.getId());
        gameRoomUpdateDO.setPlayerInfo(JSONArray.toJSONString(rtn.getPlayerInfos()));
        gameRoomUpdateDO.setGmtModified(new Date());
        gameRoomDOMapper.updateByPrimaryKeySelective(gameRoomUpdateDO);
        return rtn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomDetailsDTO exitGameRoom(ExitGameRoomParam param) {
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setCurrentUserNum(param.getCurrentUserNum());
        gameRoomOperationParam.setGameType(param.getGameType());
        gameRoomOperationParam.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setUserNum(param.getUserNum());
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        Result<GameRoomDetailsDTO> playerInfoResult = gameRoomCacheService.exitGameRoom(gameRoomOperationParam);
        if (!isSuccess(playerInfoResult)) {
            throw new WrongOperationException(playerInfoResult.getRet(), playerInfoResult.getMsg());
        }
        GameRoomDetailsDTO rtn = playerInfoResult.getModel();
        // 更新房间信息
        GameRoomDOExample gameRoomDOExample = new GameRoomDOExample();
        List<Byte> statusList = new ArrayList<>();
        statusList.add(GameRoomStatusEnum.WAITING.getVal());
        statusList.add(GameRoomStatusEnum.PLAYING.getVal());
        gameRoomDOExample.createCriteria().andTypeEqualTo(param.getGameType().getVal())
        .andRoomNumEqualTo(param.getRoomNum()).andStatusIn(statusList);
        GameRoomDO gameRoomDOUpdate = new GameRoomDO();
        // 判断房间是否已经解散, 如果房主退出, 更新房间状态为结束
        if (!rtn.getIsDissolution()) {
            String jsonStr = (rtn.getPlayerInfos() != null && !rtn.getPlayerInfos().isEmpty()) ? JSONArray.toJSONString(rtn.getPlayerInfos()) : "";
            gameRoomDOUpdate.setPlayerInfo(jsonStr);
        } else {
            // 设置玩家信息为空
            gameRoomDOUpdate.setPlayerInfo("");
            // 设置房间为已结束
            gameRoomDOUpdate.setStatus(GameRoomStatusEnum.FINISHED.getVal());
        }
        gameRoomDOUpdate.setGmtModified(new Date());
        gameRoomDOMapper.updateByExampleSelective(gameRoomDOUpdate, gameRoomDOExample);
        return rtn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameRoomDetailsDTO updateGameRoomPlayerReadyStatus(UpdateGameRoomPlayerReadyStatusParam param) {
        GameRoomDOExample gameRoomDOExample = new GameRoomDOExample();
        gameRoomDOExample.createCriteria().andRoomNumEqualTo(param.getRoomNum())
        .andTypeEqualTo(param.getGameType().getVal())
        .andStatusEqualTo(GameRoomStatusEnum.WAITING.getVal());
        List<GameRoomDO> roomDOList = gameRoomDOMapper.selectByExample(gameRoomDOExample);
        if (roomDOList.isEmpty()) {
            throw new DataNotExistException("房间不存在,或者已经开始");
        }
        GameRoomDO roomDO = roomDOList.get(0);
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setGameType(param.getGameType());
        gameRoomOperationParam.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setUserNum(param.getUserNum());
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        Result<GameRoomDetailsDTO> playerInfoResult = gameRoomCacheService.updateGameRoomPlayerReadyStatus(gameRoomOperationParam);
        if (!isSuccess(playerInfoResult)) {
            throw new WrongOperationException(playerInfoResult.getRet(), playerInfoResult.getMsg());
        }
        GameRoomDetailsDTO rtn = playerInfoResult.getModel();
        GameRoomDO gameRoomDO = new GameRoomDO();
        gameRoomDO.setId(roomDO.getId());
        gameRoomDO.setPlayerInfo(JSONArray.toJSONString(rtn.getPlayerInfos()));
        gameRoomDO.setGmtModified(new Date());
        gameRoomDOMapper.updateByPrimaryKeySelective(gameRoomDO);
        return rtn;
    }

    private Boolean saveGameRoom(GameRoomParam param, String roomNum) {
        try {
            GameRoomDO roomDO = new GameRoomDO();
            roomDO.setUserNum(param.getUserNum());
            roomDO.setAccount(param.getAccount());
            roomDO.setRoomNum(roomNum);
            roomDO.setPlayerInfo("[" + param.getPlayerInfo() + "]");
            roomDO.setPoint(param.getPoint());
            roomDO.setPwd(param.getPwd() == null ? "" : param.getPwd());
            roomDO.setType(param.getTypeEnum().getVal());
            roomDO.setStatus(param.getStatusEnum().getVal());
            if (param.getTypeEnum().equals(GameTypeEnum.PUZZLE)) {
                roomDO.setHardLevel(param.getHardLevelEnum().getVal());
            }
            roomDO.setGmtModified(new Date());

            GameRoomDOExample roomDOExample = new GameRoomDOExample();
            roomDOExample.createCriteria().andRoomNumEqualTo(roomNum).andTypeEqualTo(param.getTypeEnum().getVal()).andStatusEqualTo(GameRoomStatusEnum.FINISHED.getVal());
            int result = gameRoomDOMapper.updateByExampleSelective(roomDO, roomDOExample);
            if (result > 0) {
                return true;
            } else {
                roomDO.setGmtCreate(new Date());
                gameRoomDOMapper.insertSelective(roomDO);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public StartTheGameBO startTheGame(String roomNum, GameTypeEnum gameType) {
        StartTheGameBO rtn = new StartTheGameBO();
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setGameType(gameType);
        gameRoomOperationParam.setRoomNum(roomNum);
        Result<GameRoomDetailsDTO> getGameRoomAllPlayerResult = gameRoomCacheService.getGameRoomAllPlayer(gameRoomOperationParam);
        if (!isSuccess(getGameRoomAllPlayerResult)) {
            throw new WrongOperationException(getGameRoomAllPlayerResult.getRet(), getGameRoomAllPlayerResult.getMsg());
        }
        GameRoomDetailsDTO getGameRoomAllPlayerModel = getGameRoomAllPlayerResult.getModel();
        if (getGameRoomAllPlayerModel.getPlayerInfos().size() < 1) {
            throw new WrongOperationException("至少需要两人才能开始游戏");
        }
        List<StartTheGameUserDTO> userInfos = new ArrayList<>(getGameRoomAllPlayerModel.getPlayerInfos().size());
        for (GameRoomPlayerInfoDTO item : getGameRoomAllPlayerModel.getPlayerInfos()) {
            if (GameRoomPlayerStatusEnum.NOT_READY == item.getStatusEnum()) {
                throw new WrongOperationException("房间内有用户未准备");
            }
            StartTheGameUserDTO startTheGameUserDTO = new StartTheGameUserDTO();
            startTheGameUserDTO.setUserNum(item.getUserNum());
            startTheGameUserDTO.setIsHomeowner(item.getIsRoomHost());
            userInfos.add(startTheGameUserDTO);
        }
        rtn.setUserInfos(userInfos);
        // 更新房间的状态为进行中
        GameRoomDOExample gameRoomDOExample = new GameRoomDOExample();
        gameRoomDOExample.createCriteria().andRoomNumEqualTo(roomNum).andStatusEqualTo(GameRoomStatusEnum.WAITING.getVal());
        List<GameRoomDO> gameRoomDOList = gameRoomDOMapper.selectByExample(gameRoomDOExample);
        if (gameRoomDOList == null || gameRoomDOList.isEmpty()) {
            throw new DataNotExistException("房间信息不存在, 或者已开始");
        }
        GameRoomDO gameRoomDO = gameRoomDOList.get(0);
        rtn.setAttendPoint(gameRoomDO.getPoint());
        GameRoomDO recordUpdate = new GameRoomDO();
        recordUpdate.setStatus(GameRoomStatusEnum.PLAYING.getVal());
        recordUpdate.setGmtModified(new Date());
        gameRoomDOMapper.updateByExampleSelective(recordUpdate, gameRoomDOExample);
        return rtn;
    }

    @Override
    public GameRoomBO getGameRoomByRoomNum(String roomNum, GameTypeEnum typeEnum) {
        GameRoomDOExample example = new GameRoomDOExample();
        example.createCriteria().andRoomNumEqualTo(roomNum).andTypeEqualTo(typeEnum.getVal()).andStatusNotEqualTo(GameRoomStatusEnum.FINISHED.getVal());
        List<GameRoomDO> roomDOS = gameRoomDOMapper.selectByExample(example);
        if (roomDOS.isEmpty()) {
            return null;
        }
        return GameRoomConverter.converBO(roomDOS.get(0));
    }

    @Override
    public GameRoomBO getGameRoomByUserNum(String userNum, GameTypeEnum typeEnum) {
        GameRoomDOExample example = new GameRoomDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andTypeEqualTo(typeEnum.getVal()).andStatusNotEqualTo(GameRoomStatusEnum.FINISHED.getVal());
        List<GameRoomDO> roomDOS = gameRoomDOMapper.selectByExample(example);
        if (roomDOS.isEmpty()) {
            return null;
        }
        return GameRoomConverter.converBO(roomDOS.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeInvalidGameRoom() {
        GameRoomDOExample example = new GameRoomDOExample();
        example.createCriteria().andStatusNotEqualTo(GameRoomStatusEnum.FINISHED.getVal());
        List<GameRoomDO> roomDOS = gameRoomDOMapper.selectByExample(example);
        if (roomDOS.isEmpty()) {
            return;
        }

        Date date = new Date();
        for (GameRoomDO roomDO : roomDOS) {
            if (StringUtils.isEmpty(roomDO.getPlayerInfo()) || DateUtil.isExceeds(roomDO.getGmtModified(), date, 1, Calendar.HOUR)) {
                roomDO.setPlayerInfo("");
                roomDO.setStatus(GameRoomStatusEnum.FINISHED.getVal());
                roomDO.setGmtModified(date);
                gameRoomDOMapper.updateByPrimaryKeySelective(roomDO);
                gameRoomCacheService.recycleGameRoomNum(GameTypeEnum.getEnum(roomDO.getType()).toString(), roomDO.getRoomNum());
            }
        }
    }

}
