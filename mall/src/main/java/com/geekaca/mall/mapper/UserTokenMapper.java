package com.geekaca.mall.mapper;

import com.geekaca.mall.domain.UserToken;

/**
* @author 29484
* @description 针对表【tb_newbee_mall_user_token】的数据库操作Mapper
* @createDate 2023-10-19 15:41:43
* @Entity com.geekaca.mall.domain.UserToken
*/
public interface UserTokenMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);

}
