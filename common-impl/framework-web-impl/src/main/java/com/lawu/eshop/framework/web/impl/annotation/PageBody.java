package com.lawu.eshop.framework.web.impl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * table数据处理注解
 * @author Leach
 * @date 2017/4/24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageBody {

}
