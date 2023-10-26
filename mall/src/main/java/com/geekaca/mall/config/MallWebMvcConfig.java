package com.geekaca.mall.config;

import com.geekaca.mall.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MallWebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private TokenInterceptor tokenInterceptor;



    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //放行 swagger 的路径
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
        //放行 图片访问路径
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/goods-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }

    /**
     * 注册拦截器
     *
     * 用户请求----拦截器----> controller
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //针对哪些访问路径 设置拦截器   ，后端接口地址一定不要和前端页面的地址冲突
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/manage-api/**")
                .addPathPatterns("/api/**")
                //放行 后台的登陆
                .excludePathPatterns("/manage-api/v1/adminUser/login")
                //放行前台的注册和登陆
                .excludePathPatterns("/api/v1/user/register")
                .excludePathPatterns("/api/v1/user/login")
                .excludePathPatterns("/manage-api/v1/upload/**")
                .excludePathPatterns("/api/v1/index-infos");
    }
}
