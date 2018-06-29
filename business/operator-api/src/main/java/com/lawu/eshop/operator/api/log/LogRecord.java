package com.lawu.eshop.operator.api.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;

/**
 * 处理日志注解
 * 
 * type,title 必填  如果在调用接口上修改只有id 
 * 没有修改状态参数，可指定status,没有可不填
 * 
 * 修改，删除指定业务ID idParamName
 * 
 * @Description
 * @author zhangrc
 * @date 2018年01月09日
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {
	
	/**
	 * 操作类型
	 * @return
	 */
	OperationTypeEnum type();
	
	/**
	 * 操作标题
	 * @return
	 */
	LogTitleEnum title();
	
	/**
	 * 操作状态
	 * @return
	 */
	byte status() default -1;

	/**
	 * 业务Id名称
	 * @return
	 */
	String idParamName() default "";
	
}
