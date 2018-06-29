package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicUploadNumberBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleUserPicConverter;
import com.lawu.eshop.game.srv.domain.GamePuzzlePicDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleUserPicDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleUserPicDOExample;
import com.lawu.eshop.game.srv.mapper.GamePuzzlePicDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleUserPicDOMapper;
import com.lawu.eshop.game.srv.service.GamePuzzleUserPicService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
@Service
public class GamePuzzleUserPicServiceImpl implements GamePuzzleUserPicService {

    @Autowired
    private GamePuzzleUserPicDOMapper gamePuzzleUserPicDOMapper;

    @Autowired
    private GamePuzzlePicDOMapper gamePuzzlePicDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGamePuzzleUserPic(GamePuzzleParam param) {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setUserNum(param.getUserNum());
        picDO.setUserNickname(param.getUserNickname());
        picDO.setImgPath(param.getImgPath());
        picDO.setType(param.getTypeEnum().getVal());
        picDO.setIsSimple(param.getIsSimple());
        picDO.setIsCommon(param.getIsCommon());
        picDO.setIsHard(param.getIsHard());
        picDO.setStatus(param.getUserPicStatusEnum().getVal());
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());
        gamePuzzleUserPicDOMapper.insertSelective(picDO);
    }

    @Override
    public GamePuzzleUserPicUploadNumberBO getGamePuzzleUserPicUploadNumber(String userNum) {
        GamePuzzleUserPicDOExample example = new GamePuzzleUserPicDOExample();
        GamePuzzleUserPicDOExample.Criteria criteria = example.createCriteria();
        //查询上传次数
        criteria.andUserNumEqualTo(userNum).andTypeEqualTo(GamePuzzleTypeEnum.PUZZLE.getVal());
        int uploadNumber = (int) gamePuzzleUserPicDOMapper.countByExample(example);
        //查询采用次数
        criteria.andStatusEqualTo(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        int usedNumber = (int) gamePuzzleUserPicDOMapper.countByExample(example);

        GamePuzzleUserPicUploadNumberBO numberBO = new GamePuzzleUserPicUploadNumberBO();
        numberBO.setUploadNumber(uploadNumber);
        numberBO.setUsedNumber(usedNumber);
        return numberBO;
    }

    @Override
    public Page<GamePuzzleUserPicBO> listGamePuzzleUserPic(GamePuzzleUserPicRealQuery query) {
        GamePuzzleUserPicDOExample example = new GamePuzzleUserPicDOExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andUserNumEqualTo(query.getUserNum()).andTypeEqualTo(GamePuzzleTypeEnum.PUZZLE.getVal());
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<GamePuzzleUserPicDO> picDOS = gamePuzzleUserPicDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<GamePuzzleUserPicBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) gamePuzzleUserPicDOMapper.countByExample(example));
        page.setRecords(GamePuzzleUserPicConverter.converBOS(picDOS));
        return page;
    }

    @Override
    public Page<GamePuzzleUserPicBO> listOperatorGamePuzzleUserPic(OperatorGamePuzzleUserPicQuery query) {
        GamePuzzleUserPicDOExample example = new GamePuzzleUserPicDOExample();
        GamePuzzleUserPicDOExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(GamePuzzleTypeEnum.PUZZLE.getVal());
        if (StringUtils.isNotEmpty(query.getUserNickname())) {
            criteria.andUserNicknameLike("%" + query.getUserNickname() + "%");
        }
        if (StringUtils.isNotEmpty(query.getBeginTime())) {
            Date beginDate = DateUtil.formatDate(query.getBeginTime() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            criteria.andGmtCreateGreaterThanOrEqualTo(beginDate);
        }
        if (StringUtils.isNotEmpty(query.getEndTime())) {
            Date endDate = DateUtil.formatDate(query.getEndTime() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            criteria.andGmtCreateLessThanOrEqualTo(endDate);
        }
        if (query.getStatusEnum() != null) {
            criteria.andStatusEqualTo(query.getStatusEnum().getVal());
        }

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<GamePuzzleUserPicDO> picDOS = gamePuzzleUserPicDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Page<GamePuzzleUserPicBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount((int) gamePuzzleUserPicDOMapper.countByExample(example));
        page.setRecords(GamePuzzleUserPicConverter.converBOS(picDOS));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGamePuzzleUserPicStatus(Long id, GamePuzzleUserPicStatusEnum statusEnum) {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setId(id);
        picDO.setStatus(statusEnum.getVal());
        picDO.setGmtModified(new Date());
        gamePuzzleUserPicDOMapper.updateByPrimaryKeySelective(picDO);

        if (statusEnum.equals(GamePuzzleUserPicStatusEnum.HAVE_USE)) {
            picDO = gamePuzzleUserPicDOMapper.selectByPrimaryKey(id);
            GamePuzzlePicDO gamePuzzlePicDO = new GamePuzzlePicDO();
            gamePuzzlePicDO.setUserNum(picDO.getUserNum());
            gamePuzzlePicDO.setUserNickname(picDO.getUserNickname());
            gamePuzzlePicDO.setImgPath(picDO.getImgPath());
            gamePuzzlePicDO.setType(picDO.getType());
            gamePuzzlePicDO.setIsSimple(picDO.getIsSimple());
            gamePuzzlePicDO.setIsCommon(picDO.getIsCommon());
            gamePuzzlePicDO.setIsHard(picDO.getIsHard());
            gamePuzzlePicDO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
            gamePuzzlePicDO.setGmtModified(new Date());
            gamePuzzlePicDO.setGmtCreate(new Date());
            gamePuzzlePicDOMapper.insertSelective(gamePuzzlePicDO);
        }
    }

}
