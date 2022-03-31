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

public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		boolean isAuthenticated = (boolean) httpRequest.getAttribute("isAuthenticated");
		if(isAuthenticated) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}

}
