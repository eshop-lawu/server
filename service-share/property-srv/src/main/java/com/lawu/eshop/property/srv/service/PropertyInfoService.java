package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.param.BackagePropertyinfoDataParam;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.eshop.property.param.PropertyInfoBackageParam;
import com.lawu.eshop.property.srv.bo.FreezeBO;
import com.lawu.eshop.property.srv.bo.PropertyBalanceBO;
import com.lawu.eshop.property.srv.bo.PropertyInfoBO;
import com.lawu.eshop.property.srv.bo.PropertyPointAndBalanceBO;
import com.lawu.eshop.property.srv.bo.PropertyPointBO;
import com.lawu.framework.core.page.Page;

import java.math.BigDecimal;

/**
 * 资产管理服务接口
 *
 * @author meishuquan
 * @date 2017/3/27
 */
public interface PropertyInfoService {

    /**
     * 根据用户编号查询用户信息
     *
     * @param userNum 用户编号
     * @return
     */
    PropertyInfoBO getPropertyInfoByUserNum(String userNum);

    /**
     * 根据用户编号修改支付密码
     *
     * @param userNum 用户编号
     * @param newPwd  新密码
     */
    void updatePayPwd(String userNum, String newPwd);

    /**
     * 根据用户编号查询余额
     *
     * @param userNum 用户编号
     * @return
     */
    PropertyBalanceBO getPropertyBalanceByUserNum(String userNum);

    /**
     * 根据用户编号查询积分
     *
     * @param userNum 用户编号
     * @return
     */
    PropertyPointBO getPropertyPointByUserNum(String userNum);

    /**
     * @param userNum 用户编号
     * @param column  列名：B-余额，P-积分，L-爱心账户
     * @param flag    标记:A-加，M-减
     * @param number  余额、积分、爱心账户的数额
     * @return
     */
    int updatePropertyNumbers(String userNum, String column, String flag, BigDecimal number);

    /**
     * 校验资产财产记录、余额、支付密码、冻结情况
     *
     * @param userNum 用户编号
     * @param amount  金额
     * @param isVerifyPwd 是否校验支付密码
     * @param inPwd 传入支付密码
     * @return
     */
    int validateBalance(String userNum, String amount,boolean isVerifyPwd,String inPwd);

    /**
     * 校验积分是否足够
     *
     * @param userNum 用户编号
     * @param point   金额
     * @return
     */
    int validatePoint(String userNum, String point);

    /**
     * 获取用户商家积分余额信息
     *
     * @param userNum
     * @return
     */
    PropertyPointAndBalanceBO getPropertyInfoMoney(String userNum);

    /**
     * 新增用户资产信息
     *
     * @param userNum
     */
    void savePropertyInfo(String userNum);
    
    /**
     * 爱心账户
     * @param userNum
     * @return
     */
    BigDecimal selectLoveAccount(String userNum);

    /**
     * 运营平台余额积分处理
     * @param dparam
     * @return
     * @author yangqh
     * @date 2017年5月16日 下午2:39:15
     */
	int updateBalanceAndPoint(BackagePropertyinfoDataParam dparam);

	/**
	 * 运营平台冻结解冻
	 * @param userNum
	 * @param freeze
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午4:45:22
	 */
	int updatePropertyinfoFreeze(String userNum, PropertyinfoFreezeEnum freeze);

	/**
	 * 运营平台查询用户冻结情况
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午5:21:20
	 */
    Page<PropertyInfoBO> getPropertyinfoPageList(PropertyInfoBackageParam param);

    /**
     * 获取用户是否冻结
     * @param userNum
     * @return
     * @author yangqh
     * @date 2017年5月26日 上午11:08:53
     */
    PropertyinfoFreezeEnum getPropertyinfoFreeze(String userNum);

    /**
     * 获取商家冻结资金列表y     *
     * @param param
     * @return
     */
    Page<FreezeBO> getFreezeList(FreezeQueryParam param);
}
