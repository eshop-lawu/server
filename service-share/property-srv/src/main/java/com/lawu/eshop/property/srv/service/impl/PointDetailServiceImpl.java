package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.property.constants.PayTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.eshop.property.param.PointDetailReportParam;
import com.lawu.eshop.property.param.PointDetailSaveDataParam;
import com.lawu.eshop.property.param.PropertyInfoDataQueryPointDetailParam;
import com.lawu.eshop.property.param.ReportAdPointParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.param.TransactionDetailQueryForBackageParam;
import com.lawu.eshop.property.srv.bo.AreaPointConsumeBO;
import com.lawu.eshop.property.srv.bo.IncomeMsgBO;
import com.lawu.eshop.property.srv.bo.PointConsumeReportBO;
import com.lawu.eshop.property.srv.bo.PointDetailBO;
import com.lawu.eshop.property.srv.bo.ReportAdPointGroupByAreaBO;
import com.lawu.eshop.property.srv.converter.PointDetailConverter;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.eshop.property.srv.domain.PointDetailDOExample;
import com.lawu.eshop.property.srv.domain.PointDetailDOExample.Criteria;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.extend.AreaPointConsumeDOView;
import com.lawu.eshop.property.srv.domain.extend.IncomeMsgDOView;
import com.lawu.eshop.property.srv.domain.extend.IncomeMsgExample;
import com.lawu.eshop.property.srv.domain.extend.PointDOView;
import com.lawu.eshop.property.srv.domain.extend.PointReportDOView;
import com.lawu.eshop.property.srv.domain.extend.ReportAdPointGroupByAreaView;
import com.lawu.eshop.property.srv.mapper.PointDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.extend.PointDetailDOMapperExtend;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.framework.core.page.Page;

/**
 * 积分明细服务实现
 *
 * @author Sunny
 * @date 2017/3/30
 */
@Service
public class PointDetailServiceImpl implements PointDetailService {

	@Autowired
	private PointDetailDOMapper pointDetailDOMapper;
	@Autowired
	private PropertyInfoDOMapper propertyInfoDOMapper;
	@Autowired
	private PointDetailDOMapperExtend pointDetailDOMapperExtend;

	/**
	 * 根据用户编号、查询参数分页查询积分明细
	 * 
	 * @param userNum
	 *            用户编号
	 * @param pointDetailQueryParam
	 *            查询参数
	 * @return
	 */
	@Override
	public Page<PointDetailBO> findPageByUserNum(String userNum, PointDetailQueryParam pointDetailQueryParam) {
		PointDetailDOExample pointDetailDOExample = new PointDetailDOExample();
		Criteria criteria = pointDetailDOExample.createCriteria();
		criteria.andUserNumEqualTo(userNum);

		Integer count = pointDetailDOMapper.countByExample(pointDetailDOExample);
		
		Page<PointDetailBO> page = new Page<>();
		page.setCurrentPage(pointDetailQueryParam.getCurrentPage());
		page.setTotalCount(count.intValue());
		
		// 如果返回的总记录为0，直接返回page
		if (count <= 0 || pointDetailQueryParam.getOffset() >= count) {
			return page;
		}

		pointDetailDOExample.setOrderByClause("gmt_create desc");
		RowBounds rowBounds = new RowBounds(pointDetailQueryParam.getOffset(), pointDetailQueryParam.getPageSize());

		List<PointDetailDO> list = pointDetailDOMapper.selectByExampleWithRowbounds(pointDetailDOExample, rowBounds);

		page.setRecords(PointDetailConverter.convertBOS(list));
		list = null;
		
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int save(PointDetailSaveDataParam param) {
		PropertyInfoDOExample example = new PropertyInfoDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<PropertyInfoDO> propertyInfoList = propertyInfoDOMapper.selectByExample(example);

		PointDetailDO pointDetailDO = new PointDetailDO();
		pointDetailDO.setTitle(param.getTitle());
		pointDetailDO.setPointNum(IdWorkerHelperImpl.generate(BizIdType.POINT));
		pointDetailDO.setUserNum(param.getUserNum());
		pointDetailDO.setPointType(param.getPointType());
		pointDetailDO.setPoint(param.getPoint());
		pointDetailDO.setDirection(param.getDirection());
		pointDetailDO.setBizId(param.getBizId());
		pointDetailDO.setRemark(param.getRemark());
		pointDetailDO.setGmtCreate(param.getGmtExecute() == null ? new Date() : param.getGmtExecute());
		pointDetailDO.setPreviousPoint((propertyInfoList == null || propertyInfoList.isEmpty()) ? new BigDecimal(0) : propertyInfoList.get(0).getPoint());
		//保存省市区用于代理商区域统计
		if(param.getRegionPath() != null && !"".equals(param.getRegionPath())){
			String[] regions = param.getRegionPath().split("/");
			pointDetailDO.setProvinceId(regions.length > 0 ? Integer.valueOf(regions[0]) : 0);
			pointDetailDO.setCityId(regions.length > 1 ? Integer.valueOf(regions[1]) : 0);
			pointDetailDO.setAreaId(regions.length > 2 ? Integer.valueOf(regions[2]) : 0);
		}
		pointDetailDOMapper.insertSelective(pointDetailDO);
		return ResultCode.SUCCESS;
	}

    @Override
    public Page<PointDetailBO> getBackagePointPageList(TransactionDetailQueryForBackageParam param) {
		PointDetailDOExample pointDetailDOExample = new PointDetailDOExample();
		Criteria criteria = pointDetailDOExample.createCriteria();
		if(StringUtils.isNotEmpty(param.getUserNum())){
			criteria.andUserNumEqualTo(param.getUserNum());
		}

		List<Byte> transactionTypeList = new ArrayList<>();
		if(param.getMemberTransactionType() == null && param.getMerchantTransactionType() == null){
			transactionTypeList.add(MemberTransactionTypeEnum.BACKAGE.getValue());
			transactionTypeList.add(MemberTransactionTypeEnum.RECHARGE_POINT.getValue());
			transactionTypeList.add(MerchantTransactionTypeEnum.BACKAGE.getValue());
			transactionTypeList.add(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE.getValue());
			criteria.andPointTypeIn(transactionTypeList);
		}else{
			if (param.getMemberTransactionType() != null) {
				transactionTypeList.add(MemberTransactionTypeEnum.BACKAGE.getValue());
				transactionTypeList.add(MemberTransactionTypeEnum.RECHARGE_POINT.getValue());
				criteria.andPointTypeIn(transactionTypeList);
			}
			if(param.getMerchantTransactionType() != null){
				transactionTypeList.add(MerchantTransactionTypeEnum.BACKAGE.getValue());
				transactionTypeList.add(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE.getValue());
				criteria.andPointTypeIn(transactionTypeList);
			}
		}

		Integer count = pointDetailDOMapper.countByExample(pointDetailDOExample);

		Page<PointDetailBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		page.setTotalCount(count.intValue());

		// 如果返回的总记录为0，直接返回page
		if (page.getTotalCount() == null || page.getTotalCount() <= 0) {
			return page;
		}

		pointDetailDOExample.setOrderByClause("gmt_create desc");
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

		List<PointDetailDO> list = pointDetailDOMapper.selectByExampleWithRowbounds(pointDetailDOExample, rowBounds);

		page.setRecords(PointDetailConverter.convertBOS(list));

		return page;
    }

    /**
     * 修改积分统计
     * @author zhangrc 
     * @date 2017/11/14
     */
	@Override
	public PointConsumeReportBO selectPointDetailListByDateAndDirection(PointDetailReportParam param) {

		String begin= param.getDate() + " 00:00:00";
        String end= param.getDate() + " 23:59:59";
        PointReportDOView  pointReportDOView = new PointReportDOView();
        pointReportDOView.setBdate(begin);
        pointReportDOView.setEdate(end);
        pointReportDOView.setDirection(param.getDirection());
        List<PointDOView> rntList = pointDetailDOMapperExtend.selectPointDetailListByDateAndDirection(pointReportDOView);
        BigDecimal memberRechargeMoney = new BigDecimal(0);
        BigDecimal merchantRechargeMoney = new BigDecimal(0);
        PointConsumeReportBO bo = new PointConsumeReportBO();
        if(rntList.isEmpty()){
        	bo.setMemberRechargeMoney(memberRechargeMoney);
        	bo.setMerchantRechargeMoney(merchantRechargeMoney);
        }
        for (PointDOView rdo : rntList) {
            if(rdo.getUserType().equals(UserCommonConstant.MEMBER_NUM_TAG)){
            	bo.setMemberRechargeMoney(rdo.getSumRechargeMoney());
            	if(rdo.getSumRechargeMoney()==null){
            		bo.setMemberRechargeMoney(BigDecimal.valueOf(0));
                }
            	memberRechargeMoney=bo.getMemberRechargeMoney();
            	
            }else{
            	bo.setMerchantRechargeMoney(rdo.getSumRechargeMoney());
            	if(rdo.getSumRechargeMoney()==null){
            		bo.setMerchantRechargeMoney(BigDecimal.valueOf(0));
                }
            	merchantRechargeMoney=bo.getMerchantRechargeMoney();
            } 
            
        }
        bo.setSumRechargeMoney(merchantRechargeMoney.add(memberRechargeMoney));
        
		return bo;
	}

	@Override
	public PointConsumeReportBO selectPointDetailListByDateAndDirectionAndPointType(PointDetailReportParam param) {
		String begin= param.getDate() + " 00:00:00";
        String end= param.getDate() + " 23:59:59";
        PointReportDOView  pointReportDOView = new PointReportDOView();
        pointReportDOView.setBdate(begin);
        pointReportDOView.setEdate(end);
        pointReportDOView.setDirection(param.getDirection());
        pointReportDOView.setPointType(param.getPointType());
        List<PointDOView> rntList = pointDetailDOMapperExtend.selectPointDetailListByDateAndDirectionAndPointType(pointReportDOView);
        BigDecimal memberRechargeMoney = new BigDecimal(0);
        BigDecimal merchantRechargeMoney = new BigDecimal(0);
        PointConsumeReportBO bo = new PointConsumeReportBO();
        if(rntList.isEmpty()){
        	bo.setMemberRechargeMoney(memberRechargeMoney);
        	bo.setMerchantRechargeMoney(merchantRechargeMoney);
        }
        for (PointDOView rdo : rntList) {
        	if(rdo.getUserType().equals(UserCommonConstant.MEMBER_NUM_TAG)){
            	bo.setMemberRechargeMoney(rdo.getSumRechargeMoney());
            	if(rdo.getSumRechargeMoney()==null){
            		bo.setMemberRechargeMoney(BigDecimal.valueOf(0));
                }
            	memberRechargeMoney=bo.getMemberRechargeMoney();
            	
            }else{
            	bo.setMerchantRechargeMoney(rdo.getSumRechargeMoney());
            	if(rdo.getSumRechargeMoney()==null){
            		bo.setMerchantRechargeMoney(BigDecimal.valueOf(0));
                }
            	merchantRechargeMoney=bo.getMerchantRechargeMoney();
            }
            
        }
        bo.setSumRechargeMoney(merchantRechargeMoney.add(memberRechargeMoney));
		return bo;
	}

	@Override
	public List<ReportAdPointGroupByAreaBO> getReportAdPointGroupByArea(ReportAdPointParam param) {
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<ReportAdPointGroupByAreaView> list = pointDetailDOMapperExtend.getReportAdPointGroupByArea(param.getBdate(), param.getEdate(),rowBounds);
		List<ReportAdPointGroupByAreaBO> rntList = new ArrayList<ReportAdPointGroupByAreaBO>();
		for(ReportAdPointGroupByAreaView r : list) {
			ReportAdPointGroupByAreaBO BO = new ReportAdPointGroupByAreaBO();
			BO.setAreaId(r.getAreaId());
			BO.setTotalPoint(r.getTotalPoint());
			rntList.add(BO);
		}
		return rntList;
	}

	@Override
	public List<AreaPointConsumeBO> getAreaPointConsume(ReportAgentAreaPointParam param) {
		List<AreaPointConsumeDOView> list = pointDetailDOMapperExtend.getAreaPointConsume(param);
		List<AreaPointConsumeBO> rtnList = new ArrayList<>();
		if(list != null && !list.isEmpty()) {
			for(AreaPointConsumeDOView view : list) {
				AreaPointConsumeBO BO = new AreaPointConsumeBO();
				BO.setAreaId(view.getAreaId());
				BO.setCityId(view.getCityId());
				BO.setProvinceId(view.getProvinceId());
				BO.setTotalPoint(view.getTotalPoint());
				BO.setType(view.getType());
				rtnList.add(BO);
			}
		}
		return rtnList;
	}

	@Override
	public List<AreaPointConsumeBO> getAreaPointRefund(ReportAgentAreaPointParam param) {
		List<AreaPointConsumeDOView> list = pointDetailDOMapperExtend.getAreaPointRefund(param);
		List<AreaPointConsumeBO> rtnList = new ArrayList<>();
		if(list != null && !list.isEmpty()) {
			for(AreaPointConsumeDOView view : list) {
				AreaPointConsumeBO BO = new AreaPointConsumeBO();
				BO.setAreaId(view.getAreaId());
				BO.setCityId(view.getCityId());
				BO.setProvinceId(view.getProvinceId());
				BO.setTotalPoint(view.getTotalPoint());
				BO.setType(view.getType());
				rtnList.add(BO);
			}
		}
		return rtnList;
	}

	@Override
	public List<IncomeMsgBO> getIncomeMsgDataList(String begin,String end,int offset,int pageSize) {
		IncomeMsgExample example = new IncomeMsgExample();
		example.setBegin(begin);
		example.setEnd(end);
		example.setOffset(offset);
		example.setPageSize(pageSize);
		List<IncomeMsgDOView> list = pointDetailDOMapperExtend.getIncomeMsgDataList(example);
		List<IncomeMsgBO> bos = new ArrayList<>();
		for(IncomeMsgDOView view : list){
			IncomeMsgBO bo = new IncomeMsgBO();
			bo.setType(view.getbType());
			bo.setMoney(view.getMoney());
			bo.setUserNum(view.getUserNum());
			bos.add(bo);
		}
		return bos;
	}

	@Override
	public List<IncomeMsgBO> getIncomeMsgTotalDataList(String begin,String end) {
		IncomeMsgExample example = new IncomeMsgExample();
		example.setBegin(begin);
		example.setEnd(end);
		List<IncomeMsgDOView> list = pointDetailDOMapperExtend.getIncomeMsgTotalDataList(example);
		List<IncomeMsgBO> bos = new ArrayList<>();
		for(IncomeMsgDOView view : list){
			IncomeMsgBO bo = new IncomeMsgBO();
			bo.setMoney(view.getMoney());
			bo.setUserNum(view.getUserNum());
			bo.setPayTypeEnum(PayTypeEnum.POINT);
			bos.add(bo);
		}
		return bos;
	}

	@Override
	public boolean getPointDetailByUserNumAndBizIdAndType(PropertyInfoDataQueryPointDetailParam param) {
		PointDetailDOExample example = new PointDetailDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum()).andBizIdEqualTo(param.getBizId()).andPointTypeEqualTo(param.getMerchantTransactionTypeEnum().getValue());
		int count = pointDetailDOMapper.countByExample(example);
		if(count > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyRepeatByUserNumAndTransactionTypeAndBizId(CheckRepeatOfPropertyOperationParam param) {
		PointDetailDOExample example = new PointDetailDOExample();
		PointDetailDOExample.Criteria criteria = example.createCriteria();
		Byte transactionType = param.getMerchantTransactionTypeEnum() == null ? param.getMemberTransactionTypeEnum().getValue() : param.getMerchantTransactionTypeEnum().getValue();
		if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
			transactionType = param.getMerchantTransactionTypeEnum().getValue();
		} else if (param.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
			transactionType = param.getMemberTransactionTypeEnum().getValue();
		}
		criteria.andUserNumEqualTo(param.getUserNum()).andPointTypeEqualTo(transactionType).andBizIdEqualTo(param.getBizIds());
		int count = pointDetailDOMapper.countByExample(example);
		if(count > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean existsPointDetailByUserNumAndBizId(String userNum, String bizId) {
		PointDetailDOExample example = new PointDetailDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andBizIdEqualTo(bizId);
		int count = pointDetailDOMapper.countByExample(example);
		return count > 0;
	}

	@Override
	public PointDetailBO getPointDetailByUserNumAndBizId(String userNum, String bizId) {
		PointDetailDOExample example = new PointDetailDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andBizIdEqualTo(bizId);
		List<PointDetailDO> pointDetailDOS = pointDetailDOMapper.selectByExample(example);
		if (pointDetailDOS.isEmpty()) {
			return null;
		}
		return PointDetailConverter.convert(pointDetailDOS.get(0));
	}

	@Override
	public PointDetailBO getPointDetailById(Long id) {
		PointDetailDO pointDetailDO = pointDetailDOMapper.selectByPrimaryKey(id);
		return PointDetailConverter.convert(pointDetailDO);
	}

}
