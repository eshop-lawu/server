package com.lawu.eshop.framework.web.impl;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2017/4/24
 */
@ControllerAdvice
public class PageResponseBodyAdvice implements ResponseBodyAdvice {
    
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        PageBody pageBody = returnType.getMethod().getAnnotation(PageBody.class);
        if (pageBody != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof Result) {
            Result result = (Result) body;
            Object model = result.getModel();
            if(model instanceof Page) {
                Page pageModel = (Page) model;
                return new TableJson(pageModel);
            }
        }
        return body;
    }
}
