package com.lawu.eshop.ad.srv.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.AuditEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdDetailDTO;
import com.lawu.eshop.ad.dto.AdEgainDTO;
import com.lawu.eshop.ad.dto.AdEgainQueryDTO;
import com.lawu.eshop.ad.dto.AdFlatVideoDTO;
import com.lawu.eshop.ad.dto.AdMerchantDTO;
import com.lawu.eshop.ad.dto.AdMerchantDetailDTO;
import com.lawu.eshop.ad.dto.AdPayInfoDTO;
import com.lawu.eshop.ad.dto.AdPointDTO;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.dto.AdSaveInfoDTO;
import com.lawu.eshop.ad.dto.AdSolrDTO;
import com.lawu.eshop.ad.dto.ChoicenessAdDTO;
import com.lawu.eshop.ad.dto.ClickAdPointDTO;
import com.lawu.eshop.ad.dto.HaveAdPraiseDTO;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.IsMyDateDTO;
import com.lawu.eshop.ad.dto.MerchantInfoDTO;
import com.lawu.eshop.ad.dto.OperatorAdDTO;
import com.lawu.eshop.ad.dto.PointGetDetailDTO;
import com.lawu.eshop.ad.dto.PraisePointDTO;
import com.lawu.eshop.ad.dto.RedPacketInfoDTO;
import com.lawu.eshop.ad.dto.ReportAdDTO;
import com.lawu.eshop.ad.dto.ViewDTO;
import com.lawu.eshop.ad.param.AdChoicenessInternalParam;
import com.lawu.eshop.ad.param.AdEgainInternalParam;
import com.lawu.eshop.ad.param.AdFindParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdMerchantParam;
import com.lawu.eshop.ad.param.AdPointInternalParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.ad.param.AdSaveParam;
import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.ad.param.ListAdParam;
import com.lawu.eshop.ad.param.OperatorAdParam;
import com.lawu.eshop.ad.param.PointGetDetailParam;
import com.lawu.eshop.ad.srv.bo.AdBO;
import com.lawu.eshop.ad.srv.bo.AdClickPraiseInfoBO;
import com.lawu.eshop.ad.srv.bo.AdEgainBO;
import com.lawu.eshop.ad.srv.bo.AdEgainDetailBO;
import com.lawu.eshop.ad.srv.bo.AdPointBO;
import com.lawu.eshop.ad.srv.bo.AdPraiseBO;
import com.lawu.eshop.ad.srv.bo.AdSaveInfoBO;
import com.lawu.eshop.ad.srv.bo.ChoicenessAdBO;
import com.lawu.eshop.ad.srv.bo.ClickAdPointBO;
import com.lawu.eshop.ad.srv.bo.ClickPointBO;
import com.lawu.eshop.ad.srv.bo.GetRedPacketBO;
import com.lawu.eshop.ad.srv.bo.MerchantInfoBO;
import com.lawu.eshop.ad.srv.bo.OperatorAdBO;
import com.lawu.eshop.ad.srv.bo.PointGetDetailBO;
import com.lawu.eshop.ad.srv.bo.RedPacketInfoBO;
import com.lawu.eshop.ad.srv.bo.ReportAdBO;
import com.lawu.eshop.ad.srv.bo.ViewBO;
import com.lawu.eshop.ad.srv.converter.AdConverter;
import com.lawu.eshop.ad.srv.service.AdCountRecordService;
import com.lawu.eshop.ad.srv.service.AdService;
import com.lawu.eshop.ad.srv.service.FavoriteAdService;
import com.lawu.eshop.ad.srv.service.MemberAdRecordService;
import com.lawu.eshop.ad.srv.service.PointPoolService;
import com.lawu.eshop.ad.srv.solr.service.AdSolrService;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.solr.impl.entity.AdSolr;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * E赚接口提供
 *
 * @author zhangrc
 * @date 2017/4/6
 *
 */
@RestController
@RequestMapping(value = "ad/")
public class AdController extends BaseController {

	@Resource
	private AdService adService;

	@Resource
	private MemberAdRecordService memberAdRecordService;

	@Resource
	private PointPoolService pointPoolService;

	@Autowired
	private FavoriteAdService favoriteAdService;

	@Autowired
	private AdCountRecordService adCountRecordService;
	
	@Autowired
	private AdSolrService adSolrService;

	private static Logger logger = LoggerFactory.getLogger(AdController.class);

	/**
	 * 添加E赚
	 *
	 * @param adSaveParam
	 * @return
	 */
	@RequestMapping(value = "saveAd", method = RequestMethod.POST)
	public Result<AdSaveInfoDTO> saveAd(@RequestBody AdSaveParam adSaveParam) {

		AdSaveInfoBO bo = adService.saveAd(adSaveParam);

		if (bo.getId() == null || bo.getId() < 0) {
			successCreated(ResultCode.SAVE_FAIL);
		}
		
		AdSaveInfoDTO dto = new AdSaveInfoDTO();  
		dto.setAdCount(bo.getAdCount());
		dto.setId(bo.getId());
		dto.setAdOrderNum(bo.getAdOrderNum());
		
		return successCreated(dto);

	}

	/**
	 * 查询商家E赚,E赞
	 *
	 * @param adMerchantParam
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "selectListByMerchant", method = RequestMethod.POST)
	public Result<Page<AdMerchantDTO>> selectListByMerchant(@RequestBody AdMerchantParam adMerchantParam, @RequestParam Long memberId) {
		Page<AdBO> pageBO = adService.selectListByMerchant(adMerchantParam, memberId);
		Page<AdMerchantDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
		pageDTO.setRecords(AdConverter.convertMerchantAdDTOS(pageBO.getRecords()));
		return successAccepted(pageDTO);
	}

	/**
	 * 对广告的操作，下架和删除
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateStatus/{id}", method = RequestMethod.PUT)
	public Result updateStatus(@PathVariable Long id) {
		AdBO BO = adService.get(id);
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(new Date());// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -14); // 设置为14天前
		Date before14days = calendar.getTime(); // 得到14天前的时间
		if (BO.getBeginTime() != null && before14days.getTime() < BO.getBeginTime().getTime()) {
			return successCreated(ResultCode.AD_PUT_NOT_TIME);
		} else {
			adService.updateStatus(id);
			return successCreated();

		}

	}

	/**
	 * 对广告的操作，删除
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "remove/{id}", method = RequestMethod.PUT)
	public Result remove(@PathVariable Long id) {
		adService.remove(id);
		return successCreated();
	}

	/**
	 * 广告详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectAbById/{id}", method = RequestMethod.GET)
	public Result<AdEgainDTO> selectAbById(@PathVariable Long id, @RequestParam Long memberId) {
		AdEgainDetailBO bo = adService.selectAbById(id, memberId);
		return successAccepted(AdConverter.convertAdEgainDTO(bo));
	}

	/**
	 * 抢赞详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectAdPraiseById/{id}", method = RequestMethod.GET)
	public Result<AdPraiseDTO> selectAdPraiseById(@PathVariable Long id, @RequestParam Long memberId) {
		AdPraiseBO bo = adService.selectAdPraiseById(id, memberId);
		return successAccepted(AdConverter.convertPraiseDTO(bo));
	}

	/**
	 * 点击广告
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "clickAd/{id}", method = RequestMethod.PUT)
	public Result<ClickAdPointDTO> clickAd(@PathVariable Long id, @RequestParam Long memberId, @RequestParam String num) {
		try {
			ClickPointBO bo = adService.clickAd(id, memberId, num);
			if(bo.getIsClick()){
				return successCreated(ResultCode.AD_CLICK_EXIST);
			}
			if(bo.isOverClick()){
				return successCreated(ResultCode.AD_CLICK_PUTED);
			}
			
			ClickAdPointDTO dto = new ClickAdPointDTO();
			ClickAdPointBO clickAdPointBO = adService.getClickAdPoint(memberId, bo.getPoint());
			dto.setAddPoint(clickAdPointBO.getAddPoint());
			dto.setPoint(clickAdPointBO.getAdTotlePoint());
			return successCreated(dto);
			
	    } catch (DataNotExistException e) {
	         logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
	    }catch (SQLException e) {
	         logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.AD_CLICK_EXIST, e.getMessage());
	    }
		
		
	}

	/**
	 * 对视频广告的审核
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "auditVideo/{id}", method = RequestMethod.PUT)
	public Result auditVideo(@PathVariable Long id, @RequestParam Integer auditorId, @RequestParam String remark, @RequestBody AuditEnum auditEnum) {
		AdBO adBO = adService.selectById(id);
		if (adBO != null && adBO.getStatusEnum().val.byteValue() != AdStatusEnum.AD_STATUS_AUDIT.val) {
			return successGet(ResultCode.AD_AUDITED);
		}
		Integer i = adService.auditVideo(id, auditorId, remark, auditEnum);
		if (i == null || i < 0) {
			successCreated(ResultCode.SAVE_FAIL);
		}
		return successCreated();
	}

	/**
	 * 运营查询广告
	 *
	 * @param adPlatParam
	 * @return
	 */
	@RequestMapping(value = "selectListByPlatForm", method = RequestMethod.POST)
    public Result<Page<AdDTO>> selectListByPlatForm(@RequestBody AdFindParam adPlatParam) {
		Page<AdBO> pageBO=  adService.selectListByPlatForm(adPlatParam);
		Page<AdDTO> pageDTO=new Page<AdDTO>();
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
		pageDTO.setRecords(AdConverter.convertDTOS(pageBO.getRecords()));
		return successAccepted(pageDTO);
	}

	/**
	 * 会员查询广告
	 *
	 * @param adMemberParam
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "selectListByMember", method = RequestMethod.POST)
	public Result<Page<AdDTO>> selectListByMember(@RequestBody AdMemberParam adMemberParam, @RequestParam Long memberId) {
		Page<AdBO> pageBO = adService.selectListByMember(adMemberParam, memberId);
		Page<AdDTO> pageDTO = new Page<AdDTO>();
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
		pageDTO.setRecords(AdConverter.convertDTOS(pageBO.getRecords()));
		return successAccepted(pageDTO);
	}

	/**
	 * 会员查询广告
	 * @see
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "selectChoiceness", method = RequestMethod.POST)
	public Result<Page<AdDTO>> selectChoiceness(@RequestBody AdSolrRealParam param) {
        org.springframework.data.domain.Page<AdSolr> adSolrPage = adSolrService.selectChoiceness(param);
        Page<AdDTO> model = new Page<>();
        model.setCurrentPage(param.getCurrentPage());
        model.setTotalCount((int) adSolrPage.getTotalElements());
        model.setRecords(AdConverter.convertAdDTOList(adSolrPage.getContent()));
        return successAccepted(model);
	}

	/**
	 * 会员E赞
	 *
	 * @param adPraiseParam
	 * @return
	 */
	@RequestMapping(value = "selectPraiseListByMember", method = RequestMethod.POST)
	public Result<Page<AdDTO>> selectPraiseListByMember(@RequestBody AdPraiseParam adPraiseParam, @RequestParam Long memberId) {
		Page<AdBO> pageBO = adService.selectPraiseListByMember(adPraiseParam, memberId);
		Page<AdDTO> pageDTO = new Page<AdDTO>();
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
		pageDTO.setRecords(AdConverter.convertDTOS(pageBO.getRecords()));
		return successAccepted(pageDTO);
	}

	/**
	 * 会员E赞
	 *
	 * @param id
	 * @param memberId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "clickPraise/{id}", method = RequestMethod.PUT)
	public Result<PraisePointDTO> clickPraise(@PathVariable Long id, @RequestParam Long memberId, @RequestParam String num) {
		try {
			AdClickPraiseInfoBO bo = adService.clickPraise(id, memberId, num);
			if (bo.getIsPraise()) {
				return successCreated(ResultCode.AD_PRAISE_POINT_GET);
			}
			
			if(bo.getIsPraiseEnd()){
				return successCreated(ResultCode.AD_PRAISE_PUTED);
			}
			PraisePointDTO dto = new PraisePointDTO();
			dto.setPoint(bo.getPoint());
			dto.setIsGetPoint(true);
			return successCreated(dto);
			
	    } catch (DataNotExistException e) {
	    	
	         logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());

	    }catch (SQLException e) {

	         logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.AD_PRAISE_POINT_GET, e.getMessage());
	    }

	}

	/**
	 * 根据广告名称查询广告
	 *
	 * @param adSolrParam
	 * @return
	 */
	@RequestMapping(value = "queryAdByTitle", method = RequestMethod.POST)
	public Result<Page<AdSolrDTO>> queryAdByTitle(@RequestBody AdsolrFindParam adSolrParam) {
        org.springframework.data.domain.Page<AdSolr> pageBO = adSolrService.queryAdByTitle(adSolrParam);
        Page<AdSolrDTO> model = new Page<>();
        model.setCurrentPage(adSolrParam.getCurrentPage());
        model.setTotalCount((int) pageBO.getTotalElements());
        model.setRecords(AdConverter.convertAdSolrDTOList(pageBO.getContent()));
        return successGet(model);
	}

	/**
	 * 推荐广告
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "recommendAdByType", method = RequestMethod.POST)
	public Result<Page<AdFlatVideoDTO>> getRecommendAdByType(@RequestBody AdSolrRealParam param) {
        org.springframework.data.domain.Page<AdSolr> pageBO = adSolrService.getRecommendAdByType(param);
        Page<AdFlatVideoDTO> model = new Page<>();
        model.setCurrentPage(param.getCurrentPage());
        model.setTotalCount((int) pageBO.getTotalElements());
        model.setRecords(AdConverter.convertAdFlatVideoDTOList(pageBO.getContent()));
        return successGet(model);
	}

	/**
	 * 推荐抢赞
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "recommendEgain", method = RequestMethod.POST)
	public Result<Page<AdPraiseDTO>> getRecommendEgain(@RequestBody AdSolrRealParam param) {
        List<AdPraiseDTO> records = new ArrayList<>();
        org.springframework.data.domain.Page<AdSolr> adSolrPage = adSolrService.getRecommendEgain(param);
        if (adSolrPage != null && adSolrPage.getContent() != null && !adSolrPage.getContent().isEmpty()){
            for (AdSolr item : adSolrPage.getContent()) {
                AdPraiseDTO adPraiseDTO = AdConverter.convert(item);
                if (AdStatusEnum.AD_STATUS_ADD.equals(AdStatusEnum.getEnum(item.getStatus()))) {
                    int favoriteCount = favoriteAdService.getFavoriteCount(item.getId());
                    adPraiseDTO.setCount(favoriteCount);
                }
                if (param.getMemberId() != null || param.getMemberId() > 0) {
                    if (com.lawu.eshop.common.constants.AdTypeEnum.AD_TYPE_PRAISE.equals(com.lawu.eshop.common.constants.AdTypeEnum.getEnum(item.getType())) && AdStatusEnum.AD_STATUS_PUTING.equals(AdStatusEnum.getEnum(item.getStatus()))) {
                        Boolean flag = pointPoolService.selectStatusByMember(item.getId(), param.getMemberId());
                        adPraiseDTO.setIsPraise(flag);
                    }
                }else{
                    adPraiseDTO.setIsPraise(false);
                }
                //获取缓存中剩余数量
                if(AdStatusEnum.AD_STATUS_PUTING.equals(AdStatusEnum.getEnum(item.getStatus()))){
                    Result<Integer> result = adCountRecordService.getSurplusCount(item.getId());
					if (item.getAdCount() != null && item.getAdCount() !=0 && result.getModel() > 0 ) {
						adPraiseDTO.setCount(item.getAdCount() - result.getModel());
					}

                }
                records.add(adPraiseDTO);
            }
        }
        Page<AdPraiseDTO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount((int) adSolrPage.getTotalElements());
        page.setRecords(records);
        return successGet(page);
	}

	/**
	 * 平面广告排行榜
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "listAdRank", method = RequestMethod.POST)
	public Result<List<AdDTO>> listAdRank(@RequestBody AdSolrRealParam param) {
	    List<AdSolr> list = adSolrService.listAdRank(param);
		List<AdSolr> resultDTOS = new ArrayList<>();
		if (list.size() <= param.getPageSize()) {
			resultDTOS.addAll(list);
		} else {
			resultDTOS = list.subList(param.getOffset(), param.getPageSize());
		}
		return successGet(AdConverter.convertAdDTOList(resultDTOS));
	}

	/**
	 * 领取红包
	 *
	 * @param merchantId
	 * @param memberId
	 * @param memberNum
	 * @return
	 */
	@RequestMapping(value = "getRedPacket", method = RequestMethod.PUT)
	public Result<PraisePointDTO> getRedPacket(@RequestParam Long merchantId, @RequestParam Long memberId, @RequestParam String memberNum) {
		GetRedPacketBO redPacketBO = pointPoolService.isGetRedPacket(merchantId, memberNum);
		if (redPacketBO.isGetRedPacket()) {
			return successCreated(ResultCode.AD_RED_PACKGE_GET);
		}
		if (redPacketBO.isNullRedPacket()) {
			return successCreated(ResultCode.AD_RED_PACKGE_PUTED);
		}
		BigDecimal point = adService.getRedPacket(merchantId, memberId, memberNum);
		PraisePointDTO dto = new PraisePointDTO();
		dto.setPoint(point);
		return successGet(dto);
	}

	/**
	 * 获取所有的广告ids
	 *
	 * @return
	 */
	@RequestMapping(value = "getAllAd", method = RequestMethod.GET)
	public Result<List<ViewDTO>> getAllAd() {
		List<ViewBO> bos = adService.getAllAd();
		List<ViewDTO> dtos = new ArrayList<>();
		for (ViewBO viewBO : bos) {
			ViewDTO dto = new ViewDTO();
			dto.setId(viewBO.getId());
			dto.setViewCount(viewBO.getViewCount());
			dtos.add(dto);
		}
		return successGet(dtos);
	}

	/**
	 * 修改广告浏览次数
	 *
	 * @param id
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "updateViewCount/{id}", method = RequestMethod.PUT)
	public Result<List<Long>> updateViewCount(@PathVariable Long id, @RequestParam Integer count) {
		adService.updateViewCount(id, count);
		return successCreated();
	}

	/**
	 * 运营后台查询广告列表
	 *
	 * @param listAdParam
	 * @return
	 */
	@RequestMapping(value = "listAllAd", method = RequestMethod.POST)
	public Result<Page<AdDTO>> listAllAd(@RequestBody ListAdParam listAdParam) {
		Page<AdBO> adBOPage = adService.listAllAd(listAdParam);
		Page<AdDTO> page = new Page<>();
		page.setCurrentPage(adBOPage.getCurrentPage());
		page.setRecords(AdConverter.convertDTOS(adBOPage.getRecords()));
		page.setTotalCount(adBOPage.getTotalCount());
		return successGet(page);
	}

	/**
	 * 根据ID查询广告详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getAd/{id}", method = RequestMethod.GET)
	public Result<AdDTO> getAdById(@PathVariable Long id) {
		AdBO adBO = adService.get(id);
		if (adBO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		return successGet(AdConverter.convertDTO(adBO));
	}

	/**
	 * 商家端广告详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectById/{id}", method = RequestMethod.GET)
	public Result<AdMerchantDetailDTO> selectById(@PathVariable Long id) {
		AdBO bo = adService.selectById(id);
		return successAccepted(AdConverter.convertMerchantDetailAdDTO(bo));
	}

	/**
	 * 运营后台操作广告(下架、删除)
	 *
	 * @param id
	 * @param adStatusEnum
	 * @return
	 */
	@RequestMapping(value = "operatorUpdateAdStatus/{id}", method = RequestMethod.PUT)
	public Result operatorUpdateAdStatus(@PathVariable Long id, @RequestParam AdStatusEnum adStatusEnum) {
		AdBO adBO = adService.get(id);
		if (adBO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		adService.operatorUpdateAdStatus(id, adStatusEnum);
		return successCreated();
	}

	/**
	 * 查询上架中的平面视频广告
	 *
	 * @param listAdParam
	 * @return
	 */
	@RequestMapping(value = "listFlatVideoAd", method = RequestMethod.POST)
	public Result<List<AdDTO>> listFlatVideoAd(@RequestBody ListAdParam listAdParam) {
		List<AdBO> adBOS = adService.listFlatVideoAd(listAdParam);
		if (adBOS == null || adBOS.isEmpty()) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		return successGet(AdConverter.convertDTOS(adBOS));
	}

	/**
	 * 重建平面视频广告索引
	 *
	 * @return
	 */
	@RequestMapping(value = "rebuildAdIndex", method = RequestMethod.GET)
	public Result rebuildAdIndex(@RequestParam Integer pageSize) {
		adService.rebuildAdIndex(pageSize);
		return successCreated();
	}

	/**
	 * 删除无效的平面视频广告索引
	 *
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(value = "delInvalidAdIndex", method = RequestMethod.GET)
	public Result delInvalidAdIndex(@RequestParam DelIndexTypeEnum typeEnum) {
		adService.delInvalidAdIndex(typeEnum);
		return successGet();
	}

	/**
	 * 根据商家获取红包相关信息
	 *
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "getRedPacketInfo/{merchantId}", method = RequestMethod.GET)
	public Result<RedPacketInfoDTO> getRedPacketInfo(@PathVariable Long merchantId) {
		RedPacketInfoBO redPacketInfoBO = adService.getRedPacketInfo(merchantId);
		if (redPacketInfoBO == null) {
			return successCreated(ResultCode.AD_RED_PACKGE_PUTED);
		} else {
			RedPacketInfoDTO redPacketInfoDTO = new RedPacketInfoDTO();
			redPacketInfoDTO.setPoint(redPacketInfoBO.getPoint());
			redPacketInfoDTO.setMediaUrl(redPacketInfoBO.getMediaUrl());
			redPacketInfoDTO.setName(redPacketInfoBO.getName());
			redPacketInfoDTO.setLogoUrl(redPacketInfoBO.getLogoUrl());
			redPacketInfoDTO.setFileType(redPacketInfoBO.getFileType());
			return successCreated(redPacketInfoDTO);
		}

	}

	/**
	 * 判断红包是否领取完成
	 *
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "isExistsRedPacket/{merchantId}", method = RequestMethod.GET)
	public Result<IsExistsRedPacketDTO> isExistsRedPacket(@PathVariable Long merchantId) {
		Boolean flag = adService.isExistsRedPacket(merchantId);
		IsExistsRedPacketDTO dto = new IsExistsRedPacketDTO();
		dto.setIsExistsRedPacket(flag);
		return successCreated(dto);
	}

	/**
	 * 商家批量删除广告
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchDeleteAd", method = RequestMethod.DELETE)
	public Result batchDeleteAd(@RequestParam("ids") List<Long> ids, @RequestParam Long merchantId) {
		for (int i = 0; i < ids.size(); i++) {
			Boolean flag = adService.isMyData(ids.get(i), merchantId);
			if (!flag)
				ids.remove(i);
		}
		adService.batchDeleteAd(ids);
		return successCreated();
	}

	/**
	 * 商家详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectDetail/{id}", method = RequestMethod.GET)
	public Result<AdDetailDTO> selectDetail(@PathVariable Long id) {
		return successCreated(AdConverter.convertDetailDTO(adService.selectDetail(id)));
	}

	/**
	 * 判断数据是否是当前用户的
	 *
	 * @param id
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "isMyData/{id}", method = RequestMethod.GET)
	public Result<IsMyDateDTO> isMyData(@PathVariable Long id, @RequestParam Long merchantId) {
		Boolean flag = adService.isMyData(id, merchantId);
		IsMyDateDTO dto = new IsMyDateDTO();
		dto.setFlag(flag);
		return successCreated(dto);
	}

	/**
	 * 广告收益统计
	 *
	 * @return
	 */
	@RequestMapping(value = "selectReportAdEarnings", method = RequestMethod.GET)
	public Result<List<ReportAdDTO>> selectReportAdEarnings() {
		List<ReportAdBO> list = adService.selectReportAdEarnings();
		List<ReportAdDTO> listDTO = new ArrayList<>();
		for (ReportAdBO reportAdBO : list) {
			ReportAdDTO dto = new ReportAdDTO();
			dto.setGmtCreate(reportAdBO.getGmtCreate());
			dto.setId(reportAdBO.getId());
			dto.setMerchantId(reportAdBO.getMerchantId());
			dto.setMerchantNum(reportAdBO.getMerchantNum());
			dto.setStatusEnum(reportAdBO.getStatusEnum());
			dto.setTypeEnum(reportAdBO.getTypeEnum());
			dto.setTotalPoint(reportAdBO.getTotalPoint());
			dto.setTitle(reportAdBO.getTitle());
			listDTO.add(dto);
		}
		return successCreated(listDTO);
	}

	/**
	 * 分页查询E赚广告
	 *
	 * @param memberId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	@RequestMapping(value = "pageAdEgain/{memberId}", method = RequestMethod.PUT)
	public Result<Page<AdEgainQueryDTO>> selectPageAdEgain(@PathVariable("memberId") Long memberId, @RequestBody AdEgainInternalParam param) {
		Page<AdEgainBO> pageAdEgainBO = adService.selectPageAdEgain(memberId, param);
		Page<AdEgainQueryDTO> rtn = AdConverter.convertAdEgainQueryDTOPage(pageAdEgainBO);
		return successCreated(rtn);
	}

	/**
	 * 查询积分排行榜广告
	 *
	 * @param param
	 *            查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	@RequestMapping(value = "adPoint", method = RequestMethod.PUT)
	public Result<List<AdPointDTO>> selectAdPoint(@RequestBody AdPointInternalParam param) {
		List<AdPointBO> adPointBOList = adService.selectAdPoint(param);
		List<AdPointDTO> rtn = AdConverter.convertAdPointDTOList(adPointBOList);
		return successCreated(rtn);
	}

	/**
	 * 分页查询精选推荐广告
	 *
	 * @param memberId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	@RequestMapping(value = "choiceness/{memberId}", method = RequestMethod.PUT)
	public Result<Page<ChoicenessAdDTO>> selectChoiceness(@PathVariable("memberId") Long memberId, @RequestBody AdChoicenessInternalParam param) {
		Page<ChoicenessAdBO> pageChoicenessAdBO = adService.selectPageChoicenessAd(memberId, param);
		Page<ChoicenessAdDTO> rtn = AdConverter.convertChoicenessAdDTOPage(pageChoicenessAdBO);
		return successCreated(rtn);
	}

	/**
	 * 运营平台查询广告
	 * @param operatorAdParam
	 * @return
	 */
	@RequestMapping(value = "selectOperatorAdAll", method = RequestMethod.POST)
	public Result<Page<OperatorAdDTO>> selectOperatorAdAll(@RequestBody OperatorAdParam operatorAdParam) {
		Page<OperatorAdBO> page = adService.selectOperatorAdAll(operatorAdParam);

		List<OperatorAdDTO>  dtoList = new ArrayList<>();

		for (OperatorAdBO operatorAdBO : page.getRecords()) {
			OperatorAdDTO dto = new OperatorAdDTO();
			dto.setId(operatorAdBO.getId());
			dto.setTitle(operatorAdBO.getTitle());
			dto.setMerchantId(operatorAdBO.getMerchantId());
			dto.setTypeEnum(operatorAdBO.getTypeEnum());
			dtoList.add(dto);
		}

		Page<OperatorAdDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(page.getCurrentPage());
		pageDTO.setRecords(dtoList);
		pageDTO.setTotalCount(page.getTotalCount());
		
		return successCreated(pageDTO);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "soldOutAdByMerchantId")
	public Result soldOutAdByMerchantId(@RequestParam(value = "merchantId") Long merchantId){
		adService.soldOutAdByMerchantId(merchantId);
		return successCreated();
	}

	/**
	 * 删除全部索引数据
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "delAllAdIndex", method = RequestMethod.GET)
	public Result delAllAdIndex() {
		adSolrService.deleteAll();
		return successGet();
	}
	
	@RequestMapping(value = "selectMerchantNumByAdId", method = RequestMethod.GET)
	public Result<MerchantInfoDTO> selectMerchantNumByAdId(@RequestParam Long id){
		
		MerchantInfoBO bo = adService.selectMerchantNumByAdId(id);
		MerchantInfoDTO dto = new MerchantInfoDTO();
		dto.setMerchantNum(bo.getMerchantNum());
		dto.setTitle(bo.getTitle());
		
		return successGet(dto);
	}
	
	/**
	 * 运营平台强制下架广告
	 * @param id
	 * @param auditorId
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "downOperatorById", method = RequestMethod.PUT)
	public Result downOperatorById(@RequestParam Long id, @RequestParam Integer auditorId, @RequestParam String remark) {
		adService.downOperatorById(id, auditorId, remark);
		return successCreated();
	}
	
	/**
	 * 根据ID查询第三方支付时需要的参数
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectAdPayInfoById/{id}", method = RequestMethod.GET)
	public Result<AdPayInfoDTO> selectAdPayInfoById(@PathVariable Long id) {
		AdBO bo = adService.get(id);
		AdPayInfoDTO dto = new AdPayInfoDTO();
		dto.setMerchantRegionPath(bo.getMerchantRegionPath());
		dto.setTotalPoint(bo.getTotalPoint());
		dto.setAdOrderNum(bo.getAdOrderNum());
		return successCreated(dto);
	}
	
	/**
	 * 广告是否支付成功
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "isPay/{id}", method = RequestMethod.GET)
	public Result<Boolean> isPay(@PathVariable Long id) {
		Boolean flag = adService.isPay(id);
		return successCreated(flag);
	}
	
	
	/**
	 * 商家端广告领取详情
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getDetailPage", method = RequestMethod.POST)
	public Result<Page<PointGetDetailDTO>> getDetailPage(@RequestBody PointGetDetailParam param) {
		
		Page<PointGetDetailDTO> pageDetail = new Page<>();
		Page<PointGetDetailBO> page = adService.getDetailPage(param);
		List<PointGetDetailBO> list = page.getRecords();
		
		List<PointGetDetailDTO> listDetail = new ArrayList<>();
		for (PointGetDetailBO pointGetDetailBO : list) {
			 PointGetDetailDTO detailDTO = new PointGetDetailDTO();
			 detailDTO.setMemberId(pointGetDetailBO.getMemberId());
			 detailDTO.setGmtCreate(pointGetDetailBO.getGmtCreate());
			 detailDTO.setPoint(pointGetDetailBO.getPoint());
			 listDetail.add(detailDTO);
		}
		
		pageDetail.setCurrentPage(page.getCurrentPage());
		pageDetail.setRecords(listDetail);
		pageDetail.setTotalCount(page.getTotalCount());
		
		return successGet(pageDetail);
		
	}
	
	
	
	/**
	 * 获取广告剩余数量
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getInventory/{id}", method = RequestMethod.GET)
	public Result<Integer> getInventory(@PathVariable Long id){
		Integer inventory = 0;
        try {
            inventory = adService.getInventory(id);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
        return successCreated(inventory);
	}

	/**
	 * 是否存在
	 * @return
	 */
	@RequestMapping(value = "haveAdPraise", method = RequestMethod.GET)
	public Result<HaveAdPraiseDTO> haveAdPraise(){

		HaveAdPraiseDTO haveDTO = new HaveAdPraiseDTO();
        haveDTO.setIsHave(adService.haveAdPraise());

        return successGet(haveDTO);
	}
}
