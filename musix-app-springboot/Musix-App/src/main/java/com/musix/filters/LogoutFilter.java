package com.musix.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.musix.services.UserService;

public class LogoutFilter implements Filter{
	private UserService userService;
	
	public LogoutFilter(UserService userService) {
		this.userService = userService;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		boolean isAuthenticated = (boolean) httpRequest.getAttribute("isAuthenticated");
		
		if(!isAuthenticated) {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		String username = (String) httpRequest.getAttribute("username");
		this.userService.handleLogout(username);
		
		return;
		
	}

}
