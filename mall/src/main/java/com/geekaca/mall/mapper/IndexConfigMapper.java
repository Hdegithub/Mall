package com.geekaca.mall.mapper;

import com.geekaca.mall.domain.IndexConfig;
import com.geekaca.mall.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 29484
* @description 针对表【tb_newbee_mall_index_config】的数据库操作Mapper
* @createDate 2023-10-19 15:34:44
* @Entity com.geekaca.mall.domain.IndexConfig
*/
@Mapper
public interface IndexConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IndexConfig record);

    int insertSelective(IndexConfig record);

    IndexConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexConfig record);

    int updateByPrimaryKey(IndexConfig record);

    List<IndexConfig> findIndexConfigList(PageQueryUtil pageUtil);

    int getIndexConfigsCount(PageQueryUtil pageUtil);
}
