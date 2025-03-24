package com.xxgames.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 语言检测拦截器
 * 用于检测用户浏览器语言并设置相应的语言
 */
public class LanguageDetectionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查是否已经设置了语言
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            Locale locale = localeResolver.resolveLocale(request);
            if (locale == null) {
                // 获取浏览器语言
                Locale browserLocale = request.getLocale();
                // 设置语言
                localeResolver.setLocale(request, response, browserLocale);
            }
        }
        return true;
    }
} 