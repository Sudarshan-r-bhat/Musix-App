package com.musix.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musix.services.UserService;

import io.jsonwebtoken.Jwts;

public class JwtValidationFilter implements Filter {
	
	private final String JWT_SECRET = "SECRET-KEY";
	private UserService userService;
	
	public JwtValidationFilter (UserService userService) {
		this.userService = userService;
	}
	private boolean validateJwt(HttpServletRequest httpRequest, String jwt) {
		
		if(jwt == null) return false;
		
		try {
			
			String jwtBody = Jwts
					.parser()
					.setSigningKey(JWT_SECRET)
					.parseClaimsJws(jwt)
					.getBody().get("username", String.class);
			
			boolean isLoggedIn = this.userService.isLoggedIn(jwtBody);
			
			if(!isLoggedIn) 
				return false;
			
			httpRequest.setAttribute("username", jwtBody);
			
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String jwt = httpRequest.getHeader("jwt");
		boolean isValidJwt = validateJwt(httpRequest, jwt);
		
		if(!isValidJwt) {	
			httpRequest.setAttribute("isAuthenticated", false);
		} else {
			httpRequest.setAttribute("isAuthenticated", true);
			
		}
		chain.doFilter(httpRequest, httpResponse);
	}
}
