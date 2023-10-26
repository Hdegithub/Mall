package com.geekaca.mall;

import cn.hutool.crypto.SecureUtil;
import com.geekaca.mall.domain.GoodsInfo;
import com.geekaca.mall.mapper.AdminUserMapper;
import com.geekaca.mall.mapper.GoodsCategoryMapper;
import com.geekaca.mall.mapper.GoodsInfoMapper;
import com.geekaca.mall.utils.NetUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
/**
 *  测试 数据 自动回滚 ，JUnit单元测试 insert, delete,update不会更新到DB
 *  同一个测试用例里面
 *  add
 *  select
 */
@Transactional
class MallApplicationTests {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Test
    void testLogin() {
        String md5Str = SecureUtil.md5("123456");
//        Assertions.assertEquals("e10adc3949ba59abbe56e057f20f883e", md5Str);
//        boolean isLoginOk = adminUserMapper.checkLogin("admin", md5Str);
//        //JUnit5
//        Assertions.assertTrue(isLoginOk);
    }

//    @Test
//    void testFindAll() {
//        List<GoodsCategory> categoryList = goodsCategoryMapper.findAll();
//        Assertions.assertNotNull(categoryList);
//        Assertions.assertTrue(categoryList.size() > 0);
//        categoryList.forEach((category)->{
//            System.out.println(category);
//        });
//    }

    /**
     * 单元测试，测试用例
     * 节省精力
     */
    @Test
    public void testSelectPageByName() {
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectPageByName(0, 10, "华为note");
        GoodsInfo goodsInfo = goodsInfoList.get(0);
        Assertions.assertNotNull(goodsInfoList);
        Assertions.assertNotNull(goodsInfo);
        String goodsName = goodsInfo.getGoodsName();
        boolean isContains = goodsName.contains("华为note");
        Assertions.assertTrue(isContains);

    }
    @Autowired
    NetUtil netUtil;
    @Test
    public void testCopy() {
//        List<GoodsAddParam> goodsAddParamList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            GoodsAddParam goodParam = new GoodsAddParam();
//            goodParam.setGoodsName("IPhone" + i);
//            goodParam.setGoodsIntro("苹果手机" + i);
//            goodsAddParamList.add(goodParam);
//        }
//
//        // 复制集合数据到一个新的集合 （原始集合， 目标类型）
//        //返回值 ： 新的类型的集合
//        List<GoodsInfo> goodsInfoList = BeanUtil.copyToList(goodsAddParamList, GoodsInfo.class);
//
//        Assertions.assertNotNull(goodsInfoList);
        System.out.println(netUtil.getIp());
        System.out.println(netUtil.getWebUploadPath());

    }


}
