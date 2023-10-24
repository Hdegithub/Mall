package com.geekaca.mall.mapper;

import com.geekaca.mall.controller.admin.param.GoodsUpdateParam;
import com.geekaca.mall.controller.vo.HotGoodsesVO;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author magol
 * @description 针对表【tb_newbee_mall_goods_info】的数据库操作Mapper
 * @createDate 2023-10-13 10:26:25
 * @Entity com.geekaca.mall.domain.GoodsInfo
 */
@Mapper
public interface GoodsInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int addGoods(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> findGoodsList(@Param("limit") Integer limit, @Param("pageSize") Integer pageSize, @Param("goodsName") String goodsName);

    int findGoodsCount(@Param("goodsName") String goodsName);

    List<GoodsInfo> selectgoodsall();

    //商品模糊搜索
    List<GoodsInfo> selectPageByName(@Param("limit") Integer limit, @Param("pageSize") Integer pageSize,@Param("goodsName") String goodsName);

    List<HotGoodsesVO>  findHotGoodsList();

    //修改商品状态
    int updateSellStatus(@Param("orderIds") Long[] orderIds,@Param("sellStatus") int sellStatus);

    List<GoodsInfo> findGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalGoodsBySearch(PageQueryUtil pageUtil);

    //修改商品数据
    int updateGoods(GoodsInfo goodsInfo);

    //根据id查找某一个商品的详细数据
    GoodsInfo getGoodsById(Long goodsCategoryId);
}
