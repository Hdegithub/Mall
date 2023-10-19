package com.geekaca.mall.mapper;

import com.geekaca.mall.controller.front.param.MallUserLoginParam;
import com.geekaca.mall.controller.front.param.MallUserRegisterParam;
import com.geekaca.mall.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author magol
* @description 针对表【tb_newbee_mall_user】的数据库操作Mapper
* @createDate 2023-10-18 15:26:12
* @Entity com.geekaca.mall.domain.User
*/
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //注册
    Integer insertUser(@Param("registerUser")MallUserRegisterParam mallUserRegisterParam);
    Integer findUser(@Param("userName")String userName);
    //登录
    String findByNameAndPass(@Param("loginName") String loginName, @Param("password") String passwordMd5);

    User findUserById(@Param("userId")long uidLong);
    //用户信息
    User userCheckLogin(@Param("loginName") String loginName, @Param("passwordMD5") String passwordMD5);

    List<User> findUserList(@Param("limit") Integer limit, @Param("pageSize") Integer pageSize);

    int findUserCount();

    int updateById(@Param("user")User updateUser, @Param("uid")long uidLong);
}
