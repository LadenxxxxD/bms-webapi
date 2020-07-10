package com.springboot;

import com.springboot.util.JwtInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// // 拦截路径可自行配置多个 可用 ，分隔开
	// registry.addInterceptor(new
	// JwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
	// }

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("*").maxAge(3600 * 24);
	}
}
