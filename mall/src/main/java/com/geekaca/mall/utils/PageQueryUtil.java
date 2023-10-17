package com.geekaca.mall.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 用来携带参数
 *
 * 分页查询参数
 * 可改进空间：
   JavaBean
 PageInfo{

 }
 */
public class PageQueryUtil extends LinkedHashMap<String, Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public PageQueryUtil(Map<String, Object> params) {
       //把params这个Map中的所有key value都放入自己的Map中
        this.putAll(params);
        if (params.get("page") == null){
            params.put("page", 1);
        }
        if (params.get("limit") == null){
            params.put("limit", 10);
        }
        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        //设置 mysql limit 第一个参数的值
        //limit默认丛0开始
        //100条数据     每页10条，  看第二页
        //limit (2-1)*10, 10     从第十条记录开始 ，向后查10条
        this.put("start", (page - 1) * limit);
        //当前第几页
        this.put("page", page);
        //每页显示几条数据
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
