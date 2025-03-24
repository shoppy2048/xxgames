package com.xxgames.repository;

import com.xxgames.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游戏分类数据访问接口
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * 按显示顺序查询所有分类
     * @return 分类列表
     */
    List<Category> findAllByOrderByDisplayOrderAsc();
    
    /**
     * 根据名称查询分类
     * @param name 分类名称
     * @return 分类对象
     */
    Category findByName(String name);
} 