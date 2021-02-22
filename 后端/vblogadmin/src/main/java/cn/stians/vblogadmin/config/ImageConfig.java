package cn.stians.vblogadmin.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:Z:/image/").addResourceLocations("file:Z:/articleImage/");
//        registry.addResourceHandler("/**").addResourceLocations("file:Z:/articleImage/");
    }
}