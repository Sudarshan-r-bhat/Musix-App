package com.musix.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.musix.services.UserService;

@Configuration
public class FIltersConfiguration {

	@Autowired
	private UserService userService;
	
	@Bean
	public FilterRegistrationBean<AuthorizationFilter> setAuthorizationFilter() {
		
		FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new AuthorizationFilter());
		filterRegistrationBean.setOrder(4);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<LoginFilter> setLoginFilter() {
		
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LoginFilter(userService));
		filterRegistrationBean.setOrder(3);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<LogoutFilter> setLogoutFilter() {
		
		FilterRegistrationBean<LogoutFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LogoutFilter(userService));
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/logout");
		return filterRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean<JwtValidationFilter> setJwtValidationFilter() {
		
		FilterRegistrationBean<JwtValidationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtValidationFilter(userService));
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<GeneralPathFilter> setGeneralPathFilter() {
		
		FilterRegistrationBean<GeneralPathFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new GeneralPathFilter());
		filterRegistrationBean.setOrder(0);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	
}
