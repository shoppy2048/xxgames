package com.xxgames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 初始化控制器
 * 用于设置默认语言
 */
@Controller
public class InitController {

    /**
     * 设置默认语言
     * 当用户首次访问网站时调用
     */
    @GetMapping("/init")
    public String init(HttpServletRequest request, HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            // 强制设置为英文
            localeResolver.setLocale(request, response, Locale.ENGLISH);
        }
        return "redirect:/";
    }
} 