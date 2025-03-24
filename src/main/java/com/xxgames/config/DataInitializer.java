package com.xxgames.config;

import com.xxgames.model.Category;
import com.xxgames.model.Game;
import com.xxgames.repository.CategoryRepository;
import com.xxgames.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 数据初始化类
 * 用于在应用启动时初始化一些示例数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final GameRepository gameRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository, GameRepository gameRepository) {
        this.categoryRepository = categoryRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 如果数据库中已有数据，则不初始化
        if (categoryRepository.count() > 0) {
            return;
        }

        // 创建游戏分类
        Category actionCategory = new Category("动作游戏", "Action Games", "包含各种动作元素的游戏", "Games with various action elements", "/images/categories/action.png", 1);
        Category puzzleCategory = new Category("益智游戏", "Puzzle Games", "需要思考和解谜的游戏", "Games that require thinking and puzzle solving", "/images/categories/puzzle.png", 2);
        Category strategyCategory = new Category("策略游戏", "Strategy Games", "需要战略思维的游戏", "Games that require strategic thinking", "/images/categories/strategy.png", 3);
        Category sportsCategory = new Category("体育游戏", "Sports Games", "模拟各种体育运动的游戏", "Games that simulate various sports", "/images/categories/sports.png", 4);
        Category rpgCategory = new Category("角色扮演", "RPG Games", "扮演角色进行冒险的游戏", "Games where you play a role and go on adventures", "/images/categories/rpg.png", 5);

        // 保存分类
        List<Category> categories = Arrays.asList(actionCategory, puzzleCategory, strategyCategory, sportsCategory, rpgCategory);
        categoryRepository.saveAll(categories);

        // 创建游戏
        // 动作游戏
        Game game1 = new Game("超级玛丽", "Super Mario", "经典的横版过关游戏", "Classic side-scrolling platform game", "/images/games/mario.png", "https://www.example.com/mario", actionCategory);
        Game game2 = new Game("街头霸王", "Street Fighter", "经典的格斗游戏", "Classic fighting game", "/images/games/streetfighter.png", "https://www.example.com/streetfighter", actionCategory);
        Game game3 = new Game("忍者神龟", "四只忍者龟的冒险", "/images/games/tmnt.png", "https://www.example.com/tmnt", actionCategory);
        
        // 益智游戏
        Game game4 = new Game("俄罗斯方块", "数字填充游戏", "/images/games/tetris.png", "https://www.example.com/tetris", puzzleCategory);
        Game game5 = new Game("数独", "数字填充游戏", "/images/games/sudoku.png", "https://www.example.com/sudoku", puzzleCategory);
        Game game6 = new Game("宝石迷阵", "三消类游戏", "/images/games/bejeweled.png", "https://www.example.com/bejeweled", puzzleCategory);
        
        // 策略游戏
        Game game7 = new Game("文明", "建立自己的文明", "/images/games/civilization.png", "https://www.example.com/civilization", strategyCategory);
        Game game8 = new Game("帝国时代", "即时战略游戏", "/images/games/age-of-empires.png", "https://www.example.com/aoe", strategyCategory);
        Game game9 = new Game("星际争霸", "科幻即时战略游戏", "/images/games/starcraft.png", "https://www.example.com/starcraft", strategyCategory);
        
        // 体育游戏
        Game game10 = new Game("FIFA足球", "足球模拟游戏", "/images/games/fifa.png", "https://www.example.com/fifa", sportsCategory);
        Game game11 = new Game("NBA 2K", "篮球模拟游戏", "/images/games/nba2k.png", "https://www.example.com/nba2k", sportsCategory);
        Game game12 = new Game("实况足球", "足球模拟游戏", "/images/games/pes.png", "https://www.example.com/pes", sportsCategory);
        
        // 角色扮演
        Game game13 = new Game("最终幻想", "日式角色扮演游戏", "/images/games/final-fantasy.png", "https://www.example.com/ff", rpgCategory);
        Game game14 = new Game("上古卷轴", "西式开放世界角色扮演", "/images/games/elder-scrolls.png", "https://www.example.com/elderscrolls", rpgCategory);
        Game game15 = new Game("巫师", "奇幻角色扮演游戏", "/images/games/witcher.png", "https://www.example.com/witcher", rpgCategory);

        // 设置一些游戏的热度
        game1.setPopularity(150);
        game4.setPopularity(120);
        game7.setPopularity(100);
        game10.setPopularity(90);
        game13.setPopularity(80);

        // 在run方法中添加英文游戏数据
        // 动作游戏英文版
        Game gameEn1 = new Game("Super Mario", "Classic side-scrolling platform game", "/images/games/mario.png", "https://www.example.com/mario", actionCategory);
        Game gameEn2 = new Game("Street Fighter", "Classic fighting game", "/images/games/streetfighter.png", "https://www.example.com/streetfighter", actionCategory);
        // ... 添加更多英文游戏

        // 保存游戏
        List<Game> games = Arrays.asList(
                game1, game2, game3, game4, game5, game6, game7, game8, game9,
                game10, game11, game12, game13, game14, game15
        );
        gameRepository.saveAll(games);
    }
} 