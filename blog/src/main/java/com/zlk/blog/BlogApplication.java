package com.zlk.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import xyz.teemor.tool.RedisUtil;

import javax.servlet.MultipartConfigElement;

//@EnableTransactionManagement
@SpringBootApplication
//@EnableCaching
@MapperScan("com.zlk.blog.mapper")
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	/**
	 * 文件上传临时路径
	 */
//	@Bean
//	MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setLocation("/app/tmp");
//		return factory.createMultipartConfig();
//	}
}
