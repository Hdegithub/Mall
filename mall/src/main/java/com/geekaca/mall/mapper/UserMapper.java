package com.geekaca.mall.mapper;

import com.geekaca.mall.domain.User;

/**
* @author magol
* @description 针对表【tb_newbee_mall_user】的数据库操作Mapper
* @createDate 2023-10-18 15:26:12
* @Entity com.geekaca.mall.domain.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
