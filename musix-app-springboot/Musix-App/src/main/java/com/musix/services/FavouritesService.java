package com.musix.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.musix.model.Favourites;
import com.musix.model.User;
import com.musix.repository.FavouritesDao;
import com.musix.repository.UserDao;

@Service
public class FavouritesService {
	@Autowired
	FavouritesDao favouritesRepo;

	@Autowired
	UserDao userRepo;

	private String toggleFavouriteStatus(Favourites favourite) {
		String fav = favourite.getFavourite();
		return fav.equals("yes") ? "no" : "yes";
	}

	public ResponseEntity<Favourites> addOrRemoveFavourites(Favourites fav) {

		User userObj = this.userRepo.getEmail(fav.getUseremail());

		try {
			Favourites favObj = favouritesRepo.getSongName(fav.getSongName());
			if (favObj != null) {

				favObj.setFavourite(toggleFavouriteStatus(fav));
				this.favouritesRepo.saveAndFlush(favObj);
			} else {
				this.favouritesRepo.saveAndFlush(fav);
			}
		} catch (Exception e) {

			return new ResponseEntity<Favourites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Favourites>(HttpStatus.ACCEPTED);
	}

	public List<Favourites> getAllFavourites(Favourites fav) {

		User userObj = this.userRepo.getEmail(fav.getUseremail());
		List<Favourites> favList = this.favouritesRepo.getEmail(fav.getUseremail());
		return favList;
	}
}
