package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.eshop.user.param.SameIpCountParam;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.extend.AbnormalAccountDOView;
import com.lawu.eshop.user.srv.domain.extend.AreasCountView;
import com.lawu.eshop.user.srv.domain.extend.InviterUserDOView;
import com.lawu.eshop.user.srv.domain.extend.MemberDOView;

/**
 * @author zhangyong
 * @date 2017/6/5.
 */
public interface MemberDOMapperExtend {

    int delUserGtPush(Long memberId);
    
    List<MemberDOView> getMemberByIds(List<Long> memberIds);

	List<MemberDO> selectByExampleWithRowbounds(InviterUserDOView view);


    List<String> selectNumLikeContent(String queryContent);

    List<AbnormalAccountDOView> abnormalMemberList(AbnormalJobParam param);

    List<Integer> memberSameIpCount(SameIpCountParam param);

    int inviteMemberTotalCount(SameIpCountParam param);


    /**
     * 根据用户编号标记改用户所有的一级下线为僵尸账户
     * @param num
     * @author jiangxinjun
     * @createDate 2018年1月24日
     * @updateDate 2018年1月24日
     */
    void updateZombieAccount(@Param("num") String num, @Param("isZombie") boolean isZombie);

    /**
     * 区域通过年龄，性别 查询人数
     * @param view
     * @return
     */
	Integer findMemberCount(AreasCountView view);
}
