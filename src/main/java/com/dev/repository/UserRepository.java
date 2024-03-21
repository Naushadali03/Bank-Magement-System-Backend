package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmailOrAadharOrPan(String email,String aadhar,String pan);
	public Optional<User> findIdByPan(String pan);
}
