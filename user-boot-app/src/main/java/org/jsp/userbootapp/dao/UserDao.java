package org.jsp.userbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public List<User> findByName(String name){
		return userRepository.findByName(name);
	}
	
	public Optional<User> findByPhone(long phone) {
		return userRepository.findByphone(phone);
	}
	
	public Optional<User> verify(long phone, String password){
		return userRepository.findByPhoneAndPassword(phone, password);
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	public void deleteUser(int id) {
		 userRepository.deleteById(id);
	}
	

}
