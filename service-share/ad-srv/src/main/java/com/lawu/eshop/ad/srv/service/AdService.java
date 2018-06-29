package com.lawu.eshop.ad.srv.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.AuditEnum;
import com.lawu.eshop.ad.param.AdChoicenessInternalParam;
import com.lawu.eshop.ad.param.AdEgainInternalParam;
import com.lawu.eshop.ad.param.AdFindParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdMerchantParam;
import com.lawu.eshop.ad.param.AdPointInternalParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.ad.param.AdSaveParam;
import com.lawu.eshop.ad.param.AdSetPayParam;
import com.lawu.eshop.ad.param.ListAdParam;
import com.lawu.eshop.ad.param.OperatorAdParam;
import com.lawu.eshop.ad.param.PointGetDetailParam;
import com.lawu.eshop.ad.srv.bo.AdBO;
import com.lawu.eshop.ad.srv.bo.AdClickPraiseInfoBO;
import com.lawu.eshop.ad.srv.bo.AdDetailBO;
import com.lawu.eshop.ad.srv.bo.AdEgainBO;
import com.lawu.eshop.ad.srv.bo.AdEgainDetailBO;
import com.lawu.eshop.ad.srv.bo.AdPointBO;
import com.lawu.eshop.ad.srv.bo.AdPraiseBO;
import com.lawu.eshop.ad.srv.bo.AdSaveInfoBO;
import com.lawu.eshop.ad.srv.bo.ChoicenessAdBO;
import com.lawu.eshop.ad.srv.bo.ClickAdPointBO;
import com.lawu.eshop.ad.srv.bo.ClickPointBO;
import com.lawu.eshop.ad.srv.bo.MerchantInfoBO;
import com.lawu.eshop.ad.srv.bo.OperatorAdBO;
import com.lawu.eshop.ad.srv.bo.PointGetDetailBO;
import com.lawu.eshop.ad.srv.bo.RedPacketInfoBO;
import com.lawu.eshop.ad.srv.bo.RedPacketIsSendBO;
import com.lawu.eshop.ad.srv.bo.ReportAdBO;
import com.lawu.eshop.ad.srv.bo.ViewBO;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.framework.core.page.Page;

/**
 * E赚接口
 *
 * @author zhangrc
 * @date 2017/4/6
 */
public interface AdService {

    /**
     * 商家发布E赚
     *
     * @param adSaveParam
     * @return
     */ 
	AdSaveInfoBO saveAd(AdSaveParam adSaveParam);

    /**
     * 商家E赚查询
     *
     * @param adMerchantParam
     * @param merchantId
     * @return
     */
    Page<AdBO> selectListByMerchant(AdMerchantParam adMerchantParam, Long merchantId);

    /**
     * 运营平台(商家)对E赚的管理(下架)
     *
     * @param id
     * @return
     */
    Integer updateStatus(Long id);

    /**
     * 运营平台(商家)对E赚的管理(删除)
     *
     * @param id
     * @return
     */
    Integer remove(Long id);

    /**
     * 审核视频广告
     *
     * @param id
     * @param auditorId
     * @param remark
     * @param auditEnum
     * @return
     */
    Integer auditVideo(Long id, Integer auditorId, String remark, AuditEnum auditEnum);

    /**
     * 运营平台对E赚的查询
     *
     * @param adPlatParam
     * @return
     */
    Page<AdBO> selectListByPlatForm(AdFindParam adPlatParam);

    /**
     * 会员对E赚的观看
     *
     * @param adMemberParam
     * @param memberId
     * @return
     */
    Page<AdBO> selectListByMember(AdMemberParam adMemberParam, Long memberId);

    /**
     * 查看广告详情
     *
     * @param id
     * @return
     */
    AdEgainDetailBO selectAbById(Long id, Long memberId);

    /**
     * 抢赞详情
     * @param id
     * @param memberId
     * @return
     */
    AdPraiseBO selectAdPraiseById(Long id, Long memberId);


    /**
     * 会员对E赚的观看
     *
     * @param adPraiseParam
     * @return
     */
    Page<AdBO> selectPraiseListByMember(AdPraiseParam adPraiseParam,Long memberId);

    /**
     * 抢赞
     *
     * @param id
     * @param memberId
     * @return
     * @throws SQLException
     * @throws DataNotExistException
     */
    AdClickPraiseInfoBO clickPraise(Long id, Long memberId, String num) throws DataNotExistException, SQLException;

    /**
     * 点击广告
     *
     * @param id
     * @param memberId
     * @return
     * @throws SQLException
     * @throws DataNotExistException
     */
    ClickPointBO clickAd(Long id, Long memberId, String num) throws DataNotExistException, SQLException;



    /**
     * 今日精选
     *
     * @param adMemberParam
     * @return
     */
    Page<AdBO> selectChoiceness(AdMemberParam adMemberParam);

    /**
     * 判断用户是否发送过红包
     *
     * @param merchantId
     * @return
     */
    RedPacketIsSendBO selectRPIsSend(Long merchantId);

    /**
     * 领取红包
     *
     * @param memberId
     * @return
     */
    BigDecimal getRedPacket(Long merchantId, Long memberId, String memberNum);

    /**
     * 广告信息获取
     *
     * @param id
     * @return
     */
    AdBO get(Long id);

    /**
     * 根据用户查询是否已经抢到红包
     *
     * @param memberId
     * @return
     */
    Boolean selectRedPacketByMember(Long merchantId, Long memberId);

    /**
     * 点击广告获取的积分
     *
     * @param memberId
     * @return
     */
    ClickAdPointBO getClickAdPoint(Long memberId, BigDecimal point);

    /**
     * 获取广告
     */
    List<ViewBO> getAllAd();

    /**
     * 修改广告浏览次数
     *
     * @param id
     */
    void updateViewCount(Long id, Integer count);

    /**
     * 运营后天查询广告列表
     *
     * @param listAdParam
     * @return
     */
    Page<AdBO> listAllAd(ListAdParam listAdParam);

    /**
     * 商家端详情
     *
     * @param id
     * @return
     */
    AdBO selectById(Long id);

    /**
     * 运营后台操作广告(下架、删除)
     *
     * @param id
     * @param adStatusEnum
     */
    void operatorUpdateAdStatus(Long id, AdStatusEnum adStatusEnum);

    /**
     * 查询上架中的平面视频广告
     *
     * @param listAdParam
     * @return
     */
    List<AdBO> listFlatVideoAd(ListAdParam listAdParam);

    /**
     * 重建平面视频广告索引
     */
    void rebuildAdIndex(Integer pageSize);

	/**
	 * 删除无效的平面视频广告索引
	 *
	 * @param typeEnum
	 */
	void delInvalidAdIndex(DelIndexTypeEnum typeEnum);

	/**
     * 根据商家id 获取红包的相关信息
     * @param merchantId
     * @return
     */
    RedPacketInfoBO getRedPacketInfo(Long merchantId);
    
	/**
	 * 判断商家红包是否领完
	 * @param merchantId
	 * @return
	 */
	Boolean isExistsRedPacket(Long merchantId);
	
	/**
	 * 判断商家是否发过红包
	 * @param merchantId
	 * @return
	 */
	Boolean isSendRedPacket(Long merchantId);

	
	/**
	 * 商家端广告批量删除
	 * @param adIds
	 */
	void batchDeleteAd(List<Long> adIds);
	
	/**
	 * 商家广告详情
	 * @param id
	 * @return
	 */
	AdDetailBO selectDetail(Long id);

	/**
	 * 判断是否是当前用户的数据
	 * @param id
	 * @param merchantId
	 * @return
	 */
	Boolean isMyData(Long id ,Long merchantId);

	/**
	 * 统计广告
	 * @return
	 */
	List<ReportAdBO> selectReportAdEarnings();
	
	/**
	 * 分页查询E赚广告
	 * 
	 * @param memberId 会员id
	 * @param param 查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	Page<AdEgainBO> selectPageAdEgain(Long memberId, AdEgainInternalParam param);
	
	/**
	 * 查询积分排行版广告
	 * 
	 * @param param 查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	List<AdPointBO> selectAdPoint(AdPointInternalParam param);
	
	/**
	 * 分页查询精选推荐广告
	 * 
	 * @param memberId 会员id
	 * @param param 查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	Page<ChoicenessAdBO> selectPageChoicenessAd(Long memberId, AdChoicenessInternalParam param);

	/**
	 * 运营平台查询广告
	 * @param adEgainType 视频|平面
	 * @return
	 */
	Page<OperatorAdBO> selectOperatorAdAll(OperatorAdParam operatorAdParam);

    void soldOutAdByMerchantId(Long merchantId);
    
	
	MerchantInfoBO selectMerchantNumByAdId(Long id);
	
	/**
	 * 运营平台强制下架广告
	 * @param id
	 * @param auditorId
	 * @param remark
	 */
	void downOperatorById(Long id, Integer auditorId, String remark);
	
	/**
	 * 支付成功之后回调修改广告状态
	 * @param param
	 */
	void updateAdIsPay(AdSetPayParam param);
	
	/**
	 * 判断是否支付完成
	 * @param id
	 * @return
	 */
	Boolean isPay(Long id);
	
	/**
	 * 商家广告详情领取列表
	 * @param param
	 * @return
	 */
	Page<PointGetDetailBO> getDetailPage(PointGetDetailParam param);
	
	/**
	 * 获取剩余广告数量
	 * @param id
	 * @return
	 */
	Integer getInventory(Long id);

	/**
	 * 是否存在即将开始和正在进行中的E咻
	 * @return
	 */
	Boolean haveAdPraise();
}
