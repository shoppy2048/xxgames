package com.xxgames.model;

import javax.persistence.*;
import java.util.List;

/**
 * 游戏分类实体类
 * 用于存储游戏分类信息
 */
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 分类名称（中文）
     */
    @Column(nullable = false, length = 50)
    private String name;
    
    /**
     * 分类名称（英文）
     */
    @Column(name = "name_en", length = 50)
    private String nameEn;
    
    /**
     * 分类描述（中文）
     */
    @Column(length = 200)
    private String description;
    
    /**
     * 分类描述（英文）
     */
    @Column(name = "description_en", length = 200)
    private String descriptionEn;
    
    /**
     * 分类图标URL
     */
    @Column(length = 200)
    private String iconUrl;
    
    /**
     * 分类排序权重
     */
    private Integer displayOrder;
    
    /**
     * 该分类下的游戏列表
     */
    @OneToMany(mappedBy = "category")
    private List<Game> games;
    
    // 构造函数
    public Category() {
    }
    
    public Category(String name, String description, String iconUrl, Integer displayOrder) {
        this.name = name;
        this.description = description;
        this.iconUrl = iconUrl;
        this.displayOrder = displayOrder;
    }
    
    public Category(String name, String nameEn, String description, String descriptionEn, String iconUrl, Integer displayOrder) {
        this.name = name;
        this.nameEn = nameEn;
        this.description = description;
        this.descriptionEn = descriptionEn;
        this.iconUrl = iconUrl;
        this.displayOrder = displayOrder;
    }
    
    // Getter和Setter方法
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
    
    public String getIconUrl() {
        return iconUrl;
    }
    
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    public List<Game> getGames() {
        return games;
    }
    
    public void setGames(List<Game> games) {
        this.games = games;
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