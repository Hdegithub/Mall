package com.geekaca.mall.mapper;

import com.geekaca.mall.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ytdag
* @description 针对表【tb_newbee_mall_admin_user】的数据库操作Mapper
* @createDate 2023-10-12 15:33:37
* @Entity com.geekaca.mall.domain.AdminUser
*/
@Mapper
public interface AdminUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    AdminUser checkLogin(@Param("loginName") String loginName, @Param("passwordMD5") String passwordMD5);
}
