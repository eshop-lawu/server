package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.user.param.FavoriteMerchantParam;
import com.lawu.eshop.user.param.FavoriteStoreParam;
import com.lawu.eshop.user.srv.bo.FavoriteMerchantBO;
import com.lawu.eshop.user.srv.converter.FavoriteMerchantConverter;
import com.lawu.eshop.user.srv.domain.FavoriteMerchantDO;
import com.lawu.eshop.user.srv.domain.FavoriteMerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.extend.FavoriteMerchantDOView;
import com.lawu.eshop.user.srv.mapper.FavoriteMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.FavoriteMerchantDOMapperExtend;
import com.lawu.eshop.user.srv.service.FavoriteMerchantService;
import com.lawu.eshop.user.srv.solr.service.MerchantStoreSolrService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DistanceUtil;

@Service
public class FavoriteMerchantServiceImpl implements FavoriteMerchantService {

    @Resource
    private FavoriteMerchantDOMapper favoriteMerchantDOMapper;

    @Resource
    private MerchantStoreDOMapper merchantStoreDOMapper;
    
    @Resource
    private FavoriteMerchantDOMapperExtend favoriteMerchantDOMapperExtend;
    
    @Resource
    private MerchantStoreImageDOMapper  merchantStoreImageDOMapper;
    
    @Autowired
    private MerchantStoreSolrService merchantStoreSolrService;
    
    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(Long memberId, FavoriteStoreParam param) {
        FavoriteMerchantDO favoriteMerchant = new FavoriteMerchantDO();
        favoriteMerchant.setMemberId(memberId);
        favoriteMerchant.setMerchantId(param.getMerchantId());
        favoriteMerchant.setManageType(param.getManageTypeEnum().getVal());
        favoriteMerchant.setGmtCreate(new Date());
        int row = favoriteMerchantDOMapper.insert(favoriteMerchant);
        MerchantStoreDOExample example = new MerchantStoreDOExample();
        example.createCriteria().andMerchantIdEqualTo(param.getMerchantId());
        List<MerchantStoreDO> list = merchantStoreDOMapper.selectByExample(example);
        if (!list.isEmpty()) {
            MerchantStoreDO merchantStoreDO = list.get(0);
            Integer count = merchantStoreDO.getFavoriteNumber();
            count += 1;
            merchantStoreDO.setFavoriteNumber(count);
            merchantStoreDOMapper.updateByPrimaryKeySelective(merchantStoreDO);

            //更新solr门店收藏人数
            MerchantStoreSolr solrBean = new MerchantStoreSolr();
            solrBean.setId(list.get(0).getId());
            solrBean.setFavoriteNumber(count);
            merchantStoreSolrService.update(solrBean);
        }
        return row;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(FavoriteStoreParam param,Long memberId) {
    	FavoriteMerchantDOExample example = new FavoriteMerchantDOExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andMerchantIdEqualTo(param.getMerchantId()).andManageTypeEqualTo(param.getManageTypeEnum().getVal());
        Integer i = favoriteMerchantDOMapper.deleteByExample(example);
        MerchantStoreDOExample storeDOExample = new MerchantStoreDOExample();
        storeDOExample.createCriteria().andMerchantIdEqualTo(param.getMerchantId());
        List<MerchantStoreDO> merchantStoreDOS = merchantStoreDOMapper.selectByExample(storeDOExample);
        if (!merchantStoreDOS.isEmpty()) {
            MerchantStoreDO merchantStoreDO = merchantStoreDOS.get(0);
            Integer count = merchantStoreDO.getFavoriteNumber();
            count -= 1;
            if(count<0){
            	count=0;
            }
            merchantStoreDO.setFavoriteNumber(count);
            merchantStoreDOMapper.updateByPrimaryKeySelective(merchantStoreDO);

            //更新solr门店收藏人数
            MerchantStoreProfileDO  merchantStoreProfileDO = merchantStoreProfileDOMapper.selectByPrimaryKey(merchantStoreDO.getId());
            if(merchantStoreProfileDO.getManageType().equals(ManageTypeEnum.ENTITY.getVal())){
            	 MerchantStoreSolr solrBean = new MerchantStoreSolr();
                 solrBean.setId(merchantStoreDO.getId());
                 solrBean.setFavoriteNumber(count);
                 merchantStoreSolrService.update(solrBean);
            }
           
        }
        return i;
    }

    @Override
    public Page<FavoriteMerchantBO> getMyFavoriteMerchant(Long memberId, FavoriteMerchantParam pageQuery) {
    	FavoriteMerchantDOExample exmple=new FavoriteMerchantDOExample();
    	exmple.createCriteria().andMemberIdEqualTo(memberId).andManageTypeEqualTo(pageQuery.getManageTypeEnum().getVal());
    	FavoriteMerchantDOView view=new FavoriteMerchantDOView();
    	view.setMemberId(memberId);
    	view.setType(pageQuery.getManageTypeEnum().getVal());
        RowBounds rowBounds = new RowBounds(pageQuery.getOffset(), pageQuery.getPageSize());
        List<FavoriteMerchantDOView> list=favoriteMerchantDOMapperExtend.selectFavoriteMerchantByRowbounds(view, rowBounds);
        List<FavoriteMerchantBO> listBO=new ArrayList<>();
        for (FavoriteMerchantDOView favoriteMerchantDOView : list) {
        	FavoriteMerchantBO favoriteMerchantBO=FavoriteMerchantConverter.convertListBO(favoriteMerchantDOView);
    		if(pageQuery.getLongitude()!=null && pageQuery.getLatitude()!=null){
    			 int distance= DistanceUtil.getDistance(pageQuery.getLongitude(), pageQuery.getLatitude(), 
    					 favoriteMerchantDOView.getLongitude().doubleValue(),  favoriteMerchantDOView.getLatitude().doubleValue());
        		 favoriteMerchantBO.setDistance(distance);
    		}
        	//获取门店logo
        	MerchantStoreImageDOExample msidExample=new MerchantStoreImageDOExample();
        	msidExample.createCriteria().andMerchantIdEqualTo(favoriteMerchantDOView.getMerchantId()).andStatusEqualTo(true).andTypeEqualTo(new Byte("3"));
        	List<MerchantStoreImageDO>  msiList= merchantStoreImageDOMapper.selectByExample(msidExample);
        	if(!msiList.isEmpty()){
        		favoriteMerchantBO.setPath(msiList.get(0).getPath());
        	}
        	listBO.add(favoriteMerchantBO);
		}
        Page<FavoriteMerchantBO> page = new Page<>();
        Long count=favoriteMerchantDOMapper.countByExample(exmple);
        page.setTotalCount(count.intValue());
        page.setCurrentPage(pageQuery.getCurrentPage());
        page.setRecords(listBO);
        return page;
    }

	@Override
    public Boolean get(Long memberId, FavoriteStoreParam pageQuery) {
        FavoriteMerchantDOExample exmple = new FavoriteMerchantDOExample();
        exmple.createCriteria().andMemberIdEqualTo(memberId).andManageTypeEqualTo(pageQuery.getManageTypeEnum().getVal()).andMerchantIdEqualTo(pageQuery.getMerchantId());
        long count = favoriteMerchantDOMapper.countByExample(exmple);
        return count > 0;
    }

}
