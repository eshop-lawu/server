package com.lawu.eshop.operator.api.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.operator.api.event.EventPublisher;
import com.lawu.eshop.operator.param.LogEventParam;
import com.lawu.shiro.util.UserUtil;

/**
 * @author zhangrc
 * @date 2017/01/09
 */
@Component
@Aspect
public class LogRecordAspect {
	
	private static Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);
	
	@Autowired
	private EventPublisher eventPublisher;
	
	private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

	@Pointcut("execution(* *(..)) && @annotation(com.lawu.eshop.operator.api.log.LogRecord)")
	public void logServiceAspect() {}
	
	/**
	 * 方法执行
	 * @param joinPoint
	 */
	@Around("logServiceAspect()")
    public Object advice(ProceedingJoinPoint  joinPoint) {
		Object result = null;
		handleLog(joinPoint, null);
		try {
			result = joinPoint.proceed();
			return result;
		} catch (Throwable e) {
			logger.error("日志异常--"+e.getMessage());
			return null;
		}
		
    }
	
	
	private void handleLog(ProceedingJoinPoint joinPoint, Exception e) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (method == null) {
            return ;
        }
        LogRecord logEven = method.getAnnotation(LogRecord.class);
		if(logEven ==  null){
			return ;
		}
		
		//获取接口参数
        JSONObject jsonObject = new JSONObject();
        LogEventParam<String> logParam = new LogEventParam();
        
        String[] parameterNameArr = parameterNameDiscoverer.getParameterNames(method);
        Parameter[] parameters = method.getParameters();
        String idParamName = logEven.idParamName();
        String[] str = idParamName.split("\\.");
        for (int i = 0; i < joinPoint.getArgs().length; i++) { 
			if (StringUtils.isNotEmpty(logEven.idParamName())) {

				if (str.length == 1 && parameterNameArr[i].equals(str[0])) {
					logParam.setBusinessId(joinPoint.getArgs()[i].toString());
				}
			} else {
				logParam.setBusinessId("0");
			}
        	 Annotation[] annotations = parameters[i].getAnnotations();
        	 for (Annotation annotation : annotations) {
        		if(annotation instanceof ModelAttribute){
					Field[] fields = joinPoint.getArgs()[i].getClass().getDeclaredFields();
					for (int j = 0; j < fields.length; j++) {
						
						fields[j].setAccessible(true);
						try {
							if(str.length == 2 && fields[j].getName().equals(str[1])){
			    				logParam.setBusinessId(fields[j].get(joinPoint.getArgs()[i]).toString());
			    			}
							if (fields[j].get(joinPoint.getArgs()[i]) != null
									&& StringUtils.isNotEmpty(fields[j].get(joinPoint.getArgs()[i]).toString())
									&& fields[j].get(joinPoint.getArgs()[i]).toString() != "") {
								jsonObject.put(fields[j].getName(), fields[j].get(joinPoint.getArgs()[i]));
							}
						} catch (IllegalArgumentException e1) {
							logger.error("获取请求参数错误--"+e1.getMessage());
						} catch (IllegalAccessException e1) {
							logger.error("获取请求参数错误--"+e1.getMessage());
						}
					}
				}else{
					jsonObject.put(parameterNameArr[i],joinPoint.getArgs()[i]);
				}
				
				break;
			 }
             
        }  
       
		
		if(logEven.status() != -1){
			jsonObject.put("status", logEven.status());
		}
        logParam.setAccount(UserUtil.getCurrentUserAccount());
        logParam.setTypeEnum(logEven.type());
        logParam.setModuleEnum(logEven.title().getModel());
        logParam.setTitleEnum(logEven.title());
		if (!jsonObject.isEmpty()) {
			logParam.setChangeContent(jsonObject.toString());
		}
        eventPublisher.publishLogEvent(logParam);
	}
	

}
