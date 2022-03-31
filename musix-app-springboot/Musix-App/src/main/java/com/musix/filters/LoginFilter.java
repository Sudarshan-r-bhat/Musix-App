package com.musix.filters;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.musix.model.User;
import com.musix.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginFilter implements Filter {
	
	private final String JWT_SECRET = "SECRET-KEY";
	private UserService userService;
	
	public LoginFilter(UserService userService) {
		this.userService = userService;
	}
	
	private String generateJwt(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", username);
		
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		String token = Jwts
						.builder()
						.setClaims(claims)
						.signWith(SignatureAlgorithm.HS256, JWT_SECRET)
						.setExpiration(date)
						.setSubject("jwt-string").compact();
		return token;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		boolean isAuthenticated = (boolean) httpRequest.getAttribute("isAuthenticated");
		
		if(!isAuthenticated) {
			String auth = httpRequest.getHeader("Authorization");
			
			if(auth == null) {
				httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
			
			String userNamePassword = 
					Base64
					.getDecoder()
					.decode(auth.split(" ")[1])
					.toString();
			
			int delimiterIdx = userNamePassword.indexOf(":");
			String username = userNamePassword.substring(0, delimiterIdx);
			String password = userNamePassword.substring(delimiterIdx + 1);
			
			// hash the password and compare with password stored in the db.
			User user = userService.getUser(username);
			String dbPassword = user.getPassword();
			if(password.equals(dbPassword)) {
				
				String jwtString = this.generateJwt(username);
				
				user.setSecretKey(jwtString);
				this.userService.saveUser(user);
				
				Cookie cookie = new Cookie("jwt", jwtString);
				httpResponse.addCookie(cookie);			
				httpResponse.setStatus(HttpStatus.OK.value());

			} else {
				httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
		}
		httpResponse.setStatus(HttpStatus.OK.value());
		chain.doFilter(httpRequest, httpResponse);
	}
	

	

}
