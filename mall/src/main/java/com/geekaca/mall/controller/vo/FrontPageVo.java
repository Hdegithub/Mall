package com.geekaca.mall.controller.vo;

public class FrontPageVo {
    //搜索关键字
    private String keyword;
    //分类id
    private Long goodsCategoryId;
    //orderBy
    private String orderBy;
    //当前页码
    private Integer pageNumber;
    //每页显示条数
    private Integer pageRecord = 10;

    //起始索引   limit的第一个参数
    private Integer startIndex;

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageRecord() {
        return pageRecord;
    }

    public void setPageRecord(Integer pageRecord) {
        this.pageRecord = pageRecord;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
