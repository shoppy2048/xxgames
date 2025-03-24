package com.xxgames.service;

import com.xxgames.model.Category;
import com.xxgames.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 游戏分类服务类
 */
@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    /**
     * 获取所有分类
     * @return 分类列表
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderByDisplayOrderAsc();
    }
    
    /**
     * 根据ID获取分类
     * @param id 分类ID
     * @return 分类对象
     */
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    /**
     * 根据名称获取分类
     * @param name 分类名称
     * @return 分类对象
     */
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    /**
     * 保存分类
     * @param category 分类对象
     * @return 保存后的分类对象
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    /**
     * 删除分类
     * @param id 分类ID
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
} 