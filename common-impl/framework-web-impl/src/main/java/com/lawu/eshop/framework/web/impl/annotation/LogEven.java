package com.lawu.eshop.framework.web.impl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理日志注解
 * @Description
 * @author zhangrc
 * @date 2017年12月27日
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogEven {
	
	byte type();
	
	String title();
	
	byte module();
	
}
