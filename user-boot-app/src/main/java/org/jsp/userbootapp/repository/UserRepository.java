package org.jsp.userbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByName(String name);
	Optional<User> findByphone(long phone);
	Optional<User> findByPhoneAndPassword(long phone, String password);

}
