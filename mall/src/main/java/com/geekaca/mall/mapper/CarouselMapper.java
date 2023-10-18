package com.geekaca.mall.mapper;

import com.geekaca.mall.controller.vo.CarouselVO;
import com.geekaca.mall.domain.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 30896
* @description 针对表【tb_newbee_mall_carousel】的数据库操作Mapper
* @createDate 2023-10-13 03:49:06
* @Entity com.study.mall.domain.Carousel
*/
@Mapper
public interface CarouselMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<CarouselVO> getCarousels(@Param("Count") Integer count);

    List<Carousel> findCarouselList(@Param("limit") Integer limit, @Param("pageSize") Integer pageSize);

    int findCarouselCount();
}
