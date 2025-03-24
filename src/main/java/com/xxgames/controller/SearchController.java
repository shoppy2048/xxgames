package com.xxgames.controller;

import com.xxgames.model.Category;
import com.xxgames.model.Game;
import com.xxgames.service.CategoryService;
import com.xxgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 搜索控制器
 */
@Controller
public class SearchController {
    
    private final GameService gameService;
    private final CategoryService categoryService;
    
    @Autowired
    public SearchController(GameService gameService, CategoryService categoryService) {
        this.gameService = gameService;
        this.categoryService = categoryService;
    }
    
    /**
     * 搜索页面
     * @param keyword 搜索关键词
     * @param model 模型
     * @param request HTTP请求
     * @return 视图名称
     */
    @GetMapping("/search-games")
    public String search(@RequestParam String keyword, Model model, HttpServletRequest request) {
        // 获取当前语言
        boolean isEnglish = isEnglish(request);
        model.addAttribute("isEnglish", isEnglish);
        
        List<Game> searchResults = gameService.searchGames(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResults", searchResults);
        
        // 获取所有分类（用于导航）
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        
        return "search";
    }
    
    /**
     * 判断当前是否为英文
     * @param request HTTP请求
     * @return 是否为英文
     */
    private boolean isEnglish(HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            Locale locale = localeResolver.resolveLocale(request);
            return locale != null && "en".equals(locale.getLanguage());
        }
        return false;
    }
} 