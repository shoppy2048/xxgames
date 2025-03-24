package com.xxgames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 语言切换控制器
 */
@Controller
public class LanguageController {

    /**
     * 切换语言
     * @param lang 语言代码
     * @param request HTTP请求
     * @param response HTTP响应
     * @return 重定向到之前的页面
     */
    @GetMapping("/language")
    public String changeLanguage(@RequestParam String lang, 
                                HttpServletRequest request, 
                                HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }
        
        // 获取之前的页面URL，如果没有则返回首页
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
    
    /**
     * 设置默认语言为英文
     * 这个方法会在应用启动时自动执行
     */
    @GetMapping("/setDefaultLanguage")
    public String setDefaultLanguage(HttpServletRequest request, HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            localeResolver.setLocale(request, response, Locale.ENGLISH);
        }
        return "redirect:/";
    }
} 