package ua.com.owu.sep2022springboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathToFloder = "file:///" + System.getProperty("user.home") + File.separator + "images" + File.separator;
        registry
                .addResourceHandler("/photo/**")
                .addResourceLocations(pathToFloder);
    }
}

//<img src="localhos:8080/photo/tom.png">
//<img src="localhos:8080/photo/jerry.png">
//<img src="localhos:8080/photo/merry.png">
