package com.xxgames.controller;

import com.xxgames.model.Category;
import com.xxgames.model.Game;
import com.xxgames.service.CategoryService;
import com.xxgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 首页控制器
 */
@Controller
public class HomeController {
    
    private final CategoryService categoryService;
    private final GameService gameService;
    
    @Autowired
    public HomeController(CategoryService categoryService, GameService gameService) {
        this.categoryService = categoryService;
        this.gameService = gameService;
    }
    
    /**
     * 首页
     * @param model 模型
     * @param request HTTP请求
     * @return 视图名称
     */
    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        // 获取当前语言
        boolean isEnglish = isEnglish(request);
        model.addAttribute("isEnglish", isEnglish);
        
        // 直接添加国际化消息
        model.addAttribute("hotGamesTitle", isEnglish ? "Hot Games" : "热门游戏");
        model.addAttribute("allCategoriesTitle", isEnglish ? "All Categories" : "所有分类");
        
        // 获取所有分类
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        
        // 获取热门游戏
        List<Game> hotGames = gameService.getHotGames(10);
        model.addAttribute("hotGames", hotGames);
        
        return "index";
    }
    
    /**
     * 分类页面
     * @param categoryId 分类ID
     * @param page 页码
     * @param size 每页大小
     * @param sort 排序字段
     * @param direction 排序方向
     * @param model 模型
     * @param request HTTP请求
     * @return 视图名称
     */
    @GetMapping("/category/{categoryId}")
    public String category(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "popularity") String sort,
            @RequestParam(defaultValue = "DESC") String direction,
            Model model,
            HttpServletRequest request) {
        
        // 获取当前语言
        boolean isEnglish = isEnglish(request);
        model.addAttribute("isEnglish", isEnglish);
        
        // 获取分类信息
        categoryService.getCategoryById(categoryId).ifPresent(category -> {
            model.addAttribute("category", category);
        });
        
        // 获取分类下的游戏
        model.addAttribute("gamesPage", gameService.getGamesByCategory(categoryId, page, size, sort, direction));
        model.addAttribute("currentPage", page);
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentDirection", direction);
        
        // 获取所有分类（用于导航）
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        
        return "category";
    }
    
    /**
     * 游戏跳转
     * @param gameId 游戏ID
     * @return 重定向URL
     */
    @GetMapping("/game/{gameId}")
    public String redirectToGame(@PathVariable Long gameId) {
        // 增加游戏点击量
        gameService.incrementGamePopularity(gameId);
        
        // 获取游戏URL并重定向
        return gameService.getGameById(gameId)
                .map(game -> "redirect:" + game.getGameUrl())
                .orElse("redirect:/");
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