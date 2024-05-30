package me.alfrendosilalahi.jobportal.repository;

import me.alfrendosilalahi.jobportal.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
