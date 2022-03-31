package com.musix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.musix.model.User;
import com.musix.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userRepo;

	public boolean isValidRegistrationForm(User user) {

		boolean isPasswordPresent = user.getPassword().length() > 0;
		boolean isEmailPresent = user.getEmail().length() > 0;
		boolean isNamePresent = user.getName().length() > 0;

		return isPasswordPresent && isEmailPresent && isNamePresent;
	}

	public String hashPassword(String password) {
		// todo: use BCrypt or Scrypt to store the Hashed Password.

		return password;
	}

	public boolean validateUser(User user) {

		boolean isValidRegistrationForm = isValidRegistrationForm(user);
		String email = user.getEmail();
		User userObj = userRepo.getEmail(email);

		return isValidRegistrationForm && userObj == null;
	}

	public ResponseEntity<User> registerUser(User user) {

		try {
			boolean isValidNewUser = validateUser(user);

			if (isValidNewUser) {
				String hashedPassword = hashPassword(user.getPassword());
				user.setPassword(hashedPassword);
				this.userRepo.saveAndFlush(user);
			} else {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
	}

	public User getUser(String username) {
		User user = this.userRepo.findByEmail(username);
		return user;
	}

	public void saveUser(User user) {
		this.userRepo.save(user);
	}

	public boolean isLoggedIn(String email) {

		User user = this.userRepo.findByEmail(email);
		if (user.getSecretKey() != null && user.getSecretKey().length() > 0) {
			return true;
		}
		return false;
	}

	public void handleLogout(String email) {
		User user = this.userRepo.findByEmail(email);
		user.setSecretKey("");
		this.userRepo.saveAndFlush(user);
	}

	public ResponseEntity<User> getUserDetails(String email) {
		User userObj;
		try {
			userObj = this.userRepo.findByEmail(email);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(userObj, HttpStatus.OK);
	}

	public ResponseEntity<User> updateUserProfile(User user) {

		User userObj = null;
		try {
			userObj = this.userRepo.findByEmail(user.getEmail());
			userObj.setDisplayPicture(user.getDisplayPicture());

			if (user.getPassword().length() > 0) {
				String hashedPassword = hashPassword(user.getPassword());
				userObj.setPassword(hashedPassword);
			}

			userRepo.saveAndFlush(userObj);

		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}

}
