package com.cts.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
//
	@Query("select u from User u where u.username = :username")
	public User getUserByUsername(String username);

	Optional<User> findByemailId(String emailID);

	Optional<User> findByphoneNumber(String phoneNumber);

	Optional<User> findByusername(String username);

}
