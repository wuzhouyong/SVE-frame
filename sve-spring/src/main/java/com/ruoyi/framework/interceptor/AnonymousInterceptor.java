package com.ruoyi.framework.interceptor;

import com.ruoyi.framework.interceptor.annotation.Anonymous;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 匿名请求拦截器
 * 
 * @author ruoyi
 */
@Component
public class AnonymousInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(Anonymous.class)
            || handlerMethod.getBeanType().getAnnotation(Anonymous.class) != null)
            {
                return true;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
