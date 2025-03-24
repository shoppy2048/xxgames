package com.xxgames.repository;

import com.xxgames.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游戏数据访问接口
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
    /**
     * 根据分类ID查询游戏
     * @param categoryId 分类ID
     * @return 游戏列表
     */
    List<Game> findByCategoryId(Long categoryId);
    
    /**
     * 根据分类ID分页查询游戏
     * @param categoryId 分类ID
     * @param pageable 分页参数
     * @return 游戏分页结果
     */
    Page<Game> findByCategoryId(Long categoryId, Pageable pageable);
    
    /**
     * 查询热门游戏
     * @param limit 限制数量
     * @return 热门游戏列表
     */
    @Query("SELECT g FROM Game g ORDER BY g.popularity DESC")
    List<Game> findTopByPopularity(Pageable pageable);
    
    /**
     * 根据游戏名称搜索游戏
     * @param keyword 搜索关键词
     * @return 游戏列表
     */
    List<Game> findByNameContainingIgnoreCase(String keyword);
    
    /**
     * 根据名称或描述（中英文）搜索游戏
     */
    List<Game> findByNameContainingIgnoreCaseOrNameEnContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrDescriptionEnContainingIgnoreCase(
        String name, String nameEn, String description, String descriptionEn);
} 