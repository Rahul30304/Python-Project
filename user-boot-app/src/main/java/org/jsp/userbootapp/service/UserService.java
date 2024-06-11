package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public ResponseStructure<User> saveUser( User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("User saved");
		structure.setData(userDao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(userDao.findAll());
		structure.setMessage("List of All Users");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(id);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<User>> updateUser( User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(user.getId());
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setAge(user.getAge());
			dbUser.setName(user.getName());
			dbUser.setEmail(user.getEmail());
			dbUser.setGender(user.getGender());
			dbUser.setPassword(user.getPassword());
			dbUser.setPhone(user.getPhone());
			structure.setData(userDao.saveUser(dbUser));
			structure.setMessage("User Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new UserNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser( int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(id);
		if (recUser.isPresent()) {
			structure.setData("User Found");
			structure.setMessage("User deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			userDao.deleteUser(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("User not found");
		structure.setMessage("Cannot delete User");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<User>>> findByName(String name) {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findByName(name);
		structure.setData(users);
		if (users.size() > 0) {
			structure.setMessage("List of Users with the entered name");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid User Name");
	}

	public ResponseEntity<ResponseStructure<User>> findByPhone(long phone) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findByPhone(phone);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number");
	}

	public ResponseEntity<ResponseStructure<User>> verify( long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verify(phone, password);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("Verification Succefull");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number or Password");
	}

}
