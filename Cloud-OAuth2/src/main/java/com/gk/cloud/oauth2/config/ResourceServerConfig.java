package com.gk.cloud.oauth2.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 配置安全资源服务器
 * @author gk
 * 2019年4月13日 下午1:58:45
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http
	         .csrf().disable()
	         .exceptionHandling()
	         .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	     .and()
	         .authorizeRequests()
	         .anyRequest().authenticated()
	     .and()
	         .httpBasic();
	}
}
