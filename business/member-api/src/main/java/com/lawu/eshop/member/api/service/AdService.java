package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdEgainDTO;
import com.lawu.eshop.ad.dto.AdEgainQueryDTO;
import com.lawu.eshop.ad.dto.AdFlatVideoDTO;
import com.lawu.eshop.ad.dto.AdPointDTO;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.dto.AdSolrDTO;
import com.lawu.eshop.ad.dto.ChoicenessAdDTO;
import com.lawu.eshop.ad.dto.ClickAdPointDTO;
import com.lawu.eshop.ad.dto.HaveAdPraiseDTO;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.PointPoolDTO;
import com.lawu.eshop.ad.dto.PraisePointDTO;
import com.lawu.eshop.ad.dto.RedPacketInfoDTO;
import com.lawu.eshop.ad.param.AdChoicenessInternalParam;
import com.lawu.eshop.ad.param.AdEgainInternalParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdPointInternalParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface AdService {
	

	/**
	 * 查询广告
	 * @param adMerchantParam
	 * @param memberId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "ad/selectListByMember")
    Result<Page<AdDTO>> selectListByMember(@RequestBody AdMemberParam adMemberParam,@RequestParam("memberId") Long memberId);
	
	
	 /**
	  * 单个查询广告
	  * @return
	  */
	@RequestMapping(method = RequestMethod.GET, value = "ad/selectAbById/{id}")
	Result<AdEgainDTO> selectAbById(@PathVariable("id") Long id,@RequestParam("memberId") Long memberId);
	
	 /**
	  * 单个查询广告
	  * @return
	  */
	@RequestMapping(method = RequestMethod.GET, value = "ad/selectAdPraiseById/{id}")
	Result<AdPraiseDTO> selectAdPraiseById(@PathVariable("id") Long id,@RequestParam("memberId") Long memberId);

	/**
	 * E赞查询
	 * @param adPraiseParam
	 * @return
	 */
	@RequestMapping(value = "ad/selectPraiseListByMember", method = RequestMethod.GET)
    public Result<Page<AdDTO>> selectPraiseListByMember(@RequestBody AdPraiseParam adPraiseParam,@RequestParam("memberId") Long memberId);
	
	/**
	 * E赞前三名
	 * @param adId
	 * @return
	 */
	@RequestMapping(value = "pointPool/selectMemberList/{id}", method = RequestMethod.GET)
    public Result<List<PointPoolDTO>> selectMemberList(@PathVariable("id") Long id);
	
	/**
	 * 抢赞
	 * @param id
	 * @param memberId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "ad/clickPraise/{id}", method = RequestMethod.PUT)
    public Result<PraisePointDTO> clickPraise(@PathVariable("id") Long id,@RequestParam("memberId") Long memberId,@RequestParam("num") String num);
	
	/**
	 * 点击广告
	 * @param id
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "ad/clickAd/{id}", method = RequestMethod.PUT)
    public Result<ClickAdPointDTO> clickAd(@PathVariable("id") Long id,@RequestParam("memberId") Long memberId,@RequestParam("num") String num);
	
	
	/**
     * 会员APP广告搜索
     *
     * @param productSolrParam
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "ad/queryAdByTitle")
    Result<Page<AdSolrDTO>> queryAdByTitle(@ModelAttribute AdsolrFindParam adSolrParam);



    /**
     * 今日精选
     * @param adChoicenessParam
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "ad/selectChoiceness")
	Result<Page<AdDTO>> selectChoiceness(@ModelAttribute AdMemberParam param);
    
    /**
	 * 领取红包
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "ad/getRedPacket", method = RequestMethod.PUT)
    public Result<PraisePointDTO> getRedPacket(@RequestParam("merchantId")  Long  merchantId,@RequestParam("memberId")  Long  memberId,@RequestParam("memberNum") String memberNum);
	
	
	/**
	 * 获取红包相关信息
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "ad/getRedPacketInfo/{merchantId}", method = RequestMethod.GET)
	public Result<RedPacketInfoDTO> getRedPacketInfo(@PathVariable("merchantId") Long merchantId) ;
	
	/**
	 * 判断红包是否领取完
	 * @param adId
	 * @return
	 */
	@RequestMapping(value = "ad/isExistsRedPacket/{merchantId}", method = RequestMethod.GET)
	public Result<IsExistsRedPacketDTO> isExistsRedPacket(@PathVariable("merchantId") Long merchantId);
	
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
	@RequestMapping(value = "ad/pageAdEgain/{memberId}", method = RequestMethod.PUT)
	Result<Page<AdEgainQueryDTO>> selectPageAdEgain(@PathVariable("memberId") Long memberId, @RequestBody AdEgainInternalParam param);
	
	/**
	 * 查询积分排行榜广告
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	@RequestMapping(value = "ad/adPoint", method = RequestMethod.PUT)
	Result<List<AdPointDTO>> selectAdPoint(@RequestBody AdPointInternalParam param);
	
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
	@RequestMapping(value = "ad/choiceness/{memberId}", method = RequestMethod.PUT)
	Result<Page<ChoicenessAdDTO>> selectChoiceness(@PathVariable("memberId") Long memberId, @RequestBody AdChoicenessInternalParam param);

	/**
	 * 根据广告类型查询推荐的广告
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "ad/recommendAdByType", method = RequestMethod.POST)
	Result<Page<AdFlatVideoDTO>> getRecommendAdByType(@RequestBody AdSolrRealParam param);

	/**
	 * 查询推荐抢赞
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "ad/recommendEgain", method = RequestMethod.POST)
	Result<Page<AdPraiseDTO>> getRecommendEgain(@RequestBody AdSolrRealParam param);

	/**
	 * 平面广告排行榜
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "ad/listAdRank", method = RequestMethod.POST)
	Result<List<AdDTO>> listAdRank(@RequestBody AdSolrRealParam param);

	/**
	 * 广告首页
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "ad/selectChoiceness", method = RequestMethod.POST)
	Result<Page<AdDTO>> listAd(@RequestBody AdSolrRealParam param);

	/**
	 * 获取广告剩余数量
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "ad/getInventory/{id}", method = RequestMethod.GET)
	Result<Integer> getInventory(@PathVariable("id") Long id);
	
	/**
	 * 查询E咻是否存在即将开始和正在进行的
	 * @return
	 */
	@RequestMapping(value = "ad/haveAdPraise", method = RequestMethod.GET)
	public Result<HaveAdPraiseDTO> haveAdPraise();

}
