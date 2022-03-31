package com.musix.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.musix.model.Favourites;
import com.musix.model.Recommends;
import com.musix.model.User;
import com.musix.repository.RecommendsDao;
import com.musix.repository.UserDao;

import io.jsonwebtoken.Jwts;

@Service
public class RecommendsService {
	@Autowired
	RecommendsDao recommendsRepo;

	@Autowired
	UserDao userRepo;

	private String toggleRecommendStatus(Recommends recommends) {
		String rec = recommends.getRecommend();
		return rec.equals("yes") ? "no" : "yes";
	}

	public ResponseEntity<Recommends> addOrRemoveRecommends(Recommends rec) {

		User userObj = this.userRepo.getEmail(rec.getUseremail());

		try {
			Recommends recObj = recommendsRepo.getSongName(rec.getSongName());
			if (recObj != null) {
				recObj.setRecommend(toggleRecommendStatus(rec));
				this.recommendsRepo.saveAndFlush(recObj);
			} else {
				this.recommendsRepo.saveAndFlush(rec);
			}

		} catch (Exception e) {
			return new ResponseEntity<Recommends>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Recommends>(HttpStatus.ACCEPTED);
	}

	public List<Recommends> getAllRecommends(Recommends rec) {

		User userObj = this.userRepo.getEmail(rec.getUseremail());

		List<Recommends> recList = this.recommendsRepo.getEmail(rec.getUseremail());
		return recList;

	}
}
