package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;
import com.lawu.eshop.ad.dto.ReportAdDTO;
import com.lawu.eshop.ad.dto.ReportAdEarningsDTO;
import com.lawu.eshop.ad.dto.ViewDTO;
import com.lawu.eshop.ad.param.AdReportParam;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月25日 上午10:47:56
 *
 */
@FeignClient(value= "ad-srv")
public interface AdSrvService {

	/**
	 * 获取未计算提成的会员点击广告记录
	 * @return
	 * @author yangqh
	 */
	@RequestMapping(method = RequestMethod.GET, value = "commission/getNoneCommissionAds")
	List<MemberAdRecodeCommissionDTO> getNoneCommissionAds(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);

	/**
	 * 修改为已计算提成
	 * @param id member_ad_record主键
	 * @return
	 * @author yangqh
	 */
	@RequestMapping(method = RequestMethod.POST, value = "commission/updateMemberAdRecardStatus")
	int updateMemberAdRecardStatus(@RequestParam("id") Long id);
	
	/**
	 * 查询所有广告集合
	 * @return
	 */
	@RequestMapping(value = "ad/getAllAd", method = RequestMethod.GET)
    public Result<List<ViewDTO>> getAllAd();
	
	/**
	 * 修改广告浏览次数
	 * @param id
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "ad/updateViewCount/{id}", method = RequestMethod.PUT)
    public Result<List<Long>> updateViewCount(@PathVariable("id")  Long  id,@RequestParam("count") Integer count);
	
	/**
	 * 广告统计
	 * @return
	 */
	@RequestMapping(value = "ad/selectReportAdEarnings", method = RequestMethod.GET)
	public Result<List<ReportAdDTO>> selectReportAdEarnings();
	
	
	@RequestMapping(value = "reportEarnings/getReportEarnings", method = RequestMethod.POST)
    public Result<List<ReportAdEarningsDTO>> getReportEarnings(@RequestBody AdReportParam param);

	/**
	 * 重建广告索引
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(method = RequestMethod.GET, value = "ad/rebuildAdIndex")
	Result rebuildAdIndex(@RequestParam("pageSize") Integer pageSize);

	/**
	 * 删除无效的广告索引
	 *
	 * @param typeEnum
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(method = RequestMethod.GET, value = "ad/delInvalidAdIndex")
	Result delInvalidAdIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum);

}
