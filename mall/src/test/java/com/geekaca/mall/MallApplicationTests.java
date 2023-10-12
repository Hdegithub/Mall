package com.geekaca.mall;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.geekaca.mall.mapper.AdminUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

@SpringBootTest
class MallApplicationTests {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Test
    void testLogin() {
        String md5Str = SecureUtil.md5("123456");
//        Assertions.assertEquals("e10adc3949ba59abbe56e057f20f883e", md5Str);
//        boolean isLoginOk = adminUserMapper.checkLogin("admin", md5Str);
//        //JUnit5
//        Assertions.assertTrue(isLoginOk);
    }

}
