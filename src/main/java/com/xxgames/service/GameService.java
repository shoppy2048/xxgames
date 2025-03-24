package com.xxgames.service;

import com.xxgames.model.Game;
import com.xxgames.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 游戏服务类
 */
@Service
public class GameService {
    
    private final GameRepository gameRepository;
    
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    
    /**
     * 获取所有游戏
     * @return 游戏列表
     */
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
    
    /**
     * 根据ID获取游戏
     * @param id 游戏ID
     * @return 游戏对象
     */
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }
    
    /**
     * 根据分类ID获取游戏
     * @param categoryId 分类ID
     * @return 游戏列表
     */
    public List<Game> getGamesByCategory(Long categoryId) {
        return gameRepository.findByCategoryId(categoryId);
    }
    
    /**
     * 根据分类ID分页获取游戏
     * @param categoryId 分类ID
     * @param page 页码
     * @param size 每页大小
     * @param sortBy 排序字段
     * @param direction 排序方向
     * @return 游戏分页结果
     */
    public Page<Game> getGamesByCategory(Long categoryId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return gameRepository.findByCategoryId(categoryId, pageable);
    }
    
    /**
     * 获取热门游戏
     * @param limit 限制数量
     * @return 热门游戏列表
     */
    public List<Game> getHotGames(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return gameRepository.findTopByPopularity(pageable);
    }
    
    /**
     * 搜索游戏
     * @param keyword 搜索关键词
     * @return 游戏列表
     */
    public List<Game> searchGames(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        String searchTerm = "%" + keyword.trim().toLowerCase() + "%";
        return gameRepository.findByNameContainingIgnoreCaseOrNameEnContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrDescriptionEnContainingIgnoreCase(
            searchTerm, searchTerm, searchTerm, searchTerm);
    }
    
    /**
     * 保存游戏
     * @param game 游戏对象
     * @return 保存后的游戏对象
     */
    public Game saveGame(Game game) {
        if (game.getId() == null) {
            game.setCreatedAt(LocalDateTime.now());
        }
        game.setUpdatedAt(LocalDateTime.now());
        return gameRepository.save(game);
    }
    
    /**
     * 删除游戏
     * @param id 游戏ID
     */
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
    
    /**
     * 增加游戏点击量
     * @param id 游戏ID
     */
    public void incrementGamePopularity(Long id) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            game.incrementPopularity();
            gameRepository.save(game);
        }
    }
} 