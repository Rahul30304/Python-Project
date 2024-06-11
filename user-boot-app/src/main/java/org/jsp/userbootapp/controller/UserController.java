package org.jsp.userbootapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.repository.UserRepository;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name = "id") int id) {
		return userService.findById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable(name = "id") int id) {
		return userService.deleteUser(id);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<User>>> findByName(@PathVariable(name = "name") String name) {
		return userService.findByName(name);
	}

	@GetMapping("/find-by-phone/{phone}")
	public ResponseEntity<ResponseStructure<User>> findByPhone(@PathVariable(name = "phone") long phone) {
		return userService.findByPhone(phone);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam(name = "phone") long phone,
			@RequestParam(name = "password") String password) {
		return userService.verify(phone, password);
	}

}
