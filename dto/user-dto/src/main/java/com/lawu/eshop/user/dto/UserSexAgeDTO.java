package com.lawu.eshop.user.dto;

import com.lawu.eshop.common.constants.UserSexEnum;

/**
 * @author meishuquan
 * @date 2018/5/11.
 */
public class UserSexAgeDTO {

    private Integer age;

    private UserSexEnum sexEnum;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserSexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(UserSexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }
}
