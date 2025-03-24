package com.xxgames.util;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 本地化工具类
 */
public class LocalizationUtil {
    
    /**
     * 判断当前是否为英文
     * @param request HTTP请求
     * @return 是否为英文
     */
    public static boolean isEnglish(HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            Locale locale = localeResolver.resolveLocale(request);
            return locale != null && "en".equals(locale.getLanguage());
        }
        return false;
    }
    
    /**
     * 获取本地化文本
     * @param chinese 中文文本
     * @param english 英文文本
     * @param request HTTP请求
     * @return 根据当前语言返回对应文本
     */
    public static String getText(String chinese, String english, HttpServletRequest request) {
        return isEnglish(request) ? english : chinese;
    }
} 