package com.lawu.eshop.operator.api.service;

import java.util.List;

import com.lawu.eshop.user.dto.UserGradeDTO;
import com.lawu.eshop.user.param.UserGradeQuery;
import com.lawu.eshop.user.param.UserGradeUpdateParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 会员等级
 */
@FeignClient(value = "user-srv", path = "grade/")
public interface UserGradeService {


    /**
     * 分页查询
     *
     * @param query
     * @return
     * @author yangqh
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    Page<UserGradeDTO> selectPage(@RequestBody UserGradeQuery query);

    /**
     * 修改
     *
     * @param id
     * @param param
     * @author yangqh
     */
    @RequestMapping(value = "updateById/{id}", method = RequestMethod.POST)
    Result updateById(@PathVariable("id") Long id, @RequestBody UserGradeUpdateParam param);

    /**
     * 保存
     *
     * @param param
     * @return
     * @author yangqh
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    Result save(@RequestBody UserGradeUpdateParam param);

    /**
     *
     * @param id
     * @return
     * @author yangqh
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    UserGradeDTO selectUserGradeById(@PathVariable("id") Long id);

    /**
     * 查询会员等级
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "selectUserGradeList")
    Result<List<UserGradeDTO>> selectLotteryActivityPointByGradeValue();
}
