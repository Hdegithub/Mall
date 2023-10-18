package com.geekaca.mall.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_newbee_mall_carousel
 */
public class Carousel implements Serializable {
    /**
     * 首页轮播图主键id
     */
    private Integer carouselId;

    /**
     * 轮播图
     */
    private String carouselUrl;

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    private String redirectUrl;

    /**
     * 排序值(字段越大越靠前)
     */
    private Integer carouselRank;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建者id
     */
    private Integer createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改者id
     */
    private Integer updateUser;

    private static final long serialVersionUID = 1L;

    /**
     * 首页轮播图主键id
     */
    public Integer getCarouselId() {
        return carouselId;
    }

    /**
     * 首页轮播图主键id
     */
    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    /**
     * 轮播图
     */
    public String getCarouselUrl() {
        return carouselUrl;
    }

    /**
     * 轮播图
     */
    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl;
    }

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * 排序值(字段越大越靠前)
     */
    public Integer getCarouselRank() {
        return carouselRank;
    }

    /**
     * 排序值(字段越大越靠前)
     */
    public void setCarouselRank(Integer carouselRank) {
        this.carouselRank = carouselRank;
    }

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建者id
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * 创建者id
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 修改者id
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * 修改者id
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Carousel other = (Carousel) that;
        return (this.getCarouselId() == null ? other.getCarouselId() == null : this.getCarouselId().equals(other.getCarouselId()))
            && (this.getCarouselUrl() == null ? other.getCarouselUrl() == null : this.getCarouselUrl().equals(other.getCarouselUrl()))
            && (this.getRedirectUrl() == null ? other.getRedirectUrl() == null : this.getRedirectUrl().equals(other.getRedirectUrl()))
            && (this.getCarouselRank() == null ? other.getCarouselRank() == null : this.getCarouselRank().equals(other.getCarouselRank()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarouselId() == null) ? 0 : getCarouselId().hashCode());
        result = prime * result + ((getCarouselUrl() == null) ? 0 : getCarouselUrl().hashCode());
        result = prime * result + ((getRedirectUrl() == null) ? 0 : getRedirectUrl().hashCode());
        result = prime * result + ((getCarouselRank() == null) ? 0 : getCarouselRank().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carouselId=").append(carouselId);
        sb.append(", carouselUrl=").append(carouselUrl);
        sb.append(", redirectUrl=").append(redirectUrl);
        sb.append(", carouselRank=").append(carouselRank);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}