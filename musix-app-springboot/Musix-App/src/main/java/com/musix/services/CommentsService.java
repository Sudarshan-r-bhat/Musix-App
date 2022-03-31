package com.musix.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.musix.model.Comments;
import com.musix.model.User;
import com.musix.repository.CommentsDao;
import com.musix.repository.UserDao;

import io.jsonwebtoken.Jwts;

@Service
public class CommentsService {
	@Autowired
	CommentsDao commentsRepo;

	@Autowired
	UserDao userRepo;

	public ResponseEntity<Comments> addComment(Comments com) {

		try {

			User userObj = this.userRepo.findByEmail(com.getUseremail());
			this.commentsRepo.saveAndFlush(com);
			return new ResponseEntity<Comments>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Comments>(HttpStatus.UNAUTHORIZED);
		}
	}

	public List<Comments> getAllComments(Comments com) {

		try {
			User userObj = this.userRepo.findByEmail(com.getUseremail());
			List<Comments> comList = this.commentsRepo.getEmail(com.getUseremail());
			return comList;
		} catch (Exception e) {
			System.out.println("Exception is hit in Comments section.");
			return null;
		}
	}
}
