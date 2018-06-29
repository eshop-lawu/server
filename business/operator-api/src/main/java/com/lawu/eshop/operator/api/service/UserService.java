package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.operator.dto.UserDTO;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.eshop.operator.param.UserPageParam;
import com.lawu.eshop.operator.param.UserParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
@FeignClient(value = "operator-srv")
public interface UserService {

    @RequestMapping(value = "user/withPwd/{account}",method = RequestMethod.POST)
    public Result<UserDTO> find(@PathVariable("account") String account, @RequestParam(value = "pwd") String pwd);

    @RequestMapping(value = "user/findByAccount/{account}",method = RequestMethod.POST)
    Result<UserDTO> find(@PathVariable(value = "account") String account);

    /**
     * 新增用户
     * @param account
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "user/addUser/",method = RequestMethod.POST)
    Result addUser(@RequestParam(value = "account") String account,@RequestParam(value = "name") String name,@RequestParam(value = "password") String password);

    /**
     * 编辑用户
     * @param userParam
     * @return
     */
    @RequestMapping(value = "user/editUser", method = RequestMethod.PUT)
    Result editUser(@ModelAttribute UserParam userParam);

    @RequestMapping(value = "user/findUserList", method = RequestMethod.POST)
    Result<Page<UserListDTO>> findUserList(@ModelAttribute UserPageParam pageParam);

    /**
     * 分配角色
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "user/assignRoles", method = RequestMethod.POST)
    Result assignRoles(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "roleId") String roleId);

    @RequestMapping(value = "user/delUser/{id}", method = RequestMethod.PUT)
    Result delUser(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "user/userDisabled/{id}", method = RequestMethod.PUT)
    Result userDisabled(@PathVariable(value = "id") Integer id);

    /**
     * 启用用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "user/userEnable/{id}", method = RequestMethod.PUT)
    Result userEnable(@PathVariable(value = "id") Integer id);

    /**
     * 查询用户信息详情
     * @param id
     * @return
     */
    @RequestMapping(value = "user/findUserById/{id}", method = RequestMethod.GET)
    Result<UserListDTO> findUserById(@PathVariable(value = "id") Integer id);

    /**
     * 根据账号查询用户
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "user/getUser/{account}",method = RequestMethod.GET)
    Result<UserListDTO> getUserByAccount(@PathVariable(value = "account") String account);
}
