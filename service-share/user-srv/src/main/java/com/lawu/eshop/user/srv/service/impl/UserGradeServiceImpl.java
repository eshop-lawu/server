package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.param.UserGradeQuery;
import com.lawu.eshop.user.param.UserGradeUpdateParam;
import com.lawu.eshop.user.srv.bo.UserGradeBO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.UserGradeDO;
import com.lawu.eshop.user.srv.domain.UserGradeDOExample;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.UserGradeDOMapper;
import com.lawu.eshop.user.srv.service.UserGradeService;
import com.lawu.framework.core.page.Page;

@Service
public class UserGradeServiceImpl implements UserGradeService {

    @Autowired
    private UserGradeDOMapper userGradeDOMapper;

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Override
    public Integer selectLotteryActivityPointByGradeValue(Byte gradeValue) {
        UserGradeDOExample example = new UserGradeDOExample();
        example.createCriteria().andGradeValueEqualTo(gradeValue);
        List<UserGradeDO> userGradeList = userGradeDOMapper.selectByExample(example);
        if (userGradeList == null || userGradeList.isEmpty()) {
            return null;
        }
        return userGradeList.get(0).getLotteryActivityPoint();
    }

    @Override
    public UserGradeBO getLotteryGradeInfo(Long userId, Byte gradeValue) {
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(userId);
        if (memberDO == null) {
            return null;
        }
        UserGradeDOExample example = new UserGradeDOExample();
        example.createCriteria().andGradeValueEqualTo(memberDO.getGrade());
        List<UserGradeDO> userGradeDOS = userGradeDOMapper.selectByExample(example);
        example = new UserGradeDOExample();
        example.createCriteria().andGradeValueEqualTo(gradeValue);
        List<UserGradeDO> userGradeDOList = userGradeDOMapper.selectByExample(example);
        if (userGradeDOS.isEmpty() || userGradeDOList.isEmpty()) {
            return null;
        }

        UserGradeBO userGradeBO = new UserGradeBO();
        userGradeBO.setFreeLotteryCount(userGradeDOS.get(0).getFreeLotteryCount());
        userGradeBO.setLotteryActivityPoint(userGradeDOList.get(0).getLotteryActivityPoint());
        return userGradeBO;
    }

    @Override
    public List<UserGradeBO> selectGradeList() {
        UserGradeDOExample example = new UserGradeDOExample();
        example.setOrderByClause(" grade_weight asc ");
        List<UserGradeDO> userGradeList = userGradeDOMapper.selectByExample(example);
        List<UserGradeBO> bos = new ArrayList<>();
        for (UserGradeDO userGradeDO : userGradeList) {
            UserGradeBO bo = new UserGradeBO();
            bo.setId(userGradeDO.getId());
            bo.setGradeName(userGradeDO.getGradeName());
            bo.setGradeValue(userGradeDO.getGradeValue());
            bo.setGradeWeight(userGradeDO.getGradeWeight());
            bo.setLotteryActivityPoint(userGradeDO.getLotteryActivityPoint());
            bo.setMinGrowthValue(userGradeDO.getMinGrowthValue());
            bo.setGmtCreate(userGradeDO.getGmtCreate());
            bo.setGmtModified(userGradeDO.getGmtModified());
            bos.add(bo);
        }
        return bos;
    }

    @Override
    public UserGradeBO selectUserGradeByMinGrowthValue(Integer resultMoney) {
        UserGradeDOExample example = new UserGradeDOExample();
        example.createCriteria().andMinGrowthValueLessThanOrEqualTo(resultMoney);
        example.setOrderByClause(" grade_weight desc ");
        List<UserGradeDO> userGradeList = userGradeDOMapper.selectByExample(example);
        if (userGradeList == null || userGradeList.isEmpty()) {
            return null;
        }
        UserGradeDO userGradeDO = userGradeList.get(0);
        UserGradeBO bo = new UserGradeBO();
        bo.setId(userGradeDO.getId());
        bo.setGradeName(userGradeDO.getGradeName());
        bo.setGradeValue(userGradeDO.getGradeValue());
        bo.setGradeWeight(userGradeDO.getGradeWeight());
        bo.setLotteryActivityPoint(userGradeDO.getLotteryActivityPoint());
        bo.setMinGrowthValue(userGradeDO.getMinGrowthValue());
        bo.setGmtCreate(userGradeDO.getGmtCreate());
        bo.setGmtModified(userGradeDO.getGmtModified());
        return bo;
    }

    @Override
    public Page<UserGradeBO> selectPage(UserGradeQuery query) {
        UserGradeDOExample example = new UserGradeDOExample();
        example.setOrderByClause(" grade_weight asc ");
        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<UserGradeDO> userGradeDOS = userGradeDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Page<UserGradeBO> page = new Page<>();
        List<UserGradeBO> bos = new ArrayList<>();
        for (UserGradeDO userGradeDO : userGradeDOS) {
            UserGradeBO bo = new UserGradeBO();
            bo.setId(userGradeDO.getId());
            bo.setGradeName(userGradeDO.getGradeName());
            bo.setGradeValue(userGradeDO.getGradeValue());
            bo.setGradeWeight(userGradeDO.getGradeWeight());
            bo.setFreeLotteryCount(userGradeDO.getFreeLotteryCount());
            bo.setNextFreeLotteryCount(userGradeDO.getNextFreeLotteryCount());
            bo.setLotteryActivityPoint(userGradeDO.getLotteryActivityPoint());
            bo.setMinGrowthValue(userGradeDO.getMinGrowthValue());
            bo.setGmtCreate(userGradeDO.getGmtCreate());
            bo.setGmtModified(userGradeDO.getGmtModified());
            bos.add(bo);
        }
        page.setRecords(bos);
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(Long.valueOf(userGradeDOMapper.countByExample(example)).intValue());
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(Long id, UserGradeUpdateParam param) {

        int ret = validate(id,param);
        if (ret != ResultCode.SUCCESS) {
            return ret;
        }

        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setId(id);
        userGradeDO.setGradeName(param.getGradeName());
        userGradeDO.setGradeWeight(param.getGradeWeight());
        userGradeDO.setMinGrowthValue(param.getMinGrowthValue());
        userGradeDO.setNextFreeLotteryCount(param.getNextFreeLotteryCount());
        userGradeDO.setLotteryActivityPoint(param.getLotteryActivityPoint());
        userGradeDO.setGmtModified(new Date());
        userGradeDOMapper.updateByPrimaryKeySelective(userGradeDO);

        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(UserGradeUpdateParam param) {

        int ret = validate(null,param);
        if (ret != ResultCode.SUCCESS) {
            return ret;
        }

        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeName(param.getGradeName());
        userGradeDO.setGradeWeight(param.getGradeWeight());
        userGradeDO.setMinGrowthValue(param.getMinGrowthValue());
        userGradeDO.setLotteryActivityPoint(param.getLotteryActivityPoint());
        userGradeDOMapper.insertSelective(userGradeDO);

        return ResultCode.SUCCESS;
    }

    @Override
    public UserGradeBO selectUserGradeById(Long id) {
        UserGradeDO userGradeDO = userGradeDOMapper.selectByPrimaryKey(id);
        if(userGradeDO == null){
            return null;
        }
        UserGradeBO bo = new UserGradeBO();
        bo.setId(userGradeDO.getId());
        bo.setGradeName(userGradeDO.getGradeName());
        bo.setGradeValue(userGradeDO.getGradeValue());
        bo.setGradeWeight(userGradeDO.getGradeWeight());
        bo.setFreeLotteryCount(userGradeDO.getFreeLotteryCount());
        bo.setNextFreeLotteryCount(userGradeDO.getNextFreeLotteryCount());
        bo.setLotteryActivityPoint(userGradeDO.getLotteryActivityPoint());
        bo.setMinGrowthValue(userGradeDO.getMinGrowthValue());
        bo.setGmtCreate(userGradeDO.getGmtCreate());
        bo.setGmtModified(userGradeDO.getGmtModified());
        return bo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFreeLotteryCount() {
        List<UserGradeDO> gradeDOS = userGradeDOMapper.selectByExample(null);
        if (gradeDOS.isEmpty()) {
            return;
        }

        for (UserGradeDO gradeDO : gradeDOS) {
            UserGradeDO userGradeDO = new UserGradeDO();
            userGradeDO.setId(gradeDO.getId());
            userGradeDO.setFreeLotteryCount(gradeDO.getNextFreeLotteryCount());
            userGradeDO.setGmtModified(new Date());
            userGradeDOMapper.updateByPrimaryKeySelective(userGradeDO);
        }
    }

    public int validate(Long id,UserGradeUpdateParam param) {

        boolean bool = false;
        UserGradeDO userGradeDO = null;
        if(id != null){
            userGradeDO = userGradeDOMapper.selectByPrimaryKey(id);
            if(userGradeDO != null && param.getGradeWeight().intValue() == userGradeDO.getGradeWeight().intValue()){
                bool = true;
            }
        }

        UserGradeDOExample example = new UserGradeDOExample();
        if(!bool){
            example.createCriteria().andGradeWeightEqualTo(param.getGradeWeight());
            long count = userGradeDOMapper.countByExample(example);
            if (count > 0) {
                return ResultCode.GRADE_WEIGHT_REPEAT;
            }
        }

        example.clear();
        if(userGradeDO != null){
            //修改时排查自身
            example.createCriteria().andGradeWeightLessThan(param.getGradeWeight()).andGradeWeightNotEqualTo(userGradeDO.getGradeWeight());
        }else{
            example.createCriteria().andGradeWeightLessThan(param.getGradeWeight());
        }
        example.setOrderByClause(" grade_weight desc ");
        List<UserGradeDO> lessThanList = userGradeDOMapper.selectByExample(example);

        example.clear();
        if(userGradeDO != null) {
            //修改时排查自身
            example.createCriteria().andGradeWeightGreaterThan(param.getGradeWeight()).andGradeWeightNotEqualTo(userGradeDO.getGradeWeight());
        }else{
            example.createCriteria().andGradeWeightGreaterThan(param.getGradeWeight());
        }
        example.setOrderByClause(" grade_weight asc ");
        List<UserGradeDO> greaterThanList = userGradeDOMapper.selectByExample(example);

        if (lessThanList != null && !lessThanList.isEmpty() && greaterThanList != null && !greaterThanList.isEmpty()) {
            if (lessThanList.get(0).getMinGrowthValue() >= param.getMinGrowthValue()) {
                return ResultCode.GRADE_MIN_GROWTH_VALUE_LESSTHAN_ERROR;
            }
            if (greaterThanList.get(0).getMinGrowthValue() <= param.getMinGrowthValue()) {
                return ResultCode.GRADE_MIN_GROWTH_VALUE_GRETERTHAN_ERROR;
            }
            if (lessThanList.get(0).getLotteryActivityPoint() >= param.getLotteryActivityPoint()) {
                return ResultCode.GRADE_LOTTERY_ACTIVITY_POINT_LESSTHAN_ERROR;
            }
            if (greaterThanList.get(0).getLotteryActivityPoint() <= param.getLotteryActivityPoint()) {
                return ResultCode.GRADE_LOTTERY_ACTIVITY_POINT_GRETERTHAN_ERROR;
            }
        }else if(lessThanList == null || lessThanList.isEmpty()){
            if (greaterThanList.get(0).getMinGrowthValue() <= param.getMinGrowthValue()) {
                return ResultCode.GRADE_MIN_GROWTH_VALUE_GRETERTHAN_ERROR;
            }
            if (greaterThanList.get(0).getLotteryActivityPoint() <= param.getLotteryActivityPoint()) {
                return ResultCode.GRADE_LOTTERY_ACTIVITY_POINT_GRETERTHAN_ERROR;
            }
        }else {
            if (lessThanList.get(0).getMinGrowthValue() >= param.getMinGrowthValue()) {
                return ResultCode.GRADE_MIN_GROWTH_VALUE_LESSTHAN_ERROR;
            }
            if (lessThanList.get(0).getLotteryActivityPoint() >= param.getLotteryActivityPoint()) {
                return ResultCode.GRADE_LOTTERY_ACTIVITY_POINT_LESSTHAN_ERROR;
            }
        }
        return ResultCode.SUCCESS;
    }
}
