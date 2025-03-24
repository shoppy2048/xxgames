package com.xxgames.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 游戏实体类
 * 用于存储游戏信息
 */
@Entity
@Table(name = "games", schema = "public")
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 游戏名称（中文）
     */
    @Column(nullable = false, length = 100)
    private String name;
    
    /**
     * 游戏名称（英文）
     */
    @Column(name = "name_en", length = 100)
    private String nameEn;
    
    /**
     * 游戏描述（中文）
     */
    @Column(length = 1000)
    private String description;
    
    /**
     * 游戏描述（英文）
     */
    @Column(name = "description_en", length = 1000)
    private String descriptionEn;
    
    /**
     * 游戏Logo URL
     */
    @Column(length = 200)
    private String logoUrl;
    
    /**
     * 游戏URL链接
     */
    @Column(length = 200)
    private String gameUrl;
    
    /**
     * 游戏热度/点击量
     */
    private Integer popularity = 0;
    
    /**
     * 游戏添加时间
     */
    private LocalDateTime createdAt = LocalDateTime.now();
    
    /**
     * 游戏更新时间
     */
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    /**
     * 游戏所属分类
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    // 构造函数、getter和setter方法
    
    public Game() {
    }
    
    public Game(String name, String description, String logoUrl, String gameUrl, Category category) {
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.gameUrl = gameUrl;
        this.category = category;
    }
    
    public Game(String name, String nameEn, String description, String descriptionEn, String logoUrl, String gameUrl, Category category) {
        this.name = name;
        this.nameEn = nameEn;
        this.description = description;
        this.descriptionEn = descriptionEn;
        this.logoUrl = logoUrl;
        this.gameUrl = gameUrl;
        this.category = category;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNameEn() {
        return nameEn;
    }
    
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescriptionEn() {
        return descriptionEn;
    }
    
    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }
    
    public String getLogoUrl() {
        return logoUrl;
    }
    
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    
    public String getGameUrl() {
        return gameUrl;
    }
    
    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }
    
    public Integer getPopularity() {
        return popularity;
    }
    
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    /**
     * 增加游戏点击量
     */
    public void incrementPopularity() {
        this.popularity++;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 根据当前语言获取名称
     * @param isEnglish 是否为英文
     * @return 对应语言的名称
     */
    public String getLocalizedName(boolean isEnglish) {
        return isEnglish && nameEn != null && !nameEn.isEmpty() ? nameEn : name;
    }
    
    /**
     * 根据当前语言获取描述
     * @param isEnglish 是否为英文
     * @return 对应语言的描述
     */
    public String getLocalizedDescription(boolean isEnglish) {
        return isEnglish && descriptionEn != null && !descriptionEn.isEmpty() ? descriptionEn : description;
    }
} 