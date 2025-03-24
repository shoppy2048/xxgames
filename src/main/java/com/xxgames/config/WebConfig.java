package com.xxgames.config;

import com.xxgames.interceptor.LanguageDetectionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Web配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置语言解析器
     * 使用Cookie存储用户的语言偏好
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH); // 默认使用英语
        resolver.setCookieName("language");
        resolver.setCookieMaxAge(3600 * 24 * 365); // 一年有效期
        return resolver;
    }

    /**
     * 配置语言切换拦截器
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 先注册语言切换拦截器
        registry.addInterceptor(localeChangeInterceptor());
        
        // 注意：不要注册LanguageDetectionInterceptor，因为它会覆盖默认语言设置
        // 如果需要浏览器语言检测，可以在用户首次访问时进行
    }
} 