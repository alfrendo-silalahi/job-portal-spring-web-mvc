package me.alfrendosilalahi.jobportal.repository;

import me.alfrendosilalahi.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
