  
package com.geekaca.mall.config.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 用在方法参数上
 * 运行时
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToAdminUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "adminUser";

}
